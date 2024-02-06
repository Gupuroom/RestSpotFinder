package com.restspotfinder.route.repository;

import com.restspotfinder.route.domain.TempPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempPointRepository extends JpaRepository<TempPoint, Long> {
}