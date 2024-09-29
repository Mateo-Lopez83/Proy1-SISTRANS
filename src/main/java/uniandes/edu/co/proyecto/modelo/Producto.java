package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCTOS")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CODBARRAS")
    private Integer CodBarras;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PRECIOVENTA")
    private int precioVenta;

    @Column(name = "PRESENTACION")
    private String presentacion;

    @Column(name = "UNIDAD_MEDIDA")
    private String unidadMedida;

    @Column(name = "ESP_EMPACADO")
    private String espEmpacado;

    @Column(name = "FECHA_EXP")
    private LocalDate fechaExp;

    @ManyToOne
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "CODIGO")
    private Categoria categoria;

    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String espEmpacado,
                    LocalDate fechaExp, Categoria categoria) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.unidadMedida = unidadMedida;
        this.espEmpacado = espEmpacado;
        this.fechaExp = fechaExp;
        this.categoria = categoria;
    }

    // Si no tiene fecha de expiracion
    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String espEmpacado, Categoria categoria) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}