CREATE SEQUENCE codBarras_sequence
  MINVALUE 0
  MAXVALUE 281474976710655
  START WITH 0
  INCREMENT BY 1
  CYCLE; 
CREATE SEQUENCE bodega_sequence
  MINVALUE 0
  MAXVALUE 1000000000
  START WITH 1
  INCREMENT BY 1
  CYCLE; 
CREATE SEQUENCE sucursal_sequence
  MINVALUE 0
  MAXVALUE 1000000000
  START WITH 1
  INCREMENT BY 1
  CYCLE; 
CREATE SEQUENCE ciudad_sequence
  MINVALUE 0
  MAXVALUE 1000000000
  START WITH 1
  INCREMENT BY 1
  CYCLE; 
CREATE SEQUENCE categoria_sequence
  MINVALUE 0
  MAXVALUE 1000000000
  START WITH 1
  INCREMENT BY 1
  CYCLE; 
CREATE SEQUENCE orden_sequence
  MINVALUE 0
  MAXVALUE 1000000000
  START WITH 1
  INCREMENT BY 1
  CYCLE; 
CREATE SEQUENCE recepcion_sequence
  MINVALUE 0
  MAXVALUE 1000000000
  START WITH 1
  INCREMENT BY 1
  CYCLE; 
  
  
CREATE TABLE sucursales
    (idsucursal NUMBER,
    nombre VARCHAR2 (255 BYTE) NOT NULL,
    direccion VARCHAR2 (255 BYTE) NOT NULL,
    telefono NUMBER NOT NULL,
    CONSTRAINT unique_direccion UNIQUE (direccion),
    CONSTRAINT tel_len_suc CHECK (LENGTH(telefono)=10),
    CONSTRAINT SUC_PK PRIMARY KEY(idsucursal));

CREATE TABLE bodegas
    (id NUMBER,
    nombre VARCHAR2 (255 BYTE) NOT NULL,
    tamanio NUMBER NOT NULL,
    idsucursal NUMBER NOT NULL,
    CONSTRAINT fk_bodega_sucursal FOREIGN KEY (idsucursal)
        REFERENCES sucursales (idsucursal),
    CONSTRAINT BODEGA_PK PRIMARY KEY(ID));
    
CREATE TABLE ciudades
    (idciudad NUMBER,
    nombre VARCHAR2 (255 BYTE) NOT NULL,
    CONSTRAINT CIUDAD_PK PRIMARY KEY (idciudad),
    CONSTRAINT unique_ciudad UNIQUE (nombre));
    
CREATE TABLE categorias
    (codigo NUMBER,
    nombre VARCHAR2 (255 BYTE) NOT NULL,
    descripcion VARCHAR2(255 BYTE) NOT NULL,
    caracteristicas VARCHAR2 (255 BYTE) NOT NULL,
    CONSTRAINT CATEGORIA_PK PRIMARY KEY (codigo));
    
CREATE TABLE proveedores
    (nit NUMBER,
    nombre VARCHAR2 (255 BYTE) NOT NULL,
    direccion VARCHAR2 ( 255 BYTE) NOT NULL,
    nombrecontacto VARCHAR2 (255 BYTE) NOT NULL,
    telcontacto NUMBER NOT NULL,
    CONSTRAINT telefono_leng CHECK (LENGTH(telcontacto)=10),
    CONSTRAINT PROV_PK PRIMARY KEY (NIT),
    CONSTRAINT unique_proveedor UNIQUE (nombre));

CREATE TABLE ordenes
    (id NUMBER,
    fecha_entrega DATE NOT NULL,
    estado VARCHAR2(255 BYTE) NOT NULL,
    sucursal_envio NUMBER NOT NULL,
    nit_proveedor NUMBER NOT NULL,
    CONSTRAINT FK_orden_suc FOREIGN KEY (sucursal_envio)
        REFERENCES sucursales(idsucursal),
    CONSTRAINT FK_orden_proveedor FOREIGN KEY (nit_proveedor)
        REFERENCES proveedores(nit),
    CONSTRAINT ESTADOS_ORDEN CHECK (estado IN ('vigente', 'entregada', 'anulada')),
    CONSTRAINT ORDEN_PK PRIMARY KEY (id));

CREATE TABLE productos
    (codbarras NUMBER,
    nombre VARCHAR2 (255 BYTE) NOT NULL,
    precioventa NUMBER NOT NULL,
    presentacion VARCHAR2 (255 BYTE) NOT NULL,
    unidad_medida VARCHAR2 (10) NOT NULL,
    esp_empacado VARCHAR2 (255 BYTE) NOT NULL,
    fecha_exp DATE,
    categoria NUMBER NOT NULL,
    CONSTRAINT PK_PRODUCTO PRIMARY KEY (codbarras),
    CONSTRAINT FK_CATEGORIA FOREIGN KEY (categoria)
        REFERENCES categorias(codigo),
    CONSTRAINT CHECKPRECIO CHECK(precioventa >0));

ALTER TABLE categorias
    ADD CONSTRAINT tipo_categoria CHECK (nombre IN ('perecedero','noperecedero','aseo','congelados','prendas','muebles','herramientas','electrodomesticos'))
    ADD CONSTRAINT unique_tipo UNIQUE (nombre);

CREATE TABLE orden_producto
    (idorden NUMBER,
    idproducto NUMBER,
    cantidad_producto NUMBER NOT NULL,
    preciobodega NUMBER NOT NULL,
    CONSTRAINT FK_IDORDEN FOREIGN KEY (idorden)
        REFERENCES ordenes(id),
    CONSTRAINT FK_IDPRODUCTO FOREIGN KEY (idproducto)
        REFERENCES productos(codbarras),
    CONSTRAINT PK_ORDEN_PRODUCTO PRIMARY KEY (idorden,idproducto),
    CONSTRAINT CHECKCANTIDAD CHECK(cantidad_producto >0));
    
CREATE TABLE recepcion_producto
    (identificador NUMBER,
    producto NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    bodega NUMBER NOT NULL,
    proveedor NUMBER NOT NULL,
    costo_grupo_producto NUMBER NOT NULL,
    CONSTRAINT PK_RECEPCION PRIMARY KEY (identificador),
    CONSTRAINT MAYOR0CANTIDAD CHECK (cantidad>0),
    CONSTRAINT MAYOR0COSTO CHECK (costo_grupo_producto>0),
    CONSTRAINT FK_PRODUCTO FOREIGN KEY (producto)
        REFERENCES productos(codbarras),
    CONSTRAINT FK_BODEGA FOREIGN KEY (bodega)
        REFERENCES bodegas(id),
    CONSTRAINT FK_PROVEEDOR FOREIGN KEY (proveedor)
        REFERENCES proveedores(nit));

CREATE TABLE inventarios
    (id NUMBER,
    codigobarras NUMBER NOT NULL,
    idbodega NUMBER NOT NULL,
    costo_grupo_producto NUMBER NOT NULL,
    cantidad_ocupada NUMBER NOT NULL,
    minimo_recompra NUMBER NOT NULL,
    capacidad_max NUMBER NOT NULL,
    promedio_precio NUMBER,
    CONSTRAINT PK_INV PRIMARY KEY (id),
    CONSTRAINT FK_CODBARRAS FOREIGN KEY (codigobarras)
        REFERENCES productos(codbarras),
    CONSTRAINT FK_IDBODEGA FOREIGN KEY (idbodega)
        REFERENCES bodegas(id),
    CONSTRAINT COSTO_PRODCHECK CHECK(costo_grupo_producto>0),
    CONSTRAINT CANTIDAD_OCUPADACHECK CHECK(cantidad_ocupada>=0 AND cantidad_ocupada<capacidad_max),
    CONSTRAINT MIN_RECOMPRACHECK CHECK(minimo_recompra>=0),
    CONSTRAINT CAP_MAXCHECK CHECK(capacidad_max>0));

ALTER TABLE bodegas
    ADD CONSTRAINT TAMANIO_MAYOR_0 CHECK (tamanio >0);
        
ALTER TABLE sucursales
    ADD (ciudad_asociada NUMBER NOT NULL)
    ADD CONSTRAINT FK_CIUDAD FOREIGN KEY (ciudad_asociada)
        REFERENCES ciudades (idciudad);

CREATE TABLE inventarios
    (codigobarras NUMBER,
    idbodega NUMBER,
    costo_grupo_producto NUMBER NOT NULL,
    cantidad_ocupada NUMBER NOT NULL,
    minimo_recompra NUMBER NOT NULL,
    capacidad_max NUMBER NOT NULL,
    promedio_precio NUMBER,
    CONSTRAINT FK_CODBARRAS FOREIGN KEY (codigobarras)
        REFERENCES productos(codbarras),
    CONSTRAINT FK_IDBODEGA FOREIGN KEY (idbodega)
        REFERENCES bodegas(id),
    CONSTRAINT PK_INVENTARIO PRIMARY KEY(codigobarras,idbodega),
    CONSTRAINT COSTO_PRODCHECK CHECK(costo_grupo_producto>0),
    CONSTRAINT CANTIDAD_OCUPADACHECK CHECK(cantidad_ocupada>=0 AND cantidad_ocupada<capacidad_max),
    CONSTRAINT MIN_RECOMPRACHECK CHECK(minimo_recompra>=0),
    CONSTRAINT CAP_MAXCHECK CHECK(capacidad_max>0));
 
DESC bodegas;
DESC sucursales;


INSERT INTO CIUDADES(idciudad, NOMBRE) VALUES(ciudad_sequence.nextVal, 'Pereira');

SELECT * FROM SUCURSALES;

INSERT INTO SUCURSALES (idsucursal, nombre, direccion, telefono, ciudad_asociada) VALUES(sucursal_sequence.nextVal, 'Floresta2', 'Cra 7', '1234567890', 1);
INSERT INTO BODEGAS (id, nombre, tamanio, idsucursal) VALUES (bodega_sequence.nextVal, 'LaBo Dega', 34, 1);
ALTER TABLE sucursales
    MODIFY TELEFONO VARCHAR2(10);

INSERT INTO PROVEEDORES (nit, nombre, direccion, nombrecontacto, telcontacto) VALUES (1234, 'Los pollos hermanos', 'Cra 7', 'Walter', 1234567890);
SELECT * FROM PROVEEDORES;
SELECT * FROM PROVEEDORES;
ALTER TABLE proveedores
    DROP CONSTRAINT TELEFONO_LENG;
ALTER TABLE PROVEEDORES
    MODIFY TELCONTACTO VARCHAR2(10);
