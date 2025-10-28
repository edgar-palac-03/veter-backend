package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ISpeciesDao;
import com.company.inventory.model.Species;
import com.company.inventory.response.SpeciesResponseRest;


@Service
public class SpeciesServiceImpl implements ISpeciesService {
	
	@Autowired
	private ISpeciesDao speciesDao;

	@Override
	@Transactional
	public ResponseEntity<SpeciesResponseRest> save(Species species) {
		
		SpeciesResponseRest response = new SpeciesResponseRest();
		List<Species> list = new ArrayList<>();
				
		try {

			Species speciesSaved = speciesDao.save(species);
			
			if(speciesSaved != null) {
				list.add(speciesSaved);
				response.getSpeciesResponse().setSpecies(list);
				response.setMetadata("Respuesta ok", "00", "Especie guardada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Especie no guardada");
				return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		}catch(Exception e) {
					
			response.setMetadata("Respuesta nok", "-1", "Error al grabar especie");
			e.getStackTrace();
			return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
				
		return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SpeciesResponseRest> searchById(Long id) {
		SpeciesResponseRest response = new SpeciesResponseRest();
		List<Species> list = new ArrayList<>();
				
		try {

			Optional<Species> species = speciesDao.findById(id);

			if (species.isPresent()) {
				list.add(species.get());
				response.getSpeciesResponse().setSpecies(list);
				response.setMetadata("Respuesta ok", "00", "Especie encontrada");
			}else {
				response.setMetadata("Respuesta nok", "-1", "Especie no encontrada");
				return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		}catch(Exception e) {
					
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
				
		return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SpeciesResponseRest> searchByName(String name) {

	    SpeciesResponseRest response = new SpeciesResponseRest();
	    List<Species> list;

	    try {
	        // Buscar especies por nombre (ignorando mayúsculas/minúsculas)
	        list = speciesDao.findByNameContainingIgnoreCase(name);

	        if (!list.isEmpty()) {
	            response.getSpeciesResponse().setSpecies(list);
	            response.setMetadata("Respuesta ok", "00", "Especie encontrada");
	        } else {
	            response.setMetadata("Respuesta nok", "-1", "Especie no encontrada");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setMetadata("Respuesta nok", "-1", "Error al buscar especie por nombre");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<SpeciesResponseRest> search() {

		SpeciesResponseRest response = new SpeciesResponseRest();
		
		try {
			
			List<Species>species = (List<Species>) speciesDao.findAll();
			
			response.getSpeciesResponse().setSpecies(species);
			response.setMetadata("Respuesta ok", "00", "Respuesta Exitosa");
			
		}catch(Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SpeciesResponseRest> deleteById(Long Id) {

		SpeciesResponseRest response = new SpeciesResponseRest();
		
		try {
			
			speciesDao.deleteById(Id);
			
			response.setMetadata("Respuesta ok", "00", "Registro eliminado");
			
		}catch(Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SpeciesResponseRest> update(Species species, Long Id) {
		SpeciesResponseRest response = new SpeciesResponseRest();
		List<Species> list = new ArrayList<>();
				
		try {

			Optional<Species> speciesSearch = speciesDao.findById(Id);
			
			if(speciesSearch.isPresent()) {
				//Se procedera a actualizar el registro
				speciesSearch.get().setName(species.getName());		
				speciesSearch.get().setData(species.getData());
				
				Species speciesToUpdate = speciesDao.save(speciesSearch.get());
				
				if(speciesToUpdate != null) {
					list.add(speciesToUpdate);
					response.getSpeciesResponse().setSpecies(list);
					response.setMetadata("Respuesta ok", "00", "Categoría actualizada");
				} else {
					response.setMetadata("Respuesta nok", "-1", "Categoría no actualizada");
					return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
				
			} else {
				response.setMetadata("Respuesta nok", "-1", "Categoría no encontrada");
				return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
					
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar categoria");
			e.getStackTrace();
			return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
				
		return new ResponseEntity<SpeciesResponseRest>(response, HttpStatus.OK);
	}


}
