package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import connect.Database;
import entity.ChiTietHoaDonPhong;
import entity.HoaDon;
import entity.LoaiPhong;
import entity.Phong;

public class ChiTietHoaDonPhongDao {
	private ArrayList<ChiTietHoaDonPhong> cthd;
	/*
	 * thêm chi tiết hóa đơn
	 */
	public boolean themCTHD(ChiTietHoaDonPhong p){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " insert into ChiTietHoaDon values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getHoaDon().getMaHoaDon());
			stmt.setString(2, p.getPhong().getMaPhong());
			stmt.setTimestamp(3, p.getNgayBatDau());
			stmt.setTimestamp(4, p.getNgayKetThuc());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * cap nhat gio ket thuc
	 */
	public boolean capNhatGioKetThuc(String maHoaDon, String maPhong, Timestamp ngayKetThuc){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " update ChiTietHoaDon set ngayKetThuc = ? where maHoaDon = ? and maPhong = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setTimestamp(1, ngayKetThuc);
			stmt.setString(2,maHoaDon );
			stmt.setString(3,maPhong);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * xoa chi tiet phong, THANH TOAN THI SE DUNG CAI NAY
	 */
	public boolean xoaCTHD(String ma) {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from ChiTietHoaDon where maHoaDon = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * xoa chi tiet phong theo ma phong
	 */
	public boolean xoaCTHDMaP(String maHD, String maP) {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from ChiTietHoaDon where maHoaDon = ? and maPhong = ? ");
			stmt.setString(1, maHD);
			stmt.setString(2, maP);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * in danh sách chi tiết hóa đơn phòng
	 */
	public ArrayList<ChiTietHoaDonPhong> getDSCTP(){
		cthd = new ArrayList<ChiTietHoaDonPhong>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from ChiTietHoaDon");
			while(rs.next()) {
				String maHD = rs.getString(1);
				String maLoaiPhong = rs.getString(2);
				Timestamp ngayBatDau = rs.getTimestamp(3);
				Timestamp ngayKetThuc = rs.getTimestamp(4);
				ChiTietHoaDonPhong p = new ChiTietHoaDonPhong(new HoaDon(maHD), new Phong(maLoaiPhong),ngayBatDau,ngayKetThuc );
				cthd.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cthd;
	}
	/*
	 * in danh sách chi tiết hóa đơn phòng theo ma
	 */
	public ArrayList<ChiTietHoaDonPhong> getDSTheoMaHD(String ma){
		cthd = new ArrayList<ChiTietHoaDonPhong>();
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt;
		try {

			stmt = con.prepareStatement("Select * from ChiTietHoaDon where maHoaDon like ?");
			stmt.setString(1, ma);
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString(1);
				String maLoaiPhong = rs.getString(2);
				Timestamp ngayBatDau = rs.getTimestamp(3);
				Timestamp ngayKetThuc = rs.getTimestamp(4);
				ChiTietHoaDonPhong p = new ChiTietHoaDonPhong(new HoaDon(maHD), new Phong(maLoaiPhong),ngayBatDau,ngayKetThuc );
				cthd.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cthd;
	}
	/*
	 * in danh sách chi tiết hóa đơn phòng theo maHD, va maPhong
	 */
	public ArrayList<ChiTietHoaDonPhong> getDSTheoMHDvaMP(String ma, String maP){
		cthd = new ArrayList<ChiTietHoaDonPhong>();
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt;
		try {

			stmt = con.prepareStatement("Select * from ChiTietHoaDon where maHoaDon like ? and maPhong like ?");
			stmt.setString(1, ma);
			stmt.setString(2, maP);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				String maHD = rs.getString(1);
				String maLoaiPhong = rs.getString(2);
				Timestamp ngayBatDau = rs.getTimestamp(3);
				Timestamp ngayKetThuc = rs.getTimestamp(4);
				ChiTietHoaDonPhong p = new ChiTietHoaDonPhong(new HoaDon(maHD), new Phong(maLoaiPhong),ngayBatDau,ngayKetThuc );
				cthd.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cthd;
	}
}
