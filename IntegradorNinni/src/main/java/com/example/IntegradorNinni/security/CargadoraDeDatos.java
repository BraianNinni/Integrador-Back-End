package com.example.IntegradorNinni.security;


import com.example.IntegradorNinni.domain.Usuario;
import com.example.IntegradorNinni.domain.UsuarioRol;
import com.example.IntegradorNinni.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public CargadoraDeDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passACifrar="123456789";
        String passCifrada=cifrador.encode(passACifrar);
        Usuario usuarioAInsertar= new Usuario("Rodolfo","Baspineiro",
                "rodo@gmail.com",passCifrada, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
        BCryptPasswordEncoder cifrador2= new BCryptPasswordEncoder();
        String passACifrar2="123456789";
        String passCifrada2=cifrador.encode(passACifrar);
        Usuario usuarioAInsertar2= new Usuario("Braian","Ninni",
                "bra@gmail.com",passCifrada, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);
    }
}
