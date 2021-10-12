package com.proyect.alkemy.controller;

import com.proyect.alkemy.dto.IconoDto;
import com.proyect.alkemy.entity.ContinenteEntity;
import com.proyect.alkemy.entity.IconoEntity;
import com.proyect.alkemy.exception.SpringException;
import com.proyect.alkemy.service.IconoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/iconos")
public class IconoController {

    @Autowired
    private IconoServiceImpl iconoService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(OK).body(iconoService.findAll());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "/listaIcons")
    public ResponseEntity<?> getAllIcon(){
        try{
            return ResponseEntity.status(OK).body(iconoService.findAllIcon());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "/filtros")
    public ResponseEntity<?> getDetailsByFilters(
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String date,
            @RequestParam(required = false) Set<Long> cities,
            @RequestParam(required = false,defaultValue = "ASC")String order){

        List<IconoDto> iconos = iconoService.getByFilters(name,date,cities,order);
        return ResponseEntity.ok(iconos);
    }

    /*
    @GetMapping(path = "/denominacion")
    public ResponseEntity<?> getDenominacion(@RequestParam(value = "denominacion")String denominacion){
        try{
            return ResponseEntity.status(OK).body(iconoService.getDenominacion(denominacion));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "/date")
    public ResponseEntity<?> getDate(@RequestParam(value = "date")Date date){
        try{
            return ResponseEntity.status(OK).body(iconoService.getDate(date));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping(path = "/cities")
    public ResponseEntity<?> getCities(@RequestParam(value = "cities")Long cities){
        try{
            return ResponseEntity.status(OK).body(iconoService.getCities(cities));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }


     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(OK).body(iconoService.findById(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody IconoDto t){
        try{
            return ResponseEntity.status(OK).body(iconoService.save(t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody IconoDto t){
        try{
            return ResponseEntity.status(OK).body(iconoService.update(id,t));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            iconoService.delete(id);
            return ResponseEntity.status(NO_CONTENT).build();
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    // ******************************
    /*
    @PostMapping(path = "/{id}/pais/{idPais}")
    public ResponseEntity<?> addPais(@PathVariable Long id,@PathVariable Long idPais){
        try{
            return ResponseEntity.status(CREATED).body(iconoService.addPais(id,idPais));
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping(path = "/{id}/pais/{idPais}")
    public ResponseEntity<Void> removePais(@PathVariable Long id,@PathVariable Long idPais){
        try{
            iconoService.removePais(id,idPais);
            return ResponseEntity.status(NO_CONTENT).build();
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

     */

}
