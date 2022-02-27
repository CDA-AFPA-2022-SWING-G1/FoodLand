package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connexion.ConnectionBDD;
import model.Utilisateur_model;

public class Utilisateur_controller {
		
		String query;
		/*
		 *  Récupération de l'objet ConnectionBDD du package connexion
		 */
		Connection connection =  new ConnectionBDD().getconnect();
		
		/*
		 * Search : récupérer un utilisateur
		 */
		public Utilisateur_model get_Utilisateur(int id_user) throws SQLException {
			
//			int id = (int) idOjb;
//			Utilisateur utilisateur = null;
			
			int id = id_user;
			Utilisateur_model utilisateur = null;
			
			Statement stmt = connection.createStatement();
			ResultSet rs = null;
			
			try {
				String query = "SELECT * FROM utilisateur WHERE id_user = '" + id_user + "'";
				System.out.println(query);
				rs = stmt.executeQuery(query);
				
				while (rs.next()) {
					 //String nom_user = rs.getString("nom_user");
					 //System.out.println(nom_user);
					 
					 utilisateur = new Utilisateur_model(
							 rs.getInt("id_user"), 
							 rs.getString("nom_user"),
							 rs.getString("prenom_user"),
							 rs.getString("adresse_user"),
							 rs.getString("code_postal_user"),
							 rs.getString("ville_user"),
							 rs.getString("tel_user"),
							 rs.getString("mail_user"),
//							 rs.getString("photo_user"),
							 rs.getInt("fk_id_role")
//							 int id_utilisateur, 
//							 String nom_utilisateur, 
//							 String prenom_utilisateur,
//							 String adresse_utilisateur, 
//							 String code_postal_utilisateur, 
//							 String ville_utilisateur, 
//							 String tel_user,
//							 String mail_user, 
							 /*Blob photo_user,*/
//							 int fk_id_role
					);
				}
				rs.close();
				connection.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return utilisateur;
		}
		
		/*
		 * Create : insérer un nouvel utilisateur
		 */
		public void insert_Utilisateur(Utilisateur_model utilisateur_model) throws SQLException {
			
			Statement stmt= connection.createStatement();
			
			try {
				
				query = "INSERT INTO utilisateur(nom_user, prenom_user, adresse_user, code_postal_user, ville_user, tel_user, mail_user) " // photo_user, fk_id_role
						+ "VALUES ('"
						+ utilisateur_model.getNom_utilisateur() + "', '"
						+ utilisateur_model.getPrenom_utilisateur() + "', '"
						+ utilisateur_model.getAdresse_utilisateur() + "', '"
						+ utilisateur_model.getCode_postal_utilisateur() + "', '"
						+ utilisateur_model.getVille_utilisateur() + "', '"
						+ utilisateur_model.getTel_user() + "', '"
						+ utilisateur_model.getMail_user() + "')"
						;
				int newrow = stmt.executeUpdate(query);

				// test
				System.out.println(newrow);
				
				if(newrow>0) {
					//JOptionPane.showMessageDialog( null, this,"Informations insérées...", newrow );
					JOptionPane.showMessageDialog(null,"Votre nouvel employé(e) a bien été enregistrée."); 
				}
				
			} catch(Exception e) {
				
				System.out.println(e);
			}
			
			stmt.close();
			
		}
		
		/*
		 * Update : modifier un utilisateur
		 */
		public void update_Utilisateur(Utilisateur_model utilisateur_model) throws SQLException {
			
			Statement stmt= connection.createStatement();
			System.out.println(utilisateur_model.getTel_user());
			
			try {
//				"update dept set nom='"+ndep+"',ville='"+vdept+"', nbemp='"+nbdept+"' where nd='"+d2+"'";
//				"UPDATE `UTILISATEUR` SET IdUtilisateur=?,Mail=?,MotDePasse=?,Statut=?,IdRole=? WHERE IdUtilisateur=?");
				
//				nom_user, prenom_user, adresse_user, code_postal_user, ville_user, ..., mail_user  // photo_user, fk_id_role
				query = "UPDATE utilisateur SET tel_user = '" + utilisateur_model.getTel_user() 
				+ "' WHERE id_user = " + utilisateur_model.getId_utilisateur();
				System.out.println(query);
//						+ utilisateur_model.getNom_utilisateur() + "', '"
//						+ utilisateur_model.getPrenom_utilisateur() + "', '"
//						+ utilisateur_model.getAdresse_utilisateur() + "', '"
//						+ utilisateur_model.getCode_postal_utilisateur() + "', '"
//						+ utilisateur_model.getVille_utilisateur() + "', '"
//						+ utilisateur_model.getTel_user() + "', '"
//						+ utilisateur_model.getMail_user() + "')"
						;
				int row = stmt.executeUpdate(query);

				// test
				System.out.println(row);
				
				if(row>0) {
					//JOptionPane.showMessageDialog( null, this,"Informations insérées...", newrow );
					JOptionPane.showMessageDialog(null,"Vos informations personnelles ont bien été enregistrées.\n Enfin votre numéro de tel. Pour l’instant ;)"); 
				}
				
			} catch(Exception e) {
				
				System.out.println(e);
				
//				new SDialog("Echec", "La modification n'a pas reussie car " + e, "ok", "")
//				.setVisible(true);
//				throw new RuntimeException(e);
			}
			
			stmt.close();
			
		}

}
