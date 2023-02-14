package entity;

import java.sql.Date;
import java.sql.Timestamp;

public class ChiTietHoaDonPhong
{
    private HoaDon hoaDon;
    private Phong phong;
    private Timestamp ngayBatDau;
    private Timestamp ngayKetThuc;
    
    
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	
	public Timestamp getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Timestamp ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Timestamp getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(Timestamp ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public ChiTietHoaDonPhong() {
		super();
	}
	public ChiTietHoaDonPhong(HoaDon hoaDon, Phong phong, Timestamp ngayBatDau, Timestamp ngayKetThuc) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}
	
	public ChiTietHoaDonPhong(HoaDon hoaDon, Phong phong, Timestamp ngayKetThuc) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.ngayKetThuc = ngayKetThuc;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDonPhong [hoaDon=" + hoaDon + ", phong=" + phong + ", ngayBatDau=" + ngayBatDau
				+ ", ngayKetThuc=" + ngayKetThuc + "]";
	}
	
	
	
	
    
    
}
