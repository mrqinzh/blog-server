# 基础镜像
FROM openjdk:8-jdk-alpine

# 工作目录
VOLUME /app

ADD target/service-comment-0.0.1-SNAPSHOT.jar /app/

EXPOSE 12221

ENTRYPOINT ["java","-jar","/app/service-comment-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
