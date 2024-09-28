package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.slf4j.LoggerFactory;
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

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositories.ProductoRepository;

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
    private CategoriaRepository categoriaRepository;


    @GetMapping
    public Collection<Producto> obtenerProductos() {
        return productoRepository.darProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable("id") long id) {
        return productoRepository.darProducto(id);
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
        try {
            /*
            // Obtener el ID de la categoría del objeto Producto
            long categoriaId = producto.getCategoria().getCodigo();

            // Buscar la categoría en la base de datos
            Categoria categ = categoriaRepository.darCategoria(categoriaId);

            if (categ == null) {
                return new ResponseEntity<>("Categoría no encontrada", HttpStatus.NOT_FOUND);
            }
            
            else {   */           
            // Asignar la categoría al producto
            

            productoRepository.insertarProducto(
                producto.getNombre(),
                producto.getPrecioVenta(),
                producto.getPresentacion(),
                producto.getUnidadMedida(),
                producto.getEspEmpacado(),
                producto.getFechaExp().toString(), // Convert LocalDate to String
                producto.getCategoria().getCodigo().toString() // Convert Integer to String
            );
            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
            

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
}
