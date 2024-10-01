package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Orden_Producto;
import uniandes.edu.co.proyecto.modelo.Orden_ProductoPK;

@Repository
public interface Orden_ProductoRepository extends JpaRepository<Orden_Producto, Orden_ProductoPK> {

    @Query(value = "SELECT * FROM ORDEN_PRODUCTO", nativeQuery = true)
    Collection<Orden_Producto> darOrdenesProducto();

    @Query(value = "SELECT * FROM ORDEN_PRODUCTO WHERE IDORDEN = :idOrden", nativeQuery = true)
    Orden_Producto darProductosDeUnaOrden(@Param("idOrden") long idOrden);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDEN_PRODUCTO (IDORDEN, IDPRODUCTO, CANTIDAD_PRODUCTO, PRECIOBODEGA) VALUES (:idOrden, :idProducto, :cantidad, :precioBodega)", nativeQuery = true)
    void insertarProductoOrden(@Param("idOrden") int idOrden, @Param("idProducto") Integer idProducto, @Param("cantidad") int cantidad, @Param("precioBodega") int precioBodega);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDENES SET ESTADO = :Estado WHERE ID = :idOrden", nativeQuery = true)
    void actualizarOrden(@Param("Estado") String Estado, @Param("idOrden") long idOrden);
}
