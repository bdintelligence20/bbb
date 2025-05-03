FROM node:16-slim
WORKDIR /app
COPY ./beiqi-geoip/beiqi-geoip .
RUN npm install
EXPOSE 3000
CMD ["node", "app.js"]
