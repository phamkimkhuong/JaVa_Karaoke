package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.DichVu;
import entity.LoaiDichVu;
public class DichVu_dao {
	List<DichVu> dsdv;

	public List<DichVu> getAllDichVu() {
		dsdv = new ArrayList<DichVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {

			String sql = "Select * from DichVu";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maDV = rs.getString(1);
				String tenDV = rs.getString(2);
				String loaiDV = rs.getString(3);
				String donViTinh = rs.getString(4);
				int soLuong = rs.getInt(5);
				double donGia = rs.getDouble(6);
				DichVu dv = new DichVu(maDV, tenDV, loaiDV, donViTinh, soLuong, donGia);
				dsdv.add(dv);
				/*LoaiDichVu loaiDichVu = new LoaiDichVu(rs.getString("maLoai"),rs.getString("tenLoai"));
				dsdv.add(new DichVu(rs.getString("maDV"), rs.getString("tenDV"), loaiDichVu,
						rs.getString("donViTinh"),rs.getInt("soLuong"),rs.getDouble("donGia")));*/
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dsdv;
	}

	public boolean addDichVu(DichVu dv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into DichVu values(?,?,?,?,?,?)");
			stmt.setString(1, dv.getMaDV());
			stmt.setString(2, dv.getTenDV());
			stmt.setString(3, dv.getLoaiDichVu());
			stmt.setString(4, dv.getDonViTinh());
			stmt.setInt(5, dv.getSoLuong());
			stmt.setDouble(6, dv.getDonGia());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean updateDichVu(DichVu dv) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "update DichVu set  tenDV=?,loaiDV=?, donViTinh=?, soLuong=?, donGia=? where maDV=?";
			stmt = con.prepareStatement(sql);
			// System.out.println(sql);
			stmt.setString(1, dv.getTenDV());
			stmt.setString(2, dv.getLoaiDichVu());
			stmt.setString(3, dv.getDonViTinh());
			stmt.setInt(4, dv.getSoLuong());
			stmt.setDouble(5, dv.getDonGia());
			stmt.setString(6, dv.getMaDV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public void deleteDichVu(String maDV) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from DichVu where maDV=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maDV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<DichVu> findDichVu(String dv) {
		List<DichVu> dsdv = new ArrayList<DichVu>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from DichVu where " + dv;
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsdv.add(new DichVu(rs.getString("maDV"), rs.getString("tenDV"), rs.getString("loaiDV"),
						rs.getString("donViTinh"), rs.getInt("soLuong"), rs.getDouble("donGia")));
				/*LoaiDichVu loaiDichVu = new LoaiDichVu(rs.getString("maLoai"),rs.getString("tenLoai"));
				dsdv.add(new DichVu(rs.getString("maDV"), rs.getString("tenDV"), loaiDichVu,
						rs.getString("donViTinh"),rs.getInt("soLuong"),rs.getDouble("donGia")));*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdv;
	}

	public DichVu findDichVuTheoMa(String ma) {
		DichVu dv = new DichVu();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from DichVu where maDV = '" + ma+"'";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dv = new DichVu(rs.getString("maDV"), rs.getString("tenDV"), rs.getString("loaiDV"),
						rs.getString("donViTinh"), rs.getInt("soLuong"),rs.getDouble("donGia"));
				/*LoaiDichVu loaiDichVu = new LoaiDichVu(rs.getString("maLoai"),rs.getString("tenLoai"));
				dsdv.add(new DichVu(rs.getString("maDV"), rs.getString("tenDV"), loaiDichVu,
						rs.getString("donViTinh"),rs.getInt("soLuong"),rs.getDouble("donGia")));*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dv;
	}
}
