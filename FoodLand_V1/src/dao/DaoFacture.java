package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import model.Categorie_produit;
import model.Facture;
import outils.ConnectionDB;

public class DaoFacture implements Dao<Facture> {

	
	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoFacture() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Facture t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Facture read(Facture t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Facture t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Facture t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Facture> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
