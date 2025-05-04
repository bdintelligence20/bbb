FROM maven:3.8-openjdk-8 AS builder
WORKDIR /app
COPY ./beiqi-service-master/beiqi-service-master .

# Update database and Redis configuration
RUN sed -i 's|url: jdbc:mysql://HOST:3306/baicintl|url: jdbc:mysql://mysql:3306/baicintl|g' ruoyi-web/src/main/resources/application-production.yml && \
    sed -i 's|nodes: HOST:6379|nodes: redis:6379|g' ruoyi-web/src/main/resources/application-production.yml && \
    sed -i 's|password:|password: ${REDIS_PASSWORD}|g' ruoyi-web/src/main/resources/application-production.yml && \
    sed -i 's|username: root|username: ${MYSQL_USER}|g' ruoyi-web/src/main/resources/application-production.yml && \
    sed -i 's|password: PASSWORD|password: ${MYSQL_PASSWORD}|g' ruoyi-web/src/main/resources/application-production.yml

# Build the application
RUN mvn clean package -DskipTests -pl ruoyi-web -am

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/ruoyi-web/target/*.jar app.jar
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=production"]
