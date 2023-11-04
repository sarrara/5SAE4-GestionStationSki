FROM openjdk:11
WORKDIR /app
ADD target/*.jar app.jar
EXPOSE 9091
CMD ["java","-jar","/app/app.jar"]