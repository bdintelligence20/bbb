#!/bin/bash
set -e

# Check if SQL file exists locally first
if [ -f "/docker-entrypoint-initdb.d/dump.sql" ]; then
  echo "Using local SQL dump file"
  mysql -u root -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < /docker-entrypoint-initdb.d/dump.sql
else
  echo "Local SQL dump not found, attempting to download from GCS"
  # This requires the container to have gsutil installed or mounted credentials
  # In production, you might want to download this file during VM setup instead
  if command -v gsutil &> /dev/null; then
    gsutil cp gs://baic-sql-files-457613/baicintl_backup.sql /tmp/dump.sql
    mysql -u root -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < /tmp/dump.sql
    rm /tmp/dump.sql
  else
    echo "gsutil not available, cannot download SQL dump"
  fi
fi
