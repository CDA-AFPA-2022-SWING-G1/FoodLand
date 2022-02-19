package model;

import java.util.List;

public class Entreprise {

	
	private Integer id_entreprise;
	/**
	 * 
	 */
	private Type_entreprise type_entreprise;
	/**
	 * 
	 */
	private Integer id_type_entrperise;
	/**
	 * 
	 */
	private String siret_entreprise, adresse_entreprise, nom_entreprise, 
						ville_entreprise, code_postale_entreprise, pays_entreprise, telephone_entreprise, mail_entreprise;
	/**
	 * 
	 */
	private List<Type_entreprise> liste_types_entreprise;
	
	public Entreprise() {}
	
	public Entreprise(Integer id_entreprise, Type_entreprise type_entreprise, Integer id_type_entrperise,
			String siret_entreprise, String adresse_entreprise, String nom_entreprise, String ville_entreprise,
			String code_postale_entreprise, String pays_entreprise, String telephone_entreprise, String mail_entreprise,
			List<Type_entreprise> liste_types_entreprise) {
		this.id_entreprise = id_entreprise;
		this.type_entreprise = type_entreprise;
		this.id_type_entrperise = id_type_entrperise;
		this.siret_entreprise = siret_entreprise;
		this.adresse_entreprise = adresse_entreprise;
		this.nom_entreprise = nom_entreprise;
		this.ville_entreprise = ville_entreprise;
		this.code_postale_entreprise = code_postale_entreprise;
		this.pays_entreprise = pays_entreprise;
		this.telephone_entreprise = telephone_entreprise;
		this.mail_entreprise = mail_entreprise;
		this.liste_types_entreprise = liste_types_entreprise;
	}

	public Integer getId_entreprise() {
		return id_entreprise;
	}

	public void setId_entreprise(Integer id_entreprise) {
		this.id_entreprise = id_entreprise;
	}

	public Type_entreprise getType_entreprise() {
		return type_entreprise;
	}

	public void setType_entreprise(Type_entreprise type_entreprise) {
		this.type_entreprise = type_entreprise;
	}

	public Integer getId_type_entrperise() {
		return id_type_entrperise;
	}

	public void setId_type_entrperise(Integer id_type_entrperise) {
		this.id_type_entrperise = id_type_entrperise;
	}

	public String getSiret_entreprise() {
		return siret_entreprise;
	}

	public void setSiret_entreprise(String siret_entreprise) {
		this.siret_entreprise = siret_entreprise;
	}

	public String getAdresse_entreprise() {
		return adresse_entreprise;
	}

	public void setAdresse_entreprise(String adresse_entreprise) {
		this.adresse_entreprise = adresse_entreprise;
	}

	public String getNom_entreprise() {
		return nom_entreprise;
	}

	public void setNom_entreprise(String nom_entreprise) {
		this.nom_entreprise = nom_entreprise;
	}

	public String getVille_entreprise() {
		return ville_entreprise;
	}

	public void setVille_entreprise(String ville_entreprise) {
		this.ville_entreprise = ville_entreprise;
	}

	public String getCode_postale_entreprise() {
		return code_postale_entreprise;
	}

	public void setCode_postale_entreprise(String code_postale_entreprise) {
		this.code_postale_entreprise = code_postale_entreprise;
	}

	public String getPays_entreprise() {
		return pays_entreprise;
	}

	public void setPays_entreprise(String pays_entreprise) {
		this.pays_entreprise = pays_entreprise;
	}

	public String getTelephone_entreprise() {
		return telephone_entreprise;
	}

	public void setTelephone_entreprise(String telephone_entreprise) {
		this.telephone_entreprise = telephone_entreprise;
	}

	public String getMail_entreprise() {
		return mail_entreprise;
	}

	public void setMail_entreprise(String mail_entreprise) {
		this.mail_entreprise = mail_entreprise;
	}

	public List<Type_entreprise> getListe_types_entreprise() {
		return liste_types_entreprise;
	}

	public void setListe_types_entreprise(List<Type_entreprise> liste_types_entreprise) {
		this.liste_types_entreprise = liste_types_entreprise;
	}
	
	
	
}
