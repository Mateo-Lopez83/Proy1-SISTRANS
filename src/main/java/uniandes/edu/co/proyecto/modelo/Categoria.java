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
    private String descripcion;
    private String caracteristicas;

    public Categoria(String nombre,  String descripcion, String Caracteristicas) {
        this.nombre = nombre;
        this.descripcion = descripcion ;
        this.caracteristicas = Caracteristicas;
    }

    public Categoria() {
    ;}

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCaracteristicas_almacenamiento(String Caracteristicas) {
        this.caracteristicas = Caracteristicas;
    }  

    
    
}
