FROM openjdk:8

ENV SPRING_PROFILES_ACTIVE prod

EXPOSE 8085 8087 8088

CMD ["/bin/bash", "-x", "/opt/ke-demo/htdocs/bin/run.sh"]
