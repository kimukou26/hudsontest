::パッケージングバッチファイル

set GROOVY_HOME=C:\opt\groovy-1.7.5
set GRADLE_HOME=D:\Tooldev\gradle-0.9-rc-3
set JAVA_HOME=c:\opt\jdk

set PATH=%GROOVY_HOME%/bin;%GRADLE_HOME%/bin;%JAVA_HOME%/bin


::call gradle test
call gradle clean jar
