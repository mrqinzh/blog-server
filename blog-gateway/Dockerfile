# 基础镜像
FROM openjdk:17-jdk-alpine

# 工作目录
VOLUME /app

ADD target/blog-gateway-0.0.1-SNAPSHOT.jar /app/

EXPOSE 9999

ENTRYPOINT ["java","-jar","/app/blog-gateway-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
