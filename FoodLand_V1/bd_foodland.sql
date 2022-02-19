DROP DATABASE IF EXISTS foodland;

CREATE DATABASE foodland;

--
-- Structure de la table `entreprise`
--

CREATE TABLE entreprise (
  	id_entreprise INT(11) NOT NULL AUTO_INCREMENT,
  	siret_entreprise char(14) NOT NULL,
  	nom_entreprise varchar(50) NOT NULL,
  	adresse varchar(50)  NULL,
  	code_postal INT(5)  NULL,
  	ville CHAR(50)  NULL,
  	pays CHAR(50)  NULL,
  	telephone VARCHAR(15) NULL,
  	mail varchar(50) NOT NULL,
  	PRIMARY KEY ( id_entreprise )
);


-- Structure de la table `type_entreprise`
CREATE TABLE type_entreprise (
  	id_type_entreprise INT(11) NOT NULL AUTO_INCREMENT,
  	lib_type_entreprise char(50) NOT NULL,
    PRIMARY KEY ( id_type_entreprise )
    /*INDEX(id_type_entreprise, lib_type_entreprise)*/
);


-- Structure de la table `categorie_entreprise`
CREATE TABLE categorie_entreprise (
  	fk_id_type_entreprise INT(11) NOT NULL,
  	fk_id_entreprise INT(11) NOT NULL,
	lib_type_entreprise char(50) NOT NULL,
    PRIMARY KEY ( fk_id_type_entreprise, fk_id_entreprise ),
    FOREIGN KEY ( fk_id_type_entreprise ) REFERENCES type_entreprise(id_type_entreprise ),
    FOREIGN KEY ( fk_id_entreprise ) REFERENCES entreprise(id_entreprise ) ON DELETE CASCADE
);

-- Structure de la table `categorie_produit`

CREATE TABLE categorie_produit (
  	id_categorie INT(11) NOT NULL AUTO_INCREMENT,
  	lib_categorie char (50) NOT NULL,
    PRIMARY KEY ( id_categorie )
);

-- Structure de la table `produit`
CREATE TABLE produit (
  	id_produit INT(11) NOT NULL AUTO_INCREMENT,
  	code_barre_produit VARCHAR(50) NULL,
    	designation_produit varchar(50) NOT NULL,
  	prix_unitaire_produit varchar(50) NULL,
  	poids_quantite_produit varchar(50) NULL,
  	TVA float NULL,
   	fk_id_categorie_produit INT(11) NOT NULL,
    PRIMARY KEY ( id_produit ),
    FOREIGN KEY ( fk_id_categorie_produit ) REFERENCES categorie_produit(id_categorie ) ON DELETE CASCADE
);

-- Structure de la table `typefacture`

CREATE TABLE typefacture (
  	id_type_facture INT(11) NOT NULL AUTO_INCREMENT,
  	lib_type_facture varchar(50) NOT NULL,
    PRIMARY KEY ( id_type_facture )
);
-- Structure de la table `typecommande`

CREATE TABLE typecommande (
  	id_type_commande INT(10) NOT NULL AUTO_INCREMENT,
  	lib_type_commande varchar(30) NOT NULL, 
    PRIMARY KEY ( id_type_commande )
);

CREATE TABLE commande (
  	id_commande INT(11) NOT NULL AUTO_INCREMENT,
    	nom_commercial varchar(50) DEFAULT NULL,
    	nom_acheteur varchar(50) DEFAULT NULL,
  	nom_delivreur varchar(50) DEFAULT NULL,
  	nom_preparateur varchar(50) DEFAULT NULL,
  	date_creation_commande date DEFAULT NULL,
  	date_preparation_commande date DEFAULT NULL,
  	date_retrait_commande date DEFAULT NULL,
  	fk_id_type_commande INT(11) NULL,
  	/*id_facture varchar(50) DEFAULT NULL,*/
  	fk_id_entreprise INT(11) NOT NULL,
    PRIMARY KEY ( id_commande ),
    FOREIGN KEY ( fk_id_entreprise ) REFERENCES entreprise(id_entreprise ) ON DELETE CASCADE,
    FOREIGN KEY ( fk_id_type_commande ) REFERENCES typecommande(id_type_commande ) ON DELETE NO ACTION ON UPDATE CASCADE
    
    /*FOREIGN KEY ( id_facture ) REFERENCES facture(id_facture )*/
);

-- Structure de la table `commande_contient_produit` historique
CREATE TABLE commande_contient_produit (
  	fk_id_produit INT(11) NOT NULL,
	fk_id_commande INT(11) NOT NULL,
    PRIMARY KEY ( fk_id_commande, fk_id_produit ),
    FOREIGN KEY ( fk_id_produit ) REFERENCES produit(id_produit ),
    FOREIGN KEY ( fk_id_commande) REFERENCES commande(id_commande ) ON DELETE CASCADE
);

CREATE TABLE facture (
  	id_facture INT(11) NOT NULL AUTO_INCREMENT,
  	date_creation_facture DATE NOT NULL,
  	tva_facture float NULL,
  	fk_id_type_facture INT(11) NOT NULL,
  	fk_id_commande INT(11) NOT NULL,
    PRIMARY KEY ( id_facture ),
    FOREIGN KEY ( fk_id_type_facture ) REFERENCES typefacture(id_type_facture ),
    FOREIGN KEY ( fk_id_commande) REFERENCES commande(id_commande ) ON DELETE NO ACTION
);

CREATE TABLE lignecommandes (
  	id_ligne_commande INT(11) NOT NULL,
  	code_barre_produit varchar(20) NOT NULL,
  	quantite_poids int(11) NOT NULL,
  	prix_Unitaire_ligne_commande double NOT NULL,
  	TVA float NOT NULL,
  	fk_id_commande INT(11) NOT NULL,
    fk_id_produit INT(11) NOT NULL,
    PRIMARY KEY ( id_ligne_commande ),
    FOREIGN KEY ( fk_id_commande ) REFERENCES commande(id_commande ) ON DELETE CASCADE,
    FOREIGN KEY ( fk_id_produit ) REFERENCES produit(id_produit ) ON DELETE CASCADE
);

-- Structure de la table `role`
CREATE TABLE roles (
  	id_role INT(11) NOT NULL AUTO_INCREMENT,
  	lib_role varchar(25) NOT NULL,
   	PRIMARY KEY ( id_role )
);


-- Structure de la table `utilisateur`
CREATE TABLE utilisateur (
	id_user INT(11) NOT NULL AUTO_INCREMENT,
  	nom_user varchar(50) NOT NULL,
  	prenom_user varchar(50) NOT NULL,
  	adresse_user varchar(50) NULL,
  	code_postal_user varchar(5) NULL,
  	ville_user varchar(20) NULL,
  	tel_user varchar(15) NOT NULL,
  	mail_user varchar(50) NOT NULL,
        fk_id_role INT(11) NULL,
	photo_user BLOB NULL, -- attention
    	/*login_compte varchar(50) NOT NULL,
    	password_compte varchar(50) NOT NULL,*/
    PRIMARY KEY ( id_user ),
    FOREIGN KEY ( fk_id_role ) REFERENCES roles(id_role) ON UPDATE CASCADE
    /*
    FOREIGN KEY ( password_compte ) REFERENCES compte(password_compte )
    */
);


-- Structure de la table `compte`

CREATE TABLE compte (
    id_compte INT(11) NOT NULL AUTO_INCREMENT,
  	login_compte varchar(50) NOT NULL,
  	password_compte varchar(50) NOT NULL,
    fk_id_user INT(11) NOT NULL,
    PRIMARY KEY ( id_compte ),
    FOREIGN KEY ( fk_id_user ) REFERENCES utilisateur(id_user) ON DELETE CASCADE
);