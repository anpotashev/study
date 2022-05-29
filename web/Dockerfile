FROM openjdk:8
ADD target/web-1.0.jar /opt/web.jar

ENV BH_ADDRESS = "grpc_bh_address"
ENV TG_ADDRESS = "grpc_tg_address"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/web.jar"]
