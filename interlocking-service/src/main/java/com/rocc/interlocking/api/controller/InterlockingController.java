package com.rocc.interlocking.api.controller;

import com.rocc.interlocking.application.dto.RouteRequestCommand;
import com.rocc.interlocking.application.service.InterlockingService;
import com.rocc.interlocking.domain.model.RouteValidationResult;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interlocking")
@RequiredArgsConstructor
public class InterlockingController {

    private final InterlockingService interlockingService;

    @PostMapping("/validate-route")
    @Operation(summary = "Validate LRT route request (Interlocking logic)")
    public ResponseEntity<RouteValidationResult> validateRoute(@RequestBody @Valid RouteRequestCommand command) {
        RouteValidationResult result = interlockingService.validateRoute(command);
        return ResponseEntity.ok(result);
    }
}