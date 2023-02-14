package GUI.TimKiem;

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
import dao.KhachHangDao;
import dao.TaiKhoanDao;
import entity.KhachHang;
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

public class TimKiemTaiKhoan extends JPanel implements ActionListener{

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtTentaikhoan, txtTuKhoa;
	private JTextField txtMatkhau;
	private JButton btnTim;
	private JButton btnLamMoi, btnTuKhoa;
	private ArrayList<TaiKhoan> dstk;
	private TaiKhoanDao tkDao;

	/**
	 * Create the panel.
	 */
	public TimKiemTaiKhoan() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("TÌM KIẾM TÀI KHOẢN  ");
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
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(262, 55, 111, 25);
		panel_2.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(708, 55, 111, 13);
		panel_2.add(lblNewLabel_3);
		
		txtMatkhau = new JTextField();
		txtMatkhau.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtMatkhau.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMatkhau.setBounds(829, 47, 203, 29);
		panel_2.add(txtMatkhau);
		txtMatkhau.setColumns(10);
		
		txtTentaikhoan = new JTextField();
		txtTentaikhoan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTentaikhoan.setEditable(true);
		txtTentaikhoan.setBounds(371, 52, 176, 28);
		panel_2.add(txtTentaikhoan);
		
		JLabel lblNewLabel_8 = new JLabel("Từ khóa: ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(262, 119, 111, 25);
		panel_2.add(lblNewLabel_8);
		
		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTuKhoa.setEditable(true);
		txtTuKhoa.setBounds(371, 117, 660, 28);
		panel_2.add(txtTuKhoa);
		
		btnTim = new JButton("Tìm tài khoản");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(371, 177, 157, 38);
		panel_2.add(btnTim);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBounds(686, 177, 157, 38);
		panel_2.add(btnLamMoi);
		
		btnTuKhoa = new JButton("Tìm từ khóa");
		btnTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTuKhoa.setBounds(1030, 177, 157, 38);
		panel_2.add(btnTuKhoa);
		
		dstk = new ArrayList<TaiKhoan>();
		tkDao = new TaiKhoanDao();

		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);

		Database.getInstance().connect();
		updateTable();

		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			
			try {
				deleteTable();
				String tenTaiKhoan = txtTentaikhoan.getText();
				String matKhau = txtMatkhau.getText();
				if (tenTaiKhoan.trim().equals("")&&matKhau.trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Chưa nhập dữ liệu cần tìm!");
					updateTable();
				}
				if (!tenTaiKhoan.trim().equals("")) {
					try {
						TaiKhoan tk = tkDao.timTKTheoTen(tenTaiKhoan);

						if (!matKhau.trim().equals("")) {
							if (!tk.getMatKhau().equalsIgnoreCase(matKhau)) {
								tk = null;
							}
						}
						if (tk.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản phù hợp!");
						} else {
							dataModel.addRow(new Object[] { tk.getTenDangNhap(), tk.getMatKhau() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản phù hợp!");
					}
				}else if (!matKhau.trim().equals("")) {
					try {
						ArrayList<TaiKhoan> dstk = tkDao.timTKTheoMatKhau(tenTaiKhoan);
						for (TaiKhoan tk : dstk) {
							if (!tk.equals(null)) {
								dataModel.addRow(new Object[] { tk.getTenDangNhap(), tk.getMatKhau() });
							}
						}
						if (table.getRowCount()==0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản phù hợp!");
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản phù hợp!");
					}
				}
//				deleteTable();
//				String tim = txtTentaikhoan.getText().trim();
//				TaiKhoan ptimMa = tkDao.timTKTheoTen(tim);
//				
//				if (txtTentaikhoan.getText().equals("")) {
//					JOptionPane.showMessageDialog(this, "Chưa ghi thông tin cần tìm");
//					updateTable();
//					txtTentaikhoan.requestFocus();
//				} else {
//					for (TaiKhoan i : dstk) {
//						if (tim.equalsIgnoreCase(i.getTenDangNhap()))
//							dataModel.addRow(new Object[] { ptimMa.getTenDangNhap(),ptimMa.getMatKhau()});
//					}
//				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnLamMoi)) {
			deleteTable();
			updateTable();
		}
		
	}
	



}
