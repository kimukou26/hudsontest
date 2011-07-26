set JAVA_HOME=C:\opt\jdk
::set CMD_OPTION=--prefix=/hudson --httpPort=38080 --httpsPort=38081 --ajp13port=-1
set CMD_OPTION=--httpPort=38080 --httpsPort=38081 --ajp13Port=-1

for /F "delims=" %%s in ('cd') do @set PWD=%%s
set HUDSON_HOME=%PWD%
::%JAVA_HOME%/bin/java -jar hudson.war %CMD_OPTION% 

%JAVA_HOME%/bin/java -XX:MaxPermSize=256m -Xms256m -Xmx256m -jar hudson.war %CMD_OPTION%
