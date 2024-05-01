FROM tomcat:10.0-jdk8-corretto
COPY artifacts/bce-gui.war /usr/local/tomcat/webapps/bce-gui.war
COPY artifacts/RestServices.war /usr/local/tomcat/webapps/RestServices.war
COPY artifacts/interview-mgmt.war /usr/local/tomcat/webapps/interview-mgmt.war
EXPOSE 8080
CMD ["catalina.sh", "run"]