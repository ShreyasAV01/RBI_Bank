#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Execute the application (the command used to run the application from jar ie "java -jar target/accounts-0.0.1-SNAPSHOT.jar")
ENTRYPOINT ["java" ,"-jar", "accounts-0.0.1-SNAPSHOT.jar"]
