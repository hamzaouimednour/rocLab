package com.rocc.interlocking.domain.event;

import java.time.Instant;

/**
 * Event received from Kafka when an LRT vehicle reports its position
 */
public record LrtPositionReceivedEvent(
        String lrtNumber,
        double latitude,
        double longitude,
        int speedKmh,
        Instant timestamp
) {
}