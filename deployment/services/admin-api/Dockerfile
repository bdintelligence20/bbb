FROM maven:3.8-openjdk-8 AS builder
WORKDIR /app
COPY ./beiqi-service-master/beiqi-service-master .
RUN mvn clean package -DskipTests -pl ruoyi-admin -am

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/ruoyi-admin/target/*.jar app.jar
EXPOSE 6868
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
