FROM openjdk8
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
VOLUME /tmp
ADD target/dockerplugindemo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 9999
