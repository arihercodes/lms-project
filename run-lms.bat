@echo off
echo ===============================
echo Compiling Java LMS Project...
echo ===============================

REM Clean output directory
if exist out rmdir /s /q out
mkdir out

REM Compile Java files using sources.txt
javac -d out @sources.txt

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation failed. Please check errors above.
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo ===============================
echo Running Main Application...
echo ===============================
java -cp "out;lib\mysql-connector-j-9.3.0.jar" Main

pause
