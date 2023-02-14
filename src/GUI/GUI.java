package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.CapNhat.*;
import GUI.DanhSach.*;
import GUI.TimKiem.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class GUI extends JFrame {

	private JPanel contentPane;
	private CapNhatPhong capnhatphong;
	private CapNhatSanPham capnhatsanpham;
	private DanhSachKhachHang danhsachkhachhang;
	private CapNhatKhachHang capnhatkhachhang;
	private DanhSachNhanVien danhsachnhanvien;
	private CapNhatNhanVien capnhatnhanvien;
	private DanhSachHoaDon danhsachhoadon;
	private DanhSachLoaiPhong danhsachloaisanpham;
	private CapNhatLoaiPhong capnhatloaisanpham;
	private DanhSachTaiKhoan danhsachtaikhoan;
	private CapNhatTaiKhoan capnhataikhoan;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 1408, 692);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu jmenuPhong = new JMenu("Phòng");
		jmenuPhong.setBackground(Color.WHITE);
		jmenuPhong.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(jmenuPhong);
		
		JMenuItem mtdanhsachphong = new JMenuItem("Danh Sách Phòng");
		mtdanhsachphong.setBackground(Color.WHITE);
		mtdanhsachphong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new DanhSachPhong());
			}
		});
		mtdanhsachphong.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuPhong.add(mtdanhsachphong);
		
		JMenuItem mtcapnhatphong = new JMenuItem("Cập Nhật Phòng");
		mtcapnhatphong.setBackground(Color.WHITE);
		mtcapnhatphong.addActionListener(new ActionListener() {
			

			

			public void actionPerformed(ActionEvent e) {
				capnhatphong=new CapNhatPhong();
				hienthi(capnhatphong);
			}
		});
		mtcapnhatphong.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuPhong.add(mtcapnhatphong);
		
		JMenuItem mttimkiemphong = new JMenuItem("Tìm Kiếm Phòng");
		mttimkiemphong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new TimKiemPhong());
			}
		});
		
			
		mttimkiemphong.setBackground(Color.WHITE);
		mttimkiemphong.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuPhong.add(mttimkiemphong);
		
		JMenuItem mtDatphong = new JMenuItem("Đặt Phòng");
		mtDatphong.setBackground(Color.WHITE);
		mtDatphong.addActionListener(new ActionListener() {
			private DatPhong datphong;

			public void actionPerformed(ActionEvent e) {
				datphong = new DatPhong();
				hienthi(datphong);
			}
		});
		mtDatphong.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuPhong.add(mtDatphong);
		
		JMenu jmenuLoaiSanPham = new JMenu("Loại Phòng");
		jmenuPhong.add(jmenuLoaiSanPham);
		jmenuLoaiSanPham.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JMenuItem mtdanhsachloaisanpham = new JMenuItem("Danh Sách Loại Phòng");
		mtdanhsachloaisanpham.setBackground(Color.WHITE);
		mtdanhsachloaisanpham.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				danhsachloaisanpham=new DanhSachLoaiPhong();
				hienthi(danhsachloaisanpham);
			}
		});
		mtdanhsachloaisanpham.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuLoaiSanPham.add(mtdanhsachloaisanpham);
		
		JMenuItem mtcapnhatloaisanpham = new JMenuItem("Cập Nhật Loại Phòng");
		mtcapnhatloaisanpham.setBackground(Color.WHITE);
		mtcapnhatloaisanpham.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				capnhatloaisanpham=new CapNhatLoaiPhong();
				hienthi(capnhatloaisanpham);
			}
		});
		mtcapnhatloaisanpham.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuLoaiSanPham.add(mtcapnhatloaisanpham);
		
		JMenuItem mttimkiemloaisanpham = new JMenuItem("Tìm Kiếm Loại Phòng");
		mttimkiemloaisanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new TimKiemLoaiPhong());
			}
		});
		mttimkiemloaisanpham.setBackground(Color.WHITE);
		mttimkiemloaisanpham.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuLoaiSanPham.add(mttimkiemloaisanpham);
		
		JMenu jmenuSanPham = new JMenu("Sản Phẩm");
		jmenuSanPham.setBackground(Color.WHITE);
		jmenuSanPham.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(jmenuSanPham);
		
		JMenuItem mtdanhsachsanpham = new JMenuItem("Danh Sách Sản Phẩm");
		mtdanhsachsanpham.setBackground(Color.WHITE);
		mtdanhsachsanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new DanhSachSanPham());
			}
		});
		mtdanhsachsanpham.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuSanPham.add(mtdanhsachsanpham);
		
		JMenuItem mtcapnhatsanpham = new JMenuItem("Cập Nhật Sản Phẩm");
		mtcapnhatsanpham.setBackground(Color.WHITE);
		mtcapnhatsanpham.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				capnhatsanpham=new CapNhatSanPham();
				hienthi(capnhatsanpham);
			}
		});
		
		mtcapnhatsanpham.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuSanPham.add(mtcapnhatsanpham);
		
		JMenuItem mttimkiemsanpham = new JMenuItem("Tìm Kiếm Sản Phẩm");
		mttimkiemsanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new TimKiemSanPham());
				
			}
		});
		
		mttimkiemsanpham.setBackground(Color.WHITE);
		mttimkiemsanpham.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuSanPham.add(mttimkiemsanpham);
		
		JMenu jmenuKhachHang = new JMenu("Khách Hàng");
		jmenuKhachHang.setBackground(Color.WHITE);
		jmenuKhachHang.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(jmenuKhachHang);
		
		JMenuItem mtdanhsachkhachhang = new JMenuItem("Danh Sách Khách Hàng");
		mtdanhsachkhachhang.setBackground(Color.WHITE);
		mtdanhsachkhachhang.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				danhsachkhachhang=new DanhSachKhachHang();
				hienthi(danhsachkhachhang);
			}
		});
		mtdanhsachkhachhang.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuKhachHang.add(mtdanhsachkhachhang);
		
		JMenuItem mtcapnhatkhachhang = new JMenuItem("Cập Nhật Khách Hàng");
		mtcapnhatkhachhang.setBackground(Color.WHITE);
		mtcapnhatkhachhang.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				capnhatkhachhang=new CapNhatKhachHang();
				hienthi(capnhatkhachhang);
			}
		});
		mtcapnhatkhachhang.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuKhachHang.add(mtcapnhatkhachhang);
		
		JMenuItem mttinkiemkhachhang = new JMenuItem("Tìm Kiếm Khách Hàng");
		mttinkiemkhachhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new TimKiemKhachHang());
			}
		});
		mttinkiemkhachhang.setBackground(Color.WHITE);
		mttinkiemkhachhang.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuKhachHang.add(mttinkiemkhachhang);
		
		JMenu jmenuNhanVien = new JMenu("Nhân Viên");
		jmenuNhanVien.setBackground(Color.WHITE);
		jmenuNhanVien.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(jmenuNhanVien);
		
		JMenuItem mtdanhsachnhanvien = new JMenuItem("Danh Sách Nhân Viên");
		mtdanhsachnhanvien.setBackground(Color.WHITE);
		mtdanhsachnhanvien.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				try {
					danhsachnhanvien=new DanhSachNhanVien();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hienthi(danhsachnhanvien);
			}
		});
		mtdanhsachnhanvien.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuNhanVien.add(mtdanhsachnhanvien);
		
		JMenuItem mtcapnhatnhanvien = new JMenuItem("Cập Nhật Nhân Viên");
		mtcapnhatnhanvien.setBackground(Color.WHITE);
		mtcapnhatnhanvien.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				try {
					capnhatnhanvien=new CapNhatNhanVien();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hienthi(capnhatnhanvien);
			}
		});
		mtcapnhatnhanvien.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuNhanVien.add(mtcapnhatnhanvien);
		
		JMenuItem mttimkiemnhanvien = new JMenuItem("Tìm Kiếm Nhân Viên");
		mttimkiemnhanvien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hienthi(new TimKiemNhanVien());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mttimkiemnhanvien.setBackground(Color.WHITE);
		mttimkiemnhanvien.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuNhanVien.add(mttimkiemnhanvien);
		
		JMenu jmenuHoaDon = new JMenu("Hóa Đơn");
		jmenuHoaDon.setBackground(Color.WHITE);
		jmenuHoaDon.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(jmenuHoaDon);
		
		JMenuItem mtdanhsachhoadon = new JMenuItem("Danh Sách Hóa Đơn");
		mtdanhsachhoadon.setBackground(Color.WHITE);
		mtdanhsachhoadon.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				danhsachhoadon=new DanhSachHoaDon();
				hienthi(danhsachhoadon);
			}
		});
		mtdanhsachhoadon.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuHoaDon.add(mtdanhsachhoadon);
		
		JMenuItem mtcapnhathoadon = new JMenuItem("Cập Nhật Hóa Đơn");
		mtcapnhathoadon.addActionListener(new ActionListener() {
			private String maNhanVien;

			public void actionPerformed(ActionEvent e) {
				hienthi(new CapNhatHoaDon());
			}
		});
		mtcapnhathoadon.setBackground(Color.WHITE);
		mtcapnhathoadon.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuHoaDon.add(mtcapnhathoadon);
		
		JMenuItem mttimkiemhoadon = new JMenuItem("Tìm Kiếm Hóa Đơn");
		mttimkiemhoadon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new ThongKe());
			}
		});
		mttimkiemhoadon.setBackground(Color.WHITE);
		mttimkiemhoadon.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuHoaDon.add(mttimkiemhoadon);
		
		JMenuItem mtthongkehoadon = new JMenuItem("Thống Kê Hóa Đơn");
		mtthongkehoadon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new ThongKe());
			}
		});
		mtthongkehoadon.setBackground(Color.WHITE);
		mtthongkehoadon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		jmenuHoaDon.add(mtthongkehoadon);
		
		JMenu jmenuTaiKhoan = new JMenu("Tài Khoản");
		jmenuTaiKhoan.setFont(new Font("Dialog", Font.BOLD, 18));
		menuBar.add(jmenuTaiKhoan);
		
		JMenuItem mtdanhsachtailkhoanhoan = new JMenuItem("Danh Sách Tài Khoản");
		mtdanhsachtailkhoanhoan.setBackground(Color.WHITE);
		mtdanhsachtailkhoanhoan.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				danhsachtaikhoan=new DanhSachTaiKhoan();
				hienthi(danhsachtaikhoan);
			}
		});
		mtdanhsachtailkhoanhoan.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuTaiKhoan.add(mtdanhsachtailkhoanhoan);
		
		JMenuItem mtcapnhattaikhoan = new JMenuItem("Cập Nhật Tài Khoản");
		mtcapnhattaikhoan.setBackground(Color.WHITE);
		mtcapnhattaikhoan.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				capnhataikhoan=new CapNhatTaiKhoan();
				hienthi(capnhataikhoan);
			}
		});
		mtcapnhattaikhoan.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuTaiKhoan.add(mtcapnhattaikhoan);
		
		JMenuItem mttimkiemtaikhoan = new JMenuItem("Tìm Kiếm Tài Khoản");
		mttimkiemtaikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienthi(new TimKiemTaiKhoan());
			}
		});
		mttimkiemtaikhoan.setBackground(Color.WHITE);
		mttimkiemtaikhoan.setFont(new Font("Dialog", Font.PLAIN, 16));
		jmenuTaiKhoan.add(mttimkiemtaikhoan);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		
		
	}
	
	public void reload() {
		contentPane.setVisible(false);
		contentPane.setVisible(true);
	}
	public void hienthi(JPanel jpanel) {
		contentPane.removeAll();
		contentPane.add(jpanel);
		contentPane.repaint();
		reload();
	}
}
