package com.company.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.company.inventory.model.Owner;

public interface IOwnerDao extends CrudRepository<Owner, Long> {

    @Query("SELECT o FROM Owner o WHERE o.dpi LIKE CONCAT(:dpi, '%')")
    List<Owner> findByDpi(@Param("dpi") String dpi);
}
