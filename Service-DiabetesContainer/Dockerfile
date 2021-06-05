FROM openjdk:15-jdk-alpine as build 
ARG JAR_FILE=target/*.jar 
COPY Project09-Service-Diabetes-1.0.0.jar Service-Diabetes-1.0.0.jar 
ENTRYPOINT ["java","-jar","/Service-Diabetes-1.0.0.jar"]
