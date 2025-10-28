package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ISpecialtyDao;
import com.company.inventory.dao.IVetDao;
import com.company.inventory.model.Specialty;
import com.company.inventory.model.Vet;
import com.company.inventory.response.VetResponseRest;

@Service
public class VetServiceImpl implements IVetService {
	
	private ISpecialtyDao specialtyDao;
	private IVetDao vetDao;
	
	public VetServiceImpl(ISpecialtyDao specialtyDao, IVetDao vetDao) {
		super();
		this.specialtyDao = specialtyDao;
		this.vetDao = vetDao;
	}

	@Override
	@Transactional
	public ResponseEntity<VetResponseRest> save(Vet vet, Long specialtyId) {
		
		VetResponseRest response = new VetResponseRest();
		List<Vet> list = new ArrayList<>();
		
		try {
			
			//buscar la especialidad y ponerla en el veterinario
			Optional<Specialty> specialty = specialtyDao.findById(specialtyId);
			
			if( specialty.isPresent()) {
				vet.setSpecialty(specialty.get());
			} else {
				response.setMetadata("respuesta nok", "-1", "Especialidad no encontrada asociada al veterinario ");
				return new ResponseEntity<VetResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//guardar al veterinario
			Vet vetSaved = vetDao.save(vet);
			
			if(vetSaved != null) {
				list.add(vetSaved);
				response.getVet().setVet(list);
				response.setMetadata("respuesta ok", "00", "Veterianrio guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "Veterianrio no guardado ");
				return new ResponseEntity<VetResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar veterinario");
			return new ResponseEntity<VetResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<VetResponseRest>(response, HttpStatus.OK);
		
	}
	

	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<VetResponseRest> searchById(Long id) {

		VetResponseRest response = new VetResponseRest();
		List<Vet> list = new ArrayList<>();
		
		try {
			
			//search producto by id
			Optional<Vet> vet = vetDao.findById(id);
			
			if( vet.isPresent()) {
				
				list.add(vet.get());
				response.getVet().setVet(list);
				response.setMetadata("Respuesta ok", "00", "Veterianrio encontrado");
				
			} else {
				response.setMetadata("respuesta nok", "-1", "Veterianrio no encontrado");
				return new ResponseEntity<VetResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar veterianrio");
			return new ResponseEntity<VetResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<VetResponseRest>(response, HttpStatus.OK);
	}
	

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<VetResponseRest> searchByDpi(long dpi) {
		VetResponseRest response = new VetResponseRest();
	    List<Vet> list = new ArrayList<>();
	    
	    try {
	        // Buscar producto por DPI
	        list = vetDao.findByDpi(dpi);

	        if (!list.isEmpty()) {
	            response.getVet().setVet(list);
	            response.setMetadata("Respuesta ok", "00", "Veterianrio encontrado");
	        } else {
	            response.setMetadata("respuesta nok", "-1", "Veterianrio no encontrado");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setMetadata("respuesta nok", "-1", "Error al buscar veterianrio por DPI");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VetResponseRest> deleteById(Long id) {
		VetResponseRest response = new VetResponseRest();
		
		try {
			
			//delete veterinario by id
			vetDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Veterinario eliminado");
			
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al eliminar veterianrio");
			return new ResponseEntity<VetResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<VetResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<VetResponseRest> search() {
	    VetResponseRest response = new VetResponseRest();
	    List<Vet> list = new ArrayList<>();
	    
	    try {
	        // Buscar todos los veterinarios
	        list = (List<Vet>) vetDao.findAll();
	        
	        if (!list.isEmpty()) {
	            response.getVet().setVet(list);
	            response.setMetadata("Respuesta ok", "00", "Veterinarios encontrados");
	        } else {
	            response.setMetadata("Respuesta nok", "-1", "No se encontraron veterinarios");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setMetadata("Respuesta nok", "-1", "Error al buscar veterinarios");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<VetResponseRest> update(Vet vet, Long specialtyId, Long id) {
	    VetResponseRest response = new VetResponseRest();
	    List<Vet> list = new ArrayList<>();

	    try {
	        // Buscar la especialidad
	        Optional<Specialty> specialty = specialtyDao.findById(specialtyId);
	        if (specialty.isPresent()) {
	            vet.setSpecialty(specialty.get());
	        } else {
	            response.setMetadata("respuesta nok", "-1", "Especialidad no encontrada asociada al veterinario");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	        // Buscar veterinario a actualizar
	        Optional<Vet> vetSearch = vetDao.findById(id);
	        if (vetSearch.isPresent()) {

	            Vet vetToUpdate = vetSearch.get();

	            // Validar DPI único
	            List<Vet> existingDpi = vetDao.findByDpi(vet.getDpi());
	            if (!existingDpi.isEmpty() && !existingDpi.get(0).getId().equals(id)) {
	                response.setMetadata("respuesta nok", "-1", "DPI ya registrado en otro veterinario");
	                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	            }

	            // Validar phone único
	            List<Vet> existingPhone = vetDao.findByPhone(vet.getPhone());
	            if (!existingPhone.isEmpty() && !existingPhone.get(0).getId().equals(id)) {
	                response.setMetadata("respuesta nok", "-1", "Teléfono ya registrado en otro veterinario");
	                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	            }

	            // Actualizar los campos
	            vetToUpdate.setName(vet.getName());
	            vetToUpdate.setLastName(vet.getLastName());
	            vetToUpdate.setDpi(vet.getDpi());
	            vetToUpdate.setPhone(vet.getPhone());
	            vetToUpdate.setSalary(vet.getSalary());
	            vetToUpdate.setSpecialty(vet.getSpecialty());

	            // Guardar cambios
	            Vet updatedVet = vetDao.save(vetToUpdate);
	            list.add(updatedVet);
	            response.getVet().setVet(list);
	            response.setMetadata("respuesta ok", "00", "Veterinario actualizado");

	        } else {
	            response.setMetadata("respuesta nok", "-1", "Veterinario no encontrado");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setMetadata("respuesta nok", "-1", "Error al actualizar veterinario");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}





}