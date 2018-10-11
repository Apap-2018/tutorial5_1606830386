package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

public interface PilotService {
	PilotModel getPilotModelByLicenseNumber(String licenseNumber);
	
	void addPilot(PilotModel pilot);
	
	PilotDB getPilots();
	
	void deletePilot(PilotModel pilot);

}
