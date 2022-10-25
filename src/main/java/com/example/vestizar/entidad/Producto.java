package com.example.vestizar.entidad;

import javax.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoroducto;

    @Column(name = "aprobado",nullable = false)
    private int aprobado;

    @Column(name = "tipo_producto",nullable = false)
    private String tipoProducto;

    @Column(name = "estilo",nullable = false)
    private String estilo;

    @Column(name = "imagen1")
    private String imagen1;

    @Column(name = "imagen2")
    private String imagen2;

    @Column(name = "imagen3")
    private String imagen3;

    @Column(name = "imagen4")
    private String imagen4;

    @Column(name = "estado",nullable = false)
    private String estado;

    @Column(name = "talla",nullable = false)
    private String talla;

    @Column(name = "descripción")
    private String descripcion;

    @Column(name = "color",nullable = false)
    private String color;

    @Column(name = "marca",nullable = false)
    private String marca;

    @Column(name = "precio",nullable = false)
    private double precio ;

    @ManyToOne(targetEntity = Usuario.class,fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "FK_vendedor")
    private Usuario vendedor;

    public Producto(){
    }

    public Producto(Long idPoroducto, int aprobado, String tipoProducto, String imagen1, String imagen2, String imagen3, String imagen4, String estado, String talla, String descripcion, String color, String marca, double precio, Usuario vendedor) {
        this.idPoroducto = idPoroducto;
        this.aprobado = aprobado;
        this.tipoProducto = tipoProducto;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.estado = estado;
        this.talla = talla;
        this.descripcion = descripcion;
        this.color = color;
        this.marca = marca;
        this.precio = precio;
        this.vendedor = vendedor;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Long getIdPoroducto() {
        return idPoroducto;
    }

    public void setIdPoroducto(Long idPoroducto) {
        this.idPoroducto = idPoroducto;
    }

    public int getAprobado() {
        return aprobado;
    }

    public void setAprobado(int aprobado) {
        this.aprobado = aprobado;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen4() {
        return imagen4;
    }

    public void setImagen4(String imagen4) {
        this.imagen4 = imagen4;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
}
