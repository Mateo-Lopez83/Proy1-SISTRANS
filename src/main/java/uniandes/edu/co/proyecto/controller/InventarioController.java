package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.DTOs.ProductoInventarioDTO;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.repositories.InventarioRepository;

@RestController
public class InventarioController {
    @Autowired
    private InventarioRepository inventarioRepository;
/*
    @GetMapping("/inventarios/consulta")
    public ResponseEntity<?> consultaInventario() {
        int bodega = 31; // Hardcoded for testing
        int idsucursal = 30; // Hardcoded for testing
    
        try {
            // Log the parameters
            System.out.println("Bodega ID: " + bodega);
            System.out.println("Sucursal ID: " + idsucursal);

            // Fetch the data using the repository method
            Collection<ProductoInventarioDTO> productoInfo = inventarioRepository.darInformacionInventarios(bodega, idsucursal);
            
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
    }*/
}
