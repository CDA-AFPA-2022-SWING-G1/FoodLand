package serviceMetiers;


import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dao.DaoUtilisateur;
import model.Utilisateur;
import outils.HashMe;
import view.AuthentificationView;
import view.MainView;
import view.ManageUtilisateurView;


public class ControlUtilisateur {

	
	private DaoUtilisateur daoUtilisateur;
	private Utilisateur utilisateur;
	
	private ManageUtilisateurView muv;
	
	public ControlUtilisateur() {
		daoUtilisateur = new DaoUtilisateur();
	}
	
	public ControlUtilisateur(AuthentificationView v) {
		daoUtilisateur = new DaoUtilisateur();
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
	
	
	public void engageMainView(Utilisateur ut) {
		// vrai mvc
		MainView mv = new MainView(ut);
		mv.setVisible(false);
		ControlMainView cmv = new ControlMainView(ut, mv);
		
	}
	
	public Utilisateur rechercheUnUtilisateur(Utilisateur ut) {
		ut = daoUtilisateur.read(ut);
		System.out.println(ut.toString());
		return ut;
	}

	public int MAJUnUtilisateur(Utilisateur ut) {
		int ret = daoUtilisateur.update(ut);
		return ret;
	}

	public int enregistrerUnUtilisateur(Utilisateur ut) {
		int ret = daoUtilisateur.create(ut);
		return ret;
	}

	public int supprimerUnUtilisateur(Utilisateur ut) {
		return daoUtilisateur.delete(ut);
	}

	public ArrayList<Utilisateur> lister() {
		ArrayList<Utilisateur> liste = (ArrayList<Utilisateur>) daoUtilisateur.readAll();
		System.out.println(liste.toString());
		return liste;
	}
	
	
	public DefaultTableModel getModelTable() {
		Vector cols = DaoUtilisateur.listeColumns();
		DefaultTableModel dtm = new  DefaultTableModel();
		return dtm;
	}
}
