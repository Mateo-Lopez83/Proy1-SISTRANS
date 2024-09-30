package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.time.LocalDate;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.modelo.Orden_Producto;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositories.OrdenRepository;
import uniandes.edu.co.proyecto.repositories.Orden_ProductoRepository;
import uniandes.edu.co.proyecto.modelo.ProductoExtra;
import uniandes.edu.co.proyecto.repositories.ProductoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private static final Logger logger = LoggerFactory.getLogger(OrdenController.class);

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private Orden_ProductoRepository orden_ProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;


    @GetMapping
    public ResponseEntity<String> obtenerOrdenes() {
        Collection<Orden> ordenes = ordenRepository.darOrdenes();
        if (ordenes.isEmpty()) {
            return new ResponseEntity<>("No hay órdenes disponibles", HttpStatus.NOT_FOUND);
        }

        StringBuilder formattedOrdenes = new StringBuilder();
        for (Orden orden : ordenes) {
            formattedOrdenes.append(String.format(
                "ID: %d\nFecha Creación: %s\nFecha Entrega: %s\nEstado: %s\nSucursal Envío: %d\nProveedor: %d\n\n",
                orden.getIdentificador(),
                orden.getFechaCreacion().toString(),
                orden.getFechaEntrega().toString(),
                orden.getEstado(),
                orden.getSucursalEnvio(),
                orden.getProveedor()
            ));
        }

        return new ResponseEntity<>(formattedOrdenes.toString(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerOrden(@PathVariable("id") long id) {
        Orden orden = ordenRepository.darOrden(id);
        if (orden == null) {
            return new ResponseEntity<>("La orden con ese ID no existe", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orden, HttpStatus.OK);
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarOrden(@RequestBody Orden orden) {
        try {
            if (!"vigente".equals(orden.getEstado())) {
                return new ResponseEntity<>("Estado de nueva orden debe ser 'vigente'", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            ordenRepository.insertarOrden(
                orden.getFechaEntrega().toString(),
                orden.getEstado(),
                orden.getSucursalEnvio(),
                orden.getProveedor()
            );
            logger.info("Orden creada con éxito");
            // Hasta aquí ya se creo una orden

            int id_buscado;
            try{
                id_buscado = ordenRepository.buscarUltimaOrden();
                logger.info("ID de la orden creada: " + id_buscado);
            }
            catch (Exception e) {
                return new ResponseEntity<String>("No se halló la orden", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            
            // Ahora se deben agregar los productos a la tabla orden_producto
            try {
                List<ProductoExtra> productosExtra = orden.getProductosExtra();

                if (productosExtra == null || productosExtra.isEmpty()) {
                    logger.warn("No se recibieron productos");
                    return new ResponseEntity<>("No se recibieron productosExtra", HttpStatus.BAD_REQUEST);
                }

                for (ProductoExtra prod : productosExtra) {
                    logger.info("Procesando producto: idProducto={}, cantidad={}, precio={}",
                        prod.getCodBarras(), prod.getCantidad(), prod.getPrecioBodega());

                    if (prod.getCantidad() <= 0) {
                        return new ResponseEntity<>("La cantidad de un producto debe ser mayor a 0", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    if (prod.getPrecioBodega() <= 0) {
                        return new ResponseEntity<>("El precio de un producto debe ser mayor a 0", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    if (productoRepository.darProducto(prod.getCodBarras()) == null) {
                        return new ResponseEntity<>("El producto con ese ID no existe", HttpStatus.NOT_FOUND);
                    }

                    logger.info("Entro a");

                    orden_ProductoRepository.insertarProductoOrden(
                        id_buscado,
                        prod.getCodBarras(),
                        prod.getCantidad(),
                        prod.getPrecioBodega()
                    );

                    logger.info("Salió");
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error al agregar productos a la orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
             
           // Construir la cadena con la información de los productos
           String productosInfo = "";
           for (ProductoExtra prod : orden.getProductosExtra()) {
               productosInfo += String.format(
                   "\tidProducto: %d, cantidad: %d, precio: %d\n",
                   prod.getCodBarras(), prod.getCantidad(), prod.getPrecioBodega()
               );
           }

           // Mensaje de salida
            String responseMessage = String.format(
                "Se ha creado la orden exitosamente, con los siguientes datos:\n" +
                "FechaCreacion: %s\n" +
                "FechaEntrega: %s\n" +
                "Estado: %s\n" +
                "SucursalEnvio: %d\n" +
                "Proveedor: %d\n" +
                "Productos:\n%s",
                java.time.LocalDate.now().toString(), // Assuming CURRENT_DATE is the current date
                orden.getFechaEntrega().toString(),
                orden.getEstado(),
                orden.getSucursalEnvio(),
                orden.getProveedor(),
                productosInfo
            );

            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> actualizarOrden(@PathVariable("id") long id, @RequestBody Map<String, String> estadoMap) {
        try {
            Orden ordenAntes = ordenRepository.darOrden(id);
            if (ordenAntes == null) {
                return new ResponseEntity<>("La orden con ese ID no existe", HttpStatus.NOT_FOUND);
            }

            String est = ordenAntes.getEstado();
            String nuevoEstado = estadoMap.get("estado");
            
            if ("vigente".equalsIgnoreCase(est)) {
                ordenRepository.actualizarOrden(nuevoEstado, id);
                return new ResponseEntity<>("Orden actualizada exitosamente", HttpStatus.OK);
            } 
            
            else if ("anulada".equalsIgnoreCase(est)) {
                return new ResponseEntity<>("La orden ya había sido anulada antes", HttpStatus.OK);
            }

            else if ("entregada".equalsIgnoreCase(est)) {
                return new ResponseEntity<>("No se puede anular una orden ya entregada", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("No se pudo actualizar la orden", HttpStatus.BAD_REQUEST);
    }
}



