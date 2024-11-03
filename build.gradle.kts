plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.3"
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot starter dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA 의존성 추가
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Lombok dependency
   // implementation("org.projectlombok:lombok")
   // annotationProcessor("org.projectlombok:lombok")

    implementation("org.projectlombok:lombok:1.18.28") // 최신 버전으로 교체
    annotationProcessor("org.projectlombok:lombok:1.18.28")
   // implementation ('mysql:mysql-connector-java:8.0.33') // 최신 버전으로 변경 가능

    // Spring Boot starter dependencies
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA starter 추가
    implementation("mysql:mysql-connector-java:8.0.32") // MySQL Connector/J 추가 (버전은 필요에 따라 조정)
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
