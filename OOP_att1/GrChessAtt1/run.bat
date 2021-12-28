@echo off

set CD=%~dp0
set logPath=%CD%lib\logback
set javaFX=%CD%lib\javaFX

java --module-path "%javaFX%" --upgrade-module-path "%logPath%" --add-modules ALL-MODULE-PATH -jar %CD%GrChessAtt1.jar