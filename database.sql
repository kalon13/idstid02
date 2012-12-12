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
  `stato` int(11) DEFAULT '0',
  `data` datetime DEFAULT NULL,
  `valutata` int(11) DEFAULT '0',
  `Terzista_id` int(11) NOT NULL,
  `LavorazioneTerzista_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`Terzista_id`,`LavorazioneTerzista_id`),
  KEY `terzista_idx` (`Terzista_id`),
  KEY `lavterz_idx` (`LavorazioneTerzista_id`),
  KEY `terzista_idx1` (`Terzista_id`),
  KEY `lavterz_idx1` (`LavorazioneTerzista_id`),
  CONSTRAINT `lavterz` FOREIGN KEY (`LavorazioneTerzista_id`) REFERENCES `lavorazioneterzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `terzista` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bolla`
--

LOCK TABLES `bolla` WRITE;
/*!40000 ALTER TABLE `bolla` DISABLE KEYS */;
INSERT INTO `bolla` VALUES (1,'GHF',1,'2012-05-05 00:00:00',0,2,1),(2,'FDR',1,'2012-06-05 00:00:00',1,2,8);
/*!40000 ALTER TABLE `bolla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ddt`
--

DROP TABLE IF EXISTS `ddt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ddt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numeroDocumento` int(11) NOT NULL,
  `dataInvio` datetime DEFAULT NULL,
  `dataRicezione` datetime DEFAULT NULL,
  `Terzista_id` int(11) NOT NULL COMMENT 'destinatario',
  `flussoAzienda` tinyint(4) DEFAULT NULL,
  `registrato` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`,`Terzista_id`),
  KEY `fk_DDT_Terzista1_idx` (`Terzista_id`),
  CONSTRAINT `fk_DDT_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddt`
--

LOCK TABLES `ddt` WRITE;
/*!40000 ALTER TABLE `ddt` DISABLE KEYS */;
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
  CONSTRAINT `fk_DDTMateriale_DDT1` FOREIGN KEY (`DDT_id`) REFERENCES `ddt` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DDTMateriale_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ddtmateriale`
--

LOCK TABLES `ddtmateriale` WRITE;
/*!40000 ALTER TABLE `ddtmateriale` DISABLE KEYS */;
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
  CONSTRAINT `fk_Extraconsumo_MaterialiTeorici1` FOREIGN KEY (`MaterialiTeorici_id`) REFERENCES `materialiteorici` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extraconsumo`
--

LOCK TABLES `extraconsumo` WRITE;
/*!40000 ALTER TABLE `extraconsumo` DISABLE KEYS */;
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
  CONSTRAINT `fk_Fase_LavorazioneTerzista1` FOREIGN KEY (`LavorazioneTerzista_id`) REFERENCES `lavorazioneterzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fase`
--

LOCK TABLES `fase` WRITE;
/*!40000 ALTER TABLE `fase` DISABLE KEYS */;
INSERT INTO `fase` VALUES (1,'Taglio upd',1,1),(8,'OrlBase',1,8),(9,'Taglio down',2,1),(10,'OrlDown',2,8);
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
  CONSTRAINT `fk_Fattura_Terzista1` FOREIGN KEY (`Terzista_id`, `Terzista_Utente_id`) REFERENCES `terzista` (`id`, `Utente_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  CONSTRAINT `fk_FatturaBolla_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_FatturaBolla_Fattura1` FOREIGN KEY (`Fattura_id`) REFERENCES `fattura` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazione`
--

LOCK TABLES `lavorazione` WRITE;
/*!40000 ALTER TABLE `lavorazione` DISABLE KEYS */;
INSERT INTO `lavorazione` VALUES (1,'Taglio'),(2,'Orlatura'),(3,'Fresatura'),(4,'Smerigliatura');
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
  CONSTRAINT `fk_LavorazioneTerzista_Lavorazione1` FOREIGN KEY (`Lavorazione_id`) REFERENCES `lavorazione` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LavorazioneTerzista_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lavorazioneterzista`
--

LOCK TABLES `lavorazioneterzista` WRITE;
/*!40000 ALTER TABLE `lavorazioneterzista` DISABLE KEYS */;
INSERT INTO `lavorazioneterzista` VALUES (1,9.00,7.33333,100,3,1,2),(3,5.00,7,80,1,1,4),(8,8.50,0,21,0,2,2),(10,5.00,0,48,0,3,2);
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
  `udm` enum('cm2','m2','unit','inch2','m','cm') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiale`
--

LOCK TABLES `materiale` WRITE;
/*!40000 ALTER TABLE `materiale` DISABLE KEYS */;
INSERT INTO `materiale` VALUES (1,'PR10','Pelle',10.00,NULL);
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
  CONSTRAINT `fk_MaterialeTerzista_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaterialeTerzista_Terzista1` FOREIGN KEY (`Terzista_id`) REFERENCES `terzista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialeterzista`
--

LOCK TABLES `materialeterzista` WRITE;
/*!40000 ALTER TABLE `materialeterzista` DISABLE KEYS */;
INSERT INTO `materialeterzista` VALUES (1,101.00,2,1);
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
  CONSTRAINT `fk_MaterialiDaProdurre_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaterialiDaProdurre_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialidaprodurre`
--

LOCK TABLES `materialidaprodurre` WRITE;
/*!40000 ALTER TABLE `materialidaprodurre` DISABLE KEYS */;
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
  CONSTRAINT `fk_MaterialiTeorici_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_MaterialiTeorici_Materiale1` FOREIGN KEY (`Materiale_id`) REFERENCES `materiale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialiteorici`
--

LOCK TABLES `materialiteorici` WRITE;
/*!40000 ALTER TABLE `materialiteorici` DISABLE KEYS */;
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
  CONSTRAINT `fk_Messaggio_Bolla1` FOREIGN KEY (`Bolla_id`) REFERENCES `bolla` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messaggio_Utente1` FOREIGN KEY (`Utente_id`) REFERENCES `utente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggio`
--

LOCK TABLES `messaggio` WRITE;
/*!40000 ALTER TABLE `messaggio` DISABLE KEYS */;
/*!40000 ALTER TABLE `messaggio` ENABLE KEYS */;
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
  CONSTRAINT `fk_Terzista_Utente1` FOREIGN KEY (`Utente_id`) REFERENCES `utente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terzista`
--

LOCK TABLES `terzista` WRITE;
/*!40000 ALTER TABLE `terzista` DISABLE KEYS */;
INSERT INTO `terzista` VALUES (2,'m@p.it','89898989872','Pi.srl','Via ciao 31','87760','AN','Senigallia','0717924366','3327856432',1),(4,'f@f.it','3498796','Wi.srl','Via Piero 9','87769','AN','Porto Recanati','909009','676757',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'marco','189bbbb00c5f1fb7fba9ad9285f193d1',1),(2,'katy','189bbbb00c5f1fb7fba9ad9285f193d1',1),(3,'admin','21232f297a57a5a743894a0e4a801fc3',2);
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

-- Dump completed on 2012-12-12 16:16:07
