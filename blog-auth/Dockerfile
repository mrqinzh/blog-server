# 基础镜像
FROM openjdk:17-jdk-alpine

VOLUME /app

ADD target/blog-auth-0.0.1-SNAPSHOT.jar /app/

EXPOSE 10000

ENTRYPOINT ["java","-jar","/app/blog-auth-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]