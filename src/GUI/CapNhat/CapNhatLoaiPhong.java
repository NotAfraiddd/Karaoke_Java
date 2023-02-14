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
import dao.LoaiPhongDao;
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

public class CapNhatLoaiPhong extends JPanel implements ActionListener, MouseListener{
	
	private ArrayList<LoaiPhong> dslp;
	private LoaiPhongDao lp_dao = new LoaiPhongDao();
	
	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMaloai;
	private JTextField txtTenloai;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtDonGia;
	private JButton btnThemloai;
	private JButton btnSualoai;
	private JButton btnXoaloai;
	private JButton btnLamrong;
	private JComboBox cboSoCho;

	/**
	 * Create the panel.
	 */
	public CapNhatLoaiPhong() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("CẬP NHẬT LOẠI PHÒNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		this.add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = {  "Loại Phòng", "Tên Loại Phòng","Số chỗ ", "Đơn giá" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe,0)),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);
		this.add(panel_1,BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Loại phòng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(302, 53, 110,25);
		panel_2.add(lblNewLabel_1);
		
		txtMaloai = new JTextField();
		//txtMaloai.setEnabled(false);
		txtMaloai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaloai.setBounds(442, 50, 203, 25);
		panel_2.add(txtMaloai);
		txtMaloai.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tên loại phòng : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(712, 53, 110,25);
		panel_2.add(lblNewLabel_3);
		
		txtTenloai = new JTextField();
		txtTenloai.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenloai.setBounds(876, 49, 203, 25);
		panel_2.add(txtTenloai);
		txtTenloai.setColumns(10);
		
		btnThemloai = new JButton("Thêm loại phòng");
		btnThemloai.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemloai.setBounds(371, 144, 157, 41);
		panel_2.add(btnThemloai);
		
		btnSualoai = new JButton("Sửa thông tin");
		btnSualoai.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSualoai.setBounds(538, 144, 176, 41);
		panel_2.add(btnSualoai);
		
		btnXoaloai = new JButton("Xóa loại phòng");
		btnXoaloai.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaloai.setBounds(724, 144, 148, 41);
		panel_2.add(btnXoaloai);
		
		btnLamrong = new JButton("Làm rỗng");
		btnLamrong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamrong.setBounds(882, 144, 163, 41);
		panel_2.add(btnLamrong);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số chỗ :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(337, 90, 111, 25);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Đơn giá :");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(755, 90, 111, 25);
		panel_2.add(lblNewLabel_3_1);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDonGia.setText("0");
		txtDonGia.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(876, 90, 203, 25);
		panel_2.add(txtDonGia);
		
		cboSoCho = new JComboBox();
		cboSoCho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboSoCho.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15"}));
		cboSoCho.setBackground(Color.WHITE);
		cboSoCho.setEditable(true);
		cboSoCho.setBounds(442, 93, 203, 25);
		panel_2.add(cboSoCho);
		
		//this.add(panel_2,BorderLayout.CENTER);
		// gắn sự kiện
		btnLamrong.addActionListener(this);
		btnSualoai.addActionListener(this);
		btnThemloai.addActionListener(this);
		btnXoaloai.addActionListener(this);
		
		btnLamrong.addMouseListener(this);
		btnSualoai.addMouseListener(this);
		btnThemloai.addMouseListener(this);
		btnXoaloai.addMouseListener(this);
		table.addMouseListener(this);
		
		dslp= new ArrayList<LoaiPhong>();
		lp_dao = new LoaiPhongDao();
		
		Database.getInstance().connect();
		updateTable();
		
	}

	public void updateTable() {
		dslp=lp_dao.getAllDSLoaiPhong();
		for (LoaiPhong i : dslp) {
			dataModel.addRow(new Object[] {
					i.getMaLoaiPhong(), i.getTenLoaiPhong(), i.getSoCho(), i.getDonGia()
			});
		}
	}
	public void deleteTable() {
		int y = dataModel.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModel.removeRow(0);
		}
	}
	public void xoaRong() {
		txtMaloai.setText("");
		txtTenloai.setText("");
		cboSoCho.setSelectedItem(5);
		txtDonGia.setText("");
		txtMaloai.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemloai)) {
			if (txtMaloai.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập loại phòng");
				txtMaloai.requestFocus();
			} else if (txtTenloai.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên loại phòng");
				txtTenloai.requestFocus();
			} else if (txtDonGia.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá tiền");
				txtDonGia.requestFocus();
			} else {
				boolean a = true;
				try {
					String ma = txtMaloai.getText();
					String ten = txtTenloai.getText();
					int soCho = Integer.parseInt(cboSoCho.getSelectedItem().toString());
					double donGia = Double.parseDouble(txtDonGia.getText());
					LoaiPhong lp = new LoaiPhong(ma, ten, soCho, donGia);
					lp_dao.themLoaiPhong(lp);
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

		} else if (o.equals(btnXoaloai)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				lp_dao.xoaLoaiPhong(dataModel.getValueAt(row, 0).toString()); // sua thanh so 1
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				else
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn hàng cần xóa");
			}
		} else if (o.equals(btnSualoai)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				String ma = dataModel.getValueAt(row, 0).toString();
				String ten = txtTenloai.getText();
				int soCho = Integer.parseInt(cboSoCho.getSelectedItem().toString());
				double donGia = Double.parseDouble(txtDonGia.getText());
				LoaiPhong lp = new LoaiPhong(ma, ten, soCho, donGia);
				a = lp_dao.capNhatLoaiPhong(lp);
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
			txtMaloai.setText(dataModel.getValueAt(row, 0).toString());
			txtTenloai.setText(dataModel.getValueAt(row, 1).toString());
			cboSoCho.setSelectedItem(dataModel.getValueAt(row, 2).toString());
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
