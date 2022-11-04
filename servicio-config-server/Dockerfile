#Dockerfile from servicio-config-server with jdk11 maven
#First stage
FROM openjdk:11-jdk-slim as builder
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY src src
RUN ./mvnw clean package -DskipTests

#Second stage
FROM openjdk:11-jdk-slim
ARG TARGET_FOLDER=/app/target
COPY --from=builder $TARGET_FOLDER/servicio-config-server-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8888
ENV PORT $PORT_APP
EXPOSE $PORT
CMD ["java", "-jar", "servicio-config-server-0.0.1-SNAPSHOT.jar"]

