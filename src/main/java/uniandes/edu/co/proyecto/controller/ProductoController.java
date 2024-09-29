package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repositories.ProductoRepository;

@RestController
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public ResponseEntity<Collection<Producto>> productos() {
        try {
            Collection<Producto> prod = productoRepository.encontrarProductos();
            return ResponseEntity.ok(prod);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/productos/consulta")
    public Collection<Producto> sucursalesConProducto(
            @RequestParam(required = false) Integer idsucursal,
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max,
            @RequestParam(required = false) Integer cat,
            @RequestParam(required = false) String fecha) {
        
        if (idsucursal != null && min != null && max != null && cat != null && fecha != null) {
            return productoRepository.encontrarProductosPorTODO(idsucursal, min, max, cat, fecha);
        } else if (idsucursal != null) {
            return productoRepository.encontrarProductosPorSucursal(idsucursal);
        } else if (min != null && max!= null) {
            return productoRepository.encontraProductosPorPrecio(min, max); 
        }else if (fecha != null) {
            return productoRepository.encontrarProductosPorFechaMAYOR(fecha); 
        } else return Collections.emptyList();
    }



}