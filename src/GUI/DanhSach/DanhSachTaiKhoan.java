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
import dao.LoaiPhongDao;
import dao.TaiKhoanDao;
import entity.LoaiPhong;
import entity.TaiKhoan;

public class DanhSachTaiKhoan extends JPanel {
	
	
	private JScrollPane scroll;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<TaiKhoan> dstk;
	private TaiKhoanDao tkDao;


	/**
	 * Create the panel.
	 */
	public DanhSachTaiKhoan() {
		setLayout(new BorderLayout(0, 0));
		String[] tieuDe = { "Tên Tài Khoản", "Mật Khẩu" };
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH TÀI KHOẢN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		dstk= new ArrayList<TaiKhoan>();
		tkDao = new TaiKhoanDao();
		
		Database.getInstance().connect();
		updateTable();
	
	}

	private void updateTable() {
		dstk = tkDao.getAllTK();
		for (TaiKhoan i : dstk) {
			dataModel.addRow(new Object[] { i.getTenDangNhap(),i.getMatKhau() });
		}
	}

}

