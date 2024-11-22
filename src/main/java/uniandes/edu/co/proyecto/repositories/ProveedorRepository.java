package uniandes.edu.co.proyecto.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.modelo.Proveedor.RECEPCIONPRODUCTO;

public interface ProveedorRepository extends MongoRepository<Proveedor,Integer>{ 
    
    default void insertarProveedor(Proveedor proveedor){
        save(proveedor);
    }

    @Query("{_id: ?0}")
    @Update("{$set: {NIT: ?1, nombre: ?2, direccion: ?3, nombreContacto: ?4, telefonoContacto: ?5, recepcion_producto: ?6} }")
    void actualizarProveedor(int id, Integer NIT, String nombre, String direccion, String nombreContacto, String telefonoContacto, List<RECEPCIONPRODUCTO> recepcion_producto);

}




/* 
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
                        @Param("telcontacto") String telcontacto);

    @Modifying
    @Transactional
    @Query(value = "UPDATE PROVEEDORES SET nombre = :nombre, direccion= :direccion, nombrecontacto= :nombrecontacto, telcontacto=:telcontacto WHERE nit = :nit", nativeQuery = true)
    void actualizarProveedor(@Param("nit") Integer nit,
                            @Param("nombre") String nombre,
                            @Param("direccion") String direccion,
                            @Param("nombrecontacto") String nombrecontacto,
                            @Param("telcontacto") String telcontacto);

} 
    

 */