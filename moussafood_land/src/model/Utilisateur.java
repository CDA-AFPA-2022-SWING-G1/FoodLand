package model;

public class Utilisateur {
	private int idUtilisateur;
	private String nom_utilisateur, prenom_utilisateur;
	//private List<Adresse> list_Addresse;
	//private List<Role> liste_Roles;
	
	private String mdp;
	public Utilisateur() {}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom_utilisateur() {
		return nom_utilisateur;
	}

	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}

	public String getPrenom_utilisateur() {
		return prenom_utilisateur;
	}

	public void setPrenom_utilisateur(String prenom_utilisateur) {
		this.prenom_utilisateur = prenom_utilisateur;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Utilisateur(int idUtilisateur, String mdp) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.mdp = mdp;
	}
}
