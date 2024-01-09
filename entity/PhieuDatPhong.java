package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class PhieuDatPhong {
	protected String maPhieu;
	protected LocalDate ngayLap;
	protected String trangThai;
	protected KhachHang khachHang;
	protected NhanVien nhanVien;

	public PhieuDatPhong(String maPhieu, LocalDate ngayLap, KhachHang khachHang, NhanVien nhanVien, String trangThai) {
		super();
		this.maPhieu = maPhieu;
		this.ngayLap = ngayLap;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}

	public PhieuDatPhong() {
		super();
	}

	public PhieuDatPhong(String maPhieu) {
		super();
		this.maPhieu = maPhieu;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maPhieu=" + maPhieu + ", ngayLap=" + ngayLap + ", trangThai=" + trangThai
				+ ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + "]";
	}

}