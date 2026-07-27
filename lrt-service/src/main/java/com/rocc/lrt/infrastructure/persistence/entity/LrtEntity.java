package com.rocc.lrt.infrastructure.persistence.entity;

import com.rocc.lrt.domain.model.LrtType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "lrts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LrtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String lrtNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LrtType type;

    @Column(nullable = false)
    private String operator;

    private int maxSpeed;

    private Instant createdAt;
    private Instant updatedAt;
}