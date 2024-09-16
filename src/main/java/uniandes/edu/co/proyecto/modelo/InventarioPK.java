package uniandes.edu.co.proyecto.modelo;
import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
public class InventarioPK implements Serializable{
    @ManyToOne
    @JoinColumn(name="CODIGOBARRAS", referencedColumnName = "CODBARRAS")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "IDBODEGA", referencedColumnName = "ID")
    private Bodega bodega;
    
}
