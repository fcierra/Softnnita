-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.17-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando datos para la tabla isoftnnita.isoft_bitacora: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_bitacora` DISABLE KEYS */;
/*!40000 ALTER TABLE `isoft_bitacora` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_detalle_bitacora: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_detalle_bitacora` DISABLE KEYS */;
/*!40000 ALTER TABLE `isoft_detalle_bitacora` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_mbancos: ~26 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_mbancos` DISABLE KEYS */;
INSERT INTO `isoft_mbancos` (`ID_BANCO`, `ID_PAIS`, `COD_BANCO`, `NOMBRE_BANCO`, `HABILITADO`) VALUES
	(1, 1, '0', 'BANCO DE LA REPUBLICA', 1),
	(2, 1, '1', 'BANCO DE BOGOTA', 1),
	(7, 1, '10', 'BANCO GNB COLOMBIA S.A', 1),
	(8, 1, '12', 'BANCO GNB SUDAMERIS COLOMBIA', 1),
	(9, 1, '13', 'BBVA COLOMBIA', 1),
	(10, 1, '14', 'HELM BANK', 1),
	(11, 1, '19', 'RED MULTIBANCA COLPATRIA S.A', 1),
	(3, 1, '2', 'BANCO POPULAR', 1),
	(12, 1, '23', 'BANCO OCCIDENTE', 1),
	(13, 1, '31', 'BANCO DE COMERCIO EXTERIOR DE COLOMBIA S.A.', 1),
	(14, 1, '32', 'BANCO CAJA SOCIAL -BCSC S.A', 1),
	(15, 1, '40', 'BANCO AGRARIO DE COLOMBIA S.A.', 1),
	(16, 1, '51', 'BANCO DAVIVIENDA S.A.', 1),
	(17, 1, '52', 'BANCO AVVILLAS', 1),
	(18, 1, '53', 'BANCO WWB S.A', 1),
	(19, 1, '58', 'BANCO PROCREDIT', 1),
	(20, 1, '59', 'BANCAMIA', 1),
	(4, 1, '6', 'BANCO CORP BANCA COLOMBIA S.A.', 1),
	(21, 1, '60', 'BANCO PICHINCHA S.A.', 1),
	(22, 1, '61', 'BANCOOMEVA', 1),
	(23, 1, '62', 'BANCO FALABELLA S.A.', 1),
	(24, 1, '63', 'BANCO FINANDINA S.A.', 1),
	(25, 1, '65', 'BANCO SANTANDER DE NEGOCIOS COLOMBIA S.A.', 1),
	(26, 1, '66', 'BANCO COOPERATIVO COOPCENTRAL', 1),
	(5, 1, '7', 'BANCOLOMBIA S.A.', 1),
	(6, 1, '9', 'CITY BANK COLOMBIA', 1);
/*!40000 ALTER TABLE `isoft_mbancos` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_mcanales: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_mcanales` DISABLE KEYS */;
INSERT INTO `isoft_mcanales` (`ID_CANAL`, `COD_CANAL`, `NOMBRE_CANAL`, `HABILITADO`) VALUES
	(1, 'APP-AN', 'ANDROID', 1),
	(2, 'APP-IOS', 'IOS', 1),
	(3, 'WEB', 'WEB', 1),
	(4, 'WEB-SERVICE', 'SERVICIO WEB', 1);
/*!40000 ALTER TABLE `isoft_mcanales` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_menu: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_menu` DISABLE KEYS */;
INSERT INTO `isoft_menu` (`ID_MENU`, `MENU_LABEL`, `ORDEN`, `REF_SECURITY`, `HABILITADO`) VALUES
	(1, 'Inicio', 1, 'welcome', 1),
	(2, 'Perfil Usuario', 2, 'profile_user', 1),
	(3, 'Configuracion', 3, 'config_panel', 1),
	(4, 'Gestion Usuarios', 4, 'config_users', 1);
/*!40000 ALTER TABLE `isoft_menu` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_menus_perfil: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_menus_perfil` DISABLE KEYS */;
INSERT INTO `isoft_menus_perfil` (`ID_MENU_PERFIL`, `ID_MENU`, `ID_PERFIL`) VALUES
	(1, 2, 1),
	(2, 3, 1),
	(3, 2, 2),
	(4, 4, 1);
/*!40000 ALTER TABLE `isoft_menus_perfil` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_menu_item: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_menu_item` DISABLE KEYS */;
INSERT INTO `isoft_menu_item` (`ID_MENU_ITEM`, `MENU_LINK`, `MENU_LABEL`, `ID_MENU_PADRE`, `ID_MENU_HIJO`, `ORDEN`, `REF_SECURITY`, `HABILITADO`) VALUES
	(1, '/secure/profiler/profile_actividad.xhtml', 'Registro de Actividad', 2, 0, 2, 'profileBitacora', 1),
	(2, '/secure/profiler/profile_misdatos.xhtml', 'Mis Datos', 2, 0, 1, 'profileMisDatos', 1),
	(3, '/secure/configuration/config_permisions.xhtml', 'Configurar Permisos', 3, 0, 1, 'configPermisos', 1),
	(4, '/secure/configuration/config_menus.xhtml', 'Configurar Menus', 3, 0, 2, 'configMenus', 1),
	(5, '/secure/gestion/usuarios/config_usuarios.xhtml', 'Crear Usuarios', 4, 0, 1, 'configUsuarios', 1);
/*!40000 ALTER TABLE `isoft_menu_item` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_mestilos: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_mestilos` DISABLE KEYS */;
INSERT INTO `isoft_mestilos` (`ID_ESTILO`, `COD_ESTILO`, `NOMBRE_ESTILO`, `HABILITADO`) VALUES
	(1, 'ISOFT-BLACK', 'ESTILO OSCURO', 1),
	(2, 'ISOFT-GRAY', 'ESTILO GRIS', 1),
	(3, 'ISOFT-LIGTH', 'ESTILO BLANCO', 1);
/*!40000 ALTER TABLE `isoft_mestilos` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_meventos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_meventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `isoft_meventos` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_midiomas: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_midiomas` DISABLE KEYS */;
INSERT INTO `isoft_midiomas` (`ID_IDIOMA`, `COD_IDIOMA`, `NOMBRE_IDIOMA`, `HABILITADO`) VALUES
	(2, 'EN', 'INGLES', 1),
	(1, 'ES', 'ESPAÑOL', 1),
	(3, 'IT', 'ITALIANO', 1);
/*!40000 ALTER TABLE `isoft_midiomas` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_mmonedas: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_mmonedas` DISABLE KEYS */;
INSERT INTO `isoft_mmonedas` (`ID_MONEDA`, `COD_MONEDA`, `NOMBRE_MONEDA`, `HABILITADO`) VALUES
	(1, 'COP', 'Pesos Colombianos', 1),
	(3, 'PEN', 'Soles Peruanos', 0),
	(2, 'US', 'Dolares Americanos', 0);
/*!40000 ALTER TABLE `isoft_mmonedas` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_mpaises: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_mpaises` DISABLE KEYS */;
INSERT INTO `isoft_mpaises` (`ID_PAIS`, `COD_PAIS`, `NOMBRE_PAIS`, `HABILITADO`) VALUES
	(1, 'CO', 'COLOMBIA', 1);
/*!40000 ALTER TABLE `isoft_mpaises` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_mtipos_pagos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_mtipos_pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `isoft_mtipos_pagos` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_pagos_cliente: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_pagos_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `isoft_pagos_cliente` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_pagos_referencias: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_pagos_referencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `isoft_pagos_referencias` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_parametros: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_parametros` DISABLE KEYS */;
INSERT INTO `isoft_parametros` (`ID_PARAMETRO`, `DESCRIPCION`, `VALOR`, `TIPO_DATO`, `NOMBRE`, `GRUPO`) VALUES
	(1, 'Parametro que define el servicio a prestar', 'SERVICIO_NNITA', 'string', 'SERVICIO_CLIENTE', 'Administracion');
/*!40000 ALTER TABLE `isoft_parametros` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_perfiles: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_perfiles` DISABLE KEYS */;
INSERT INTO `isoft_perfiles` (`ID_PERFIL`, `NOMBRE_PERFIL`, `ADMINISTRADOR`, `HABILITADO`) VALUES
	(1, 'ADMINISTRADOR', 1, 1),
	(2, 'COORDINADOR', 0, 1);
/*!40000 ALTER TABLE `isoft_perfiles` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_permisos: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_permisos` DISABLE KEYS */;
INSERT INTO `isoft_permisos` (`ID_PERMISO`, `ID_MENU_ITEM`, `ID_PERFIL`) VALUES
	(1, 1, 1),
	(2, 2, 1),
	(3, 3, 1),
	(4, 1, 2),
	(5, 2, 2),
	(6, 5, 1);
/*!40000 ALTER TABLE `isoft_permisos` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_usuarios` DISABLE KEYS */;
INSERT INTO `isoft_usuarios` (`ID_USUARIO`, `LOGIN`, `CLAVE`, `NOMBRE_USUARIO`, `APELLIDO_USUARIO`, `EMAIL`, `SEXO`, `FECHA_REGISTRO`, `FECHA_ULTIMA_VIS`, `PERFIL_DEFAULT`, `HABILITADO`) VALUES
	(24, 'admin', '$2a$10$PhEwZb3dluIosQurVvzpaubbwpDS.jV1sNcwpRb8H6zAREhyCtdCq', 'Administrador', 'Adminsitrador', 'admin@admin.com', 'M', '2018-07-13', '2018-07-13', 1, 1),
	(25, 'yaher', '$2a$10$NbGiIMmixoFePzrOnptrcOYMIWRtlY6mYgCqyqn9.x6on9Kc9JtYK', 'Yaher', 'Carrillo', 'yahercarrillo@gmail.com', 'M', '2018-08-26', '2018-08-26', 2, 1);
/*!40000 ALTER TABLE `isoft_usuarios` ENABLE KEYS */;

-- Volcando datos para la tabla isoftnnita.isoft_usuario_perfil: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `isoft_usuario_perfil` DISABLE KEYS */;
INSERT INTO `isoft_usuario_perfil` (`ID_USUARIO_PERFIL`, `ID_USUARIO`, `ID_PERFIL`, `HABILITADO`) VALUES
	(1, 24, 1, 1),
	(2, 25, 2, 1);
/*!40000 ALTER TABLE `isoft_usuario_perfil` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
