package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUCURSALES")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idsucursal", columnDefinition = "NUMBER")
    private Integer idSucursal;
    private String nombre;
    private String direccion;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "ciudad_asociada", referencedColumnName = "IDCIUDAD")
    private Ciudad ciudad_Asociada;

    public Sucursal(String nombre, String direccion, String telefono, Ciudad ciudad_Asociada) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad_Asociada = ciudad_Asociada;
    }

    public Sucursal() {
    ;}

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Ciudad getCiudad_Asociada() {
        return ciudad_Asociada;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad_Asociada(Ciudad ciudad_Asociada) {
        this.ciudad_Asociada = ciudad_Asociada;
    }

    
    
}


