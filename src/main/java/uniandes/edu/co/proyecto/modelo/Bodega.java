package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "BODEGA")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BODEGA_SEQUENCE")
    @SequenceGenerator(name = "BODEGA_SEQUENCE", sequenceName = "BODEGA_SEQUENCE", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TAMANIO")
    private Integer tamanioM2;

    @Column(name = "ID_SUC")
    private Integer sucursalAsociada;

    public Bodega(String nombre, Integer tamañoM2, Integer Sucursal_Asociada) {
        this.nombre = nombre;
        this.tamanioM2 = tamañoM2;
        this.sucursalAsociada = Sucursal_Asociada;
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
        return tamanioM2;
    }

    public Integer getSucursal_Asociada() {
        return sucursalAsociada;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTamañoM2(Integer tamañoM2) {
        this.tamanioM2 = tamañoM2;
    }

    public void setSucursal_Asociada(Integer Sucursal_Asociada) {
        this.sucursalAsociada = Sucursal_Asociada;
    }
    
    
}
