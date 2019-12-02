package com.home.test.utils;

public class HaversineFormula {

	final static int R = 6371; // Radius of the earth
	
	public static Double calcDistance(Double lat1,Double lon1,Double lat2,Double lon2) {
		Double latDistance = degToRad(lat2 - lat1);
		Double lonDistance = degToRad(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(degToRad(lat1)) * Math.cos(degToRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;// Distance in km
	}
	
	private static Double degToRad(Double deg) {
		return deg * Math.PI / 180;
	}

}