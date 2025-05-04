FROM node:16 AS builder
WORKDIR /app
COPY ./beiqi-home-master/beiqi-home-master .

# Set environment variables
ENV NODE_ENV=production
ENV HOME_API_URL=http://web-api:8080
ENV GEOIP_URL=http://geoip:3000

# Update proxy configuration in nuxt.config.js
RUN sed -i 's|http://192.168.1.141:8080|http://web-api:8080|g' nuxt.config.js && \
    sed -i 's|http://192.168.1.141:3000|http://geoip:3000|g' nuxt.config.js

# Install dependencies and build
RUN npm install
RUN npm run build
RUN npm run generate

FROM nginx:1.21-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
