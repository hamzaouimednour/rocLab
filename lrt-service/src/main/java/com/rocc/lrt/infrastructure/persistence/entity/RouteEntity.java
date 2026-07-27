package com.rocc.lrt.infrastructure.persistence.entity;

import com.rocc.lrt.domain.model.RouteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String routeCode;

    @Column(nullable = false)
    private String fromStation;

    @Column(nullable = false)
    private String toStation;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RouteStatus status;

    private Instant createdAt;
    private Instant updatedAt;
}