# 基础镜像
FROM openjdk:17-jdk-alpine

# 工作目录
VOLUME /app

ADD target/service-article-0.0.1-SNAPSHOT.jar /app/

EXPOSE 11111

ENTRYPOINT ["java","-jar","/app/service-article-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
