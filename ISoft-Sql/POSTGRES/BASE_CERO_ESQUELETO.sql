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

/*Secuencia MCIUDADES**/		
CREATE SEQUENCE public.ISOFT_MIDIOMAS_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_MIDIOMAS_SEQ
  OWNER TO isoft;	

/*Tabla Maestro de Idiomas sobre la aplicacion */
CREATE TABLE public.isoft_midiomas (
	ID_IDIOMA INTEGER NOT NULL DEFAULT nextval('ISOFT_MIDIOMAS_SEQ'::regclass) ,
	COD_IDIOMA  character varying(60) NOT NULL ,
	NOMBRE_IDIOMA  character varying(60) ,		
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
	
	

CREATE SEQUENCE public.ISOFT_MESTILOS_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_MESTILOS_SEQ
  OWNER TO isoft;	
/*Tabla Maestro de Estilos sobre la aplicacion */
CREATE TABLE public.isoft_mestilos (
	ID_ESTILO INTEGER NOT NULL DEFAULT nextval('ISOFT_MESTILOS_SEQ'::regclass) ,	
	COD_ESTILO  character varying(60) NOT NULL ,
	NOMBRE_ESTILO  character varying(60) ,		
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
	

CREATE SEQUENCE public.ISOFT_MEVENTOS_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_MEVENTOS_SEQ
  OWNER TO isoft;
/*Tabla Maestro de Eventos sobre la aplicacion */
CREATE TABLE public.isoft_meventos (
	ID_EVENTO INTEGER NOT NULL DEFAULT nextval('ISOFT_MEVENTOS_SEQ'::regclass) ,	
	COD_EVENTO  character varying(60) NOT NULL ,
	NOMBRE_EVENTO  character varying(60) ,		
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



CREATE SEQUENCE public.ISOFT_MCANALES_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_MCANALES_SEQ
  OWNER TO isoft;
/*Tabla Maestro de Canales de la aplicacion */
CREATE TABLE public.isoft_mcanales (
	ID_CANAL INTEGER NOT NULL DEFAULT nextval('ISOFT_MCANALES_SEQ'::regclass),	
	COD_CANAL  character varying(60) NOT NULL ,
	NOMBRE_CANAL  character varying(60) ,		
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


CREATE SEQUENCE public.ISOFT_PERFILES_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_PERFILES_SEQ
  OWNER TO isoft;
/*Tabla de Perfiles del sistema.*/
CREATE TABLE public.isoft_perfiles (
	ID_PERFIL INTEGER NOT NULL DEFAULT nextval('ISOFT_PERFILES_SEQ'::regclass),
	NOMBRE_PERFIL CHARACTER VARYING(60) NOT NULL ,	
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
ALTER TABLE public.isoft_perfiles
    OWNER to isoft;
COMMENT ON COLUMN isoft_perfiles.ID_PERFIL IS 'identificador unico de la tabla maestro isoft_perfiles.';								
COMMENT ON COLUMN isoft_perfiles.NOMBRE_PERFIL IS 'Nombre identificador del elemento.';								
COMMENT ON COLUMN isoft_perfiles.ADMINISTRADOR IS 'logico que indica si el perfil es un administrador / deshabilita el registro val: 0,1.';								
COMMENT ON COLUMN isoft_perfiles.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';		


CREATE SEQUENCE public.ISOFT_USUARIOS_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_USUARIOS_SEQ
  OWNER TO isoft;

/*Tabla de Usuarios del sistema.*/
CREATE TABLE public.isoft_usuarios (
	ID_USUARIO INTEGER NOT NULL DEFAULT nextval('ISOFT_USUARIOS_SEQ'::regclass),
	LOGIN character varying(60) NOT NULL ,
	CLAVE character varying(256) NOT NULL ,
	NOMBRE_USUARIO character varying(60) NOT NULL ,
	APELLIDO_USUARIO character varying(60) NOT NULL ,
	EMAIL character varying(60) ,
	SEXO character varying(1) ,
	FECHA_REGISTRO DATE NOT NULL, 
	FECHA_ULTIMA_VIS DATE , 
	PERFIL_DEFAULT INT , 
	HABILITADO INT NOT NULL DEFAULT 0,	
	CONSTRAINT isoft_usuarios_pkey PRIMARY KEY (ID_USUARIO),
	CONSTRAINT FK_USU_PER FOREIGN KEY (PERFIL_DEFAULT) REFERENCES isoft_perfiles (ID_PERFIL),
	UNIQUE (ID_USUARIO),
	UNIQUE (LOGIN),
	UNIQUE (EMAIL)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_USUARIOS_ID on isoft_usuarios(ID_USUARIO);
create unique index IDX_USUARIOS_LOGIN on isoft_usuarios(LOGIN);
create unique index IDX_USUARIOS_EMAIL on isoft_usuarios(EMAIL);
create unique index IDX_USUARIOS_SEXO on isoft_usuarios(SEXO);
create unique index IDX_USUARIOS_PER_DEFAULT on isoft_usuarios(PERFIL_DEFAULT);
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
COMMENT ON COLUMN isoft_usuarios.PERFIL_DEFAULT IS 'Perfil por defecto del usuario.';					
COMMENT ON COLUMN isoft_usuarios.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';					



CREATE SEQUENCE public.ISOFT_USUARIO_PERFIL_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_USUARIO_PERFIL_SEQ
  OWNER TO isoft;
/*Tabla de relacional de relaciones  entre usuarios y perfiles*/
CREATE TABLE public.isoft_usuario_perfil (
	ID_USUARIO_PERFIL INTEGER NOT NULL DEFAULT nextval('ISOFT_USUARIO_PERFIL_SEQ'::regclass),
	ID_USUARIO BIGINT NOT NULL,
	ID_PERFIL BIGINT NOT NULL ,
	HABILITADO INT DEFAULT 0,		
	CONSTRAINT isoft_usuario_perfil_pkey PRIMARY KEY (ID_USUARIO,ID_PERFIL),
	CONSTRAINT FK_USU_PER_UNION FOREIGN KEY (ID_USUARIO) REFERENCES isoft_usuarios (ID_USUARIO),
	CONSTRAINT FK_USU_PER_P_UNION FOREIGN KEY (ID_PERFIL) REFERENCES isoft_perfiles (ID_PERFIL),
	UNIQUE (ID_USUARIO_PERFIL)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_ID_USUARIO_PERFIL on isoft_usuario_perfil(ID_USUARIO_PERFIL);
create index IDX_ID_USUARIO on isoft_usuario_perfil(ID_USUARIO);
create index IDX_ID_PERFIL on isoft_usuario_perfil(ID_PERFIL);
ALTER TABLE public.isoft_usuario_perfil
    OWNER to isoft;
COMMENT ON COLUMN isoft_usuario_perfil.ID_USUARIO_PERFIL IS 'identificador unico de la tabla maestro isoft_parametros.';												
COMMENT ON COLUMN isoft_usuario_perfil.ID_USUARIO IS 'Identificador de usuarios.';												
COMMENT ON COLUMN isoft_usuario_perfil.ID_PERFIL IS 'Identificador de perfiles.';												
COMMENT ON COLUMN isoft_usuario_perfil.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';	

CREATE SEQUENCE public.ISOFT_MENU_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_MENU_SEQ
  OWNER TO isoft;
/*Tabla de Menues principales del sistema.*/
CREATE TABLE public.isoft_menu (
	ID_MENU INTEGER NOT NULL DEFAULT nextval('ISOFT_MENU_SEQ'::regclass),	
	MENU_LABEL CHARACTER VARYING(60) NOT NULL ,		
	ORDEN INT,
	REF_SECURITY character varying(220),
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


CREATE SEQUENCE public.ISOFT_MENU_ITEM_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_MENU_ITEM_SEQ
  OWNER TO isoft;
/*Tabla de Items de menu disponibles.*/
CREATE TABLE public.isoft_menu_item (
	ID_MENU_ITEM INTEGER NOT NULL DEFAULT nextval('ISOFT_MENU_ITEM_SEQ'::regclass),
	MENU_LINK character varying(120) NOT NULL ,	
	MENU_LABEL character varying(60) NOT NULL ,	
	ID_MENU_PADRE INT,
	ID_MENU_HIJO INT,
	ORDEN INT,
	REF_SECURITY character varying(220),
	HABILITADO INT NOT NULL DEFAULT 0,	
	CONSTRAINT isoft_menu_item_pkey PRIMARY KEY (ID_MENU_ITEM),
	CONSTRAINT FK_ITEM_MENU_PADRE FOREIGN KEY (ID_MENU_PADRE) REFERENCES isoft_menu (ID_MENU),
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


CREATE SEQUENCE public.ISOFT_MENUS_PERFIL_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_MENUS_PERFIL_SEQ
  OWNER TO isoft;
/*Tabla de Items de menu disponibles.*/
CREATE TABLE public.isoft_menus_perfil (
	ID_MENU_PERFIL INTEGER NOT NULL DEFAULT nextval('ISOFT_MENUS_PERFIL_SEQ'::regclass),	
	ID_MENU INT,
	ID_PERFIL INT,	
	CONSTRAINT isoft_menu_perfil_pkey PRIMARY KEY (ID_MENU_PERFIL),
	CONSTRAINT FK_MENU_PERFIL_MENU FOREIGN KEY (ID_MENU) REFERENCES isoft_menu (ID_MENU),
	CONSTRAINT FK_MENU_PERFIL_PERF FOREIGN KEY (ID_PERFIL) REFERENCES isoft_perfiles (ID_PERFIL),
	UNIQUE (ID_MENU_PERFIL)	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_MENU_PERFIL_ID on isoft_menus_perfil(ID_MENU_PERFIL);
create unique index IDX_MENU_PERFIL_MENU on isoft_menus_perfil(ID_MENU);
create unique index IDX_MENU_PERFIL_PERF on isoft_menus_perfil(ID_PERFIL);
ALTER TABLE public.isoft_menus_perfil
    OWNER to isoft;
COMMENT ON COLUMN isoft_menus_perfil.ID_MENU_PERFIL IS 'identificador unico de la tabla maestro isoft_menus_perfil.';						
COMMENT ON COLUMN isoft_menus_perfil.ID_MENU IS 'id de  menu de referencia.';						
COMMENT ON COLUMN isoft_menus_perfil.ID_PERFIL IS 'id de perfil de referencia.';						
	
CREATE SEQUENCE public.ISOFT_PERMISOS_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_PERMISOS_SEQ
  OWNER TO isoft;

/*Relacional de permisos de accion sobre perfiles*/
CREATE TABLE public.isoft_permisos (
	ID_PERMISO INTEGER NOT NULL DEFAULT nextval('ISOFT_PERMISOS_SEQ'::regclass),	
	ID_MENU  BIGINT NOT NULL,
	ID_PERFIL  BIGINT NOT NULL,	
	HABILITADO INT NOT NULL DEFAULT 0,	
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

CREATE SEQUENCE public.ISOFT_BITACORA_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_BITACORA_SEQ
  OWNER TO isoft;

/*Tabla Maestro de Eventos sobre la aplicacion */
CREATE TABLE public.isoft_bitacora (
	ID_BITACORA INTEGER NOT NULL DEFAULT nextval('ISOFT_BITACORA_SEQ'::regclass),	
	ID_EVENTO  BIGINT NOT NULL,
	ID_CANAL  BIGINT NOT NULL,		
	ID_USUARIO  BIGINT NOT NULL,		
	FECHA_REGISTRO  DATE NULL DEFAULT NULL,
	FECHA_REGISTRO_SEGUNDOS BIGINT DEFAULT NULL,
	IP   character varying(60),		
	HABILITADO INT NOT NULL DEFAULT 0,		
	CONSTRAINT isoft_bitacora_pkey PRIMARY KEY (ID_BITACORA),
	UNIQUE (ID_BITACORA),		
	CONSTRAINT FK_MAES_BIT_EVENT FOREIGN KEY (ID_EVENTO) REFERENCES isoft_meventos (ID_EVENTO),	
	CONSTRAINT FK_MAES_BIT_CANAL FOREIGN KEY (ID_CANAL) REFERENCES isoft_mcanales (ID_CANAL),
	CONSTRAINT FK_MAES_BIT_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES isoft_usuarios (ID_USUARIO)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
create unique index IDX_BITACORA_ID_BITACORA on isoft_bitacora(ID_BITACORA);
create  index IDX_BITACORA_ID_EVENTO on isoft_bitacora(ID_EVENTO);
create  index IDX_BITACORA_ID_CANAL on isoft_bitacora(ID_CANAL);
create  index IDX_BITACORA_ID_USUARIO on isoft_bitacora(ID_USUARIO);
ALTER TABLE public.isoft_bitacora
    OWNER to isoft;
COMMENT ON COLUMN isoft_bitacora.ID_BITACORA IS 'identificador unico de la tabla maestro isoft_bitacora.';										
COMMENT ON COLUMN isoft_bitacora.ID_EVENTO IS 'identificador unico de la tabla maestro isoft_eventos.';										
COMMENT ON COLUMN isoft_bitacora.ID_CANAL IS 'identificador unico de la tabla maestro isoft_canales.';										
COMMENT ON COLUMN isoft_bitacora.ID_USUARIO IS 'identificador unico de la tabla maestro isoft_usuarios.';										
COMMENT ON COLUMN isoft_bitacora.FECHA_REGISTRO IS 'fecha de registro de la transaccion.';										
COMMENT ON COLUMN isoft_bitacora.FECHA_REGISTRO_SEGUNDOS IS 'fecha de registro de la transaccion en segundos.';										
COMMENT ON COLUMN isoft_bitacora.IP IS 'identificador de la terminal de ejecucion de la transaccion.';										
COMMENT ON COLUMN isoft_bitacora.HABILITADO IS 'logico que habilita / deshabilita el registro val: 0,1.';										

CREATE SEQUENCE public.ISOFT_DETALLE_BITACORA_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_DETALLE_BITACORA_SEQ
  OWNER TO isoft;
/*Tabla Detalle de Eventos sobre la aplicacion */
CREATE TABLE public.isoft_detalle_bitacora (
	ID_DETALLE_BITACORA INTEGER NOT NULL DEFAULT nextval('ISOFT_DETALLE_BITACORA_SEQ'::regclass),	
	ID_BITACORA  BIGINT NOT NULL,	
	ID_EVENTO  BIGINT NOT NULL,
	HORA_INICIO BIGINT NULL DEFAULT NULL,
	HORA_FIN BIGINT NULL DEFAULT NULL,
	DETALLE_VALOR_INICIO  CHARACTER VARYING(260) ,		
	DETALLE_VALOR_FIN  CHARACTER VARYING(260),		
	DESCRIPCION  CHARACTER VARYING(260) ,		
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
create  index IDX_DETALLE_BITACORA_ID_BITACORA on isoft_bitacora(ID_BITACORA);
create  index IDX_DETALLE_BITACORA_ID_EVENTO on isoft_meventos(ID_EVENTO);
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


CREATE SEQUENCE public.ISOFT_PARAMETROS_SEQ
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE public.ISOFT_PARAMETROS_SEQ
  OWNER TO isoft;
/*Tabla de Detalles adjuntos de Registro de pagos de cliente propietarios*/
CREATE TABLE public.isoft_parametros (
	ID_PARAMETRO INTEGER NOT NULL DEFAULT nextval('ISOFT_PARAMETROS_SEQ'::regclass),
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


commit;