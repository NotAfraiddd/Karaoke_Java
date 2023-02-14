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
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridBagLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connect.Database;
import dao.SanPhamDao;
import entity.LoaiPhong;
import entity.SanPham;

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

public class CapNhatSanPham extends JPanel implements ActionListener, MouseListener{
	

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JTextField txtDonGia;
	private ArrayList<SanPham> dssp;
	private SanPhamDao spDao;
	private JButton btnThemSanPham;
	private JButton btnSuaSanPham;
	private JButton btnXoaSanPham;
	private JButton btnLamRong;
	private JComboBox cboLoaiSanPham;

	/**
	 * Create the panel.
	 */
	public CapNhatSanPham() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("CẬP NHẬT SẢN PHẨM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = {  "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm","Đơn Giá" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(274, 26, 110, 25);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Loại sản phẩm:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(274, 75, 110, 25);
		panel_2.add(lblNewLabel_2);
		
		txtMaSanPham = new JTextField();
		txtMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//txtMasanpham.setEnabled(false);
		txtMaSanPham.setBounds(399, 23, 203, 25);
		panel_2.add(txtMaSanPham);
		txtMaSanPham.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(709, 26, 110, 25);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Đơn giá :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(744, 75, 74, 25);
		panel_2.add(lblNewLabel_4);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenSanPham.setBounds(844, 23, 203, 25);
		panel_2.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);
		
		btnThemSanPham = new JButton("Thêm sản phẩm");
		btnThemSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemSanPham.setBounds(384, 145, 137, 39);
		panel_2.add(btnThemSanPham);
		
		btnSuaSanPham = new JButton("Sửa thông tin");
		btnSuaSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSuaSanPham.setBounds(564, 145, 137, 39);
		panel_2.add(btnSuaSanPham);
		
		btnXoaSanPham = new JButton("Xóa sản phẩm");
		btnXoaSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaSanPham.setBounds(744, 145, 137, 39);
		panel_2.add(btnXoaSanPham);
		
		btnLamRong = new JButton("Làm rỗng");
		btnLamRong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamRong.setBounds(926, 145, 137, 39);
		panel_2.add(btnLamRong);
		
		txtDonGia = new JTextField();
		txtDonGia.setText("0");
		txtDonGia.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDonGia.setBounds(844, 76, 203, 25);
		panel_2.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		cboLoaiSanPham = new JComboBox();
		cboLoaiSanPham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboLoaiSanPham.setModel(new DefaultComboBoxModel(new String[] {"Nước", "Bánh", "Đồ ăn"}));
		cboLoaiSanPham.setEditable(true);
		cboLoaiSanPham.setBounds(399, 75, 203, 25);
		panel_2.add(cboLoaiSanPham);

		// chèn sự kiện
		btnLamRong.addActionListener(this);
		btnSuaSanPham.addActionListener(this);
		btnThemSanPham.addActionListener(this);
		btnXoaSanPham.addActionListener(this);
		
		btnLamRong.addMouseListener(this);
		btnSuaSanPham.addMouseListener(this);
		btnThemSanPham.addMouseListener(this);
		btnXoaSanPham.addMouseListener(this);
		table.addMouseListener(this);
		
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

	public void deleteTable() {
		int y = dataModel.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModel.removeRow(0);
		}
	}
	public void xoaRong() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		cboLoaiSanPham.setSelectedItem(5);
		txtDonGia.setText("");
		txtMaSanPham.requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemSanPham)) {
			if (txtMaSanPham.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm");
				txtMaSanPham.requestFocus();
			} else if (txtTenSanPham.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm");
				txtTenSanPham.requestFocus();
			} else if (txtDonGia.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá tiền");
				txtDonGia.requestFocus();
			} else {
				boolean a = true;
				try {
					String ma = txtMaSanPham.getText();
					String ten = txtTenSanPham.getText();
					String loaiSanPham = cboLoaiSanPham.getSelectedItem().toString();
					double donGia = Double.parseDouble(txtDonGia.getText());
					SanPham sp = new SanPham(ma, ten, loaiSanPham, donGia);
					spDao.themSanPham(sp);
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

		} else if (o.equals(btnXoaSanPham)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				spDao.xoaSP(dataModel.getValueAt(row, 0).toString()); // sua thanh so 1
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				else
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn hàng cần xóa");
			}
		} else if (o.equals(btnSuaSanPham)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				String ma = dataModel.getValueAt(row, 0).toString();
				String ten = txtTenSanPham.getText();
				String loaiSanPham = cboLoaiSanPham.getSelectedItem().toString();
				double donGia = Double.parseDouble(txtDonGia.getText());
				SanPham sp = new SanPham(ma, ten, loaiSanPham, donGia);
				spDao.capNhatSP(sp);
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				else
					JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if(o.equals(btnLamRong)) {
			xoaRong();
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			txtMaSanPham.setText(dataModel.getValueAt(row, 0).toString());
			txtTenSanPham.setText(dataModel.getValueAt(row, 1).toString());
			cboLoaiSanPham.setSelectedItem(dataModel.getValueAt(row, 2).toString());
			txtDonGia.setText(dataModel.getValueAt(row, 3).toString());
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
