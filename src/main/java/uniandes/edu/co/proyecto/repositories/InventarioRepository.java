package uniandes.edu.co.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

/*
    @Query(value = "SELECT INVENTARIOS.*, RECEPCION_PRODUCTO.PROVEEDOR " +
                   "FROM INVENTARIOS " +
                   "INNER JOIN RECEPCION_PRODUCTO  ON INVENTARIOS.IDBODEGA = RECEPCION_PRODUCTO.BODEGA " +
                   "WHERE INVENTARIOS.CANTIDAD_OCUPADA < INVENTARIOS.MINIMO_RECOMPRA " +
                   "AND INVENTARIOS.CODIGOBARRAS = RECEPCION_PRODUCTO.PRODUCTO", nativeQuery = true)
    List<Inventario> findOcupacionInventario();*/
    
    @Query(value = "SELECT i " +
                   "FROM Inventario i " +
                   "WHERE i.cantidadOcupada < i.minimoRecompra")
    List<Inventario> findOcupacionInventario();



} 