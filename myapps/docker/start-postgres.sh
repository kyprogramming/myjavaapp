#!/bin/bash
set -e

# ═══════════════════════════════════════
# PostgreSQL का actual path ढूँढें
# ═══════════════════════════════════════
PG_BIN=$(ls -d /usr/lib/postgresql/*/bin 2>/dev/null | head -1)

if [ -z "$PG_BIN" ]; then
    echo "❌ PostgreSQL binaries not found in /usr/lib/postgresql/*/bin"
    exit 1
fi

echo "📁 PostgreSQL binaries: $PG_BIN"
echo "📁 PGDATA: $PGDATA"

# ═══════════════════════════════════════
# PostgreSQL initialize करें (अगर पहली बार है)
# ═══════════════════════════════════════
if [ ! -f "$PGDATA/PG_VERSION" ]; then
    echo "📦 Initializing PostgreSQL database..."
    
    mkdir -p "$PGDATA"
    chown -R postgres:postgres "$PGDATA"
    chmod 700 "$PGDATA"
    
    su postgres -c "$PG_BIN/initdb -D $PGDATA"
    
    # Start PostgreSQL temporarily to create DB and user
    su postgres -c "$PG_BIN/pg_ctl -D $PGDATA -o '-c listen_addresses=localhost' -w start"
    
    # Create database and user
    su postgres -c "psql -c \"CREATE USER myappuser WITH PASSWORD 'myapppassword';\""
    su postgres -c "psql -c \"CREATE DATABASE myappdb OWNER myappuser;\""
    su postgres -c "psql -c \"GRANT ALL PRIVILEGES ON DATABASE myappdb TO myappuser;\""
    
    # Stop temporary instance (supervisord will restart it)
    su postgres -c "$PG_BIN/pg_ctl -D $PGDATA -w stop"
    
    echo "✅ PostgreSQL initialized"
else
    echo "✅ PostgreSQL already initialized"
fi

# ═══════════════════════════════════════
# PostgreSQL foreground में start करें
# ═══════════════════════════════════════
exec su postgres -c "$PG_BIN/postgres -D $PGDATA -c listen_addresses=localhost"