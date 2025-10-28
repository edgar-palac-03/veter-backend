package com.company.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Species;

public interface ISpeciesDao extends CrudRepository<Species, Long>{
	
	@Query("select a from Species a where a.name like %?1%")
	List<Species> findByNameLike(String name);
	
	
	List<Species> findByNameContainingIgnoreCase(String name);

}
