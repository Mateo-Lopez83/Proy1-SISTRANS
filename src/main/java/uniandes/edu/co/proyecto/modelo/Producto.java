package uniandes.edu.co.proyecto.modelo;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "PRODUCTOS")
public class Producto {
   
    @Id
    private int id;
    @Field("CODBARRAS")
    private Integer CodBarras;
    @Field("NOMBRE")
    private String nombre;
    @Field("PRECIOVENTA")
    private int precioVenta;
    @Field("PRESENTACION")
    private String presentacion;
    @Field("UNIDAD_MEDIDA")
    private String unidadMedida;
    @Field("ESP_EMPACADO")
    private String espEmpacado;
    @Field("FECHA_EXP")
    private LocalDate fechaExp;
    @Field("CATEGORIA")
    private int categoria;

    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String espEmpacado,
                    LocalDate fechaExp, int categoria) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.unidadMedida = unidadMedida;
        this.espEmpacado = espEmpacado;
        this.fechaExp = fechaExp;
        this.categoria = categoria;
    }

    // Si no tiene fecha de expiracion
    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String espEmpacado, int categoria) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.unidadMedida = unidadMedida;
        this.espEmpacado = espEmpacado;
        this.fechaExp = null;
        this.categoria = categoria;
    }

    public Producto() {}

    public Integer getCodBarras() {
        return CodBarras;
    }

    public void setCodBarras(Integer codBarras) {
        CodBarras = codBarras;
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
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getEspEmpacado() {
        return espEmpacado;
    }

    public void setEspEmpacado(String esp_Empacado) {
        this.espEmpacado = esp_Empacado;
    }

    public LocalDate getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(LocalDate fechaExp) {
        this.fechaExp = fechaExp;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}