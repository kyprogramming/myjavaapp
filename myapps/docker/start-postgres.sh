#!/bin/bash
set -e

PG_BIN=$(ls -d /usr/lib/postgresql/*/bin 2>/dev/null | head -1)
if [ -z "$PG_BIN" ]; then
    echo "❌ PostgreSQL binaries not found"
    exit 1
fi

echo "📁 PostgreSQL binaries: $PG_BIN"

if [ ! -f "$PGDATA/PG_VERSION" ]; then
    echo "📦 Initializing PostgreSQL database..."
    mkdir -p "$PGDATA"
    chown -R postgres:postgres "$PGDATA"
    chmod 700 "$PGDATA"
    
    su postgres -c "$PG_BIN/initdb -D $PGDATA"
    
    # Local-only connections
    cat > "$PGDATA/pg_hba.conf" <<EOF
local   all             all                                     trust
host    all             all             127.0.0.1/32            trust
host    all             all             ::1/128                 trust
EOF
    
    # Listen only on localhost
    echo "listen_addresses = 'localhost'" >> "$PGDATA/postgresql.conf"
    
    su postgres -c "$PG_BIN/pg_ctl -D $PGDATA -w start"
    su postgres -c "psql -c \"CREATE USER myappuser WITH PASSWORD 'myapppassword';\""
    su postgres -c "psql -c \"CREATE DATABASE myappdb OWNER myappuser;\""
    su postgres -c "psql -c \"GRANT ALL PRIVILEGES ON DATABASE myappdb TO myappuser;\""
    su postgres -c "$PG_BIN/pg_ctl -D $PGDATA -w stop"
    
    echo "✅ PostgreSQL initialized"
fi

exec su postgres -c "$PG_BIN/postgres -D $PGDATA -c listen_addresses=localhost"