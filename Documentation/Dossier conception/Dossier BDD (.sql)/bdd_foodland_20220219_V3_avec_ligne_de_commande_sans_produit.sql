-- Requete CRUD dans une relation n, n / many to many (table de realtion)

    -- insérer des donnéer
        -- Exemple externe d’insertion : https://stackoverflow.com/questions/19714308/mysql-how-to-insert-into-table-that-has-many-to-many-relationship
            -- créer les tables
                DROP DATABASE IF EXISTS test_many_to_many;

                CREATE DATABASE test_many_to_many;
                
                CREATE TABLE persons (
                  person_id int(11) NOT NULL AUTO_INCREMENT,
                  firstname varchar(30) NOT NULL,
                  lastname varchar(30) NOT NULL,
                  PRIMARY KEY (person_id)
                );

                CREATE TABLE properties (
                  property_id int(11) NOT NULL AUTO_INCREMENT,
                  property varchar(254) NOT NULL UNIQUE,
                  PRIMARY KEY (property_id)
                );

                CREATE TABLE has_property (
                  person_id int(11) NOT NULL,
                  property_id int(11) NOT NULL,
                  PRIMARY KEY (person_id,property_id),
                  FOREIGN KEY (person_id) REFERENCES persons (person_id),
                  FOREIGN KEY (property_id) REFERENCES properties (property_id)
                );

            -- 1ère requète d’insertion
                INSERT INTO persons (firstname,lastname) VALUES ('John','Doe');
                SET @person_id = LAST_INSERT_ID();

                INSERT IGNORE INTO properties (property) VALUES ('property_A');
                SET @property_id = LAST_INSERT_ID();
                INSERT INTO has_property (person_id,property_id) VALUES(@person_id, @property_id);

                INSERT IGNORE INTO properties (property) VALUES ('property_B');
                SET @property_id = LAST_INSERT_ID();
                INSERT INTO has_property (person_id,property_id) VALUES(@person_id, @property_id);

                INSERT IGNORE INTO properties (property) VALUES ('property_C');
                SET @property_id = LAST_INSERT_ID();
                INSERT INTO has_property (person_id,property_id) VALUES(@person_id, @property_id);

            -- 2ème requète d’insertion
                INSERT INTO persons (firstname,lastname) VALUES ('Tang','Doe');
                SET @person_id = LAST_INSERT_ID();
                INSERT INTO has_property (person_id,property_id) VALUES(@person_id, 1);
                INSERT INTO has_property (person_id,property_id) VALUES(@person_id, 2);
                INSERT INTO has_property (person_id,property_id) VALUES(@person_id, 3);

        /* table entreprise
         * table catégorie (Client et/ou Fournisseur)
         * table entreprise_a_une_categorie
         */
        -- les tables sont déjà créées

-- CASCADE ON DELETE VS ON UPDATE
    -- https://stackoverflow.com/questions/1481476/when-to-use-on-update-cascade

-- DESCRIBE : décrit les champs de la table (type, null...)

-- BOOLEAN
    -- https://www.mysqltutorial.org/mysql-boolean/

-- Relation d’héritage
    -- Transformez votre héritage par référence : recherche Google (héritage base de données relationnelle)
        -- https://openclassrooms.com/fr/courses/6938711-modelisez-vos-bases-de-donnees/7562106-transformez-vos-relations-d-heritage

-- INSERT IGNORE INTO
    -- https://www.mysqltutorial.org/mysql-insert-ignore/

/*
 *
 * CRÉATION FOODLAND
 * - BDD
 * - Tables
 * 
 */
-- Pas de catégorie pour lignecommande
    -- Comment ajouter des catégories dans la table categorieproduit lié à produit et lignecommande ?

DROP DATABASE IF EXISTS foodland_avec_ligne_de_commande_sans_produit;

CREATE DATABASE foodland_avec_ligne_de_commande_sans_produit;

USE foodland_avec_ligne_de_commande_sans_produit;

-- ENTREPRISE + TYPE_ENTREPRISE + CATEGORIE_ENTREPRISE (n,n)
    -- entreprise
    CREATE TABLE entreprise (
        id_entreprise INT(11) NOT NULL AUTO_INCREMENT,
        -- date_enregistrement_entreprise date NULL,
        siret_entreprise char(14) NULL,
        nom_entreprise varchar(50) NULL,
        adresse_entreprise varchar(50) NULL,
        code_postal_entreprise INT(5) NULL,
        ville_entreprise CHAR(50) NULL,
        pays_entreprise CHAR(50) NULL,
        tel_entreprise VARCHAR(15) NULL,
        mail_entreprise varchar(50) NULL,

        -- ajouter par Tanguy
        logo_entreprise BLOB NULL,
        -- CA_foodland_entreprise... NULL, somme achat - somme vente

        PRIMARY KEY ( id_entreprise )
    );

    -- type_entreprise
        -- On devrait l’appeler categorie_entreprise
        -- J’ai ajouté UNIQUE
    CREATE TABLE type_entreprise (
        id_type_entreprise INT(11) NOT NULL AUTO_INCREMENT,
        lib_type_entreprise VARCHAR(50) NOT NULL UNIQUE,
        PRIMARY KEY ( id_type_entreprise )
        /*INDEX(id_type_entreprise, lib_type_entreprise)*/
    );

    -- categorie_entreprise TABLE MANY TO MANY
        -- On devrait l’appeler entreprise_a_une_categorie
        -- ON DELETE CASCADE sur les 2
    CREATE TABLE categorie_entreprise (
        fk_id_entreprise INT(11) NOT NULL,
        fk_id_type_entreprise INT(11) NOT NULL,
        --  fk_lib_type_entreprise char(50) NOT NULL,


        PRIMARY KEY ( fk_id_type_entreprise, fk_id_entreprise ),
/*-----------------------------------------------------------------------------
EMBROUILLE ligne du dessus
                (ressenti)
                le problème c’est la PRIMARY KEY ci-dessus comme l’autre table MANY TO MANY commande_contient_produit 
                ou
                ...
                comparer cette table MANY TO MANY avec l’autre table MANY TO MANY commande_contient_produit
-- --------------------------------------------------------------------------- */



        --    FOREIGN KEY ( fk_id_type_entreprise ) REFERENCES type_entreprise(id_type_entreprise ),
        FOREIGN KEY ( fk_id_type_entreprise ) REFERENCES type_entreprise ( id_type_entreprise ) ON DELETE CASCADE,
/*-----------------------------------------------------------------------------
EMBROUILLE ligne du dessus

            -- ON DELETE CASCADE c’est faux
            -- mais ça n’efface pas (quoi ?) si une insertion l’utilise (quelle insertion ?)
                -- En effet, j’avais essayé d’effacer une entreprise pour voir si Bug mais non !
            -- POURQUOI C FAUX ?
            -- Car...
            --
-- --------------------------------------------------------------------------- */



        --    FOREIGN KEY ( fk_lib_type_entreprise ) REFERENCES type_entreprise(lib_type_entreprise ) ON UPDATE CASCADE,
        FOREIGN KEY ( fk_id_entreprise ) REFERENCES entreprise(id_entreprise ) ON DELETE CASCADE
    );
-- JEUX DE DONNÉES FOODLAND COMPLIQUÉ !!!
    -- 1ère insertion à la création
        /*
            INSERT INTO entreprise (nom_entreprise) VALUES ('Mac Do');
            SET @entreprise_id = LAST_INSERT_ID();

            INSERT IGNORE INTO type_entreprise (lib_type_entreprise) VALUES ('Fournisseur');
            SET @type_entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(@type_entreprise_id, @entreprise_id);

            INSERT IGNORE INTO type_entreprise (lib_type_entreprise) VALUES ('Client');
            SET @type_entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(@type_entreprise_id, @entreprise_id);
        */
    -- 2ème insertion en production
        /*
            INSERT INTO entreprise (nom_entreprise) VALUES ('Resto du coeur');
            SET @entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(1, @entreprise_id);
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(2, @entreprise_id);

            INSERT INTO entreprise (nom_entreprise) VALUES ('Foodland');
            SET @entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(1, @entreprise_id);
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(2, @entreprise_id);
        */

-- categorie_produit
    CREATE TABLE categorie_produit (
      	id_categorie_produit INT(11) NOT NULL AUTO_INCREMENT,
      	lib_categorie_produit char (50) NOT NULL UNIQUE,
        PRIMARY KEY ( id_categorie_produit )
    );
-- JEUX DE DONNÉES FOODLAND
    /*
        INSERT INTO `categorie_produit` (`lib_categorie_produit`) VALUES
          ('Conserves'),
          ('Surgelés'),
          ('Emballages'),
          ('Boissons'),
          ('Sauces'),
          ('Entretien');
    */

-- produit : UNE LIGNE PRODUIT DOIT ÊTRE UNIQUE POUR GÉRER LE STOCK
    CREATE TABLE produit (
      	id_produit INT(11) NOT NULL AUTO_INCREMENT, -- un concat du type ART001 aurait été mieux !
      	code_barre_produit VARCHAR(50) NULL,
        designation_produit varchar(50) NOT NULL,
      	prix_unitaire_produit DECIMAL (11,2) UNSIGNED NULL, -- varchar(50) NULL,
            -- https://dev.mysql.com/doc/refman/8.0/en/precision-math-decimal-characteristics.html
            -- https://www.mysqltutorial.org/mysql-decimal/
            -- https://stackoverflow.com/questions/5256469/what-is-the-benefit-of-zerofill-in-mysql
      	poids_quantite_produit DECIMAL(20,2) UNSIGNED NULL,-- on peut stocker 20 chiffres avant la virgule -- on vend au kg, 10 tonnes max -- varchar(50) NULL,
        TVA_produit ENUM ('0', '2.1', '5.5', '10', '20') NULL,
            -- TVA float NULL,
            -- https://dev.mysql.com/doc/refman/8.0/en/enum.html
       	
        fk_id_categorie_produit INT(11) NOT NULL,
        
        PRIMARY KEY ( id_produit ),
        
        FOREIGN KEY ( fk_id_categorie_produit ) REFERENCES categorie_produit( id_categorie_produit ) ON DELETE CASCADE
    );
-- JEUX DE DONNÉES TEST AVEC TVA EN ENUM
    -- INSERT INTO `produit` (`id_produit`, `code_barre_produit`, `designation_produit`, `prix_unitaire_produit`, `poids_quantite_produit`, `TVA`, `fk_id_categorie_produit`) 
    -- VALUES (NULL, NULL, 'test', NULL, NULL, '20', '1');

-- JEUX DE DONNÉES FOODLAND
    -- Comment mettre à jour la table produit (stock et facture Foodland) grâce à la table lignecommandes ?
    -- Quand accepter_ou_refuser_commande BOOLEAN NULL (de la table commande), est TRUE càd qu’une commande ou livraison acceptée
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
    */
    
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
-- JEUX DE DONNÉES FOODLAND
    /*
        INSERT INTO `typecommande` (`id_type_commande`, `lib_type_commande`)
        VALUES (NULL, 'Client'), (NULL, 'Fournisseur');
    */

-- commande
    CREATE TABLE commande (
      	id_commande INT(11) NOT NULL AUTO_INCREMENT,
        nom_commercial_commande varchar(100) NULL,
        nom_acheteur_commande varchar(100) NULL,

        nom_preparateur_commande varchar(100) NULL,
      	nom_delivreur_commande varchar(100) NULL,
        -- Et le réceptionneur de livraison ?
      	nom_receptionneur_commande varchar(100) NULL,

      	date_creation_commande date NOT NULL, -- quand bouton « Valider la commande » est cliqué alors insérer now() et déclencher le reste
      	date_preparation_commande date NULL,
      	date_retrait_commande date NULL, -- date_fin_commande implique BOOL not null
            -- Et la date de réception de la livraison ?
            -- INUTILE je pense car date retrait = date livraison
            -- date_reception_commande date DEFAULT NULL, serait un meilleur nommage

        -- Création d’un booléen qui permet de :
            -- savoir si la commande est acceptée
                -- en retrait client
                -- en réception livraison
            -- récupérer toutes les factures
            -- CODE : si TRUE alors :
                    -- effacer la commande des JTable concernés
                    -- afficher Facture de ... 
        accepter_ou_refuser_commande BOOLEAN NULL, -- permet la transformation de la commande en facture client ou fournissseur

      	fk_id_type_commande INT(11) NULL,
      	-- FAIT BUGUER : id_facture varchar(50) DEFAULT NULL,
      	fk_id_entreprise INT(11) NOT NULL,

        PRIMARY KEY ( id_commande ),
        
        FOREIGN KEY ( fk_id_entreprise ) REFERENCES entreprise(id_entreprise ) ON DELETE CASCADE,
            -- Est-ce que si une entreprise (la mère) est effacée, on efface ses factures (ses filles) ?
            -- NON !!!
            -- Je laisse mais y a un bug métier
        FOREIGN KEY ( fk_id_type_commande ) REFERENCES typecommande(id_type_commande ) ON DELETE NO ACTION ON UPDATE CASCADE
            -- OK.
            -- Mais en fait du coup, personne ne devrait pouvoir modifier Client / Fournisseur
            -- quand un système de gestion facture est mis en place.
            
        -- FAIT BUGUER : FOREIGN KEY ( id_facture ) REFERENCES facture(id_facture )
    );
-- JEUX DE DONNÉES FOODLAND / Requète test : date, Mac Do, Client 
    /*
        INSERT INTO `commande` (`id_commande`, `nom_commercial_commande`, `nom_acheteur_commande`, `nom_preparateur_commande`, `nom_delivreur_commande`, `nom_receptionneur_commande`, `date_creation_commande`, `date_preparation_commande`, `date_retrait_commande`, `accepter_ou_refuser_commande`, `fk_id_type_commande`, `fk_id_entreprise`) 
        VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NOW(), NULL, NULL, NULL, '1', '1');
    */

-- commande_contient_produit TABLE MANY TO MANY
        -- historique achat / vente == historique des factures
        -- ne doit être utilisée que 
        -- si accepter_ou_refuser_commande BOOLEAN est VRAI
        -- alors on peut enregistrer les quantités
    CREATE TABLE commande_contient_produit (
      	fk_id_produit INT(11) NOT NULL,
    	fk_id_commande INT(11) NOT NULL,

        -- PRIMARY KEY ( fk_id_commande, fk_id_produit ), 
            -- J’ai commenté PRIMARY KEY
            -- Erreur chaude à expliquer
            -- Recherche Google : sql #1062 - duplicate entry => https://bobcares.com/blog/error-no-1062/ 
        
        FOREIGN KEY ( fk_id_produit ) REFERENCES produit(id_produit ),
        FOREIGN KEY ( fk_id_commande) REFERENCES commande(id_commande ) ON DELETE CASCADE
        -- ? Je ne peux plus DELETE une commande puisque c’est une facture ?
        -- ? De plus, si on  DELETE une commande alors on DELETE produit
        -- à cause de PRIMARY KEY ( fk_id_commande, fk_id_produit ) ?
    );
-- JEUX DE DONNÉES FOODLAND
    /*
        INSERT INTO `commande_contient_produit` (`fk_id_produit`, `fk_id_commande`)
        VALUES ('3', '1'), ('4', '1'), ('5', '1'), ('6', '1'), ('7', '1'), ('8', '1');
    */

-- facture TABLE INUTILE effacée
    -- COURS : https://openclassrooms.com/fr/courses/6938711-modelisez-vos-bases-de-donnees/7562106-transformez-vos-relations-d-heritage
        -- Transformez votre héritage par référence
            -- table classe mère commande
            -- table classe fille facture
            -- clés étrangères dans les tables des classes filles permettront de référencer la table de la classe mère.
                -- quand le BOOL commande_facturé sera à TRUE
            -- clé primaire de chaque table fille doit être la même clé primaire que la table mère.
            -- Chacune des clés primaires des tables filles est donc également une clé étrangère qui référence la table mère.
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





















/*-----------------------------------------------------------------------------
TABLE ci-dessous AJOUTÉ PAR TANGUY car oublié
-- --------------------------------------------------------------------------- */
-- categorie_produit
    CREATE TABLE categorie_lignecommandes (
        id_categorie_lignecommandes INT(11) NOT NULL AUTO_INCREMENT,
        lib_categorie_lignecommandes char (50) NOT NULL UNIQUE,

        PRIMARY KEY ( id_categorie_lignecommandes )
    );
-- JEUX DE DONNÉES FOODLAND
    /*
        INSERT INTO `categorie_lignecommandes` (`lib_categorie_lignecommandes`) VALUES
          ('Conserves'),
          ('Surgelés'),
          ('Emballages'),
          ('Boissons'),
          ('Sauces'),
          ('Entretien');
    */

-- lignecommandes : REPRÉSENTE LES EN COURS (en quantité) ET LES CATALOGUE FOODLAND / FOURNISSEURS
    -- produit : REPRÉSENTE LE STOCK FOODLAND ET TOUTES LES FACTURES
    CREATE TABLE lignecommandes (
      	id_ligne_commande INT(11) NOT NULL AUTO_INCREMENT,
      	code_barre_ligne_commande varchar(20) NOT NULL,

/*-----------------------------------------------------------------------------
designation_ligne_commande AJOUTÉ PAR TANGUY car oublié
-- --------------------------------------------------------------------------- */
        designation_ligne_commande varchar(50) NOT NULL, -- Oublié
      	
        prix_unitaire_ligne_commande DECIMAL (11,2) UNSIGNED NULL, -- double NOT NULL,
        poids_quantite_ligne_commande DECIMAL(20,2) UNSIGNED NULL, -- int(11) NOT NULL,
      	TVA_ligne_commande ENUM ('0', '2.1', '5.5', '10', '20') NULL, -- TVA float NOT NULL,

      	fk_id_commande INT(11) NOT NULL,
        -- fk_id_produit INT(11) NOT NULL,
/*-----------------------------------------------------------------------------
EMBROUILLE ci-dessus
    - Une lignecommande n’est pas lié à un seul et un seul produit. Quoi que !!?
    - En tout cas un produit est lié à un ou n lignecommande
-- --------------------------------------------------------------------------- */

/*-----------------------------------------------------------------------------
fk_id_categorie_lignecommandes AJOUTÉ PAR TANGUY sinon pas de catégorie
-- --------------------------------------------------------------------------- */
        fk_id_categorie_lignecommandes INT(11) NOT NULL,

        PRIMARY KEY ( id_ligne_commande ),

        FOREIGN KEY ( fk_id_commande ) REFERENCES commande(id_commande ) ON DELETE CASCADE,
        FOREIGN KEY ( fk_id_categorie_lignecommandes ) REFERENCES categorie_lignecommandes( id_categorie_lignecommandes ) ON DELETE CASCADE


        -- FOREIGN KEY ( fk_id_produit ) REFERENCES produit(id_produit ) ON DELETE CASCADE,
/*-----------------------------------------------------------------------------
EMBROUILLE 2 ci-dessus
    ça bug : #1364 - Field 'fk_id_produit' doesn't have a default value
    on ne peut pas car on peut refuser une commande donc les lignes de commandes
-- --------------------------------------------------------------------------- */       
-- ? Je ne comprends par les ON DELETE CASCADE ?
/*-----------------------------------------------------------------------------
EMBROUILLE 2 ci-dessus

    IL M’EFFACE MON STOCK AVEC produit(id_produit ) ON DELETE CASCADE !!!!
    LE STOCK SE TROUVE DANS LA TABLE « PRODUIT »
    NORMALEMENT ÇA PEUT PAS CAR 
    - LA TABLE « COMMANDE » NE S’EFFACERA JAMAIS
    - À CAUSE DU FAIT QUE LA TABLE « FACTURE » HÉRITE DE LA TABLE COMMANDE GRÂCE AU BOOLEAN
    - DONC ELLE EST À LA FOIS « COMMANDE » ET « FACTURE »
-- --------------------------------------------------------------------------- */


    );
/*-----------------------------------------------------------------------------
jeux de données AJOUTÉ PAR TANGUY car oublié
    - tous les articles doivent être liés à une commande qui peut être : en cours ou refusée ou facturée.
-- --------------------------------------------------------------------------- */
-- JEUX DE DONNÉES FOODLAND
-- C’est ici les articles de Momo car la table produit correspond à facture et stock quand BOOL true
-- 

/*
        INSERT INTO `lignecommandes` (`code_barre_ligne_commande`, `designation_ligne_commande`, `prix_unitaire_ligne_commande`, `poids_quantite_ligne_commande`, `TVA_ligne_commande`, `fk_id_categorie_lignecommandes`)
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
    */


-- role
    CREATE TABLE roles (
      	id_role INT(11) NOT NULL AUTO_INCREMENT,
      	lib_role varchar(25) NOT NULL UNIQUE,

       	PRIMARY KEY ( id_role )
    );
-- JEUX DE DONNÉES FOODLAND
    /*    
        INSERT INTO `roles` (`lib_role`) 
            VALUES ('admin'), ('gerant'), ('acheteur'), ('vendeur'), ('preparateur_commande'),('delivreur_commande'),('receptionneur'),('livreur');
            -- de prépa à livreur c’est inutile
        INSERT INTO `roles` (`id_role`, `lib_role`)
            VALUES (NULL, 'magasinier');
    */

-- utilisateur
    CREATE TABLE utilisateur (
    	id_user INT(11) NOT NULL AUTO_INCREMENT,
      	nom_user varchar(50) NOT NULL,
      	prenom_user varchar(50) NOT NULL,
      	adresse_user varchar(200) NULL, -- on s’en fout
      	code_postal_user varchar(5) NULL, -- on s’en fout
      	ville_user varchar(20) NULL, -- on s’en fout
      	tel_user varchar(15) NOT NULL, -- IMPORTANT : numéro de poste de travail
      	mail_user varchar(50) NOT NULL,
    	photo_user BLOB NULL, -- attention
        	/*login_compte varchar(50) NOT NULL,
        	password_compte varchar(50) NOT NULL,*/

        fk_id_role INT(11) NULL,

        PRIMARY KEY ( id_user ),

        FOREIGN KEY ( fk_id_role ) REFERENCES roles(id_role) ON UPDATE CASCADE
        
        -- FOREIGN KEY ( password_compte ) REFERENCES compte ( password_compte ) -- ? c t quoi l’idée ?
    );
-- JEUX DE DONNÉES FOODLAND
    /*
        INSERT INTO `utilisateur` (`nom_user`, `prenom_user`, `tel_user`, `mail_user`, `fk_id_role`) 
        VALUES 
            ('admins', 'pierre', '0145450001', 'pierreadmins@foodland.fr', '1'),
            ('gerand', 'paul', '0145450000', 'paulgerand@foodland.fr', '2'),
            ('vendor','john', '0145450010', 'johnvendor@foodland.fr', '3'),
            ('achetur', 'jacques', '0145450020', 'pierreadmins@foodland.fr', '4'),
            ('magasinne','tim', '0145450050', 'timmagasinne@foodland.fr', '9'),
            ('magase','tom', '0145450050', 'tommagase@foodland.fr', '9'),
            ('maga','thomas', '0145450050', 'thomasmaga@foodland.fr', '9');
    */

-- compte
    CREATE TABLE compte (
        id_compte INT(11) NOT NULL AUTO_INCREMENT,
      	login_compte varchar(200) NOT NULL,
      	password_compte varchar(200) NOT NULL,

        fk_id_user INT(11) NOT NULL,

        PRIMARY KEY ( id_compte ),
        FOREIGN KEY ( fk_id_user ) REFERENCES utilisateur(id_user) ON DELETE CASCADE
    );
-- JEUX DE DONNÉES FOODLAND
    /*
        INSERT INTO `compte` (`login_compte`, `password_compte`, `fk_id_user`) 
        VALUES 
            ('admins', '0000', '1'),
            ('gerand', '0000', '2'),
            ('vendor', '0000', '3'),
            ('achetur', '0000', '4'),
            ('magasinne', '0000', '5'),
            ('magase', '0000', '6'),
            ('maga', '0000', '7');
    */







/*********************************
 *
 * INSERTION DES DONNÉES FOODLAND
 * 
 *********************************/

    -- V1 ENTREPRIS BUG
        -- 1ère insertion à la création
        /*
            INSERT INTO entreprise (nom_entreprise) VALUES ('Mac Do');
            SET @entreprise_id = LAST_INSERT_ID();

            INSERT IGNORE INTO type_entreprise (lib_type_entreprise) VALUES ('Fournisseur');
            SET @type_entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(@type_entreprise_id, @entreprise_id);

            INSERT IGNORE INTO type_entreprise (lib_type_entreprise) VALUES ('Client');
            SET @type_entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(@type_entreprise_id, @entreprise_id);
        -- */
        
        -- 2ème insertion en production
        /*

            INSERT INTO entreprise (nom_entreprise) VALUES ('Resto du coeur');
            SET @entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(1, @entreprise_id);
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(2, @entreprise_id);

            INSERT INTO entreprise (nom_entreprise) VALUES ('Foodland');
            SET @entreprise_id = LAST_INSERT_ID();
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(1, @entreprise_id);
            INSERT INTO categorie_entreprise (fk_id_type_entreprise, fk_id_entreprise) VALUES(2, @entreprise_id);
        -- */

    -- V2 ENTREPRISE

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




        INSERT INTO `categorie_lignecommandes` (`lib_categorie_lignecommandes`) VALUES
            ('Conserves'),
            ('Surgelés'),
            ('Emballages'),
            ('Boissons'),
            ('Sauces'),
            ('Entretien');

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
            ('vendor','john', '0145450010', 'johnvendor@foodland.fr', '3'),
            ('achetur', 'jacques', '0145450020', 'pierreadmins@foodland.fr', '4'),
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