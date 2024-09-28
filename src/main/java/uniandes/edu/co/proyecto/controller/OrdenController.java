package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

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

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Inventario;
import uniandes.edu.co.proyecto.modelo.Orden;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositories.OrdenRepository;


@RestController
@RequestMapping("/ordenes")
public class OrdenController {
    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping
    public Collection<Orden> obtenerOrdenes() {
        return ordenRepository.darOrdenes();
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
        if (!"vigente".equalsIgnoreCase(orden.getEstado())) {
            return new ResponseEntity<>("Estado de nueva orden debe ser 'Vigente'", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ordenRepository.insertarOrden(
            orden.getFechaEntrega().toString(),
            orden.getEstado(),
            orden.getSucursalEnvio(),
            orden.getProveedor()
        );

        String responseMessage = String.format(
            "Se ha creado la orden exitosamente, con los siguientes datos:\n" +
            "fechaCreacion: %s\n" +
            "fechaEntrega: %s\n" +
            "estado: %s\n" +
            "sucursalEnvio: %d\n" +
            "proveedor: %d",
            java.time.LocalDate.now().toString(), // Assuming CURRENT_DATE is the current date
            orden.getFechaEntrega().toString(),
            orden.getEstado(),
            orden.getSucursalEnvio(),
            orden.getProveedor()
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



