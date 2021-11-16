FROM openjdk:11-jdk-windowsservercore-ltsc2022
ADD target/cms-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar cms-0.0.1-SNAPSHOT.jar
