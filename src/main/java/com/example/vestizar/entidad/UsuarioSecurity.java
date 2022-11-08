package com.example.vestizar.entidad;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UsuarioSecurity implements UserDetails {

    private Usuario usuario;

    public UsuarioSecurity(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getUsername() {
        return usuario.getNombre();
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    /**
     * Las autorizaciones se atribuyen a partir de un mapeado de estos leyendo el string del atributo roles del usuario
     * el cual esta separado por ",". Se crean objetos de la clase SimpleGrantedAuthority por cada rol identificado y se convierte en un listado.
     * @return Retorna la lista de autorizaciones/permisos del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(usuario
                        .getRoles()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
