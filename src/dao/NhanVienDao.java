package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.NhanVien;
import connect.Database;

public class NhanVienDao {
	Database conDB = new Database();
	private ArrayList<NhanVien> dsnv;

	public NhanVienDao() {
		// TODO Auto-generated constructor stub
	}
	public String getTenNhanVien(String ma) throws SQLException {
		String tenNhanVien = null;
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "Select tenNhanVien from NhanVien where maNhanVien like ?";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, ma);
		rs = stmt.executeQuery();
		if(rs.next())
			tenNhanVien = rs.getString(1);
		return tenNhanVien;
	}
	public ArrayList<NhanVien> getAllNhanVien() throws ClassNotFoundException, SQLException {
		dsnv = new ArrayList<NhanVien>();
		Connection con = Database.getInstance().getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("Select * from NhanVien");
			while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);				
				String diaChi = rs.getString(3);
				String soDT = rs.getString(4);
				String chucVu = rs.getString(5);
				String gioiTinh = rs.getString(6);
				double mucLuong = rs.getDouble(7);
				NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDT, chucVu, gioiTinh, mucLuong);
				dsnv.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}

	/*
	 * Tìm nhân viên theo tên
	 */
	public NhanVien timNhanVienTheoTen(String tenNhanVien) {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where tenNhanVien like ?");
			stmt.setString(1, tenNhanVien);
			ResultSet rs = stmt.executeQuery();
			NhanVien timNV = new NhanVien();
			if (rs.next()) {
				timNV.setMaNhanVien(rs.getString(1));
				timNV.setTenNhanVien(rs.getString(2));
				timNV.setDiaChi(rs.getString(3));
				timNV.setSoDT(rs.getString(4));
				timNV.setChucVu(rs.getString(5));
				timNV.setGioiTinh(rs.getString(6));
				timNV.setMucLuong(rs.getDouble(7));
				return timNV;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<NhanVien> timNhanVienTheoMucLuongNhoNhat(double ml) {
		dsnv = new ArrayList<NhanVien>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where mucLuong > ?");
			stmt.setDouble(1, ml);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maNhanVien = rs.getString("maNhanVien");
				String tenNhanVien = rs.getString("tenNhanVien");
				String diaChi = rs.getString("diaChi");
				String soDT = rs.getString("soDT");
				String chucVu = rs.getString("chucVu");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				double mucLuong = rs.getDouble("mucLuong");
				String gt = "Nữ";
				if(gioiTinh) {
					gt = "Nam";
				}
				NhanVien timNV = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDT, chucVu, gt, mucLuong);
				dsnv.add(timNV);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
	public ArrayList<NhanVien> timNhanVienTheoMucLuongLonNhat(double ml) {
		dsnv = new ArrayList<NhanVien>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where mucLuong < ?");
			stmt.setDouble(1, ml);
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				String maNhanVien = rs.getString("maNhanVien");
				String tenNhanVien = rs.getString("tenNhanVien");
				String diaChi = rs.getString("diaChi");
				String soDT = rs.getString("soDT");
				String chucVu = rs.getString("chucVu");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				double mucLuong = rs.getDouble("mucLuong");
				String gt = "Nữ";
				if(gioiTinh) {
					gt = "Nam";
				}
				NhanVien timNV = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDT, chucVu, gt, mucLuong);
				dsnv.add(timNV);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
	public ArrayList<NhanVien> timNhanVienCoGioiTinhNam() {
		dsnv = new ArrayList<NhanVien>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where gioiTinh = 1");
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				String maNhanVien = rs.getString("maNhanVien");
				String tenNhanVien = rs.getString("tenNhanVien");
				String diaChi = rs.getString("diaChi");
				String soDT = rs.getString("soDT");
				String chucVu = rs.getString("chucVu");
				boolean gioiTinh = true;
				double mucLuong = rs.getDouble("mucLuong");
//				String gt = "Nữ";
//				if(gioiTinh) {
//					gt = "Nam";
//				}
				NhanVien timNV = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDT, chucVu, "Nam", mucLuong);
				dsnv.add(timNV);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
	public ArrayList<NhanVien> timNhanVienCoGioiTinhNu() {
		dsnv = new ArrayList<NhanVien>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where gioiTinh = 0");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maNhanVien = rs.getString("maNhanVien");
				String tenNhanVien = rs.getString("tenNhanVien");
				String diaChi = rs.getString("diaChi");
				String soDT = rs.getString("soDT");
				String chucVu = rs.getString("chucVu");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				double mucLuong = rs.getDouble("mucLuong");
				String gt = "Nữ";
				if(gioiTinh) {
					gt = "Nam";
				}
				NhanVien timNV = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDT, chucVu, gt, mucLuong);
				dsnv.add(timNV);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
	/*
	 * Tìm nhân viên theo ma
	 */
	public NhanVien timNhanVienTheoMa(String maNhanVien) {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where maNhanVien like ?");
			stmt.setString(1, maNhanVien);
			ResultSet rs = stmt.executeQuery();
			NhanVien timNV = new NhanVien();
			if (rs.next()) {
				timNV.setMaNhanVien(rs.getString(1));
				timNV.setTenNhanVien(rs.getString(2));
				timNV.setDiaChi(rs.getString(3));
				timNV.setSoDT(rs.getString(4));
				timNV.setChucVu(rs.getString(5));
				timNV.setGioiTinh(rs.getString(6));
				timNV.setMucLuong(rs.getDouble(7));
				return timNV;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Tìm nhân viên theo sdt
	 */
	public NhanVien timNhanVienTheoSDT(String sdt) {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where soDT like ?");
			stmt.setString(1, sdt);
			ResultSet rs = stmt.executeQuery();
			NhanVien timNV = new NhanVien();
			if (rs.next()) {
				timNV.setMaNhanVien(rs.getString("maNhanVien"));
				timNV.setTenNhanVien(rs.getString("tenNhanVien"));
				timNV.setDiaChi(rs.getString("diaChi"));
				timNV.setSoDT(rs.getString("soDT"));
				timNV.setChucVu(rs.getString("chucVu"));
				timNV.setGioiTinh(rs.getString("gioiTinh"));
				timNV.setMucLuong(rs.getDouble("mucLuong"));
				return timNV;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Tìm nhân viên theo dia chi
	 */
	public NhanVien timNhanVienTheoDiaChi(String dc) {
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where diaChi like ?");
			stmt.setString(1, dc);
			ResultSet rs = stmt.executeQuery();
			NhanVien timNV = new NhanVien();
			if (rs.next()) {
				timNV.setMaNhanVien(rs.getString(1));
				timNV.setTenNhanVien(rs.getString(2));
				timNV.setDiaChi(rs.getString(3));
				timNV.setSoDT(rs.getString(4));
				timNV.setChucVu(rs.getString(5));
				timNV.setGioiTinh(rs.getString(6));
				timNV.setMucLuong(rs.getDouble(7));
				return timNV;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Tìm nhân viên theo chuc vu
	 */
	public ArrayList<NhanVien> timNhanVienTheoChucVu(String chucvu) {
		dsnv = new ArrayList<NhanVien>();
		Connection con = Database.getInstance().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from NhanVien where chucVu like ?");
			stmt.setString(1, chucvu);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maNhanVien = rs.getString("maNhanVien");
				String tenNhanVien = rs.getString("tenNhanVien");
				String diaChi = rs.getString("diaChi");
				String soDT = rs.getString("soDT");
				String chucVu = rs.getString("chucVu");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				double mucLuong = rs.getDouble("mucLuong");
				String gt = "Nữ";
				if(gioiTinh) {
					gt = "Nam";
				}
				NhanVien timNV = new NhanVien(maNhanVien, tenNhanVien, diaChi, soDT, chucVu, gt, mucLuong);
				dsnv.add(timNV);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
	/*
	 * tạo nhân viên
	 */
	public boolean themNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("Insert into NhanVien values(?,?,?,?,?,?,?)");
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getTenNhanVien());
			stmt.setString(3, nv.getDiaChi());
			stmt.setString(4, nv.getSoDT());
			stmt.setString(5, nv.getChucVu());
			stmt.setString(6, nv.getGioiTinh());
			stmt.setDouble(7, nv.getMucLuong());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * Cap nhat nhan vien
	 */
	public boolean capNhatNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement(
					"update NhanVien set tenNhanVien = ?, diaChi = ? , soDT = ? , chucVu = ?,  gioiTinh = ? ,"
							+ " mucLuong = ? where maNhanVien = ?");
			stmt.setString(1, nv.getTenNhanVien());
			stmt.setString(2, nv.getDiaChi());
			stmt.setString(3, nv.getSoDT());
			stmt.setString(4, nv.getChucVu());
			stmt.setString(5, nv.getGioiTinh());
			stmt.setDouble(6, nv.getMucLuong());
			stmt.setString(7, nv.getMaNhanVien());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * Xoa nhan vien
	 */
	public boolean xoaNhanVien(String ma) throws ClassNotFoundException, SQLException {
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("delete from NhanVien where maNhanVien = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
}
