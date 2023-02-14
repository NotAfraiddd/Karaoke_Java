package GUI.TimKiem;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import dao.NhanVienDao;
import entity.KhachHang;
import entity.NhanVien;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JRadioButton;

public class TimKiemNhanVien extends JPanel implements ActionListener {

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtManhanvien;
	private JTextField txtTennhanvien;
	private JTextField txtDiachi;
	private JTextField txtSDT;
	private JTextField txtTuKhoa;
	private JButton btnTim;
	private JButton btnLamMoi, btnTuKhoa;
	private JComboBox<String> cbbChucVu;
	private ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
	private NhanVienDao nvDao = new NhanVienDao();
	JRadioButton rdoNam, rdoNu;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TimKiemNhanVien() throws ClassNotFoundException, SQLException {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Nhân Viên", "Tên Nhân Viên", "Địa Chỉ", "Số Điện Thoại", "Chức Vụ", "Giới Tính",
				"Mức Lương" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(26, 27, 115, 13);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(26, 79, 115, 13);
		panel_2.add(lblNewLabel_2);

		txtManhanvien = new JTextField();
		txtManhanvien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtManhanvien.setBounds(151, 24, 203, 26);
		panel_2.add(txtManhanvien);
		txtManhanvien.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên nhân viên: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(461, 27, 125, 13);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Chức vụ :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(496, 79, 74, 13);
		panel_2.add(lblNewLabel_4);

		txtTennhanvien = new JTextField();
		txtTennhanvien.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTennhanvien.setBounds(596, 24, 203, 26);
		panel_2.add(txtTennhanvien);
		txtTennhanvien.setColumns(10);

		btnTim = new JButton("Tìm nhân viên");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(379, 175, 128, 35);
		panel_2.add(btnTim);
		DefaultComboBoxModel<String> cboDataModel = new DefaultComboBoxModel<String>();

		JLabel lblNewLabel_3_1 = new JLabel("Địa chỉ : ");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(915, 27, 89, 13);
		panel_2.add(lblNewLabel_3_1);

		txtDiachi = new JTextField();
		txtDiachi.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDiachi.setColumns(10);
		txtDiachi.setBounds(1002, 23, 203, 26);
		panel_2.add(txtDiachi);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(151, 74, 203, 26);
		panel_2.add(txtSDT);

		cbbChucVu = new JComboBox();
		cbbChucVu.setEditable(true);
		cbbChucVu.setModel(new DefaultComboBoxModel(new String[] { "Nhân viên", "Quản lý" }));
		cbbChucVu.setBounds(596, 74, 89, 26);
		panel_2.add(cbbChucVu);

		JLabel lblNewLabel_2_1 = new JLabel("Giới tính :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(48, 123, 80, 13);
		panel_2.add(lblNewLabel_2_1);

		rdoNam = new JRadioButton("Nam");
		rdoNam.setBackground(Color.WHITE);
		rdoNam.setBounds(151, 120, 103, 21);

		rdoNu = new JRadioButton("Nữ");
		rdoNu.setBackground(Color.WHITE);
		rdoNu.setBounds(151, 145, 103, 21);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdoNam);
		bg.add(rdoNu);
		rdoNam.setSelected(true);
		panel_2.add(rdoNu);
		panel_2.add(rdoNam);
		
		JLabel lblNewLabel_8 = new JLabel("Từ khoá :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(496, 123, 74, 13);
		panel_2.add(lblNewLabel_8);
		
		txtTuKhoa = new JTextField();
		txtTuKhoa.setEditable(true);
		txtTuKhoa.setBounds(595, 115, 610, 26);
		panel_2.add(txtTuKhoa);
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBounds(671, 174, 157, 37);
		panel_2.add(btnLamMoi);
		
		btnTuKhoa = new JButton("Tìm từ khóa");
		btnTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTuKhoa.setBounds(1008, 175, 157, 35);
		panel_2.add(btnTuKhoa);
		
		dsnv = new ArrayList<NhanVien>();
		nvDao = new NhanVienDao();
		// chen su kien
		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			try {
				deleteTable();
				String maNhanVien = txtManhanvien.getText();
				String tenNhanVien = txtTennhanvien.getText();
				String diaChi = txtDiachi.getText();
				String soDT = txtSDT.getText();
				String chucVu = cbbChucVu.getSelectedItem().toString();
				boolean gioiTinh = rdoNam.isSelected();
				ArrayList<NhanVien> dsnv = nvDao.getAllNhanVien();
				if (!maNhanVien.trim().equals("")) {
					try {
						NhanVien nv = nvDao.timNhanVienTheoMa(maNhanVien);

						if (!tenNhanVien.trim().equals("")) {
							if (!nv.getTenNhanVien().equalsIgnoreCase(tenNhanVien)) {
								nv = null;
							}
						}
						if (!diaChi.trim().equals("")) {
							if (!nv.getDiaChi().equalsIgnoreCase(diaChi)) {
								nv = null;
							}
						}
						if (!soDT.trim().equals("")) {
							if (!nv.getSoDT().equalsIgnoreCase(soDT)) {
								nv = null;
							}
						}
						if (!chucVu.trim().equals("")) {
							if (!nv.getChucVu().equalsIgnoreCase(chucVu)) {
								nv = null;
							}
						}
						
						if (rdoNam.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("1")) {
							nv = null;
						}
						if (rdoNu.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("0")) {
							nv = null;
						}
						if (nv.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
						} else {
							dataModel.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getDiaChi(),
									nv.getSoDT(), nv.getChucVu(), nv.getGioiTinh(), nv.getMucLuong() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Mức luơng chỉ được chứa kiểu giá trị só!");
					}

				} else if (!tenNhanVien.trim().equals("")) {
					try {
						NhanVien nv = nvDao.timNhanVienTheoTen(tenNhanVien);

						if (!diaChi.trim().equals("")) {
							if (!nv.getDiaChi().equalsIgnoreCase(diaChi)) {
								nv = null;
							}
						}
						if (!soDT.trim().equals("")) {
							if (!nv.getSoDT().equalsIgnoreCase(soDT)) {
								nv = null;
							}
						}
						if (!chucVu.trim().equals("")) {
							if (!nv.getChucVu().equalsIgnoreCase(chucVu)) {
								nv = null;
							}
						}
						
						if (rdoNam.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("1")) {
							nv = null;
						}
						if (rdoNu.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("0")) {
							nv = null;
						}
						if (nv.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
						} else {
							dataModel.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getDiaChi(),
									nv.getSoDT(), nv.getChucVu(), nv.getGioiTinh(), nv.getMucLuong() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Mức luơng chỉ được chứa kiểu giá trị só!");
					}

				} else if (!diaChi.trim().equals("")) {
					try {
						NhanVien nv = nvDao.timNhanVienTheoDiaChi(diaChi);

						if (!soDT.trim().equals("")) {
							if (!nv.getSoDT().equalsIgnoreCase(soDT)) {
								nv = null;
							}
						}
						if (!chucVu.trim().equals("")) {
							if (!nv.getChucVu().equalsIgnoreCase(chucVu)) {
								nv = null;
							}
						}
						
						if (rdoNam.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("1")) {
							nv = null;
						}
						if (rdoNu.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("0")) {
							nv = null;
						}
						if (nv.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
						} else {
							dataModel.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getDiaChi(),
									nv.getSoDT(), nv.getChucVu(), nv.getGioiTinh(), nv.getMucLuong() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Mức luơng chỉ được chứa kiểu giá trị só!");
					}

				} else if (!soDT.trim().equals("")) {
					try {
						NhanVien nv = nvDao.timNhanVienTheoSDT(soDT);

						if (!chucVu.trim().equals("")) {
							if (!nv.getChucVu().equalsIgnoreCase(chucVu)) {
								nv = null;
							}
						}
						
						if (rdoNam.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("1")) {
							nv = null;
						}
						if (rdoNu.isSelected() && !nv.getGioiTinh().equalsIgnoreCase("0")) {
							nv = null;
						}
						if (nv.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
						} else {
							dataModel.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getDiaChi(),
									nv.getSoDT(), nv.getChucVu(), nv.getGioiTinh(), nv.getMucLuong() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Mức luơng chỉ được chứa kiểu giá trị só!");
					}

				} 

				 else if (!chucVu.trim().equals("")) {
					try {
						ArrayList<NhanVien> dsnvTim = nvDao.timNhanVienTheoChucVu(chucVu);
						for (NhanVien nhanVien : dsnvTim) {
						if (rdoNam.isSelected() && !nhanVien.getGioiTinh().equalsIgnoreCase("1")) {
							nhanVien = null;
						}
						if (rdoNu.isSelected() && !nhanVien.getGioiTinh().equalsIgnoreCase("0")) {
							nhanVien = null;
						}
						if (nhanVien.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
						} else {
							dataModel.addRow(new Object[] { nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(), nhanVien.getDiaChi(),
									nhanVien.getSoDT(), nhanVien.getChucVu(), nhanVien.getGioiTinh(), nhanVien.getMucLuong() });
						}
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp!");
					}
				} else if (rdoNam.isSelected()) {
					ArrayList<NhanVien> dsnvTim = nvDao.timNhanVienCoGioiTinhNam();
					for (NhanVien nhanVien : dsnvTim) {
						dataModel.addRow(new Object[] { nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(),
								nhanVien.getDiaChi(), nhanVien.getSoDT(), nhanVien.getChucVu(), nhanVien.getGioiTinh(),
								nhanVien.getMucLuong() });
					}

				} else if (rdoNu.isSelected()) {
					ArrayList<NhanVien> dsnvTim = nvDao.timNhanVienCoGioiTinhNu();
					for (NhanVien nhanVien : dsnvTim) {
						dataModel.addRow(new Object[] { nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(),
								nhanVien.getDiaChi(), nhanVien.getSoDT(), nhanVien.getChucVu(), nhanVien.getGioiTinh(),
								nhanVien.getMucLuong() });
					}
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnLamMoi)) {
			try {
				deleteTable();
				updateTable();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
