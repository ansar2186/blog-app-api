FROM openjdk:8
EXPOSE 9090
ADD target/blog-app-api.jar blog-app-api.jar
ENTRYPOINT ["java","-jar","/blog-app-api.jar"]
