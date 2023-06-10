FROM openjdk:17-ea-33-jdk-buster
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함 (파일 올릴 때 마다 gradle 다운로드 안하게 캐시 작업)
COPY build.gradle.kts settings.gradle.kts /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /build/
CMD ["gradle", "clean", "bootJar"]

# APP
FROM openjdk:17-ea-33-jdk-buster
WORKDIR /target

COPY /build/libs/image-server-api-0.0.1-SNAPSHOT.jar image-server-api.jar

EXPOSE 8080
#ENV	USE_PROFILE prod

#ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar", "/target/backend-api.jar"]
ENTRYPOINT ["java", "-jar", "/target/image-server-api.jar"]

# docker build --platform=linux/amd64 -t coals0329/image-server-api .
