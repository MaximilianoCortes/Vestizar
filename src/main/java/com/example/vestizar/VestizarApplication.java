package com.example.vestizar;

import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.repositorio.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class VestizarApplication {

    public static void main(String[] args) {
        SpringApplication.run(VestizarApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepositorio usuarios, PasswordEncoder encoder) {
        return args -> {

            usuarios.save(new Usuario("user", encoder.encode("password"), "ROLE_USER"));
            usuarios.save(new Usuario("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"));

        };
    }
     */

}
