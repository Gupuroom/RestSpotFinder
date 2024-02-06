package com.restspotfinder.restarea.repository;

import com.restspotfinder.restarea.domain.RestArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestAreaRepository extends JpaRepository<RestArea, Long> {
    @Query(value = "SELECT r.* FROM temp_point t " +
            "JOIN rest_area r ON ST_DWithin( " +
            "    CAST(ST_SetSRID(t.position, 4326) AS geography), " +
            "    CAST(ST_SetSRID(r.position, 4326) AS geography), " +
            "    500) AND t.route_id = :tempRouteId"
            , nativeQuery = true)
    List<RestArea> findNearbyRoutes(@Param("tempRouteId") Long tempRouteId);
}