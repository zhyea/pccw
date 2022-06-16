FROM openjdk:8

ENV SPRING_PROFILES_ACTIVE prod

EXPOSE 8088
VOLUME /tmp
ADD target/*.jar cm-eureka-server.jar
ENTRYPOINT ["java","-jar","/cm-eureka-server.jar"]