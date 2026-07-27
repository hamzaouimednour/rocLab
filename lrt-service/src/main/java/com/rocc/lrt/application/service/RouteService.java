package com.rocc.lrt.application.service;

import com.rocc.lrt.application.dto.RouteCreateCommand;
import com.rocc.lrt.application.dto.RouteDto;
import com.rocc.lrt.domain.event.RouteCreatedEvent;
import com.rocc.lrt.domain.model.Route;
import com.rocc.lrt.infrastructure.persistence.entity.RouteEntity;
import com.rocc.lrt.infrastructure.persistence.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public RouteDto createRoute(RouteCreateCommand command) {
        // Check if route code already exists
        if (repository.existsByRouteCode(command.routeCode())) {
            throw new IllegalArgumentException("Route with code " + command.routeCode() + " already exists");
        }

        Route domainRoute = Route.createNew(
                command.routeCode(),
                command.fromStation(),
                command.toStation(),
                command.description()
        );

        // Convert domain to JPA entity
        RouteEntity entity = RouteEntity.builder()
                .routeCode(domainRoute.routeCode())
                .fromStation(domainRoute.fromStation())
                .toStation(domainRoute.toStation())
                .description(domainRoute.description())
                .status(domainRoute.status())
                .createdAt(domainRoute.createdAt())
                .updatedAt(domainRoute.updatedAt())
                .build();

        RouteEntity savedEntity = repository.save(entity);

        // Convert back to domain model for event
        Route savedRoute = new Route(
                savedEntity.getId(),
                savedEntity.getRouteCode(),
                savedEntity.getFromStation(),
                savedEntity.getToStation(),
                savedEntity.getDescription(),
                savedEntity.getStatus(),
                savedEntity.getCreatedAt(),
                savedEntity.getUpdatedAt()
        );

        eventPublisher.publishEvent(new RouteCreatedEvent(
                savedRoute.routeCode(),
                savedRoute.fromStation(),
                savedRoute.toStation(),
                savedRoute.status()
        ));

        return RouteDto.from(savedRoute);
    }

    public List<RouteDto> getAllRoutes() {
        return repository.findAll().stream()
                .map(entity -> RouteDto.from(
                        new Route(
                                entity.getId(),
                                entity.getRouteCode(),
                                entity.getFromStation(),
                                entity.getToStation(),
                                entity.getDescription(),
                                entity.getStatus(),
                                entity.getCreatedAt(),
                                entity.getUpdatedAt()
                        )
                ))
                .toList();
    }

    public RouteDto getRouteById(Long id) {
        RouteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));

        Route route = new Route(
                entity.getId(),
                entity.getRouteCode(),
                entity.getFromStation(),
                entity.getToStation(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );

        return RouteDto.from(route);
    }
}
