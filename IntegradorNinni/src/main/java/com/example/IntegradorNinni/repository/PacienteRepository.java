package com.example.IntegradorNinni.repository;

import com.example.IntegradorNinni.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByemail(String email);
}
