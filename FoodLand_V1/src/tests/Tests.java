package tests;

import model.Utilisateur;
import outils.HashMe;
import outils.ValidationPatternsRegex;
import serviceMetiers.ControlMainView;
import view.MainView;

public class Tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String hashmoi = "totolapraline";
		
		String retour;
		
		HashMe has = new HashMe();
		
		String hashe = has.sha1(hashmoi);
		
		System.out.println(hashe);
		
		Utilisateur ut = null;
		MainView mv = new MainView(ut);
		ControlMainView cmv = new ControlMainView(ut, mv);
		cmv.authorizedMenusRole();
		
		
		// test match email OK
		String email = "totoa@lpraline";
		ValidationPatternsRegex vpr = new ValidationPatternsRegex();
		System.out.println(vpr.matchEmail(email));
		System.out.println(vpr.getMessage());
		
	}

}
