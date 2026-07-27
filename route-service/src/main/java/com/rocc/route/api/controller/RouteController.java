package com.rocc.route.api.controller;

import com.rocc.route.application.dto.LrtPositionCommand;
import com.rocc.route.application.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/position")
    @Operation(summary = "Update train position and publish event")
    public ResponseEntity<String> updatePosition(@RequestBody @Valid LrtPositionCommand command) {
        routeService.updateLrtPosition(command);
        return ResponseEntity.ok("Position update received and published for LRT: " + command.lrtNumber());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Route Service is running");
    }
}