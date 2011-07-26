cd /d %0\..

set JAVA_HOME=C:\opt\jdk
set CMD_URL=http://localhost:9090


::echo %JAVA_HOME%/bin/java -jar %HUDSON_PATH%/war/WEB-INF/hudson-cli.jar -s %CMD_URL% groovy %*
::%JAVA_HOME%/bin/java -jar %HUDSON_PATH%/war/WEB-INF/hudson-cli.jar -s %CMD_URL% groovy %*

set CLASSPATH=./war/WEB-INF/*;./script/lib/*
%JAVA_HOME%/bin/java -cp "%CLASSPATH%" hudson.cli.CLI -s %CMD_URL% groovy %* %CMD_URL%
