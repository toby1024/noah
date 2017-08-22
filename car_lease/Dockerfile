FROM daocloud.io/tcsoft2016/springboot-maven

ENV RUN_ENV prod

COPY car_lease/pom.xml /tmp/build/
COPY car_lease/src /tmp/build/src

RUN echo "root:Q!W@E#R$" | chpasswd \
    && cd /tmp/build \
    && mkdir /app \
    && mvn clean package -q -P${RUN_ENV} -DskipTests=true \
    && mv target/*.jar /app/gateway.jar \
    && rm -rf /tmp/build

CMD ["java", "-Xmx200m", "-jar", "/app/hystrix_dashboard.jar"]

EXPOSE 8761