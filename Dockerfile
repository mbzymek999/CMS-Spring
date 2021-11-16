FROM openjdk:18-ea-alpine3.13
ADD target/cms-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar cms-0.0.1-SNAPSHOT.jar
