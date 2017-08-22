FROM java:8-jre
MAINTAINER Zhangbin <zhangbin.zj@gmail.com>

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/eureka.jar"]

EXPOSE 8761