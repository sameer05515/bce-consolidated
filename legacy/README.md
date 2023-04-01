# bce-gui

## To create docker build
sudo docker build -t bce-gui:legacy ./legacy/docker-files/

## To run docker
sudo docker run --name bce-gui-legacy -p 8081:8080 -d --rm bce-gui:legacy

sudo docker run --add-host=host.docker.internal:host-gateway  --name bce-gui-legacy -p 8081:8080 --rm bce-gui:legacy

## To look inside container
sudo docker exec -it bce-gui-01apr2023 bash


### To copy war files to tomcat


#### **apache-tomcat-8.5.87**
rm -Rf /home/premendra/Desktop/tomcats/apache-tomcat-8.5.87/webapps/*.war

cp ./RestServices/target/RestServices.war /home/premendra/Desktop/tomcats/apache-tomcat-8.5.87/webapps/RestServices.war

cp ./bce-gui/target/bce-gui.war /home/premendra/Desktop/tomcats/apache-tomcat-8.5.87/webapps/bce-gui.war

cp ./interview-mgmt/target/interview-mgmt.war /home/premendra/Desktop/tomcats/apache-tomcat-8.5.87/webapps/interview-mgmt.war


cp ../RestServices/target/RestServices.war ./artifacts/RestServices.war
cp ../bce-gui/target/bce-gui.war ./artifacts/bce-gui.war

interview-mgmt

pushd ./legacy/bce-gui/ && mvn clean install && cp ./target/bce-gui.war ../docker-files/artifacts/bce-gui.war && popd

pushd ./legacy/RestServices/ && mvn clean install && cp ./target/RestServices.war ../docker-files/artifacts/RestServices.war && popd

pushd ./legacy/interview-mgmt/ && mvn clean install && cp ./target/interview-mgmt.war ../docker-files/artifacts/interview-mgmt.war && popd