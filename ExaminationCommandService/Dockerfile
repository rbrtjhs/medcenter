FROM maven:3.8.6-eclipse-temurin-17-alpine

RUN mkdir /app

COPY ./target/ExaminationCommandService-0.0.1-SNAPSHOT.jar /app

WORKDIR /app

CMD ["java", "-jar", "ExaminationCommandService-0.0.1-SNAPSHOT.jar"]