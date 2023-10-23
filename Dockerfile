# Sử dụng một base image với môi trường Java đã được cài đặt
FROM openjdk:11-jre-slim

# Thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR vào trong container
COPY target/*.jar app.jar

# Chạy ứng dụng
ENTRYPOINT ["java","-jar","/app/app.jar"]
