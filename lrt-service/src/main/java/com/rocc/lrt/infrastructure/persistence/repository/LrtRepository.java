package com.rocc.lrt.infrastructure.persistence.repository;

import com.rocc.lrt.infrastructure.persistence.entity.LrtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LrtRepository extends JpaRepository<LrtEntity, Long> {

    boolean existsByLrtNumber(String lrtNumber);

    Optional<LrtEntity> findByLrtNumber(String trainNumber);
}
