package controller;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import View.tableau_bord_Magasinier;
import connexion.DBConnexion;

public class AdminController {
	
	static String query;
	/*r
	 * écuperation de l'objet connexion du package DBconnexion*/
	static Connection cn=  new DBConnexion().connect();
	public static boolean comparaison(String NomAdmin,String MdpAdmin) throws SQLException {
		boolean reussie=false;
		Statement stmt= cn.createStatement();
		
		try{
			//Contunue la requete
			query="SELECT * FROM compte";
			
			ResultSet resultat=stmt.executeQuery("SELECT * FROM compte");
			while(resultat.next())
			{
				if((NomAdmin.equals(resultat.getString("login_compte"))==true) && (MdpAdmin.equals(resultat.getString("password_compte"))==true))
				{
					reussie=true;
					//new AddDept();
					new tableau_bord_Magasinier();
					return reussie;
				}
				else{
					
					JOptionPane.showMessageDialog(null, "Erreur connexion");
				}
				
			}

		}catch(Exception e)
		{
			System.out.println(e);
		}
		return reussie;
		
	}


}
