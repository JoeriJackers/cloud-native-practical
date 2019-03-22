FROM maven:3-jdk-8-alpine as builder
LABEL maintainer="joeri.jackers@gmail.com"

COPY src/ /opt/src/
COPY pom.xml /opt/
RUN ls -lah /opt
WORKDIR /opt

#RUN mvn -Dmaven.test.skip=true package
#
FROM openjdk:8-jre-alpine
#
#ENV ENV "DEV"
COPY --from=builder /opt/target/*.jar /opt/app.jar
#
EXPOSE 8080
EXPOSE 8443

CMD ["java", "-jar", "/opt/app.jar"]