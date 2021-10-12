package com.proyect.alkemy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class IconoFilterDto {
    private String name;
    private String date;
    private Set<Long> cities = new HashSet<>();
    private String order;

    public IconoFilterDto(String name, String date, Set<Long> cities, String order) {
        this.name = name;
        this.date = date;
        this.cities = cities;
        this.order = order;
    }

    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }
    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
