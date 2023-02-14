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
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import connect.Database;
import dao.KhachHangDao;
import entity.KhachHang;

public class DanhSachKhachHang extends JPanel {
	
	
	private JScrollPane scroll;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<KhachHang> dskh;
	private KhachHangDao khDao;


	/**
	 * Create the panel.
	 */
	public DanhSachKhachHang() {
		setLayout(new BorderLayout(0, 0));
		String[] tieuDe = {  "Mã Khách Hàng", "Tên Khách Hàng", "Số CMND","Số Điện Thoại" };
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH KHÁCH HÀNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		dskh = new ArrayList<KhachHang>();
		khDao = new KhachHangDao();
		Database.getInstance().connect();
		updateTable();
	
	}

	public void updateTable() {
		dskh = khDao.getAllKhachHang();
		for (KhachHang i : dskh) {
			dataModel.addRow(new Object[] { i.getMaKhachHang(), i.getTenKhachHang(), i.getSoCMND(), i.getSoDT() });
		}
	}

}

