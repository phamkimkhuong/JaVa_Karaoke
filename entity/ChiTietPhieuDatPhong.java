package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChiTietPhieuDatPhong {
	private PhieuDatPhong phieuDatPhong;
	private Phong phong;
	private LocalDate ngayNhan;
	private LocalTime gioVao;

	public ChiTietPhieuDatPhong(PhieuDatPhong phieuDatPhong, Phong phong, LocalDate ngayNhan, LocalTime gioVao) {
		super();
		this.phieuDatPhong = phieuDatPhong;
		this.phong = phong;
		this.ngayNhan = ngayNhan;
		this.gioVao = gioVao;
	}

	public ChiTietPhieuDatPhong() {
		// TODO Auto-generated constructor stub
	}

	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}

	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public LocalDate getNgayNhan() {
		return ngayNhan;
	}

	public void setNgayNhan(LocalDate ngayNhan) {
		this.ngayNhan = ngayNhan;
	}

	public LocalTime getGioVao() {
		return gioVao;
	}

	public void setGioVao(LocalTime gioVao) {
		this.gioVao = gioVao;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDatPhong [phieuDatPhong=" + phieuDatPhong + ", phong=" + phong + ", ngayNhan=" + ngayNhan
				+ ", gioVao=" + gioVao + "]";
	}

}