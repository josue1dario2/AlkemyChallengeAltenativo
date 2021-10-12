package com.proyect.alkemy.service;

import com.proyect.alkemy.dto.ContinenteDto;
import com.proyect.alkemy.entity.ContinenteEntity;
import com.proyect.alkemy.exception.SpringException;
import com.proyect.alkemy.mapper.ContinenteMapper;
import com.proyect.alkemy.repository.ContinenteRepository;
import com.proyect.alkemy.service.impl.ContinenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ContinenteServiceImpl implements ContinenteService<ContinenteDto,Long> {

    private ContinenteRepository continenteRepository;
    private ContinenteMapper continenteMapper;

    @Autowired
    public ContinenteServiceImpl(ContinenteRepository continenteRepository,ContinenteMapper continenteMapper){
        this.continenteRepository = continenteRepository;
        this.continenteMapper = continenteMapper;
    }

    private static String ERROR_1 = "No hay continentes en la base de datos";
    private static String ERROR_2 = "El continente no esta en la base de datos";

    @Override
    @Transactional
    public List<ContinenteDto> findAll() throws SpringException {
        try{
            List<ContinenteEntity> continentes  = continenteRepository.findAll();
            List<ContinenteDto> continenteDtos = new ArrayList<>();

            if(continentes.isEmpty()){
                throw new SpringException(ERROR_1);
            }

            for(ContinenteEntity contiente : continentes){
                continenteDtos.add(continenteMapper.convertToDto(contiente));
            }
            return continenteDtos;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ContinenteDto findById(Long id) throws SpringException {
        try{
            Optional<ContinenteEntity> continente = continenteRepository.findById(id);

            if(!continente.isPresent()){
                throw new SpringException(ERROR_2);
            }
            return continenteMapper.convertToDto(continente.get());

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ContinenteDto save(ContinenteDto dto) throws SpringException {
        try{
            ContinenteEntity continente = continenteMapper.convertToEntity(dto);
            continenteRepository.save(continente);
            return continenteMapper.convertToDto(continente);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ContinenteDto update(Long id, ContinenteDto dto) throws SpringException {
        try{
            Optional<ContinenteEntity> continente = continenteRepository.findById(id);

            if(!continente.isPresent()){
                throw new SpringException(ERROR_2);
            }
            ContinenteEntity entity = continenteMapper.convertToEntity(dto);
            continenteRepository.save(entity);

            return continenteMapper.convertToDto(continente.get());

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws SpringException {
        try{
            if(continenteRepository.existsById(id)){
                continenteRepository.deleteById(id);
                return true;
            }else{
                throw new SpringException(ERROR_2);
            }

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
}
