// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package model;

import java.util.List;

/************************************************************/
/**
 * 
 */
public class Categorie_produit {
	
	/**
	 * 
	 */
	private int id_categorie;
	
	/**
	 * 
	 */
	private String description_categorie;
	
	/**
	 *  relation one to many liste des produits de la categorie cible
	 */
	public List<Produit> liste_produits;

	public Categorie_produit() {
	}

	public Categorie_produit(int id_categorie, String description_categorie) {
		this.id_categorie = id_categorie;
		this.description_categorie = description_categorie;
	}

	public Categorie_produit(int id_categorie, String description_categorie, List<Produit> liste_produits) {
		this.id_categorie = id_categorie;
		this.description_categorie = description_categorie;
		this.liste_produits = liste_produits;
	}

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getDescription_categorie() {
		return description_categorie;
	}

	public void setDescription_categorie(String description_categorie) {
		this.description_categorie = description_categorie;
	}

	public List<Produit> getListe_produits() {
		return liste_produits;
	}

	public void setListe_produits(List<Produit> liste_produits) {
		this.liste_produits = liste_produits;
	}

	@Override
	public String toString() {
		return "Categorie_produit [id_categorie=" + id_categorie + ", description_categorie=" + description_categorie
				+ ", liste_produits=" + liste_produits.toString() + "]";
	}

}
