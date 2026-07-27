package com.rocc.route.application.service;

import com.rocc.route.application.dto.LrtPositionCommand;
import com.rocc.route.domain.event.LrtPositionUpdatedEvent;
import com.rocc.route.infrastructure.kafka.RouteEventProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RouteService {

    private final RouteEventProducer producer;

    public void updateLrtPosition(LrtPositionCommand command) {
        LrtPositionUpdatedEvent event = new LrtPositionUpdatedEvent(
                command.lrtNumber(),
                command.latitude(),
                command.longitude(),
                command.speedKmh()
        );

        producer.publishPositionUpdate(event);
    }
}