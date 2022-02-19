package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import model.Categorie_produit;
import model.Promotion;
import outils.ConnectionDB;

public class DaoPromotion implements Dao<Promotion>{

	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoPromotion() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Promotion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Promotion read(Promotion t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Promotion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promotion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Promotion> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
