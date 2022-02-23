package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;

import controller.AdminController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Authentification {

	private JFrame frame;
	private JTextField passwordField;
	private JTextField tfIdentifiant;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("mdp");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(40, 98, 84, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("login");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(40, 39, 108, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JTextField();
		passwordField.setBounds(149, 107, 96, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		tfIdentifiant = new JTextField();
		tfIdentifiant.setColumns(10);
		tfIdentifiant.setBounds(149, 48, 96, 20);
		frame.getContentPane().add(tfIdentifiant);
		
		JButton VALIDER = new JButton("VALIDER");
		VALIDER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = tfIdentifiant.getText().toString();
	           	 
	           	 System.out.println(login);
	                String mdp = passwordField.getText();
	                System.out.println(mdp);
	                
	                
	                // Appel au controleur 
	                
	                AdminController  admincontrol= new AdminController();
	                try {
						admincontrol.comparaison(login, mdp);
					} catch (SQLException ee1) {
						// TODO Auto-generated catch block
						ee1.printStackTrace();
					}
			}
		});
		VALIDER.setBounds(156, 170, 89, 23);
		frame.getContentPane().add(VALIDER);
		frame.setVisible(true);
	}
	
}
