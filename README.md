# Shree Jewellers — Auth Service

Independent authentication and authorization microservice for Shree Jewellers ERP.

## Stack
- Java 24
- Spring Boot 4.0.9 (Spring Framework 7 / Spring Security 7)
- Gradle 8.14+ or 9.x
- Spring Security, JWT, MySQL 8, Flyway

## Run locally
1. Install JDK 24 and Gradle 8.14+ or 9.x.
2. Create MySQL database `auth_db`.
3. Set `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET` (minimum 32 UTF-8 bytes), `BOOTSTRAP_ADMIN_USERNAME`, and `BOOTSTRAP_ADMIN_PASSWORD` (minimum 12 characters) as environment variables.
4. Run `gradle clean build` then `gradle bootRun`.

Default port: 8081.

## Endpoints
- `POST /api/auth/login` — username/password, returns a 30-minute JWT.
- `GET /api/auth/me` — requires bearer JWT.
- `GET /api/auth/admin/check` — requires ADMIN role.

Do not commit passwords or JWT signing keys. Bootstrap credentials are intended only for initial setup; remove them from the environment after provisioning.

**Status:** initial implementation; Spring Boot 4 migration has not yet been verified by compiling or running integration tests. Production hardening is still required.
