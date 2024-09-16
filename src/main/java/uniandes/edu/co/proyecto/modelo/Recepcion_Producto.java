package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RECEPCION_PRODUCTO")

public class Recepcion_Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private int Identificador;
    private Producto producto;
    private int cantidad;
    private Bodega bodega;

    public Recepcion_Producto(Producto producto, int cantidad, Bodega bodega){
        this.producto = producto;
        this.cantidad = cantidad;
        this.bodega= bodega;
    }
    public Recepcion_Producto(){;}

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    

}
