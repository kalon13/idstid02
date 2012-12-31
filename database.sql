-- MySQL dump 10.13  Distrib 5.5.27, for Win32 (x86)
--
-- Host: localhost    Database: progingsw
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `bolla`
--

DROP TABLE IF EXISTS `bolla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bolla` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codice` varchar(45) NOT NULL,
  `stato` smallint(6) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `valutata` int(11) DEFAULT '0',
  `Terzista_id` int(11) NOT NULL,
  `LavorazioneTerzista_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Terzista_id`,`LavorazioneTerzista_id`),
  KEY `fk_Bolla_Terzista1_idx` (`Terzista_id`),
  KEY `fk_Bolla_Lavorazione1_idx` (`LavorazioneTerzista_id`),
  KEY `fk_Bolla_LavTerzista_idx` (`LavorazioneTerzista_id`),
  CONSTRAINT `fk_Bolla_LavTerzista` FOREIGN KEY (`LavorazioneTerzista_id`) REFERENCES `lavorazioneterzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bolla_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bolla`
--

LOCK TABLES `bolla` WRITE;
/*!40000 ALTER TABLE `bolla` DISABLE KEYS */;
INSERT INTO `bolla` VALUES (8,'1A',3,'2012-12-01 00:00:00',0,1,5),(9,'2A',1,'2012-11-15 00:00:00',0,2,6),(10,'3A',2,'2012-12-20 00:00:00',0,1,6);
/*!40000 ALTER TABLE `bolla` ENABLE KEYS */;
UNLOCK TABLES;

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
  `udm` enum('cm2','m2','unit','inch2','m','cm') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `prodotto_idx` (`prodotto`),
  KEY `materiaPrima_idx` (`materiaPrima`),
  CONSTRAINT `materiaPrima` FOREIGN KEY (`materiaPrima`) REFERENCES `materiale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prodotto` FOREIGN KEY (`prodotto`) REFERENCES `materiale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo`
--

LOCK TABLES `consumo` WRITE;
/*!40000 ALTER TABLE `consumo` DISABLE KEYS */;
INSERT INTO `consumo` VALUES (1,10,12,3,'inch2'),(2,11,14,6,'inch2');
/*!40000 ALTER TABLE `consumo` ENABLE KEYS */;
UNLOCK TABLES;

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
  `registrato` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`,`Terzista_id`),
  KEY `fk_DDT_Terzista1_idx` (`Terzista_id`),
  CONSTRAINT `fk_DDT_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt`
--

LOCK TABLES `ddt` WRITE;
/*!40000 ALTER TABLE `ddt` DISABLE KEYS */;
INSERT INTO `ddt` VALUES (1,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(2,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(3,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(4,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(5,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(6,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(7,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(8,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(9,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(10,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(11,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(12,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(13,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(14,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(15,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(16,NULL,'2012-12-27 00:00:00',NULL,1,0,0),(17,NULL,'2012-12-27 00:00:00',NULL,1,0,0);
/*!40000 ALTER TABLE `ddt` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `fk_DDTMateriale_DDT1` FOREIGN KEY (`DDT_id`) REFERENCES `ddt` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_DDTMateriale_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddtmateriale`
--

LOCK TABLES `ddtmateriale` WRITE;
/*!40000 ALTER TABLE `ddtmateriale` DISABLE KEYS */;
INSERT INTO `ddtmateriale` VALUES (6,1,10,1),(7,2,10,10),(8,3,10,12),(9,3,11,0),(10,4,10,2),(11,4,11,0),(12,5,10,12),(13,5,11,0),(14,6,10,30),(15,6,11,40),(16,7,10,28),(17,7,11,30),(18,8,10,11),(19,8,11,10),(20,9,10,12),(21,9,11,10),(22,10,10,0),(23,10,11,0),(24,11,10,10),(25,11,11,10),(26,12,10,10),(27,12,11,0),(28,13,10,3),(29,14,10,3),(30,17,10,1);
/*!40000 ALTER TABLE `ddtmateriale` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `fk_Extraconsumo_MaterialiTeorici1` FOREIGN KEY (`MaterialiTeorici_id`) REFERENCES `materialiteorici` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extraconsumo`
--

LOCK TABLES `extraconsumo` WRITE;
/*!40000 ALTER TABLE `extraconsumo` DISABLE KEYS */;
INSERT INTO `extraconsumo` VALUES (1,7,33,0,'2012-12-19'),(2,6,44,0,'2012-12-19'),(3,7,90,0,'2012-12-19'),(4,11,33,0,'2012-12-20'),(5,13,20,0,'2012-12-20'),(6,6,3,0,'2012-12-20'),(7,12,21,0,'2012-12-21'),(8,8,12,0,'2012-12-21'),(9,7,21,0,'2012-12-27');
/*!40000 ALTER TABLE `extraconsumo` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `fk_Fase_LavorazioneTerzista1` FOREIGN KEY (`LavorazioneTerzista_id`) REFERENCES `lavorazioneterzista` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fase`
--

LOCK TABLES `fase` WRITE;
/*!40000 ALTER TABLE `fase` DISABLE KEYS */;
INSERT INTO `fase` VALUES (1,'Prima fase',1,5),(2,'Seconda fase',2,5),(3,'Terza fase',3,5),(4,'Prima',1,6),(5,'Seconda',2,6),(6,'Terza',3,6);
/*!40000 ALTER TABLE `fase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fattura`
--

DROP TABLE IF EXISTS `fattura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fattura` (
  `id` int(11) NOT NULL,
  `numeroFattura` int(11) NOT NULL,
  `dataEmissione` datetime DEFAULT NULL,
  `importo` float DEFAULT NULL,
  `Terzista_id` int(11) NOT NULL,
  `Terzista_Utente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Terzista_id`,`Terzista_Utente_id`),
  KEY `fk_Fattura_Terzista1_idx` (`Terzista_id`,`Terzista_Utente_id`),
  CONSTRAINT `fk_Fattura_Terzista1` FOREIGN KEY (`Terzista_id`, `Terzista_Utente_id`) REFERENCES `terzista` (`id`, `Utente_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fattura`
--

LOCK TABLES `fattura` WRITE;
/*!40000 ALTER TABLE `fattura` DISABLE KEYS */;
/*!40000 ALTER TABLE `fattura` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fatturabolla`
--

LOCK TABLES `fatturabolla` WRITE;
/*!40000 ALTER TABLE `fatturabolla` DISABLE KEYS */;
/*!40000 ALTER TABLE `fatturabolla` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazione`
--

LOCK TABLES `lavorazione` WRITE;
/*!40000 ALTER TABLE `lavorazione` DISABLE KEYS */;
INSERT INTO `lavorazione` VALUES (3,'Taglio'),(4,'Ricamo'),(5,'Orlatura');
/*!40000 ALTER TABLE `lavorazione` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazioneterzista`
--

LOCK TABLES `lavorazioneterzista` WRITE;
/*!40000 ALTER TABLE `lavorazioneterzista` DISABLE KEYS */;
INSERT INTO `lavorazioneterzista` VALUES (5,0.50,6,120,1,3,1),(6,0.50,6,100,1,4,1),(7,0.60,6,200,1,4,2);
/*!40000 ALTER TABLE `lavorazioneterzista` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiale`
--

LOCK TABLES `materiale` WRITE;
/*!40000 ALTER TABLE `materiale` DISABLE KEYS */;
INSERT INTO `materiale` VALUES (10,'A','Tacco',1.50,'unit','MP'),(11,'B','Suola',2.00,'unit','MP'),(12,'C','Pelle1',1.00,'inch2','MP'),(13,'D','Pelle2',2.00,'inch2','MP'),(14,'E','Cuoio',3.00,'inch2','MP'),(15,'F','Stivale A',15.00,'unit','MP');
/*!40000 ALTER TABLE `materiale` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialeterzista`
--

LOCK TABLES `materialeterzista` WRITE;
/*!40000 ALTER TABLE `materialeterzista` DISABLE KEYS */;
INSERT INTO `materialeterzista` VALUES (21,55.00,1,10),(22,-136.00,1,12),(23,-430.00,1,14),(24,22.00,1,11);
/*!40000 ALTER TABLE `materialeterzista` ENABLE KEYS */;
UNLOCK TABLES;

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

LOCK TABLES `materialidaprodurre` WRITE;
/*!40000 ALTER TABLE `materialidaprodurre` DISABLE KEYS */;
INSERT INTO `materialidaprodurre` VALUES (6,30,0,30,3,8,10),(7,40,0,40,0,8,11),(8,20,0,0,0,9,10),(9,20,0,0,0,9,11),(10,30,2,26,26,10,10),(11,30,0,10,10,10,11),(12,50,0,0,0,9,15);
/*!40000 ALTER TABLE `materialidaprodurre` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialiteorici`
--

LOCK TABLES `materialiteorici` WRITE;
/*!40000 ALTER TABLE `materialiteorici` DISABLE KEYS */;
INSERT INTO `materialiteorici` VALUES (6,12,8,12),(7,10,8,13),(8,45,8,14),(9,30,9,13),(10,10,9,14),(11,90,10,12),(12,90,10,13),(13,90,10,14);
/*!40000 ALTER TABLE `materialiteorici` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggio`
--

LOCK TABLES `messaggio` WRITE;
/*!40000 ALTER TABLE `messaggio` DISABLE KEYS */;
INSERT INTO `messaggio` VALUES (3,6,'Ciao!','2012-12-03 00:00:00',8,1);
/*!40000 ALTER TABLE `messaggio` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paia`
--

LOCK TABLES `paia` WRITE;
/*!40000 ALTER TABLE `paia` DISABLE KEYS */;
INSERT INTO `paia` VALUES (1,7,36,3),(2,7,37,3),(3,7,38,3),(4,7,39,3),(5,7,40,3),(6,8,37,6),(7,8,38,6),(8,8,40,6),(9,9,37,7),(10,9,38,7),(11,9,39,7),(12,10,38,5),(13,10,39,5),(14,10,40,5),(15,10,41,5),(16,12,36,4),(17,12,39,4),(18,12,42,4),(19,6,36,11),(20,6,38,11),(21,6,39,11),(22,6,42,11);
/*!40000 ALTER TABLE `paia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paia1`
--

DROP TABLE IF EXISTS `paia1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paia1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMatDaProd` int(11) DEFAULT NULL,
  `paia36` int(11) DEFAULT NULL,
  `paia37` int(11) DEFAULT NULL,
  `paia38` int(11) DEFAULT NULL,
  `paia39` int(11) DEFAULT NULL,
  `paia40` int(11) DEFAULT NULL,
  `paia41` int(11) DEFAULT NULL,
  `paia42` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idMatDaProd_idx` (`idMatDaProd`),
  CONSTRAINT `idMatDaProd` FOREIGN KEY (`idMatDaProd`) REFERENCES `materialidaprodurre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paia1`
--

LOCK TABLES `paia1` WRITE;
/*!40000 ALTER TABLE `paia1` DISABLE KEYS */;
INSERT INTO `paia1` VALUES (1,9,3,1,2,1,1,1,1),(2,10,0,3,3,4,4,3,0),(3,8,3,5,5,5,5,5,5),(4,7,6,6,6,6,6,6,6);
/*!40000 ALTER TABLE `paia1` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terzista`
--

LOCK TABLES `terzista` WRITE;
/*!40000 ALTER TABLE `terzista` DISABLE KEYS */;
INSERT INTO `terzista` VALUES (-1,NULL,NULL,'Da Assegnare',NULL,NULL,NULL,NULL,NULL,NULL,-1),(1,'ABC','234','ABC srl','re','33','ps','pio','0','0',6),(2,'ciao@libero.it','09234586981','Gio srl','Via Santa','9988','pu','piobbico','09867654','98765444',8);
/*!40000 ALTER TABLE `terzista` ENABLE KEYS */;
UNLOCK TABLES;

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

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (-1,NULL,NULL,NULL),(2,'admin','21232f297a57a5a743894a0e4a801fc3',2),(3,'mag','741f63d12d767bb3fd2b0251ed839499',2),(4,'op1','4736b2b496ba3de748c6eea6c6b9ca65',2),(5,'op2','f90a07a0f460574ca7da086f1556066a',2),(6,'ter1','7aad5512055a7a935b56b7ba36714402',5),(7,'ter2','d17b319bda005deed3f0f61f5e7295fc',5),(8,'ter3','24f7b011d374fd5a3dbda723756133bf',5);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-27 17:11:38
