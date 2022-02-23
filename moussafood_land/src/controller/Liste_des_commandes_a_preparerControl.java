
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import View.Liste_des_commandes_a_preparerView;
import connexion.DBConnexion;

public class Liste_des_commandes_a_preparerControl {

	String query;
	/*récuperation de l'objet connexion du package DBconnexion*/
	Connection cn=  new DBConnexion().connect();

	
	
	/*Selectionner tous les enregistrements*/


	/* Insertion de departement*/
	public ResultSet selectCommandes() throws SQLException 
	{
		Statement stmt= cn.createStatement();
		ResultSet rs= null;
		try{
			query = "SELECT id_commande, date_creation_commande, fk_id_entreprise FROM commande ";
			rs = stmt.executeQuery(query);

		}catch(Exception e)
		{
			System.out.println(e);
		}

		return rs;

	}	
}
