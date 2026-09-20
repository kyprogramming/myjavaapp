# ═══════════════════════════════════════
# STAGE 1: BUILD SPRING BOOT APP
# ═══════════════════════════════════════
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ═══════════════════════════════════════
# STAGE 2: RUNTIME WITH POSTGRESQL + APP
# ═══════════════════════════════════════
FROM eclipse-temurin:17-jre

# Install PostgreSQL और supervisor
RUN apt-get update && \
    apt-get install -y postgresql postgresql-contrib supervisor && \
    rm -rf /var/lib/apt/lists/*

# PostgreSQL setup
ENV PGDATA=/var/lib/postgresql/data
RUN mkdir -p $PGDATA && \
    chown -R postgres:postgres /var/lib/postgresql

# App directory
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar

# Copy configs
COPY docker/start-postgres.sh /app/start-postgres.sh
COPY docker/supervisord.conf /etc/supervisor/conf.d/supervisord.conf

RUN chmod +x /app/start-postgres.sh

# PostgreSQL data directory (persistent)
VOLUME ["/var/lib/postgresql/data"]

# Expose port
EXPOSE 8080

# Start supervisor (jo PostgreSQL aur Spring Boot dono manage karega)
CMD ["/usr/bin/supervisord", "-c", "/etc/supervisor/conf.d/supervisord.conf"]