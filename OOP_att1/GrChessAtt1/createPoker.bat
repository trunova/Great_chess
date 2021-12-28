@echo off

set CD=%~dp0
SET mainPath=sample
SET binPath=%CD%bin

cd %binPath%
jar cvfe ../GrChessAtt1.jar sample.Main %mainPath%\*.class edu\csf\oop\java\chess\board\*.class edu\csf\oop\java\chess\pieces\*.class edu\csf\oop\java\chess\*.class