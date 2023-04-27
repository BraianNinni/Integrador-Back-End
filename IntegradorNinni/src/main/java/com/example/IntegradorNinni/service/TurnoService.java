package com.example.IntegradorNinni.service;

import com.example.IntegradorNinni.domain.Odontologo;
import com.example.IntegradorNinni.domain.Paciente;
import com.example.IntegradorNinni.domain.Turno;
import com.example.IntegradorNinni.dto.TurnoDTO;
import com.example.IntegradorNinni.exceptions.BadRequestException;
import com.example.IntegradorNinni.exceptions.ResourceNotFoundException;
import com.example.IntegradorNinni.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired

    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }
    public TurnoDTO guardar (TurnoDTO turno) throws BadRequestException, ResourceNotFoundException {
        //trabajo
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologo(turno.getOdontologo_id());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
           return  convertirTurnoaTurnoDTO(turnoRepository.save(convertirTurnoDTOaTurno(turno)));
        }
        else{
            throw new BadRequestException("Error. No se pudo crear el turno");
        }
    }
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            turnoRepository.deleteById(id);

        }
        else {
            throw new ResourceNotFoundException("Error. No se pudo eliminar el turno");

        }
    }


    public Optional<TurnoDTO> buscarTurno(Long id) {
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
        return Optional.of(convertirTurnoaTurnoDTO(turnoRepository.findById(id).get()));
    }
        else {
            return Optional.empty();
        }
    }

    public List<TurnoDTO> buscarTodos(){
        List<Turno> turnoList = turnoRepository.findAll();
        List <TurnoDTO> turnoDTOList = new ArrayList<>();
        for (Turno turno : turnoList) {
           turnoDTOList.add(convertirTurnoaTurnoDTO(turno));

        }

        return turnoDTOList;
    }
    private Turno convertirTurnoDTOaTurno (TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Paciente paciente=new Paciente();
        Odontologo odontologo= new Odontologo();
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombre_paciente());
        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());
        //vincular objetos
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        //turno listo
        return turno;
    }
    private TurnoDTO convertirTurnoaTurnoDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setOdontologo_id((turno.getOdontologo().getId()));
        turnoDTO.setPaciente_id(turno.getPaciente().getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());
        return turnoDTO;

    }
}
