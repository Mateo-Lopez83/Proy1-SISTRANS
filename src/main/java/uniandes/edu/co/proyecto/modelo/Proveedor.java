package uniandes.edu.co.proyecto.modelo;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "PROVEEDORES")
public class Proveedor {

    @Id
    @JsonProperty("_id") 
    private int id;

    
    @Field("NIT")
    @JsonProperty("NIT")
    private int NIT;

    @Field("NOMBRE")
    @JsonProperty("NOMBRE")
    private String nombre;

    @Field("DIRECCION")
    @JsonProperty("DIRECCION")
    private String direccion;
    
    @Field("NOMBRECONTACTO")
    @JsonProperty("NOMBRECONTACTO")
    private String nombrecontacto;

    @Field("TELCONTACTO")
    @JsonProperty("TELCONTACTO")
    private int telcontacto;


    //TODO: crear una nueva clase para guardar los elementos de la lista llamad Recepcion
    @Field("RECEPCIONPRODUCTO")
    @JsonProperty("RECEPCIONPRODUCTO")
    private List<RECEPCIONPRODUCTO> recepcion_producto;

    public static class RECEPCIONPRODUCTO{
        @Id
        @JsonProperty("_id")
        private int id;

        @Field("IDPRODUCTO")
        @JsonProperty("IDPRODUCTO")
        private int idProducto;

        @Field("IDORDEN")
        @JsonProperty("IDORDEN")
        private int idOrden;

        @Field("IDBODEGA")
        @JsonProperty("IDBODEGA")
        private int idBodega;

        @Field("CANTIDADENTREGADA")
        @JsonProperty("CANTIDADENTREGADA")
        private int cantidadEntregada;

        @Field("COSTOGRUPO")
        @JsonProperty("COSTOGRUPO")
        private int costoGrupo;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getIdProducto() {
            return idProducto;
        }

        public void setIdProducto(Integer idProducto) {
            this.idProducto = idProducto;
        }

        public Integer getIdOrden() {
            return idOrden;
        }

        public void setIdOrden(Integer idOrden) {
            this.idOrden = idOrden;
        }

        public Integer getIdBodega() {
            return idBodega;
        }

        public void setIdBodega(Integer idBodega) {
            this.idBodega = idBodega;
        }

        public Integer getCantidadEntregada() {
            return cantidadEntregada;
        }

        public void setCantidadEntregada(Integer cantidadEntregada) {
            this.cantidadEntregada = cantidadEntregada;
        }

        public Integer getCostoGrupo() {
            return costoGrupo;
        }

        public void setCostoGrupo(Integer costoGrupo) {
            this.costoGrupo = costoGrupo;
        }

        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    



    public Proveedor(Integer NIT, String nombre, String direccion, String nombreContacto, int telefonoContacto, List<RECEPCIONPRODUCTO> recepcion_producto) {
        this.NIT = NIT;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombrecontacto = nombreContacto;
        this.telcontacto = telefonoContacto;
        this.recepcion_producto = recepcion_producto;
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
        return nombrecontacto;
    }

    public int getTelefonoContacto() {
        return telcontacto;
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
        this.nombrecontacto = nombreContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.telcontacto = telefonoContacto;
    }

    public List<RECEPCIONPRODUCTO> getRecepcion_producto() {
        return recepcion_producto;
    }

    public void setRecepcion_producto(List<RECEPCIONPRODUCTO> recepcion_producto) {
        this.recepcion_producto = recepcion_producto;
    }

    
    
}
