package com.example.vestizar.aplicacion.servicio;

import com.example.vestizar.aplicacion.entidad.Producto;
import com.example.vestizar.aplicacion.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Clase servicio de Producto
 */
@Service
public class ProductoServicio {

    @Autowired
    ProductoRepositorio repositorio;


    /**
     * Permite crear y guardar un producto una vez obtenido todos los datos que se solicitan para poder crearlo.
     *
     * @param file           Es la imagen que producto.
     * @param tipoProducto   Es el tipo de producto que tendrá el nuevo producto.
     * @param categoria      Es la categoría en la que entrará el nuevo producto.
     * @param estado         Es el estado en el que se encuentra el producto.
     * @param talla          Es la talla del producto.
     * @param descripcion    Es la descripción del producto.
     * @param marca          Es la marca del producto.
     * @param precio         Es el precio del producto.
     */
    public void crearNuevoProducto(MultipartFile file, String tipoProducto,String categoria,String estado,String talla,String descripcion, String marca,double precio, Long idVendedor){

        Producto p=new Producto();

        if(!file.isEmpty()){
            Path directorioImagenes= Paths.get("src//main//resources//static/imgPagina");
            String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg=file.getBytes();
                Path rutaCompleta=Paths.get(rutaAbsoluta+"//"+file.getOriginalFilename());
                Files.write(rutaCompleta,bytesImg);

                p.setImagen1(file.getOriginalFilename());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        p.setAprobado(0);
        p.setTipoProducto(tipoProducto);
        p.setCategoria(categoria);

        p.setEstado(estado);
        p.setTalla(talla);
        p.setDescripcion(descripcion);
        p.setMarca(marca);
        p.setPrecio(precio);
        p.setVendedor(idVendedor);


        repositorio.save(p);
    }


    /**
     * Obtiene un producto específico de acuerdo a la id asociada
     *
     * @param id Id del producto que se quiere encontrar
     * @return el producto que corrresponde con la id ingresada
     */
    public Producto obtenerProductoPorId(Long id){
        return repositorio.findById(id).get();
    }

    /**
     * Permite que se guarde y modifique una publicacion cambiando el estado de aprobado de 0 a 1 y lo guarda.
     *
     * @param producto se entrega el producto que se quiere aprobar.
     */
    public void aprobar(Producto producto){
        repositorio.save(producto);
    }

    /**
     * encuentra todas las publicaciones de acuerdo a si se requieren las aprobadas o las no aprobadas
     *
     * @param aprobado se ingresa 1 o 0 de acuerdo a si se necesitan encontrar productos aprobados o no aprobados
     * @return La lista de estos productos de acuerdo a lo requerido
     */
    public List<Producto> obtenerProductoPorAprobado(int aprobado){
        return repositorio.findAllByAprobado(aprobado);
    }

    /**
     * encuentra todas las publicaciones de acuerdo a si se requieren las aprobadas o las no aprobadas y de un tipo de producto específico
     *
     * @param aprobado se ingresa 1 o 0 de acuerdo a si se necesitan encontrar productos aprobados o no aprobados
     * @param tipo     El tipo de producto que se quiere buscar en las publicaciones
     * @return La lista de estos productos de acuerdo a lo requerido
     */
    public List<Producto> obteneProductoPorTipoProducto(int aprobado,String tipo){

        return repositorio.findAllByAprobadoAndAndTipoProducto(aprobado,tipo);

    }

    /**
     * encuentra todas las publicaciones de acuerdo a si se requieren las aprobadas o las no aprobadas y de un tipo de producto y categoría específica
     *
     * @param aprobado  se ingresa 1 o 0 de acuerdo a si se necesitan encontrar productos aprobados o no aprobados
     * @param categoria El tipo de categoría del cual se quieran encontrar las publicaciones.
     * @param tipo      El tipo de producto que se quiere buscar en las publicaciones
     * @return La lista de estos productos de acuerdo a lo requerido
     */
    public List<Producto> obteneProductoPorCategoriaYTipoProducto(int aprobado,String categoria, String tipo){

        return repositorio.findAllByAprobadoAndCategoriaAndTipoProducto(aprobado,categoria,tipo);

    }

    /**
     * Elimina un producto de acuerdo al id
     *
     * @param id id del producto que se quiera eliminar
     */
    public void eliminarProducto(Long id){
        repositorio.deleteById(id);
    }



}
