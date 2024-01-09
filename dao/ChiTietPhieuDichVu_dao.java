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
import entity.ChiTietPhieuDichVu;
import entity.DichVu;
import entity.PhieuDatDichVu;

public class ChiTietPhieuDichVu_dao {
	private List<ChiTietPhieuDichVu> dsCTPDV;

	public List<ChiTietPhieuDichVu> getAllCTPDV() {
		dsCTPDV = new ArrayList<ChiTietPhieuDichVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "select * from ChiTietPhieuDichVu ct join DichVu dv on dv.maDV=ct.maDV join PhieuDatDichVu p on p.maPhieu=ct.maPhieu";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				DichVu dv = new DichVu(rs.getString("maDV"),rs.getString("tenDV"),rs.getString("loaiDV"),rs.getString("donViTinh"),rs.getDouble("donGia"));
				PhieuDatDichVu pddv = new PhieuDatDichVu(rs.getString("maPhieu"));
				dsCTPDV.add(new ChiTietPhieuDichVu(dv, rs.getInt(3), pddv));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTPDV;
	}

	/*
	 * public ChiTietPhieuDichVu getChiTietPhieuDichVu(String maPhieu) { try {
	 * ChiTietPhieuDichVu ct = null; connectSQL.getInstance(); Connection con =
	 * connectSQL.getConnection(); String sql = "select * from ChiTietPhieuDichVu";
	 * Statement statement = con.createStatement(); // Thực thi câu lệnh SQL trả vể
	 * đối tượng ResultSet ResultSet rs = statement.executeQuery(sql); while
	 * (rs.next()) { DichVu dv = new DichVu(rs.getString("maDV")); PhieuDatDichVu
	 * pddv = new PhieuDatDichVu(rs.getString("maPhieu")); ct = new
	 * ChiTietPhieuDichVu(rs.getString("maPhieu"), dv, rs.getInt("soLuong"), pddv);
	 * } return ct; }catch(Exception e) { return null; } }
	 */
	public void addCTPDV(ChiTietPhieuDichVu ct) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into ChiTietPhieuDichVu values(?,?,?,?)");
			stmt.setString(1, ct.getPhieuDichVu().getMaPhieu());
			stmt.setString(2, ct.getDichVu().getMaDV());
			stmt.setInt(3, ct.getSoLuongBan());
			stmt.setDouble(4, ct.getDichVu().getDonGia());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	 public List<ChiTietPhieuDichVu> layDanhSachChiTietDichVuTheoMaKhachHang(String maKH) {
		    List<ChiTietPhieuDichVu> dsCTPDVTheoMaKH = new ArrayList<>();
		    try {
		        connectSQL.getInstance();
		        Connection con = connectSQL.getConnection();
		        
		        String sql = "SELECT * FROM ChiTietPhieuDichVu ct " + 
		                     "JOIN DichVu dv ON dv.maDV = ct.maDV " + 
		                     "JOIN PhieuDatDichVu p ON p.maPhieu = ct.maPhieu " +
		                     "WHERE p.maKH = ?";
		        
		        PreparedStatement statement = con.prepareStatement(sql);
		        statement.setString(1, maKH);
		        
		        // Thực thi câu lệnh SQL và lấy kết quả trả về
		        ResultSet rs = statement.executeQuery();
		        
		        while (rs.next()) {
		            DichVu dv = new DichVu(rs.getString("maDV"), rs.getString("tenDV"), rs.getString("loaiDV"), rs.getString("donViTinh"), rs.getDouble("donGia"));
		            PhieuDatDichVu pddv = new PhieuDatDichVu(rs.getString("maPhieu"));
		            ChiTietPhieuDichVu chiTiet = new ChiTietPhieuDichVu(dv, rs.getInt("soLuong"), pddv);
		            dsCTPDVTheoMaKH.add(chiTiet);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return dsCTPDVTheoMaKH;
		}
	 public List<ChiTietPhieuDichVu> layDanhSachChiTietDichVuTheoMaPhieu(String maPhieu) { 
		  List<ChiTietPhieuDichVu> dsCTPDVTheoMaPhieu = new ArrayList<>();
		    try {
		        connectSQL.getInstance();
		        Connection con = connectSQL.getConnection();
		        
		        String sql = "SELECT * FROM ChiTietPhieuDichVu ct " + 
		                     "JOIN DichVu dv ON dv.maDV = ct.maDV " + 
		                     "JOIN PhieuDatDichVu p ON p.maPhieu = ct.maPhieu " +
		                     "WHERE ct.maPhieu = ?";
		        
		        PreparedStatement statement = con.prepareStatement(sql);
		        statement.setString(1, maPhieu);
		        
		        // Thực thi câu lệnh SQL và lấy kết quả trả về
		        ResultSet rs = statement.executeQuery();
		        
		        while (rs.next()) {
		            DichVu dv = new DichVu(rs.getString("maDV"), rs.getString("tenDV"), rs.getString("loaiDV"), rs.getString("donViTinh"), rs.getDouble("donGia"));
		            PhieuDatDichVu pddv = new PhieuDatDichVu(rs.getString("maPhieu"));
		            ChiTietPhieuDichVu chiTiet = new ChiTietPhieuDichVu(dv, rs.getInt("soLuong"), pddv);
		            dsCTPDVTheoMaPhieu.add(chiTiet);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return dsCTPDVTheoMaPhieu;
		  }
	  public List<ChiTietPhieuDichVu> layDanhSachChiTietDichVuTheoMaPhong(String maPhong,LocalDate ngayLap) {
		    List<ChiTietPhieuDichVu> dsCTPDVTheoMaPhong = new ArrayList<>();
		    try {
		        connectSQL.getInstance();
		        Connection con = connectSQL.getConnection();
		        Date sqlDate = Date.valueOf(ngayLap);
		        String sql = "SELECT * FROM ChiTietPhieuDichVu ct " + 
		                     "JOIN DichVu dv ON dv.maDV = ct.maDV " + 
		                     "JOIN PhieuDatDichVu p ON p.maPhieu = ct.maPhieu " +
		                     "WHERE p.maPhong = ? and p.ngayLap = ?";
		        
		        PreparedStatement statement = con.prepareStatement(sql);
		        statement.setString(1, maPhong);
		        statement.setDate(2, sqlDate);
		        
		        // Thực thi câu lệnh SQL và lấy kết quả trả về
		        ResultSet rs = statement.executeQuery();
		        
		        while (rs.next()) {
		            DichVu dv = new DichVu(rs.getString("maDV"), rs.getString("tenDV"), rs.getString("loaiDV"), rs.getString("donViTinh"), rs.getDouble("donGia"));
		            PhieuDatDichVu pddv = new PhieuDatDichVu(rs.getString("maPhieu"));
		            ChiTietPhieuDichVu chiTiet = new ChiTietPhieuDichVu(dv, rs.getInt("soLuong"), pddv);
		            dsCTPDVTheoMaPhong.add(chiTiet);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return dsCTPDVTheoMaPhong;
		}

//	public ChiTietPhieuDichVu findCTPhieuDVTheoMa(String ma) {
//		ChiTietPhieuDichVu ct = new ChiTietPhieuDichVu();
//		connectSQL.getInstance();
//		Connection con = connectSQL.getConnection();
//		try {
//			String sql = " select * from ChiTietPhieuDichVu where maPhieu = '" + ma + "'";
////			System.out.println(sql2);
//			Statement statement = con.createStatement();
//			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//				DichVu dv = new DichVu(rs.getString("maDV"));
//				PhieuDatDichVu pddv = new PhieuDatDichVu(rs.getString("maPhieu"));
//				ct = new ChiTietPhieuDichVu(rs.getString("maPhieu"), dv, rs.getInt("soLuong"), pddv);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ct;
//
//	}
}



