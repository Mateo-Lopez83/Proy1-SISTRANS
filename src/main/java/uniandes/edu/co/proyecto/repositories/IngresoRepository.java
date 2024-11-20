package uniandes.edu.co.proyecto.repositories;
/*
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ingreso;

public interface IngresoRepository extends JpaRepository<Ingreso,Integer>{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO INGRESOPRODUCTO (IDINGRESO, FECHAINGRESO, BODEGA, ORDENCOMPRA) VALUES (ingreso_sequence.nextVal, SYSDATE, :bodega, :orden)", nativeQuery = true)
    void insertarIngreso(@Param("bodega") long bodega, @Param("orden") long orden);

    
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "SELECT INGRESOPRODUCTO.*, ORDENES.SUCURSAL_ENVIO, PROVEEDORES.NIT,PROVEEDORES.nombre as nombre_proveedor"+
                ",PRODUCTOS.NOMBRE as nombre_producto, ORDEN_PRODUCTO.CANTIDAD_PRODUCTO, ORDEN_PRODUCTO.PRECIOBODEGA" + 
                "FROM INGRESOPRODUCTO" + //
                "INNER JOIN ORDENES ON ORDENES.ID = INGRESOPRODUCTO.ORDENCOMPRA" + 
                "INNER JOIN ORDEN_PRODUCTO ON ORDEN_PRODUCTO.IDORDEN = ORDENES.ID" + 
                "INNER JOIN PROVEEDORES ON PROVEEDORES.NIT = ORDENES.NIT_PROVEEDOR " + 
                "INNER JOIN PRODUCTOS ON ORDEN_PRODUCTO.IDPRODUCTO = PRODUCTOS.CODBARRAS" + 
                "WHERE INGRESOPRODUCTO.IDINGRESO = :idIngreso;")
    void obtenerinformacionIngreso(@Param("idIngreso") long idIngreso);

    
    @Query(value = "SELECT PRODUCTOS.CODBARRAS,ORDEN_PRODUCTO.CANTIDAD_PRODUCTO, ORDEN_PRODUCTO.PRECIOBODEGA" +
    "FROM INGRESOPRODUCTO" + //
                "INNER JOIN ORDENES ON ORDENES.ID = INGRESOPRODUCTO.ORDENCOMPRA" +
                "INNER JOIN ORDEN_PRODUCTO ON ORDEN_PRODUCTO.IDORDEN = ORDENES.ID" + 
                "INNER JOIN PROVEEDORES ON PROVEEDORES.NIT = ORDENES.NIT_PROVEEDOR " + 
                "INNER JOIN PRODUCTOS ON ORDEN_PRODUCTO.IDPRODUCTO = PRODUCTOS.CODBARRAS" + 
                "WHERE INGRESOPRODUCTO.IDINGRESO = :idIngreso;")
    List<Object[]> obtenerinfoRF10Ingresos(@Param("idIngreso") long idIngreso);


    
}  */