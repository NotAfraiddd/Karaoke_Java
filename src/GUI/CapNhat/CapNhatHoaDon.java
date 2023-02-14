package GUI.CapNhat;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connect.Database;
import dao.ChiTietHoaDonPhongDao;
import dao.ChiTietHoaDonSanPhamDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiPhongDao;
import dao.NhanVienDao;
import dao.PhongDao;
import dao.SanPhamDao;
import entity.ChiTietHoaDonPhong;
import entity.ChiTietHoaDonSanPham;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import entity.SanPham;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class CapNhatHoaDon extends JPanel implements MouseListener {

	private JTable tableCTSanPham;
	private JTable tableSanPham;
	private JTable tableCTPhong;
	private JTable tableHoaDon;
	private JTable tablePhong;
	private JTable tableKhachHang;
	private DefaultTableModel dataModelHoaDon;
	private JScrollPane scrollHoaDon;
	private DefaultTableModel dataModelCTSanPham;
	private DefaultTableModel dataModelSanPham;
	private DefaultTableModel dataModelCTPhong;
	private DefaultTableModel dataModelPhong;
	private DefaultTableModel dataModelKH;
	private JDialog jdialogChonSanPham = new JDialog();
	private JDialog jdialogChonPhong = new JDialog();
	private JDialog jdialogKH = new JDialog();
	private JScrollPane scrollCTSanPham_1;
	private JScrollPane scrollCTPhong_1;
	private JButton btnHuysanpham;
	private ArrayList<HoaDon> dshd;
	private ArrayList<SanPham> dssp;
	private ArrayList<Phong> dsp;
	private HoaDonDao hdDao;
	private SanPhamDao spDao;
	private PhongDao pDao;
	private LoaiPhongDao lpDao;
	private ArrayList<ChiTietHoaDonPhong> dsctp;
	private ArrayList<ChiTietHoaDonSanPham> dsctsp;
	private ChiTietHoaDonSanPhamDao ctspDao;
	private ChiTietHoaDonPhongDao ctpDao;
	private ArrayList<KhachHang> dskh;
	private KhachHangDao khDao;
	private JButton btnThanhToan;
	private NhanVienDao nvDao;
	private JButton btnTaoHoaDon;
	private JButton btnSuaHoaDon;
	private JComboBox cboMaKH;
	private JComboBox cboMaNV;
	private ArrayList<NhanVien> dsnv;
	private Timestamp ngayLap;
	private JComboBox cboSoLuong;
	private JComboBox cboMaPhong;
	private JComboBox cboHDvaP;
	private JButton btnRoiphong;
	private JButton btnXemphongtrong;
	private JButton btnThemsp;
	private JComboBox cboSPvHD;
	private JComboBox cboMSP;
	private JButton btnThemPvaHD;
	private JButton btnXoahuyphong;
	private Timestamp ngayBatDau;
	private Timestamp ngayKetThuc;
	private int row;

	/**
	 * Create the panel.
	 */
	public CapNhatHoaDon() {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("CẬP NHẬT HÓA ĐƠN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JPanel panelHoaDon = new JPanel();
		panelHoaDon.setBounds(0, 0, 532, 743);
		panelHoaDon.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"H\u00F3a \u0110\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panelHoaDon);

		String[] tieuDeHoaDon = { "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Ngày lập", "Thành tiền" };
		panelHoaDon.setLayout(null);
		scrollHoaDon = new JScrollPane(
				tableHoaDon = new JTable(dataModelHoaDon = new DefaultTableModel(tieuDeHoaDon, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollHoaDon.setBounds(6, 121, 522, 612);
		panelHoaDon.add(scrollHoaDon);

		JPanel pnlformHoaDon = new JPanel();
		pnlformHoaDon.setBackground(Color.WHITE);
		pnlformHoaDon.setBounds(6, 15, 522, 211);
		panelHoaDon.add(pnlformHoaDon);
		pnlformHoaDon.setLayout(null);

		JButton btnChonkhachhang = new JButton("Xem khách hàng");
		btnChonkhachhang.addActionListener(new ActionListener() {

			private final JPanel contentPanel = new JPanel();
			private JTextField txtTuKhoa;
			private JScrollPane scrollKH;
			private JButton btnTimKhachhang;
			private JButton btnLamMoiKH;

			public void actionPerformed(ActionEvent e) {

				jdialogKH.setSize(600, 400);
				jdialogKH.setLocationRelativeTo(null);

				jdialogKH.setBounds(100, 100, 1033, 659);
				jdialogKH.getContentPane().setLayout(new BorderLayout());
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				jdialogKH.getContentPane().add(contentPanel, BorderLayout.CENTER);
				contentPanel.setLayout(new BorderLayout(0, 0));
				{
					String[] tieuKH = { "Mã khách hàng", "Tên khách hàng", "Số CMND", "Số điện thoại" };
					scrollKH = new JScrollPane(
							tableKhachHang = new JTable(dataModelKH = new DefaultTableModel(tieuKH, 0)),
							JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					contentPanel.add(scrollKH, BorderLayout.SOUTH);
				}
				{
					JPanel panel = new JPanel();
					contentPanel.add(panel, BorderLayout.CENTER);
					panel.setLayout(null);

					JLabel lblNewLabel_1 = new JLabel("Từ khóa:");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(400, 53, 111, 25);
					panel.add(lblNewLabel_1);

					txtTuKhoa = new JTextField();
					txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 13));
					txtTuKhoa.setColumns(10);
					txtTuKhoa.setBounds(500, 50, 203, 25);
					panel.add(txtTuKhoa);

					btnTimKhachhang = new JButton("Tìm khách hàng");
					btnTimKhachhang.setFont(new Font("Tahoma", Font.BOLD, 13));
					btnTimKhachhang.setBounds(390, 100, 180, 25);
					panel.add(btnTimKhachhang);

					btnLamMoiKH = new JButton("Làm mới");
					btnLamMoiKH.setFont(new Font("Tahoma", Font.BOLD, 13));
					btnLamMoiKH.setBounds(591, 100, 120, 25);
					panel.add(btnLamMoiKH);

					updateTableKH();
					btnTimKhachhang.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object o = e.getSource();
							if (o.equals(btnTimKhachhang)) {
								try {
									deleteTableKH();
									String tim = txtTuKhoa.getText().trim();
									KhachHang ptimMa = khDao.timKhachHangTheoMa(tim);
									ArrayList<KhachHang> ptimCMND = khDao.timKhachHangTheoSoCMND(tim);
									KhachHang ptimTen = khDao.timKhachHangTheoTen(tim);
									ArrayList<KhachHang> ptimSDT = khDao.timKhachHangTheoSoDT(tim);
									if (txtTuKhoa.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Chưa ghi thông tin cần tìm");
										updateTableKH();
										txtTuKhoa.requestFocus();
									} else {
										for (KhachHang i : dskh) {
											if (tim.equalsIgnoreCase(i.getMaKhachHang()))
												dataModelKH.addRow(new Object[] { ptimMa.getMaKhachHang(),
														ptimMa.getTenKhachHang(), ptimMa.getSoCMND(),
														ptimMa.getSoDT() });
											else if (tim.equalsIgnoreCase(i.getSoCMND())) {
												for (KhachHang kh : ptimCMND) {
													dataModelKH.addRow(new Object[] { kh.getMaKhachHang(),
															kh.getTenKhachHang(), kh.getSoCMND(), kh.getSoDT() });
												}

											} else if (tim.equalsIgnoreCase(i.getSoDT())) {
												for (KhachHang kh : ptimSDT) {
													dataModelKH.addRow(new Object[] { kh.getMaKhachHang(),
															kh.getTenKhachHang(), kh.getSoCMND(), kh.getSoDT() });
												}

											} else if (tim.equalsIgnoreCase(i.getTenKhachHang())) {
												dataModelKH.addRow(new Object[] { ptimTen.getMaKhachHang(),
														ptimTen.getTenKhachHang(), ptimTen.getSoCMND(),
														ptimTen.getSoDT() });
											}
										}
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}

						}
					});
					btnLamMoiKH.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Object o = e.getSource();
							if (o.equals(btnLamMoiKH)) {
								deleteTableKH();
								updateTableKH();
							}

						}
					});
				}
				{
					JPanel buttonPane = new JPanel();
					jdialogKH.getContentPane().add(buttonPane, BorderLayout.SOUTH);
					buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						JButton cancelButtonKH = new JButton("Hủy");
						cancelButtonKH.setActionCommand("Cancel");
						buttonPane.add(cancelButtonKH);
						cancelButtonKH.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								jdialogKH.setVisible(false);
							}
						});
					}
					jdialogKH.setVisible(false);
					jdialogKH.setVisible(true);
				}

			}
		});
		btnChonkhachhang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnChonkhachhang.setBounds(382, 10, 130, 33);
		pnlformHoaDon.add(btnChonkhachhang);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setForeground(Color.BLACK);
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThanhToan.setBounds(382, 53, 130, 33);
		pnlformHoaDon.add(btnThanhToan);
		btnThanhToan.addActionListener(new ActionListener() {

			private double tongTienSP;
			private double tongTienphong;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tableCTPhong.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng ở phòng cần thanh toán");
				else {
					try {
						ngayBatDau = (Timestamp) dataModelCTPhong.getValueAt(tableCTPhong.getSelectedRow(), 2);
						Date ngayBD = new Date(ngayBatDau.getTime());
						int gioBatDau = ngayBD.getHours();
						System.out.println("Gio bat dau: " + gioBatDau);
						ngayKetThuc = (Timestamp) dataModelCTPhong.getValueAt(tableCTPhong.getSelectedRow(), 3);
						Date ngayKT = new Date(ngayKetThuc.getTime());
						int gioKetThuc = ngayKT.getHours();
						System.out.println("Gio ket thuc: " + gioKetThuc);
						int tongGio = 0;
						if ((gioKetThuc - gioBatDau) > 1)
							tongGio = tongGio + (gioKetThuc - gioBatDau);
						else
							tongGio = 1;
						System.out.println("Tong gio: " + tongGio);
						ArrayList<ChiTietHoaDonPhong> dshdp = new ArrayList<ChiTietHoaDonPhong>();
						dshdp = ctpDao
								.getDSTheoMaHD((String) dataModelCTPhong.getValueAt(tableCTPhong.getSelectedRow(), 0));

						tongTienphong = 0;
						for (int i = 0; i < dshdp.size(); i++) {
							tongTienphong = (int) (tongTienphong
									+ pDao.getTienPhong(dshdp.get(i).getPhong().getMaPhong()) * tongGio);
						}
						System.out.println("Tong tien phong: " + tongTienphong);

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				if (tableCTSanPham.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng ở sản phẩm cần thanh toán");
				} else {
					ArrayList<ChiTietHoaDonSanPham> dshdsp = new ArrayList<ChiTietHoaDonSanPham>();
					dshdsp = ctspDao
							.getDSTheoMaHD((String) dataModelCTSanPham.getValueAt(tableCTSanPham.getSelectedRow(), 0));

					tongTienSP = 0;
					for (int i = 0; i < dshdsp.size(); i++) {
						try {
							tongTienSP = (tongTienSP + spDao.getTienSP(dshdsp.get(i).getSanPham().getMaSanPham())
									* spDao.getSoLuongSp(dshdsp.get(i).getSanPham().getMaSanPham()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("Tien san pham: " + tongTienSP);

				}
				double thanhTien = tongTienphong + tongTienSP;
				System.out.println("Thanhtien: " + thanhTien);
				hdDao.capNhatTien((String) dataModelHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0), thanhTien);
				deleteHDDaTT();
			}
		});
		btnTaoHoaDon = new JButton("Tạo hóa đơn");

		btnTaoHoaDon.addActionListener(new ActionListener() {
			private String count;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					count = String.valueOf(hdDao.soMaHD());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String maKH = cboMaKH.getSelectedItem().toString();
				String maNV = cboMaNV.getSelectedItem().toString();
				Timestamp ngayLapHoaDon = new Timestamp(System.currentTimeMillis());
				HoaDon hd = new HoaDon("HD" + count, new KhachHang(maKH), new NhanVien(maNV), ngayLapHoaDon, 0);
				hdDao.themHoaDon(hd);
				deleteTableHDCTT();
				updateTableHDCTT();
			}
		});
		btnTaoHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTaoHoaDon.setBounds(21, 10, 121, 33);
		pnlformHoaDon.add(btnTaoHoaDon);

		btnSuaHoaDon = new JButton("Sửa hóa đơn");
		btnSuaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSuaHoaDon.setBounds(21, 53, 121, 33);
		pnlformHoaDon.add(btnSuaHoaDon);

		cboMaKH = new JComboBox();
		cboMaKH.setBounds(266, 12, 96, 31);
		pnlformHoaDon.add(cboMaKH);

		cboMaNV = new JComboBox();
		cboMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboMaNV.setBounds(266, 54, 96, 31);
		pnlformHoaDon.add(cboMaNV);

		JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMaKH.setBounds(159, 18, 111, 25);
		pnlformHoaDon.add(lblMaKH);

		JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMaNV.setBounds(169, 57, 111, 25);
		pnlformHoaDon.add(lblMaNV);

		btnSuaHoaDon.addActionListener(new ActionListener() {

			private String count;

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean a = true;
				try {
					int row = tableHoaDon.getSelectedRow();
					String maHD = dataModelHoaDon.getValueAt(row, 0).toString();
					String maKH = cboMaKH.getSelectedItem().toString();
					String maNV = cboMaNV.getSelectedItem().toString();
					ngayLap = new Timestamp(System.currentTimeMillis());
					HoaDon hd = new HoaDon("HD" + count, new KhachHang(maKH), new NhanVien(maNV), ngayLap, 0);
					if (a == true)
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					else
						JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JPanel panelCTSanPham = new JPanel();
		panelCTSanPham.setBounds(1044, 0, 492, 743);
		panelCTSanPham.setBorder(new TitledBorder(null, "Chi Ti\u1EBFt S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.add(panelCTSanPham);

		JScrollPane scrollCTSanPham = new JScrollPane();
		String[] tieuDeCTSanPham = { "Mã hóa đơn", "Mã sản phẩm", "Số lượng" };
		panelCTSanPham.setLayout(null);
		scrollCTSanPham_1 = new JScrollPane(
				tableCTSanPham = new JTable(dataModelCTSanPham = new DefaultTableModel(tieuDeCTSanPham, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableCTSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollCTSanPham_1.setBounds(6, 121, 476, 612);
		panelCTSanPham.add(scrollCTSanPham_1);

		JPanel pnlformCTSanPham = new JPanel();
		pnlformCTSanPham.setBackground(Color.WHITE);
		pnlformCTSanPham.setBounds(6, 15, 476, 211);
		panelCTSanPham.add(pnlformCTSanPham);
		pnlformCTSanPham.setLayout(null);

		JButton btnXemsanpham = new JButton("Xem sản phẩm");
		btnXemsanpham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXemsanpham.setBounds(10, 10, 123, 32);
		pnlformCTSanPham.add(btnXemsanpham);
		btnXemsanpham.addActionListener(new ActionListener() {

			private final JPanel contentPanel = new JPanel();
			private Component scrollSanPham;
			private JTable tableSanPham;
			private JTextField txtTimMaSanPham;

			private JButton btnTimSanPham;
			private JButton btnLamMoiSP;
			private JTextField txtSoLuongSP;
			private JButton btnThemchisanpham;

			@Override
			public void actionPerformed(ActionEvent e) {

				jdialogChonSanPham.setSize(600, 400);
				jdialogChonSanPham.setLocationRelativeTo(null);

				jdialogChonSanPham.setBounds(100, 100, 1033, 659);
				jdialogChonSanPham.getContentPane().setLayout(new BorderLayout());
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				jdialogChonSanPham.getContentPane().add(contentPanel, BorderLayout.CENTER);
				contentPanel.setLayout(new BorderLayout(0, 0));
				{
					String[] tieuSanPham = { "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá" };
					scrollSanPham = new JScrollPane(
							tableSanPham = new JTable(dataModelSanPham = new DefaultTableModel(tieuSanPham, 0)),
							JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					contentPanel.add(scrollSanPham, BorderLayout.SOUTH);
				}
				{
					JPanel panel = new JPanel();
					contentPanel.add(panel, BorderLayout.CENTER);
					panel.setLayout(null);

					btnTimSanPham = new JButton("Tìm Sản Phẩm");
					btnTimSanPham.setBounds(380, 100, 120, 25);
					panel.add(btnTimSanPham);

					btnLamMoiSP = new JButton("Làm mới");
					btnLamMoiSP.setFont(new Font("Tahoma", Font.BOLD, 13));
					btnLamMoiSP.setBounds(541, 100, 120, 25);
					panel.add(btnLamMoiSP);

					JLabel lblNewLabel_1 = new JLabel("Từ khóa :");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(150, 24, 80, 25);
					panel.add(lblNewLabel_1);

					txtTimMaSanPham = new JTextField();
					txtTimMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 13));
					txtTimMaSanPham.setColumns(10);
					txtTimMaSanPham.setBounds(226, 21, 203, 25);
					panel.add(txtTimMaSanPham);

					JLabel lblSoLuong = new JLabel("Số lượng :");
					lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblSoLuong.setBounds(450, 24, 80, 25);
					panel.add(lblSoLuong);

					txtSoLuongSP = new JTextField();
					txtSoLuongSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
					txtSoLuongSP.setColumns(10);
					txtSoLuongSP.setBounds(540, 21, 203, 25);
					panel.add(txtSoLuongSP);

					updateTableSP();

					btnTimSanPham.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object o = e.getSource();
							if (o.equals(btnTimSanPham)) {
								try {
									deleteTableSP();
									String tim = txtTimMaSanPham.getText().trim();
									SanPham ptimMa = spDao.timSanPhamTheoMa(tim);
									SanPham ptimLoai = spDao.timSanPhamTheoNuoc(tim);
									SanPham ptimTen = spDao.timSanPhamTheoTen(tim);

									if (txtTimMaSanPham.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Chưa ghi thông tin cần tìm");
										updateTableSP();
										txtTimMaSanPham.requestFocus();
									} else {
										for (SanPham i : dssp) {
											if (tim.equalsIgnoreCase(i.getMaSanPham()))
												dataModelSanPham.addRow(
														new Object[] { ptimMa.getMaSanPham(), ptimMa.getTenSanPham(),
																ptimMa.getLoaiSanPham(), ptimMa.getDonGia() });
											else if (tim.equalsIgnoreCase(i.getTenSanPham())) {
												dataModelSanPham.addRow(
														new Object[] { ptimTen.getMaSanPham(), ptimTen.getTenSanPham(),
																ptimTen.getLoaiSanPham(), ptimTen.getDonGia() });
											}
//											else if (tim.equalsIgnoreCase(i.getLoaiSanPham())) {
//												dataModelSanPham.addRow(new Object[] { ptimLoai.getMaSanPham(), ptimLoai.getTenSanPham(),
//														ptimLoai.getLoaiSanPham(), ptimLoai.getDonGia() });
//											}
										}
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					});
					btnLamMoiSP.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Object o = e.getSource();
							if (o.equals(btnLamMoiSP)) {
								deleteTableSP();
								updateTableSP();
							}
						}
					});
				}
				{
					JPanel buttonPane = new JPanel();
					jdialogChonSanPham.getContentPane().add(buttonPane, BorderLayout.SOUTH);
					buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

					{
						JButton cancelButtonSP = new JButton("Hủy");
						cancelButtonSP.setActionCommand("Cancel");
						buttonPane.add(cancelButtonSP);
						cancelButtonSP.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								jdialogChonSanPham.setVisible(false);
							}
						});
					}
				}

				jdialogChonSanPham.setVisible(false);
				jdialogChonSanPham.setVisible(true);

			}
		});

		btnHuysanpham = new JButton("Hủy sản phẩm");
		btnHuysanpham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean a = true;
				try {
					int r = tableCTSanPham.getSelectedRow();
					ctspDao.xoaCTSP(dataModelHoaDon.getValueAt(row, 0).toString(),
							dataModelCTSanPham.getValueAt(r, 1).toString());

					deleteTableCTTSP();
					updateTableCTSP(dataModelHoaDon.getValueAt(row, 0).toString());

					if (a == true)
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					else
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Chưa chọn hàng cần xóa");
				}

			}
		});
		btnHuysanpham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHuysanpham.setBounds(164, 10, 135, 32);
		pnlformCTSanPham.add(btnHuysanpham);

		btnThemsp = new JButton("Thêm sản phẩm");
		btnThemsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// De tu tu them
					int a = 0;
					int slc = 0;
					ArrayList<ChiTietHoaDonSanPham> listctsp;
					listctsp = ctspDao.getAllCTSP();

					for (ChiTietHoaDonSanPham i : listctsp) {
						if ((i.getHoaDon().getMaHoaDon().equalsIgnoreCase(dataModelHoaDon.getValueAt(row, 0).toString())
								&& i.getSanPham().getMaSanPham()
										.equalsIgnoreCase(cboMSP.getSelectedItem().toString()))) {
							a++;
							slc = i.getSoLuong();
						}

					}

					if (a == 0) {
						ctspDao.themCTHD(dataModelHoaDon.getValueAt(row, 0).toString(),
								cboMSP.getSelectedItem().toString(),
								Integer.parseInt(cboSoLuong.getSelectedItem().toString()));
						deleteTableCTTSP();
						updateTableCTSP(dataModelHoaDon.getValueAt(row, 0).toString());
					}

					else {

						int sl = Integer.parseInt(cboSoLuong.getSelectedItem().toString()) + slc;
						ctspDao.capNhatSoLuong(dataModelHoaDon.getValueAt(row, 0).toString(),
								cboMSP.getSelectedItem().toString(), sl);
						deleteTableCTTSP();
						updateTableCTSP(dataModelHoaDon.getValueAt(row, 0).toString());
					}

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnThemsp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemsp.setBounds(325, 10, 141, 32);
		pnlformCTSanPham.add(btnThemsp);

		cboSPvHD = new JComboBox();
		cboSPvHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboSPvHD.setBounds(93, 60, 61, 32);
		pnlformCTSanPham.add(cboSPvHD);

		cboMSP = new JComboBox();
		cboMSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboMSP.setBounds(245, 60, 54, 32);
		pnlformCTSanPham.add(cboMSP);

		JLabel lbSoluong = new JLabel("Số lượng");
		lbSoluong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbSoluong.setBounds(313, 60, 69, 26);
		pnlformCTSanPham.add(lbSoluong);

		cboSoLuong = new JComboBox();
		cboSoLuong.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
		cboSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboSoLuong.setEditable(true);
		cboSoLuong.setBounds(373, 59, 54, 33);
		pnlformCTSanPham.add(cboSoLuong);

		JLabel lblMSp = new JLabel("Mã SP:");
		lblMSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMSp.setBounds(189, 64, 81, 25);
		pnlformCTSanPham.add(lblMSp);

		JLabel lblMHd_1 = new JLabel("Mã Hóa Đơn:");
		lblMHd_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMHd_1.setBounds(10, 64, 111, 25);
		pnlformCTSanPham.add(lblMHd_1);

		JPanel panelCTPhong = new JPanel();
		panelCTPhong.setBounds(554, 0, 480, 743);
		panelCTPhong.setBorder(
				new TitledBorder(null, "Chi Ti\u1EBFt Ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelCTPhong);

		JScrollPane scrollCTPhong = new JScrollPane();
		String[] tieuDeCTPhong = { "Mã hóa đơn", "Mã phòng", "Ngày bắt đầu", "Ngày kết thúc" };
		panelCTPhong.setLayout(null);

		scrollCTPhong_1 = new JScrollPane(
				tableCTPhong = new JTable(dataModelCTPhong = new DefaultTableModel(tieuDeCTPhong, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableCTPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollCTPhong_1.setBounds(0, 119, 476, 614);
		panelCTPhong.add(scrollCTPhong_1);

		JPanel pnlformCTPhong = new JPanel();
		pnlformCTPhong.setBackground(Color.WHITE);
		pnlformCTPhong.setBounds(0, 15, 476, 211);
		panelCTPhong.add(pnlformCTPhong);
		pnlformCTPhong.setLayout(null);

		btnXemphongtrong = new JButton("Xem phòng trống");
		btnXemphongtrong.addActionListener(new ActionListener() {

			private final JPanel contentPanel = new JPanel();
			private JTable table;
			private JScrollPane scrollPhong;
			private JTextField textField;
			private JTextField textField_1;
			private JButton btnTimCTPhong;
			private JButton btnLamMoiPhong;

			public void actionPerformed(ActionEvent e) {

				jdialogChonPhong.setSize(600, 400);
				jdialogChonPhong.setLocationRelativeTo(null);

				jdialogChonPhong.setBounds(100, 100, 1033, 659);
				jdialogChonPhong.getContentPane().setLayout(new BorderLayout());
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				jdialogChonPhong.getContentPane().add(contentPanel, BorderLayout.CENTER);
				contentPanel.setLayout(new BorderLayout(0, 0));
				{

					String[] tieuPhong = { "Mã phòng", "Mã loại phòng", "Tên phòng", "Trạng thái", "Lầu" };
					scrollPhong = new JScrollPane(
							tablePhong = new JTable(dataModelPhong = new DefaultTableModel(tieuPhong, 0)),
							JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					contentPanel.add(scrollPhong, BorderLayout.SOUTH);
				}
				{
					JPanel panel = new JPanel();
					contentPanel.add(panel, BorderLayout.CENTER);
					panel.setLayout(null);

					JLabel lblNewLabel_1 = new JLabel("Từ khóa : ");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNewLabel_1.setBounds(400, 53, 111, 25);
					panel.add(lblNewLabel_1);

					textField = new JTextField();
					textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
					textField.setColumns(10);
					textField.setBounds(500, 50, 203, 25);
					panel.add(textField);

					btnTimCTPhong = new JButton("Tìm Phòng");
					btnTimCTPhong.setBounds(430, 100, 120, 25);
					panel.add(btnTimCTPhong);

					btnLamMoiPhong = new JButton("Làm mới");
					btnLamMoiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
					btnLamMoiPhong.setBounds(591, 100, 120, 25);
					panel.add(btnLamMoiPhong);

					updateTablePT();

					btnTimCTPhong.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object o = e.getSource();
							if (o.equals(btnTimCTPhong)) {
								try {

									String tim = textField.getText().trim();
									Phong ptimMa = pDao.timPhongTheoMa(tim);
									Phong ptimTen = pDao.timPhongTheoTen(tim);
									Phong ptimMLP = pDao.timPhongTheoMLP(tim);
									if (textField.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Chưa ghi thông tin cần tìm");

										textField.requestFocus();
									} else {
										for (Phong i : dsp) {
											if (tim.equalsIgnoreCase(i.getMaPhong()))
												dataModelPhong.addRow(new Object[] { ptimMa.getMaPhong(),
														ptimMa.getLoaiPhong().getMaLoaiPhong(), ptimMa.getTenPhong(),
														ptimMa.getTrangThai(), ptimMa.getLau() });
											else if (tim.equalsIgnoreCase(i.getTenPhong())) {
												dataModelPhong.addRow(new Object[] { ptimTen.getMaPhong(),
														ptimTen.getLoaiPhong().getMaLoaiPhong(), ptimTen.getTenPhong(),
														ptimTen.getTrangThai(), ptimTen.getLau() });
											} else if (tim.equalsIgnoreCase(i.getLoaiPhong().getMaLoaiPhong())) {
												dataModelPhong.addRow(new Object[] { ptimMLP.getMaPhong(),
														ptimMLP.getLoaiPhong().getMaLoaiPhong(), ptimMLP.getTenPhong(),
														ptimMLP.getTrangThai(), ptimMLP.getLau() });
											}
										}
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					});
					btnLamMoiPhong.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Object o = e.getSource();
							if (o.equals(btnLamMoiPhong)) {
								deleteTableCTP();
								updateTableCTP(dataModelHoaDon.getValueAt(row, 0).toString());
							}

						}
					});
				}
				{
					JPanel buttonPane = new JPanel();
					jdialogChonPhong.getContentPane().add(buttonPane, BorderLayout.SOUTH);
					buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						JButton cancelButtonP = new JButton("Hủy");
						cancelButtonP.setActionCommand("Cancel");
						buttonPane.add(cancelButtonP);
						cancelButtonP.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								jdialogChonPhong.setVisible(false);
							}
						});
					}
				}

				jdialogChonPhong.setVisible(false);
				jdialogChonPhong.setVisible(true);

			}
		});
		btnXemphongtrong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXemphongtrong.setBounds(10, 10, 135, 33);
		pnlformCTPhong.add(btnXemphongtrong);

		btnRoiphong = new JButton("Rời phòng");
		btnRoiphong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRoiphong.setBounds(314, 10, 135, 33);
		pnlformCTPhong.add(btnRoiphong);
		/*
		 * roi phong
		 */
		btnRoiphong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String maHD = dataModelHoaDon.getValueAt(row, 0).toString();
				int r = tableCTPhong.getSelectedRow();
				String maPhong = dataModelCTPhong.getValueAt(r, 1).toString();
				pDao.capNhatPhongTrong(maPhong, "Trống");
				Timestamp gioKetThuc = new Timestamp(System.currentTimeMillis());
				ChiTietHoaDonPhong ctp = new ChiTietHoaDonPhong(new HoaDon(maHD), new Phong(maPhong), gioKetThuc);
				ctpDao.capNhatGioKetThuc(maHD, maPhong, gioKetThuc);

				deleteTableCTP();
				updateTableCTP(dataModelHoaDon.getValueAt(row, 0).toString());
				deleteCBOmaP();
				updateCBOmaP();
				// deleteTablePT();
				// updateTablePT();
			}
		});
		btnXoahuyphong = new JButton("Hủy phòng");
		btnXoahuyphong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXoahuyphong.setBounds(178, 10, 112, 33);
		pnlformCTPhong.add(btnXoahuyphong);
		cboHDvaP = new JComboBox();
		cboHDvaP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboHDvaP.setBounds(87, 59, 58, 33);
		pnlformCTPhong.add(cboHDvaP);

		cboMaPhong = new JComboBox();
		cboMaPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		cboMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboMaPhong.setBounds(241, 59, 49, 32);
		pnlformCTPhong.add(cboMaPhong);

		btnThemPvaHD = new JButton("Thêm phòng");
		btnThemPvaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemPvaHD.setBounds(314, 58, 135, 33);
		pnlformCTPhong.add(btnThemPvaHD);

		JLabel lblMHd = new JLabel("Mã Hóa Đơn:");
		lblMHd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMHd.setBounds(10, 63, 111, 25);
		pnlformCTPhong.add(lblMHd);

		JLabel lblMPhng = new JLabel("Mã Phòng:");
		lblMPhng.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMPhng.setBounds(169, 63, 81, 25);
		pnlformCTPhong.add(lblMPhng);
		/*
		 * theem chi tiet phong vao hoa don
		 */
		btnThemPvaHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean a = true;
				String maHD = dataModelHoaDon.getValueAt(row, 0).toString();
				String maPhong = cboMaPhong.getSelectedItem().toString();
				Timestamp gioBatDau = new Timestamp(System.currentTimeMillis());
				ChiTietHoaDonPhong cthd = new ChiTietHoaDonPhong(new HoaDon(maHD), new Phong(maPhong), gioBatDau, null);
				ctpDao.themCTHD(cthd);
				deleteTableCTP();
				updateTableCTP(dataModelHoaDon.getValueAt(row, 0).toString());
				if (a == true) {

					pDao.capNhatPhongTrong(maPhong, "Đang hoạt động");
					deleteCBOmaP();
					updateCBOmaP();
					JOptionPane.showMessageDialog(null, "Thêm vào phòng hóa đơn thành công");
				} else
					JOptionPane.showMessageDialog(null, "Thêm vào phòng hóa đơn thất bại");
			}
		});
		btnXoahuyphong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean a = true;
				try {
					int r = tableCTPhong.getSelectedRow();
					ctpDao.xoaCTHDMaP(dataModelCTPhong.getValueAt(r, 0).toString(),
							dataModelCTPhong.getValueAt(r, 1).toString());
					String maPhong = dataModelCTPhong.getValueAt(r, 1).toString();
					pDao.capNhatPhongTrong(maPhong, "Trống");
					deleteTableCTP();
					updateTableCTP(dataModelHoaDon.getValueAt(row, 0).toString());
					deleteCBOmaP();
					updateCBOmaP();
					if (a == true)
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					else
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Chưa chọn hàng cần xóa");
				}

			}
		});
		dshd = new ArrayList<HoaDon>();
		dssp = new ArrayList<SanPham>();
		dsctsp = new ArrayList<ChiTietHoaDonSanPham>();
		dsp = new ArrayList<Phong>();
		dsctp = new ArrayList<ChiTietHoaDonPhong>();
		dskh = new ArrayList<KhachHang>();
		dsnv = new ArrayList<NhanVien>();

		hdDao = new HoaDonDao();
		spDao = new SanPhamDao();
		ctspDao = new ChiTietHoaDonSanPhamDao();
		pDao = new PhongDao();
		ctpDao = new ChiTietHoaDonPhongDao();
		khDao = new KhachHangDao();
		nvDao = new NhanVienDao();

		tableHoaDon.addMouseListener(this);
		tableCTPhong.addMouseListener(this);
		tableCTSanPham.addMouseListener(this);

		Database.getInstance().connect();

		updateTableHDCTT();

		updateCBOMaKH();
		updateCBOMaNV();
		updateCBOHDvaP();
		updateCBOmaP();
		updatecboHDvaSP();
		updateCBOmSP();
	}

	// Hoa don chua thanh toan
	public void updateTableHDCTT() {
		dshd = hdDao.getHDChuaTT();
		for (HoaDon i : dshd) {
			dataModelHoaDon.addRow(new Object[] { i.getMaHoaDon(), i.getKhachHang().getMaKhachHang(),
					i.getNhanVien().getMaNhanVien(), i.getNgayLapHoaDon(), i.getThanhTien() });
		}
	}

	public void deleteTableHDCTT() {
		int y = dataModelHoaDon.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelHoaDon.removeRow(0);
		}
	}

	// Hoa don
	public void updateTableHD() {
		dshd = hdDao.getAllHoaDon();
		for (HoaDon i : dshd) {
			dataModelHoaDon.addRow(new Object[] { i.getMaHoaDon(), i.getKhachHang().getMaKhachHang(),
					i.getNhanVien().getMaNhanVien(), i.getNgayLapHoaDon(), i.getThanhTien() });
		}
	}

	public void deleteTableHD() {
		int y = dataModelHoaDon.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelHoaDon.removeRow(0);
		}
	}

	// San pham
	public void updateTableSP() {
		dssp = spDao.getAllSanPham();
		for (SanPham i : dssp) {
			dataModelSanPham
					.addRow(new Object[] { i.getMaSanPham(), i.getTenSanPham(), i.getLoaiSanPham(), i.getDonGia() });
		}
	}

	public void deleteTableSP() {
		int y = dataModelSanPham.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelSanPham.removeRow(0);
		}
	}

	// Chi tiet san pham
	public void updateTableCTSP(String ma) {
		dsctsp = ctspDao.getDSTheoMaHD(ma);
		for (ChiTietHoaDonSanPham i : dsctsp) {
			dataModelCTSanPham.addRow(
					new Object[] { i.getHoaDon().getMaHoaDon(), i.getSanPham().getMaSanPham(), i.getSoLuong() });
		}
	}

	public void deleteTableCTTSP() {
		int y = dataModelCTSanPham.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelCTSanPham.removeRow(0);
		}
	}

	// Phong Trong
	public void updateTablePT() {
		dsp = pDao.getDSPhongTheoTrangThai();
		for (Phong i : dsp) {
			dataModelPhong.addRow(new Object[] { i.getMaPhong(), i.getLoaiPhong().getMaLoaiPhong(), i.getTenPhong(),
					i.getTrangThai(), i.getLau() });
		}
	}

	public void deleteTablePT() {
		for (int i = 0; i < dataModelPhong.getRowCount(); i++) {
			dataModelPhong.removeRow(0);
		}
	}
	// Phong

	// Chi tiet Phong
	public void updateTableCTP(String maHD) {
		dsctp = ctpDao.getDSTheoMaHD(maHD);
		for (ChiTietHoaDonPhong i : dsctp) {
			dataModelCTPhong.addRow(new Object[] { i.getHoaDon().getMaHoaDon(), i.getPhong().getMaPhong(),
					i.getNgayBatDau(), i.getNgayKetThuc() });
		}
	}

	public void deleteTableCTP() {
		int y = dataModelCTPhong.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelCTPhong.removeRow(0);
		}
	}

	// Khach Hang
	public void updateTableKH() {
		dskh = khDao.getAllKhachHang();
		for (KhachHang i : dskh) {
			dataModelKH.addRow(new Object[] { i.getMaKhachHang(), i.getTenKhachHang(), i.getSoCMND(), i.getSoDT() });
		}
	}

	public void deleteTableKH() {
		int y = dataModelKH.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelKH.removeRow(0);
		}
	}

	public void deleteHDDaTT() {
		dataModelHoaDon.removeRow(tableHoaDon.getSelectedRow());
	}

	public void updateCBOMaNV() {
		try {
			dsnv = nvDao.getAllNhanVien();
			for (NhanVien nv : dsnv) {
				cboMaNV.addItem(nv.getMaNhanVien());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateCBOMaKH() {
		dskh = khDao.getAllKhachHang();
		for (KhachHang kh : dskh) {
			cboMaKH.addItem(kh.getMaKhachHang());
		}
	}

	public void updateCBOHDvaP() {
		dshd = hdDao.getHDChuaTT();
		for (HoaDon p : dshd) {
			cboHDvaP.addItem(p.getMaHoaDon());
		}
	}

	public void updateCBOmaP() {
		dsp = pDao.getDSPhongTheoTrangThai();
		for (Phong p : dsp) {
			cboMaPhong.addItem(p.getMaPhong());
		}
	}

	public void deleteCBOmaP() {
		cboMaPhong.removeAllItems();
	}

	public void updatecboHDvaSP() {
		dshd = hdDao.getHDChuaTT();
		for (HoaDon p : dshd) {
			cboSPvHD.addItem(p.getMaHoaDon());
		}
	}

	public void updateCBOmSP() {
		dssp = spDao.getAllSanPham();
		for (SanPham p : dssp) {
			cboMSP.addItem(p.getMaSanPham());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tableHoaDon)) {
			row = tableHoaDon.getSelectedRow();
			cboMaKH.setSelectedItem(dataModelHoaDon.getValueAt(row, 1));
			cboMaNV.setSelectedItem(dataModelHoaDon.getValueAt(row, 2));
			String maHD = dataModelHoaDon.getValueAt(row, 0).toString();
			deleteTableCTP();
			updateTableCTP(maHD);

			deleteTableCTTSP();
			updateTableCTSP(maHD);

		} else if (o.equals(tableCTPhong)) {
			int row = tableCTPhong.getSelectedRow();
			cboHDvaP.setSelectedItem(dataModelCTPhong.getValueAt(row, 0));
		} else if (o.equals(tableCTSanPham)) {
			int row = tableCTSanPham.getSelectedRow();
			cboSPvHD.setSelectedItem(dataModelCTSanPham.getValueAt(row, 0));
			cboMSP.setSelectedItem(dataModelCTSanPham.getValueAt(row, 1));
			cboSoLuong.setSelectedItem(dataModelCTSanPham.getValueAt(row, 2));
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
