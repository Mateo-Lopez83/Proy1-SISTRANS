package uniandes.edu.co.proyecto.modelo;
import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "BODEGAS")
public class Bodega implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id", columnDefinition = "NUMBER")
    private int id;

    //@Column(name = "nombre", columnDefinition = "VARCHAR2(255)")
    private String nombre;

    //@Column(name = "tamanio", columnDefinition = "NUMBER")
    private int tamanio;

    @ManyToOne
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL")
    private Sucursal idsucursal;

    public Bodega() {;
    }

    public Bodega(String nombre, Integer tamanio, Sucursal Sucursal_Asociada) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.idsucursal = Sucursal_Asociada;
    }

    

    public Integer getID() {
        return id;
    }

    public void setID(Integer iD) {
        id = iD;
    }

    public String getNOMBRE() {
        return nombre;
    }

    public void setNOMBRE(String nOMBRE) {
        nombre = nOMBRE;
    }

    public Integer getTAMANIO() {
        return tamanio;
    }

    public void setTAMANIO(Integer tAMANIO) {
        tamanio = tAMANIO;
    }
    
    
    
}
