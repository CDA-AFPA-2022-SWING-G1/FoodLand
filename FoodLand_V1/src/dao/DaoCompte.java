package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import model.Categorie_produit;
import model.Compte;
import outils.ConnectionDB;

public class DaoCompte implements Dao<Compte> {

	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoCompte() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Compte t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Compte read(Compte t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Compte t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Compte t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Compte> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
