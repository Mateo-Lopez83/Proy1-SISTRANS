package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.repositories.BodegaRepository;
import uniandes.edu.co.proyecto.repositories.SucursalRepositoryCustom;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BodegaController {
    @Autowired
    private BodegaRepository bodegaRepository;
    @Autowired
    private SucursalRepositoryCustom sucursalRepositoryCustom;

    @PostMapping("bodegas/new/{idsucursal}")
    public ResponseEntity<String> bodegasGuardar(@RequestBody Bodega bodega, @PathVariable("idsucursal") long idsucursal) {
        try{
            bodegaRepository.insertarBodega(bodega);
            
        }catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al crear la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            sucursalRepositoryCustom.insertarBodegaEnSucursal((int) idsucursal, (int)bodega.getID());
            return new ResponseEntity<>("Ambos pasos creados exitosamente", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al guardar el id de la bodega en sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO:arreglar para que no necesite el id de la sucursal sino que lo encuentre 
    @DeleteMapping("bodegas/{idsucursal}/{idBodega}/delete")
    public ResponseEntity<String> bodegasEliminar(@PathVariable("idsucursal") long idsucursal, @PathVariable("idBodega") long idBodega) {
        try{
            bodegaRepository.eliminarBodegaporId((int) idBodega);
            
        }catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al eliminar la bodega", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            sucursalRepositoryCustom.eliminarBodegaSucursal((int) idsucursal, (int)idBodega);
            return new ResponseEntity<>("Ambos pasos eliminados exitosamente", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al eliminar el id de la bodega en sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    




}








/* 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.DTOs.ProductoInventarioDTO;
import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.repositories.BodegaRepository;
import uniandes.edu.co.proyecto.repositories.BodegaRepository.respuestaDocumento;
import uniandes.edu.co.proyecto.services.BodegaService;



@RestController
public class BodegaController {


    
    
    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private BodegaService bodegaService;

    @GetMapping("/bodegas")
    public Collection<Bodega> bodegas(){
        return bodegaRepository.darBodegas();
    }

    @PostMapping("/bodegas/new/save")
    public ResponseEntity<String> ciudadGuardar(@RequestBody Bodega bodega) {
        try {
            // Imprimir los valores recibidos

            // Verificar que el valor de tamanio cumple con la restricción
            if (bodega.getTAMANIO() <= 0) {
                return new ResponseEntity<>("El tamaño de la bodega debe ser mayor que 0", HttpStatus.BAD_REQUEST);
            }

            // Guardar la nueva bodega en la base de datos
            bodegaRepository.insertarBodega(bodega.getNOMBRE(), bodega.getTAMANIO(), bodega.getIdsucursal().getIdSucursal());
            System.out.println("Bodega creada exitosamente con tamaño: " + bodega.getTAMANIO());
            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
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

    @PostMapping("/bodegas/RFC6") 
    public ResponseEntity<?> consultaBodegaRF6(@RequestBody List<String> params) {
        if (params.size() != 2) {
            return new ResponseEntity<>("Número incorrecto de parámetros para la consulta del documento", HttpStatus.BAD_REQUEST);
        }
        try {
        Long id = Long.valueOf(params.get(0));
        Long idsucursal = Long.valueOf(params.get(1));

        Collection<respuestaDocumento> informacion = bodegaService.getDocumentoIngresoS(id, idsucursal);

        // Verificar cuántos objetos están llegando en la respuesta
        int cantidadObjetos = informacion.size();
        System.out.println("Cantidad de objetos en la respuesta: " + cantidadObjetos);

        // Construir una lista de respuestas
        List<Map<String, Object>> responseList = new ArrayList<>();
        for (respuestaDocumento info : informacion) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucursal", info.getSucursal());
            response.put("bodega", info.getBodega());
            response.put("idIngreso", info.getIdIngreso());
            response.put("fecha", info.getFecha());
            response.put("nombreProvedor", info.getNombreProvedor());
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al consultar la información del documento", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/bodegas/RFC7")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> consultaBodegaRF7(@RequestBody List<String> params) {
        if (params.size() != 2) {
            return new ResponseEntity<>("Número incorrecto de parámetros para la consulta del documento", HttpStatus.BAD_REQUEST);
        }
        try {
            Long id = Long.valueOf(params.get(0));
            Long idsucursal = Long.valueOf(params.get(1));
    
            Collection<respuestaDocumento> informacion = bodegaService.getDocumentoIngresoRC(id, idsucursal);
    
            // Verificar cuántos objetos están llegando en la respuesta
            int cantidadObjetos = informacion.size();
            System.out.println("Cantidad de objetos en la respuesta: " + cantidadObjetos);
    
            // Construir una lista de respuestas
            List<Map<String, Object>> responseList = new ArrayList<>();
            for (respuestaDocumento info : informacion) {
                Map<String, Object> response = new HashMap<>();
                response.put("sucursal", info.getSucursal());
                response.put("bodega", info.getBodega());
                response.put("idIngreso", info.getIdIngreso());
                response.put("fecha", info.getFecha());
                response.put("nombreProvedor", info.getNombreProvedor());
                responseList.add(response);
            }
    
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al consultar la información del documento", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
    
}
 */



            
           