package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import connect.Database;
import entity.KhachHang;
import entity.PhieuDatPhongTruoc;
import entity.Phong;

public class PhieuDatPhongTruocDao {
	private ArrayList<PhieuDatPhongTruoc> pdpt;
	
	/*
	 * in danh sách phieu dat phòng
	 */
	public ArrayList<PhieuDatPhongTruoc> getDSCTP(){
		pdpt = new ArrayList<PhieuDatPhongTruoc>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from PhieuDatPhongTruoc");
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String maKhachHang = rs.getString(2);
				Timestamp ngayLapPhieu = rs.getTimestamp(3);
				Timestamp ngayDatPhong = rs.getTimestamp(4);
				PhieuDatPhongTruoc p = new PhieuDatPhongTruoc(new Phong(maPhong),new KhachHang(maKhachHang),ngayLapPhieu,ngayDatPhong);
				pdpt.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pdpt;
	}
	/*
	 * thêm phiếu lập
	 */
	public boolean themPhieu(PhieuDatPhongTruoc p){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " insert into PhieuDatPhongTruoc values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getPhong().getMaPhong());
			stmt.setString(2, p.getKhachHang().getMaKhachHang());
			stmt.setTimestamp(3, p.getThoiGianLapPhieu());
			stmt.setTimestamp(4, p.getThoiGianDatPhong());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}/*
	 * xoa chi tiet phong theo ma khach hang, ma phong
	 */
	public boolean xoaCTHDMaP(String maPhong, String maKhachHang,String tg) {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from PhieuDatPhongTruoc where maPhong = ? and maKhachHang = ? and thoiGianLapPhieu = ?");
			stmt.setString(1, maPhong);
			stmt.setString(2, maKhachHang);
			stmt.setString(3, tg);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	 * in danh sách phiếu theo mã phòng, mã kh
	 */
	public ArrayList<PhieuDatPhongTruoc> getDSTheoMaHD(String maP, String maKH){
		pdpt = new ArrayList<PhieuDatPhongTruoc>();
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt;
		try {

			stmt = con.prepareStatement("Select * from PhieuDatPhongTruoc where maPhong like ? and maKhachHang like ?");
			stmt.setString(1, maP);
			stmt.setString(1, maKH);
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				String maPhong = rs.getString(1);
				String maKhachHang = rs.getString(2);
				Timestamp ngayLapPhieu = rs.getTimestamp(3);
				Timestamp ngayDatPhong = rs.getTimestamp(4);
				PhieuDatPhongTruoc p = new PhieuDatPhongTruoc(new Phong(maPhong),new KhachHang(maKhachHang),ngayLapPhieu,ngayDatPhong);
				pdpt.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pdpt;
	}
	
	public boolean suaPhieu(String map, Timestamp tgd,String makh, String tgl){
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " update PhieuDatPhongTruoc set maPhong = ? , thoiGianDatPhong = ? where maKhachHang = ? and thoiGianLapPhieu = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, map);
			stmt.setString(3, makh);
			stmt.setString(4, tgl);
			stmt.setTimestamp(2, tgd);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	
	
}
