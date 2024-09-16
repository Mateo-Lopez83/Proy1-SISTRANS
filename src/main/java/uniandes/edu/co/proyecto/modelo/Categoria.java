package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    private String nombre;
    private String direccion;
    private String descripcion;
    private String Caracteristicas_almacenamiento;

    public Categoria(String nombre, String direccion, String descripcion, String Caracteristicas_almacenamiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion ;
        this.Caracteristicas_almacenamiento = Caracteristicas_almacenamiento;
    }

    public Categoria() {
    ;}

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public String getCaracteristicas_almacenamiento() {
        return Caracteristicas_almacenamiento;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCaracteristicas_almacenamiento(String Caracteristicas_almacenamiento) {
        this.Caracteristicas_almacenamiento = Caracteristicas_almacenamiento;
    }  

    
    
}
