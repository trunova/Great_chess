@echo off

set CD=%~dp0
SET mainPath=%CD%src\sample
set logPath=%CD%lib\logback
set javaFX=%CD%lib\javaFX

javac --module-path "%javaFX%" --upgrade-module-path "%logPath%" --add-modules ALL-MODULE-PATH --patch-module="D:\Java\GrChessAtt1\src\sample\d.fxml" -d bin %mainPath%\*.java -d bin src\edu\csf\oop\java\chess\board\*.java -d bin src\edu\csf\oop\java\chess\pieces\*.java -d bin src\edu\csf\oop\java\chess\*.java