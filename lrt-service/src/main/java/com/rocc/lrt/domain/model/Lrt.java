package com.rocc.lrt.domain.model;

import java.time.Instant;

public record Lrt(
        Long id,
        String lrtNumber,
        LrtType type,
        String operator, // "CP"
        int maxSpeed,
        Instant createdAt,
        Instant updatedAt
) {
    // Compact constructor for validation (still immutable!)
    public Lrt {
        if (lrtNumber == null || lrtNumber.isBlank()) {
            throw new IllegalArgumentException("LRT number cannot be empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("LRT type is required");
        }
        if (maxSpeed <= 0) {
            throw new IllegalArgumentException("Max speed must be positive");
        }
        if (operator == null || operator.isBlank()) {
            throw new IllegalArgumentException("Operator cannot be blank");
        }
    }

    // factory method
    public static Lrt createNew(String trainNumber, LrtType type, String operator, int maxSpeedKmh) {
        Instant now = Instant.now();
        return new Lrt(null, trainNumber, type, operator, maxSpeedKmh, now, now);
    }
}
