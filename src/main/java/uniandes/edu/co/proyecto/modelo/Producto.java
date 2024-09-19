package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CodBarras;
    private String nombre;
    private int precioVenta;
    private String presentacion;
    private String unidadMedida;
    private String espEmpacado;
    private String fechaExp;
    @ManyToOne
    @JoinColumn(name="CATEGORIA", referencedColumnName = "codigo")
    private Categoria categoria;

    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String espEmpacado,
            String fechaExp, Categoria categoria) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.unidadMedida = unidadMedida;
        this.espEmpacado = espEmpacado;
        this.fechaExp = fechaExp;
        this.categoria = categoria;
    }

    //Si no tiene fecha de expiracion
    public Producto(String nombre, int precioVenta, String presentacion, String unidadMedida, String espEmpacado, Categoria categoria) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
        this.unidadMedida = unidadMedida;
        this.espEmpacado = espEmpacado;
        this.categoria = categoria;
    }

    public Producto(){;}

    public Integer getCodBarras() {
        return CodBarras;
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

    public void setEspEmpacado(String espEmpacado) {
        this.espEmpacado = espEmpacado;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    

    
}
