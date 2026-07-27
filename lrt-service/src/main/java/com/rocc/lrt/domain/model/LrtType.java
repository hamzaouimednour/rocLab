package com.rocc.lrt.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LrtType {
    // High-Speed / Premium Long-Distance
    ALFA_PENDULAR("AP"),
    INTERCIDADES("IC"),

    // Local and Regional
    REGIONAL("R"),
    INTERREGIONAL("IR"),
    URBANO("U"),

    // International (e.g., Celta)
    INTERNACIONAL("IN"),

    // Logistics (Medway/Captrain)
    FREIGHT("F");

    private final String code;
}