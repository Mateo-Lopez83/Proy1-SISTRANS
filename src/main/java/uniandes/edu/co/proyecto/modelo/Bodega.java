package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection = "BODEGAS")
public class Bodega{
    @Id
    @JsonProperty("_id") 
    private int id;

    @Field("NOMBRE")
    @JsonProperty("NOMBRE")
    private String NOMBRE;
    
    @Field("TAMANIO")
    @JsonProperty("TAMANIO")
    private int TAMANIO;


    public Bodega() {;
    }

    public Bodega(String nombre, Integer tamanio, Sucursal Sucursal_Asociada) {
        this.NOMBRE = nombre;
        this.TAMANIO = tamanio;
    }

    

    public Integer getID() {
        return id;
    }

    public void setID(Integer iD) {
        id = iD;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String nOMBRE) {
        NOMBRE = nOMBRE;
    }

    public Integer getTAMANIO() {
        return TAMANIO;
    }

    public void setTAMANIO(Integer tAMANIO) {
        TAMANIO = tAMANIO;
    }
    
}
