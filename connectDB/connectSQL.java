package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectSQL {
	public static Connection con = null;
	private static connectSQL instance = new connectSQL();

	public static connectSQL getInstance() {
		return instance;
	}
	public void connect()  throws SQLException{
		String url = "jdbc:sqlserver://localhost:1433;databasename=karaoke;encrypt=false";
		String user = "sa";
		String password = "123";
		con = DriverManager.getConnection(url,user,password);
	}
	public void disconnect() {
		if(con!=null) 
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static Connection getConnection(){
		return con;
	}
//        public static void main(String[] args) {
//        try {
//			connectSQL.getInstance().connect();
//                        System.out.println("Kết nối với SQL sever thành công");
//
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//    }
}
