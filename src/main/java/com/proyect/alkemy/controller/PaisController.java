package com.proyect.alkemy.controller;

import com.proyect.alkemy.dto.PaisDto;
import com.proyect.alkemy.entity.PaisEntity;
import com.proyect.alkemy.exception.SpringException;
import com.proyect.alkemy.service.PaisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/paises")
public class PaisController {

    @Autowired
    private PaisServiceImpl paisService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(OK).body(paisService.findAll());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "cities")
    public ResponseEntity<?> getAllCities(){
        try{
            return ResponseEntity.status(OK).body(paisService.findCities());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    /*
    @GetMapping(path = "/denominacion")
    public ResponseEntity<?> getDenominacion(@RequestParam(value = "denominacion")String denominacion){
        try{
            return ResponseEntity.status(OK).body(paisService.findByDenominacion(denominacion));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }
    @GetMapping(path = "/contineteId")
    public ResponseEntity<?> getIdContinete(@RequestParam(value = "continenteId")Long continenteId){
        try{
            return ResponseEntity.status(OK).body(paisService.findByIdContinente(continenteId));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }
    @GetMapping(path = "/order")
    public ResponseEntity<?> getDenominacioAscDesc(@RequestParam(value = "order")String order){
        try{
            return ResponseEntity.status(OK).body(paisService.findPaisOrderByDenominacion(order));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }


     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(OK).body(paisService.findById(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PaisDto t){
        try{
            return ResponseEntity.status(OK).body(paisService.save(t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody PaisDto t){
        try{
            return ResponseEntity.status(OK).body(paisService.update(id,t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(NO_CONTENT).body(paisService.delete(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    //************************* **************************** **************************
    @PostMapping(path = "/{id}/icono/{idIcono}")
    public ResponseEntity<Void> addIcon(@PathVariable Long id,@PathVariable Long idIcono){
        try{
            paisService.addIcono(id,idIcono);
            return ResponseEntity.status(CREATED).build();
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }
    @DeleteMapping(path = "/{id}/icono/{idIcono}")
    public ResponseEntity<Void> removeIcono(@PathVariable Long id,@PathVariable Long idIcono){
        try{
            paisService.removeIcono(id,idIcono);
            return ResponseEntity.status(NO_CONTENT).build();
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }
}
