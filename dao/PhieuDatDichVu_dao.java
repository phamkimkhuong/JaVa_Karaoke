package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import connectDB.connectSQL;
import entity.KhachHang;
import entity.PhieuDatDichVu;
import entity.Phong;

public class PhieuDatDichVu_dao {
	List<PhieuDatDichVu> dsPDDV;

	public List<PhieuDatDichVu> getAllPhieuDatDichVu() {
		dsPDDV = new ArrayList<PhieuDatDichVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "select * from PhieuDatDichVu pddv join ChiTietPhieuDichVu ct on pddv.maPhieu=ct.maPhieu join \r\n"
					+ "					Phong p on p.maPhong = pddv.maPhong join KhachHang kh on kh.maKH=pddv.maKH \r\n";
//			System.out.println(sql);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"));
				KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString("SDT"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				dsPDDV.add(new PhieuDatDichVu(rs.getString("maPhieu"), p, kh, ngayLap, rs.getString(5)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dsPDDV;
	}

	/*
	 * public PhieuDatDichVu getPhieuDichVu() { try { PhieuDatDichVu pdv = new
	 * PhieuDatDichVu(null); connectSQL.getInstance(); Connection con =
	 * connectSQL.getConnection(); String sql = "select * from PhieuDatDichVu";
	 * Statement statement = con.createStatement(); // Thực thi câu lệnh SQL trả vể
	 * đối tượng ResultSet ResultSet rs = statement.executeQuery(sql); while
	 * (rs.next()) { DichVu dv = new DichVu(rs.getString("maDV")); LocalDate ngayLap
	 * = rs.getDate("ngayLap").toLocalDate(); Phong p = new
	 * Phong(rs.getString("maPhong"),rs.getString("tenPhong")); KhachHang kh = new
	 * KhachHang(rs.getString("maKH"),rs.getString("hoTen"),rs.getString("SDT"));
	 * PhieuDatDichVu pddv = new PhieuDatDichVu(rs.getString("maPhieu")); pdv = new
	 * PhieuDatDichVu(rs.getString("maPhieu"), p, kh, ngayLap); } return pdv;
	 * }catch(Exception e) { return null; } }
	 */
	public boolean addPhieuDatDichVu(PhieuDatDichVu pddv) {
//		System.out.println(pddv);
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			Date sqlDate = Date.valueOf(pddv.getNgayLap());
			stmt = con.prepareStatement("insert into PhieuDatDichVu values(?,?,?,?,?)");
			stmt.setString(1, pddv.getMaPhieu());
			stmt.setString(2, pddv.getPhong().getMaPhong());
			stmt.setString(3, pddv.getKhachHang().getMaKH());
			stmt.setDate(4, sqlDate);
			stmt.setString(5, "Chưa thanh toán");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public void updateTrangThaiPhieu(String trangThai, String maPhieu) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "update PhieuDatDichVu set  tinhTrangPhieu=?  where maPhieu=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, trangThai);
			stmt.setString(2, maPhieu);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	public PhieuDatDichVu findPhieuDVTheoMa(String maPhieu) {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		PhieuDatDichVu PDV = new PhieuDatDichVu();
		try {
			String sql2 = "select * from PhieuDatDichVu pddv join ChiTietPhieuDichVu ct on pddv.maPhieu=ct.maPhieu join\r\n"
					+ "Phong p on p.maPhong = pddv.maPhong join KhachHang kh on kh.maKH=pddv.maKH ";
			String sql3 = sql2 + " where pddv.maPhieu = '" + maPhieu + "'";
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"));
				KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString("SDT"));
//				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				PDV = new PhieuDatDichVu(rs.getString("maPhieu"), p, kh, ngayLap, rs.getString("tinhTrangPhieu"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PDV;
	}
	public List<PhieuDatDichVu> findPhieuDichVu(String pddv) {
		List<PhieuDatDichVu> dspdv = new ArrayList<PhieuDatDichVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql1 = "SELECT * FROM PhieuDatDichVu pddv " +
		              "JOIN ChiTietPhieuDichVu ct ON pddv.maPhieu = ct.maPhieu " +
		              "JOIN Phong p ON p.maPhong = pddv.maPhong " +
		              "JOIN KhachHang kh ON kh.maKH = pddv.maKH ";
			String sql2 = sql1 + " where " + pddv;
			//System.out.println(sql2);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql2);
			while (rs.next()) {
				Phong p = new Phong(rs.getString("maPhong"),rs.getString("tenPhong"));
				KhachHang kh = new KhachHang(rs.getString("maKH"),rs.getString("hoTen"),rs.getString("SDT"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				dspdv.add(new PhieuDatDichVu(rs.getString("maPhieu"), p, kh, ngayLap,rs.getString("tinhTrangPhieu")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dspdv;
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

