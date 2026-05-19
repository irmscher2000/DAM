-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.7.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para concesionario
CREATE DATABASE IF NOT EXISTS `concesionario` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `concesionario`;

-- Volcando estructura para tabla concesionario.propietarios
CREATE TABLE IF NOT EXISTS `propietarios` (
  `DNI` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla concesionario.propietarios: ~3 rows (aproximadamente)
DELETE FROM `propietarios`;
INSERT INTO `propietarios` (`DNI`, `nombre`) VALUES
	('12345678A', 'Eugen'),
	('12345678B', 'Pepe'),
	('12345678C', 'Paco');

-- Volcando estructura para tabla concesionario.vehiculos
CREATE TABLE IF NOT EXISTS `vehiculos` (
  `matricula` varchar(10) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `km` int(11) DEFAULT 0,
  `precio` decimal(8,2) NOT NULL,
  `DNI_propietario` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`matricula`),
  KEY `DNI_propietario` (`DNI_propietario`),
  CONSTRAINT `vehiculos_ibfk_1` FOREIGN KEY (`DNI_propietario`) REFERENCES `propietarios` (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla concesionario.vehiculos: ~8 rows (aproximadamente)
DELETE FROM `vehiculos`;
INSERT INTO `vehiculos` (`matricula`, `marca`, `modelo`, `km`, `precio`, `DNI_propietario`) VALUES
	('ABC1111', 'Opel', 'Zafira', 600000, 20000.00, '12345678A'),
	('ABC1112', 'Toyota', 'Avensis', 35000, 20500.00, '12345678B'),
	('ABC1113', 'Toyota', 'Yaris', 0, 16500.00, NULL),
	('ABC1114', 'Toyota', 'Navarra', 5000, 25500.00, '12345678A'),
	('ABC1115', 'Opel', 'Astra', 125000, 8500.00, '12345678A'),
	('ABC1116', 'Toyota', 'Celica', 235000, 20500.00, '12345678B'),
	('ABC1117', 'Toyota', 'Avensis', 35000, 20500.00, '12345678C'),
	('ABC1118', 'Nissan', 'Primera', 0, 27990.00, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
