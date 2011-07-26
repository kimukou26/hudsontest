hudson test aria
==========

1. griffon
	* griffon gradle build setting
		* copy to griffon project and run (using windows)

2. hudson-mst
	* hudson masternode setting file and testjob
		* filecheck
			* file check retrying job(need -XX:MaxPermSize=256m)
	* griffon
		* using gradle griffon compile
	* exewatch
		* mastar node watch exist code process

3. hudson-sub
	* hudson subnode connection setting file

4. youdebug
	* https://github.com/kohsuke/youdebug compile setting
		* build.gradle add
		* pom.xml edit

------

* Hudson Enviroment setting
	* hudson.war download
	* mkdir d:/applications/hudson-mst
	* copy to hudson.war
	* copy to hudson.bat edit JAVA_HOME
	* run hudson.bat
	* shutdown hudson
	* copy to hudson-mst(exclude hudson.bat)
	* hudson-ci.bat edit JAVA_HOME,HUDSON_URL
	* rerun hudon.bat

* Aditional Setting
	* Hudson General -> Gradle Path Set
	* Hudson General -> URL Set (Other PC access need)
