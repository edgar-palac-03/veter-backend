package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Owner;
import com.company.inventory.response.OwnerResponseRest;

public interface IOwnerService {
	
	public ResponseEntity<OwnerResponseRest> save(Owner owner);
	public ResponseEntity<OwnerResponseRest> searchById(Long id);
	public ResponseEntity<OwnerResponseRest> searchByDpi(String dpi);
	public ResponseEntity<OwnerResponseRest> update(Owner owner, Long id);
	public ResponseEntity<OwnerResponseRest> search();
	public ResponseEntity<OwnerResponseRest> deleteById(Long id);

	

	
	

}
