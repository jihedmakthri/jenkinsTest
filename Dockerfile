FROM openjdk:11
COPY --from=build target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]