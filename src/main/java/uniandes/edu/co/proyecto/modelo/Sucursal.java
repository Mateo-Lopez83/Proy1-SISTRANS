package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SUCURSALES")
public class Sucursal {

    @Id
    private int id;
    private String NOMBRE;
    private String DIRECCION;
    private String CIUDAD;
    private String TELEFONO;
    private List<Integer> BODEGA;
    private List <Producto> INVENTARIOS;
    public String getCIUDAD() {
        return CIUDAD;
    }

    public void setCIUDAD(String cIUDAD) {
        CIUDAD = cIUDAD;
    }

    public List<Integer> getBODEGA() {
        return BODEGA;
    }

    public void setBODEGA(List<Integer> bODEGA) {
        BODEGA = bODEGA;
    }

    public List<Producto> getINVENTARIOS() {
        return INVENTARIOS;
    }

    public void setINVENTARIOS(List<Producto> iNVENTARIOS) {
        INVENTARIOS = iNVENTARIOS;
    }

   

    

    public Sucursal(String NOMBRE, String DIRECCION, String TELEFONO, String ciudad_Asociada) {
        this.NOMBRE = NOMBRE;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
    }

    public Sucursal() {
    ;}

    public Integer getIdSucursal() {
        return id;
    }

    public String getNombre() {
        return NOMBRE;
    }

    public String getDireccion() {
        return DIRECCION;
    }

    public String getTelefono() {
        return TELEFONO;
    }


    public void setIdSucursal(Integer idSucursal) {
        this.id = idSucursal;
    }

    public void setNombre(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public void setDireccion(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public void setTelefono(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    
    
}


