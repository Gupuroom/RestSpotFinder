package com.restspotfinder.restarea.repository;

import com.restspotfinder.restarea.domain.RestArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestAreaRepository extends JpaRepository<RestArea, Long> {
}