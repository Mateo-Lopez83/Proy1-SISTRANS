package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "ID")
    private Integer Id;

    @Column(name = "FECHA_ENTREGA")
    private LocalDate fechaEntrega;

    @Column(name = "FECHA_CREACION")
    private LocalDate fechaCreacion;

    @Column(name = "ESTADO")
    private String Estado;

    @Column(name = "SUCURSAL_ENVIO")
    private Integer sucursalEnvio;

    @Column(name = "NIT_PROVEEDOR")
    private Integer Proveedor;

    public Orden(LocalDate fechaEntrega, LocalDate fechaCreacion, 
                String Estado, Integer sucursalEnvio, 
                Integer Proveedor) {
        this.fechaEntrega = fechaEntrega;
        this.fechaCreacion = fechaCreacion;
        this.Estado = Estado;
        this.sucursalEnvio = sucursalEnvio;
        this.Proveedor = Proveedor;
    }

    public Orden() {
        ;
    }

    public Integer getIdentificador() {
        return Id;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
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
        Id = identificador;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
