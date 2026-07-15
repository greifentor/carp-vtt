@echo off
setlocal enabledelayedexpansion

rem ============================================================================
rem  CARP VTT - Startet REST-Server und Swing-GUI zusammen.
rem
rem  Die GUI wird so konfiguriert, dass sie den REST-Server ansprechen kann.
rem  Alle Einstellungen werden ueber "-D" an die JVM uebergeben und koennen
rem  hier zentral angepasst werden.
rem ============================================================================

rem --- Konfiguration ----------------------------------------------------------
rem Port, unter dem der REST-Server lauscht.
set REST_PORT=8080

rem Base-URL, unter der die GUI den REST-Server erreicht.
set REST_BASE_URL=http://localhost:%REST_PORT%

rem Datenbank der Swing-GUI (HSQL In-Memory, benoetigt keinen externen Server).
set GUI_DB_URL=jdbc:hsqldb:mem:carp-vtt-gui
set GUI_DB_DRIVER=org.hsqldb.jdbc.JDBCDriver
set GUI_DB_USER=sa
set GUI_DB_PASSWORD=

rem Wartezeit (Sekunden), bevor die GUI startet, damit der Server oben ist.
set STARTUP_DELAY=10
rem ---------------------------------------------------------------------------

set BASEDIR=%~dp0
set REST_JAR=%BASEDIR%rest-server\target\carp-vtt-rest-server-0.0.1.jar
set GUI_JAR=%BASEDIR%swing-gui-starter\target\carp-vtt-swing-starter-0.0.1.jar

if not exist "%REST_JAR%" goto :missingjars
if not exist "%GUI_JAR%" goto :missingjars

echo [CARP VTT] Starte REST-Server auf Port %REST_PORT% ...
start "CARP VTT REST-Server" java ^
  -Dserver.port=%REST_PORT% ^
  -jar "%REST_JAR%"

echo [CARP VTT] Warte %STARTUP_DELAY% Sekunden, bis der REST-Server bereit ist ...
timeout /t %STARTUP_DELAY% /nobreak >nul

echo [CARP VTT] Starte Swing-GUI (verbindet sich mit %REST_BASE_URL%) ...
rem start "CARP VTT Swing-GUI" 
java ^
  -Dcarp.vtt.rest-client.base-url=%REST_BASE_URL% ^
  -Dspring.datasource.url=%GUI_DB_URL% ^
  -Dspring.datasource.driverClassName=%GUI_DB_DRIVER% ^
  -Dspring.datasource.username=%GUI_DB_USER% ^
  -Dspring.datasource.password=%GUI_DB_PASSWORD% ^
  -jar "%GUI_JAR%"

echo [CARP VTT] REST-Server und Swing-GUI wurden gestartet.
goto :eof

:missingjars
echo [CARP VTT] FEHLER: Die ausfuehrbaren Jars wurden nicht gefunden.
echo            Bitte zuerst bauen mit:
echo                mvn clean package
echo.
echo            Erwartet:
echo              %REST_JAR%
echo              %GUI_JAR%
exit /b 1
