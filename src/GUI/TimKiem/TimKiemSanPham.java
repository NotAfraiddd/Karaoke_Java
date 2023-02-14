package GUI.TimKiem;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridBagLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connect.Database;
import dao.KhachHangDao;
import dao.SanPhamDao;
import entity.SanPham;
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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TimKiemSanPham extends JPanel implements ActionListener {

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtTuKhoa, txtMasanpham, txtTensanpham, txtLoaisanpham;
	private JComboBox<String> cboLoaiSanPham;
	private JButton btnTim;
	private JButton btnLamMoi, btnTuKhoa;
	private ArrayList<SanPham> dssp;
	private SanPhamDao spDao;

	/**
	 * Create the panel.
	 */
	public TimKiemSanPham() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("TÌM KIẾM SẢN PHẨM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(281, 26, 102, 25);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Loại sản phẩm:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(281, 87, 102, 25);
		panel_2.add(lblNewLabel_2);

		txtMasanpham = new JTextField();
		txtMasanpham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMasanpham.setBounds(406, 23, 203, 36);
		panel_2.add(txtMasanpham);
		txtMasanpham.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(716, 26, 102, 25);
		panel_2.add(lblNewLabel_3);

		txtTensanpham = new JTextField();
		txtTensanpham.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTensanpham.setBounds(851, 23, 203, 36);
		panel_2.add(txtTensanpham);
		txtTensanpham.setColumns(10);

//		JButton btnTim = new JButton("Tìm sản phẩm");
//		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
//		btnTim.setBounds(654, 130, 127, 21);
//		panel_2.add(btnTim);

		cboLoaiSanPham = new JComboBox();
		cboLoaiSanPham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboLoaiSanPham.setModel(new DefaultComboBoxModel(new String[] { "", "Nước", "Bánh", "Đồ ăn" }));
		cboLoaiSanPham.setEditable(true);
		cboLoaiSanPham.setBounds(406, 81, 203, 36);
		panel_2.add(cboLoaiSanPham);

		JLabel lblNewLabel_8 = new JLabel("Từ khóa:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(281, 148, 80, 25);
		panel_2.add(lblNewLabel_8);

		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTuKhoa.setBounds(404, 145, 650, 30);
		panel_2.add(txtTuKhoa);
		txtTuKhoa.setColumns(10);

		btnTim = new JButton("Tìm sản phẩm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(494, 197, 157, 36);
		panel_2.add(btnTim);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBounds(774, 197, 157, 36);
		panel_2.add(btnLamMoi);

		btnTuKhoa = new JButton("Tìm từ khoá");
		btnTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTuKhoa.setBounds(1069, 197, 157, 36);
		panel_2.add(btnTuKhoa);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Đơn Giá" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

//chen su kien
		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			try {
				deleteTable();
				String maSanPham = txtMasanpham.getText();
				String tenSanPham = txtTensanpham.getText();
				String loaiSanPham = cboLoaiSanPham.getSelectedItem().toString();
				if (maSanPham.trim().equals("") && tenSanPham.trim().equals("") && loaiSanPham.trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Chưa nhập dữ liệu cần tìm!");
					updateTable();
				}
				if (!maSanPham.trim().equals("")) {
					try {
						SanPham sp = spDao.timSanPhamTheoMa(maSanPham);

						if (!tenSanPham.trim().equals("")) {
							if (!sp.getTenSanPham().equalsIgnoreCase(tenSanPham)) {
								sp = null;
							}
						}
						if (!loaiSanPham.trim().equals("")) {
							if (!sp.getLoaiSanPham().equalsIgnoreCase(loaiSanPham)) {
								sp = null;
							}
						}

						if (sp.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!");
						} else {
							dataModel.addRow(new Object[] { sp.getMaSanPham(), sp.getTenSanPham(), sp.getLoaiSanPham(),
									sp.getDonGia() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!");
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Đơn giá chỉ được chứa kiểu giá trị só!");
					}
				} else if (!tenSanPham.trim().equals("")) {
					try {
						SanPham sp = spDao.timSanPhamTheoTen(tenSanPham);

						if (!loaiSanPham.trim().equals("")) {
							if (!sp.getLoaiSanPham().equalsIgnoreCase(loaiSanPham)) {
								sp = null;
							}
						}

						if (sp.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!");
						} else {
							dataModel.addRow(new Object[] { sp.getMaSanPham(), sp.getTenSanPham(), sp.getLoaiSanPham(),
									sp.getDonGia() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!");
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Đơn giá chỉ được chứa kiểu giá trị só!");
					}
				} else if (!loaiSanPham.trim().equals("")) {
					try {
						ArrayList<SanPham> dsspTim = spDao.timSanPhamTheoLoaiSanPham(loaiSanPham);
						for (SanPham sanPham : dsspTim) {

							if (sanPham.equals(null)) {
								JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!");
							} else {
								dataModel.addRow(new Object[] { sanPham.getMaSanPham(), sanPham.getTenSanPham(),
										sanPham.getLoaiSanPham(), sanPham.getDonGia() });
							}
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!");
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Đơn giá chỉ được chứa kiểu giá trị só!");
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnLamMoi)) {
			deleteTable();
			updateTable();
		}

	}

}
