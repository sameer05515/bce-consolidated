@echo off
cls
set curr_dir=%cd%
set proj_dir=C:\Prem\GIT\bce-gui\bce-gui
set tomcat_webapp_dir=C:\CUSTOM_INSTALLATION\apache-tomcat-8.5.72-test\webapps

call mvn clean package
copy %proj_dir%\target\bce-gui.war %tomcat_webapp_dir%\bce-gui.war /y

echo "Done !!"
cd %curr_dir%
