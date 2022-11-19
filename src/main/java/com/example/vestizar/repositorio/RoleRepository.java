package com.example.vestizar.repositorio;


import com.example.vestizar.entidad.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
