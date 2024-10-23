package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;



@Entity
@Table(name = "ingresoproducto")
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idingreso")
    private int idIngreso;
    @Column(name = "fechaingreso")
    private LocalDate fechaIngreso;
    @ManyToOne
    @JoinColumn(name = "bodega", referencedColumnName = "ID")
    private Bodega idBodega;
    @OneToOne
    @JoinColumn(name="ordencompra", referencedColumnName = "ID")
    private Orden ordencompra;
}
