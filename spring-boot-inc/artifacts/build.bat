@echo off

SET CURRENT_DIR=%cd%
SET TOMCAT_WEB_APP_FOLDER_LOCATION=C:\cust_inst\apache-tomcat-9.0.86\webapps
SET BASE_FOLDER= D:\GIT\bce-consolidated\spring-boot-inc

SET projectFolder1=bce-gui
SET projectFolder2=interview-mgmt
SET projectFolder3=RestServices

SET projectFolder4= interview-mgmt-services
SET projectFolder5= rest-services

SET archive_folder=%BASE_FOLDER%\artifacts\wars
@REM TOMCAT_WEB_APP_FOLDER_LOCATION= %archive_folder%

d:
cd D:\GIT\bce-consolidated\legacy\bce-gui
call mvn clean install


cd D:\GIT\bce-consolidated\legacy\interview-mgmt
call mvn clean install

cd D:\GIT\bce-consolidated\legacy\RestServices
call mvn clean install

cd D:\GIT\bce-consolidated\spring-boot-inc\interview-mgmt-services
call mvn clean install

cd D:\GIT\bce-consolidated\spring-boot-inc\rest-services
call mvn clean install


copy %BASE_FOLDER%\%projectFolder1%\target\bce-gui.war %archive_folder% /y
copy %BASE_FOLDER%\%projectFolder2%\target\interview-mgmt.war %archive_folder% /y
copy %BASE_FOLDER%\%projectFolder3%\target\RestServices.war %archive_folder% /y
copy %BASE_FOLDER%\%projectFolder4%\target\interview-mgmt-services.war %archive_folder% /y
copy %BASE_FOLDER%\%projectFolder6%\target\rest-services.war %archive_folder% /y

cd D:\GIT\bce-consolidated\legacy\artifacts