package uniandes.edu.co.proyecto.modelo;


public class ProductoExtra {
    private Integer codBarras;
    private int cantidad;
    private int precioBodega;

    public ProductoExtra(Integer codBarras, int cantidad, int precioBodega) {
        this.codBarras = codBarras;
        this.cantidad = cantidad;
        this.precioBodega = precioBodega;
    }

    public Integer getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(Integer codBarras) {
        this.codBarras = codBarras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioBodega() {
        return precioBodega;
    }

    public void setPrecioBodega(int precioBodega) {
        this.precioBodega = precioBodega;
    }
}
