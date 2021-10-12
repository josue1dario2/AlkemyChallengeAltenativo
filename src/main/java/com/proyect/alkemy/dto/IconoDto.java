package com.proyect.alkemy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IconoDto {

    private Long id;
    private String imagen;
    private String denominacion;
    private String fechaDeCreacion;
    private Float altura;
    private String historia;
    private List<PaisDto> paises = new ArrayList<>();
}
