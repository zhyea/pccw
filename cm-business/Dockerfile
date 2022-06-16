FROM openjdk:8

ENV SPRING_PROFILES_ACTIVE prod

EXPOSE 8087
VOLUME /tmp
ADD target/*.jar cm-business.jar
ENTRYPOINT ["java","-jar","/cm-business.jar"]