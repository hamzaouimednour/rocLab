package com.rocc.lrt.api.controller;

import com.rocc.lrt.application.dto.ScheduleCreateCommand;
import com.rocc.lrt.application.dto.ScheduleDto;
import com.rocc.lrt.application.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "Create a new schedule")
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody @Valid ScheduleCreateCommand command) {
        ScheduleDto created = scheduleService.createSchedule(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @GetMapping("/lrt/{lrtNumber}")
    public ResponseEntity<List<ScheduleDto>> getSchedulesByLrtNumber(@PathVariable String lrtNumber) {
        return ResponseEntity.ok(scheduleService.getSchedulesByLrtNumber(lrtNumber));
    }
}
