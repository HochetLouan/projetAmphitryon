-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 14 mai 2025 à 13:37
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdd_amphitryon`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `codeType` smallint NOT NULL,
  `nomType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codeType`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`codeType`, `nomType`) VALUES
(1, 'Entrée'),
(2, 'Plat Principal'),
(3, 'Dessert');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `idCommande` smallint NOT NULL,
  `EtatCommande` varchar(50) DEFAULT NULL,
  `DateDuJour` date NOT NULL,
  `idService` smallint NOT NULL,
  `numTable` smallint NOT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `DateDuJour` (`DateDuJour`,`idService`,`numTable`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`idCommande`, `EtatCommande`, `DateDuJour`, `idService`, `numTable`) VALUES
(1, 'En cours', '2025-01-10', 1, 1),
(2, 'Terminée', '2025-01-10', 2, 2),
(3, 'En attente', '2025-01-11', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `datej`
--

DROP TABLE IF EXISTS `datej`;
CREATE TABLE IF NOT EXISTS `datej` (
  `DateDuJour` date NOT NULL,
  PRIMARY KEY (`DateDuJour`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `datej`
--

INSERT INTO `datej` (`DateDuJour`) VALUES
('2025-01-10'),
('2025-01-11'),
('2025-01-12');

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

DROP TABLE IF EXISTS `ligne_commande`;
CREATE TABLE IF NOT EXISTS `ligne_commande` (
  `idPlat` smallint NOT NULL,
  `idCommande` smallint NOT NULL,
  `quantite` smallint DEFAULT NULL,
  `commentaire` varchar(50) DEFAULT NULL,
  `etat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idPlat`,`idCommande`),
  KEY `idCommande` (`idCommande`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `ligne_commande`
--

INSERT INTO `ligne_commande` (`idPlat`, `idCommande`, `quantite`, `commentaire`, `etat`) VALUES
(1, 1, 2, 'Sans sauce', 'En cours'),
(2, 1, 1, '', 'Servi'),
(3, 2, 3, 'Bien chaud', 'Servi'),
(1, 3, 1, 'Sans croûtons', 'En préparation');

-- --------------------------------------------------------

--
-- Structure de la table `plat`
--

DROP TABLE IF EXISTS `plat`;
CREATE TABLE IF NOT EXISTS `plat` (
  `idPlat` smallint NOT NULL,
  `nomPlat` varchar(50) DEFAULT NULL,
  `descriptif` text,
  `codeType` smallint DEFAULT NULL,
  PRIMARY KEY (`idPlat`),
  KEY `codeType` (`codeType`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `plat`
--

INSERT INTO `plat` (`idPlat`, `nomPlat`, `descriptif`, `codeType`) VALUES
(1, 'Salade César', 'Salade avec poulet, croûtons et sauce César', 1),
(2, 'Steak Frites', 'Steak accompagné de frites maison', 2),
(3, 'Tarte aux Pommes', 'Tarte classique aux pommes', 3);

-- --------------------------------------------------------

--
-- Structure de la table `proposerplat`
--

DROP TABLE IF EXISTS `proposerplat`;
CREATE TABLE IF NOT EXISTS `proposerplat` (
  `idPlat` smallint NOT NULL,
  `idService` smallint NOT NULL,
  `DateDuJour` date NOT NULL,
  `quantiteProposee` smallint DEFAULT NULL,
  `prixVente` decimal(10,2) DEFAULT NULL,
  `qteVendue` int DEFAULT NULL,
  PRIMARY KEY (`idPlat`,`idService`,`DateDuJour`),
  KEY `idService` (`idService`),
  KEY `DateDuJour` (`DateDuJour`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `proposerplat`
--

INSERT INTO `proposerplat` (`idPlat`, `idService`, `DateDuJour`, `quantiteProposee`, `prixVente`, `qteVendue`) VALUES
(1, 1, '2025-01-10', 10, 12.50, 3),
(2, 1, '2025-01-10', 8, 18.00, 5),
(3, 2, '2025-01-10', 6, 6.00, 2),
(1, 1, '2025-01-11', 10, 12.50, 4);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `idService` smallint NOT NULL,
  PRIMARY KEY (`idService`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`idService`) VALUES
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- Structure de la table `table_service`
--

DROP TABLE IF EXISTS `table_service`;
CREATE TABLE IF NOT EXISTS `table_service` (
  `DateDuJour` date NOT NULL,
  `idService` smallint NOT NULL,
  `numTable` smallint NOT NULL,
  `nbPlace` smallint DEFAULT NULL,
  `id` smallint DEFAULT NULL,
  PRIMARY KEY (`DateDuJour`,`idService`,`numTable`),
  KEY `idService` (`idService`),
  KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `table_service`
--

INSERT INTO `table_service` (`DateDuJour`, `idService`, `numTable`, `nbPlace`, `id`) VALUES
('2025-01-10', 1, 1, 4, 2),
('2025-01-10', 2, 2, 6, 3),
('2025-01-11', 1, 1, 4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` smallint NOT NULL,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `statut` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `password`, `statut`) VALUES
(1, 'chef', 'password123', 'Chef'),
(2, 'serveur1', 'password456', 'Serveur'),
(3, 'serveur2', 'password789', 'Serveur'),
(4, 'serveur', 'serveur', 'Serveur');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
