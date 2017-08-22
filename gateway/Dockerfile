FROM daocloud.io/tcsoft2016/springboot-maven

ENV RUN_ENV prod

COPY gateway/pom.xml /tmp/build/
COPY gateway/src /tmp/build/src

RUN echo "root:Q!W@E#R$" | chpasswd \
    && cd /tmp/build \
    && mkdir /app \
    && mvn clean package -q -P${RUN_ENV} -DskipTests=true \
    && mv target/*.jar /app/gateway.jar \
    && rm -rf /tmp/build

CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 8761