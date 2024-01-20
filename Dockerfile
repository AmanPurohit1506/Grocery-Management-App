# Use an official OpenJDK runtime as a base image
FROM openjdk

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/grocery-0.0.1-SNAPSHOT.jar /app

# Specify the command to run on container startup
CMD ["java", "-jar", "grocery-0.0.1-SNAPSHOT.jar"]
