package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CIUDAD")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCiudad;
    private String nombre;

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad() {
    ;}

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }                  

    
    
}
