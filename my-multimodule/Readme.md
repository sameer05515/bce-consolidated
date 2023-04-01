## To create docker build

sudo docker build -t bce-gui:01apr2023 ./docker-files/

## To run docker
sudo docker run --add-host=host.docker.internal:host-gateway  --name bce-gui-01apr2023 -p 8081:8080 --rm bce-gui:01apr2023

## To look inside container
sudo docker exec -it bce-gui-01apr2023 bash


## My commands

### To create build and copy artifacts
mvn clean package
cp ./bce-gui/target/bce-gui.war ./docker-files/artifacts/bce-gui.war
cp ./topic-mgmt-services/target/RestServices.war ./docker-files/artifacts/RestServices.war


### To copy war files to tomcat

#### **apache-tomcat-10.1.7**
cp ./docker-files/artifacts/bce-gui.war /home/premendra/Desktop/apache-tomcat-10.1.7/webapps/bce-gui.war

cp ./docker-files/artifacts/RestServices.war /home/premendra/Desktop/apache-tomcat-10.1.7/webapps/RestServices.war

#### **apache-tomcat-8.5.87**
cp ./topic-mgmt-services/target/RestServices.war /home/premendra/Desktop/tomcats/apache-tomcat-8.5.87/webapps/RestServices.war

cp ./bce-gui/target/bce-gui.war /home/premendra/Desktop/tomcats/apache-tomcat-8.5.87/webapps/bce-gui.war

### Set JAVA HOME and JRE HOME
```
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export JRE_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=${PATH}:${JAVA_HOME}/bin
```

### To connect local mysql server to docker container
```
CREATE USER 'root'@'172.17.0.2' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'172.17.0.2' IDENTIFIED BY '';
```

































