FROM openjdk:17-oracle

EXPOSE 8080

COPY ./target/*.jar /usr/app/comment-analyser.jar
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "comment-analyser.jar"]
