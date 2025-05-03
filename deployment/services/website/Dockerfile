FROM node:16 AS builder
WORKDIR /app
COPY ./beiqi-home-master/beiqi-home-master .
RUN npm install
RUN npm run generate

FROM nginx:1.21-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
