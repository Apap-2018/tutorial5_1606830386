package com.apap.tutorial5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	private FlightDB flightDb;

	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
		
	}

	@Override
	public void deleteFlight(long id) {
		flightDb.deleteById(id);
	}
	
	public FlightModel findById(long id) {
		List<FlightModel> flights = flightDb.findAll();
		FlightModel count = new FlightModel();
		for(FlightModel flight:flights) {
			if(flight.getId()==id) {
				count = flight;
			}
		}
		return count;
	}
}
