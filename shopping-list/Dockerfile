# BUILD image
FROM maven:3-jdk-8-alpine as builder
LABEL maintainer="joeri.jackers@gmail.com"

COPY src/ /opt/src/
COPY pom.xml /opt/
WORKDIR /opt

RUN mvn -Dmaven.test.skip=true package

# RUN image
FROM openjdk:8-jre-alpine
COPY --from=builder /opt/target/shopping-list-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 8080
EXPOSE 8443

CMD ["java", "-jar", "/opt/app.jar"]