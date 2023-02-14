package entity;

public class ChiTietHoaDonSanPham {
	private HoaDon hoaDon;
	private SanPham sanPham;
	private int soLuong;
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public ChiTietHoaDonSanPham(HoaDon hoaDon, SanPham sanPham, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}
	public ChiTietHoaDonSanPham() {
		super();
	}
	@Override
	public String toString() {
		return "ChiTietHoaDonSanPham [hoaDon=" + hoaDon.getMaHoaDon() + ", sanPham=" + sanPham.getMaSanPham() + ", soLuong=" + soLuong + "]";
	}
	
}
