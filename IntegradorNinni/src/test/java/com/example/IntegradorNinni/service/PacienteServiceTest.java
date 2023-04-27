package com.example.IntegradorNinni.service;

import com.example.IntegradorNinni.domain.Domicilio;
import com.example.IntegradorNinni.domain.Paciente;
import com.example.IntegradorNinni.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Domicilio domicilioAGuardar= new Domicilio("Calle A", "1912","Buenos Aires", "Buenos Aires");
        Paciente pacienteAGuardar= new Paciente("Ninni","Braian","1278936",
                LocalDate.of(2023,03,29),domicilioAGuardar,"bra@gmail.com");
        Paciente pacienteGuardado =pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L,pacienteGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPacienteTest() {
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get().getNombre());
        System.out.println(pacienteBuscado.get().getNombre());
    }
    @Test
    @Order(3)
    public void buscarTodosLosPacientesTest() {
        List<Paciente> pacientesABuscar = pacienteService.buscarTodosPacientes();
        assertEquals(1, pacientesABuscar.size());

    }
    @Test
    @Order(4)
    public void actualizarPaciente() {

        Paciente pacienteAActualizar = new Paciente(1L, "Ninni", "Braian", "1278936");
        Paciente pacienteActualizado = pacienteService.actualizarPaciente(pacienteAActualizar);
        pacienteActualizado.setNombre("JULIO");
        pacienteActualizado.setApellido("LUNA");
        assertEquals("LUNA", pacienteActualizado.getApellido());
    }
    @Test
    @Order(5)
    public void eliminarPaciente() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        Paciente pacienteAEliminar = new Paciente(1L, "Ninni", "Braian", "1278936");
        Optional<Paciente> pacienteEliminado = pacienteService.eliminar(idAEliminar);
        assertNull(pacienteEliminado);


    }
}
