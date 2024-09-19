package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventarios")
public class Inventario {

   @EmbeddedId
    private InventarioPK inventarioPK;
    
    public Inventario(Bodega bodega, Producto producto){
        this.inventarioPK = new InventarioPK(bodega, producto);
    }
    public Inventario() {;}
    
}
