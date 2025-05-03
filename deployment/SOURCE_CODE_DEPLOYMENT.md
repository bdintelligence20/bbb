# Source Code Deployment Guide

This guide explains how to deploy both the deployment configuration and the source code to your VM.

## Understanding the Deployment Process

The deployment solution consists of two main components:

1. **Deployment Configuration**: The Docker, Nginx, and other configuration files in the `deployment` directory.
2. **Source Code**: The actual application code in the `beiqi-home-master`, `beiqi-service-master`, `beiqi-web-master`, and `beiqi-geoip` directories.

Both components are necessary for a successful deployment. The Dockerfiles in the deployment configuration reference the source code directories to build the Docker images.

## Option 1: Transfer Everything Together

The simplest approach is to transfer both the deployment configuration and the source code to the VM together.

### Directory Structure

Your local directory structure should look like this:

```
baic-global-codebase/
├── deployment/
│   ├── docker-compose.yml
│   ├── services/
│   │   ├── website/
│   │   │   └── Dockerfile
│   │   ├── management-panel/
│   │   │   └── Dockerfile
│   │   ├── admin-api/
│   │   │   └── Dockerfile
│   │   ├── web-api/
│   │   │   └── Dockerfile
│   │   └── geoip/
│   │       └── Dockerfile
│   ├── nginx/
│   │   ├── nginx.conf
│   │   └── conf.d/
│   │       └── default.conf
│   └── mysql/
│       └── init/
│           └── 01-init.sh
├── beiqi-home-master/
│   └── beiqi-home-master/
│       └── ... (source code)
├── beiqi-service-master/
│   └── beiqi-service-master/
│       └── ... (source code)
├── beiqi-web-master/
│   └── beiqi-web-master/
│       └── ... (source code)
└── beiqi-geoip/
    └── beiqi-geoip/
        └── ... (source code)
```

### Steps

1. **Compress everything**:
   ```bash
   # On your local machine
   cd /path/to/baic-global-codebase
   zip -r baic-global-codebase.zip *
   ```

2. **Transfer the zip file to the VM**:
   ```bash
   # Using SCP
   scp baic-global-codebase.zip username@34.136.197.42:~/
   ```

3. **SSH into the VM**:
   ```bash
   ssh username@34.136.197.42
   ```

4. **Extract and deploy**:
   ```bash
   # On the VM
   mkdir -p ~/baic-global-codebase
   cd ~/baic-global-codebase
   unzip ~/baic-global-codebase.zip
   cd deployment
   bash setup-vm.sh
   ```

## Option 2: Modify Dockerfiles to Pull Source Code

If you prefer not to transfer the source code to the VM, you can modify the Dockerfiles to pull the source code from a Git repository or other source.

### Prerequisites

- Source code hosted in a Git repository (e.g., GitHub, GitLab, Bitbucket)
- Git installed on the VM

### Steps

1. **Modify the Dockerfiles**:

   **Website Dockerfile** (`deployment/services/website/Dockerfile`):
   ```dockerfile
   FROM node:16 AS builder
   WORKDIR /app
   
   # Clone the repository instead of copying local files
   RUN git clone https://github.com/your-username/beiqi-home-master.git .
   # Optionally checkout a specific branch or tag
   # RUN git checkout main
   
   RUN npm install
   RUN npm run generate

   FROM nginx:1.21-alpine
   COPY --from=builder /app/dist /usr/share/nginx/html
   EXPOSE 80
   CMD ["nginx", "-g", "daemon off;"]
   ```

   Make similar changes to the other Dockerfiles.

2. **Transfer only the deployment configuration**:
   ```bash
   # On your local machine
   cd /path/to/baic-global-codebase
   zip -r deployment.zip deployment
   scp deployment.zip username@34.136.197.42:~/
   ```

3. **SSH into the VM**:
   ```bash
   ssh username@34.136.197.42
   ```

4. **Extract and deploy**:
   ```bash
   # On the VM
   mkdir -p ~/baic-global-codebase
   cd ~/baic-global-codebase
   unzip ~/deployment.zip
   cd deployment
   bash setup-vm.sh
   ```

## Option 3: Use Docker Volumes for Source Code

You can use Docker volumes to mount the source code into the containers at runtime.

### Steps

1. **Modify the docker-compose.yml**:
   ```yaml
   services:
     # Website Service (Nuxt.js)
     website:
       build:
         context: ./services/website
       volumes:
         - /path/on/vm/to/beiqi-home-master:/app/src
         - website_dist:/app/dist
       environment:
         NODE_ENV: production
       restart: always
   ```

   Make similar changes to the other services.

2. **Modify the Dockerfiles**:

   **Website Dockerfile** (`deployment/services/website/Dockerfile`):
   ```dockerfile
   FROM node:16
   WORKDIR /app
   
   # Source code will be mounted at /app/src
   WORKDIR /app/src
   
   CMD ["sh", "-c", "npm install && npm run generate && cp -r dist/* /app/dist/"]
   ```

   Make similar changes to the other Dockerfiles.

3. **Transfer the source code and deployment configuration separately**:
   ```bash
   # On your local machine
   cd /path/to/baic-global-codebase
   
   # Transfer deployment configuration
   zip -r deployment.zip deployment
   scp deployment.zip username@34.136.197.42:~/
   
   # Transfer source code
   zip -r source-code.zip beiqi-home-master beiqi-service-master beiqi-web-master beiqi-geoip
   scp source-code.zip username@34.136.197.42:~/
   ```

4. **SSH into the VM**:
   ```bash
   ssh username@34.136.197.42
   ```

5. **Extract and deploy**:
   ```bash
   # On the VM
   mkdir -p ~/baic-global-codebase
   cd ~/baic-global-codebase
   
   # Extract deployment configuration
   unzip ~/deployment.zip
   
   # Extract source code
   unzip ~/source-code.zip
   
   # Deploy
   cd deployment
   bash setup-vm.sh
   ```

## Option 4: Use Pre-built Docker Images

If you have access to a Docker registry (e.g., Docker Hub, Google Container Registry), you can build the Docker images locally and push them to the registry. Then, modify the docker-compose.yml to pull the pre-built images.

### Steps

1. **Build and push Docker images**:
   ```bash
   # On your local machine
   cd /path/to/baic-global-codebase
   
   # Build and push website image
   docker build -t your-registry/beiqi-website:latest -f deployment/services/website/Dockerfile .
   docker push your-registry/beiqi-website:latest
   
   # Build and push management panel image
   docker build -t your-registry/beiqi-management:latest -f deployment/services/management-panel/Dockerfile .
   docker push your-registry/beiqi-management:latest
   
   # Build and push admin API image
   docker build -t your-registry/beiqi-admin-api:latest -f deployment/services/admin-api/Dockerfile .
   docker push your-registry/beiqi-admin-api:latest
   
   # Build and push web API image
   docker build -t your-registry/beiqi-web-api:latest -f deployment/services/web-api/Dockerfile .
   docker push your-registry/beiqi-web-api:latest
   
   # Build and push GeoIP image
   docker build -t your-registry/beiqi-geoip:latest -f deployment/services/geoip/Dockerfile .
   docker push your-registry/beiqi-geoip:latest
   ```

2. **Modify the docker-compose.yml**:
   ```yaml
   services:
     # Website Service (Nuxt.js)
     website:
       image: your-registry/beiqi-website:latest
       volumes:
         - website_dist:/usr/share/nginx/html
       restart: always
   ```

   Make similar changes to the other services.

3. **Transfer only the modified docker-compose.yml and other configuration files**:
   ```bash
   # On your local machine
   cd /path/to/baic-global-codebase
   zip -r deployment-config.zip deployment
   scp deployment-config.zip username@34.136.197.42:~/
   ```

4. **SSH into the VM**:
   ```bash
   ssh username@34.136.197.42
   ```

5. **Extract and deploy**:
   ```bash
   # On the VM
   mkdir -p ~/baic-global-codebase
   cd ~/baic-global-codebase
   unzip ~/deployment-config.zip
   cd deployment
   bash setup-vm.sh
   ```

## Recommended Approach

For simplicity and reliability, we recommend **Option 1: Transfer Everything Together**. This approach ensures that all the necessary files are available on the VM and minimizes the risk of deployment issues.

If you have concerns about the size of the transfer or prefer a different approach, please refer to the other options in this guide.
