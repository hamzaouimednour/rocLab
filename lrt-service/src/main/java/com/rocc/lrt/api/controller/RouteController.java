package com.rocc.lrt.api.controller;

import com.rocc.lrt.application.dto.RouteCreateCommand;
import com.rocc.lrt.application.dto.RouteDto;
import com.rocc.lrt.application.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    @Operation(summary = "Create a new route")
    public ResponseEntity<RouteDto> createRoute(@RequestBody @Valid RouteCreateCommand command) {
        RouteDto created = routeService.createRoute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<RouteDto>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }
}
