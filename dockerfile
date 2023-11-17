FROM openjdk:8
ADD /target/SkiStationProject-2.1.jar app.jar
EXPOSE 9091
CMD ["/bin/sh", "-c", "sleep 30 && java -jar app.jar"]
