# Pokepedia BE

Backend service for a Pokédex API built with Kotlin, Spring Boot, Spring Data JPA, and PostgreSQL.

## Tech Stack
- Kotlin 2.2
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Gradle (Kotlin DSL)

## Features
- Generation 1 Pokémon data seeding from JSON at startup
- Evolution relationship seeding at startup
- Public Pokémon read endpoints
- Protected Pokémon write endpoints (JWT authentication required)
- Admin login endpoint for JWT issuance
- Standardized API response wrapper

## Project Structure
- `src/main/kotlin/dev/sws/pokepedia/feature/pokemon` — Pokémon module
- `src/main/kotlin/dev/sws/pokepedia/feature/auth` — Authentication and security
- `src/main/kotlin/dev/sws/pokepedia/core/response` — Response contracts
- `src/main/kotlin/dev/sws/pokepedia/exception` — Global exception handling
- `src/main/resources` — app config and seed JSON files

## Prerequisites
- JDK 21
- PostgreSQL running locally (default config expects `localhost:5432/pokedex_db`)

## Environment Variables
Set these before starting the application:

- `JWT_SECRET` — Base64-encoded JWT signing key
- `DEFAULT_ADMIN_USERNAME` — default seeded admin username
- `DEFAULT_ADMIN_PASSWORD` — default seeded admin password

## Run the Application
```bash
./gradlew bootRun
```

## Build and Test
```bash
./gradlew build
```

## API Overview
Base path: `/api/v1`

### Auth
- `POST /auth/login` — returns JWT token

### Pokémon
- `GET /pokemon?page=1&size=10&search=...` — paginated list (public)
- `GET /pokemon/{identifier}` — details by Pokémon number or name (public)
- `POST /pokemon` — create Pokémon (requires JWT authentication)
- `PUT /pokemon/{id}` — update Pokémon (requires JWT authentication)
- `DELETE /pokemon?id={id}` — delete Pokémon (requires JWT authentication)

For module-level endpoint notes and request fields, see:
- `src/main/kotlin/dev/sws/pokepedia/feature/auth/README.md`
- `src/main/kotlin/dev/sws/pokepedia/feature/pokemon/README.md`

## Security Rules
- Login endpoint is public.
- All `GET /api/v1/pokemon/**` endpoints are public.
- Non-GET Pokémon endpoints require an `Authorization` header with a valid JWT.

## Seed Data
On startup:
- Pokémon list is imported from `src/main/resources/pokemon_gen1.json` when DB is empty.
- Evolution lines are linked from `src/main/resources/pokemon_evolutions.json`.
- Default admin is created when admin table is empty.

## Troubleshooting
- If tests fail with PostgreSQL connection errors, ensure PostgreSQL is running and credentials in `application.properties` (or overrides) are valid.
