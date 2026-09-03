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

-- Volcando datos para la tabla libreria.corrientes_literarias: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `corrientes_literarias` DISABLE KEYS */;
INSERT IGNORE INTO `corrientes_literarias` (`id`, `descripcion`, `caracteristicas`, `fundamentos`, `nombre`, `periodo`) VALUES
	(1, 'Es la base de la cultura occidental (Grecia y Roma)', 'La busqueda de la perfeccion, la armonia y el equilibrio', 'Uso de la mitologia, respeto por las unidades de tiempo y espacio y un fin didactico o moral', 'Clasicismo', 'V a.C -V d.C'),
	(2, 'Dominada por la cosmovision teocentrica (Dios como centro)', 'La transmision de valores religiosos y caballerescos.', 'Gran parte es anonima y oral(juglares). Aparecen los cantares de gesta y los cuentos morales', 'Literatura Medieval', 'V-XV'),
	(3, 'El paso del teocentrismo al antropecentrismo (el hombre como centro)', 'El retorno a los clasicos grecorromanos y la valoracion de la naturaleza y el amor idealizado', 'Lenguaje sencillo y claro, optimismo y el uso del soneto', 'Renacimiento', 'XV-XVI'),
	(4, 'Una epoca de crisis, pesimismo y  desengaño', 'Uso de las figuras retoricas complejas (metaforas, hiperbaton). Se duvude en Culteranismo (forma) y Conceptismo (contenido/ideas)', 'La ornamentacion excesiva y la complejidad intelectual', 'Barroco', 'XVII'),
	(5, 'El Siglo de las Luces, La razon se impone sobre los sentimientos', 'Rigidez en las normas, rechazo a lo fantastico y predominio del ensayo y la fabula', 'La utilidad pedagogica. La literatura debe enseñar no solo entretener', 'Neoclasicismo', 'XVIII'),
	(6, 'El gran grito de libertad y rebeldia contra la razon', ' Culto al individuo,fascinacion por lo oscuro y lo sobrenatural, y la naturaleza como reflejo del estado de animo del autor', 'El predominio del Yo, los sentimientos desbordados y la evasion de mundos lejanos o pasados', 'Romanticismo', 'XIX - Primera Mitad'),
	(7, 'Como reaccion al excesp sentimental del Romanticismo, surge el deseo de retratar la realidad tal cual es. ', 'Descripciones minuciosas, lenguaje cotidiano y enofque en problemas sociales(pobreza,burguesia, injusticia. El NATURALISMO es un realismo extrem que cree que el hombre esta determiando por su herencia y su entorno', 'La observacion casi cientifica de la sociedad', 'Realismo y Naturalismo', 'XIX - Segunda Mitad'),
	(8, 'Surge en Hispanoamerica con Ruben Dario', 'Uso de simbolos (como el cisne), exotismo(palacios, princesas, Oriente) y una musicalidad muy marcada en el verso', 'La busqueda de la belleza absoluta y el refinamiento estetico', 'Modernismo', 'Finales del XIX - Principios del XX'),
	(9, 'Conjunto de movimientos(Ismos: Surrealismo, Futurismo, Dadaismo) tras la primera guerra mundial', 'Alteracion del orden logico, versolibrismo, caligramas y exploracion del subconciente', 'La ruptura total con el pasado y la experimentacion', 'Vanguardismo', 'Principios del XX'),
	(10, 'Libros Contemporaneos', 'Fragmentacion del tiempo, multiplicidad de narradores y la mezcla de lo fantastico con lo cotidiano de forma natural', 'El juego con la estructura narrativa y el Realismo Magico', 'Posmodernidad', 'Mitad del XX - Actualidad');
/*!40000 ALTER TABLE `corrientes_literarias` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.detalle_orden: ~23 rows (aproximadamente)
/*!40000 ALTER TABLE `detalle_orden` DISABLE KEYS */;
INSERT IGNORE INTO `detalle_orden` (`cantidad`, `precio_unitario`, `subtotal`, `id`, `libro_id`, `orden_id`) VALUES
	(1, 50000.00, 50000.00, 1, 15, 1),
	(1, 50000.00, 50000.00, 2, 15, 2),
	(1, 50000.00, 50000.00, 3, 15, 3),
	(1, 50000.00, 50000.00, 4, 15, 4),
	(1, 50000.00, 50000.00, 5, 15, 5),
	(1, 50000.00, 50000.00, 6, 13, 6),
	(1, 80000.00, 80000.00, 7, 2, 7),
	(1, 80000.00, 80000.00, 8, 2, 8),
	(1, 80000.00, 80000.00, 9, 2, 9),
	(1, 80000.00, 80000.00, 10, 2, 10),
	(1, 80000.00, 80000.00, 11, 2, 11),
	(1, 80000.00, 80000.00, 12, 2, 12),
	(1, 80000.00, 80000.00, 13, 2, 13),
	(1, 80000.00, 80000.00, 14, 2, 14),
	(1, 80000.00, 80000.00, 15, 2, 15),
	(1, 80000.00, 80000.00, 16, 2, 16),
	(1, 80000.00, 80000.00, 17, 2, 17),
	(1, 80000.00, 80000.00, 18, 2, 18),
	(1, 50000.00, 50000.00, 19, 16, 19),
	(1, 30000.00, 30000.00, 20, 17, 1),
	(1, 30000.00, 30000.00, 21, 17, 2),
	(1, 80000.00, 80000.00, 22, 2, 3),
	(1, 65000.00, 65000.00, 23, 19, 4);
/*!40000 ALTER TABLE `detalle_orden` ENABLE KEYS */;

-- Volcando estructura para tabla libreria.flyway_schema_history
CREATE TABLE IF NOT EXISTS `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.flyway_schema_history: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT IGNORE INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '0', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2026-08-27 22:33:42', 0, 1),
	(2, '1', 'schema libreria', 'SQL', 'V1__schema_libreria.sql', 606887046, 'root', '2026-08-31 23:34:16', 684, 1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;

-- Volcando estructura para tabla libreria.generos_literarios
CREATE TABLE IF NOT EXISTS `generos_literarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.generos_literarios: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `generos_literarios` DISABLE KEYS */;
INSERT IGNORE INTO `generos_literarios` (`id`, `descripcion`, `nombre`) VALUES
	(1, 'Relato de hechos reales o imaginarios', 'Narrativo'),
	(2, 'Expresión de sentimientos subjetivos generalmente a traves del verso', 'Lírico'),
	(3, 'Obras creadas para ser representadas ante un publico mediante el dialogo de los personajes', 'Dramático'),
	(4, 'Su finalidad principal es la enseñanza o la divulgacion de ideas', 'Didactico');
/*!40000 ALTER TABLE `generos_literarios` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.libro: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT IGNORE INTO `libro` (`anio_publicacion`, `ejemplares`, `precio`, `corriente_id`, `fecha_creacion`, `fecha_modificacion`, `id`, `subgenero_id`, `version`, `autor`, `isbn`, `titulo`, `sinopsis`, `imagen_nombre`) VALUES
	(1890, 21, 80000.00, 6, '2026-06-16 20:09:37.244329', '2026-08-27 22:25:14.154421', 2, 1, 12, 'Oscar Wilde', '978881236', 'El Retrato De Dorian Gray', 'Prueba', 'https://res.cloudinary.com/px5zilji/image/upload/v1786046729/dorianGray_f9huyh.jpg'),
	(1970, 2, 50000.00, 9, '2026-06-25 17:42:14.926316', '2026-07-23 02:55:04.609428', 13, 2, 1, 'Jorge Luis Borges', '978881207', 'El Aleph', 'p', 'https://res.cloudinary.com/px5zilji/image/upload/v1786046741/aleph_aqi0nh.jpg'),
	(1933, 3, 50000.00, 9, '2026-07-20 19:57:36.680304', '2026-08-26 23:00:53.343236', 15, 8, 12, 'Federico Garcia Lorca', '978111236', 'Bodas de Sangre', 'Esta obra es una proeza de ingeniería poética que toma un hecho real (el crimen de Níjar de 1928, donde una novia se fugó con su amante el mismo día de su boda civil, desatando una venganza de sangre) y lo eleva a la categoría de mito universal. Lorca despoja el suceso de su condición de crónica periodística o policial para construir un sistema trágico donde los personajes son prisioneros de fuerzas cósmicas e históricas incontrolables.', 'https://res.cloudinary.com/px5zilji/image/upload/v1786046806/bodasDeSangre_yfjway.jpg'),
	(1866, 4, 50000.00, 7, '2026-08-03 12:57:42.282588', '2026-08-06 17:42:55.796621', 16, 1, 1, 'Fiodor Dostoievsky', '', 'Crimen y Castigo', 'En los barrios más pobres y claustrofóbicos de San Petersburgo, Rodión Románovich Raskólnikov, un exestudiante de Derecho inteligente pero sumido en la miseria extrema y la soledad, concibe una teoría peligrosa: divide a la humanidad en dos categorías, los hombres "ordinarios" (que deben obedecer las leyes) y los "extraordinarios" (aquellos que, como Napoleón, tienen el derecho y el deber moral de transgredir la ley para traer un bien mayor a la sociedad).', 'https://res.cloudinary.com/px5zilji/image/upload/v1785772658/catalogo_libros/wudphu70sqwolobmyi6h.jpg'),
	(1984, 3, 30000.00, 10, '2026-08-26 22:06:21.908159', '2026-08-27 22:20:59.315335', 17, 1, 3, 'Milan Kundera', '978111237', 'La Insoportable Levedad Del Ser', 'Ambientada principalmente en Praga durante la Primavera de 1968 y la posterior invasión soviética a Checoslovaquia, la novela entrelaza las vidas de cuatro personajes cuyas filosofías de vida colisionan. Kundera interrumpe la narrativa para ensayar sobre el Especio Histórico, la música de Beethoven, la psicología del Kitsch y la paradoja del eterno retorno de Nietzsche.', 'https://res.cloudinary.com/px5zilji/image/upload/v1787792777/catalogo_libros/tudcmdz6afdyhdiffuvz.jpg'),
	(1945, 5, 20000.00, 10, '2026-08-27 15:19:22.391490', NULL, 18, 1, 0, 'George Orwell', '978881239', 'Rebelion En La Granja', 'Cansados de los maltratos y la explotación del granjero Mr. Jones, los animales de la Granja Manor —liderados por la elocuencia e inteligencia de los cerdos— llevan a cabo una revolución violenta para tomar el control de la propiedad. Bajo la promesa de una sociedad libre, justa e igualitaria basada en los principios del Animalismo, expulsan a los humanos y establecen sus propias reglas', 'https://res.cloudinary.com/px5zilji/image/upload/v1787854759/catalogo_libros/mvtfssymckrjjpdjr3nc.jpg'),
	(1942, 4, 65000.00, 10, '2026-08-27 18:17:53.315937', '2026-08-27 22:26:23.597373', 19, 10, 1, 'Albert Camus', '978881230', 'El Mito De Sísifo', 'El ensayo comienza con una de las sentencias más célebres de la filosofía del siglo XX: "No hay más que un problema filosófico verdaderamente serio: el suicidio". Camus plantea que la pregunta fundamental de la existencia es determinar si la vida vale o no la pena de ser vivida tras tomar conciencia de la falta de sentido del mundo.', 'https://res.cloudinary.com/px5zilji/image/upload/v1787865470/catalogo_libros/jgdcb1f0q9wbkchsd2tn.jpg'),
	(1774, 5, 80000.00, 6, '2026-09-01 21:44:20.301800', NULL, 21, 1, 0, 'Johann Wolfgang von Goethe', '978881235', 'Las Penas Del Joven Werther', 'Werther es un joven artista con una sensibilidad desbordante que se muda a un valle idílico para pintar y leer. Allí conoce a Lotte, una mujer que encarna su ideal de belleza y pureza, pero que ya está comprometida con Albert, un hombre razonable, estable y pragmático.', 'https://res.cloudinary.com/px5zilji/image/upload/v1788309854/catalogo_libros/bdk7rj1fibo0gbgsgnyl.jpg'),
	(1880, 5, 200000.00, 7, '2026-09-01 21:58:05.180441', NULL, 22, 1, 0, 'Fiodor Dostoievsky', '977881230', 'Los Hermanos Karamazov', 'En Los hermanos Karamázov, última obra y síntesis monumental de su pensamiento y de su arte, desarrolla su íntimo convencimiento de la necesidad de un cambio radical en los destinos sociales y morales de la humanidad. El autor plasma un trágico cuadro de la sociedad de su tiempo y denuncia la corrupción engendrada por el poder del dinero, las pasiones incontroladas, el egoísmo y la ignominia espiritual.', 'https://res.cloudinary.com/px5zilji/image/upload/v1788310679/catalogo_libros/hhnfyja4aaxj0bhbembf.webp'),
	(1925, 3, 80000.00, 9, '2026-09-01 22:01:11.727497', NULL, 24, 1, 0, 'Franz Kakfa', '977881231', 'El Proceso', 'Josef K., un respetable empleado de un banco, es arrestado una mañana en su habitación por dos agentes. Lo curioso es que no se le comunica de qué se le acusa, ni se le lleva a una prisión real. K. sigue con su vida "normal", pero debe presentarse a interrogatorios en oficinas escondidas en desvanes lúgubres y barrios marginales.', 'https://res.cloudinary.com/px5zilji/image/upload/v1788310866/catalogo_libros/xc0siitnveb6pi5mjixe.jpg'),
	(1853, 5, 70000.00, 6, '2026-09-01 22:07:08.651755', NULL, 25, 1, 0, 'Herman Melville', '977881232', 'Bartleby, El Escribiente', 'El narrador, un abogado pragmático y acomodado, contrata a Bartleby para que trabaje como copista en su despacho. Al principio, Bartleby es un empleado modelo: silencioso, pálido y extremadamente eficiente. Sin embargo, un día, ante una petición rutinaria de revisar un documento, responde con la frase que pasó a la historia: "Preferiría no hacerlo" (I would prefer not to).', 'https://res.cloudinary.com/px5zilji/image/upload/v1788311222/catalogo_libros/buzzdqb43dkyohux6mrs.jpg');
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;

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

-- Volcando datos para la tabla libreria.libros_analisis: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `libros_analisis` DISABLE KEYS */;
INSERT IGNORE INTO `libros_analisis` (`libro_id`, `eje_psicologico`, `introduccion_teorica`, `mapa_sensaciones`, `sustrato_filosofico`) VALUES
	(2, 'El retrato como sistema de persistencia moral, la juventud eterna como condena psicológica, y el arte como espejo incorruptible de la verdad interior.', 'Desde la teoría literaria y la estética victoriana, la novela supuso un escándalo monumental y una profunda subversión de los valores de su época:\n\n**El Pacto Fáustico Estético**: A diferencia del Fausto tradicional que vende su alma al diablo a cambio de poder político o conocimiento infinito, Dorian la intercambia por la juventud eterna y la inmunidad sensorial. Su máxima aspiración es vivir la vida como si fuera una obra de arte, donde las consecuencias éticas carecen de valor frente a la intensidad de la experiencia estética.\n\n**La máscara y la duplicidad urbana**: La obra anticipa la esquizofrenia de la modernidad urbana: el respeto absoluto en la superficie social (Dorian es un aristócrata impecable, educado y filántropo) coexistiendo con los bajos fondos más sórdidos de Londres (fumaderos de opio, extorsiones y crímenes ocultos). La fachada es intachable.\n\n**El Arte como Juez Ineludible**: Wilde ironiza sobre su propia teoría del "arte inútil". Al final, el retrato demuestra que el arte no es neutral: es un espejo absoluto de la verdad interior. Por más que Dorian intente destruir el cuadro con un cuchillo para borrar las evidencias de sus actos, lo único que logra es colapsar su propio sistema vital', 'En la capa de los sujetos, la obra despliega una tragedia de alienación mental impulsada por la influencia tóxica y el narcisismo:\n\n**Dorian Gray (El Narcisismo Crónico y el Pánico a la Desintegración)**: Su motor inicial es el miedo al paso del tiempo y la pérdida de la belleza, inoculado por las palabras de Lord Henry. Al ver que sus maldades no dejan marca en su rostro, su psique experimenta una desconexión total entre la acción y la consecuencia. Sin embargo, a medida que pasan los años, su mapa emocional se degrada hacia una paranoia claustrofóbica y un terror visceral cada vez que se atreve a mirar el cuadro oculto en el altillo. Vive aterrorizado por la culpa y por la posibilidad de ser descubierto.\n\n**Lord Henry Wotton (El Hedonismo Especulativo y el Cinismo Distante):** Es el arquitecto intelectual del desastre. Su mapa de sensaciones está anestesiado por el desapego: no comete actos viles por sí mismo, sino que se dedica a inyectar ideas venenosas y teorías hedonistas en la mente maleable de Dorian para ver cómo reaccionan. Experimenta la vida como un espectador o un crítico de arte que manipula cobayas humanas desde la comodidad de su salón aristocrático.\n\n**Basil Hallward (La Devoción Estética y la Culpa Creadora)**: El pintor del retrato. Su motor es el amor platónico y la idolatría artística hacia Dorian. Su mapa emocional oscila entre la inspiración sublime y el tormento moral; es el único que percibe la monstruosidad que se está gestando bajo la superficie y el que intenta, demasiado tarde, activar un proceso de redención y arrepentimiento en el protagonista.', 'El culto al hedonismo, la disociación entre la moral y la estética, y la inevitabilidad de la culpa existencial.'),
	(13, 'prueba', 'pp', 'pp', 'prueba'),
	(15, 'La navaja como símbolo del destino violento, la personificación vanguardista de elementos abstractos (la Luna y la Muerte), y la inevitabilidad de la tragedia cuando el deseo individual rompe el código ético colectivo.', 'Desde la perspectiva de la teoría literaria y la semiótica teatral, Bodas de sangre destaca por su simetría formal y su densa red de simbolismo abstracto:\n\n**La desindividualización mítica (Arquetipos)**: A nivel de diseño de personajes, Lorca toma una decisión técnica radical: despoja a casi todos los protagonistas de nombres propios. Excepto Leonardo, los demás son definidos por su función sistémica o biológica: La Madre, La Novia, El Novio, La Suegra. No son individuos con psicología burguesa aislada; son arquetipos que encarnan fuerzas telúricas (de la tierra) y mandatos sociales ancestrales.\n\nEl quiebre de la mímesis realista (El Acto III): El valor académico formal de la obra radica en su transición estética. Los dos primeros actos operan bajo un realismo rural estilizado, pero el Acto III rompe por completo el frontend de la realidad para adentrarse en el expresionismo simbólico del bosque. Personajes alegóricos como La Luna (un leñador joven con la cara blanca) y La Mendiga (que encarna a la Muerte) dialogan y operan como directores de escena que coordinan el desenlace fatal, funcionando como el equivalente moderno del coro trágico griego.\n\nEl determinismo biológico y social (Sustrato Filosófico): La obra se apoya en una tensión trágica entre el Vitalismo irracional (el impulso de la sangre, el deseo ciego que ignora la ley humana) y el Materialismo social/económico (la boda vista como un contrato de tierras, castas y procreación). Los personajes están atrapados en un algoritmo social donde la reputación, el patrimonio y la venganza de sangre son mandatos hereditarios de los cuales es imposible desertar sin destruir el sistema entero.', 'La tragedia avanza impulsada por una atmósfera de asfixia térmica y sequedad, donde las afecciones de los personajes operan con la fijeza de imanes con polaridades enfrentadas:\n\nLa Madre (La Paranoia Traumática y el Culto al Rigor): Su motor interno es el miedo crónico a la pérdida. Vive en un estado de luto perpetuo y paranoia biológica, traumatizada por el asesinato de su esposo y su hijo mayor. Experimenta la tierra no como sustento, sino como un cementerio potencial. Su sensación dominante es la amargura y la obsesión por las armas de corte (la navaja), las cuales percibe como amenazas directas al linaje que le queda. Al final, su dolor muta en una asombrosa y fría serenidad trágica.\n\nLa Novia (La Asfixia del Deber y la Conflagración Interna): Su mapa de sensaciones está gobernado por una violenta contradicción interna. Por un lado, experimenta el deseo de cumplir con el orden social (casarse con el Novio, pacificar su entorno); por el otro, sufre la atracción física y destructiva hacia Leonardo, que ella misma describe como una fuerza que la arrastra como un río oscuro. Siente la culpa y la decencia como cadenas pesadas, viviendo en una constante agitación que colinda con la locura defensiva ante la inminencia de su boda.\n\nLeonardo (El Impulso Ciego y el Resentimiento Sordo): Es el único personaje con nombre propio porque es el vector dinámico que fractura el equilibrio del sistema. Su motor es la pasión insatisfecha y el orgullo herido. Experimenta una frustración social y económica constante (es de una casta inferior y carece de tierras). Su mapa emocional está dominado por la impaciencia y un fatalismo agresivo: sabe que su deseo por la Novia es destructivo para su propia familia, pero asume su impulso con la resignación de quien obedece a una ley natural superior a la moral humana.\n\nEl Novio (La Ilusión Confiada y la Dignidad Ultrajada): Arranca la obra bajo sensaciones de optimismo burgués, inocencia y rectitud moral. Su motor es la continuidad del orden familiar y el trabajo agrícola. No obstante, al verse traicionado en su honor y su hombría el mismo día de la boda, su mapa de sensaciones da un giro de 180 grados: la docilidad transmuta instantáneamente en la furia implacable de la casta herida, asumiendo el rol de ejecutor de la venganza con una frialdad heredada de su madre.', 'Dialéctica entre el Vitalismo Trágico (pulsión de muerte y deseo) y el Determinismo Socioeconómico (ley de la tierra y la casta).'),
	(16, 'Anhelo de confesión y aislamiento de la comunidad humana', 'La Polifonía Narrativa (Mijaíl Bajtín): Dostoyevski no utiliza al narrador para imponer una sola verdad ideológica. La novela es un concierto de voces e ideas autónomas (polifonía), donde cada personaje (Raskólnikov, Porfiri, Svidrigáilov, Luzhin) encarna una posición filosófica o social que debate de igual a igual con las demás.\n\nLa crítica al Nihilismo y Racionalismo Utilitarista: En la Rusia de 1860 cobraban fuerza las ideas del racionalismo extremo, el utilitarismo y el nihilismo occidental. Dostoyevski escribe la obra para demostrar los peligros de estas teorías: cuando la razón pura intenta suplantar la empatía, la compasión y la ley moral humana, el resultado inevitable es la deshumanización y la violencia.\n\nLa dialéctica entre el "Crimen" y el "Castigo": En el título original ruso (Prestupléniye significa literalmente "dar un paso por encima de" o "transgresión"), el delito no es solo una infracción legal, sino una ruptura espiritual con la comunidad humana. El "castigo" no es la Siberia, sino el aislamiento cósmico que experimenta el criminal tras cruzar la línea.', 'Rodión Raskólnikov (La Soberbia Dividida y la Paranoia del Aislamiento): Su mente es un campo de batalla entre el orgullo intelectual y un corazón intrínsecamente compasivo. Al intentar demostrarse que es un "Napoleón", comete el asesinato; pero al instante, su mapa emocional se desploma en fiebre, pánico y náusea. Se siente físicamente incapaz de abrazar a su madre o a su hermana, pues la culpa actúa como un Muro de Berlín en su mente que lo desconecta de la humanidad.\n\nSonia Marmeládova (La Abnegación Sagrada y la Fe Inquebrantable): Hija de un alcohólico, empujada a la prostitución para evitar que sus hermanastros mueran de hambre. Encarna el dolor asumido con dignidad. Su mapa de sensaciones no guarda rencor ni soberbia; su motor es un amor compasivo y redentor que ve a través de la monstruosidad del crimen de Raskólnikov para rescatar el alma sufriente que hay detrás.\n\nPorfiri Petrovich (El Cinismo Analítico y la Caza Psicológica): El juez de instrucción a cargo del caso. Su mapa emocional es frío, lúdico y profundamente observador. No necesita pruebas materiales inmediatas; utiliza la presión psicológica y la manipulación socrática, sabiendo que el propio intelecto y la culpa de Raskólnikov terminarán entregándolo.\n\nArkadi Svidrigáilov (El Nihilismo Absoluto y la Vaciedad Existencial): Funciona como el doble oscuro de Raskólnikov. Es un aristócrata corrompido que ha llevado el amoralismo hasta sus últimas consecuencias sin sentir culpa, pero cuyo mapa final es de un aburrimiento atroz, la perversión y la desesperación absoluta, demostrando el destino final de la vida sin valores.', 'La culpa y la fragmentación psíquica como castigo ineludible del delito'),
	(17, 'La paradoja de la libertad: el vacío que deja la ausencia de ataduras', '**Desmontaje del Eterno Retorno de Nietzsche:** Kundera abre la novela discutiendo el mito del eterno retorno. Argumenta que si el universo se repitiera infinitamente, cada acto tendría un peso insoportable. Al no existir la repetición, la vida humana se vuelve extremadamente leve, flotando en la nada, lo que resulta paradójicamente insoportable.\n\n**La Crítica al Kitsch:** El autor define el Kitsch no solo como una estética cursi, sino como una actitud ante la vida: el rechazo absoluto a admitir la mierda y lo grotesco del mundo. El Kitsch es el imperativo de sonreír a toda costa, utilizado tanto por la propaganda comunista como por la cultura de masas occidental.\n\n**Metaficción y Voz Narrativa:** Kundera interviene activamente en el texto recordándole al lector que los personajes son construcciones de su imaginación creadas para explorar situaciones existenciales específicas.\n\n**El impacto de la Historia en el individuo:** Cómo los macro-eventos geopolíticos (la ocupación de Praga) destruyen arbitrariamente las líneas de vida y las carreras profesionales de los ciudadanos comunes.\n\n**El amor como coincidencia:** La novela enfatiza cómo los grandes amores nacen de una acumulación de pequeñas casualidades (seis casualidades en el caso de Tomás y Teresa) a las que otorgamos categoría de destino.\n\n**La ilusión de la verdad única:** Demostración de cómo una misma acción o palabra es interpretada de maneras diametralmente opuestas por dos personas según sus mapas mentales (el "Diccionario de palabras incomprendidas" entre Franz y Sabina).', '**Tomás (El Observador Léger):** Dividido entre su afecto genuino por Teresa y su pulsión incontrolable por la exploración erótica sin compromiso emocional. Su evolución pasa de la levedad irresponsable a asumir el peso de sus elecciones, aceptando la degradación profesional por no retractarse de sus ideas.\n\n**Teresa (La Necesidad de Anclaje)**: Su psique está marcada por el trauma de la invasión y el cuerpo como prisión. Busca obsesivamente en Tomás la certeza de que su existencia importa y no es un mero accidente biológico.\n\n**Sabina (La Traición Perpetua):** Su mecanismo de defensa es la fuga. Cada vez que una relación o un estilo de vida amenaza con volverse definitivo, rompe con ello. Descubre al final que la levedad extrema genera un vacío insoportable.', 'La dicotomía entre la levedad de la existencia y el peso de las decisiones'),
	(18, '"Megalomanía, tiranía y paranoia en el ejercicio del mando",     "La obediencia ciega, el adoctrinamiento y la pérdida de pensamiento crítico",     "La apatía social y la resignación ante la injusticia"', '**Alegoría histórica del estalinismo:** La novela es un reflejo casi idéntico de la Revolución Rusa de 1917 y la posterior consolidación del régimen soviético. Los personajes representan figuras clave: el Viejo Mayor (Marx/Lenin), Napoleon (Stalin), Snowball (Trotsky), Boxer (la clase obrera estajanovista) y Squealer (el aparato de propaganda / Pravda).\n\n**La corrupción del lenguaje como herramienta de control:** La célebre síntesis final del mandamiento —"Todos los animales son iguales, pero algunos animales son más iguales que otros"— ilustra cómo el poder autoritario deforma la semántica y la lógica para encubrir la desigualdad radical bajo un discurso de equidad.\n\n**Crítica universal al totalitarismo:** Aunque escrita pensando en la URSS, la obra funciona como una advertencia atemporal sobre los mecanismos de cualquier régimen que concentre el poder absoluto sin controles, contrapesos ni libertad de prensa.', '**Napoleon (La Dictadura Fría y Autocrática):** Su psique no busca la teoría o la utopía, sino la dominación táctica y el control de los recursos. Governa mediante el miedo, el aislamiento de sus oponentes y el adiestramiento de las fuerzas de represión.\n\n**Squealer (El Propagandista / Alterador de la Verdad):** Carece de convicciones morales; su función es la racionalización de los abusos del líder. Utiliza datos falsos, sofismas y la amenaza constante del "retorno del enemigo" (Mr. Jones) para desarmar el juicio crítico de los demás.\n\n**Boxer, el caballo de tiro (La Devoción Ingenua y el Trabajo Duro):** Encarna la lealtad incondicional. Sus lemas son "Trabajaré más duro" y "Napoleon siempre tiene razón". Su mapa emocional se guía por el deber y la ingenuidad, volviéndose la víctima trágica del sistema cuando deja de ser útil.', 'Socialismo Democrático / Anti-totalitarismo'),
	(19, '"Agotamiento y alienación provocados por la rutina mecánica diaria (\'despertar, tranvía, cuatro horas de oficina...\')"   \n"Toma de conciencia repentina (lucidez) y la angustia resultante ante el vacío",     \n"La victoria psicológica sobre el castigo mediante el desprecio a la desesperación y la apropiación del destino"', '**Trilogía del Absurdo:** El mito de Sísifo (ensayo) forma parte del ciclo inicial de Camus junto a El extranjero (novela) y Calígula (teatro), todas publicadas o concebidas alrededor de 1942 para explorar la misma intuición desde tres géneros distintos.\n\n**La figura del Héroe Absurdo:** Sísifo es el héroe absurdo definitivo porque es plenamente consciente de la futilidad de su castigo. En el momento en que baja la montaña para volver a buscar la piedra, esa conciencia de su destino lo vuelve superior a su condena.\n\n**Rechazo al Existencialismo Teológico y Nihilista:** Camus se distancia de Kierkegaard (que busca a Dios tras el colapso de la razón) y del nihilismo pasivo (que conduce al suicidio o la apatía). Propone la rebelión como el acto de vivir plenamente a pesar de saber que no hay recompensa final.', '**El Hombre Cotidiano (La Revelación del Vacío)**: Un mapa mental gobernado por la rutina automática hasta que un día surge el "¿por qué?". Esa grieta en la rutina desencadena el colapso del sistema de certezas.\n\n**Sísifo (La Lucidez Triunfante)**: Su psique no se refugia en la esperanza de que un día la piedra se quedará en la cima. Su victoria radica en la falta de ilusión: "No hay destino que no se venza con el desprecio".\n\n**El Actor / El Conquistador / Don Juan (Los Modelos Absurdos):** Perfiles psicológicos que Camus utiliza para ilustrar la vida absurda: no buscan vivir "mejor" en términos morales, sino experimentar la mayor cantidad de vida y sensaciones posibles (la ética de la cantidad).', '"Filosofía del Absurdo (Absurdismo)", "Nihilismo Heroico / Rebelión Existencial", "Humanismo Secular Laico"');
/*!40000 ALTER TABLE `libros_analisis` ENABLE KEYS */;

-- Volcando estructura para tabla libreria.orden
CREATE TABLE IF NOT EXISTS `orden` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.orden: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
INSERT IGNORE INTO `orden` (`id`, `fecha_creacion`, `fecha_modificacion`, `estado`, `total`) VALUES
	(1, '2026-08-27 22:18:26.858135', NULL, 'COMPLETADA', 30000.00),
	(2, '2026-08-27 22:20:59.311334', NULL, 'COMPLETADA', 30000.00),
	(3, '2026-08-27 22:25:14.150422', NULL, 'COMPLETADA', 80000.00),
	(4, '2026-08-27 22:26:23.584373', NULL, 'COMPLETADA', 65000.00);
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;

-- Volcando estructura para tabla libreria.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` enum('ADMIN','INVITED','USER') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.roles: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT IGNORE INTO `roles` (`id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

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

-- Volcando datos para la tabla libreria.subgeneros: ~12 rows (aproximadamente)
/*!40000 ALTER TABLE `subgeneros` DISABLE KEYS */;
INSERT IGNORE INTO `subgeneros` (`genero_id`, `id`, `descripcion`, `nombre`) VALUES
	(1, 1, 'Relato extenso y complejo', 'Novela'),
	(1, 2, 'Narración breve', 'Cuento'),
	(1, 3, 'Relato con moraleja', 'Fábula'),
	(2, 4, 'Composición de 14 versos', 'Soneto'),
	(2, 5, 'Poema de tono elevado', 'Oda'),
	(2, 6, 'Poema de duelo por una perdida', 'Elegia'),
	(3, 7, 'Conflictos fatales', 'Tragedia'),
	(3, 8, 'Tono alegre y final feliz', 'Comedia'),
	(3, 9, 'Mezcla de elementos tragicos y comicos', 'Drama'),
	(4, 10, 'Escrito en prosa donde el autor exponse su punto de vista sobre un tema', 'Ensayo'),
	(4, 11, 'Narracion de la vida de una persona', 'Biografia'),
	(4, 12, 'El arte de hablar con elocuencia para persuadir', 'Oratoria');
/*!40000 ALTER TABLE `subgeneros` ENABLE KEYS */;

-- Volcando estructura para tabla libreria.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK2chxp26bnpqjibydrikgq4t9e` (`user_id`),
  CONSTRAINT `FK2chxp26bnpqjibydrikgq4t9e` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.user_roles: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT IGNORE INTO `user_roles` (`role_id`, `user_id`) VALUES
	(1, 1),
	(2, 2),
	(2, 3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;

-- Volcando estructura para tabla libreria.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKm2dvbwfge291euvmk6vkkocao` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla libreria.usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT IGNORE INTO `usuarios` (`id`, `password`, `username`, `email`) VALUES
	(1, '$2a$10$vmF42WqJ2Wg6kGfeuGy5x.HST7zp7tlQvhsgn0CPkv/84J30GF3Dy', 'admin', ''),
	(2, '$2a$10$6Qj.pus.xoVpwHVkg51W/O8CGtwpmN34tFyqhsoDxEeuw0JzNaPQe', 'tomb', ''),
	(3, '$2a$10$v0oe5WTxhROnxPqwqKAH2OVnhZD221VGJKZ86n0h47hXAVaJ5UcAa', 'Gwynbleidd', 'tomas.barone@hotmail.com');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;