-- --------------------------------------------------------
-- Host:                         168.232.165.245
-- Versión del servidor:         5.5.54-0ubuntu0.12.04.1 - (Ubuntu)
-- SO del servidor:              debian-linux-gnu
-- HeidiSQL Versión:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para inf40
CREATE DATABASE IF NOT EXISTS `inf40` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `inf40`;

-- Volcando estructura para tabla inf40.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `rut` varchar(10) NOT NULL,
  `correo` varchar(30) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inf40.empleados
CREATE TABLE IF NOT EXISTS `empleados` (
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `rut` varchar(10) NOT NULL,
  `id_local` varchar(30) NOT NULL,
  `contraseña` varchar(30) NOT NULL,
  `admin` varchar(9) NOT NULL,
  PRIMARY KEY (`rut`),
  KEY `id_local` (`id_local`),
  CONSTRAINT `id_local` FOREIGN KEY (`id_local`) REFERENCES `locales` (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla inf40.locales
CREATE TABLE IF NOT EXISTS `locales` (
  `id_local` varchar(5) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `region` varchar(25) NOT NULL,
  PRIMARY KEY (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
empleadosempleados