# VM 부하 최적화를 위해 경량화 이미지 사용; 파이프라인에서 빌드 및 전달 받음
FROM eclipse-temurin:17-jre-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 Docker 컨테이너로 복사
COPY build/libs/*.jar ciabatta-core.jar

# 타임존 환경 변수 설정
ENV TZ Asia/Seoul

# 애플리케이션 실행 명령어 설정
ENTRYPOINT ["java", "-jar", "ciabatta-core.jar"]