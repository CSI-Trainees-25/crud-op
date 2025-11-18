
# ================================
# Stage 1: Build the application
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy Maven configuration first (better layer caching)
COPY pom.xml .

# Download dependencies (cached unless pom.xml changes)
RUN mvn dependency:go-offline

# Copy the entire project
COPY src ./src

# Build the JAR file
RUN mvn -B clean package -DskipTests


# ================================
# Stage 2: Create the runtime image
# ================================
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Set Render PORT environment default
ENV PORT=8080

# Expose port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]

