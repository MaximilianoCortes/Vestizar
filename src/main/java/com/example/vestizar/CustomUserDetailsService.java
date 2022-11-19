package com.example.vestizar;

import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.repositorio.UsuarioRepositorio;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UsuarioRepositorio userRepository;

    public CustomUserDetailsService(UsuarioRepositorio userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Usuario user = userRepository.findByEmail(usernameOrEmail);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getContrasena(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        }else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
