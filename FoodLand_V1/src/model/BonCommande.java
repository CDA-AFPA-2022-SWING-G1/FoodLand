// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/************************************************************/
/**
 * 
 */
public class BonCommande {
	/**
	 * id
	 */
	private Integer id_bonCommande;
	/**
	 * dates
	 */
	private Date date_creation_commande, date_preparation_commande, date_retrait_commande;

	/**
	 * relation one to many table lignecommandes
	 */
	private List<Produit> list_produits; // si Produit a pour attribut une quantit�/poids � pr�ciser pour cette commande
	// ou 
	private HashMap<Produit, Integer> produit_quantite; // map produit & quantit� � pr�ciser en dehors du produit
	/**
	 *  
	 */
	private Entreprise entreprise; // acheteur 
	private Integer fk_id_entreprise;
	/**
	 *  
	 */
	private Type_commande type_commande; // acheteur 
	private Integer fk_id_commande;
	/**
	 * relation one to many 
	 */
	private List<Utilisateur> utilisateurs;  // delivreur // preparateur // commercial
	
	public BonCommande() {}
	
	public BonCommande(Integer id_bonCommande, Date date_creation_commande, Date date_preparation_commande,
			Date date_retrait_commande, List<Produit> list_produits, HashMap<Produit, Integer> produit_quantite,
			Entreprise entreprise, Integer fk_id_entreprise, Type_commande type_commande, Integer fk_id_commande,
			List<Utilisateur> utilisateurs) {
		
		this.id_bonCommande = id_bonCommande;
		this.date_creation_commande = date_creation_commande;
		this.date_preparation_commande = date_preparation_commande;
		this.date_retrait_commande = date_retrait_commande;
		this.list_produits = list_produits;
		this.produit_quantite = produit_quantite;
		this.entreprise = entreprise;
		this.fk_id_entreprise = fk_id_entreprise;
		this.type_commande = type_commande;
		this.fk_id_commande = fk_id_commande;
		this.utilisateurs = utilisateurs;
	}

	public Integer getId_bonCommande() {
		return id_bonCommande;
	}

	public void setId_bonCommande(Integer id_bonCommande) {
		this.id_bonCommande = id_bonCommande;
	}

	public Date getDate_creation_commande() {
		return date_creation_commande;
	}

	public void setDate_creation_commande(Date date_creation_commande) {
		this.date_creation_commande = date_creation_commande;
	}

	public Date getDate_preparation_commande() {
		return date_preparation_commande;
	}

	public void setDate_preparation_commande(Date date_preparation_commande) {
		this.date_preparation_commande = date_preparation_commande;
	}

	public Date getDate_retrait_commande() {
		return date_retrait_commande;
	}

	public void setDate_retrait_commande(Date date_retrait_commande) {
		this.date_retrait_commande = date_retrait_commande;
	}

	public List<Produit> getList_produits() {
		return list_produits;
	}

	public void setList_produits(List<Produit> list_produits) {
		this.list_produits = list_produits;
	}

	public HashMap<Produit, Integer> getProduit_quantite() {
		return produit_quantite;
	}

	public void setProduit_quantite(HashMap<Produit, Integer> produit_quantite) {
		this.produit_quantite = produit_quantite;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
		
	      if (entreprise != null)
	      {
	         this.fk_id_entreprise = entreprise.getId_entreprise();    
	      }
	}
	

	public Integer getFk_id_entreprise() {
		return fk_id_entreprise;
	}

	public void setFk_id_entreprise(Integer fk_id_entreprise) {
		this.fk_id_entreprise = fk_id_entreprise;
	}

	public Type_commande getType_commande() {
		return type_commande;
	}

	public void setType_commande(Type_commande type_commande) {
		this.type_commande = type_commande;
		
	      if (type_commande != null)
	      {
	         this.fk_id_commande = type_commande.getId_type_commande();    
	      }
	}
	

	public Integer getFk_id_commande() {
		return fk_id_commande;
	}

	public void setFk_id_commande(Integer fk_id_commande) {
		this.fk_id_commande = fk_id_commande;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	
	
}
