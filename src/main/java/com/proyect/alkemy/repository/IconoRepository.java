package com.proyect.alkemy.repository;

import com.proyect.alkemy.entity.IconoEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface IconoRepository extends JpaRepository<IconoEntity, Long> {

    List<IconoEntity> findByDenominacionIgnoreCase(String denominacion);

    List<IconoEntity> findByFechaDeCreacion(String fecha);

    List<IconoEntity> findByPaises(Long id);

    List<IconoEntity> findAll(Specification<IconoEntity> spec);

}
