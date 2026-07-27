package com.rocc.lrt.domain.model;


public enum ScheduleStatus {

    PLANNED,
    CONFIRMED,
    RUNNING,
    DELAYED,
    COMPLETED,
    CANCELLED,
    ABORTED,
    SKIPPED;

    public boolean isActive() {
        return this == RUNNING || this == DELAYED;
    }

    public boolean isFinished() {
        return this == COMPLETED || this == CANCELLED || this == ABORTED;
    }
}