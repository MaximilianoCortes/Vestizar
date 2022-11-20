package com.example.vestizar.entidad;

import com.example.vestizar.Enums.tipoCiudad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "celular", nullable = false,unique = true)
    private String celular;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "idUsuario") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    private List<Role> roles = new ArrayList<>();

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "ciudad")
    private  tipoCiudad ciudad;



    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String apellido, String correo, String contrasena, String celular, List<Role> roles, String fotoPerfil, String nombreUsuario, tipoCiudad ciudad) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = correo;
        this.contrasena = contrasena;
        this.celular = celular;
        this.roles = roles;
        this.fotoPerfil = fotoPerfil;
        this.nombreUsuario = nombreUsuario;
        this.ciudad = ciudad;
    }

    public Usuario(Long idUsuario,String nombre, String apellido, String email, String contrasena, String celular) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", celular='" + celular + '\'' +
                ", roles=" + roles +
                ", fotoPerfil='" + fotoPerfil + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", ciudad=" + ciudad +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String correo) {
        this.email = correo;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
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
