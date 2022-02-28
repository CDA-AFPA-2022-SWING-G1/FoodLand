package serviceMetiers;

import java.util.Vector;

import dao.DaoCommande;
import model.BonCommande;
import model.Utilisateur;
import view.InternalPanneauCommande;

public class ServiceCommande {

	private DaoCommande daoCommande;
	private InternalPanneauCommande panneau; 
	private BonCommande commande;
	
	/// commande
	public ServiceCommande(InternalPanneauCommande panneau, BonCommande commande) {
		this.panneau = panneau;
		this.commande = commande;
	}
	
	public BonCommande rechercheUneCommande(BonCommande ut) {
		commande = daoCommande.read(ut);
		System.out.println(ut.toString());
		return commande;
	}

	public int MAJUneCommande(BonCommande ut) {
		int ret = daoCommande.update(ut);
		return ret;
	}

	public int enregistrerUneCommande(BonCommande ut) {
		int ret = daoCommande.create(ut);
		return ret;
	}

	public int supprimerUneCommande(BonCommande ut) {
		return daoCommande.delete(ut);
	}

	public Vector<BonCommande> listerCommandes() {
		Vector<BonCommande> liste =  (Vector<BonCommande>) daoCommande.readAll();
		System.out.println(liste.toString());
		return liste;
	}
	
	
	
}
