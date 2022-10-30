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

    public void crearNuevoUsuario(Usuario u) {
        repositorio.save(u);
    }

    public String cifrarContrasena(String clave) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(clave);
    }



}
