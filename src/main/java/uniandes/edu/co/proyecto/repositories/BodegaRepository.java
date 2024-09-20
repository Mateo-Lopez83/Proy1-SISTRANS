package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bodega;


public interface BodegaRepository extends JpaRepository<Bodega,Integer>{
    @Query(value ="SELECT * FROM BODEGAS", nativeQuery = true)
    Collection<Bodega> darBodegas();

    @Query(value = "SELECT * FROM BODEGAS WHERE ID = :idBodega", nativeQuery = true)
    Bodega darCiudad(@Param("id") long idBodega);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO BODEGAS (id,nombre,tamanio,idsucursal) VALUES (bodega_sequence.nextVal,:nombre,:tamanio,:idsucursal)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);


    @Modifying
    @Transactional
    @Query(value = "UPDATE BODEGA SET nombre = :nombre WHERE idciudad = :idciudad", nativeQuery = true)
    void actualizarBodega(@Param("idciudad") long idciudad, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CIUDADES WHERE idciudad = :idciudad", nativeQuery = true)
    void eliminarCiudad(@Param("idciudad") long idciudad);
    
    
} 
