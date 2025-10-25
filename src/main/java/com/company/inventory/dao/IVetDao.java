package com.company.inventory.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.company.inventory.model.Vet;

public interface IVetDao extends CrudRepository<Vet, Long> {
    
    @Query("SELECT v FROM Vet v WHERE v.dpi = :dpi")
    List<Vet> findByDpi(@Param("dpi") long dpi);
    List<Vet> findByPhone(@Param("phone") long phone);
}
