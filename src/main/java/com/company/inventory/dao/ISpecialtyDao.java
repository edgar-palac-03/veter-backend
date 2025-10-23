package com.company.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Specialty;

public interface ISpecialtyDao extends CrudRepository<Specialty, Long>{

}
