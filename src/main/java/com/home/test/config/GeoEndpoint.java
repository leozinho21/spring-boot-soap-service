package com.home.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.home.test.domain.Geolocation;
import com.home.test.service.GeolocationService;

import webserviceapi.GeolocationRequest;
import webserviceapi.GeolocationResponse;


@Endpoint
public class GeoEndpoint {
	
	private static final String NAMESPACE_URI = "localhost:8080/test/geo";

	@Autowired
	private GeolocationService service;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GeolocationRequest")
	@ResponsePayload
	public GeolocationResponse findNearestStadium(@RequestPayload GeolocationRequest request) {
		
		Geolocation closest = service.findNearest(request.getLatitude(),request.getLongitude());

		GeolocationResponse response = new GeolocationResponse();
		
		if(closest != null) {
			response.setName(closest.getName());
		}
		else {
			response.setFeedback("Nearest area not found.");
		}
		return response;
	}
}