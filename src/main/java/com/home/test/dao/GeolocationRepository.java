package com.home.test.dao;

import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.test.domain.Geolocation;


public interface GeolocationRepository extends JpaRepository<Geolocation, Long> {
	
	@Query(value = "SELECT se FROM Geolocation se WHERE within(se.location, :bounds) = true AND se.name LIKE :filter")
    public List<Geolocation> findAllWithin(@Param("bounds") Geometry bounds, @Param("filter") String titleFilter);

}