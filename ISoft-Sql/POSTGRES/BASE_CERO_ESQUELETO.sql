/*BASE CERO &SOFT POSTGRES
@author Yaher Carrillo
@Date-Create 03/06/2018
@Date-last-change 04/06/2018
@Desciption:
EJECUTAR COMO SE MUESTRA A CONTINUACION*/

/*Crear usuario isoftnnita*/
CREATE USER isoft WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	REPLICATION
	CONNECTION LIMIT -1
	PASSWORD '4l3gn412$';

GRANT postgres TO isoft;
COMMENT ON ROLE isoft IS 'Usuario Isoft';

/*Crear base de datos*/
CREATE DATABASE isoftnnita
    WITH 
    OWNER = isoftnnita
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE isoftnnita
    IS 'Base de datos Isoft Profile.';
	
/*
Ejecutar lo anterior de primero
*/


/*Tablas maestro**/

/*Tabla Maestro de Monedas sobre la aplicacion */
-- Table: public.isoft_mmonedas
-- DROP TABLE public.isoft_mmonedas;
CREATE TABLE public.isoft_mmonedas
(
    ID_MONEDA SERIAL NOT NULL,
    COD_MONEDA character varying(20)[] COLLATE pg_catalog.default NOT NULL,
    NOMBRE_MONEDA character varying(50)[] COLLATE pg_catalog.default,
    HABILITADO integer NOT NULL DEFAULT 0,
    CONSTRAINT isoft_mmonedas_pkey PRIMARY KEY (COD_MONEDA),
	UNIQUE (ID_MONEDA)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_MONEDAS on isoft_mmonedas(ID_MONEDA);
ALTER TABLE public.isoft_mmonedas
    OWNER to isoft;
	
COMMENT ON COLUMN isoft_mmonedas.ID_MONEDA IS 'identificador unico de la tabla maestro monedas';	
COMMENT ON COLUMN isoft_mmonedas.COD_MONEDA IS 'identificador de acceso comun de la tabla, llave primaria';	
COMMENT ON COLUMN isoft_mmonedas.NOMBRE_MONEDA IS 'nombre descriptivo del item';	
COMMENT ON COLUMN isoft_mmonedas.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';	

/*Tabla Maestro de Idiomas sobre la aplicacion */
CREATE TABLE public.isoft_midiomas (
	ID_IDIOMA SERIAL NOT NULL ,
	COD_IDIOMA  character varying(20) NOT NULL ,
	NOMBRE_IDIOMA  character varying(50) ,		
	HABILITADO INT NOT NULL DEFAULT 0 ,		
	CONSTRAINT isoft_midiomas_pkey PRIMARY KEY (COD_IDIOMA),
	UNIQUE (ID_IDIOMA)	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_IDIOMAS on isoft_midiomas(ID_IDIOMA);
ALTER TABLE public.isoft_midiomas
    OWNER to isoft;
	
COMMENT ON COLUMN isoft_midiomas.ID_IDIOMA IS 'identificador unico de la tabla maestro idiomas';		
COMMENT ON COLUMN isoft_midiomas.COD_IDIOMA IS 'identificador de acceso comun de la tabla, llave primaria';		
COMMENT ON COLUMN isoft_midiomas.NOMBRE_IDIOMA IS 'nombre descriptivo del item';		
COMMENT ON COLUMN isoft_midiomas.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';		
	
	

/*Tabla Maestro de Estilos sobre la aplicacion */
CREATE TABLE public.isoft_mestilos (
	ID_ESTILO SERIAL NOT NULL ,	
	COD_ESTILO  character varying(20) NOT NULL ,
	NOMBRE_ESTILO  character varying(50) ,		
	HABILITADO INT NOT NULL DEFAULT 0 ,		
	CONSTRAINT isoft_mestilos_pkey PRIMARY KEY (COD_ESTILO),
	UNIQUE (ID_ESTILO)		
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_ESTILO on isoft_mestilos(ID_ESTILO);
ALTER TABLE public.isoft_mestilos
    OWNER to isoft;
COMMENT ON COLUMN isoft_mestilos.ID_ESTILO IS 'identificador unico de la tabla maestro estilos';			
COMMENT ON COLUMN isoft_mestilos.COD_ESTILO IS 'identificador de acceso comun de la tabla, llave primaria';			
COMMENT ON COLUMN isoft_mestilos.NOMBRE_ESTILO IS 'nombre descriptivo del item';			
COMMENT ON COLUMN isoft_mestilos.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';			
	


/*Tabla Maestro de Eventos sobre la aplicacion */
CREATE TABLE public.isoft_meventos (
	ID_EVENTO SERIAL NOT NULL ,	
	COD_EVENTO  character varying(20) NOT NULL ,
	NOMBRE_EVENTO  character varying(50) ,		
	HABILITADO INT NOT NULL DEFAULT 0 ,		
	CONSTRAINT isoft_meventos_pkey PRIMARY KEY (COD_EVENTO),
	UNIQUE (ID_EVENTO)		
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_EVENTOS on isoft_meventos(ID_EVENTO);
ALTER TABLE public.isoft_meventos
    OWNER to isoft;
COMMENT ON COLUMN isoft_meventos.ID_EVENTO IS 'identificador unico de la tabla maestro EVENTOS';			
COMMENT ON COLUMN isoft_meventos.COD_EVENTO IS 'identificador de acceso comun de la tabla, llave primaria';			
COMMENT ON COLUMN isoft_meventos.NOMBRE_EVENTO IS 'nombre descriptivo del item';			
COMMENT ON COLUMN isoft_meventos.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';			

/*Tabla Maestro de Canales de la aplicacion */
CREATE TABLE public.isoft_mcanales (
	ID_CANAL SERIAL NOT NULL,	
	COD_CANAL  character varying(20) NOT NULL ,
	NOMBRE_CANAL  character varying(50) ,		
	HABILITADO INT NOT NULL DEFAULT 0,		
	CONSTRAINT isoft_mcanales_pkey PRIMARY KEY (COD_CANAL),
	UNIQUE (ID_CANAL)	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_CANALES on isoft_mcanales(ID_CANAL);
ALTER TABLE public.isoft_mcanales
    OWNER to isoft;
COMMENT ON COLUMN isoft_mcanales.ID_CANAL IS 'identificador unico de la tabla maestro CANALES';			
COMMENT ON COLUMN isoft_mcanales.COD_CANAL IS 'identificador de acceso comun de la tabla, llave primaria';			
COMMENT ON COLUMN isoft_mcanales.NOMBRE_CANAL IS 'nombre descriptivo del item';			
COMMENT ON COLUMN isoft_mcanales.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';			


/*Tabla Maestro de Paises de la aplicacion */
CREATE TABLE public.isoft_mpaises (
	ID_PAIS SERIAL NOT NULL,	
	COD_PAIS  character varying(20) NOT NULL ,
	NOMBRE_PAIS  character varying(50),		
	HABILITADO INT NOT NULL DEFAULT 0,		
	CONSTRAINT isoft_mpais_pkey PRIMARY KEY (COD_PAIS),
	UNIQUE (ID_PAIS)	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_PAISES on isoft_mpaises(ID_PAIS);
ALTER TABLE public.isoft_mpaises
    OWNER to isoft;
COMMENT ON COLUMN isoft_mpaises.ID_PAIS IS 'identificador unico de la tabla maestro PAISES';			
COMMENT ON COLUMN isoft_mpaises.COD_PAIS IS 'identificador de acceso comun de la tabla, llave primaria';			
COMMENT ON COLUMN isoft_mpaises.NOMBRE_PAIS IS 'nombre descriptivo del item';			
COMMENT ON COLUMN isoft_mpaises.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';			

/*Tabla Maestro de Bancos de la aplicacion */
CREATE TABLE public.isoft_mbancos (
	ID_BANCO SERIAL NOT NULL,	
	ID_PAIS INT NOT NULL,	
	COD_BANCO   character varying(20) NOT NULL ,
	NOMBRE_BANCO   character varying(50) ,		
	HABILITADO INT NOT NULL DEFAULT 0,		
	CONSTRAINT isoft_mbancos_pkey PRIMARY KEY (COD_BANCO),
	UNIQUE (ID_BANCO),
	CONSTRAINT FK_BANCO_PAIS FOREIGN KEY (ID_PAIS) REFERENCES isoft_mpaises (ID_PAIS)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_BANCO on isoft_mbancos(ID_BANCO);
create unique index IDX_BANCO_PAIS on isoft_mbancos(ID_PAIS);
ALTER TABLE public.isoft_mpaises
    OWNER to isoft;
COMMENT ON COLUMN isoft_mbancos.ID_BANCO IS 'identificador unico de la tabla maestro BANCOS';			
COMMENT ON COLUMN isoft_mbancos.ID_PAIS IS 'identificador unico de la tabla maestro PAISES';			
COMMENT ON COLUMN isoft_mbancos.COD_BANCO IS 'identificador de acceso comun de la tabla, llave primaria';			
COMMENT ON COLUMN isoft_mbancos.NOMBRE_BANCO IS 'nombre descriptivo del item';			
COMMENT ON COLUMN isoft_mbancos.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';			

/*Tabla Maestro de Tipos de Pagos que soporta la aplicacion */
CREATE TABLE public.isoft_mtipos_pagos (
	ID_TIPO_PAGO SERIAL NOT NULL,	
	COD_TIPO_PAGO  character varying(20) NOT NULL,
	NOMBRE_TIPO_PAGO  character varying(50) ,		
	HABILITADO INT NOT NULL DEFAULT 0,		
	CONSTRAINT isoftmtipos_pagos_pkey PRIMARY KEY (ID_TIPO_PAGO)	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_M_TIPOS_PAGOS on isoft_mtipos_pagos(ID_TIPO_PAGO);
ALTER TABLE public.isoft_mpaises
    OWNER to isoft;
COMMENT ON COLUMN isoft_mtipos_pagos.ID_TIPO_PAGO IS 'identificador unico de la tabla maestro MTIPOS_PAGOS';				
COMMENT ON COLUMN isoft_mtipos_pagos.COD_TIPO_PAGO IS 'identificador de acceso comun de la tabla, llave primaria';				
COMMENT ON COLUMN isoft_mtipos_pagos.NOMBRE_TIPO_PAGO IS 'nombre descriptivo del item';				
COMMENT ON COLUMN isoft_mtipos_pagos.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1';				

/*Tabla de Usuarios del sistema.*/
CREATE TABLE public.isoft_usuarios (
	ID_USUARIO SERIAL NOT NULL,
	LOGIN character varying(30) NOT NULL ,
	CLAVE character varying(256) NOT NULL ,
	NOMBRE_USUARIO character varying(50) NOT NULL ,
	APELLIDO_USUARIO character varying(50) NOT NULL ,
	EMAIL character varying(30) ,
	SEXO character varying(1) ,
	FECHA_REGISTRO DATE NOT NULL, 
	FECHA_ULTIMA_VIS DATE , 
	HABILITADO INT NOT NULL DEFAULT 0,	
	CONSTRAINT isoft_usuarios_pkey PRIMARY KEY (ID_USUARIO),
	UNIQUE (ID_USUARIO),
	UNIQUE (LOGIN)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_USUARIOS_ID on isoft_usuarios(ID_USUARIO);
create unique index IDX_USUARIOS_LOGIN on isoft_usuarios(LOGIN);
create unique index IDX_USUARIOS_EMAIL on isoft_usuarios(EMAIL);
create unique index IDX_USUARIOS_SEXO on isoft_usuarios(SEXO);
ALTER TABLE public.isoft_usuarios
    OWNER to isoft;

COMMENT ON COLUMN isoft_usuarios.ID_USUARIO IS 'identificador unico de la tabla maestro isoft_usuarios.';					
COMMENT ON COLUMN isoft_usuarios.LOGIN IS 'identificador unico de acceso a la aplicacion de los usuarios registrados.';					
COMMENT ON COLUMN isoft_usuarios.CLAVE IS 'clave de acceso a la aplicacion de los usuarios registrados.';					
COMMENT ON COLUMN isoft_usuarios.NOMBRE_USUARIO IS 'nombre de usuario de la aplicacion registrado.';					
COMMENT ON COLUMN isoft_usuarios.APELLIDO_USUARIO IS 'apellido de usuario de la aplicacion registrado.';					
COMMENT ON COLUMN isoft_usuarios.EMAIL IS 'email de usuario de la aplicacion registrado.';					
COMMENT ON COLUMN isoft_usuarios.FECHA_REGISTRO IS 'fecha en la que se registro el usuario de la aplicacion registrado.';					
COMMENT ON COLUMN isoft_usuarios.FECHA_ULTIMA_VIS IS 'ultima fecha en la que se interactuo el usuario de la aplicacion registrado.';					
COMMENT ON COLUMN isoft_usuarios.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';					


/*Tabla de Items de menu disponibles.*/
CREATE TABLE public.isoft_menu_item (
	ID_MENU_ITEM SERIAL NOT NULL,
	MENU_LINK character varying(120) NOT NULL ,	
	MENU_LABEL character varying(30) NOT NULL ,	
	ID_MENU_PADRE INT,
	ID_MENU_HIJO INT,
	ORDEN INT,
	HABILITADO INT NOT NULL DEFAULT 0,	
	CONSTRAINT isoft_menu_item_pkey PRIMARY KEY (ID_MENU_ITEM),
	UNIQUE (ID_MENU_ITEM)	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_MENU_ITEM_ID on isoft_menu_item(ID_MENU_ITEM);
create unique index IDX_MENU_ITEM_ID_PADRE on isoft_menu_item(ID_MENU_PADRE);
create unique index IDX_MENU_ITEM_ID_HIJO on isoft_menu_item(ID_MENU_HIJO);
ALTER TABLE public.isoft_menu_item
    OWNER to isoft;
COMMENT ON COLUMN isoft_menu_item.ID_MENU_ITEM IS 'identificador unico de la tabla maestro isoft_menu_item.';						
COMMENT ON COLUMN isoft_menu_item.MENU_LINK IS 'Ruta de acceso al archivo que expone la funcionalidad el item.';						
COMMENT ON COLUMN isoft_menu_item.MENU_LABEL IS 'Nombre identificador del elemento.';						
COMMENT ON COLUMN isoft_menu_item.ID_MENU_PADRE IS 'identificador unico del menu padre al que pertenece el item.';						
COMMENT ON COLUMN isoft_menu_item.ID_MENU_HIJO IS 'identificador unico del menu dependiente del item.';						
COMMENT ON COLUMN isoft_menu_item.ORDEN IS 'id identificador del orden del menu en el sistema.';						
COMMENT ON COLUMN isoft_menu_item.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';						
	

/*Tabla de Menues principales del sistema.*/
CREATE TABLE public.isoft_menu (
	ID_MENU SERIAL NOT NULL,	
	MENU_LABEL CHARACTER VARYING(30) NOT NULL ,		
	ORDEN INT,
	HABILITADO INT NOT NULL DEFAULT 0,	
	CONSTRAINT isoft_menu_id_pkey PRIMARY KEY (ID_MENU)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_MENU_ID on isoft_menu(ID_MENU);
ALTER TABLE public.isoft_menu
    OWNER to isoft;
COMMENT ON COLUMN isoft_menu.ID_MENU IS 'identificador unico de la tabla maestro isoft_menu.';							
COMMENT ON COLUMN isoft_menu.MENU_LABEL IS 'Nombre identificador del elemento.';							
COMMENT ON COLUMN isoft_menu.ORDEN IS 'id identificador del orden del menu en el sistema.';							
COMMENT ON COLUMN isoft_menu.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';							


/*Tabla de Perfiles del sistema.*/
CREATE TABLE public.isoft_perfiles (
	ID_PERFIL SERIAL NOT NULL,
	NOMBRE_PERFIL CHARACTER VARYING(50) NOT NULL ,	
	ADMINISTRADOR INT NOT NULL DEFAULT 0,	
	HABILITADO INT NOT NULL DEFAULT 0,	
	CONSTRAINT isoft_perfiles_pkey PRIMARY KEY (NOMBRE_PERFIL),
	UNIQUE (ID_PERFIL),
	UNIQUE (NOMBRE_PERFIL)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_PERFILES_ID on isoft_perfiles(ID_PERFIL);
ALTER TABLE public.isoft_menu
    OWNER to isoft;
COMMENT ON COLUMN isoft_perfiles.ID_PERFIL IS 'identificador unico de la tabla maestro isoft_perfiles.';								
COMMENT ON COLUMN isoft_perfiles.NOMBRE_PERFIL IS 'Nombre identificador del elemento.';								
COMMENT ON COLUMN isoft_perfiles.ADMINISTRADOR IS 'logico que indica si el perfil es un administrador / deshabilita el registro val: 0,1.';								
COMMENT ON COLUMN isoft_perfiles.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';								


/*Relacional de permisos de accion sobre perfiles*/
CREATE TABLE public.isoft_permisos (
	ID_PERMISO SERIAL NOT NULL,	
	ID_MENU  BIGINT NOT NULL,
	ID_PERFIL  BIGINT NOT NULL,	
	CONSTRAINT isoft_permisos_id_permiso PRIMARY KEY (ID_PERMISO),
	UNIQUE (ID_PERMISO),	
	CONSTRAINT FK_PERM_MENU FOREIGN KEY (ID_MENU) REFERENCES isoft_menu (ID_MENU),
	CONSTRAINT FK_PERM_PERF FOREIGN KEY (ID_PERFIL) REFERENCES isoft_perfiles (ID_PERFIL)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_PERMISOS_ID_PERFIL on isoft_permisos(ID_PERFIL);
create unique index IDX_PERFILES_ID_MENU on isoft_permisos(ID_MENU);
ALTER TABLE public.isoft_permisos
    OWNER to isoft;
COMMENT ON COLUMN isoft_permisos.ID_PERMISO IS 'identificador unico de la tabla maestro isoft_permisos.';									
COMMENT ON COLUMN isoft_permisos.ID_MENU IS 'identificador unico de la tabla maestro isoft_menu.';									
COMMENT ON COLUMN isoft_permisos.ID_PERFIL IS 'identificador unico de la tabla maestro isoft_perfiles.';									


/*Tabla Maestro de Eventos sobre la aplicacion */
CREATE TABLE public.isoft_bitacora (
	ID_BITACORA SERIAL NOT NULL,	
	ID_EVENTO  BIGINT NOT NULL,
	ID_CANAL  BIGINT NOT NULL,		
	FECHA_REGISTRO  DATE NULL DEFAULT NULL,
	FECHA_REGISTRO_SEGUNDOS BIGINT DEFAULT NULL,
	IP  VARCHAR NOT NULL,		
	HABILITADO INT NOT NULL DEFAULT 0,		
	CONSTRAINT isoft_bitacora_pkey PRIMARY KEY (ID_BITACORA),
	UNIQUE (ID_BITACORA),		
	CONSTRAINT FK_MAES_BIT_EVENT FOREIGN KEY (ID_EVENTO) REFERENCES isoft_meventos (ID_EVENTO),	
	CONSTRAINT FK_MAES_BIT_CANAL FOREIGN KEY (ID_CANAL) REFERENCES isoft_mcanales (ID_CANAL)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_BITACORA_ID_BITACORA on isoft_bitacora(ID_BITACORA);
create unique index IDX_BITACORA_ID_EVENTO on isoft_bitacora(ID_EVENTO);
create unique index IDX_BITACORA_ID_CANAL on isoft_bitacora(ID_CANAL);
ALTER TABLE public.isoft_bitacora
    OWNER to isoft;
COMMENT ON COLUMN isoft_bitacora.ID_BITACORA IS 'identificador unico de la tabla maestro isoft_bitacora.';										
COMMENT ON COLUMN isoft_bitacora.ID_EVENTO IS 'identificador unico de la tabla maestro isoft_eventos.';										
COMMENT ON COLUMN isoft_bitacora.ID_CANAL IS 'identificador unico de la tabla maestro isoft_canales.';										
COMMENT ON COLUMN isoft_bitacora.FECHA_REGISTRO IS 'fecha de registro de la transaccion.';										
COMMENT ON COLUMN isoft_bitacora.FECHA_REGISTRO_SEGUNDOS IS 'fecha de registro de la transaccion en segundos.';										
COMMENT ON COLUMN isoft_bitacora.IP IS 'identificador de la terminal de ejecucion de la transaccion.';										
COMMENT ON COLUMN isoft_bitacora.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';										

/*Tabla Detalle de Eventos sobre la aplicacion */
CREATE TABLE public.isoft_detalle_bitacora (
	ID_DETALLE_BITACORA SERIAL NOT NULL,	
	ID_BITACORA  BIGINT NOT NULL,	
	ID_EVENTO  BIGINT NOT NULL,
	HORA_INICIO BIGINT NOT NULL,
	HORA_FIN BIGINT NULL DEFAULT NULL,
	DETALLE_VALOR_INICIO  CHARACTER VARYING(260) ,		
	DETALLE_VALOR_FIN  CHARACTER VARYING(260),		
	DESCRIPCION  CHARACTER VARYING(50) ,		
	HABILITADO INT NOT NULL DEFAULT 0,		
	CONSTRAINT isoft_detalle_bitacora_pkey PRIMARY KEY (ID_DETALLE_BITACORA),
	UNIQUE (ID_DETALLE_BITACORA),	
	CONSTRAINT FK_DET_MAES_BITACORA FOREIGN KEY (ID_BITACORA) REFERENCES isoft_bitacora (ID_BITACORA),
	CONSTRAINT FK_DET_BIT_EVENTOS FOREIGN KEY (ID_EVENTO) REFERENCES isoft_meventos (ID_EVENTO)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_DETALLE_BITACORA_ID_DET_BITACORA on isoft_detalle_bitacora(ID_DETALLE_BITACORA);
create unique index IDX_DETALLE_BITACORA_ID_BITACORA on isoft_bitacora(ID_BITACORA);
create unique index IDX_DETALLE_BITACORA_ID_EVENTO on isoft_meventos(ID_EVENTO);
ALTER TABLE public.isoft_detalle_bitacora
    OWNER to isoft;
COMMENT ON COLUMN isoft_detalle_bitacora.ID_DETALLE_BITACORA IS 'identificador unico de la tabla maestro isoft_detalle_bitacora.';										
COMMENT ON COLUMN isoft_detalle_bitacora.ID_BITACORA IS 'identificador unico de la tabla maestro isoft_bitacora.';										
COMMENT ON COLUMN isoft_detalle_bitacora.ID_EVENTO IS 'identificador unico de la tabla maestro isoft_meventos.';										
COMMENT ON COLUMN isoft_detalle_bitacora.HORA_INICIO IS 'hora inicio en la que se realizo la transaccion.';										
COMMENT ON COLUMN isoft_detalle_bitacora.HORA_FIN IS 'hora fin en la que se realizo la transaccion.';										
COMMENT ON COLUMN isoft_detalle_bitacora.DETALLE_VALOR_INICIO IS 'detalle de inicio de la transaccion con respecto a los valores usados.';										
COMMENT ON COLUMN isoft_detalle_bitacora.DETALLE_VALOR_FIN IS 'detalle de fin de la transaccion con respecto a los valores usados.';										
COMMENT ON COLUMN isoft_detalle_bitacora.DESCRIPCION IS 'Descripcion de la transaccion.';										
COMMENT ON COLUMN isoft_detalle_bitacora.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';										




/*Tabla de Registro de pagos de cliente propietarios*/
CREATE TABLE public.isoft_pagos_cliente (
	ID_PAGO SERIAL NOT NULL,	
	ID_BANCO  BIGINT NOT NULL,	
	ID_MONEDA  BIGINT NOT NULL,
	MONTO NUMERIC NOT NULL,
	FECHA_REGISTRO  DATE NOT NULL,
	HORA_REGISTRO BIGINT,	
	DESCRIPCION  CHARACTER VARYING(50),		
	HABILITADO INT DEFAULT 0,		
	CONSTRAINT isoft_pagos_cliente_pkey PRIMARY KEY (ID_PAGO),
	UNIQUE (ID_PAGO),		
	CONSTRAINT FK_PAG_MAES_BANCO FOREIGN KEY (ID_BANCO) REFERENCES isoft_mbancos (ID_BANCO),
	CONSTRAINT FK_PAG_MAES_MONEDA FOREIGN KEY (ID_MONEDA) REFERENCES isoft_mmonedas (ID_MONEDA)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_PAGOS_CLIETE_ID_PAGO on isoft_pagos_cliente(ID_PAGO);
create unique index IDX_PAGOS_CLIETE_ID_BANCO on isoft_pagos_cliente(ID_BANCO);
create unique index IDX_PAGOS_CLIETE_ID_MONEDA on isoft_pagos_cliente(ID_MONEDA);
ALTER TABLE public.isoft_pagos_cliente
    OWNER to isoft;
COMMENT ON COLUMN isoft_pagos_cliente.ID_PAGO IS 'identificador unico de la tabla maestro isoft_pagos_cliente.';											
COMMENT ON COLUMN isoft_pagos_cliente.ID_BANCO IS 'identificador unico de la tabla maestro isoft_mbancos.';											
COMMENT ON COLUMN isoft_pagos_cliente.ID_MONEDA IS 'identificador unico de la tabla maestro isoft_mmonedas.';											
COMMENT ON COLUMN isoft_pagos_cliente.MONTO IS 'monto del registro.';											
COMMENT ON COLUMN isoft_pagos_cliente.FECHA_REGISTRO IS 'fecha creacion del registro.';											
COMMENT ON COLUMN isoft_pagos_cliente.HORA_REGISTRO IS 'hora creacion del registro.';											
COMMENT ON COLUMN isoft_pagos_cliente.DESCRIPCION IS 'descripcion del registro.';											
COMMENT ON COLUMN isoft_pagos_cliente.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';											


/*Tabla de Detalles adjuntos de Registro de pagos de cliente propietarios*/
CREATE TABLE public.isoft_pagos_referencias (
	ID_PAGO_REF SERIAL NOT NULL,
	ID_PAGO BIGINT NOT NULL,	
	IMAGEN bytea NOT NULL,
	RUTA_NOMBRE_ARCHIVO_IMAGEN CHARACTER VARYING(256) NOT NULL ,
	CONSTRAINT isoft_pagos_referencias_pkey PRIMARY KEY (ID_PAGO_REF),
	UNIQUE (ID_PAGO_REF),	
	CONSTRAINT FK_PAG_REF_PAGO FOREIGN KEY (ID_PAGO) REFERENCES isoft_pagos_cliente (ID_PAGO)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_PAGOS_REF_ID on isoft_pagos_referencias(ID_PAGO_REF);
create unique index IDX_PAGOS_REF_ID_PAGO on isoft_pagos_referencias(ID_PAGO);
ALTER TABLE public.isoft_pagos_referencias
    OWNER to isoft;
COMMENT ON COLUMN isoft_pagos_referencias.ID_PAGO_REF IS 'identificador unico de la tabla maestro isoft_pagos_referencias.';												
COMMENT ON COLUMN isoft_pagos_referencias.ID_PAGO IS 'identificador unico de la tabla isoft_pagos_cliente.';												
COMMENT ON COLUMN isoft_pagos_referencias.IMAGEN IS 'Imagen de referencia de transaccion.';												
COMMENT ON COLUMN isoft_pagos_referencias.RUTA_NOMBRE_ARCHIVO_IMAGEN IS 'Ruta donde esta la imagen.';	

/*Tabla de Detalles adjuntos de Registro de pagos de cliente propietarios*/
CREATE TABLE public.isoft_parametros (
	ID_PARAMETRO SERIAL NOT NULL,
	DESCRIPCION CHARACTER VARYING(256) NOT NULL ,
	VALOR CHARACTER VARYING(256) NOT NULL ,
	TIPO_DATO CHARACTER VARYING(64) NOT NULL ,
	NOMBRE CHARACTER VARYING(128) NOT NULL ,
	GRUPO CHARACTER VARYING(64) NOT NULL ,	
	CONSTRAINT isoft_parametros_pkey PRIMARY KEY (NOMBRE),
	UNIQUE (ID_PARAMETRO)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_ID_PARAMETRO on isoft_parametros(ID_PARAMETRO);
create unique index IDX_NOMBRE_PARAMETRO on isoft_parametros(NOMBRE);
ALTER TABLE public.isoft_parametros
    OWNER to isoft;
COMMENT ON COLUMN isoft_parametros.ID_PARAMETRO IS 'identificador unico de la tabla maestro isoft_parametros.';												
COMMENT ON COLUMN isoft_parametros.DESCRIPCION IS 'Descripcion de los registros de la tabla.';												
COMMENT ON COLUMN isoft_parametros.VALOR IS 'Valor del parametro.';												
COMMENT ON COLUMN isoft_parametros.TIPO_DATO IS 'Tipo de datos generico a usar por el parametro para su interpretacion en el code.';												
COMMENT ON COLUMN isoft_parametros.NOMBRE IS 'Nombre unico del parametro.';												
COMMENT ON COLUMN isoft_parametros.GRUPO IS 'Grupo al que pertenece el parametro.';																						



/*Tabla de relacional de relaciones  entre usuarios y perfiles*/
CREATE TABLE public.isoft_usuario_perfil (
	ID_USUARIO_PERFIL SERIAL NOT NULL,
	ID_USUARIO BIGINT NOT NULL,
	ID_PERFIL BIGINT NOT NULL ,
	HABILITADO INT DEFAULT 0,		
	CONSTRAINT isoft_usuario_perfil_pkey PRIMARY KEY (ID_USUARIO,ID_PERFIL),
	UNIQUE (ID_USUARIO_PERFIL)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_ID_USUARIO_PERFIL on isoft_usuario_perfil(ID_USUARIO_PERFIL);
create unique index IDX_ID_USUARIO on isoft_usuario_perfil(ID_USUARIO);
create unique index IDX_ID_PERFIL on isoft_usuario_perfil(ID_PERFIL);
ALTER TABLE public.isoft_usuario_perfil
    OWNER to isoft;
COMMENT ON COLUMN isoft_usuario_perfil.ID_USUARIO_PERFIL IS 'identificador unico de la tabla maestro isoft_parametros.';												
COMMENT ON COLUMN isoft_usuario_perfil.ID_USUARIO IS 'Identificador de usuarios.';												
COMMENT ON COLUMN isoft_usuario_perfil.ID_PERFIL IS 'Identificador de perfiles.';												
COMMENT ON COLUMN isoft_usuario_perfil.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';	
commit;