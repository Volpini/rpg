-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: rpg
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `aventura`
--

DROP TABLE IF EXISTS `aventura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aventura` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataFim` date DEFAULT NULL,
  `dataInicio` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `campanha_id` bigint(20) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK74E9C52E7C7AB52F` (`campanha_id`),
  CONSTRAINT `FK74E9C52E7C7AB52F` FOREIGN KEY (`campanha_id`) REFERENCES `campanha` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aventura`
--

LOCK TABLES `aventura` WRITE;
/*!40000 ALTER TABLE `aventura` DISABLE KEYS */;
INSERT INTO `aventura` VALUES (1,'2015-07-07','2014-07-07','Aventura 1',4,NULL,NULL),(2,'2015-07-07','2014-07-07','Aventura 2',4,NULL,NULL);
/*!40000 ALTER TABLE `aventura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aventura_personagem`
--

DROP TABLE IF EXISTS `aventura_personagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aventura_personagem` (
  `aventura_id` bigint(20) NOT NULL,
  `personagem_id` bigint(20) NOT NULL,
  `personagens_id` bigint(20) NOT NULL,
  KEY `FKC448EFF45DCD4FCF` (`aventura_id`),
  KEY `FKC448EFF4742F9A6F` (`personagem_id`),
  KEY `FK85CE93F48D246643` (`personagens_id`),
  CONSTRAINT `FK85CE93F48D246643` FOREIGN KEY (`personagens_id`) REFERENCES `personagem` (`id`),
  CONSTRAINT `FKC448EFF45DCD4FCF` FOREIGN KEY (`aventura_id`) REFERENCES `aventura` (`id`),
  CONSTRAINT `FKC448EFF4742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aventura_personagem`
--

LOCK TABLES `aventura_personagem` WRITE;
/*!40000 ALTER TABLE `aventura_personagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `aventura_personagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campanha`
--

DROP TABLE IF EXISTS `campanha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campanha` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataInicio` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `mestre_id` bigint(20) DEFAULT NULL,
  `descricao` varchar(2000) DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFB837807468F27D` (`mestre_id`),
  CONSTRAINT `FKFB837807468F27D` FOREIGN KEY (`mestre_id`) REFERENCES `systemuser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campanha`
--

LOCK TABLES `campanha` WRITE;
/*!40000 ALTER TABLE `campanha` DISABLE KEYS */;
INSERT INTO `campanha` VALUES (1,NULL,'Campanha Um ',1,NULL,NULL),(2,'2010-05-20','The Six Elements',1,'O mundo começa a sucumbir pelo medo, e um novo poder começa a surgir. Deuses, heróis e criaturas começam a temer por um novo acontecimento tão parecido ou até mais forte que o cataclismas gêmeos. Eis que diante desta situações várias formas de combater o perigo surge, mas são alguns inexperientes aventureiros que começam a se destacar.','2015-02-20'),(4,NULL,'Campanha Super Master',NULL,NULL,NULL);
/*!40000 ALTER TABLE `campanha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campanha_personagem`
--

DROP TABLE IF EXISTS `campanha_personagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campanha_personagem` (
  `Campanha_id` bigint(20) NOT NULL,
  `personagem_id` bigint(20) NOT NULL,
  KEY `FK2026CF7B7C7AB52F` (`Campanha_id`),
  KEY `FK2026CF7B742F9A6F` (`personagem_id`),
  CONSTRAINT `FK2026CF7B742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`),
  CONSTRAINT `FK2026CF7B7C7AB52F` FOREIGN KEY (`Campanha_id`) REFERENCES `campanha` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campanha_personagem`
--

LOCK TABLES `campanha_personagem` WRITE;
/*!40000 ALTER TABLE `campanha_personagem` DISABLE KEYS */;
INSERT INTO `campanha_personagem` VALUES (4,10);
/*!40000 ALTER TABLE `campanha_personagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `campanhasview`
--

DROP TABLE IF EXISTS `campanhasview`;
/*!50001 DROP VIEW IF EXISTS `campanhasview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `campanhasview` (
  `id` tinyint NOT NULL,
  `nome` tinyint NOT NULL,
  `mestre` tinyint NOT NULL,
  `jogador` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `cenario`
--

DROP TABLE IF EXISTS `cenario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cenario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataCenario` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `aventura_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8AD6E3435DCD4FCF` (`aventura_id`),
  CONSTRAINT `FK8AD6E3435DCD4FCF` FOREIGN KEY (`aventura_id`) REFERENCES `aventura` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cenario`
--

LOCK TABLES `cenario` WRITE;
/*!40000 ALTER TABLE `cenario` DISABLE KEYS */;
/*!40000 ALTER TABLE `cenario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cenario_personagem`
--

DROP TABLE IF EXISTS `cenario_personagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cenario_personagem` (
  `cenario_id` bigint(20) NOT NULL,
  `personagem_id` bigint(20) NOT NULL,
  KEY `FKF81FAE7F742F9A6F` (`personagem_id`),
  KEY `FKF81FAE7FD7978365` (`cenario_id`),
  CONSTRAINT `FKF81FAE7F742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`),
  CONSTRAINT `FKF81FAE7FD7978365` FOREIGN KEY (`cenario_id`) REFERENCES `cenario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cenario_personagem`
--

LOCK TABLES `cenario_personagem` WRITE;
/*!40000 ALTER TABLE `cenario_personagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `cenario_personagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controller`
--

DROP TABLE IF EXISTS `controller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `controller` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controller`
--

LOCK TABLES `controller` WRITE;
/*!40000 ALTER TABLE `controller` DISABLE KEYS */;
INSERT INTO `controller` VALUES (1,'menu.controle.itens','ItemController'),(2,'menu.controle.usuarios','SystemUserController'),(3,'menu.controle.grupousuarios','UserGroupController'),(4,'menu.controle.personagens','PersonagemController'),(5,'menu.controle.talentos','TalentoController'),(6,'menu.controle.livros','LivroController'),(7,'menu.controle.campanhas','CampanhaController'),(8,'menu.controle.aventuras','AventuraController');
/*!40000 ALTER TABLE `controller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controllergroup`
--

DROP TABLE IF EXISTS `controllergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `controllergroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controllergroup`
--

LOCK TABLES `controllergroup` WRITE;
/*!40000 ALTER TABLE `controllergroup` DISABLE KEYS */;
INSERT INTO `controllergroup` VALUES (1,'Controle');
/*!40000 ALTER TABLE `controllergroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controllergroup_controller`
--

DROP TABLE IF EXISTS `controllergroup_controller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `controllergroup_controller` (
  `ControllerGroup_id` bigint(20) NOT NULL,
  `controllers_id` bigint(20) NOT NULL,
  UNIQUE KEY `controllers_id` (`controllers_id`),
  KEY `FKC6CE7558F8F03845` (`ControllerGroup_id`),
  KEY `FKC6CE7558CA279134` (`controllers_id`),
  CONSTRAINT `FKC6CE7558CA279134` FOREIGN KEY (`controllers_id`) REFERENCES `controller` (`id`),
  CONSTRAINT `FKC6CE7558F8F03845` FOREIGN KEY (`ControllerGroup_id`) REFERENCES `controllergroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controllergroup_controller`
--

LOCK TABLES `controllergroup_controller` WRITE;
/*!40000 ALTER TABLE `controllergroup_controller` DISABLE KEYS */;
INSERT INTO `controllergroup_controller` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8);
/*!40000 ALTER TABLE `controllergroup_controller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encontro`
--

DROP TABLE IF EXISTS `encontro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encontro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encontro`
--

LOCK TABLES `encontro` WRITE;
/*!40000 ALTER TABLE `encontro` DISABLE KEYS */;
/*!40000 ALTER TABLE `encontro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encontro_personagem`
--

DROP TABLE IF EXISTS `encontro_personagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encontro_personagem` (
  `encontro_id` bigint(20) NOT NULL,
  `personagem_id` bigint(20) NOT NULL,
  KEY `FK85564BEA742F9A6F` (`personagem_id`),
  KEY `FK85564BEA44D838F` (`encontro_id`),
  CONSTRAINT `FK85564BEA44D838F` FOREIGN KEY (`encontro_id`) REFERENCES `encontro` (`id`),
  CONSTRAINT `FK85564BEA742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encontro_personagem`
--

LOCK TABLES `encontro_personagem` WRITE;
/*!40000 ALTER TABLE `encontro_personagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `encontro_personagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encontro_rodada`
--

DROP TABLE IF EXISTS `encontro_rodada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encontro_rodada` (
  `Encontro_id` bigint(20) NOT NULL,
  `rodadas_id` bigint(20) NOT NULL,
  UNIQUE KEY `rodadas_id` (`rodadas_id`),
  KEY `FKC872F0BEBB0A1EA` (`rodadas_id`),
  KEY `FKC872F0BE44D838F` (`Encontro_id`),
  CONSTRAINT `FKC872F0BE44D838F` FOREIGN KEY (`Encontro_id`) REFERENCES `encontro` (`id`),
  CONSTRAINT `FKC872F0BEBB0A1EA` FOREIGN KEY (`rodadas_id`) REFERENCES `rodada` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encontro_rodada`
--

LOCK TABLES `encontro_rodada` WRITE;
/*!40000 ALTER TABLE `encontro_rodada` DISABLE KEYS */;
/*!40000 ALTER TABLE `encontro_rodada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habilidade`
--

DROP TABLE IF EXISTS `habilidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `habilidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habilidade`
--

LOCK TABLES `habilidade` WRITE;
/*!40000 ALTER TABLE `habilidade` DISABLE KEYS */;
INSERT INTO `habilidade` VALUES (1,'Representa a força física','Força'),(2,'Representa a prestreza e celeridade','Destreza'),(3,'Representa a vitalidade','Constituição'),(4,'Representa o quoficiente de inteligencia','Inteligência'),(5,'Representa a gama de conhecimento','Sabedoria'),(6,'Representa a habilidade social','Carisma');
/*!40000 ALTER TABLE `habilidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `livro_id` bigint(20) DEFAULT NULL,
  `pagina` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22EF33BD807945` (`livro_id`),
  CONSTRAINT `FK22EF33BD807945` FOREIGN KEY (`livro_id`) REFERENCES `livro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (2,'Teste',1,32);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `versao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES (1,'Livro do Jogador','3.5');
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pericia`
--

DROP TABLE IF EXISTS `pericia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pericia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `habilidade_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3AC3AE2F5085D36F` (`habilidade_id`),
  CONSTRAINT `FK3AC3AE2F5085D36F` FOREIGN KEY (`habilidade_id`) REFERENCES `habilidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pericia`
--

LOCK TABLES `pericia` WRITE;
/*!40000 ALTER TABLE `pericia` DISABLE KEYS */;
/*!40000 ALTER TABLE `pericia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pAdd` tinyint(1) DEFAULT NULL,
  `pDel` tinyint(1) DEFAULT NULL,
  `pEdit` tinyint(1) DEFAULT NULL,
  `pGrid` tinyint(1) DEFAULT NULL,
  `pView` tinyint(1) DEFAULT NULL,
  `cont_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK57F7A1EF8FFDC079` (`cont_id`),
  CONSTRAINT `FK57F7A1EF8FFDC079` FOREIGN KEY (`cont_id`) REFERENCES `controller` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,1,1,1,1,1,1),(2,1,1,1,1,1,2),(3,1,1,1,1,1,3),(4,1,1,1,1,1,4),(5,1,1,1,1,1,5),(6,1,1,1,1,1,6),(7,1,1,1,1,1,7),(8,1,1,1,1,1,8);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personagem`
--

DROP TABLE IF EXISTS `personagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personagem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `systemUser_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9F4B6C43648BDB2F` (`systemUser_id`),
  CONSTRAINT `FK9F4B6C43648BDB2F` FOREIGN KEY (`systemUser_id`) REFERENCES `systemuser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personagem`
--

LOCK TABLES `personagem` WRITE;
/*!40000 ALTER TABLE `personagem` DISABLE KEYS */;
INSERT INTO `personagem` VALUES (10,'Personagem teste1',1);
/*!40000 ALTER TABLE `personagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personagem_talento`
--

DROP TABLE IF EXISTS `personagem_talento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personagem_talento` (
  `Personagem_id` bigint(20) NOT NULL,
  `talentos_id` bigint(20) NOT NULL,
  KEY `FK64CB24E7742F9A6F` (`Personagem_id`),
  KEY `FK64CB24E7FD674978` (`talentos_id`),
  CONSTRAINT `FK64CB24E7742F9A6F` FOREIGN KEY (`Personagem_id`) REFERENCES `personagem` (`id`),
  CONSTRAINT `FK64CB24E7FD674978` FOREIGN KEY (`talentos_id`) REFERENCES `talento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personagem_talento`
--

LOCK TABLES `personagem_talento` WRITE;
/*!40000 ALTER TABLE `personagem_talento` DISABLE KEYS */;
INSERT INTO `personagem_talento` VALUES (10,4);
/*!40000 ALTER TABLE `personagem_talento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personagemhabilidade`
--

DROP TABLE IF EXISTS `personagemhabilidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personagemhabilidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `valor` int(11) DEFAULT NULL,
  `habilidade_id` bigint(20) DEFAULT NULL,
  `personagem_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC848845E5085D36F` (`habilidade_id`),
  KEY `FKC848845E742F9A6F` (`personagem_id`),
  CONSTRAINT `FKC848845E5085D36F` FOREIGN KEY (`habilidade_id`) REFERENCES `habilidade` (`id`),
  CONSTRAINT `FKC848845E742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personagemhabilidade`
--

LOCK TABLES `personagemhabilidade` WRITE;
/*!40000 ALTER TABLE `personagemhabilidade` DISABLE KEYS */;
INSERT INTO `personagemhabilidade` VALUES (55,14,2,10),(56,16,4,10),(57,10,6,10),(58,12,1,10),(59,13,5,10),(60,10,3,10);
/*!40000 ALTER TABLE `personagemhabilidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personagemitem`
--

DROP TABLE IF EXISTS `personagemitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personagemitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` double NOT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `personagem_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF78881F6DC55082F` (`item_id`),
  KEY `FKF78881F6742F9A6F` (`personagem_id`),
  CONSTRAINT `FKF78881F6742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKF78881F6DC55082F` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personagemitem`
--

LOCK TABLES `personagemitem` WRITE;
/*!40000 ALTER TABLE `personagemitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `personagemitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personagempericia`
--

DROP TABLE IF EXISTS `personagempericia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personagempericia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `graduacao` double DEFAULT NULL,
  `pericia_id` bigint(20) DEFAULT NULL,
  `personagem_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFF5808C1C568BE5` (`pericia_id`),
  KEY `FKFF5808C742F9A6F` (`personagem_id`),
  CONSTRAINT `FKFF5808C1C568BE5` FOREIGN KEY (`pericia_id`) REFERENCES `pericia` (`id`),
  CONSTRAINT `FKFF5808C742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personagempericia`
--

LOCK TABLES `personagempericia` WRITE;
/*!40000 ALTER TABLE `personagempericia` DISABLE KEYS */;
/*!40000 ALTER TABLE `personagempericia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rodada`
--

DROP TABLE IF EXISTS `rodada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rodada` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero` int(11) DEFAULT NULL,
  `encontro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9238819744D838F` (`encontro_id`),
  CONSTRAINT `FK9238819744D838F` FOREIGN KEY (`encontro_id`) REFERENCES `encontro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rodada`
--

LOCK TABLES `rodada` WRITE;
/*!40000 ALTER TABLE `rodada` DISABLE KEYS */;
/*!40000 ALTER TABLE `rodada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rodada_turno`
--

DROP TABLE IF EXISTS `rodada_turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rodada_turno` (
  `Rodada_id` bigint(20) NOT NULL,
  `turnos_id` bigint(20) NOT NULL,
  UNIQUE KEY `turnos_id` (`turnos_id`),
  KEY `FKE6D3B6CA622C55D6` (`turnos_id`),
  KEY `FKE6D3B6CA102CBC6F` (`Rodada_id`),
  CONSTRAINT `FKE6D3B6CA102CBC6F` FOREIGN KEY (`Rodada_id`) REFERENCES `rodada` (`id`),
  CONSTRAINT `FKE6D3B6CA622C55D6` FOREIGN KEY (`turnos_id`) REFERENCES `turno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rodada_turno`
--

LOCK TABLES `rodada_turno` WRITE;
/*!40000 ALTER TABLE `rodada_turno` DISABLE KEYS */;
/*!40000 ALTER TABLE `rodada_turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userGroup_id` bigint(20) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `login` (`login`),
  KEY `FK9D23FEBA10F22E5` (`userGroup_id`),
  CONSTRAINT `FK9D23FEBA10F22E5` FOREIGN KEY (`userGroup_id`) REFERENCES `usergroup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
INSERT INTO `systemuser` VALUES (1,'admin@mail.com','Administrador','0de54161ae43ed61db8165b69f850dc9cfccfee891e9f4f74a2796196f5efbd4b27877773c6a4d14711aead13e405896b64d2c21040e57bc268d3ae341352224',1,'');
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talento`
--

DROP TABLE IF EXISTS `talento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talento`
--

LOCK TABLES `talento` WRITE;
/*!40000 ALTER TABLE `talento` DISABLE KEYS */;
INSERT INTO `talento` VALUES (2,'Teste\r\n','Lutar com Duas Armas'),(3,'Att','Ataque Poderoso'),(4,'alskdfjajkl','Especialização em Combate');
/*!40000 ALTER TABLE `talento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno`
--

DROP TABLE IF EXISTS `turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `personagem_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4D69F72742F9A6F` (`personagem_id`),
  CONSTRAINT `FK4D69F72742F9A6F` FOREIGN KEY (`personagem_id`) REFERENCES `personagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup`
--

LOCK TABLES `usergroup` WRITE;
/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` VALUES (1,'Permissão total','Administrador');
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup_permission`
--

DROP TABLE IF EXISTS `usergroup_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup_permission` (
  `UserGroup_id` bigint(20) NOT NULL,
  `permissions_id` bigint(20) NOT NULL,
  UNIQUE KEY `permissions_id` (`permissions_id`),
  KEY `FK403DB9BA10F22E5` (`UserGroup_id`),
  KEY `FK403DB9BABCC4041A` (`permissions_id`),
  CONSTRAINT `FK403DB9BA10F22E5` FOREIGN KEY (`UserGroup_id`) REFERENCES `usergroup` (`id`),
  CONSTRAINT `FK403DB9BABCC4041A` FOREIGN KEY (`permissions_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup_permission`
--

LOCK TABLES `usergroup_permission` WRITE;
/*!40000 ALTER TABLE `usergroup_permission` DISABLE KEYS */;
INSERT INTO `usergroup_permission` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8);
/*!40000 ALTER TABLE `usergroup_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `campanhasview`
--

/*!50001 DROP TABLE IF EXISTS `campanhasview`*/;
/*!50001 DROP VIEW IF EXISTS `campanhasview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `campanhasview` AS select `c`.`id` AS `id`,`c`.`nome` AS `nome`,`c`.`mestre_id` AS `mestre`,NULL AS `jogador` from `campanha` `c` union all select `cc`.`id` AS `id`,`cc`.`nome` AS `nome`,`cc`.`mestre_id` AS `mestre`,`p`.`systemUser_id` AS `jogador` from ((`campanha` `cc` join `campanha_personagem` `cp` on((`cc`.`id` = `cp`.`Campanha_id`))) join `personagem` `p` on((`cp`.`personagem_id` = `p`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-03 11:37:11
