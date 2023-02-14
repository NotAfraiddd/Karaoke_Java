package GUI.DanhSach;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import connect.Database;
import dao.NhanVienDao;
import entity.NhanVien;

public class DanhSachNhanVien extends JPanel {
	
	
	private JScrollPane scroll;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<NhanVien> dsnv;
	private NhanVienDao nvDao;


	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public DanhSachNhanVien() throws ClassNotFoundException, SQLException {
		setLayout(new BorderLayout(0, 0));
		String[] tieuDe = { "Mã Nhân Viên", "Tên Nhân Viên", "Địa Chỉ","Số Điện Thoại","Chức Vụ","Giới Tính","Mức Lương" };
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());;
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		dsnv = new ArrayList<NhanVien>();
		nvDao = new NhanVienDao();
		Database.getInstance().connect();
		updateTable();
		
	}

	public void updateTable() throws ClassNotFoundException, SQLException {
		dsnv = nvDao.getAllNhanVien();
		for (NhanVien i : dsnv) {
			dataModel.addRow(new Object[] { i.getMaNhanVien(), i.getTenNhanVien(), i.getDiaChi(), i.getSoDT(),
					i.getChucVu(), i.getGioiTinh(), i.getMucLuong() });
		}
	}

}

