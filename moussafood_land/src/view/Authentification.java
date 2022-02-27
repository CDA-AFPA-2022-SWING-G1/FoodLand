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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

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


public class Authentification {

	/*
	 * Attributs des écrans
	 */
	private JFrame frame;
	private JPanel contentPane;
	private JLabel labelTitle; // Titre de la fenêtre : Connexion
	private JLabel label_login_compte, label_password_compte;
	private JTextField textField_login_compte;
	//	private JTextField passwordField_password_compte; // FAUX
	private JPasswordField passwordField_password_compte;
	private JButton button_valider;
	private JCheckBox chckbxNewCheckBox;

	public void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
		this.chckbxNewCheckBox = chckbxNewCheckBox;
	}

	/*
	 *  Getters et setters 
	 */
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
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

	public JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
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
	public Authentification() {

		/*
		 *  Créer Objet de type Jframe 
		 */	
		frame = new JFrame();
		
		// Afficher
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 300, 200); //(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);		
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
//		frame.getContentPane().setLayout(null);

		/* 
		 * Créer les éléments
		 * - labels
		 * - textField
		 * - bouton
		 * Et les organiser dans la fenêtre
		 */
		label_login_compte = new JLabel("Login");
//		label_login_compte.setFont(new Font("Tahoma", Font.BOLD, 14));
//		label_login_compte.setBounds(40, 39, 108, 34);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_login_compte, 15, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_login_compte, 10, SpringLayout.WEST, contentPane);
		contentPane.add(label_login_compte);

		label_password_compte = new JLabel("Mot de passe");
//		label_password_compte.setFont(new Font("Tahoma", Font.BOLD, 14));
//		label_password_compte.setBounds(40, 98, 84, 34);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_password_compte , 24, SpringLayout.SOUTH, label_login_compte);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_password_compte , 10, SpringLayout.WEST, contentPane);
		contentPane.add(label_password_compte);

		passwordField_password_compte = new JPasswordField();
//		passwordField_password_compte.setBounds(149, 107, 96, 20);
//		passwordField_password_compte.setColumns(10);
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordField_password_compte, -5, SpringLayout.NORTH, label_password_compte);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField_password_compte, 6, SpringLayout.EAST, label_password_compte);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField_password_compte, -10, SpringLayout.EAST, contentPane);
		contentPane.add(passwordField_password_compte);
		passwordField_password_compte.setColumns(10);


		textField_login_compte = new JTextField();
//		textField_login_compte.setColumns(10);
//		textField_login_compte.setBounds(149, 48, 96, 20);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_login_compte, -5, SpringLayout.NORTH, label_login_compte);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_login_compte, 54, SpringLayout.EAST, label_login_compte);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_login_compte, 0, SpringLayout.EAST, passwordField_password_compte);
		contentPane.add(textField_login_compte);
		textField_login_compte.setColumns(10);


		button_valider = new JButton("Valider");
//		button_valider.setBounds(156, 170, 89, 23);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_valider, 0, SpringLayout.WEST, label_login_compte);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, button_valider, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, button_valider, -15, SpringLayout.EAST, contentPane);
		contentPane.add(button_valider);
		
		chckbxNewCheckBox = new JCheckBox("afficher le mot de passe");
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 2, SpringLayout.SOUTH, passwordField_password_compte);
		sl_contentPane.putConstraint(SpringLayout.EAST, chckbxNewCheckBox, 0, SpringLayout.EAST, passwordField_password_compte);
		contentPane.add(chckbxNewCheckBox);
		
		/*
		 * Ajouter les composants à la fenetre
		 */
//		frame.getContentPane().add(label_login_compte);
//		frame.getContentPane().add(label_password_compte);
//		frame.getContentPane().add(textField_login_compte);
//		frame.getContentPane().add(passwordField_password_compte);
//		frame.getContentPane().add(button_valider);
		
		/*
		 *  Ajouter un Action listener au bouton
		 */  
		button_valider.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				/*
				 * PROCESSUS pour ouvrir le tableau de bord de l’utilisateur 
				 * view
				 * - Récupérer les données saisies par l’utilisateur
				 * controller
				 * - Tester le login et le mdp de l’utilisateur
				 * - Identifier l’utilisateur grâce fk_id_user de compte
				 * - Intéroger la table utilisateur pour connaitre son role (fk_id_role)
				 * - Renvoyer l’user vers son tableau de bord
				 */
				
				/*
				 * Récupération
				 * - de données saisies
				 * - d’event
				 * - …
				 */
				String login_compte = textField_login_compte.getText().toString();
				//System.out.println(login_compte);
				String password_compte = String.valueOf(passwordField_password_compte.getPassword());
				//System.out.println(password_compte);
				
				/* DAO
				 * Instanciation de l’objet Compte
				 * du package model
				 * C’est une table de BDD
				 */
				Compte_model compte_model = new Compte_model();
				compte_model.setLogin_compte(login_compte);
				compte_model.setPassword_compte(password_compte);
				
				/* DAO
				 * Instanciation du controleur (SCRUD)
				 */
				Compte_controller compte_controller = new Compte_controller();
				
				/* DAO
				 * Lancer le test mdp / login
				 */
				compte_controller.getAuthentification(compte_model, Authentification/*.view*/.this);
				System.out.println("Je suis dans la view Authentification_view : compte_controller.getAuthentification(compte_model, Authentification/*.view*/.this);");
			}
		});
		
		/*
		 * Afficher
		 */
//		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
