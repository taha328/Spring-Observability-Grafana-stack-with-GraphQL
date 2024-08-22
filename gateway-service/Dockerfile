FROM openjdk:17-jdk-alpine

VOLUME /tmp

COPY target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
