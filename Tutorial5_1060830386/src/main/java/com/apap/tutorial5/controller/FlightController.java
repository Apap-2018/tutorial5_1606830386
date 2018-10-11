package com.apap.tutorial5.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value= "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		
		PilotModel pilot = pilotService.getPilotModelByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight: pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.GET)
	private String UpdateFlight(@RequestParam long id, Model model) {
		FlightModel flight = flightService.findById(id);
		model.addAttribute("flight", flight);
		return "update-Flight";
	}
	
	@RequestMapping(value="/flight/update/{licenseNumber}", method = RequestMethod.POST)
	private String UpdatedFlight(@ModelAttribute FlightModel newFlight) {
		
		
		FlightModel flight = flightService.findById(newFlight.getId());
		
		flightService.deleteFlight(newFlight.getId());
		
		flight.setFlightNumber(newFlight.getFlightNumber());
		flight.setDestination(newFlight.getDestination());
		flight.setOrigin(newFlight.getOrigin());
		flight.setTime(newFlight.getTime());
		
		flightService.addFlight(flight);
		return "update";
	}
}
