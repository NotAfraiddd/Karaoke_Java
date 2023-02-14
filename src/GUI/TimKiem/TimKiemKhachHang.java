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
import entity.KhachHang;
import entity.KhachHang;
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

public class TimKiemKhachHang extends JPanel implements ActionListener {

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMakhachhang;
	private JTextField txtTenkhachhang;
	private JTextField txtSDT;
	private JTextField txtCMND;
	private JTextField txtTuKhoa;
	private JButton btnTim;
	private JButton btnLamMoi, btnTuKhoa;
	private ArrayList<KhachHang> dskh;
	private KhachHangDao khDao;

	/**
	 * Create the panel.
	 */
	public TimKiemKhachHang() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("TÌM KIẾM KHÁCH HÀNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Khách Hàng", "Tên Khách Hàng", "Số CMND", "Số Điện Thoại" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(295, 26, 110, 25);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số CMND:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(337, 69, 110, 25);
		panel_2.add(lblNewLabel_2);

		txtMakhachhang = new JTextField();
		txtMakhachhang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// txtMakhachhang.setEnabled(false);
		txtMakhachhang.setBounds(433, 26, 203, 25);
		panel_2.add(txtMakhachhang);
		txtMakhachhang.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên khách hàng : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(722, 26, 110, 25);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Số điện thoại :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(743, 69, 110, 25);
		panel_2.add(lblNewLabel_4);

		txtTenkhachhang = new JTextField();
		txtTenkhachhang.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenkhachhang.setBounds(878, 23, 203, 25);
		panel_2.add(txtTenkhachhang);
		txtTenkhachhang.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Từ khóa : ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(337, 120, 110, 25);
		panel_2.add(lblNewLabel_8);

		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTuKhoa.setBounds(433, 120, 648, 25);
		panel_2.add(txtTuKhoa);
		txtTuKhoa.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setBounds(878, 70, 203, 25);
		txtSDT.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);

		txtCMND = new JTextField();
		txtCMND.setBounds(435, 70, 203, 25);
		txtCMND.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(txtCMND);
		txtCMND.setColumns(10);

		btnTim = new JButton("Tìm khách hàng");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(378, 168, 157, 37);
		panel_2.add(btnTim);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBounds(675, 168, 157, 37);
		panel_2.add(btnLamMoi);
		
		btnTuKhoa = new JButton("Tìm từ khóa");
		btnTuKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTuKhoa.setBounds(973, 168, 157, 37);
		panel_2.add(btnTuKhoa);
		
		dskh = new ArrayList<KhachHang>();
		khDao = new KhachHangDao();

		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTim)) {

			try {
				deleteTable();
				String maKhachHang = txtMakhachhang.getText();
				String tenKhachHang = txtTenkhachhang.getText();
				String soCMND = txtCMND.getText();
				String soDT = txtSDT.getText();
				if (maKhachHang.trim().equals("")&&tenKhachHang.trim().equals("")&&soCMND.trim().equals("")&&soDT.trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Chưa nhập dữ liệu cần tìm!");
					updateTable();
				}
				if (!maKhachHang.trim().equals("")) {
					try {
						KhachHang kh = khDao.timKhachHangTheoMa(maKhachHang);

						if (!tenKhachHang.trim().equals("")) {
							if (!kh.getTenKhachHang().equalsIgnoreCase(tenKhachHang)) {
								kh = null;
							}
						}
						if (!soCMND.trim().equals("")) {
							if (!kh.getSoCMND().equalsIgnoreCase(soCMND)) {
								kh = null;
							}
						}
						if (!soDT.trim().equals("")) {
							if (!kh.getSoDT().equalsIgnoreCase(soDT)) {
								kh = null;
							}
						}
						if (kh.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
						} else {
							dataModel.addRow(new Object[] { kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoCMND(),
									kh.getSoDT() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
					}
				} else if (!tenKhachHang.trim().equals("")) {
					try {
						KhachHang kh = khDao.timKhachHangTheoTen(tenKhachHang);
						if (!soCMND.trim().equals("")) {
							if (!kh.getSoCMND().equalsIgnoreCase(soCMND)) {
								kh = null;
							}
						}
						if (!soDT.trim().equals("")) {
							if (!kh.getSoDT().equalsIgnoreCase(soDT)) {
								kh = null;
							}
						}
						if (kh.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
						} else {
							dataModel.addRow(new Object[] { kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoCMND(),
									kh.getSoDT() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
					}
				}else if (!soCMND.trim().equals("")) {
					try {
						ArrayList<KhachHang> dskh = khDao.timKhachHangTheoSoCMND(soCMND);
						for (KhachHang kh : dskh) {
							if (!soDT.trim().equals("")) {
								if (!kh.getSoDT().equalsIgnoreCase(soDT)) {
									kh = null;
								}
							}
							if (!kh.equals(null)) {
								dataModel.addRow(new Object[] { kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoCMND(),
										kh.getSoDT() });
							}
						}
						if (table.getRowCount()==0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
					}
				}else if (!soDT.trim().equals("")) {
					try {
						ArrayList<KhachHang> dskh = khDao.timKhachHangTheoSoDT(soDT);
						for (KhachHang kh : dskh) {
							if (!kh.equals(null)) {
								dataModel.addRow(new Object[] { kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoCMND(),
										kh.getSoDT() });
							}
						}
						if (table.getRowCount()==0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
						}
					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng phù hợp!");
					}
				}
				
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//				deleteTable();
//				String tim = txtTuKhoa.getText().trim();
//				KhachHang ptimMa = khDao.timKhachHangTheoMa(tim);
//				KhachHang ptimCMND = khDao.timKhachHangTheoSoCMND(tim);
//				KhachHang ptimTen = khDao.timKhachHangTheoTen(tim);
//				KhachHang ptimSDT = khDao.timKhachHangTheoSoDT(tim);
//				if (txtTuKhoa.getText().equals("")) {
//					JOptionPane.showMessageDialog(this, "Chưa ghi thông tin cần tìm");
//					updateTable();
//					txtTuKhoa.requestFocus();
//				} else {
//					for (KhachHang i : dskh) {
//						if (tim.equalsIgnoreCase(i.getMaKhachHang()))
//							dataModel.addRow(new Object[] { ptimMa.getMaKhachHang(), ptimMa.getTenKhachHang(),
//									ptimMa.getSoCMND(), ptimMa.getSoDT() });
//						else if (tim.equalsIgnoreCase(i.getSoCMND())) {
//							dataModel.addRow(new Object[] { ptimCMND.getMaKhachHang(), ptimCMND.getTenKhachHang(),
//									ptimCMND.getSoCMND(), ptimCMND.getSoDT() });
//						}
//						else if (tim.equalsIgnoreCase(i.getSoDT())) {
//							dataModel.addRow(new Object[] { ptimSDT.getMaKhachHang(), ptimSDT.getTenKhachHang(),
//									ptimSDT.getSoCMND(), ptimSDT.getSoDT() });
//						}
//						else if(tim.equalsIgnoreCase(i.getTenKhachHang())) {
//							dataModel.addRow(new Object[] { ptimTen.getMaKhachHang(), ptimTen.getTenKhachHang(),
//									ptimTen.getSoCMND(), ptimTen.getSoDT() });
//						}
//					}
//				}
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
		} else if (o.equals(btnLamMoi)) {
			deleteTable();
			updateTable();
		}

	}
}
