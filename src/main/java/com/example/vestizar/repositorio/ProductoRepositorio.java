package com.example.vestizar.repositorio;

import com.example.vestizar.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
    List<Producto> findAllByAprobado(int aprobado);

    List<Producto> findAllByAprobadoAndAndTipoProducto(int aprobado, String tipoProducto);

    List<Producto> findAllByAprobadoAndCategoriaAndTipoProducto(int aprobado,String categoria, String tipoProducto);


}
