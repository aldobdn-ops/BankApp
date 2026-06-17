@echo off
dir /s /b src\*.java | find /v "\Test\" > sources.txt
javac -d bin -cp "lib/*" @sources.txt
del sources.txt
