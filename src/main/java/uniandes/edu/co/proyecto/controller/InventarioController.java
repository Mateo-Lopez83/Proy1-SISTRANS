package uniandes.edu.co.proyecto.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.DTOs.ProductoInventarioDTO;
import uniandes.edu.DTOs.ProductoOcupacionDTO;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.repositories.InventarioRepository;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {
    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping("/consultaRC3")
    public ResponseEntity<?> consultaInventario(@RequestParam(required = true) Integer bodega,
        @RequestParam(required = true) Integer idsucursal) {

        try {
    
            System.out.println("Bodega ID: " + bodega);
            System.out.println("Sucursal ID: " + idsucursal);

            // Supremamente intuitivo, si uno pide que se guarde como un Array de Objects, se guarda como un array de Objects[]
            Object[][] productoInfo = inventarioRepository.darInformacionInventarios(bodega, idsucursal);

            List<ProductoInventarioDTO> inventarios = new ArrayList<>(); 
            Map<String, Object> response = new HashMap<>();

            
            if (productoInfo != null && productoInfo.length > 0) {
                for (Object[] row : productoInfo) {
                
                    ProductoInventarioDTO dto = new ProductoInventarioDTO(
                        //Re sencillo mk
                        ((BigDecimal) row[0]).longValue(), // CODIGOBARRAS
                        ((String) row[1]),                 // NOMBRE
                        ((BigDecimal) row[2]).longValue(), // IDBODEGA
                        ((BigDecimal) row[3]).longValue(), // CANTIDAD_TOTAL
                        ((BigDecimal) row[4]).longValue(), // MINIMO_RECOMPRA
                        ((BigDecimal) row[5]).longValue(), // CAPACIDAD_MAX
                        ((BigDecimal) row[6]).doubleValue() // PROMEDIO
                    );
                    inventarios.add(dto); 
                }
                response.put("infoProducto", inventarios); 
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given parameters.");
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/consultaRC1")
    public ResponseEntity<?> consultaInventario(@RequestParam(required = true) List<Long> codbarras) {
        try {
        // Log the parameter
        System.out.println("Codbarras List: " + codbarras);
        
        // Fetch data using the repository method
        List<Object[]> productoInfo = inventarioRepository.findOcupacionByCodbarras(codbarras);
        
        List<ProductoOcupacionDTO> ocupaciones = new ArrayList<>();
        
        // Check if productoInfo is not null and has values
        if (productoInfo != null && !productoInfo.isEmpty()) {
            for (Object[] row : productoInfo) {
                // Create a new DTO instance for each row in the result
                ProductoOcupacionDTO dto = new ProductoOcupacionDTO(
                    ((BigDecimal) row[0]).longValue(), // CODIGOBARRAS
                    ((String) row[1]),                 // NOMBRE
                    ((BigDecimal) row[3]).longValue(), // IDBODEGA
                    ((BigDecimal) row[2]).doubleValue() // OCUPACION
                );
                ocupaciones.add(dto);
            }
            return ResponseEntity.ok(ocupaciones);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given parameters.");
        }
    } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Necesitamos todos los productos cuya cantidad en bodega es menor a la mínima 
    // (Cantidad_ocupada < Minimo_recompra en inventario)
    // Mostar producto.nom , producto.id , bodega.id (en que está bajo), bodega.sucursal, 
    // proveedor.nit (proveedores que se les haya comprado antes) , inventario.cantidad_ocupada
    @GetMapping("/RFC5")
    public ResponseEntity<?> consultaRFC5() {
        try {
            List<Inventario> productoInfo = inventarioRepository.findOcupacionInventario();
            StringBuilder responseBuilder = new StringBuilder();

            for (Inventario i : productoInfo) {
                String response = String.format(
                    "Producto código: %s, Producto nombre: %s, Bodega Id: %s, Sucursal: %s, Cantidad Ocupada: %s\n",
                    i.getInventarioPK().getProducto().getCodBarras(),
                    i.getInventarioPK().getProducto().getNombre(),
                    i.getInventarioPK().getBodega().getID(),
                    i.getInventarioPK().getBodega().getIdsucursal().getIdSucursal(),
                    i.getCantidadOcupada()
                );
                responseBuilder.append(response);
            }

            return new ResponseEntity<>(responseBuilder.toString(), HttpStatus.OK);

        }
        catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
