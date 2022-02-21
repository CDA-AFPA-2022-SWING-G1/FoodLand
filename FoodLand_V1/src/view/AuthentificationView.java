package view;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JDesktopPane;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import model.Utilisateur;
import outils.HashMe;
import serviceMetiers.ControlUtilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AuthentificationView extends JFrame {
	
	private JTextField tfIdentifiant;
	private JTextField tfNom;
	private JPasswordField passwordField;
	
	private Utilisateur utilisateur;
	
	public AuthentificationView() {
		setPreferredSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 384, 261);
		desktopPane.setPreferredSize(new Dimension(30, 30));
		getContentPane().add(desktopPane);
		
		JLabel lblAuth = new JLabel("Authentification");
		lblAuth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAuth.setBounds(150, 32, 118, 14);
		desktopPane.add(lblAuth);
		
		JLabel lblIdEmp = new JLabel("Identifiant:");
		lblIdEmp.setBounds(65, 89, 70, 14);
		desktopPane.add(lblIdEmp);
		
		JLabel lblNomEmp = new JLabel("Nom:");
		lblNomEmp.setBounds(65, 134, 70, 14);
		desktopPane.add(lblNomEmp);
		
		JLabel lblPassword = new JLabel("Mot de passe:");
		lblPassword.setBounds(65, 172, 70, 14);
		desktopPane.add(lblPassword);
		
		tfIdentifiant = new JTextField();
		tfIdentifiant.setBounds(177, 86, 63, 20);
		desktopPane.add(tfIdentifiant);
		tfIdentifiant.setColumns(10);
		
		tfNom = new JTextField();
		tfNom.setBounds(177, 131, 134, 20);
		desktopPane.add(tfNom);
		tfNom.setColumns(10);
		
		JButton btnAuth = new JButton("S'authentifier");
		btnAuth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			ControlUtilisateur controlAuth = new ControlUtilisateur();

				try {
				utilisateur = new Utilisateur();
				utilisateur.setNom_utilisateur(tfNom.getText());
				utilisateur.setIdUtilisateur((Integer.parseInt(tfIdentifiant.getText())));
				
				HashMe hme = new HashMe();
				utilisateur.setMdp(hme.sha1(passwordField.getText()));
				
				boolean at = controlAuth.authentifier(utilisateur);
				
				System.out.println("retourempController: " + controlAuth.getUtilisateur().toString()); 
				System.out.println(at);
				if(at== true) {
					controlAuth.engageMainView(utilisateur);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "utilisateur non authentifier.");
				}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Attention aux saisies.");
			}
			}});
		
		btnAuth.setBounds(137, 214, 118, 23);
		desktopPane.add(btnAuth);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 169, 134, 20);
		desktopPane.add(passwordField);
		this.setSize(new Dimension(400, 300));
		this.setVisible(true);
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur ut) {
		this.utilisateur = ut;
	}
	
}
