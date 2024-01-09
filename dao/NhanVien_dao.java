package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.NhanVien;

public class NhanVien_dao {
	List<NhanVien> dsnv;

	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		/*
		 * try { String sql = "Select * from NhanVien"; Statement statement =
		 * con.createStatement(); // Thực thi câu lệnh SQL trả vể đối tượng ResultSet
		 * ResultSet rs = statement.executeQuery(sql); while (rs.next()) { dsnv.add(new
		 * NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"),
		 * rs.getString("ngaySinh"), rs.getString("SDT"), rs.getString("email"),
		 * rs.getString("trinhDo"), rs.getString("chucVu"),
		 * rs.getDouble("heSoLuong"),rs.getDouble("luongCoBan"))); } } catch
		 * (SQLException e) { e.printStackTrace(); } return dsnv;
		 */
		try {

			String sql = "Select * from NhanVien";
			// System.out.println(sql);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoTenNV = rs.getString(2);
				String ngaySinh = rs.getString(3);
				String SDT = rs.getString(4);
				String email = rs.getString(5);
				String trinhDo = rs.getString(6);
				String chucVu = rs.getString(7);
				double heSoLuong = rs.getDouble(8);
				double luongCoBan = rs.getDouble(9);
				NhanVien nv = new NhanVien(maNV, hoTenNV, ngaySinh, SDT, email, trinhDo, chucVu, heSoLuong, luongCoBan,null);
				dsnv.add(nv);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dsnv;
	}

	public boolean addNhanVien(NhanVien nv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getHoTen());
			stmt.setString(3, nv.getNgaySinh());
			stmt.setString(4, nv.getsDT());
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getTrinhDo());
			stmt.setString(7, nv.getChucVu());
			stmt.setDouble(8, nv.getHeSoLuong());
			stmt.setDouble(9, nv.getLuongCoBan());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean updateNhanVien(NhanVien nv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "update NhanVien set  hoTenNV=?,ngaySinh=?, SDT=?, email=?, trinhDo=?, chucVu=?, heSoLuong=?, luongCoBan=? where maNV=?";
			stmt = con.prepareStatement(sql);
			// System.out.println(sql);
			stmt.setString(1, nv.getHoTen());
			stmt.setString(2, nv.getNgaySinh());
			stmt.setString(3, nv.getsDT());
			stmt.setString(4, nv.getEmail());
			stmt.setString(5, nv.getChucVu());
			stmt.setString(6, nv.getTrinhDo());
			stmt.setDouble(7, nv.getHeSoLuong());
			stmt.setDouble(8, nv.getLuongCoBan());
			stmt.setString(9, nv.getMaNV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return n > 0;
	}

	public List<NhanVien> findNhanVien(String nv) {
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from NhanVien where " + nv;
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsnv.add(new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"), rs.getString("ngaySinh"),
						rs.getString("SDT"), rs.getString("email"), rs.getString("trinhDo"), rs.getString("chucVu"),
						rs.getDouble("heSoLuong"), rs.getDouble("luongCoBan"), null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	private void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
