package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.Phong;

public class PhongDao {
	private ArrayList<Phong> dsphong;
	/*
	 * get tien phong
	 */
	public double getTienPhong(String maPhong) throws SQLException {
		double tienPhong = 0;
		Connection con = Database.getInstance().getConnection();
		String sql = " select donGia from Phong p join LoaiPhong lp on p.maLoaiPhong = lp.maLoaiPhong where maPhong like ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maPhong);
		ResultSet rs =stmt.executeQuery();
		if(rs.next())
			tienPhong = Double.parseDouble(rs.getString(1));
		return tienPhong;
	}
	/*
	 * Cap nhat danh sach phong trong
	 */
	public boolean capNhatPhongTrong(String maPhong, String trangThai) {
		Connection con = Database.getInstance().getConnection();
		int n =0;
		String sql = "UPDATE Phong" + " set trangThai = ?" + " where maPhong = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, trangThai);
			stmt.setString(2, maPhong);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n>0;
		
	}
	/*
	 * Tìm phòng theo trang thai trong
	 */
	public ArrayList<Phong> timPhongTrong(){
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from Phong where trangThai like 'trống' ";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
//			stmt.setString(1, trong);
			ResultSet rs = stmt.executeQuery();
			Phong pTim = new Phong();
			ArrayList<Phong> dsphongTim = new ArrayList<Phong>();
			while (rs.next()) {
				pTim.setMaPhong(rs.getString("maPhong"));
				pTim.setLoaiPhong(new LoaiPhong(rs.getString("maLoaiPhong")));
				pTim.setTenPhong(rs.getString("tenPhong"));
				pTim.setTrangThai(rs.getString("trangThai"));
				pTim.setLau(rs.getInt("lau"));
				dsphongTim.add(pTim);
			}
			return dsphongTim;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * Tìm phòng theo lầu
	 */
	public ArrayList<Phong> timPhongTheoLau(int lau){
		dsphong = new ArrayList<Phong>();
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from Phong where lau = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, lau);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String ma = rs.getString("maPhong");
				String ten = rs.getString("tenPhong");
				int l = rs.getInt("lau");
				String trangThai = rs.getString("trangThai");
				Phong p = new Phong(ma, new LoaiPhong(rs.getString("maLoaiPhong")), ten, trangThai, l);
				dsphong.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsphong;
	}
	/*
	 * Tìm phòng theo ten phong
	 */
	public Phong timPhongTheoTen(String ten){
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from Phong where tenPhong like ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ten);
			ResultSet rs = stmt.executeQuery();
			Phong pTim = new Phong();
			if (rs.next()) {
				pTim.setMaPhong(rs.getString("maPhong"));
				pTim.setLoaiPhong(new LoaiPhong(rs.getString("maLoaiPhong")));
				pTim.setTenPhong(rs.getString("tenPhong"));
				pTim.setTrangThai(rs.getString("trangThai"));
				pTim.setLau(rs.getInt("lau"));
				return pTim;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * Tìm phòng theo trạng thái
	 */
	public Phong timPhongTheoMLP(String mlp){
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from Phong where maLoaiPhong like ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mlp);
			ResultSet rs = stmt.executeQuery();
			Phong pTim = new Phong();
			if (rs.next()) {
				pTim.setMaPhong(rs.getString("maPhong"));
				pTim.setLoaiPhong(new LoaiPhong(rs.getString("maLoaiPhong")));
				pTim.setTenPhong(rs.getString("tenPhong"));
				pTim.setTrangThai(rs.getString("trangThai"));
				pTim.setLau(rs.getInt("lau"));
				return pTim;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * tìm phòng theo mã
	 */
	public Phong timPhongTheoMa(String ma) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		String sql = "Select * from Phong where maPhong like ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ma);
		ResultSet rs = stmt.executeQuery();
		Phong pTim = new Phong();
		if (rs.next()) {
			pTim.setMaPhong(rs.getString("maPhong"));
			pTim.setLoaiPhong(new LoaiPhong(rs.getString("maLoaiPhong")));
			pTim.setTenPhong(rs.getString("tenPhong"));
			pTim.setTrangThai(rs.getString("trangThai"));
			pTim.setLau(rs.getInt("lau"));
			return pTim;
		}
		return null;
	}
	/*
	 * lấy danh sách phòng trống
	 */
	public ArrayList<Phong> getDSPhongTheoTrangThai(){
		dsphong = new ArrayList<Phong>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Phong where trangThai like 'T%'");
			while (rs.next()) {
				String ma = rs.getString("maPhong");
				String ten = rs.getString("tenPhong");
				int lau = rs.getInt("lau");
				String trangThai = rs.getString("trangThai");
				Phong p = new Phong(ma, new LoaiPhong(rs.getString("maLoaiPhong")), ten, trangThai, lau);
				dsphong.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsphong;
	}
	public ArrayList<Phong> timDSPhongTrong(){
		dsphong = new ArrayList<Phong>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Phong where trangThai like 'T%'");
			while (rs.next()) {
				String ma = rs.getString("maPhong");
				String ten = rs.getString("tenPhong");
				int lau = rs.getInt("lau");
				String trangThai = rs.getString("trangThai");
				Phong p = new Phong(ma, new LoaiPhong(rs.getString("maLoaiPhong")), ten, trangThai, lau);
				dsphong.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsphong;
	}
	public ArrayList<Phong> timDSPhongDangHoatDong(){
		dsphong = new ArrayList<Phong>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Phong where trangThai like 'Đ%'");
			while (rs.next()) {
				String ma = rs.getString("maPhong");
				String ten = rs.getString("tenPhong");
				int lau = rs.getInt("lau");
				String trangThai = rs.getString("trangThai");
				Phong p = new Phong(ma, new LoaiPhong(rs.getString("maLoaiPhong")), ten, trangThai, lau);
				dsphong.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsphong;
	}
	/*
	 * thêm phòng
	 */
	public boolean themPhong(Phong p)
			throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " Insert into Phong values(?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMaPhong());
			stmt.setString(2, p.getLoaiPhong().getMaLoaiPhong());
			stmt.setString(3, p.getTenPhong());
			stmt.setString(4, p.getTrangThai());
			stmt.setInt(5, p.getLau());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * Cập nhật phòng
	 */
	public boolean capNhatPhong(Phong p) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement(
					"update Phong set maLoaiPhong = ?, tenPhong = ? , trangThai = ? , lau = ? where maPhong = ?");
			stmt.setString(1, p.getLoaiPhong().getMaLoaiPhong());
			stmt.setString(2, p.getTenPhong());
			stmt.setString(3, p.getTrangThai());
			stmt.setInt(4, p.getLau());
			stmt.setString(5, p.getMaPhong());
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
	public boolean xoaPhong(String ma) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from Phong where maPhong = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
/*
 * lay danh sach phong
 */
	public ArrayList<Phong> getAllDSPhong() {
		dsphong = new ArrayList<Phong>();
		Connection con = null;
		try {
			con = Database.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Phong");
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String maLoaiPhong =rs.getString(2);
				String ten = rs.getString(3);
				String trangThai = rs.getString(4);
				int lau = rs.getInt(5);
				Phong p = new Phong(maPhong, new LoaiPhong(maLoaiPhong), ten, trangThai, lau);
				dsphong.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsphong;
	}
	
	
public ArrayList<Phong> timPhongTheoTuKhoa(String tuKhoa){
		
		dsphong = new ArrayList<Phong>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from Phong where maPhong like ?  or maLoaiPhong like ? or tenPhong like ? or trangThai = ?");
			
			stmt.setString(1,"%" + tuKhoa + "%");
			stmt.setString(2,"%" + tuKhoa + "%");
			stmt.setString(3,"%" + tuKhoa + "%");
			stmt.setString(4,"%" + tuKhoa + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String maLoaiPhong =rs.getString(2);
				String ten = rs.getString(3);
				String trangThai = rs.getString(4);
				int lau = rs.getInt(5);
				Phong p = new Phong(maPhong, new LoaiPhong(maLoaiPhong), ten, trangThai, lau);
				dsphong.add(p);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsphong;
	}
	
}
