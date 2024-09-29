package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.DTOs.ProductoInventarioDTO;
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
    @DeleteMapping("/bodegas/{id}/delete")
    public ResponseEntity<String> bodegaBorrar(@PathVariable("id") long id) {
        try {
            bodegaRepository.eliminarBodega(id);
            return ResponseEntity.ok("Bodega eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bodegas/consulta")
    public ResponseEntity<?> consultaBodega() {
        Long bodega = 31L; // Hardcoded for testing
        Long idsucursal = 30L; // Hardcoded for testing
    
        try {
            // Log the parameters
            System.out.println("Bodega ID: " + bodega);
            System.out.println("Sucursal ID: " + idsucursal);

            // Fetch the data using the repository method
            Collection<ProductoInventarioDTO> productoInfo = bodegaRepository.consultarInfoBodegaSucursal(bodega, idsucursal);
            
            // Log the result
            System.out.println("Producto Info: " + productoInfo);
            
            // Prepare the response
            Map<String, Object> response = new HashMap<>();
            response.put("productoInfo", productoInfo);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            // Log the exception if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
}
