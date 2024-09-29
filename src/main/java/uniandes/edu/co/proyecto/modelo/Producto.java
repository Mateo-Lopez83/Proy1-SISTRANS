package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTOS")
public class Producto implements Serializable{
    @Id
    @Column(name = "CODBARRAS")
    private Integer CODBARRAS;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PRECIOVENTA")
    private int precioVenta;
    @Column(name = "PRESENTACION")
    private String presentacion;
    @Column(name = "UNIDAD_MEDIDA")
    private String unidad_Medida;
    @Column(name = "ESP_EMPACADO")
    private String esp_Empacado;
    @Column(name = "FECHA_EXP")
    private String fecha_Exp;
    @ManyToOne
    @JoinColumn(name="CATEGORIAS", referencedColumnName = "CODIGO")
    private Categoria categoria;

    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String esp_Empacado,
            String fechaExp, Categoria categoria) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.unidad_Medida = unidadMedida;
        this.esp_Empacado = esp_Empacado;
        this.fecha_Exp = fechaExp;
        this.categoria = categoria;
    }

    //Si no tiene fecha de expiracion
    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String esp_Empacado, Categoria categoria) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.unidad_Medida = unidadMedida;
        this.esp_Empacado = esp_Empacado;
        this.categoria = categoria;
    }

    public Producto(){;}

    public Integer getCodBarras() {
        return CODBARRAS;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getUnidadMedida() {
        return unidad_Medida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidad_Medida = unidadMedida;
    }

    public String getesp_Empacado() {
        return esp_Empacado;
    }

    public void setesp_Empacado(String esp_Empacado) {
        this.esp_Empacado = esp_Empacado;
    }

    public String getFechaExp() {
        return fecha_Exp;
    }

    public void setFechaExp(String fechaExp) {
        this.fecha_Exp = fechaExp;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    

    
}
