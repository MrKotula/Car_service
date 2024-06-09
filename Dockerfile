FROM jelastic/maven:3.9.5-openjdk-21 AS builder

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

RUN mvn package -DskipTests

FROM openjdk:21

WORKDIR /application

COPY --from=builder /build/target/carservice-0.0.1-SNAPSHOT.jar carservice.jar

ENTRYPOINT ["java", "-jar", "carservice.jar"]