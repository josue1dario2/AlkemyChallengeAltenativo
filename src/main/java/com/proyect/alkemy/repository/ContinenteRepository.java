package com.proyect.alkemy.repository;

import com.proyect.alkemy.entity.ContinenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContinenteRepository extends JpaRepository<ContinenteEntity, Long> {

}
