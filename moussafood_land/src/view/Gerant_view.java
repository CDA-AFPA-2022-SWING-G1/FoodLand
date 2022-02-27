package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import controller.Utilisateur_controller;
import model.Utilisateur_model;

//import horloge.Horloge.ClockListener;

public class Gerant_view implements ListSelectionListener {

	/*
	 * Attributs des écrans
	 */
	JFrame frame;

	//Panel droit du tableau
	JPanel table_panel;
	DefaultTableModel model;

	private JTable table;

	private JScrollPane pane;
	private TableRowSorter<TableModel> sort;
	private java.util.List<RowSorter.SortKey> sortList;
	private JPanel form_panel;
	private JLabel search_lbl;
	private JSeparator separator;
	private JLabel role_lbl;
	private JComboBox<Object> role_view; // role_view
	private JLabel nom_lbl;
	private JLabel prenom_lbl;
	private JLabel adresse_lbl;
	private JLabel codepostal_lbl;
	private JLabel ville_lbl;
	private JLabel phone_lbl;
	private JLabel mail_lbl;
	private JLabel photo_lbl;
	private JButton photo_btn_view; // photo_btn_view
	private JLabel photoPlace;
	private JButton addButton; // Add
	private JButton updateButton; // Update
	private JButton deleteButton; // Delete
	private JLabel logo_lbl; // logo Foodland
	private JLabel lblNewLabel; // Date du jour

	private JTextField textField, nom_fld_view, prenom_fld_view, adresse_fld_view, codepostal_fld_view, ville_fld_view, phone_fld_view, mail_fld_view;
	
	/*
	 * Constructeur
	 */	
	public Gerant_view() {
		
		
		
		try {
			/*
			 * Définir
			 * - Frame
			 * - JTable...
			 * avant d’insérer les données
			 * sinon le listener de ligne du tableau
			 * risque de ne pas fonctionner
			 */
			frame = new JFrame();

			/*
			 * Panel droit du tableau
			 */
			table_panel = new JPanel();
			
//			model = new DefaultTableModel();
//			table = new JTable(model);
			

/*
 * 
 *  
 *  
 *  
 *  
 *  
 *  
 *  DBConnection
 *  
 */
			//Mettre à jour le formulaire
			String url = "jdbc:mysql://localhost:8889/foodland2";
			String user = "root";
			String password = "root";

			Connection con = DriverManager.getConnection(url, user, password);
/*
 * 
 *  
 *  	
 *  
 *  
 *  
 *  
 *  SCRUD Select / Read
 *  Lire
 */
			String query = "SELECT * FROM utilisateur";

			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery(query);

			String columns[] = { "ID", "Photo", "Nom", "Prenom",  "Rôle", "Téléphone", "Mail", "Adresse", "Code Postal", "Ville" };
			String data[][] = new String[10][10];

			
			int i = 0; // 0
			while (res.next()) {
				String id = res.getString("id_user");
				String nom = res.getString("nom_user");
				String prenom = res.getString("prenom_user");
				String adresse = res.getString("adresse_user");
				String codepostal = res.getString("code_postal_user");
				String ville = res.getString("ville_user");
				String telephone = res.getString("tel_user");
				String mail = res.getString("mail_user");
				String photoPlace = res.getString("photo_user");
				String role = res.getString("fk_id_role");

				data[i][0] = id;
				data[i][1] = nom;
				data[i][2] = prenom;
				data[i][3] = adresse;
				data[i][4] = codepostal;
				data[i][5] = ville;
				data[i][6] = telephone;
				data[i][7] = mail;
				data[i][8] = photoPlace;
				data[i][9] = role;
				i++;
				
//				model.addRow(new Object[]{id, nom, ville});//Adding row in Table
//				model.addTableModelListener(table);
			}

			model = new DefaultTableModel(data, columns);
			table = new JTable(model);			
			pane = new JScrollPane(table);
			sort = new TableRowSorter<>(table.getModel());
				
			//Trier les lignes du tableau
			table.setRowSorter(sort);
			sortList = new ArrayList<>(5); // c’est quoi ce 5 ?
			sortList.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
			sortList.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
			sort.setSortKeys(sortList);

			table_panel.setBounds(376, 88, 993, 610);
			table_panel.setLayout(null);
			table_panel.add(pane);     
			pane.setBounds(10, 11, 973, 610);
			table.setShowGrid(true);
			table.setShowVerticalLines(true);
			
			table.setDefaultEditor(Object.class, null); // tableau non editable
				// https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(table_panel);

		/*
		 * Panel gauche du formulaire
		 */
			form_panel = new JPanel();
			form_panel.setBounds(10, 88, 356, 610);
			frame.getContentPane().add(form_panel);
			form_panel.setLayout(null);

			search_lbl = new JLabel("Chercher un mot :");
			search_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			search_lbl.setBounds(10, 11, 139, 25);
			form_panel.add(search_lbl);

			textField = new JTextField();
			textField.setBounds(159, 11, 187, 24);
			form_panel.add(textField);
			textField.setColumns(10);
			textField.getDocument().addDocumentListener(new DocumentListener()
			{
				@Override
				public void insertUpdate(DocumentEvent e) {
					String str = textField.getText();
					if (str.trim().length() == 0) {
						sort.setRowFilter(null);
					} else {
						//(?i) recherche insensible à la casse
						sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
					}
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
					String str = textField.getText();
					if (str.trim().length() == 0) {
						sort.setRowFilter(null);
					} else {
						sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
					}
				}
				@Override
				public void changedUpdate(DocumentEvent e) {}
			});

			separator = new JSeparator();
			separator.setBounds(6, 41, 340, 8);
			form_panel.add(separator);

			role_lbl = new JLabel("R\u00F4le");
			role_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			role_lbl.setBounds(11, 54, 139, 25);
			form_panel.add(role_lbl);

			role_view = new JComboBox<Object>();
			role_view.setModel(new DefaultComboBoxModel<Object>(new String[] {"", "Administrateur", "G\u00E9rant", "Vendeur", "Acheteur", "Magasinier"}));
			role_view.setBounds(159, 47, 187, 32);
			form_panel.add(role_view);

			nom_lbl = new JLabel("Nom");
			nom_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			nom_lbl.setBounds(11, 84, 139, 25);
			form_panel.add(nom_lbl);

			nom_fld_view = new JTextField();
			nom_fld_view.setColumns(10);
			nom_fld_view.setBounds(159, 84, 187, 24);
			form_panel.add(nom_fld_view);

			prenom_lbl = new JLabel("Pr\u00E9nom");
			prenom_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			prenom_lbl.setBounds(10, 120, 139, 25);
			form_panel.add(prenom_lbl);

			prenom_fld_view = new JTextField();
			prenom_fld_view.setColumns(10);
			prenom_fld_view.setBounds(159, 120, 187, 24);
			form_panel.add(prenom_fld_view);

			adresse_lbl = new JLabel("Adresse");
			adresse_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			adresse_lbl.setBounds(10, 156, 139, 25);
			form_panel.add(adresse_lbl);

			adresse_fld_view = new JTextField();
			adresse_fld_view.setColumns(10);
			adresse_fld_view.setBounds(159, 156, 187, 24);
			form_panel.add(adresse_fld_view);

			codepostal_lbl = new JLabel("Code Postal");
			codepostal_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			codepostal_lbl.setBounds(10, 192, 139, 25);
			form_panel.add(codepostal_lbl);

			codepostal_fld_view = new JTextField();
			codepostal_fld_view.setColumns(10);
			codepostal_fld_view.setBounds(159, 192, 187, 24);
			form_panel.add(codepostal_fld_view);

			ville_lbl = new JLabel("Ville");
			ville_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			ville_lbl.setBounds(10, 228, 139, 25);
			form_panel.add(ville_lbl);

			ville_fld_view = new JTextField();
			ville_fld_view.setColumns(10);
			ville_fld_view.setBounds(159, 228, 187, 24);
			form_panel.add(ville_fld_view);

			phone_lbl = new JLabel("T\u00E9l\u00E9phone");
			phone_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			phone_lbl.setBounds(10, 264, 139, 25);
			form_panel.add(phone_lbl);

			phone_fld_view = new JTextField();
			phone_fld_view.setColumns(10);
			phone_fld_view.setBounds(159, 264, 187, 24);
			form_panel.add(phone_fld_view);

			mail_lbl = new JLabel("Mail");
			mail_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			mail_lbl.setBounds(10, 300, 139, 25);
			form_panel.add(mail_lbl);

			mail_fld_view = new JTextField();
			mail_fld_view.setColumns(10);
			mail_fld_view.setBounds(159, 300, 187, 24);
			form_panel.add(mail_fld_view);

			photo_lbl = new JLabel("Photo");
			photo_lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
			photo_lbl.setBounds(10, 336, 139, 25);
			form_panel.add(photo_lbl);

			photo_btn_view = new JButton("Parcourir");
			photo_btn_view.setBounds(159, 335, 187, 25);
			form_panel.add(photo_btn_view);

			photoPlace = new JLabel("");
			photoPlace.setBackground(Color.WHITE);
			photoPlace.setHorizontalAlignment(SwingConstants.CENTER);
			photoPlace.setBounds(159, 372, 187, 174);
			form_panel.add(photoPlace);

/*
 * 
 *  
 *  
 *  
 *  
 *  
 *  PHOTO
 */
			//Mettre à jour le formulaire
			photo_btn_view.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					JFileChooser file = new JFileChooser();
					file.setCurrentDirectory(new File(System.getProperty("user.home")));
					//filtrer les fichiers
					FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
					file.addChoosableFileFilter(filter);
					int res = file.showSaveDialog(null);
					//si l'utilisateur clique sur enregistrer dans Jfilechooser
					if(res == JFileChooser.APPROVE_OPTION){
						File selFile = file.getSelectedFile();
						String path = selFile.getAbsolutePath();
						photoPlace.setIcon(resize(path));
					}
				}

				// Méthode pour redimensionner l'image avec la même taille du Jlabel
				public ImageIcon resize(String imgPath)
				{
					ImageIcon path = new ImageIcon(imgPath);
					Image img = path.getImage();
					Image newImg = img.getScaledInstance(photoPlace.getWidth(), photoPlace.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon image = new ImageIcon(newImg);
					return image;
				}
			});

			addButton = new JButton("Ajouter");
			addButton.setBounds(10, 558, 106, 43);
			addButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
/*
 * 
 *  
 *  	
 *  
 *  
 *  
 *  
 *  SCRUD Create
 *  Ajouter
 */
					/* 
					 * Model
					 * - manque Photo
					 * - Role à gérer : Fk_id_role
					 */
					Utilisateur_model utilisateur_model = new Utilisateur_model();
					utilisateur_model.setNom_utilisateur(nom_fld_view.getText());
					utilisateur_model.setPrenom_utilisateur(prenom_fld_view.getText());
					// utilisateur_model.setFk_id_role(3);
					utilisateur_model.setTel_user(phone_fld_view.getText());
					utilisateur_model.setMail_user(mail_fld_view.getText());
					utilisateur_model.setAdresse_utilisateur(adresse_fld_view.getText());
					utilisateur_model.setCode_postal_utilisateur(codepostal_fld_view.getText());
					utilisateur_model.setVille_utilisateur(ville_fld_view.getText());
					
					/*
					 * Controller
					 */
					Utilisateur_controller utilisateur_controller = new Utilisateur_controller();
					
					try {
						utilisateur_controller.insert_Utilisateur(utilisateur_model);
						System.out.println("Gerant_view : appel du Controller");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Ajouter les données du formulaire
					model.addRow(
						new Object[]{
								photoPlace.getIcon(),
								nom_fld_view.getText(), 
								prenom_fld_view.getText(),
								role_view.getSelectedItem().toString(),
								phone_fld_view.getText(),
								mail_fld_view.getText(),
								adresse_fld_view.getText(),
								codepostal_fld_view.getText(),
								ville_fld_view.getText()
						}
					);

					//Effacer le formulaire aprés l'ajout
					role_view.setSelectedItem("");
					nom_fld_view.setText("");
					prenom_fld_view.setText("");
					adresse_fld_view.setText("");
					codepostal_fld_view.setText("");
					ville_fld_view.setText("");
					phone_fld_view.setText("");
					mail_fld_view.setText("");
					photoPlace.setIcon(null);
				}
			});
			form_panel.add(addButton);

			updateButton = new JButton("Modifier");
			updateButton.setBounds(122, 558, 106, 43);
			// Ce code est appelé lorsque le bouton Update est cliqué.
			updateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
/*
 * 
 *  
 *  
 *  
 *  
 *  
 *  
 *  SCRUD Modifier
 *  Update
 */
//Mettre à jour le formulaire
					int i = table.getSelectedRow();
					model.setValueAt(role_view.getSelectedItem(), i, 0);
					model.setValueAt(nom_fld_view.getText(), i, 1);
					model.setValueAt(prenom_fld_view.getText(), i, 2);
					model.setValueAt(adresse_fld_view.getText(), i, 3);
					model.setValueAt(codepostal_fld_view.getText(), i, 4);
					model.setValueAt(ville_fld_view.getText(), i, 5);
					model.setValueAt(phone_fld_view.getText(), i, 6);
					model.setValueAt(mail_fld_view.getText(), i, 7);
					model.setValueAt(photoPlace.getIcon(), i, 8);
				}
			});
			form_panel.add(updateButton);
/*
 * 
 *  
 *  
 *  
 *  
 *  
 *  
 *  SCRUD Supprimer
 *  Delete
 */
			deleteButton = new JButton("Supprimer");
			deleteButton.setBounds(240, 558, 106, 43);
			form_panel.add(deleteButton);

			logo_lbl = new JLabel("Logo FoodLand");
			logo_lbl.setFont(new Font("Tahoma", Font.BOLD, 24));
			logo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
			logo_lbl.setBounds(6, 6, 348, 70);
			frame.getContentPane().add(logo_lbl);

			// Date
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //  HH:mm:ss  
			Date date = new Date(); 
			lblNewLabel = new JLabel(formatter.format(date)); //
			lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(906, 11, 463, 70);
			// Horloge
			javax.swing.Timer t = new javax.swing.Timer(1000, new ClockListener());
			t.start();
			frame.getContentPane().add(lblNewLabel);

			//  gestion de la selection sur le Jtable
			model.addTableModelListener(table); // Listener pour sélectionner les lignes dans le while normalement
			ListSelectionModel listModel = table.getSelectionModel();
			listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listModel.addListSelectionListener(this); // avec cette ligne je rentre dans le listener
//			listModel.addListSelectionListener(getTable());		
			
			// Paramétrage de la frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1395,800);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Horloge
	 */
	class ClockListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			lblNewLabel.setText(" "+ df.format(Calendar.getInstance().getTime()));
		}
	}

	/*
	 *  Getters et setters 
	 */
	public JButton getPhoto_btn_view() {
		return photo_btn_view;
	}

	public void setPhoto_btn_view(JButton photo_btn_view) {
		this.photo_btn_view = photo_btn_view;
	}

	public JTextField getNom_fld_view() {
		return nom_fld_view;
	}

	public void setNom_fld_view(JTextField nom_fld_view) {
		this.nom_fld_view = nom_fld_view;
	}

	public JTextField getPrenom_fld_view() {
		return prenom_fld_view;
	}

	public void setPrenom_fld_view(JTextField prenom_fld_view) {
		this.prenom_fld_view = prenom_fld_view;
	}

	public JTextField getAdresse_fld_view() {
		return adresse_fld_view;
	}

	public void setAdresse_fld_view(JTextField adresse_fld_view) {
		this.adresse_fld_view = adresse_fld_view;
	}

	public JTextField getCodepostal_fld_view() {
		return codepostal_fld_view;
	}

	public void setCodepostal_fld_view(JTextField codepostal_fld_view) {
		this.codepostal_fld_view = codepostal_fld_view;
	}

	public JTextField getVille_fld_view() {
		return ville_fld_view;
	}

	public void setVille_fld_view(JTextField ville_fld_view) {
		this.ville_fld_view = ville_fld_view;
	}

	public JTextField getPhone_fld_view() {
		return phone_fld_view;
	}

	public void setPhone_fld_view(JTextField phone_fld_view) {
		this.phone_fld_view = phone_fld_view;
	}

	public JTextField getMail_fld_view() {
		return mail_fld_view;
	}

	public void setMail_fld_view(JTextField mail_fld_view) {
		this.mail_fld_view = mail_fld_view;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Quand je clic sur la ligne, ça réagit 2 fois !!?");
		
		int[] line_selection;
		Object value;
		int val;

		if (!e.getValueIsAdjusting()) 
		{
			line_selection = table.getSelectedRows(); // Returns: an array of integers containing the indices of all selected rows, or an empty array if no row is selected
			System.out.println(line_selection);
			if (line_selection.length > 0) 
			{

				TableModel tm = table.getModel(); // Returns: the TableModel that provides the data displayed by this JTable
				// recuperation de l'objet colonne nd [0],[0] => clé primaire (nd)
				value = tm.getValueAt(line_selection[0],1);
//					Returns the value for the cell at columnIndex and rowIndex.
//					Parameters:
//					rowIndex the row whose value is to be queried
//					columnIndex the column whose value is to be queried
//					Returns:
//					the value Object at the specified cell
				System.out.println("Valeur colonne 0 : " + value);

				//  conversion de la valeur de l'objet value en string ( .string) et parsint en int
//				val =Integer.parseInt(value.toString()); // val de la cle priaire en Int
//				System.out.println("Valeur de la clé primaire : " + val);
				// Creation d'un objet dept avec un nd connu          
//				Dept d = new Dept();
//				d.setNd(val);
//
//
//				// System.out.print(value + " ");
//
//				try {
//					FindDeptDao dao=new FindDeptDao(d);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//				frame2.setVisible(false);

			}
		}
	}

}
