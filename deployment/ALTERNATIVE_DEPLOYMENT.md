# Alternative Deployment Methods

This guide provides alternative methods for deploying the BAIC Global Codebase to your VM without using the Google Cloud CLI.

## Option 1: Using SCP (Secure Copy)

SCP is a command-line utility that allows you to securely copy files between hosts on a network.

### Prerequisites

- SSH access to your VM
- SSH key pair (if using key-based authentication)
- The deployment files on your local machine

### Steps

1. **Compress the deployment files**:
   ```bash
   # On your local machine
   cd /path/to/deployment
   zip -r deployment.zip *
   ```

2. **Transfer the files using SCP**:
   ```bash
   # On your local machine
   scp deployment.zip username@34.136.197.42:~/
   ```
   Replace `username` with your VM username.

3. **SSH into the VM**:
   ```bash
   # On your local machine
   ssh username@34.136.197.42
   ```

4. **Extract and deploy**:
   ```bash
   # On the VM
   mkdir -p ~/beiqi-deploy
   unzip deployment.zip -d ~/beiqi-deploy
   cd ~/beiqi-deploy
   bash setup-vm.sh
   ```

## Option 2: Using SFTP

SFTP (SSH File Transfer Protocol) is a secure file transfer protocol that provides file access, file transfer, and file management over a reliable data stream.

### Prerequisites

- SFTP client (e.g., FileZilla, WinSCP)
- SSH access to your VM

### Steps

1. **Connect to the VM using SFTP**:
   - Host: 34.136.197.42
   - Username: Your VM username
   - Password: Your VM password (or SSH key)
   - Port: 22 (default SSH port)

2. **Upload the deployment files**:
   - Navigate to the local directory containing the deployment files
   - Create a directory on the VM (e.g., `/home/username/beiqi-deploy`)
   - Upload all files to this directory

3. **SSH into the VM**:
   ```bash
   # On your local machine
   ssh username@34.136.197.42
   ```

4. **Run the setup script**:
   ```bash
   # On the VM
   cd ~/beiqi-deploy
   bash setup-vm.sh
   ```

## Option 3: Using a Web-based File Manager

If your VM has a web-based file manager installed (e.g., Webmin, cPanel), you can use it to upload files.

### Steps

1. **Access the web-based file manager**:
   - Open a web browser and navigate to the file manager URL
   - Log in with your credentials

2. **Upload the deployment files**:
   - Create a directory for the deployment files
   - Upload all files to this directory

3. **SSH into the VM**:
   ```bash
   # On your local machine
   ssh username@34.136.197.42
   ```

4. **Run the setup script**:
   ```bash
   # On the VM
   cd /path/to/uploaded/files
   bash setup-vm.sh
   ```

## Option 4: Using Git

If you have a Git repository containing the deployment files, you can clone it directly on the VM.

### Prerequisites

- Git installed on the VM
- A Git repository containing the deployment files

### Steps

1. **SSH into the VM**:
   ```bash
   # On your local machine
   ssh username@34.136.197.42
   ```

2. **Clone the repository**:
   ```bash
   # On the VM
   git clone https://github.com/your-username/your-repo.git beiqi-deploy
   cd beiqi-deploy
   ```

3. **Run the setup script**:
   ```bash
   # On the VM
   bash setup-vm.sh
   ```

## Option 5: Using Google Cloud Storage Web Console

You can upload files to Google Cloud Storage using the web console and then download them to your VM.

### Steps

1. **Upload files to Google Cloud Storage**:
   - Go to the [Google Cloud Console](https://console.cloud.google.com/)
   - Navigate to Storage > Browser
   - Create a bucket or use an existing one
   - Upload the deployment files to the bucket

2. **SSH into the VM**:
   ```bash
   # On your local machine
   ssh username@34.136.197.42
   ```

3. **Download the files from Google Cloud Storage**:
   ```bash
   # On the VM
   # Install gsutil if not already installed
   curl https://sdk.cloud.google.com | bash
   exec -l $SHELL
   gcloud init

   # Download the files
   mkdir -p ~/beiqi-deploy
   gsutil -m cp -r gs://your-bucket-name/* ~/beiqi-deploy/
   cd ~/beiqi-deploy
   bash setup-vm.sh
   ```

## Modifying the SQL Dump Path

If you're not using Google Cloud Storage for the SQL dump, you'll need to modify the `setup-vm.sh` script to download the SQL dump from your preferred location.

1. **Edit the setup-vm.sh file**:
   ```bash
   # On the VM
   cd ~/beiqi-deploy
   nano setup-vm.sh
   ```

2. **Modify the SQL dump download section**:
   ```bash
   # Replace this line:
   gsutil cp gs://baic-sql-files-457613/baicintl_backup.sql /opt/beiqi/mysql/init/dump.sql

   # With one of these, depending on where your SQL dump is located:
   # If the SQL dump is on your local machine and you've uploaded it to the VM:
   cp /path/to/local/baicintl_backup.sql /opt/beiqi/mysql/init/dump.sql

   # If the SQL dump is available via HTTP:
   curl -o /opt/beiqi/mysql/init/dump.sql http://your-server.com/path/to/baicintl_backup.sql

   # If the SQL dump is in a different GCS bucket:
   gsutil cp gs://your-bucket-name/baicintl_backup.sql /opt/beiqi/mysql/init/dump.sql
   ```

3. **Save the file and run the setup script**:
   ```bash
   bash setup-vm.sh
   ```

## Troubleshooting

If you encounter any issues during the deployment process, check the following:

1. **File permissions**:
   ```bash
   # Make sure the setup script is executable
   chmod +x setup-vm.sh
   ```

2. **SSH connection issues**:
   - Verify that you have the correct IP address and credentials
   - Check if the VM's firewall allows SSH connections

3. **SQL dump download issues**:
   - Verify that the SQL dump is accessible from the VM
   - Check if you have the necessary permissions to access the SQL dump

4. **Docker and Docker Compose installation**:
   ```bash
   # Check if Docker is installed
   docker --version

   # Check if Docker Compose is installed
   docker-compose --version

   # If not installed, install them
   sudo apt update
   sudo apt install -y docker.io docker-compose
   sudo usermod -aG docker $USER
   ```
