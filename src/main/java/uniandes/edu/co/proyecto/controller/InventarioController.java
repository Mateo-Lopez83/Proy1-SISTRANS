package uniandes.edu.co.proyecto.controller;
/* 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class InventarioController {
    
    private static final Logger logger = LoggerFactory.getLogger(OrdenController.class);


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

        System.out.println("Codbarras List: " + codbarras);
        
        List<Object[]> productoInfo = inventarioRepository.findOcupacionByCodbarras(codbarras);
        
        List<ProductoOcupacionDTO> ocupaciones = new ArrayList<>();
        
        if (productoInfo != null && !productoInfo.isEmpty()) {
            for (Object[] row : productoInfo) {
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
            List<Object[]> productoInfoProveedor = inventarioRepository.findOcupacionInventarioConProveedor();
            
            List<Map<String, Object>> responseList = new ArrayList<>();
    
            // Procesar productoInfoProveedor
            for (Object[] i : productoInfoProveedor) {
                Map<String, Object> response = new HashMap<>();
                response.put("productoCodigo", ((BigDecimal) i[0]).longValue());
                response.put("productoNombre", i[3]);
                response.put("bodegaId", ((BigDecimal) i[1]).longValue());
                response.put("sucursal", ((BigDecimal) i[4]).longValue());
                response.put("cantidadOcupada", ((BigDecimal) i[2]).longValue());
                response.put("proveedor", i[5]);
                responseList.add(response);
            }
            /* 
            // Procesar productoInfo
            for (Inventario i : productoInfo) {
                Map<String, Object> response = new HashMap<>();
                response.put("productoCodigo", i.getInventarioPK().getProducto().getCodBarras());
                response.put("productoNombre", i.getInventarioPK().getProducto().getNombre());
                response.put("bodegaId", i.getInventarioPK().getBodega().getID());
                response.put("sucursal", i.getInventarioPK().getBodega().getIdsucursal().getIdSucursal());
                response.put("cantidadOcupada", i.getCantidadOcupada());
                responseList.add(response);
            }
            return ResponseEntity.ok(responseList);
    
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al consultar el inventario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
*/