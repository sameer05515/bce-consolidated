::@echo off

::rm -Rf .\artifacts\*.war

pushd ..\bce-gui\ 
call mvn clean install
copy .\target\bce-gui.war ..\docker-files\artifacts\bce-gui.war
popd

pushd ..\RestServices\
call mvn clean install
copy .\target\RestServices.war ..\docker-files\artifacts\RestServices.war
popd

pushd ..\interview-mgmt\
call mvn clean install
copy .\target\interview-mgmt.war ..\docker-files\artifacts\interview-mgmt.war
popd