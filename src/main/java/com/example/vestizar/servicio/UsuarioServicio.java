package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio repositorio;

    public List<Usuario> listar() {
        return (List<Usuario>) repositorio.findAll();
    }

    public Optional<Usuario> listarId(Long id) {
        return repositorio.findById(id);
    }

    /**
     * Se guardan los datos del usuario creado en el front end en el repositorio correspondiente para registrarlo en la base de datos.
     * @param u Usuario creado en el front end
     */
    public void crearNuevoUsuario(Usuario u) {
        repositorio.save(u);
    }

    /**
     * La contrasena se cifra a partir de la clase importada BCryptPasswordEncoder.
     * @param contrasena Contrasena a cifrar.
     * @return Contrasena cifrada
     */
    public String cifrarContrasena(String contrasena) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(contrasena);
    }



}
