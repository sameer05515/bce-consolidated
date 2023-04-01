FROM tomcat:8-jdk8-temurin
COPY artifacts/bce-gui.war /usr/local/tomcat/webapps/bce-gui.war
COPY artifacts/RestServices.war /usr/local/tomcat/webapps/RestServices.war
EXPOSE 8080
CMD ["catalina.sh", "run"]