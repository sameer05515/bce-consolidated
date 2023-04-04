@echo off

call mvn clean package
copy .\bce-gui\target\bce-gui.war .\docker-files\artifacts\bce-gui.war
copy .\topic-mgmt-services\target\RestServices.war .\docker-files\artifacts\RestServices.war