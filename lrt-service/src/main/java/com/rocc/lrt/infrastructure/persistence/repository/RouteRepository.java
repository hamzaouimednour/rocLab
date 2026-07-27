package com.rocc.lrt.infrastructure.persistence.repository;

import com.rocc.lrt.domain.model.RouteStatus;
import com.rocc.lrt.infrastructure.persistence.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

    List<RouteEntity> findByStatus(RouteStatus status);
    boolean existsByRouteCode(String routeCode);
}