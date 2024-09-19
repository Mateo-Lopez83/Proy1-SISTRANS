package uniandes.edu.co.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import uniandes.edu.co.proyecto.modelo.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer>{

    @Query(value = "SELECT * FROM CIUDADES", nativeQuery = true)
    Collection<Ciudad>darCiudades();

    @Query(value = "SELECT * FROM CIUDADES WHERE idciudad = :idciudad", nativeQuery = true)
    Ciudad darCiudad(@Param("idciudad") long idciudad);

    //Revisar este query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CIUDADES (nombre, idciudad) VALUES (:nombre, :idciudad)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);


    @Modifying
    @Transactional
    @Query(value = "UPDATE CIUDAD SET nombre = :nombre WHERE idciudad = :idciudad", nativeQuery = true)
    void actualizarCiudad(@Param("idciudad") long idciudad, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CIUDADES WHERE idciudad = :idciudad", nativeQuery = true)
    void eliminarCiudad(@Param("idciudad") long idciudad);
    
}
