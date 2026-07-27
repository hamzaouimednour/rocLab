package com.rocc.lrt.domain.event;

import java.time.Instant;

public record LrtPositionEvent(
        String trainNumber,
        double latitude,
        double longitude,
        int speedKmh,
        Instant timestamp,
        String status // "ON_TIME", "DELAYED", "STOPPED"
) {
    // static factory methods
    public static LrtPositionEvent of(String trainNumber, double lat, double lon, int speed) {
        return new LrtPositionEvent(trainNumber, lat, lon, speed, Instant.now(), "ON_TIME");
    }
}