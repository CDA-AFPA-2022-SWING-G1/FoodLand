package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import model.Categorie_produit;
import model.Lot;
import outils.ConnectionDB;

public class DaoLot implements Dao<Lot> {

	
	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoLot() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Lot t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Lot read(Lot t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Lot t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Lot t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Lot> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
