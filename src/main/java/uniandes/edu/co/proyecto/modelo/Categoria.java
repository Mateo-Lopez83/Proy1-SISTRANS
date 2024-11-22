package uniandes.edu.co.proyecto.modelo;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "CATEGORIAS")
public class Categoria {

    @Id
    @JsonProperty("_id")
    private int id;
    
    @Field("NOMBRE")
    @JsonProperty("NOMBRE")
    private String nombre;

    @Field("DESCRIPCION")
    @JsonProperty("DESCRIPCION")
    private String descripcion;

    @Field("CARACTERISTICAS")
    @JsonProperty("CARACTERISTICAS") 
    private String caracteristicas;

    public Categoria(String nombre,  String descripcion, String Caracteristicas) {
        this.nombre = nombre;
        this.descripcion = descripcion ;
        this.caracteristicas = Caracteristicas;
    }

    public Categoria() {
    ;}

    
    public String getNombre() {
        return nombre;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCaracteristicas_almacenamiento(String Caracteristicas) {
        this.caracteristicas = Caracteristicas;
    }  

    
    
}
