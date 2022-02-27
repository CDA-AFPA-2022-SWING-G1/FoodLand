package model;

public class Utilisateur_model {
	
	private int id_utilisateur;
	private String nom_utilisateur;
	private String prenom_utilisateur;
	private String adresse_utilisateur;
	private String code_postal_utilisateur;
	private String ville_utilisateur;
	private String tel_user;
	private String mail_user;
//	private ? photo_user;
	private int fk_id_role;
	
	// Constructors
	public Utilisateur_model() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	public Utilisateur_model(int id_utilisateur, String nom_utilisateur, String prenom_utilisateur,
			String adresse_utilisateur, String code_postal_utilisateur, String ville_utilisateur, String tel_user,
			String mail_user,/*Blob photo_user,*/ int fk_id_role) {
		//super();
		this.id_utilisateur = id_utilisateur;
		this.nom_utilisateur = nom_utilisateur;
		this.prenom_utilisateur = prenom_utilisateur;
		this.adresse_utilisateur = adresse_utilisateur;
		this.code_postal_utilisateur = code_postal_utilisateur;
		this.ville_utilisateur = ville_utilisateur;
		this.tel_user = tel_user;
		this.mail_user = mail_user;
		/*this.photo_user = photo_user;*/
		this.fk_id_role = fk_id_role;
	}


	// Getters and Setters
	public int getId_utilisateur() {
		return id_utilisateur;
	}
	
	public void setId_utilisateur(int id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
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
	public String getAdresse_utilisateur() {
		return adresse_utilisateur;
	}
	public void setAdresse_utilisateur(String adresse_utilisateur) {
		this.adresse_utilisateur = adresse_utilisateur;
	}
	public String getCode_postal_utilisateur() {
		return code_postal_utilisateur;
	}
	public void setCode_postal_utilisateur(String code_postal_utilisateur) {
		this.code_postal_utilisateur = code_postal_utilisateur;
	}
	public String getVille_utilisateur() {
		return ville_utilisateur;
	}
	public void setVille_utilisateur(String ville_utilisateur) {
		this.ville_utilisateur = ville_utilisateur;
	}
	public String getTel_user() {
		return tel_user;
	}
	public void setTel_user(String tel_user) {
		this.tel_user = tel_user;
	}
	public String getMail_user() {
		return mail_user;
	}
	public void setMail_user(String mail_user) {
		this.mail_user = mail_user;
	}
	public int getFk_id_role() {
		return fk_id_role;
	}
	public void setFk_id_role(int fk_id_role) {
		this.fk_id_role = fk_id_role;
	}
		
}
