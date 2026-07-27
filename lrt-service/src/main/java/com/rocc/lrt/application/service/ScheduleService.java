package com.rocc.lrt.application.service;

import com.rocc.lrt.application.dto.ScheduleCreateCommand;
import com.rocc.lrt.application.dto.ScheduleDto;
import com.rocc.lrt.domain.event.ScheduleCreatedEvent;
import com.rocc.lrt.domain.model.Schedule;
import com.rocc.lrt.infrastructure.persistence.entity.ScheduleEntity;
import com.rocc.lrt.infrastructure.persistence.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public ScheduleDto createSchedule(ScheduleCreateCommand command) {
        // Validate that arrival time is after departure time
        if (command.arrivalTime().isBefore(command.departureTime())) {
            throw new IllegalArgumentException("Arrival time must be after departure time");
        }

        Schedule domainSchedule = Schedule.createNew(
                command.lrtNumber(),
                command.departureStation(),
                command.arrivalStation(),
                command.departureTime(),
                command.arrivalTime(),
                command.routeCode()
        );

        // Convert domain to JPA entity
        ScheduleEntity entity = ScheduleEntity.builder()
                .lrtNumber(domainSchedule.lrtNumber())
                .departureStation(domainSchedule.departureStation())
                .arrivalStation(domainSchedule.arrivalStation())
                .departureTime(domainSchedule.departureTime())
                .arrivalTime(domainSchedule.arrivalTime())
                .routeCode(domainSchedule.routeCode())
                .status(domainSchedule.status())
                .notes(command.notes()) // Use command notes since domain doesn't have it in createNew
                .build();

        ScheduleEntity savedEntity = repository.save(entity);

        // Convert back to domain model for event
        Schedule savedSchedule = new Schedule(
                savedEntity.getId(),
                savedEntity.getLrtNumber(),
                savedEntity.getDepartureStation(),
                savedEntity.getArrivalStation(),
                savedEntity.getDepartureTime(),
                savedEntity.getArrivalTime(),
                savedEntity.getRouteCode(),
                savedEntity.getStatus(),
                savedEntity.getNotes()
        );

        eventPublisher.publishEvent(new ScheduleCreatedEvent(
                savedSchedule.lrtNumber(),
                savedSchedule.routeCode(),
                savedSchedule.departureStation(),
                savedSchedule.arrivalStation(),
                savedSchedule.departureTime(),
                savedSchedule.arrivalTime(),
                savedSchedule.status()
        ));

        return ScheduleDto.from(savedSchedule);
    }

    public List<ScheduleDto> getAllSchedules() {
        return repository.findAll().stream()
                .map(entity -> ScheduleDto.from(
                        new Schedule(
                                entity.getId(),
                                entity.getLrtNumber(),
                                entity.getDepartureStation(),
                                entity.getArrivalStation(),
                                entity.getDepartureTime(),
                                entity.getArrivalTime(),
                                entity.getRouteCode(),
                                entity.getStatus(),
                                entity.getNotes()
                        )
                ))
                .toList();
    }

    public ScheduleDto getScheduleById(Long id) {
        ScheduleEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        Schedule schedule = new Schedule(
                entity.getId(),
                entity.getLrtNumber(),
                entity.getDepartureStation(),
                entity.getArrivalStation(),
                entity.getDepartureTime(),
                entity.getArrivalTime(),
                entity.getRouteCode(),
                entity.getStatus(),
                entity.getNotes()
        );

        return ScheduleDto.from(schedule);
    }

    public List<ScheduleDto> getSchedulesByLrtNumber(String lrtNumber) {
        return repository.findByLrtNumber(lrtNumber).stream()
                .map(entity -> ScheduleDto.from(
                        new Schedule(
                                entity.getId(),
                                entity.getLrtNumber(),
                                entity.getDepartureStation(),
                                entity.getArrivalStation(),
                                entity.getDepartureTime(),
                                entity.getArrivalTime(),
                                entity.getRouteCode(),
                                entity.getStatus(),
                                entity.getNotes()
                        )
                ))
                .toList();
    }
}
