package com.rocc.lrt.application.service;

import com.rocc.lrt.application.dto.LrtCreateCommand;
import com.rocc.lrt.application.dto.LrtDto;
import com.rocc.lrt.domain.event.LrtCreatedEvent;
import com.rocc.lrt.domain.model.Lrt;
import com.rocc.lrt.infrastructure.persistence.entity.LrtEntity;
import com.rocc.lrt.infrastructure.persistence.repository.LrtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LrtService {

    private final LrtRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public LrtDto createLrt(LrtCreateCommand command) {
        Lrt domainLrt = Lrt.createNew(
                command.lrtNumber(),
                command.type(),
                command.operator(),
                command.maxSpeed()
        );

        // Convert domain to JPA entity
        LrtEntity entity = LrtEntity.builder()
                .lrtNumber(domainLrt.lrtNumber())
                .type(domainLrt.type())
                .operator(domainLrt.operator())
                .maxSpeed(domainLrt.maxSpeed())
                .createdAt(domainLrt.createdAt())
                .updatedAt(domainLrt.updatedAt())
                .build();

        LrtEntity savedEntity = repository.save(entity);

        // Convert back to domain model for event
        Lrt savedLrt = new Lrt(
                savedEntity.getId(),
                savedEntity.getLrtNumber(),
                savedEntity.getType(),
                savedEntity.getOperator(),
                savedEntity.getMaxSpeed(),
                savedEntity.getCreatedAt(),
                savedEntity.getUpdatedAt()
        );

        eventPublisher.publishEvent(new LrtCreatedEvent(
                savedLrt.lrtNumber(),
                savedLrt.type(),
                savedLrt.operator()
        ));

        return LrtDto.from(savedLrt);
    }

    public List<LrtDto> getAllLrts() {
        return repository.findAll().stream()
                .map(entity -> LrtDto.from(
                        new Lrt(
                                entity.getId(),
                                entity.getLrtNumber(),
                                entity.getType(),
                                entity.getOperator(),
                                entity.getMaxSpeed(),
                                entity.getCreatedAt(),
                                entity.getUpdatedAt()
                        )
                ))
                .toList();
    }

    public LrtDto getLrtById(Long id) {
        LrtEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lrt not found with id: " + id));

        Lrt lrt = new Lrt(
                entity.getId(),
                entity.getLrtNumber(),
                entity.getType(),
                entity.getOperator(),
                entity.getMaxSpeed(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );

        return LrtDto.from(lrt);
    }
}