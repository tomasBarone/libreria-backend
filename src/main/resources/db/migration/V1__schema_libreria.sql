-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.30 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla libreria.corrientes_literarias
CREATE TABLE IF NOT EXISTS `corrientes_literarias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(1000) DEFAULT NULL,
  `caracteristicas` varchar(255) DEFAULT NULL,
  `fundamentos` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.detalle_orden
CREATE TABLE IF NOT EXISTS `detalle_orden` (
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(19,2) DEFAULT NULL,
  `subtotal` decimal(19,2) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `libro_id` bigint(20) DEFAULT NULL,
  `orden_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3hxkhre90yxh1m5p4ycq0t64` (`libro_id`),
  KEY `FKqdjy4ifpng402x0bnpx9etujp` (`orden_id`),
  CONSTRAINT `FK3hxkhre90yxh1m5p4ycq0t64` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`),
  CONSTRAINT `FKqdjy4ifpng402x0bnpx9etujp` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.generos_literarios
CREATE TABLE IF NOT EXISTS `generos_literarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.libro
CREATE TABLE IF NOT EXISTS `libro` (
  `anio_publicacion` int(11) DEFAULT NULL,
  `ejemplares` int(11) DEFAULT NULL,
  `precio` decimal(38,2) DEFAULT NULL,
  `corriente_id` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subgenero_id` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `autor` varchar(255) NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  `sinopsis` varchar(1000) DEFAULT NULL,
  `imagen_nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKehuya6b4bxgkc4ru5wcf5njgr` (`isbn`),
  KEY `FKq93io4vty64j82rti8cw9ord7` (`corriente_id`),
  KEY `FKnjokqpe8mojet346ndstl8sno` (`subgenero_id`),
  CONSTRAINT `FKnjokqpe8mojet346ndstl8sno` FOREIGN KEY (`subgenero_id`) REFERENCES `subgeneros` (`id`),
  CONSTRAINT `FKq93io4vty64j82rti8cw9ord7` FOREIGN KEY (`corriente_id`) REFERENCES `corrientes_literarias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.libros_analisis
CREATE TABLE IF NOT EXISTS `libros_analisis` (
  `libro_id` bigint(20) NOT NULL,
  `eje_psicologico` text,
  `introduccion_teorica` text,
  `mapa_sensaciones` text,
  `sustrato_filosofico` text,
  PRIMARY KEY (`libro_id`),
  CONSTRAINT `FKtfk2kl6l05mtjuqv0gbln0f2n` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` enum('ADMIN','INVITED','USER') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.subgeneros
CREATE TABLE IF NOT EXISTS `subgeneros` (
  `genero_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd590wdl0ewgldn2ah1sf26bxq` (`genero_id`),
  CONSTRAINT `FKd590wdl0ewgldn2ah1sf26bxq` FOREIGN KEY (`genero_id`) REFERENCES `generos_literarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK2chxp26bnpqjibydrikgq4t9e` (`user_id`),
  CONSTRAINT `FK2chxp26bnpqjibydrikgq4t9e` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla libreria.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKm2dvbwfge291euvmk6vkkocao` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
