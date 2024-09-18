package uniandes.edu.co.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import uniandes.edu.co.proyecto.modelo.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer>{

    @Query(value = "SELECT * FROM CIUDAD", nativeQuery = true)
    Collection<Ciudad>darCiudades();

    @Query(value = "SELECT * FROM CIUDAD WHERE ID_CIUDAD = :idCiudad", nativeQuery = true)
    Ciudad darCiudad(@Param("idCiudad") long idCiudad);

    //Query revisado
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CIUDAD (NOMBRE, ID_CIUDAD) VALUES (:nombre, ciudad_sequence.nextVal)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);


    @Modifying
    @Transactional
    @Query(value = "UPDATE CIUDAD SET NOMBRE = :nombre WHERE ID_CIUDAD = :idCiudad", nativeQuery = true)
    void actualizarCiudad(@Param("idCiudad") long idCiudad, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CIUDAD WHERE ID_CIUDAD = :idCiudad", nativeQuery = true)
    void eliminarCiudad(@Param("idCiudad") long idCiudad);
    
}
