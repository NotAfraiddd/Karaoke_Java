package entity;

public class Phong {
	private String maPhong;
	private LoaiPhong loaiPhong;
	private String tenPhong;
	private String trangThai;
	private int lau;
	
	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public int getLau() {
		return lau;
	}

	public void setLau(int lau) {
		this.lau = lau;
	}

	

	public Phong(String maPhong, LoaiPhong loaiPhong, String tenPhong, String trangThai, int lau) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.lau = lau;
	}

	public Phong(String maPhong, LoaiPhong loaiPhong) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
	}

	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public Phong() {
		super();
	}
	
	public Phong(String maPhong, String trangThai) {
		super();
		this.maPhong = maPhong;
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", tenPhong=" + tenPhong + ", trangThai="
				+ trangThai + ", lau=" + lau + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhong == null) ? 0 : maPhong.hashCode());
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
		Phong other = (Phong) obj;
		if (maPhong == null) {
			if (other.maPhong != null)
				return false;
		} else if (!maPhong.equals(other.maPhong))
			return false;
		return true;
	}

}