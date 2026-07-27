package com.rocc.route.infrastructure.kafka;

import com.rocc.route.domain.event.LrtPositionUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RouteEventProducer {

    private final Environment env;
    private final KafkaTemplate<String, LrtPositionUpdatedEvent> kafkaTemplate;

    public void publishPositionUpdate(LrtPositionUpdatedEvent event) {
        String topicName = env.getProperty("spring.kafka.topic.lrt-positions", "lrt.positions");
        kafkaTemplate.send(topicName, event.lrtNumber(), event);
        log.info("Published position event for train: {}", event.lrtNumber());
    }
}