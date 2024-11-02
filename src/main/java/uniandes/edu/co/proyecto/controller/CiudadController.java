package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Ciudad;
import uniandes.edu.co.proyecto.repositories.CiudadRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CiudadController {
    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping("/ciudades")
    public ResponseEntity<Collection<Ciudad>> ciudades() {
        try {
            Collection<Ciudad> ciudad = ciudadRepository.darCiudades();
            return ResponseEntity.ok(ciudad);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/ciudades/new/save")
    public ResponseEntity<String> ciudadGuardar(@RequestBody Ciudad ciudad) {
        try{
            ciudadRepository.insertarCiudad(ciudad.getNombre());
        return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al crear la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PostMapping("/ciudades/{idCiudad}/edit/save")
    public ResponseEntity<String> ciudadEditarGuardar(@PathVariable("idCiudad") long idCiudad,@RequestBody Ciudad ciudad) {
        try{
            ciudadRepository.actualizarCiudad(idCiudad, ciudad.getNombre());
        return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al editar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @GetMapping("/ciudades/{idCiudad}/delete")
    public ResponseEntity<String> ciudadEliminar(@PathVariable("idCiudad") long idCiudad) {
        try{
            ciudadRepository.eliminarCiudad(idCiudad);
        return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al eliminar la ciudad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    


}
