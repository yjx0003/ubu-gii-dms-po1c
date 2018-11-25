@echo off
if not exist .\bin mkdir .\bin
javac -d .\bin -sourcepath .\src -cp .\lib\* -encoding cp1252 src\controller/Main.java
pause