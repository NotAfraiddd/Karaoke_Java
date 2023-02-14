package GUI.CapNhat;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridBagLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connect.Database;
import dao.NhanVienDao;
import entity.LoaiPhong;
import entity.NhanVien;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JRadioButton;

public class CapNhatNhanVien extends JPanel implements ActionListener, MouseListener {

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMaNhanVien, txtTenNhanVien, txtDiaChi, txtSoDT, txtMucLuong;
	/**
	 * Create the panel.
	 */
	private ButtonGroup rad;
	private ArrayList<NhanVien> dsnv;
	private NhanVienDao nvDao;
	private JButton btnLamrong;
	private JButton btnXoanhanvien;
	private JButton btnSuanhanvien;
	private JButton btnThemnhanvien;
	private JComboBox cboChucVu;
	private JRadioButton radNam;
	private JRadioButton radNu;

	public CapNhatNhanVien() throws ClassNotFoundException, SQLException {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("CẬP NHẬT NHÂN VIÊN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Nhân Viên", "Tên Nhân Viên", "Địa Chỉ", "Số Điện Thoại", "Chức Vụ", "Giới Tính",
				"Mức Lương" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(26, 27, 110, 25);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(26, 60, 110, 25);
		panel_2.add(lblNewLabel_2);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//txtMaNhanVien.setEnabled(false);
		txtMaNhanVien.setBounds(151, 24, 203, 25);
		panel_2.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên nhân viên: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(461, 27, 110, 25);
		panel_2.add(lblNewLabel_3);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenNhanVien.setBounds(596, 24, 203, 25);
		panel_2.add(txtTenNhanVien);
		txtTenNhanVien.setColumns(10);

		btnThemnhanvien = new JButton("Thêm nhân viên");
		btnThemnhanvien.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemnhanvien.setBounds(352, 144, 166, 41);
		panel_2.add(btnThemnhanvien);

		btnSuanhanvien = new JButton("Sửa thông tin");
		btnSuanhanvien.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSuanhanvien.setBounds(544, 144, 166, 41);
		panel_2.add(btnSuanhanvien);

		btnXoanhanvien = new JButton("Xóa nhân viên");
		btnXoanhanvien.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoanhanvien.setBounds(732, 144, 145, 41);
		panel_2.add(btnXoanhanvien);

		btnLamrong = new JButton("Làm rỗng");
		btnLamrong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamrong.setBounds(902, 144, 145, 41);
		panel_2.add(btnLamrong);
		DefaultComboBoxModel<String> cboDataModel = new DefaultComboBoxModel<String>();

		JLabel lblNewLabel_3_1 = new JLabel("Địa chỉ : ");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(915, 27, 110, 25);
		panel_2.add(lblNewLabel_3_1);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(1002, 23, 203, 25);
		panel_2.add(txtDiaChi);

		txtSoDT = new JTextField();
		txtSoDT.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(151, 58, 203, 25);
		panel_2.add(txtSoDT);

		JLabel lblNewLabel_4 = new JLabel("Chức vụ :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(496, 60, 110, 25);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_3_1_1 = new JLabel("Mức lương : ");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1_1.setBounds(889, 60, 89, 25);
		panel_2.add(lblNewLabel_3_1_1);

		txtMucLuong = new JTextField();
		txtMucLuong.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtMucLuong.setColumns(10);
		txtMucLuong.setText("0");
		txtMucLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMucLuong.setBounds(1002, 60, 203, 25);
		panel_2.add(txtMucLuong);

		JLabel lblNewLabel_2_1 = new JLabel("Giới tính :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(26, 91, 103, 25);
		panel_2.add(lblNewLabel_2_1);

		cboChucVu = new JComboBox();
		cboChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboChucVu.setModel(new DefaultComboBoxModel(new String[] { "Quản lí", "Nhân viên" }));
		cboChucVu.setEditable(true);
		cboChucVu.setBounds(596, 63, 203, 25);
		panel_2.add(cboChucVu);

		radNam = new JRadioButton("Nam");
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radNam.setBackground(Color.WHITE);
		radNam.setBounds(151, 94, 103, 21);
		panel_2.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radNu.setBackground(Color.WHITE);
		radNu.setBounds(151, 116, 103, 21);
		panel_2.add(radNu);

		rad = new ButtonGroup();
		rad.add(radNam);
		rad.add(radNu);

		dsnv = new ArrayList<NhanVien>();
		nvDao = new NhanVienDao();
		// chen su kien
		btnLamrong.addActionListener(this);
		btnThemnhanvien.addActionListener(this);
		btnSuanhanvien.addActionListener(this);
		btnXoanhanvien.addActionListener(this);

		btnLamrong.addMouseListener(this);
		btnSuanhanvien.addMouseListener(this);
		btnThemnhanvien.addMouseListener(this);
		btnXoanhanvien.addMouseListener(this);
		
		table.addMouseListener(this);

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

	public void deleteTable() {
		int y = dataModel.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModel.removeRow(0);
		}
	}

	public void xoaRong() {
		String gt = "";
		txtMaNhanVien.setText("");
		txtTenNhanVien.setText("");
		txtDiaChi.setText("");
		txtSoDT.setText("");
		cboChucVu.setSelectedItem("Nhân viên");
		if (radNam.isSelected())
			gt = "Nam";
		else
			gt = "Nữ";
		txtMucLuong.setText("");
		txtMaNhanVien.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemnhanvien)) {
			if (txtMaNhanVien.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã nhân viên");
				txtMaNhanVien.requestFocus();
			} else if (txtTenNhanVien.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhân viên");
				txtTenNhanVien.requestFocus();
			} else if (txtDiaChi.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ");
				txtDiaChi.requestFocus();
			} else if (txtMucLuong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập mức lương");
				txtMucLuong.requestFocus();
			} else if(txtSoDT.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại");
				txtSoDT.requestFocus();
			}
			else {
				boolean a = true;
				try {
					String ma = txtMaNhanVien.getText();
					String ten = txtTenNhanVien.getText();
					String diaChi = txtDiaChi.getText();
					String soDT = txtSoDT.getText();
					String chucVu = cboChucVu.getSelectedItem().toString();
					String gtinh = "";
					if (radNam.isSelected())
						gtinh = "Nam";
					else
						gtinh = "Nữ";
					double mucLuong = Double.parseDouble(txtMucLuong.getText());
					NhanVien nv = new NhanVien(ma, ten, diaChi, soDT, chucVu, gtinh, mucLuong);
					nvDao.themNhanVien(nv);
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

		} else if (o.equals(btnXoanhanvien)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				nvDao.xoaNhanVien(dataModel.getValueAt(row, 0).toString()); // sua thanh so 1
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				else
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn hàng cần xóa");
			}
		} else if (o.equals(btnSuanhanvien)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				String ma = dataModel.getValueAt(row, 0).toString();
				String ten = txtTenNhanVien.getText();
				String diaChi = txtDiaChi.getText();
				String soDT = txtSoDT.getText();
				String chucVu = cboChucVu.getSelectedItem().toString();
				String gtinh = "";
				if (radNam.isSelected())
					gtinh = "Nam";
				else
					gtinh = "Nữ";
				double mucLuong = Double.parseDouble(txtMucLuong.getText());
				NhanVien nv = new NhanVien(ma, ten, diaChi, soDT, chucVu, gtinh, mucLuong);
				nvDao.capNhatNhanVien(nv);
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
			txtMaNhanVien.setText(dataModel.getValueAt(row, 0).toString());
			txtTenNhanVien.setText(dataModel.getValueAt(row, 1).toString());
			txtDiaChi.setText(dataModel.getValueAt(row, 2).toString());
			txtSoDT.setText(dataModel.getValueAt(row, 3).toString());
			cboChucVu.setSelectedItem(dataModel.getValueAt(row, 4).toString());
			if (dataModel.getValueAt(row, 5).toString().equalsIgnoreCase("Nam"))
				radNam.setSelected(true);
			else radNu.setSelected(true);
			txtMucLuong.setText(dataModel.getValueAt(row, 6).toString());
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
