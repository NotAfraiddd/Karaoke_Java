package entity;

import java.sql.Timestamp;

public class HoaDon
{
    private String maHoaDon;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Timestamp ngayLapHoaDon;
    private double thanhTien;
    
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	
	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	public HoaDon() {
		super();
	}
	
	public Timestamp getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}
	public void setNgayLapHoaDon(Timestamp ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, Timestamp ngayLapHoaDon, double thanhTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.thanhTien = thanhTien;
	}
	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", khachHang=" + khachHang.getMaKhachHang() + ", nhanVien=" + nhanVien.getMaNhanVien()
				+ ", ngayLapHoaDon=" + ngayLapHoaDon + ", thanhTien=" + thanhTien + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHoaDon == null) ? 0 : maHoaDon.hashCode());
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
		HoaDon other = (HoaDon) obj;
		if (maHoaDon == null) {
			if (other.maHoaDon != null)
				return false;
		} else if (!maHoaDon.equals(other.maHoaDon))
			return false;
		return true;
	}
    
    
}
