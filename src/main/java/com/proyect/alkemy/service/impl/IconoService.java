package com.proyect.alkemy.service.impl;

import com.proyect.alkemy.entity.IconoEntity;
import com.proyect.alkemy.entity.PaisEntity;
import com.proyect.alkemy.exception.SpringException;

import java.util.Date;
import java.util.List;


public interface IconoService <T,ID> {

    public List<T> findAll()throws SpringException;

    public List<T> findAllIcon()throws SpringException;

    public List<T> getDenominacion(String denominacion)throws SpringException;

    public List<T> getDate(String fecha)throws SpringException;

    public List<T> getCities(Long idCity)throws SpringException;

    public T findById(ID id)throws SpringException;

    public T save(T entity)throws SpringException;

    public T update(ID id,T entity)throws SpringException;

    //T addPais(Long id,Long idPais)throws SpringException;

    //void removePais(Long id,Long idPais)throws SpringException;

    public void delete(ID id)throws SpringException;

}
