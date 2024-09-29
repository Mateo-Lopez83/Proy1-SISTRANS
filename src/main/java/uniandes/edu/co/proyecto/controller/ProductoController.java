package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


import org.slf4j.LoggerFactory;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositories.ProductoRepository;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.repositories.CategoriaRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public Collection<Producto> obtenerProductos() {
        return productoRepository.darProductos();
    }
    

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable("id") long id) {
        try {
            return productoRepository.darProducto(id);
        } catch (Exception e) {
            logger.error("Error al obtener el producto por id", e);
            return null;
        }
    }

    @GetMapping("/nombre/{nombre}")
    public Producto obtenerProductoNombre(@PathVariable("nombre") String nombre) {
        try {
            return productoRepository.darProductoNom(nombre);
        } catch (Exception e) {
            logger.error("Error al obtener el producto por nombre", e);
            return null;
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
        try {
            String fechaExp = producto.getFechaExp() != null ? producto.getFechaExp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

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
    
    // Hay 4 casos: rango precio, rango fechas, de una sucursal, de una categoría
    @GetMapping("/consulta")
    public Collection<Producto> sucursalesConProducto(@RequestBody List<String> params) {
        try {
            if (params == null || params.isEmpty()) {
                return Collections.emptyList();
            }

            String tipoConsulta = params.get(0);

            switch (tipoConsulta.toLowerCase()) {
                case "sucursal":
                    if (params.size() == 2) {
                        logger.info("Buscando productos por sucursal");
                        Integer idsucursal = Integer.valueOf(params.get(1));
                        return productoRepository.encontrarProductosPorSucursal(idsucursal);
                    }
                    break;
                case "precio":
                    if (params.size() == 3) {
                        logger.info("Buscando productos por precio");
                        Integer min = Integer.valueOf(params.get(1));
                        Integer max = Integer.valueOf(params.get(2));
                        return productoRepository.encontraProductosPorPrecio(min, max);
                    }
                    break;
                case "fecha":
                    if (params.size() == 3) {
                        logger.info("Buscando productos por fecha");
                        String fecha = params.get(1);
                        String operador = params.get(2);
                        if (operador.equals("mayor")) {
                            return productoRepository.encontrarProductosPorFechaMAYOR(fecha);
                        } else if (operador.equals("menor")) {
                            return productoRepository.encontrarProductosPorFechaMENOR(fecha);
                        }
                    }
                    break;
                case "categoria":
                    if (params.size() == 2) {
                        logger.info("Buscando productos por categoria");
                        Integer cat = Integer.valueOf(params.get(1));
                        return productoRepository.encontrarProductosPorCategoria(cat);
                    }
                    break;
                default:
                    return Collections.emptyList();
            }

            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
        
}

