package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import model.Categorie_produit;
import model.Compte;
import model.Utilisateur;
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
		int res = 0;
		String insert = "INSERT INTO compte (login_compte, password_compte) VALUES ("
				+ "'" + t.getLogin_compte() + "', "
				+ "'" + t.getPassword_compte() + "'"
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

	/**
	 * read compte by its  id
	 */
	@Override
	public Compte read(Compte t) {
		
		try {
			stmt = c.createStatement();
			String read = "SELECT * FROM compte WHERE id_compte =" + t.getId_compte();
			rs = stmt.executeQuery(read);
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id_user"));
//				System.out.println(rs.getString("nom_user"));

				t.setId_compte(((rs.getInt("id_compte"))));
				t.setLogin_compte((rs.getString("login_compte")));
				t.setPassword_compte(rs.getString("password_compte"));
				t.setId_utilisateur(rs.getInt("fk_id_user"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return t;
	}


	/**
	 * update compte by its id
	 */
	@Override
	public int update(Compte t) {
		int r = 0;

		try {
			System.out.println(c.toString());
			stmt = c.createStatement();
			
			String update = "UPDATE compte SET " 
					+ "id_compte= '" + t.getId_compte() + "', "
					+ "login_compte= '" + t.getLogin_compte() + "', "
					+ "password_compte= '" + t.getPassword_compte() + "', "
					+ "fk_id_user= '" + t.getId_utilisateur()+ "' "
					
					+ "WHERE id_compte = " + t.getId_compte() + "";
			
			r = stmt.executeUpdate(update);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return r;
	}

	/**
	 * delete compte by its id
	 */
	@Override
	public int delete(Compte t) {
		int r = 0;
		try {
			stmt = c.createStatement();
			String delete = "DELETE FROM compte WHERE id_compte = "  
							+ "'" + t.getId_compte() + "'" 
								+ " AND login_compte = " + "'" + t.getLogin_compte() + "'" + ";";
			System.out.println(delete);
			r = stmt.executeUpdate(delete);
			System.out.println(r);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return r;
	}

	@Override
	public List<Compte> readAll() {
		Vector<Compte> list = new Vector<>();
		try {
			stmt = c.createStatement();
			String select = "SELECT * FROM compte;";
			rs = stmt.executeQuery(select);
			
			while(rs.next()) {
				Compte t = new Compte();
				t.setId_compte(((rs.getInt("id_compte"))));
				t.setLogin_compte(((rs.getString("login_compte"))));
				t.setPassword_compte(((rs.getString("password_compte"))));
				t.setId_utilisateur((rs.getInt("fk_id_user")));

				//System.out.println(compte.toString());
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
