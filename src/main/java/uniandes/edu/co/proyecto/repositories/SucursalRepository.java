package uniandes.edu.co.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Sucursal;

import java.util.Collection;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{

    @Query(value = "SELECT * FROM SUCURSALES", nativeQuery = true)
    Collection<Sucursal>darSucursales();

    @Query(value = "SELECT * FROM SUCURSALES WHERE idsucursal = :idsucursal", nativeQuery = true)
    Sucursal darSucursal(@Param("idsucursal") long idsucursal);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SUCURSALES (nombre, idsucursal, direccion, telefono, ciudad_asociada) VALUES (:nombre, sucursal_sequence.nextVal, :direccion, :telefono, :ciudad_asociada)", nativeQuery = true)
    void insertarSucursal(@Param("nombre") String nombre, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("ciudad_asociada")Integer ciudad_asociada);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SUCURSALES SET nombre = :nombre WHERE idsucursal = :idsucursal", nativeQuery = true)
    void actualizarSucursal(@Param("idsucursal") long idsucursal, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SUCURSALES WHERE idsucursal = :idsucursal", nativeQuery = true)
    void eliminarSucursal(@Param("idsucursal") long idsucursal);

    @Query(value = "SELECT DISTINCT SUCURSALES.* " +
               "FROM SUCURSALES " +
               "INNER JOIN BODEGAS ON BODEGAS.IDSUCURSAL = SUCURSALES.IDSUCURSAL " +
               "INNER JOIN INVENTARIOS ON INVENTARIOS.IDBODEGA = BODEGAS.ID " +
               "INNER JOIN PRODUCTOS ON INVENTARIOS.CODIGOBARRAS = PRODUCTOS.CODBARRAS " +
               "WHERE (PRODUCTOS.CODBARRAS = :CodBarras OR PRODUCTOS.NOMBRE = :nombre) " +
               "AND INVENTARIOS.CANTIDAD_OCUPADA > 0",
       nativeQuery = true)
    Collection<Sucursal> darSucursalesConProducto(@Param("CodBarras") int CodBarras, @Param("nombre") String nombre);


    @Query(value = "SELECT DISTINCT SUCURSALES.* " +
    "FROM SUCURSALES " +
    "INNER JOIN BODEGAS ON BODEGAS.IDSUCURSAL = SUCURSALES.IDSUCURSAL " +
    "INNER JOIN INVENTARIOS ON INVENTARIOS.IDBODEGA = BODEGAS.ID " +
    "INNER JOIN PRODUCTOS ON INVENTARIOS.CODIGOBARRAS = PRODUCTOS.CODBARRAS " +
    "WHERE PRODUCTOS.CODBARRAS = :CodBarras " +
    "AND INVENTARIOS.CANTIDAD_OCUPADA > 0",
    nativeQuery = true)
    Collection<Sucursal> darSucursalesConProductoIdentificador(@Param("CodBarras") int CodBarras);


    @Query(value = "SELECT DISTINCT SUCURSALES.* " +
    "FROM SUCURSALES " +
    "INNER JOIN BODEGAS ON BODEGAS.IDSUCURSAL = SUCURSALES.IDSUCURSAL " +
    "INNER JOIN INVENTARIOS ON INVENTARIOS.IDBODEGA = BODEGAS.ID " +
    "INNER JOIN PRODUCTOS ON INVENTARIOS.CODIGOBARRAS = PRODUCTOS.CODBARRAS " +
    "WHERE PRODUCTOS.NOMBRE = :nombre " +
    "AND INVENTARIOS.CANTIDAD_OCUPADA > 0",
    nativeQuery = true)
    Collection<Sucursal> darSucursalesConProductoNombre(@Param("nombre") String nombre);

    
}
