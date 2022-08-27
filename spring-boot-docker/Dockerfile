FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY opentelemetry-javaagent.jar opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-jar", "/app.jar"]
#ENTRYPOINT ["java","-jar","/app.jar"]


#FROM maven:3.8.6-eclipse-temurin-17-alpine as BUILDER
#WORKDIR /app
#COPY pom.xml .
#RUN mvn dependency:resolve
#COPY src /app/src#
#RUN mvn -f pom.xml package -DskipTests


#FROM eclipse-temurin:17-jre-alpine as APPLICATION
#COPY --from=BUILDER /app/target/spring-boot-docker-0.0.1-SNAPSHOT.jar /app/todo-0.0.1-SNAPSHOT.jar
#ENV dbhost="localhost"
#ENV dbport="5432"
#ENV db="todo"
#ENV POSTGRES_PASSWORD="root1"
#ENV POSTGRES_USER="postgres"
#ENTRYPOINT ["java", "-jar", "/app/todo-0.0.1-SNAPSHOT.jar"]