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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Role;
import model.Utilisateur;
import outils.ImageConverter;
import serviceMetiers.ControlUtilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
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
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManageUtilisateurView extends JFrame implements ListSelectionListener {
	
	private JTextField tfIdentifiant;
	private JTextField tfNom;
	private JPasswordField tfPassword;
	private JTextField textFieldRole;
	private JTextField textFieldPrenom;
	private JTextField textFieldAdresse;
	private JTextField textFieldCodePostale;
	private JTextField textFieldVille;
	private JTextField textFieldTelephone;
	
	private ControlUtilisateur controlUtilisateur;
	private Utilisateur utilisateur;
	
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField textFieldMail;
	
	private Image img;
	private ImageIcon icone;
	private JLabel lblChargerImage;
	
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
		lblIdUtilisateur.setBounds(10, 162, 70, 14);
		desktopPane.add(lblIdUtilisateur);
		
		JLabel lblNomUtilisateur = new JLabel("Nom:");
		lblNomUtilisateur.setBounds(10, 190, 70, 14);
		desktopPane.add(lblNomUtilisateur);
		
		JLabel lblPassword = new JLabel("Mot de passe:");
		lblPassword.setBounds(10, 433, 70, 14);
		desktopPane.add(lblPassword);
		
		tfIdentifiant = new JTextField();
		tfIdentifiant.setEditable(false);
		tfIdentifiant.setBounds(110, 159, 63, 20);
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
		tfNom.setBounds(110, 187, 134, 20);
		desktopPane.add(tfNom);
		tfNom.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(110, 430, 134, 20);
		desktopPane.add(tfPassword);
		tfPassword.setColumns(10);
		
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
				utilisateur.setPhoto_utilisateur(null);//(tfPassword.getText());
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
		lblRoleUtilisateur.setBounds(10, 252, 46, 14);
		desktopPane.add(lblRoleUtilisateur);
		
		textFieldRole = new JTextField();
		textFieldRole.setBounds(110, 249, 134, 20);
		desktopPane.add(textFieldRole);
		textFieldRole.setColumns(10);
		
		JLabel lblprenomUtilisateur = new JLabel("Pr\u00E9nom:");
		lblprenomUtilisateur.setBounds(10, 222, 86, 14);
		desktopPane.add(lblprenomUtilisateur);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(110, 218, 134, 20);
		desktopPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setBounds(10, 283, 46, 14);
		desktopPane.add(lblAdresse);
		
		JLabel lblCodePostale = new JLabel("Code postale:");
		lblCodePostale.setBounds(10, 315, 70, 14);
		desktopPane.add(lblCodePostale);
		
		JLabel lblVille = new JLabel("Ville:");
		lblVille.setBounds(10, 340, 86, 14);
		desktopPane.add(lblVille);
		
		JLabel lblTelephone = new JLabel("T\u00E9l\u00E9phone:");
		lblTelephone.setBounds(10, 375, 100, 14);
		desktopPane.add(lblTelephone);
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(110, 280, 134, 20);
		desktopPane.add(textFieldAdresse);
		textFieldAdresse.setColumns(10);
		
		textFieldCodePostale = new JTextField();
		textFieldCodePostale.setBounds(110, 309, 38, 20);
		desktopPane.add(textFieldCodePostale);
		textFieldCodePostale.setColumns(10);
		
		textFieldVille = new JTextField();
		textFieldVille.setBounds(110, 341, 134, 20);
		desktopPane.add(textFieldVille);
		textFieldVille.setColumns(10);
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setBounds(110, 372, 86, 20);
		desktopPane.add(textFieldTelephone);
		textFieldTelephone.setColumns(10);
		
		JButton btnRecherche = new JButton("Rechercher");
		btnRecherche.setBackground(new Color(135, 206, 250));
		
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfIdentifiant.getText().isEmpty()) {
					clearJTextField();
					//ArrayList<Emp> le = empControl.lister();
//					Vector<Object> la = empControl.listerVector();
//					System.out.println("Vector" + la.toString());
					dtm = controlUtilisateur.getModelTable();
					
					ArrayList listCols = controlUtilisateur.cols();
					for(int i = 0; i <  listCols.size(); i++) {
						String s = (String) listCols.get(i);
						dtm.addColumn(s);
					}
					
					//TableModele tableModel = new TableModele(empControl.cols(), la);
					table.setModel(dtm);
					
					ArrayList<Utilisateur> listUtilisateur = controlUtilisateur.lister();
					
					System.out.println("taille: " + controlUtilisateur.lister().size());
					
					for(int i = 0; i < listUtilisateur.size(); i++) {
						Utilisateur utilisateur = (Utilisateur) listUtilisateur.get(i);
						//System.out.println("Row: " + emp.toString());
						Vector row =  new Vector<>();
						row.add(utilisateur.getNom_utilisateur());
						row.add(utilisateur.getPrenom_utilisateur());
						row.add(utilisateur.getAdresse_utilisateur());
						row.add(utilisateur.getVille_utilisateur());
						row.add(utilisateur.getCode_postale_utilisateur());
						row.add(utilisateur.getTel_utilisateur());
						row.add(utilisateur.getMail_utilisateur());
						row.add(utilisateur.getPhoto_utilisateur()); // a remplacer par ImageConverter
						dtm.addRow(row);
					}
					dtm.addTableModelListener(table);
					
					Iterator<Utilisateur> it  = controlUtilisateur.lister().iterator();
					while(it.hasNext()) {
					Utilisateur ut = new Utilisateur();	
						ut = it.next();
						System.out.println("from iterator: " + ut.toString());
					}
					
					//test TableModeleGenericite
//					Iterator<Utilisateur> it2  = controlUtilisateur.cols().iterator();
//					Vector cols = new Vector<>();
//					while(it.hasNext()) {
//						cols.add(it2.next());
//					}
//					TableModeleGenericite tmg = new TableModeleGenericite(controlUtilisateur.lister(), cols);
//					
//					JFrame f = new JFrame();
//					JTable tab = new JTable(tmg);
//					f.getContentPane().add(tab);
//					f.setSize(300, 500);
//					f.setVisible(true);
					// fintest TableModeleGenericite
					
					
				}else {		
				// traiter une recherche
				
				utilisateur.setIdUtilisateur(Integer.parseInt(tfIdentifiant.getText()));
				
				utilisateur = controlUtilisateur.rechercheUnUtilisateur(utilisateur);
				tfIdentifiant.setText(String.valueOf(utilisateur.getIdUtilisateur()));
				tfNom.setText(utilisateur.getNom_utilisateur());
				textFieldPrenom.setText(utilisateur.getPrenom_utilisateur());
				textFieldRole.setText(utilisateur.getRole_utilisateur().getLibelle_role());
				textFieldAdresse.setText(String.valueOf(utilisateur.getAdresse_utilisateur()));
				textFieldCodePostale.setText(String.valueOf(utilisateur.getCode_postale_utilisateur()));
				textFieldTelephone.setText(String.valueOf(utilisateur.getTel_utilisateur()));
				textFieldVille.setText(String.valueOf(utilisateur.getVille_utilisateur()));
				}
				
			}
		});
		btnRecherche.setBounds(267, 12, 111, 23);
		desktopPane.add(btnRecherche);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 46, 607, 570);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSupp = new JButton("Supprimer");
		btnSupp.setBackground(new Color(255, 192, 203));
		btnSupp.setBounds(457, 627, 100, 23);
		desktopPane.add(btnSupp);
		
		JButton btnCreer = new JButton("Cr\u00E9er");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utilisateur.setNom_utilisateur(tfNom.getText());
				utilisateur.setPrenom_utilisateur(textFieldPrenom.getText());
				utilisateur.setAdresse_utilisateur(textFieldAdresse.getText());
				utilisateur.setCode_postale_utilisateur(textFieldCodePostale.getText());
				utilisateur.setVille_utilisateur(textFieldVille.getText());
				//utilisateur.se
			}
		});
		btnCreer.setBackground(new Color(154, 205, 50));
		btnCreer.setBounds(267, 627, 70, 23);
		desktopPane.add(btnCreer);
		
		JLabel lblMail = new JLabel("Email:");
		lblMail.setBounds(10, 403, 46, 14);
		desktopPane.add(lblMail);
		
		textFieldMail = new JTextField();
		textFieldMail.setBounds(110, 403, 134, 20);
		desktopPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		lblChargerImage = new JLabel("image");
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
						BufferedImage bm  = ImageIO.read(f);  //Files.readAllBytes(Paths.get(fl));
//						 img = Toolkit.getDefaultToolkit().createImage(encoded);
//						 Image newimg = img.getScaledInstance(lblChargerImage.getHeight(), lblChargerImage.getWidth(), Image.SCALE_SMOOTH);
//						 icone = new ImageIcon(newimg);
						
						ImageConverter ic = new ImageConverter();
						icone = ic.imageConverter(bm, 0, 0);
						icone = new ImageIcon(icone.getImage().getScaledInstance(lblChargerImage.getHeight(), lblChargerImage.getWidth(), Image.SCALE_SMOOTH));
						 lblChargerImage.setIcon(icone);
						 System.out.println(encoded);
						 System.out.println(f.getName());
						 
						 Optional<String> oop;
						 utilisateur.setPhoto_utilisateur(encoded);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			        
					
					
			        byte[] test = utilisateur.getPhoto_utilisateur();
			        System.out.println("test: " + test);
			       
			      }

			}
		});
		
		// charger l'image de base
		lblChargerImage.setIcon(new ImageIcon("C:\\Users\\Toto\\GIT\\FoodLand\\FoodLand_V1\\plus.png"));
		lblChargerImage.setBounds(10, 42, 106, 106);
		desktopPane.add(lblChargerImage);
		
		JButton btnAssocierCompte = new JButton("Associer un compte");
		btnAssocierCompte.setBackground(new Color(255, 255, 224));
		btnAssocierCompte.setBounds(740, 627, 134, 23);
		desktopPane.add(btnAssocierCompte);
		
		JLabel lblChoixRole = new JLabel("Choix r\u00F4le:");
		lblChoixRole.setBounds(10, 481, 86, 14);
		desktopPane.add(lblChoixRole);
		
		JComboBox comboBoxRole = new JComboBox();
		for(int i = 0; i < controlUtilisateur.populateRoleComboBox().size(); i++) {
			Role r = controlUtilisateur.populateRoleComboBox().get(i);
			comboBoxRole.addItem(r.getLibelle_role());
		}
		
		comboBoxRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBoxRole.setBounds(110, 477, 134, 22);
		desktopPane.add(comboBoxRole);
		
		// gestion des selections sur le tableau
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(this);
		
		this.setSize(new Dimension(900, 700));
		this.setVisible(true);
	}
	
	
	public void clearJTextField() {
		tfNom.setText("");
		textFieldRole.setText("");
		textFieldAdresse.setText("");
		textFieldCodePostale.setText("");
		textFieldPrenom.setText("");
		textFieldTelephone.setText("");
		textFieldVille.setText("");
		tfPassword.setText("");
	}
	
	public void populateJTextField(Utilisateur ut) {
		tfIdentifiant.setText(String.valueOf(ut.getIdUtilisateur()));
		tfNom.setText(ut.getNom_utilisateur());
		textFieldRole.setText(ut.getRole_utilisateur().getLibelle_role());
		textFieldAdresse.setText(String.valueOf(ut.getAdresse_utilisateur()));
		textFieldCodePostale.setText(String.valueOf(ut.getCode_postale_utilisateur()));
		textFieldPrenom.setText(ut.getPrenom_utilisateur());
		textFieldTelephone.setText(ut.getTel_utilisateur());
		textFieldVille.setText(ut.getVille_utilisateur());
		tfPassword.setText("");
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int[] sel;
		utilisateur = new Utilisateur();
		sel = table.getSelectedRows();
		
		int cols = table.getColumnCount();
		System.out.println(cols);
		
		for(int i = 0; i < cols; i++) {
			// fill vector
			Vector v = new Vector();
			v.add(dtm.getValueAt(sel[0], i));
			System.out.println(v.toString());
		}
		
		// fill Emp	and populate textFields	
		utilisateur.setIdUtilisateur((int) dtm.getValueAt(sel[0], 0));
		utilisateur.setNom_utilisateur((String) dtm.getValueAt(sel[0], 1));
		utilisateur.setPrenom_utilisateur((String) dtm.getValueAt(sel[0], 2));
		utilisateur.setAdresse_utilisateur((String) dtm.getValueAt(sel[0], 3));
		utilisateur.setCode_postale_utilisateur((String) dtm.getValueAt(sel[0], 4));
		utilisateur.setVille_utilisateur((String) dtm.getValueAt(sel[0], 5));
		utilisateur.setTel_utilisateur((String) dtm.getValueAt(sel[0], 6));
		utilisateur.setMail_utilisateur( (String) dtm.getValueAt(sel[0], 7));
		utilisateur.setFk_id_role((Integer) dtm.getValueAt(sel[0], 8));
		utilisateur.setPhoto_utilisateur((byte[]) dtm.getValueAt(sel[0], 9));
		utilisateur.setFk_id_compte((Integer) dtm.getValueAt(sel[0], 10));
		System.out.println("emp: " + utilisateur.toString());
		
		populateJTextField(utilisateur);
		
		
		
//		Object o = dtm.getValueAt(sel[0], 0);
//		System.out.println(o);
	}
}

// avec vector
class TableModele<T> extends AbstractTableModel{
	T t;
	ArrayList<String> cols; 
	Vector<Object> listeT;
	
	// ou 
	Vector<Vector<Object>> lis; // correspond à une liste de liste d'attributs d'une entité 
	
	// doit pouvoir être typé avec généricité
	Vector<Vector<T>> listeGenerique;
	
	public TableModele(ArrayList<String> cols, Vector<Object> list) {
		this.cols = cols;
		this.listeT = list;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listeT.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value;
		Vector<Object> row;
		
//		if(rowIndex < listeT.size()){
//			row = listeT.
//			Object o = listeT.get(rowIndex);
//			for(columnIndex = 0; columnIndex < cols.size(); columnIndex++) {
//				
//				Object o = emp.get + getColumnName(columnIndex) + "()"; 
//			}
//		}
			return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cols.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// PREPARER UNE INTROSPECTION DE LA CLASSE ? 
		// RECUPERER LES ATTRIBUTS ET LE TYPE
		// SWITCH(columnIndex)
		// pour chaque cas retourner Type.class; en fonction du type
		Class c = null;

		//c = Class.forName(listeT.get(columnIndex).getClass());
		return c;
	}
	
}


// avec array 
class TableModele2 extends AbstractTableModel{
	
	ArrayList<String> cols; 
	ArrayList<Object> listeT;
	
	
	public TableModele2(ArrayList<String> cols, ArrayList<Object> list) {
		this.cols = cols;
		this.listeT = list;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listeT.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object value;
		Vector<Object> row;
		
//		if(rowIndex < listeT.size()){
//			row = listeT.
//			Object o = listeT.get(rowIndex);
//			for(columnIndex = 0; columnIndex < cols.size(); columnIndex++) {
//				
//				Object o = emp.get + getColumnName(columnIndex) + "()"; 
//			}
//		}
			return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cols.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// PREPARER UNE INTROSPECTION DE LA CLASSE ? 
		// RECUPERER LES ATTRIBUTS ET LE TYPE
		// SWITCH(columnIndex)
		// pour chaque cas retourner Type.class; en fonction du type
		Class c = null;

		//c = Class.forName(listeT.get(columnIndex).getClass());
		return c;
	}
	
}


// avec généricité & introspection
class TableModeleGenericite<T> extends AbstractTableModel implements ListSelectionListener{
	
	private Vector<T> listeLignes; 
	private Vector<Object> cols;
	
	public TableModeleGenericite(Vector<T> listeLignes, Vector<Object> cols) {
		this.listeLignes = listeLignes;
		this.cols = cols;
	}
	
	public TableModeleGenericite(List<T> listeLignes, Vector<Object> cols) {
		//this.listeLignes = listeLignes;
		this.cols = cols;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listeLignes.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Class c = listeLignes.get(1).getClass();
		Object value;
		Vector<Object> row;
		
			return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) cols.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// PREPARER UNE INTROSPECTION DE LA CLASSE ? 
		// RECUPERER LES ATTRIBUTS ET LE TYPE
		// SWITCH(columnIndex)
		// pour chaque cas retourner Type.class; en fonction du type
		Class c = null;

		//c = Class.forName(listeT.get(columnIndex).getClass());
		return c;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

