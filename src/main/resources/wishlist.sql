CREATE DATABASE  IF NOT EXISTS `wishlist` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wishlist`;

DROP TABLE IF EXISTS `wishlists`;

CREATE TABLE `wishlists` (
  `wishlistID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `itemName` varchar(255) NOT NULL,
  `itemQuantity` smallint NOT NULL,
  PRIMARY KEY (`wishlistID`),
  UNIQUE KEY `itemName_UNIQUE` (`itemName`),
  KEY `fkwishlistusers_idx` (`userID`),
  CONSTRAINT `fkwishlistusers` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
