package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.ChucVu;

public class ChucVu_dao {
	List<ChucVu> dscv;
	public List<ChucVu> getAllChucVu() {
		dscv = new ArrayList<ChucVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {

			String sql = "Select * from ChucVu";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maCV = rs.getString(1);
				String tenCV = rs.getString(2);
				ChucVu ldv = new ChucVu(maCV, tenCV);
				dscv.add(ldv);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dscv;
	}
	public boolean addChucVu(ChucVu cv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ChucVu values(?,?)");
			stmt.setString(1, cv.getMaCV());
			stmt.setString(2, cv.getTenCV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean updateChucVu(ChucVu ldv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "update ChucVu set  tenChucVu=? where maChucVu=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ldv.getTenCV());
			stmt.setString(2, ldv.getMaCV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public void deleteChucVu(String maCV) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from ChucVu where maChucVu=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<ChucVu> findChucVu(String ldv) {
		List<ChucVu> dsldv = new ArrayList<ChucVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from ChucVu where " + ldv;
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsldv.add(new ChucVu(rs.getString("maChucVu"), rs.getString("tenChucVu")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsldv;
	}
	public ChucVu findChucVuTheoMa(String ma) {
		ChucVu cv = new ChucVu();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from ChucVu where maChucVu = '" + ma+"'";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				cv = new ChucVu(rs.getString("maChucVu"), rs.getString("tenChucVu"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cv;
	}
	
}
