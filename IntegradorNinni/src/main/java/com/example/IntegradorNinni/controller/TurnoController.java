package com.example.IntegradorNinni.controller;


import com.example.IntegradorNinni.domain.Odontologo;
import com.example.IntegradorNinni.domain.Paciente;
import com.example.IntegradorNinni.domain.Turno;
import com.example.IntegradorNinni.dto.TurnoDTO;
import com.example.IntegradorNinni.exceptions.BadRequestException;
import com.example.IntegradorNinni.exceptions.ResourceNotFoundException;
import com.example.IntegradorNinni.service.OdontologoService;
import com.example.IntegradorNinni.service.PacienteService;
import com.example.IntegradorNinni.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;


    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;

    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarTurno(id);
        //conozco dos salidas 1: el turno 2 es nulo
        if (turnoBuscado.isPresent()){
            //encontrado
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            //no lo encontramos
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
       return ResponseEntity.ok("Se elimino el turno correctamente.");
    }
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodos());


    }
}
