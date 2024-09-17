package uniandes.edu.co.proyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import uniandes.edu.co.proyecto.modelo.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer>{

    public interface RespuestaInformacionCiudad {
        
        
    }
    @Query(value = "SELECT * FROM CIUDAD", nativeQuery = true)
    Collection<Ciudad>darCiudades();
    
}
