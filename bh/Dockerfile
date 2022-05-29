FROM openjdk:8
ADD target/bh-1.0.jar /opt/bh.jar

ENV JDBC_URL = "jdbc:postgresql://localhost/dev?ssl=false"
ENV DB_USERNAME = "enter_username"
ENV DB_PASSWORD = "enter_password"

EXPOSE 6565
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/bh.jar"]
