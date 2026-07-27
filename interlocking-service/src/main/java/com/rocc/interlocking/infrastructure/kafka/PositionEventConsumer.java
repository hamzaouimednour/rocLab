package com.rocc.interlocking.infrastructure.kafka;

import com.rocc.interlocking.application.service.InterlockingService;
import com.rocc.interlocking.domain.event.LrtPositionReceivedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PositionEventConsumer {

    private final InterlockingService interlockingService;

    public PositionEventConsumer(InterlockingService interlockingService) {
        this.interlockingService = interlockingService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.train-positions:lrt.positions}",
            groupId = "interlocking-group")
    public void consumePosition(LrtPositionReceivedEvent event) {
        System.out.println("Received LRT position from Kafka: " + event.lrtNumber());

        interlockingService.handlePositionUpdate(
                event.lrtNumber(),
                event.latitude(),
                event.longitude(),
                event.speedKmh()
        );
    }
}