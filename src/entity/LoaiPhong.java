package entity;

public class LoaiPhong {
	private String maLoaiPhong;
	private String tenLoaiPhong;
	private int soCho;
	private double donGia;
	
	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, int soCho, double donGia) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.soCho = soCho;
		this.donGia = donGia;
	}

	public LoaiPhong(int soCho, double donGia) {
		super();
		this.soCho = soCho;
		this.donGia = donGia;
	}

	public LoaiPhong(String maLoaiPhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
	}
	public LoaiPhong() {
		super();
	}
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}
	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}
	public int getSoCho() {
		return soCho;
	}
	public void setSoCho(int soCho) {
		this.soCho = soCho;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	@Override
	public String toString() {
		return "LoaiPhong [maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", soCho=" + soCho
				+ ", donGia=" + donGia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLoaiPhong == null) ? 0 : maLoaiPhong.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		if (maLoaiPhong == null) {
			if (other.maLoaiPhong != null)
				return false;
		} else if (!maLoaiPhong.equals(other.maLoaiPhong))
			return false;
		return true;
	}
	
	
}
