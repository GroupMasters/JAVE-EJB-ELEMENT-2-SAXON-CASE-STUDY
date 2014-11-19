
@echo OFF 
cls

set path=%path%;C:\\Program Files (x86)\Java\jdk1.8.0_25\bin\
set "serverapp=C:\Users\obaro\Desktop\Element1\Server\build\classes\server"
set "application_root=C:\Users\obaro\Desktop\Element1"
set "root_src=%application_root%\Server\src\"
rem specifing the build path where the compiler will save the .classes 
set "build_classes=C:\Users\obaro\Desktop\Element1\Server\build\classes\"
set "jar_file_location_name=%application_root%\Server\dist\Server.jar"
set "main_method_class=server\Server.java"

rem this will check if the files exist then clear then and re-generated another ones
set CLASSPATH=%CLASSPATH%;%build_classes%
set CLASSPATH=%CLASSPATH%;%serverapp%
set "policy_path=%serverapp%\Server.policy"
rem comment this command off
rem in case there is any .class in my src folder
if exist Server.class (
   rem Tell the user that you are about to clear the old files 
	echo Cleaning build classes...
	del  /S *.class
)

echo Building java classes...
echo ...
javac  -Xlint:deprecation -d %build_classes%  -classpath  "../" *.java


echo Packing the .classes file into a jar file...
jar cvf %jar_file_location_name%   %build_classes%
cls
echo Server.java [main] building...
cd %root_src%
rem compile the main file 
javac -Xlint:deprecation -cp %jar_file_location_name% -classpath  %build_classes% %main_method_class%
rem we can set the jar file path
set CLASSPATH=%CLASSPATH%;%jar_file_location_name%
cd server

rem start the rmiregister , but since my application will auto start it, I will not include
start rmiregister 1099


rem specific the policy file here
java -cp  %jar_file_location_name% Server.java -Djava.rmi.server.codebase=%jar_file_location_name%
 -Djava.rmi.server.hostname=localhost -Djava.security.policy=%policy_path% server.controllers.ServerController,server.share.EntrySite,server.share.ConnectionImp
       
pause





