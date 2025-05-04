# Use a simple nginx image to serve static files
FROM nginx:1.21-alpine

# Copy the nginx configuration
COPY deployment/nginx/conf.d/management-panel.conf /etc/nginx/conf.d/default.conf

# Create a directory for the static files
RUN mkdir -p /usr/share/nginx/html

# We'll mount the source code volume at runtime
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
