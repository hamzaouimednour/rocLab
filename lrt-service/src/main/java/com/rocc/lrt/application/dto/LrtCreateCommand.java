package com.rocc.lrt.application.dto;

import com.rocc.lrt.domain.model.LrtType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Command object used to create a new Train.
 * This is what the frontend sends to the API.
 */
public record LrtCreateCommand(

        @NotBlank(message = "LRT number is required")
        @Size(min = 3, max = 20, message = "LRT number must be between 3 and 20 characters")
        String lrtNumber,

        @NotNull(message = "LRT type is required")
        LrtType type,

        @NotBlank(message = "Operator is required")
        @Size(max = 100, message = "Operator name is too long")
        String operator,

        @Min(value = 10, message = "Maximum speed must be at least 10 km/h")
        int maxSpeed

) {
}