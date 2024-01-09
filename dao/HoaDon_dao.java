package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_dao {
	private List<HoaDon> dsHD;
	private String sql;

	public List<HoaDon> getAllHoaDon() {
		dsHD = new ArrayList<HoaDon>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			sql = "select * from HoaDon hd join KhachHang kh on kh.maKH=hd.maKH join NhanVien nv "
					+ "on nv.maNV=hd.maNV";
//			System.out.println(sql);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString(8));
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				dsHD.add(new HoaDon(rs.getString("maHD"), kh, nv, ngayLap));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHD;
	}

	public void addHD(HoaDon hd) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			Date sqlDate = Date.valueOf(hd.getNgayLap());
			stmt = con.prepareStatement("insert into HoaDon values(?,?,?,?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setString(2, hd.getKhachHang().getMaKH());
			stmt.setString(3, hd.getNhanVien().getMaNV());
			stmt.setDate(4, sqlDate);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	private void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public HoaDon timHoaDonTheoMa(String maHD) {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		HoaDon hd = new HoaDon();
		try {
			String sql2 = "select * from HoaDon hd join KhachHang kh on kh.maKH=hd.maKH join NhanVien nv on nv.maNV=hd.maNV";
			String sql3 = sql2 + " where hd.maHD = '" + maHD + "'";
//				System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString(8));
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				hd = new HoaDon(rs.getString("maHD"), kh, nv, ngayLap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hd;
	}

	public List<HoaDon> timKiemHoaDon(String hoaDon) {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		List<HoaDon> listHD = new ArrayList<HoaDon>();
		try {
			String sql2 = "select * from HoaDon hd join KhachHang kh on kh.maKH=hd.maKH join NhanVien nv on nv.maNV=hd.maNV\r\n"
					+ "join ChiTietHoaDon ct on ct.maHD = hd.maHD join Phong p on p.maPhong=ct.maPhong";
			String sql3 = sql2 + " where " + hoaDon;
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("hoTen"), rs.getString(8));
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("hoTenNV"));
				LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
				HoaDon hd = new HoaDon(rs.getString("maHD"), kh, nv, ngayLap);
				int n = 0;
				for (HoaDon hoaDon2 : listHD) {
					if (hoaDon2.getMaHD().equals(hd.getMaHD()))
						n = 1;
				}
				if (n == 0)
					listHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listHD;
	}

}
