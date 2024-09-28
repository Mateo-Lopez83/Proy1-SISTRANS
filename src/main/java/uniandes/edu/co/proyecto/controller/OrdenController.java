package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

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
import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositories.OrdenRepository;


@RestController
@RequestMapping("/ordenes")
public class OrdenController {
    @Autowired
    private OrdenRepository productoRepository;

    @GetMapping
    public Collection<Orden> obtenerOrdenes() {
        return productoRepository.darOrdenes();
    }

    @GetMapping("/{id}")
    public Orden obtenerOrden(@PathVariable("id") long id) {
        return productoRepository.darOrden(id);
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> guardarOrden(@RequestBody Orden orden) {
        try {
            
            if (orden.getEstado() != "Vigente") {
                return new ResponseEntity<>("Estado de nueva orden debe ser 'Vigente'", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
            productoRepository.insertarOrden(
                orden.getFechaEntrega(),
                orden.getEstado(),
                orden.getSucursalEnvio(),
                orden.getProveedor()  
            );
            return new ResponseEntity<>("Orden creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> actualizarOrden(@PathVariable("id") long id, @RequestBody Orden orden) {
        try {
            Orden ordenAntes = productoRepository.darOrden(id);
            if (ordenAntes.getEstado() != "Entregada" | ordenAntes.getEstado() != "Anulada") {
                productoRepository.actualizarOrden(orden.getEstado());
            }
            return new ResponseEntity<>("Orden actualizada exitosamente", HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
