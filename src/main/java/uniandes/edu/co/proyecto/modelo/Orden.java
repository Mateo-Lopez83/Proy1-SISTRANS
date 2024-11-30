package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ORDENCOMPRA")
public class Orden {
    @Id
    @JsonProperty("_id")
    private int id;

    @Field("IDSUCURSAL")
    private int sucursalEnvio;

    @Field("IDPROVEEDOR")
    private int proveedor;

    @Field("FECHAENTREGA")
    private LocalDate fechaEntrega;

    @Field("FECHACREACION")
    private LocalDate fechaCreacion;

    @Field("ESTADO")
    private String estado;

    @Field("DETALLEPRODUCTO")
    private List<ProductoExtra> productosExtra;

    public static class ProductoExtra {
        
        @Id
        @JsonProperty("_id")
        private int id;

        @Field("CODIGOBARRASPROD")
        private int codigoBarras;

        @Field("CANTIDAD_PEDIDA")
        private int cantidad;

        @Field("PRECIOBODEGA")
        private int precioBodega;


        public int getCodigoBarras() {
            return codigoBarras;
        }

        public void setCodigoBarras(int codigoBarras) {
            this.codigoBarras = codigoBarras;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public int getPrecioBodega() {
            return precioBodega;
        }

        public void setPrecioBodega(int precioBodega) {
            this.precioBodega = precioBodega;
        }
    }
    
    public Orden(LocalDate fechaEntrega, LocalDate fechaCreacion, String estado, Integer sucursalEnvio, 
                int proveedor, List<ProductoExtra> productosExtra) {
        this.sucursalEnvio = sucursalEnvio;
        this.proveedor = proveedor;
        this.fechaEntrega = fechaEntrega;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.productosExtra = productosExtra;
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
        return estado;
    }

    public Integer getSucursalEnvio() {
        return sucursalEnvio;
    }

    public Integer getProveedor() {
        return proveedor;
    }


    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setSucursalEnvio(Integer sucursalEnvio) {
        this.sucursalEnvio = sucursalEnvio;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public List<ProductoExtra> getProductosExtra() {
        return productosExtra;
    }

    public void setProductosExtra(List<ProductoExtra> productosExtra) {
        this.productosExtra = productosExtra;
    }

}
