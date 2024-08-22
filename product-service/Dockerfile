FROM openjdk:17-jdk-alpine

VOLUME /tmp

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "product-service.jar"]
