FROM openjdk:15-jdk-alpine
LABEL maintainer dieuj@sheridancollege.ca
COPY target/capstone-0.0.1-SNAPSHOT.jar capstone.jar
ENTRYPOINT ["java","-jar","capstone.jar"]

