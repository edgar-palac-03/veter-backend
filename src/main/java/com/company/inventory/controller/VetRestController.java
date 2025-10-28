
package com.company.inventory.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.Vet;
import com.company.inventory.response.VetResponseRest;
import com.company.inventory.services.IVetService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/vet1")
public class VetRestController {
	
	private IVetService vetService;
	
	public VetRestController(IVetService vetService) {
		super();
		this.vetService = vetService;
	}
	
	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param dpi
	 * @param phone
	 * @param specialtyID
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/vet")
	public ResponseEntity<VetResponseRest> save(
			@RequestParam("name") String name,
			@RequestParam("lastName") String lastName,
			@RequestParam("dpi") long dpi,
			@RequestParam("phone") Long phone,
			@RequestParam("salary") BigDecimal salary,
			@RequestParam("specialtyId") Long specialtyID) throws IOException
	{
		
		Vet vet = new Vet();
		vet.setName(name);
		vet.setLastName(lastName);
		vet.setDpi(dpi);
		vet.setPhone(phone);
		vet.setSalary(salary);
		
		ResponseEntity<VetResponseRest> response = vetService.save(vet, specialtyID);
		
		return response;
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/vet/{id}")
	public ResponseEntity<VetResponseRest> searchById(@PathVariable Long id) {
		ResponseEntity<VetResponseRest> response = vetService.searchById(id);
		return response;
	}
	
	
	/**
	 * 
	 * @param dpi
	 * @return
	 */
	@GetMapping("/vet/dpi/{dpi}")
	public ResponseEntity<VetResponseRest> searchByDpi(@PathVariable long dpi) {
	    ResponseEntity<VetResponseRest> response = vetService.searchByDpi(dpi);
	    return response;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/vet/{id}")
	public ResponseEntity<VetResponseRest> deleteById(@PathVariable Long id){
		ResponseEntity<VetResponseRest> response = vetService.deleteById(id);
		return response;
	}


	/**
	 * 
	 * @return
	 */
	@GetMapping("/vet")
	public ResponseEntity<VetResponseRest> search() {
		ResponseEntity<VetResponseRest> response = vetService.search();
		return response;
	}
	
	@PutMapping("/vet/{id}")
	public ResponseEntity<VetResponseRest> update(
	        @RequestParam("name") String name,
	        @RequestParam("lastName") String lastName,
	        @RequestParam("dpi") long dpi,
	        @RequestParam("phone") long phone,
	        @RequestParam("salary") BigDecimal salary,
	        @RequestParam("specialtyId") Long specialtyId,
	        @PathVariable Long id) {

	    // Crear objeto Vet con los datos recibidos
	    Vet vet = new Vet();
	    vet.setName(name);
	    vet.setLastName(lastName);
	    vet.setDpi(dpi);
	    vet.setPhone(phone);
	    vet.setSalary(salary);

	    // Llamar al servicio para actualizar
	    ResponseEntity<VetResponseRest> response = vetService.update(vet, specialtyId, id);

	    return response;
	}


	

}
