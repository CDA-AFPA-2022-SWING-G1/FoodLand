package model;

public class Compte_model {
	
	private int id_compte;
	private String login_compte;
	private String password_compte;
	private int fk_id_user;
	
	public int getId_compte() {
		return id_compte;
	}
	public void setId_compte(int id_compte) {
		this.id_compte = id_compte;
	}
	public String getLogin_compte() {
		return login_compte;
	}
	public void setLogin_compte(String login_compte) {
		this.login_compte = login_compte;
	}
	public String getPassword_compte() {
		return password_compte;
	}
	public void setPassword_compte(String password_compte) {
		this.password_compte = password_compte;
	}
	public int getFk_id_user() {
		return fk_id_user;
	}
	public void setFk_id_user(int fk_id_user) {
		this.fk_id_user = fk_id_user;
	}
	
	

}
