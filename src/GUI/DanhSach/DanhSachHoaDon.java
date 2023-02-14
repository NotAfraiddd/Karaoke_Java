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
import dao.HoaDonDao;
import dao.KhachHangDao;
import entity.HoaDon;
import entity.KhachHang;

public class DanhSachHoaDon extends JPanel {

	private JScrollPane scroll;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<HoaDon> dshd;
	private HoaDonDao hdDao;

	/**
	 * Create the panel.
	 */
	public DanhSachHoaDon() {
		setLayout(new BorderLayout(0, 0));
		String[] tieuDe = { "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên","Ngày lập hóa đơn","Thành Tiền" };
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("DANH SÁCH HÓA ĐƠN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		dshd = new ArrayList<HoaDon>();
		hdDao = new HoaDonDao();

		Database.getInstance().connect();
		updateTable();
		

	}

	private void updateTable() {
		dshd = hdDao.getAllHoaDon();
		for (HoaDon i : dshd) {
			dataModel.addRow(new Object[] { i.getMaHoaDon(), i.getKhachHang().getMaKhachHang(),
					i.getNhanVien().getMaNhanVien(), i.getNgayLapHoaDon(), i.getThanhTien() });
		}

	}

}
