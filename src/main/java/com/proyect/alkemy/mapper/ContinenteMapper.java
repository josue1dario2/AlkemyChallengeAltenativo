package com.proyect.alkemy.mapper;

import com.proyect.alkemy.dto.ContinenteDto;
import com.proyect.alkemy.entity.ContinenteEntity;
import org.springframework.stereotype.Component;

@Component
public class ContinenteMapper {

    public ContinenteEntity convertToEntity(ContinenteDto continenteDto) {

        ContinenteEntity entity = new ContinenteEntity();
                entity.setId(continenteDto.getId());
                entity.setImagen(continenteDto.getImagen());
                entity.setDenominacion(continenteDto.getDenominacion());
        return entity;
    }

    public ContinenteDto convertToDto(ContinenteEntity continenteEntity) {

        ContinenteDto dto = new  ContinenteDto();
                dto.setId(continenteEntity.getId());
                dto.setImagen(continenteEntity.getImagen());
                dto.setDenominacion(continenteEntity.getDenominacion());
        return dto;
    }
}
