package com.example.vestizar.repositorio;


import com.example.vestizar.entidad.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.annotation.Rollback;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UsuarioRepositorioTest {


    @Autowired
    private UsuarioRepositorio repo;
    Usuario usuario, usuario2, usuario3;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        usuario.setNombre("testNombre");
        usuario.setApellido("testApellido");
        usuario.setEmail("test@gmail.com");
        usuario.setContrasena("testPass");
        usuario.setCelular("123456");
        repo.save(usuario);

        usuario2 = new Usuario();
        usuario2.setNombreUsuario("testUser2");
        usuario2.setNombre("testNombre2");
        usuario2.setApellido("testApellido2");
        usuario2.setEmail("test2@gmail.com");
        usuario2.setContrasena("testPass2");
        usuario2.setCelular("1234562");
        repo.save(usuario2);

        usuario3 = new Usuario();
        usuario3.setNombreUsuario("testUser3");
        usuario3.setNombre("testNombre3");
        usuario3.setApellido("testApellido3");
        usuario3.setEmail("test3@gmail.com");
        usuario3.setContrasena("testPass3");
        usuario3.setCelular("1234563");
        repo.save(usuario3);
    }

    @DisplayName("crea usuario y corrobora que esta en la base de datos solo con el mail")
    @Test
    void findByEmail() {

        Usuario u1 = repo.findByEmail(usuario.getEmail());
        Usuario u2 = repo.findByEmail(usuario2.getEmail());
        Usuario u3 = repo.findByEmail(usuario3.getEmail());
        Assertions.assertThat(u1.equals(usuario));
        Assertions.assertThat(u2.equals(usuario2));
        Assertions.assertThat(u3.equals(usuario3));


    }
    @AfterEach
    void tearDown() {
        repo.deleteById(repo.findByEmail("test@gmail.com").getId());
        repo.deleteById(repo.findByEmail("test2@gmail.com").getId());
        repo.deleteById(repo.findByEmail("test3@gmail.com").getId());
        usuario = null;
        usuario2 = null;
        usuario3 = null;
    }


}