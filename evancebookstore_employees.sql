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
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(25) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `employeeName` varchar(25) DEFAULT NULL,
  `department` varchar(25) DEFAULT NULL,
  `managerId` varchar(25) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `admin` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employeeId_UNIQUE` (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'100','1234','admin','A','','0911','comalexander@evancebookstore.com',120000,1),(2,'101','123456','Neena','A','100','0922','neena@evancebookstore.com',85000,1),(3,'102','111111','Lex','A','100','0933','lexlexlex@evancebookstore.com',85000,1),(4,'103','123123','Alexander','B','102','0944','alexander@evancebookstore.com',45000,0),(5,'104','000000','Bruce','B','102','0955','goodbruce@evancebookstore.com',30000,0),(6,'105','114422','David','C','101','0954','david1997@evancebookstore.com',28000,0),(7,'106','3232123','Valli','C','101','0943','vallivalli@evancebookstore.com',28000,0),(8,'107','321321','Diana','B','102','0932','diana@evancebookstore.com',28000,0),(9,'108','77777','Nancy','B','100','0921','nancy@evancebookstore.com',60000,0),(10,'109','88888','Daniel','D','101','0910','daniel@evancebookstore.com',45000,0),(11,'110','aaaddd','John','D','101','0982','john@evancebookstore.com',41000,0),(12,'111','223388','Amy','A','102','091323','AmyWang@evancebookstore.com',NULL,NULL),(13,'112','tom7661','tom','A','103','0912221122','tom879@evancebookstore.com',NULL,NULL),(14,'113','0213','Irene','A','102','09282736','Irene@gmmaaail.com',NULL,NULL),(15,'114','000','ihopenobug',NULL,NULL,'09123','000',NULL,NULL);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
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
