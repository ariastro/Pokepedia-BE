# Auth Module

This module handles admin authentication and JWT-based request authorization.

## Responsibilities
- Admin login and credential validation
- JWT generation and validation
- Security filter chain configuration
- Default admin bootstrap on application startup

## Main Components
- `controller/AuthController.kt` — `/api/v1/auth/login`
- `service/AuthService.kt` — validates username/password and returns token
- `security/JwtUtil.kt` — JWT issue/parse/validate utilities
- `security/JwtAuthenticationFilter.kt` — reads `Authorization` header and sets security context
- `security/SecurityConfig.kt` — access rules and stateless security setup
- `security/AdminDataInitializer.kt` — seeds default admin when repository is empty
- `repository/AdminRepository.kt` and `entity/Admin.kt` — admin persistence model

## Endpoint
### `POST /api/v1/auth/login`
Request body:
```json
{
  "username": "admin",
  "password": "password"
}
```

Success response shape:
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "..."
  }
}
```

## Configuration
Required properties/environment variables:
- `jwt.secret` (from `JWT_SECRET`)
- `jwt.expiration`
- `app.admin.username` (from `DEFAULT_ADMIN_USERNAME`)
- `app.admin.password` (from `DEFAULT_ADMIN_PASSWORD`)
