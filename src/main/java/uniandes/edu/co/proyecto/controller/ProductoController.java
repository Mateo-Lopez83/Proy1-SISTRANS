package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.List;


import org.slf4j.LoggerFactory;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositories.ProductoRepository;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public Collection<Producto> obtenerProductos() {
        return productoRepository.darProductos();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable("id") long id) {
        try {
            Producto producto = productoRepository.darProducto(id);
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el producto con id: " + id);
            }
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            logger.error("Error al obtener el producto por id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el producto");
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerProductoNombre(@PathVariable("nombre") String nombre) {
        try {
            Producto producto = productoRepository.darProductoNom(nombre);
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el producto con nombre: " + nombre);
            }
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            logger.error("Error al obtener el producto por nombre", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el producto");
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
        try {
            String fechaExp = producto.getFechaExp() != null ? producto.getFechaExp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

            if (productoRepository.darProductoNom(producto.getNombre()) != null) {
                return new ResponseEntity<>("Ya existe un producto con ese nombre", HttpStatus.CONFLICT);
            }

            productoRepository.insertarProducto(
                producto.getNombre(),
                producto.getPrecioVenta(),
                producto.getPresentacion(),
                producto.getUnidadMedida(),
                producto.getEspEmpacado(),
                fechaExp,
                producto.getCategoria().getCodigo().toString() // Convert Integer to String
            );
            return ResponseEntity.ok("Producto guardado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> actualizarProducto(@PathVariable("id") long id, @RequestBody Producto producto) {
        try {
            Producto productoExistente = productoRepository.darProducto(id);
    
            // Actualizar solo los atributos proporcionados
            if (producto.getNombre() != null) {
                productoExistente.setNombre(producto.getNombre());
            }
            if (producto.getPrecioVenta() != 0) {
                productoExistente.setPrecioVenta(producto.getPrecioVenta());
            }
            if (producto.getPresentacion() != null) {
                productoExistente.setPresentacion(producto.getPresentacion());
            }
            if (producto.getUnidadMedida() != null) {
                productoExistente.setUnidadMedida(producto.getUnidadMedida());
            }
            if (producto.getEspEmpacado() != null) {
                productoExistente.setEspEmpacado(producto.getEspEmpacado());
            }
            if (producto.getFechaExp() != null) {
                productoExistente.setFechaExp(producto.getFechaExp());
            }
    
            // Guardar el producto actualizado en la base de datos
            productoRepository.save(productoExistente);
            return ResponseEntity.ok("Producto actualizado exitosamente");
    
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> borrarProducto(@PathVariable("id") long id) {
        try {
            productoRepository.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/consulta")
    public ResponseEntity<?> sucursalesConProducto(@RequestBody List<String> params) {
    try {
        if (params == null || params.isEmpty()) {
            return new ResponseEntity<>("No se proporcionaron parámetros", HttpStatus.BAD_REQUEST);
        }

        String tipoConsulta = params.get(0);

        switch (tipoConsulta.toLowerCase()) {
            case "sucursal":
                if (params.size() != 2) {
                    return new ResponseEntity<>("Número incorrecto de parámetros para la consulta por sucursal", HttpStatus.BAD_REQUEST);
                }
                try {
                    Integer idsucursal = Integer.valueOf(params.get(1));
                    Collection<Producto> productos = productoRepository.encontrarProductosPorSucursal(idsucursal);
                    if (productos.isEmpty()) {
                        return new ResponseEntity<>("No se encontraron productos para la sucursal proporcionada", HttpStatus.NOT_FOUND);
                    }
                    return new ResponseEntity<>(productos, HttpStatus.OK);
                } catch (NumberFormatException e) {
                    return new ResponseEntity<>("El ID de la sucursal debe ser un número", HttpStatus.BAD_REQUEST);
                }
            case "precio":
                if (params.size() != 3) {
                    return new ResponseEntity<>("Número incorrecto de parámetros para la consulta por precio", HttpStatus.BAD_REQUEST);
                }
                try {
                    Integer min = Integer.valueOf(params.get(1));
                    Integer max = Integer.valueOf(params.get(2));
                    Collection<Producto> productos = productoRepository.encontraProductosPorPrecio(min, max);
                    if (productos.isEmpty()) {
                        return new ResponseEntity<>("No se encontraron productos en el rango de precios proporcionado", HttpStatus.NOT_FOUND);
                    }
                    return new ResponseEntity<>(productos, HttpStatus.OK);
                } catch (NumberFormatException e) {
                    return new ResponseEntity<>("Los valores de precio deben ser números", HttpStatus.BAD_REQUEST);
                }
            case "fecha":
                if (params.size() != 3) {
                    return new ResponseEntity<>("Número incorrecto de parámetros para la consulta por fecha", HttpStatus.BAD_REQUEST);
                }
                String fecha = params.get(1);
                String operador = params.get(2);
                if (!operador.equals("mayor") && !operador.equals("menor")) {
                    return new ResponseEntity<>("El operador de fecha debe ser 'mayor' o 'menor'", HttpStatus.BAD_REQUEST);
                }
                Collection<Producto> productos;
                if (operador.equals("mayor")) {
                    productos = productoRepository.encontrarProductosPorFechaMAYOR(fecha);
                } else {
                    productos = productoRepository.encontrarProductosPorFechaMENOR(fecha);
                }
                if (productos.isEmpty()) {
                    return new ResponseEntity<>("No se encontraron productos para la fecha y operador proporcionados", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(productos, HttpStatus.OK);
            case "categoria":
                if (params.size() != 2) {
                    return new ResponseEntity<>("Número incorrecto de parámetros para la consulta por categoría", HttpStatus.BAD_REQUEST);
                }
                int categoria = Integer.parseInt(params.get(1));
                Collection<Producto> productosPorCategoria = productoRepository.encontrarProductosPorCategoria(categoria);
                if (productosPorCategoria.isEmpty()) {
                    return new ResponseEntity<>("No se encontraron productos para la categoría proporcionada", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(productosPorCategoria, HttpStatus.OK);
            default:
                return new ResponseEntity<>("Tipo de consulta no válido", HttpStatus.BAD_REQUEST);
        }
    } catch (Exception e) {
        logger.error("Error al realizar la consulta de productos", e);
        return new ResponseEntity<>("Error al realizar la consulta de productos", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    
    private String formatProductos(Collection<Producto> productos) {
        StringBuilder formattedProductos = new StringBuilder();
        for (Producto producto : productos) {
            formattedProductos.append(String.format(
                "Código de barras: %d, Nombre: %s,  Precio de venta: %d, Presentación: %s, Unidad de medida: %s, Empacado: %s, Fecha de expiración: %s, Categoría: %s\n",
                producto.getCodBarras(),
                producto.getNombre(),
                producto.getPrecioVenta(),
                producto.getPresentacion(),
                producto.getUnidadMedida(),
                producto.getEspEmpacado(),
                producto.getFechaExp() != null ? producto.getFechaExp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "N/A",
                producto.getCategoria().getNombre()
            ));
        }
        return formattedProductos.toString();
    }
        
}

