package com.rocc.lrt.application.dto;

import com.rocc.lrt.domain.model.Lrt;
import com.rocc.lrt.domain.model.LrtType;

import java.time.Instant;

public record LrtDto(
        Long id,
        String lrtNumber,
        LrtType type,
        String operator,
        int maxSpeedKmh,
        Instant createdAt,
        Instant updatedAt
) {
    public static LrtDto from(Lrt t) {
        return new LrtDto(
                t.id(),
                t.lrtNumber(),
                t.type(),
                t.operator(),
                t.maxSpeed(),
                t.createdAt(),
                t.updatedAt()
        );
    }
}