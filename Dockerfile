FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring 
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eCommerce-0.0.1.jar
ENTRYPOINT ["java","-jar","/eCommerce-0.0.1.jar"]