-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 17 fév. 2022 à 20:14
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `foodland`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie_entreprise`
--

CREATE TABLE `categorie_entreprise` (
  `FK_type_entreprise` varchar(50) NOT NULL,
  `FK_entreprise` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `categorie_produit`
--

CREATE TABLE `categorie_produit` (
  `id_categorie` varchar(50) NOT NULL,
  `lib_categorie` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie_produit`
--

INSERT INTO `categorie_produit` (`id_categorie`, `lib_categorie`) VALUES
('CAT001', 'Conserves'),
('CAT002', 'Surgelés'),
('CAT003', 'Emballages'),
('CAT004', 'Boissons'),
('CAT005', 'Sauces'),
('CAT006', 'Entretien');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` varchar(50) NOT NULL,
  `nom_commercial` varchar(50) DEFAULT NULL,
  `nom_acheteur` varchar(50) DEFAULT NULL,
  `nom_delivreur` varchar(50) DEFAULT NULL,
  `nom_preparateur` varchar(50) DEFAULT NULL,
  `date_creation_commande` date DEFAULT NULL,
  `date_preparation_commande` date DEFAULT NULL,
  `date_retrait_commande` date DEFAULT NULL,
  `FK_type_commande` varchar(50) NOT NULL,
  `FK_facture` varchar(50) DEFAULT NULL,
  `FK_entreprise` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `commande_contient_produit`
--

CREATE TABLE `commande_contient_produit` (
  `FK_produit` varchar(50) NOT NULL,
  `FK_commande` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `login_compte` varchar(50) NOT NULL,
  `password_compte` varchar(50) NOT NULL,
  `id_user` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `id_entreprise` varchar(10) NOT NULL,
  `siret_entreprise` varchar(14) NOT NULL,
  `nom_entreprise` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `code_postal` varchar(5) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `pays` varchar(50) NOT NULL,
  `telephone` varchar(11) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `FK_type_entreprise` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`id_entreprise`, `siret_entreprise`, `nom_entreprise`, `adresse`, `code_postal`, `ville`, `pays`, `telephone`, `mail`, `FK_type_entreprise`) VALUES
('ENT001', '65485215975345', 'FoodLand', '6 Rue Georges et Mai Politzer', '75012', 'Paris', 'France', '987563214', 'contact@foodland.com', ''),
('ENT002', '30717862400010', 'ETABLISSEMENTS ROUX SARL', 'FRUILEG 620\\n100 RUE D\'AVIGNON\\nCP 10645', '94550', 'CHEVILLY LARUE', 'France', '141730950', 'roux@ozfreres.fr', 'FR001'),
('ENT003', '83291876700019', 'LA POM\' ROSE', '81 RUE D\'AGEN\\nFRUILEG 50623', '94550', 'CHEVILLY LARUE', 'France', '149790152', 'pomrose@pomrose.fr', 'FR001'),
('ENT004', '', 'FRITES DOREES', '55-73 RUE BALAISE PASCAL', '93600', 'AULNAY-SOUS-BOIS', 'France', '148680303', 'fritesdorees@orange.fr', 'FR001'),
('ENT005', '313710303', 'CRETA-GEL', '39-43 RUE DE LA POINTE', '93100', 'MONTREUIL', 'France', '148596159', 'contact@creta-gel.com', 'FR001'),
('ENT006', '', 'EKIN', 'AULNAY SOUS BOIS', '93600', 'AULNAY-SOUS-BOIS', 'France', '148680303', '', 'FR001'),
('ENT007', '79458394800011', 'GLACIERE FOOD', '123 RUE DE LA GLACIČRE', '75013', 'PARIS', 'France', '', '', 'CLI001'),
('ENT008', '75180018600015', 'SARL ORLEANS FOOD', '57 RUE BEAUNIER', '75014', 'PARIS', 'France', '', '', 'CLI001'),
('ENT009', '48501625700019', 'EAT MEAT', '76 B ROUTE DE LA RIENE', '92100', 'BOULOGNE', 'France', '', 'kamel.fourti@gmail.com', 'CLI001'),
('ENT010', '79258163900020', 'LEIKA', 'CENTRE COMMERCIAL QUAIS D\'IVRY 30 BD PAUL VAILLANT', '94200', 'IVRY SUR SEINE', 'France', '610180155', '', 'CLI001'),
('ENT011', '50893037700025', 'H M D', '7 RUE FRANCISCO FERRER', '93100', 'MONTREUIL', 'France', '148594219', '', 'CLI001'),
('ENT012', '40276020100010', 'PLANET FOOD', '138 AVENUE DU GÉNÉRAL LECLERC', '75014', 'PARIS', 'France', '', '', 'CLI001'),
('ENT013', '52394258900012', 'MIXTE', '5 RUE THÉOPHILE GAUTIER', '94120', 'MONTROUGE', 'France', '', '', 'CLI001'),
('ENT014', '79928344500010', 'LE RELAIS DE VAUGIRARD SARL', '125 RUE VAUGERARD', '75015', 'PARIS', 'France', '', '', 'CLI001'),
('ENT015', '51452488300014', 'CROCUS CAFE', '24 RUE PASTEUR', '94270', 'LE KREMLIN BICETRE', 'France', '658168605', 'Crocuscafe.cc@gmail.com', 'CLI001'),
('ENT016', '81287746200026', 'NK ARCUEIL', 'CENTRE COMMERCIAL LA VACHE NOIRE PLACE DE LA VACHE', '94110', 'ARCUEIL', 'France', '663778709', '', 'CLI001'),
('ENT017', '804729507', 'SAS IFMS', '39 AVENUE DE CLICHY', '75017', 'PARIS', 'France', '783051765', 'ifmsnabab@gmail.com', 'CLI001'),
('ENT018', '818074973', 'BUFALINI', '401 RUE DE VAUGIRARD', '75015', 'PARIS', 'France', '148280630', '', 'CLI001'),
('ENT019', '82279232100017', 'YSLM', '4 Place de l\'Europe', '94220', 'CHARENTON LE PONT', 'France', '630860870', '', 'CLI001'),
('ENT020', '42337114500024', 'VINCENNES FOOD', '20 Avenue de Paris', '94300', 'VINCENNES', 'France', '', '', 'CLI001'),
('ENT021', '79219478900014', 'N20 FOOD', '63 AVENUE ARISTIDE BRIAND', '92120', 'MONTROUGE', 'France', '', '', 'CLI001'),
('ENT022', '840862809', 'LE PRESTIGE SARL', '134, Grand Rue', '92310', 'SEVRES', 'France', '982482761', 'bjrad.mourad@yahoo.fr', 'CLI001'),
('id_entrepr', 'siret_entrepri', 'nom_entreprise', 'adresse', 'code_', 'ville', 'pays', 'telephone', 'mail', 'FK_type_entreprise');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id_facture` varchar(10) NOT NULL,
  `date_creation_facture` date NOT NULL,
  `tva` float NOT NULL,
  `FK_type_facture` varchar(10) NOT NULL,
  `FK_commande` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `lignecommandes`
--

CREATE TABLE `lignecommandes` (
  `id_ligne_commande` varchar(10) NOT NULL,
  `code_barre_produit` varchar(14) NOT NULL,
  `quantite_poids` int(11) NOT NULL,
  `prix_Unitaire_ligne_commande` double NOT NULL,
  `TVA` float NOT NULL,
  `FK_commande` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id_produit` varchar(50) NOT NULL,
  `code_barre_produit` varchar(50) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `prix_unitaire_produit` varchar(50) NOT NULL,
  `poids_quantite` varchar(50) NOT NULL,
  `TVA` varchar(50) NOT NULL,
  `FK_categorie` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `code_barre_produit`, `designation`, `prix_unitaire_produit`, `poids_quantite`, `TVA`, `FK_categorie`) VALUES
('ART001', '75325641785246', 'Petit Pois 250g', '0.82', '10', '5.5', 'CAT001'),
('ART002', '95412365478522', 'Frites Alumettes 1kg', '1.20', '5', '5.5', 'CAT002'),
('ART003', '115499089171798', 'Steak Haché 45 - 10kg', '12.93', '1', '5.5', 'CAT002'),
('ART004', '135585812865074', 'Steak Haché 90 - 10kg', '21.50', '1', '5.5', 'CAT002'),
('ART005', '155672536558350', 'Boites Pizza - 23cm - 50 pièces', '15.32', '1', '20', 'CAT003'),
('ART006', '175759260251626', 'Boites Pizza - 45cm - 30 pièces', '23.56', '1', '20', 'CAT003'),
('ART007', '195845983944902', 'Boites Burger Simple - 45 pièces', '18.90', '1', '20', 'CAT003'),
('ART008', '215932707638178', 'Boites Burger Double - 30 pièces', '24.36', '1', '20', 'CAT003'),
('ART009', '236019431331454', 'Gobelet Carton - 200 pièces', '16.25', '1', '20', 'CAT003'),
('ART010', '256106155024730', 'Coca Cola 33cl - 24 pièces', '12.34', '1', '5.5', 'CAT004'),
('ART011', '276192878718006', 'Fanta Orange 33cl - 24 pièces', '13.20', '1', '5.5', 'CAT004'),
('ART012', '296279602411282', 'Jus Pomme 50cl - 12 pièces', '8.21', '1', '5.5', 'CAT004'),
('ART013', '316366326104558', 'Jus d\'Orange - 12 pièces', '9.96', '1', '5.5', 'CAT004'),
('ART014', '336453049797834', 'Haricot Blanc 500g', '1.12', '10', '5.5', 'CAT001'),
('ART015', '356539773491110', 'Pois Chiche 750g', '0.95', '10', '5.5', 'CAT001'),
('ART016', '376626497184386', 'ALGERIENNE NAWHAL\'S 950ML', '3.20', '5', '5.5', 'CAT005'),
('ART017', '396713220877662', 'AMERICAINE NAWHALS 950ML', '3.22', '5', '5.5', 'CAT005'),
('ART018', '416799944570938', 'ANDALOUSE NAWHAL\'S 950 ML', '3.18', '5', '5.5', 'CAT005'),
('ART019', '436886668264214', 'BARBECUE NAWHAL\'S 950ML', '3.26', '5', '5.5', 'CAT005'),
('ART020', '456973391957490', 'BIGGY BURGER NAWHAL\'S 950 ML', '3.33', '5', '5.5', 'CAT005'),
('ART021', '477060115650766', 'DETERGENT CONCENTRE ALIMENTAIRE 1L', '27.32', '1', '20', 'CAT006'),
('ART022', '497146839344042', 'ESSUIE TOUT 2 ROULEAUX 48', '17.21', '1', '20', 'CAT006'),
('ART023', '517233563037318', 'JAVEL 5L', '4.75', '1', '20', 'CAT006'),
('ART024', '537320286730594', 'LIQUIDE VAISSELLE - DETERGENT PLONGE MAGISTER 5L', '13.21', '1', '20', 'CAT006'),
('ART025', '557407010423870', 'LOTION MAIN 5L', '8.26', '1', '20', 'CAT006'),
('id_produit', 'code_barre_produit', 'designation', 'prix_unitaire_produit', 'poids_quantite', 'TVA', 'FK_categorie');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id_role` varchar(10) NOT NULL,
  `lib_role` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id_role`, `lib_role`) VALUES
('role001', 'administrator'),
('role002', 'gerant'),
('role003', 'acheteur');

-- --------------------------------------------------------

--
-- Structure de la table `typecommande`
--

CREATE TABLE `typecommande` (
  `id_type_commande` varchar(10) NOT NULL,
  `lib_type_commande` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `typecommande`
--

INSERT INTO `typecommande` (`id_type_commande`, `lib_type_commande`) VALUES
('TCCLI001', 'Commande Client'),
('TCFR001', 'Commande Fournisseur');

-- --------------------------------------------------------

--
-- Structure de la table `typeentreprise`
--

CREATE TABLE `typeentreprise` (
  `id_type_entreprise` varchar(50) NOT NULL,
  `lib_type_entreprise` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `typeentreprise`
--

INSERT INTO `typeentreprise` (`id_type_entreprise`, `lib_type_entreprise`) VALUES
('CLI001', 'Client'),
('FR001', 'Fournisseur');

-- --------------------------------------------------------

--
-- Structure de la table `typefacture`
--

CREATE TABLE `typefacture` (
  `id_type_facture` varchar(10) NOT NULL,
  `lib_type_facture` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `typefacture`
--

INSERT INTO `typefacture` (`id_type_facture`, `lib_type_facture`) VALUES
('TFCLI001', 'Facture Client'),
('TFFR001', 'Facture Fournisseur');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_user` varchar(10) NOT NULL,
  `nom_user` varchar(50) NOT NULL,
  `prenom_user` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `code_postal` varchar(5) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `tel_user` varchar(10) NOT NULL,
  `mail_user` varchar(50) NOT NULL,
  `FK_role` varchar(10) NOT NULL,
  `login_compte` varchar(50) NOT NULL,
  `password_compte` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `nom_user`, `prenom_user`, `adresse`, `code_postal`, `ville`, `tel_user`, `mail_user`, `FK_role`, `login_compte`, `password_compte`) VALUES
('user001', 'Doe', 'John', '1 rue de la ville', '12345', 'City ISland', '0123456789', 'john.doe@foodland.com', 'role001', 'admin', 'admin');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie_entreprise`
--
ALTER TABLE `categorie_entreprise`
  ADD PRIMARY KEY (`FK_type_entreprise`,`FK_entreprise`);

--
-- Index pour la table `categorie_produit`
--
ALTER TABLE `categorie_produit`
  ADD PRIMARY KEY (`id_categorie`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `FK_entreprise` (`FK_entreprise`),
  ADD KEY `FK_facture` (`FK_facture`),
  ADD KEY `FK_type_commande` (`FK_type_commande`);

--
-- Index pour la table `commande_contient_produit`
--
ALTER TABLE `commande_contient_produit`
  ADD PRIMARY KEY (`FK_produit`,`FK_commande`),
  ADD KEY `id_commande` (`FK_commande`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`login_compte`,`password_compte`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`id_entreprise`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id_facture`);

--
-- Index pour la table `lignecommandes`
--
ALTER TABLE `lignecommandes`
  ADD PRIMARY KEY (`id_ligne_commande`),
  ADD KEY `FK_commande` (`FK_commande`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`),
  ADD KEY `FK_categorie` (`FK_categorie`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_role`);

--
-- Index pour la table `typecommande`
--
ALTER TABLE `typecommande`
  ADD PRIMARY KEY (`id_type_commande`);

--
-- Index pour la table `typeentreprise`
--
ALTER TABLE `typeentreprise`
  ADD PRIMARY KEY (`id_type_entreprise`);

--
-- Index pour la table `typefacture`
--
ALTER TABLE `typefacture`
  ADD PRIMARY KEY (`id_type_facture`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_role` (`FK_role`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`FK_entreprise`) REFERENCES `entreprise` (`id_entreprise`),
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`FK_facture`) REFERENCES `facture` (`id_facture`),
  ADD CONSTRAINT `commande_ibfk_3` FOREIGN KEY (`FK_type_commande`) REFERENCES `typecommande` (`id_type_commande`);

--
-- Contraintes pour la table `commande_contient_produit`
--
ALTER TABLE `commande_contient_produit`
  ADD CONSTRAINT `commande_contient_produit_ibfk_1` FOREIGN KEY (`FK_commande`) REFERENCES `commande` (`id_commande`);

--
-- Contraintes pour la table `lignecommandes`
--
ALTER TABLE `lignecommandes`
  ADD CONSTRAINT `lignecommandes_ibfk_1` FOREIGN KEY (`FK_commande`) REFERENCES `commande` (`id_commande`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`FK_categorie`) REFERENCES `categorie_produit` (`id_categorie`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`FK_role`) REFERENCES `roles` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
