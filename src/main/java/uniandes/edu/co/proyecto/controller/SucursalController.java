package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositories.SucursalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class SucursalController {
    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping("/sucursales")
    public Collection<Sucursal> sucursales(){
        return sucursalRepository.darSucursales();
    }

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
    public Collection<Sucursal> sucursalesConProducto(
            @RequestParam(required = false) Integer CodBarras,
            @RequestParam(required = false) String nombre) {
        
        if (CodBarras != null && nombre != null) {
            return sucursalRepository.darSucursalesConProducto(CodBarras, nombre);
        } else if (CodBarras != null) {
            return sucursalRepository.darSucursalesConProductoIdentificador(CodBarras); 
        } else if (nombre != null) {
            return sucursalRepository.darSucursalesConProductoNombre(nombre); 
        } else return Collections.emptyList();
    }

}
    

