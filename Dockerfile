# Sử dụng OpenJDK 17 làm base image
FROM openjdk:21-jdk-slim

# Đặt biến môi trường cho ứng dụng
ENV APP_NAME=shopquanao-0.0.1-SNAPSHOT.jar

# Tạo thư mục để chứa ứng dụng
WORKDIR /app

# Sao chép file JAR từ thư mục build vào container
COPY target/${APP_NAME} app.jar

# Cấu hình cổng chạy ứng dụng
EXPOSE 8080

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
