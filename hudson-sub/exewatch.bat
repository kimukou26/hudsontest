set JAVA_HOME=c:\opt\jdk
set HUDSON_HOME=http://msie8:9090/

::%JAVA_HOME%/bin/java -jar ./hudson-core.jar "job name" <program arg1 arg2...>
:: test.exe exit signal , hudson get process dead status.
%JAVA_HOME%/bin/java -jar ./hudson-core.jar "exewatch" C:\applications\test.exe

