package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	
	void deleteFlight(long id);

	FlightModel findById(long id);


}
