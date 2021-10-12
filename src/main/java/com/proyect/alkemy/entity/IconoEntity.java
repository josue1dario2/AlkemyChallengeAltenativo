package com.proyect.alkemy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "icono")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE icono SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")
public class IconoEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imagen;
    @Column
    private String denominacion;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDeCreacion;
    @Column
    private Float altura;
    @Column
    private String historia;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "iconos",cascade = CascadeType.ALL)
    private List<PaisEntity> paises = new ArrayList<>();

    /*public void addPais(PaisEntity pais){
        this.paises.add(pais);
    }
    public void removePais(PaisEntity pais){
        this.paises.remove(pais);
    }

     */

}
