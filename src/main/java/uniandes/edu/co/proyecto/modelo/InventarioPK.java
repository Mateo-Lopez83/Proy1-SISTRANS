package uniandes.edu.co.proyecto.modelo;
import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
public class InventarioPK implements Serializable{
    @ManyToOne
    @JoinColumn(name="CODIGOBARRAS", referencedColumnName = "codbarras")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "IDBODEGA", referencedColumnName = "id")
    private Bodega bodega;

    public InventarioPK(Bodega bodega, Producto producto){
        super();
        this.bodega = bodega;
        this.producto = producto;
    }

    public Producto getProducto() {
        return producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    
    
}
