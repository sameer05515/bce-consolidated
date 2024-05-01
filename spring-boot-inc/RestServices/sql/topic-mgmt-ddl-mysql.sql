-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: topic-mgmt
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `groupviews`
--

DROP TABLE IF EXISTS `groupviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) NOT NULL,
  `viewid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8bwk7loervun51id4x7v5qua2` (`viewid`,`groupid`),
  KEY `FK_spu9g96ug3njkoyhwwubj0kam` (`groupid`),
  CONSTRAINT `FK_awjafickpxbaiu7qfntstkllu` FOREIGN KEY (`viewid`) REFERENCES `view` (`ID`),
  CONSTRAINT `FK_spu9g96ug3njkoyhwwubj0kam` FOREIGN KEY (`groupid`) REFERENCES `t_group` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_group`
--

DROP TABLE IF EXISTS `t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime NOT NULL,
  `last_updation_date` datetime NOT NULL,
  `description` longtext NOT NULL,
  `rating` int(11) NOT NULL,
  `title` longtext NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime NOT NULL,
  `last_updation_date` datetime NOT NULL,
  `description` longtext NOT NULL,
  `isprivate` bit(1) NOT NULL,
  `rating` int(11) NOT NULL,
  `title` longtext NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=373 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topicgroups`
--

DROP TABLE IF EXISTS `topicgroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topicgroups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) NOT NULL,
  `topicid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n61tvmdd3uuqk9fq6e4i16jy8` (`topicid`,`groupid`),
  KEY `FK_cor9qh790ccydysg9yt8r1j1j` (`groupid`),
  CONSTRAINT `FK_cor9qh790ccydysg9yt8r1j1j` FOREIGN KEY (`groupid`) REFERENCES `t_group` (`ID`),
  CONSTRAINT `FK_fu3o7d7vrcdtyxo31l7nqvph` FOREIGN KEY (`topicid`) REFERENCES `topic` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime NOT NULL,
  `last_updation_date` datetime NOT NULL,
  `description` longtext NOT NULL,
  `rating` int(11) NOT NULL,
  `title` longtext NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `viewtopics`
--

DROP TABLE IF EXISTS `viewtopics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `viewtopics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topicid` int(11) NOT NULL,
  `viewid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i9ni7awvi8fbvlafuj2jc3dgx` (`topicid`,`viewid`),
  KEY `FK_sphfrhsmk86o709nlr10wdcj1` (`viewid`),
  CONSTRAINT `FK_d537vo4b5xtguo52p1mp931c3` FOREIGN KEY (`topicid`) REFERENCES `topic` (`ID`),
  CONSTRAINT `FK_sphfrhsmk86o709nlr10wdcj1` FOREIGN KEY (`viewid`) REFERENCES `view` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-23 22:22:29
