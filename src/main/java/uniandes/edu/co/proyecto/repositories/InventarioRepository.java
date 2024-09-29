package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.DTOs.ProductoInventarioDTO;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.InventarioPK;

public interface InventarioRepository extends JpaRepository<Inventario,InventarioPK>{ 

    public interface InnerInventarioRepository {
        
        
    }
    /*
    @Query(value = "SELECT INVENTARIOS.CODIGOBARRAS, INVENTARIOS.IDBODEGA, " +
    "SUM(INVENTARIOS.CANTIDAD_OCUPADA) AS CANTIDAD_TOTAL, INVENTARIOS.MINIMO_RECOMPRA, " +
    "INVENTARIOS.CAPACIDAD_MAX, AVG(INVENTARIOS.COSTO_GRUPO_PRODUCTO) AS PROMEDIO " +
    "FROM INVENTARIOS " +
    "INNER JOIN BODEGAS ON BODEGAS.ID = INVENTARIOS.IDBODEGA " +
    "WHERE BODEGAS.ID = :id AND BODEGAS.IDSUCURSAL = :idsucursal " +
    "GROUP BY INVENTARIOS.CODIGOBARRAS, INVENTARIOS.IDBODEGA, INVENTARIOS.MINIMO_RECOMPRA, INVENTARIOS.CAPACIDAD_MAX",nativeQuery = true)
    Collection<ProductoInventarioDTO> darInformacionInventarios(@Param("id") Integer id, @Param("idsucursal") Integer idsucursal);
    */
} 