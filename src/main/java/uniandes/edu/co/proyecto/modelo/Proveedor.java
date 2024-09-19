package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    private Integer NIT;
    private String nombre;
    private String direccion;
    private String nombreContacto;
    private String telefonoContacto;

    public Proveedor(Integer NIT, String nombre, String direccion, String nombreContacto, String telefonoContacto) {
        this.NIT = NIT;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
    }

    public Proveedor() {
        ;
    }

    public Integer getNIT() {
        return NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setNIT(Integer nIT) {
        NIT = nIT;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    
    
}
