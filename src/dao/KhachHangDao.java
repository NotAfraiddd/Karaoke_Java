package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.*;
import connect.Database;

public class KhachHangDao {
	Database conDB = new Database();
	private ArrayList<KhachHang> dskh;

	public KhachHangDao() {
		// TODO Auto-generated constructor stub
	}
	public String getMaKhachHang(){
		dskh = new ArrayList<KhachHang>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select maKhachHang from KhachHang");
			while(rs.next()) {
				String maKhachHang = rs.getString(1);
				KhachHang kh = new KhachHang(maKhachHang);
				dskh.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<KhachHang> getAllKhachHang(){
		dskh = new ArrayList<KhachHang>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from KhachHang");
			while(rs.next()) {
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String soCMND = rs.getString(3);
				String soDT = rs.getString(4);
				KhachHang kh = new KhachHang(maKhachHang, tenKhachHang,soCMND,soDT);
				dskh.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dskh;
	}
	/*
	 * Tìm KH theo SDT
	 */
	public ArrayList<KhachHang> timKhachHangTheoSoDT(String sodt) throws SQLException, ClassNotFoundException {
		dskh = new ArrayList<KhachHang>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from KhachHang where soDT like ?");
			stmt.setString(1, sodt);
			ResultSet rs = stmt.executeQuery();
			KhachHang timKH = new KhachHang();
			while(rs.next()) {
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String soCMND = rs.getString(3);
				String soDT = rs.getString(4);
				KhachHang kh = new KhachHang(maKhachHang, tenKhachHang,soCMND,soDT);
				dskh.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dskh;
	}
	/*
	 * Tìm KH theo cmnd
	 */
	public ArrayList<KhachHang> timKhachHangTheoSoCMND(String cmnd) throws SQLException, ClassNotFoundException {
		dskh = new ArrayList<KhachHang>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from KhachHang where soCMND = ?");
			stmt.setString(1, cmnd);
			ResultSet rs = stmt.executeQuery();
			KhachHang timKH = new KhachHang();
			while(rs.next()) {
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String soCMND = rs.getString(3);
				String soDT = rs.getString(4);
				KhachHang kh = new KhachHang(maKhachHang, tenKhachHang,soCMND,soDT);
				dskh.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return dskh;
	}
	/*
	 * Tìm KH theo ma
	 */
	public KhachHang timKhachHangTheoMa(String maKhachHang) throws SQLException, ClassNotFoundException {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from KhachHang where maKhachHang = ?");
			stmt.setString(1, maKhachHang);
			ResultSet rs = stmt.executeQuery();
			KhachHang timKH = new KhachHang();
			if(rs.next()) {
				timKH.setMaKhachHang(rs.getString("maKhachHang"));
				timKH.setTenKhachHang(rs.getString("tenKhachHang"));
				timKH.setSoCMND(rs.getString("soCMND"));
				timKH.setSoDT(rs.getString("soDT"));
				return timKH;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Tìm KH theo tên
	 */
	public KhachHang timKhachHangTheoTen(String tenKhachHang) throws SQLException, ClassNotFoundException {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from KhachHang where tenKhachHang like ? ");
			stmt.setString(1, tenKhachHang);
			ResultSet rs = stmt.executeQuery();
			KhachHang timKH = new KhachHang();
			if(rs.next()) {
				timKH.setMaKhachHang(rs.getString("maKhachHang"));
				timKH.setTenKhachHang(rs.getString("tenKhachHang"));
				timKH.setSoCMND(rs.getString("soCMND"));
				timKH.setSoDT(rs.getString("soDT"));
				return timKH;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Tao KH
	 */
	public boolean themKhachHang(KhachHang kh) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n =0;
		try {
			PreparedStatement stmt = con.prepareStatement("Insert into KhachHang values(?,?,?,?)");
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getTenKhachHang());
			stmt.setString(3, kh.getSoCMND());
			stmt.setString(4, kh.getSoDT());
			n= stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * Theem khach hang vao HD
	 */
	public void themKHVaoHD(String maKhachHang, String tenKh, String soCMND, String soDT) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		String sql = "INSERT INTO KhachHang VALUES(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maKhachHang);
		stmt.setString(2, tenKh);
		stmt.setString(3, soCMND);
		stmt.setString(4, soDT);
		stmt.executeUpdate();
		con.close();
	}
	/*
	 * Theem khach hang vao hoa don
	 */
	public void themVaoChiTietHoaDon(String maKhachHang) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		String sql = "INSERT INTO KhachHang VALUES(?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maKhachHang);
		stmt.executeUpdate();
		con.close();
	}
	/*
	 * Cap nhat sinh vien
	 */
	public boolean capNhatKhachHang(KhachHang kh) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n =0;
		try {
			PreparedStatement stmt = con.prepareStatement("update KhachHang set tenKhachHang = ? , soCMND = ? , soDT = ? where maKhachHang = ?");
			stmt.setString(1, kh.getTenKhachHang());
			stmt.setString(2, kh.getSoCMND());
			stmt.setString(3, kh.getSoDT());
			stmt.setString(4, kh.getMaKhachHang());
			n=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * Xoa KH
	 */
	public boolean xoaKhachHang(String ma) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n=0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from KhachHang where maKhachHang = ?");
			stmt.setString(1, ma);
			n =  stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<KhachHang> timKhachHangTheoTuKhoa(String tuKhoa){
		dskh = new ArrayList<KhachHang>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from KhachHang where maKhachHang like ?  or tenKhachHang like ? or soCMND like ? or soDT like ?");
			stmt.setString(1,"%" + tuKhoa + "%");
			stmt.setString(2,"%" + tuKhoa + "%");
			stmt.setString(3,"%" + tuKhoa + "%");
			stmt.setString(4,"%" + tuKhoa + "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String soCMND = rs.getString(3);
				String soDT = rs.getString(4);
				KhachHang kh = new KhachHang(maKhachHang, tenKhachHang,soCMND,soDT);
				dskh.add(kh);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dskh;
	}
	
	
	
	
}
