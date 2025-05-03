#!/bin/bash
set -e

echo "Setting up BAIC Global Codebase deployment environment..."

# Create directory structure
mkdir -p /opt/beiqi/nginx/conf.d
mkdir -p /opt/beiqi/mysql/data
mkdir -p /opt/beiqi/mysql/init
mkdir -p /opt/beiqi/redis/data
mkdir -p /opt/beiqi/uploads
mkdir -p /opt/beiqi/services

# Set permissions for uploads directory
chmod 755 /opt/beiqi/uploads

# Download SQL dump from GCS bucket
echo "Downloading SQL dump from GCS bucket..."
echo "This SQL dump will be automatically imported into the MySQL database during container startup"
gsutil cp gs://baic-sql-files-457613/baicintl_backup.sql /opt/beiqi/mysql/init/dump.sql
echo "SQL dump downloaded successfully to /opt/beiqi/mysql/init/dump.sql"

# Copy configuration files
echo "Copying configuration files..."
cp -r nginx /opt/beiqi/
cp -r mysql/init /opt/beiqi/mysql/
cp docker-compose.yml /opt/beiqi/

# Create .env file from template if it doesn't exist
if [ ! -f "/opt/beiqi/.env" ]; then
  cp .env.template /opt/beiqi/.env
  echo "Created .env file from template. Please edit with secure passwords."
else
  echo ".env file already exists, not overwriting."
fi

# Pull and start containers
echo "Starting Docker containers..."
cd /opt/beiqi
docker-compose pull
docker-compose up -d

echo "Deployment completed successfully!"
echo "You can access the application at http://34.136.197.42"
echo "Remember to configure your domain name and set up SSL certificates for production use."
