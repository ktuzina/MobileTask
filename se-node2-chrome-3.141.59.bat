:: Beginning of hub batch file
set SERVER_VERSION=3.141.59
set TASK_NAME=SeleniumServerNode
set NODE_PORT=5558
set HUB_PORT=4444
set REGISTER_IP=localhost
set CHROME_DRIVER=C:\Soft\Webdriver\chromedriver.exe
java -Dwebdriver.chrome.driver=%CHROME_DRIVER% -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%REGISTER_IP%:%HUB_PORT%/grid/register -browser "browserName=chrome,version=74,maxInstances=5,platform=WINDOWS" -port %NODE_PORT%
:: End of hub batch file
pause