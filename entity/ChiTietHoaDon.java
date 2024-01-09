package entity;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ChiTietHoaDon {
	protected HoaDon hoaDon;
	protected Phong phong;
	protected DichVu dv;
	protected int soLuongBan;
	protected LocalTime gioVao;
	protected LocalTime gioRa;
	protected List<ChiTietPhieuDichVu> dsDV;

	public ChiTietHoaDon(HoaDon hoaDon, Phong phong, DichVu dv, int soLuongBan, LocalTime gioVao, LocalTime gioRa) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		setDv(dv);
		this.soLuongBan = soLuongBan;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
	}

	public ChiTietHoaDon() {
		super();
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public DichVu getDv() {
		return dv;
	}

	public void setDv(DichVu dv) {
		if (dv == null)
			this.dv = null;
		else
			this.dv = dv;
	}

	public LocalTime getGioVao() {
		return gioVao;
	}

	public void setGioVao(LocalTime gioVao) {
		this.gioVao = gioVao;
	}

	public List<ChiTietPhieuDichVu> getDsDV() {
		return dsDV;
	}

	public void setDsDV(List<ChiTietPhieuDichVu> dsDV) {
		this.dsDV = dsDV;
	}

	public ChiTietHoaDon(HoaDon hoaDon, Phong phong, LocalTime gioVao, LocalTime gioRa) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getSoLuongBan() {
		return soLuongBan;
	}

	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

	public LocalTime getGioRa() {
		return gioRa;
	}

	public void setGioRa(LocalTime gioRa) {
		this.gioRa = gioRa;
	}

	public double tinhTongTien() {
		double tienDV = this.soLuongBan * this.dv.getDonGia();
		long soPhut = this.getGioVao().until(this.getGioRa(), ChronoUnit.MINUTES);
//		System.out.println(soPhut);
		double tienHat = Math.round((this.getPhong().getLoaiPhong().getGiaHat() / 60) * soPhut);
		return tienDV + tienHat;
	}

	public double tinhTongTienHat() {
		long soPhut = this.getGioVao().until(this.getGioRa(), ChronoUnit.MINUTES);
		double tienHat = Math.round((this.getPhong().getLoaiPhong().getGiaHat() / 60) * soPhut);
		return tienHat;
	}

	public double tinhTongTienDV() {
		double tienDV = this.soLuongBan * this.dv.getDonGia();
		return tienDV;
	}

	public long tinhSoPhutHat() {
		long soPhut = this.getGioVao().until(this.getGioRa(), ChronoUnit.MINUTES);
		return soPhut;
	}

	public double tinhTongTienPhongThuong() {
		if (this.getPhong().getLoaiPhong().getTenLoai().contains("Phòng VIP"))
			return 0.0;
		double tienDV = this.soLuongBan * this.dv.getDonGia();
		long soPhut = this.getGioVao().until(this.getGioRa(), ChronoUnit.MINUTES);
		double tienHat = Math.round((this.getPhong().getLoaiPhong().getGiaHat() / 60) * soPhut);
		return tienDV + tienHat;
	}

	public double tinhTongTienDVPhongThuong() {
		if (this.getPhong().getLoaiPhong().getTenLoai().contains("Phòng VIP"))
			return 0.0;
		double tienDV = this.soLuongBan * this.dv.getDonGia();
		return tienDV;
	}

	public double tinhTongTienHatPhongThuong() {
		if (this.getPhong().getLoaiPhong().getTenLoai().contains("Phòng VIP"))
			return 0.0;
		long soPhut = this.getGioVao().until(this.getGioRa(), ChronoUnit.MINUTES);
		double tienHat = Math.round((this.getPhong().getLoaiPhong().getGiaHat() / 60) * soPhut);
		return tienHat;
	}

	public double tinhTongTienPhongVIP() {
		if (this.getPhong().getLoaiPhong().getTenLoai().contains("Phòng Thường"))
			return 0.0;
		double tienDV = this.soLuongBan * this.dv.getDonGia();
		long soPhut = this.getGioVao().until(this.getGioRa(), ChronoUnit.MINUTES);
		double tienHat = Math.round((this.getPhong().getLoaiPhong().getGiaHat() / 60) * soPhut);
		return tienDV + tienHat;
	}

	public double tinhTongTienDVPhongVIP() {
		if (this.getPhong().getLoaiPhong().getTenLoai().contains("Phòng Thường"))
			return 0.0;
		double tienDV = this.soLuongBan * this.dv.getDonGia();
		return tienDV;
	}

	public double tinhTongTienHatPhongVIP() {
		if (this.getPhong().getLoaiPhong().getTenLoai().contains("Phòng Thường"))
			return 0.0;
		long soPhut = this.getGioVao().until(this.getGioRa(), ChronoUnit.MINUTES);
		double tienHat = Math.round((this.getPhong().getLoaiPhong().getGiaHat() / 60) * soPhut);
		return tienHat;
	}
	

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", phong=" + phong + ", dv=" + dv + ", soLuongBan=" + soLuongBan
				+ ", gioVao=" + gioVao + ", gioRa=" + gioRa + "]";
	}

}