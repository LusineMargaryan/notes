FROM openjdk:8
ADD notes-0.0.1.jar notes.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","notes.jar"]