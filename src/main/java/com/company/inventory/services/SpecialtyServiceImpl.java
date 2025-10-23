package com.company.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ISpecialtyDao;
import com.company.inventory.model.Specialty;
import com.company.inventory.response.SpecialtyResponseRest;


@Service
public class SpecialtyServiceImpl implements ISpecialtyService {
	
	@Autowired
	private ISpecialtyDao specialtyDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SpecialtyResponseRest> search() {
		
		SpecialtyResponseRest response = new SpecialtyResponseRest();
		
		try {
			
			List<Specialty>specialty = (List<Specialty>) specialtyDao.findAll();
			
			response.getSpecialtyResponse().setSpecialty(specialty);
			response.setMetadata("Respuesta ok", "00", "Respuesta Exitosa");
			
		}catch(Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.OK);
	}

}
