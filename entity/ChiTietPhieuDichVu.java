package entity;

public class ChiTietPhieuDichVu {
	protected DichVu dichVu;
	protected int soLuongBan;
	protected PhieuDatDichVu phieuDichVu;

	public ChiTietPhieuDichVu(DichVu dichVu, int soLuongBan, PhieuDatDichVu phieuDichVu) {
		super();
		this.dichVu = dichVu;
		this.soLuongBan = soLuongBan;
		this.phieuDichVu = phieuDichVu;
	}

	public ChiTietPhieuDichVu(DichVu dichVu, int soLuongBan) {
		super();
		this.dichVu = dichVu;
		this.soLuongBan = soLuongBan;
	}

	public ChiTietPhieuDichVu() {
		// TODO Auto-generated constructor stub
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public int getSoLuongBan() {
		return soLuongBan;
	}

	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

	public PhieuDatDichVu getPhieuDichVu() {
		return phieuDichVu;
	}

	public void setPhieuDichVu(PhieuDatDichVu phieuDichVu) {
		this.phieuDichVu = phieuDichVu;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDichVu [dichVu=" + dichVu + ", soLuongBan=" + soLuongBan + ", phieuDichVu=" + phieuDichVu
				+ "]";
	}
}