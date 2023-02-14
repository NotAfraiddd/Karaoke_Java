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
import dao.LoaiPhongDao;
import entity.LoaiPhong;
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

public class TimKiemLoaiPhong extends JPanel implements ActionListener {

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMaloai;
	private JTextField txtTenloai;
	private JTextField txtSoCho;
	private JTextField txtTuKhoa;
	private JComboBox<String> cboSoCho;
	private ArrayList<LoaiPhong> dslp;
	private LoaiPhongDao lp_dao;
	private JButton btnTim, btnTuKhoa;
	private JButton btnLamMoi;

	/**
	 * Create the panel.
	 */
	public TimKiemLoaiPhong() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("TÌM KIẾM LOẠI PHÒNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 470));
		String[] tieuDe = { "Loại Phòng", "Tên Loại Phòng", "Số chỗ ", "Đơn giá" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Loại phòng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(302, 25, 110, 25);
		panel_2.add(lblNewLabel_1);

		txtMaloai = new JTextField();
		txtMaloai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaloai.setBounds(442, 22, 203, 31);
		panel_2.add(txtMaloai);
		txtMaloai.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tên loại phòng : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(712, 25, 110, 25);
		panel_2.add(lblNewLabel_3);

		txtTenloai = new JTextField();
		txtTenloai.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTenloai.setBounds(876, 22, 203, 31);
		panel_2.add(txtTenloai);
		txtTenloai.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Số chỗ :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(337, 82, 111, 25);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_8 = new JLabel("Từ khoá :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(337, 141, 111, 25);
		panel_2.add(lblNewLabel_8);

		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTuKhoa.setBounds(439, 138, 640, 31);
		txtTuKhoa.setColumns(10);
		panel_2.add(txtTuKhoa);

		cboSoCho = new JComboBox();
		cboSoCho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboSoCho.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15"}));
		cboSoCho.setBackground(Color.WHITE);
		cboSoCho.setEditable(true);
		cboSoCho.setBounds(442, 82, 44, 31);
		panel_2.add(cboSoCho);

		btnTim = new JButton("Tìm loại phòng");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(467, 187, 157, 39);
		panel_2.add(btnTim);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBounds(751, 187, 157, 39);
		panel_2.add(btnLamMoi);

		btnTuKhoa = new JButton("Tìm từ khóa");
		btnTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTuKhoa.setBounds(1009, 187, 157, 39);
		panel_2.add(btnTuKhoa);
		// GAN SU KIEN
		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);

		dslp = new ArrayList<LoaiPhong>();
		lp_dao = new LoaiPhongDao();

		Database.getInstance().connect();
		updateTable();

	}

	public void updateTable() {
		dslp = lp_dao.getAllDSLoaiPhong();
		for (LoaiPhong i : dslp) {
			dataModel.addRow(new Object[] { i.getMaLoaiPhong(), i.getTenLoaiPhong(), i.getSoCho(), i.getDonGia() });
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
				String maLoaiPhong = txtMaloai.getText();
				String tenLoaiPhong = txtTenloai.getText();
				String txtSC = cboSoCho.getSelectedItem().toString();
				ArrayList<LoaiPhong> dsnv = lp_dao.getAllDSLoaiPhong();
				if (maLoaiPhong.trim().equals("")&&tenLoaiPhong.trim().equals("")&&txtSC.trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Chưa nhập dữ liệu cần tìm!");
					updateTable();
				}
				if (!maLoaiPhong.trim().equals("")) {
					try {
						LoaiPhong lp = lp_dao.timLoaiPhongTheoMa(maLoaiPhong);
						if (!tenLoaiPhong.trim().equals("")) {
							if (!lp.getTenLoaiPhong().equalsIgnoreCase(tenLoaiPhong)) {
								lp = null;
							}
						}
						if (!txtSC.trim().equals("")) {
							int soCho = Integer.parseInt(txtSC);
							if (lp.getSoCho() != soCho) {
								lp = null;
							}
						}

						if (lp.equals(null)) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy loại phòng phù hợp!");
						} else {
							dataModel.addRow(new Object[] { lp.getMaLoaiPhong(), lp.getTenLoaiPhong(), lp.getSoCho(),
									lp.getDonGia() });
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy loại phòng phù hợp!");
					}

				} else if (!tenLoaiPhong.trim().equals("")) {
					try {
						ArrayList<LoaiPhong> dslp = lp_dao.timLoaiPhongTheoTen(tenLoaiPhong);
						for (LoaiPhong lp : dslp) {
							if (!txtSC.trim().equals("")) {
								int soCho = Integer.parseInt(txtSC);
								if (lp.getSoCho() != soCho) {
									lp = null;
								}
							}

							if (!lp.equals(null)) {
								dataModel.addRow(new Object[] { lp.getMaLoaiPhong(), lp.getTenLoaiPhong(), lp.getSoCho(),
										lp.getDonGia() });
							}
						}
						if (table.getRowCount() == 0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy loại phòng phù hợp!");
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy loại phòng phù hợp!");
					}

				} else if (!txtSC.trim().equals("")) {
					try {
						int soCho = Integer.parseInt(txtSC);
						ArrayList<LoaiPhong> dslp = lp_dao.timLoaiPhongTheoSoCho(soCho);
						for (LoaiPhong lp : dslp) {

							if (!lp.equals(null)) {
								dataModel.addRow(new Object[] { lp.getMaLoaiPhong(), lp.getTenLoaiPhong(),
										lp.getSoCho(), lp.getDonGia() });
							}

						}
						if (table.getRowCount() == 0) {
							JOptionPane.showMessageDialog(this, "Không tìm thấy loại phòng phù hợp!");
						}

					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy loại phòng phù hợp!");
					}
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnLamMoi)) {
			deleteTable();
			updateTable();
		}
	}
}
