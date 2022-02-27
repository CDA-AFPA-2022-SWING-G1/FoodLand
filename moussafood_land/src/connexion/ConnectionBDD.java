package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionBDD {

	/* 
	 * Déclaration d'une variable connection
	 */
	private Connection connection;

	/* 
	 * fonction qui retourne un objet connection
	 */
	public Connection getconnect() {

		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Success");
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("Driver not found" + cnfe);
//            System.out.println("Probleme de connexion");
            JOptionPane.showMessageDialog(null, "Problème de connexion", "Alert", JOptionPane.WARNING_MESSAGE);
		}

		String url="jdbc:mysql://localhost:8889/foodland2";        

		try{
			connection = DriverManager.getConnection(url,"root","root");
			System.out.println("Connexion reussie");
		}
		catch(SQLException se){
			System.out.println("Soucis de connexion");
			JOptionPane.showMessageDialog(null, "Vous n’êtes pas connecté(e) à la BDD", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		/*
		 * Retourne l'objet de type connexion
		 */
		return connection;
	}
	/* go to controller for SCRUD ;) */ 
}