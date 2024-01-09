package entity;

import java.util.Objects;

public class NhanVien {
	protected String maNV;
	protected String hoTen;
	protected String ngaySinh;
	protected String sDT;
	protected String email;
	protected String trinhDo;
	protected String chucVu;
	protected double heSoLuong;
	protected double luongCoBan;
	protected TaiKhoan taiKhoan;

	public NhanVien(String maNV, String hoTen, String ngaySinh, String sDT, String email, String trinhDo, String chucVu,
			double heSoLuong, double luongCoBan, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.sDT = sDT;
		this.email = email;
		this.trinhDo = trinhDo;
		this.chucVu = chucVu;
		this.heSoLuong = heSoLuong;
		this.luongCoBan = luongCoBan;
		this.taiKhoan = taiKhoan;
	}

	public NhanVien(String maNV, String hoTen) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public double getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chucVu, email, heSoLuong, hoTen, luongCoBan, maNV, ngaySinh, sDT, taiKhoan, trinhDo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(chucVu, other.chucVu) && Objects.equals(email, other.email)
				&& Double.doubleToLongBits(heSoLuong) == Double.doubleToLongBits(other.heSoLuong)
				&& Objects.equals(hoTen, other.hoTen)
				&& Double.doubleToLongBits(luongCoBan) == Double.doubleToLongBits(other.luongCoBan)
				&& Objects.equals(maNV, other.maNV) && Objects.equals(ngaySinh, other.ngaySinh)
				&& Objects.equals(sDT, other.sDT) && Objects.equals(taiKhoan, other.taiKhoan)
				&& Objects.equals(trinhDo, other.trinhDo);
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", sDT=" + sDT + ", email="
				+ email + ", trinhDo=" + trinhDo + ", chucVu=" + chucVu + ", heSoLuong=" + heSoLuong + ", luongCoBan="
				+ luongCoBan + ", taiKhoan=" + taiKhoan + "]";
	}

}