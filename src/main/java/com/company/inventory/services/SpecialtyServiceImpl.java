package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SpecialtyResponseRest> searchById(Long id) {
		SpecialtyResponseRest response = new SpecialtyResponseRest();
		List<Specialty> list = new ArrayList<>();
		
		try {
			
			Optional<Specialty> specialty = specialtyDao.findById(id);
			
			if(specialty.isPresent()) {
				list.add(specialty.get());
				response.getSpecialtyResponse().setSpecialty(list);
				response.setMetadata("Respiuesta ok", "00", "Especialidad encontrada");
			} else {
				response.setMetadata("Respiuesta nok", "-1", "Especialidad no encontrada");
				return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e){
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SpecialtyResponseRest> searchByName(String name) {
	    SpecialtyResponseRest response = new SpecialtyResponseRest();
	    List<Specialty> list = new ArrayList<>();

	    try {
	        // Buscar por nombre (ignorando mayúsculas/minúsculas)
	        list = specialtyDao.findByNameContainingIgnoreCase(name);

	        if (!list.isEmpty()) {
	            response.getSpecialtyResponse().setSpecialty(list);
	            response.setMetadata("Respuesta ok", "00", "Especialidades encontradas");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.setMetadata("Respuesta nok", "-1", "Especialidades no encontradas");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        response.setMetadata("Respuesta nok", "-1", "Error al buscar por nombre: " + e.getMessage());
	        e.printStackTrace();
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	

	@Override
	@Transactional
	public ResponseEntity<SpecialtyResponseRest> save(Specialty specialty) {

		SpecialtyResponseRest response = new SpecialtyResponseRest();
		List<Specialty> list = new ArrayList<>();
				
		try {

			Specialty specialtySaved = specialtyDao.save(specialty);
			
			if(specialtySaved != null) {
				list.add(specialtySaved);
				response.getSpecialtyResponse().setSpecialty(list);
				response.setMetadata("Respuesta ok", "00", "Categoría guardada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Categoría no guardada");
				return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		}catch(Exception e) {
					
			response.setMetadata("Respuesta nok", "-1", "Error al grabar categoria");
			e.getStackTrace();
			return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
				
		return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SpecialtyResponseRest> deleteById(Long id) {

		SpecialtyResponseRest response = new SpecialtyResponseRest();
		
		try {
			specialtyDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Especialidad eliminada");
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al eliminar especialidad");
			return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SpecialtyResponseRest> update(Specialty specialty, Long id) {

		
		SpecialtyResponseRest response = new SpecialtyResponseRest();
		List<Specialty> list = new ArrayList<>();
				
		try {

			Optional<Specialty> specialtySearch = specialtyDao.findById(id);
			
			if(specialtySearch.isPresent()) {
				//Se procedera a actualizar el registro
				specialtySearch.get().setName(specialty.getName());		
				specialtySearch.get().setDescription(specialty.getDescription());
				
				Specialty apecialtyToUpdate = specialtyDao.save(specialtySearch.get());
				
				if(apecialtyToUpdate != null) {
					list.add(apecialtyToUpdate);
					response.getSpecialtyResponse().setSpecialty(list);
					response.setMetadata("Respuesta ok", "00", "Categoría actualizada");
				} else {
					response.setMetadata("Respuesta nok", "-1", "Categoría no actualizada");
					return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
				
			} else {
				response.setMetadata("Respuesta nok", "-1", "Categoría no encontrada");
				return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
					
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar categoria");
			e.getStackTrace();
			return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
				
		return new ResponseEntity<SpecialtyResponseRest>(response, HttpStatus.OK);

	}


}
