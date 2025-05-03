FROM maven:3.8-openjdk-8 AS builder
WORKDIR /app
COPY ./beiqi-service-master/beiqi-service-master .
RUN mvn clean package -DskipTests -pl ruoyi-web -am

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/ruoyi-web/target/*.jar app.jar
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
