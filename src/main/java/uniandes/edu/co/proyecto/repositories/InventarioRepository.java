package uniandes.edu.co.proyecto.repositories;
/* 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.InventarioPK;

public interface InventarioRepository extends JpaRepository<Inventario,InventarioPK>{ 

    
    
    @Query(value = "SELECT INVENTARIOS.CODIGOBARRAS,PRODUCTOS.NOMBRE, INVENTARIOS.IDBODEGA, " +
    "SUM(INVENTARIOS.CANTIDAD_OCUPADA) AS CANTIDAD_TOTAL, INVENTARIOS.MINIMO_RECOMPRA, " +
    "INVENTARIOS.CAPACIDAD_MAX, AVG(INVENTARIOS.COSTO_GRUPO_PRODUCTO) AS PROMEDIO " +
    "FROM INVENTARIOS  " +
    "INNER JOIN BODEGAS ON BODEGAS.ID = INVENTARIOS.IDBODEGA " +
    "INNER JOIN PRODUCTOS ON PRODUCTOS.CODBARRAS = INVENTARIOS.CODIGOBARRAS " +
    "WHERE BODEGAS.ID = :id AND BODEGAS.IDSUCURSAL = :idsucursal " +
    "GROUP BY PRODUCTOS.NOMBRE,INVENTARIOS.CODIGOBARRAS, INVENTARIOS.IDBODEGA, INVENTARIOS.MINIMO_RECOMPRA, INVENTARIOS.CAPACIDAD_MAX",nativeQuery = true)
    Object[][] darInformacionInventarios(@Param("id") Integer id, @Param("idsucursal") Integer idsucursal);
    

    @Query(value = "SELECT PRODUCTOS.codbarras, PRODUCTOS.nombre, " +
               "ROUND(SUM(INVENTARIOS.cantidad_Ocupada) / NULLIF(INVENTARIOS.capacidad_Max, 0), 3) as ocupacion, " +
               "INVENTARIOS.idBodega " +
               "FROM INVENTARIOS " +
               "INNER JOIN BODEGAS ON BODEGAS.id = INVENTARIOS.idBodega " +
               "INNER JOIN PRODUCTOS ON PRODUCTOS.codbarras = INVENTARIOS.codigobarras " +
               "WHERE PRODUCTOS.codbarras IN :codbarrasList " +
               "AND INVENTARIOS.capacidad_Max != 0 " +
               "GROUP BY PRODUCTOS.codbarras, PRODUCTOS.nombre, INVENTARIOS.idBodega, INVENTARIOS.capacidad_Max", 
       nativeQuery = true)
    List<Object[]> findOcupacionByCodbarras(@Param("codbarrasList") List<Long> codbarrasList);


    @Query(value = "SELECT INVENTARIOS.CODIGOBARRAS, INVENTARIOS.IDBODEGA, INVENTARIOS.CANTIDAD_OCUPADA, " +
                   "PRODUCTOS.NOMBRE, BODEGAS.IDSUCURSAL, RECEPCION_PRODUCTO.PROVEEDOR " +
                   "FROM INVENTARIOS " +
                   "INNER JOIN RECEPCION_PRODUCTO ON INVENTARIOS.IDBODEGA = RECEPCION_PRODUCTO.BODEGA " +
                   "INNER JOIN PRODUCTOS ON PRODUCTOS.CODBARRAS = INVENTARIOS.CODIGOBARRAS " +
                   "INNER JOIN BODEGAS ON BODEGAS.ID = INVENTARIOS.IDBODEGA " +
                   "WHERE INVENTARIOS.CANTIDAD_OCUPADA < INVENTARIOS.MINIMO_RECOMPRA " +
                   "AND INVENTARIOS.CODIGOBARRAS = RECEPCION_PRODUCTO.PRODUCTO", nativeQuery = true)
    List<Object[]> findOcupacionInventarioConProveedor();
    
    @Query(value = "SELECT i " +
                   "FROM Inventario i " +
                   "WHERE i.cantidadOcupada < i.minimoRecompra")
    List<Inventario> findOcupacionInventario();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO INVENTARIOS (CODIGOBARRAS, IDBODEGA, COSTO_GRUPO_PRODUCTO, CANTIDAD_OCUPADA, MINIMO_RECOMPRA, CAPACIDAD_MAX) " +
               "SELECT :codbarras, :idbodega, :costogrupo, :cantidad, i.MINIMO_RECOMPRA, i.capacidad_max " +
               "FROM INVENTARIOS i WHERE i.CODIGOBARRAS = :codbarras AND i.IDBODEGA = :idbodega",nativeQuery = true)
    void ActualizarInventarios (@Param("codbarras") long codbarras,@Param("idbodega")long idbodega,@Param("costogrupo")long costogrupo,@Param("cantidad")long cantidad );



} */