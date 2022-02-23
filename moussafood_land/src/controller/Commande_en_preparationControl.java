package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.DBConnexion;

public class Commande_en_preparationControl {
	String query;
	/*récuperation de l'objet connexion du package DBconnexion*/
	Connection cn=  new DBConnexion().connect();

	
	private int id;
	/*Selectionner tous les enregistrements*/


	/* Insertion de departement*/
	public ResultSet selectCommandes(int id) throws SQLException 
	{
		this.id = id;
		Statement stmt= cn.createStatement();
		ResultSet rs= null;
		try{
			query = "select designation_ligne_commande, categorie_produit.lib_categorie_produit,poids_quantite_ligne_commande FROM lignecommandes, categorie_produit WHERE fk_id_commande = "+id;
			rs = stmt.executeQuery(query);

		}catch(Exception e)
		{
			System.out.println(e);
		}

		return rs;

	}	

}
