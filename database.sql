CREATE DATABASE  IF NOT EXISTS `progingsw` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `progingsw`;
-- MySQL dump 10.13  Distrib 5.5.28, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: progingsw
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.10.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `terzista`
--

DROP TABLE IF EXISTS `terzista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terzista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `pIva` varchar(11) DEFAULT NULL,
  `ragSociale` varchar(45) DEFAULT NULL,
  `indirizzo` varchar(45) DEFAULT NULL,
  `cap` varchar(45) DEFAULT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  `citta` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `Utente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Utente_id`),
  KEY `fk_Terzista_Utente1_idx` (`Utente_id`),
  CONSTRAINT `fk_Terzista_Utente1` FOREIGN KEY (`Utente_id`) REFERENCES `utente` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terzista`
--

/*!40000 ALTER TABLE `terzista` DISABLE KEYS */;
INSERT INTO `terzista` VALUES (-1,'0@0.it','00000000','null','null','00000','null','null','0000000000','0000000000',-1),(2,'pi@libero.com','89898989875','Pi srl','Via San Giuseppe 30','98865','AN','Senigallia','0717924366','0717924364',1),(4,'shoes@libero.it','34987967659','Shoes srl','Via Piero 9','87769','AN','Porto Recanati','0719090095','0719090094',2),(5,'gioscarpe@libero.it','12345678900','GioScarpe srl','Via Giacomo Leopardi 10','45523','PU','Urbino','07324563244','07324563243',5),(6,'davy@gmail.com','76589323652','Davy Scarpe srl','Via Frassini 3','67729','FM','Fermo','0983456211','0983456215',6),(7,'barbarella@gmail.com','34235664789','Barb srl','Via Giampiero 5','90078','AN','Agugliano','0712345433','0712345434',7),(9,'cancella@cancella.it','12345678901','Cancella snc','Via Bianco 99','99009','ZZ','Zzzzzz','888888888','888888888',8);
/*!40000 ALTER TABLE `terzista` ENABLE KEYS */;

--
-- Table structure for table `extraconsumo`
--

DROP TABLE IF EXISTS `extraconsumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extraconsumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MaterialiTeorici_id` int(11) NOT NULL,
  `quantita` float NOT NULL,
  `giustificato` smallint(6) DEFAULT NULL,
  `dataRichiesta` date DEFAULT NULL,
  PRIMARY KEY (`id`,`MaterialiTeorici_id`),
  KEY `fk_Extraconsumo_MaterialiTeorici1_idx` (`MaterialiTeorici_id`),
  CONSTRAINT `fk_Extraconsumo_MaterialiTeorici1` FOREIGN KEY (`MaterialiTeorici_id`) REFERENCES `materialiteorici` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extraconsumo`
--

/*!40000 ALTER TABLE `extraconsumo` DISABLE KEYS */;
INSERT INTO `extraconsumo` VALUES (1,9,40,0,'2013-01-07'),(2,3,12,0,'2013-01-16');
/*!40000 ALTER TABLE `extraconsumo` ENABLE KEYS */;

--
-- Table structure for table `materialidaprodurre`
--

DROP TABLE IF EXISTS `materialidaprodurre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialidaprodurre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantita` int(11) DEFAULT NULL,
  `numeroMorti` int(11) DEFAULT NULL,
  `quantitaProdotta` double DEFAULT NULL,
  `quantitaSpedita` double DEFAULT NULL,
  `Bolla_id` int(11) NOT NULL,
  `Materiale_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Bolla_id`,`Materiale_id`),
  KEY `fk_MaterialiDaProdurre_Bolla1_idx` (`Bolla_id`),
  KEY `fk_MaterialiDaProdurre_Materiale1_idx` (`Materiale_id`),
  CONSTRAINT `fk_MaterialiDaProdurre_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaterialiDaProdurre_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialidaprodurre`
--

/*!40000 ALTER TABLE `materialidaprodurre` DISABLE KEYS */;
INSERT INTO `materialidaprodurre` VALUES (1,50,10,40,40,1,13),(2,50,0,50,50,2,16),(3,50,0,50,50,3,15),(4,100,0,0,0,4,14),(5,100,0,0,0,5,15),(6,100,0,100,100,12,14),(7,200,2,198,198,13,9),(8,200,2,198,198,14,15),(9,200,0,200,200,15,13),(10,200,0,0,0,16,13),(11,300,0,0,0,17,17),(12,150,0,0,0,18,9);
/*!40000 ALTER TABLE `materialidaprodurre` ENABLE KEYS */;

--
-- Table structure for table `paia`
--

DROP TABLE IF EXISTS `paia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMatDaProd` int(11) DEFAULT NULL,
  `nScarpa` int(11) DEFAULT NULL,
  `paia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idMatDaProd_idx` (`idMatDaProd`),
  CONSTRAINT `` FOREIGN KEY (`idMatDaProd`) REFERENCES `materialidaprodurre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paia`
--

/*!40000 ALTER TABLE `paia` DISABLE KEYS */;
INSERT INTO `paia` VALUES (1,1,36,10),(2,1,37,10),(3,1,38,10),(4,1,39,10),(5,1,40,10),(6,2,37,25),(7,2,38,25),(8,3,39,25),(9,3,40,25),(10,4,36,25),(11,4,37,25),(12,4,38,25),(13,4,39,25),(14,5,37,25),(15,5,38,25),(16,5,39,25),(17,5,40,25),(18,6,36,25),(19,6,37,25),(20,6,38,25),(21,6,39,25),(22,7,36,40),(23,7,37,40),(24,7,38,40),(25,7,39,40),(26,7,40,40),(27,8,38,100),(28,8,39,100),(29,9,36,40),(30,9,37,40),(31,9,38,40),(32,9,39,40),(33,9,40,40),(34,10,38,40),(35,10,39,40),(36,10,40,40),(37,10,41,40),(38,10,42,40),(39,11,40,150),(40,11,41,150),(41,12,39,50),(42,12,40,50),(43,12,41,50);
/*!40000 ALTER TABLE `paia` ENABLE KEYS */;

--
-- Table structure for table `consumo`
--

DROP TABLE IF EXISTS `consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prodotto` int(11) NOT NULL,
  `materiaPrima` int(11) NOT NULL,
  `quantita` double NOT NULL DEFAULT '0',
  `udm` enum('cm2','m2','unit','inch2','m','m3') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `materiaPrima_idx` (`materiaPrima`),
  KEY `prodotto_idx` (`prodotto`),
  CONSTRAINT `materiaPrima` FOREIGN KEY (`materiaPrima`) REFERENCES `materiale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `prodotto` FOREIGN KEY (`prodotto`) REFERENCES `materiale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo`
--

/*!40000 ALTER TABLE `consumo` DISABLE KEYS */;
INSERT INTO `consumo` VALUES (1,13,2,0.5,'m2'),(2,16,18,2,'m'),(3,13,18,2,'m'),(4,14,3,0.5,'m2'),(5,14,18,2,'m'),(6,15,4,0.5,'m2'),(7,15,18,2,'m'),(8,16,3,0.5,'m2'),(9,17,4,0.5,'m2'),(10,8,7,0.01,'m3'),(11,10,7,0.04,'m3'),(12,11,7,0.05,'m3'),(13,15,12,50,'unit'),(14,9,3,0.1,'m2'),(15,9,4,0.1,'m2'),(16,14,5,24,'unit'),(17,13,5,20,'unit'),(18,19,3,0.5,'m2'),(19,19,2,0.5,'m2'),(20,19,4,0.5,'m2'),(21,13,19,1,'unit'),(22,14,19,1,'unit'),(23,15,19,1,'unit'),(24,16,19,1,'unit'),(25,17,19,1,'unit'),(26,14,9,1,'unit'),(27,14,8,1,'unit');
/*!40000 ALTER TABLE `consumo` ENABLE KEYS */;

--
-- Table structure for table `lavorazioneterzista`
--

DROP TABLE IF EXISTS `lavorazioneterzista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lavorazioneterzista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prezzo` decimal(10,2) DEFAULT NULL,
  `qualita` float DEFAULT NULL,
  `capacitaProduzione` float DEFAULT NULL,
  `numeroVotazioni` int(11) DEFAULT NULL,
  `Lavorazione_id` int(11) NOT NULL,
  `Terzista_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Lavorazione_id`,`Terzista_id`),
  KEY `fk_LavorazioneTerzista_Lavorazione1_idx` (`Lavorazione_id`),
  KEY `fk_LavorazioneTerzista_Terzista1_idx` (`Terzista_id`),
  CONSTRAINT `fk_LavorazioneTerzista_Lavorazione1` FOREIGN KEY (`Lavorazione_id`) REFERENCES `lavorazione` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_LavorazioneTerzista_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazioneterzista`
--

/*!40000 ALTER TABLE `lavorazioneterzista` DISABLE KEYS */;
INSERT INTO `lavorazioneterzista` VALUES (-1,0.00,0,0,0,-1,-1),(1,0.80,7.6,100,5,1,2),(3,0.35,7,150,1,1,4),(8,1.00,0,200,0,2,2),(9,0.80,0,300,0,4,2),(10,0.20,8,250,1,3,4),(11,0.15,0,300,0,4,5),(12,0.20,7.4,150,2,5,5),(13,0.40,0,100,0,6,5),(14,0.30,7,80,2,1,6),(15,0.50,0,100,0,2,6),(16,0.45,8,200,4,6,6),(17,0.50,0,200,0,2,7),(18,0.55,7,200,1,3,7),(19,0.60,0,200,0,4,7),(20,0.40,0,200,0,6,7),(27,0.20,0,200,0,7,2),(28,0.30,0,200,0,7,4),(29,0.20,0,200,0,7,5),(30,0.30,0,200,0,7,6),(31,0.20,0,200,0,7,7),(32,0.50,0,200,0,4,4),(33,9.00,0,20,0,6,2);
/*!40000 ALTER TABLE `lavorazioneterzista` ENABLE KEYS */;

--
-- Table structure for table `lavorazione`
--

DROP TABLE IF EXISTS `lavorazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lavorazione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazione`
--

/*!40000 ALTER TABLE `lavorazione` DISABLE KEYS */;
INSERT INTO `lavorazione` VALUES (-1,'null'),(1,'Taglio pelle'),(2,'Orlatura singola'),(3,'Taglio gomma'),(4,'Taglio cuoio'),(5,'Orlatura doppia'),(6,'Montaggio'),(7,'Taglio legno');
/*!40000 ALTER TABLE `lavorazione` ENABLE KEYS */;

--
-- Table structure for table `materialiteorici`
--

DROP TABLE IF EXISTS `materialiteorici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialiteorici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantita` float DEFAULT NULL,
  `Bolla_id` int(11) NOT NULL,
  `Materiale_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Bolla_id`,`Materiale_id`),
  KEY `fk_MaterialiTeorici_Bolla1_idx` (`Bolla_id`),
  KEY `fk_MaterialiTeorici_Materiale1_idx` (`Materiale_id`),
  CONSTRAINT `fk_MaterialiTeorici_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaterialiTeorici_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialiteorici`
--

/*!40000 ALTER TABLE `materialiteorici` DISABLE KEYS */;
INSERT INTO `materialiteorici` VALUES (1,25,1,2),(2,100,2,18),(3,50,2,19),(4,100,3,18),(6,50,4,3),(8,50,5,4),(9,2400,12,5),(10,600,13,3),(11,400,14,18),(12,100,15,2),(13,400,16,18),(14,150,17,4),(15,15,18,3),(16,50,3,19),(17,100,12,19),(18,100,12,8),(19,100,12,9),(20,200,14,19);
/*!40000 ALTER TABLE `materialiteorici` ENABLE KEYS */;

--
-- Table structure for table `materialeterzista`
--

DROP TABLE IF EXISTS `materialeterzista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialeterzista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantita` decimal(10,2) DEFAULT NULL,
  `Terzista_id` int(11) NOT NULL,
  `Materiale_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Terzista_id`,`Materiale_id`),
  KEY `fk_MaterialeTerzista_Terzista1_idx` (`Terzista_id`),
  KEY `fk_MaterialeTerzista_Materiale1_idx` (`Materiale_id`),
  CONSTRAINT `fk_MaterialeTerzista_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaterialeTerzista_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialeterzista`
--

/*!40000 ALTER TABLE `materialeterzista` DISABLE KEYS */;
INSERT INTO `materialeterzista` VALUES (2,0.00,2,2),(3,500.00,4,3),(4,589.00,4,4),(5,623.00,7,4),(6,344.00,7,3),(7,763.00,2,18),(8,821.00,2,19),(9,500.00,4,3),(10,500.00,4,4),(11,4000.00,5,5),(12,530.20,5,3),(13,1960.00,5,18),(14,2000.00,5,19),(15,3600.00,5,8),(16,876.00,5,9),(17,9150.00,6,2),(18,1100.00,6,18),(19,1500.00,7,4),(20,15000.00,7,3),(21,5000.00,5,14),(22,1500.00,5,15),(23,1500.00,2,13),(24,150.00,2,16),(25,1500.00,2,15),(26,2000.00,6,13),(27,200.00,6,15);
/*!40000 ALTER TABLE `materialeterzista` ENABLE KEYS */;

--
-- Table structure for table `fatturabolla`
--

DROP TABLE IF EXISTS `fatturabolla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatturabolla` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Fattura_id` int(11) NOT NULL,
  `Bolla_id` int(11) NOT NULL,
  `importo` float DEFAULT NULL,
  PRIMARY KEY (`id`,`Fattura_id`,`Bolla_id`),
  KEY `fk_FatturaBolla_Fattura1_idx` (`Fattura_id`),
  KEY `fk_FatturaBolla_Bolla1_idx` (`Bolla_id`),
  CONSTRAINT `fk_FatturaBolla_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_FatturaBolla_Fattura1` FOREIGN KEY (`Fattura_id`) REFERENCES `fattura` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fatturabolla`
--

/*!40000 ALTER TABLE `fatturabolla` DISABLE KEYS */;
INSERT INTO `fatturabolla` VALUES (15,3,2,50),(16,4,1,32),(17,5,3,50),(18,6,15,60);
/*!40000 ALTER TABLE `fatturabolla` ENABLE KEYS */;

--
-- Table structure for table `fattura`
--

DROP TABLE IF EXISTS `fattura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fattura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numeroFattura` int(11) DEFAULT NULL,
  `dataEmissione` datetime DEFAULT NULL,
  `importo` float DEFAULT NULL,
  `Terzista_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Terzista_id`),
  KEY `fk_Fattura_Terzista1_idx` (`Terzista_id`),
  CONSTRAINT `fk_Fattura_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fattura`
--

/*!40000 ALTER TABLE `fattura` DISABLE KEYS */;
INSERT INTO `fattura` VALUES (3,0,'2013-01-16 12:48:00',118.58,2),(4,1,'2013-01-16 13:51:00',38.72,2),(5,2,'2013-01-16 15:12:00',60.5,2),(6,3,'2013-01-16 15:16:00',72.6,6);
/*!40000 ALTER TABLE `fattura` ENABLE KEYS */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `progingsw`.`tr_nmFatt`
BEFORE INSERT ON `progingsw`.`fattura`
FOR EACH ROW
BEGIN
DECLARE x INT;
SET x = (SELECT MAX(numeroFattura) FROM progingsw.fattura);
SET NEW.numeroFattura = x+1; 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `materiale`
--

DROP TABLE IF EXISTS `materiale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materiale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codiceArticolo` varchar(45) DEFAULT NULL,
  `descrizione` varchar(45) DEFAULT NULL,
  `costoUnitario` decimal(10,2) DEFAULT NULL,
  `udm` varchar(45) DEFAULT NULL,
  `tipo` enum('MP','SL') NOT NULL DEFAULT 'MP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiale`
--

/*!40000 ALTER TABLE `materiale` DISABLE KEYS */;
INSERT INTO `materiale` VALUES (2,'1A','Pelle',30.00,'m2','MP'),(3,'2A','Cuoio',20.00,'m2','MP'),(4,'3A','Gomma',2.00,'m2','MP'),(5,'4A','Anelli',0.05,'unit','MP'),(6,'5A','Tela',5.00,'m2','MP'),(7,'6A','Legno',10.00,'m3','MP'),(8,'1B','Tacco 3',1.00,'unit','SL'),(9,'2B','Suola',1.50,'unit','SL'),(10,'3B','Tacco 12',1.50,'unit','SL'),(11,'4B','Tacco 15',2.00,'unit','SL'),(12,'7A','Borchie',0.10,'unit','MP'),(13,'8A','Stivale mod1',4.00,'unit','SL'),(14,'9A','Stivale mod2',4.00,'unit','SL'),(15,'10A','Scarpa mod1',3.50,'unit','SL'),(16,'11A','Scarpa mod2',3.80,'unit','SL'),(17,'12A','Sandalo mod1',3.00,'unit','SL'),(18,'13A','Filo',0.05,'m','MP'),(19,'14A','Sagoma',4.00,'unit','SL');
/*!40000 ALTER TABLE `materiale` ENABLE KEYS */;

--
-- Table structure for table `ddt`
--

DROP TABLE IF EXISTS `ddt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numeroDocumento` int(11) DEFAULT NULL,
  `dataInvio` datetime DEFAULT NULL,
  `dataRicezione` datetime DEFAULT NULL,
  `Terzista_id` int(11) NOT NULL COMMENT 'destinatario',
  `flussoAzienda` tinyint(4) DEFAULT NULL,
  `registrato` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`,`Terzista_id`),
  KEY `fk_DDT_Terzista1_idx` (`Terzista_id`),
  CONSTRAINT `fk_DDT_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt`
--

/*!40000 ALTER TABLE `ddt` DISABLE KEYS */;
INSERT INTO `ddt` VALUES (1,0,'2013-01-16 14:25:00',NULL,2,0,0),(2,1,'2013-01-16 15:11:00',NULL,2,0,0),(3,2,'2013-01-16 15:16:00',NULL,6,0,0),(4,3,'2013-01-14 15:16:00',NULL,6,1,0);
/*!40000 ALTER TABLE `ddt` ENABLE KEYS */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `progingsw`.`tr_nmDDT`
BEFORE INSERT ON `progingsw`.`ddt`
FOR EACH ROW
BEGIN
DECLARE x INT;
SET x = (SELECT MAX(numeroDocumento) FROM progingsw.ddt);
SET NEW.numeroDocumento = x+1; 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `ddtmateriale`
--

DROP TABLE IF EXISTS `ddtmateriale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddtmateriale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DDT_id` int(11) NOT NULL,
  `Materiale_id` int(11) NOT NULL,
  `quantita` float NOT NULL,
  PRIMARY KEY (`id`,`DDT_id`,`Materiale_id`),
  KEY `fk_DDTMateriale_DDT1_idx` (`DDT_id`),
  KEY `fk_DDTMateriale_Materiale1_idx` (`Materiale_id`),
  CONSTRAINT `fk_DDTMateriale_DDT1` FOREIGN KEY (`DDT_id`) REFERENCES `ddt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DDTMateriale_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddtmateriale`
--

/*!40000 ALTER TABLE `ddtmateriale` DISABLE KEYS */;
INSERT INTO `ddtmateriale` VALUES (3,1,2,5),(4,2,15,50),(5,3,13,200),(6,4,15,100);
/*!40000 ALTER TABLE `ddtmateriale` ENABLE KEYS */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `psw` varchar(45) DEFAULT NULL,
  `tipo` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (-1,'null','null',2),(1,'marco','189bbbb00c5f1fb7fba9ad9285f193d1',5),(2,'katy','189bbbb00c5f1fb7fba9ad9285f193d1',5),(3,'admin','21232f297a57a5a743894a0e4a801fc3',2),(5,'giorgia','7f8f98248522f399e9f98a29fb149eee',5),(6,'davide','446fca5553df49ad9c6348cf1ff71d51',5),(7,'barbara','4d6c4d6b5b6c7fd2c43727ce32a56f4e',5),(8,'cancella','353467ad28d3ec477450a2e4f093ad19',5);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;

--
-- Table structure for table `messaggio`
--

DROP TABLE IF EXISTS `messaggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messaggio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Utente_id` int(11) NOT NULL COMMENT 'mittente',
  `testo` varchar(512) NOT NULL,
  `data` datetime DEFAULT NULL,
  `Bolla_id` int(11) NOT NULL,
  `letto` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`,`Utente_id`,`Bolla_id`),
  KEY `fk_Messaggio_Utente1_idx` (`Utente_id`),
  KEY `fk_Messaggio_Bolla1_idx` (`Bolla_id`),
  CONSTRAINT `fk_Messaggio_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messaggio_Utente1` FOREIGN KEY (`Utente_id`) REFERENCES `utente` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggio`
--

/*!40000 ALTER TABLE `messaggio` DISABLE KEYS */;
INSERT INTO `messaggio` VALUES (1,5,'Gli anelli me li aspettavo più resistenti.','2013-01-05 00:00:00',16,1),(2,1,'prova','2013-01-15 09:00:20',3,0),(3,1,'invio messaggio di prova','2013-01-16 10:22:45',1,0),(4,1,'test','2013-01-16 10:22:55',1,0),(5,1,'test 2','2013-01-16 11:13:14',1,0),(6,6,'Pazienza!','2013-01-16 14:12:10',16,1),(7,6,'Il modello dello stivale non è molto dettagliato!','2013-01-16 14:12:58',15,1),(8,3,'pazienza','2013-01-16 14:17:17',15,0),(9,3,'nuovo mess','2013-01-16 14:18:18',15,0),(10,3,'Ma che risposta è???','2013-01-16 15:36:50',16,1),(11,6,'nuovo','2013-01-16 16:44:45',16,0);
/*!40000 ALTER TABLE `messaggio` ENABLE KEYS */;

--
-- Table structure for table `fase`
--

DROP TABLE IF EXISTS `fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `ordine` smallint(6) DEFAULT NULL,
  `LavorazioneTerzista_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`LavorazioneTerzista_id`),
  KEY `fk_Fase_LavorazioneTerzista1_idx` (`LavorazioneTerzista_id`),
  CONSTRAINT `fk_Fase_LavorazioneTerzista1` FOREIGN KEY (`LavorazioneTerzista_id`) REFERENCES `lavorazioneterzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fase`
--

/*!40000 ALTER TABLE `fase` DISABLE KEYS */;
INSERT INTO `fase` VALUES (1,'Taglio up',1,1),(8,'OrlBase',1,8),(9,'Taglio down',2,1),(10,'OrlDown',2,8),(14,'Taglio retro',3,1);
/*!40000 ALTER TABLE `fase` ENABLE KEYS */;

--
-- Table structure for table `bolla`
--

DROP TABLE IF EXISTS `bolla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bolla` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codice` varchar(45) NOT NULL,
  `stato` int(11) DEFAULT '0',
  `data` datetime DEFAULT NULL,
  `valutata` int(11) DEFAULT '0',
  `Terzista_id` int(11) NOT NULL DEFAULT '0',
  `LavorazioneTerzista_id` int(11) NOT NULL DEFAULT '0',
  `url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`LavorazioneTerzista_id`,`Terzista_id`),
  KEY `terzista_idx` (`Terzista_id`),
  KEY `lavterz_idx` (`LavorazioneTerzista_id`),
  KEY `terzista_idx1` (`Terzista_id`),
  KEY `lavterz_idx1` (`LavorazioneTerzista_id`),
  CONSTRAINT `lavterz` FOREIGN KEY (`LavorazioneTerzista_id`) REFERENCES `lavorazioneterzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `terzista` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bolla`
--

/*!40000 ALTER TABLE `bolla` DISABLE KEYS */;
INSERT INTO `bolla` VALUES (1,'1A',3,'2012-05-05 00:00:00',0,2,1,'img04.jpg'),(2,'2B',3,'2012-06-05 00:00:00',0,2,8,'img02.jpg'),(3,'3C',3,'2012-08-05 00:00:00',0,2,8,'img01.jpg'),(4,'2A',1,'2012-09-05 00:00:00',0,4,32,'img04.jpg'),(5,'4D',1,'2012-06-20 00:00:00',0,4,10,'img01.jpg'),(12,'5E',3,'2012-11-09 00:00:00',0,5,13,'img04.jpg'),(13,'6B',4,'2012-11-10 00:00:00',0,5,11,'img03.jpg'),(14,'7U',3,'2012-12-19 00:00:00',0,5,12,'img01.jpg'),(15,'1Z',3,'2013-01-01 00:00:00',0,6,14,'img04.jpg'),(16,'2S',1,'2012-09-29 00:00:00',0,6,15,'img04.jpg'),(17,'4F',1,'2012-11-21 00:00:00',0,7,18,'img05.jpg'),(18,'2D',1,'2012-10-09 00:00:00',0,7,19,'img06.jpg');
/*!40000 ALTER TABLE `bolla` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-16 18:21:44
