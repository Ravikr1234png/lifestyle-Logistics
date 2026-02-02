# Lifestyle Logistics (Backend-only, No Eureka, MySQL-only)

Microservices backend for cost-of-living analysis and favorites, **without Service Discovery (Eureka)** and **without any frontend**. Routing is done via **API Gateway (static routes)**. Persistence is **MySQL only**. Messaging via **Kafka**. JWT-based security.

## Services
- `api-gateway` — Spring Cloud Gateway + global JWT filter; static routes to services
- `auth-service` — login/register; issues JWT; Feign to UserProfile via fixed URL
- `userprofile-service` — user CRUD (MySQL, JPA, BCrypt)
- `lifestyle-service` — proxy to external Lifestyle API & Kafka **producer**
- `favorite-service` — consumes Kafka, persists favorites in MySQL (JPA), CRUD
- `config-server` (optional) — stubbed native config (not required)

## Quick start

1) Start external Lifestyle API (or rely on compose service `lifestyleapi`):
```bash
docker run -p3232:3232 --name lifecontainer -d stackroutenew/lifestyleapi
```

2) Build & up:
```bash
mvn -q -DskipTests clean package
docker compose up -d --build
```

**Endpoints** (direct):
- Gateway: `http://localhost:8080`
- Auth: `http://localhost:8081`
- UserProfile: `http://localhost:8082`
- Lifestyle: `http://localhost:8083`
- Favorite: `http://localhost:8084`

**Infra**: MySQL `localhost:3306` (root/secret), Kafka `localhost:9092`, Lifestyle API `http://localhost:3232/lifestyles`

3) Flow via Gateway
```bash
# Register
curl -X POST http://localhost:8080/auth/register  -H 'Content-Type: application/json'  -d '{"username":"ravi","password":"Pass@123","email":"ravi@example.com"}'

# Login
TOKEN=$(curl -s -X POST http://localhost:8080/auth/login  -H 'Content-Type: application/json'  -d '{"username":"ravi","password":"Pass@123"}' | jq -r '.token')

# Get all lifestyles
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/lifestyles

# Favorite one (Kafka -> Favorite service -> MySQL)
curl -X POST http://localhost:8080/lifestyles/favorite  -H 'Content-Type: application/json' -H "Authorization: Bearer $TOKEN"  -d '{"Country":"India","Rank":10,"costOfLivingIndex":35.2}'

# List favorites
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/favorites
```

4) Shutdown
```bash
docker compose down
```

> Notes: This is a reference skeleton; tune DTOs per the real Lifestyle API schema.
