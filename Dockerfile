FROM openjdk:15-jdk-alpine
<<<<<<< HEAD
LABEL maintainer dieuj@sheridancollege.ca
COPY target/capstone-0.0.1-SNAPSHOT.jar capstone.jar
ENTRYPOINT ["java","-jar","capstone.jar"]
=======
LABEL maintainer huara@sheridancollege.ca
COPY target/HelloDocker-0.0.1-SNAPSHOT.jar hellodocker.jar
ENTRYPOINT ["java","-jar","hellodocker.jar"]
>>>>>>> branch 'main' of https://github.com/Sheridan-Capstone/Microloans
