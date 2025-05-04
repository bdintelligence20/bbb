#!/bin/bash
set -e

echo "Setting up BAIC Global Codebase deployment environment..."

# Create directory structure with sudo
sudo mkdir -p /opt/beiqi/nginx/conf.d
sudo mkdir -p /opt/beiqi/mysql/data
sudo mkdir -p /opt/beiqi/mysql/init
sudo mkdir -p /opt/beiqi/redis/data
sudo mkdir -p /opt/beiqi/uploads
sudo mkdir -p /opt/beiqi/services
sudo mkdir -p /opt/beiqi/source/beiqi-home-master
sudo mkdir -p /opt/beiqi/source/beiqi-web-master

# Set permissions for uploads directory
sudo chmod 755 /opt/beiqi/uploads

# Make sure the cloudbuild user owns the directories
sudo chown -R $(whoami):$(whoami) /opt/beiqi

# Download SQL dump from GCS bucket
echo "Downloading SQL dump from GCS bucket..."
echo "This SQL dump will be automatically imported into the MySQL database during container startup"
gsutil cp gs://baic-sql-files-457613/baicintl_backup.sql /opt/beiqi/mysql/init/dump.sql
echo "SQL dump downloaded successfully to /opt/beiqi/mysql/init/dump.sql"

# Copy source code
echo "Copying source code..."
cp -r ../beiqi-home-master /opt/beiqi/source/
cp -r ../beiqi-web-master /opt/beiqi/source/
cp -r ../beiqi-service-master /opt/beiqi/source/
cp -r ../beiqi-geoip /opt/beiqi/source/

# Build the management panel
echo "Building management panel..."
cd /opt/beiqi/source/beiqi-web-master/beiqi-web-master
npm install
npm run build:prod

# Copy configuration files
echo "Copying configuration files..."
cp -r nginx /opt/beiqi/
cp -r mysql/init /opt/beiqi/mysql/
cp docker-compose.yml /opt/beiqi/

# Update docker-compose.yml paths
echo "Updating docker-compose.yml paths..."
sed -i 's|../beiqi-home-master|/opt/beiqi/source/beiqi-home-master|g' /opt/beiqi/docker-compose.yml
sed -i 's|../beiqi-web-master|/opt/beiqi/source/beiqi-web-master|g' /opt/beiqi/docker-compose.yml
sed -i 's|../beiqi-service-master|/opt/beiqi/source/beiqi-service-master|g' /opt/beiqi/docker-compose.yml
sed -i 's|../beiqi-geoip|/opt/beiqi/source/beiqi-geoip|g' /opt/beiqi/docker-compose.yml

# Create .env file from template if it doesn't exist
if [ ! -f "/opt/beiqi/.env" ]; then
  cp .env.template /opt/beiqi/.env
  echo "Created .env file from template. Please edit with secure passwords."
else
  echo ".env file already exists, not overwriting."
fi

# Make sure Docker can access all the files
sudo chown -R $(whoami):$(whoami) /opt/beiqi

# Pull and start containers
echo "Starting Docker containers..."
cd /opt/beiqi
docker-compose pull
docker-compose up -d

echo "Deployment completed successfully!"
echo "You can access the application at http://34.136.197.42"
echo "Remember to configure your domain name and set up SSL certificates for production use."
