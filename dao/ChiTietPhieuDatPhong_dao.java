package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.ChiTietPhieuDatPhong;
import entity.Phong;

public class ChiTietPhieuDatPhong_dao {
	private String sql;
	private List<ChiTietPhieuDatPhong> dsCTPDP;
	private ArrayList<ChiTietPhieuDatPhong> dsCTPHDP;
	private String sql2;
	private ArrayList<ChiTietPhieuDatPhong> dsPDP;

	public List<ChiTietPhieuDatPhong> getAllPDatPhong() {
		dsPDP = new ArrayList<ChiTietPhieuDatPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			sql = "select * from ChiTietPhieuDatPhong ct join PhieuDatPhong pdp on ct.maPhieu=pdp.maPhieu "
					+ "join Phong p on p.maPhong = ct.maPhong join LoaiPhong lp on lp.maLoai=p.maLP ";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString("maPhieu"));
				dsPDP.add(new ChiTietPhieuDatPhong(pdp, p, ngayNhan, gioVao));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPDP;
	}
	
	
	public List<ChiTietPhieuDatPhong> getCTPhieuDatPhong() {
		dsCTPDP = new ArrayList<ChiTietPhieuDatPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			sql = "select * from ChiTietPhieuDatPhong ct join PhieuDatPhong pdp on ct.maPhieu=pdp.maPhieu "
					+ "join Phong p on p.maPhong = ct.maPhong join LoaiPhong lp on lp.maLoai=p.maLP where ct.maPhieu like '%PDP%'";

			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString("maPhieu"));
				dsCTPDP.add(new ChiTietPhieuDatPhong(pdp, p, ngayNhan, gioVao));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTPDP;
	}

	public List<ChiTietPhieuDatPhong> getCTPhieuHenDatPhong() {
		dsCTPHDP = new ArrayList<ChiTietPhieuDatPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			sql2 = "select * from ChiTietPhieuDatPhong ct join PhieuDatPhong pdp on ct.maPhieu=pdp.maPhieu "
					+ "join Phong p on p.maPhong = ct.maPhong join LoaiPhong lp on lp.maLoai=p.maLP where ct.maPhieu like '%PHDP%'";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql2);
			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString("maPhieu"));
				dsCTPDP.add(new ChiTietPhieuDatPhong(pdp, p, ngayNhan, gioVao));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTPHDP;
	}

	/*
	 * Chức năng thêm mới một chi tiet phiếu đặt phòng
	 */
	public void addCTPDPhong(ChiTietPhieuDatPhong ct) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			Date sqlDate2 = Date.valueOf(ct.getNgayNhan());
			Time sqlTime = Time.valueOf(ct.getGioVao());
			stmt = con.prepareStatement("insert into ChiTietPhieuDatPhong values(?,?,?,?)");
			stmt.setString(1, ct.getPhieuDatPhong().getMaPhieu());
			stmt.setString(2, ct.getPhong().getMaPhong());
			stmt.setDate(3, sqlDate2);
			stmt.setTime(4, sqlTime);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	/*
	 * Tìm Kiếm Các Phiếu Đặt Phòng theo yêu cầu, trả về danh sách phiếu đặt phòng
	 */
	public List<ChiTietPhieuDatPhong> findPhieuDatPhong(String pdp) {
		List<ChiTietPhieuDatPhong> dsfindPHDP = new ArrayList<ChiTietPhieuDatPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql2 = "select * from ChiTietPhieuDatPhong ct "
					+ " join Phong p on p.maPhong=ct.maPhong join LoaiPhong lp on lp.maLoai = p.maLP ";
			String sql3 = sql2 + " where " + pdp;
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				PhieuDatPhong phdp = new PhieuDatPhong(rs.getString("maPhieu"));
				ChiTietPhieuDatPhong ct = new ChiTietPhieuDatPhong(phdp, p, ngayNhan, gioVao);
				if (ct.getPhieuDatPhong().getMaPhieu().startsWith("PHDP"))
					dsfindPHDP.add(ct);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsfindPHDP;
	}

	public ChiTietPhieuDatPhong findPhieuDatPhongTheoMaPhieu(String pdp) {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		ChiTietPhieuDatPhong ct = new ChiTietPhieuDatPhong();
		try {
			String sql2 = "select * from ChiTietPhieuDatPhong ct "
					+ " join Phong p on p.maPhong=ct.maPhong join LoaiPhong lp on lp.maLoai = p.maLP  ";
			String sql3 = sql2 + " where ct.maPhieu = '" + pdp + "'";
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				PhieuDatPhong PDP = new PhieuDatPhong(rs.getString("maPhieu"));
				ct = new ChiTietPhieuDatPhong(PDP, p, ngayNhan, gioVao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}

	public ChiTietPhieuDatPhong findCTPhieuDatPhongTheoMaPhong(String phong) {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		ChiTietPhieuDatPhong ct = new ChiTietPhieuDatPhong();
		try {
			String sql2 = "select * from ChiTietPhieuDatPhong ct "
					+ " join Phong p on p.maPhong=ct.maPhong join LoaiPhong lp on lp.maLoai = p.maLP ";
			String sql3 = sql2 + " where p.maPhong = '" + phong + "'";
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString("maPhieu"));
				ct = new ChiTietPhieuDatPhong(pdp, p, ngayNhan, gioVao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
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
