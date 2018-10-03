FROM openjdk:10-jre-slim

ADD target/shopie-0.0.1-SNAPSHOT.jar shopie.jar

RUN bash -c 'touch /shopie.jar'

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","-jar","/shopie.jar"]


