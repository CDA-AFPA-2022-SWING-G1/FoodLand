package serviceMetiers;

import model.Utilisateur;
import view.MainView;

public class ControlMainView {

	private Utilisateur u;
	private MainView mv;
	
	public ControlMainView(Utilisateur u, MainView mv) {
		this.u = u;
		this.mv = mv;
	}
	
	public void authorizedMenusRole() {

		//String role = u.getRole_utilisateur().getLibelle_role();
		String roleTest = "ADMIN";
		switch (roleTest) {
		
		case "ADMIN": 
						mv.getMenuFichiers().setVisible(true);
						mv.getMenuEdition().setVisible(true);
						mv.getMenuVentes().setVisible(false);
						mv.getMenuGestion().setVisible(true);
						mv.getMenuItemGestionStock().setVisible(false);
						mv.getMenuItemGestionVentes().setVisible(false);
						mv.getMenuItemGestionDuPersonnel().setVisible(true);
			
			break;
			
		case "ACHETEUR":
						mv.getMenuFichiers().setVisible(true);
						mv.getMenuEdition().setVisible(true);
						mv.getMenuVentes().setVisible(false);
						mv.getMenuGestion().setVisible(true);
						mv.getMenuItemGestionStock().setVisible(false);
						mv.getMenuItemGestionVentes().setVisible(true);
						mv.getMenuItemGestionDuPersonnel().setVisible(false);
			
			break;
			
		case "VENDEUR":
			
			break;
			
		case "GERANT":
			
			break;
			
			default:
	}
	

	
}
	
//	private void autorisationListeRoles() {
//	List<Role> roles = util.getListe_Roles();
//	
//	for(Role r : roles) {
//		String role = r.getLibelle_role();
//		switch (role) {
//		
//		case "ADMIN": 
//			
//			break;
//			
//		case "ACHETEUR":
//			
//			break;
//			
//		case "VENDEUR":
//			
//			break;
//			
//		case "GERANT":
//			
//			break;
//			
//			default:
//	}
//	
//}
//	
//}
}
