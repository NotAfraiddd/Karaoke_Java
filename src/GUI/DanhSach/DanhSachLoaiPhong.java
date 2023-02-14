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
import entity.LoaiPhong;

public class DanhSachLoaiPhong extends JPanel {

	private JScrollPane scroll;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<LoaiPhong> dslp;
	private LoaiPhongDao lp_dao;

	/**
	 * Create the panel.
	 */
	public DanhSachLoaiPhong() {
		setLayout(new BorderLayout(0, 0));
		String[] tieuDe = { "Mã loại phòng", "Tên loại phòng", "Số chỗ ", "Đơn giá" };
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());

		JLabel lblNewLabel = new JLabel("DANH SÁCH LOẠI PHÒNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		dslp= new ArrayList<LoaiPhong>();
		lp_dao = new LoaiPhongDao();
		
		Database.getInstance().connect();
		updateTable();

	}

	public void updateTable() {
		dslp = lp_dao.getAllDSLoaiPhong();
		for (LoaiPhong i : dslp) {
			dataModel.addRow(new Object[] { i.getMaLoaiPhong(), i.getTenLoaiPhong(), i.getSoCho(), i.getDonGia() });
		}
	}

}
