package dao;

import java.sql.PreparedStatement;
import java.util.List;

public interface Interface_dao<T> {
	public List<T> selectALL();
	
	public void add(T t);
	
	public void delete(String condition);
	
	public void update(T t);
	
	public T findByID(String id);
	
	public List<T> findByCondition(String condition);
	
	public void close(PreparedStatement stmt);
}
