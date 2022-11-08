FROM openjdk:11-jdk-slim
VOLUME /tmp
EXPOSE 9411
ADD ./zipkin-server-2.23.19-exec.jar zipkin-server.jar
ENTRYPOINT ["java", "-jar", "/zipkin-server.jar"]