package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repositories.CategoriaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public Collection<Categoria> categorias(){
        return categoriaRepository.darCategorias();
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
            categoriaRepository.actualizarCategoria(categoria.getCodigo(), categoria.getNombre(), categoria.getdescripcion(), categoria.getCaracteristicas());
        return new ResponseEntity<>("Categoria actualizada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al actualizar la categoria", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        
    }
}


