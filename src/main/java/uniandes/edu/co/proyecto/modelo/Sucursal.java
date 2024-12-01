package uniandes.edu.co.proyecto.modelo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection = "SUCURSALES")
public class Sucursal {

    @Id
    @JsonProperty("_id") 
    private int id;

    @Field("NOMBRE")
    @JsonProperty("NOMBRE")
    private String nombre;

    @Field("DIRECCION")
    @JsonProperty("DIRECCION")
    private String direccion;

    @Field("CIUDAD")
    @JsonProperty("CIUDAD") 
    private String ciudad;

    @Field("TELEFONO")
    @JsonProperty("TELEFONO") 
    private int telefono;

    @Field("BODEGA")
    @JsonProperty("BODEGA") 
    private List<Integer> bodega;

    @Field("INVENTARIOS")
    @JsonProperty("INVENTARIOS")
    private List<Inventario> inventarios;

    public static class Inventario {
        @Id
        @JsonProperty("_id")
        private int id;
        
        @Field("CODIDIGOBARRAS")
        @JsonProperty("CODIDIGOBARRAS")
        private int codigoBarras;

        @Field("IDBODEGA")
        @JsonProperty("IDBODEGA")
        private int idBodega;

        @Field("CANTIDAD_OCUPADA")
        @JsonProperty("CANTIDAD_OCUPADA")
        private int cantidadOcupada;

        @Field("COSTO_GRUPO_PRODUCTO")
        @JsonProperty("COSTO_GRUPO_PRODUCTO")
        private int costoGrupoProducto;
        
        @Field("MINIMO_RECOMPRA")
        @JsonProperty("MINIMO_RECOMPRA")
        private int minimoRecompra;

        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getCodigoBarras() {
            return codigoBarras;
        }
        public void setCodigoBarras(Integer codigoBarras) {
            this.codigoBarras = codigoBarras;
        }
        public Integer getIdBodega() {
            return idBodega;
        }
        public void setIdBodega(Integer idBodega) {
            this.idBodega = idBodega;
        }
        public Integer getCantidadOcupada() {
            return cantidadOcupada;
        }
        public void setCantidadOcupada(Integer cantidadOcupada) {
            this.cantidadOcupada = cantidadOcupada;
        }
        public Integer getCostoGrupoProducto() {
            return costoGrupoProducto;
        }
        public void setCostoGrupoProducto(Integer costoGrupoProducto) {
            this.costoGrupoProducto = costoGrupoProducto;
        }
        public Integer getMinimoRecompra() {
            return minimoRecompra;
        }
        public void setMinimoRecompra(Integer minimoRecompra) {
            this.minimoRecompra = minimoRecompra;
        }
    
        
    }
    public String getciudad() {
        return ciudad;
    }

    public void setciudad(String cIUDAD) {
        ciudad = cIUDAD;
    }

    public List<Integer> getbodega() {
        return bodega;
    }

    public void setbodega(List<Integer> bODEGA) {
        bodega = bODEGA;
    }

    public List<Inventario> getinventarios() {
        return inventarios;
    }

    public void setinventarios(List<Inventario> iNVENTARIOS) {
        inventarios = iNVENTARIOS;
    }

   

    

    public Sucursal(String nombre, String direccion, int telefono, String ciudad_Asociada) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Sucursal() {
    ;}

    public Integer getIdSucursal() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }


    public void setIdSucursal(Integer idSucursal) {
        this.id = idSucursal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

   

    
}


