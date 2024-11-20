package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ORDENCOMPRA")
public class Orden {
    @Id
    private int id;
    @Field("IDSUCURSAL")
    private int sucursalEnvio;

    @Field("IDPROVEEDOR")
    private int Proveedor;

    @Field("NOMBRE")
    private LocalDate fechaEntrega;

    @Field("NOMBRE")
    private LocalDate fechaCreacion;

    @Field("NOMBRE")
    private String Estado;

    @Field("TELEFONO")
    private String telefono;

    //TODO: cambiar esto a una lista de DetalleProductos
    @Field("DETALLEPRODUCTO")
    private List<Producto> productos;

    
    //girl what the HELL IS THIS
    //@Transient
    //private List<ProductoExtra> productosExtra = new ArrayList<ProductoExtra>();

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

    public int getIdentificador() {
        return id;
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

    public void setIdentificador(int identificador) {
        this.id = identificador;
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

    /*public List<ProductoExtra> getProductosExtra() {
        return productosExtra;
    }*/
    
}
