package com.proyect.alkemy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.proyect.alkemy.entity.PaisEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContinenteDto {

    private Long id;
    private String imagen;
    private String denominacion;

}
