FROM openjdk:13-jdk-alpine
MAINTAINER sathish vasu 
VOLUME /tmp
EXPOSE 8081
COPY /. /tmp/
ENTRYPOINT ["java","-jar","urlShortener-0.0.1-SNAPSHOT.jar"]

