package com.rocc.interlocking.domain.model;

public record RouteValidationResult(
        boolean approved,
        String message,
        String routeId,
        String reasonCode
) {
    public static RouteValidationResult approved(String routeId) {
        return new RouteValidationResult(true, "Route approved", routeId, "SAFE");
    }

    public static RouteValidationResult rejected(String reason) {
        return new RouteValidationResult(false, "Route rejected: " + reason, null, "CONFLICT");
    }
}