package com.proyect.alkemy.service.impl;

import com.proyect.alkemy.entity.PaisEntity;
import com.proyect.alkemy.exception.SpringException;

import java.util.List;


public interface PaisService <T,ID> {

    public List<T> findAll()throws SpringException;

    public List<T> findCities()throws SpringException;

    public T findById(ID id)throws SpringException;

    public T findByDenominacion(String denominacion)throws SpringException;

    public List<T> findByIdContinente(Long continenteId)throws SpringException;

    //public List<T> findPaisOrderByDenominacion(String order)throws SpringException;

    public T save(T entity)throws SpringException;

    public T update(ID id,T entity)throws SpringException;

    public boolean delete(ID id)throws SpringException;

    void addIcono(Long id,Long idIcono)throws SpringException;

    void removeIcono(Long id,Long idIcono)throws SpringException;

}
