FROM openjdk:8
ADD target/tg-1.0.jar /opt/tg.jar

ENV JDBC_URL = "jdbc:postgresql://localhost/dev?ssl=false"
ENV DB_USERNAME = "username"
ENV DB_PASSWORD = "password"
ENV BOT_NAME = "bot_name"
ENV BOT_TOKEN = "bot_token"
ENV WEBHOOK_URL = "webhook_url"
ENV SPRING_PROFILES_ACTIVE = ""

EXPOSE 6565
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/tg.jar"]
