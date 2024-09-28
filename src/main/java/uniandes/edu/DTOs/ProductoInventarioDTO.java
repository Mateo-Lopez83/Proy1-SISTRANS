package uniandes.edu.DTOs;

public class ProductoInventarioDTO {
    private String codBarras;
    private Long idBodega;
    private Long cantidadTotal;
    private Long minimoRecompra;
    private Long capacidadMax;
    private Double promedio;

    // Constructor
    public ProductoInventarioDTO(String codBarras, Long idBodega, Long cantidadTotal, Long minimoRecompra, Long capacidadMax, Double promedio) {
        this.codBarras = codBarras;
        this.idBodega = idBodega;
        this.cantidadTotal = cantidadTotal;
        this.minimoRecompra = minimoRecompra;
        this.capacidadMax = capacidadMax;
        this.promedio = promedio;
    }

    // Getters
    public String getCodBarras() {
        return codBarras;
    }

    public Long getIdBodega() {
        return idBodega;
    }

    public Long getCantidadTotal() {
        return cantidadTotal;
    }

    public Long getMinimoRecompra() {
        return minimoRecompra;
    }

    public Long getCapacidadMax() {
        return capacidadMax;
    }

    public Double getPromedio() {
        return promedio;
    }

    // Setters
    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public void setCantidadTotal(Long cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public void setMinimoRecompra(Long minimoRecompra) {
        this.minimoRecompra = minimoRecompra;
    }

    public void setCapacidadMax(Long capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    // Optional: toString method for easier debugging
    @Override
    public String toString() {
        return "ProductoInventarioDTO{" +
                "codBarras='" + codBarras + '\'' +
                ", idBodega=" + idBodega +
                ", cantidadTotal=" + cantidadTotal +
                ", minimoRecompra=" + minimoRecompra +
                ", capacidadMax=" + capacidadMax +
                ", promedio=" + promedio +
                '}';
    }
}
