package com.example.vestizar.servicio;

import com.example.vestizar.entidad.Producto;
import com.example.vestizar.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class ProductoServicio {


    @Autowired
    ProductoRepositorio repositorio;


    public void crearNuevoProducto(MultipartFile file, String tipoProducto, String estilo,String estado,String talla,String descripcion, String color,String marca,double precio){

        Producto p=new Producto();

        if(!file.isEmpty()){
            Path directorioImagenes= Paths.get("src//main//resources//static/img");
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


        p.setTipoProducto(tipoProducto);
        p.setEstilo(estilo);


        p.setEstado(estado);
        p.setTalla(talla);
        p.setDescripcion(descripcion);
        p.setColor(color);
        p.setMarca(marca);
        p.setPrecio(precio);

        repositorio.save(p);

    }



    public List<Producto> listar(){
        return repositorio.findAll();
    }


    public Producto obtenerProductoPorId(Long id){
        return repositorio.findById(id).get();
    }




}
