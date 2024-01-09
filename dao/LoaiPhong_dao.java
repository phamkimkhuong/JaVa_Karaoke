package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.LoaiPhong;

public class LoaiPhong_dao implements Interface_dao<LoaiPhong> {
	/*
	 * Lấy danh sách loại phòng
	 */
	@Override
	public List<LoaiPhong> selectALL() {
		List<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "select * from LoaiPhong";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"), rs.getDouble("giaHat"),
						rs.getString("moTa")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}

	/*
	 * Chức năng thêm mới một phòng
	 */
	@Override
	public void add(LoaiPhong loaiPhong) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into LoaiPhong values(?,?,?,?)");
			stmt.setString(1, loaiPhong.getMaLoai());
			stmt.setString(2, loaiPhong.getTenLoai());
			stmt.setDouble(3, loaiPhong.getGiaHat());
			stmt.setString(4, loaiPhong.getMota());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	/*
	 * Xóa loại phòng theo mã loại
	 */
	@Override
	public void delete(String maLoai) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from LoaiPhong where maLoai=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLoai);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	/*
	 * Cập Nhật thông tin Một phòng theo mã phòng
	 */
	@Override
	public void update(LoaiPhong loaiPhong) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "update LoaiPhong set tenloai=?, giaHat=? , moTa=? where maLoai=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loaiPhong.getTenLoai());
			stmt.setDouble(2, loaiPhong.getGiaHat());
			stmt.setString(3, loaiPhong.getMota());
			stmt.setString(4, loaiPhong.getMaLoai());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	/*
	 * Tìm Kiếm Các Phòng theo yêu cầu, trả về danh sách phòng
	 */
	@Override
	public List<LoaiPhong> findByCondition(String Loaiphong) {
		List<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql = "Select * from LoaiPhong where " + Loaiphong;
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"), rs.getDouble("giaHat"),
						rs.getString("moTa")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}

	@Override
	public void close(PreparedStatement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	@Override
	public LoaiPhong findByID(String id) {
		return null;
	}
}
