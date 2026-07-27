# AI Agent Guidelines for lrt-service

## Architecture Overview
This is a Spring Boot microservice following Clean Architecture principles with Domain-Driven Design (DDD). The codebase is structured into four main layers:

- **Domain** (`domain/`): Core business logic, immutable models, and domain events
- **Application** (`application/`): Use cases, services, and DTOs  
- **Infrastructure** (`infrastructure/`): External concerns like persistence and configuration
- **API** (`api/`): REST controllers and external interfaces

## Key Patterns & Conventions

### Domain Layer
- Use **records** for all domain models (e.g., `Lrt.java`) - they are immutable with built-in validation in compact constructors
- Domain events implement sealed interface `LrtEvent` (e.g., `LrtCreatedEvent`)
- Factory methods like `Lrt.createNew()` for object creation
- Validation happens in domain model constructors, not controllers

### Application Layer  
- Services handle business logic and coordinate between layers
- DTOs are records with static factory methods (e.g., `LrtDto.from(domainModel)`)
- Commands (e.g., `LrtCreateCommand`) use Jakarta validation annotations
- Events published via `ApplicationEventPublisher`

### Infrastructure Layer
- JPA entities use Lombok (`@Data`, `@Builder`) for boilerplate
- Manual conversion between domain models and JPA entities in services
- Repositories extend `JpaRepository` with custom finder methods

### API Layer
- Controllers use constructor injection
- OpenAPI documentation with `@Operation` annotations
- Standard REST patterns with `ResponseEntity`

## Development Workflows

### Building & Running
```bash
# Build with Maven wrapper
./mvnw clean compile

# Run tests (includes Testcontainers for Kafka/PostgreSQL)
./mvnw test

# Run application locally
./mvnw spring-boot:run
```

### Testing
- Integration tests use Testcontainers (`TestcontainersConfiguration.java`)
- Tests import `@Import(TestcontainersConfiguration.class)` for containerized dependencies
- Kafka and PostgreSQL containers start automatically for tests

### Dependencies
- **Spring Boot 4.0.5** with Java 21
- **PostgreSQL** for persistence, **Kafka** for events
- **Resilience4j** for circuit breakers (configured in `ResilienceConfig.java`)
- **SpringDoc OpenAPI** for API documentation
- **Testcontainers** for integration testing

## Code Examples

### Creating a new domain entity:
```java
// Domain model with validation
public record Lrt(...) {
    public Lrt {
        if (maxSpeed <= 0) throw new IllegalArgumentException("...");
    }
    public static Lrt createNew(String number, LrtType type, String operator, int maxSpeed) {
        return new Lrt(null, number, type, operator, maxSpeed, Instant.now(), Instant.now());
    }
}
```

### Service layer conversion:
```java
// Convert domain -> entity -> save -> convert back
LrtEntity entity = LrtEntity.builder()
    .lrtNumber(domainLrt.lrtNumber())
    .build();
LrtEntity saved = repository.save(entity);
eventPublisher.publishEvent(new LrtCreatedEvent(saved.getLrtNumber(), ...));
```

### Controller with validation:
```java
@PostMapping
public ResponseEntity<LrtDto> create(@RequestBody @Valid LrtCreateCommand command) {
    return ResponseEntity.status(CREATED).body(service.createLrt(command));
}
```

## Key Files to Reference
- `Lrt.java` - Domain model example
- `LrtService.java` - Service layer patterns  
- `LrtController.java` - REST API patterns
- `LrtEntity.java` - JPA entity structure
- `TestcontainersConfiguration.java` - Test setup</content>
<parameter name="filePath">/home/dev/github/rocLab/lrt-service/AGENTS.md
