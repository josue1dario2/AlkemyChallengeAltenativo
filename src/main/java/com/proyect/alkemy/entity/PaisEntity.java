package com.proyect.alkemy.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "pais")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE pais SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")
public class PaisEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imagen;
    @Column
    private String denominacion;
    @Column
    private Long cantidadHabitantes;
    @Column
    private Float superficie;

    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "continente_id",insertable = false,updatable = false)
    private ContinenteEntity continente;

    @Column(name = "continente_id",nullable = false)
    private Long continenteId;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        }
    )
    @JoinTable(
            name = "pais_icono",
            joinColumns = @JoinColumn(name = "pais_id"),
            inverseJoinColumns = @JoinColumn(name = "icono_id")
    )
    private List<IconoEntity> iconos = new ArrayList<>();

    public void addIcono(IconoEntity icono){

        this.iconos.add(icono);
    }
    public void removePais(IconoEntity icono){

        this.iconos.remove(icono);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PaisEntity other = (PaisEntity) obj;
        return other.id == this.id;
    }
}
