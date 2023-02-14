package GUI.CapNhat;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridBagLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connect.Database;
import dao.LoaiPhongDao;
import dao.PhongDao;
import entity.LoaiPhong;
import entity.Phong;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dimension;

public class CapNhatPhong extends JPanel implements ActionListener, MouseListener {

	private JScrollPane scroll;
	public JTable table;
	public DefaultTableModel dataModel;
	private JTextField txtMaphong;
	private JTextField txtTenphong;
	private ArrayList<Phong> dsp;
	private PhongDao p_dao;
	private JButton btnThemphong;
	private JButton btnSuaphong;
	private JButton btnXoaphong;
	private JButton btnLamrong;
	private JComboBox cboLoaiPhong;
	private ArrayList<LoaiPhong> dslp;
	private LoaiPhongDao lp_dao;
	private JComboBox cboLau;
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Create the panel.
	 */
	public CapNhatPhong() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("CẬP NHẬT PHÒNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Phòng", "Loại Phòng", "Tên Phòng", "Trạng thái", "Lầu" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã phòng : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(31, 23, 74, 33);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Loại phòng :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(23, 104, 95, 33);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Tên phòng : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(476, 27, 74, 25);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Trạng thái :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(476, 105, 77, 17);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Lầu :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(941, 39, 77, 17);
		panel_2.add(lblNewLabel_5);

		btnThemphong = new JButton("Thêm phòng");
		btnThemphong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemphong.setBounds(297, 173, 127, 40);
		panel_2.add(btnThemphong);

		btnSuaphong = new JButton("Sửa thông tin");
		btnSuaphong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSuaphong.setBounds(493, 173, 137, 40);
		panel_2.add(btnSuaphong);

		btnXoaphong = new JButton("Xóa phòng");
		btnXoaphong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaphong.setBounds(686, 173, 137, 40);
		panel_2.add(btnXoaphong);

		btnLamrong = new JButton("Làm rỗng");
		btnLamrong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamrong.setBounds(887, 173, 137, 40);
		panel_2.add(btnLamrong);

		txtMaphong = new JTextField();
		txtMaphong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// txtMaphong.setEnabled(false);
		txtMaphong.setBounds(128, 27, 203, 33);
		panel_2.add(txtMaphong);
		txtMaphong.setColumns(10);

		txtTenphong = new JTextField();
		txtTenphong.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenphong.setBounds(596, 27, 203, 33);
		panel_2.add(txtTenphong);
		txtTenphong.setColumns(10);

		cboLoaiPhong = new JComboBox();
		cboLoaiPhong.setEditable(true);
		cboLoaiPhong.setBounds(128, 105, 203, 33);
		panel_2.add(cboLoaiPhong);

		cboLau = new JComboBox();
		cboLau.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
		cboLau.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboLau.setEditable(true);
		cboLau.setBounds(1003, 23, 150, 33);
		panel_2.add(cboLau);

		chckbxNewCheckBox = new JCheckBox("Đang hoạt động");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxNewCheckBox.setBounds(568, 105, 119, 21);
		panel_2.add(chckbxNewCheckBox);

		dsp = new ArrayList<Phong>();
		dslp = new ArrayList<LoaiPhong>();
		p_dao = new PhongDao();
		lp_dao = new LoaiPhongDao();
		// gan su kien
		btnLamrong.addActionListener(this);
		btnSuaphong.addActionListener(this);
		btnThemphong.addActionListener(this);
		btnXoaphong.addActionListener(this);

		btnLamrong.addMouseListener(this);
		btnSuaphong.addMouseListener(this);
		btnThemphong.addMouseListener(this);
		btnXoaphong.addMouseListener(this);
		table.addMouseListener(this);
		Database.getInstance().connect();
		updateTable();
		updateCBO();
	}

	public void updateTable() {
		dsp = p_dao.getAllDSPhong();
		for (Phong i : dsp) {
			dataModel.addRow(new Object[] { i.getMaPhong(), i.getLoaiPhong().getMaLoaiPhong(), i.getTenPhong(),
					i.getTrangThai(), i.getLau() });
		}
	}

	public void deleteTable() {
		int y = dataModel.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModel.removeRow(0);
		}
	}

	public void updateCBO() {
		dslp = lp_dao.getAllDSLoaiPhong();
		for (LoaiPhong i : dslp) {
			cboLoaiPhong.addItem(i.getMaLoaiPhong());
		}
	}

	public void xoaRong() {
		txtMaphong.setText("");
		txtTenphong.setText("");
		cboLoaiPhong.setSelectedItem("THU");
		chckbxNewCheckBox.setSelected(false);
		cboLau.setSelectedItem(1);
		txtMaphong.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemphong)) {
			if (txtMaphong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã phòng");
				txtMaphong.requestFocus();
			} else if (txtTenphong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên phòng");
				txtTenphong.requestFocus();
			} else {
				boolean a = true;
				try {
					String ma = txtMaphong.getText();
					String malp = cboLoaiPhong.getSelectedItem().toString();
					String ten = txtTenphong.getText();
					String tinhTrang;
					if (chckbxNewCheckBox.isSelected() == false) {
						tinhTrang = "Trống";
					} else
						tinhTrang = "Đang hoạt động";
					int lau = Integer.parseInt(cboLau.getSelectedItem().toString());
					Phong p = new Phong(ma, new LoaiPhong(malp), ten, tinhTrang, lau);
					p_dao.themPhong(p);
					deleteTable();
					updateTable();
					if (a == true)
						JOptionPane.showMessageDialog(this, "Thêm thành công");
					else
						JOptionPane.showMessageDialog(this, "Thêm thất bại");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Chưa điền thông tin đầy đủ");
				}
			}

		} else if (o.equals(btnXoaphong)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				p_dao.xoaPhong(dataModel.getValueAt(row, 0).toString());
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				else
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn hàng cần xóa");
			}
		} else if (o.equals(btnSuaphong)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				String ma = dataModel.getValueAt(row, 0).toString();
				String malp = cboLoaiPhong.getSelectedItem().toString();
				String ten = txtTenphong.getText();
				String tinhTrang;
				if (chckbxNewCheckBox.isSelected() == false) {
					tinhTrang = "Trống";
				} else
					tinhTrang = "Đang hoạt động";
				int lau = Integer.parseInt(cboLau.getSelectedItem().toString());
				Phong p = new Phong(ma, new LoaiPhong(malp), ten, tinhTrang, lau);
				a = p_dao.capNhatPhong(p);
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				else
					JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnLamrong)) {
			xoaRong();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			txtMaphong.setText(dataModel.getValueAt(row, 0).toString());
			cboLoaiPhong.setSelectedItem(dataModel.getValueAt(row, 1).toString());
			txtTenphong.setText(dataModel.getValueAt(row, 2).toString());
			if (dataModel.getValueAt(row, 3).toString().equalsIgnoreCase("Trống"))
				chckbxNewCheckBox.setSelected(false);
			else chckbxNewCheckBox.setSelected(true);
			cboLau.setSelectedItem(dataModel.getValueAt(row, 4).toString());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
