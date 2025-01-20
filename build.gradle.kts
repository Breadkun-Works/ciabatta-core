plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.ciabatta.core"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.postgresql:r2dbc-postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // implementation("io.projectreactor.kotlin:reactor-kotlin-extensions") // 리액터 확장; 코루틴 사용으로 리액터 사용 최소화
    // implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0") // 포스트맨으로 이사~
    // implementation("org.liquibase:liquibase-core") // Liquibase는 CI/CD 파이프라인에서만 동작
    // implementation("org.springframework.boot:spring-boot-starter-data-jpa") // Liquibase update 위한 의존성
    // implementation("org.springframework:spring-jdbc") // R2DBC 사용 지향
    // runtimeOnly("org.postgresql:postgresql") //	JDBC-postgreSQL 드라이버
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
