package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import entity.*;
import connect.Database;

public class HoaDonDao {
	Database conDB = new Database();
	private ArrayList<HoaDon> dshd;

	public HoaDonDao() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * Cap nhat tien trong hoa don
	 */
	public boolean capNhatTien(String maHD, double thanhTien){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("update HoaDon set thanhTien = ? where maHoaDon = ? ");
			stmt.setDouble(1, thanhTien);
			stmt.setString(2, maHD);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	/*
	 * Theem khach hang vao hoa don
	 */
	public void themKHVaoChiTietHoaDon(String maKhachHang) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		String sql = "INSERT INTO KhachHang VALUES(?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maKhachHang);
		stmt.executeUpdate();
		con.close();
	}
	/*
	 * lay so hoa don
	 */
	public int soMaHD() throws SQLException {
		Connection con = Database.getInstance().getConnection();
		Statement stmt;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from HoaDon");
		int count = 1;
		while(rs.next()) {
			count++;
		}
		return count;
	}
	/*
	 * get danh sach hoa don chua thanh toan
	 */
	public ArrayList<HoaDon> getHDChuaTT() {
		Connection con = Database.getInstance().getConnection();
		dshd = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from HoaDon where thanhTien like '0%'");
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maKhachHang = rs.getString("maKhachHang");
				String maNhanVien = rs.getString("maNhanVien");
				Timestamp ngayLapHoaDon = rs.getTimestamp("ngayLapHoaDon");
				double thanhTien = rs.getDouble("thanhTien");
				HoaDon hd = new HoaDon(maHoaDon, new KhachHang(maKhachHang), new NhanVien(maNhanVien), ngayLapHoaDon,
						thanhTien);
				dshd.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}
	
	
	
	public ArrayList<HoaDon> getHDTT() {
		Connection con = Database.getInstance().getConnection();
		dshd = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from HoaDon where thanhTien > 0");
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maKhachHang = rs.getString("maKhachHang");
				String maNhanVien = rs.getString("maNhanVien");
				Timestamp ngayLapHoaDon = rs.getTimestamp("ngayLapHoaDon");
				double thanhTien = rs.getDouble("thanhTien");
				HoaDon hd = new HoaDon(maHoaDon, new KhachHang(maKhachHang), new NhanVien(maNhanVien), ngayLapHoaDon,
						thanhTien);
				dshd.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * get danh sach hoa don
	 */
	public ArrayList<HoaDon> getAllHoaDon() {
		Connection con = Database.getInstance().getConnection();
		dshd = new ArrayList<HoaDon>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from HoaDon");
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maKhachHang = rs.getString("maKhachHang");
				String maNhanVien = rs.getString("maNhanVien");
				Timestamp ngayLapHoaDon = rs.getTimestamp("ngayLapHoaDon");
				double thanhTien = rs.getDouble("thanhTien");
				HoaDon hd = new HoaDon(maHoaDon, new KhachHang(maKhachHang), new NhanVien(maNhanVien), ngayLapHoaDon,
						thanhTien);
				dshd.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}

	/*
	 * Tìm hoa don theo ma
	 */
	public HoaDon timHDTheoMa(String maHD){
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from HoaDon where maHoaDon like ?");
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			HoaDon hdTim = new HoaDon();
			if (rs.next()) {
				hdTim.setMaHoaDon(rs.getString("maHoaDon"));
				hdTim.setKhachHang(new KhachHang(rs.getString("maKhachHang")));
				hdTim.setNhanVien(new NhanVien(rs.getString("maNhanVien")));
				hdTim.setNgayLapHoaDon(rs.getTimestamp("ngayLapHoaDon"));
				hdTim.setThanhTien(rs.getDouble("thanhTien"));
				return hdTim;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Tìm hoa don theo ma khach hang
	 */
	public ArrayList<HoaDon> timHDTheoMaKH(String tt) {
		dshd = new ArrayList<HoaDon>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from HoaDon where maKhachHang like ?");
			stmt.setString(1, tt);
			ResultSet rs = stmt.executeQuery();
			HoaDon hdTim = new HoaDon();
			if (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maKhachHang = rs.getString("maKhachHang");
				String maNhanVien = rs.getString("maNhanVien");
				Timestamp ngayLapHoaDon = rs.getTimestamp("ngayLapHoaDon");
				double thanhTien = rs.getDouble("thanhTien");
				KhachHang kh = new KhachHang(maKhachHang);
				NhanVien nv = new NhanVien(maNhanVien);
				hdTim = new HoaDon(maHoaDon, kh, nv, ngayLapHoaDon, thanhTien);
				dshd.add(hdTim);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}

	/*
	 * Tìm hoa don theo ma nhan vien
	 */
	public ArrayList<HoaDon> timHDTheoMaNV(String tt) {
		Connection con = Database.getInstance().getConnection();
		try {
			dshd = new ArrayList<HoaDon>();
			PreparedStatement stmt = con.prepareStatement("Select * from HoaDon where maNhanVien like ?");
			stmt.setString(1, tt);
			ResultSet rs = stmt.executeQuery();
			HoaDon hdTim = new HoaDon();
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maKhachHang = rs.getString("maKhachHang");
				String maNhanVien = rs.getString("maNhanVien");
				Timestamp ngayLapHoaDon = rs.getTimestamp("ngayLapHoaDon");
				double thanhTien = rs.getDouble("thanhTien");
				KhachHang kh = new KhachHang(maKhachHang);
				NhanVien nv = new NhanVien(maNhanVien);
				hdTim = new HoaDon(maHoaDon, kh, nv, ngayLapHoaDon, thanhTien);
				dshd.add(hdTim);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}

	/*
	 * Tao hoa don
	 */
	public boolean themHoaDon(HoaDon hd){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("Insert into HoaDon values(?,?,?,?,?)");
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setString(2, hd.getKhachHang().getMaKhachHang());
			stmt.setString(3, hd.getNhanVien().getMaNhanVien());
			stmt.setTimestamp(4, hd.getNgayLapHoaDon());
			stmt.setDouble(5, hd.getThanhTien());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * Cap nhat hoa don
	 */
	public boolean capNhatHD(HoaDon hd){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("update HoaDon set maKhachHang = ? , maNhanVien = ? "
					+ " , ngayLapHoaDon = ? , thanhTien = ? where maHoaDon = ? ");
			stmt.setString(1, hd.getKhachHang().getMaKhachHang());
			stmt.setString(2, hd.getNhanVien().getMaNhanVien());
			stmt.setTimestamp(3, hd.getNgayLapHoaDon());
			stmt.setDouble(4, hd.getThanhTien());
			stmt.setString(5, hd.getMaHoaDon());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * Xoa hoa don
	 */
	public boolean xoaHD(String ma) {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from HoaDon where maHoaDon = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	

	
	public ArrayList<HoaDon> timHDTT(Timestamp a,Timestamp b) {
		Connection con = Database.getInstance().getConnection();
		try {
			dshd = new ArrayList<HoaDon>();
			PreparedStatement stmt = con.prepareStatement("Select * from HoaDon where ngayLapHoaDon between ? and ? and thanhTien >0 ");
			stmt.setTimestamp(1, a);
			stmt.setTimestamp(2, b);
			
			ResultSet rs = stmt.executeQuery();
			HoaDon hdTim = new HoaDon();
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maKhachHang = rs.getString("maKhachHang");
				String maNhanVien = rs.getString("maNhanVien");
				Timestamp ngayLapHoaDon = rs.getTimestamp("ngayLapHoaDon");
				double thanhTien = rs.getDouble("thanhTien");
				KhachHang kh = new KhachHang(maKhachHang);
				NhanVien nv = new NhanVien(maNhanVien);
				hdTim = new HoaDon(maHoaDon, kh, nv, ngayLapHoaDon, thanhTien);
				dshd.add(hdTim);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dshd;
	}
	
	
	
	
	
	
}
