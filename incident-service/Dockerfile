FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} incident-service.jar

ENTRYPOINT ["java", "-jar", "/incident-service.jar"]

EXPOSE 8082
