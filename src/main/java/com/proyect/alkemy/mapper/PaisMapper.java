package com.proyect.alkemy.mapper;

import com.proyect.alkemy.dto.PaisDto;
import com.proyect.alkemy.entity.PaisEntity;
import com.proyect.alkemy.service.impl.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaisMapper{

    @Autowired
    private IconoMapper iconoMapper;


    public PaisEntity convertToEntity(PaisDto paisDto) {
        PaisEntity entity = new PaisEntity();
                entity.setId(paisDto.getId());
                entity.setImagen(paisDto.getImagen());
                entity.setDenominacion(paisDto.getDenominacion());
                entity.setCantidadHabitantes(paisDto.getCantidadHabitantes());
                entity.setSuperficie(paisDto.getSuperficie());
                entity.setContinenteId(paisDto.getContinenteId());
                entity.setIconos(iconoMapper.iconDtoToEntityList(paisDto.getIconos()));

        return entity;
    }


    public PaisDto convertToDto(PaisEntity paisEntity,boolean loadIcons) {
            PaisDto dto = new PaisDto();
                    dto.setId(paisEntity.getId());
                    dto.setImagen(paisEntity.getImagen());
                    dto.setDenominacion(paisEntity.getDenominacion());
                    dto.setCantidadHabitantes(paisEntity.getCantidadHabitantes());
                    dto.setSuperficie(paisEntity.getSuperficie());
                    dto.setContinenteId(paisEntity.getContinenteId());
                    if(loadIcons) {
                        dto.setIconos(iconoMapper.iconEntityToDtoList(paisEntity.getIconos(), false));
                    }
            return dto;

    }

    public PaisDto convertToDtoCities(PaisEntity paisEntity) {
        PaisDto dto = new PaisDto();
                dto.setImagen(paisEntity.getImagen());
                dto.setDenominacion(paisEntity.getDenominacion());
                dto.setCantidadHabitantes(paisEntity.getCantidadHabitantes());

        return dto;
    }

    public List<PaisDto> convertToDtoList(List<PaisEntity> entities,boolean loadIcons){
        List<PaisDto> dtos = new ArrayList<>();
        for(PaisEntity pais : entities){
            dtos.add(convertToDto(pais,loadIcons));
        }
        return  dtos;
    }
    public List<PaisEntity> convertToEntityList(List<PaisDto> dtos){
        List<PaisEntity> paises = new ArrayList<>();
        for(PaisDto dto : dtos){
            paises.add(convertToEntity(dto));
        }
        return paises;
    }
}
