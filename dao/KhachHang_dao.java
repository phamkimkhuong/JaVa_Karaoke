package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.connectSQL;
import entity.KhachHang;

public class KhachHang_dao {
	ArrayList<KhachHang> dskh;

	public KhachHang_dao() {
		dskh = new ArrayList<KhachHang>();
	}

	public ArrayList<KhachHang> doctuBang() {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {

			String sql = "Select * from KhachHang";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
				String ngaySinh = rs.getString(3);
				String sdt = rs.getString(4);
				String email = rs.getString(5);
				KhachHang kh = new KhachHang(maKH,hoTen,ngaySinh,sdt,email);
				dskh.add(kh);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dskh;
	}

	public boolean createKhachHang(KhachHang kh) {
		Connection con = connectSQL.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into KhachHang values(?,?,?,?,?)");
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTen());
			stmt.setString(3, kh.getNgaySinh());
			stmt.setString(4, kh.getSDT());
			stmt.setString(5, kh.getEmail());
			n = stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}

	public boolean updateKhachHang(KhachHang kh) {
		Connection con = connectSQL.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql =  "update KhachHang" + " set hoTen = ?" + ", ngaySinh=?" + ", SDT=?" + ", email=?" + " where maKH = ?";
			stmt = con.prepareStatement(
					sql);
			//System.out.println(sql);
			stmt.setString(1, kh.getHoTen());
			stmt.setString(2, kh.getNgaySinh());
			stmt.setString(3, kh.getSDT());
			stmt.setString(4, kh.getEmail());
			stmt.setString(5, kh.getMaKH());
			n = stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(stmt);
		}
		return n > 0;
	}

	public ArrayList<KhachHang> findKhachHang(String kh) {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from KhachHang where " + kh;
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dskh.add(new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString("ngaySinh"),
						rs.getString("SDT"), rs.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	private void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
}
