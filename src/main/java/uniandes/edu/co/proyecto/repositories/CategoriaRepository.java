package uniandes.edu.co.proyecto.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria,Integer>{ 
    
    default void insertarProveedor(Categoria categoria){
        save(categoria);
    }
    
    @Query("{_id: ?0}")
    List<Categoria> darCategoria(int id);

    @Query("{nombre: ?0}")
    List<Categoria> darCategoriaNom(String nombre);
}

/* 
import uniandes.edu.co.proyecto.modelo.Categoria;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
    @Query(value = "SELECT * FROM CATEGORIAS", nativeQuery = true)
    Collection<Categoria>darCategorias();

    @Query(value = "SELECT * FROM CATEGORIAS WHERE codigo = :codigo", nativeQuery = true)
    Categoria darCategoria(@Param("codigo") long codigo);

    @Query(value = "SELECT * FROM CATEGORIAS WHERE nombre = :nombre", nativeQuery = true)
    Categoria darCategoriaNom(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CATEGORIAS (nombre, codigo, descripcion, caracteristicas) VALUES (:nombre, categoria_sequence.nextVal, :descripcion, :caracteristicas)", nativeQuery = true)
    void insertarCategoria(@Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("caracteristicas")String caracteristicas);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CATEGORIAS SET nombre = :nombre, descripcion= :descripcion, caracteristicas= :caracteristicas WHERE codigo = :codigo", nativeQuery = true)
    void actualizarCategoria(@Param("codigo") long codigo, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("caracteristicas")String caracteristicas);



}
 */