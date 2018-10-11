package com.apap.tutorial5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	
	@Autowired
	private PilotDB pilotDb;
	

	@Override
	public PilotModel getPilotModelByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}

	@Override
	public PilotDB getPilots() {
		return pilotDb;
	}

	@Override
	public void deletePilot(PilotModel pilot) {
		pilotDb.delete(pilot);
		
	}

}
