package uniandes.edu.co.proyecto.services;
/*
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.repositories.IngresoRepository;
import uniandes.edu.co.proyecto.repositories.InventarioRepository;
import uniandes.edu.co.proyecto.repositories.OrdenRepository;

@Service
public class IngresoService {
    @Autowired
    private IngresoRepository ingresoRepository;
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private InventarioRepository inventarioRepository;


    //RFC10
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void ingresarIngreso(long idBodega, long idOrden ) throws Exception {
        try{
            //Operación 1: Registrar el ingreso en la tabla INGRESOPRODUCTO
            ingresoRepository.insertarIngreso(idBodega, idOrden);
            List<Object[]> productosAfectados = ordenRepository.obtenerinfoRF10Ingresos(idOrden);
            if (productosAfectados != null && !productosAfectados.isEmpty()) {
             
                for (Object[] row : productosAfectados) {
                    long codbarras = ((BigDecimal) row[0]).longValue();
                    long cantidad = ((BigDecimal) row[1]).longValue();
                    long costo = ((BigDecimal) row[2]).longValue();
                    //Operación 2: Actualizar la información de los inventarios con los productos ingresados
                    inventarioRepository.ActualizarInventarios(codbarras,idBodega, costo, cantidad);
              
                }
            }
            else{
                System.out.println("hay algo re mal");
            }
            //Operación 3: Actualizar el estado de la Orden por "ENTREGADO"
            ordenRepository.entregarOrden(idOrden);

        }
        catch (Exception e) {
            throw e; // Manejo de excepciones.
        }
    }
    
}
 */