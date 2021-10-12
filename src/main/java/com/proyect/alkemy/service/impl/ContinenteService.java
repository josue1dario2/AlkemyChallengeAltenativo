package com.proyect.alkemy.service.impl;

import com.proyect.alkemy.entity.ContinenteEntity;
import com.proyect.alkemy.exception.SpringException;

import java.util.List;


public interface ContinenteService <T,ID> {

    public List<T> findAll()throws SpringException;

    public T findById(ID id)throws SpringException;

    public T save(T entity)throws SpringException;

    public T update(ID id,T entity)throws SpringException;

    public boolean delete(ID id)throws SpringException;

}
