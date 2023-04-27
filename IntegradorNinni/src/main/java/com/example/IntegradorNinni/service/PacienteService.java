package com.example.IntegradorNinni.service;


import com.example.IntegradorNinni.domain.Paciente;
import com.example.IntegradorNinni.exceptions.ResourceNotFoundException;
import com.example.IntegradorNinni.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    @Autowired

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> buscarTodosPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarXEmail(String email) {
        return pacienteRepository.findByemail(email);
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        if (pacienteRepository.findById(paciente.getId()).isPresent()){
         pacienteRepository.save(paciente);
        }
    return paciente;
    }

    public Optional<Paciente> buscarPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> eliminar (Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteRepository.findById(id);
        if (pacienteBuscado.isPresent()) {
            pacienteRepository.deleteById(id);
        }
        else{
            throw  new ResourceNotFoundException("Error. No existe el paciente con id= "+id);
        }
        return null;
    }
}
