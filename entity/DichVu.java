package entity;

import java.util.Objects;

public class DichVu {
	protected String maDV;
	protected String tenDV;
	protected String loaiDichVu;
	protected String donViTinh;
	protected int soLuong;
	protected double donGia;
	public DichVu(String maDV, String tenDV, String loaiDichVu, String donViTinh, int soLuong, double donGia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.loaiDichVu = loaiDichVu;
		this.donViTinh = donViTinh;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
	public DichVu(String maDV) {
		this(maDV, "ten dv", "loai dv", "don vi tinh",0,0);
	}
	

	public DichVu(String maDV, String tenDV, int soLuong, double donGia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
	/*public DichVu() {
		this("ma dich vu");
	}*/
	
	public DichVu(String maDV, String tenDV, double donGia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.donGia = donGia;
	}
	
	public DichVu(String maDV, String tenDV, String loaiDichVu, String donViTinh, double donGia) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		loaiDichVu = loaiDichVu;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
	}
	public DichVu() {
	}

	public String getMaDV() {
		return maDV;
	}
	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	public String getLoaiDichVu() {
		return loaiDichVu;
	}
	public void setLoaiDichVu(String loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(loaiDichVu, donGia, donViTinh, maDV, soLuong, tenDV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(loaiDichVu, other.loaiDichVu)
				&& Double.doubleToLongBits(donGia) == Double.doubleToLongBits(other.donGia)
				&& Objects.equals(donViTinh, other.donViTinh) && Objects.equals(maDV, other.maDV)
				&& soLuong == other.soLuong && Objects.equals(tenDV, other.tenDV);
	}
	@Override
	public String toString() {
		return "DichVu [maDV=" + maDV + ", tenDV=" + tenDV + ", LoaiDichVu=" + loaiDichVu + ", donViTinh=" + donViTinh
				+ ", soLuong=" + soLuong + ", donGia=" + donGia + "]";
	}
	
	
}