package com.proyect.alkemy.controller;

import com.proyect.alkemy.dto.ContinenteDto;
import com.proyect.alkemy.entity.ContinenteEntity;
import com.proyect.alkemy.exception.SpringException;
import com.proyect.alkemy.service.ContinenteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/continentes")
public class ContinenteController {

    @Autowired
    private ContinenteServiceImpl continenteService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(OK).body(continenteService.findAll());
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(OK).body(continenteService.findById(id));
        }catch (SpringException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());

        }

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ContinenteDto t){
        try{
            return ResponseEntity.status(CREATED).body(continenteService.save(t));
        }catch (SpringException e){
          return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody ContinenteDto t){
        try{
            return ResponseEntity.status(OK).body(continenteService.update(id,t));
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(NO_CONTENT).body(continenteService.delete(id));
        }catch (SpringException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }


}
