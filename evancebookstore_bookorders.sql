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
-- Table structure for table `bookorders`
--

DROP TABLE IF EXISTS `bookorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookorders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderId` varchar(45) NOT NULL,
  `userId` varchar(45) NOT NULL,
  `usePoint` int NOT NULL,
  `finalAmount` int NOT NULL,
  `newPoint` int NOT NULL,
  `orderTime` datetime NOT NULL,
  `employeesId` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookorders`
--

LOCK TABLES `bookorders` WRITE;
/*!40000 ALTER TABLE `bookorders` DISABLE KEYS */;
INSERT INTO `bookorders` VALUES (1,'o0001','a1',0,2422,121,'2024-09-11 10:14:56','By User'),(2,'o0002','a1',0,308,15,'2024-09-11 13:12:48','By User'),(3,'o0003','a1',0,1728,86,'2024-09-11 14:01:46','By User'),(4,'o0004','yyyy',111,2081,104,'2024-09-11 14:03:43','By User'),(5,'o0005','yyyy',269,132,6,'2024-09-11 14:04:39','By User'),(6,'o0006','yyyy',6,367,18,'2024-09-11 14:04:53','By User'),(7,'o0007','a1',600,1656,82,'2024-09-11 14:39:43','By User'),(8,'o0008','a1',0,1167,58,'2024-09-11 14:46:08','By User'),(9,'o0009','yyyy',0,1167,58,'2024-09-11 14:48:16','By User'),(10,'o0010','evance',0,2178,108,'2024-09-11 15:59:58','By User'),(11,'o0011','evance',0,308,15,'2024-09-11 16:00:45','By User'),(12,'o0012','evance',0,308,15,'2024-09-11 16:01:12','By User'),(13,'o0013','evance',0,1317,65,'2024-09-11 16:03:11','By User'),(14,'o0014','evance',0,0,0,'2024-09-11 16:07:18','By User'),(15,'o0015','evance',0,589,29,'2024-09-11 16:08:54','By User'),(16,'o0016','evance',0,277,13,'2024-09-11 16:11:09','By User'),(17,'o0017','evance',2,628,31,'2024-09-11 16:36:26','By User'),(18,'o0018','evance',0,603,30,'2024-09-11 16:49:27','By User'),(19,'o0019','lero',0,308,15,'2024-09-12 11:41:24','By User'),(20,'o0020','lero',0,607,30,'2024-09-12 11:46:19','By User'),(21,'o0021','lero',5,1378,68,'2024-09-12 11:48:13','By User'),(22,'o0022','lero',0,306,15,'2024-09-12 11:48:42','By User'),(23,'o0023','apple',0,710,35,'2024-09-12 11:49:51','By User'),(24,'o0024','lucy',0,773,38,'2024-09-12 19:49:32','By User'),(25,'o0025','lucy',0,954,47,'2024-09-12 19:59:59','By User'),(26,'o0026','jack',0,1023,51,'2024-09-12 20:08:49','By User'),(27,'o0027','apple',0,1002,50,'2024-09-12 21:21:32','By User'),(28,'o0028','notmember',0,308,15,'2024-09-12 22:40:05','100'),(29,'o0029','notmember',0,394,19,'2024-09-12 22:46:38','108'),(30,'o0030','notmember',0,710,35,'2024-09-12 22:47:41','108'),(31,'o0031','notmember',0,399,19,'2024-09-12 22:49:35','108'),(32,'o0032','notmember',0,768,38,'2024-09-12 22:50:21','108'),(33,'o0033','notmember',0,679,33,'2024-09-12 22:51:39','100'),(34,'o0034','tommy',0,394,19,'2024-09-12 23:21:27','By User'),(35,'o0035','notmember',0,276,13,'2024-09-12 23:34:54','114'),(36,'o0036','tommy',0,379,18,'2024-09-12 23:49:22','100'),(37,'o0037','tommy',23,1548,77,'2024-09-12 23:51:11','100');
/*!40000 ALTER TABLE `bookorders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-13  0:07:30
