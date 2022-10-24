package com.example.vestizar.aplicacion.servicio.repositorio;

import com.example.vestizar.aplicacion.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}
