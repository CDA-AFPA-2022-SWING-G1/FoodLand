package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.LigneCommande;
import outils.ConnectionDB;

public class DaoLignesCommandes implements Dao<LigneCommande>{

	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	public DaoLignesCommandes() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}



	@Override
	public int create(LigneCommande t) {
		int res = 0;
		
		String insert = "INSERT INTO commande (code_barre_ligne_commande, designation_ligne_commande, "
				+ "prix_unitaire_ligne_commande, poids_quantite_ligne_commande, TVA_ligne_commande, "
				+ "fk_id_commande, fk_id_categorie_lignecommandes) VALUES "
				+ "("
				+ "'" + t.getCode_barre_ligne_commande()+ "', "
				+ "'" + t.getDesignation_ligne_commande()+ "', "
				+ "" + t.getPrix_unitaire_ligne_commande()+ ", "
				+ "" + t.getPoids_quantite_ligne_commande() + ", "
				+ "'" + t.getTVA_ligne_commande() + "', "
				+ "" + t.getFk_id_commande() + ", "
				+ "" + t.getFk_id_categorie_lignecommandes()
				+ ");";
		try {
			stmt = c.createStatement();
			res = stmt.executeUpdate(insert);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return res;
	}



	@Override
	public LigneCommande read(LigneCommande t) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int update(LigneCommande t) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int delete(LigneCommande t) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<LigneCommande> readAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
