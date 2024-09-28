package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.DTOs.ProductoInventarioDTO;
import uniandes.edu.co.proyecto.modelo.Bodega;



public interface BodegaRepository extends JpaRepository<Bodega,Integer>{
    @Query(value ="SELECT * FROM BODEGAS", nativeQuery = true)
    Collection<Bodega> darBodegas();

    @Query(value = "SELECT * FROM BODEGAS WHERE ID = :idBodega", nativeQuery = true)
    Bodega darBodega(@Param("id") long idBodega);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO BODEGAS (id,nombre,tamanio,idsucursal) VALUES (bodega_sequence.nextVal,:nombre,:tamanio,:idsucursal)", nativeQuery = true)
    void insertarBodega(@Param("nombre") String nombre,@Param("tamanio") Integer tamanio,@Param("idsucursal") Integer idsucursal);


    @Modifying
    @Transactional
    @Query(value = "UPDATE BODEGA SET nombre = :nombre WHERE idciudad = :idciudad", nativeQuery = true)
    void actualizarBodega(@Param("idciudad") long idciudad, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM BODEGAS WHERE id = :id", nativeQuery = true)
    void eliminarBodega(@Param("id") long id);


    @Query(value = "SELECT PRODUCTOS.CODBARRAS, PRODUCTOS.NOMBRE,SUM(INVENTARIOS.CANTIDAD_OCUPADA), INVENTARIOS.MINIMO_RECOMPRA,INVENTARIOS.CAPACIDAD_MAX, AVG(INVENTARIOS.COSTO_GRUPO_PRODUCTO) AS AVG_COSTO \r\n" + //
        "FROM BODEGAS \r\n" + //
        "INNER JOIN INVENTARIOS ON BODEGAS.ID = INVENTARIOS.IDBODEGA  \r\n" + //
        "INNER JOIN PRODUCTOS ON PRODUCTOS.CODBARRAS = INVENTARIOS.CODIGOBARRAS \r\n" + //
        "WHERE BODEGAS.ID = :id AND BODEGAS.IDSUCURSAL = :idsucursal \r\n" + //
        "GROUP BY PRODUCTOS.CODBARRAS, PRODUCTOS.NOMBRE, INVENTARIOS.MINIMO_RECOMPRA, INVENTARIOS.CAPACIDAD_MAX", nativeQuery = true)
        Collection<ProductoInventarioDTO> consultarInfoBodegaSucursal(@Param("id") Long id, @Param("idsucursal") Long idsucursal);

    
    
} 
