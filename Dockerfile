FROM openjdk:11
EXPOSE 9091
COPY ./target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
ENTRYPOINT ["java","-jar","gestion-station-ski-1.0.jar"]