package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDEN")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Identificador;
    private String fechaEntrega;
    private String Estado;
    private Integer sucursalEnvio;
    private Integer Proveedor;

    public Orden(String fechaEntrega, String Estado, Integer sucursalEnvio, Integer Proveedor) {
        this.fechaEntrega = fechaEntrega;
        this.Estado = Estado;
        this.sucursalEnvio = sucursalEnvio;
        this.Proveedor = Proveedor;
    }

    public Orden() {
        ;
    }

    public Integer getIdentificador() {
        return Identificador;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public String getEstado() {
        return Estado;
    }

    public Integer getSucursalEnvio() {
        return sucursalEnvio;
    }

    public Integer getProveedor() {
        return Proveedor;
    }

    public void setIdentificador(Integer identificador) {
        Identificador = identificador;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setSucursalEnvio(Integer sucursalEnvio) {
        this.sucursalEnvio = sucursalEnvio;
    }

    public void setProveedor(Integer proveedor) {
        Proveedor = proveedor;
    }

    
    
}
