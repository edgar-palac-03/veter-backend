package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.IOwnerDao;
import com.company.inventory.model.Owner;
import com.company.inventory.response.OwnerResponseRest;

@Service
public class OwnerServiceImpl implements IOwnerService {
	
	@Autowired
	private IOwnerDao ownerDao;

	@Override
	@Transactional
	public ResponseEntity<OwnerResponseRest> save(Owner owner) {

	    OwnerResponseRest response = new OwnerResponseRest();
	    List<Owner> list = new ArrayList<>();

	    try {
	        // 🔹 Validar si el DPI está vacío
	        if (owner.getDpi() == null || owner.getDpi().trim().isEmpty()) {
	            response.setMetadata("Respuesta nok", "-1", "El DPI no puede estar vacío");
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }

	        // 🔹 Validar si ya existe un dueño con ese DPI
	        List<Owner> existingOwners = ownerDao.findByDpi(owner.getDpi());
	        if (!existingOwners.isEmpty()) {
	            response.setMetadata("Respuesta nok", "-1", "El DPI ya está registrado para otro dueño");
	            return new ResponseEntity<>(response, HttpStatus.CONFLICT); // 409: conflicto
	        }

	        // 🔹 Guardar el nuevo dueño
	        Owner ownerSaved = ownerDao.save(owner);

	        if (ownerSaved != null) {
	            list.add(ownerSaved);
	            response.getOwnerResponse().setOwner(list);
	            response.setMetadata("Respuesta ok", "00", "Dueño guardado correctamente");
	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } else {
	            response.setMetadata("Respuesta nok", "-1", "No se pudo guardar el dueño");
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }

	    } catch (Exception e) {
	        response.setMetadata("Respuesta nok", "-1", "Error al guardar dueño: " + e.getMessage());
	        e.printStackTrace(); // ✅ imprime la traza correctamente
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<OwnerResponseRest> searchById(Long id) {

		OwnerResponseRest response = new OwnerResponseRest();
		List<Owner> list = new ArrayList<>();
				
		try {

			Optional<Owner> owner = ownerDao.findById(id);

			if (owner.isPresent()) {
				list.add(owner.get());
				response.getOwnerResponse().setOwner(list);
				response.setMetadata("Respuesta ok", "00", "Dueño encontrado");
			}else {
				response.setMetadata("Respuesta nok", "-1", "Dueño no encontrado");
				return new ResponseEntity<OwnerResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		}catch(Exception e) {
					
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<OwnerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
				
		return new ResponseEntity<OwnerResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<OwnerResponseRest> searchByDpi(String dpi) {

	    OwnerResponseRest response = new OwnerResponseRest();
	    List<Owner> list = new ArrayList<>();

	    try {
	        // Validación básica del parámetro
	        if (dpi == null || dpi.trim().isEmpty()) {
	            response.setMetadata("Respuesta nok", "-1", "El DPI no puede estar vacío");
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }

	        // Consulta al repositorio (usa tu DAO real aquí)
	        list = ownerDao.findByDpi(dpi);

	        if (!list.isEmpty()) {
	            response.getOwnerResponse().setOwner(list);
	            response.setMetadata("Respuesta ok", "00", "Dueño encontrado");
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            response.setMetadata("Respuesta nok", "-1", "Dueño no encontrado");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        response.setMetadata("Respuesta nok", "-1", "Error al buscar por DPI: " + e.getMessage());
	        e.printStackTrace(); // Puedes cambiarlo por log.error(e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



	@Override
	@Transactional
	public ResponseEntity<OwnerResponseRest> update(Owner owner, Long id) {

	    OwnerResponseRest response = new OwnerResponseRest();
	    List<Owner> list = new ArrayList<>();

	    try {
	        // 🔹 Buscar si el dueño existe
	        Optional<Owner> ownerSearch = ownerDao.findById(id);

	        if (ownerSearch.isPresent()) {
	            Owner existingOwner = ownerSearch.get();

	            // 🔹 Validar que el DPI no esté vacío
	            if (owner.getDpi() == null || owner.getDpi().trim().isEmpty()) {
	                response.setMetadata("Respuesta nok", "-1", "El DPI no puede estar vacío");
	                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	            }

	            // 🔹 Verificar si el nuevo DPI ya pertenece a otro dueño
	            List<Owner> ownersWithSameDpi = ownerDao.findByDpi(owner.getDpi());
	            if (!ownersWithSameDpi.isEmpty() && !ownersWithSameDpi.get(0).getId().equals(id)) {
	                response.setMetadata("Respuesta nok", "-1", "El DPI ya está registrado para otro dueño");
	                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	            }

	            // 🔹 Actualizar campos (puedes agregar más si tu modelo tiene más atributos)
	            existingOwner.setName(owner.getName());
	            existingOwner.setLastName(owner.getLastName());
	            existingOwner.setPhone(owner.getPhone());
	            existingOwner.setEmail(owner.getEmail());
	            existingOwner.setAddress(owner.getAddress());
	            existingOwner.setDpi(owner.getDpi());

	            // 🔹 Guardar cambios
	            Owner ownerUpdated = ownerDao.save(existingOwner);

	            if (ownerUpdated != null) {
	                list.add(ownerUpdated);
	                response.getOwnerResponse().setOwner(list);
	                response.setMetadata("Respuesta ok", "00", "Dueño actualizado correctamente");
	                return new ResponseEntity<>(response, HttpStatus.OK);
	            } else {
	                response.setMetadata("Respuesta nok", "-1", "Error al actualizar el dueño");
	                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	            }

	        } else {
	            response.setMetadata("Respuesta nok", "-1", "Dueño no encontrado");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	    } catch (Exception e) {
	        response.setMetadata("Respuesta nok", "-1", "Error al actualizar dueño: " + e.getMessage());
	        e.printStackTrace();
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



	@Override
	public ResponseEntity<OwnerResponseRest> search() {

		OwnerResponseRest response = new OwnerResponseRest();
		
		try {
			
			List<Owner>owner = (List<Owner>) ownerDao.findAll();
			
			response.getOwnerResponse().setOwner(owner);
			response.setMetadata("Respuesta ok", "00", "Respuesta Exitosa");
			
		}catch(Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<OwnerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<OwnerResponseRest>(response, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<OwnerResponseRest> deleteById(Long id) {
		OwnerResponseRest response = new OwnerResponseRest();
		
		try {
			
			ownerDao.deleteById(id);
			
			response.setMetadata("Respuesta ok", "00", "Dueño eliminado");
			
		}catch(Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<OwnerResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<OwnerResponseRest>(response, HttpStatus.OK);
	}

	
	

}
