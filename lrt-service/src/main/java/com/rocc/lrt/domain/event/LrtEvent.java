package com.rocc.lrt.domain.event;

import java.time.Instant;

public sealed interface LrtEvent permits
        LrtCreatedEvent,
        LrtUpdatedEvent,
        LrtDeletedEvent {

    String trainNumber();
    Instant timestamp();
}