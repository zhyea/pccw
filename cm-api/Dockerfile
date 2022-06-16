FROM openjdk:8

ENV SPRING_PROFILES_ACTIVE prod

EXPOSE 8085
VOLUME /tmp
ADD target/*.jar cm-api.jar
ENTRYPOINT ["java","-jar","/cm-api.jar"]