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

import GUI.DanhSach.DanhSachNhanVien;
import connect.Database;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import entity.TaiKhoan;

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

public class CapNhatTaiKhoan extends JPanel implements ActionListener,MouseListener{
	

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMatkhau;
	private ArrayList<TaiKhoan> dstk;
	private TaiKhoanDao tkDao;
	private JButton btnThemTaiKhoan;
	private JButton btnSuaTaiKhoan;
	private JButton btnXoaTaiKhoan;
	private JButton btnLamrong;
	private JComboBox cboTaiKhoan;
	private ArrayList<NhanVien> dsnv;
	private NhanVienDao nvDao;

	/**
	 * Create the panel.
	 */
	public CapNhatTaiKhoan() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("CẬP NHẬT TÀI KHOẢN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Tên Tài Khoản", "Mật Khẩu" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(262, 55, 111, 25);
		panel_2.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(708, 55, 111, 13);
		panel_2.add(lblNewLabel_3);
		
		btnThemTaiKhoan = new JButton("Thêm tài khoản");
		btnThemTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemTaiKhoan.setBounds(145, 116, 157, 40);
		panel_2.add(btnThemTaiKhoan);
		
		btnSuaTaiKhoan = new JButton("Đổi mật khẩu");
		btnSuaTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSuaTaiKhoan.setBounds(412, 116, 176, 40);
		panel_2.add(btnSuaTaiKhoan);
		
		btnXoaTaiKhoan = new JButton("Xóa tài khoản");
		btnXoaTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaTaiKhoan.setBounds(671, 116, 148, 40);
		panel_2.add(btnXoaTaiKhoan);
		
		btnLamrong = new JButton("Làm rỗng");
		btnLamrong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamrong.setBounds(912, 116, 163, 40);
		panel_2.add(btnLamrong);
		
		txtMatkhau = new JTextField();
		txtMatkhau.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtMatkhau.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMatkhau.setBounds(829, 47, 203, 29);
		panel_2.add(txtMatkhau);
		txtMatkhau.setColumns(10);
		
		cboTaiKhoan = new JComboBox();
		cboTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboTaiKhoan.setEditable(true);
		cboTaiKhoan.setBounds(371, 52, 176, 28);
		panel_2.add(cboTaiKhoan);
		DefaultComboBoxModel<String> cboDataModel = new DefaultComboBoxModel<String>();

		// chen su kien
		btnLamrong.addActionListener(this);
		btnSuaTaiKhoan.addActionListener(this);
		btnThemTaiKhoan.addActionListener(this);
		btnXoaTaiKhoan.addActionListener(this);
		
		btnLamrong.addMouseListener(this);
		btnSuaTaiKhoan.addMouseListener(this);
		btnThemTaiKhoan.addMouseListener(this);
		btnXoaTaiKhoan.addMouseListener(this);
		table.addMouseListener(this);
		
		dstk = new ArrayList<TaiKhoan>();
		dsnv = new ArrayList<NhanVien>();
		
		nvDao = new NhanVienDao();
		tkDao = new TaiKhoanDao();

		Database.getInstance().connect();
		updateTable();
		updateCBO();
	}
	private void updateTable() {
		dstk = tkDao.getAllTK();
		for (TaiKhoan i : dstk) {
			dataModel.addRow(new Object[] { i.getTenDangNhap(),i.getMatKhau() });
		}
	}
	public void deleteTable() {
		int y = dataModel.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModel.removeRow(0);
		}
	}
	public void updateCBO() {
		try {
			dsnv = nvDao.getAllNhanVien();
			for (NhanVien i : dsnv) {
				cboTaiKhoan.addItem(i.getMaNhanVien());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void xoaRong() {
		txtMatkhau.setText("");
		cboTaiKhoan.setSelectedItem("");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(table)) {
			int row = table.getSelectedRow();
			cboTaiKhoan.setSelectedItem(dataModel.getValueAt(row, 0).toString());
			txtMatkhau.setText(dataModel.getValueAt(row, 1).toString());
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemTaiKhoan)) {
			if (txtMatkhau.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã phòng");
				txtMatkhau.requestFocus();
			} else {
				boolean a = true;
				try {
					String taiKhoan = cboTaiKhoan.getSelectedItem().toString();
					String mk = txtMatkhau.getText();
					TaiKhoan tk = new TaiKhoan(taiKhoan, mk);
					tkDao.taoTK(tk);
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

		} else if (o.equals(btnXoaTaiKhoan)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				tkDao.xoaTaiKhoan(dataModel.getValueAt(row, 0).toString());
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				else
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn hàng cần xóa");
			}
		} else if (o.equals(btnSuaTaiKhoan)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				String taiKhoan = cboTaiKhoan.getSelectedItem().toString();
				String mk = txtMatkhau.getText();
				TaiKhoan tk = new TaiKhoan(taiKhoan, mk);
				tkDao.capNhatTK(tk);
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
}
