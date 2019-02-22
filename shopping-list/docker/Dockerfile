FROM bluecap-registry.intapp.eu/be-public-pro-registry/rhel:7

RUN yum install -y java-1.8.0-openjdk \
    && yum clean all \
    && rm -rf /var/cach/yum

ADD ./target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]