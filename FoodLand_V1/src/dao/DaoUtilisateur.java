package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Categorie_produit;
import model.Emp;
import model.Utilisateur;
import outils.ConnectionDB;

public class DaoUtilisateur implements Dao<Utilisateur> {

	
	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String joinRoleAndCompte = 
			"SELECT * FROM `utilisateur` LEFT JOIN roles ON utilisateur.fk_id_role = roles.id_role where utilisateur.id_user = 8;";
	
	
	//SELECT * FROM `utilisateur` LEFT JOIN roles ON utilisateur.fk_id_role = roles.id_role LEFT JOIN compte on utilisateur.fk_compte_user = compte.id_compte where utilisateur.id_user = 8;
	
	public DaoUtilisateur() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	@Override
	public int create(Utilisateur t) {
		int res = 0;
		
		String insert = "INSERT INTO utilisateur (nom_user, prenom_user, adresse_user, code_postal_user, ville_user, tel_user, mail_user, fk_id_role, photo_user, fk_compte_user) VALUES ("
				+ "'" + t.getNom_utilisateur() + "', "
				+ "'" + t.getPrenom_utilisateur() + "', "
				+ "'" + t.getAdresse_utilisateur() + "', "
				+ "'" + t.getCode_postale_utilisateur() + "', "
				+ "'" + t.getVille_utilisateur() + "', "
				+ "'" + t.getTel_utilisateur() + "', "
				+ "'" + t.getMail_utilisateur() + "', "
				+ t.getFk_id_role() + ","
				+ "'" + t.getPhoto_utilisateur() + "', "
				+ t.getFk_id_compte() 
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
	public Utilisateur read(Utilisateur t) {
		
		try {
			stmt = c.createStatement();
			String read = "SELECT * FROM utilisateur WHERE id_user =" + t.getIdUtilisateur();
			rs = stmt.executeQuery(read);
			
			while(rs.next()) {
//				System.out.println(rs.getInt("id_user"));
//				System.out.println(rs.getString("nom_user"));

				
				t.setIdUtilisateur((rs.getInt("id_user")));
				t.setNom_utilisateur((rs.getString("nom_user")));
				t.setPrenom_utilisateur((rs.getString("prenom_user")));
				t.setAdresse_utilisateur((rs.getString("adresse_user")));
				t.setCode_postale_utilisateur(rs.getString("code_postal_user"));
				t.setVille_utilisateur(rs.getString("ville_user"));
				t.setTel_utilisateur(rs.getString("tel_user"));
				t.setMail_utilisateur(rs.getString("mail_user"));
				t.setFk_id_role(rs.getInt("fk_id_role"));
				t.setPhoto_utilisateur(rs.getBlob("photo_user"));
				t.setFk_id_compte(rs.getInt("fk_compte_user"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return t;
	}

	@Override
	public int update(Utilisateur t) {
		int r = 0;

		try {
			System.out.println(c.toString());
			stmt = c.createStatement();
			
			String update = "UPDATE utilisateur SET " 
					+ "id_user= '" + t.getIdUtilisateur() + "', "
					+ "nom_user= '" + t.getNom_utilisateur() + "', "
					+ "prenom_user= '" + t.getPrenom_utilisateur() + "', "
					+ "adresse_user= '" + t.getAdresse_utilisateur() + "', "
					+ "code_postal_user= '" + t.getCode_postale_utilisateur() + "', "
					+ "ville_user= '" + t.getVille_utilisateur() + "', "
					+ "tel_user= '" +t.getTel_utilisateur() + "', "
					+ "mail_user= '" + t.getMail_utilisateur() + "', "
					+ "fk_id_role_user= '" + t.getFk_id_role() + "'"
					+ "photo_user= '" + t.getPhoto_utilisateur() + "', "
					+ "fk_compte_user= '" + t.getFk_id_compte() + "', "
					
					+ "WHERE id_user = " + t.getIdUtilisateur() + "";
			
			String update2 = "UPDATE utilisateur SET " 
					+ "id_user= '" + t.getIdUtilisateur() + "', "
					+ "nom_user= '" + t.getNom_utilisateur() + "', "
					+ "photo_user= '" + t.getPhoto_utilisateur() + "'"
					+ "WHERE id_user = " + t.getIdUtilisateur() + "";
			
			r = stmt.executeUpdate(update);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return r;
	}

	@Override
	public int delete(Utilisateur t) {
		// TODO Auto-generated method stub
		int r = 0;
		try {
			stmt = c.createStatement();
			String delete = "DELETE FROM utilisateur WHERE id_user = "  
							+ "'" + t.getIdUtilisateur() + "'" 
								+ " AND nom_user = " + "'" + t.getNom_utilisateur() + "'" + ";";
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
	public List<Utilisateur> readAll() {
		Vector<Utilisateur> list = new Vector<>();
		try {
			stmt = c.createStatement();
			String select = "SELECT * FROM utilisateur;";
			rs = stmt.executeQuery(select);
			
			while(rs.next()) {
				Utilisateur t = new Utilisateur();
				t.setIdUtilisateur((rs.getInt("id_user")));
				t.setNom_utilisateur((rs.getString("nom_user")));
				t.setPrenom_utilisateur((rs.getString("prenom_user")));
				t.setAdresse_utilisateur((rs.getString("adresse_user")));
				t.setCode_postale_utilisateur(rs.getString("code_postal_user"));
				t.setVille_utilisateur(rs.getString("ville_user"));
				t.setTel_utilisateur(rs.getString("tel_user"));
				t.setMail_utilisateur(rs.getString("mail_user"));
				t.setFk_id_role(rs.getInt("fk_id_role"));
				t.setPhoto_utilisateur(rs.getBlob("photo_user"));
				t.setFk_id_compte(rs.getInt("fk_compte_user"));
				
				//System.out.println(utilisateur.toString());
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public  List<String> listeColumns() {
		ArrayList<String> listCols = new ArrayList<>();
		try {
			stmt = c.createStatement();
			String q = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'utilisateur';";
			
			rs = stmt.executeQuery(q);
			
			while(rs.next()) {
			String col = rs.getString("COLUMN_NAME");
			listCols.add(col);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return listCols;
	}
	
}
