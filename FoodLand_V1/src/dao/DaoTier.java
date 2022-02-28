package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Categorie_produit;
import model.Entreprise;
import outils.ConnectionDB;

public class DaoTier implements Dao<Entreprise>{

	
	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoTier() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Entreprise t) {
		int res = 0;
		
		String insert = "INSERT INTO entreprise (siret_entreprise, nom_entreprise, adresse_entreprise, code_postal_entreprise, ville_entreprise, pays_entreprise, tel_entreprise, mail_entreprise, logo_entreprise) VALUES ("
				+ "'" + t.getSiret_entreprise() + "', "
				+ "'" + t.getNom_entreprise() + "', "
				+ "'" + t.getAdresse_entreprise() + "', "
				+ "'" + t.getCode_postale_entreprise()+ "', "
				+ "'" + t.getVille_entreprise() + "', "
				+ "'" + t.getPays_entreprise() + "', "
				+ "'" + t.getTelephone_entreprise()+ "', "
				+ t.getMail_entreprise() + ","
				+ "'" + t.getLogo_entreprise() + "' "
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
	public Entreprise read(Entreprise t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Entreprise t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Entreprise t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Entreprise> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
