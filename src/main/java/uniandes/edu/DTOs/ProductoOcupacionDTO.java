package uniandes.edu.DTOs;

public class ProductoOcupacionDTO {
    private Long codigobarras;
    private String nombre;
    private Long idBodega;
    private Double ocupacion;

    public ProductoOcupacionDTO(Long codigobarras, String nombre, Long idBodega, Double ocupacion) {
        this.codigobarras = codigobarras;
        this.nombre = nombre;
        this.idBodega = idBodega;
        this.ocupacion = ocupacion;
    }

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

    public Long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public Double getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Double ocupacion) {
        this.ocupacion = ocupacion;
    }
}

