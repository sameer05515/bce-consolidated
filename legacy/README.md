# bce-gui

## To create docker build
sudo docker build -t bce-gui:31mar2023 .

## To run docker
sudo docker run --name bce-gui-31mar2023 -p 8081:8080 -d --rm bce-gui:31mar2023

## To look inside container
sudo docker exec -it bce-gui-01apr2023 bash


cp ../RestServices/target/RestServices.war ./artifacts/RestServices.war
cp ../bce-gui/target/bce-gui.war ./artifacts/bce-gui.war

interview-mgmt

pushd ./legacy/bce-gui/ && mvn clean install && cp ./target/bce-gui.war ../docker-files/artifacts/bce-gui.war && popd

pushd ./legacy/RestServices/ && mvn clean install && cp ./target/RestServices.war ../docker-files/artifacts/RestServices.war && popd

pushd ./legacy/interview-mgmt/ && mvn clean install && cp ./target/interview-mgmt.war ../docker-files/artifacts/interview-mgmt.war && popd