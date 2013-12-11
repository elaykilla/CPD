# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Hôte: cpd.cyasppybz2f8.us-east-1.rds.amazonaws.com (MySQL 5.5.31-log)
# Base de données: cpd
# Temps de génération: 2013-12-01 17:20:22 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Affichage de la table AS_ANNOUNCES_COMMENTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_ANNOUNCES_COMMENTS`;

CREATE TABLE `AS_ANNOUNCES_COMMENTS` (
  `ANNOUNCE_ID` bigint(20) NOT NULL,
  `COMMENT_ID` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `COMMENT_ID` (`COMMENT_ID`),
  KEY `FKA4219EB652926B47` (`ANNOUNCE_ID`),
  KEY `FKA4219EB6B3AD298D` (`COMMENT_ID`),
  KEY `FKA4219EB6F3D50C72` (`modifier_id`),
  CONSTRAINT `FKA4219EB6F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FKA4219EB652926B47` FOREIGN KEY (`ANNOUNCE_ID`) REFERENCES `T_ANNOUNCES` (`id`),
  CONSTRAINT `FKA4219EB6B3AD298D` FOREIGN KEY (`COMMENT_ID`) REFERENCES `T_COMMENTS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_ANNOUNCES_COMMENTS` WRITE;
/*!40000 ALTER TABLE `AS_ANNOUNCES_COMMENTS` DISABLE KEYS */;

INSERT INTO `AS_ANNOUNCES_COMMENTS` (`ANNOUNCE_ID`, `COMMENT_ID`, `CREATED`, `MODIFIED`, `modifier_id`)
VALUES
	(3,4,NULL,NULL,NULL),
	(3,5,NULL,NULL,NULL),
	(3,9,NULL,NULL,NULL),
	(6,12,NULL,NULL,NULL),
	(6,28,NULL,NULL,NULL),
	(3,29,NULL,NULL,NULL),
	(6,30,NULL,NULL,NULL),
	(8,53,NULL,NULL,NULL),
	(8,54,NULL,NULL,NULL),
	(8,55,NULL,NULL,NULL),
	(6,56,NULL,NULL,NULL),
	(8,58,NULL,NULL,NULL),
	(3,63,NULL,NULL,NULL);

/*!40000 ALTER TABLE `AS_ANNOUNCES_COMMENTS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_ANNOUNCES_SUBSCRIPTIONS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_ANNOUNCES_SUBSCRIPTIONS`;

CREATE TABLE `AS_ANNOUNCES_SUBSCRIPTIONS` (
  `announce_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `NOMBER_NOTIFY` int(11) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`announce_id`,`user_id`),
  KEY `FK37BD3B5447140EFE` (`user_id`),
  KEY `FK37BD3B5452926B47` (`announce_id`),
  KEY `FK37BD3B54F3D50C72` (`modifier_id`),
  CONSTRAINT `FK37BD3B54F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK37BD3B5447140EFE` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK37BD3B5452926B47` FOREIGN KEY (`announce_id`) REFERENCES `T_ANNOUNCES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_ANNOUNCES_SUBSCRIPTIONS` WRITE;
/*!40000 ALTER TABLE `AS_ANNOUNCES_SUBSCRIPTIONS` DISABLE KEYS */;

INSERT INTO `AS_ANNOUNCES_SUBSCRIPTIONS` (`announce_id`, `user_id`, `CREATED`, `MODIFIED`, `NOMBER_NOTIFY`, `modifier_id`)
VALUES
	(3,144,'2013-10-18 21:20:20','2013-11-15 12:38:37',2,NULL),
	(3,236,'2013-11-12 23:38:23','2013-11-15 12:38:38',1,NULL),
	(6,144,'2013-10-27 12:04:34','2013-11-14 18:55:54',2,NULL),
	(8,247,'2013-11-14 17:43:48','2013-11-14 21:55:02',2,NULL);

/*!40000 ALTER TABLE `AS_ANNOUNCES_SUBSCRIPTIONS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_ARTICLES_COMMENTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_ARTICLES_COMMENTS`;

CREATE TABLE `AS_ARTICLES_COMMENTS` (
  `ARTICLE_ID` bigint(20) NOT NULL,
  `COMMENT_ID` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `COMMENT_ID` (`COMMENT_ID`),
  KEY `FK2B2E7AE9239D0E2D` (`ARTICLE_ID`),
  KEY `FK2B2E7AE9B3AD298D` (`COMMENT_ID`),
  KEY `FK2B2E7AE9F3D50C72` (`modifier_id`),
  CONSTRAINT `FK2B2E7AE9F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK2B2E7AE9239D0E2D` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `T_ARTICLES` (`id`),
  CONSTRAINT `FK2B2E7AE9B3AD298D` FOREIGN KEY (`COMMENT_ID`) REFERENCES `T_COMMENTS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_ARTICLES_COMMENTS` WRITE;
/*!40000 ALTER TABLE `AS_ARTICLES_COMMENTS` DISABLE KEYS */;

INSERT INTO `AS_ARTICLES_COMMENTS` (`ARTICLE_ID`, `COMMENT_ID`, `CREATED`, `MODIFIED`, `modifier_id`)
VALUES
	(9,18,'2013-10-21 01:58:11',NULL,NULL),
	(9,22,'2013-10-22 14:41:56',NULL,NULL),
	(9,25,'2013-10-22 15:17:03',NULL,NULL),
	(9,26,'2013-10-24 13:28:47',NULL,NULL),
	(9,27,'2013-10-24 22:15:31',NULL,NULL),
	(11,31,'2013-11-05 21:19:40',NULL,NULL),
	(11,32,'2013-11-05 21:24:23',NULL,NULL),
	(11,33,'2013-11-05 21:25:43',NULL,NULL),
	(12,34,'2013-11-12 17:06:12',NULL,NULL),
	(11,35,'2013-11-12 17:08:52',NULL,NULL),
	(11,36,'2013-11-12 20:12:31',NULL,NULL),
	(11,37,'2013-11-12 20:31:40',NULL,NULL),
	(11,38,'2013-11-12 20:39:37',NULL,NULL),
	(11,39,'2013-11-12 20:59:28',NULL,NULL),
	(11,40,'2013-11-12 22:21:49',NULL,NULL),
	(12,41,'2013-11-12 23:05:03',NULL,NULL),
	(11,42,'2013-11-12 23:13:46',NULL,NULL),
	(11,43,'2013-11-12 23:28:58',NULL,NULL),
	(12,44,'2013-11-12 23:39:57',NULL,NULL),
	(11,45,'2013-11-13 11:03:12',NULL,NULL),
	(11,46,'2013-11-13 11:10:44',NULL,NULL),
	(11,47,'2013-11-13 18:30:28',NULL,NULL),
	(11,48,'2013-11-13 20:10:29',NULL,NULL),
	(11,49,'2013-11-13 20:23:52',NULL,NULL),
	(11,50,'2013-11-13 20:30:41',NULL,NULL),
	(11,51,'2013-11-14 15:03:14',NULL,NULL),
	(11,52,'2013-11-14 15:03:42',NULL,NULL),
	(13,57,'2013-11-14 21:12:24',NULL,NULL),
	(13,59,'2013-11-14 22:06:35',NULL,NULL),
	(13,60,'2013-11-14 22:09:45',NULL,NULL),
	(13,61,'2013-11-14 22:11:49',NULL,NULL),
	(13,62,'2013-11-15 17:04:16',NULL,NULL),
	(13,64,'2013-11-15 15:04:46',NULL,NULL),
	(13,65,'2013-11-15 21:00:44',NULL,NULL),
	(13,66,'2013-11-16 03:15:05',NULL,NULL),
	(13,67,'2013-11-16 10:38:19',NULL,NULL),
	(13,68,'2013-11-16 10:38:54',NULL,NULL),
	(9,69,'2013-11-16 14:30:20',NULL,NULL),
	(14,70,'2013-11-26 13:46:02',NULL,NULL),
	(9,71,'2013-11-26 21:47:52',NULL,NULL),
	(14,72,'2013-11-27 14:44:41',NULL,NULL);

/*!40000 ALTER TABLE `AS_ARTICLES_COMMENTS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_ARTICLES_SUBSCRIPTIONS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_ARTICLES_SUBSCRIPTIONS`;

CREATE TABLE `AS_ARTICLES_SUBSCRIPTIONS` (
  `article_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `NOMBER_NOTIFY` int(11) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`article_id`,`user_id`),
  KEY `FKF9610701239D0E2D` (`article_id`),
  KEY `FKF961070147140EFE` (`user_id`),
  KEY `FKF9610701F3D50C72` (`modifier_id`),
  CONSTRAINT `FKF9610701F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FKF9610701239D0E2D` FOREIGN KEY (`article_id`) REFERENCES `T_ARTICLES` (`id`),
  CONSTRAINT `FKF961070147140EFE` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_ARTICLES_SUBSCRIPTIONS` WRITE;
/*!40000 ALTER TABLE `AS_ARTICLES_SUBSCRIPTIONS` DISABLE KEYS */;

INSERT INTO `AS_ARTICLES_SUBSCRIPTIONS` (`article_id`, `user_id`, `CREATED`, `MODIFIED`, `NOMBER_NOTIFY`, `modifier_id`)
VALUES
	(9,144,'2013-10-21 03:45:17','2013-11-26 21:47:52',4,NULL),
	(9,147,'2013-10-24 13:29:16','2013-11-26 21:47:52',3,NULL),
	(9,153,'2013-10-21 01:54:40','2013-11-26 21:47:52',10,NULL),
	(9,249,'2013-11-26 21:46:05','2013-11-26 21:46:05',0,NULL),
	(11,83,'2013-11-13 20:46:20','2013-11-14 15:03:42',2,NULL),
	(11,144,'2013-11-05 22:34:08','2013-11-14 15:03:42',13,NULL),
	(11,223,'2013-11-05 19:14:24','2013-11-14 15:03:42',14,NULL),
	(12,144,'2013-11-06 09:32:43','2013-11-12 23:39:57',3,NULL),
	(12,223,'2013-11-05 20:58:36','2013-11-12 23:39:57',3,NULL),
	(13,144,'2013-11-14 21:12:08','2013-11-16 10:38:54',9,NULL),
	(13,223,'2013-11-14 18:38:32','2013-11-16 03:15:05',6,NULL),
	(13,236,'2013-11-15 21:01:14','2013-11-16 10:38:54',3,NULL),
	(14,121,'2013-11-27 14:49:07','2013-11-27 14:49:07',0,NULL),
	(14,144,'2013-11-26 09:30:08','2013-11-27 14:44:41',2,NULL),
	(14,201,'2013-11-26 13:23:38','2013-11-27 14:44:41',1,NULL),
	(14,265,'2013-11-22 07:09:17','2013-11-27 14:44:41',2,NULL);

/*!40000 ALTER TABLE `AS_ARTICLES_SUBSCRIPTIONS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_CVS_LANGUAGES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_CVS_LANGUAGES`;

CREATE TABLE `AS_CVS_LANGUAGES` (
  `CV_ID` bigint(20) NOT NULL,
  `LANGUAGE_ID` bigint(20) NOT NULL,
  UNIQUE KEY `LANGUAGE_ID` (`LANGUAGE_ID`),
  KEY `FK2971B36FDBD2534C` (`CV_ID`),
  KEY `FK2971B36FE26F8FAC` (`LANGUAGE_ID`),
  CONSTRAINT `FK2971B36FDBD2534C` FOREIGN KEY (`CV_ID`) REFERENCES `T_CVS` (`id`),
  CONSTRAINT `FK2971B36FE26F8FAC` FOREIGN KEY (`LANGUAGE_ID`) REFERENCES `T_LANGUAGES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_CVS_LANGUAGES` WRITE;
/*!40000 ALTER TABLE `AS_CVS_LANGUAGES` DISABLE KEYS */;

INSERT INTO `AS_CVS_LANGUAGES` (`CV_ID`, `LANGUAGE_ID`)
VALUES
	(121,4),
	(144,1),
	(144,2),
	(144,8),
	(234,13),
	(234,14),
	(234,15);

/*!40000 ALTER TABLE `AS_CVS_LANGUAGES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_DENDRITES_MESSAGES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_DENDRITES_MESSAGES`;

CREATE TABLE `AS_DENDRITES_MESSAGES` (
  `dendrite_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `READY` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dendrite_id`,`user_id`),
  KEY `FK7C718784EBA3847E` (`dendrite_id`),
  KEY `FK7C71878447140EFE` (`user_id`),
  KEY `FK7C718784F3D50C72` (`modifier_id`),
  CONSTRAINT `FK7C718784F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK7C71878447140EFE` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK7C718784EBA3847E` FOREIGN KEY (`dendrite_id`) REFERENCES `T_DENDRITES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_DENDRITES_MESSAGES` WRITE;
/*!40000 ALTER TABLE `AS_DENDRITES_MESSAGES` DISABLE KEYS */;

INSERT INTO `AS_DENDRITES_MESSAGES` (`dendrite_id`, `user_id`, `CREATED`, `MODIFIED`, `READY`, `modifier_id`)
VALUES
	(2,144,'2013-10-21 11:53:31','2013-10-25 08:15:43','isread',144);

/*!40000 ALTER TABLE `AS_DENDRITES_MESSAGES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_DENDRITES_POSTES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_DENDRITES_POSTES`;

CREATE TABLE `AS_DENDRITES_POSTES` (
  `DENDRITE_ID` bigint(20) NOT NULL,
  `POSTE_ID` bigint(20) NOT NULL,
  KEY `FK18FAB586EBA3847E` (`DENDRITE_ID`),
  KEY `FK18FAB586B9253C82` (`POSTE_ID`),
  CONSTRAINT `FK18FAB586B9253C82` FOREIGN KEY (`POSTE_ID`) REFERENCES `T_POSTES` (`id`),
  CONSTRAINT `FK18FAB586EBA3847E` FOREIGN KEY (`DENDRITE_ID`) REFERENCES `T_DENDRITES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_DENDRITES_POSTES` WRITE;
/*!40000 ALTER TABLE `AS_DENDRITES_POSTES` DISABLE KEYS */;

INSERT INTO `AS_DENDRITES_POSTES` (`DENDRITE_ID`, `POSTE_ID`)
VALUES
	(2,1),
	(2,2),
	(2,4),
	(2,5),
	(2,6),
	(2,7),
	(4,1),
	(4,2),
	(4,4),
	(4,5),
	(4,6),
	(4,7),
	(5,1),
	(5,2),
	(5,4),
	(5,5),
	(5,6),
	(5,7),
	(1,9),
	(1,1),
	(1,2),
	(1,4),
	(1,5),
	(1,6),
	(1,7);

/*!40000 ALTER TABLE `AS_DENDRITES_POSTES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_EVENT_ADMIN
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_EVENT_ADMIN`;

CREATE TABLE `AS_EVENT_ADMIN` (
  `event_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`user_id`),
  KEY `FK5971509D81DE9749` (`event_id`),
  KEY `FK5971509D47140EFE` (`user_id`),
  KEY `FK5971509DF3D50C72` (`modifier_id`),
  CONSTRAINT `FK5971509DF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK5971509D47140EFE` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK5971509D81DE9749` FOREIGN KEY (`event_id`) REFERENCES `T_EVENTS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table AS_EVENT_INVITATIONS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_EVENT_INVITATIONS`;

CREATE TABLE `AS_EVENT_INVITATIONS` (
  `event_id` bigint(20) NOT NULL,
  `guest_id` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `PAYMENT_STATE` varchar(255) DEFAULT NULL,
  `INVITATION_STATE` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`guest_id`),
  KEY `FK3DE60AC881DE9749` (`event_id`),
  KEY `FK3DE60AC8FB8F75D1` (`guest_id`),
  KEY `FK3DE60AC8F3D50C72` (`modifier_id`),
  CONSTRAINT `FK3DE60AC8F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK3DE60AC881DE9749` FOREIGN KEY (`event_id`) REFERENCES `T_EVENTS` (`id`),
  CONSTRAINT `FK3DE60AC8FB8F75D1` FOREIGN KEY (`guest_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_EVENT_INVITATIONS` WRITE;
/*!40000 ALTER TABLE `AS_EVENT_INVITATIONS` DISABLE KEYS */;

INSERT INTO `AS_EVENT_INVITATIONS` (`event_id`, `guest_id`, `CREATED`, `MODIFIED`, `PAYMENT_STATE`, `INVITATION_STATE`, `modifier_id`)
VALUES
	(10,144,'2013-10-25 05:44:54','2013-10-25 05:44:54','NOT_PAID','GOING',144),
	(10,222,'2013-10-28 14:00:34','2013-10-28 14:00:34','NOT_PAID','GOING',222);

/*!40000 ALTER TABLE `AS_EVENT_INVITATIONS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_EVENTS_COMMENTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_EVENTS_COMMENTS`;

CREATE TABLE `AS_EVENTS_COMMENTS` (
  `EVENT_ID` bigint(20) NOT NULL,
  `COMMENT_ID` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `COMMENT_ID` (`COMMENT_ID`),
  KEY `FK9E52904D81DE9749` (`EVENT_ID`),
  KEY `FK9E52904DB3AD298D` (`COMMENT_ID`),
  KEY `FK9E52904DF3D50C72` (`modifier_id`),
  CONSTRAINT `FK9E52904DF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK9E52904D81DE9749` FOREIGN KEY (`EVENT_ID`) REFERENCES `T_EVENTS` (`id`),
  CONSTRAINT `FK9E52904DB3AD298D` FOREIGN KEY (`COMMENT_ID`) REFERENCES `T_COMMENTS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table AS_EVENTS_SUBSCRIPTIONS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_EVENTS_SUBSCRIPTIONS`;

CREATE TABLE `AS_EVENTS_SUBSCRIPTIONS` (
  `event_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `NOMBER_NOTIFY` int(11) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`user_id`),
  KEY `FK5D26B01D81DE9749` (`event_id`),
  KEY `FK5D26B01D47140EFE` (`user_id`),
  KEY `FK5D26B01DF3D50C72` (`modifier_id`),
  CONSTRAINT `FK5D26B01DF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK5D26B01D47140EFE` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK5D26B01D81DE9749` FOREIGN KEY (`event_id`) REFERENCES `T_EVENTS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_EVENTS_SUBSCRIPTIONS` WRITE;
/*!40000 ALTER TABLE `AS_EVENTS_SUBSCRIPTIONS` DISABLE KEYS */;

INSERT INTO `AS_EVENTS_SUBSCRIPTIONS` (`event_id`, `user_id`, `CREATED`, `MODIFIED`, `NOMBER_NOTIFY`, `modifier_id`)
VALUES
	(10,144,'2013-10-20 06:01:19','2013-10-20 06:01:19',0,NULL);

/*!40000 ALTER TABLE `AS_EVENTS_SUBSCRIPTIONS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_MANDATES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_MANDATES`;

CREATE TABLE `AS_MANDATES` (
  `DENDRITE_ID` bigint(20) NOT NULL,
  `ELECTION_ID` bigint(20) NOT NULL,
  `POSTE_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `BEGIN_MANDATE_DATE` datetime DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `END_MANDATE_DATE` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DENDRITE_ID`,`ELECTION_ID`,`POSTE_ID`,`USER_ID`),
  KEY `FK3D6CDAD8EBA3847E` (`DENDRITE_ID`),
  KEY `FK3D6CDAD847140EFE` (`USER_ID`),
  KEY `FK3D6CDAD84C982932` (`ELECTION_ID`),
  KEY `FK3D6CDAD8F3D50C72` (`modifier_id`),
  KEY `FK3D6CDAD8B9253C82` (`POSTE_ID`),
  CONSTRAINT `FK3D6CDAD8B9253C82` FOREIGN KEY (`POSTE_ID`) REFERENCES `T_POSTES` (`id`),
  CONSTRAINT `FK3D6CDAD847140EFE` FOREIGN KEY (`USER_ID`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK3D6CDAD84C982932` FOREIGN KEY (`ELECTION_ID`) REFERENCES `T_ELECTIONS` (`id`),
  CONSTRAINT `FK3D6CDAD8EBA3847E` FOREIGN KEY (`DENDRITE_ID`) REFERENCES `T_DENDRITES` (`id`),
  CONSTRAINT `FK3D6CDAD8F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_MANDATES` WRITE;
/*!40000 ALTER TABLE `AS_MANDATES` DISABLE KEYS */;

INSERT INTO `AS_MANDATES` (`DENDRITE_ID`, `ELECTION_ID`, `POSTE_ID`, `USER_ID`, `BEGIN_MANDATE_DATE`, `CREATED`, `DESCRIPTION`, `END_MANDATE_DATE`, `MODIFIED`, `modifier_id`)
VALUES
	(1,11,1,268,'2011-06-01 00:00:00','2013-11-29 02:28:10','Doumma est l\'actuel président de la CPD.\r\nSon mandat prend fin dès la nouvelle élection.','2014-01-01 00:00:00','2013-11-29 02:28:10',NULL);

/*!40000 ALTER TABLE `AS_MANDATES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_POSTES_ROLES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_POSTES_ROLES`;

CREATE TABLE `AS_POSTES_ROLES` (
  `POSTE_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  KEY `FK8FB3FB59A1E94B1E` (`ROLE_ID`),
  KEY `FK8FB3FB59B9253C82` (`POSTE_ID`),
  CONSTRAINT `FK8FB3FB59A1E94B1E` FOREIGN KEY (`ROLE_ID`) REFERENCES `T_ROLES` (`id`),
  CONSTRAINT `FK8FB3FB59B9253C82` FOREIGN KEY (`POSTE_ID`) REFERENCES `T_POSTES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_POSTES_ROLES` WRITE;
/*!40000 ALTER TABLE `AS_POSTES_ROLES` DISABLE KEYS */;

INSERT INTO `AS_POSTES_ROLES` (`POSTE_ID`, `ROLE_ID`)
VALUES
	(1,3),
	(8,3),
	(2,5);

/*!40000 ALTER TABLE `AS_POSTES_ROLES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_ROLES_RIGHTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_ROLES_RIGHTS`;

CREATE TABLE `AS_ROLES_RIGHTS` (
  `ROLE_ID` bigint(20) NOT NULL,
  `RIGHT_ID` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  KEY `FK537C64A6A1E94B1E` (`ROLE_ID`),
  KEY `FK537C64A655835B96` (`RIGHT_ID`),
  KEY `FK537C64A6F3D50C72` (`modifier_id`),
  CONSTRAINT `FK537C64A6F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK537C64A655835B96` FOREIGN KEY (`RIGHT_ID`) REFERENCES `T_RIGHTS` (`id`),
  CONSTRAINT `FK537C64A6A1E94B1E` FOREIGN KEY (`ROLE_ID`) REFERENCES `T_ROLES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_ROLES_RIGHTS` WRITE;
/*!40000 ALTER TABLE `AS_ROLES_RIGHTS` DISABLE KEYS */;

INSERT INTO `AS_ROLES_RIGHTS` (`ROLE_ID`, `RIGHT_ID`, `CREATED`, `DESCRIPTION`, `MODIFIED`, `modifier_id`)
VALUES
	(2,26,NULL,NULL,NULL,NULL),
	(2,27,NULL,NULL,NULL,NULL),
	(2,23,NULL,NULL,NULL,NULL),
	(2,30,NULL,NULL,NULL,NULL),
	(2,31,NULL,NULL,NULL,NULL),
	(3,30,NULL,NULL,NULL,NULL),
	(3,31,NULL,NULL,NULL,NULL),
	(3,23,NULL,NULL,NULL,NULL),
	(3,17,NULL,NULL,NULL,NULL),
	(3,16,NULL,NULL,NULL,NULL),
	(1,32,NULL,NULL,NULL,NULL),
	(1,45,NULL,NULL,NULL,NULL),
	(1,19,NULL,NULL,NULL,NULL),
	(1,46,NULL,NULL,NULL,NULL),
	(1,18,NULL,NULL,NULL,NULL),
	(1,44,NULL,NULL,NULL,NULL),
	(1,13,NULL,NULL,NULL,NULL),
	(1,11,NULL,NULL,NULL,NULL),
	(1,15,NULL,NULL,NULL,NULL),
	(1,17,NULL,NULL,NULL,NULL),
	(1,36,NULL,NULL,NULL,NULL),
	(1,7,NULL,NULL,NULL,NULL),
	(1,33,NULL,NULL,NULL,NULL),
	(1,49,NULL,NULL,NULL,NULL),
	(1,37,NULL,NULL,NULL,NULL),
	(1,42,NULL,NULL,NULL,NULL),
	(1,51,NULL,NULL,NULL,NULL),
	(1,50,NULL,NULL,NULL,NULL),
	(1,4,NULL,NULL,NULL,NULL),
	(1,3,NULL,NULL,NULL,NULL),
	(1,29,NULL,NULL,NULL,NULL),
	(1,25,NULL,NULL,NULL,NULL),
	(1,40,NULL,NULL,NULL,NULL),
	(1,9,NULL,NULL,NULL,NULL),
	(1,35,NULL,NULL,NULL,NULL),
	(1,21,NULL,NULL,NULL,NULL),
	(1,31,NULL,NULL,NULL,NULL),
	(1,27,NULL,NULL,NULL,NULL),
	(1,43,NULL,NULL,NULL,NULL),
	(1,23,NULL,NULL,NULL,NULL),
	(1,47,NULL,NULL,NULL,NULL),
	(1,6,NULL,NULL,NULL,NULL),
	(1,2,NULL,NULL,NULL,NULL),
	(1,28,NULL,NULL,NULL,NULL),
	(1,24,NULL,NULL,NULL,NULL),
	(1,38,NULL,NULL,NULL,NULL),
	(1,8,NULL,NULL,NULL,NULL),
	(1,34,NULL,NULL,NULL,NULL),
	(1,20,NULL,NULL,NULL,NULL),
	(1,30,NULL,NULL,NULL,NULL),
	(1,26,NULL,NULL,NULL,NULL),
	(1,39,NULL,NULL,NULL,NULL),
	(1,52,NULL,NULL,NULL,NULL),
	(1,41,NULL,NULL,NULL,NULL),
	(1,22,NULL,NULL,NULL,NULL),
	(1,54,NULL,NULL,NULL,NULL),
	(1,53,NULL,NULL,NULL,NULL),
	(1,48,NULL,NULL,NULL,NULL),
	(1,5,NULL,NULL,NULL,NULL),
	(1,1,NULL,NULL,NULL,NULL),
	(1,55,NULL,NULL,NULL,NULL),
	(1,12,NULL,NULL,NULL,NULL),
	(1,10,NULL,NULL,NULL,NULL),
	(1,14,NULL,NULL,NULL,NULL),
	(1,16,NULL,NULL,NULL,NULL),
	(2,43,NULL,NULL,NULL,NULL),
	(3,32,NULL,NULL,NULL,NULL),
	(3,37,NULL,NULL,NULL,NULL),
	(3,43,NULL,NULL,NULL,NULL),
	(3,42,NULL,NULL,NULL,NULL),
	(3,44,NULL,NULL,NULL,NULL),
	(2,32,NULL,NULL,NULL,NULL),
	(2,29,NULL,NULL,NULL,NULL),
	(2,25,NULL,NULL,NULL,NULL),
	(2,21,NULL,NULL,NULL,NULL),
	(4,32,NULL,NULL,NULL,NULL),
	(4,61,NULL,NULL,NULL,NULL),
	(4,60,NULL,NULL,NULL,NULL),
	(4,59,NULL,NULL,NULL,NULL),
	(4,58,NULL,NULL,NULL,NULL),
	(1,56,NULL,NULL,NULL,NULL),
	(1,57,NULL,NULL,NULL,NULL),
	(1,60,NULL,NULL,NULL,NULL),
	(1,61,NULL,NULL,NULL,NULL),
	(1,58,NULL,NULL,NULL,NULL),
	(1,59,NULL,NULL,NULL,NULL),
	(3,57,NULL,NULL,NULL,NULL),
	(5,32,NULL,NULL,NULL,NULL),
	(5,57,NULL,NULL,NULL,NULL),
	(1,62,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `AS_ROLES_RIGHTS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_USERS_MESSAGES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_USERS_MESSAGES`;

CREATE TABLE `AS_USERS_MESSAGES` (
  `user_id1` bigint(20) NOT NULL,
  `user_id2` bigint(20) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `READY` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id1`,`user_id2`),
  KEY `FK596A6D70F3D50C72` (`modifier_id`),
  KEY `FK596A6D703FFBF4F2` (`user_id2`),
  KEY `FK596A6D703FFBF4F1` (`user_id1`),
  CONSTRAINT `FK596A6D703FFBF4F1` FOREIGN KEY (`user_id1`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK596A6D703FFBF4F2` FOREIGN KEY (`user_id2`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK596A6D70F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_USERS_MESSAGES` WRITE;
/*!40000 ALTER TABLE `AS_USERS_MESSAGES` DISABLE KEYS */;

INSERT INTO `AS_USERS_MESSAGES` (`user_id1`, `user_id2`, `CREATED`, `MODIFIED`, `READY`, `modifier_id`)
VALUES
	(92,121,'2013-10-12 22:40:52','2013-10-26 10:03:00','notready',121),
	(92,144,'2013-10-12 22:36:57','2013-10-24 23:31:09','isread',92),
	(121,92,'2013-10-12 22:40:43','2013-10-26 10:05:08','isread',121),
	(121,144,'2013-11-05 10:24:07','2013-11-27 14:04:34','isread',121),
	(123,144,'2013-11-05 22:35:19','2013-11-06 20:16:49','isread',123),
	(144,92,'2013-10-12 22:37:47','2013-11-05 00:37:23','isread',144),
	(144,121,'2013-11-05 10:23:56','2013-11-15 09:22:34','isread',144),
	(144,123,'2013-11-05 22:34:37','2013-11-05 22:35:24','isread',144),
	(144,147,'2013-10-24 15:16:14','2013-11-13 20:25:45','isread',144),
	(144,153,'2013-10-20 23:28:16','2013-10-22 09:57:09','isread',144),
	(144,202,'2013-10-10 21:54:06','2013-11-14 21:10:34','isread',144),
	(144,223,'2013-11-06 19:46:12','2013-11-16 11:10:52','isread',144),
	(144,241,'2013-11-13 11:14:41','2013-11-13 22:47:17','isread',144),
	(144,251,'2013-11-16 21:55:52','2013-11-23 18:38:48','isread',144),
	(144,254,'2013-11-16 21:45:35','2013-11-27 14:57:58','isread',144),
	(144,256,'2013-11-15 12:58:04','2013-11-15 12:58:27','isread',144),
	(144,259,'2013-11-17 01:15:43','2013-11-17 01:15:59','isread',144),
	(147,144,'2013-10-24 15:17:31','2013-10-24 15:17:31','notready',144),
	(153,144,'2013-10-20 23:28:01','2013-10-21 18:30:32','isread',153),
	(202,144,'2013-10-10 23:15:48','2013-11-14 21:10:34','notready',144),
	(223,144,'2013-11-06 19:47:45','2013-11-16 11:10:52','notready',144),
	(223,243,'2013-11-13 20:27:10','2013-11-13 20:27:10','isread',223),
	(234,149,'2013-11-12 19:22:22','2013-11-12 19:22:22','isread',234),
	(236,241,'2013-11-13 21:17:29','2013-11-14 21:30:45','isread',236),
	(241,144,'2013-11-13 11:14:57','2013-11-13 22:36:59','isread',241),
	(251,144,'2013-11-16 21:56:34','2013-11-23 18:38:48','notready',144),
	(254,144,'2013-11-16 21:45:51','2013-11-16 21:45:51','notready',144),
	(255,249,'2013-11-15 05:07:20','2013-11-15 05:07:20','isread',255),
	(256,144,'2013-11-15 12:58:27','2013-11-15 12:58:27','notready',144),
	(259,144,'2013-11-17 01:15:58','2013-11-17 01:15:58','notready',144),
	(267,249,'2013-11-21 00:37:55','2013-11-21 00:37:55','isread',267);

/*!40000 ALTER TABLE `AS_USERS_MESSAGES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table AS_USERS_ROLES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AS_USERS_ROLES`;

CREATE TABLE `AS_USERS_ROLES` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  KEY `FK36732CF9A1E94B1E` (`ROLE_ID`),
  KEY `FK36732CF947140EFE` (`USER_ID`),
  CONSTRAINT `FK36732CF947140EFE` FOREIGN KEY (`USER_ID`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK36732CF9A1E94B1E` FOREIGN KEY (`ROLE_ID`) REFERENCES `T_ROLES` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `AS_USERS_ROLES` WRITE;
/*!40000 ALTER TABLE `AS_USERS_ROLES` DISABLE KEYS */;

INSERT INTO `AS_USERS_ROLES` (`USER_ID`, `ROLE_ID`)
VALUES
	(144,4),
	(144,1),
	(121,4),
	(121,1),
	(151,4),
	(151,1),
	(83,4),
	(83,1),
	(123,4),
	(123,1),
	(92,4),
	(92,1),
	(213,4);

/*!40000 ALTER TABLE `AS_USERS_ROLES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_ADRESS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_ADRESS`;

CREATE TABLE `T_ADRESS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `ADRESS` varchar(255) DEFAULT NULL,
  `PAYS` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `VILLE` varchar(255) DEFAULT NULL,
  `ZIP_CODE` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2A2248C1F3D50C72` (`modifier_id`),
  KEY `FK2A2248C1B2118A9E` (`visibility_id`),
  CONSTRAINT `FK2A2248C1B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK2A2248C1F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_ADRESS` WRITE;
/*!40000 ALTER TABLE `T_ADRESS` DISABLE KEYS */;

INSERT INTO `T_ADRESS` (`id`, `CREATED`, `MODIFIED`, `ADRESS`, `PAYS`, `PHONE`, `VILLE`, `ZIP_CODE`, `modifier_id`, `visibility_id`)
VALUES
	(46,'2013-09-12 17:48:42',NULL,'1 Rue de la merde','France','','Caca','92200',NULL,NULL),
	(57,'2013-10-10 22:02:59',NULL,'12 Boulevard des maréchaux','France','+33620790921','Palaiseau','91120',NULL,NULL),
	(58,'2013-10-12 22:34:52',NULL,'68 rue philippe de girard','France','','Paris','75018',NULL,NULL),
	(61,'2013-10-20 06:01:16','2013-10-20 06:01:18','Amblard','France','+3362345789','Valence','26000',144,NULL),
	(62,'2013-10-20 22:57:39',NULL,'9 Boulevard des Maréchaux','France','0621881787','Palaiseau','91120',NULL,NULL),
	(63,'2013-10-28 13:58:52','2013-10-28 13:58:52','21 Avenue de Verdun','France','0667510453','Valence','26000',NULL,NULL),
	(64,'2013-11-05 20:35:27','2013-11-05 20:35:27','Maisel Télécom Bretagne Bat I9 Ch 108','France','0613241308','Brest','29200',NULL,NULL),
	(65,'2013-11-05 20:35:57','2013-11-05 20:35:57','Brest','France','0613241308','Brest','29200',NULL,NULL),
	(66,'2013-11-05 20:58:17','2013-11-05 20:58:17','54 rue Amblard','France','0762324654','VALENCE','26000',NULL,NULL),
	(67,'2013-11-06 20:12:02','2013-11-06 20:12:02','23 Avenue Ampère Res Perronet','France','0625684436','Champs-sur-Marne','77420',NULL,NULL),
	(68,'2013-11-12 19:50:24','2013-11-12 19:50:24','1 avenue jarrow Apt 63','France','0695166362','Epinay sur seine','93800',NULL,NULL),
	(69,'2013-11-12 23:31:20','2013-11-12 23:31:20','Batiment Clacton 20 Rue Clacton Tendring Logement 403','France','0752522715','VALENCE','26000',NULL,NULL),
	(70,'2013-11-14 17:36:05','2013-11-14 17:36:05','51 chemin des Mouilles Residence Paul emile Victor','France','0658688065','Ecully','69130',NULL,NULL),
	(71,'2013-11-14 19:12:34','2013-11-14 19:12:34','31 avenue Lombart, Resid les lombard Ch.103','France','0668272657','Fontenay aux Roses','92260',NULL,NULL),
	(72,'2013-11-15 04:57:50','2013-11-15 04:57:50','A','France','0659858342','Paris','75000',NULL,NULL),
	(73,'2013-11-15 09:22:06','2013-11-15 09:22:06','Bagneux','France','','Bagneux','92220',NULL,NULL),
	(74,'2013-11-17 12:11:13','2013-11-17 12:11:13','8 passage des crayons','france','+33662150864','PARIS','75013',NULL,NULL),
	(75,'2013-11-21 00:30:50','2013-11-21 00:30:50','51 Chemin des Mouilles, Res Paul Emile Victor','France','0610332564','ECULLY','69130',NULL,NULL);

/*!40000 ALTER TABLE `T_ADRESS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_ANNOUNCES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_ANNOUNCES`;

CREATE TABLE `T_ANNOUNCES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CONTENT` longtext,
  `TITLE` varchar(255) NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `dendrite_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK780EAADFEBA3847E` (`dendrite_id`),
  KEY `FK780EAADFA7CD013E` (`author_id`),
  KEY `FK780EAADFF3D50C72` (`modifier_id`),
  KEY `FK780EAADFB2118A9E` (`visibility_id`),
  CONSTRAINT `FK780EAADFB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK780EAADFA7CD013E` FOREIGN KEY (`author_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK780EAADFEBA3847E` FOREIGN KEY (`dendrite_id`) REFERENCES `T_DENDRITES` (`id`),
  CONSTRAINT `FK780EAADFF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_ANNOUNCES` WRITE;
/*!40000 ALTER TABLE `T_ANNOUNCES` DISABLE KEYS */;

INSERT INTO `T_ANNOUNCES` (`id`, `CREATED`, `MODIFIED`, `CONTENT`, `TITLE`, `modifier_id`, `author_id`, `dendrite_id`, `visibility_id`)
VALUES
	(3,'2013-09-18 10:35:12','2013-10-14 18:01:21','Bonjour les amis. Je voulais juste vous informer que je suis au Japon. Si jamais vous faites un tours n\'hesitez pas à me contacter ;)','Depart pour le Japon',149,121,2,NULL),
	(6,'2013-10-15 11:58:42','2013-10-17 19:22:22','<p>Quelqu\'un est-il &agrave; jour pour ses d&eacute;clarations de revenus aux imp&ocirc;ts&nbsp;</p>','Déclaration de revenus en France ',144,149,1,NULL),
	(8,'2013-11-14 17:43:48','2013-11-14 17:43:48','<p>Contenu</p>','Hello - A qui adresse-t-on les commentaires sur le site ? ',247,247,1,NULL);

/*!40000 ALTER TABLE `T_ANNOUNCES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_ARTICLES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_ARTICLES`;

CREATE TABLE `T_ARTICLES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CONTENT` longtext,
  `TITLE` varchar(255) NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK12E511C8A7CD013E` (`author_id`),
  KEY `FK12E511C8F3D50C72` (`modifier_id`),
  KEY `FK12E511C8B2118A9E` (`visibility_id`),
  CONSTRAINT `FK12E511C8B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK12E511C8A7CD013E` FOREIGN KEY (`author_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK12E511C8F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_ARTICLES` WRITE;
/*!40000 ALTER TABLE `T_ARTICLES` DISABLE KEYS */;

INSERT INTO `T_ARTICLES` (`id`, `CREATED`, `MODIFIED`, `CONTENT`, `TITLE`, `modifier_id`, `author_id`, `visibility_id`)
VALUES
	(9,'2013-10-21 01:54:39','2013-10-21 01:54:40','<p><strong>Discours pour la c&eacute;r&eacute;monie des couleurs du Mali&nbsp;</strong></p>\r\n<p>&nbsp;</p>\r\n<p>Monsieur le directeur g&eacute;n&eacute;ral de l&rsquo;Ecole Polytechnique, Mesdames et messieurs les officiers et sous-officiers, mesdames et messieurs les cadres civils de l&rsquo;&eacute;cole, chers camarades,</p>\r\n<p>&nbsp;</p>\r\n<p>Permettez-moi tout d&rsquo;abord de vous remercier de m&rsquo;avoir offert cette tribune pour &eacute;voquer la f&ecirc;te nationale de mon pays. Mesdames et Messieurs, le Mali a c&eacute;l&eacute;br&eacute; le 22 Septembre dernier le 53<sup>e</sup> anniversaire de son accession &agrave; la souverainet&eacute; internationale. Cette tribune est donc pour moi l&rsquo;occasion de rendre un vibrant hommage &agrave; toutes ces femmes et &agrave; tous ces hommes qui, par leur d&eacute;vouement et leur sens de l&rsquo;int&eacute;r&ecirc;t collectif, ont ouvert la voie de l&rsquo;ind&eacute;pendance au Mali en 1960. L&rsquo;histoire de cette date est intimement li&eacute;e &agrave; celle d&rsquo;un homme, Feu Modibo Ke&iuml;ta premier pr&eacute;sident du Mali, celui que Jean Lacouture qualifiait de&laquo;statue vivante de l&rsquo;Afrique&nbsp;&raquo;. En effet, Mesdames et Messieurs, &agrave; l&rsquo;initiative de cet homme d&eacute;vou&eacute; &agrave; la cause de l&rsquo;unit&eacute; africaine, une conf&eacute;rence dite des f&eacute;d&eacute;ralistes r&eacute;unissant le soudan fran&ccedil;ais(actuel Mali), le S&eacute;n&eacute;gal, le Dahomey(actuel b&eacute;nin) et la Haute Volta(actuel Burkina Faso) se tient &agrave; Bamako en 1958. Elle d&eacute;bouchera sur la naissance d&rsquo;un &eacute;tat f&eacute;d&eacute;ral, appel&eacute; la f&eacute;d&eacute;ration du Mali, regroupant les 4 nations le 17 janvier 1959 &agrave; Dakar. Mais le Dahomey et la Haute Volta ne tardent pas &agrave; se retirer de la f&eacute;d&eacute;ration. Soudanais et S&eacute;n&eacute;galais r&eacute;clament ensuite et obtiennent l&rsquo;ind&eacute;pendance de la f&eacute;d&eacute;ration du Mali. La proclamation solennelle est faite par L&eacute;opord S&eacute;dar Senghor le 20 juin 1960. Cependant deux mois, plus tard des conflits id&eacute;ologiques auront le dessus sur cette premi&egrave;re tentative d&rsquo;int&eacute;gration africaine et aboutiront &agrave; l&rsquo;&eacute;clatement de la f&eacute;d&eacute;ration du Mali le 20 Ao&ucirc;t 1960. A partir de ce moment, le Soudan Fran&ccedil;ais est oblig&eacute; de faire chemin tout seul et Modibo Ke&iuml;ta proclamera solennellement son ind&eacute;pendance le 22 septembre 1960 sous le nom de la r&eacute;publique du Mali. Le 28 septembre 1960, le Mali est admis avec le soutien de son ancien colonisateur, la France, &agrave; l&rsquo;Organisation des Nations Unies. Voil&agrave; Mesdames et Messieurs l&rsquo;historique de cette date marquant la naissance de la r&eacute;publique du Mali. Convaincu que le salut du continent noir passe n&eacute;cessairement par l&rsquo;unit&eacute;, le jeune &eacute;tat continue son combat pour le panafricanisme et une preuve irr&eacute;futable de cette volont&eacute; est l&rsquo;article 117 de la premi&egrave;re constitution de la r&eacute;publique du Mali, je cite&nbsp;&laquo;&nbsp;La R&eacute;publique du Mali peut conclure avec tout &eacute;tat africain des accords d\'association ou de communaut&eacute; impliquant un abandon total ou partiel de souverainet&eacute; en vue de r&eacute;aliser l&rsquo;unit&eacute; africaine.&nbsp;&raquo;. Le Mali est aujourd&rsquo;hui membre de la communaut&eacute; &eacute;conomique des &eacute;tats de l&rsquo;Afrique de l&rsquo;Ouest (CEDEAO), de l&rsquo;union &eacute;conomique et mon&eacute;taire ouest africaine (UEMOA) et de l&rsquo;union africaine (UA).</p>\r\n<p>Mesdames et Messieurs, une nouvelle page des relations franco-maliennes vient de s&rsquo;ouvrir. En effet nous sommes le 11 janvier 2013&nbsp;; les trois r&eacute;gions du nord du pays sont contr&ocirc;l&eacute;es par des groupes arm&eacute;es et terroristes. Cette occupation se caract&eacute;rise par une application stricte de la charia avec son corollaire de bras coup&eacute;s, de femmes et jeunes flagell&eacute;s en public, de destruction de sites touristiques dont une partie est inscrite au patrimoine mondial de l&rsquo;UNESCO. L&rsquo;existence m&ecirc;me du pays est menac&eacute;e. C&rsquo;est en ce moment que sur demande des autorit&eacute;s maliennes, la France, dans un &eacute;lan de solidarit&eacute; et de d&eacute;fense des droits de l&rsquo;homme, lance l&rsquo;op&eacute;ration &laquo;&nbsp;serval&nbsp;&raquo; permettant ainsi au Mali de recouvrer son int&eacute;grit&eacute; territoriale et de revenir &agrave; une vie constitutionnelle normale par l&rsquo;organisation d&rsquo;&eacute;lections libres et transparentes. J&rsquo;aimerais naturellement rendre un hommage m&eacute;rit&eacute; &agrave; l&rsquo;arm&eacute;e fran&ccedil;aise et &agrave; tous ces officiers et sous-officiers fran&ccedil;ais qui ont pay&eacute; de leur vie, cette lutte pour les droits de l&rsquo;homme et contre l&rsquo;obscurantisme. Je m&rsquo;incline ici devant la m&eacute;moire du Lieutenant Damien Boiteux, du sergent-chef Harold Vormezeele, du caporal C&eacute;dric Charenton, du brigadier-chef Wilfried Pingaud, du caporal Alexandre Van Dooren, du caporal-chef St&eacute;phane Duval et du brigadier-chef Marc Martin-Vallet. Je souhaite aussi un prompt r&eacute;tablissement au sous-officier gri&egrave;vement bless&eacute; il y a &agrave; peine une semaine.</p>\r\n<p>Je voudrais remercier la France pour cette marque de solidarit&eacute;, dont la tribune qui m&rsquo;est offerte aujourd&rsquo;hui en est &eacute;galement une illustration. Elle confirme une fois de plus son statut de pays des droits de l&rsquo;homme. Cet esprit de solidarit&eacute; et de d&eacute;fense des droits de l&rsquo;homme est un d&eacute;nominateur commun des peuples du Mali et de la France&nbsp;; en effet les maliens aussi accordent une importance capitale &agrave; ces deux concepts, en atteste la proclamation depuis le 13<sup>e</sup> si&egrave;cle des chartes de Kouroukan Fouga inscrites depuis 2009 au patrimoine immat&eacute;rielle de l&rsquo;humanit&eacute; et consid&eacute;r&eacute;es comme l&rsquo;une des plus anciennes r&eacute;f&eacute;rences concernant les droits de l&rsquo;homme. Je remercie aussi le Tchad et tous les pays africains dont les troupes constituent aujourd&rsquo;hui la mission multidimensionnelle int&eacute;gr&eacute;e des nations unies pour la stabilit&eacute; au Mali (MINUSMA).</p>\r\n<p>Pour conclure, mesdames et messieurs je voudrais dire &agrave; chacun de vous un grand merci pour votre pr&eacute;sence ici ce matin. Un homme du pays de la diatiguiya (hospitalit&eacute; en bambara) ne peut rester indiff&eacute;rent &agrave; ce sens de l&rsquo;hospitalit&eacute; dont vous faites preuve.</p>\r\n<p>Je vous remercie.</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Palaiseau, le 22 Octobre 2013</p>','cérémonie des couleurs',153,153,NULL),
	(11,'2013-11-05 19:14:24','2013-11-05 19:14:24','<p>Bonjour &agrave; tous,</p>\r\n<p>Je voudrais faire un petit sondage pour savoir ceux qui sont interess&eacute;s par la creation d\'un v&ecirc;tement de la CPD, l\'id&eacute;e c\'est que les nouveaux re&ccedil;oivent un t-shirt leur premi&egrave;re ann&eacute;e le jour de la ceremonie d\'integration et ensuite l\'ann&eacute;e suivante comme ils seront integr&eacute;s ils recevront le sweat comme tout les membres de la CPD, donc la premiere ann&eacute;e on a le tshirt puis &agrave; partir de la deuxieme ann&eacute;e on a le sweat comme les anciens, le sweat coutera &agrave; peu pres 25-30euros et le tshirt sera au frais de la CPD, si vous pensez que c\'est une bonne id&eacute;e faite oui en commentaire, sachant que plus il y a de personne interess&eacute; plus le prix sera moins cher.</p>\r\n<p>Merci kadiatou toure promo 2008</p>','sweat et t-shirt pour la CPD',223,223,NULL),
	(12,'2013-11-05 20:58:36','2013-11-05 20:58:36','<p>Bonjour &agrave; tout les biologistes et chimistes voici la liste des &eacute;coles d\'ingenieurs auxquelles vous pouvez postuler! Il y a un peu de tout chimie et biologie &agrave; vous de faire le tri dans les deux ans qui viennent pour faire votre choix! Bon courage!!!</p>\r\n<p>-ESIL MARSEILLE</p>\r\n<p>-ISAB, UTC AMIENS</p>\r\n<p>-ENSGTI, ESTBB, ISTAB BORDEAUX</p>\r\n<p>-ENITAC, ENSCCF CLERMONT-FERRAND</p>\r\n<p>-ISBS PARIS</p>\r\n<p>-ENSBANA, ENESAD DIJON</p>\r\n<p>-PHELMA GRENOBLE</p>\r\n<p>-ENSCL, HEI,ISAL,ITIAPE LILLE</p>\r\n<p>-ENSIL LIMOGES</p>\r\n<p>-CPE, INSA, ISARA LYON</p>\r\n<p>-ENSCM, SUPAGRO, ENSAM, ENSIA, POLYTECH MONTPELIER</p>\r\n<p>-ENSAIA,ENSIC NANCY-METZ</p>\r\n<p>-AGROCAMPUS OUEST, ENITIAA, ESA NANTES</p>\r\n<p>-POLYTECH NICE</p>\r\n<p>-AGROPARITECH, ENSCP, ESPCI, INA PG PARIS</p>\r\n<p>-ENSCR, ENSAR, ESITPA RENNES</p>\r\n<p>-ESITPA ROUEN</p>\r\n<p>-ESBS STRASBOURG</p>\r\n<p>-ENSAT,ESAP, INSA TOULOUSE</p>\r\n<p>-EBI, ENSEA, ISTOM VERSAILLES</p>\r\n<p>-ISAB AMIENS</p>\r\n<p>-ENITAB BORDEAUX</p>\r\n<p>&nbsp;</p>','Les Ecoles d’ingénieurs pour les biologistes',223,223,NULL),
	(13,'2013-11-14 18:38:32','2013-11-14 18:38:32','<pre>Nous avons mis en place le sondage &laquo; LE SWEAT ET TSHIRT DE LA CPD &raquo; sur le site Doodle. Le lien du sondage est le suivant:<br /><br /><a href=\"http://www.doodle.com/e8sxb3swg3w2pvgw\" target=\"_blank\">http://www.doodle.com/e8sxb3swg3w2pvgw</a></pre>','LIEN DOODLE POUR DIRE OUI AU SWEAT ET TSHIRT CPD',223,223,NULL),
	(14,'2013-11-22 07:09:17','2013-11-23 06:26:14','<p>Bonjour &agrave; tous, je suis &agrave; la recherche d\'id&eacute;es novatrices de jeunes &eacute;conomistes ou non sur les conditions (&agrave; statisfaire) pour une meilleure relance de la dynamique de d&eacute;veloppement (&eacute;conomique et social) du Mali. L\'objectif est de r&eacute;aliser un petit article afin de le publier dans un quotidien malien (ou mieux &agrave; des personnes ressources) &nbsp;au nom de la CPD.&nbsp;</p>\r\n<p>Vous pourriez formuler vos id&eacute;es en 8 lignes maximum si possible. Un effort de synth&egrave;se est de mise.</p>\r\n<p>Je vous remercie par avance.&nbsp;</p>\r\n<p>1-</p>\r\n<p>&nbsp;</p>\r\n<p>2-</p>\r\n<p>&nbsp;</p>\r\n<p>3-</p>\r\n<p>&nbsp;</p>\r\n<p>...&nbsp;</p>','Les trente conditions pour relancer la dynamique de développement économique et social du Mali',265,265,NULL);

/*!40000 ALTER TABLE `T_ARTICLES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_CANDIDATURES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_CANDIDATURES`;

CREATE TABLE `T_CANDIDATURES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `PRESENTATION` longtext,
  `modifier_id` bigint(20) DEFAULT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  `election_id` bigint(20) DEFAULT NULL,
  `poste_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK619350384C982932` (`election_id`),
  KEY `FK61935038F3D50C72` (`modifier_id`),
  KEY `FK6193503887E06A46` (`candidate_id`),
  KEY `FK61935038B9253C82` (`poste_id`),
  KEY `FK61935038B2118A9E` (`visibility_id`),
  CONSTRAINT `FK61935038B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK619350384C982932` FOREIGN KEY (`election_id`) REFERENCES `T_ELECTIONS` (`id`),
  CONSTRAINT `FK6193503887E06A46` FOREIGN KEY (`candidate_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK61935038B9253C82` FOREIGN KEY (`poste_id`) REFERENCES `T_POSTES` (`id`),
  CONSTRAINT `FK61935038F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_CANDIDATURES` WRITE;
/*!40000 ALTER TABLE `T_CANDIDATURES` DISABLE KEYS */;

INSERT INTO `T_CANDIDATURES` (`id`, `CREATED`, `MODIFIED`, `PRESENTATION`, `modifier_id`, `candidate_id`, `election_id`, `poste_id`, `visibility_id`)
VALUES
	(1,'2013-06-29 02:41:15',NULL,NULL,NULL,144,2,8,NULL),
	(2,'2013-06-29 02:41:15',NULL,NULL,NULL,121,2,8,NULL),
	(3,'2013-06-29 02:41:15',NULL,NULL,NULL,123,2,8,NULL),
	(4,'2013-09-13 23:47:32','2013-09-13 23:47:33','yesssss I ammmmmm!!!',121,121,3,1,NULL),
	(5,'2013-09-13 23:47:46','2013-09-13 23:47:46','yes i did!!!',121,121,3,2,NULL),
	(6,'2013-09-13 23:48:07','2013-09-13 23:48:07','I had a dream!!!!',121,121,3,4,NULL),
	(7,'2013-09-13 23:49:58','2013-09-13 23:49:58','Yes I AM!!!!!!!',121,121,8,8,NULL),
	(8,'2013-09-13 23:50:12','2013-09-13 23:50:12','Yesssss I Willllll!!!',121,121,8,9,NULL),
	(9,'2013-09-13 23:51:50','2013-09-13 23:51:50','Je suis motivé.\r\nVotez pour moi car je suis le meilleur.',144,144,8,8,NULL),
	(10,'2013-09-13 23:51:51','2013-09-13 23:51:51','je suis le meilleur\r\n',123,123,8,8,NULL),
	(11,'2013-09-13 23:52:16','2013-09-13 23:52:16','Votez pour moi ou vous mourrez',123,123,8,9,NULL),
	(12,'2013-09-13 23:55:23','2013-09-13 23:55:23','Vous voterez pour moi ou vous mourez.',144,144,9,2,NULL),
	(13,'2013-09-13 23:57:03','2013-09-13 23:57:03','YOHOOOOO',121,121,9,1,NULL),
	(14,'2013-09-13 23:57:18','2013-09-13 23:57:18','yiipiiii',121,121,9,7,NULL),
	(15,'2013-09-13 23:57:28','2013-09-13 23:57:28','yiiiipiiiiii',121,121,9,2,NULL),
	(16,'2013-09-13 23:58:48','2013-09-13 23:58:48','yammmmaaaa!!!!',121,121,9,4,NULL),
	(18,'2013-09-17 22:42:21','2013-09-17 22:42:22','Je dépose ma candidature pour le poste vice président car j\'aime bien être un VP qui rime avec VIP.',144,144,11,9,NULL);

/*!40000 ALTER TABLE `T_CANDIDATURES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_COMMENTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_COMMENTS`;

CREATE TABLE `T_COMMENTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CONTENT` longtext NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK383C1D7FA7CD013E` (`author_id`),
  KEY `FK383C1D7FF3D50C72` (`modifier_id`),
  KEY `FK383C1D7FB2118A9E` (`visibility_id`),
  CONSTRAINT `FK383C1D7FB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK383C1D7FA7CD013E` FOREIGN KEY (`author_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK383C1D7FF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_COMMENTS` WRITE;
/*!40000 ALTER TABLE `T_COMMENTS` DISABLE KEYS */;

INSERT INTO `T_COMMENTS` (`id`, `CREATED`, `MODIFIED`, `CONTENT`, `modifier_id`, `author_id`, `visibility_id`)
VALUES
	(4,'2013-09-18 10:36:19','2013-09-18 10:36:19','Franchement venez faire un torus c\'est honnetement genial!!!!',121,121,NULL),
	(5,'2013-09-19 21:13:18','2013-09-19 21:13:18','Haha! Ok on a compris on passera te voir un de ces 4!',144,144,NULL),
	(9,'2013-10-14 18:01:21','2013-10-14 18:01:21','Eh Allah Elay comment vas-tu? alors raconte c\'est comment par rapport à la France?',149,149,NULL),
	(12,'2013-10-17 19:22:21','2013-10-17 19:22:21','Oui moi je suis à jour!\r\nIl y a t il une information particulière que tu aimerais avoir ?',144,144,NULL),
	(18,'2013-10-21 01:58:11','2013-10-21 01:58:11','Un orateur doté d\'un talent et d\'un charisme inégalable. ',153,153,NULL),
	(22,'2013-10-22 14:41:56','2013-10-22 14:41:56','Je confirme ;)\r\n',123,123,NULL),
	(25,'2013-10-22 15:17:03','2013-10-22 15:17:03','Je suis d\'accoard avec toi Nanakassé pour cette fois',144,144,NULL),
	(26,'2013-10-24 13:28:47','2013-10-24 13:28:47','Par contre il faudrait qu\'on regarde la disposition du texte pour les articles long!! La maniere donc c\'est mis, ca donne pas envi de lire!! :D',147,147,NULL),
	(27,'2013-10-24 22:15:31','2013-10-24 22:15:31','Oui tu as raison Kady il faut qu\'on revois ça.\r\nMais qu\'est ce que tu proposes ?',144,144,NULL),
	(28,'2013-10-26 10:06:17','2013-10-26 10:06:17','ehhh moi aussi je suis plus ou moins a jour lol\r\n',121,121,NULL),
	(29,'2013-11-05 10:36:42','2013-11-05 10:36:42','Ahhhh Hawa!!!! Franchement c\'est trop bien, mais il y a des hauts et des bas :D\r\n',121,121,NULL),
	(30,'2013-11-05 21:00:18','2013-11-05 21:00:18','moi aussi je suis à jour, c\'est pas trop tot hein :p\r\n',223,223,NULL),
	(31,'2013-11-05 21:19:40','2013-11-05 21:19:40','Hi Kadi, c\'est une super bonne idée, mais je crois que ça doit pas être au frais de la CPD parce qu\'il n\'a pas de sous. Il vaut mieux peut etre mettre un systeme de paiement Paypal ou pas pour que chacun puisse régler ce qu\'il a commandé.\r\nJe pense qu\'il faut aussi l\'opportunité de personnifier son sweat, genre mettre son nom ou un texte par exemple etc...\r\nSinon j\'adhère completement',123,123,NULL),
	(32,'2013-11-05 21:24:23','2013-11-05 21:24:23','c\'est tres cher si on personnalise avec son nom, si on garde le logo pour tous ca revient super moins cher pas plus de 30 euros par personne, mais il suffit de changer la couleur d\'un seul suite pour sortir du prix en gros!!!\r\n',223,223,NULL),
	(33,'2013-11-05 21:25:43','2013-11-05 21:25:43','C\'est une bonne idée!',144,144,NULL),
	(34,'2013-11-12 17:06:12','2013-11-12 17:06:12','Est qeu tu as des suggestions particuliers a faire par rapport a toutes ces ecoles??',121,121,NULL),
	(35,'2013-11-12 17:08:52','2013-11-12 17:08:52','Je suis aussi pour cette idée!!! Apres c\'est vrai que la personnalisation peut revenir chers!! Sinon on pourrait faire genre un pull comme dans les ecoles! Avec le logo et tout!! Ca serait super style ca!!!',121,121,NULL),
	(36,'2013-11-12 20:12:31','2013-11-12 20:12:31','oui je pense comme Elay, on peut mettre le logo de la CPD et préciser la promo, le pull sur la photo que kady a mis sur facebook est très jolie, je proposais je mettre une couleur différente dans la capuche, apres il faut voir si ça va couter bcp plus cher ou pas \r\n',149,149,NULL),
	(37,'2013-11-12 20:31:39','2013-11-12 20:31:39','il faut que kelkun s\'oppose du logo en vectoriel, un autre d\'un echantillon que l\'on montrera au gens sur facebook et sur le site ( logiciel keynote sur mac par ex) et un autre pour la promo et que tout le monde soit au courant puis un doodle!!!! qui fait quoi???\r\n',223,223,NULL),
	(38,'2013-11-12 20:39:37','2013-11-12 20:39:37','@kadiatou par s\'oppose tu veux dire s\'occupe ?\r\nSinon moi je suis perdu!',144,144,NULL),
	(39,'2013-11-12 20:59:28','2013-11-12 20:59:28','oui @cheick desoler!\r\n',223,223,NULL),
	(40,'2013-11-12 22:21:49','2013-11-12 22:21:49','Ah j\'ai enfin trouvé où se passe la discussion! Moi aussi je suis pour 100%\r\n',201,201,NULL),
	(41,'2013-11-12 23:05:03','2013-11-12 23:05:03','ça fait tourner la tête tout ça!\r\n',151,151,NULL),
	(42,'2013-11-12 23:13:46','2013-11-12 23:13:46','Je pense que c\'est une très bonne idée mais ça sera très difficile de mobiliser les fonds nécessaires. déjà c\'était très compliqué avec les cotisations mensuelles de 5 $ donc imagine si on demandait aux gens de débourser 35-40 . Bon disons ça sera l\'occasion de checker l\'amour des uns et des autres pour la cellule . ',238,238,NULL),
	(43,'2013-11-12 23:28:58','2013-11-12 23:28:58','Ibrahim ça sera un peu different car dans ce cas les gens savent dans quoi part leur argent du coup on va cotiser sans problème!\r\nLa CPD c\'est pour nous et on va la faire vivre!\r\nLa CPD c\'est pas PD! :)',144,144,NULL),
	(44,'2013-11-12 23:39:57','2013-11-12 23:39:57','Merci beaucoup pour ton aide Kadi !',236,236,NULL),
	(45,'2013-11-13 11:03:12','2013-11-13 11:03:12','Hello la team,\r\n\r\nJe suis in pour les sweats.\r\nS\'il y a un max de personnes, la CPD pourrait même demander au fournisseur d\'effectuer un geste commercial de sorte à gagner environ 5€/sweat.',241,241,NULL),
	(46,'2013-11-13 11:10:44','2013-11-13 11:10:44','ok, comme je l\'ai dit plus haut il faut trois personnes motivé, moi je connais un fourniseur donc je pux m\'occuper de cette partie commende prix et tout, en ce qu\'il en ai de la monter du prototype et de l\'image vectoriel je my connais pas, donc si deux peronnes peuvent se devourer thanks',223,223,NULL),
	(47,'2013-11-13 18:30:27','2013-11-13 18:30:27','Good Idea ;) !\r\n',236,236,NULL),
	(48,'2013-11-13 20:10:29','2013-11-13 20:10:29','desoler les gens mais apres une petite recherche le fait juste de mettre des promo donc chiffres differents comptent grave cher, car le fournisseur est obliger de faire les pulls par lots, j\'imagine mm pas si les gens veulent des messages personnalisé, ce que veulent les fournisseur c\'est un mm imprimer qu\'ils font a la chaine d\'un coup, du coup là il peuvent nous faire un bon tarif! ',223,223,NULL),
	(49,'2013-11-13 20:23:52','2013-11-13 20:23:52','Je crois qu\'on peut s\'en passer de la promo ou tout ce qui relève de la personnalisation. ',243,243,NULL),
	(50,'2013-11-13 20:30:41','2013-11-13 20:30:41','Autre chose est possible afin de ne pas pénaliser certaines personnes qui ne veulent pas payer le frais de personnalisation.\r\nOn peut donner le prix sans personnalisation et donc ceux veulent personnaliser paieront le frais.',144,144,NULL),
	(51,'2013-11-14 15:03:13','2013-11-14 15:03:13','Sinon les fournisseurs il y en a pleins, mais avant de se fixer sur 1 faudrait voir quel genre de pulls se fournisseurs propose :D \r\n',121,121,NULL),
	(52,'2013-11-14 15:03:42','2013-11-14 15:03:42','Sinon pour les Logos je peux m\'en charger!! ',121,121,NULL),
	(53,'2013-11-15 02:45:09','2013-11-15 02:45:09','Tu cliques sur le petit bouton feedback en bas a gauche :D',121,121,NULL),
	(54,'2013-11-14 17:48:35','2013-11-14 17:48:35','Plutôt réactif ! Merci. ',247,247,NULL),
	(55,'2013-11-14 17:50:38','2013-11-14 17:50:38','Rien de majeur en fait ! A l\'inscription j\'ai constaté que j\'aurai pu mettre une année de promotion <0 ! Je veux bien être traité de nul...mais je ne suis quand même pas négatif comme mec. ++',247,247,NULL),
	(56,'2013-11-14 18:55:54','2013-11-14 18:55:54','Moi non!!!! Lol. je voulais juste savoir quand es ce que l\'on devrais le faire et où? Merci déjas de vos reponses.\r\n',248,248,NULL),
	(57,'2013-11-14 21:12:24','2013-11-14 21:12:24','Merci Kady!',144,144,NULL),
	(58,'2013-11-14 21:55:02','2013-11-14 21:55:02','Merci pour la remarque c\'est vrai que ce n\'est pas géré ça!\r\nOn tiendra compte de ça!',144,144,NULL),
	(59,'2013-11-14 22:06:35','2013-11-14 22:06:35','Cool Kady, mais ton doodle ne permet pas de donner son opinion sur les options du genre ( mettre un pseudo, ta promo , citation perso etc..) quitte à faire deux commandes séparées, ceux qui veulent un sweat simple et ce qui veut le personnaliser. ça revient pas super cher par rapport au sweat tout court.\r\nje me souviens à l\'x, on en avait fait et ça nous ai est revenu à 35 euros',123,123,NULL),
	(60,'2013-11-14 22:09:45','2013-11-14 22:09:45','ok nanaclass du coup on va faire une estimation des gens interessé d\'abord, puis on fera un deuxieme doodle pour le choix, la personnalisation la couleur ect, ici on veut juste une estimation pour lancer le processus',223,223,NULL),
	(61,'2013-11-14 22:11:49','2013-11-14 22:11:49',';) oki\r\n',123,123,NULL),
	(62,'2013-11-15 17:04:15','2013-11-15 17:04:16','Et aussi il faut pas se precipiter non plus, je pense qu\'on peut regarder ce qu\'on a comme possibilites!! Les fournisseurs il y a en a des dizaines, on ne doit pas succomber a la pression d\'un seul!!!',121,121,NULL),
	(63,'2013-11-15 12:38:37','2013-11-15 12:38:37','Lol, Elay je n\'ai jamais réçu ton email pour les feedbacks !!!  m\'aurais tu oublié ?  Dis donc c\'est pas mal ici ( site de la CPD) du tout !!!  :D ',256,256,NULL),
	(64,'2013-11-15 15:04:46','2013-11-15 15:04:46','oui bien sur toujours qu\'il faut une estimation du nombre de personne pour discuter un prix avec n\'importe quel fournisseurs',223,223,NULL),
	(65,'2013-11-15 21:00:44','2013-11-15 21:00:44','Euh Kadi donc il faut que les nouveaux attendent d\'etre anciens pour avoir le sweat meme s\'ils sont prets à le payer ?\r\n',236,236,NULL),
	(66,'2013-11-16 03:15:05','2013-11-16 03:15:05','Ahh Tall, bonne remarque... on peut aussi faire différents types de sweat (des couleurs # par ex.) : pour les nouveaux, pour les anciens encore étudiants, pour les dinosaure... !!!',92,92,NULL),
	(67,'2013-11-16 10:38:19','2013-11-16 10:38:19','oui c\'est possible au debut j\'avais penser tshirt pour les nouveaux avant leur integration, l\'integration pourra se faire au minicode en juin, on les batise au minicode et il recoivent leur sweat et deviennent anciens,  mais bon c\'est a reflechir',223,223,NULL),
	(68,'2013-11-16 10:38:54','2013-11-16 10:38:54','l\'idee de baya est ok aussi!!\r\n',223,223,NULL),
	(69,'2013-11-16 14:30:20','2013-11-16 14:30:20','Vous n\'avez pas l\'option \"J\'aime\" ou \" J\'adore\" ?  En tout cas moi j\'aime lol',258,258,NULL),
	(70,'2013-11-26 13:46:02','2013-11-26 13:46:02','C\'est un sujet très complexe (on s\'en doutait!!) sur lequel on pourrait écrire une voire plusieurs thèses sans trouver de solution. En fait, je pense qu\'il n\'y a pas de solution unique et donc plusieurs stratégies de développement peuvent aboutir à des résultats positifs. La clef, à mon humble avis, c\'est d\'avoir une stratégie de développement A LONG TERME et surtout COHERENTE. Cela peut sembler vague comme recommandation, mais c\'est un exercice très complexe de formuler une stratégie de développement coehérente et surtout de s\'y tenir car le dévéloppement est un processus qui se déroule sur des dizaines d\'années.\r\n\r\nAutant dire qu\'à mon avis c\'est avant tout une question de gouvernance: il faut un gouvernement visionnaire qui se fixe des objectifs et qui met en place des politiques cohérentes qui guident les autres acteurs vers cet objectif.  Un exemple concret: si la stratégie privilégiée est le développement agricole, il faut que l\'offre de formation et d\'éducation, le système d\'incitation fiscale, les infrastructures, etc soient adaptés en conséquence. \r\n\r\nDans l\'immédiat, je pense que les mesures les plus urgentes pour relancer l\'activité économique sont la sécurité et la stabilité (qui va investir dans une activité si on ne peut pas se projeter sur quelques années avec toutes les tensions politiques actuelles?).\r\n',201,201,NULL),
	(71,'2013-11-26 21:47:52','2013-11-26 21:47:52','Pappapaapa :) ',249,249,NULL),
	(72,'2013-11-27 14:44:41','2013-11-27 14:44:41','Je rejoins Sekou essentiellement sur l\'idée de long terme. Je pense que pour pouvoir relancer et dynamiser le développement il faut orienter l’éducation vers des domaines porteurs. Je pense qu\'il faut qu\'on essaye de trouver le moyen d\'orienter les gens qui veulent travailler tôt vers des formations rapides (Menuiserie, métallurgie ect...) qui aboutissent à des bacs spécialisés. \r\nEnsuite (cela ne relève que de mon opinion personnel) il faudrait favoriser entrepreneuriat et mettre en places de structures d’accueil et des aides pour les entrepreneurs ayant des projets qui tiennent la route. ',121,121,NULL);

/*!40000 ALTER TABLE `T_COMMENTS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_CVS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_CVS`;

CREATE TABLE `T_CVS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `RESUME` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `adresse_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4CBEBD56239C28` (`adresse_id`),
  KEY `FK4CBEBD5F3D50C72` (`modifier_id`),
  KEY `FK4CBEBD5B2118A9E` (`visibility_id`),
  CONSTRAINT `FK4CBEBD5B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK4CBEBD56239C28` FOREIGN KEY (`adresse_id`) REFERENCES `T_ADRESS` (`id`),
  CONSTRAINT `FK4CBEBD5F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_CVS` WRITE;
/*!40000 ALTER TABLE `T_CVS` DISABLE KEYS */;

INSERT INTO `T_CVS` (`id`, `CREATED`, `MODIFIED`, `RESUME`, `TITLE`, `modifier_id`, `adresse_id`, `visibility_id`)
VALUES
	(57,'2013-06-29 02:41:13',NULL,NULL,NULL,NULL,NULL,NULL),
	(82,'2013-06-29 02:41:13',NULL,NULL,NULL,NULL,NULL,NULL),
	(83,'2013-06-29 02:41:13',NULL,NULL,NULL,NULL,58,NULL),
	(92,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,NULL,NULL),
	(95,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,NULL,NULL),
	(121,'2013-06-29 02:41:14','2013-11-15 09:22:15','Etudiant En formation Ingénieur Centrale Paris','Etudiant En formation Ingénieur Centrale Paris',121,73,NULL),
	(123,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,67,NULL),
	(144,'2013-06-29 02:41:14','2013-10-19 00:24:36','Je suis actuellement à l\'école polytechnique.','Recherche d\'un stage',144,57,NULL),
	(147,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,NULL,NULL),
	(149,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,NULL,NULL),
	(150,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,NULL,NULL),
	(151,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,NULL,NULL),
	(153,'2013-06-29 02:41:14',NULL,NULL,NULL,NULL,62,NULL),
	(193,'2013-09-12 18:14:04',NULL,NULL,NULL,NULL,NULL,NULL),
	(198,'2013-09-13 15:54:38',NULL,NULL,NULL,NULL,NULL,NULL),
	(201,'2013-09-18 06:22:58',NULL,NULL,NULL,NULL,NULL,NULL),
	(202,'2013-09-18 07:44:32',NULL,NULL,NULL,NULL,NULL,NULL),
	(203,'2013-09-18 09:59:15',NULL,NULL,NULL,NULL,NULL,NULL),
	(204,'2013-09-18 12:03:20',NULL,NULL,NULL,NULL,NULL,NULL),
	(205,'2013-09-18 16:50:24',NULL,NULL,NULL,NULL,NULL,NULL),
	(206,'2013-09-19 20:08:34',NULL,NULL,NULL,NULL,NULL,NULL),
	(207,'2013-09-24 09:09:54',NULL,NULL,NULL,NULL,NULL,NULL),
	(208,'2013-09-26 17:38:59',NULL,NULL,NULL,NULL,NULL,NULL),
	(210,'2013-10-14 21:10:23',NULL,NULL,NULL,NULL,NULL,NULL),
	(213,'2013-10-20 21:17:24',NULL,NULL,NULL,NULL,65,NULL),
	(217,'2013-10-27 17:14:49','2013-10-27 17:14:49',NULL,NULL,NULL,NULL,NULL),
	(218,'2013-10-27 19:42:25','2013-10-27 19:42:25',NULL,NULL,NULL,NULL,NULL),
	(219,'2013-10-27 19:52:03','2013-10-27 19:52:03',NULL,NULL,NULL,NULL,NULL),
	(221,'2013-10-28 13:08:01','2013-10-28 13:08:01',NULL,NULL,NULL,66,NULL),
	(222,'2013-10-28 13:51:23','2013-10-28 13:51:23',NULL,NULL,NULL,63,NULL),
	(223,'2013-10-29 17:14:09','2013-10-29 17:14:09',NULL,NULL,NULL,NULL,NULL),
	(224,'2013-10-30 19:41:07','2013-10-30 19:41:07',NULL,NULL,NULL,NULL,NULL),
	(225,'2013-11-05 10:40:42','2013-11-05 10:40:42',NULL,NULL,NULL,NULL,NULL),
	(230,'2013-11-05 18:50:33','2013-11-05 18:50:33',NULL,NULL,NULL,NULL,NULL),
	(231,'2013-11-05 18:57:11','2013-11-05 18:57:11',NULL,NULL,NULL,NULL,NULL),
	(232,'2013-11-05 19:14:32','2013-11-05 19:14:32',NULL,NULL,NULL,NULL,NULL),
	(233,'2013-11-05 22:29:45','2013-11-05 22:29:45',NULL,NULL,NULL,70,NULL),
	(234,'2013-11-12 19:06:13','2013-11-12 19:47:50',NULL,NULL,234,68,NULL),
	(235,'2013-11-12 21:46:40','2013-11-12 21:46:40',NULL,NULL,NULL,NULL,NULL),
	(236,'2013-11-12 22:15:29','2013-11-12 22:15:29',NULL,NULL,NULL,69,NULL),
	(237,'2013-11-12 22:34:02','2013-11-12 22:34:02',NULL,NULL,NULL,NULL,NULL),
	(238,'2013-11-12 23:08:46','2013-11-12 23:08:46',NULL,NULL,NULL,NULL,NULL),
	(239,'2013-11-12 23:11:07','2013-11-12 23:11:07',NULL,NULL,NULL,NULL,NULL),
	(240,'2013-11-13 11:20:49','2013-11-13 11:20:49',NULL,NULL,NULL,NULL,NULL),
	(241,'2013-11-13 10:36:14','2013-11-13 10:36:14',NULL,NULL,NULL,NULL,NULL),
	(242,'2013-11-13 12:10:50','2013-11-13 12:10:50',NULL,NULL,NULL,NULL,NULL),
	(243,'2013-11-13 20:15:39','2013-11-13 20:15:39',NULL,NULL,NULL,NULL,NULL),
	(244,'2013-11-13 21:23:34','2013-11-13 21:23:34',NULL,NULL,NULL,NULL,NULL),
	(245,'2013-11-14 07:59:33','2013-11-14 07:59:33',NULL,NULL,NULL,NULL,NULL),
	(246,'2013-11-14 14:20:13','2013-11-14 14:20:13',NULL,NULL,NULL,NULL,NULL),
	(247,'2013-11-14 17:40:34','2013-11-14 17:40:34',NULL,NULL,NULL,NULL,NULL),
	(248,'2013-11-14 18:02:31','2013-11-14 18:02:31',NULL,NULL,NULL,NULL,NULL),
	(249,'2013-11-14 19:03:53','2013-11-14 19:15:42','A Valence j\'ai fait 2 ans en Maths Info, après je suis allée à Grenoble en L3 Maths A et ensuite j\'ai passé les concours de Centrale Paris. Now je fais mon stage de fin d\'études en Actuariat chez AXA.','Ingénieure généraliste avec une spécialisations en Maths Apps et Info/Actuariat',249,71,NULL),
	(250,'2013-11-14 19:21:36','2013-11-14 19:21:36',NULL,NULL,NULL,NULL,NULL),
	(251,'2013-11-14 19:59:24','2013-11-17 12:12:28','Élève ingénieur à l\'E.C.E PARIS','.',251,74,NULL),
	(252,'2013-11-14 20:38:44','2013-11-14 20:38:44',NULL,NULL,NULL,NULL,NULL),
	(253,'2013-11-14 20:42:21','2013-11-14 20:42:21',NULL,NULL,NULL,NULL,NULL),
	(254,'2013-11-14 22:37:57','2013-11-14 22:37:57',NULL,NULL,NULL,NULL,NULL),
	(255,'2013-11-15 04:06:17','2013-11-15 04:06:17',NULL,NULL,NULL,72,NULL),
	(256,'2013-11-15 12:29:21','2013-11-15 12:29:21',NULL,NULL,NULL,NULL,NULL),
	(257,'2013-11-15 15:17:58','2013-11-15 15:17:58',NULL,NULL,NULL,NULL,NULL),
	(258,'2013-11-16 14:25:26','2013-11-16 14:25:26',NULL,NULL,NULL,NULL,NULL),
	(259,'2013-11-17 01:14:32','2013-11-17 01:14:32',NULL,NULL,NULL,NULL,NULL),
	(260,'2013-11-17 15:10:43','2013-11-17 15:10:43',NULL,NULL,NULL,NULL,NULL),
	(261,'2013-11-18 11:45:08','2013-11-18 11:45:08',NULL,NULL,NULL,NULL,NULL),
	(262,'2013-11-18 18:03:30','2013-11-18 18:03:30',NULL,NULL,NULL,NULL,NULL),
	(263,'2013-11-18 20:19:51','2013-11-18 20:19:51',NULL,NULL,NULL,NULL,NULL),
	(264,'2013-11-18 22:38:13','2013-11-18 22:38:13',NULL,NULL,NULL,NULL,NULL),
	(265,'2013-11-18 22:54:30','2013-11-18 22:54:30',NULL,NULL,NULL,NULL,NULL),
	(266,'2013-11-19 17:22:40','2013-11-19 17:22:40',NULL,NULL,NULL,NULL,NULL),
	(267,'2013-11-21 00:19:05','2013-11-21 00:19:05',NULL,NULL,NULL,75,NULL),
	(268,'2013-11-26 23:54:50','2013-11-26 23:54:50',NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `T_CVS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_DENDRITES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_DENDRITES`;

CREATE TABLE `T_DENDRITES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `GENERAL` bit(1) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`CODE`),
  KEY `FK7AFFC8A9F3D50C72` (`modifier_id`),
  KEY `FK7AFFC8A95F0BDBD6` (`photo_id`),
  KEY `FK7AFFC8A9B2118A9E` (`visibility_id`),
  KEY `DENDRITE_EMAIL` (`EMAIL`),
  CONSTRAINT `FK7AFFC8A95F0BDBD6` FOREIGN KEY (`photo_id`) REFERENCES `T_PHOTOS` (`id`),
  CONSTRAINT `FK7AFFC8A9B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK7AFFC8A9F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_DENDRITES` WRITE;
/*!40000 ALTER TABLE `T_DENDRITES` DISABLE KEYS */;

INSERT INTO `T_DENDRITES` (`id`, `CREATED`, `MODIFIED`, `CODE`, `DESCRIPTION`, `GENERAL`, `NAME`, `modifier_id`, `photo_id`, `visibility_id`, `EMAIL`)
VALUES
	(1,'2013-06-29 02:41:11','2013-10-12 23:25:38','dendrite_bureau','La dendrite de CPD est la dendrite générale.',00000001,'CPD',144,NULL,NULL,'cpd@cpd-mali.com'),
	(2,'2013-06-29 02:41:11','2013-10-19 00:15:36','dendrite_paris','La dendrite de Paris est la dendrite qui est à Paris.',00000000,'Paris',144,9,NULL,'paris@cpd-mali.com'),
	(3,'2013-06-29 02:41:11','2013-10-19 00:16:39','dendrite_grenoble','La dendrite de Grenoble est la dendrite qui est à Grenoble.',00000000,'Grenoble',144,10,NULL,'grenoble@cpd-mali.com'),
	(4,'2013-06-29 02:41:11','2013-10-19 00:18:09','dendrite_valence','La dendrite de Valence est la dendrite qui est à Valence.',00000000,'Valence',144,11,NULL,'valence@cpd-mali.com'),
	(5,'2013-06-29 02:41:11','2013-10-19 00:19:24','dendrite_angers','La dendrite de Angers est la dendrite qui est à Angers.',00000000,'Angers',144,12,NULL,'angers@cpd-mali.com'),
	(6,'2013-06-29 02:41:11','2013-10-19 00:20:08','dendrite_lyon','La dendrite de Lyon est la dendrite qui est à Lyon.',00000000,'Lyon',144,13,NULL,'lyon@cpd-mali.com'),
	(7,'2013-06-29 02:41:11','2013-10-19 00:21:01','dendrite_mali','La dendrite du Mali est la dendrite qui est au Mali.',00000000,'Mali',144,14,NULL,'mali@cpd-mali.com');

/*!40000 ALTER TABLE `T_DENDRITES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_ELECTIONS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_ELECTIONS`;

CREATE TABLE `T_ELECTIONS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `END_CANDIDATURE` datetime NOT NULL,
  `FIN_VOTE` datetime NOT NULL,
  `START_CANDIDATURE` datetime NOT NULL,
  `START_VOTE` datetime NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `dendrite_id` bigint(20) DEFAULT NULL,
  `organiser_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`CODE`),
  KEY `FK8BBA12FEBA3847E` (`dendrite_id`),
  KEY `FK8BBA12F8A6370C1` (`organiser_id`),
  KEY `FK8BBA12FF3D50C72` (`modifier_id`),
  KEY `FK8BBA12FB2118A9E` (`visibility_id`),
  CONSTRAINT `FK8BBA12FB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK8BBA12F8A6370C1` FOREIGN KEY (`organiser_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK8BBA12FEBA3847E` FOREIGN KEY (`dendrite_id`) REFERENCES `T_DENDRITES` (`id`),
  CONSTRAINT `FK8BBA12FF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_ELECTIONS` WRITE;
/*!40000 ALTER TABLE `T_ELECTIONS` DISABLE KEYS */;

INSERT INTO `T_ELECTIONS` (`id`, `CREATED`, `MODIFIED`, `CODE`, `DESCRIPTION`, `END_CANDIDATURE`, `FIN_VOTE`, `START_CANDIDATURE`, `START_VOTE`, `modifier_id`, `dendrite_id`, `organiser_id`, `visibility_id`)
VALUES
	(1,'2013-06-29 02:41:15','2013-09-13 17:51:24','Election CPD 2015','Elections pour le nouveau Bureau CPD 2015-2016','2015-05-21 00:00:00','2015-05-22 00:00:00','2015-05-20 00:00:00','2015-05-21 00:00:00',144,4,144,NULL),
	(2,'2013-06-29 02:41:15',NULL,'Election CPD 2016','Elections pour le nouveau Bureau CPD 2016-2017','2016-06-21 07:07:59','2016-06-22 09:07:59','2016-05-04 05:07:59','2016-06-21 08:07:59',NULL,4,121,NULL),
	(3,'2013-09-13 19:25:23','2013-09-14 22:35:32','Code','Hello ceci est une élection.','2013-09-15 00:00:00','2013-09-16 00:00:00','2013-09-03 00:00:00','2013-09-15 00:00:00',144,5,144,NULL),
	(8,'2013-09-13 23:49:40','2013-09-14 00:07:31','Test Alpha 1','Test avec le beton!!!','2013-09-13 00:00:00','2013-09-13 00:00:00','2013-09-13 00:00:00','2013-09-13 00:00:00',144,1,121,NULL),
	(9,'2013-09-13 23:49:48','2013-09-14 00:10:25','Demo paris','Ceci est une élection de demo','2013-09-13 00:00:00','2013-09-13 00:00:00','2013-09-13 00:00:00','2013-09-13 00:00:00',144,2,144,NULL),
	(11,'2013-09-17 21:55:35','2013-09-17 21:55:38','Election CPD Test','Ceci est une élection test qui nous permettra de connaitre d\'éventuelles erreurs qu\'on corrigera avant les vraies élections.','2013-09-22 00:00:00','2013-09-24 00:00:00','2013-09-17 00:00:00','2013-09-22 00:00:00',144,1,144,NULL);

/*!40000 ALTER TABLE `T_ELECTIONS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_EVENTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_EVENTS`;

CREATE TABLE `T_EVENTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CONTENT` longtext,
  `TITLE` varchar(255) NOT NULL,
  `BEGINNING` datetime NOT NULL,
  `COST` double NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `dendrite_id` bigint(20) DEFAULT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `adresse_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31ED8B64EBA3847E` (`dendrite_id`),
  KEY `FK31ED8B64A7CD013E` (`author_id`),
  KEY `FK31ED8B64F3D50C72` (`modifier_id`),
  KEY `FK31ED8B645F0BDBD6` (`photo_id`),
  KEY `FK31ED8B646239C28` (`adresse_id`),
  KEY `FK31ED8B64B2118A9E` (`visibility_id`),
  CONSTRAINT `FK31ED8B64B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK31ED8B645F0BDBD6` FOREIGN KEY (`photo_id`) REFERENCES `T_PHOTOS` (`id`),
  CONSTRAINT `FK31ED8B646239C28` FOREIGN KEY (`adresse_id`) REFERENCES `T_ADRESS` (`id`),
  CONSTRAINT `FK31ED8B64A7CD013E` FOREIGN KEY (`author_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK31ED8B64EBA3847E` FOREIGN KEY (`dendrite_id`) REFERENCES `T_DENDRITES` (`id`),
  CONSTRAINT `FK31ED8B64F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_EVENTS` WRITE;
/*!40000 ALTER TABLE `T_EVENTS` DISABLE KEYS */;

INSERT INTO `T_EVENTS` (`id`, `CREATED`, `MODIFIED`, `CONTENT`, `TITLE`, `BEGINNING`, `COST`, `modifier_id`, `author_id`, `dendrite_id`, `photo_id`, `adresse_id`, `visibility_id`)
VALUES
	(10,'2013-10-20 06:01:16','2013-10-20 06:01:18','<p>Tout le monde est invit&eacute; &agrave; cette grande f&ecirc;te d\'int&eacute;gration des boursiers nouvellement venus du Mali.</p>\r\n<p>Ils ont besoin de tout un chacun afin de partager avec eux nos exp&eacute;riences, nos aventures.</p>\r\n<p>Nous vous souhaitons la bienvenue &agrave; l\'avance!</p>','Fête d\'intégration','2013-10-26 10:00:00',0,144,144,2,NULL,61,NULL);

/*!40000 ALTER TABLE `T_EVENTS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_EVOLUTIONS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_EVOLUTIONS`;

CREATE TABLE `T_EVOLUTIONS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `PROGRESS` int(11) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `feedback_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC695D52371EA9D7F` (`feedback_id`),
  KEY `FKC695D523F3D50C72` (`modifier_id`),
  KEY `FKC695D523B2118A9E` (`visibility_id`),
  CONSTRAINT `FKC695D523B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FKC695D52371EA9D7F` FOREIGN KEY (`feedback_id`) REFERENCES `T_FEEDBACKS` (`id`),
  CONSTRAINT `FKC695D523F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_EVOLUTIONS` WRITE;
/*!40000 ALTER TABLE `T_EVOLUTIONS` DISABLE KEYS */;

INSERT INTO `T_EVOLUTIONS` (`id`, `CREATED`, `MODIFIED`, `DESCRIPTION`, `PRIORITY`, `PROGRESS`, `modifier_id`, `feedback_id`, `visibility_id`)
VALUES
	(7,'2013-10-12 23:09:51','2013-10-12 23:09:51','Changement de priorité de 0 à 99',NULL,NULL,144,13,NULL),
	(8,'2013-10-12 23:30:35','2013-10-12 23:30:35','Changement de priorité de 0 à 80',NULL,NULL,144,14,NULL),
	(9,'2013-10-13 00:00:32','2013-10-13 00:00:32','Changement de priorité de 0 à 70',NULL,NULL,144,12,NULL),
	(10,'2013-10-13 00:08:43','2013-10-13 00:08:43','Changement de priorité de 0 à 20',NULL,NULL,144,15,NULL),
	(11,'2013-10-12 23:16:57','2013-10-12 23:16:57','Progression de 20 à 39',3,39,123,7,NULL),
	(12,'2013-10-12 23:17:02','2013-10-12 23:17:02','Progression de 39 à 25',3,25,123,7,NULL),
	(13,'2013-10-13 01:24:07','2013-10-13 01:24:07','Changement de priorité de 0 à 20',NULL,NULL,144,16,NULL),
	(14,'2013-10-13 01:23:55','2013-10-13 01:23:55','Progression de 0 à 32',0,32,151,17,NULL),
	(15,'2013-10-13 01:23:59','2013-10-13 01:23:59','Progression de 32 à 0',0,0,151,17,NULL),
	(16,'2013-10-13 01:26:02','2013-10-13 01:26:03','Changement de priorité de 0 à 10',NULL,NULL,151,17,NULL),
	(17,'2013-10-13 01:28:40','2013-10-13 01:28:41','Changement de priorité de 20 à 10',NULL,NULL,144,15,NULL),
	(18,'2013-10-13 01:29:57','2013-10-13 01:29:57','Changement de priorité de 80 à 5',NULL,NULL,144,14,NULL),
	(19,'2013-10-13 01:30:46','2013-10-13 01:30:46','Changement de priorité de 70 à 5',NULL,NULL,144,12,NULL),
	(20,'2013-10-13 02:45:25','2013-10-13 02:45:25','Progression de 0 à 30\nChangement de priorité de 0 à 21',NULL,NULL,92,18,NULL),
	(21,'2013-10-13 02:45:28','2013-10-13 02:45:28','Progression de 0 à 30\nChangement de priorité de 0 à 21',NULL,NULL,92,18,NULL),
	(22,'2013-10-13 02:45:31','2013-10-13 02:45:31','Progression de 0 à 30\nChangement de priorité de 0 à 21',NULL,NULL,92,18,NULL),
	(23,'2013-10-13 02:47:58','2013-10-13 02:47:58','Progression de 0 à 20',20,20,144,16,NULL),
	(24,'2013-10-13 02:47:10','2013-10-13 02:47:10','Progression de 0 à 48',NULL,NULL,83,13,NULL),
	(25,'2013-10-19 00:06:01','2013-10-19 00:06:01','Progression de 20 à 80',20,80,144,16,NULL),
	(26,'2013-10-20 22:38:09','2013-10-20 22:38:09','Progression de 80 à 100',20,100,144,16,NULL),
	(27,'2013-10-20 22:38:32','2013-10-20 22:38:32','Progression de 48 à 99',99,99,144,13,NULL),
	(28,'2013-10-21 03:36:08','2013-10-21 03:36:08','Progression de 0 à 100',5,100,144,12,NULL),
	(29,'2013-10-21 03:36:26','2013-10-21 03:36:26','Progression de 0 à 70',5,70,144,14,NULL),
	(30,'2013-10-21 23:57:27','2013-10-21 23:57:27','Changement de priorité de 0 à 20',NULL,NULL,144,20,NULL),
	(31,'2013-10-22 22:13:17','2013-10-22 22:13:17','Progression de 0 à 100\nChangement de priorité de 0 à 20',NULL,NULL,144,21,NULL),
	(32,'2013-10-24 22:08:09','2013-10-24 22:08:09','Changement de priorité de 0 à 30',NULL,NULL,144,25,NULL),
	(33,'2013-10-24 22:08:57','2013-10-24 22:08:57','Changement de priorité de 0 à 30',NULL,NULL,144,24,NULL),
	(34,'2013-10-24 22:09:21','2013-10-24 22:09:21','Changement de priorité de 0 à 30',NULL,NULL,144,23,NULL),
	(35,'2013-10-24 22:09:52','2013-10-24 22:09:52','Changement de priorité de 0 à 30',NULL,NULL,144,22,NULL),
	(36,'2013-10-24 22:45:37','2013-10-24 22:45:37','Progression de 0 à 100',30,100,144,25,NULL),
	(37,'2013-10-25 01:19:06','2013-10-25 01:19:06','Changement de priorité de 0 à 30',NULL,NULL,144,26,NULL),
	(38,'2013-10-24 23:24:21','2013-10-24 23:24:21','Progression de 0 à 100',30,100,144,23,NULL),
	(39,'2013-10-24 23:24:28','2013-10-24 23:24:28','Progression de 0 à 100',30,100,144,24,NULL),
	(40,'2013-10-25 00:57:24','2013-10-25 00:57:24','Progression de 0 à 100',30,100,144,22,NULL),
	(41,'2013-10-26 15:50:01','2013-10-26 15:50:02','Changement de priorité de 0 à 25',NULL,NULL,144,27,NULL),
	(42,'2013-10-29 08:48:20','2013-10-29 08:48:20','Progression de 99 à 100',99,100,144,13,NULL),
	(43,'2013-11-06 19:45:38','2013-11-06 19:45:38','Progression de 70 à 100',5,100,144,14,NULL),
	(44,'2013-11-09 13:30:36','2013-11-09 13:30:36','Progression de 50 à 51',2,51,213,8,NULL),
	(45,'2013-11-12 17:13:10','2013-11-12 17:13:10','Changement de priorité de 25 à 0',NULL,NULL,144,27,NULL),
	(46,'2013-11-12 17:13:21','2013-11-12 17:13:21','Changement de priorité de 30 à 0',NULL,NULL,144,26,NULL),
	(47,'2013-11-12 17:13:45','2013-11-12 17:13:45','Changement de priorité de 21 à 0',NULL,NULL,144,18,NULL),
	(48,'2013-11-12 17:14:19','2013-11-12 17:14:19','Changement de priorité de 3 à 0',NULL,NULL,144,7,NULL),
	(49,'2013-11-12 17:14:32','2013-11-12 17:14:32','Changement de priorité de 2 à 0',NULL,NULL,144,8,NULL),
	(50,'2013-11-12 17:15:17','2013-11-12 17:15:17','Changement de priorité de 20 à 0',NULL,NULL,144,20,NULL),
	(51,'2013-11-12 17:15:28','2013-11-12 17:15:28','Changement de priorité de 10 à 0',NULL,NULL,144,17,NULL),
	(52,'2013-11-12 17:15:41','2013-11-12 17:15:41','Changement de priorité de 10 à 0',NULL,NULL,144,15,NULL),
	(53,'2013-11-12 17:15:54','2013-11-12 17:15:54','Changement de priorité de 1 à 0',NULL,NULL,144,2,NULL),
	(54,'2013-11-12 17:16:08','2013-11-12 17:16:08','Changement de priorité de 1 à 0',NULL,NULL,144,3,NULL),
	(55,'2013-11-13 11:47:02','2013-11-13 11:47:02','Progression de 0 à 100',0,100,144,41,NULL),
	(56,'2013-11-13 11:47:19','2013-11-13 11:47:19','Progression de 0 à 100',0,100,144,40,NULL),
	(57,'2013-11-13 11:49:49','2013-11-13 11:49:49','Progression de 0 à 100',0,100,144,42,NULL),
	(58,'2013-11-13 16:02:40','2013-11-13 16:02:40','Progression de 0 à 100',0,100,144,44,NULL),
	(59,'2013-11-15 22:59:30','2013-11-15 22:59:30','Progression de 0 à 6',NULL,NULL,213,34,NULL),
	(60,'2013-11-15 23:02:39','2013-11-15 23:02:39','Changement de priorité de 0 à 33',NULL,NULL,213,37,NULL),
	(61,'2013-11-15 23:05:21','2013-11-15 23:05:21','Changement de priorité de 0 à 23',NULL,NULL,213,34,NULL),
	(62,'2013-11-15 23:06:27','2013-11-15 23:06:27','Progression de 0 à 1\nChangement de priorité de 0 à 6',NULL,NULL,213,39,NULL),
	(63,'2013-11-16 03:50:17','2013-11-16 03:50:17','Progression de 0 à 6',33,6,213,37,NULL),
	(64,'2013-11-16 03:50:25','2013-11-16 03:50:25','Progression de 6 à 33',23,33,213,34,NULL),
	(65,'2013-11-16 03:50:40','2013-11-16 03:50:40','Progression de 0 à 6',0,6,213,54,NULL),
	(66,'2013-11-16 03:50:42','2013-11-16 03:50:42','Progression de 6 à 0',0,0,213,54,NULL),
	(67,'2013-11-16 23:20:07','2013-11-16 23:20:07','Progression de 0 à 100',0,100,144,52,NULL),
	(68,'2013-11-16 23:20:31','2013-11-16 23:20:31','Progression de 0 à 100',0,100,144,49,NULL),
	(69,'2013-11-16 23:21:33','2013-11-16 23:21:33','Progression de 0 à 100',0,100,144,32,NULL),
	(70,'2013-11-16 23:21:40','2013-11-16 23:21:40','Progression de 0 à 100',0,100,144,31,NULL),
	(71,'2013-11-16 23:21:52','2013-11-16 23:21:52','Progression de 0 à 100',0,100,144,30,NULL),
	(72,'2013-11-16 23:21:59','2013-11-16 23:21:59','Progression de 0 à 100',0,100,144,28,NULL),
	(73,'2013-11-16 23:22:10','2013-11-16 23:22:10','Progression de 0 à 100',0,100,144,27,NULL),
	(74,'2013-11-17 00:59:11','2013-11-17 00:59:11','Progression de 30 à 42',0,42,151,18,NULL),
	(75,'2013-11-17 00:59:13','2013-11-17 00:59:13','Progression de 42 à 26',0,26,151,18,NULL),
	(76,'2013-11-27 12:14:07','2013-11-27 12:14:07','Changement de priorité de 0 à 1',NULL,NULL,144,58,NULL);

/*!40000 ALTER TABLE `T_EVOLUTIONS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_EXPERIENCES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_EXPERIENCES`;

CREATE TABLE `T_EXPERIENCES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `BEGIN_MONTH` varchar(255) DEFAULT NULL,
  `BEGIN_YEAR` int(11) DEFAULT NULL,
  `COMPANY` varchar(255) DEFAULT NULL,
  `END_MONTH` varchar(255) DEFAULT NULL,
  `END_YEAR` int(11) DEFAULT NULL,
  `MISSION` varchar(255) DEFAULT NULL,
  `ON_GOING` bit(1) DEFAULT NULL,
  `POSTE` varchar(255) DEFAULT NULL,
  `SECTOR` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `cv_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22E8BEDBD2534C` (`cv_id`),
  KEY `FK22E8BEF3D50C72` (`modifier_id`),
  KEY `FK22E8BEB2118A9E` (`visibility_id`),
  CONSTRAINT `FK22E8BEB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK22E8BEDBD2534C` FOREIGN KEY (`cv_id`) REFERENCES `T_CVS` (`id`),
  CONSTRAINT `FK22E8BEF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_EXPERIENCES` WRITE;
/*!40000 ALTER TABLE `T_EXPERIENCES` DISABLE KEYS */;

INSERT INTO `T_EXPERIENCES` (`id`, `CREATED`, `MODIFIED`, `BEGIN_MONTH`, `BEGIN_YEAR`, `COMPANY`, `END_MONTH`, `END_YEAR`, `MISSION`, `ON_GOING`, `POSTE`, `SECTOR`, `modifier_id`, `cv_id`, `visibility_id`)
VALUES
	(1,'2013-09-12 18:07:50','2013-09-12 18:07:50','Janvier',2013,'Accenture','Juillet',2013,'Au sein de l\'equipe cliente',00000000,'Stagiaire','Conseil Systeme D\'Information ',121,121,NULL),
	(2,'2013-10-10 22:48:50','2013-10-10 23:03:42','Juillet',2013,'Orange Mali','Août',2013,'Mise en place de l\'application SIRH de la DRH',00000000,'Développeur','Informatique',144,144,NULL),
	(3,'2013-10-19 00:28:17','2013-10-19 00:28:19','Septembre',2011,'Academie Versailles','Avril',2012,'Accompagner, Encadrer, Développer l\'attitude autoritaire, attitude responsabilité',00000000,'Soutien scolaire','Education',144,144,NULL),
	(4,'2013-10-19 00:30:19','2013-10-19 00:30:20','Mai',2010,'Grenoble (UFR)','Juillet',2010,'Démonstration du théorème de point fixe de Brouwer et ses applications',00000000,'Chef d\'équipe','Recherche',144,144,NULL),
	(5,'2013-11-14 19:18:42','2013-11-14 19:18:43','Avril',2013,'AXA France','Novembre',2013,'- Calcul des provisions d\'AXA France sur le périmètre de la Prévoyance et de la dépendance',00000000,'Chargée d\'études actuarielles','Assurances',249,249,NULL);

/*!40000 ALTER TABLE `T_EXPERIENCES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_FEEDBACKS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_FEEDBACKS`;

CREATE TABLE `T_FEEDBACKS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `content` longtext,
  `priority` int(11) DEFAULT NULL,
  `progress` int(11) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `responsable_id` bigint(20) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `POSTER` varchar(255) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC68B3743A7CD013E` (`author_id`),
  KEY `FKC68B37439B2985EB` (`responsable_id`),
  KEY `FKC68B3743F3D50C72` (`modifier_id`),
  KEY `FKC68B3743B2118A9E` (`visibility_id`),
  CONSTRAINT `FKC68B3743B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FKC68B37439B2985EB` FOREIGN KEY (`responsable_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FKC68B3743A7CD013E` FOREIGN KEY (`author_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FKC68B3743F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_FEEDBACKS` WRITE;
/*!40000 ALTER TABLE `T_FEEDBACKS` DISABLE KEYS */;

INSERT INTO `T_FEEDBACKS` (`id`, `CREATED`, `MODIFIED`, `content`, `priority`, `progress`, `SUBJECT`, `modifier_id`, `author_id`, `responsable_id`, `EMAIL`, `POSTER`, `visibility_id`)
VALUES
	(1,'2013-09-26 10:06:18','2013-09-29 20:34:55','Il se produit une erreur quand on essaye de supprimer un feedback.\nA revoir.',0,100,'Administration suppression d\'un feedback',144,144,NULL,NULL,NULL,NULL),
	(2,'2013-09-23 16:14:53','2013-11-12 17:15:54','Rendre plus visible les élections surtout quand ça s\'approche!',0,0,'Election',144,144,121,NULL,NULL,NULL),
	(3,'2013-09-23 16:13:56','2013-11-12 17:16:08','Ajouter la possibilité de filtrer par dendrite aussi.',0,0,'Annuaire',144,144,92,NULL,NULL,NULL),
	(4,'2013-09-23 16:12:47','2013-09-29 20:34:33','Rendre plus riche le champ de saisi de l\'article c\'est trop basique ce champ de texte.',0,100,'Edition d\'article',144,144,NULL,NULL,NULL,NULL),
	(5,'2013-09-23 16:11:39','2013-09-29 20:34:21','Enlever l\'autocomplétion dans le champs de saisi des messages',0,100,'Messages',144,144,NULL,NULL,NULL,NULL),
	(6,'2013-09-23 16:10:10','2013-09-29 20:34:13','Il faut changer le menu de gauche dans l\'espace Dendrites.\nLes accordions ne fonctionnent pas bien.',1,100,'Menu de gauche dans dendrite',144,144,NULL,NULL,NULL,NULL),
	(7,'2013-09-29 02:22:36','2013-11-12 17:14:19','Deux ou trois niveaux d\'accès Dendrites, Cellule ou tout le monde.',0,25,'Events',144,144,123,NULL,NULL,NULL),
	(8,'2013-09-29 13:07:08','2013-11-12 17:14:32','Repenser l\'organisation générale des menus du site.',0,51,'Organisation menus',144,NULL,92,'baya.demba@cpd-mali.com','Baya',NULL),
	(12,'2013-09-30 19:26:05','2013-10-21 03:36:08','Bon les gars le site est bien et tout mais il faut qu\'on revoit le contenu, le rendre plus digérable et intuitif. \nPublier des articles sur le site pour attirer les gens dessus.\n\nIl faut que le site leur apporte une valeur ajouté au délà d\'\'avoir le site en place.\n\nNous allons le faire progressivement. \nJe vais intervenir un peu plus souvent sur cpd desormais! \nA+',5,100,'Le contenu!',144,NULL,83,'yacouba.sagara@gmail.com','Yo',NULL),
	(13,'2013-10-12 23:08:43','2013-10-29 08:48:20','La page doit avoir un caractère attractif et simpliste.\r\nDoit faire l\'objet de guide pour l\'utilisateur afin de l\'aider à avoir ce qu\'il cherche ou ce qui l\'intéresse.',99,100,'Presentation',144,144,83,NULL,NULL,NULL),
	(14,'2013-10-12 23:22:03','2013-11-06 19:45:38','Donner une plus grande visibilité aux publications faites sur CPD.\r\nPar exemple poster un résumé sur Facebook et/ou Twitter.',5,100,'Publications',144,144,144,NULL,NULL,NULL),
	(15,'2013-10-13 00:08:10','2013-11-12 17:15:41','Mettre des catégories pour des publications',0,0,'Catégorisation',144,144,144,NULL,NULL,NULL),
	(16,'2013-10-13 01:23:23','2013-10-20 22:38:09','Je me porte garant de mise en place d\'une page d\'accueil pour les informations liées aux membres.\r\nLeurs profils, Leurs champs d\'accès.',20,100,'Page présentation membres',144,144,144,NULL,NULL,NULL),
	(17,'2013-10-13 01:23:35','2013-11-12 17:15:28','Organisation du module',0,0,'Evénements et activités',144,151,151,NULL,NULL,NULL),
	(18,'2013-10-13 02:43:43','2013-11-17 00:59:13','\r\nPrésentation du domaine \"Activités\" :\r\n\r\nLa CPD organise des activités tout au long de l\'année... eh oui on ne fait pas que bosser!\r\nCes foultitudes d\'activités sont autant d\'occasions pour toi de faire connaissance et de passer de bons moments avec d\'autres maliens :\r\n\r\n- Le Minicode : viens booster ta culture G sur différents sujets!\r\n- La Fête d\'intégration des nouveaux : retour au bercail ... notre communauté prend de l\'ampleur  :-)\r\n- Le WE Ski : un malien au ski pour la 1ère fois, voilà ce que ça donne ;-) \r\n- Des sorties divers (resto, ciné, bowling...) : du fun\' !!! viens remplir ta vie de moments inoubliables.\r\n- Elections : la Cellule se renouvelle, c\'est le moment de choisir ton bureau de rêve!!',0,26,'Présentation Domaine \"Activités\"',151,92,151,NULL,NULL,NULL),
	(20,'2013-10-21 23:54:14','2013-11-12 17:15:17','Sur la page d\'accueil, je pense que c\'est mieux de mettre les liens directement dans les titires (contribuer au dév....) au lieu de mettre \"En savoir plus\" à chaque fois !',0,0,'Lien page home',144,NULL,83,'baya.demba@gmail.com','Baya',NULL),
	(21,'2013-10-22 22:11:13','2013-10-22 22:13:17','Salut cheick,\r\nil faut affiché le nom des personnes au niveau des commentaires ou générer un tooltip avec leur profil.\r\n',20,100,'Commentaires',144,NULL,144,'alassane.kane7@gmail.com','Alassane KANE',NULL),
	(22,'2013-10-24 13:14:49','2013-10-25 00:57:24','Sans etre connecté, quand je vais dans Publications--> Evenements, ca me dirige dans Dendrites alors que les 2 autres dirigent dans Cellule?',30,100,'Publications',144,NULL,92,'elaysama@gmail.com','Elay',NULL),
	(23,'2013-10-24 13:27:14','2013-10-24 23:24:21','C\'est ecris Mon Profil en haut meme quand je suis sur le Profil de quelqu\'un d\'autre!',30,100,'Mon profil',144,147,144,NULL,NULL,NULL),
	(24,'2013-10-24 13:32:01','2013-10-24 23:24:28','C\'est genial les messages!! Sauf qu\'on peut rien faire dans la parti message??',30,100,'Messages',144,147,144,NULL,NULL,NULL),
	(25,'2013-10-24 13:34:27','2013-10-24 22:45:37','Lors de l\'inscription on ne sait pas a quoi correspond la date qu\'il faut rentrer!!!',30,100,'Inscription',144,NULL,144,'elaysama@gmail.com','Elay',NULL),
	(26,'2013-10-24 22:52:23','2013-11-12 17:13:21','J\'ai des remarques mais pas de propositions de solutions. 1 Remarque: Le drapeau du Mali et le sceau. le drapeau au centre ne semble pas joli, la matière le constituant aussi n\'est pas trop top. Le sceau en gros et en face de \"....Mali et Afrique...\" doit être déplacé et diminué. 1. Proposition: Sur la page d\'accueil (sur l\'herbe), arranger la carte du Mali aux couleurs nationales et le sceau à gauche. 2.Remarque: Le texte \"Cellule Pour le Développement \" et au-dessous \"L\'excellence au service du Mali\". La mise en couleur de ces deux textes n\'est pas top à mon avis ainsi que la majuscule de toutes les lettres de \"CELLULE...MALI\". 2. Proposition: Centrer et faire le bas (pas peut etre sur les nuages mais sur l\'herbe) et en italique: Cellule Pour le Développement (Seules les lettres correspondant au sigle CPD en majuscule. Et au dessous de cette écriture, le slogan \"L\'excellence au service du Mali\". Je reviendrai prochainement sur le reste des remarques et toujours sur la première page (accueil). Courage à vous, nous vous sommes reconnaissants. Ne vous décourager surtout pas!',0,0,'AVIS',144,NULL,92,'grandbaba.mali@yahoo.fr','KEITA Mamadou',NULL),
	(27,'2013-10-26 10:04:59','2013-11-16 23:22:10','dans les message, on ne sait pas avec qui on communique quand on est dans le message! ',0,100,'CV',144,121,144,NULL,NULL,NULL),
	(28,'2013-10-27 20:17:16','2013-11-16 23:21:59','Pourquoi je ne reçois pas les emails de confirmation ?',0,100,'Email de confirmation',144,144,NULL,NULL,NULL,NULL),
	(29,'2013-10-29 08:47:46','2013-10-29 08:47:46','Salut les devs,\r\nEst ce quelqu\'un peut se porter volontaire pour mettre en place la possibilité de joindre des fichiers (Photos, Présentations, ...)\r\nJ\'ai un examen juste après les vacances.',0,0,'Events Fichiers joints',144,144,NULL,NULL,NULL,NULL),
	(30,'2013-10-29 17:25:34','2013-11-16 23:21:52','j\'essaie de me connecter mais on me dit que mon adresse mail na pas ete verifier, et je suis aller ma boite mais il nya pas de courier ni dans ma boite de reception ni dan le courrier indesirable ',0,100,'impossibilite de se connecter',144,NULL,NULL,'taylore@live.fr','TOURE KADIATOU',NULL),
	(31,'2013-11-05 07:25:22','2013-11-16 23:21:40','Impossible d\'envoyer des msg :D',0,100,'Msg',144,121,NULL,NULL,NULL,NULL),
	(32,'2013-11-05 10:43:21','2013-11-16 23:21:33','1. Il me met confirm.user quand je cree, faudrait changer le message d\'erreur\r\n\r\n2. Pas d\'email de confirmation recu!!',0,100,'Creation',144,NULL,NULL,'elaysama@gmail.com','Elay',NULL),
	(33,'2013-11-05 21:03:33','2013-11-05 21:03:33','cc, qd on clique sur articles avant que la page se rafraiche il ya d\'abord le texte avec des >p< c\'est pas tres joli',0,0,'affichage des articles',223,223,NULL,NULL,NULL,NULL),
	(34,'2013-11-12 17:06:57','2013-11-16 03:50:25','On devrait farie des notifications un peu comme facebook pour quand quelqu\'un commente un de nos articles ou events!!',23,33,'Notification',213,121,213,NULL,NULL,NULL),
	(35,'2013-11-12 17:10:21','2013-11-12 17:10:21','On devrait aussi travailler sur l\'apparence des evements!! Ca fait super moche pour l\'instant :D ',0,0,'Evenement',121,121,NULL,NULL,NULL,NULL),
	(36,'2013-11-12 17:14:39','2013-11-12 17:14:39','Il faudrait peut etre mettre a droite genre les msgs recent, pour que quand on ouvre un message on soit pas oblige de revenir en arriere pour voir les autres messages...',0,0,'Messages',121,121,NULL,NULL,NULL,NULL),
	(37,'2013-11-12 17:15:42','2013-11-16 03:50:17','On a quand meme un souci sur IE! (je sais QUI utilise encore IE mais bon)',33,6,'Internet explorer',213,121,213,NULL,NULL,NULL),
	(38,'2013-11-12 19:41:55','2013-11-12 19:41:55','Salut, merci à tous ceux qui se sont penchés sur la création du site. Il est bien fait, cependant je pense qu\'il manque de contenu : Je propose que cette année les personnes se rendant au Ski s\'inscrivent en ligne sur le site et procèdent au paiement en ligne. Cela incitera peut être les gens à visiter le site.  Inciter les gens à améliorer leur profil qui peut Maybe être regardé par des recruteurs d\'entreprise du Mali ou pour permettre au gouvernement de voir ce qu\'on devenu les anciens. Se serait bien aussi de voir combien de membres sont inscrits sur le site ainsi que leur photo défilée ou bien peut être que cela y est déjà ?  Parler du mini code demander des résumés des anciennes présentations déjà réalisées qui pourront être consultées par les nouveaux et autres visiteurs du site. Bon je vais Try de réfléchir à d\'autres suggestions ;) merci encore à tous ',0,0,'Contenu du site',234,234,NULL,NULL,NULL,NULL),
	(39,'2013-11-12 20:40:35','2013-11-15 23:06:27','a part la premiere page d\'accueil on a pas d\'images, il faut mettre plus de couleurs et dimages pour faire venir les gens!!!',6,1,'image',213,223,213,NULL,NULL,NULL),
	(40,'2013-11-12 20:44:35','2013-11-13 11:47:19','\"CREER DES SOUVENIR POUR DES ANNEES A VENIR\" IL MANQUE UN \"S\"',0,100,'faute',144,223,NULL,NULL,NULL,NULL),
	(41,'2013-11-12 20:45:45','2013-11-13 11:47:02','\"un site à l\'image de ses utilisateur\" a avec accent',0,100,'faute',144,223,NULL,NULL,NULL,NULL),
	(42,'2013-11-12 22:58:02','2013-11-13 11:49:49','Bonjour à tous,\r\nJe voulais tout d\'abord vous féliciter pour ce travail. Rien à dire sur l\'inscription. La procédure et la confirmation. J\'ai constaté quelques fautes aux premiers abords que je voudrais signaler; Sur les slogans: créer des souvenirs, développement, à venir; Dans les paragraphes suivants de la page: Elle s\'investit, savoir-faire et dans la dernière partie en bas, j\'ai trouvé qu\'il manque quelques accents, et inscrivez-vous. Je reviendrai vers vous avec d\'autres feedbacks. Bien à vous.\r\n\r\nCheickna',0,100,'feedback de l\'inscription',144,237,NULL,NULL,NULL,NULL),
	(44,'2013-11-13 11:11:39','2013-11-13 16:02:40','Hello la team technique,\r\n\r\nJ\'ai fait une petite erreur lors de mon enrégistrement: inversion du nom et du prénom. J\'ai tenté de la corriger sans succès; un message d\'erreur apparait dès que je valide la modification. Je crois que c\'est lié à la case mot de passe. \r\n\r\nThanks d\'avance!',0,100,'Chnager mes coordonnées',144,241,NULL,NULL,NULL,NULL),
	(45,'2013-11-13 15:02:35','2013-11-17 00:58:07','Comment ajouter des images dans les Annonces?? ',0,0,'Ajout D\'images',151,121,151,NULL,NULL,NULL),
	(46,'2013-11-13 20:24:57','2013-11-13 20:24:57','Il sera bien de penser à mettre en place le système de paiement afin que les cotisations se passent bien!\r\nQu\'est ce que vous en pensez ?',0,0,'Paiement de cotisation',144,144,NULL,NULL,NULL,NULL),
	(47,'2013-11-14 03:41:45','2013-11-14 03:41:45','Faudrait qu\'on ajoute les membres du bureau actuel sur CPD!! :D',0,0,'Membre',NULL,NULL,NULL,'elaysama@gmail.com','Elay Maiga',NULL),
	(48,'2013-11-14 05:13:14','2013-11-14 05:13:14','Que pensez vous de la proposition de Oumou ?\r\nLe fait de faire défiler les images des utilisateurs.\r\nOn pourra le faire dans la page membres comme dans index!',0,0,'Défilement des images des utilisateurs',144,144,NULL,NULL,NULL,NULL),
	(49,'2013-11-14 08:05:56','2013-11-16 23:20:31','Bonjour,\r\n\r\nj\'ai remarqué que quand l\'on s\'inscrit et que l\'on est boursier, vous nous demandez d\'indiquer notre dendride.\r\n\r\nEt si on ne fait partie d\'aucune dendride ? \r\n\r\nDans l\'enventualité où on appartient forcement tous à une dendridre, comment peut on determiner sa dendride ?\r\n\r\ncordialement,\r\nMamadou Goundiam',0,100,'inscription',144,NULL,NULL,'goundiam1991@gmail.com','Mamadou Goundiam',NULL),
	(50,'2013-11-14 21:17:49','2013-11-14 21:17:49','Je propose la création d\'une discussion instantanée sur le site. Comme ça on pourra voir les personnes connectées et bavarder entre nous. Si vous voulez que les gens passent moins de temps sur Facebook et plus sur cpd-mali... ça commence par la création d\'un tchat je crois...',0,0,'Créer un Tchat !',236,236,NULL,NULL,NULL,NULL),
	(51,'2013-11-14 23:26:05','2013-11-14 23:26:05','Bonjour, \r\nBravo et félicitation\r\nQuelle petite remarque\r\n- Sur le pavé en haut les rubriques disparaissent quand on clique dessous, peut être que je suis myope. \r\n- Dans la rubrique sign in les blocs Facebook, Google+ et le dernier ne sont pas de la même taille et sur la même ligne\r\n- Dans la partie la cellule l\'icone accueil et aussi le cotiser/faire un don\r\n- En bas il y\'a deux rubriques information dont 1 avec s\r\n- Dans les évènements des dendrites les icones des rubriques disparaissent quand on clique dessus. \r\n\r\nBonne journée.',0,0,'Feedback 1',254,254,NULL,NULL,NULL,NULL),
	(52,'2013-11-15 12:50:59','2013-11-16 23:20:07','Hello !\r\nJe me suis trompé de promotion\r\nc\'est 2007 au lieu de 2008 mais j\'arrive pas à le modifier pour commencer...... :D  Aller j\'ai confiance c\'est une petite modif à faire. Biz\r\n',0,100,'Changement de Profil',144,256,NULL,NULL,NULL,NULL),
	(53,'2013-11-15 22:56:37','2013-11-15 22:56:37','Il y a un problème lorsqu\'on essaie de changer la photo.',0,0,'Photo',213,213,NULL,NULL,NULL,NULL),
	(54,'2013-11-15 23:54:12','2013-11-16 03:50:42','Il y a un problème au niveau des alertes comme celui qui s\'affiche sur  la page d\'accueil \"Cette page est réservé au boursier veuillez.... \"',0,0,'Alert',213,213,NULL,NULL,NULL,NULL),
	(55,'2013-11-16 13:07:37','2013-11-16 13:07:37','tu nas pas corriger les fautes',0,0,'fautes',NULL,NULL,NULL,'taylore@live.fr','kadiatou toure',NULL),
	(56,'2013-11-18 12:19:12','2013-11-18 12:19:12','Salut les gars,\r\n\r\nBravo pour le site, ça fait plusieurs fois que je le visite maintenant et je me rends compte que c\'est difficile de suivre les avancées dans une discussion. Par exemple Après avoir suivi l\'article de Kady sur les sweats, quand je suis revenu sur le site j\'ai eu du mal à suivre ce qui s\'est passé en mon absence. Une sorte de notification serait une solution par exemple.\r\n\r\nEn termes de présentation, je préfère des onglets pour \"Evènements\", \"Annonces\" et \"Articles\" plutôt que les menus déroulants. \r\n\r\nBon c\'est tout pour l\'instant hein; évidement je ne note que les points que j\'aimerai améliorer, si je devais noter tous les points poisitifs ce serait trop long ;) ',0,0,'Impressions',201,201,NULL,NULL,NULL,NULL),
	(57,'2013-11-26 08:50:54','2013-11-26 08:50:54','<a href=http://trustdrugsshop.com/cialis/>buy cheap cialis</a> - <a href=http://trustdrugsshop.com/cialis/>cialis 10 mg</a> , http://trustdrugsshop.com/cialis/ cheap generic cialis',0,0,'buy generic cialis a 20929',NULL,NULL,NULL,'gavyn.jervis@aol.com','Tutanadamen',NULL),
	(58,'2013-11-26 21:40:21','2013-11-27 12:14:07','Serait possible de liker les commentaires publiés. \r\nEt aussi de croitre la visibilité des commentaires. \r\n\r\nExemple: j\'aime la publication de Kady et de Sékou, je veux les liker et faire savoir aux autres. Un peut comme chez Facebook :D \r\n\r\nBon courage nos Geeks Chériii Coco :) ',1,0,'Like de commentaires',144,249,144,NULL,NULL,NULL),
	(59,'2013-11-26 21:45:56','2013-11-26 21:45:56','Sur la page d\'acceuil il serait possible de mettre la personne en honneur le jour de son anniv. On peut aussi penser à un lien vers doodle pour l\'organisation de surprise. \r\nDe même cela peut nous aider à nous rassembler et contribuer à l\'occasion de naissance ou de décès dans une famille de  CPDien(nes) ',0,0,'Anniversaire de Cpédien(nes) ',249,249,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `T_FEEDBACKS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_FORMATIONS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_FORMATIONS`;

CREATE TABLE `T_FORMATIONS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `BEGIN_YEAR` int(11) NOT NULL,
  `DOMAIN` varchar(255) NOT NULL,
  `END_YEAR` int(11) DEFAULT NULL,
  `LEVEL` varchar(255) NOT NULL,
  `ON_GOING` bit(1) DEFAULT NULL,
  `SCHOOL` varchar(255) NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `cv_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF76912ADDBD2534C` (`cv_id`),
  KEY `FKF76912ADF3D50C72` (`modifier_id`),
  KEY `FKF76912ADB2118A9E` (`visibility_id`),
  CONSTRAINT `FKF76912ADB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FKF76912ADDBD2534C` FOREIGN KEY (`cv_id`) REFERENCES `T_CVS` (`id`),
  CONSTRAINT `FKF76912ADF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_FORMATIONS` WRITE;
/*!40000 ALTER TABLE `T_FORMATIONS` DISABLE KEYS */;

INSERT INTO `T_FORMATIONS` (`id`, `CREATED`, `MODIFIED`, `BEGIN_YEAR`, `DOMAIN`, `END_YEAR`, `LEVEL`, `ON_GOING`, `SCHOOL`, `modifier_id`, `cv_id`, `visibility_id`)
VALUES
	(1,'2013-06-29 02:53:10','2013-10-19 00:33:08',2011,'Informatiques, Réseaux et sécurité',NULL,'3ème Année',00000001,'Ecole Polytechnique',144,144,NULL),
	(2,'2013-07-02 08:49:06','2013-09-12 16:28:29',2010,'Mathématiques',2011,'Licence',00000000,'Université Joseph Fourier',144,144,NULL),
	(4,'2013-09-12 16:42:56','2013-09-12 17:09:38',2008,'Mathématiques Physique Mécanique',2010,'2ème Année',00000000,'Université Joseph Fourier',144,144,NULL),
	(8,'2013-09-12 17:11:19','2013-10-19 00:22:43',2008,'Mathématiques',2010,'2ème Année',00000000,'Université Joseph Fourier',144,144,NULL),
	(9,'2013-09-12 17:47:02','2013-09-12 17:47:02',2010,'Intelligence Artificielle',NULL,'Ingenieur',00000001,'Ecole Centrale Paris',121,121,NULL),
	(11,'2013-10-20 22:59:47','2013-10-20 22:59:47',2012,'Maths appliquées-Economie',NULL,'2e Année',00000001,'Ecole polytechnique',153,153,NULL),
	(12,'2013-10-20 23:01:33','2013-10-20 23:01:33',2009,'Mathématiques',2012,'Licence',00000000,'Université Joseph Fourier',153,153,NULL),
	(13,'2013-11-06 20:13:09','2013-11-06 20:13:09',2004,'Technique',2007,'Bac',00000000,'Lycée Technique de Bamako',123,123,NULL),
	(14,'2013-11-06 20:13:44','2013-11-06 20:13:44',2007,'Mécanique',2010,'Licence',00000000,'Université Joseph Fourier',123,123,NULL),
	(15,'2013-11-06 20:13:45','2013-11-06 20:14:40',2010,'Mécanique/Génie civil',2014,'Ingénieur',00000001,'Ecole Polytechnique',123,123,NULL),
	(16,'2013-11-06 20:15:35','2013-11-06 20:15:35',2013,'Génie civil/ Mécanique',NULL,'BAC+5',00000001,'Ecole des Ponts & Chaussées',123,123,NULL),
	(17,'2013-11-14 19:17:04','2013-11-14 19:17:04',2010,'Mathematiques Appliquées/Info/Actuariat/Management de projets',2013,'Bac + 5',00000000,'Ecole Centrale Paris',249,249,NULL);

/*!40000 ALTER TABLE `T_FORMATIONS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_HOBBIES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_HOBBIES`;

CREATE TABLE `T_HOBBIES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `COMMENT` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `cv_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9E54C0E5DBD2534C` (`cv_id`),
  KEY `FK9E54C0E5F3D50C72` (`modifier_id`),
  KEY `FK9E54C0E5B2118A9E` (`visibility_id`),
  CONSTRAINT `FK9E54C0E5B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK9E54C0E5DBD2534C` FOREIGN KEY (`cv_id`) REFERENCES `T_CVS` (`id`),
  CONSTRAINT `FK9E54C0E5F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_HOBBIES` WRITE;
/*!40000 ALTER TABLE `T_HOBBIES` DISABLE KEYS */;

INSERT INTO `T_HOBBIES` (`id`, `CREATED`, `MODIFIED`, `COMMENT`, `TITLE`, `modifier_id`, `cv_id`, `visibility_id`)
VALUES
	(1,'2013-10-04 06:47:40','2013-10-10 23:09:00','Oui j\'aime le basket','Basket',144,144,NULL),
	(2,'2013-10-19 00:31:10','2013-10-19 00:31:12','C\'est une passion.\r\nEn Java, Le web framework play.','Programmation',144,144,NULL),
	(3,'2013-11-14 19:20:55','2013-11-14 19:20:55','J\'adore voyager et apprendre de nouvelles langues, m\'immerger dans de nouvelles cultures :)','Voyager dans la nature  et parler aux gens dans leur langue :)',249,249,NULL),
	(4,'2013-11-14 19:22:27','2013-11-14 19:22:27','Je suis une experte du crochet :) Je me suis fais plein de jolies robes/bikini mini-mini ^^','Passionnée de Crochet/Couture/Broderie',249,249,NULL),
	(5,'2013-11-14 19:24:07','2013-11-14 19:24:08','Méga gourmande n\'hésitez pas à m\'inviter aussi ^^ :) ','Ventre plein nègre Content  ==> J\'adore cuisiner :)',249,249,NULL);

/*!40000 ALTER TABLE `T_HOBBIES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_LANGUAGES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_LANGUAGES`;

CREATE TABLE `T_LANGUAGES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `LANGUAGE` varchar(255) DEFAULT NULL,
  `LEVEL` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK82E389D0F3D50C72` (`modifier_id`),
  KEY `FK82E389D0B2118A9E` (`visibility_id`),
  CONSTRAINT `FK82E389D0B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK82E389D0F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_LANGUAGES` WRITE;
/*!40000 ALTER TABLE `T_LANGUAGES` DISABLE KEYS */;

INSERT INTO `T_LANGUAGES` (`id`, `CREATED`, `MODIFIED`, `LANGUAGE`, `LEVEL`, `modifier_id`, `visibility_id`)
VALUES
	(1,'2013-06-29 02:53:22','2013-06-29 02:53:22','Anglais','Scolaire',144,NULL),
	(2,'2013-06-29 02:53:33','2013-06-29 02:53:33','Bambara','Maternelle',144,NULL),
	(4,'2013-09-12 17:45:41',NULL,'Anglais','Bilingue',NULL,NULL),
	(8,'2013-10-04 09:35:44','2013-10-04 09:35:46','Français','Bilingue',144,NULL),
	(9,'2013-10-04 09:42:02','2013-10-04 09:42:04','Portugais','Scolaire',144,NULL),
	(12,'2013-10-10 22:09:01',NULL,'Arabe','Fort',NULL,NULL),
	(13,'2013-11-12 19:46:37','2013-11-12 19:46:37','Espagnol','Moyen',234,NULL),
	(14,'2013-11-12 19:47:26','2013-11-12 19:47:26','Anglais','Fort',234,NULL),
	(15,'2013-11-12 19:47:50','2013-11-12 19:47:50','Français','Maternelle',234,NULL);

/*!40000 ALTER TABLE `T_LANGUAGES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_MESSAGES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_MESSAGES`;

CREATE TABLE `T_MESSAGES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CONTENT` longtext,
  `ready` bit(1) DEFAULT NULL,
  `SUBJECT` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `convers_user_id1` bigint(20) DEFAULT NULL,
  `convers_user_id2` bigint(20) DEFAULT NULL,
  `cud_dendrite_id` bigint(20) DEFAULT NULL,
  `cud_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK409940D78C2795E8` (`cud_dendrite_id`,`cud_user_id`),
  KEY `FK409940D7B2118A9E` (`visibility_id`),
  KEY `FK409940D7A7CD013E` (`author_id`),
  KEY `FK409940D7F3D50C72` (`modifier_id`),
  KEY `FK409940D72FBD0D86` (`convers_user_id1`,`convers_user_id2`),
  CONSTRAINT `FK409940D72FBD0D86` FOREIGN KEY (`convers_user_id1`, `convers_user_id2`) REFERENCES `AS_USERS_MESSAGES` (`user_id1`, `user_id2`),
  CONSTRAINT `FK409940D78C2795E8` FOREIGN KEY (`cud_dendrite_id`, `cud_user_id`) REFERENCES `AS_DENDRITES_MESSAGES` (`dendrite_id`, `user_id`),
  CONSTRAINT `FK409940D7A7CD013E` FOREIGN KEY (`author_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK409940D7B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK409940D7F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_MESSAGES` WRITE;
/*!40000 ALTER TABLE `T_MESSAGES` DISABLE KEYS */;

INSERT INTO `T_MESSAGES` (`id`, `CREATED`, `MODIFIED`, `CONTENT`, `ready`, `SUBJECT`, `modifier_id`, `visibility_id`, `author_id`, `convers_user_id1`, `convers_user_id2`, `cud_dendrite_id`, `cud_user_id`)
VALUES
	(1,'2013-10-10 23:15:49','2013-10-10 23:15:49','Je t\'écris.',00000001,'Aucun object',144,NULL,144,144,202,NULL,NULL),
	(2,'2013-10-10 23:15:49','2013-11-14 20:44:59','Je t\'écris.',00000001,'Aucun object',202,NULL,144,202,144,NULL,NULL),
	(3,'2013-10-12 22:37:47','2013-10-12 22:37:47','hey!',00000001,'Aucun object',92,NULL,92,92,144,NULL,NULL),
	(4,'2013-10-12 22:37:47','2013-10-12 22:46:29','hey!',00000001,'Aucun object',144,NULL,92,144,92,NULL,NULL),
	(5,'2013-10-12 22:37:50','2013-10-12 22:37:50','hey!',00000001,'Aucun object',92,NULL,92,92,144,NULL,NULL),
	(7,'2013-10-12 22:37:53','2013-10-12 22:37:53','hey!',00000001,'Aucun object',92,NULL,92,92,144,NULL,NULL),
	(9,'2013-10-12 22:38:12','2013-10-12 22:38:12','c\'est cool',00000001,'Aucun object',92,NULL,92,92,144,NULL,NULL),
	(10,'2013-10-12 22:38:12','2013-10-12 22:46:30','c\'est cool',00000001,'Aucun object',144,NULL,92,144,92,NULL,NULL),
	(11,'2013-10-12 22:38:15','2013-10-12 22:38:15','c\'est cool',00000001,'Aucun object',92,NULL,92,92,144,NULL,NULL),
	(13,'2013-10-12 22:40:52','2013-10-12 22:40:52','hello',00000001,'Aucun object',121,NULL,121,121,92,NULL,NULL),
	(14,'2013-10-12 22:40:52','2013-10-24 23:31:05','hello',00000001,'Aucun object',92,NULL,121,92,121,NULL,NULL),
	(15,'2013-10-12 22:46:42','2013-10-12 22:46:42','En effet',00000001,'Aucun object',144,NULL,144,144,92,NULL,NULL),
	(16,'2013-10-12 22:46:42','2013-10-24 23:31:09','En effet',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(17,'2013-10-20 23:23:24','2013-10-20 23:23:24','Hello!',00000001,'Aucun object',144,NULL,144,144,202,NULL,NULL),
	(18,'2013-10-20 23:23:24','2013-11-14 20:45:00','Hello!',00000001,'Aucun object',202,NULL,144,202,144,NULL,NULL),
	(19,'2013-10-20 23:28:16','2013-10-20 23:28:16','Casse toi pauvre con',00000001,'Aucun object',153,NULL,153,153,144,NULL,NULL),
	(20,'2013-10-20 23:28:16','2013-10-20 23:30:17','Casse toi pauvre con',00000001,'Aucun object',144,NULL,153,144,153,NULL,NULL),
	(21,'2013-10-20 23:30:31','2013-10-20 23:30:31','Un peu de politesse!',00000001,'Aucun object',144,NULL,144,144,153,NULL,NULL),
	(22,'2013-10-20 23:30:31','2013-10-20 23:34:17','Un peu de politesse!',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(23,'2013-10-20 23:34:09','2013-10-20 23:34:09','ça va mec?',00000001,'Aucun object',153,NULL,153,153,144,NULL,NULL),
	(24,'2013-10-20 23:34:09','2013-10-20 23:36:54','ça va mec?',00000001,'Aucun object',144,NULL,153,144,153,NULL,NULL),
	(25,'2013-10-20 23:34:13','2013-10-20 23:34:13','ça va mec?',00000001,'Aucun object',153,NULL,153,153,144,NULL,NULL),
	(27,'2013-10-20 23:37:19','2013-10-20 23:37:19','Non!',00000001,'Aucun object',144,NULL,144,144,153,NULL,NULL),
	(28,'2013-10-20 23:37:19','2013-10-21 18:30:14','Non!',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(30,'2013-10-20 23:43:34','2013-10-21 18:30:14','jkjsgf',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(32,'2013-10-20 23:43:44','2013-10-21 18:30:14','jkjsgf',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(34,'2013-10-20 23:44:36','2013-10-21 18:30:14','Salut',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(36,'2013-10-20 23:44:41','2013-10-21 18:30:14','Salut',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(38,'2013-10-20 23:53:48','2013-10-21 18:30:14','Un test',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(40,'2013-10-20 23:54:14','2013-10-21 18:30:14','coucou',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(42,'2013-10-21 18:25:48','2013-10-21 18:25:48','Salut petit si tu reçois ce message réponds moi vite et maintenant!',00000001,'Aucun object',144,NULL,144,144,153,NULL,NULL),
	(43,'2013-10-21 18:25:48','2013-10-21 18:30:14','Salut petit si tu reçois ce message réponds moi vite et maintenant!',00000001,'Aucun object',153,NULL,144,153,144,NULL,NULL),
	(44,'2013-10-21 18:30:31','2013-10-21 18:30:31','jevaistetuermec',00000001,'Aucun object',153,NULL,153,153,144,NULL,NULL),
	(45,'2013-10-21 18:30:31','2013-10-21 18:38:46','jevaistetuermec',00000001,'Aucun object',144,NULL,153,144,153,NULL,NULL),
	(46,'2013-10-24 15:17:31','2013-10-24 15:17:31','Salut Kadi, En fait si il n\'y a de conversation ouverte c\'est à dire si tu n\'as jamais écris à quelqu\'un et que personne ne t\'a jamais écris du coup tu ne verras pas de conversation.',00000001,'Aucun object',144,NULL,144,144,147,NULL,NULL),
	(47,'2013-10-24 15:17:31','2013-10-24 15:17:31','Salut Kadi, En fait si il n\'y a de conversation ouverte c\'est à dire si tu n\'as jamais écris à quelqu\'un et que personne ne t\'a jamais écris du coup tu ne verras pas de conversation.',00000000,'Aucun object',144,NULL,144,147,144,NULL,NULL),
	(49,'2013-10-24 17:45:12','2013-10-24 23:31:09','cvdk',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(51,'2013-10-24 17:57:08','2013-10-24 23:31:09','a\r\n',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(52,'2013-10-24 18:07:47','2013-10-24 18:07:47','Cheick\r\nSissoko\r\n\r\n',00000001,'Aucun object',144,NULL,144,144,92,NULL,NULL),
	(53,'2013-10-24 18:07:47','2013-10-24 23:31:09','Cheick\r\nSissoko\r\n\r\n',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(54,'2013-10-24 18:09:35','2013-10-24 18:09:35','Je suis un homme; Désolé Baye de t\'avoir pourri la méssagérie\r\n',00000001,'Aucun object',144,NULL,144,144,92,NULL,NULL),
	(55,'2013-10-24 18:09:35','2013-10-24 23:31:09','Je suis un homme; Désolé Baye de t\'avoir pourri la méssagérie\r\n',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(57,'2013-10-24 18:10:07','2013-10-24 23:31:09','cheic\r\n',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(58,'2013-10-24 18:11:40','2013-10-24 18:11:40','Hello\r\nComment vas tu ?\r\nOui bien et toi ?\r\nTrès bien aussi!\r\n\r\n',00000001,'Aucun object',144,NULL,144,144,92,NULL,NULL),
	(59,'2013-10-24 18:11:40','2013-10-24 23:31:09','Hello\r\nComment vas tu ?\r\nOui bien et toi ?\r\nTrès bien aussi!\r\n\r\n',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(60,'2013-10-24 18:12:37','2013-10-24 18:12:37','Hello\r\n\r\n',00000001,'Aucun object',144,NULL,144,144,92,NULL,NULL),
	(61,'2013-10-24 18:12:37','2013-10-24 23:31:09','Hello\r\n\r\n',00000001,'Aucun object',92,NULL,144,92,144,NULL,NULL),
	(66,'2013-10-25 08:15:41','2013-10-25 08:15:43','<p>Bonjour,</p>\r\n<p>La dendrite de Paris vous souhaite la beinvenue sur la CPD!!</p>',00000000,'Bienvenue!',144,NULL,144,NULL,NULL,2,144),
	(72,'2013-10-26 10:03:00','2013-10-26 10:03:00','coucou!!',00000001,'Aucun object',121,NULL,121,121,92,NULL,NULL),
	(73,'2013-10-26 10:03:00','2013-10-26 10:03:00','coucou!!',00000000,'Aucun object',121,NULL,121,92,121,NULL,NULL),
	(76,'2013-11-05 10:24:07','2013-11-05 10:24:07','Yo c\'est bon maintenant!',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(77,'2013-11-05 10:24:07','2013-11-05 10:34:45','Yo c\'est bon maintenant!',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(78,'2013-11-05 10:34:57','2013-11-05 10:34:57','bien joué gars!!\r\n',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(79,'2013-11-05 10:34:57','2013-11-05 10:36:35','bien joué gars!!\r\n',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(80,'2013-11-05 10:35:37','2013-11-05 10:35:37','Il faudrait qu\'on travaille le J-query pour ne pas recharger la page quand on fait entrer pour envoyer le message :P\r\n',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(81,'2013-11-05 10:35:37','2013-11-05 10:36:35','Il faudrait qu\'on travaille le J-query pour ne pas recharger la page quand on fait entrer pour envoyer le message :P\r\n',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(82,'2013-11-05 10:38:02','2013-11-05 10:38:02','Oui on peut le faire!\r\nEt aussi la possibilité de lier des fichiers aux events\r\nEt mettre une durée de vie pour les annonces!',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(83,'2013-11-05 10:38:02','2013-11-12 17:04:20','Oui on peut le faire!\r\nEt aussi la possibilité de lier des fichiers aux events\r\nEt mettre une durée de vie pour les annonces!',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(84,'2013-11-05 22:35:19','2013-11-05 22:35:20','Mon gars le problème vient d\'amazon,\r\nIls commencent à nous faire chier vraiment!',00000001,'Aucun object',144,NULL,144,144,123,NULL,NULL),
	(85,'2013-11-05 22:35:19','2013-11-06 20:16:49','Mon gars le problème vient d\'amazon,\r\nIls commencent à nous faire chier vraiment!',00000001,'Aucun object',123,NULL,144,123,144,NULL,NULL),
	(86,'2013-11-06 19:47:46','2013-11-06 19:47:46','Ta remarque sur l\'affichage des textes des articles on va essayer de trouver une solution.\r\nC\'est vrai que ce n\'est pas joli!',00000001,'Aucun object',144,NULL,144,144,223,NULL,NULL),
	(87,'2013-11-06 19:47:46','2013-11-07 15:13:00','Ta remarque sur l\'affichage des textes des articles on va essayer de trouver une solution.\r\nC\'est vrai que ce n\'est pas joli!',00000001,'Aucun object',223,NULL,144,223,144,NULL,NULL),
	(88,'2013-11-07 15:13:09','2013-11-07 15:13:09','ok :)',00000001,'Aucun object',223,NULL,223,223,144,NULL,NULL),
	(89,'2013-11-07 15:13:09','2013-11-09 00:28:19','ok :)',00000001,'Aucun object',144,NULL,223,144,223,NULL,NULL),
	(90,'2013-11-12 17:04:47','2013-11-12 17:04:47','et pourquoi les choses ont l\'air applatit?',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(91,'2013-11-12 17:04:47','2013-11-12 17:09:20','et pourquoi les choses ont l\'air applatit?',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(92,'2013-11-12 17:05:23','2013-11-12 17:05:23','est qu\'on peut augmenter la longeur des photos pour pas que ca soit carré??',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(93,'2013-11-12 17:05:23','2013-11-12 17:09:20','est qu\'on peut augmenter la longeur des photos pour pas que ca soit carré??',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(94,'2013-11-12 17:10:38','2013-11-12 17:10:38','Les choses ?\r\nLesquelles ?\r\nPour la dimension des photos il faut qu\'on y réfléchisse c\'est possible mais j\'ai pris le code de Sagara et l\'adapter en fait! ',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(95,'2013-11-12 17:10:38','2013-11-12 17:10:57','Les choses ?\r\nLesquelles ?\r\nPour la dimension des photos il faut qu\'on y réfléchisse c\'est possible mais j\'ai pris le code de Sagara et l\'adapter en fait! ',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(96,'2013-11-12 17:11:51','2013-11-12 17:11:51','Genre les commentaires, j\'ai l\'impression que ca a l air applatit, peut etre qu\'il faudrait qu\'on regle la longueur en function de la taille de l\'ecran :D\r\n',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(97,'2013-11-12 17:11:51','2013-11-12 17:16:34','Genre les commentaires, j\'ai l\'impression que ca a l air applatit, peut etre qu\'il faudrait qu\'on regle la longueur en function de la taille de l\'ecran :D\r\n',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(98,'2013-11-12 17:13:05','2013-11-12 17:13:05','Aussi l\'apparence des messages dans la liste des messages..',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(99,'2013-11-12 17:13:05','2013-11-12 17:16:34','Aussi l\'apparence des messages dans la liste des messages..',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(100,'2013-11-12 17:17:17','2013-11-12 17:17:17','Ah je vois oui on peut changer le style',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(101,'2013-11-12 17:17:17','2013-11-14 03:48:44','Ah je vois oui on peut changer le style',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(102,'2013-11-13 05:38:55','2013-11-13 05:38:55','Coucou Kady, pour les fautes d\'orthographe est ce que tu peux spécifier la page comme ça, ça sera plus facile!',00000001,'Aucun object',144,NULL,144,144,223,NULL,NULL),
	(103,'2013-11-13 05:38:55','2013-11-15 16:07:35','Coucou Kady, pour les fautes d\'orthographe est ce que tu peux spécifier la page comme ça, ça sera plus facile!',00000001,'Aucun object',223,NULL,144,223,144,NULL,NULL),
	(104,'2013-11-13 11:13:03','2013-11-13 11:13:03','Salut Elay,\r\nSi tu as le temps fais un update et teste les modifications que j\'ai effectué\r\nJe trouve que ça peut être intéressant.',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(105,'2013-11-13 11:13:03','2013-11-14 03:48:44','Salut Elay,\r\nSi tu as le temps fais un update et teste les modifications que j\'ai effectué\r\nJe trouve que ça peut être intéressant.',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(106,'2013-11-13 11:14:57','2013-11-13 11:14:57','Je vais voir le problème tout à l\'heure.',00000001,'Aucun object',144,NULL,144,144,241,NULL,NULL),
	(107,'2013-11-13 11:14:57','2013-11-13 22:35:54','Je vais voir le problème tout à l\'heure.',00000001,'Aucun object',241,NULL,144,241,144,NULL,NULL),
	(108,'2013-11-13 11:17:08','2013-11-13 11:17:08','Tu ne dois pas en théorie avoir de problème.\r\nIl ne faut pas toucher au champ mot de passe si tu ne veux pas le modifier.',00000001,'Aucun object',144,NULL,144,144,241,NULL,NULL),
	(109,'2013-11-13 11:17:08','2013-11-13 22:35:54','Tu ne dois pas en théorie avoir de problème.\r\nIl ne faut pas toucher au champ mot de passe si tu ne veux pas le modifier.',00000001,'Aucun object',241,NULL,144,241,144,NULL,NULL),
	(110,'2013-11-13 11:42:57','2013-11-13 11:42:57','C\'est bon j\'ai mis de l\'ordre dans les informations!',00000001,'Aucun object',144,NULL,144,144,241,NULL,NULL),
	(111,'2013-11-13 11:42:57','2013-11-13 22:35:54','C\'est bon j\'ai mis de l\'ordre dans les informations!',00000001,'Aucun object',241,NULL,144,241,144,NULL,NULL),
	(112,'2013-11-13 22:36:59','2013-11-13 22:36:59','Merci beaucoup Cheick. tout est OK :)',00000001,'Aucun object',241,NULL,241,241,144,NULL,NULL),
	(113,'2013-11-13 22:36:59','2013-11-13 22:47:17','Merci beaucoup Cheick. tout est OK :)',00000001,'Aucun object',144,NULL,241,144,241,NULL,NULL),
	(114,'2013-11-14 03:49:34','2013-11-14 03:49:35','Cheick!! :D Je trouve que c\'est quand meme dure de retrouver la Cellule dans le menu en dessous d\'utilisateur donc je vais l\'ajouter dans le menu en haut...\r\n\r\n',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(115,'2013-11-14 03:49:34','2013-11-14 06:08:30','Cheick!! :D Je trouve que c\'est quand meme dure de retrouver la Cellule dans le menu en dessous d\'utilisateur donc je vais l\'ajouter dans le menu en haut...\r\n\r\n',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(116,'2013-11-14 06:11:13','2013-11-14 06:11:14','Oui je vois tu as raison!\r\nJ\'ai moi qui l\'ai mis là-bas Sagara l\'avait enlever complètement :) ',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(117,'2013-11-14 06:11:13','2013-11-15 17:06:10','Oui je vois tu as raison!\r\nJ\'ai moi qui l\'ai mis là-bas Sagara l\'avait enlever complètement :) ',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(118,'2013-11-14 20:45:17','2013-11-14 20:45:17','je te reponds',00000001,'Aucun object',202,NULL,202,202,144,NULL,NULL),
	(119,'2013-11-14 20:45:17','2013-11-14 21:10:22','je te reponds',00000001,'Aucun object',144,NULL,202,144,202,NULL,NULL),
	(120,'2013-11-14 21:10:34','2013-11-14 21:10:34','lol',00000001,'Aucun object',144,NULL,144,144,202,NULL,NULL),
	(121,'2013-11-14 21:10:34','2013-11-14 21:10:34','lol',00000000,'Aucun object',144,NULL,144,202,144,NULL,NULL),
	(122,'2013-11-15 17:07:03','2013-11-15 17:07:03','ahh dans ce cas il faudrait qu\'on en discute alors!! \r\n',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(123,'2013-11-15 17:07:03','2013-11-15 08:27:09','ahh dans ce cas il faudrait qu\'on en discute alors!! \r\n',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(124,'2013-11-15 17:08:25','2013-11-15 17:08:25','faudrait qu\'on essaye de faire 1 deuxieme meeting skype alors!!',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(125,'2013-11-15 17:08:25','2013-11-15 08:27:09','faudrait qu\'on essaye de faire 1 deuxieme meeting skype alors!!',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(126,'2013-11-15 08:27:56','2013-11-15 08:27:56','Oui on peut le faire ce week-end ?\r\nTu organises ?',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(127,'2013-11-15 08:27:56','2013-11-15 08:50:03','Oui on peut le faire ce week-end ?\r\nTu organises ?',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(128,'2013-11-15 08:28:53','2013-11-15 08:28:53','Comment ça se fait que ton message n\'a pas de date ?',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(129,'2013-11-15 08:28:53','2013-11-15 08:50:03','Comment ça se fait que ton message n\'a pas de date ?',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(130,'2013-11-15 08:50:48','2013-11-15 08:50:49','Looll!!! je l\'ai fait depuis CPD de dev qui est branche sur la BD de prod :D\r\n',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(131,'2013-11-15 08:50:48','2013-11-15 08:52:20','Looll!!! je l\'ai fait depuis CPD de dev qui est branche sur la BD de prod :D\r\n',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(132,'2013-11-15 08:52:19','2013-11-15 08:52:19','Mais ça n\'explique pas le fait qu\'il n\'y a pas de date.\r\nOu bien ton template n\'est pas à jour!',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(133,'2013-11-15 08:52:19','2013-11-15 18:05:58','Mais ça n\'explique pas le fait qu\'il n\'y a pas de date.\r\nOu bien ton template n\'est pas à jour!',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(134,'2013-11-15 18:07:34','2013-11-15 18:07:34','il est forcement plus a jour que celui qui est sur le site, donc je ne sais pas non plus!! Et la date est revenue je pense!',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(135,'2013-11-15 18:07:34','2013-11-15 09:21:38','il est forcement plus a jour que celui qui est sur le site, donc je ne sais pas non plus!! Et la date est revenue je pense!',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(136,'2013-11-15 18:08:41','2013-11-15 18:08:41','Loll il est un peu en couille je pense a cause du Decalage horaire aussi, il me met que ton message c\'etait il y a 9h!!!',00000001,'Aucun object',121,NULL,121,121,144,NULL,NULL),
	(137,'2013-11-15 18:08:41','2013-11-15 09:21:38','Loll il est un peu en couille je pense a cause du Decalage horaire aussi, il me met que ton message c\'etait il y a 9h!!!',00000001,'Aucun object',144,NULL,121,144,121,NULL,NULL),
	(138,'2013-11-15 09:22:34','2013-11-15 09:22:34','Lol',00000001,'Aucun object',144,NULL,144,144,121,NULL,NULL),
	(139,'2013-11-15 09:22:34','2013-11-27 14:04:34','Lol',00000001,'Aucun object',121,NULL,144,121,144,NULL,NULL),
	(140,'2013-11-15 12:58:27','2013-11-15 12:58:27','C\'est bon Rehana tu es à la bonne promotion maintenant!',00000001,'Aucun object',144,NULL,144,144,256,NULL,NULL),
	(141,'2013-11-15 12:58:27','2013-11-15 12:58:27','C\'est bon Rehana tu es à la bonne promotion maintenant!',00000000,'Aucun object',144,NULL,144,256,144,NULL,NULL),
	(142,'2013-11-15 16:08:34','2013-11-15 16:08:34','les fautes c\'est la premiere page acceuil dans le texte qui defile!!!!',00000001,'Aucun object',223,NULL,223,223,144,NULL,NULL),
	(143,'2013-11-15 16:08:34','2013-11-16 11:10:18','les fautes c\'est la premiere page acceuil dans le texte qui defile!!!!',00000001,'Aucun object',144,NULL,223,144,223,NULL,NULL),
	(144,'2013-11-16 11:10:52','2013-11-16 11:10:52','Oui c\'est vrai ça sera corrigé!',00000001,'Aucun object',144,NULL,144,144,223,NULL,NULL),
	(145,'2013-11-16 11:10:52','2013-11-16 11:10:52','Oui c\'est vrai ça sera corrigé!',00000000,'Aucun object',144,NULL,144,223,144,NULL,NULL),
	(146,'2013-11-16 21:45:51','2013-11-16 21:45:51','Salut!',00000001,'Aucun object',144,NULL,144,144,254,NULL,NULL),
	(147,'2013-11-16 21:45:51','2013-11-16 21:45:51','Salut!',00000000,'Aucun object',144,NULL,144,254,144,NULL,NULL),
	(148,'2013-11-16 21:56:34','2013-11-16 21:56:34','On abuse de la faille d\'un site pour mettre des promo négatives.',00000001,'Aucun object',144,NULL,144,144,251,NULL,NULL),
	(149,'2013-11-16 21:56:34','2013-11-17 12:05:08','On abuse de la faille d\'un site pour mettre des promo négatives.',00000001,'Aucun object',251,NULL,144,251,144,NULL,NULL),
	(150,'2013-11-17 01:15:58','2013-11-17 01:15:58','Bien venu Fatim sur CPD!',00000001,'Aucun object',144,NULL,144,144,259,NULL,NULL),
	(151,'2013-11-17 01:15:58','2013-11-17 01:15:58','Bien venu Fatim sur CPD!',00000000,'Aucun object',144,NULL,144,259,144,NULL,NULL),
	(152,'2013-11-17 12:05:37','2013-11-17 12:05:37','lol bien vu !! ',00000001,'Aucun object',251,NULL,251,251,144,NULL,NULL),
	(153,'2013-11-17 12:05:37','2013-11-19 09:31:12','lol bien vu !! ',00000001,'Aucun object',144,NULL,251,144,251,NULL,NULL),
	(154,'2013-11-19 09:32:28','2013-11-19 09:32:28','Mais ne t\'inquiète pas on va tout corriger au prochain déploiement.\r\nÇa se passe bien les cours ?',00000001,'Aucun object',144,NULL,144,144,251,NULL,NULL),
	(155,'2013-11-19 09:32:28','2013-11-19 22:52:30','Mais ne t\'inquiète pas on va tout corriger au prochain déploiement.\r\nÇa se passe bien les cours ?',00000001,'Aucun object',251,NULL,144,251,144,NULL,NULL),
	(156,'2013-11-19 22:54:47','2013-11-19 22:54:47','ça va ! je trouve qu\'il faut des personnes pour compléter les rubriques du site \r\n',00000001,'Aucun object',251,NULL,251,251,144,NULL,NULL),
	(157,'2013-11-19 22:54:47','2013-11-23 18:38:09','ça va ! je trouve qu\'il faut des personnes pour compléter les rubriques du site \r\n',00000001,'Aucun object',144,NULL,251,144,251,NULL,NULL),
	(158,'2013-11-23 18:38:48','2013-11-23 18:38:48','Des rubriques comme quoi par exemple ?',00000001,'Aucun object',144,NULL,144,144,251,NULL,NULL),
	(159,'2013-11-23 18:38:48','2013-11-23 18:38:48','Des rubriques comme quoi par exemple ?',00000000,'Aucun object',144,NULL,144,251,144,NULL,NULL);

/*!40000 ALTER TABLE `T_MESSAGES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_PHOTOS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_PHOTOS`;

CREATE TABLE `T_PHOTOS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  `announce_id` bigint(20) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK43F232CCF3D50C72` (`modifier_id`),
  KEY `FK43F232CCB2118A9E` (`visibility_id`),
  KEY `FK43F232CC239D0E2D` (`article_id`),
  KEY `FK43F232CC81DE9749` (`event_id`),
  KEY `FK43F232CC47140EFE` (`user_id`),
  KEY `FK43F232CC52926B47` (`announce_id`),
  CONSTRAINT `FK43F232CC52926B47` FOREIGN KEY (`announce_id`) REFERENCES `T_ANNOUNCES` (`id`),
  CONSTRAINT `FK43F232CC239D0E2D` FOREIGN KEY (`article_id`) REFERENCES `T_ARTICLES` (`id`),
  CONSTRAINT `FK43F232CC47140EFE` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK43F232CC81DE9749` FOREIGN KEY (`event_id`) REFERENCES `T_EVENTS` (`id`),
  CONSTRAINT `FK43F232CCB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK43F232CCF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_PHOTOS` WRITE;
/*!40000 ALTER TABLE `T_PHOTOS` DISABLE KEYS */;

INSERT INTO `T_PHOTOS` (`id`, `CREATED`, `MODIFIED`, `DESCRIPTION`, `TITLE`, `URL`, `modifier_id`, `visibility_id`, `announce_id`, `article_id`, `event_id`, `user_id`)
VALUES
	(1,'2013-06-29 02:41:11',NULL,NULL,'90e66d3b-f85f-42ec-a7a0-8085573861f1IMG_0193.PNG','https://s3.amazonaws.com/cpd300/90e66d3b-f85f-42ec-a7a0-8085573861f1IMG_0193.PNG',NULL,NULL,NULL,NULL,NULL,NULL),
	(2,'2013-06-29 02:41:11',NULL,NULL,'b4ee91d5-e6e9-4042-a508-788b348a012celay.jpg','https://s3.amazonaws.com/cpd300/b4ee91d5-e6e9-4042-a508-788b348a012celay.jpg',NULL,NULL,NULL,NULL,NULL,NULL),
	(5,'2013-09-13 23:54:40','2013-09-13 23:54:41','','9d7215c4-1ea3-4686-aeb6-cefc67165b2fle-role-de-jessica-alba-est-encore-inconnu.jpg','https://s3.amazonaws.com/cpd300/9d7215c4-1ea3-4686-aeb6-cefc67165b2fle-role-de-jessica-alba-est-encore-inconnu.jpg',121,NULL,NULL,NULL,NULL,NULL),
	(8,'2013-10-15 12:03:53','2013-10-15 12:03:56','','4c4f018f-382f-4a20-8eac-c38edcb6aa6e196276_1300790377787_822651_n.jpg','https://s3.amazonaws.com/cpd300/4c4f018f-382f-4a20-8eac-c38edcb6aa6e196276_1300790377787_822651_n.jpg',149,NULL,NULL,NULL,NULL,NULL),
	(9,'2013-10-19 00:15:20','2013-10-19 00:15:34','La dendrite de Paris est la meilleure.','94abeb8f-032e-4deb-9ebc-7a507c502194paris.jpg','https://s3.amazonaws.com/cpd300/94abeb8f-032e-4deb-9ebc-7a507c502194paris.jpg',144,NULL,NULL,NULL,NULL,NULL),
	(10,'2013-10-19 00:16:36','2013-10-19 00:16:37','Grenoble','08d255ee-07ff-44c0-9a7e-90e443a5c979grenoble.jpg','https://s3.amazonaws.com/cpd300/08d255ee-07ff-44c0-9a7e-90e443a5c979grenoble.jpg',144,NULL,NULL,NULL,NULL,NULL),
	(11,'2013-10-19 00:18:05','2013-10-19 00:18:07','','f699e801-14a9-433f-ab3e-3350a3f7e09dvalence.jpg','https://s3.amazonaws.com/cpd300/f699e801-14a9-433f-ab3e-3350a3f7e09dvalence.jpg',144,NULL,NULL,NULL,NULL,NULL),
	(12,'2013-10-19 00:19:21','2013-10-19 00:19:23','','fe595b69-6e1c-42a5-9465-719f272f3359angers.jpg','https://s3.amazonaws.com/cpd300/fe595b69-6e1c-42a5-9465-719f272f3359angers.jpg',144,NULL,NULL,NULL,NULL,NULL),
	(13,'2013-10-19 00:20:05','2013-10-19 00:20:06','','0206ef26-f6bb-46e3-96ce-cc6b46316ee3lyon.jpg','https://s3.amazonaws.com/cpd300/0206ef26-f6bb-46e3-96ce-cc6b46316ee3lyon.jpg',144,NULL,NULL,NULL,NULL,NULL),
	(14,'2013-10-19 00:20:58','2013-10-19 00:21:00','','7d23a212-05b8-4179-a2e3-197b709b264fmali.jpg','https://s3.amazonaws.com/cpd300/7d23a212-05b8-4179-a2e3-197b709b264fmali.jpg',144,NULL,NULL,NULL,NULL,NULL),
	(17,'2013-10-20 00:46:03','2013-10-20 00:46:04','Ma photo de profil est coolllllll!!!!!!','8f27a69e-2035-453a-8008-6d158e34d02cCheick.JPG','https://s3.amazonaws.com/cpd300/8f27a69e-2035-453a-8008-6d158e34d02cCheick.JPG',144,NULL,NULL,NULL,NULL,NULL),
	(19,'2013-10-28 13:57:42','2013-10-28 13:57:52','','7d394bb4-644e-4d82-9b5c-1f61dc3742351278165_526229044129568_1920460937_o.jpg','https://s3.amazonaws.com/cpd300/7d394bb4-644e-4d82-9b5c-1f61dc3742351278165_526229044129568_1920460937_o.jpg',222,NULL,NULL,NULL,NULL,NULL),
	(20,'2013-11-05 19:32:47','2013-11-05 19:32:49','','dacbba83-1d97-42d5-93e6-a93df6a98423IMG_2000.JPG','https://s3.amazonaws.com/cpd300/dacbba83-1d97-42d5-93e6-a93df6a98423IMG_2000.JPG',223,NULL,NULL,NULL,NULL,NULL),
	(21,'2013-11-05 20:34:31','2013-11-05 20:34:32','','63fe6e5a-3f3b-4446-bd45-bc230d8eb230profil.jpg','https://s3.amazonaws.com/cpd300/63fe6e5a-3f3b-4446-bd45-bc230d8eb230profil.jpg',213,NULL,NULL,NULL,NULL,NULL),
	(22,'2013-11-05 20:45:48','2013-11-05 20:45:50','','aa31e26f-8a7a-4f56-9932-0fc9daf77baf1011457_530781393625446_1404650990_n.jpg','https://s3.amazonaws.com/cpd300/aa31e26f-8a7a-4f56-9932-0fc9daf77baf1011457_530781393625446_1404650990_n.jpg',221,NULL,NULL,NULL,NULL,NULL),
	(24,'2013-11-05 21:24:49','2013-11-05 21:24:49','','24b81d04-4ceb-405b-b52f-971a628c5cb8DSC02821.JPG','https://s3.amazonaws.com/cpd300/24b81d04-4ceb-405b-b52f-971a628c5cb8DSC02821.JPG',123,NULL,NULL,NULL,NULL,NULL),
	(25,'2013-11-12 23:15:39','2013-11-12 23:15:39','Une petite description','ident.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(38,'2013-11-13 20:33:10','2013-11-13 20:33:12','','e6ed2abd-c997-442f-b07f-7147eceb6ce61380620_655972674434318_1317997705_n.jpg','https://s3.amazonaws.com/cpd300/e6ed2abd-c997-442f-b07f-7147eceb6ce61380620_655972674434318_1317997705_n.jpg',243,NULL,NULL,NULL,NULL,NULL),
	(39,'2013-11-13 21:01:28','2013-11-13 21:01:28','Une petite description','55aa5286-71c2-4401-a118-915e477688f820130906_124829 (2).jpg','https://s3.amazonaws.com/cpd300/55aa5286-71c2-4401-a118-915e477688f820130906_124829 (2).jpg',236,NULL,NULL,NULL,NULL,NULL),
	(40,'2013-11-14 17:33:48','2013-11-14 17:33:49','','143734a5-111c-45c0-90f8-7ec41c908c71citation-image-couverture-facebook 1.jpg','https://s3.amazonaws.com/cpd300/143734a5-111c-45c0-90f8-7ec41c908c71citation-image-couverture-facebook 1.jpg',233,NULL,NULL,NULL,NULL,NULL),
	(41,'2013-11-14 18:06:25','2013-11-14 18:06:27','','3babbdac-2c8c-47af-bb4f-bc8f15fe941520130922_114958.jpg','https://s3.amazonaws.com/cpd300/3babbdac-2c8c-47af-bb4f-bc8f15fe941520130922_114958.jpg',248,NULL,NULL,NULL,NULL,NULL),
	(42,'2013-11-14 19:11:34','2013-11-14 19:11:36','Hello Happy Peeps :) Je serai super contente d\'aider mes nouveaux et nouvelles sous quelque forme que ce soit :D Cheeerrrrss !!!','943a06a4-9570-40dc-b6c3-e608a7486d25282980_487675724617364_292554956_n.jpg','https://s3.amazonaws.com/cpd300/943a06a4-9570-40dc-b6c3-e608a7486d25282980_487675724617364_292554956_n.jpg',249,NULL,NULL,NULL,NULL,NULL),
	(44,'2013-11-14 20:09:30','2013-11-14 20:09:31','','7dcfd051-c3f5-447a-9157-0a5b61a879101017630_10151636348557752_816444371_n_2.jpg','https://s3.amazonaws.com/cpd300/7dcfd051-c3f5-447a-9157-0a5b61a879101017630_10151636348557752_816444371_n_2.jpg',251,NULL,NULL,NULL,NULL,NULL),
	(46,'2013-11-14 20:53:34','2013-11-14 20:53:34','','986c801b-eb8c-4ab9-affc-ba67776838c22012-04-01 17.55.38-2.jpg','https://s3.amazonaws.com/cpd300/986c801b-eb8c-4ab9-affc-ba67776838c22012-04-01 17.55.38-2.jpg',253,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `T_PHOTOS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_POSTES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_POSTES`;

CREATE TABLE `T_POSTES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `GENERAL` bit(1) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4456A799F3D50C72` (`modifier_id`),
  KEY `FK4456A799B2118A9E` (`visibility_id`),
  CONSTRAINT `FK4456A799B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK4456A799F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_POSTES` WRITE;
/*!40000 ALTER TABLE `T_POSTES` DISABLE KEYS */;

INSERT INTO `T_POSTES` (`id`, `CREATED`, `MODIFIED`, `CODE`, `DESCRIPTION`, `GENERAL`, `TITLE`, `modifier_id`, `visibility_id`)
VALUES
	(1,'2013-06-29 02:41:11','2013-09-13 17:45:12','bureau_prez','Laisser au soin des gens.\r\nPour faire ce qu\'il faut.',00000001,'Président',144,NULL),
	(2,'2013-06-29 02:41:11','2013-10-10 21:49:30','bureau_vp_exec','Laisser au soin des gens.',00000001,'Vice Président',144,NULL),
	(3,'2013-06-29 02:41:11',NULL,'trez','Laisser au soin des gens.',00000000,'Vice Président à la Trésorie et aux finances',NULL,NULL),
	(4,'2013-06-29 02:41:11',NULL,'bureau_vp_com','Laisser au soin des gens.',00000001,'Vice Président à la Communication',NULL,NULL),
	(5,'2013-06-29 02:41:11',NULL,'bureau_vp_admin','Laisser au soin des gens.',00000001,'Vice Président Administratif',NULL,NULL),
	(6,'2013-06-29 02:41:11',NULL,'bureau_vp_aap','Laisser au soin des gens.',00000001,'Vice Président Activités Académiques et Pédagogiques',NULL,NULL),
	(7,'2013-06-29 02:41:11',NULL,'bureau_vp_as','Laisser au soin des gens.',00000001,'Vice Président Affaires Socioculturelles',NULL,NULL),
	(8,'2013-06-29 02:41:11','2013-09-19 12:34:33','prez','Laisser au soin des gens.',00000000,'Président',144,NULL),
	(9,'2013-06-29 02:41:11',NULL,'vp_exec','Laisser au soin des gens.',00000000,'Vice Président Exécutif',NULL,NULL),
	(10,'2013-06-29 02:41:11',NULL,'vp_com','Laisser au soin des gens.',00000000,'Vice Président à la Communication',NULL,NULL),
	(11,'2013-06-29 02:41:11',NULL,'vp_admin','Laisser au soin des gens.',00000000,'Vice Président Administratif',NULL,NULL),
	(12,'2013-06-29 02:41:11',NULL,'vp_aap','Laisser au soin des gens.',00000000,'Vice Président Activités Académiques et Pédagogiques',NULL,NULL),
	(13,'2013-06-29 02:41:11',NULL,'vp_as','Laisser au soin des gens.',00000000,'Vice Président Affaires Socioculturelles',NULL,NULL);

/*!40000 ALTER TABLE `T_POSTES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_RIGHTS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_RIGHTS`;

CREATE TABLE `T_RIGHTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`CODE`),
  KEY `FK47662C22B2118A9E` (`visibility_id`),
  KEY `FK47662C22F3D50C72` (`modifier_id`),
  CONSTRAINT `FK47662C22B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK47662C22F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_RIGHTS` WRITE;
/*!40000 ALTER TABLE `T_RIGHTS` DISABLE KEYS */;

INSERT INTO `T_RIGHTS` (`id`, `CREATED`, `MODIFIED`, `CODE`, `DESCRIPTION`, `modifier_id`, `visibility_id`)
VALUES
	(1,'2013-06-29 02:41:10',NULL,'EDIT_USER','Permet la modification des utilisateurs.',NULL,NULL),
	(2,'2013-06-29 02:41:10',NULL,'DELETE_USER','Permet la suppression des utilisateurs.',NULL,NULL),
	(3,'2013-06-29 02:41:10',NULL,'CREATE_USER','Permet la création des utilisateurs.',NULL,NULL),
	(4,'2013-06-29 02:41:10',NULL,'CREATE_ROLE','Permet la création des rôles.',NULL,NULL),
	(5,'2013-06-29 02:41:10',NULL,'EDIT_ROLE','Permet la modification des rôles.',NULL,NULL),
	(6,'2013-06-29 02:41:10',NULL,'DELETE_ROLE','Permet la suppression des rôles.',NULL,NULL),
	(7,'2013-06-29 02:41:10',NULL,'CREATE_DENDRITE','Permet la création des dendrites.',NULL,NULL),
	(8,'2013-06-29 02:41:10',NULL,'EDIT_DENDRITE','Permet la modification des dendrites.',NULL,NULL),
	(9,'2013-06-29 02:41:10',NULL,'DELETE_DENDRITE','Permet la suppression des dendrites.',NULL,NULL),
	(10,'2013-06-29 02:41:10',NULL,'REMOVE_ROLE_FROM_USER','Permet enlève un rôle d\'un utilisateur.',NULL,NULL),
	(11,'2013-06-29 02:41:10',NULL,'ADD_ROLE_TO_USER','Permet l\'ajout d\'un rôle d\'un utilisateur.',NULL,NULL),
	(12,'2013-06-29 02:41:10',NULL,'REMOVE_RIGHT_FROM_ROLE','Permet enlève un droit d\'un rôle.',NULL,NULL),
	(13,'2013-06-29 02:41:10',NULL,'ADD_RIGHT_TO_ROLE','Permet l\'ajout d\'un rôle d\'un utilisateur.',NULL,NULL),
	(14,'2013-06-29 02:41:10',NULL,'REMOVE_USER_FROM_DENDRITE','Permet enlève un utilisateur d\'une dendrite.',NULL,NULL),
	(15,'2013-06-29 02:41:10',NULL,'ADD_USER_TO_DENDRITE','Permet l\'ajout d\'un utilsateur dans une dendrite.',NULL,NULL),
	(16,'2013-06-29 02:41:10',NULL,'REMOVE_USER_FROM_MY_DENDRITE','Permet enlève un utilisateur de sa dendrite.',NULL,NULL),
	(17,'2013-06-29 02:41:10',NULL,'ADD_USER_TO_MY_DENDRITE','Permet l\'ajout d\'un utilsateur dans sa dendrite.',NULL,NULL),
	(18,'2013-06-29 02:41:10',NULL,'ADD_AND_REMOVE_ROLE_USER','L\'ajout et suppression d\'un rôle de l\'utilisateur.',NULL,NULL),
	(19,'2013-06-29 02:41:10',NULL,'ADD_AND_REMOVE_RIGHT_ROLE','L\'ajout et suppression d\'un droit d\'un rôle.',NULL,NULL),
	(20,'2013-06-29 02:41:10',NULL,'EDIT_EVENT','Permet la modification des évènements.',NULL,NULL),
	(21,'2013-06-29 02:41:11',NULL,'DELETE_EVENT','Permet la suppression des évènements.',NULL,NULL),
	(22,'2013-06-29 02:41:11',NULL,'EDIT_MY_EVENT','Permet la modification des évènements de sa dendrite.',NULL,NULL),
	(23,'2013-06-29 02:41:11',NULL,'DELETE_MY_EVENT','Permet la suppression des évènements de sa dendrite.',NULL,NULL),
	(24,'2013-06-29 02:41:11',NULL,'EDIT_ARTICLE','Permet la modification des articles.',NULL,NULL),
	(25,'2013-06-29 02:41:11',NULL,'DELETE_ARTICLE','Permet la suppression des articles.',NULL,NULL),
	(26,'2013-06-29 02:41:11',NULL,'EDIT_MY_ARTICLE','Permet la modification des articles de sa dendrite.',NULL,NULL),
	(27,'2013-06-29 02:41:11',NULL,'DELETE_MY_ARTICLE','Permet la suppression des articles de sa dendrite.',NULL,NULL),
	(28,'2013-06-29 02:41:11',NULL,'EDIT_ANNOUNCE','Permet la modification des announces.',NULL,NULL),
	(29,'2013-06-29 02:41:11',NULL,'DELETE_ANNOUNCE','Permet la suppression des announces.',NULL,NULL),
	(30,'2013-06-29 02:41:11',NULL,'EDIT_MY_ANNOUNCE','Permet la modification des announces.',NULL,NULL),
	(31,'2013-06-29 02:41:11',NULL,'DELETE_MY_ANNOUNCE','Permet la suppression des announces de la dendrite de l\'utilisateur.',NULL,NULL),
	(32,'2013-06-29 02:41:11',NULL,'ACCESS_ADMIN','Permet l\'accès à l\'administration.',NULL,NULL),
	(33,'2013-06-29 02:41:11',NULL,'CREATE_ELECTION','Permet de créer une élection.',NULL,NULL),
	(34,'2013-06-29 02:41:11',NULL,'EDIT_ELECTION','Permet de modifier une élection.',NULL,NULL),
	(35,'2013-06-29 02:41:11',NULL,'DELETE_ELECTION','Permet de supprimer une élection.',NULL,NULL),
	(36,'2013-06-29 02:41:11',NULL,'CREATE_CANDIDATURE','Permet de créer une candidature.',NULL,NULL),
	(37,'2013-06-29 02:41:11',NULL,'CREATE_MY_CANDIDATURE','Permet de créer une candidature dans sa dendrite.',NULL,NULL),
	(38,'2013-06-29 02:41:11',NULL,'EDIT_CANDIDATURE','Permet de modifier une candidature.',NULL,NULL),
	(39,'2013-06-29 02:41:11',NULL,'EDIT_MY_CANDIDATURE','Permet de modifier une candidature dans sa dendrite.',NULL,NULL),
	(40,'2013-06-29 02:41:11',NULL,'DELETE_CANDIDATURE','Permet de supprimer une candidature.',NULL,NULL),
	(41,'2013-06-29 02:41:11',NULL,'EDIT_MY_ELECTION','Permet de modifier l\'élection de la dendrite de l\'utilisateur.',NULL,NULL),
	(42,'2013-06-29 02:41:11',NULL,'CREATE_MY_ELECTION','Permet de créer l\'élection de la dendrite de l\'utilisateur.',NULL,NULL),
	(43,'2013-06-29 02:41:11',NULL,'DELETE_MY_ELECTION','Permet de supprimer une élection dans sa dendrite.',NULL,NULL),
	(44,'2013-06-29 02:41:11',NULL,'ADD_AND_REMOVE_USER_TO_DENDRITE','Permet d\'ajouter ou d\'enléver un utilisateur d\'une dendrite.',NULL,NULL),
	(45,'2013-06-29 02:41:11',NULL,'ADD_AND_REMOVE_POSTE_TO_DENDRITE','Permet d\'ajouter ou d\'enléver un poste d\'une dendrite.',NULL,NULL),
	(46,'2013-06-29 02:41:11',NULL,'ADD_AND_REMOVE_ROLE_TO_POSTE','Permet d\'ajouter ou d\'enléver un rôle d\'un poste.',NULL,NULL),
	(47,'2013-06-29 02:41:11',NULL,'DELETE_POSTE','Permet de supprimer un poste.',NULL,NULL),
	(48,'2013-06-29 02:41:11',NULL,'EDIT_POSTE','Permet de modifier un poste.',NULL,NULL),
	(49,'2013-09-17 02:38:40','2013-09-17 02:38:40','CREATE_EVENT',NULL,NULL,NULL),
	(50,'2013-09-17 02:38:41','2013-09-17 02:38:41','CREATE_RIGHT',NULL,NULL,NULL),
	(51,'2013-09-17 02:38:41','2013-09-17 02:38:41','CREATE_MY_EVENT',NULL,NULL,NULL),
	(52,'2013-09-17 02:38:41','2013-09-17 02:38:41','EDIT_MY_DENDRITE',NULL,NULL,NULL),
	(53,'2013-09-17 02:38:41','2013-09-17 02:38:41','EDIT_MY_USER',NULL,NULL,NULL),
	(54,'2013-09-17 02:38:41','2013-09-17 02:38:41','EDIT_MY_POSTE',NULL,NULL,NULL),
	(55,'2013-09-17 02:38:41','2013-09-17 02:38:41','ENABLE_DESABLE_USER',NULL,NULL,NULL),
	(56,'2013-10-10 21:43:42','2013-10-10 21:43:42','SEND_MESSAGE',NULL,144,NULL),
	(57,'2013-10-10 21:43:42','2013-10-10 21:43:42','SEND_MY_MESSAGE',NULL,144,NULL),
	(58,'2013-10-10 21:43:42','2013-10-10 21:43:43','EDIT_FEEDBACK',NULL,144,NULL),
	(59,'2013-10-10 21:43:42','2013-10-10 21:43:43','EDIT_MY_FEEDBACK',NULL,144,NULL),
	(60,'2013-10-10 21:43:42','2013-10-10 21:43:43','DELETE_FEEDBACK',NULL,144,NULL),
	(61,'2013-10-10 21:43:42','2013-10-10 21:43:44','DELETE_MY_FEEDBACK',NULL,144,NULL),
	(62,'2013-11-29 02:25:30','2013-11-29 02:25:30','ADD_AND_REMOVE_POSTE_TO_USER',NULL,144,NULL);

/*!40000 ALTER TABLE `T_RIGHTS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_ROLES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_ROLES`;

CREATE TABLE `T_ROLES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`CODE`),
  KEY `FK2506AF2F3D50C72` (`modifier_id`),
  KEY `FK2506AF2B2118A9E` (`visibility_id`),
  CONSTRAINT `FK2506AF2B2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK2506AF2F3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_ROLES` WRITE;
/*!40000 ALTER TABLE `T_ROLES` DISABLE KEYS */;

INSERT INTO `T_ROLES` (`id`, `CREATED`, `MODIFIED`, `CODE`, `DESCRIPTION`, `modifier_id`, `visibility_id`)
VALUES
	(1,'2013-06-29 02:41:11','2013-09-19 12:33:30','SUPER_USER','Le rôle super utilisateur.\r\nIl a tout le droit',144,NULL),
	(2,'2013-06-29 02:41:11',NULL,'MODERATOR','Le rôle modérateur.',NULL,NULL),
	(3,'2013-06-29 02:41:11',NULL,'PRESIDENT','Le rôle président.',NULL,NULL),
	(4,'2013-10-10 08:26:31','2013-10-10 08:26:33','DEV','Les développeurs',144,NULL),
	(5,'2013-10-10 21:48:04','2013-10-10 21:48:06','VICE_PRESIDENT','Le vice président.',144,NULL);

/*!40000 ALTER TABLE `T_ROLES` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_SKILLS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_SKILLS`;

CREATE TABLE `T_SKILLS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `COMMENT` varchar(255) DEFAULT NULL,
  `DOMAIN` varchar(255) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `cv_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49382A8DDBD2534C` (`cv_id`),
  KEY `FK49382A8DF3D50C72` (`modifier_id`),
  KEY `FK49382A8DB2118A9E` (`visibility_id`),
  CONSTRAINT `FK49382A8DB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK49382A8DDBD2534C` FOREIGN KEY (`cv_id`) REFERENCES `T_CVS` (`id`),
  CONSTRAINT `FK49382A8DF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_SKILLS` WRITE;
/*!40000 ALTER TABLE `T_SKILLS` DISABLE KEYS */;

INSERT INTO `T_SKILLS` (`id`, `CREATED`, `MODIFIED`, `COMMENT`, `DOMAIN`, `modifier_id`, `cv_id`, `visibility_id`)
VALUES
	(1,'2013-09-12 12:33:59',NULL,'Pas de commentaire.','Informatique',NULL,144,NULL),
	(2,'2013-09-12 17:27:08','2013-09-12 17:27:08','JAVA HTML JSF PHP','Web',144,144,NULL);

/*!40000 ALTER TABLE `T_SKILLS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_USERS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_USERS`;

CREATE TABLE `T_USERS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `BOURSIER` bit(1) DEFAULT NULL,
  `CIVILITY` varchar(255) DEFAULT NULL,
  `CONFIRM` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PWDT` varchar(255) DEFAULT NULL,
  `PROMOTION` int(11) DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `cv_id` bigint(20) DEFAULT NULL,
  `dendrite_id` bigint(20) DEFAULT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `EMAIL_REDIRECT` varchar(255) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  `CONNEXION` int(11) DEFAULT NULL,
  `LAST_CONNEXION` datetime DEFAULT NULL,
  `FACEBOOK_ID` varchar(255) DEFAULT NULL,
  `FACEBOOK_TOKEN` varchar(255) DEFAULT NULL,
  `CONFIRM_REDIRECT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  KEY `FK27C6A3DEBA3847E` (`dendrite_id`),
  KEY `FK27C6A3DDBD2534C` (`cv_id`),
  KEY `FK27C6A3DF3D50C72` (`modifier_id`),
  KEY `FK27C6A3D5F0BDBD6` (`photo_id`),
  KEY `USER_EMAIL` (`EMAIL`),
  KEY `FK27C6A3DB2118A9E` (`visibility_id`),
  CONSTRAINT `FK27C6A3D5F0BDBD6` FOREIGN KEY (`photo_id`) REFERENCES `T_PHOTOS` (`id`),
  CONSTRAINT `FK27C6A3DB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK27C6A3DDBD2534C` FOREIGN KEY (`cv_id`) REFERENCES `T_CVS` (`id`),
  CONSTRAINT `FK27C6A3DEBA3847E` FOREIGN KEY (`dendrite_id`) REFERENCES `T_DENDRITES` (`id`),
  CONSTRAINT `FK27C6A3DF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_USERS` WRITE;
/*!40000 ALTER TABLE `T_USERS` DISABLE KEYS */;

INSERT INTO `T_USERS` (`id`, `CREATED`, `MODIFIED`, `ACTIVE`, `BIRTHDAY`, `BOURSIER`, `CIVILITY`, `CONFIRM`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `PWDT`, `PROMOTION`, `modifier_id`, `cv_id`, `dendrite_id`, `photo_id`, `EMAIL_REDIRECT`, `visibility_id`, `CONNEXION`, `LAST_CONNEXION`, `FACEBOOK_ID`, `FACEBOOK_TOKEN`, `CONFIRM_REDIRECT`)
VALUES
	(57,'2013-06-29 02:41:13','2013-09-15 19:55:33',00000001,'2003-10-01 00:00:00',00000001,'MONSIEUR',NULL,'abdourhamane.idrissa@cpd-mali.com','Abdourhamane','IDRISSA','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2003,144,57,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(82,'2013-06-29 02:41:13','2013-10-22 08:41:12',00000001,'2005-10-01 02:00:00',00000001,'MONSIEUR',NULL,'ousmane.kodio2005@cpd-mali.com','Ousmane','KODIO','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2005,82,82,1,NULL,NULL,NULL,1,'2013-10-22 08:41:12',NULL,NULL,NULL),
	(83,'2013-06-29 02:41:13','2013-11-13 20:42:26',00000001,'1987-09-25 00:00:00',00000001,'MONSIEUR',NULL,'yacouba.sagara2005@cpd-mali.com','Yacouba','SAGARA','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2005,83,83,1,NULL,'yacouba.sagara@gmail.com',NULL,4,'2013-11-13 20:42:26',NULL,NULL,NULL),
	(92,'2013-06-29 02:41:14','2013-11-16 03:12:44',00000001,'2006-10-01 02:00:00',00000001,'MONSIEUR',NULL,'baya.demba2006@cpd-mali.com','Baya','DEMBA','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2006,92,92,1,NULL,NULL,NULL,3,'2013-11-16 03:12:44',NULL,NULL,NULL),
	(95,'2013-06-29 02:41:14','2013-10-19 20:26:38',00000001,'2006-10-01 00:00:00',00000001,'MONSIEUR',NULL,'mamadou.diaby2006@cpd-mali.com','Mamadou','DIABY','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2006,144,95,2,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),
	(121,'2013-06-29 02:41:14','2013-11-27 14:04:22',00000001,'2013-09-19 00:00:00',00000001,'MONSIEUR',NULL,'abdoulaye.maiga2007@cpd-mali.com','Abdoulaye','MAIGA','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2007,121,121,2,5,'elaysama@gmail.com',NULL,15,'2013-11-27 14:04:22',NULL,NULL,NULL),
	(123,'2013-06-29 02:41:14','2013-11-18 21:04:27',00000001,'1990-04-16 00:00:00',00000001,'MONSIEUR',NULL,'souleymane.nanakasse2007@cpd-mali.com','Souleymane','NANAKASSE','$2a$10$0sS.6XcHsYmlvICABf3a1Oc7JgDBNxy1RkW0DWX0pGA8z5LNFiC/6',NULL,2007,123,123,2,24,'solomane.nanakasse@gmail.com',NULL,4,'2013-11-18 21:04:27','','',NULL),
	(144,'2013-06-29 02:41:14','2013-11-27 09:21:37',00000001,'1989-02-03 00:00:00',00000001,'MONSIEUR',NULL,'cheick-mahady.sissoko@cpd-mali.com','Cheick Mahady','SISSOKO','$2a$10$wh/i1iJ8p9gsHq/ZvBjdz.FoYYESRvHj3FwcbF5PGzYPFutZfvywO','ec25bacf-b544-45ca-b1dc-60dea3aa2c92',2008,144,144,2,17,'cheickm.sissoko@gmail.com',NULL,30,'2013-11-27 09:21:37','100000617074517','CAAKfj8W0zH0BAErUcaClXFrZAZCvCbDSOE0ColqbIDiHRwywRHXdNgXUFE09ZBmiCcdygYYbYeCl1rLgrgTChH8M0hRmZA7SwzqBy25dm1SpOsKWsygdtsXj8dY9ZA53KL90NeNMBec5KDlAmuL0qh3TZAc4Kyw4I112TTYRaUhfUcQgsMHPLV',NULL),
	(147,'2013-06-29 02:41:14','2013-10-24 13:33:13',00000001,'2008-10-01 02:00:00',00000001,'MADAME',NULL,'kadiatou.toure2008@cpd-mali.com','Kadiatou','TOURE','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2008,147,147,1,NULL,NULL,NULL,1,'2013-10-24 13:33:13',NULL,NULL,NULL),
	(149,'2013-06-29 02:41:14','2013-11-12 20:09:21',00000001,'2008-10-01 00:00:00',00000001,'MADAME',NULL,'hawa.traore2008@cpd-mali.com','Hawa','TRAORE','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2008,149,149,5,8,NULL,NULL,1,'2013-11-12 20:09:21',NULL,NULL,NULL),
	(150,'2013-06-29 02:41:14','2013-10-26 10:23:10',00000001,'2008-10-01 02:00:00',00000001,'MONSIEUR',NULL,'mohamed-dit-mady.traore2008@cpd-mali.com','Mohamed dit Mady','TRAORE','$2a$10$p847g9AkUFuSbYDy8.XeQeUTWMQBMqps82JOmYtPZb.as6rKKNSSi',NULL,2008,150,150,1,NULL,NULL,NULL,1,'2013-10-26 10:23:10',NULL,NULL,NULL),
	(151,'2013-06-29 02:41:14','2013-11-30 23:55:24',00000001,'1990-05-20 00:00:00',00000001,'MONSIEUR',NULL,'issa.camara2009@cpd-mali.com','Issa','CAMARA','$2a$10$hVbqHmDKXAfbV/bzdN5gguLrosVEiEt.zt/E0xWEYalyr1YwDwQs.',NULL,2009,151,151,2,NULL,'isskamara@hotmail.fr',NULL,10,'2013-11-30 23:55:24','','',NULL),
	(153,'2013-06-29 02:41:14','2013-11-26 22:35:55',00000001,'1990-10-21 00:00:00',00000001,'MONSIEUR',NULL,'mahamadou.doumbia2009@cpd-mali.com','Mahamadou','DOUMBIA','$2a$10$RYhO.cHNPlVVzp6gBNgkQO0bxcvKZ7sLTiYr92dWsvyW0tnfEUo5e',NULL,2009,153,153,2,NULL,'mahamadou.doumbia90@gmail.com',NULL,5,'2013-11-26 22:35:55',NULL,NULL,NULL),
	(193,'2013-09-12 18:14:04','2013-09-12 18:14:04',00000001,'1989-04-24 00:00:00',00000001,'MONSIEUR','4557e2e2-57c8-45aa-a6e0-8b5cf5ff2632','testelay@gmail.com','Elay','Maiga','$2a$10$G4Q9Xs5Ufww.hT44/xrt4.yGF/rycB37EsRVDg0sg.AlZDg4vKBUq',NULL,2007,NULL,193,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(198,'2013-09-13 15:54:38','2013-09-13 15:55:53',00000001,'1990-07-25 00:00:00',00000000,'MADAME',NULL,'baby@cpd-mali.com','Baby','Babylon','$2a$10$L2udyrrMRDuSAJubuhjKvuBXVtF7oi4PSkTl8UG5WbVKKVntu9j8S',NULL,0,NULL,198,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(201,'2013-09-18 06:22:58','2013-11-26 13:23:32',00000001,'1989-09-10 00:00:00',00000001,'MONSIEUR',NULL,'sekoutheokeita@hotmail.com','Sekou','Keita','$2a$10$Tl5mxXYqNYvHQ1RQRmadg.CXP/h9Zq4fhXRlnzxNSPY71DQzb.5lO',NULL,7,201,201,1,NULL,NULL,NULL,4,'2013-11-26 13:23:32',NULL,NULL,NULL),
	(202,'2013-09-18 07:44:32','2013-11-14 20:44:12',00000001,'1995-05-21 00:00:00',00000001,'MADEMOISELLE',NULL,'kmariam162@yahoo.fr','MARIAM','KONATE','$2a$10$9OR/21N2IxH.3LAiQsa6Xey51wgPcSKL5ukcQtIHbl1Iy9S1dIgQe',NULL,2010,202,202,2,NULL,NULL,NULL,1,'2013-11-14 20:44:12',NULL,NULL,NULL),
	(203,'2013-09-18 09:59:15','2013-09-18 10:01:27',00000001,'2013-09-18 00:00:00',00000001,'MONSIEUR','da5f2b8a-3f67-4909-8a5f-930207c82ccc','modibo.kd@gmail.com','Modibo','DIABATE','$2a$10$h1D8TEphwpJidi3HySIOGOpL.SXwu8wxJqhwwFq03vhUwpvzErxaK','8f6e6c5f-43fd-4719-88f0-17eb11cbd023',2011,NULL,203,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(204,'2013-09-18 12:03:20','2013-11-12 19:11:42',00000001,'1992-02-25 00:00:00',00000001,'MONSIEUR',NULL,'pauldembele22@gmail.com','Paul','DEMBELE','$2a$10$/IhvIzWO7seyAT4e20ugwuN7eYqPTWAxO2B6Hfi/UgB6w5Y6QinOe',NULL,2010,204,204,3,NULL,NULL,NULL,1,'2013-11-12 19:11:42',NULL,NULL,NULL),
	(205,'2013-09-18 16:50:24','2013-09-19 21:04:09',00000001,'1992-11-02 00:00:00',00000001,'MADEMOISELLE',NULL,'marthiam@yahoo.fr','Mariam','THIAM','$2a$10$HYv5rGh5YnpnXBRkd8vIMulgWr.JuMzL0tRcK6yNsT9c/.4B/C.9a',NULL,2010,NULL,205,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(206,'2013-09-19 20:08:34','2013-09-19 20:43:00',00000001,'1994-03-29 00:00:00',00000001,'MONSIEUR',NULL,'mariam.barry300j@gmail.com','Mariam','BARRY','$2a$10$THKunXbKzhHlVoULhLIDkO01bin/27Nol6gvM2aY4kdAgy5ZNNE.e',NULL,2011,NULL,206,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(207,'2013-09-24 09:09:54','2013-09-24 09:09:54',00000001,'1994-10-24 00:00:00',00000001,'MADEMOISELLE','7571e9be-fea4-40ce-b4f6-a3b04428cea4','touretema@gmail.com','Tema','Toure','$2a$10$hicXenWtU0bZLvE7/O271.RLDrwt9GrBdc804Eg7YeQPrv439CFJm',NULL,12,NULL,207,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(208,'2013-09-26 17:38:59','2013-09-26 17:43:25',00000001,'2000-09-01 00:00:00',00000001,'MONSIEUR',NULL,'ousmane.traore@cpd-mali.com','Ousmane','Traoré','$2a$10$cUqqTb7DWaPkbdGyCa60Be1FjN9OjqcLhtKcx0G957diTFWCDbB.6',NULL,2000,NULL,208,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(210,'2013-10-14 21:10:23','2013-10-14 21:10:23',00000001,'2013-10-14 00:00:00',00000001,'MONSIEUR','34ff069e-fad1-49ae-998b-6a9da34819ed','salem_youbba@yahoo.fr','Youbba Ould','SALEM','$2a$10$pTlmATBxqU6qqw5Kl0o3.uPZUk5ZLU8wsOg0RzyZxqKkHvX/u6NS.',NULL,2009,NULL,210,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(213,'2013-10-20 21:17:24','2013-11-24 13:45:39',00000001,'1989-01-26 00:00:00',00000000,'MONSIEUR',NULL,'alassane.kane7@gmail.com','Alassane','KANE','$2a$10$YVLlNRft7M4Imm42smjdrOavKH7xBM.XRyOFE/r.xlan9wVTT4NB2',NULL,NULL,213,213,1,21,NULL,NULL,23,'2013-11-24 13:45:39',NULL,NULL,NULL),
	(217,'2013-10-27 17:14:49','2013-11-14 18:47:53',00000001,'1995-01-21 00:00:00',00000001,'MONSIEUR',NULL,'mamadembele7@gmail.com','Mama','DEMBELE','$2a$10$aD4fr8hCUHc93kjbc7tXPO0WuX8NJQ7tbwKBJ6DPMM4EGEn2yvfJ.',NULL,2013,217,217,4,NULL,NULL,NULL,1,'2013-11-14 18:47:53','','',NULL),
	(218,'2013-10-27 19:42:25','2013-10-29 13:34:32',00000001,'1995-08-01 00:00:00',00000001,'MONSIEUR',NULL,'amadouyorothiam@yahoo.fr','Amadou Yoro','THIAM','$2a$10$qPuql7OQSlOBsy5k2b.Ase5aB/3zfB/jxfa18fwKoevQJTjxaRmeW',NULL,2013,218,218,4,NULL,NULL,NULL,2,'2013-10-29 13:34:32','','',NULL),
	(219,'2013-10-27 19:52:03','2013-11-27 21:27:18',00000001,'1996-04-13 00:00:00',00000001,'MONSIEUR',NULL,'moussdiak1395@yahoo.fr','Moussa','DIAKITE','$2a$10$/XrPBZ4wfTP5huOsR4g2Qu9LVGpoVg6Zqm1Vr6C7T7XN3zG6.O0OK',NULL,2013,219,219,4,NULL,NULL,NULL,2,'2013-11-27 21:27:18','','',NULL),
	(221,'2013-10-28 13:08:01','2013-11-05 20:58:18',00000001,'1993-05-26 00:00:00',00000000,'MADEMOISELLE',NULL,'djeneboumakadji@yahoo.fr','djenebou','Makadji','$2a$10$wbibigZ9u4514nzjgd.HwuqNZ4Bshsq6RP92SW9pew8Nw1IzkU8Oi',NULL,NULL,221,221,1,22,NULL,NULL,1,'2013-11-05 20:40:06','','',NULL),
	(222,'2013-10-28 13:51:23','2013-10-28 13:58:56',00000001,'1994-04-29 00:00:00',00000001,'MONSIEUR',NULL,'mohamedseydoudem@gmail.com','Mohamed','Seydou Dem','$2a$10$BYj6mdhfagqHFP50FxdrX.XLAdXMAJ00oYDHXwuC5Pkigfk0Dtu8O',NULL,2012,222,222,4,19,NULL,NULL,NULL,NULL,'100002274322856','CAAKfj8W0zH0BALgiXzOHqAZA3o08ghYEZAINWpDabExk2uUkmkmBWzn177tn2ZCNKmsAIip3AZBZBvZCo75xIsr26TR5FrRepZALO69vfAVeFET6pHDSX0otZCR3eQ0N9m9ARCLds4V1RGQX4KmEO81WJZCcLbWe8s8NwU6YU564slt2oQZBVCYX2dmcVDmCOxFe4ZD',NULL),
	(223,'2013-10-29 17:14:09','2013-11-26 13:29:39',00000001,'1989-06-04 00:00:00',00000001,'MADEMOISELLE',NULL,'taylore@live.fr','kadiatou','toure','$2a$10$lrrWgSsjw8vztIqpLsIokuLyXrZjippwFqK9WdD/w9E1Or6WJ7VNG',NULL,2008,223,223,2,20,NULL,NULL,10,'2013-11-26 13:29:39','','',NULL),
	(224,'2013-10-30 19:41:07','2013-10-30 19:54:14',00000001,'1995-12-16 00:00:00',00000001,'MONSIEUR',NULL,'mahamadousidibe.valence@gmail.com','Mahamadou','SIDIBE','$2a$10$9YV7H1qybVpc3Ax7S/YrXuAlPMf7I0NevNZZ01W.6N5UuRqYXzgFC',NULL,2013,224,224,4,NULL,NULL,NULL,1,'2013-10-30 19:54:14','','',NULL),
	(225,'2013-11-05 10:40:42','2013-11-05 10:40:42',00000001,'1989-04-24 00:00:00',00000001,'MONSIEUR','cbc36681-cf25-41ab-84a2-41349e28a12f','abdoulnn@hotmail.com','Elay','Maiga','$2a$10$CB6RDY201Q3/znUyEsGDieIMJh2zXdZ1ETv7YWcrlOJh6N.e/zVuW',NULL,2007,NULL,225,2,NULL,NULL,NULL,NULL,NULL,'','',NULL),
	(230,'2013-11-05 18:50:33','2013-11-05 18:52:04',00000001,'2013-11-05 00:00:00',00000001,'MADEMOISELLE',NULL,'sowassata@yahoo.fr','Aïssata','SOW','$2a$10$.zA.WiMy6Ljn9Ya1V58FuuHszHSEQjS.8AEL06L3HVNqglDteQ6ZS',NULL,2008,230,230,2,NULL,NULL,NULL,1,'2013-11-05 18:52:04','','',NULL),
	(231,'2013-11-05 18:57:11','2013-11-05 18:57:11',00000001,'2013-11-05 00:00:00',00000001,'MADEMOISELLE','8d460086-4e75-4749-807c-2846635a25a3','alima.koite300j@gmail.com','alima','koite','$2a$10$pIC/s2C8JfdBibdqHTFS9eMvYVBhUboxgU6YY8bhPjSkmXbqpEYIy',NULL,2011,NULL,231,3,NULL,NULL,NULL,NULL,NULL,'','',NULL),
	(232,'2013-11-05 19:14:32','2013-11-13 11:30:56',00000001,'1986-05-04 00:00:00',00000001,'MONSIEUR',NULL,'habibmarouan@yahoo.fr','FAYSSAL','KONATE','$2a$10$CnJDW/G71XI3/LLRPVhivOAVqwUrOXvSQS9y1gahLcE45Vx9Ow5Ty',NULL,2006,144,232,2,NULL,NULL,NULL,1,'2013-11-05 19:15:49','','',NULL),
	(233,'2013-11-05 22:29:45','2013-11-14 17:36:12',00000001,'1992-03-20 00:00:00',00000001,'MONSIEUR',NULL,'bamboadama.camara@yahoo.com','Camara','Bambo Adama','$2a$10$XxLy.TcMKIR06nF1ZpjU4eA.icdk6W2jsHWxtOumbV4SX1If2PJ52',NULL,2010,233,233,6,40,NULL,NULL,1,'2013-11-14 17:31:35','100001690687431','',NULL),
	(234,'2013-11-12 19:06:13','2013-11-12 19:50:24',00000001,'1991-09-06 00:00:00',00000001,'MADEMOISELLE',NULL,'oumou0691@yahoo.fr','Oumou','Diallo','$2a$10$uy5Xto9kUXgq39NKasJ6JeZo5OY5bZByVlHIhvy79sh4vOZb8YUSW',NULL,2008,234,234,1,NULL,NULL,NULL,1,'2013-11-12 19:14:51','','',NULL),
	(235,'2013-11-12 21:46:40','2013-11-14 06:06:34',00000001,'1990-10-26 00:00:00',00000001,'MONSIEUR',NULL,'aminata.aseck@gmail.com','Aminata','SECK','$2a$10$MSZ2I4KrmRYTXsrsgCbU9udbYI1qzXJIM54FNZfitufAyF.Fvevu6',NULL,0,235,235,2,NULL,NULL,NULL,1,'2013-11-14 06:06:34','','',NULL),
	(236,'2013-11-12 22:15:29','2013-11-30 22:05:41',00000001,'2013-11-12 00:00:00',00000001,'MONSIEUR',NULL,'amadoubeidy@live.fr','Amadou Sékou',' Tall','$2a$10$X9oXayTrclIMzRvKGMHgu.ixRxtS51/A/dpxGezI4kOBII2u8O.Bu',NULL,2013,236,236,4,39,NULL,NULL,20,'2013-11-30 22:05:41','','',NULL),
	(237,'2013-11-12 22:34:02','2013-11-21 13:30:17',00000001,'1984-08-20 00:00:00',00000001,'MONSIEUR',NULL,'cheickna2@yahoo.fr','CHEICKNA','CISSÉ','$2a$10$Q2Z6biCCkBvnpg2OP3sRkO5mKxK3y5QefX7t0zFt3ggMs0MQXRcwO',NULL,2003,237,237,2,NULL,NULL,NULL,3,'2013-11-21 13:30:17','','',NULL),
	(238,'2013-11-12 23:08:46','2013-11-12 23:15:39',00000001,'1991-04-14 00:00:00',00000001,'MONSIEUR',NULL,'ibra.excel@yahoo.fr','Ibrahim','Touré','$2a$10$aBF983t8cFk5ATkH949d9e56ebGbkE98QGunVFxlXZLRkX2nfzELq',NULL,2008,238,238,2,25,NULL,NULL,1,'2013-11-12 23:10:20','','',NULL),
	(239,'2013-11-12 23:11:07','2013-11-12 23:11:07',00000001,'1987-12-12 00:00:00',00000000,'MONSIEUR','80bf5ad6-384d-4eb7-874a-56cbbadc6b47','jahbromo@yahoo.fr','jahbromo','SIDIBE','$2a$10$lJQW9JHIRXS5ebzIwx5JHufVjZ3w9dNk3dp/mo98w0.8URTF8/aku',NULL,NULL,NULL,239,1,NULL,NULL,NULL,NULL,NULL,'','',NULL),
	(240,'2013-11-13 11:20:49','2013-11-13 11:53:31',00000001,'2013-11-13 00:00:00',00000001,'MONSIEUR',NULL,'bob.biot@live.fr','Akark','Kraka','$2a$10$WLg40gt8MKhmsqjhWliLD.Sg4jhfmmLgx8e5elLAQ3VrqFxwi2psi',NULL,2008,240,240,3,NULL,NULL,NULL,1,'2013-11-13 11:53:31','100002173033524','',NULL),
	(241,'2013-11-13 10:36:14','2013-11-13 11:28:06',00000001,'1991-09-06 00:00:00',00000001,'MADEMOISELLE',NULL,'ibrahimovickia@yahoo.fr','ROKIATOU','KONATE','$2a$10$I6UEDFgdK6gpUdYC0uwJlOt2bXEf0lXpsJPblwqfSo1qNrAOSxIny',NULL,2008,144,241,2,NULL,NULL,NULL,1,'2013-11-13 10:46:21','','',NULL),
	(242,'2013-11-13 12:10:50','2013-11-13 12:10:51',00000001,'2013-11-12 00:00:00',00000001,'MONSIEUR',NULL,'generalmlt@yahoo.fr','Moussa L','Traore','$2a$10$SZt/P6zHiRk5uAUVxlDm7eDflpMyDZa0zxj3NhT57cmNDXV54byHu',NULL,10,NULL,242,2,NULL,NULL,NULL,NULL,NULL,'100000622222819','',NULL),
	(243,'2013-11-13 20:15:39','2013-11-26 16:53:55',00000001,'1989-08-08 00:00:00',00000001,'MONSIEUR',NULL,'grandbaba.mali@yahoo.fr','MAMADOU','KEITA','$2a$10$JNx4ZfMo4XD73AbPhmJMNuuAa8g4HLNBXrvkLvpaUrPhUkg3AEPfO','4b7ea405-1e3d-4494-8e01-d70147eb9795',2008,NULL,243,1,38,NULL,NULL,2,'2013-11-17 22:42:48','','',NULL),
	(244,'2013-11-13 21:23:34','2013-11-13 21:26:51',00000001,'1993-02-01 00:00:00',00000001,'MONSIEUR',NULL,'salia.tangara20@yahoo.fr','salia','tangara','$2a$10$nvivC9aQphKoe0AkXW7.J.8YU9E8tuQP.lubjOZGa.L/u7QIvUIyC',NULL,11,244,244,3,NULL,NULL,NULL,1,'2013-11-13 21:26:51','','',NULL),
	(245,'2013-11-14 07:59:33','2013-11-15 22:07:30',00000001,'1991-08-13 00:00:00',00000001,'MONSIEUR',NULL,'goundiam1991@gmail.com','Mamadou','Goundiam','$2a$10$.fEWBf9mh/5QBpfTBMEkz.PpzGL4V8JdTBERNzfuMYuaN.WYd16Ta',NULL,2008,245,245,1,NULL,NULL,NULL,1,'2013-11-15 22:07:30','','',NULL),
	(246,'2013-11-14 14:20:13','2013-11-14 14:21:16',00000001,'1988-01-26 00:00:00',00000001,'MADEMOISELLE',NULL,'dicko737@gmail.com','Fatoumata','Dicko','$2a$10$uVPOwkM5KxwdTNByPgY3LuW31qbEJ3qYR/.00O.BobuWqDL4dkWcK',NULL,2006,246,246,2,NULL,NULL,NULL,1,'2013-11-14 14:21:16','','',NULL),
	(247,'2013-11-14 17:40:34','2013-11-14 17:41:41',00000001,'2013-12-26 00:00:00',00000001,'MONSIEUR',NULL,'adoutoure@gmail.com','Abdoulaye ','TOURE','$2a$10$Kuf4STThGRsM4OvqHrq4lOb.e6AxDsOg.kdjqWOqSKPibRUZlissG',NULL,2006,247,247,1,NULL,NULL,NULL,1,'2013-11-14 17:41:41','','',NULL),
	(248,'2013-11-14 18:02:31','2013-11-14 18:50:06',00000001,'1994-10-27 00:00:00',00000001,'MONSIEUR',NULL,'diourte_5@yahoo.fr','Adama','Diourté','$2a$10$qRowuaZCfmOfo/DeqzwDZ.8x0asx/m5BTIadqDuDIOEsi9WdIim12',NULL,2012,248,248,4,41,'diourte26@gmail.com',NULL,NULL,NULL,'100002530230029','','4097b0c5-3a46-4309-830a-295f9ce96975'),
	(249,'2013-11-14 19:03:53','2013-11-14 19:12:44',00000001,'1989-04-19 00:00:00',00000001,'MADEMOISELLE',NULL,'konatemahkadidia@yahoo.fr','Mah Kadidia','Konate','$2a$10$5zPPo5v5zMd0KF.lT3eqmOSEEe8f07bBn75xZnVohHaxIJwN.9vpG',NULL,2007,249,249,2,42,NULL,NULL,1,'2013-11-14 19:09:16','','',NULL),
	(250,'2013-11-14 19:21:36','2013-11-14 19:22:14',00000001,'1991-03-24 00:00:00',00000000,'MONSIEUR',NULL,'fousseinydemba@yahoo.fr','Fousseiny','Demba','$2a$10$hkdzOJuF/JW.TRqGNy4MruU9ooO7BEtW4K4BGkJd03gIxE.cCOr4a',NULL,NULL,250,250,1,NULL,NULL,NULL,1,'2013-11-14 19:22:14','','',NULL),
	(251,'2013-11-14 19:59:24','2013-11-22 19:33:14',00000001,'1992-08-16 00:00:00',00000001,'MONSIEUR',NULL,'souleymane.kane@ymail.com','Souleymane','KANE','$2a$10$LSw6nF12eQRnzKNiciEPaOWnn4erj3Iq5.Cv6XAWab1wSryLwig5e',NULL,-9,251,251,2,44,NULL,NULL,5,'2013-11-22 19:33:14','','',NULL),
	(252,'2013-11-14 20:38:44','2013-11-14 20:42:22',00000001,'1994-07-25 00:00:00',00000000,'MADEMOISELLE',NULL,'namina_tour@yahoo.fr','Namina','Toure','$2a$10$VbS9ODIBM8prcthaKRxg4e7mxhrE8BOsAqb9yjNzBtvGHhJiNBuPe',NULL,NULL,252,252,1,NULL,NULL,NULL,1,'2013-11-14 20:42:22','','',NULL),
	(253,'2013-11-14 20:42:21','2013-11-14 20:53:34',00000001,'2013-11-14 00:00:00',00000001,'MADEMOISELLE',NULL,'fatoumata_traore88@yahoo.fr','Fatoumata Haroun','TRAORE','$2a$10$W728Xs9bF4CjCabp4UdX0uMft6tvYRQ.NtMXLsMRCsGMuTx.PW0B6',NULL,2007,253,253,1,46,NULL,NULL,1,'2013-11-14 20:43:30','','',NULL),
	(254,'2013-11-14 22:37:57','2013-11-14 22:40:04',00000001,'2012-05-22 00:00:00',00000001,'MADEMOISELLE',NULL,'aminiele@yahoo.fr','Niélé','Coulibaly','$2a$10$UprQ/75fDsrLQZ0jYM/eheuX8fM2AYv.ACUGY3S7Rkm3.Bql88eH.',NULL,2004,254,254,2,NULL,NULL,NULL,1,'2013-11-14 22:40:04','','',NULL),
	(255,'2013-11-15 04:06:17','2013-11-15 04:57:50',00000001,'1990-01-12 00:00:00',00000001,'MONSIEUR',NULL,'odiarra@sas.upenn.edu','Oumar Amadou','DIARRA','$2a$10$g0PlCP3jqJrC2WT31MwvJOrT59M2Wap8qv0Mqz8zN8l5XObV2ssMm',NULL,2007,255,255,2,NULL,NULL,NULL,1,'2013-11-15 04:50:27','','',NULL),
	(256,'2013-11-15 12:29:21','2013-11-15 12:57:34',00000001,'2013-11-15 00:00:00',00000001,'MADEMOISELLE',NULL,'tazumi5@yahoo.fr','Rehanna','YATTARA','$2a$10$bwdSGllQP8K1hRR/BAmlhOZITkM.X9oFn1OgaCpzcjs9ZRzBUVmwm',NULL,2007,144,256,1,NULL,'',NULL,1,'2013-11-15 12:32:35','','',NULL),
	(257,'2013-11-15 15:17:58','2013-11-15 15:17:58',00000001,'1987-11-01 00:00:00',00000000,'MADAME','44b6e6d3-e489-4128-8b96-8f197a134a43','kaltoum.diallo@yahoo.fr','kaltoum','diallo','$2a$10$eBIq4s07z/kouC283Nhs2OOGL3z5wsT6Q5c8Lm/kqfSr1tRqZ6no6',NULL,NULL,NULL,257,1,NULL,NULL,NULL,NULL,NULL,'','',NULL),
	(258,'2013-11-16 14:25:26','2013-11-16 14:26:41',00000001,'1992-10-26 00:00:00',00000001,'MONSIEUR',NULL,'sanogoyoussa@hotmail.fr','YOUSSOUF','SANOGO','$2a$10$g4gIr6qB4ZyEzUQSWviBK.jb3jLeKal5lcdC/aRhFIAhrLKtUVNUe',NULL,-12,258,258,2,NULL,NULL,NULL,1,'2013-11-16 14:26:41','','',NULL),
	(259,'2013-11-17 01:14:32','2013-11-17 01:16:54',00000001,'1995-09-30 00:00:00',00000001,'MADEMOISELLE',NULL,'traorefatoumatadama@yahoo.fr','Fatoumata Adama','TRAORE','$2a$10$5iXdFV1GBiWLsR8QZ7/Mm.yvz8ZRBykVrWz/q6yCL/WumrONFuFI2',NULL,2013,NULL,259,4,NULL,NULL,NULL,NULL,NULL,'','',NULL),
	(260,'2013-11-17 15:10:43','2013-11-17 15:12:01',00000001,'1989-07-08 00:00:00',00000001,'MONSIEUR',NULL,'ablo.samakey@yahoo.fr','ABDOULAYE','SAMAKE','$2a$10$I28JeR14iwc0vD13uBVe9eX9ENArbTBpTmjg2SUjNi/uqqdaL1rFq',NULL,2007,260,260,1,NULL,NULL,NULL,1,'2013-11-17 15:12:01','','',NULL),
	(261,'2013-11-18 11:45:08','2013-11-18 11:49:50',00000001,'1990-09-08 00:00:00',00000001,'MADAME',NULL,'b_habibatou@yahoo.fr','Habibatou','Badini','$2a$10$0RdG6D8./WDi4On.oCeUD.1z1hUMyTguOX5PexgT5557l0OAt/7au',NULL,2007,261,261,7,NULL,NULL,NULL,1,'2013-11-18 11:49:50','','',NULL),
	(262,'2013-11-18 18:03:30','2013-11-18 22:05:39',00000001,'1959-01-02 00:00:00',00000000,'MONSIEUR',NULL,'marcbuonomo26@gmail.com','Marc','Buonomo','$2a$10$5Hp8.uv5B3aA15d8xi7Upey8BuRm055Nz2KJhZzkIWsSgKSNLC.rq',NULL,NULL,262,262,1,NULL,NULL,NULL,2,'2013-11-18 22:05:39','','',NULL),
	(263,'2013-11-18 20:19:51','2013-11-18 20:19:52',00000001,'1988-09-12 00:00:00',00000000,'MADEMOISELLE','7e7dab66-6916-4046-9f19-2c8ce4fa2301','touma1209@live.fr','Mariétou ','GUINDO','$2a$10$vRIUnH5N0uw/q4IcyVV4Getpx1OADjh7.1dJ6jQWHg90y4fh9Ewk2',NULL,NULL,NULL,263,1,NULL,NULL,NULL,NULL,NULL,'','',NULL),
	(264,'2013-11-18 22:38:13','2013-11-18 22:38:13',00000001,'1987-12-03 00:00:00',00000001,'MONSIEUR',NULL,'seydouxtraore@yahoo.fr','Seydou','Traoré','$2a$10$vifsVaMbR6BFzMAjAaQOtuX0Fw8Bde9iiCG5BriEo8YUBmh65dL9K',NULL,2006,NULL,264,1,NULL,NULL,NULL,NULL,NULL,'1585381014','',NULL),
	(265,'2013-11-18 22:54:30','2013-11-29 10:18:07',00000001,'1981-08-13 00:00:00',00000001,'MONSIEUR',NULL,'ykoloma@yahoo.fr','Yaya','KOLOMA','$2a$10$3JrYxnlZqn3vFum3WeCzBOSGJ82yv6gc08Ha5.H5..QeybIo0W6o2','fb0dcae6-6697-4745-a668-e8fbb0276391',1,NULL,265,2,NULL,NULL,NULL,1,'2013-11-18 22:55:28','','',NULL),
	(266,'2013-11-19 17:22:40','2013-11-19 17:22:41',00000001,'1994-10-24 00:00:00',00000001,'MONSIEUR',NULL,'temisstema@hotmail.fr','Tema','Toure','$2a$10$wjI/V7gO9aGTHmJh1cM4vOMFQy4pnohbCeTzmTSSrGF0bQuGMgdAa',NULL,12,NULL,266,3,NULL,NULL,NULL,NULL,NULL,'100002524377542','',NULL),
	(267,'2013-11-21 00:19:05','2013-11-21 00:30:50',00000001,'1990-06-28 00:00:00',00000001,'MONSIEUR',NULL,'salifoufofana28@yahoo.fr','Salif','FOFANA','$2a$10$3JwlGQ.KDGHTKT2QuzPr4eGV6bfuDCs1t7oKpbiSHe8VQ9ZrDuPL2',NULL,2004,267,267,6,NULL,NULL,NULL,1,'2013-11-21 00:25:33','','',NULL),
	(268,'2013-11-26 23:54:50','2013-11-26 23:54:50',00000001,'1985-12-01 00:00:00',00000001,'MONSIEUR',NULL,'doumma001@yahoo.fr','Doumma','Maiga','$2a$10$Vnaf87.XASLqRS7YIhh2NOOhwX6iGHUTqqBqYjPidB4yxV.xFpYRu',NULL,2003,NULL,268,2,NULL,NULL,NULL,NULL,NULL,'1129732209','',NULL);

/*!40000 ALTER TABLE `T_USERS` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table T_VISIBILITIES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_VISIBILITIES`;

CREATE TABLE `T_VISIBILITIES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`CODE`),
  KEY `FK892E313BF3D50C72` (`modifier_id`),
  KEY `FK892E313BB2118A9E` (`visibility_id`),
  CONSTRAINT `FK892E313BB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK892E313BF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Affichage de la table T_VOTES
# ------------------------------------------------------------

DROP TABLE IF EXISTS `T_VOTES`;

CREATE TABLE `T_VOTES` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `MODIFIED` datetime DEFAULT NULL,
  `modifier_id` bigint(20) DEFAULT NULL,
  `candidature_id` bigint(20) DEFAULT NULL,
  `elector_id` bigint(20) DEFAULT NULL,
  `visibility_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK288E6FE2CB51AE2` (`candidature_id`),
  KEY `FK288E6FE7AD4D1B7` (`elector_id`),
  KEY `FK288E6FEF3D50C72` (`modifier_id`),
  KEY `FK288E6FEB2118A9E` (`visibility_id`),
  CONSTRAINT `FK288E6FEB2118A9E` FOREIGN KEY (`visibility_id`) REFERENCES `T_VISIBILITIES` (`id`),
  CONSTRAINT `FK288E6FE2CB51AE2` FOREIGN KEY (`candidature_id`) REFERENCES `T_CANDIDATURES` (`id`),
  CONSTRAINT `FK288E6FE7AD4D1B7` FOREIGN KEY (`elector_id`) REFERENCES `T_USERS` (`id`),
  CONSTRAINT `FK288E6FEF3D50C72` FOREIGN KEY (`modifier_id`) REFERENCES `T_USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `T_VOTES` WRITE;
/*!40000 ALTER TABLE `T_VOTES` DISABLE KEYS */;

INSERT INTO `T_VOTES` (`id`, `CREATED`, `MODIFIED`, `modifier_id`, `candidature_id`, `elector_id`, `visibility_id`)
VALUES
	(1,'2013-09-14 00:00:55','2013-09-14 00:00:55',121,13,121,NULL),
	(2,'2013-09-14 00:00:59','2013-09-14 00:00:59',123,13,123,NULL),
	(3,'2013-09-14 00:01:01','2013-09-14 00:01:01',121,15,121,NULL),
	(4,'2013-09-14 00:01:06','2013-09-14 00:01:06',121,16,121,NULL),
	(6,'2013-09-14 00:01:25','2013-09-14 00:01:25',123,12,123,NULL),
	(7,'2013-09-14 00:01:29','2013-09-14 00:01:29',121,14,121,NULL),
	(12,'2013-09-14 00:03:21','2013-09-14 00:03:21',144,7,144,NULL),
	(13,'2013-09-14 00:03:57','2013-09-14 00:03:57',144,8,144,NULL),
	(14,'2013-09-14 00:04:05','2013-09-14 00:04:05',121,7,121,NULL),
	(15,'2013-09-14 00:04:11','2013-09-14 00:04:11',121,11,121,NULL);

/*!40000 ALTER TABLE `T_VOTES` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
