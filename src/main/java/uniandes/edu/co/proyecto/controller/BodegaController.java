package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.repositories.BodegaRepository;

@RestController
public class BodegaController {
    @Autowired
    private BodegaRepository bodegaRepository;

    @GetMapping("/bodegas")
    public Collection<Bodega> bodegas(){
        return bodegaRepository.darBodegas();
    }
    @PostMapping("/bodegas/new/save")
    public ResponseEntity<String> ciudadGuardar(@RequestBody Bodega bodega) {
        try{
            bodegaRepository.insertarBodega(bodega.getNOMBRE(), bodega.getTAMANIO(), bodega.getIdsucursal().getIdSucursal());
        return new ResponseEntity<>("bodega creada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al crear la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    @GetMapping("/bodegas/{id}/delete")
    public ResponseEntity<?> bodegaBorrar(@PathVariable("id") long id) {
        try {
            bodegaRepository.eliminarBodega(id);
            return ResponseEntity.ok("Bodega eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}
