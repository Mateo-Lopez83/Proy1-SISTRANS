package uniandes.edu.co.proyecto.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.DTOs.ProductoInventarioDTO;
import uniandes.edu.co.proyecto.repositories.InventarioRepository;

@RestController
public class InventarioController {
    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping("/inventarios/consulta")
public ResponseEntity<?> consultaInventario(@RequestParam(required = true) Integer bodega,
        @RequestParam(required = true) Integer idsucursal) {

    try {
        // Log the parameters
        System.out.println("Bodega ID: " + bodega);
        System.out.println("Sucursal ID: " + idsucursal);

        // Automatically retrieves an array of BigDecimal within an array of Object
        Object[][] productoInfo = inventarioRepository.darInformacionInventarios(bodega, idsucursal);

        List<ProductoInventarioDTO> inventarios = new ArrayList<>(); // Moved outside the loop
        Map<String, Object> response = new HashMap<>();

        // Check if productoInfo is not null and has values
        if (productoInfo != null && productoInfo.length > 0) {
            for (Object[] row : productoInfo) {
                // Create a DTO instance for each row in the result
                ProductoInventarioDTO dto = new ProductoInventarioDTO(
                    ((BigDecimal) row[0]).longValue(), // CODIGOBARRAS
                    ((String) row[1]),                 // NOMBRE
                    ((BigDecimal) row[2]).longValue(), // IDBODEGA
                    ((BigDecimal) row[3]).longValue(), // CANTIDAD_TOTAL
                    ((BigDecimal) row[4]).longValue(), // MINIMO_RECOMPRA
                    ((BigDecimal) row[5]).longValue(), // CAPACIDAD_MAX
                    ((BigDecimal) row[6]).doubleValue() // PROMEDIO
                );
                inventarios.add(dto); // Add each DTO to the same list
            }
            response.put("infoProducto", inventarios); // Add the entire list to the response
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given parameters.");
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception for debugging
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


}
