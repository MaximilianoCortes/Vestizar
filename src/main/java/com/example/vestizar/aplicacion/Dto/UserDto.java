package com.example.vestizar.aplicacion.Dto;




import com.example.vestizar.aplicacion.Enums.tipoCiudad;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDto {
    private Long id;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    private String celular;

    private tipoCiudad ciudad;
    @NotEmpty(message = "El correo no deberia estar vacio")
    @Email
    private String email;
    @NotEmpty(message = "La contraseña no deberia estar vacia")
    private String contrasena;
    @NotEmpty(message = "el usuario no deberia estar vacio")
    private String nombreUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public UserDto() {
    }

    public UserDto(Long id, String nombre, String apellido, String celular, tipoCiudad ciudad, String correo, String contraseña, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.ciudad = ciudad;
        this.email = correo;
        this.contrasena = contraseña;
        this.nombreUsuario = usuario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public tipoCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(tipoCiudad ciudad) {
        this.ciudad = ciudad;
    }
}
