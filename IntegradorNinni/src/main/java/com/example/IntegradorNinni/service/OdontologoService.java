package com.example.IntegradorNinni.service;

import com.example.IntegradorNinni.domain.Odontologo;
import com.example.IntegradorNinni.exceptions.ResourceNotFoundException;
import com.example.IntegradorNinni.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;
    Logger logger = Logger.getLogger(getClass().getName());
    @Autowired

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Optional<Odontologo> buscarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoRepository.findById(id);
        if(odontologoBuscado.isPresent()){
            logger.info("se encontro el odontologo con id: "+id);
           return odontologoBuscado;
        }
        else{
            logger.warn("Error. No existe el odontologo con id: "+id);
            throw new ResourceNotFoundException("No se encontro el odontologo "+id);

        }
    }


    public List<Odontologo> buscarOdontologos() {
        List<Odontologo> odontologoList= odontologoRepository.findAll();
        logger.info("se listaron "+odontologoList.size()+""+" odontologos");

        return odontologoRepository.findAll();
    }

    public Odontologo altaOdontologo(Odontologo odontologo) {
        logger.info("se creo el odontologo: "+odontologo.getNombre()+", "+odontologo.getApellido()+" con matricula: "+odontologo.getMatricula());
        return odontologoRepository.save(odontologo);


    }
    public void borrarOdontologo (Long id)throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoRepository.findById(id);
        if(odontologoBuscado.isPresent()){
            logger.info("se elimino el odontologo con id: "+id);
            odontologoRepository.deleteById(id);
        }
        else{
            logger.info("Error. No se encontro el odontologo con id "+id);
            throw new ResourceNotFoundException("No se encontro el odontologo "+id);
        }
    }
}



