package entity;

import java.sql.Timestamp;

public class PhieuDatPhongTruoc {
	private Phong phong;
	private KhachHang khachHang;
	private Timestamp thoiGianLapPhieu;
    private Timestamp thoiGianDatPhong;
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public Timestamp getThoiGianLapPhieu() {
		return thoiGianLapPhieu;
	}
	public void setThoiGianLapPhieu(Timestamp thoiGianLapPhieu) {
		this.thoiGianLapPhieu = thoiGianLapPhieu;
	}
	public Timestamp getThoiGianDatPhong() {
		return thoiGianDatPhong;
	}
	public void setThoiGianDatPhong(Timestamp thoiGianDatPhong) {
		this.thoiGianDatPhong = thoiGianDatPhong;
	}
	public PhieuDatPhongTruoc(Phong phong, KhachHang khachHang, Timestamp thoiGianLapPhieu,
			Timestamp thoiGianDatPhong) {
		super();
		this.phong = phong;
		this.khachHang = khachHang;
		this.thoiGianLapPhieu = thoiGianLapPhieu;
		this.thoiGianDatPhong = thoiGianDatPhong;
	}
	public PhieuDatPhongTruoc() {
		super();
	}
	@Override
	public String toString() {
		return "PhieuDatPhongTruoc [phong=" + phong.getMaPhong() + ", khachHang=" + khachHang.getMaKhachHang() + ", thoiGianLapPhieu="
				+ thoiGianLapPhieu + ", thoiGianDatPhong=" + thoiGianDatPhong + "]";
	}
    
    
}
