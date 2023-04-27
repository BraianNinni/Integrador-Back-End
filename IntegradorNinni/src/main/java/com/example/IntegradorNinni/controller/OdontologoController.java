package com.example.IntegradorNinni.controller;


import com.example.IntegradorNinni.domain.Odontologo;
import com.example.IntegradorNinni.exceptions.ResourceNotFoundException;
import com.example.IntegradorNinni.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }
    @PostMapping
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.altaOdontologo(odontologo));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id)throws ResourceNotFoundException {
        odontologoService.borrarOdontologo(id);
        return ResponseEntity.ok("Se elimino el odontologo "+id);
    }
}
