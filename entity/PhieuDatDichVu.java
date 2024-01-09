package entity;

import java.time.LocalDate;
import java.util.List;

public class PhieuDatDichVu {
	protected String maPhieu;
	protected Phong phong;
	protected KhachHang khachHang;
	protected LocalDate ngayLap;
	protected String tinhTrang;
	protected List<ChiTietPhieuDichVu> dsdv;

	
	
	public PhieuDatDichVu(String maPhieu, Phong phong, KhachHang khachHang, LocalDate ngayLap, String tinhTrang) {
		super();
		this.maPhieu = maPhieu;
		this.phong = phong;
		this.khachHang = khachHang;
		this.ngayLap = ngayLap;
		this.tinhTrang = tinhTrang;
	}
	

	public PhieuDatDichVu() {
		super();
	}

	
	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public List<ChiTietPhieuDichVu> getDsdv() {
		return dsdv;
	}

	public void setDsdv(List<ChiTietPhieuDichVu> dsdv) {
		this.dsdv = dsdv;
	}

	public PhieuDatDichVu(String maPhieu) {
		super();
		this.maPhieu = maPhieu;
	}

	public String getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	@Override
	public String toString() {
		return "PhieuDatDichVu [maPhieu=" + maPhieu + ", phong=" + phong + ", khachHang=" + khachHang + ", ngayLap="
				+ ngayLap + ", dsdv=" + dsdv + "]";
	}
}