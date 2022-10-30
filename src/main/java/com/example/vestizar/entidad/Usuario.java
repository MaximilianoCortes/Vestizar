package com.example.vestizar.entidad;

import com.example.vestizar.Enums.tipoCiudad;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "correo", nullable = false,unique = true)
    private String correo;

    @Column(name = "contraseña", nullable = false)
    private String contrasena;

    @Column(name = "celular", nullable = false,unique = true)
    private String celular;

    @Column(name = "roles")
    private String roles;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "ciudad")
    private  tipoCiudad ciudad;



    public Usuario() {
    }

    public Usuario(String nombreUsuario, String contrasena, String roles) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.roles = roles;
    }

    public Usuario(Long idUsuario, String nombre, String apellido, String correo, String contrasena, String celular, String roles, String fotoPerfil, String nombreUsuario, tipoCiudad ciudad) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.celular = celular;
        this.roles = roles;
        this.fotoPerfil = fotoPerfil;
        this.nombreUsuario = nombreUsuario;
        this.ciudad=ciudad;

    }

    public tipoCiudad  getCiudad() {
        return ciudad;
    }

    public void setCiudad( tipoCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public Long getId() {
        return idUsuario;
    }

    public void setId(Long id) {
        this.idUsuario  = id;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String rol) {
        this.roles = roles;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
