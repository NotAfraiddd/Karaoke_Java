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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import connect.Database;
import dao.PhongDao;
import entity.Phong;

public class DanhSachPhong extends JPanel{
	
	
	private JScrollPane scroll;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<Phong> dsp;
	private PhongDao p_dao;


	/**
	 * Create the panel.
	 */
	public DanhSachPhong() {
		setLayout(new BorderLayout(0, 0));
		String[] tieuDe = { "Mã Phòng", "Tên Phòng", "Loại Phòng","Trạng thái","Lầu" };
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH PHÒNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		dsp = new ArrayList<Phong>();
		p_dao = new PhongDao();
		Database.getInstance().connect();
		updateTable();
	
	}

	private void updateTable() {
		dsp = p_dao.getAllDSPhong();
		for (Phong i : dsp) {
			dataModel.addRow(new Object[] { i.getMaPhong(), i.getLoaiPhong().getMaLoaiPhong(), i.getTenPhong(),
					i.getTrangThai(), i.getLau() });
		}
	}




}
