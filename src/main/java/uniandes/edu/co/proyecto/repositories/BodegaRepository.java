package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Bodega;



public interface BodegaRepository extends MongoRepository<Bodega,Integer>{

    public interface respuestaDocumento{
        String getSucursal();
        String getBodega();
        Long getIdIngreso();
        String getFecha();
        String getNombreProvedor();
    } 

    //@Query(value ="SELECT * FROM BODEGAS", nativeQuery = true)
    @Query(value="{}")
    Collection<Bodega> darBodegas();

    //@Query(value = "SELECT * FROM BODEGAS WHERE ID = :idBodega", nativeQuery = true)
    @Query(value="{_id:?0}")
    Bodega darBodega(int idBodega);

    //@Modifying
    //@Transactional
    //@Query(value = "INSERT INTO BODEGAS (id,nombre,tamanio,idsucursal) VALUES (bodega_sequence.nextVal,:nombre,:tamanio,:idsucursal)", nativeQuery = true)
    //void insertarBodega(@Param("nombre") String nombre,@Param("tamanio") Integer tamanio,@Param("idsucursal") Integer idsucursal);
    default void insertarBodega(Bodega bodega){
        save(bodega);
    }

    //@Modifying
    //@Transactional
    //@Query(value = "UPDATE BODEGA SET nombre = :nombre WHERE idciudad = :idciudad", nativeQuery = true)
    @Query(value="{_id:?0}")
    @Update("{$set:{nombre:?1,tamanio:?2}}")
    void actualizarBodega(int id, String nombre, int tamanio);
    /*
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BODEGAS WHERE id = :id", nativeQuery = true)
    void eliminarBodega(@Param("id") long id);


    @Query(value = "SELECT PRODUCTOS.CODBARRAS as codigobarras, PRODUCTOS.NOMBRE as nombre,SUM(INVENTARIOS.CANTIDAD_OCUPADA) as cantidadOcupada, INVENTARIOS.MINIMO_RECOMPRA as minimoRecompra,INVENTARIOS.CAPACIDAD_MAX as capacidadMax, AVG(INVENTARIOS.COSTO_GRUPO_PRODUCTO) AS avgCosto \r\n" + //
        "FROM BODEGAS \r\n" + //
        "INNER JOIN INVENTARIOS ON BODEGAS.ID = INVENTARIOS.IDBODEGA  \r\n" + //
        "INNER JOIN PRODUCTOS ON PRODUCTOS.CODBARRAS = INVENTARIOS.CODIGOBARRAS \r\n" + //
        "WHERE BODEGAS.ID = :id AND BODEGAS.IDSUCURSAL = :idsucursal \r\n" + //
        "GROUP BY PRODUCTOS.CODBARRAS, PRODUCTOS.NOMBRE, INVENTARIOS.MINIMO_RECOMPRA, INVENTARIOS.CAPACIDAD_MAX", nativeQuery = true)
        Collection<ProductoInventarioDTO> consultarInfoBodegaSucursal(@Param("id") Long id, @Param("idsucursal") Long idsucursal);

    //RFC 6   
    @Query(value = "SELECT SUCURSALES.nombre as sucursal, BODEGAS.nombre as bodega, INGRESOPRODUCTO.idingreso as idIngreso, INGRESOPRODUCTO.fechaingreso as fecha, PROVEEDORES.nombre as nombreProvedor  \r\n" + //
        "FROM BODEGAS \r\n" + //
        "INNER JOIN SUCURSALES ON BODEGAS.IDSUCURSAL = SUCURSALES.IDSUCURSAL \r\n" + //
        "INNER JOIN INGRESOPRODUCTO ON BODEGAS.ID = INGRESOPRODUCTO.BODEGA \r\n" + //
        "INNER JOIN ORDENES ON INGRESOPRODUCTO.ORDENCOMPRA = ORDENES.ID \r\n" + //
        "INNER JOIN PROVEEDORES ON ORDENES.NIT_PROVEEDOR = PROVEEDORES.NIT \r\n" + //
        "WHERE INGRESOPRODUCTO.fechaingreso >= SYSDATE - 30 AND BODEGAS.ID = :id AND BODEGAS.IDSUCURSAL = :idsucursal", nativeQuery = true)
        Collection<respuestaDocumento> consultarInfoBodega(@Param("id") Long id, @Param("idsucursal") Long idsucursal);
    */
} 
