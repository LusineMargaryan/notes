FROM openjdk:8
ADD target/notes-0.0.1-SNAPSHOT.jar notes.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","notes.jar"]