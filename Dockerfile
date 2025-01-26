FROM openjdk:21-jdk

EXPOSE 8080

COPY ./build/libs/spring-boot-ci-cd-demo-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "spring-boot-ci-cd-demo-0.0.1-SNAPSHOT.jar"]