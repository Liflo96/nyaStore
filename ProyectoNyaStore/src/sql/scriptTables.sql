-- Table: public.articulo

-- DROP TABLE public.articulo;

CREATE TABLE public.articulo
(
  articuloid character(5) NOT NULL,
  nombre_serie character varying,
  descripcion character varying,
  tipoarticuloid_tipoarticulo character varying,
  preciocompra integer,
  precioventa integer,
  codigo_sucursal character(2),
  razonsocial_proveedor character varying,
  CONSTRAINT pk_articulo PRIMARY KEY (articuloid),
  CONSTRAINT fk_articulo_proveedor FOREIGN KEY (razonsocial_proveedor)
      REFERENCES public.proveedor (razonsocial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_articulo_serie FOREIGN KEY (nombre_serie)
      REFERENCES public.serie (nombre) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_articulo_sucursal FOREIGN KEY (codigo_sucursal)
      REFERENCES public.sucursal (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_articulo_tipoarticulo FOREIGN KEY (tipoarticuloid_tipoarticulo)
      REFERENCES public.tipoarticulo (tipoarticuloid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.articulo
  OWNER TO postgres;
-------------------
-- Table: public.articulosucursal

-- DROP TABLE public.articulosucursal;

CREATE TABLE public.articulosucursal
(
  articuloid_articulo character(5) NOT NULL,
  codigo_sucursal character(2) NOT NULL,
  cantidad integer,
  CONSTRAINT pk_articulosucursal PRIMARY KEY (articuloid_articulo, codigo_sucursal),
  CONSTRAINT fk_articulosucursal_articulo FOREIGN KEY (articuloid_articulo)
      REFERENCES public.articulo (articuloid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_articulosucursal_sucursal FOREIGN KEY (codigo_sucursal)
      REFERENCES public.sucursal (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.articulosucursal
  OWNER TO postgres;
-------------------
-- Table: public.cliente

-- DROP TABLE public.cliente;

CREATE TABLE public.cliente
(
  ci_personafisica character varying NOT NULL,
  CONSTRAINT pk_cliente PRIMARY KEY (ci_personafisica),
  CONSTRAINT fk_cliente_personafisica FOREIGN KEY (ci_personafisica)
      REFERENCES public.personafisica (ci) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cliente
  OWNER TO postgres;
-------------------
-- Table: public.empleado

-- DROP TABLE public.empleado;

CREATE TABLE public.empleado
(
  ci_personafisica character varying NOT NULL,
  gerente boolean,
  codigo_sucursal character varying,
  CONSTRAINT pk_empleado PRIMARY KEY (ci_personafisica),
  CONSTRAINT fk_empleado_sucursal FOREIGN KEY (codigo_sucursal)
      REFERENCES public.sucursal (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.empleado
  OWNER TO postgres;
-------------------
-- Table: public.factura

-- DROP TABLE public.factura;

CREATE TABLE public.factura
(
  nrofactura integer NOT NULL,
  fechaemision date,
  descripcion_mediopago character varying,
  codigo_sucursal character(2),
  importe integer,
  cantidad integer,
  CONSTRAINT pk_factura PRIMARY KEY (nrofactura),
  CONSTRAINT fk_factura_mediopago FOREIGN KEY (descripcion_mediopago)
      REFERENCES public.mediopago (descripcion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_factura_sucursal FOREIGN KEY (codigo_sucursal)
      REFERENCES public.sucursal (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.factura
  OWNER TO postgres;
-------------------
-- Table: public.facturacompra

-- DROP TABLE public.facturacompra;

CREATE TABLE public.facturacompra
(
  nrofactura_factura integer NOT NULL,
  ruc_proveedor character varying,
  CONSTRAINT pk_facturacompra PRIMARY KEY (nrofactura_factura),
  CONSTRAINT fk_facturacompra_factura FOREIGN KEY (nrofactura_factura)
      REFERENCES public.factura (nrofactura) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_facturacompra_proveedor FOREIGN KEY (ruc_proveedor)
      REFERENCES public.proveedor (razonsocial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.facturacompra
  OWNER TO postgres;
-------------------
-- Table: public.facturaventa

-- DROP TABLE public.facturaventa;

CREATE TABLE public.facturaventa
(
  nrofactura_factura integer NOT NULL,
  ci_cliente character varying,
  CONSTRAINT pk_facturaventa PRIMARY KEY (nrofactura_factura),
  CONSTRAINT fk_facturaventa_cliente FOREIGN KEY (ci_cliente)
      REFERENCES public.cliente (ci_personafisica) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT,
  CONSTRAINT fk_facturaventa_factura FOREIGN KEY (nrofactura_factura)
      REFERENCES public.factura (nrofactura) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.facturaventa
  OWNER TO postgres;
-------------------
-- Table: public.mediopago

-- DROP TABLE public.mediopago;

CREATE TABLE public.mediopago
(
  descripcion character varying NOT NULL,
  iva boolean,
  CONSTRAINT pk_mediopago PRIMARY KEY (descripcion)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.mediopago
  OWNER TO postgres;
-------------------
-- Table: public.persona

-- DROP TABLE public.persona;

CREATE TABLE public.persona
(
  personaid integer NOT NULL,
  direccion character varying,
  telefono character varying,
  email character varying,
  CONSTRAINT pk_persona PRIMARY KEY (personaid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.persona
  OWNER TO postgres;
-------------------
-- Table: public.personafisica

-- DROP TABLE public.personafisica;

CREATE TABLE public.personafisica
(
  personaid_persona integer,
  nombre character varying,
  apellido character varying,
  ci character varying NOT NULL,
  fechanacimiento date,
  sexo character varying,
  CONSTRAINT pk_personafisica PRIMARY KEY (ci),
  CONSTRAINT fk_personafisica_persona FOREIGN KEY (personaid_persona)
      REFERENCES public.persona (personaid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.personafisica
  OWNER TO postgres;
-------------------
-- Table: public.proveedor

-- DROP TABLE public.proveedor;

CREATE TABLE public.proveedor
(
  personaid_persona integer,
  razonsocial character varying NOT NULL,
  ruc character varying,
  CONSTRAINT pk_proveedor PRIMARY KEY (razonsocial),
  CONSTRAINT fk_proveedor_persona FOREIGN KEY (personaid_persona)
      REFERENCES public.persona (personaid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.proveedor
  OWNER TO postgres;
-------------------
-- Table: public.serie

-- DROP TABLE public.serie;

CREATE TABLE public.serie
(
  nombre character varying NOT NULL,
  CONSTRAINT pk_serie PRIMARY KEY (nombre)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.serie
  OWNER TO postgres;
-------------------
-- Table: public.sucursal

-- DROP TABLE public.sucursal;

CREATE TABLE public.sucursal
(
  codigo character varying NOT NULL,
  direccion character varying,
  telefono character varying,
  email character varying,
  ci_personafisica character varying,
  CONSTRAINT pk_sucursal PRIMARY KEY (codigo),
  CONSTRAINT fk_sucursal_empleado FOREIGN KEY (ci_personafisica)
      REFERENCES public.empleado (ci_personafisica) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.sucursal
  OWNER TO postgres;
-------------------
-- Table: public.tipoarticulo

-- DROP TABLE public.tipoarticulo;

CREATE TABLE public.tipoarticulo
(
  tipoarticuloid character(5) NOT NULL,
  descripcion character varying,
  CONSTRAINT pk_tipoarticulo PRIMARY KEY (tipoarticuloid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tipoarticulo
  OWNER TO postgres;

