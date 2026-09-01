-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.30 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

-- --------------------------------------------------------
-- Flyway Migration: V1__schema_libreria.sql
-- --------------------------------------------------------

-- 1. TABLA: corrientes_literarias
CREATE TABLE IF NOT EXISTS `corrientes_literarias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(1000) DEFAULT NULL,
  `caracteristicas` varchar(255) DEFAULT NULL,
  `fundamentos` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. TABLA: generos_literarios
CREATE TABLE IF NOT EXISTS `generos_literarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. TABLA: subgeneros
CREATE TABLE IF NOT EXISTS `subgeneros` (
  `genero_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd590wdl0ewgldn2ah1sf26bxq` (`genero_id`),
  CONSTRAINT `FKd590wdl0ewgldn2ah1sf26bxq` FOREIGN KEY (`genero_id`) REFERENCES `generos_literarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. TABLA: libro
CREATE TABLE IF NOT EXISTS `libro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `precio` decimal(38,2) DEFAULT NULL,
  `ejemplares` int(11) DEFAULT NULL,
  `anio_publicacion` int(11) DEFAULT NULL,
  `sinopsis` varchar(1000) DEFAULT NULL,
  `imagen_nombre` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `corriente_id` bigint(20) DEFAULT NULL,
  `subgenero_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKehuya6b4bxgkc4ru5wcf5njgr` (`isbn`),
  KEY `FKq93io4vty64j82rti8cw9ord7` (`corriente_id`),
  KEY `FKnjokqpe8mojet346ndstl8sno` (`subgenero_id`),
  CONSTRAINT `FKnjokqpe8mojet346ndstl8sno` FOREIGN KEY (`subgenero_id`) REFERENCES `subgeneros` (`id`),
  CONSTRAINT `FKq93io4vty64j82rti8cw9ord7` FOREIGN KEY (`corriente_id`) REFERENCES `corrientes_literarias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. TABLA: libros_analisis
CREATE TABLE IF NOT EXISTS `libros_analisis` (
  `libro_id` bigint(20) NOT NULL,
  `eje_psicologico` text,
  `introduccion_teorica` text,
  `mapa_sensaciones` text,
  `sustrato_filosofico` text,
  PRIMARY KEY (`libro_id`),
  CONSTRAINT `FKtfk2kl6l05mtjuqv0gbln0f2n` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. TABLA: usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKm2dvbwfge291euvmk6vkkocao` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7. TABLA: roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` enum('ADMIN','INVITED','USER') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 8. TABLA: user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK2chxp26bnpqjibydrikgq4t9e` (`user_id`),
  CONSTRAINT `FK2chxp26bnpqjibydrikgq4t9e` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 9. TABLA: orden
CREATE TABLE IF NOT EXISTS `orden` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `fecha_modificacion` datetime(6) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orden_usuario` (`usuario_id`),
  CONSTRAINT `FK_orden_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 10. TABLA: detalle_orden
CREATE TABLE IF NOT EXISTS `detalle_orden` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(19,2) DEFAULT NULL,
  `subtotal` decimal(19,2) DEFAULT NULL,
  `libro_id` bigint(20) DEFAULT NULL,
  `orden_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3hxkhre90yxh1m5p4ycq0t64` (`libro_id`),
  KEY `FKqdjy4ifpng402x0bnpx9etujp` (`orden_id`),
  CONSTRAINT `FK3hxkhre90yxh1m5p4ycq0t64` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`),
  CONSTRAINT `FKqdjy4ifpng402x0bnpx9etujp` FOREIGN KEY (`orden_id`) REFERENCES `orden` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;