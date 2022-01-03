FROM openjdk:11
ADD target/cms-0.0.1-SNAPSHOT.jar cms.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar","/cms.jar"]
