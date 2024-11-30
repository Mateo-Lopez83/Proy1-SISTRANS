package uniandes.edu.co.proyecto.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Producto;

public interface ProductoRepository extends MongoRepository<Producto,Integer>{ 
    
    default void insertarProveedor(Producto producto){
        save(producto);
    }

    @Query("{_id: ?0}")
    List<Producto> darProducto(int id);

    @Query("{nombre: ?0}")
    List<Producto> darProductoNom(String nombre);

    @Query("{_id: ?0}")
    @Update("{$set: {nombre: ?1, precioVenta: ?2, presentacion: ?3, unidadMedida: ?4, espEmpacado: ?5, fechaExp: ?6, categoria: ?7} }")
    void actualizarProducto(int id, String nombre, int precioVenta, String presentacion, String unidadMedida, String espEmpacado, LocalDate fechaExp, int categoria);


    // Del RF1
    @Query("{PRECIOVENTA: {$gte: ?0, $lte: ?1}}")
    List<Producto> encontraProductosPorPrecio(int min, int max);

    @Query("{CATEGORIA: ?0}")
    List<Producto> encontrarProductosPorCategoria(int cat);

    @Query("{FECHA_EXP: {$gt: ?0}}")
    List<Producto> encontrarProductosPorFechaMAYOR(LocalDate fecha);

    @Query("{FECHA_EXP: {$lt: ?0}}")
    List<Producto> encontrarProductosPorFechaMENOR(LocalDate fecha);

    @Query("{CODBARRAS: { $in: ?0 }}")
    List<Producto> findProductosByCodigosBarras(List<Integer> codigosBarras);


}


/* 
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Categoria;

public interface ProductoRepository extends JpaRepository<Producto,Integer>{

    @Query(value = "SELECT * FROM PRODUCTOS", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM PRODUCTOS WHERE CODBARRAS = :codBarras", nativeQuery = true)
    Producto darProducto(@Param("codBarras") long codBarras);

    @Query(value = "SELECT * FROM PRODUCTOS WHERE NOMBRE = :nombre", nativeQuery = true)
    Producto darProductoNom(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PRODUCTOS (CODBARRAS, NOMBRE, PRECIOVENTA, PRESENTACION, UNIDAD_MEDIDA, ESP_EMPACADO, FECHA_EXP, CATEGORIA) VALUES (codbarras_sequence.nextVal, :nombre, :precioVenta, :presentacion, :unidadMedida, :espEmpacado, CASE WHEN :fechaExp IS NULL THEN NULL ELSE TO_DATE(:fechaExp, 'YYYY-MM-DD') END, :categoria)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, 
                          @Param("precioVenta") int precioVenta, 
                          @Param("presentacion") String presentacion, 
                          @Param("unidadMedida") String unidadMedida, 
                          @Param("espEmpacado") String espEmpacado, 
                          @Param("fechaExp") String fechaExp, 
                          @Param("categoria") String categoria); 

    @Modifying
    @Transactional
    @Query(value = "UPDATE PRODUCTOS SET nombre = :nombre, precioVenta = :precioVenta, presentacion = :presentacion, unidadMedida = :unidadMedida, espEmpacado = :espEmpacado, fechaExp = :fechaExp, categoria = :categoria WHERE id = :idProducto", nativeQuery = true)
    void actualizarProducto(@Param("idProducto") long idProducto, 
                            @Param("nombre") String nombre, 
                            @Param("precioVenta") int precioVenta, 
                            @Param("presentacion") String presentacion, 
                            @Param("unidadMedida") String unidadMedida, 
                            @Param("espEmpacado") String espEmpacado, 
                            @Param("fechaExp") String fechaExp, 
                            @Param("categoria") Categoria categoria);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PRODUCTOS WHERE CODBARRAS = :idProducto", nativeQuery = true)
    void eliminarProducto(@Param("idProducto") long idProducto);



    @Query(value = "SELECT p.* " +
               "FROM PRODUCTOS p " +
               "INNER JOIN INVENTARIOS i ON i.CODIGOBARRAS = p.CODBARRAS " +
               "INNER JOIN BODEGAS b ON b.ID = i.IDBODEGA " +
               "INNER JOIN SUCURSALES s ON s.IDSUCURSAL = b.IDSUCURSAL " +
               "WHERE s.IDSUCURSAL = :idsucursal", nativeQuery = true)
    Collection<Producto> encontrarProductosPorSucursal(@Param("idsucursal") Integer idsucursal);

    @Query(value = "SELECT p.* " +
               "FROM PRODUCTOS p " +
               "WHERE p.PRECIOVENTA BETWEEN :min AND :max", nativeQuery = true)
    Collection<Producto> encontraProductosPorPrecio(@Param("min") Integer min, @Param("max") Integer max);

    @Query(value = "SELECT p.* " +
                "FROM PRODUCTOS p " +
                "WHERE p.CATEGORIA = :cat", nativeQuery = true)
    Collection<Producto> encontrarProductosPorCategoria(@Param("cat") Integer cat);

    @Query(value = "SELECT p.* " +
               "FROM PRODUCTOS p " +
               "WHERE p.FECHA_EXP > TO_DATE(:fecha, 'YYYY-MM-DD')", nativeQuery = true)
    Collection<Producto> encontrarProductosPorFechaMAYOR(@Param("fecha") String fecha);

    @Query(value = "SELECT p.* " +
               "FROM PRODUCTOS p " +
               "WHERE p.FECHA_EXP < TO_DATE(:fecha, 'YYYY-MM-DD')", nativeQuery = true)
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
 */