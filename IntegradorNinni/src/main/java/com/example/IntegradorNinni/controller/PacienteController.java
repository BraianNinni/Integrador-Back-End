package com.example.IntegradorNinni.controller;


import com.example.IntegradorNinni.domain.Odontologo;
import com.example.IntegradorNinni.domain.Paciente;
import com.example.IntegradorNinni.exceptions.ResourceNotFoundException;
import com.example.IntegradorNinni.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }
//    @GetMapping("/{email}")
//    public ResponseEntity<Paciente> buscarXEmail(@PathVariable String email){
//        Optional<Paciente> pacienteBuscado = pacienteService.buscarXEmail(email);
//        if(pacienteBuscado.isPresent()) {
//            return ResponseEntity.ok(pacienteBuscado.get());
//        } else{
//            return ResponseEntity.notFound().build();
//        }
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPacienteXID(@PathVariable Long id) throws ResourceNotFoundException{
        pacienteService.eliminar(id);
        return ResponseEntity.ok("Eliminacion del paciente con id= "+id+ "con exito");
    }
}
