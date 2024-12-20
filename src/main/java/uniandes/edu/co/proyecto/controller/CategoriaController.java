package uniandes.edu.co.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repositories.CategoriaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/new/save")
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria) {
        try{
            categoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoria creado exitosamente", HttpStatus.CREATED);
        } catch( Exception e) {;
            return new ResponseEntity<>("Error al crear la categoria: "+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Categoria>> obtenerCategoriasPorId(@PathVariable("id") int id) {
        try{
            List<Categoria> categorias = categoriaRepository.darCategoria(id);
            if (categorias != null) {
                return new ResponseEntity<>(categorias, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch( Exception e) {;
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Categoria>> obtenerCategoriasPorNombre(@PathVariable("nombre") String nombre) {
        try{
            List<Categoria> categorias = categoriaRepository.darCategoriaNom(nombre);
            if (categorias != null) {
                return new ResponseEntity<>(categorias, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch( Exception e) {;
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}

/* 
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repositories.CategoriaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public Collection<Categoria> categorias(){
        return categoriaRepository.darCategorias();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> categoria(@PathVariable("id") long id) {
        try {
            Categoria categoria = categoriaRepository.darCategoria(id);  // Pass the 'id' from the URL
            if (categoria != null) {
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Handle exceptions
        }
    }

    @GetMapping("/categorias/nom/{nombre}")
    public ResponseEntity<Categoria> categoria(@PathVariable("nombre") String nombre) {
        try {
            Categoria categoria = categoriaRepository.darCategoriaNom(nombre);  // Pass the 'nombre' from the URL
            if (categoria != null) {
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Handle exceptions
        }
    }
    

    @PostMapping("/categorias/new/save")
    public ResponseEntity<String> categoriaGuardar(@RequestBody Categoria categoria) {
        try{
            categoriaRepository.insertarCategoria(categoria.getNombre(), categoria.getdescripcion(), categoria.getCaracteristicas());
        return new ResponseEntity<>("Categoria creada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al crear la categoria", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/categorias/{id}/update")
    public ResponseEntity<String> categoriaActualizar(@RequestBody Categoria categoria) {
        try{
            categoriaRepository.actualizarCategoria(categoria.getId(), categoria.getNombre(), categoria.getdescripcion(), categoria.getCaracteristicas());
        return new ResponseEntity<>("Categoria actualizada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al actualizar la categoria", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        
    }
}
 */

