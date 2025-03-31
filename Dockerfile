
FROM maven:3.9.9-eclipse-temurin-21 AS build


WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:21

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

