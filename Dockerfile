FROM openjdk:15-jdk-alpine
LABEL maintainer huara@sheridancollege.ca
COPY target/HelloDocker-0.0.1-SNAPSHOT.jar hellodocker.jar
ENTRYPOINT ["java","-jar","hellodocker.jar"]
