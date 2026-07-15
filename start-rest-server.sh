#!/usr/bin/env bash
# ============================================================================
#  CARP VTT - Startet den REST-Server mit einer HSQL-Datenbank.
#
#  Alle Einstellungen werden ueber "-D" an die JVM uebergeben und koennen
#  ueber Umgebungsvariablen ueberschrieben werden.
# ============================================================================
set -euo pipefail

# --- Konfiguration (per Umgebungsvariable ueberschreibbar) ------------------
REST_PORT="${REST_PORT:-8080}"

# HSQL In-Memory-Datenbank (benoetigt keinen externen Server).
DB_URL="${DB_URL:-jdbc:hsqldb:mem:carp-vtt-web}"
DB_DRIVER="${DB_DRIVER:-org.hsqldb.jdbc.JDBCDriver}"
DB_USER="${DB_USER:-sa}"
DB_PASSWORD="${DB_PASSWORD:-}"
# ---------------------------------------------------------------------------

BASEDIR="$(cd "$(dirname "$0")" && pwd)"
REST_JAR="${BASEDIR}/rest-server/target/carp-vtt-rest-server-0.0.1.jar"

if [ ! -f "${REST_JAR}" ]; then
	echo "[CARP VTT] FEHLER: Ausfuehrbares Jar nicht gefunden:"
	echo "             ${REST_JAR}"
	echo "           Bitte zuerst bauen mit:"
	echo "             mvn clean package"
	exit 1
fi

echo "[CARP VTT] Starte REST-Server auf Port ${REST_PORT} mit HSQL-Datenbank (${DB_URL}) ..."
exec java \
	-Dserver.port="${REST_PORT}" \
	-Dspring.datasource.url="${DB_URL}" \
	-Dspring.datasource.driverClassName="${DB_DRIVER}" \
	-Dspring.datasource.username="${DB_USER}" \
	-Dspring.datasource.password="${DB_PASSWORD}" \
	-jar "${REST_JAR}"
