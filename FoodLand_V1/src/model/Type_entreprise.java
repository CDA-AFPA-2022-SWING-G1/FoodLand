package model;

import java.util.List;

public class Type_entreprise {

	/**
	 * 
	 */
	private int id_type_entreprise;
	/**
	 * 
	 */
	private String description_type_entreprise; // fournisseur, client ou les deux 
	/**
	 * relation one to many permet de lister les entreprises pour un type cible
	 */
	public List<Entreprise> liste_entreprises;

	public Type_entreprise() {}
	
	public int getId_type_entreprise() {
		return id_type_entreprise;
	}

	public void setId_type_entreprise(int id_type_entreprise) {
		this.id_type_entreprise = id_type_entreprise;
	}

	public String getDescription_type_entreprise() {
		return description_type_entreprise;
	}

	public void setDescription_type_entreprise(String description_type_entreprise) {
		this.description_type_entreprise = description_type_entreprise;
	}

	public List<Entreprise> getListe_entreprises() {
		return liste_entreprises;
	}

	public void setListe_entreprises(List<Entreprise> liste_entreprises) {
		this.liste_entreprises = liste_entreprises;
	}
	
	
	
}
