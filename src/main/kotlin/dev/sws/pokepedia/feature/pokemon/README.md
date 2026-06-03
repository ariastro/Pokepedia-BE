# Pokemon Module

This module manages Pokédex data, including listing, detail retrieval, creation, updates, deletion, and evolution linkage.

## Responsibilities
- Pokémon CRUD operations
- Pagination and search for list endpoint
- Detail lookup by Pokémon number or name
- Mapping entities to API DTOs
- Startup import for Gen 1 Pokémon and evolution links

## Main Components
- `controller/PokemonController.kt` — REST endpoints under `/api/v1/pokemon`
- `service/PokemonService.kt` — business logic and data orchestration
- `repository/PokemonRepository.kt` — JPA data access
- `mapper/PokemonMapper.kt` — entity-to-response conversion helpers
- `entity/*` — persistence entities (pokemon, detail, abilities, evolutions)
- `dto/*` — request/response contracts
- `service/PokemonDataInitializer.kt` — imports `pokemon_gen1.json` on empty DB
- `service/EvolutionDataInitializer.kt` — links evolutions from `pokemon_evolutions.json`

## Endpoints
- `GET /api/v1/pokemon?page=1&size=10&search=...`  
  Returns paginated list with `meta` fields.
- `GET /api/v1/pokemon/{identifier}`  
  `identifier` can be a Pokémon number (e.g., `25`) or name (e.g., `pikachu`).
- `POST /api/v1/pokemon` *(authenticated)*
- `PUT /api/v1/pokemon/{id}` *(authenticated)*
- `DELETE /api/v1/pokemon?id={id}` *(authenticated)*

## Request Contract (Create/Update)
Main fields expected in `PokemonRequest`:
- identity and display: `pokemon_number`, `name`, `type`, `type2`, `image_url`, `generation`
- detail: `description`, `height`, `weight`, `species_category`
- stats: `hp`, `attack`, `defense`, `special_attack`, `special_defense`, `speed`
- relationships:
  - `abilities`: list of `{ name, is_hidden }`
  - `evolutions`: list of `{ evolved_pokemon_number, trigger_method, trigger_value }`

## Error Handling
`PokemonNotFoundException` is converted by global exception handling to a 404 response with the standard `BaseResponse` structure.
