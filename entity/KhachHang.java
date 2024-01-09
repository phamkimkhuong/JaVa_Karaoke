package entity;

import java.util.Objects;

public class KhachHang {
	protected String maKH;
	protected String hoTen;
	protected String ngaySinh;
	protected String sdt;
	protected String email;

	public KhachHang(String maKH, String hoTen, String ngaySinh, String sDT, String email) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		setNgaySinh(ngaySinh);
		this.sdt = sDT;
		setEmail(email);
	}

	public KhachHang(String hoTen, String sdt) {
		super();
		this.hoTen = hoTen;
		this.sdt = sdt;
	}

	public KhachHang(String maKH, String hoTen, String sdt) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.sdt = sdt;
	}

	public KhachHang(String maKH) {
		this(maKH, "ho Ten", "ngay Sinh", "sdt", "email");
	}

	public KhachHang() {
		this("ma khach hang");
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
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
		if (ngaySinh == null)
			this.ngaySinh = "";
		else
			this.ngaySinh = ngaySinh;
	}

	public String getSDT() {
		return sdt;
	}

	public void setSDT(String sDT) {
		sdt = sDT;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null)
			this.email = "";
		else
			this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sdt, email, hoTen, maKH, ngaySinh);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(sdt, other.sdt) && Objects.equals(email, other.email)
				&& Objects.equals(hoTen, other.hoTen) && maKH == other.maKH && Objects.equals(ngaySinh, other.ngaySinh);
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", SDT=" + sdt + ", email="
				+ email + "]";
	}

}