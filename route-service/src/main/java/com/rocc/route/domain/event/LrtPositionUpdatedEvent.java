package com.rocc.route.domain.event;

import java.time.Instant;

public record LrtPositionUpdatedEvent(
        String lrtNumber,
        double latitude,
        double longitude,
        int speedKmh,
        Instant timestamp
) implements RouteEvent {

    public LrtPositionUpdatedEvent(String lrtNumber, double latitude, double longitude, int speedKmh) {
        this(lrtNumber, latitude, longitude, speedKmh, Instant.now());
    }
}