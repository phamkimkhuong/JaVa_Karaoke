package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.LoaiDichVu;

public class LoaiDichVu_dao {
	List<LoaiDichVu> dsldv;
	public List<LoaiDichVu> getAllLoaiDichVu() {
		dsldv = new ArrayList<LoaiDichVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {

			String sql = "Select * from LoaiDichVu";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLoaiDV = rs.getString(1);
				String tenLoaiDV = rs.getString(2);
				LoaiDichVu ldv = new LoaiDichVu(maLoaiDV, tenLoaiDV);
				dsldv.add(ldv);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dsldv;
	}
	public boolean addLoaiDichVu(LoaiDichVu ldv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LoaiDichVu values(?,?)");
			stmt.setString(1, ldv.getMaLoai());
			stmt.setString(2, ldv.getTenLoai());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean updateLoaiDichVu(LoaiDichVu ldv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "update LoaiDichVu set  tenLoai=? where maLoai=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ldv.getTenLoai());
			stmt.setString(2, ldv.getMaLoai());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public void deleteLoaiDichVu(String maLoaiDV) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from LoaiDichVu where maLoai=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLoaiDV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<LoaiDichVu> findLoaiDichVu(String ldv) {
		List<LoaiDichVu> dsldv = new ArrayList<LoaiDichVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from LoaiDichVu where " + ldv;
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsldv.add(new LoaiDichVu(rs.getString("maLoai"), rs.getString("tenLoai")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsldv;
	}
	
}
