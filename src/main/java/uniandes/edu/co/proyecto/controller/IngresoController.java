package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;
import uniandes.edu.co.proyecto.services.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/ingresos")
@CrossOrigin(origins = "http://localhost:3000")
public class IngresoController {
    

    @Autowired
    private IngresoService ingresoService;

    @PostMapping("/nuevo")
    public ResponseEntity<String> guardarProducto
                (@RequestParam(required = false) Long idBodega,
                @RequestParam(required = false) Long idOrden)  {

        try {
            ingresoService.ingresarIngreso(idBodega,idOrden);
        } catch (Exception e) {
            System.out.println(e.getMessage());
             return new ResponseEntity<>("Error al realizar la transacción. Haciendo rollback...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Transacción completada exitosamente", HttpStatus.CREATED);
        }
    }
    

