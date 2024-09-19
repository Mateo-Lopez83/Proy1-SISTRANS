package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orden_producto")
public class Orden_Producto {

    @EmbeddedId
    private Orden_ProductoPK orden_ProductoPK;
    
    public Orden_Producto(Orden orden, Producto producto){
        this.orden_ProductoPK = new Orden_ProductoPK(orden, producto);
    }
    public Orden_Producto() {;}
}
