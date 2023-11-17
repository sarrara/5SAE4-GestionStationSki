  # Use an official Node.js runtime as the base image
FROM openjdk:11

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install application dependencies


# Copy the rest of the application code to the container
COPY . .

# Expose the port the app runs on
EXPOSE 9091

# Define the command to run your application
CMD ["java","-jar","/app/app.jar"]
