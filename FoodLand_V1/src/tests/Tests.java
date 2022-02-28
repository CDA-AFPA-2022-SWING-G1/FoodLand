package tests;

import java.util.ArrayList;
import java.util.Arrays;

import dao.DaoColumns;
import dao.DaoCommande;
import dao.DaoUtilisateur;
import model.BonCommande;
import model.LigneCommande;
import model.Utilisateur;
import outils.HashMe;
import outils.ValidationPatternsRegex;
import serviceMetiers.ControlMainView;
import serviceMetiers.ControlUtilisateur;
import view.MainView;

public class Tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String hashmoi = "totolapraline";
		
		String retour;
		
		HashMe has = new HashMe();
		
		String hashe = has.sha1(hashmoi);
		
		System.out.println(hashe);
		
		DaoUtilisateur daou = new DaoUtilisateur();
		ArrayList<String> colonnes  = (ArrayList<String>) daou.listeColumns();
		System.out.println("colnnes : " + colonnes.size());
		
		
		Utilisateur ut = null;
		MainView mv = new MainView(ut);
		ControlMainView cmv = new ControlMainView(ut, mv);
		cmv.authorizedMenusRole();
		
		
		// test match email OK
		String email = "totoa@lpraline";
		ValidationPatternsRegex vpr = new ValidationPatternsRegex();
		System.out.println(vpr.matchEmail(email));
		System.out.println(vpr.getMessage());
		
		// test match numeric et taille du numeric
		String phone = "012345677";
		ValidationPatternsRegex vpr2 = new ValidationPatternsRegex();
		System.out.println("hpoe " + vpr2.matchNumeric(phone, 8));
		System.out.println(vpr2.getMessage());
		
		
		// test DaoColumns Ok
		DaoColumns daoCols = new DaoColumns();
		ArrayList<String> listeCols = new ArrayList<>();
		listeCols = daoCols.columnsTable("foodland", "commande");
		System.out.println(listeCols);
		
		// test DaoCommandes readCommandesByCriteria "sans lignes"
		DaoCommande daoCommande = new DaoCommande();
		ArrayList<BonCommande> listeCommandesById;
		listeCommandesById = (ArrayList<BonCommande>) daoCommande.readCommandesByCriteria("id_commande", "1");
		System.out.println("liste commandes" + listeCommandesById.toString());
		
		//test DaoCommandes read one and relational N with ligneCommande
		BonCommande b1 = new BonCommande();
		b1.setId_bonCommande(1);
		b1 = daoCommande.read(b1);
		System.out.println("BonCommande 1: " + b1.toString());
		ArrayList<LigneCommande> liste_ligne = (ArrayList<LigneCommande>) b1.getListe_lignes();
		for(LigneCommande l : liste_ligne) {
			System.out.println("ligne commande b1: " + l.toString());
		}
		
		// test recherche utilisateur par le service utilisateur + compte
		ControlUtilisateur serviceUt = new ControlUtilisateur();
		Utilisateur u = new Utilisateur();
		u.setIdUtilisateur(7);
		u = serviceUt.rechercheUnUtilisateur(u);
		System.out.println(u.toString());
		//System.out.println(u.getPhoto_utilisateur());
		
		// test dao tilisateurs readAll avec jointures relationnelles
		ArrayList<Utilisateur> llisut = (ArrayList<Utilisateur>) daou.readAll();
		System.out.println("liste utils readall: " + llisut.toString());
	}

}
