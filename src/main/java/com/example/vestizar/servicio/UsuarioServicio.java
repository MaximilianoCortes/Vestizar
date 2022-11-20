package com.example.vestizar.servicio;

import com.example.vestizar.Dto.UserDto;
import com.example.vestizar.entidad.Role;
import com.example.vestizar.entidad.Usuario;
import com.example.vestizar.repositorio.RoleRepository;
import com.example.vestizar.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Usuario servicio.
 */
@Service
public class UsuarioServicio {

    /**
     * The Repositorio.
     */
    @Autowired
    UsuarioRepositorio repositorio;

    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public UsuarioServicio(UsuarioRepositorio repositorio, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.repositorio = repositorio;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    /**
     * Crear nuevo usuario.
     *
     * @param userDto the usuario
     */
    public void crearNuevoUsuario(UserDto userDto){
        Usuario user = new Usuario();
        user.setNombre(userDto.getNombre());
        user.setApellido(userDto.getApellido());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setContrasena(encoder.encode(userDto.getContrasena()));
        user.setCelular(userDto.getCelular());
        user.setNombreUsuario(userDto.getNombreUsuario());
        user.setCiudad(userDto.getCiudad());

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        repositorio.save(user);
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public Usuario findUserByEmail(String email) {
        return repositorio.findByEmail(email);
    }

    public List<UserDto> findAllUsers() {
        List<Usuario> users = repositorio.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(Usuario user){
        UserDto userDto = new UserDto();
        userDto.setNombre(user.getNombre());
        userDto.setApellido(user.getApellido());
        userDto.setEmail(user.getEmail());
        return userDto;
    }



    public Usuario obtenerUsuarioPorId(Long id){
        return repositorio.findById(id).get();

    }






}
