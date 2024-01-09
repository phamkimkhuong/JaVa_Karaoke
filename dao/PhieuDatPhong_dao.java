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
import entity.Phong;

public class PhieuDatPhong_dao {
	private String sql;
	private List<PhieuDatPhong> dsPDP;
	private ArrayList<PhieuDatPhong> dsPHDP;
	private String sql2;

	public List<PhieuDatPhong> getAllPhieuDatPhong() {
		dsPDP = new ArrayList<PhieuDatPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			sql = "select * from KhachHang kh join PhieuDatPhong pdp  on kh.maKH =pdp.maKH  join "
					+ "NhanVien nv on nv.maNV=pdp.maNV where pdp.maPhieu like '%PDP%'";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
//				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
//				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString("SDT"));
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
//				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
//				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				dsPDP.add(new PhieuDatPhong(rs.getString("maPhieu"), ngayLap, kh, nv, rs.getString("trangThaiPhieu")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPDP;
	}

	public List<PhieuDatPhong> getPhieuHenDatPhong() {
		dsPHDP = new ArrayList<PhieuDatPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			sql2 = "select * from KhachHang kh join PhieuDatPhong pdp  on kh.maKH =pdp.maKH join "
					+ "NhanVien nv on nv.maNV=pdp.maNV where pdp.maPhieu like '%PHDP%'";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql2);
			while (rs.next()) {
//				LoaiPhong lp = new LoaiPhong(rs.getString("tenLoai"), rs.getDouble("giaHat"));
//				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), lp, rs.getString("trangThai"));
				KhachHang kh = new KhachHang(rs.getString("hoTen"), rs.getString("SDT"));
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
//				LocalDate ngayNhan = rs.getDate("ngayNhanPhong").toLocalDate();
//				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				dsPHDP.add(new PhieuDatPhong(rs.getString("maPhieu"), ngayLap, kh, nv, rs.getString("trangThaiPhieu")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPHDP;
	}

	/*
	 * Chức năng thêm mới một phiếu đặt phòng
	 */
	public void addPDPhong(PhieuDatPhong pdp) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			Date sqlDate = Date.valueOf(pdp.getNgayLap());
			stmt = con.prepareStatement("insert into PhieuDatPhong values(?,?,?,?,?)");
			stmt.setString(1, pdp.getMaPhieu());
			stmt.setDate(2, sqlDate);
			stmt.setString(3, pdp.getKhachHang().getMaKH());
			stmt.setString(4, pdp.getNhanVien().getMaNV());
			stmt.setString(5, pdp.getTrangThai());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	/*
	 * Cập Nhật thông tin Một PDP phòng theo mã phiếu đặt phòng
	 */
	public void updateTrangThaiPDPhong(String trangThai, String maPhieu) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "update PhieuDatPhong set  trangThaiPhieu=?  where maPhieu=?";
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

	/*
	 * Tìm Kiếm Các Phiếu Đặt Phòng theo yêu cầu, trả về danh sách phiếu đặt phòng
	 */
	public List<PhieuDatPhong> findPhieuDatPhong(String pdp) {
		List<PhieuDatPhong> dsfindPHDP = new ArrayList<PhieuDatPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql2 = "select * from KhachHang kh join PhieuDatPhong pdp  on kh.maKH =pdp.maKH  join\r\n"
					+ "				NhanVien nv on nv.maNV=pdp.maNV join ChiTietPhieuDatPhong ct on ct.maPhieu=pdp.maPhieu ";
			String sql3 = sql2 + " where " + pdp;
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString("hoTen"), rs.getString("SDT"));
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				PhieuDatPhong phdp = new PhieuDatPhong(rs.getString("maPhieu"), ngayLap, kh, nv,
						rs.getString("trangThaiPhieu"));
				if (phdp.getMaPhieu().startsWith("PHDP"))
					dsfindPHDP.add(phdp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsfindPHDP;
	}

	public PhieuDatPhong findPhieuDatPhongTheoMaPhieu(String pdp) {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		PhieuDatPhong PDP = new PhieuDatPhong();
		try {
			String sql2 = "select * from KhachHang kh join PhieuDatPhong pdp  on kh.maKH =pdp.maKH join\r\n"
					+ "				NhanVien nv on nv.maNV=pdp.maNV ";
			String sql3 = sql2 + " where pdp.maPhieu = '" + pdp + "'";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString("SDT"));
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				PDP = new PhieuDatPhong(rs.getString("maPhieu"), ngayLap, kh, nv, rs.getString("trangThaiPhieu"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PDP;
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
