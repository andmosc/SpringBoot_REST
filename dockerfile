FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

COPY target/authorizationService-0.0.1-SNAPSHOT.jar authorizationService.jar

CMD ["java", "-jar", "authorizationService.jar"]