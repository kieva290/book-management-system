FROM openjdk:17
ADD target/book-management-system-0.0.1-SNAPSHOT.jar book-management-system-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "book-management-system-0.0.1-SNAPSHOT.jar"]