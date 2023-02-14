package GUI.TimKiem;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridBagLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connect.Database;
import dao.HoaDonDao;
import entity.HoaDon;
import entity.KhachHang;

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

public class TimKiemHoaDon extends JPanel implements ActionListener {

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtManhanvien;
	private JTextField txtNgaylaphoadon;
	private JTextField txtTuKhoa;
	private JButton btnTim;
	private JButton btnTuKhoa;
	private JButton btnLamMoi;
	private HoaDonDao hdDao;
	private ArrayList<HoaDon> dshd;
	private JTextField txtMaDon;
	private JTextField txtKH;

	/**
	 * Create the panel.
	 */
	public TimKiemHoaDon() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridBagLayout());
		JLabel lblNewLabel = new JLabel("TÌM KIẾM HÓA ĐƠN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.setPreferredSize(new Dimension(10, 70));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Ngày lập hóa đơn", "Thành tiền" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(scroll, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(309, 29, 89, 16);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày lâp hóa đơn:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(280, 90, 107, 19);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mã khách hàng : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(739, 29, 120, 16);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mã nhân viên : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(748, 91, 203, 17);
		panel_2.add(lblNewLabel_4);
		
		txtManhanvien = new JTextField(); 
		txtManhanvien.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtManhanvien.setBounds(886, 84, 203, 31);
		panel_2.add(txtManhanvien);
		txtManhanvien.setColumns(10);

		DefaultComboBoxModel<String> cboDataModel = new DefaultComboBoxModel<String>();
		
		txtNgaylaphoadon = new JTextField();
		txtNgaylaphoadon.setBounds(441, 85, 203, 31);
		panel_2.add(txtNgaylaphoadon);
		txtNgaylaphoadon.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Từ khóa:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(326, 146, 107, 19);
		panel_2.add(lblNewLabel_8);

		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTuKhoa.setBounds(441, 140, 648, 31);
		panel_2.add(txtTuKhoa);
		txtTuKhoa.setColumns(10);

		btnTim = new JButton("Tìm hóa đơn");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(291, 181, 157, 42);
		panel_2.add(btnTim);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoi.setBounds(663, 181, 157, 42);
		panel_2.add(btnLamMoi);
		
		btnTuKhoa = new JButton("Tìm từ khóa");
		btnTuKhoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTuKhoa.setBounds(1015, 181, 157, 42);
		panel_2.add(btnTuKhoa);
		
		txtMaDon = new JTextField();
		txtMaDon.setColumns(10);
		txtMaDon.setBounds(441, 23, 203, 31);
		panel_2.add(txtMaDon);
		
		txtKH = new JTextField();
		txtKH.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtKH.setColumns(10);
		txtKH.setBounds(886, 22, 203, 31);
		panel_2.add(txtKH);
		
		btnTuKhoa.addActionListener(this);
		btnTim.addActionListener(this);
		
		hdDao = new HoaDonDao();
		dshd = new ArrayList<HoaDon>();

		Database.getInstance().connect();
		updateTable();
	}

	public void updateTable() {
		dshd = hdDao.getAllHoaDon();
		for (HoaDon i : dshd) {
			dataModel.addRow(new Object[] { i.getMaHoaDon(), i.getKhachHang().getMaKhachHang(),
					i.getNhanVien().getMaNhanVien(), i.getNgayLapHoaDon(), i.getThanhTien() });
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
				String maHoaDon = txtMaDon.getText();
				String maKhachHang = txtKH.getText();
				String maNhanVien = txtManhanvien.getText();
				String ngayLapHoaDon = txtNgaylaphoadon.getText();
				if (maHoaDon.trim().equals("")&&maKhachHang.trim().equals("")&&maNhanVien.trim().equals("")&&ngayLapHoaDon.trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Chưa nhập dữ liệu cần tìm!");
					updateTable();
				}
				if (!maHoaDon.trim().equals("")) {
					HoaDon hd = hdDao.timHDTheoMa(maHoaDon);
					if (!maKhachHang.trim().equals("")) {
						if (!hd.getKhachHang().getMaKhachHang().equalsIgnoreCase(maKhachHang)) {
							hd = null;
						}
						
					}
					if (!maNhanVien.trim().equals("")) {
						if (!hd.getNhanVien().getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
							hd = null;
						}
					}
					if (!ngayLapHoaDon.trim().equals("")) {
						if (!hd.getNgayLapHoaDon().equals(new Date(ngayLapHoaDon))) {
							hd = null;
						}
					}
					if (hd.equals(null)) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp!");
					} else {
						dataModel.addRow(new Object[] { hd.getMaHoaDon(), hd.getKhachHang().getMaKhachHang(), hd.getNhanVien().getMaNhanVien(),
								hd.getNgayLapHoaDon(), hd.getThanhTien() });
					}
				}else if (!maKhachHang.trim().equals("")) {
					ArrayList<HoaDon> dshd = hdDao.timHDTheoMaKH(maKhachHang);
					for (HoaDon hd : dshd) {
						if (!maNhanVien.trim().equals("")) {
							if (!hd.getNhanVien().getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
								hd = null;
							}
						}
						if (!ngayLapHoaDon.trim().equals("")) {
							if (!hd.getNgayLapHoaDon().equals(ngayLapHoaDon)) {
								hd = null;
							}
						}	
						if (!hd.equals(null)) {
							dataModel.addRow(new Object[] { hd.getMaHoaDon(), hd.getKhachHang().getMaKhachHang(), hd.getNhanVien().getMaNhanVien(),
									hd.getNgayLapHoaDon(), hd.getThanhTien() });
						}
					}
					if (table.getRowCount()==0) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp!");
					}
					
				}else if (!maNhanVien.trim().equals("")) {
					ArrayList<HoaDon> dshd = hdDao.timHDTheoMaNV(maNhanVien);
					for (HoaDon hd : dshd) {
						if (!ngayLapHoaDon.trim().equals("")) {
							if (!hd.getNgayLapHoaDon().equals(ngayLapHoaDon)) {
								hd = null;
							}
						}	
						if (!hd.equals(null)) {
							dataModel.addRow(new Object[] { hd.getMaHoaDon(), hd.getKhachHang().getMaKhachHang(), hd.getNhanVien().getMaNhanVien(),
									hd.getNgayLapHoaDon(), hd.getThanhTien() });
						}
					}
					if (table.getRowCount()==0) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp!");
					}
					
				}
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp!");;
			}
		} else if (o.equals(btnLamMoi)) {
			deleteTable();
			updateTable();
		}

	}
}
