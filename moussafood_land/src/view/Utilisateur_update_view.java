package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import controller.Compte_controller;
import controller.Utilisateur_controller;
import model.Compte_model;
import model.Utilisateur;
import model.Utilisateur_model;

public class Utilisateur_update_view {
	
	/*
	 * Attributs des écrans
	 */
	// nom_user prenom_user tel_user mail_user
	// login_compte password_compte
	private JLabel nom_user_JLabel, prenom_user_JLabel, tel_user_JLabel, mail_user_JLabel, login_compte_JLabel, password_compte_JLabel;
	private JTextField nom_user_JTextField, prenom_user_JTextField, tel_user_JTextField, mail_user_JTextField, login_compte_JTextField, password_compte_JTextField;
	private JButton valider_Button;
	
	/*
	 *  Getters et setters 
	 */
	public JTextField getTel_user_JTextField() {
		return tel_user_JTextField;
	}

	public void setTel_user_JTextField(JTextField tel_user_JTextField) {
		this.tel_user_JTextField = tel_user_JTextField;
	}

	/*
	 * Constructeur
	 */
	public Utilisateur_update_view(Utilisateur_model utilisateur_model) {
		
//		Utilisateur_controller utilisateur_controller = new Utilisateur_controller();
//		Utilisateur_model utilisateur = new Utilisateur_model();
		Utilisateur_model utilisateur = utilisateur_model;
		System.out.println(utilisateur.getNom_utilisateur());
		
		
//		JFrame f = new JFrame();
//		JPanel contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		f.setContentPane(contentPane);
//		
//		
//		JDialog d = new JDialog(f , "Données personnelles", true);
//		d.setLayout( new SpringLayout() ); // ? le bouton ne s’affiche pas si null
//		
//		JButton b = new JButton ("OK");  
//		b.addActionListener ( new ActionListener(){  
//			public void actionPerformed( ActionEvent e )  
//			{  
//				/*DialogExample.*/d.setVisible(false);  
//			}  
//		});  
//		d.add( new JLabel ("Click button to continue."));  
//		d.add(b);
//
//		
//		d.setSize(450,700);
//		d.setResizable(false);
//		d.setLocationRelativeTo(null);
//		d.setVisible(true);  

		
		
		/*
		 *  Créer Objet de type Jframe 
		 */	
		JFrame frame = new JFrame("Modifier mes informations personnelles");
		
		// Afficher
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);		
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		
		/* 
		 * Créer les éléments
		 * - labels
		 * - textField
		 * - bouton
		 * Et les organiser dans la fenêtre
		 */
		tel_user_JLabel = new JLabel("Téléphone");
//		nom_user_JLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
//		nom_user_JLabel.setBounds(40, 39, 108, 34);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tel_user_JLabel, 15, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tel_user_JLabel, 10, SpringLayout.WEST, contentPane);
		contentPane.add(tel_user_JLabel);

		JLabel label_password_compte = new JLabel("Mot de passe");
//		label_password_compte.setFont(new Font("Tahoma", Font.BOLD, 14));
//		label_password_compte.setBounds(40, 98, 84, 34);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_password_compte , 24, SpringLayout.SOUTH, tel_user_JLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_password_compte , 10, SpringLayout.WEST, contentPane);
		contentPane.add(label_password_compte);

		JPasswordField passwordField_password_compte = new JPasswordField();
//		passwordField_password_compte.setBounds(149, 107, 96, 20);
//		passwordField_password_compte.setColumns(10);
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordField_password_compte, -5, SpringLayout.NORTH, label_password_compte);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField_password_compte, 6, SpringLayout.EAST, label_password_compte);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField_password_compte, -10, SpringLayout.EAST, contentPane);
		contentPane.add(passwordField_password_compte);
		passwordField_password_compte.setColumns(10);


		tel_user_JTextField = new JTextField(utilisateur_model.getTel_user());
//		textField_login_compte.setColumns(10);
//		textField_login_compte.setBounds(149, 48, 96, 20);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tel_user_JTextField, -5, SpringLayout.NORTH, tel_user_JLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, tel_user_JTextField, 54, SpringLayout.EAST, tel_user_JLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, tel_user_JTextField, 0, SpringLayout.EAST, passwordField_password_compte);
		contentPane.add(tel_user_JTextField);
		tel_user_JTextField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("afficher le mot de passe");
		sl_contentPane.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 2, SpringLayout.SOUTH, passwordField_password_compte);
		sl_contentPane.putConstraint(SpringLayout.EAST, chckbxNewCheckBox, 0, SpringLayout.EAST, passwordField_password_compte);
		contentPane.add(chckbxNewCheckBox);
		
		JButton button_valider = new JButton("Valider");
//		button_valider.setBounds(156, 170, 89, 23);
		sl_contentPane.putConstraint(SpringLayout.WEST, button_valider, 0, SpringLayout.WEST, tel_user_JLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, button_valider, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, button_valider, -15, SpringLayout.EAST, contentPane);
		contentPane.add(button_valider);

		/*
		 *  Ajouter un Action listener au bouton
		 */
		button_valider.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				/*
				 * PROCESSUS pour update user 
				 * view
				 * - ...
				 * controller
				 * - ...
				 */
							
				/* DAO
				 * Instanciation du controleur (SCRUD)
				 */
				Utilisateur_controller utilisateur_controller = new Utilisateur_controller();
				
				/* DAO
				 * Lancer le update
				 */
				String tel = tel_user_JTextField.getText().toString();
				utilisateur.setTel_user(tel);
				try {
					
					System.out.println(tel);
					utilisateur_controller.update_Utilisateur(utilisateur);
					System.out.println("try ok");
					frame.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		/*
		 * Afficher
		 */
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.setSize(450,700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
