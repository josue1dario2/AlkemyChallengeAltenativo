package com.proyect.alkemy.repository;

import com.proyect.alkemy.entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<PaisEntity, Long> {

    PaisEntity findByDenominacionIgnoreCase(String denominacion);

    @Query(value = "SELECT * FROM pais p WHERE p.continenteId = ?1 ",nativeQuery = true)
    List<PaisEntity> findByIdContinente(Long continenteId);

    //List<PaisEntity> findAllOrderByDenominacionAsc();

    //List<PaisEntity> findAllOrderByDenominacionDesc();

}
