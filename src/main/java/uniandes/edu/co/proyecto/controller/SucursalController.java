package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositories.SucursalRepository;
import uniandes.edu.co.proyecto.repositories.SucursalRepositoryCustom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SucursalController {
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private SucursalRepositoryCustom sucursalRepositoryCustom;

    @GetMapping("/sucursales")
    public Collection<Sucursal> sucursales(){
        return sucursalRepository.darSucursales();
    }
    @PostMapping("/sucursales/new/save")
    public ResponseEntity<String> sucursalGuardar(@RequestBody Sucursal sucursal) {
        try{
         
            sucursalRepository.insertarSucursal(sucursal);
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al crear la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/sucursales/RFC2/{idsucursal}")
    public ResponseEntity<List<Document>> consultaProductosSucursal(@PathVariable("idsucursal") long idsucursal){
        try{
         
            List<Document> resultados = sucursalRepositoryCustom.obtenerInfoProductosSucursal((int)idsucursal);
            return ResponseEntity.ok(resultados);
        }
        catch(Exception e){
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    /*
    @PostMapping("/sucursales/new/save")
    public ResponseEntity<String> sucursalGuardar(@RequestBody Sucursal sucursal) {
        try{
            sucursalRepository.insertarSucursal(sucursal.getNombre(), sucursal.getDireccion(), sucursal.getTelefono(), sucursal.getCiudad_Asociada().getIdCiudad());
        return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al crear la sucursal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
     
    }
    @GetMapping("/sucursales/consulta")
    public ResponseEntity<?> sucursalesConProducto(
            @RequestParam(required = false) Integer CodBarras,
            @RequestParam(required = false) String nombre) {
        
        if (CodBarras != null && nombre != null) {
            Collection<Sucursal> sucursales = sucursalRepository.darSucursalesConProducto(CodBarras, nombre);
            return new ResponseEntity<>(sucursales, HttpStatus.OK);
        } else if (CodBarras != null) {
            Collection<Sucursal> sucursales = sucursalRepository.darSucursalesConProductoIdentificador(CodBarras); 
            return new ResponseEntity<>(sucursales, HttpStatus.OK);
        } else if (nombre != null) {
            Collection<Sucursal> sucursales = sucursalRepository.darSucursalesConProductoNombre(nombre);
            return new ResponseEntity<>(sucursales, HttpStatus.OK);
        } else return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }*/

}
    

