# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -e -DskipTests package

# Runtime stage (FIXED FOR SOAP + JAVA 17)
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/course-service-1.0.0.jar /app/course-service.jar

EXPOSE 8083

CMD ["java", "--add-opens=java.base/java.lang=ALL-UNNAMED", "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED", "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED", "--add-opens=java.base/sun.reflect.annotation=ALL-UNNAMED", "-jar", "/app/course-service.jar"]
