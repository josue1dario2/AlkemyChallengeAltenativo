package com.proyect.alkemy.mapper;

import com.proyect.alkemy.dto.IconoDto;
import com.proyect.alkemy.dto.PaisDto;
import com.proyect.alkemy.entity.IconoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class IconoMapper {

    @Autowired
    private PaisMapper paisMapper;

    public IconoEntity convertToEntity(IconoDto iconoDto) {
        IconoEntity entity = new IconoEntity();
                entity.setId(iconoDto.getId());
                entity.setImagen(iconoDto.getImagen());
                entity.setDenominacion(iconoDto.getDenominacion());
                entity.setFechaDeCreacion(stringToLocalDate(iconoDto.getFechaDeCreacion()));
                entity.setAltura(iconoDto.getAltura());
                entity.setHistoria(iconoDto.getHistoria());

        return entity;
    }

    public IconoDto convertToDto(IconoEntity iconoEntity ,boolean loadPaises) {

            IconoDto dto = new IconoDto();
                    dto.setId(iconoEntity.getId());
                    dto.setImagen(iconoEntity.getImagen());
                    dto.setDenominacion(iconoEntity.getDenominacion());
                    dto.setFechaDeCreacion(iconoEntity.getFechaDeCreacion().toString());//Transformamos la fecha de tipo localdate a String con .toString
                    dto.setAltura(iconoEntity.getAltura());
                    dto.setHistoria(iconoEntity.getHistoria());
                    if(loadPaises) {
                        dto.setPaises(paisMapper.convertToDtoList(iconoEntity.getPaises(), false));
                    }
            return dto;

    }
    public IconoDto listaIcono(IconoEntity iconoEntity) {
        IconoDto dto = new IconoDto();
                dto.setImagen(iconoEntity.getImagen());
                dto.setDenominacion(iconoEntity.getDenominacion());
        return dto;
    }
    public List<IconoEntity> iconDtoToEntityList(List<IconoDto> dtos){
        List<IconoEntity> entities = new ArrayList<>();
        for(IconoDto dto : dtos){
            entities.add(this.convertToEntity(dto));
        }
        return entities;
    }
    public List<IconoDto> iconEntityToDtoList(List<IconoEntity> entities,boolean loadPaises){
        List<IconoDto> dtos = new ArrayList<>();
        for(IconoEntity entity : entities){
            dtos.add(convertToDto(entity,loadPaises));
        }
        return dtos;
    }
    private LocalDate stringToLocalDate(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha,formato);
        return date;
    }
    public void iconoEntityRefreshValues(IconoEntity entity,IconoDto iconoDto){
        entity.setImagen(iconoDto.getImagen());
        entity.setDenominacion(iconoDto.getDenominacion());
        entity.setFechaDeCreacion(stringToLocalDate(iconoDto.getFechaDeCreacion()));
        entity.setAltura(iconoDto.getAltura());
        entity.setHistoria(iconoDto.getHistoria());
    }

}
