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
import dao.SanPhamDao;
import entity.SanPham;

public class DanhSachSanPham extends JPanel {
	
	
	private JScrollPane scroll;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<SanPham> dssp;
	private SanPhamDao spDao;


	/**
	 * Create the panel.
	 */
	public DanhSachSanPham() {
		setLayout(new BorderLayout(0, 0));
		String[] tieuDe = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm","Đơn Giá" };
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH SẢN PHẨM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		dssp = new ArrayList<SanPham>();
		spDao = new SanPhamDao();
		Database.getInstance().connect();
		updateTable();
	
	}

	private void updateTable() {
		dssp = spDao.getAllSanPham();
		for (SanPham i : dssp) {
			dataModel.addRow(new Object[] { i.getMaSanPham(), i.getTenSanPham(), i.getLoaiSanPham(), i.getDonGia() });
		}
	}


}

