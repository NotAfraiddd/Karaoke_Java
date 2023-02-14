package GUI.TimKiem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.Database;
import dao.PhongDao;
import entity.Phong;

public class TimKiemPhong extends JPanel implements ActionListener {

	private JScrollPane scroll;
	public JTable table;
	public DefaultTableModel dataModel;

	private JTextField txtTuKhoa, txtMaphong, txtTenphong;
	private JComboBox<String> cboLau;
	private JButton btnLamMoi;
	private JCheckBox chkTinhTrang;
	private JComponent lblNewLabel_1;
	private JButton btnTim, btnTuKhoa;

	private ArrayList<Phong> dsp;
	private PhongDao p_dao;
	private JComboBox<String> cboLoaiPhong;

	/**
	 * Create the panel.
	 */
	public TimKiemPhong() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("TÌM KIẾM PHÒNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Phòng", "Mã Loại Phòng", "Tên Phòng", "Trạng thái", "Lầu" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Ph\u00F2ng", "M\u00E3 Lo\u1EA1i Ph\u00F2ng", "T\u00EAn Ph\u00F2ng", "Tr\u1EA1ng th\u00E1i", "L\u1EA7u"
			}
		)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Mã phòng : ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(31, 23, 74, 33);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_2 = new JLabel("Loại phòng :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(31, 97, 95, 33);
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

		txtMaphong = new JTextField();
		txtMaphong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		// txtMaphong.setEnabled(false);
		txtMaphong.setBounds(128, 27, 203, 33);
		panel_2.add(txtMaphong);
		txtMaphong.setColumns(10);

		txtTenphong = new JTextField();
		txtTenphong.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenphong.setBounds(596, 27, 203, 29);
		panel_2.add(txtTenphong);
		txtTenphong.setColumns(10);

		cboLau = new JComboBox();
		cboLau.setModel(new DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6" }));
		cboLau.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboLau.setEditable(true);
		cboLau.setBounds(1003, 23, 95, 33);
		panel_2.add(cboLau);

		chkTinhTrang = new JCheckBox("Đang hoạt động");
		chkTinhTrang.setBackground(Color.WHITE);
		chkTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chkTinhTrang.setBounds(568, 105, 119, 21);
		panel_2.add(chkTinhTrang);

		lblNewLabel_8 = new JLabel("Từ khóa:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(926, 101, 77, 25);
		panel_2.add(lblNewLabel_8);
		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTuKhoa.setBounds(1007, 97, 207, 33);
		panel_2.add(txtTuKhoa);
		txtTuKhoa.setColumns(10);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBounds(742, 168, 157, 33);
		panel_2.add(btnLamMoi);

		btnTim = new JButton("Tìm phòng");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(432, 168, 157, 33);
		panel_2.add(btnTim); 
		
		btnTuKhoa = new JButton("Tìm từ khóa");
		btnTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTuKhoa.setBounds(1047, 168, 157, 33);
		panel_2.add(btnTuKhoa);
		
		cboLoaiPhong = new JComboBox();
		cboLoaiPhong.setBounds(128, 104, 203, 34);
		panel_2.add(cboLoaiPhong);
		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);

		dsp = new ArrayList<Phong>();
		p_dao = new PhongDao();

		Database.getInstance().connect();
		//updateTable();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			try {
				deleteTable();
				String maPhong = txtMaphong.getText();
				String tenPhong = txtTenphong.getText();
				String loaiPhong = cboLoaiPhong.getSelectedItem().toString();
				String txtL = cboLau.getSelectedItem().toString();

				boolean trangThai = chkTinhTrang.isSelected();
			
				if (!maPhong.trim().equals("")) {
					try {
						Phong p = p_dao.timPhongTheoMa(maPhong);

						if (!tenPhong.trim().equals("")) {
							if (!p.getTenPhong().equalsIgnoreCase(tenPhong)) {
								p = null;
							}
						}
						if (!loaiPhong.trim().equals("")) {
							if (!p.getLoaiPhong().getMaLoaiPhong().equalsIgnoreCase(loaiPhong)) {
								p = null;
							}
						}
						if (trangThai && p.getTrangThai().equalsIgnoreCase("trống")) {
							p = null;
						}
						if (!trangThai && p.getTrangThai().equalsIgnoreCase("đang hoạt động")) {
							p = null;
						}
						if (!txtL.trim().equals("")) {
							int lau = Integer.parseInt(cboLau.getSelectedItem().toString());
							if (p.getLau() != lau) {
								p = null;
							}
						}

						if (p.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
						} else {
							dataModel.addRow(new Object[] { p.getMaPhong(), p.getLoaiPhong().getTenLoaiPhong(),
									p.getTenPhong(), p.getTrangThai(), p.getLau() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
					}
				} else if (!tenPhong.trim().equals("")) {
					try {
						Phong p = p_dao.timPhongTheoTen(tenPhong);

						if (!loaiPhong.trim().equals("")) {
							if (!p.getLoaiPhong().getMaLoaiPhong().equalsIgnoreCase(loaiPhong)) {
								p = null;
							}
						}
						if (trangThai && p.getTrangThai().equalsIgnoreCase("trống")) {
							p = null;
						}
						if (!trangThai && p.getTrangThai().equalsIgnoreCase("đang hoạt động")) {
							p = null;
						}
						if (!txtL.trim().equals("")) {
							int lau = Integer.parseInt(cboLau.getSelectedItem().toString());
							if (p.getLau() != lau) {
								p = null;
							}
						}

						if (p.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
						} else {
							dataModel.addRow(new Object[] { p.getMaPhong(), p.getLoaiPhong().getMaLoaiPhong(),
									p.getTenPhong(), p.getTrangThai(), p.getLau() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
					}
				} else if (!loaiPhong.trim().equals("")) {
					try {
						Phong p = p_dao.timPhongTheoTen(tenPhong);
						if (trangThai && p.getTrangThai().equalsIgnoreCase("trống")) {
							p = null;
						}
						if (!trangThai && p.getTrangThai().equalsIgnoreCase("đang hoạt động")) {
							p = null;
						}
						if (!txtL.trim().equals("")) {
							int lau = Integer.parseInt(cboLau.getSelectedItem().toString());
							if (p.getLau() != lau) {
								p = null;
							}
						}

						if (p.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
						} else {
							dataModel.addRow(new Object[] { p.getMaPhong(), p.getLoaiPhong().getMaLoaiPhong(),
									p.getTenPhong(), p.getTrangThai(), p.getLau() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
					}
				} else if (!txtL.trim().equals("")) {
					try {
						int lau = Integer.parseInt(cboLau.getSelectedItem().toString());
						ArrayList<Phong> dsphong = p_dao.timPhongTheoLau(lau);
						for (Phong p : dsphong) {
							if (trangThai && p.getTrangThai().equalsIgnoreCase("trống")) {
								p = null;
							}
							if (!trangThai && p.getTrangThai().equalsIgnoreCase("đang hoạt động")) {
								p = null;
							}
							if (!p.equals(null)) {
								dataModel.addRow(new Object[] { p.getMaPhong(), p.getLoaiPhong().getMaLoaiPhong(),
										p.getTenPhong(), p.getTrangThai(), p.getLau() });
							}
						}
						if(table.getRowCount()== 0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
						}
							

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
					}
				}else if (!trangThai) {
					ArrayList<Phong> dsphong = p_dao.timDSPhongTrong();
					for (Phong p : dsphong) {
						if (!p.equals(null)) {
							dataModel.addRow(new Object[] { p.getMaPhong(), p.getLoaiPhong().getMaLoaiPhong(),
									p.getTenPhong(), p.getTrangThai(), p.getLau() });
						}
						if(table.getRowCount()== 0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
						}
					}
				}else if (trangThai) {
					ArrayList<Phong> dsphong = p_dao.timDSPhongDangHoatDong();
					for (Phong p : dsphong) {
						if (!p.equals(null)) {
							dataModel.addRow(new Object[] { p.getMaPhong(), p.getLoaiPhong().getMaLoaiPhong(),
									p.getTenPhong(), p.getTrangThai(), p.getLau() });
						}
						if(table.getRowCount()== 0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy phòng phù hợp!");
						}
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
