# BAIC Global Codebase Deployment Guide

This guide provides detailed instructions for deploying the BAIC Global Codebase on a Google Cloud Platform VM using Docker and Cloud Build.

## Overview

The deployment solution consists of:

1. Docker containers for each service
2. Cloud Build configuration for automated builds
3. Nginx as a reverse proxy
4. MySQL and Redis for data storage and caching
5. Scripts for setting up the VM and deploying the application

## Your VM Details

- **VM Name**: beiqi-vm
- **Zone**: us-central1-a
- **External IP**: 34.136.197.42
- **Internal IP**: 10.128.0.9
- **OS**: Ubuntu 24.04 LTS Minimal
- **Disk Size**: 20 GB

## Deployment Files

The deployment solution includes the following files:

- `docker-compose.yml`: Orchestrates all services
- `services/*/Dockerfile`: Dockerfiles for each service
- `nginx/nginx.conf` and `nginx/conf.d/default.conf`: Nginx configuration
- `mysql/init/01-init.sh`: Script to download and import the SQL dump
- `setup-vm.sh`: Script to set up the VM and deploy the application
- `cloudbuild.yaml`: Cloud Build configuration
- `.env.template`: Template for environment variables
- `README.md`: Comprehensive documentation

## Deployment Steps

### Option 1: Using Google Cloud Build (Recommended)

1. **Set up GCP Project**:
   - Create a GCP project if you don't have one
   - Enable Cloud Build, Container Registry, Compute Engine, and Cloud Storage APIs
   - Create a Cloud Storage bucket for deployment files:
     ```bash
     gsutil mb gs://YOUR_PROJECT_ID-beiqi-deploy
     ```
   - Grant the Cloud Build service account the necessary permissions

2. **Prepare the VM**:
   - Install Docker and Docker Compose:
     ```bash
     sudo apt update
     sudo apt install -y apt-transport-https ca-certificates curl software-properties-common
     curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
     sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
     sudo apt update
     sudo apt install -y docker-ce docker-compose
     sudo usermod -aG docker $USER
     ```

3. **Configure Deployment**:
   - Update the `.env.template` file with your desired configuration values
   - Create a `.env` file from the template:
     ```bash
     cp .env.template .env
     ```
   - Edit the `.env` file with your desired configuration values:
     ```bash
     nano .env
     ```

4. **Submit the Build**:
   - Submit the build to Cloud Build:
     ```bash
     gcloud builds submit --config=cloudbuild.yaml .
     ```

5. **Verify Deployment**:
   - Access the application at http://34.136.197.42/
   - Check if all services are running:
     ```bash
     docker-compose ps
     ```

### Option 2: Manual Deployment

1. **Prepare the VM** as described above.

2. **Copy Deployment Files**:
   - Copy the deployment files to the VM:
     ```bash
     gcloud compute scp --recurse deployment/* beiqi-vm:~/beiqi-deploy
     ```

3. **SSH into the VM**:
   ```bash
   gcloud compute ssh beiqi-vm
   ```

4. **Deploy the Application**:
   - Navigate to the deployment directory:
     ```bash
     cd ~/beiqi-deploy
     ```
   - Create and configure the `.env` file:
     ```bash
     cp .env.template .env
     nano .env
     ```
   - Run the setup script:
     ```bash
     bash setup-vm.sh
     ```

5. **Verify Deployment** as described above.

## SQL Database

The SQL database dump is stored in a GCS bucket at:
```
gs://baic-sql-files-457613/baicintl_backup.sql
```

The deployment process handles the SQL dump as follows:

1. During VM setup, the `setup-vm.sh` script downloads the SQL dump from the GCS bucket to `/opt/beiqi/mysql/init/dump.sql`
2. When the MySQL container starts, it automatically runs any SQL files in the `/docker-entrypoint-initdb.d/` directory
3. The `01-init.sh` script in that directory imports the SQL dump into the MySQL database
4. The database is configured using the values from the `.env` file (MYSQL_DATABASE, MYSQL_USER, etc.)

You don't need to manually import the SQL dump - this process is fully automated. Just make sure the database name in the `.env` file matches the one expected by the application.

## Accessing the Services

Once deployed, you can access the services at:

- **Website**: http://34.136.197.42/
- **Management Panel**: http://34.136.197.42/manage-panel-path/
- **Admin API**: http://34.136.197.42/api/
- **Web API**: http://34.136.197.42/home-api/
- **GeoIP Service**: http://34.136.197.42/geoip/

## Production Considerations

For a production deployment, consider the following:

1. **Domain Name**: Set up a domain name and configure it to point to your VM's IP address.

2. **SSL Certificates**: Set up SSL certificates using Let's Encrypt:
   ```bash
   sudo apt install certbot python3-certbot-nginx
   sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com
   ```

3. **Backups**: Configure regular backups of the MySQL database:
   ```bash
   # Add to crontab
   0 0 * * * docker exec beiqi_mysql mysqldump -uroot -p${MYSQL_ROOT_PASSWORD} --all-databases > /backup/mysql_backup_$(date +%Y%m%d).sql
   ```

4. **Monitoring**: Set up monitoring and alerting for the VM and services.

5. **CI/CD**: Implement a CI/CD pipeline for automated deployments.

## Troubleshooting

If you encounter any issues during deployment, check the following:

1. **Docker Logs**:
   ```bash
   docker-compose logs [service_name]
   ```

2. **VM Logs**:
   ```bash
   sudo journalctl -xe
   ```

3. **Cloud Build Logs**:
   ```bash
   gcloud builds list
   gcloud builds log BUILD_ID
   ```

4. **Firewall Rules**: Ensure all required ports are open in the GCP firewall rules.

5. **Database Import**: Check that the SQL dump was imported correctly:
   ```bash
   docker exec -it beiqi_mysql mysql -uroot -p${MYSQL_ROOT_PASSWORD} -e "SHOW DATABASES;"
   ```

## Support

If you need further assistance, please refer to the detailed documentation in the `README.md` file or contact your system administrator.
