# Use Java 17 or 21 base image
FROM openjdk:17-jdk-slim

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Command to run the JAR
ENTRYPOINT ["java","-jar","/app.jar"]
