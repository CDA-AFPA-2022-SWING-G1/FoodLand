/*********************************
 *
 * CRÉATION BDD FOODLAND
 * 
 *********************************/

    DROP DATABASE IF EXISTS foodland;

    CREATE DATABASE foodland;

    USE foodland;

    -- entreprise
        CREATE TABLE entreprise (
            id_entreprise INT(11) NOT NULL AUTO_INCREMENT,
            siret_entreprise char(14) NULL,
            nom_entreprise varchar(50) NULL,
            adresse_entreprise varchar(50) NULL,
            code_postal_entreprise INT(5) NULL,
            ville_entreprise CHAR(50) NULL,
            pays_entreprise CHAR(50) NULL,
            tel_entreprise VARCHAR(15) NULL,
            mail_entreprise varchar(50) NULL,
            logo_entreprise BLOB NULL,

            PRIMARY KEY ( id_entreprise )
        );

    -- type_entreprise
        CREATE TABLE type_entreprise (
            id_type_entreprise INT(11) NOT NULL AUTO_INCREMENT,
            lib_type_entreprise VARCHAR(50) NOT NULL UNIQUE,
            
            PRIMARY KEY ( id_type_entreprise )
        );

    -- categorie_entreprise TABLE MANY TO MANY
        CREATE TABLE categorie_entreprise (
            fk_id_entreprise INT(11) NOT NULL,
            fk_id_type_entreprise INT(11) NOT NULL,

            PRIMARY KEY ( fk_id_type_entreprise, fk_id_entreprise ),

            FOREIGN KEY ( fk_id_type_entreprise ) REFERENCES type_entreprise ( id_type_entreprise ) ON DELETE CASCADE,
            FOREIGN KEY ( fk_id_entreprise ) REFERENCES entreprise(id_entreprise ) ON DELETE CASCADE
        );

    -- categorie_produit
        CREATE TABLE categorie_produit (
          	id_categorie_produit INT(11) NOT NULL AUTO_INCREMENT,
          	lib_categorie_produit char (50) NOT NULL UNIQUE,
            
            PRIMARY KEY ( id_categorie_produit )
        );

    -- produit :
        CREATE TABLE produit (
          	id_produit INT(11) NOT NULL AUTO_INCREMENT,
          	code_barre_produit VARCHAR(50) NULL,
            designation_produit varchar(50) NOT NULL,
          	prix_unitaire_produit DECIMAL (11,2) UNSIGNED NULL,
          	poids_quantite_produit DECIMAL(20,2) UNSIGNED NULL,
            TVA_produit ENUM ('0', '2.1', '5.5', '10', '20') NULL,
                
            fk_id_categorie_produit INT(11) NOT NULL,
            
            PRIMARY KEY ( id_produit ),
            
            FOREIGN KEY ( fk_id_categorie_produit ) REFERENCES categorie_produit( id_categorie_produit ) ON DELETE CASCADE
        );
        
    -- typefacture TABLE INUTILE effacée car table facture effacée
            /*
                Si commande accepté alors la commande est, devient une facture
                Donc Facture hérite du typecommande
            */
        /* CREATE TABLE typefacture (
              	id_type_facture INT(11) NOT NULL AUTO_INCREMENT,
                lib_type_facture ENUM ('Client', 'Fournisseur') NOT NULL,
              	-- lib_type_facture varchar(50) NOT NULL,
                PRIMARY KEY ( id_type_facture )
        );*/

    -- typecommande
        CREATE TABLE typecommande (
          	id_type_commande INT(10) NOT NULL AUTO_INCREMENT,
            lib_type_commande ENUM ('Client', 'Fournisseur') NOT NULL,
               	-- lib_type_commande varchar(30) NOT NULL,

            PRIMARY KEY ( id_type_commande )

        );

    -- commande
        CREATE TABLE commande (
          	id_commande INT(11) NOT NULL AUTO_INCREMENT,
            nom_commercial_commande varchar(100) NULL,
            nom_acheteur_commande varchar(100) NULL,

            nom_preparateur_commande varchar(100) NULL,
          	nom_delivreur_commande varchar(100) NULL,
            -- Et le réceptionneur de livraison ?
          	nom_receptionneur_commande varchar(100) NULL,

          	date_creation_commande date NOT NULL,
          	date_preparation_commande date NULL,
          	date_retrait_commande date NULL,

            accepter_ou_refuser_commande BOOLEAN NULL,

          	fk_id_type_commande INT(11) NULL,
          	fk_id_entreprise INT(11) NOT NULL,

            PRIMARY KEY ( id_commande ),
            
            FOREIGN KEY ( fk_id_entreprise ) REFERENCES entreprise(id_entreprise ) ON DELETE CASCADE,
            FOREIGN KEY ( fk_id_type_commande ) REFERENCES typecommande(id_type_commande ) ON DELETE NO ACTION ON UPDATE CASCADE
        );

    -- commande_contient_produit TABLE MANY TO MANY
        CREATE TABLE commande_contient_produit (
          	fk_id_produit INT(11) NOT NULL,
        	fk_id_commande INT(11) NOT NULL,
            
            FOREIGN KEY ( fk_id_produit ) REFERENCES produit(id_produit ),
            FOREIGN KEY ( fk_id_commande) REFERENCES commande(id_commande ) ON DELETE CASCADE
        );

    -- facture TABLE INUTILE effacée
        /* CREATE TABLE facture (
          	id_facture INT(11) NOT NULL AUTO_INCREMENT, 
          	date_creation_facture DATE NOT NULL, -- == accepter_ou_refuser_commande BOOL NULL,
          	tva_facture float NULL, -- ???, ce sont les produits !!!
          	fk_id_type_facture INT(11) NOT NULL,
          	fk_id_commande INT(11) NOT NULL,
            PRIMARY KEY ( id_facture ),
            FOREIGN KEY ( fk_id_type_facture ) REFERENCES typefacture(id_type_facture ),
            FOREIGN KEY ( fk_id_commande) REFERENCES commande(id_commande ) ON DELETE NO ACTION
        ); */

    -- categorie_produit
        CREATE TABLE categorie_lignecommandes (
            id_categorie_lignecommandes INT(11) NOT NULL AUTO_INCREMENT,
            lib_categorie_lignecommandes char (50) NOT NULL UNIQUE,

            PRIMARY KEY ( id_categorie_lignecommandes )
        );

    -- lignecommandes
        CREATE TABLE lignecommandes (
          	id_ligne_commande INT(11) NOT NULL AUTO_INCREMENT,
          	code_barre_ligne_commande varchar(20) NOT NULL,
            designation_ligne_commande varchar(50) NOT NULL,
            prix_unitaire_ligne_commande DECIMAL (11,2) UNSIGNED NULL,
            poids_quantite_ligne_commande DECIMAL(20,2) UNSIGNED NULL,
          	TVA_ligne_commande ENUM ('0', '2.1', '5.5', '10', '20') NULL,

          	fk_id_commande INT(11) NOT NULL,
            fk_id_categorie_lignecommandes INT(11) NOT NULL,

            PRIMARY KEY ( id_ligne_commande ),

            FOREIGN KEY ( fk_id_commande ) REFERENCES commande(id_commande ) ON DELETE CASCADE,
            FOREIGN KEY ( fk_id_categorie_lignecommandes ) REFERENCES categorie_lignecommandes( id_categorie_lignecommandes ) ON DELETE CASCADE
        );

    -- role
        CREATE TABLE roles (
          	id_role INT(11) NOT NULL AUTO_INCREMENT,
          	lib_role varchar(25) NOT NULL UNIQUE,

           	PRIMARY KEY ( id_role )
        );

    -- utilisateur
        CREATE TABLE utilisateur (
        	id_user INT(11) NOT NULL AUTO_INCREMENT,
          	nom_user varchar(50) NOT NULL,
          	prenom_user varchar(50) NOT NULL,
          	adresse_user varchar(200) NULL,
          	code_postal_user varchar(5) NULL,
          	ville_user varchar(20) NULL,
          	tel_user varchar(15) NOT NULL,
          	mail_user varchar(50) NOT NULL,
        	photo_user BLOB NULL,

            fk_id_role INT(11) NULL,

            PRIMARY KEY ( id_user ),

            FOREIGN KEY ( fk_id_role ) REFERENCES roles(id_role) ON UPDATE CASCADE
        );

    -- compte
        CREATE TABLE compte (
            id_compte INT(11) NOT NULL AUTO_INCREMENT,
          	login_compte varchar(200) NOT NULL UNIQUE,
          	password_compte varchar(200) NOT NULL,

            fk_id_user INT(11) NOT NULL,

            PRIMARY KEY ( id_compte ),
            FOREIGN KEY ( fk_id_user ) REFERENCES utilisateur(id_user) ON DELETE CASCADE
        );

/*********************************
 *
 * INSERTION DES DONNÉES FOODLAND
 * 
 *********************************/

     -- /*
        INSERT INTO type_entreprise (lib_type_entreprise) VALUES ('Client');
        INSERT INTO type_entreprise (lib_type_entreprise) VALUES ('Fournisseur');

        INSERT INTO entreprise (nom_entreprise) VALUES ('Foodland');
        SET @entreprise_id = LAST_INSERT_ID();
        INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(1, @entreprise_id);
        INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(2, @entreprise_id);

        INSERT INTO entreprise (nom_entreprise) VALUES ('Resto du coeur');
        SET @entreprise_id = LAST_INSERT_ID();
        INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(1, @entreprise_id);
        INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(2, @entreprise_id);

        INSERT INTO entreprise (nom_entreprise) VALUES ('Mac Do');
        SET @entreprise_id = LAST_INSERT_ID();
        INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(1, @entreprise_id);
    -- */

    -- /*
        INSERT INTO `categorie_lignecommandes` (`lib_categorie_lignecommandes`) VALUES
            ('Conserves'),
            ('Surgelés'),
            ('Emballages'),
            ('Boissons'),
            ('Sauces'),
            ('Entretien');
    -- */

    -- /*
        INSERT INTO `categorie_produit` (`lib_categorie_produit`) VALUES
            ('Conserves'),
            ('Surgelés'),
            ('Emballages'),
            ('Boissons'),
            ('Sauces'),
            ('Entretien');
    -- */

    /*
        INSERT INTO `produit` (`code_barre_produit`, `designation_produit`, `prix_unitaire_produit`, `poids_quantite_produit`, `TVA_produit`, `fk_id_categorie_produit`)
        VALUES
            ('75325641785246', 'Petit Pois 250g', '0.82', '10', '5.5', '1'),
            ('95412365478522', 'Frites Alumettes 1kg', '1.20', '5', '5.5','2'),
            ('115499089171798', 'Steak Haché 45 - 10kg', '12.93', '1', '5.5', '2'),
            ('135585812865074', 'Steak Haché 90 - 10kg', '21.50', '1', '5.5', '2'),
            ('155672536558350', 'Boites Pizza - 23cm - 50 pièces', '15.32', '1', '20', '3'),
            ('175759260251626', 'Boites Pizza - 45cm - 30 pièces', '23.56', '1', '20', '3'),
            ('195845983944902', 'Boites Burger Simple - 45 pièces', '18.90', '1', '20', '3'),
            ('215932707638178', 'Boites Burger Double - 30 pièces', '24.36', '1', '20', '3'),
            ('236019431331454', 'Gobelet Carton - 200 pièces', '16.25', '1', '20', '3'),
            ('256106155024730', 'Coca Cola 33cl - 24 pièces', '12.34', '1', '5.5', '4'),
            ('276192878718006', 'Fanta Orange 33cl - 24 pièces', '13.20', '1', '5.5', '4'),
            ('296279602411282', 'Jus Pomme 50cl - 12 pièces', '8.21', '1', '5.5', '4'),
            ('316366326104558', 'Jus d\'Orange - 12 pièces', '9.96', '1', '5.5', '4'),
            ('336453049797834', 'Haricot Blanc 500g', '1.12', '10', '5.5', '1'),
            ('356539773491110', 'Pois Chiche 750g', '0.95', '10', '5.5', '1'),
            ('376626497184386', 'ALGERIENNE NAWHAL\'S 950ML', '3.20', '5', '5.5', '5'),
            ('396713220877662', 'AMERICAINE NAWHALS 950ML', '3.22', '5', '5.5', '5'),
            ('416799944570938', 'ANDALOUSE NAWHAL\'S 950 ML', '3.18', '5', '5.5', '5'),
            ('436886668264214', 'BARBECUE NAWHAL\'S 950ML', '3.26', '5', '5.5', '5'),
            ('456973391957490', 'BIGGY BURGER NAWHAL\'S 950 ML', '3.33', '5', '5.5', '5'),
            ('477060115650766', 'DETERGENT CONCENTRE ALIMENTAIRE 1L', '27.32', '1', '20', '6'),
            ('497146839344042', 'ESSUIE TOUT 2 ROULEAUX 48', '17.21', '1', '20', '6'),
            ('517233563037318', 'JAVEL 5L', '4.75', '1', '20', '6'),
            ('537320286730594', 'LIQUIDE VAISSELLE - DETERGENT PLONGE MAGISTER 5L', '13.21', '1', '20', '6'),
            ('557407010423870', 'LOTION MAIN 5L', '8.26', '1', '20', '6');
    -- */

    -- /*
        INSERT INTO `typecommande` (`id_type_commande`, `lib_type_commande`)
        VALUES (NULL, 'Client'), (NULL, 'Fournisseur');
    -- */

    -- /*
        INSERT INTO `commande` (`id_commande`, `nom_commercial_commande`, `nom_acheteur_commande`, `nom_preparateur_commande`, `nom_delivreur_commande`, `nom_receptionneur_commande`, `date_creation_commande`, `date_preparation_commande`, `date_retrait_commande`, `accepter_ou_refuser_commande`, `fk_id_type_commande`, `fk_id_entreprise`) 
        VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NOW(), NULL, NULL, NULL, '1', '2'); -- 2 c’est Resto du coeur
    -- */

            INSERT INTO `lignecommandes` (`fk_id_commande`, `code_barre_ligne_commande`, `designation_ligne_commande`, `prix_unitaire_ligne_commande`, `poids_quantite_ligne_commande`, `TVA_ligne_commande`, `fk_id_categorie_lignecommandes`)
            VALUES
                  ('1', '75325641785246', 'Petit Pois 250g', '0.82', '10', '5.5', '1'),
                  ('1', '95412365478522', 'Frites Alumettes 1kg', '1.20', '5', '5.5','2'),
                  ('1', '115499089171798', 'Steak Haché 45 - 10kg', '12.93', '1', '5.5', '2'),
                  ('1', '135585812865074', 'Steak Haché 90 - 10kg', '21.50', '1', '5.5', '2'),
                  ('1', '155672536558350', 'Boites Pizza - 23cm - 50 pièces', '15.32', '1', '20', '3'),
                  ('1', '175759260251626', 'Boites Pizza - 45cm - 30 pièces', '23.56', '1', '20', '3'),
                  ('1', '195845983944902', 'Boites Burger Simple - 45 pièces', '18.90', '1', '20', '3'),
                  ('1', '215932707638178', 'Boites Burger Double - 30 pièces', '24.36', '1', '20', '3'),
                  ('1', '236019431331454', 'Gobelet Carton - 200 pièces', '16.25', '1', '20', '3'),
                  ('1', '256106155024730', 'Coca Cola 33cl - 24 pièces', '12.34', '1', '5.5', '4'),
                  ('1', '276192878718006', 'Fanta Orange 33cl - 24 pièces', '13.20', '1', '5.5', '4'),
                  ('1', '296279602411282', 'Jus Pomme 50cl - 12 pièces', '8.21', '1', '5.5', '4'),
                  ('1', '316366326104558', 'Jus d\'Orange - 12 pièces', '9.96', '1', '5.5', '4'),
                  ('1', '336453049797834', 'Haricot Blanc 500g', '1.12', '10', '5.5', '1'),
                  ('1', '356539773491110', 'Pois Chiche 750g', '0.95', '10', '5.5', '1'),
                  ('1', '376626497184386', 'ALGERIENNE NAWHAL\'S 950ML', '3.20', '5', '5.5', '5'),
                  ('1', '396713220877662', 'AMERICAINE NAWHALS 950ML', '3.22', '5', '5.5', '5'),
                  ('1', '416799944570938', 'ANDALOUSE NAWHAL\'S 950 ML', '3.18', '5', '5.5', '5'),
                  ('1', '436886668264214', 'BARBECUE NAWHAL\'S 950ML', '3.26', '5', '5.5', '5'),
                  ('1', '456973391957490', 'BIGGY BURGER NAWHAL\'S 950 ML', '3.33', '5', '5.5', '5'),
                  ('1', '477060115650766', 'DETERGENT CONCENTRE ALIMENTAIRE 1L', '27.32', '1', '20', '6'),
                  ('1', '497146839344042', 'ESSUIE TOUT 2 ROULEAUX 48', '17.21', '1', '20', '6'),
                  ('1', '517233563037318', 'JAVEL 5L', '4.75', '1', '20', '6'),
                  ('1', '537320286730594', 'LIQUIDE VAISSELLE - DETERGENT PLONGE MAGISTER 5L', '13.21', '1', '20', '6'),
                  ('1', '557407010423870', 'LOTION MAIN 5L', '8.26', '1', '20', '6');

    /*
        INSERT INTO `commande_contient_produit` (`fk_id_produit`, `fk_id_commande`)
        VALUES ('3', '1'), ('4', '1'), ('5', '1'), ('6', '1'), ('7', '1'), ('8', '1');
    -- */

    -- /*    
        INSERT INTO `roles` (`lib_role`) 
            VALUES ('admin'), ('gerant'), ('acheteur'), ('vendeur'), ('preparateur_commande'),('delivreur_commande'),('receptionneur'),('livreur');
            -- de prépa à livreur c’est inutile
        INSERT INTO `roles` (`id_role`, `lib_role`)
            VALUES (NULL, 'magasinier');
    -- */

    -- /*
        INSERT INTO `utilisateur` (`nom_user`, `prenom_user`, `tel_user`, `mail_user`, `fk_id_role`) 
        VALUES 
            ('admins', 'pierre', '0145450001', 'pierreadmins@foodland.fr', '1'),
            ('gerand', 'paul', '0145450000', 'paulgerand@foodland.fr', '2'),
            ('vendor','john', '0145450010', 'johnvendor@foodland.fr', '4'),
            ('achetur', 'jacques', '0145450020', 'pierreadmins@foodland.fr', '3'),
            ('magasinne','tim', '0145450050', 'timmagasinne@foodland.fr', '9'),
            ('magase','tom', '0145450050', 'tommagase@foodland.fr', '9'),
            ('maga','thomas', '0145450050', 'thomasmaga@foodland.fr', '9');
    -- */

    -- /*
        INSERT INTO `compte` (`login_compte`, `password_compte`, `fk_id_user`) 
        VALUES 
            ('admins', '0000', '1'),
            ('gerand', '0000', '2'),
            ('vendor', '0000', '3'),
            ('achetur', '0000', '4'),
            ('magasinne', '0000', '5'),
            ('magase', '0000', '6'),
            ('maga', '0000', '7');
    -- */