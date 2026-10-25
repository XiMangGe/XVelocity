@echo off
set VERSION=26.10.25.1
echo Building XVelocity v%VERSION%...
call mvn clean package -q
if %ERRORLEVEL% equ 0 (
    if not exist "build" mkdir build
    copy "target\XVelocity-%VERSION%.jar" "build\" >nul
    echo Build successful! Output: build\XVelocity-%VERSION%.jar
) else (
    echo Build failed!
    exit /b 1
)
