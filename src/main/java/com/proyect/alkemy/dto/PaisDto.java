package com.proyect.alkemy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.proyect.alkemy.entity.ContinenteEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaisDto {

    private Long id;
    private String imagen;
    private String denominacion;
    private Long cantidadHabitantes;
    private Float superficie;
    private Long continenteId;
    private List<IconoDto>iconos = new ArrayList<>();

}
