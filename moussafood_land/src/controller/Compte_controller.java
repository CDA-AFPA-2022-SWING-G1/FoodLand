package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connexion.ConnectionBDD;
import model.Compte_model;
import view.Authentification;
import view.Gerant_view;
import view.Vendeur_view;
import view.tableau_bord_Magasinier;

public class Compte_controller {
	
	String query;
	/*
	 *  Récupération de l'objet ConnectionBDD du package connexion
	 */
	Connection connection =  new ConnectionBDD().getconnect();
	
	public void getAuthentification( Compte_model compte_model, Authentification authentification/*_view*/ ) /*throws SQLException*/ {
		
		try {
			/*
			 *  Déclarer les variables
			 */
			String sql_valider_le_compte = "select * from `compte` "
					+ "where login_compte=? and password_compte=?";	
			
			PreparedStatement ps = connection.prepareStatement(sql_valider_le_compte); // preparer_la_requete
			ResultSet rs = null;

			/* TESTER LOGIN ET MDP USER
			 * Stocker le résultat de l’execution de la requête
			 * dans la variable rs 
			 */
			ps.setString(1, compte_model.getLogin_compte());
			ps.setString(2, compte_model.getPassword_compte());
			System.out.println("PACKAGE COMPTE CONTROLLER sql_valider_le_compte : " + ps);
			rs = ps.executeQuery();
			// System.out.println("rs :" + rs.getInt("fk_id_user"));
				/* ******* BUG *************
				 * Tanguy : l’appli bug ici
				 *  System.out.println("rs :" + rs.getInt("fk_id_user"));
				 *  java.sql.SQLException: Before start of result set
				 *  Le println fait bugger l’appli car
				 *  le user n’a pas encore rempli les champs
				 */
			// System.out.println("rs :" + rs.next()); // return a Bool
				/* ******* BUG *************
				 * Ne pas oublier de commenter ce println en prod
				 * sinon le if ne fonctionne pas
				 */
			
			if (rs.next()) { 
				/* ******* BUG *************
				 * Pourquoi le rs.next ne fonctionnait pas
				 * le 1er rs.next() renvoie true
				 * le 2ème renvoie false
				 * donc il faut commenter le println qui se trouve juste au-dessus du if
				 */
				int fk_id_user = rs.getInt("fk_id_user");
				System.out.println("rs valider compte : " + fk_id_user);
				System.out.println("rs fk_id_utilisateur : " + fk_id_user);

				JOptionPane.showMessageDialog(authentification.getFrame(),"Bonjour prenom_utilisateur.");						
				
				/*
				 * ALGO pour récupérer le rôle
				 */
				String sql_tableau_de_bord = "select fk_id_role from `utilisateur` "
						+ "where id_user=" + fk_id_user;
				System.out.println("sql_tableau_de_bord : " + sql_tableau_de_bord);
				
				Statement stmt= connection.createStatement();
				ResultSet resultat_requete = stmt.executeQuery(sql_tableau_de_bord); 
				resultat_requete.next(); // Bool doit être true
				System.out.println( "resultat_requete fk_id_role : " + resultat_requete.getString("fk_id_role") );
				
				/*
				 * ALGO pour envoyer vers le bon TdB
				 */
				switch (resultat_requete.getString("fk_id_role")) {
				// Admin
				case "1": 
					System.out.println("1");
					break;
				// Gerant
				case "2":
					System.out.println("2 + fk_id_user = " + fk_id_user);
					authentification.getFrame().dispose();
					new Gerant_view(); /*fk_id_user*/
					break;
				// Acheteur
				case "3": 
					System.out.println("ok 3");
					break;
				// Vendeur
				case "4":
					System.out.println("4 + fk_id_user = " + fk_id_user);
					authentification.getFrame().dispose();
					new Vendeur_view(fk_id_user); /*fk_id_user*/
					break;
				// Magasinier
				case "9":
					System.out.println("9 + fk_id_user = " + fk_id_user);
					authentification.getFrame().dispose();
					new tableau_bord_Magasinier(); // fk_id_user
					break;
				default :
					System.out.println("n’appartient à aucun rôle !!!");
					JOptionPane.showMessageDialog(authentification.getFrame(), "Vous ne faites pas partie de l’entreprise.", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				
				//frame.setVisible(false); // La fenêtre est cachée pas fermée
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Si on clic sur la croix toute l’appli se ferme
				//authentification.getFrame().dispose(); // ferme la fenêtre en cours (libère les ressources
			} else {
				System.out.println("rs :" + rs.next());
				JOptionPane.showMessageDialog(authentification.getFrame(), "Login ou mot de passe incorrect.\n Veuillez réessayer.", "Alert", JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(authentification.getFrame(), "Nous avons été déconnectés...\n Si cela persiste contactez l’administrateur.", "Alert", JOptionPane.WARNING_MESSAGE);
		}	
		
	}
}
