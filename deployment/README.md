# BAIC Global Codebase Deployment

This directory contains all the necessary files to deploy the BAIC Global Codebase on a Google Cloud Platform VM using Docker and Cloud Build.

## Project Components

The project consists of several interconnected services:

1. **Website Service (beiqi-home-master)** - Nuxt.js 2 + Vue.js 2 application
2. **Management Panel (beiqi-web-master)** - Vue.js application
3. **Admin API Service (beiqi-service-master/ruoyi-admin)** - Java Spring Boot application
4. **Web API Service (beiqi-service-master/ruoyi-web)** - Java Spring Boot application
5. **GeoIP Service (beiqi-geoip)** - Node.js Express application
6. **MySQL Database** - For data storage
7. **Redis Cache** - For caching
8. **Nginx Gateway** - For routing traffic to the appropriate services

## Deployment Options

There are two ways to deploy this project:

### Option 1: Using Google Cloud Build (Recommended)

This option uses Google Cloud Build to build the Docker images and deploy them to a GCP VM.

#### Prerequisites

1. Create a GCP project and enable the following APIs:
   - Cloud Build API
   - Container Registry API
   - Compute Engine API
   - Cloud Storage API

2. Create a GCP VM instance with:
   - Ubuntu 24.04 LTS Minimal (as per your current VM)
   - At least 2 vCPUs and 4GB RAM
   - 20GB+ SSD storage (your current VM has 20GB)
   - Allow HTTP/HTTPS traffic
   - Static external IP address (your current VM has 34.136.197.42)

3. Install Docker and Docker Compose on the VM:
   ```bash
   sudo apt update
   sudo apt install -y apt-transport-https ca-certificates curl software-properties-common
   curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
   sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
   sudo apt update
   sudo apt install -y docker-ce docker-compose
   sudo usermod -aG docker $USER
   ```

4. Create a Cloud Storage bucket for deployment files:
   ```bash
   gsutil mb gs://YOUR_PROJECT_ID-beiqi-deploy
   ```

5. Grant the Cloud Build service account the necessary permissions:
   - Compute Instance Admin
   - Storage Admin
   - Service Account User

#### Deployment Steps

1. Update the `.env.template` file with your desired configuration values.

2. Update the `cloudbuild.yaml` file with your VM name and zone.

3. Submit the build to Cloud Build:
   ```bash
   gcloud builds submit --config=cloudbuild.yaml .
   ```

4. Cloud Build will:
   - Build all Docker images
   - Push them to Container Registry
   - Deploy them to your VM
   - Set up the necessary configuration

### Option 2: Manual Deployment

This option involves manually deploying the project on a GCP VM.

#### Prerequisites

1. Create a GCP VM instance as described above.

2. Install Docker and Docker Compose on the VM as described above.

#### Deployment Steps

1. Copy the deployment files to the VM:
   ```bash
   gcloud compute scp --recurse deployment/* YOUR_VM_NAME:~/beiqi-deploy
   ```

2. SSH into the VM:
   ```bash
   gcloud compute ssh YOUR_VM_NAME
   ```

3. Navigate to the deployment directory:
   ```bash
   cd ~/beiqi-deploy
   ```

4. Create a `.env` file from the template:
   ```bash
   cp .env.template .env
   ```

5. Edit the `.env` file with your desired configuration values:
   ```bash
   nano .env
   ```

6. Run the setup script:
   ```bash
   bash setup-vm.sh
   ```

## SQL Database

The SQL database dump is stored in a GCS bucket at:
```
gs://baic-sql-files-457613/baicintl_backup.sql
```

The deployment process will automatically download and import this SQL dump into the MySQL database.

## Accessing the Application

Once deployed, you can access the application at:

- Website: http://34.136.197.42/
- Management Panel: http://34.136.197.42/manage-panel-path/
- Admin API: http://34.136.197.42/api/
- Web API: http://34.136.197.42/home-api/
- GeoIP Service: http://34.136.197.42/geoip/

## Production Considerations

For a production deployment, consider the following:

1. Set up a domain name and configure it to point to your VM's IP address.

2. Set up SSL certificates using Let's Encrypt:
   ```bash
   sudo apt install certbot python3-certbot-nginx
   sudo certbot --nginx -d yourdomain.com -d www.yourdomain.com
   ```

3. Configure regular backups of the MySQL database:
   ```bash
   # Add to crontab
   0 0 * * * docker exec beiqi_mysql mysqldump -uroot -p${MYSQL_ROOT_PASSWORD} --all-databases > /backup/mysql_backup_$(date +%Y%m%d).sql
   ```

4. Set up monitoring and alerting for the VM and services.

5. Implement a CI/CD pipeline for automated deployments.

## Troubleshooting

If you encounter any issues during deployment, check the following:

1. Docker container logs:
   ```bash
   docker-compose logs [service_name]
   ```

2. VM system logs:
   ```bash
   sudo journalctl -xe
   ```

3. Cloud Build logs (if using Cloud Build):
   ```bash
   gcloud builds list
   gcloud builds log BUILD_ID
   ```

4. Ensure all required ports are open in the GCP firewall rules.

5. Check that the SQL dump was imported correctly:
   ```bash
   docker exec -it beiqi_mysql mysql -uroot -p${MYSQL_ROOT_PASSWORD} -e "SHOW DATABASES;"
   ```
