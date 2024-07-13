FROM ubuntu:latest
LABEL authors="98173"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/SA58_LUSHUWEN_Fibonacci-1.0-SNAPSHOT.jar app.jar
COPY config.yml .

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "server", "config.yml"]