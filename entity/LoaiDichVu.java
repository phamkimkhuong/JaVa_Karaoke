package entity;

public class LoaiDichVu {
	protected String maLoai;
	protected String tenLoai;
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
	public LoaiDichVu(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}
	
	public LoaiDichVu(String tenLoai) {
		super();
		this.tenLoai = tenLoai;
	}
	public LoaiDichVu() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoaiDichVu [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}
	
}
