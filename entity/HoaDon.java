package entity;

import java.time.LocalDate;
import java.util.Objects;

public class HoaDon {
	protected String maHD;
	protected PhieuDatPhong phieuDatPhong;
	protected PhieuDatDichVu phieuDatDichVu;
	protected KhachHang khachHang;
	protected NhanVien nhanVien;
	protected LocalDate ngayLap;

	public HoaDon(String maHD, KhachHang khachHang, NhanVien nhanVien, LocalDate ngayLap) {
		super();
		this.maHD = maHD;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLap = ngayLap;
	}

	public HoaDon() {
		super();
	}

	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
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

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}

	public PhieuDatDichVu getPhieuDatDichVu() {
		return phieuDatDichVu;
	}

	public void setPhieuDatDichVu(PhieuDatDichVu phieuDatDichVu) {
		this.phieuDatDichVu = phieuDatDichVu;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", ngayLap=" + ngayLap
				+ "]";
	}
}