package com.proyect.alkemy.service.impl.especificacion;

import com.proyect.alkemy.dto.IconoFilterDto;
import com.proyect.alkemy.entity.IconoEntity;
import com.proyect.alkemy.entity.PaisEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class IconoEspecificacion {

    public Specification<IconoEntity> getByFilters(IconoFilterDto filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("denominacion")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"));
            }

            if (StringUtils.hasLength(filtersDTO.getDate())) {
            
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getDate(), formatter);

                predicates.add(criteriaBuilder.equal(root.<LocalDate>get("fechaCreacion"), date));
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getCities())) {
                Join<PaisEntity, IconoEntity> join = root.join("paises", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(filtersDTO.getCities()));
            }

            // Remove duplucates
            query.distinct(true);

            // Order resolver
            String orderByField = "denominacion";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }


}
