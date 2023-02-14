package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.LoaiPhong;
import entity.Phong;

public class LoaiPhongDao {
	private ArrayList<LoaiPhong> dslp;
	
	/*
	 * thêm loai phòng
	 */
	public boolean themLoaiPhong(LoaiPhong p) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " insert into LoaiPhong values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMaLoaiPhong());
			stmt.setString(2, p.getTenLoaiPhong());
			stmt.setInt(3, p.getSoCho());
			stmt.setDouble(4, p.getDonGia());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * Cập nhật phòng
	 */
	public boolean capNhatLoaiPhong(LoaiPhong p) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement(
					"update LoaiPhong set tenLoaiPhong = ? , soCho = ? , donGia = ? where maLoaiPhong = ?");
			stmt.setString(1, p.getTenLoaiPhong());
			stmt.setInt(2, p.getSoCho());
			stmt.setDouble(3, p.getDonGia());
			stmt.setString(4, p.getMaLoaiPhong());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * Xóa phòng
	 */
	public boolean xoaLoaiPhong(String ma) {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from LoaiPhong where maLoaiPhong = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * In danh sách loại phòng
	 */

	public ArrayList<LoaiPhong> getAllDSLoaiPhong() {
		Connection con = Database.getInstance().getConnection();
		dslp = new ArrayList<LoaiPhong>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from LoaiPhong");
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				int soCho = rs.getInt(3);
				double donGia = rs.getDouble(4);
				LoaiPhong p = new LoaiPhong(ma, ten, soCho, donGia);
				dslp.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dslp;
	}

	/*
	 * tìm loại phòng theo mã
	 */
	public LoaiPhong timLoaiPhongTheoMa(String ma) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from LoaiPhong where maLoaiPhong like ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ma);
		ResultSet rs = stmt.executeQuery();
		LoaiPhong lpTim = new LoaiPhong();
		if (rs.next()) {
			lpTim.setMaLoaiPhong(rs.getString("maLoaiPhong"));
			lpTim.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
			lpTim.setSoCho(rs.getInt("soCho"));
			lpTim.setDonGia(rs.getDouble("donGia"));
			return lpTim;
		}
		return null;
	}

	public ArrayList<LoaiPhong> timLoaiPhongTheoSoCho(int soCho) throws ClassNotFoundException, SQLException {
		dslp = new ArrayList<LoaiPhong>();
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from LoaiPhong where soCho = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, soCho);
		ResultSet rs = stmt.executeQuery();
		LoaiPhong lpTim = new LoaiPhong();
		while (rs.next()) {
			String maLoaiPhong = rs.getString("maLoaiPhong");
			String tenLoaiPhong = rs.getString("tenLoaiPhong");
			int sc = rs.getInt("soCho");
			double donGia = rs.getDouble("donGia");
			LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, sc, donGia);
			dslp.add(lp);
		}
		return dslp;
	}

	public ArrayList<LoaiPhong> timLoaiPhongTheoDonGiaNhoNhat(double donGia)
			throws ClassNotFoundException, SQLException {
		dslp = new ArrayList<LoaiPhong>();
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from LoaiPhong where donGia >= ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setDouble(1, donGia);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String maLoaiPhong = rs.getString("maLoaiPhong");
			String tenLoaiPhong = rs.getString("tenLoaiPhong");
			int soCho = rs.getInt("soCho");
			double dg = rs.getDouble("donGia");
			LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, soCho, dg);
			dslp.add(lp);
		}
		return dslp;
	}

	public ArrayList<LoaiPhong> timLoaiPhongTheoDonGiaLonNhat(double donGia)
			throws ClassNotFoundException, SQLException {
		dslp = new ArrayList<LoaiPhong>();
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from LoaiPhong where donGia <= ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setDouble(1, donGia);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String maLoaiPhong = rs.getString("maLoaiPhong");
			String tenLoaiPhong = rs.getString("tenLoaiPhong");
			int soCho = rs.getInt("soCho");
			double dg = rs.getDouble("donGia");
			LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, soCho, dg);
			dslp.add(lp);
		}
		return dslp;
	}

	public ArrayList<LoaiPhong> timLoaiPhongTheoTen(String tenLoaiPhong) throws ClassNotFoundException, SQLException {
		dslp = new ArrayList<LoaiPhong>();
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from LoaiPhong where tenLoaiPhong like ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, tenLoaiPhong);
		ResultSet rs = stmt.executeQuery();
		LoaiPhong lpTim = new LoaiPhong();
		while (rs.next()) {
			String maLoaiPhong = rs.getString("maLoaiPhong");
			String tlp = rs.getString("tenLoaiPhong");
			int soCho = rs.getInt("soCho");
			double donGia = rs.getDouble("donGia");
			LoaiPhong lp = new LoaiPhong(maLoaiPhong, tlp, soCho, donGia);
			dslp.add(lp);
		}
		return dslp;
	}
	/*
	 * tìm loại phòng theo ten loai phong
	 */
//	public LoaiPhong timLoaiPhongTheoTen(String ten) throws ClassNotFoundException, SQLException {
//		Connection con = Database.getInstance().getConnection();
//		String sql = "Select * from LoaiPhong where tenLoaiPhong like ?";
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setString(1, ten);
//		ResultSet rs = stmt.executeQuery();
//		LoaiPhong lpTim = new LoaiPhong();
//		if (rs.next()) {
//			lpTim.setMaLoaiPhong(rs.getString("maLoaiPhong"));
//			lpTim.setTenLoaiPhong(rs.getString("tenLoaiPhong"));
//			lpTim.setSoCho(rs.getInt("soCho"));
//			lpTim.setDonGia(rs.getDouble("donGia"));
//			return lpTim;
//		}
//		return null;
//	}
}
