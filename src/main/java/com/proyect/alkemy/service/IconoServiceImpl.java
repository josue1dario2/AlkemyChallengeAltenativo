package com.proyect.alkemy.service;


import com.proyect.alkemy.dto.IconoDto;
import com.proyect.alkemy.dto.IconoFilterDto;
import com.proyect.alkemy.dto.PaisDto;
import com.proyect.alkemy.entity.ContinenteEntity;
import com.proyect.alkemy.entity.IconoEntity;
import com.proyect.alkemy.entity.PaisEntity;
import com.proyect.alkemy.exception.SpringException;
import com.proyect.alkemy.mapper.IconoMapper;
import com.proyect.alkemy.mapper.PaisMapper;
import com.proyect.alkemy.repository.ContinenteRepository;
import com.proyect.alkemy.repository.IconoRepository;
import com.proyect.alkemy.service.impl.IconoService;
import com.proyect.alkemy.service.impl.especificacion.IconoEspecificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class IconoServiceImpl implements IconoService<IconoDto,Long> {

    @Autowired
    private PaisServiceImpl paisService;

    @Autowired
    private IconoRepository iconoRepository;

    @Autowired
    private IconoMapper iconoMapper;

    @Autowired
    private PaisMapper paisMapper;

    @Autowired
    private IconoEspecificacion iconoEspecificacion;

    private static String ERROR_1 = "No hay íconos en la base de datos";
    private static String ERROR_2 = "El ícono no esta en la base de datos";
    private static String ERROR_3 = "La denominación ingresada no existe";
    private static String ERROR_4 = "La fecha introducida no existe";
    private static String ERROR_5 = "La pais_id introducido no existe";

    @Override
    @Transactional
    public List<IconoDto> findAll() throws SpringException {
        try{
            List<IconoEntity> iconos  = iconoRepository.findAll();
            if(iconos.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<IconoDto> iconosDtos = iconoMapper.iconEntityToDtoList(iconos,false);
            return iconosDtos;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
    @Transactional
    public List<IconoDto> getByFilters(String name, String date, Set<Long> cities, String order){
        try {

            IconoFilterDto filtersDTO = new IconoFilterDto(name, date, cities, order);
            List<IconoEntity> entities = iconoRepository.findAll(this.iconoEspecificacion.getByFilters(filtersDTO));
            List<IconoDto> dtos = iconoMapper.iconEntityToDtoList(entities, true);
            return dtos;

        }catch (SpringException e){
           throw new SpringException(e.getMessage());
       }
    }

    @Override
    public List<IconoDto> findAllIcon() throws SpringException {
        try{
            List<IconoEntity> iconos  = iconoRepository.findAll();
            List<IconoDto> iconosDtos = new ArrayList<>();

            if(iconos.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            for(IconoEntity icono : iconos){
                iconosDtos.add(iconoMapper.listaIcono(icono));
            }
            return iconosDtos;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<IconoDto> getDenominacion(String denominacion) throws SpringException {
        try{
            List<IconoDto> listaDto = new ArrayList<>();
            List<IconoEntity> lista = iconoRepository.findByDenominacionIgnoreCase(denominacion);
            if(lista.isEmpty()){
                throw new SpringException(ERROR_3);
            }
            for(IconoEntity entity : lista){
                listaDto.add(iconoMapper.convertToDto(entity,false));
            }
            return listaDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<IconoDto> getDate(String fecha) throws SpringException {
        try{
            List<IconoDto> listaDto = new ArrayList<>();
            List<IconoEntity> lista = iconoRepository.findByFechaDeCreacion(fecha);
            if(lista.isEmpty()){
                throw new SpringException(ERROR_4);
            }
            for(IconoEntity entity : lista){
                listaDto.add(iconoMapper.convertToDto(entity,false));
            }
            return listaDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<IconoDto> getCities(Long idCity) throws SpringException {
        try{
            List<IconoDto> listaDto = new ArrayList<>();
            List<IconoEntity> lista = iconoRepository.findByPaises(idCity);
            if(listaDto.isEmpty()){
                throw new SpringException(ERROR_5);
            }
            for(IconoEntity entity : lista){
                listaDto.add(iconoMapper.convertToDto(entity,false));
            }
            return listaDto;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public IconoDto findById(Long id) throws SpringException {
        try{
            Optional<IconoEntity> icono = iconoRepository.findById(id);
            if(!icono.isPresent()){
                throw new SpringException(ERROR_2);
            }
            return iconoMapper.convertToDto(icono.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public IconoDto save(IconoDto dto) throws SpringException {
        try{
            IconoEntity icono = iconoMapper.convertToEntity(dto);
            iconoRepository.save(icono);

            return iconoMapper.convertToDto(icono,false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public IconoDto update(Long id, IconoDto dto) throws SpringException {
        try{
            Optional<IconoEntity> icono = iconoRepository.findById(id);

            if(!icono.isPresent()){
                throw new SpringException(ERROR_2);
            }
            IconoEntity entity = iconoMapper.convertToEntity(dto);
            iconoRepository.save(entity);

            return iconoMapper.convertToDto(icono.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    /*
    @Override
    public IconoDto addPais(Long id, Long idPais) throws SpringException {
        try{
            Optional<IconoEntity> icono = iconoRepository.findById(id);
            if(!icono.isPresent()){
                throw new SpringException(ERROR_2);
            }
            IconoEntity iconoEntity = icono.get();
            iconoEntity.getPaises().size();
            PaisDto pais = paisService.findById(idPais);
            iconoEntity.addPais(paisMapper.convertToEntity(pais));
            iconoRepository.save(iconoEntity);

            return iconoMapper.convertToDto(iconoEntity);
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }*/


    /*
    @Override
    public void removePais(Long id, Long idPais) throws SpringException {
        try{
            Optional<IconoEntity> icono = iconoRepository.findById(id);
            if(!icono.isPresent()){
                throw new SpringException(ERROR_2);
            }
            IconoEntity iconoEntity = icono.get();//al ser tipo LAZY el size me ayuda a cargar los datos
            iconoEntity.getPaises().size();
            PaisDto pais = paisService.findById(idPais);
            iconoEntity.removePais(paisMapper.convertToEntity(pais));
            iconoRepository.save(iconoEntity);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }*/

    @Override
    @Transactional
    public void delete(Long id) throws SpringException {
        try{
            if(iconoRepository.existsById(id)){
                iconoRepository.deleteById(id);
            }else{
                throw new SpringException(ERROR_2);
            }
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
}
