package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Vet;
import com.company.inventory.response.VetResponseRest;

public interface IVetService {
	
	public ResponseEntity<VetResponseRest> save(Vet vet, Long specialtyId);
	public ResponseEntity<VetResponseRest> searchById(Long id);
	public ResponseEntity<VetResponseRest> searchByDpi(long dpi);
	public ResponseEntity<VetResponseRest> deleteById(Long id);
	public ResponseEntity<VetResponseRest> search();
	public ResponseEntity<VetResponseRest> update(Vet vet, Long specialtyId, Long id);




}