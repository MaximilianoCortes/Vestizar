package com.example.vestizar.aplicacion.repositorio;

import com.example.vestizar.aplicacion.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de Producto
 */
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
    /**
     * Encuentra todas las publicaciones que estén o no aprobadas
     *
     * @param aprobado Se le introduce el valor 0 o 1 dependiendo si se quieren buscar publicaciones aprobadas o aún no aprobadas
     * @return La lista de estos productos de acuerdo a lo requerido
     */
    List<Producto> findAllByAprobado(int aprobado);

    /**
     * Encuentra todas las publicaciones que estén o no aprobadas y además por el tipo de producto que se requiera
     *
     * @param aprobado   Se le introduce el valor 0 o 1 dependiendo si se quieren buscar publicaciones aprobadas o aún no aprobadas
     * @param tipoProducto El tipo de producto que se quiere buscar en las publicaciones
     * @return La lista de estos productos de acuerdo a lo requerido
     */
    List<Producto> findAllByAprobadoAndAndTipoProducto(int aprobado, String tipoProducto);

    /**
     * Encuentra todas las publicaciones que estén o no aprobadas y además por el tipo de producto que se requiera y por la categoría
     *
     * @param aprobado      Se le introduce el valor 0 o 1 dependiendo si se quieren buscar publicaciones aprobadas o aún no aprobadas
     * @param categoria     El tipo de categoría del cual se quieran encontrar las publicaciones.
     * @param tipoProducto El tipo de producto que se quiere buscar en las publicaciones
     * @return La lista de estos productos de acuerdo a lo requerido
     */
    List<Producto> findAllByAprobadoAndCategoriaAndTipoProducto(int aprobado,String categoria, String tipoProducto);


}
