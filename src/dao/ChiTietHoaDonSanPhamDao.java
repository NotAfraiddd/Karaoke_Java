package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import connect.Database;
import entity.ChiTietHoaDonPhong;
import entity.ChiTietHoaDonSanPham;
import entity.HoaDon;
import entity.LoaiPhong;
import entity.Phong;
import entity.SanPham;

public class ChiTietHoaDonSanPhamDao {
	private ArrayList<ChiTietHoaDonSanPham> ctsp;
	
	
	/*
	 * thêm chi tiết hóa đơn sản phẩm
	 */
	public boolean themCTHD(String maHoaDon, String maSanPham, int soLuong) throws SQLException{
		Connection con = Database.getInstance().getConnection();
		String sql = " insert into ChiTietSanPham values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maHoaDon);
		stmt.setString(2, maSanPham);		
		stmt.setInt(3,soLuong);
		stmt.executeUpdate();
		
		
		return true;
	}
	
	/*
	 * in danh sách chi tiết hóa đơn sản phẩm
	 */
	public ArrayList<ChiTietHoaDonSanPham> getAllCTSP(){
		ctsp = new ArrayList<ChiTietHoaDonSanPham>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from ChiTietSanPham");
			while(rs.next()) {
				String maHoaDon = rs.getString(1);
				String maSanPham = rs.getString(2);
				int soLuong = rs.getInt(3);
				ChiTietHoaDonSanPham p = new ChiTietHoaDonSanPham(new HoaDon(maHoaDon),new SanPham(maSanPham),  soLuong);
				ctsp.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctsp;
	}
	
	
	
	public ArrayList<ChiTietHoaDonSanPham> getDSTheoMaHD(String ma){
		ctsp = new ArrayList<ChiTietHoaDonSanPham>();
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt;
		try {

			stmt = con.prepareStatement("Select * from ChiTietSanPham where maHoaDon like ?");
			stmt.setString(1, ma);
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString(1);
				String maSP = rs.getString(2);
				int soluong= Integer.parseInt(rs.getString(3));
				ChiTietHoaDonSanPham p = new ChiTietHoaDonSanPham(new HoaDon(maHD), new SanPham(maSP), soluong);
				ctsp.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctsp;
	}
	
	public boolean xoaCTSP(String maHD, String maSP) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from ChiTietSanPham where maHoaDon = ? and maSanPham = ?");
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatSoLuong(String maHD,String maSP,int soluong){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " update ChiTietSanPham set soluong=? where maHoaDon = ? and maSanPham = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,soluong);
			stmt.setString(2,maHD);
			stmt.setString(3,maSP);
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
}
