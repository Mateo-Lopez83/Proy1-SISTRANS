package uniandes.edu.DTOs;

public class ProductoInventarioDTO {
    private Long codigobarras;
    private String nombre;
    private Long cantidadOcupada;
    private Long minimoRecompra;
    private Long capacidadMax;
    private Double avgCosto;

    // Constructor
    public ProductoInventarioDTO(Long codigobarras, String nombre, Long cantidadOcupada, 
                                  Long minimoRecompra, Long capacidadMax, Double avgCosto) {
        this.codigobarras = codigobarras;
        this.nombre = nombre;
        this.cantidadOcupada = cantidadOcupada;
        this.minimoRecompra = minimoRecompra;
        this.capacidadMax = capacidadMax;
        this.avgCosto = avgCosto;
    }

    // Getters and Setters (optional, but recommended)
    public Long getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(Long codigobarras) {
        this.codigobarras = codigobarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCantidadOcupada() {
        return cantidadOcupada;
    }

    public void setCantidadOcupada(Long cantidadOcupada) {
        this.cantidadOcupada = cantidadOcupada;
    }

    public Long getMinimoRecompra() {
        return minimoRecompra;
    }

    public void setMinimoRecompra(Long minimoRecompra) {
        this.minimoRecompra = minimoRecompra;
    }

    public Long getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(Long capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public Double getAvgCosto() {
        return avgCosto;
    }

    public void setAvgCosto(Double avgCosto) {
        this.avgCosto = avgCosto;
    }
}

