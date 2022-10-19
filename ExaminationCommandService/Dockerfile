FROM maven:3.8.6-eclipse-temurin-17

COPY . /app/src

WORKDIR /app/src

RUN mvn --ntp clean install -DskipTests

CMD ["java", "-jar", "./target/ExaminationCommandService-0.0.1-SNAPSHOT.jar"]