package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.KhachHang;
import entity.TaiKhoan;

public class TaiKhoanDao {
	Database conDB = new Database();
	ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
	public TaiKhoanDao() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * xem tat ca tai khoan
	 */
	public ArrayList<TaiKhoan> getAllTK(){
		dstk = new ArrayList<TaiKhoan>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from TaiKhoan");
			while(rs.next()) {
				String tenDangNhap = rs.getString(1);
				String matKhau = rs.getString(2);
				TaiKhoan kh = new TaiKhoan(tenDangNhap, matKhau);
				dstk.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dstk;
	}
	/*
	 * Tìm tai khoan theo tên dang nhap
	 */
	public TaiKhoan timTKTheoTen(String ten) throws SQLException, ClassNotFoundException {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from TaiKhoan where tenDangNhap like ?");
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			TaiKhoan tkTim = new TaiKhoan();
			if(rs.next()) {
				tkTim.setTenDangNhap(rs.getString("tenDangNhap"));
				tkTim.setMatKhau(rs.getString("matKhau"));
				return tkTim;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<TaiKhoan> timTKTheoMatKhau(String mk) throws SQLException, ClassNotFoundException {
		Connection con = Database.getInstance().getConnection();
		dstk = new ArrayList<TaiKhoan>();
		try {	
			PreparedStatement stmt = con.prepareStatement("Select * from TaiKhoan where matKhau like ?");
			stmt.setString(1, mk);
			ResultSet rs = stmt.executeQuery();
			TaiKhoan tkTim = new TaiKhoan();
			while(rs.next()) {
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				TaiKhoan tk = new TaiKhoan(tenDangNhap, matKhau);
				dstk.add(tk);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dstk;
	}
	/*
	 * tao tai khoan
	 */
	public boolean taoTK(TaiKhoan tk) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n =0;
		try {
			PreparedStatement stmt = con.prepareStatement("Insert into TaiKhoan values(?,?)");
			stmt.setString(1, tk.getTenDangNhap());
			stmt.setString(2, tk.getMatKhau());
			n= stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * cap nhat tai khoan
	 */
	public boolean capNhatTK(TaiKhoan tk) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n =0;
		try {
			PreparedStatement stmt = con.prepareStatement("update TaiKhoan set matKhau = ? where tenDangNhap = ?");
			stmt.setString(1, tk.getMatKhau());
			stmt.setString(2, tk.getTenDangNhap());
			n=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * lay danh sach taikhoan
	 */
	public ArrayList<TaiKhoan> getDSTaiKhoan(){
		Connection con = Database.getInstance().getConnection();
		dstk = new ArrayList<TaiKhoan>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from TaiKhoan");
			while (rs.next()) {
				TaiKhoan taiKhoan = new TaiKhoan(rs.getString("tenDangNhap"), rs.getString("matKhau"));
				dstk.add(taiKhoan);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dstk;
	}
	/*
	 * xoa tai khoan
	 */
	public boolean xoaTaiKhoan(String ten){
		Connection con = Database.getInstance().getConnection();
		int n=0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from TaiKhoan where tenDangNhap = ?");
			stmt.setString(1, ten);
			n =  stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
}
