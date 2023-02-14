package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

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
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import pic.DateLabelFormatter;

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
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ThongKe extends JPanel  {

	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dataModel;
	private JButton btnTim;
	private HoaDonDao hdDao;
	private ArrayList<HoaDon> dshd;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private UtilDateModel model2;
	private JDatePanelImpl datePanel2;
	private JDatePickerImpl datePicker2;
	private JLabel lblTngDoanhThu;
	private JPanel panel_3;

	/**
	 * Create the panel.
	 */
	public ThongKe() {
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
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		
		lblTngDoanhThu = new JLabel("");
		panel_3.add(lblTngDoanhThu);
		lblTngDoanhThu.setVisible(false);
		
		lblTngDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ngày bắt đầu :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(309, 68, 89, 16);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Ngày kết thúc :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(713, 68, 120, 16);
		panel_2.add(lblNewLabel_3);

		DefaultComboBoxModel<String> cboDataModel = new DefaultComboBoxModel<String>();

		btnTim = new JButton("Thống kê");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				Timestamp a = new Timestamp(model.getYear()-1900, model.getMonth(), model.getDay(),0,0, 0, 0) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
				};
				
				Timestamp b = new Timestamp(model2.getYear()-1900, model2.getMonth(), model2.getDay(),0,0, 0, 0) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
				};
				
				
				
				
				
				deleteTable();
				
				int tongtien=0;
				
				dshd = hdDao.timHDTT(a, b);
				for (HoaDon i : dshd) {
					dataModel.addRow(new Object[] { i.getMaHoaDon(), i.getKhachHang().getMaKhachHang(),
							i.getNhanVien().getMaNhanVien(), i.getNgayLapHoaDon(), i.getThanhTien() });
					
					tongtien = (int) (tongtien + i.getThanhTien());
				}
				
				
				String dated = a.toString();
				String[] dated1 = dated.split(" ");
				
				String datek = b.toString();
				String[] datek1 = datek.split(" ");
				
				
				lblTngDoanhThu.setVisible(true);
				lblTngDoanhThu.setText("Tổng doanh thu từ ngày "+dated1[0]+" đến ngày "+datek1[0]+" là : "+ tongtien+" đ");
				
			}
		});
		
		
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTim.setBounds(621, 168, 157, 42);
		panel_2.add(btnTim);
		
		
		
		model=new UtilDateModel();
		Properties p=new Properties();
		p.put("text.today","Today");
		p.put("text.month","Month");
		p.put("text.year","Year");
		datePanel=new JDatePanelImpl(model);
		datePicker=new JDatePickerImpl(datePanel,new DateLabelFormatter());
		datePicker.setBounds(426, 59, 181, 25);
		panel_2.add(datePicker);
		Date date=java.util.Calendar.getInstance().getTime();
		model.setValue(date);
		
		
		
		
		model2=new UtilDateModel();
		Properties p2=new Properties();
		p2.put("text.today","Today");
		p2.put("text.month","Month");
		p2.put("text.year","Year");
		datePanel2=new JDatePanelImpl(model2);
		datePicker2=new JDatePickerImpl(datePanel2,new DateLabelFormatter());
		datePicker2.setBounds(811, 59, 181, 25);
		panel_2.add(datePicker2);
		model2.setValue(date);
		
		
		hdDao = new HoaDonDao();
		dshd = new ArrayList<HoaDon>();

		Database.getInstance().connect();
		updateTable();
	}

	public void updateTable() {
		dshd = hdDao.getHDTT();
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
}
