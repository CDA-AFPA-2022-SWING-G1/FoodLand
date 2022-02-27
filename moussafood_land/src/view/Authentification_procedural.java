package view;

/*
 * Import java
 * - IHM
 * - SQL ???
 */
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Import Application
 * - model
 * - controller
 */
import model.Compte_model;
import controller.AdminController;
import controller.Compte_controller;
import connexion.ConnectionBDD;


public class Authentification_procedural {

	/*
	 * Attributs des écrans
	 */
	private JFrame frame;
	private JLabel labelTitle; // Titre de la fenêtre : Connexion
	private JLabel label_login_compte, label_password_compte;
	private JTextField textField_login_compte;
	//	private JTextField passwordField_password_compte; // FAUX
	private JPasswordField passwordField_password_compte;
	private JButton button_valider;

	/*
	 *  Getters et setters 
	 */
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JLabel getLabelTitle() {
		return labelTitle;
	}

	public void setLabelTitle(JLabel labelTitle) {
		this.labelTitle = labelTitle;
	}

	public JLabel getLabel_login_compte() {
		return label_login_compte;
	}

	public void setLabel_login_compte(JLabel label_login_compte) {
		this.label_login_compte = label_login_compte;
	}

	public JLabel getLabel_password_compte() {
		return label_password_compte;
	}

	public void setLabel_password_compte(JLabel label_password_compte) {
		this.label_password_compte = label_password_compte;
	}

	public JTextField getTextField_login_compte() {
		return textField_login_compte;
	}

	public void setTextField_login_compte(JTextField textField_login_compte) {
		this.textField_login_compte = textField_login_compte;
	}

	public JPasswordField getPasswordField_password_compte() {
		return passwordField_password_compte;
	}

	public void setPasswordField_password_compte(JPasswordField passwordField_password_compte) {
		this.passwordField_password_compte = passwordField_password_compte;
	}

	public JButton getButton_valider() {
		return button_valider;
	}

	public void setButton_valider(JButton button_valider) {
		this.button_valider = button_valider;
	}
	
	/*
	 * Constructeur
	 */	
	public Authentification_procedural() {

		/*
		 *  Créer Objet de type Jframe 
		 */	
		frame = new JFrame();

		/* 
		 * Créer les éléments
		 * - labels
		 * - textField
		 * - bouton
		 * Et les organiser dans la fenêtre
		 */
		label_login_compte = new JLabel("Votre login");
		label_login_compte.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_login_compte.setBounds(40, 39, 108, 34);

		label_password_compte = new JLabel("Mot de passe");
		label_password_compte.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_password_compte.setBounds(40, 98, 84, 34);

		textField_login_compte = new JTextField();
		textField_login_compte.setColumns(10);
		textField_login_compte.setBounds(149, 48, 96, 20);

		passwordField_password_compte = new JPasswordField();
		passwordField_password_compte.setBounds(149, 107, 96, 20);
		passwordField_password_compte.setColumns(10);

		button_valider = new JButton("Valider");
		button_valider.setBounds(156, 170, 89, 23);
		
		
		/*
		 * Ajouter les composants à la fenetre
		 */
		frame.getContentPane().add(label_login_compte);
		frame.getContentPane().add(label_password_compte);
		frame.getContentPane().add(textField_login_compte);
		frame.getContentPane().add(passwordField_password_compte);
		frame.getContentPane().add(button_valider);
		
		/*
		 *  Ajouter un Action listener au bouton
		 */  
		button_valider.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				/*
				 * PROCESSUS pour ouvrir le tableau de bord de l’utilisateur (view)
				 * - Récupérer les données saisies par l’utilisateur
				 * - Tester son login et son mdp (controller vs view)
				 * - Identifier l’utilisateur grâce fk_id_user de compte
				 * - Intéroger la table utilisateur pour connaitre son role (fk_id_role)
				 * - Renvoyer l’user vers son tableau de bord
				 */
				
				
				/*
				 * Récupération des données saisies
				 */
				String login_compte = textField_login_compte.getText().toString();
//				System.out.println(login_compte);
				
//				String password_compte = passwordField_password_compte.getText(); // getText() deprecated
				String password_compte = String.valueOf(passwordField_password_compte.getPassword());
//				System.out.println(password_compte);
				
				
				/* TANGUY PAS ENCORE UTILISÉ
				 * Instanciation de l’objet Compte
				 * du package model
				 */
				Compte_model compte_model = new Compte_model();
				compte_model.setLogin_compte(login_compte);
				compte_model.setPassword_compte(password_compte);
				
				/*
				 * Appel du controleur Dao / CRUD
				 */
				Compte_controller compte_controller = new Compte_controller();
				
				try {
					/* MOUSSA
					 * Appel au controleur 
					 */
					//AdminController admincontrol = new AdminController();
					//admincontrol.comparaison(login_compte, password_compte);
					
					
					/*
					 *  Déclarer les variables
					 */
					String sql_valider_le_compte = "select * from `compte` "
							+ "where login_compte=? and password_compte=?";	
					Connection connection =  new ConnectionBDD().getconnect();
					PreparedStatement ps = connection.prepareStatement(sql_valider_le_compte); // preparer_la_requete
					ResultSet rs = null;

					/* TESTER LOGIN ET MDP USER
					 * Stocker le résultat de l’execution de la requête
					 * dans la variable rs 
					 */
					ps.setString(1, login_compte);
					ps.setString(2, password_compte);
					System.out.println("sql_valider_le_compte : " + ps);
					rs = ps.executeQuery();
						/* 
						 * Tanguy : l’appli bug ici
						 *  System.out.println("rs :" + rs.getInt("fk_id_user"));
						 *  java.sql.SQLException: Before start of result set
						 *  Le println fait bugger l’appli car
						 *  le user n’a pas encore rempli les champs
						 */
					// System.out.println("rs :" + rs.next()); // return a Bool
						/* Tanguy : attention
						 * Ne pas oublier de commenter ce println en prod
						 * sinon le if ne fonctionne pas
						 */
					
					if (rs.next()) { 
						/* Tanguy : pourquoi le rs.next ne fonctionnait pas
						 * le 1er rs.next() renvoie true
						 * le 2ème renvoie false
						 * donc il faut commenter le println qui se trouve juste au-dessus du if
						 */
						int fk_id_user = rs.getInt("fk_id_user");
						System.out.println("rs valider compte : " + fk_id_user);
						System.out.println("rs fk_id_utilisateur : " + fk_id_user);

						JOptionPane.showMessageDialog(frame,"Bonjour prenom_utilisateur.");						
						
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
						
						case "1":
							System.out.println("1");
							break;
						case "2":
							System.out.println("2");
							break;
						case "3":
							System.out.println("ok 3");
							break;
						case "4":
							System.out.println("4");
							break;
						case "9":
							System.out.println("9 + fk_id_user = " + fk_id_user);
							new tableau_bord_Magasinier(); // fk_id_user
							break;
						default :
							System.out.println("n’appartient à aucun cas !!!");
						}
						
						//frame.setVisible(false); // La fenêtre est cachée pas fermée
						//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Si on clic sur la croix toute l’appli se ferme
						frame.dispose(); // ferme la fenêtre en cours (libère les ressources
						
					} else {
						System.out.println("rs :" + rs.next());
						JOptionPane.showMessageDialog(frame, "Nom de famille ou Mot de passe incorrect, Veuillez réessayer", "Alert", JOptionPane.WARNING_MESSAGE);
					}
					
				} catch (SQLException ee1) {
					// TODO Auto-generated catch block
					ee1.printStackTrace();
					
				}
			}
		});
		
		// Afficher
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}

}