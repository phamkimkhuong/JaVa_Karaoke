package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectSQL;
import entity.LoaiPhong;
import entity.Phong;

public class Phong_dao implements Interface_dao<Phong> {
	private String sql;
	private List<Phong> dsPhong;

	@Override
	public List<Phong> selectALL() {
		dsPhong = new ArrayList<Phong>();
		try {

			// Bước 1: Tạo kết nối đến CSDL
			connectSQL.getInstance();
			Connection con = connectSQL.getConnection();

			// Bước 2: Tạo ra đối tượng statement
			sql = "select * from Phong inner join LoaiPhong on Phong.maLP=LoaiPhong.maLoai";
			PreparedStatement statement = con.prepareStatement(sql);

			// Bước 3: Thực thi câu lệnh SQL
			ResultSet rs = statement.executeQuery();

			// Bước 4:
			while (rs.next()) {
				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"),
						rs.getDouble("giaHat"), rs.getString("moTa"));
				dsPhong.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), loaiPhong,
						rs.getString("trangthai")));
			}

			// Bước 5: Đóng kết nối
			close(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	/*
	 * Thêm mới một phòng
	 */
	@Override
	public void add(Phong phong) {

		// Bước 1: Tạo kết nối đến CSDL
		Connection con = connectSQL.getConnection();

		// Bước 2: Tạo ra đối tượng statement
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into Phong values(?,?,?,?)");
			stmt.setString(1, phong.getMaPhong());
			stmt.setString(2, phong.getTenPhong());
			stmt.setString(3, phong.getTrangThai());
			stmt.setString(4, phong.getLoaiPhong().getMaLoai());

			// Bước 3: Thực thi câu lệnh SQL
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Bước 4: Đóng kết nối
			close(stmt);
		}
	}

	/*
	 * Xóa Phòng theo mã phòng
	 */
	@Override
	public void delete(String maPhong) {

		// Bước 1: Tạo kết nối đến CSDL
		Connection con = connectSQL.getConnection();

		// Bước 2: Tạo ra đối tượng statement
		PreparedStatement stmt = null;
		String sql = "delete from Phong where MaPhong=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhong);

			// Bước 3: Thực thi câu lệnh SQL
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Bước 4: Đóng kết nối
			close(stmt);
		}
	}

	/*
	 * Cập Nhật thông tin Một phòng theo mã phòng
	 */
	@Override
	public void update(Phong phong) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "update Phong set  tenPhong=? ,trangthai=? where maPhong=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, phong.getTenPhong());
			stmt.setString(2, phong.getTrangThai());
			stmt.setString(3, phong.getMaPhong());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	/*
	 * Tìm Phòng theo mã phòng
	 */
	@Override
	public Phong findByID(String ma) {
		Phong P = new Phong();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql2 = " select * from Phong where tenPhong = N'" + ma + "'";
			PreparedStatement statement = con.prepareStatement(sql2);
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
//			ResultSet rs = statement.executeQuery(sql2);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				P = new Phong(rs.getString("maPhong"), rs.getString("tenPhong"));
			}
			close(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return P;

	}

	/*
	 * Tìm Kiếm Các Phòng theo yêu cầu, trả về danh sách phòng
	 */
	@Override
	public List<Phong> findByCondition(String phong) {
		List<Phong> dsPhong = new ArrayList<Phong>();
		connectSQL.getInstance();
		Connection con = connectSQL.getConnection();
		try {
			String sql2 = sql + " where " + phong;
			PreparedStatement statement = con.prepareStatement(sql2);
			// Thực thi câu lệnh SQL trả vể đối tượng ResultSet
//			ResultSet rs = statement.executeQuery(sql2);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				LoaiPhong loaiPhong = new LoaiPhong(rs.getString("maLoai"), rs.getString("tenLoai"),
						rs.getDouble("giaHat"), rs.getString("moTa"));
				dsPhong.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), loaiPhong,
						rs.getString("trangthai")));
			}
			close(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	/*
	 * Chức năng thêm mới một phòng
	 */

	public void updateALLPhong(Phong phong) {
		Connection con = connectSQL.getConnection();
		PreparedStatement stmt = null;
		String sql = "update Phong set  tenPhong=? ,trangthai=?,maLP=? where maPhong=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, phong.getTenPhong());
			stmt.setString(2, phong.getTrangThai());
			stmt.setString(3, phong.getLoaiPhong().getMaLoai());
			stmt.setString(4, phong.getMaPhong());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
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

}
