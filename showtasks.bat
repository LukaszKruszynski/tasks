call runcrud
if "%ERRORLEVEL%" == "0" goto showtasks
echo.
echo runcrud file error

:showtasks
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end

:end
echo Show tasks success.


