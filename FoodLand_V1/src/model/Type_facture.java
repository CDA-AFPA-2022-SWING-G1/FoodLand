package model;

public class Type_facture {

	/**
	 * 
	 */
	private int id_type_facture;
	/**
	 * 
	 */
	private String libelle_type_facture;
	
	
	public Type_facture() {}
	
	
	public Type_facture(int id_type_facture, String libelle_type_facture) {
		this.id_type_facture = id_type_facture;
		this.libelle_type_facture = libelle_type_facture;
	}


	public int getId_type_facture() {
		return id_type_facture;
	}


	public void setId_type_facture(int id_type_facture) {
		this.id_type_facture = id_type_facture;
	}


	public String getLibelle_type_facture() {
		return libelle_type_facture;
	}


	public void setLibelle_type_facture(String libelle_type_facture) {
		this.libelle_type_facture = libelle_type_facture;
	}
	
	
}
