package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Categoria;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM PRODUCTOS", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM PRODUCTOS WHERE ID = :idProducto", nativeQuery = true)
    Producto darProducto(@Param("idProducto") long idProducto);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PRODUCTOS (id, nombre, precioVenta, presentacion, unidadMedida, espEmpacado, fechaExp, categoria) VALUES (producto_sequence.nextVal, :nombre, :precioVenta, :presentacion, :unidadMedida, :espEmpacado, :fechaExp, :categoria)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, 
                          @Param("precioVenta") int precioVenta, 
                          @Param("presentacion") String presentacion, 
                          @Param("unidadMedida") String unidadMedida, 
                          @Param("espEmpacado") String espEmpacado, 
                          @Param("fechaExp") String fechaExp, 
                          @Param("categoria") Categoria categoria);

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
    @Query(value = "DELETE FROM PRODUCTOS WHERE id = :idProducto", nativeQuery = true)
    void eliminarProducto(@Param("idProducto") long idProducto);
}
