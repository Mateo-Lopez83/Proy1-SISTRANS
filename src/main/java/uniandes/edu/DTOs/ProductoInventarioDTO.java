package uniandes.edu.DTOs;

public class ProductoInventarioDTO {
    private Long CODIGOBARRAS;
    private String nombre;
    private Long idBodega;
    private Long cantidad_Total;
    private Long minimo_Recompra;
    private Long capacidad_Max;
    private Double promedio;

    // Constructor
    public ProductoInventarioDTO(Long CODIGOBARRAS,String nombre, Long idBodega, Long cantidad_Total, Long minimo_Recompra, Long capacidad_Max, Double promedio) {
        this.CODIGOBARRAS = CODIGOBARRAS;
        this.nombre=nombre;
        this.idBodega = idBodega;
        this.cantidad_Total = cantidad_Total;
        this.minimo_Recompra = minimo_Recompra;
        this.capacidad_Max = capacidad_Max;
        this.promedio = promedio;
    }

    // Getters
    public Long getCODIGOBARRAS() {
        return CODIGOBARRAS;
    }

    public Long getIdBodega() {
        return idBodega;
    }
    public String getNombre() {
        return nombre;
    }

    public Long getcantidad_Total() {
        return cantidad_Total;
    }

    public Long getminimo_Recompra() {
        return minimo_Recompra;
    }

    public Long getcapacidad_Max() {
        return capacidad_Max;
    }

    public Double getPromedio() {
        return promedio;
    }

    // Setters
    public void setCODIGOBARRAS(Long CODIGOBARRAS) {
        this.CODIGOBARRAS = CODIGOBARRAS;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public void setcantidad_Total(Long cantidad_Total) {
        this.cantidad_Total = cantidad_Total;
    }

    public void setminimo_Recompra(Long minimo_Recompra) {
        this.minimo_Recompra = minimo_Recompra;
    }

    public void setcapacidad_Max(Long capacidad_Max) {
        this.capacidad_Max = capacidad_Max;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    // Optional: toString method for easier debugging
    @Override
    public String toString() {
        return "ProductoInventarioDTO{" +
                "CODIGOBARRAS='" + CODIGOBARRAS + '\'' +
                ", idBodega=" + idBodega +
                ", cantidad_Total=" + cantidad_Total +
                ", minimo_Recompra=" + minimo_Recompra +
                ", capacidad_Max=" + capacidad_Max +
                ", promedio=" + promedio +
                '}';
    }
}
