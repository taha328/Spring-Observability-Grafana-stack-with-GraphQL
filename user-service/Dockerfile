FROM openjdk:17-jdk-alpine

VOLUME /tmp

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "user-service.jar"]
