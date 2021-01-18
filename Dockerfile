FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/ProjectDocker-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]