package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		// TODO Auto-generated method stub
		return 0;
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
