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

public class SanPhamDao {
	Database conDB = new Database();
	private ArrayList<SanPham> dssp;
	private ArrayList<HoaDon> dshd;

	public SanPhamDao() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * getTien sna pham
	 */
	public double getTienSP(String maSp) throws SQLException {
		double tienSanPham = 0;
		Connection con = Database.getInstance().getConnection();
		String sql = " select donGia from SanPham where maSanPham like ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maSp);
		ResultSet rs =stmt.executeQuery();
		if(rs.next())
			tienSanPham = Double.parseDouble(rs.getString(1));
		return tienSanPham;
	}
	/*
	 * get so luong sna pham
	 */
	public double getSoLuongSp(String maSp) throws SQLException {
		int soLuong = 0;
		Connection con = Database.getInstance().getConnection();
		String sql = " select soLuong from SanPham sp join ChiTietSanPham ctsp on sp.maSanPham = ctsp.maSanPham where ctsp.maSanPham like ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maSp);
		ResultSet rs =stmt.executeQuery();
		if(rs.next())
			soLuong = Integer.parseInt(rs.getString(1));
		return soLuong;
	}
	
	/*
	 * Them san pham vao hao don
	 */
	
	public void themSpVaoHD(String ma, int soLuong) {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("update ChiTietSanPham set soLuong = ? where maSanPham = ?");
			stmt.setInt(1, soLuong);
			stmt.setString(2, ma);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * danh sach san pham
	 */
	public ArrayList<SanPham> getAllSanPham(){
		dssp = new ArrayList<SanPham>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from SanPham");
			while(rs.next()) {
				String maSanPham = rs.getString(1);
				String tenSanPham = rs.getString(2);
				String loaiSanPham = rs.getString(3);
				double donGia = rs.getDouble(4);
				SanPham sp = new SanPham(maSanPham, tenSanPham,loaiSanPham,donGia);
				dssp.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dssp;
	}
	/*
	 * Tìm san pham theo tên
	 */
	public SanPham timSanPhamTheoTen(String tenSanPham) {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from SanPham where tenSanPham like ?");
			stmt.setString(1, tenSanPham);
			ResultSet rs = stmt.executeQuery();
			SanPham spTim = new SanPham();
			if(rs.next()) {
				spTim.setMaSanPham(rs.getString("maSanPham"));
				spTim.setTenSanPham(rs.getString("tenSanPham"));
				spTim.setLoaiSanPham(rs.getString("loaiSanPham"));
				spTim.setDonGia(rs.getDouble("donGia"));
				return spTim;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Tìm san pham theo ma 
	 */
	public SanPham timSanPhamTheoMa(String maSanPham){
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from SanPham where maSanPham like ?");
			stmt.setString(1, maSanPham);
			ResultSet rs = stmt.executeQuery();
			SanPham spTim = new SanPham();
			if(rs.next()) {
				spTim.setMaSanPham(rs.getString("maSanPham"));
				spTim.setTenSanPham(rs.getString("tenSanPham"));
				spTim.setLoaiSanPham(rs.getString("loaiSanPham"));
				spTim.setDonGia(rs.getDouble("donGia"));
				return spTim;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Tìm san pham theo loai
	 */
	public SanPham timSanPhamTheoNuoc(String nuoc){
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from SanPham where loaiSanPham like ?");
			stmt.setString(1, nuoc);
			ResultSet rs = stmt.executeQuery();
			SanPham spTim = new SanPham();
			if(rs.next()) {
				spTim.setMaSanPham(rs.getString("maSanPham"));
				spTim.setTenSanPham(rs.getString("tenSanPham"));
				spTim.setLoaiSanPham(rs.getString("loaiSanPham"));
				spTim.setDonGia(rs.getDouble("donGia"));
				return spTim;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<SanPham> timSanPhamTheoLoaiSanPham(String loaiSanPham){
		
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from SanPham where loaiSanPham like ?");
			stmt.setString(1, loaiSanPham);
			ResultSet rs = stmt.executeQuery();
			SanPham spTim = new SanPham();
			ArrayList<SanPham> dsspTim = new ArrayList<SanPham>();
			while(rs.next()) {
				spTim.setMaSanPham(rs.getString("maSanPham"));
				spTim.setTenSanPham(rs.getString("tenSanPham"));
				spTim.setLoaiSanPham(rs.getString("loaiSanPham"));
				spTim.setDonGia(rs.getDouble("donGia"));
				dsspTim.add(spTim);
			}
			return dsspTim;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
public ArrayList<SanPham> timSanPhamTheoDonGiaNhoNhat(double donGia){
		
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from SanPham where donGia > ?");
			stmt.setDouble(1, donGia);
			ResultSet rs = stmt.executeQuery();
			SanPham spTim = new SanPham();
			ArrayList<SanPham> dsspTim = new ArrayList<SanPham>();
			while(rs.next()) {
				spTim.setMaSanPham(rs.getString("maSanPham"));
				spTim.setTenSanPham(rs.getString("tenSanPham"));
				spTim.setLoaiSanPham(rs.getString("loaiSanPham"));
				spTim.setDonGia(rs.getDouble("donGia"));
				dsspTim.add(spTim);
			}
			return dsspTim;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
public ArrayList<SanPham> timSanPhamTheoDonGiaLonNhat(double donGia){
	
	
	Connection con = Database.getInstance().getConnection();
	try {
		PreparedStatement stmt = con.prepareStatement("Select * from SanPham where donGia < ?");
		stmt.setDouble(1, donGia);
		ResultSet rs = stmt.executeQuery();
		SanPham spTim = new SanPham();
		ArrayList<SanPham> dsspTim = new ArrayList<SanPham>();
		while(rs.next()) {
			spTim.setMaSanPham(rs.getString("maSanPham"));
			spTim.setTenSanPham(rs.getString("tenSanPham"));
			spTim.setLoaiSanPham(rs.getString("loaiSanPham"));
			spTim.setDonGia(rs.getDouble("donGia"));
			dsspTim.add(spTim);
		}
		return dsspTim;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return null;
}
	/*
	 * Tao san pham
	 */
	public boolean themSanPham(SanPham sp) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n =0;
		try {
			PreparedStatement stmt = con.prepareStatement("Insert into SanPham values(?,?,?,?)");
			stmt.setString(1, sp.getMaSanPham());
			stmt.setString(2, sp.getTenSanPham());
			stmt.setString(3, sp.getLoaiSanPham());
			stmt.setDouble(4, sp.getDonGia());
			n= stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * Cap nhat san pham
	 */
	public boolean capNhatSP(SanPham sp) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n =0;
		try {
			PreparedStatement stmt = con.prepareStatement("update SanPham set tenSanPham = ? , loaiSanPham = ? , donGia = ? where maSanPham = ?");
			stmt.setString(1, sp.getTenSanPham());
			stmt.setString(2,sp.getLoaiSanPham());
			stmt.setDouble(3, sp.getDonGia());
			n=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * Xoa san pham
	 */
	public boolean xoaSP(String ma) {
		Connection con = Database.getInstance().getConnection();
		int n=0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from SanPham where maSanPham = ?");
			stmt.setString(1, ma);
			n =  stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
}
