CREATE DATABASE  IF NOT EXISTS `evancebookstore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `evancebookstore`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: evancebookstore
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `bookordersdetail`
--

DROP TABLE IF EXISTS `bookordersdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookordersdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookordersid` varchar(45) NOT NULL,
  `bookname` varchar(45) NOT NULL,
  `bookprice` int NOT NULL,
  `bookamount` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookordersdetail`
--

LOCK TABLES `bookordersdetail` WRITE;
/*!40000 ALTER TABLE `bookordersdetail` DISABLE KEYS */;
INSERT INTO `bookordersdetail` VALUES (1,'o0001','藥師謀殺案',308,1),(2,'o0001','謀殺藝術大學院',394,1),(3,'o0001','解憂雜貨店',276,2),(4,'o0001','謊言裡的魔術師',379,2),(5,'o0001','間諜海岸',410,1),(6,'o0002','藥師謀殺案',308,1),(7,'o0003','藥師謀殺案',308,1),(8,'o0003','和魔女共度的七天',316,2),(9,'o0003','懸案密碼9：純潔殺手',394,2),(10,'o0004','謊言裡的魔術師',379,1),(11,'o0004','謀殺藝術大學院',394,1),(12,'o0004','懸案密碼9：純潔殺手',394,1),(13,'o0004','黑水',363,1),(14,'o0004','間諜海岸',410,1),(15,'o0004','方舟',363,1),(16,'o0005','懸案密碼9：純潔殺手',394,1),(17,'o0005','解憂雜貨店',276,1),(18,'o0006','謊言裡的魔術師',379,1),(19,'o0007','藥師謀殺案',308,1),(20,'o0007','777',339,1),(21,'o0007','13．67',379,1),(22,'o0007','方舟',363,1),(23,'o0007','死了七次的伊芙琳',489,1),(24,'o0007','小提琴家',323,1),(25,'o0007','謊言裡的魔術師',379,1),(26,'o0007','解憂雜貨店',276,1),(27,'o0008','謀殺藝術大學院',394,1),(28,'o0008','間諜海岸',410,1),(29,'o0008','黑水',363,1),(30,'o0009','懸案密碼9：純潔殺手',394,1),(31,'o0009','間諜海岸',410,1),(32,'o0009','黑水',363,1),(33,'o0010','謊言裡的魔術師',379,2),(34,'o0010','謀殺藝術大學院',394,2),(35,'o0010','和魔女共度的七天',316,2),(36,'o0011','藥師謀殺案',308,1),(37,'o0012','藥師謀殺案',308,1),(38,'o0013','解憂雜貨店',276,1),(39,'o0013','懸案密碼9：純潔殺手',394,1),(40,'o0013','時間跳躍的妳來自昨日',331,1),(41,'o0013','和魔女共度的七天',316,1),(42,'o0015','謊言裡的魔術師',379,1),(43,'o0015','解憂雜貨店',276,1),(44,'o0016','藥師謀殺案',308,1),(45,'o0017','藥師謀殺案',308,1),(46,'o0017','謀殺藝術大學院',394,1),(47,'o0018','黑水',363,1),(48,'o0018','藥師謀殺案',308,1),(49,'o0019','藥師謀殺案',308,1),(50,'o0020','解憂雜貨店',276,1),(51,'o0020','時間跳躍的妳來自昨日',331,1),(52,'o0021','時間跳躍的妳來自昨日',331,2),(53,'o0021','方舟',363,2),(54,'o0022','小提琴家',323,1),(55,'o0023','和魔女共度的七天',316,1),(56,'o0023','謀殺藝術大學院',394,1),(57,'o0024','謊言裡的魔術師',379,1),(58,'o0024','謀殺藝術大學院',394,1),(59,'o0025','死神拉斯卡解謎中',342,1),(60,'o0025','雪女',252,1),(61,'o0025','詭祕客Crimystery2024',360,1),(62,'o0026','藥師謀殺案',308,1),(63,'o0026','和魔女共度的七天',316,1),(64,'o0026','光與影',399,1),(65,'o0027','解憂雜貨店',276,1),(66,'o0027','黑水',363,2),(67,'o0028','藥師謀殺案',308,1),(68,'o0029','謀殺藝術大學院',394,1),(69,'o0030','謀殺藝術大學院',394,1),(70,'o0030','和魔女共度的七天',316,1),(71,'o0031','光與影',399,1),(72,'o0032','光與影',399,1),(73,'o0032','迷宮裡的魔術師',410,1),(74,'o0033','黑水',363,1),(75,'o0033','和魔女共度的七天',316,1),(76,'o0034','懸案密碼9：純潔殺手',394,1),(77,'o0035','解憂雜貨店',276,1),(78,'o0036','謊言裡的魔術師',379,1),(79,'o0037','謊言裡的魔術師',379,1),(80,'o0037','死了七次的伊芙琳',489,1),(81,'o0037','方舟',363,2);
/*!40000 ALTER TABLE `bookordersdetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-13  9:23:56
