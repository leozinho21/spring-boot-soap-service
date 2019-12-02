package com.home.test.config;

import javax.annotation.PostConstruct;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.home.test.dao.GeolocationRepository;
import com.home.test.domain.Geolocation;

@Configuration
@DependsOn("dataSource")
public class DBInitializer {
	
	@Autowired 
	private GeolocationRepository geolocationRepository;
	
	@PostConstruct
	public void initialize() {
		
		GeometryFactory factory = new GeometryFactory();
		
		Geolocation karaiskakiStadium = new Geolocation();
		karaiskakiStadium.setLocation(factory.createPoint(new Coordinate(37.9465897, 23.6622109 , 17)));
		karaiskakiStadium.getLocation().setSRID(4326);  // needed by geometry types
		karaiskakiStadium.setName("Karaiskaki Stadium, Pireas");
		geolocationRepository.save(karaiskakiStadium);

		Geolocation allianzStadium = new Geolocation();
		allianzStadium.setName("Allianz Arena");
		allianzStadium.setLocation(factory.createPoint(new Coordinate(48.2188033, 11.6225185 , 17)));
		allianzStadium.getLocation().setSRID(4326);
		geolocationRepository.save(allianzStadium);

		Geolocation tottenhamStadium = new Geolocation();
		tottenhamStadium.setName("Tottenham Hotspur Stadium");
		tottenhamStadium.setLocation(factory.createPoint(new Coordinate(51.6042889, -0.06857 , 17)));
		tottenhamStadium.getLocation().setSRID(4326);
		geolocationRepository.save(tottenhamStadium);

		Geolocation asterasStadium = new Geolocation();
		asterasStadium.setName("Rajko Mitic Stadium");
		asterasStadium.setLocation(factory.createPoint(new Coordinate(44.7830618, 20.4626087 , 17)));
		asterasStadium.getLocation().setSRID(4326);
		geolocationRepository.save(asterasStadium);
		
	}
	
}
