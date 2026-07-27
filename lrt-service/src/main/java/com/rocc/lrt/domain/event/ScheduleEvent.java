package com.rocc.lrt.domain.event;

import java.time.Instant;

public sealed interface ScheduleEvent permits
        ScheduleCreatedEvent,
        ScheduleUpdatedEvent,
        ScheduleDeletedEvent {

    String lrtNumber();
    Instant timestamp();
}
