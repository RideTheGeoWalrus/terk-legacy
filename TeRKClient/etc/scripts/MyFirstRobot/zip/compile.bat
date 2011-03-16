@echo OFF
IF EXIST MyFirstRobot.jar del MyFirstRobot.jar
IF EXIST .\bin\*.class del .\bin\*.class
IF EXIST .\bin rmdir .\bin
mkdir bin
javac -g -cp .;./bin;./terk.jar -sourcepath .;./bin -d ./bin MyFirstRobot.java
jar cf MyFirstRobot.jar -C ./bin .