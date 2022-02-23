package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

import controller.Commande_en_preparationControl;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class commande_en_preparation_View extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField date;
	private JTextField nom_client;
	private JTextField quantite;
	private JButton valider_1;
	private JButton modifier;
	private JButton btnValiderCommande;
	private JLabel Désignation;
	private JTextField txt_categorie;
	private JLabel Catégorie;
	private JTextField txt_designation;
	private JTextField txt_total;
	private JLabel Total;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					commande_en_preparation_View frame = new commande_en_preparation_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public commande_en_preparation_View() throws SQLException {
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Collection<? extends String> valider;
		Vector<String> valider;
		//valider = "valider";
		JButton supprimer;
		//valider = new JButton("valider");
		//valider = new JButton("supprimer");
		JLabel commande_en_preparation = new JLabel("Commande en pr\u00E9paration");
		commande_en_preparation.setBounds(91, 11, 445, 14);
		commande_en_preparation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(commande_en_preparation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 48, 455, 269);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Vector<String> rowHeader = new Vector<String> ();      
		  rowHeader.add ("Désignation");  
		  rowHeader.add ("Catégorie");  
		  rowHeader.add ("Total");
		  rowHeader.add ("");
		  rowHeader.add ("");
		  
		  DefaultTableModel model = new DefaultTableModel(rowHeader,0);     
		  table.setModel(model); 
		  
		  date = new JTextField();
		  date.setBounds(238, 24, 147, 20);
		  contentPane.add(date);
		  date.setColumns(10);
		  
		  nom_client = new JTextField();
		  nom_client.setBounds(91, 24, 137, 20);
		  nom_client.setColumns(10);
		  contentPane.add(nom_client);
		  
		  quantite = new JTextField();
		  quantite.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
			  }
		  });
		  quantite.setBounds(395, 24, 147, 20);
		  quantite.setColumns(10);
		  contentPane.add(quantite);
		  
		  valider_1 = new JButton("VALIDER");
		  valider_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		  valider_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	}
		  });
		  valider_1.setBounds(10, 379, 120, 23);
		  contentPane.add(valider_1);
		  
		  modifier = new JButton("MODIFIER");
		  modifier.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	}
		  });
		  modifier.setFont(new Font("Tahoma", Font.PLAIN, 18));
		  modifier.setBounds(157, 379, 120, 23);
		  contentPane.add(modifier);
		  
		  btnValiderCommande = new JButton("VALIDER COMMANDE");
		  btnValiderCommande.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	}
		  });
		  btnValiderCommande.setFont(new Font("Tahoma", Font.PLAIN, 18));
		  btnValiderCommande.setBounds(341, 379, 232, 23);
		  contentPane.add(btnValiderCommande);
		  
		  Désignation = new JLabel("D\u00E9signation");
		  Désignation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  Désignation.setBounds(10, 344, 72, 14);
		  contentPane.add(Désignation);
		  
		  txt_categorie = new JTextField();
		  txt_categorie.setBounds(270, 341, 85, 20);
		  contentPane.add(txt_categorie);
		  txt_categorie.setColumns(10);
		  
		  Catégorie = new JLabel("Cat\u00E9gorie");
		  Catégorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  Catégorie.setBounds(192, 344, 72, 14);
		  contentPane.add(Catégorie);
		  
		  txt_designation = new JTextField();
		  txt_designation.setColumns(10);
		  txt_designation.setBounds(91, 341, 85, 20);
		  contentPane.add(txt_designation);
		  
		  
		  
		  txt_total = new JTextField();
		  txt_total.setColumns(10);
		  txt_total.setBounds(461, 341, 85, 20);
		  contentPane.add(txt_total);
		  
		  Total = new JLabel("Total");
		  Total.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  Total.setBounds(379, 344, 72, 14);
		  contentPane.add(Total);
		  //scrollPane.getViewport().add(table);
		  table.addMouseListener(new MouseAdapter()  {
		    	 
	          public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	  txt_designation.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
	        	  txt_categorie.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
	        	  txt_total.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
	        	  System.out.println("test");
	             
	             }
	          
	          
	       });
		  Commande_en_preparationControl commandes = new Commande_en_preparationControl();
		  ResultSet res= commandes.selectCommandes(1);
		   
		   Vector<String> rowData;      
		   if (res != null) while (res.next()){  
		    rowData = new Vector<String>() ;  
		    rowData.add (res.getString("designation_ligne_commande"));  
		    rowData.add (res.getString("categorie_produit.lib_categorie_produit")); 
		    rowData.add (res.getString("poids_quantite_ligne_commande"));  
		    //rowData.add(valider);
		     
		    model.addRow(rowData);
		    //model.addRow(valider);
          }
		   
		   
	}
	
	public void actualiser() {
		  txt_designation.setText("");
		  txt_categorie.setText("");
		  txt_total.setText("");	  
	  }

}
