package GUI.CapNhat;
import pic.DateLabelFormatter;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.GridBagLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import connect.Database;
import dao.KhachHangDao;
import dao.PhieuDatPhongTruocDao;
import dao.PhongDao;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.PhieuDatPhongTruoc;
import entity.Phong;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;



import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class DatPhong extends JPanel implements ActionListener, MouseListener{
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTextField txtMakhachhang;
	private JTextField txtTGlapphieu;
	private JTextField txtPhong;
	private ArrayList<KhachHang> dskh;
	private KhachHangDao khDao;
	private JButton btnSuathongtin;
	private JButton btnDatphong;
	private JButton btnHuydatphong;
	private JButton btnLamrong;
	private JDialog jdialogKH= new JDialog();
	
	private JTable tableKhachHang;
	private DefaultTableModel dataModelKH;
	
	private JDialog jdialogChonPhong;
	
	private JTable tablePhong;
	private DefaultTableModel dataModelPhong;
	private ArrayList<Phong> dsp;
	private PhongDao pDao;
	

	private JTextField textField_1;

	private PhieuDatPhongTruocDao pdtDao = new PhieuDatPhongTruocDao();
	private ArrayList<PhieuDatPhongTruoc> dsdp;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	
	JComboBox cobGio;
	JComboBox cobPhut;
	
	
	
	
	
	
	
	
	/**
	 * Create the panel.
	 */
	public DatPhong() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setPreferredSize(new Dimension(10, 480));
		String[] tieuDe = { "Mã Khách Hàng", "Mã phòng", "Thời gian lập phiếu", "Thời gian đặt phòng" };
		panel_1.setLayout(new BorderLayout(0, 0));
		scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0)), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(scroll);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(190, 26, 110, 25);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Phòng: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(225, 59, 110, 25);
		panel_2.add(lblNewLabel_2);
		
		txtMakhachhang = new JTextField();
		txtMakhachhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtMakhachhang.setEditable(false);
		txtMakhachhang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//txtMakhachhang.setEnabled(false);
		txtMakhachhang.setBounds(345, 26, 181, 25);
		panel_2.add(txtMakhachhang);
		txtMakhachhang.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Thời gian lập phiếu đặt phòng:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(633, 26, 176, 25);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Thời gian đặt phòng:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(686, 59, 146, 25);
		panel_2.add(lblNewLabel_4);

		txtTGlapphieu = new JTextField();
		txtTGlapphieu.setEditable(false);
		txtTGlapphieu.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtTGlapphieu.setBounds(842, 23, 203, 25);
		panel_2.add(txtTGlapphieu);
		txtTGlapphieu.setColumns(10);

		btnDatphong = new JButton("Đặt phòng");
		btnDatphong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDatphong.setBounds(378, 135, 157, 43);
		panel_2.add(btnDatphong);

		btnSuathongtin = new JButton("Sửa thông tin");
		btnSuathongtin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSuathongtin.setBounds(557, 135, 157, 43);
		panel_2.add(btnSuathongtin);

		btnHuydatphong = new JButton("Hủy đặt phòng");
		btnHuydatphong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHuydatphong.setBounds(735, 135, 137, 43);
		panel_2.add(btnHuydatphong);

		btnLamrong = new JButton("Làm rỗng");
		btnLamrong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamrong.setBounds(899, 135, 146, 43);
		panel_2.add(btnLamrong);
		DefaultComboBoxModel<String> cboDataModel = new DefaultComboBoxModel<String>();
		
		model=new UtilDateModel();
		Properties p=new Properties();
		p.put("text.today","Today");
		p.put("text.month","Month");
		p.put("text.year","Year");
		datePanel=new JDatePanelImpl(model);
		datePicker=new JDatePickerImpl(datePanel,new DateLabelFormatter());
		datePicker.setBounds(842, 59, 181, 25);
		panel_2.add(datePicker);

		txtPhong = new JTextField();
		txtPhong.setEditable(false);
		txtPhong.setBounds(345, 57, 181, 25);
		txtPhong.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(txtPhong);
		txtPhong.setColumns(10);
		
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		JButton btnChonKH = new JButton("+");
		btnChonKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChonKH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jdialogKH.setVisible(true);
				
			}

		
			}
		);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		btnChonKH.setBounds(536, 26, 52, 24);
		panel_2.add(btnChonKH);
		
		JButton btnXemphongtrong = new JButton("+");
		btnXemphongtrong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXemphongtrong.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				jdialogChonPhong.setVisible(true);
			}
		});
		
		
		btnXemphongtrong.setBounds(536, 58, 52, 25);
		panel_2.add(btnXemphongtrong);
		
		cobGio = new JComboBox();
		cobGio.setFont(new Font("Dialog", Font.PLAIN, 14));
		cobGio.setModel(new DefaultComboBoxModel(new String[] {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"}));
		cobGio.setBounds(1033, 62, 52, 21);
		panel_2.add(cobGio);
		
		cobPhut = new JComboBox();
		cobPhut.setModel(new DefaultComboBoxModel(new String[] {"0", "15", "30", "45"}));
		cobPhut.setFont(new Font("Dialog", Font.PLAIN, 13));
		cobPhut.setBounds(1134, 62, 52, 21);
		panel_2.add(cobPhut);
		
		JLabel lblNewLabel_5 = new JLabel("Giờ");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(1095, 65, 45, 13);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Phút");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(1196, 65, 45, 13);
		panel_2.add(lblNewLabel_6);

		// chèn sự kiện
		btnLamrong.addActionListener(this);
		btnSuathongtin.addActionListener(this);
		btnDatphong.addActionListener(this);
		btnHuydatphong.addActionListener(this);
		
		btnLamrong.addMouseListener(this);
		btnSuathongtin.addMouseListener(this);
		btnDatphong.addMouseListener(this);
		btnHuydatphong.addMouseListener(this);
		table.addMouseListener(this);
		
		dskh = new ArrayList<KhachHang>();
		khDao = new KhachHangDao();
		dsp = new ArrayList<Phong>();
		pDao = new PhongDao();
		Database.getInstance().connect();
		updateTable();
		taoJdPhong();
		taoJdkh();

	}
	public void updateTable() {
		dsdp = pdtDao.getDSCTP();
		for (PhieuDatPhongTruoc i : dsdp) {
			dataModel.addRow(new Object[] { i.getKhachHang().getMaKhachHang(), i.getPhong().getMaPhong(), i.getThoiGianLapPhieu(), i.getThoiGianDatPhong() });
		}
	}

	public void deleteTable() {
		int y = dataModel.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModel.removeRow(0);
		}
	}
	public void xoaRong() {
		txtMakhachhang.setText("");
		txtTGlapphieu.setText("");
		txtPhong.setText("");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDatphong)) {
			if (txtMakhachhang.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng");
				txtMakhachhang.requestFocus();
			}  else if (txtPhong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng");
				txtPhong.requestFocus();
			} else if (model.getValue()==null) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập thời gian đặt phòng");
				
			} else {
				boolean a = true;
				try {
					String maKH = txtMakhachhang.getText();
					Timestamp thoiGianLapPhieu = new Timestamp(System.currentTimeMillis());
					String maPhong = txtPhong.getText();
					
					
					
					Timestamp thoiGianDatPhong = new Timestamp(model.getYear()-1900, model.getMonth(), model.getDay(),Integer.parseInt((String) cobGio.getSelectedItem()), Integer.parseInt((String) cobPhut.getSelectedItem()), 0, 0) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
					};
					
					
					
					Phong p = new Phong(maPhong);
					KhachHang k = new KhachHang(maKH);
					
					PhieuDatPhongTruoc kh = new PhieuDatPhongTruoc(p,k,thoiGianLapPhieu,thoiGianDatPhong);
					pdtDao.themPhieu(kh);
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

		} else if (o.equals(btnHuydatphong)) {
			boolean a = true;
			try {
				int row = table.getSelectedRow();
				
				String tr=txtTGlapphieu.getText();

				
				pdtDao.xoaCTHDMaP(txtPhong.getText(),txtMakhachhang.getText(),tr);
				deleteTable();
				updateTable();
				xoaRong();
				
				if (a == true)
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				else
					JOptionPane.showMessageDialog(this, "Xóa thất bại");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn hàng cần xóa");
				e2.printStackTrace();
			}
		} else if (o.equals(btnSuathongtin)) {
						
					boolean a = true;
			try {
				String maKH = txtMakhachhang.getText();
				String thoiGianLapPhieu = txtTGlapphieu.getText();
				String maPhong = txtPhong.getText();
				
				
				
				Timestamp thoiGianDatPhong = new Timestamp(model.getYear()-1900, model.getMonth(), model.getDay(),Integer.parseInt((String) cobGio.getSelectedItem()), Integer.parseInt((String) cobPhut.getSelectedItem()), 0, 0) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
				};
				

				pdtDao.suaPhieu(maPhong,thoiGianDatPhong,maKH,thoiGianLapPhieu);
				deleteTable();
				updateTable();
				if (a == true)
					JOptionPane.showMessageDialog(this, "Sửa thành công");
				else
					JOptionPane.showMessageDialog(this, "Thêm thất bại");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Chưa điền thông tin đầy đủ");
			}
			
			
			
			
		} else if(o.equals(btnLamrong)) {
			xoaRong();
		}
		
	}
	
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
	
	public void updateTablePT() {
		dsp = pDao.getAllDSPhong();
		for (Phong i : dsp) {
			dataModelPhong.addRow(new Object[] { i.getMaPhong(), i.getLoaiPhong().getMaLoaiPhong(), i.getTenPhong(),
					i.getTrangThai(), i.getLau() });
		}
	}

	public void deleteTablePT() {
		int y=dataModelPhong.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelPhong.removeRow(0);
		}
	}
	

	private JScrollPane scrollPhong;
	private JTextField textField;
	private JButton btnTimCTPhong;
	private JButton btnLamMoiPhong;
	
	public void taoJdPhong() {
			jdialogChonPhong= new JDialog();
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
				
				
				btnTimCTPhong.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						deleteTablePT();
					
						if (textField.getText().equals("")) {
							JOptionPane.showMessageDialog(jdialogChonPhong, "Chưa nhập từ khóa cần tìm!");
							updateTablePT();
							textField.requestFocus();
						} else {
							String tuKhoa2 = textField.getText();
							ArrayList<Phong> dsphong = pDao.timPhongTheoTuKhoa(tuKhoa2);
							dsphong.forEach(n->{
								System.out.println(n.toString());
							});
							
							for (Phong p : dsphong) {
								dataModelPhong.addRow(new Object[] { p.getMaPhong(), p.getLoaiPhong().getMaLoaiPhong(),
										p.getTenPhong(), p.getTrangThai(), p.getLau() });
							}
							
							
							if (tablePhong.getRowCount()==0) {
								JOptionPane.showMessageDialog(jdialogChonPhong, "Không tìm thấy phòng phù hợp!");
							}
						}
						
						
					
						
						
						
					}
				});
				
				

				btnLamMoiPhong = new JButton("Làm mới");
				btnLamMoiPhong.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnLamMoiPhong.setBounds(591, 100, 120, 25);
				panel.add(btnLamMoiPhong);
				btnLamMoiPhong.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						deleteTablePT();
						updateTablePT();
											
					}
				});
			

				
				
			}
			
			
			
			{
				JPanel buttonPane = new JPanel();
				jdialogChonPhong.getContentPane().add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JButton btnChonPhong = new JButton("Chọn Phòng");
					btnChonPhong .setActionCommand("OK");
					buttonPane.add(btnChonPhong);
					//jdialogKH.getRootPane().setDefaultButton(btnChonPhong );

					btnChonPhong.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int sr=tablePhong.getSelectedRow();
							txtPhong.setText((String) dataModelPhong.getValueAt(sr,0));
							jdialogChonPhong.setVisible(false);

						}
					});
				}
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
			 updateTablePT();
				
			}
			
			
	}
	
		private final JPanel contentPanel2 = new JPanel();
		private JTextField txtTuKhoa2;
		private JScrollPane scrollKH;
		private JButton btnTimKhachhang;
	
	public void taoJdkh() {

			jdialogKH.setSize(600, 400);
			jdialogKH = new JDialog();
			jdialogKH.setLocationRelativeTo(null);

			jdialogKH.setBounds(100, 100, 1033, 659);
			jdialogKH.getContentPane().setLayout(new BorderLayout());
			contentPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
			jdialogKH.getContentPane().add(contentPanel2, BorderLayout.CENTER);
			contentPanel2.setLayout(new BorderLayout(0, 0));
			{
				String[] tieuKH = { "Mã khách hàng", "Tên khách hàng", "Số CMND", "Số điện thoại" };
				scrollKH = new JScrollPane(
						tableKhachHang = new JTable(dataModelKH = new DefaultTableModel(tieuKH, 0)),
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				contentPanel2.add(scrollKH, BorderLayout.SOUTH);
			}
			{
				JPanel panel = new JPanel();
				contentPanel2.add(panel, BorderLayout.CENTER);
				panel.setLayout(null);

				JLabel lblNewLabel_1 = new JLabel("Từ khóa:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1.setBounds(400, 53, 111, 25);
				panel.add(lblNewLabel_1);

				txtTuKhoa2 = new JTextField();
				txtTuKhoa2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtTuKhoa2.setColumns(10);
				txtTuKhoa2.setBounds(500, 50, 203, 25);
				panel.add(txtTuKhoa2);

				btnTimKhachhang = new JButton("Tìm khách hàng");
				btnTimKhachhang.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnTimKhachhang.setBounds(390, 100, 180, 25);
				panel.add(btnTimKhachhang);
				
				btnTimKhachhang.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {


						deleteTableKH();
						if (txtTuKhoa2.getText().equals("")) {
							JOptionPane.showMessageDialog(jdialogKH, "Chưa nhập từ khóa cần tìm!");
							updateTableKH();
							txtTuKhoa2.requestFocus();
						} else {
							String tuKhoa = txtTuKhoa2.getText();
							ArrayList<KhachHang> dskh1 = khDao.timKhachHangTheoTuKhoa(tuKhoa);
							for (KhachHang kh : dskh1) {
								dataModelKH.addRow(new Object[] { kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoCMND(),
										kh.getSoDT() });
							}
							if (tableKhachHang.getRowCount()==0) {
								JOptionPane.showMessageDialog(jdialogKH, "Không tìm thấy khách hàng phù hợp!");
							}
						}
						
						
						
						
					}
				});
				
				

				JButton btnLamMoiKH = new JButton("Làm mới");
				btnLamMoiKH.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnLamMoiKH.setBounds(591, 100, 120, 25);
				panel.add(btnLamMoiKH);
				btnLamMoiKH.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteTableKH();
						updateTableKH();

					}
				});

				
				
			}
			{
				JPanel buttonPane = new JPanel();
				jdialogKH.getContentPane().add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JButton btnThemchikhachhang = new JButton("Chọn khách hàng");
					btnThemchikhachhang.setActionCommand("OK");
					buttonPane.add(btnThemchikhachhang);
					jdialogKH.getRootPane().setDefaultButton(btnThemchikhachhang);

					btnThemchikhachhang.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							txtMakhachhang.setText((String) dataModelKH.getValueAt(tableKhachHang.getSelectedRow(),0));
							jdialogKH.setVisible(false);
						}
					});

				}
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
				
				updateTableKH();
				
			
			}

		
	}
	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(table)) {
			int row = table.getSelectedRow();
			txtMakhachhang.setText(dataModel.getValueAt(row, 0).toString());
			txtTGlapphieu.setText(dataModel.getValueAt(row, 2).toString());
			txtPhong.setText(dataModel.getValueAt(row, 1).toString());
			
			String tr= dataModel.getValueAt(row, 3).toString();
			
			String tr2[] = tr.split(" ");
			
			String tr3[] = tr2[0].split("-");
			
			String year = tr3[0];
			String month = tr3[1];
			String day = tr3[2];
			
			String time[] = tr2[1].split(":");
			
			String gio = time[0];
			String phut = time[1];
			
			
				
			
				
			
				Date date = new Date(Integer.parseInt(year)-1900,Integer.parseInt(month)-1,Integer.parseInt(day));
				
				
				model.setValue(date);
				
				cobGio.setSelectedItem(gio);
				cobPhut.setSelectedItem(phut);
	
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
