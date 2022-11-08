package com.example.vestizar.servicio;

import com.example.vestizar.entidad.UsuarioSecurity;
import com.example.vestizar.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * A partir del metodo findByNombre del repositorio de la entidad usuario se busca por nombre entregado por parametro, se construye un mapeado del usuario encontrado
     * y se "transforma" en un objeto de clase UsuarioSecurity para compatibilizar los detalles de dicho usuario con las configuraciones de Spring Security.
     * Tiene un manejo de errores por si no se encuentra el usuario.
     * @param nombre Nombre del usuario a buscar
     * @return El usuario buscado por parametro a partir de una busqueda en el repositorio de usuario.
     * @throws UsernameNotFoundException Arroja un error cuando no se encuentra un usuario con el nombre enviado por parametro dentro de la base de datos
     */
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        return usuarioRepositorio
                .findByNombre(nombre)
                .map(UsuarioSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nombre));
    }
}