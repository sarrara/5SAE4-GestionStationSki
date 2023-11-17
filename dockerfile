FROM openjdk:11
ADD /target/gestion-station-ski-2.2.jar app.jar
EXPOSE 9091
CMD ["/bin/sh", "-c", "sleep 30 && java -jar app.jar"]
