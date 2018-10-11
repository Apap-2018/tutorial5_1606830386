package com.apap.tutorial5.controller;

import java.util.List;

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
import com.apap.tutorial5.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	
	@RequestMapping(value = "/pilot/view-pilot", method = RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotModelByLicenseNumber(licenseNumber);
		//List<FlightModel> pilots = archive.getPilotFlight();
		model.addAttribute("pilot", archive);
		//model.addAttribute("pilots", pilots);
		return "view-pilot";
	}
	
	/*@RequestMapping(value = "/pilot/view/")
	private String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotModelByLicenseNumber(licenseNumber);
		List<FlightModel> archive = pilot.getPilotFlight();
		model.addAttribute("pilots", archive);
		return "view";
	}*/
	
	@RequestMapping(value="/pilot/delete", method = RequestMethod.POST)
	private String deletePilot(@RequestParam String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotModelByLicenseNumber(licenseNumber);		
		pilotService.deletePilot(pilot);
		return "delete";
	}
	
	@RequestMapping(value="/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String UpdatePilot(@PathVariable String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotModelByLicenseNumber(licenseNumber);		
		model.addAttribute("pilot", pilot);
		return "update-pilot";
	}
	
	@RequestMapping(value="/pilot/update", method = RequestMethod.POST)
	private String UpdatePilot(@ModelAttribute PilotModel newPilot) {
		String licenseNumber = newPilot.getLicenseNumber();
		String name = newPilot.getName();
		int flyHour = newPilot.getFlyHour();
		
		PilotModel pilot = pilotService.getPilotModelByLicenseNumber(licenseNumber);
		
		pilotService.deletePilot(pilot);
		
		pilot.setFlyHour(flyHour);
		pilot.setName(name);
		
		pilotService.addPilot(pilot);
		return "update";
	}
}
