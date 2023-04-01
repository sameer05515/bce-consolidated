rm -Rf ./docker-files/artifacts/*.war
mvn clean package
cp ./bce-gui/target/bce-gui.war ./docker-files/artifacts/bce-gui.war
cp ./topic-mgmt-services/target/RestServices.war ./docker-files/artifacts/RestServices.war