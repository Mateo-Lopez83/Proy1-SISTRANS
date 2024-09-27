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
    @Query(value = "INSERT INTO ORDENES (id, fechaEntrega, fechaCreacion, Estado, sucursalEnvio, Proveedor) VALUES (orden_sequence.nextVal, :fechaEntrega, :fechaCreacion, :Estado, :sucursalEnvio, :Proveedor)", nativeQuery = true)
    void insertarOrden(@Param("fechaEntrega") String fechaEntrega, 
                        @Param("fechaCreacion") String fechaCreacion,
                        @Param("Estado") String Estado, 
                        @Param("sucursalEnvio") int sucursalEnvio, 
                        @Param("Proveedor") int Proveedor);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDENES SET Estado = :Estado, WHERE id = :idOrden", nativeQuery = true)
    void actualizarOrden(@Param("Estado") String Estado);
}
