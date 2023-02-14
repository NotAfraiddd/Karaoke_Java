package GUI.CapNhat;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

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
import dao.KhachHangDao;
import entity.KhachHang;
import entity.LoaiPhong;

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

public class CapNhatKhachHang extends JPanel implements ActionListener, MouseListener{

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMakhachhang;
	private JTextField txtTenkhachhang;
	private JTextField txtSDT;
	private JTextField txtCMND;
	private ArrayList<KhachHang> dskh;
	private KhachHangDao khDao;
	private JButton btnSuakhachhang;
	private JButton btnThemkhachhang;
	private JButton btnXoakhachhang;
	private JButton btnLamrong;

	/**
	 * Create the panel.
	 */
	public CapNhatKhachHang() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("CẬP NHẬT KHÁCH HÀNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Khách Hàng", "Tên Khách Hàng", "Số CMND", "Số Điện Thoại" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(272, 26, 110, 25);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số CMND:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(307, 59, 110, 25);
		panel_2.add(lblNewLabel_2);
		
		txtMakhachhang = new JTextField();
		txtMakhachhang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//txtMakhachhang.setEnabled(false);
		txtMakhachhang.setBounds(397, 23, 203, 25);
		panel_2.add(txtMakhachhang);
		txtMakhachhang.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên khách hàng : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(699, 26, 110, 25);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Số điện thoại :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(714, 59, 110, 25);
		panel_2.add(lblNewLabel_4);

		txtTenkhachhang = new JTextField();
		txtTenkhachhang.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenkhachhang.setBounds(842, 23, 203, 25);
		panel_2.add(txtTenkhachhang);
		txtTenkhachhang.setColumns(10);

		btnThemkhachhang = new JButton("Thêm khách hàng");
		btnThemkhachhang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemkhachhang.setBounds(378, 135, 157, 43);
		panel_2.add(btnThemkhachhang);

		btnSuakhachhang = new JButton("Sửa thông tin");
		btnSuakhachhang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSuakhachhang.setBounds(557, 135, 157, 43);
		panel_2.add(btnSuakhachhang);

		btnXoakhachhang = new JButton("Xóa khách hàng");
		btnXoakhachhang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoakhachhang.setBounds(735, 135, 137, 43);
		panel_2.add(btnXoakhachhang);

		btnLamrong = new JButton("Làm rỗng");
		btnLamrong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamrong.setBounds(899, 135, 146, 43);
		panel_2.add(btnLamrong);
		DefaultComboBoxModel<String> cboDataModel = new DefaultComboBoxModel<String>();

		txtSDT = new JTextField();
		txtSDT.setBounds(842, 57, 203, 25);
		txtSDT.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);

		txtCMND = new JTextField();
		txtCMND.setBounds(397, 57, 203, 25);
		txtCMND.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(txtCMND);
		txtCMND.setColumns(10);

		// chèn sự kiện
		btnLamrong.addActionListener(this);
		btnSuakhachhang.addActionListener(this);
		btnThemkhachhang.addActionListener(this);
		btnXoakhachhang.addActionListener(this);
		
		btnLamrong.addMouseListener(this);
		btnSuakhachhang.addMouseListener(this);
		btnThemkhachhang.addMouseListener(this);
		btnXoakhachhang.addMouseListener(this);
		table.addMouseListener(this);
		
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

	public void deleteTable() {
		int y = dataModel.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModel.removeRow(0);
		}
	}
	public void xoaRong() {
		txtMakhachhang.setText("");
		txtTenkhachhang.setText("");
		txtCMND.setText("");
		txtSDT.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemkhachhang)) {
			if (txtMakhachhang.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã khách hàng");
				txtMakhachhang.requestFocus();
			} else if (txtTenkhachhang.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên loại phòng");
				txtTenkhachhang.requestFocus();
			} else if (txtCMND.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập số CMND");
				txtCMND.requestFocus();
			} else if (txtSDT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại");
				txtSDT.requestFocus();
			} else {
				boolean a = true;
				try {
					String ma = txtMakhachhang.getText();
					String ten = txtTenkhachhang.getText();
					String soCMND = txtCMND.getText();
					String soDT = txtSDT.getText();
					KhachHang kh = new KhachHang(ma, ten, soCMND, soDT);
					khDao.themKhachHang(kh);
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

		} else if (o.equals(btnXoakhachhang)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				khDao.xoaKhachHang(dataModel.getValueAt(row, 0).toString()); // sua thanh so 1
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				else
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn hàng cần xóa");
			}
		} else if (o.equals(btnSuakhachhang)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				String ma = dataModel.getValueAt(row, 0).toString();
				String ten = txtTenkhachhang.getText();
				String soCMND = txtCMND.getText();
				String soDT = txtSDT.getText();
				KhachHang kh = new KhachHang(ma, ten, soCMND, soDT);
				khDao.capNhatKhachHang(kh);
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				else
					JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if(o.equals(btnLamrong)) {
			xoaRong();
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			txtMakhachhang.setText(dataModel.getValueAt(row, 0).toString());
			txtTenkhachhang.setText(dataModel.getValueAt(row, 1).toString());
			txtCMND.setText(dataModel.getValueAt(row, 2).toString());
			txtSDT.setText(dataModel.getValueAt(row, 3).toString());
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
