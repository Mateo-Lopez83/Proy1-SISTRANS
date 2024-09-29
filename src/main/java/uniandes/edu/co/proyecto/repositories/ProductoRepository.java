package uniandes.edu.co.proyecto.repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.modelo.Producto;
public interface ProductoRepository extends JpaRepository<Producto,Integer>{

    @Query(value = "SELECT p.* " +
               "FROM PRODUCTOS" +
               "INNER JOIN INVENTARIOS i ON PRODUCTOS.CODBARRAS = i.CODIGOBARRAS " +
               "INNER JOIN BODEGAS b ON b.ID = i.IDBODEGA " +
               "INNER JOIN SUCURSALES s ON s.IDSUCURSAL = b.IDSUCURSAL " +
               "WHERE s.IDSUCURSAL = :idsucursal", nativeQuery = true)
    Collection<Producto> encontrarProductosPorSucursal(@Param("idsucursal") Integer idsucursal);

    @Query(value = "SELECT p.* " +
               "FROM PRODUCTOS p " +
               "WHERE PRODUCTOS.PRECIOVENTA BETWEEN :min AND :max", nativeQuery = true)
    Collection<Producto> encontraProductosPorPrecio(@Param("min") Integer min,@Param("max") Integer max);

    @Query(value = "SELECT p.* " +
                "FROM PRODUCTOS p " +
                "WHERE PRODUCTOS.CATEGORIA = :cat", nativeQuery = true)
    Collection<Producto> encontrarProductosPorCategoria(@Param("cat") Integer cat);

    @Query(value = "SELECT p.* " +
               "WHERE PRODUCTOS.FECHA_EXP > TO_DATE(:fecha, 'DD-MM-YYYY')", nativeQuery = true)
    Collection<Producto> encontrarProductosPorFechaMAYOR(@Param("fecha") String fecha);

    @Query(value = "SELECT p.* " +
               "WHERE PRODUCTOS.FECHA_EXP < TO_DATE(:fecha, 'DD-MM-YYYY')", nativeQuery = true)
    Collection<Producto> encontrarProductosPorFechaMENOR(@Param("fecha") String fecha);

    @Query(value = "SELECT p.* " +
               "FROM PRODUCTOS p " +
               "INNER JOIN INVENTARIOS i ON p.CODBARRAS = i.CODIGOBARRAS " +
               "INNER JOIN BODEGAS b ON b.ID = i.IDBODEGA " +
               "INNER JOIN SUCURSALES s ON s.IDSUCURSAL = b.IDSUCURSAL " +
               "WHERE (s.IDSUCURSAL = :idsucursal) AND (PRODUCTOS.PRECIOVENTA BETWEEN :min AND :max) AND (PRODUCTOS.CATEGORIA = :cat) AND (RODUCTOS.FECHA_EXP > TO_DATE(:fecha,'dd-mm-yyyy'))", nativeQuery = true)
    Collection<Producto> encontrarProductosPorTODO(@Param("idsucursal") Integer idsucursal,@Param("min") Integer min,@Param("max") Integer max,@Param("cat") Integer cat,@Param("fecha") String fecha);

    @Query(value = "SELECT * FROM PRODUCTOS" , nativeQuery = true)
    Collection<Producto> encontrarProductos();

    

    
}