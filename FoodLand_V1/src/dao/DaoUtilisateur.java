package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import model.Categorie_produit;
import model.Utilisateur;
import outils.ConnectionDB;

public class DaoUtilisateur implements Dao<Utilisateur> {

	
	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoUtilisateur() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Utilisateur t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Utilisateur read(Utilisateur t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Utilisateur t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Utilisateur t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Utilisateur> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Vector<String> listeColumns() {
		// TODO Auto-generated method stub
		return null;
	}
}
