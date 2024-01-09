package entity;

import java.util.Objects;

public class Phong {
	protected String maPhong;
	protected String tenPhong;
	protected LoaiPhong LoaiPhong; 
	// Trang thái có:phòng trống, đang sử dụng, phòng chờ
	protected String trangThai;

	public Phong(String maPhong, String tenPhong, entity.LoaiPhong loaiPhong, String trangThai) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		LoaiPhong = loaiPhong;
		this.trangThai = trangThai;
	}

	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public Phong(String maPhong, String tenPhong, entity.LoaiPhong loaiPhong) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		LoaiPhong = loaiPhong;
	}

	public Phong(String tenPhong, entity.LoaiPhong loaiPhong, String trangThai) {
		super();
		this.tenPhong = tenPhong;
		LoaiPhong = loaiPhong;
		this.trangThai = trangThai;
	}

	public Phong() {
		super();
	}

	public Phong(String maPhong, String tenPhong) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return LoaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		LoaiPhong = loaiPhong;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", LoaiPhong=" + LoaiPhong + ", trangThai="
				+ trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

}