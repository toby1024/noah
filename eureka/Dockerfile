FROM daocloud.io/tcsoft2016/springboot-maven

ENV RUN_ENV prod

COPY eureka/pom.xml /tmp/build/
COPY eureka/src /tmp/build/src

RUN echo "root:Q!W@E#R$" | chpasswd \
    && cd /tmp/build \
    && mkdir /app \
    && mvn clean package -q -P${RUN_ENV} -DskipTests=true \
    && mv target/*.jar /app/eureka.jar \
    && rm -rf /tmp/build

CMD ["java", "-Xmx200m", "-jar", "/app/eureka.jar", "--spring.config.location=/app/application.yml"]

EXPOSE 8761