FROM openjdk:11
EXPOSE 8080
ADD target/cms-0.0.1-SNAPSHOT.jar cms.jar
ENTRYPOINT ["java", "-jar","/cms.jar"]
