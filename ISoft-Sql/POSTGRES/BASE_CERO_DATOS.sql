/*BASE CERO Datos &SOFT POSTGRES
@author Yaher Carrillo
@Date-Create 22/05/2018
@Date-last-change 22/05/2018
@Desciption:
Elementos iniciales de las tablas maestro.
EJECUTAR COMO SE MUESTRA A CONTINUACION*/

/*Se toma la base de datos*/
use isoftnnita;

/**SE CARGAN LAS MONEDAS DE USO**/
INSERT INTO `isoft_mmonedas` (`COD_MONEDA`, `NOMBRE_MONEDA`, `HABILITADO`) VALUES ('COP', 'Pesos Colombianos', '1');
INSERT INTO `isoft_mmonedas` (`COD_MONEDA`, `NOMBRE_MONEDA`, `HABILITADO`) VALUES ('US', 'Dolares Americanos', '0');
INSERT INTO `isoft_mmonedas` (`COD_MONEDA`, `NOMBRE_MONEDA`, `HABILITADO`) VALUES ('PEN', 'Soles Peruanos', '0');

/**SE CARGAN LOS IDIOMAS DE USO**/
INSERT INTO `isoft_midiomas` (`COD_IDIOMA`, `NOMBRE_IDIOMA`, `HABILITADO`) VALUES ('ES', 'ESPAÑOL', '1');
INSERT INTO `isoft_midiomas` (`COD_IDIOMA`, `NOMBRE_IDIOMA`, `HABILITADO`) VALUES ('EN', 'INGLES', '1');
INSERT INTO `isoft_midiomas` (`COD_IDIOMA`, `NOMBRE_IDIOMA`, `HABILITADO`) VALUES ('IT', 'ITALIANO', '1');

/**SE CARGAN LOS ESTILOS DE USO DE LA APLICACION**/
INSERT INTO `isoft_mestilos` (`COD_ESTILO`, `NOMBRE_ESTILO`, `HABILITADO`) VALUES ('ISOFT-BLACK', 'ESTILO OSCURO', '1');
INSERT INTO `isoft_mestilos` (`COD_ESTILO`, `NOMBRE_ESTILO`, `HABILITADO`) VALUES ('ISOFT-GRAY', 'ESTILO GRIS', '1');
INSERT INTO `isoft_mestilos` (`COD_ESTILO`, `NOMBRE_ESTILO`, `HABILITADO`) VALUES ('ISOFT-LIGTH', 'ESTILO BLANCO', '1');

/**SE CARGAN LOS ESTILOS DE USO DE LA APLICACION**/
INSERT INTO `isoft_mcanales` (`COD_CANAL`, `NOMBRE_CANAL`, `HABILITADO`) VALUES ('APP-AN', 'ANDROID', '1');
INSERT INTO `isoft_mcanales` (`COD_CANAL`, `NOMBRE_CANAL`, `HABILITADO`) VALUES ('APP-IOS', 'IOS', '1');
INSERT INTO `isoft_mcanales` (`COD_CANAL`, `NOMBRE_CANAL`, `HABILITADO`) VALUES ('WEB', 'WEB', '1');
INSERT INTO `isoft_mcanales` (`COD_CANAL`, `NOMBRE_CANAL`, `HABILITADO`) VALUES ('WEB-SERVICE', 'SERVICIO WEB', '1');

/**SE CARGAN LOS PAISES DE USO DE LA APLICACION**/
INSERT INTO `isoft_mpaises` (`COD_PAIS`, `NOMBRE_PAIS`,`HABILITADO`) VALUES ('CO', 'COLOMBIA','1');

/**SE CARGAN LOS BANCOS ASOCIADOS A CADA PAIS**/
INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '0', 'BANCO DE LA REPUBLICA', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '1', 'BANCO DE BOGOTA', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '2', 'BANCO POPULAR', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '6', 'BANCO CORP BANCA COLOMBIA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '7', 'BANCOLOMBIA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '9', 'CITY BANK COLOMBIA', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '10', 'BANCO GNB COLOMBIA S.A', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '12', 'BANCO GNB SUDAMERIS COLOMBIA', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '13', 'BBVA COLOMBIA', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '14', 'HELM BANK', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '19', 'RED MULTIBANCA COLPATRIA S.A', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '23', 'BANCO OCCIDENTE', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '31', 'BANCO DE COMERCIO EXTERIOR DE COLOMBIA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '32', 'BANCO CAJA SOCIAL -BCSC S.A', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '40', 'BANCO AGRARIO DE COLOMBIA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '51', 'BANCO DAVIVIENDA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '52', 'BANCO AVVILLAS', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '53', 'BANCO WWB S.A', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '58', 'BANCO PROCREDIT', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '59', 'BANCAMIA', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '60', 'BANCO PICHINCHA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '61', 'BANCOOMEVA', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '62', 'BANCO FALABELLA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '63', 'BANCO FINANDINA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '65', 'BANCO SANTANDER DE NEGOCIOS COLOMBIA S.A.', '1');

INSERT INTO `isoft_mbancos` (`ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) 
	VALUES ((SELECT pais.ID_PAIS FROM isoft_mpaises pais where pais.cod_pais='CO'), '66', 'BANCO COOPERATIVO COOPCENTRAL', '1');


/*Se crea el perfil administrador*/
INSERT INTO `isoft_perfiles` (`NOMBRE_PERFIL`, `ADMINISTRADOR`, `HABILITADO`) VALUES ('ADMINISTRADOR', '1', '1');

/* Se crea el parametro de administracion de clientes */

INSERT INTO isoft_parametros (ID_PARAMETRO, DESCRIPCION, VALOR, TIPO_DATO, NOMBRE,GRUPO) VALUES ('1', 'Parametro que define el servicio a prestar', 'SERVICIO_NNITA', 'string', 'SERVICIO_CLIENTE', 'Administracion');

/*Se confirman las transacciones*/
commit;