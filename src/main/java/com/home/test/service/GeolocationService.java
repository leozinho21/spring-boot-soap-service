package com.home.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.home.test.dao.GeolocationRepository;
import com.home.test.domain.Geolocation;
import com.home.test.utils.HaversineFormula;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GeolocationService {
	
	@Autowired
	private GeolocationRepository repository;
	
	private Map<LocationKey,Geolocation> cachedMap = new HashMap<>();
	
	//The nearest point will have a counter that will increase +1 with each closest request. 
	private Map<LocationKey,Integer> counterMap = new HashMap<>();
	
	public Geolocation findNearest(double latitude , double longitude) {
		
		LocationKey key = new LocationKey(latitude,longitude);
		
		Geolocation closest = cachedMap.get(key);

		if(closest == null) {
			
			List<Geolocation> locations = repository.findAll(); // we can exclude already cached records from query
			
			if(locations.isEmpty()) {
				return null;
			}
			
			
			double closestDistance = 1000000000000000.0 ;
			
			for(Geolocation gc : locations) { // O(n) complexity
				
				Double dist = HaversineFormula.calcDistance(latitude, longitude,gc.getLocation().getX(), gc.getLocation().getX());
				
				if(dist < closestDistance) {
					closestDistance = dist;
					closest = gc;
				}
			}
			
			cachedMap.put(key,closest);
		}
		
		Integer counter = counterMap.get(key);
		if(counter == null) {
			counter = 0;
			counterMap.put(key, counter);
		}
		counter++;
		
		Logger.getLogger(getClass()).info("Location ["+closest.getName()+"] request counter ["+counter+"]");
		
		return closest;
	}
	
	class LocationKey {
		
		double latitude , longitude;
		
		LocationKey(double latitude , double longitude){
			this.latitude = latitude;
			this.longitude = longitude;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(! (obj instanceof LocationKey) ) {
				return super.equals(obj);
			}
			
			return (this.latitude == ((LocationKey)obj).latitude && this.longitude == ((LocationKey)obj).longitude );
		}
	}
}