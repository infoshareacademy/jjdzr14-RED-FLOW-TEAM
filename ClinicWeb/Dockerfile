FROM ubuntu:latest
LABEL authors="Grzegorz"
FROM openjdk:22
WORKDIR /ClinicWeb
COPY . /ClinicWeb
RUN ./mvn clean install

CMD ["java", "-jar", "target/your-app.jar"]
ENTRYPOINT ["top", "-b"]