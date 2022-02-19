package model;

public class Type_commande {

	/**
	 * 
	 */
	private int id_type_commande;
	/**
	 * 
	 */
	private String libelle_type_commande;
	
	public Type_commande() {}
	
	public Type_commande(int id_type_commande, String libelle_type_commande) {
		super();
		this.id_type_commande = id_type_commande;
		this.libelle_type_commande = libelle_type_commande;
	}

	public int getId_type_commande() {
		return id_type_commande;
	}

	public void setId_type_commande(int id_type_commande) {
		this.id_type_commande = id_type_commande;
	}

	public String getLibelle_type_commande() {
		return libelle_type_commande;
	}

	public void setLibelle_type_commande(String libelle_type_commande) {
		this.libelle_type_commande = libelle_type_commande;
	}


	
	
}
