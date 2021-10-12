package com.proyect.alkemy.service;

import com.proyect.alkemy.dto.IconoDto;
import com.proyect.alkemy.dto.PaisDto;
import com.proyect.alkemy.entity.IconoEntity;
import com.proyect.alkemy.entity.PaisEntity;
import com.proyect.alkemy.exception.SpringException;
import com.proyect.alkemy.mapper.IconoMapper;
import com.proyect.alkemy.mapper.PaisMapper;
import com.proyect.alkemy.repository.IconoRepository;
import com.proyect.alkemy.repository.PaisRepository;
import com.proyect.alkemy.service.impl.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaisServiceImpl implements PaisService<PaisDto,Long> {


    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private IconoServiceImpl iconoService;

    @Autowired
    private PaisMapper paisMapper;

    @Autowired
    private IconoMapper iconoMapper;

    private static String ERROR_1 = "No hay paises en la base de datos";
    private static String ERROR_2 = "El pais no esta en la base de datos";
    private static String ERROR_3 = "La denominación ingresada no existe en la base de datos";
    private static String ERROR_4 = "El id ingresado no tiene paises vinculados";
    private static String ERROR_5 = "El orden ingresado no es válido";

    @Override
    @Transactional
    public List<PaisDto> findAll() throws SpringException {
        try{
            List<PaisEntity> paises  = paisRepository.findAll();
            if(paises.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<PaisDto> paisesDto = paisMapper.convertToDtoList(paises,true);
            return paisesDto;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<PaisDto> findCities() throws SpringException {
        try{
            List<PaisEntity> paises  = paisRepository.findAll();
            if(paises.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<PaisDto> paisesDto = new ArrayList<>();

            for(PaisEntity pais : paises){
                paisesDto.add(paisMapper.convertToDtoCities(pais));
            }

            return paisesDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PaisDto findById(Long id) throws SpringException {
        try{
            Optional<PaisEntity> entity = paisRepository.findById(id);

            if(!entity.isPresent()){
                throw new SpringException(ERROR_2);
            }
            return paisMapper.convertToDto(entity.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public PaisDto findByDenominacion(String denominacion) throws SpringException {
        try{
            PaisEntity pais = paisRepository.findByDenominacionIgnoreCase(denominacion);
            if(pais == null){
                throw new SpringException(ERROR_3);
            }
            return paisMapper.convertToDto(pais,false);
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<PaisDto> findByIdContinente(Long continenteId) throws SpringException {
        try{
            List<PaisEntity> paises  = paisRepository.findByIdContinente(continenteId);
            List<PaisDto> paisesDto = new ArrayList<>();

            if(paises.isEmpty()){
                throw new SpringException(ERROR_4);
            }
            for(PaisEntity pais : paises){
                paisesDto.add(paisMapper.convertToDtoCities(pais));
            }

            return paisesDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    /*@Override
    public List<PaisDto> findPaisOrderByDenominacion(String order) throws SpringException {
        try{
            List<PaisEntity> paises = new ArrayList<>();
            if(order.equalsIgnoreCase("asc")){
                paises = paisRepository.findAllOrderByDenominacionAsc();
                //paises.addAll(paisRepository.findAllOrderByDenominacionAsc());
            }else if(order.equalsIgnoreCase("desc")){
                paises = paisRepository.findAllOrderByDenominacionDesc();
                //paises.addAll(paisRepository.findAllOrderByDenominacionDesc());
            }else{
                throw new SpringException(ERROR_5);
            }
            List<PaisDto> paisesDto = new ArrayList<>();
            for(PaisEntity pais : paises){
                paisesDto.add(paisMapper.convertToDto(pais));
            }

            return paisesDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }


     */
    @Override
    @Transactional
    public PaisDto save(PaisDto paisDto) throws SpringException {
        try{
            PaisEntity entity = paisMapper.convertToEntity(paisDto);
            paisRepository.save(entity);

            return paisMapper.convertToDto(entity,false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PaisDto update(Long id, PaisDto paisDto) throws SpringException {
        try{
            Optional<PaisEntity> pais = paisRepository.findById(id);

            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            PaisEntity entity = paisMapper.convertToEntity(paisDto);
            paisRepository.save(entity);
            return paisMapper.convertToDto(pais.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws SpringException {
        try{
            if(paisRepository.existsById(id)){
                paisRepository.deleteById(id);
                return true;
            }else{
                throw new SpringException(ERROR_2);
            }
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
    @Override
    public void addIcono(Long id, Long idIcono) throws SpringException {
        try{
            Optional<PaisEntity> pais = paisRepository.findById(id);
            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            PaisEntity paisEntity = pais.get();
            paisEntity.getIconos().size();
            IconoDto iconoDto = iconoService.findById(idIcono);
            paisEntity.addIcono(iconoMapper.convertToEntity(iconoDto));
            paisRepository.save(paisEntity);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public void removeIcono(Long id, Long idIcono) throws SpringException {
        try{
            Optional<PaisEntity> pais = paisRepository.findById(id);
            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            PaisEntity paisEntity = pais.get();//al ser tipo LAZY el size me ayuda a cargar los datos
            paisEntity.getIconos().size();
            IconoDto iconoDto = iconoService.findById(idIcono);
            paisEntity.removePais(iconoMapper.convertToEntity(iconoDto));
            paisRepository.save(paisEntity);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
}
