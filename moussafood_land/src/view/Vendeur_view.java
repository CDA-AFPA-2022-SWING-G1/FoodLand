package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controller.Utilisateur_controller;
import model.Utilisateur_model;

public class Vendeur_view {
	/* Attributs des ecrans */
	private JFrame vendeur_Jframe;

	private JLabel foodland_Label, nom_vendeur_Label, date_Label, horloge_Label, info_perso_Label;
	private ImageIcon foodland_Image;

	private JPanel tdb_JPanel;
	private DefaultTableModel tdb_DefaultTable;
	private JTable tdb_JTable;
	private JScrollPane tdb_JScroll;

	private JSplitPane splitPane;

	private JPanel fichier_client_JPanel;
	private DefaultTableModel fichier_client_DefaultTable;
	private JTable fichier_client_JTable;
	private JScrollPane fichier_client_JScroll;

	private JButton nouveau_client_Button, modifier_client_Button, supprimer_client_Button,
			historique_commandes_client_Button, nouvelle_commande_Button;

	/* Constructeur */
	public Vendeur_view(int fk_id_user) throws SQLException {
		
		int id_user = fk_id_user;
		System.out.println("Vendeur_view de fk_id_user : " + id_user);
		
		/*
		 *  Algo pour récupérer le nom du commercial
		 *  S de SCRUD
		 */
		 // DAO : Instanciation du controleur (SCRUD)
		 Utilisateur_controller utilisateur_controller = new Utilisateur_controller();
//		 System.out.println("new Utilisateur_controller(); : " + utilisateur_controller);
		 
		 // DAO : Création du modèle utilisateur
		 Utilisateur_model utilisateur = utilisateur_controller.get_Utilisateur(id_user);
//		 System.out.println("get_Utilisateur(id_user); " + utilisateur.getNom_utilisateur());		 
		 
		 /*
		  * 
		  * Construire IHM
		  * 
		  */
		 // Objet de type Jframe
		vendeur_Jframe = new JFrame("COMMERCIAL");

		// Ajouter le logo
		foodland_Label = new JLabel();
		foodland_Image = new LogoFoodland().getFoodland_Image();
		foodland_Label.setBounds(0, 10, 120, 80);
		foodland_Label.setIcon(foodland_Image);
		vendeur_Jframe.add(foodland_Label);

		// Ajouter le nom du commercial
		nom_vendeur_Label = new JLabel(utilisateur.getNom_utilisateur() + " " + utilisateur.getPrenom_utilisateur());
		nom_vendeur_Label.setFont(new Font("Serif", Font.BOLD, 60));
		nom_vendeur_Label.setBounds(121, 10, 500, 80);
		vendeur_Jframe.add(nom_vendeur_Label);

		// Horloge
		horloge_Label = new JLabel();
		horloge_Label.setFont(new Font("SansSerif", Font.BOLD, 20));
		horloge_Label.setHorizontalAlignment(SwingConstants.CENTER);
		horloge_Label.setBounds(1050, 15, 150, 30);
		javax.swing.Timer t = new javax.swing.Timer(1000, new ClockListener());
		t.start();
		vendeur_Jframe.add(horloge_Label);
		
		// Ajouter la date
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //  HH:mm:ss  
		Date date = new Date(); 
		date_Label = new JLabel(formatter.format(date)); //
		date_Label.setFont(new Font("SansSerif", Font.BOLD, 20));
		date_Label.setHorizontalAlignment(SwingConstants.CENTER);
		date_Label.setBounds(1200, 15, 150, 30);
		vendeur_Jframe.add(date_Label);
		
		/*
		 * 
		 * Modifier les informations personnelles 
		 * 
		 */
		// Underline the JLabel : https://stackoverflow.com/questions/12389160/how-to-underline-a-jlabel-on-mouseenter
		info_perso_Label = new JLabel("Modifier mes données personnelles");
		info_perso_Label.setFont(new Font("SansSerif", Font.BOLD, 20));
		info_perso_Label.setForeground(Color.blue);
		info_perso_Label.setHorizontalAlignment(SwingConstants.CENTER);
		info_perso_Label.setBounds(970, 50, 370, 30);
		MouseListener l = new MouseAdapter() {
		    Font original;

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        original = e.getComponent().getFont();
		        Map attributes = original.getAttributes();
		        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		        e.getComponent().setFont(original.deriveFont(attributes));
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        e.getComponent().setFont(original);
		    }


		};
		// Clicking a JLabel to open a new frame : https://stackoverflow.com/questions/14735085/clicking-a-jlabel-to-open-a-new-frame
		info_perso_Label.addMouseListener(l);
		vendeur_Jframe.add(info_perso_Label);
//		vendeur_Jframe.add(new JButton("dummy focus")); // À quoi ça sert !!?
		info_perso_Label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
//				System.out.println(utilisateur.getNom_utilisateur());
				new Utilisateur_update_view(utilisateur); 

			}  
		}); 
		
		
		// Afficher la fenêtre à la fin
		vendeur_Jframe.setSize(1395, 800);
		vendeur_Jframe.setResizable(false);
		vendeur_Jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		vendeur_Jframe.setLayout(null);
		vendeur_Jframe.setLocationRelativeTo(null);
		vendeur_Jframe.setVisible(true);
	}
	
	// Horloge
	class ClockListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			horloge_Label.setText(" "+ df.format(Calendar.getInstance().getTime()));
		}
	}
}
