package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.repositories.ProveedorRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProveedorController {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/proveedores")
    public ResponseEntity<Collection<Proveedor>> proveedores() {
        try {
            Collection<Proveedor> proveedores = proveedorRepository.darProveedores();
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/proveedores/new/save")
    public ResponseEntity<String> proveedorGuardar(@RequestBody Proveedor proveedor){
        try {
            proveedorRepository.insertarProveedor(proveedor.getNIT(), proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombreContacto(), proveedor.getTelefonoContacto());
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error al crear el proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/proveedores/{id}/update")
    public ResponseEntity<String> proveedorActualizar(@RequestBody Proveedor proveedor) {
        try{
            proveedorRepository.actualizarProveedor(proveedor.getNIT(),proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombreContacto(), proveedor.getTelefonoContacto());
        return new ResponseEntity<>("Categoria actualizada exitosamente", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace(); 
            return new ResponseEntity<>("Error al actualizar la categoria", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        
    }

    
    
    
}
