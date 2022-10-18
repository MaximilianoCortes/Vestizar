package com.example.vestizar.entidad;

import javax.persistence.*;

@Entity
@Table(name = "conversaciones")
public class Conversacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConversacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprador")
    private Usuario comprador;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Vendedor")
    private Usuario vendedor;

    public Conversacion() {
    }

    public Conversacion(Long idConversacion, Producto producto, Usuario comprador, Usuario vendedor) {
        this.idConversacion = idConversacion;
        this.producto = producto;
        this.comprador = comprador;
        this.vendedor = vendedor;
    }

    public Long getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Long idConversacion) {
        this.idConversacion = idConversacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
}
