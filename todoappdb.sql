-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: todoappdb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_category`
--
use heroku_6f9fe01ecd52993;

DROP TABLE IF EXISTS `tbl_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `tbl_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8f25rdca1qev4kqtyrxwsx0k8` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_category`
--

LOCK TABLES `tbl_category` WRITE;
/*!40000 ALTER TABLE `tbl_category` DISABLE KEYS */;
INSERT INTO `tbl_category` VALUES (2,'Important'),(1,'My day'),(3,'New list');
/*!40000 ALTER TABLE `tbl_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_login_type`
--

DROP TABLE IF EXISTS `tbl_login_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `tbl_login_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_login_type`
--

LOCK TABLES `tbl_login_type` WRITE;
/*!40000 ALTER TABLE `tbl_login_type` DISABLE KEYS */;
INSERT INTO `tbl_login_type` VALUES (1,'Google'),(2,'Username and Password');
/*!40000 ALTER TABLE `tbl_login_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_new_list`
--

DROP TABLE IF EXISTS `tbl_new_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `tbl_new_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `fk_user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsykgag0hx8bkmh4688wb5ms6g` (`fk_user_id`),
  CONSTRAINT `FKsykgag0hx8bkmh4688wb5ms6g` FOREIGN KEY (`fk_user_id`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_new_list`
--

LOCK TABLES `tbl_new_list` WRITE;
/*!40000 ALTER TABLE `tbl_new_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_new_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_task`
--

DROP TABLE IF EXISTS `tbl_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `tbl_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `completed` bit(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `fk_category_id` int NOT NULL,
  `fk_new_list_id` int DEFAULT NULL,
  `fk_user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr7dtvtxqjeq0ru37mik0nv34a` (`fk_category_id`),
  KEY `FK3pc5ceirtcdkxb8xtcv3napp5` (`fk_new_list_id`),
  KEY `FK570dy0fxrbo478ve5e5yddlcq` (`fk_user_id`),
  CONSTRAINT `FK3pc5ceirtcdkxb8xtcv3napp5` FOREIGN KEY (`fk_new_list_id`) REFERENCES `tbl_new_list` (`id`),
  CONSTRAINT `FK570dy0fxrbo478ve5e5yddlcq` FOREIGN KEY (`fk_user_id`) REFERENCES `tbl_user` (`id`),
  CONSTRAINT `FKr7dtvtxqjeq0ru37mik0nv34a` FOREIGN KEY (`fk_category_id`) REFERENCES `tbl_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_task`
--

LOCK TABLES `tbl_task` WRITE;
/*!40000 ALTER TABLE `tbl_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `display_name` varchar(255) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `fk_login_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK2f0bnfiyttbr0dd1xn5079ev1` (`email`,`username`),
  KEY `FKl2vg5jmgnnoqbk8iet86xh0ht` (`fk_login_type_id`),
  CONSTRAINT `FKl2vg5jmgnnoqbk8iet86xh0ht` FOREIGN KEY (`fk_login_type_id`) REFERENCES `tbl_login_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'2022-05-31 01:03:44','Kiet dep trai',NULL,'fb219cb336cb328448527531f7a576638bad550f608484d67219d6d06723667d','2022-05-31 01:03:44','kietdeptrai',2),(2,'2022-05-31 01:07:24','Kiet dep trai','kietnguyen17052001@gmail.com',NULL,'2022-05-31 01:07:24',NULL,1);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-31  1:10:06
