# Shree Jewellers — Auth Service

Independent authentication and authorization microservice for Shree Jewellers ERP.

## Stack
Java 21 · Spring Boot 3.4.5 · Gradle · Spring Security · OAuth2 Resource Server · MySQL · Flyway

## Local setup
1. Install JDK 21 and MySQL 8; create the `auth_db` database.
2. Set `DB_USERNAME`, `DB_PASSWORD`, and `JWT_SECRET` environment variables. `JWT_SECRET` must be at least 32 bytes; never commit it.
3. Run `gradle bootRun` (Gradle installation required; wrapper to be added).

Runs on port 8081 by default.

**Status:** initial scaffold, not yet verified with a build or suitable for production. JWT, role management, tests, and secure administrator provisioning are planned next.
