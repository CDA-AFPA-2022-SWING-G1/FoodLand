package model;

public class LigneCommande {

	private Integer id_ligne_commande;
	private String code_barre_ligne_commande;
	private String designation_ligne_commande;
	private double prix_unitaire_ligne_commande;
	private double poids_quantite_ligne_commande;
	private String TVA_ligne_commande;
	
	private Integer fk_id_commande;
	private Integer fk_id_categorie_lignecommandes;
	
	public LigneCommande() {
	}

	public LigneCommande(Integer id_ligne_commande, String code_barre_ligne_commande,
			String designation_ligne_commande, double prix_unitaire_ligne_commande,
			double poids_quantite_ligne_commande, String tVA_ligne_commande, Integer fk_id_commande,
			Integer fk_id_categorie_lignecommandes) {
		super();
		this.id_ligne_commande = id_ligne_commande;
		this.code_barre_ligne_commande = code_barre_ligne_commande;
		this.designation_ligne_commande = designation_ligne_commande;
		this.prix_unitaire_ligne_commande = prix_unitaire_ligne_commande;
		this.poids_quantite_ligne_commande = poids_quantite_ligne_commande;
		TVA_ligne_commande = tVA_ligne_commande;
		this.fk_id_commande = fk_id_commande;
		this.fk_id_categorie_lignecommandes = fk_id_categorie_lignecommandes;
	}

	public Integer getId_ligne_commande() {
		return id_ligne_commande;
	}

	public void setId_ligne_commande(Integer id_ligne_commande) {
		this.id_ligne_commande = id_ligne_commande;
	}

	public String getCode_barre_ligne_commande() {
		return code_barre_ligne_commande;
	}

	public void setCode_barre_ligne_commande(String code_barre_ligne_commande) {
		this.code_barre_ligne_commande = code_barre_ligne_commande;
	}

	public String getDesignation_ligne_commande() {
		return designation_ligne_commande;
	}

	public void setDesignation_ligne_commande(String designation_ligne_commande) {
		this.designation_ligne_commande = designation_ligne_commande;
	}

	public double getPrix_unitaire_ligne_commande() {
		return prix_unitaire_ligne_commande;
	}

	public void setPrix_unitaire_ligne_commande(double prix_unitaire_ligne_commande) {
		this.prix_unitaire_ligne_commande = prix_unitaire_ligne_commande;
	}

	public double getPoids_quantite_ligne_commande() {
		return poids_quantite_ligne_commande;
	}

	public void setPoids_quantite_ligne_commande(double poids_quantite_ligne_commande) {
		this.poids_quantite_ligne_commande = poids_quantite_ligne_commande;
	}

	public String getTVA_ligne_commande() {
		return TVA_ligne_commande;
	}

	public void setTVA_ligne_commande(String tVA_ligne_commande) {
		TVA_ligne_commande = tVA_ligne_commande;
	}

	public Integer getFk_id_commande() {
		return fk_id_commande;
	}

	public void setFk_id_commande(Integer fk_id_commande) {
		this.fk_id_commande = fk_id_commande;
	}

	public Integer getFk_id_categorie_lignecommandes() {
		return fk_id_categorie_lignecommandes;
	}

	public void setFk_id_categorie_lignecommandes(Integer fk_id_categorie_lignecommandes) {
		this.fk_id_categorie_lignecommandes = fk_id_categorie_lignecommandes;
	}

	@Override
	public String toString() {
		return "LignesCommande [id_ligne_commande=" + id_ligne_commande + ", code_barre_ligne_commande="
				+ code_barre_ligne_commande + ", designation_ligne_commande=" + designation_ligne_commande
				+ ", prix_unitaire_ligne_commande=" + prix_unitaire_ligne_commande + ", poids_quantite_ligne_commande="
				+ poids_quantite_ligne_commande + ", TVA_ligne_commande=" + TVA_ligne_commande + ", fk_id_commande="
				+ fk_id_commande + ", fk_id_categorie_lignecommandes=" + fk_id_categorie_lignecommandes + "]";
	}
	
	
}
