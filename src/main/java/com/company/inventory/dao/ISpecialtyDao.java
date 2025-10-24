package com.company.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Specialty;

public interface ISpecialtyDao extends CrudRepository<Specialty, Long>{
	
	@Query("select s from Specialty s where s.name like %?1%")
	List<Specialty> findByName(String name);
	
	List<Specialty> findByNameContainingIgnoreCase(String name);

}
