package uniandes.edu.co.proyecto.modelo;
/* 
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "INVENTARIOS")
public class Inventario {

   @EmbeddedId
    private InventarioPK inventarioPK;

    @Column(name = "COSTO_GRUPO_PRODUCTO")
    private int costoGrupoProducto;

    @Column(name = "CANTIDAD_OCUPADA")
    private int cantidadOcupada;

    @Column(name = "MINIMO_RECOMPRA")
    private int minimoRecompra;

    @Column(name = "CAPACIDAD_MAX")
    private int capacidadMax;

    
    public Inventario(Bodega bodega, Producto producto){
        this.inventarioPK = new InventarioPK(bodega, producto);
    }
    public Inventario() {;}

    public InventarioPK getInventarioPK() {
        return inventarioPK;
    }

    public void setInventarioPK(InventarioPK inventarioPK) {
        this.inventarioPK = inventarioPK;
    }

    public int getCostoGrupoProducto() {
        return costoGrupoProducto;
    }

    public void setCostoGrupoProducto(int costoGrupoProducto) {
        this.costoGrupoProducto = costoGrupoProducto;
    }

    public int getCantidadOcupada() {
        return cantidadOcupada;
    }

    public void setCantidadOcupada(int cantidadOcupada) {
        this.cantidadOcupada = cantidadOcupada;
    }

    public int getMinimoRecompra() {
        return minimoRecompra;
    }

    public void setMinimoRecompra(int minimoRecompra) {
        this.minimoRecompra = minimoRecompra;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }
    
}
*/