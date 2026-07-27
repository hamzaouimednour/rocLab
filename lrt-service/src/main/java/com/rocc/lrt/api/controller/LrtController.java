package com.rocc.lrt.api.controller;

import com.rocc.lrt.application.dto.LrtCreateCommand;
import com.rocc.lrt.application.dto.LrtDto;
import com.rocc.lrt.application.service.LrtService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lrts")
public class LrtController {

    private final LrtService lrtService;

    @PostMapping
    @Operation(summary = "Create a new lrt")
    public ResponseEntity<LrtDto> createLrt(@RequestBody @Valid LrtCreateCommand command) {
        LrtDto created = lrtService.createLrt(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<LrtDto>> getAllLrts() {
        return ResponseEntity.ok(lrtService.getAllLrts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LrtDto> getLrtById(@PathVariable Long id) {
        return ResponseEntity.ok(lrtService.getLrtById(id));
    }
}