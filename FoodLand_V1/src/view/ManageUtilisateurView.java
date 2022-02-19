package view;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JDesktopPane;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Controller.EmpController;
import model.Emp;
import model.Utilisateur;
import serviceMetiers.ControlUtilisateur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JRadioButton;

public class ManageUtilisateurView extends JFrame implements ListSelectionListener {
	
	private JTextField tfIdentifiant;
	private JTextField tfNom;
	private JPasswordField tfPassword;
	private JTextField textFieldJOB;
	private JTextField textFieldDateEmbauche;
	private JTextField textFieldSalaire;
	private JTextField textFieldComm;
	private JTextField textFieldNuDept;
	private JTextField textFieldNEchef;
	
	private ControlUtilisateur controlUtilisateur;
	private Utilisateur utilisateur;
	
	private JTable table;
	private DefaultTableModel dtm;
	
	public ManageUtilisateurView() {

		controlUtilisateur = new ControlUtilisateur();
		
		setPreferredSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 834, 461);
		desktopPane.setPreferredSize(new Dimension(30, 30));
		getContentPane().add(desktopPane);
		
		JLabel lblMAJ = new JLabel("Panneau gestion personnel");
		lblMAJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMAJ.setBounds(10, 11, 205, 20);
		desktopPane.add(lblMAJ);
		
		JLabel lblIdEmp = new JLabel("Identifiant:");
		lblIdEmp.setBounds(10, 72, 70, 14);
		desktopPane.add(lblIdEmp);
		
		JLabel lblNomEmp = new JLabel("Nom:");
		lblNomEmp.setBounds(10, 100, 70, 14);
		desktopPane.add(lblNomEmp);
		
		JLabel lblPassword = new JLabel("Mot de passe:");
		lblPassword.setBounds(10, 340, 70, 14);
		desktopPane.add(lblPassword);
		
		tfIdentifiant = new JTextField();
		tfIdentifiant.setBounds(110, 69, 63, 20);
		desktopPane.add(tfIdentifiant);
		tfIdentifiant.setColumns(10);
		
		tfNom = new JTextField();
		tfNom.setBounds(110, 97, 134, 20);
		desktopPane.add(tfNom);
		tfNom.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(110, 337, 134, 20);
		desktopPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnMAJ = new JButton("Modifier");
		btnMAJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utilisateur = new Utilisateur();
				utilisateur.set      (Integer.parseInt(tfIdentifiant.getText()));
				utilisateur.setNomEmp(tfNom.getText());
				utilisateur.setJobEmp(textFieldJOB.getText());
				utilisateur.setDateEmbauche(Date.valueOf(textFieldDateEmbauche.getText()));
				utilisateur.setSalaireEmp(Double.parseDouble(textFieldSalaire.getText()));
				utilisateur.setCommEmp(Double.parseDouble(textFieldComm.getText()));
				utilisateur.setNd(Integer.parseInt(textFieldNuDept.getText()));
				utilisateur.setNeChef(Integer.parseInt(textFieldNEchef.getText()));
				utilisateur.setMdp(tfPassword.getText());
				System.out.println(utilisateur.toString());
				
				int ret = controlUtilisateur.MAJUnUtilisateur(utilisateur);
				
				if(ret == 1) {
					JOptionPane.showMessageDialog(null, "Mise à jour de l'utilisateur" + utilisateur.getNomEmp() + " effectuée. " + ret );
				}else {
					JOptionPane.showMessageDialog(null, "Mise à jour de l'utilisateur" + utilisateur.getNomEmp() + " non effectuée. " + ret);
				}
			}
		});
		btnMAJ.setBounds(347, 429, 70, 23);
		desktopPane.add(btnMAJ);
		
		JLabel lblJob = new JLabel("R\u00F4le:");
		lblJob.setBounds(10, 125, 46, 14);
		desktopPane.add(lblJob);
		
		textFieldJOB = new JTextField();
		textFieldJOB.setBounds(110, 128, 134, 20);
		desktopPane.add(textFieldJOB);
		textFieldJOB.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Date embauche:");
		lblNewLabel.setBounds(10, 163, 86, 14);
		desktopPane.add(lblNewLabel);
		
		textFieldDateEmbauche = new JTextField();
		textFieldDateEmbauche.setBounds(110, 159, 134, 20);
		desktopPane.add(textFieldDateEmbauche);
		textFieldDateEmbauche.setColumns(10);
		
		JLabel lblSalaire = new JLabel("Salaire:");
		lblSalaire.setBounds(10, 193, 46, 14);
		desktopPane.add(lblSalaire);
		
		JLabel lblNewLabel_2 = new JLabel("Commission:");
		lblNewLabel_2.setBounds(10, 225, 70, 14);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Num\u00E9ro dept:");
		lblNewLabel_3.setBounds(10, 257, 86, 14);
		desktopPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Num\u00E9ro chef:");
		lblNewLabel_4.setBounds(10, 288, 86, 14);
		desktopPane.add(lblNewLabel_4);
		
		textFieldSalaire = new JTextField();
		textFieldSalaire.setBounds(110, 190, 134, 20);
		desktopPane.add(textFieldSalaire);
		textFieldSalaire.setColumns(10);
		
		textFieldComm = new JTextField();
		textFieldComm.setBounds(110, 222, 134, 20);
		desktopPane.add(textFieldComm);
		textFieldComm.setColumns(10);
		
		textFieldNuDept = new JTextField();
		textFieldNuDept.setBounds(110, 254, 38, 20);
		desktopPane.add(textFieldNuDept);
		textFieldNuDept.setColumns(10);
		
		textFieldNEchef = new JTextField();
		textFieldNEchef.setBounds(110, 285, 38, 20);
		desktopPane.add(textFieldNEchef);
		textFieldNEchef.setColumns(10);
		
		JButton btnRecherche = new JButton("Rechercher");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfIdentifiant.getText().isEmpty()) {
					clearJTextField();
					//ArrayList<Emp> le = empControl.lister();
//					Vector<Object> la = empControl.listerVector();
//					System.out.println("Vector" + la.toString());
					dtm = new DefaultTableModel();
					
					ArrayList listCols = empControl.cols();
					for(int i = 0; i <  listCols.size(); i++) {
						String s = (String) listCols.get(i);
						dtm.addColumn(s);
					}
					
					//TableModele tableModel = new TableModele(empControl.cols(), la);
					table.setModel(dtm);
					
					Vector<Emp> listEmp = empControl.listerVectorEmp();
					System.out.println("taille: " + empControl.listerVectorEmp().size());
					for(int i = 0; i < listEmp.size(); i++) {
						Emp emp = (Emp) listEmp.get(i);
						//System.out.println("Row: " + emp.toString());
						Vector row =  new Vector<>();
						row.add(emp.getIdEmp());
						row.add(emp.getNomEmp());
						row.add(emp.getJobEmp());
						row.add(emp.getDateEmbauche());
						row.add(emp.getSalaireEmp());
						row.add(emp.getCommEmp());
						row.add(emp.getNd());
						row.add(emp.getNeChef());
						dtm.addRow(row);
					}
					dtm.addTableModelListener(table);
					
					Iterator<Emp> it  = empControl.lister().iterator();
					while(it.hasNext()) {
					Emp emp = new Emp();	
						emp = it.next();
						System.out.println("from iterator: " + emp.toString());
					}
					
					//test TableModeleGenericite
					Iterator<Emp> it2  = empControl.cols().iterator();
					Vector cols = new Vector<>();
					while(it.hasNext()) {
						
						cols.add(it2.next());
					}

					TableModeleGenericite tmg = new TableModeleGenericite(empControl.listerVectorEmp(), cols);
					
					JFrame f = new JFrame();
					JTable tab = new JTable(tmg);
					f.getContentPane().add(tab);
					f.setSize(300, 500);
					f.setVisible(true);
					// fintest TableModeleGenericite
					
					
				}else {
					
				// traiter une recherche
				utilisateur = new Emp();
				utilisateur.setIdEmp(Integer.parseInt(tfIdentifiant.getText()));
				utilisateur = empControl.rechercheUnEmp(utilisateur);
				tfIdentifiant.setText(String.valueOf(utilisateur.getIdEmp()));
				tfNom.setText(utilisateur.getNomEmp());
				textFieldJOB.setText(utilisateur.getJobEmp());
				textFieldSalaire.setText(String.valueOf(utilisateur.getSalaireEmp()));
				textFieldComm.setText(String.valueOf(utilisateur.getCommEmp()));
				textFieldDateEmbauche.setText(utilisateur.getDateEmbauche().toString());
				textFieldNEchef.setText(String.valueOf(utilisateur.getNeChef()));
				textFieldNuDept.setText(String.valueOf(utilisateur.getNd()));
				}
				
			}
		});
		btnRecherche.setBounds(267, 12, 100, 23);
		desktopPane.add(btnRecherche);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 46, 557, 376);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.setBounds(427, 429, 70, 23);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Cr\u00E9er");
		btnNewButton_2.setBounds(267, 429, 70, 23);
		desktopPane.add(btnNewButton_2);
		
		// gestion des selections sur le tableau
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(this);
		
		this.setSize(new Dimension(850, 500));
		this.setVisible(true);
	}
	
	
	public void clearJTextField() {
		tfNom.setText("");
		textFieldJOB.setText("");
		textFieldSalaire.setText("");
		textFieldComm.setText("");
		textFieldDateEmbauche.setText("");
		textFieldNEchef.setText("");
		textFieldNuDept.setText("");
		tfPassword.setText("");
	}
	
	public void populateJTextField(Emp emp) {
		tfIdentifiant.setText(String.valueOf(emp.getIdEmp()));
		tfNom.setText(emp.getNomEmp());
		textFieldJOB.setText(emp.getJobEmp());
		textFieldSalaire.setText(String.valueOf(emp.getSalaireEmp()));
		textFieldComm.setText(String.valueOf(emp.getCommEmp()));
		textFieldDateEmbauche.setText(emp.getDateEmbauche().toString());
		textFieldNEchef.setText(String.valueOf(emp.getNeChef()));
		textFieldNuDept.setText(String.valueOf(emp.getNd()));
		tfPassword.setText("");
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int[] sel;
		utilisateur = new Emp();
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
		utilisateur.setIdEmp((int) dtm.getValueAt(sel[0], 0));
		utilisateur.setNomEmp((String) dtm.getValueAt(sel[0], 1));
		utilisateur.setJobEmp((String) dtm.getValueAt(sel[0], 2));
		utilisateur.setDateEmbauche((java.util.Date) dtm.getValueAt(sel[0], 3));
		utilisateur.setSalaireEmp((double) dtm.getValueAt(sel[0], 4));
		utilisateur.setCommEmp((double) dtm.getValueAt(sel[0], 5));
		utilisateur.setNd((int) dtm.getValueAt(sel[0], 6));
		utilisateur.setNeChef((int) dtm.getValueAt(sel[0], 7));
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

