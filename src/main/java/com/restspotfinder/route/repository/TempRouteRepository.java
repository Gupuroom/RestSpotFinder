package com.restspotfinder.route.repository;

import com.restspotfinder.route.domain.TempRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRouteRepository extends JpaRepository<TempRoute, Long> {
}