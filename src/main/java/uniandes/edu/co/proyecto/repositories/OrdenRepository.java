package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.modelo.Producto;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {

    @Query(value = "SELECT * FROM ORDENES", nativeQuery = true)
    Collection<Orden> darOrdenes();

    @Query(value = "SELECT * FROM ORDENES WHERE ID = :idOrden", nativeQuery = true)
    Orden darOrden(@Param("idOrden") long idOrden);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDENES (ID, FECHA_ENTREGA, FECHA_CREACION, ESTADO, SUCURSAL_ENVIO, NIT_PROVEEDOR) VALUES (orden_sequence.nextVal, TO_DATE(:fechaEntrega, 'YYYY-MM-DD'), CURRENT_DATE, :Estado, :sucursalEnvio, :Proveedor)", nativeQuery = true)
    void insertarOrden(@Param("fechaEntrega") String fechaEntrega, 
                       @Param("Estado") String Estado, 
                       @Param("sucursalEnvio") int sucursalEnvio, 
                       @Param("Proveedor") int Proveedor);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM ORDENES WHERE FECHA_CREACION=CURRENT_DATE AND FECHA_ENTREGA= :fechaEntrega AND ESTADO= :Estado AND SUCURSAL_ENVIO= :sucursalEnvio AND NIT_PROVEEDOR= :Proveedor", nativeQuery = true)
    Orden buscarOrden(@Param("fechaEntrega") String fechaEntrega, 
                      @Param("Estado") String Estado, 
                      @Param("sucursalEnvio") int sucursalEnvio, 
                      @Param("Proveedor") int Proveedor);



    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDENES (ID, FECHA_ENTREGA, FECHA_CREACION, ESTADO, SUCURSAL_ENVIO, NIT_PROVEEDOR) VALUES (orden_sequence.nextVal, TO_DATE(:fechaEntrega, 'YYYY-MM-DD'), CURRENT_DATE, :Estado, :sucursalEnvio, :Proveedor) RETURNING ID INTO p_id", nativeQuery = true)
    int insertarOrdenYDevolver(@Param("fechaEntrega") String fechaEntrega, 
                                @Param("Estado") String Estado, 
                                @Param("sucursalEnvio") int sucursalEnvio, 
                                @Param("Proveedor") int Proveedor);

    // Buscar id de la orden con id más alto
    /*@Modifying
    @Transactional
    @Query(value = "SELECT ORDENES.ID FROM ORDENES WHERE ID = (SELECT MIN(ID) FROM ORDENES)", nativeQuery = true)
    int buscarUltimaOrden();*/

    
    @Query(value = "SELECT MAX(ID) FROM ORDENES", nativeQuery = true)
    int buscarUltimaOrden();
                  


    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDENES SET ESTADO = :Estado WHERE ID = :idOrden", nativeQuery = true)
    void actualizarOrden(@Param("Estado") String Estado, @Param("idOrden") long idOrden);
}
