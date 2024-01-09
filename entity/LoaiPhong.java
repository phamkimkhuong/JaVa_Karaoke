package entity;

public class LoaiPhong {
	@Override
	public String toString() {
		return "LoaiPhong [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", giaHat=" + giaHat + ", mota=" + mota + "]";
	}

	protected String maLoai;
	protected String tenLoai;
	protected double giaHat;
	protected String mota;

	public LoaiPhong(String maLoai, String tenLoai, double giaHat, String mota) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.giaHat = giaHat;
		setMota(mota);
	}

	public LoaiPhong(String tenLoai, double giaHat) {
		super();
		this.tenLoai = tenLoai;
		this.giaHat = giaHat;
	}

	public LoaiPhong() {
		super();
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public double getGiaHat() {
		return giaHat;
	}

	public void setGiaHat(double giaHat) {
		this.giaHat = giaHat;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		if (mota == null)
			this.mota = "ghj";
		else
			this.mota = mota;
	}

}
