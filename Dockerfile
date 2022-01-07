FROM openjdk:11-jre-slim
ARG JAR_FILE=./build/libs/DiabeteRiskMSA-1.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","app.jar"]