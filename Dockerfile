FROM openjdk:10-jre-slim
COPY ./target/shopie-0.0.1-SNAPSHOT.jar /usr/src/shopie/
WORKDIR /usr/src/shopie
EXPOSE 8080
CMD ["java", "-jar", "shopie-0.0.1-SNAPSHOT.jar"]