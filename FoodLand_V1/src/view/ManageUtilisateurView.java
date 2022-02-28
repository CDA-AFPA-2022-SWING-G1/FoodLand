package view;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet.ColorAttribute;

import com.mysql.jdbc.Blob;

import model.Compte;
import model.Role;
import model.Utilisateur;
import outils.ImageConverter;
import outils.ValidationPatternsRegex;
import serviceMetiers.ControlUtilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class ManageUtilisateurView extends JFrame implements ListSelectionListener {
	
	private JTextField tfIdentifiant;
	private JTextField tfNom;
	private JPasswordField tfPasswordCompte;
	private JTextField textFieldRole;
	private JTextField textFieldPrenom;
	private JTextField textFieldAdresse;
	private JTextField textFieldCodePostale;
	private JTextField textFieldVille;
	private JTextField textFieldTelephone;
	
	private ControlUtilisateur controlUtilisateur;

	
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField textFieldMail;
	
	private Image img;
	private ImageIcon icone;
	private JLabel lblChargerImage;
	private JLabel lblAssocierCompte;
	private JTextField textFieldLoginCompte;
	private JComboBox comboBoxRole;
	private JButton btnCreer;
	
	private Utilisateur utilisateur;
	private Vector<Utilisateur> listUtilisateur;
	
	private Role role;
	private Compte compte;

	
	public ManageUtilisateurView() {

		controlUtilisateur = new ControlUtilisateur();
		utilisateur = new Utilisateur();
		
		setPreferredSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 884, 661);
		desktopPane.setPreferredSize(new Dimension(30, 30));
		getContentPane().add(desktopPane);
		
		JLabel lblMAJ = new JLabel("Panneau gestion du personnel");
		lblMAJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMAJ.setBounds(10, 11, 234, 20);
		desktopPane.add(lblMAJ);
		
		JLabel lblIdUtilisateur = new JLabel("Identifiant:");
		lblIdUtilisateur.setBounds(10, 236, 70, 14);
		desktopPane.add(lblIdUtilisateur);
		
		JLabel lblNomUtilisateur = new JLabel("Nom:");
		lblNomUtilisateur.setBounds(10, 264, 70, 14);
		desktopPane.add(lblNomUtilisateur);
		
		JLabel lblPassword = new JLabel("Mot de passe:");
		lblPassword.setBounds(10, 599, 90, 14);
		desktopPane.add(lblPassword);
		
		tfIdentifiant = new JTextField();
		tfIdentifiant.setEditable(false);
		tfIdentifiant.setBounds(110, 233, 63, 20);
		desktopPane.add(tfIdentifiant);
		tfIdentifiant.setColumns(10);
		
		tfNom = new JTextField();
		tfNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("l'ane trotro");
			}
		});
		tfNom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("l'ane trotro");
			}
		});
		tfNom.setBounds(110, 261, 134, 20);
		desktopPane.add(tfNom);
		tfNom.setColumns(10);
		
		tfPasswordCompte = new JPasswordField();
		tfPasswordCompte.setBounds(110, 596, 134, 20);
		desktopPane.add(tfPasswordCompte);
		tfPasswordCompte.setColumns(10);
		
		JButton btnMAJ = new JButton("Modifier");
		btnMAJ.setBackground(new Color(255, 222, 173));
		btnMAJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur((Integer.parseInt(tfIdentifiant.getText())));
				utilisateur.setNom_utilisateur((tfNom.getText()));
				utilisateur.setPrenom_utilisateur(getName());//(textFieldJOB.getText());
				utilisateur.setAdresse_utilisateur(getName());//(Date.valueOf(textFieldDateEmbauche.getText()));
				utilisateur.setCode_postale_utilisateur(getName());//(Double.parseDouble(textFieldSalaire.getText()));
				utilisateur.setVille_utilisateur(getName());//(Double.parseDouble(textFieldComm.getText()));
				utilisateur.setTel_utilisateur(getName());//(Integer.parseInt(textFieldNuDept.getText()));
				utilisateur.setMail_utilisateur(getName());//(Integer.parseInt(textFieldNEchef.getText()));
				
				System.out.println(utilisateur.toString());
				
				int ret = controlUtilisateur.MAJUnUtilisateur(utilisateur);
				
				if(ret == 1) {
					JOptionPane.showMessageDialog(null, "Mise à jour de l'utilisateur" + utilisateur.getNom_utilisateur() + " effectuée. " + ret );
				}else {
					JOptionPane.showMessageDialog(null, "Mise à jour de l'utilisateur" + utilisateur.getNom_utilisateur() + " non effectuée. " + ret);
				}
			}
		});
		btnMAJ.setBounds(347, 627, 100, 23);
		desktopPane.add(btnMAJ);
		
		JLabel lblRoleUtilisateur = new JLabel("R\u00F4le:");
		lblRoleUtilisateur.setBounds(10, 326, 46, 14);
		desktopPane.add(lblRoleUtilisateur);
		
		textFieldRole = new JTextField();
		textFieldRole.setBounds(110, 323, 134, 20);
		textFieldRole.setEditable(false);
		desktopPane.add(textFieldRole);
		textFieldRole.setColumns(10);
		
		JLabel lblprenomUtilisateur = new JLabel("Pr\u00E9nom:");
		lblprenomUtilisateur.setBounds(10, 296, 86, 14);
		desktopPane.add(lblprenomUtilisateur);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(110, 292, 134, 20);
		desktopPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setBounds(10, 357, 46, 14);
		desktopPane.add(lblAdresse);
		
		JLabel lblCodePostale = new JLabel("Code postale:");
		lblCodePostale.setBounds(10, 389, 70, 14);
		desktopPane.add(lblCodePostale);
		
		JLabel lblVille = new JLabel("Ville:");
		lblVille.setBounds(10, 418, 86, 14);
		desktopPane.add(lblVille);
		
		JLabel lblTelephone = new JLabel("T\u00E9l\u00E9phone:");
		lblTelephone.setBounds(10, 449, 90, 14);
		desktopPane.add(lblTelephone);
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(110, 354, 134, 20);
		desktopPane.add(textFieldAdresse);
		textFieldAdresse.setColumns(10);
		
		textFieldCodePostale = new JTextField();
		textFieldCodePostale.setBounds(110, 383, 38, 20);
		desktopPane.add(textFieldCodePostale);
		textFieldCodePostale.setColumns(10);
		
		textFieldVille = new JTextField();
		textFieldVille.setBounds(110, 415, 134, 20);
		desktopPane.add(textFieldVille);
		textFieldVille.setColumns(10);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setBounds(110, 446, 86, 20);
		desktopPane.add(textFieldTelephone);
		textFieldTelephone.setColumns(10);
		

		
		dtm = controlUtilisateur.getModelTable();
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 46, 607, 570);
		scrollPane.setViewportView(table);
		table.setModel(dtm);
		
		// gestion des selections sur le tableau
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(this);
		
	    //Trier les lignes du tableau 
//		TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
//	      table.setRowSorter(sort);
//	      	java.util.List<RowSorter.SortKey> sortList = new ArrayList<>(5);
//	      	sortList.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
//	      	sortList.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//	      	sort.setSortKeys(sortList);
		
		
		JButton btnRecherche = new JButton("Rechercher");
		btnRecherche.setBackground(new Color(135, 206, 250));
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRecherche.setText("Rafraîchir");
				
				listSelectionModel.clearSelection();
				table.getSelectionModel().clearSelection();
				dtm.getDataVector().removeAllElements();
				
//				Iterator<Utilisateur> it  = controlUtilisateur.lister().iterator();
//				while(it.hasNext()) {
//				Utilisateur ut = new Utilisateur();	
//					ut = it.next();
//					System.out.println("from iterator: " + ut.toString());
//				}
				
				//if(tfIdentifiant.getText().isEmpty()) {
					clearJTextField();
					
	
//					ArrayList listCols = controlUtilisateur.cols();
//					for(int i = 0; i <  listCols.size(); i++) {
//						String s = (String) listCols.get(i);
//						dtm.addColumn(s);
//					}
					
					
					listUtilisateur = new Vector(controlUtilisateur.lister());
					
					for(int i = 0; i < listUtilisateur.size(); i++) {
						utilisateur = (Utilisateur) listUtilisateur.get(i);
						
						Vector row =  new Vector<>();
						row.add(utilisateur.getIdUtilisateur());
						row.add(utilisateur.getNom_utilisateur());
						row.add(utilisateur.getPrenom_utilisateur());
						row.add(utilisateur.getAdresse_utilisateur());
						row.add(utilisateur.getCode_postale_utilisateur());
						row.add(utilisateur.getVille_utilisateur());
						row.add(utilisateur.getTel_utilisateur());
						row.add(utilisateur.getMail_utilisateur());
						row.add(utilisateur.getPhoto_utilisateur()); // a remplacer par ImageConverter
						dtm.addRow(row);
					}
					dtm.addTableModelListener(table);
							
//				}else {		
//				
//				
//				utilisateur.setIdUtilisateur(Integer.parseInt(tfIdentifiant.getText()));
//				
//				utilisateur = controlUtilisateur.rechercheUnUtilisateur(utilisateur);
//				tfIdentifiant.setText(String.valueOf(utilisateur.getIdUtilisateur()));
//				tfNom.setText(utilisateur.getNom_utilisateur());
//				textFieldPrenom.setText(utilisateur.getPrenom_utilisateur());
//				textFieldRole.setText(utilisateur.getRole_utilisateur().getLibelle_role());
//				textFieldAdresse.setText(String.valueOf(utilisateur.getAdresse_utilisateur()));
//				textFieldCodePostale.setText(String.valueOf(utilisateur.getCode_postale_utilisateur()));
//				textFieldTelephone.setText(String.valueOf(utilisateur.getTel_utilisateur())); 
//				textFieldVille.setText(String.valueOf(utilisateur.getVille_utilisateur()));
//				}
				
			}
		});
		btnRecherche.setBounds(267, 12, 157, 23);
		desktopPane.add(btnRecherche);
		
		desktopPane.add(scrollPane);
		
		JButton btnSupp = new JButton("Supprimer");
		btnSupp.setBackground(new Color(255, 192, 203));
		btnSupp.setBounds(457, 627, 100, 23);
		desktopPane.add(btnSupp);
		
		btnCreer = new JButton("Cr\u00E9er");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				utilisateur.setNom_utilisateur(tfNom.getText());
//				utilisateur.setPrenom_utilisateur(textFieldPrenom.getText());
//				utilisateur.setAdresse_utilisateur(textFieldAdresse.getText());
//				utilisateur.setCode_postale_utilisateur(textFieldCodePostale.getText());
//				utilisateur.setVille_utilisateur(textFieldVille.getText());
//				utilisateur.setTel_utilisateur(textFieldTelephone.getText());
//				utilisateur.setMail_utilisateur(textFieldMail.getText());
				
				//System.out.println(utilisateur.getPhoto_utilisateur());
				
//				Role r = new Role();
//				r.setLibelle_role((String) comboBoxRole.getSelectedItem());
//				System.out.println(r.getLibelle_role());
//				utilisateur.setRole_utilisateur(r);
				
				// mvc
				// controlUtilisateur.creerUtitilisateur(U u);	
			}
		});
		btnCreer.setBackground(new Color(154, 205, 50));
		btnCreer.setBounds(267, 627, 70, 23);
		desktopPane.add(btnCreer);
		
		JLabel lblMail = new JLabel("Email:");
		lblMail.setBounds(10, 477, 90, 14);
		desktopPane.add(lblMail);
		
		textFieldMail = new JTextField();
		textFieldMail.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				validerLesSaisies();
			}
		});
		textFieldMail.setBounds(110, 477, 134, 20);
		desktopPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		lblChargerImage = new JLabel("");
		lblChargerImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser(FileSystemView
				        .getFileSystemView()
				        .getHomeDirectory());
				
				int returnValue = chooser.showOpenDialog(null);

			    if (returnValue == JFileChooser.APPROVE_OPTION) {
			        File f = chooser.getSelectedFile();
			        String fl = f.getAbsolutePath();
			        
			        byte[] encoded;
			        
					try {
						encoded = Files.readAllBytes(Paths.get(fl));
						//System.out.println(Arrays.toString(encoded));
						 // traiter l'extension
						 Optional<String> oop;
						 // assigner à l'utilisateur courant
						 //utilisateur.setPhoto_utilisateur(encoded);
						 Blob b;
						 FileImageInputStream is = new FileImageInputStream(f);
						 //b.setBinaryData(is);
						 // traitement pour l'affichage dans le jlabl
						BufferedImage bm  = ImageIO.read(f);  //Files.readAllBytes(Paths.get(fl));
						
						//code mort mais en attente
//						 img = Toolkit.getDefaultToolkit().createImage(encoded);
//						 Image newimg = img.getScaledInstance(lblChargerImage.getHeight(), lblChargerImage.getWidth(), Image.SCALE_SMOOTH);
//						 icone = new ImageIcon(newimg);
						
						// utilisation de ImageConverter pour traiter la mise en forme de l'image selectionnée
						ImageConverter ic = new ImageConverter();
						icone = ic.imageConverter(bm, 0, 0);
						// on passera l'image retournée à l'icone en précisant le scale du label 
						icone = new ImageIcon(icone.getImage().getScaledInstance(lblChargerImage.getHeight(), lblChargerImage.getWidth(), Image.SCALE_SMOOTH));
						 lblChargerImage.setIcon(icone);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			      }
			}
		});
		
		// charger l'image de base "plus"
		lblChargerImage.setIcon(new ImageIcon("C:\\Users\\Toto\\GIT\\FoodLand\\FoodLand_V1\\plus.png"));
		lblChargerImage.setBounds(10, 42, 163, 163);
		desktopPane.add(lblChargerImage);
		
		JButton btnAssocierCompte = new JButton("Associer le compte");
		btnAssocierCompte.setBackground(new Color(255, 255, 224));
		btnAssocierCompte.setBounds(110, 627, 134, 23);
		desktopPane.add(btnAssocierCompte);
		
		JLabel lblChoixRole = new JLabel("Choix r\u00F4le:");
		lblChoixRole.setBounds(10, 513, 86, 14);
		desktopPane.add(lblChoixRole);
		
		comboBoxRole = new JComboBox();
		for(int i = 0; i < controlUtilisateur.populateRoleComboBox().size(); i++) {
			Role r = controlUtilisateur.populateRoleComboBox().get(i);
			comboBoxRole.addItem(r.getLibelle_role());
			
		}
		
		comboBoxRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldRole.setText(comboBoxRole.getSelectedItem().toString());
				utilisateur.getRole_utilisateur().setLibelle_role(comboBoxRole.getSelectedItem().toString());
				//verification
				System.out.println(utilisateur.toString());
			}
		});
		comboBoxRole.setBounds(110, 509, 134, 22);
		desktopPane.add(comboBoxRole);
		
		lblAssocierCompte = new JLabel("---Associer un compte---");
		lblAssocierCompte.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssocierCompte.setBounds(10, 545, 234, 14);
		desktopPane.add(lblAssocierCompte);
		
		JLabel lblLoginCompte = new JLabel("Login compte:");
		lblLoginCompte.setBounds(10, 570, 86, 14);
		desktopPane.add(lblLoginCompte);
		
		textFieldLoginCompte = new JTextField();
		textFieldLoginCompte.setBounds(110, 570, 134, 20);
		desktopPane.add(textFieldLoginCompte);
		textFieldLoginCompte.setColumns(10);
		
		this.setSize(new Dimension(900, 700));
		this.setVisible(true);
	}
	
	
	public void clearJTextField() {
		tfIdentifiant.setText("");
		tfNom.setText("");
		textFieldRole.setText("");
		textFieldAdresse.setText("");
		textFieldCodePostale.setText("");
		textFieldPrenom.setText("");
		//textFieldTelephone.setText("");
		textFieldVille.setText("");
		tfPasswordCompte.setText("");
	}
	
	public void populateJTextField(Utilisateur ut) {
		System.out.println("uuuut" + ut.toString());
		try {
		tfIdentifiant.setText(String.valueOf(ut.getIdUtilisateur()));
		tfNom.setText(ut.getNom_utilisateur());
		textFieldPrenom.setText(ut.getPrenom_utilisateur());
		textFieldRole.setText(ut.getRole_utilisateur().getLibelle_role());
		textFieldAdresse.setText(String.valueOf(ut.getAdresse_utilisateur()));
		textFieldCodePostale.setText(String.valueOf(ut.getCode_postale_utilisateur()));
		textFieldTelephone.setText(String.valueOf(ut.getTel_utilisateur()));
		textFieldMail.setText(ut.getMail_utilisateur());
		textFieldVille.setText(ut.getVille_utilisateur());
		textFieldLoginCompte.setText(ut.getCompte_utilisateur().getLogin_compte());
		tfPasswordCompte.setText("");
		}catch(NullPointerException ex) {	
		}
	}
	
	// a voir: à déplacer dans service / control
	public void validerLesSaisies() {
		ValidationPatternsRegex vpr = new ValidationPatternsRegex();
		boolean ok = vpr.matchEmail(textFieldMail.getText());
		
		// mail
		if(ok==false && !textFieldMail.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, vpr.getMessage());
			textFieldMail.setBackground(Color.PINK);
		}else {
			textFieldMail.setBackground(null);
		}
		
		//pass
		ok = vpr.matchPassword(tfPasswordCompte.getText());
		if(ok==false) {
			JOptionPane.showMessageDialog(null, vpr.getMessage());
			tfPasswordCompte.setBackground(Color.PINK);
		}else {
			tfPasswordCompte.setBackground(Color.WHITE);
		}
		
		//numeric
		ArrayList<Integer> listNumerics  = new ArrayList<>();
//		listNumerics.add(Integer.valueOf(textFieldCodePostale.getText()));
//		listNumerics.add(Integer.valueOf(textFieldRole.getText()));
		//ok = vpr.matchNumeric(listNumerics);
		if(ok==false) {
			JOptionPane.showMessageDialog(null, vpr.getMessage());
			tfPasswordCompte.setBackground(Color.PINK);
		}else {
			tfPasswordCompte.setBackground(Color.WHITE);
			
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int[] sel;		
		if (e.getValueIsAdjusting()) {
			
		sel = table.getSelectedRows();
		
		//TableModel mt = table.getModel();
		System.out.println(dtm.getValueAt(sel[0], 0));
		
		System.out.println("from listt : " +  listUtilisateur.toString());
		for(Utilisateur u : listUtilisateur) {
			//try {
			if(u.getIdUtilisateur() == dtm.getValueAt(sel[0], 0)) {
			System.out.println("u toruvé? " + u.toString());
			populateJTextField(u);
			this.utilisateur = u;
			
//			}catch(NullPointerException ex) {	
//			}
			}
		}
		
		} // fin if e.getValueIs..

		// fill Utilisateur	and populate textFields	
		
		//try {
		//tfIdentifiant.setText( String.valueOf(dtm.getValueAt(sel[0], 0)));
		//tfNom.setText((String) dtm.getValueAt(sel[0], 1));
//		textFieldPrenom.setText(ut.getPrenom_utilisateur());
//		textFieldAdresse.setText(String.valueOf(ut.getAdresse_utilisateur()));
//		textFieldCodePostale.setText(String.valueOf(ut.getCode_postale_utilisateur()));
//		textFieldVille.setText(ut.getVille_utilisateur());
//		textFieldTelephone.setText(ut.getTel_utilisateur());
//		textFieldMail.setText(ut.getMail_utilisateur());
//		textFieldRole.setText(ut.getRole_utilisateur().getLibelle_role());
		//textFieldLoginCompte.setText((String) dtm.getValueAt(sel[0], 2));
		
//		}catch(NullPointerException ex) {	
//		}
//		
//		utilisateur.setIdUtilisateur((int) dtm.getValueAt(sel[0], 0));
//		utilisateur.setNom_utilisateur((String) dtm.getValueAt(sel[0], 1));
//		utilisateur.setPrenom_utilisateur((String) dtm.getValueAt(sel[0], 2)); System.out.println("prenom:" + dtm.getValueAt(sel[0], 2));
//		utilisateur.setAdresse_utilisateur((String) dtm.getValueAt(sel[0], 3)); System.out.println("adress:" + dtm.getValueAt(sel[0], 3));
//		utilisateur.setCode_postale_utilisateur((String) dtm.getValueAt(sel[0], 4)); System.out.println("cp:" + dtm.getValueAt(sel[0], 4));
//		utilisateur.setVille_utilisateur((String) dtm.getValueAt(sel[0], 5)); System.out.println("ville:" + dtm.getValueAt(sel[0], 5));
//		utilisateur.setTel_utilisateur((String) dtm.getValueAt(sel[0], 6));  System.out.println("tel:" + dtm.getValueAt(sel[0], 6));
//		utilisateur.setMail_utilisateur( (String) dtm.getValueAt(sel[0], 7));
//		utilisateur.setFk_id_role((Integer)dtm.getValueAt(sel[0], 8));
//		textFieldTelephone.setText(String.valueOf(utilisateur.getTel_utilisateur()));
		//utilisateur.setPhoto_utilisateur((byte[]) dtm.getValueAt(sel[0], 9));
		//utilisateur.setFk_id_compte((Integer) dtm.getValueAt(sel[0], 10));

		System.out.println(utilisateur.getCompte_utilisateur().getLogin_compte()  + "/" + utilisateur.getCompte_utilisateur().getPassword_compte());
	}

	public JButton getBtnCreer() {
		return btnCreer;
	}

	public void setBtnCreer(JButton btnCreer) {
		this.btnCreer = btnCreer;
	}
	
}









