package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BODEGA")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private Integer tamañoM2;
    private Integer Sucursal_Asociada;

    public Bodega(String nombre, Integer tamañoM2, Integer Sucursal_Asociada) {
        this.nombre = nombre;
        this.tamañoM2 = tamañoM2;
        this.Sucursal_Asociada = Sucursal_Asociada;
    }

    public Bodega() {;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getTamañoM2() {
        return tamañoM2;
    }

    public Integer getSucursal_Asociada() {
        return Sucursal_Asociada;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTamañoM2(Integer tamañoM2) {
        this.tamañoM2 = tamañoM2;
    }

    public void setSucursal_Asociada(Integer Sucursal_Asociada) {
        this.Sucursal_Asociada = Sucursal_Asociada;
    }
    
    
}
