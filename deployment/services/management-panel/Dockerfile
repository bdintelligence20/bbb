FROM node:16 AS builder
WORKDIR /app
COPY ./beiqi-web-master/beiqi-web-master .

# Set environment variables
ENV NODE_ENV=production
ENV VUE_APP_BASE_API=/api
ENV VUE_APP_FILE_UOLOAD_HOST=/file

# Create a custom .env.production file
RUN echo "port = '80'" > .env.production && \
    echo "ENV = 'production'" >> .env.production && \
    echo "VUE_APP_BASE_API = '/api'" >> .env.production && \
    echo "VUE_APP_FILE_UOLOAD_HOST = '/file'" >> .env.production && \
    echo "VUE_APP_TITLE = '北汽官网后台管理系统'" >> .env.production && \
    echo "VUE_APP_WEB_HOST = 'http://website'" >> .env.production

# Install dependencies and build
RUN npm install
RUN npm run build:prod

FROM nginx:1.21-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
