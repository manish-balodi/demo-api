FROM openjdk:8
ADD target/person-demo-api-1.0.jar person-demo-api-1.0.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "person-demo-api-1.0.jar"]