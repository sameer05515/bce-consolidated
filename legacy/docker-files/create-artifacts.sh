rm -Rf ./artifacts/*.war

pushd ../bce-gui/ && mvn clean install && cp ./target/bce-gui.war ../docker-files/artifacts/bce-gui.war && popd

pushd ../RestServices/ && mvn clean install && cp ./target/RestServices.war ../docker-files/artifacts/RestServices.war && popd

pushd ../interview-mgmt/ && mvn clean install && cp ./target/interview-mgmt.war ../docker-files/artifacts/interview-mgmt.war && popd