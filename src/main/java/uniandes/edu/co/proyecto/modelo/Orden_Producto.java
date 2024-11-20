package uniandes.edu.co.proyecto.modelo;

/* 
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orden_producto")
public class Orden_Producto {

    @EmbeddedId
    private Orden_ProductoPK orden_ProductoPK;

    @Column(name = "CANTIDAD_PRODUCTO")
    private int cantidadProducto;

    @Column(name = "PRECIOBODEGA")
    private double precioBodega;
    
    public Orden_Producto(Orden orden, Producto producto){
        this.orden_ProductoPK = new Orden_ProductoPK(orden, producto);
    }
    
    public Orden_Producto() {}

    public Orden_ProductoPK getOrden_ProductoPK() {
        return orden_ProductoPK;
    }

    public void setOrden_ProductoPK(Orden_ProductoPK orden_ProductoPK) {
        this.orden_ProductoPK = orden_ProductoPK;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getPrecioBodega() {
        return precioBodega;
    }

    public void setPrecioBodega(double precioBodega) {
        this.precioBodega = precioBodega;
    }

    @Override
    public String toString() {
        return "Orden_Producto{" +
                "orden_ProductoPK=" + orden_ProductoPK +
                ", cantidadProducto=" + cantidadProducto +
                ", precioBodega=" + precioBodega +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orden_Producto that = (Orden_Producto) o;

        return orden_ProductoPK != null ? orden_ProductoPK.equals(that.orden_ProductoPK) : that.orden_ProductoPK == null;
    }

    @Override
    public int hashCode() {
        return orden_ProductoPK != null ? orden_ProductoPK.hashCode() : 0;
    }
}*/