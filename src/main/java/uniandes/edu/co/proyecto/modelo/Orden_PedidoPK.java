package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

@Embeddable
public class Orden_PedidoPK implements Serializable {
    @JoinColumn(name = "idOrden", referencedColumnName = "Identificador" )
    private Orden idOrden;
    @JoinColumn(name = "codigoProducto", referencedColumnName = "CodigoBarras")
    private Producto idProducto;

   
}
