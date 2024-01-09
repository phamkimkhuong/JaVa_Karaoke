package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.LoaiPhong;
import entity.Phong;

public class ChiTietHoaDon_dao {
	private List<ChiTietHoaDon> dsCTHD;

	public List<ChiTietHoaDon> getAllCTHD() {
		dsCTHD = new ArrayList<ChiTietHoaDon>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "select * from ChiTietHoaDon ct join HoaDon hd on hd.maHD=ct.maHD join Phong p on ct.maPhong=p.maPhong\r\n"
					+ " join LoaiPhong lp on lp.maLoai=p.maLP where ct.maDV is NULL";
//			System.out.println(sql);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"));
				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"),
						rs.getDouble("giaHat"), rs.getString("moTa"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"),loaiPhong);
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				LocalTime gioRa = rs.getTime("gioRa").toLocalTime();
				dsCTHD.add(new ChiTietHoaDon(hd, p, gioVao, gioRa));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public List<ChiTietHoaDon> getAllDVCTHD() {
		List<ChiTietHoaDon> dsCTHDDV = new ArrayList<ChiTietHoaDon>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "select * from ChiTietHoaDon ct join HoaDon hd on hd.maHD=ct.maHD join Phong p on ct.maPhong=p.maPhong \r\n"
					+ "join LoaiPhong lp on lp.maLoai=p.maLP join DichVu dv on dv.maDV=ct.maDV";
//			System.out.println(sql);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"));
				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"),
						rs.getDouble("giaHat"), rs.getString("moTa"));
				Phong p = new Phong(rs.getString("maPhong"),rs.getString("tenPhong"),loaiPhong);
				DichVu dv = new DichVu(rs.getString("maDV"), rs.getString("tenDV"), rs.getString("loaiDV"),
						rs.getString("donViTinh"), rs.getDouble("donGia"));
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				LocalTime gioRa = rs.getTime("gioRa").toLocalTime();
				dsCTHDDV.add(new ChiTietHoaDon(hd, p, dv, rs.getInt("soLuongBan"), gioVao, gioRa));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHDDV;
	}

	public void addCTHD(ChiTietHoaDon ct) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			Time sqlGioVao = Time.valueOf(ct.getGioVao());
			Time sqlGioRa = Time.valueOf(ct.getGioRa());
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, ct.getHoaDon().getMaHD());
			stmt.setString(2, ct.getPhong().getMaPhong());
			if (ct.getDv().getMaDV() == null)
				stmt.setString(3, null);
			else
				stmt.setString(3, ct.getDv().getMaDV());
			stmt.setInt(4, ct.getSoLuongBan());
			stmt.setDouble(5, ct.getDv().getDonGia());
			stmt.setTime(6, sqlGioVao);
			stmt.setTime(7, sqlGioRa);
			stmt.setDouble(8, ct.getPhong().getLoaiPhong().getGiaHat());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}
	public void addCTHDnoDV(ChiTietHoaDon ct) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			Time sqlGioVao = Time.valueOf(ct.getGioVao());
			Time sqlGioRa = Time.valueOf(ct.getGioRa());
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, ct.getHoaDon().getMaHD());
			stmt.setString(2, ct.getPhong().getMaPhong());
			stmt.setString(3,null);
			stmt.setInt(4, 0);
			stmt.setDouble(5, 0);
			stmt.setTime(6, sqlGioVao);
			stmt.setTime(7, sqlGioRa);
			stmt.setDouble(8, ct.getPhong().getLoaiPhong().getGiaHat());
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
				// TODO: handle exception
				e.printStackTrace();
			}
	}

	public List<ChiTietHoaDon> timCTHoaDonTheoMa(String maHD) {
		List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		ChiTietHoaDon ct = new ChiTietHoaDon();
		try {
			String sql = "select * from ChiTietHoaDon ct join HoaDon hd on hd.maHD=ct.maHD join Phong p on ct.maPhong=p.maPhong\r\n"
					+ "join LoaiPhong lp on lp.maLoai=p.maLP join DichVu dv on dv.maDV=ct.maDV";
			String sql3 = sql + " where ct.maHD = '" + maHD + "'";
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"));
				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"),
						rs.getDouble("giaHat"), rs.getString("moTa"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"),loaiPhong);
				DichVu dv = new DichVu(rs.getString("maDV"), rs.getString("tenDV"), rs.getString("loaiDV"),
						rs.getString("donViTinh"), rs.getDouble("donGia"));
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				LocalTime gioRa = rs.getTime("gioRa").toLocalTime();
				dsCTHD.add(new ChiTietHoaDon(hd, p, dv, rs.getInt("soLuongBan"), gioVao, gioRa));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}
	public ChiTietHoaDon timAllCTHoaDonTheoMa(String maHD) {
		ChiTietHoaDon CTHD = new ChiTietHoaDon();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		ChiTietHoaDon ct = new ChiTietHoaDon();
		try {
			String sql = "select * from ChiTietHoaDon ct ";
			String sql3 = sql + " where ct.maHD = '" + maHD + "'";
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"));
				Phong p = new Phong(rs.getString("maPhong"));
				DichVu dv = new DichVu(rs.getString("maDV"));
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				LocalTime gioRa = rs.getTime("gioRa").toLocalTime();
				CTHD = new ChiTietHoaDon(hd, p, dv, rs.getInt("soLuongBan"), gioVao, gioRa);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CTHD;
	}
	
	public ChiTietHoaDon timCTHDPTheoMa(String maHD) {
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		ChiTietHoaDon ct = new ChiTietHoaDon();
		try {
			String sql = "select * from ChiTietHoaDon ct join HoaDon hd on hd.maHD=ct.maHD join Phong p on ct.maPhong=p.maPhong\r\n"
					+ "join LoaiPhong lp on lp.maLoai=p.maLP";
			String sql3 = sql + " where ct.maHD = '" + maHD + "'";
//			System.out.println(sql3);
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql3);
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString("maHD"));
				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"),
						rs.getDouble("giaHat"), rs.getString("moTa"));
				Phong p = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"),loaiPhong);
				LocalTime gioVao = rs.getTime("gioVao").toLocalTime();
				LocalTime gioRa = rs.getTime("gioRa").toLocalTime();
				ct = new ChiTietHoaDon(hd, p, gioVao, gioRa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}

}
