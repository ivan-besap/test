FROM gradle:8.7-jdk17-alpine

COPY . .

EXPOSE 8088

RUN gradle build

ENTRYPOINT ["java", "-jar", "build/libs/eVolGreen-0.0.1-SNAPSHOT.jar"]

