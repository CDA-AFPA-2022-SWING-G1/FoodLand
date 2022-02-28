package serviceMetiers;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.DaoCompte;
import dao.DaoRole;
import dao.DaoUtilisateur;
import model.Compte;
import model.Role;
import model.Utilisateur;
import outils.HashMe;
import outils.ValidationPatternsRegex;
import view.AuthentificationView;
import view.MainView;
import view.ManageUtilisateurView;


public class ControlUtilisateur {

	
	private DaoUtilisateur daoUtilisateur;
	private Utilisateur utilisateur;
	
	private DaoRole daoRole;
	private Role role;
	
	private DaoCompte daoCompte;
	private Compte compte;
	
	private ManageUtilisateurView muv;
	
	public ControlUtilisateur() {
		daoUtilisateur = new DaoUtilisateur();
	}
	
	public ControlUtilisateur(AuthentificationView v) {
		daoUtilisateur = new DaoUtilisateur();
	}
	
	public ControlUtilisateur(ManageUtilisateurView muv) {
		daoUtilisateur = new DaoUtilisateur();
		this.muv = muv;
		manageButtonCreer();
	}
	
	public boolean authentifier(Utilisateur e) {
		System.out.println("utilsateur recherché: " + e.toString());
		int id = e.getIdUtilisateur();
		String nom = e.getNom_utilisateur();
		String mdp = e.getMdp();

		daoUtilisateur = new DaoUtilisateur();
		this.utilisateur = new Utilisateur();
		
		utilisateur = daoUtilisateur.read(e);
		System.out.println("emp trouvé: " + utilisateur.toString());
		
		if(utilisateur.getIdUtilisateur() == id && utilisateur.getNom_utilisateur().equals(nom) && utilisateur.getMdp().equals(mdp)) { 
			
			this.setUtilisateur(utilisateur);
			return true;
			
		}else{
			return false;
		}
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur ut) {
		this.utilisateur = ut;
	}
	
	
	// déplacer vers controlMainView ou control de la frame aux boutons lançant les panneaux
	public void engageMainView(Utilisateur ut) {
		//
		MainView mv = new MainView(ut);
		mv.setVisible(false);
		ControlMainView cmv = new ControlMainView(ut, mv);
		
	}
	
	public Utilisateur rechercheUnUtilisateur(Utilisateur ut) {
		ut = daoUtilisateur.read(ut);
		DaoCompte daoCompte = new DaoCompte();
		Compte c = new Compte();
		c.setId_utilisateur(ut.getFk_id_compte());
		c = daoCompte.read(c);
		
		ut.setCompte_utilisateur(c);
//		System.out.println(c.toString());
//		System.out.println(ut.toString());
		return ut;
	}

	public int MAJUnUtilisateur(Utilisateur ut) {
		int ret = daoUtilisateur.update(ut);
		return ret;
	}

	public int enregistrerUnUtilisateur(Utilisateur ut) {
		int ret = daoUtilisateur.create(ut);
		// role & compte
		return ret;
	}

	public int supprimerUnUtilisateur(Utilisateur ut) {
		return daoUtilisateur.delete(ut);
	}

	public Vector<Utilisateur> lister() {
		Vector<Utilisateur> liste =  (Vector<Utilisateur>) daoUtilisateur.readAll();
		System.out.println(liste.toString());
		return liste;
	}
	
	public ArrayList cols() {
		ArrayList<String> liste = (ArrayList<String>) daoUtilisateur.listeColumns();
		System.out.println("cols: " + liste.size());
		return liste;
	}
	
	public DefaultTableModel getModelTable() {
		//Vector cols = cols();
		DefaultTableModel dtm = new  DefaultTableModel();
		
		for(int i = 0; i <  cols().size(); i++) {
			String s = (String) cols().get(i);
			dtm.addColumn(s);
		}
		return dtm;
	}
	
	public ArrayList<Role> populateRoleComboBox() {
		daoRole = new DaoRole();
		ArrayList<Role> listeRoles = (ArrayList<Role>) daoRole.readAll();
		return listeRoles;
	}
	
	public void manageButtonCreer() {
		muv.getBtnCreer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.out.println("action créer managée");
			//daoUtilisateur.create(muv.getUtilisateur());
			
			}
		});
	}
	
	
//	public void validerLesSaisies() {
//		ValidationPatternsRegex vpr = new ValidationPatternsRegex();
//		boolean ok = vpr.matchEmail(textFieldMail.getText());
//		
//		// mail
//		if(ok==false && !textFieldMail.getText().isEmpty()) {
//			//JOptionPane.showMessageDialog(null, vpr.getMessage());
//			textFieldMail.setBackground(Color.RED);
//		}else {
//			textFieldMail.setBackground(null);
//		}
//		
//		//pass
//		ok = vpr.matchPassword(tfPasswordCompte.getText());
//		if(ok==false) {
//			JOptionPane.showMessageDialog(null, vpr.getMessage());
//			tfPasswordCompte.setBackground(Color.RED);
//		}else {
//			tfPasswordCompte.setBackground(Color.WHITE);
//		}
//		
//		//numeric
//		ArrayList<Integer> listNumerics  = new ArrayList<>();
////		listNumerics.add(Integer.valueOf(textFieldCodePostale.getText()));
////		listNumerics.add(Integer.valueOf(textFieldRole.getText()));
//		//ok = vpr.matchNumeric(listNumerics);
//		if(ok==false) {
//			JOptionPane.showMessageDialog(null, vpr.getMessage());
//			tfPasswordCompte.setBackground(Color.RED);
//		}else {
//			tfPasswordCompte.setBackground(Color.WHITE);
//			
//		}
//	}
}
