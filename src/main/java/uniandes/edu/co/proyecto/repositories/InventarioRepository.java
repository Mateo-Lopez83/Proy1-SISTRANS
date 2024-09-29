package uniandes.edu.co.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.InventarioPK;

public interface InventarioRepository extends JpaRepository<Inventario,InventarioPK>{ 

    
    
    @Query(value = "SELECT INVENTARIOS.CODIGOBARRAS,PRODUCTOS.NOMBRE, INVENTARIOS.IDBODEGA, " +
    "SUM(INVENTARIOS.CANTIDAD_OCUPADA) AS CANTIDAD_TOTAL, INVENTARIOS.MINIMO_RECOMPRA, " +
    "INVENTARIOS.CAPACIDAD_MAX, AVG(INVENTARIOS.COSTO_GRUPO_PRODUCTO) AS PROMEDIO " +
    "FROM INVENTARIOS " +
    "INNER JOIN BODEGAS ON BODEGAS.ID = INVENTARIOS.IDBODEGA " +
    "INNER JOIN PRODUCTOS ON PRODUCTOS.CODBARRAS = INVENTARIOS.CODIGOBARRAS " +
    "WHERE BODEGAS.ID = :id AND BODEGAS.IDSUCURSAL = :idsucursal " +
    "GROUP BY PRODUCTOS.NOMBRE,INVENTARIOS.CODIGOBARRAS, INVENTARIOS.IDBODEGA, INVENTARIOS.MINIMO_RECOMPRA, INVENTARIOS.CAPACIDAD_MAX",nativeQuery = true)
    Object[][] darInformacionInventarios(@Param("id") Integer id, @Param("idsucursal") Integer idsucursal);
    
} 