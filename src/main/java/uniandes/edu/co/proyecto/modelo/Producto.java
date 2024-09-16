package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
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
    @JoinColumn(name="CATEGORIA", referencedColumnName = "CODIGO")
    private Categoria categoria;

}
