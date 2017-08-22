FROM daocloud.io/tcsoft2016/springboot-maven

ENV RUN_ENV prod

COPY hystrix_dashboard/pom.xml /tmp/build/
COPY hystrix_dashboard/src /tmp/build/src

RUN echo "root:Q!W@E#R$" | chpasswd \
    && cd /tmp/build \
    && mkdir /app \
    && mvn clean package -q -P${RUN_ENV} -DskipTests=true \
    && mv target/*.jar /app/gateway.jar \
    && rm -rf /tmp/build

CMD ["java", "-Xmx200m", "-jar", "/app/hystrix_dashboard.jar"]

EXPOSE 8761