FROM eclipse-temurin:17.0.17_10-jre-alpine-3.23

LABEL authors="nhannguyenh"

COPY target/mini-ecommerce-platform-server*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
