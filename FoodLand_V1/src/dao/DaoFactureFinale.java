package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import model.Categorie_produit;
import model.FactureFinale;
import outils.ConnectionDB;

public class DaoFactureFinale implements Dao<FactureFinale> {

	
	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoFactureFinale() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(FactureFinale t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FactureFinale read(FactureFinale t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(FactureFinale t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(FactureFinale t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FactureFinale> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
