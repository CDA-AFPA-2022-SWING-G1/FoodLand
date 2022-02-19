package dao;

import java.util.List;

import outils.ConnectionDB;

public interface Dao<T> {
	
	//public static final ConnectionDB conn = new ConnectionDB();
	
	public int create(T t);
	public T read(T t);
	public int update(T t);
	public int delete(T t);
	public List<T> readAll();

}
