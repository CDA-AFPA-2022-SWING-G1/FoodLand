package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import model.Categorie_produit;
import outils.ConnectionDB;

public class DaoCategorie implements Dao<Categorie_produit> {

	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoCategorie() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Categorie_produit cat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie_produit read(Categorie_produit t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Categorie_produit t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Categorie_produit t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Categorie_produit> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
