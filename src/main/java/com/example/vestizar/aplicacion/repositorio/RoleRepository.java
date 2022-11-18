package com.example.vestizar.aplicacion.repositorio;


import com.example.vestizar.aplicacion.entidad.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
