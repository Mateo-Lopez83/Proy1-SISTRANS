package uniandes.edu.co.proyecto.repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{

    @Query(value = "SELECT * FROM PROVEEDORES", nativeQuery = true)
    Collection<Proveedor>darProveedores();

    @Modifying
    @Transactional
    @Query(value="INSERT INTO PROVEEDORES (nit, nombre, direccion, nombrecontacto, telcontacto) VALUES (:nit,:nombre,:direccion,:nombrecontacto,:telcontacto)", nativeQuery = true)
    void insertarProveedor(@Param("nit") Integer nit,
                        @Param("nombre") String nombre,
                        @Param("direccion") String direccion,
                        @Param("nombrecontacto") String nombrecontacto,
                        @Param("telcontacto") Integer telcontacto);

                        
    
} 
    

