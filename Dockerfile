FROM openjdk:17-oracle
COPY build/libs/back-service-0.0.1-SNAPSHOT.jar /applicationBS.jar
ENTRYPOINT ["java", "-jar", "applicationBS.jar"]