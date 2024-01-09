package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import connectDB.connectSQL;
import dao.ChiTietHoaDon_dao;
import dao.ChiTietPhieuDatPhong_dao;
import dao.ChiTietPhieuDichVu_dao;
import dao.DichVu_dao;
import dao.HoaDon_dao;
import dao.PhieuDatDichVu_dao;
import dao.PhieuDatPhong_dao;
import dao.Phong_dao;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.ChiTietPhieuDichVu;
import entity.DichVu;
import entity.HoaDon;
import entity.PhieuDatDichVu;
import entity.PhieuDatPhong;
import entity.Phong;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;

public class LapHoaDon_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private PhieuDatPhong PDP;
	private List<PhieuDatPhong> dsPDP;
	private PhieuDatPhong_dao pdp_dao;
	private PhieuDatDichVu PDDV;
	private HoaDon_dao hd_dao;
	private ChiTietHoaDon_dao cthd_dao;
	private PhieuDatDichVu_dao pddv_dao;
	protected JPanel pnl_ContentPaneLHD;
	private JPanel pnlLapHoaDon;
	private JPanel pnlWest;
	private JPanel pnlDSPhongActivity;
	private JScrollPane scr_DSPhongActivity;
	private JTable tbl_DSPhong;
	private JPanel pnlButtonControl;
	private JButton btnLapHoaDon;
	private JPanel pnlCenTer;
	private JTextField txtMaNV;
	private JLabel lblMaNV;
	private JPanel pnl_1;
	private JTextField txtTenKH;
	private JLabel lblTTenKH;
	private JPanel pnl_2;
	private JTextField txtTenPhong;
	private JLabel lblMaPhong;
	private JLabel lblLoaiPhong;
	private JTextField txtLoaiPhong;
	private JTextField txtNgayDat;
	private JTextField txtThue;
	private JPanel pnl_3;
	private JLabel lblNgayLap;
	private JLabel lblThueVAT;
	private JLabel lblGioVao;
	private JLabel lblTongTien;
	private JLabel lblTongThanhTien;
	private JTextField txtGioVao;
	private JTextField txtTienThanhToan;
	private JTextField txtTongThanhTien;
	private JLabel lblgGioRa;
	private JPanel pnl_4;
	private JPanel pnlDSDichVu;
	private JTable tblDSDichVu;
	private JScrollPane scrDSDichVu;
	private JPanel pnl_5;
	private JPanel pnl_6;
	private JPanel pnl_7;
	private JButton btnThanhToan;
	private JLabel lblSDTKH;
	private JTextField txtSDT;
	private JPanel pnl_GioPhutRa;
	private Calendar cal;
	private SpinnerNumberModel spnM;
	private JSpinner spn_PhutRa;
	private SpinnerNumberModel spnMHour;
	private JSpinner spn_GioRa;
	private DefaultTableModel tableModelDSDV;
	private long tienHat;
	private DecimalFormat decimalFormat;
	private int HDCounter = 1;
	private LocalTime gioRa;
	private JTextField txtChietKhau;
	private ArrayList<PhieuDatPhong> listPDP = new ArrayList<PhieuDatPhong>();
	private ChiTietPhieuDatPhong_dao ctpdp_dao;
	private Phong_dao p_dao;
	private String TienHat;

	/**
	 * Create the frame.
	 */
	public LapHoaDon_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPaneLHD = new JPanel();
		pnl_ContentPaneLHD.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPaneLHD.setLayout(new BorderLayout());

		hd_dao = new HoaDon_dao();
		cthd_dao = new ChiTietHoaDon_dao();
		pddv_dao = new PhieuDatDichVu_dao();
		pdp_dao = new PhieuDatPhong_dao();
		ctpdp_dao = new ChiTietPhieuDatPhong_dao();
		dsPDP = pdp_dao.getAllPhieuDatPhong();
		p_dao = new Phong_dao();
		DatPhong_gui DP_gui = new DatPhong_gui();

		cal = Calendar.getInstance();
		decimalFormat = new DecimalFormat("#,### VND");
		
		UIManager.put("TextField.inactiveForeground", new ColorUIResource(Color.BLACK)); 
		Color color = new Color(197, 199, 199);
		/*
		 * Panel Lập Hóa đơn
		 */
		pnlLapHoaDon = new JPanel();
		pnlLapHoaDon.setBackground(color);
		pnl_ContentPaneLHD.add(pnlLapHoaDon, BorderLayout.CENTER);
		pnlLapHoaDon.setLayout(new BorderLayout());
		/*
		 * Pannel Danh sách phòng đang sử dụng
		 */
		pnlWest = new JPanel();
		pnlWest.setBackground(color);
		pnlWest.setPreferredSize(new Dimension(450, 10));
		pnlLapHoaDon.add(pnlWest, BorderLayout.WEST);
		pnlWest.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch ph\u00F2ng \u0111ang s\u1EED d\u1EE5ng", TitledBorder.LEADING, TitledBorder.ABOVE_TOP,
				null, new Color(0, 0, 0)));
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		pnlDSPhongActivity = new JPanel();
		pnlDSPhongActivity.setMaximumSize(new Dimension(32767, 100));
		pnlWest.add(pnlDSPhongActivity);
		pnlDSPhongActivity.setLayout(new BorderLayout(0, 0));
		scr_DSPhongActivity = new JScrollPane();
		pnlDSPhongActivity.add(scr_DSPhongActivity);

		tbl_DSPhong = new JTable();
		tbl_DSPhong.setPreferredScrollableViewportSize(new Dimension(450, 200));
		tbl_DSPhong.setDefaultEditor(Object.class, null);// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên
//		// JTable
//		tableModelPhong = new DefaultTableModel();
//		tableModelPhong.addColumn("Mã Phòng");
//		tableModelPhong.addColumn("Tên Phòng");
//		tableModelPhong.addColumn("Loại Phòng");
//		for (Phong p : dsPhong) {
//			if (p.getTrangThai().equals("Đang sử dụng")) {
//				Object[] rowData = { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getTenLoai() };
//				tableModelPhong.addRow(rowData);
//			}
//		}
		taoBangPhong();
		tbl_DSPhong.setRowSelectionAllowed(true);
		tbl_DSPhong.setDefaultEditor(Object.class, null);// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên
		scr_DSPhongActivity.setViewportView(tbl_DSPhong);
		pnlWest.add(Box.createVerticalStrut(160));
		pnlButtonControl = new JPanel();
		pnlButtonControl.setBackground(color);
		pnlWest.add(pnlButtonControl);

		btnLapHoaDon = new JButton("Lập Hóa Đơn");
		btnLapHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lapHoaDon();
			}
		});
		pnlButtonControl.add(btnLapHoaDon);

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thanhToan();
			}
		});
		btnThanhToan.setEnabled(false);
		pnlButtonControl.add(btnThanhToan);
		/*
		 * Panel Center
		 */
		pnlCenTer = new JPanel();
		pnlCenTer.setBackground(color);
		pnlCenTer.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.RAISED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"L\u1EADp h\u00F3a \u0111\u01A1n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlLapHoaDon.add(pnlCenTer, BorderLayout.CENTER);
		pnlCenTer.setLayout(new BoxLayout(pnlCenTer, BoxLayout.Y_AXIS));
		/*
		 * panel 1
		 */
		pnl_1 = new JPanel();
		pnl_1.setBackground(color);
		pnl_1.setMaximumSize(new Dimension(32767, 60));
		pnlCenTer.add(pnl_1);
		pnl_1.setLayout(new BoxLayout(pnl_1, BoxLayout.X_AXIS));

		lblTTenKH = new JLabel("Tên Khách Hàng:");
		lblTTenKH.setPreferredSize(new Dimension(110, 14));
		txtTenKH = new JTextField(5);
		txtTenKH.setEditable(false);
		txtTenKH.setMaximumSize(new Dimension(2147483647, 25));
		txtTenKH.setHorizontalAlignment(SwingConstants.LEFT);

		pnl_1.add(Box.createHorizontalStrut(8));
		pnl_1.add(lblTTenKH);
		pnl_1.add(txtTenKH);

		Component horizontalStrut_3 = Box.createHorizontalStrut(40);
		pnl_1.add(horizontalStrut_3);

		lblSDTKH = new JLabel("SDT Khách Hàng:");
		lblSDTKH.setPreferredSize(new Dimension(110, 14));
		pnl_1.add(lblSDTKH);

		txtSDT = new JTextField(5);
		txtSDT.setMaximumSize(new Dimension(2147483647, 25));
		txtSDT.setHorizontalAlignment(SwingConstants.LEFT);
		txtSDT.setEditable(false);
		pnl_1.add(txtSDT);

		pnl_1.add(Box.createHorizontalStrut(20));

		/*
		 * Panel 2
		 */
		pnlCenTer.setLayout(new BoxLayout(pnlCenTer, BoxLayout.Y_AXIS));
		pnl_2 = new JPanel();
		pnl_2.setBackground(color);
		pnl_2.setMaximumSize(new Dimension(32767, 50));
		pnlCenTer.add(pnl_2);
		pnl_2.setLayout(new BoxLayout(pnl_2, BoxLayout.X_AXIS));

		lblMaPhong = new JLabel("Tên Phòng:");
		lblMaPhong.setPreferredSize(new Dimension(110, 14));
		txtTenPhong = new JTextField(5);
		txtTenPhong.setEditable(false);
		txtTenPhong.setMaximumSize(new Dimension(2147483647, 25));
		txtTenPhong.setHorizontalAlignment(SwingConstants.LEFT);

		pnl_2.add(Box.createHorizontalStrut(8));

		lblMaNV = new JLabel("Nhân Viên Lập:");
		pnl_2.add(lblMaNV);
		lblMaNV.setPreferredSize(new Dimension(110, 14));
		lblMaNV.setHorizontalAlignment(SwingConstants.LEFT);

		txtMaNV = new JTextField(5);
		txtMaNV.setEditable(false);
		pnl_2.add(txtMaNV);
		txtMaNV.setMaximumSize(new Dimension(2147483647, 25));

		Component horizontalStrut_2 = Box.createHorizontalStrut(40);
		pnl_2.add(horizontalStrut_2);
		pnl_2.add(lblMaPhong);
		pnl_2.add(txtTenPhong);

		pnl_2.add(Box.createHorizontalStrut(20));

		/*
		 * Panel 3
		 */
		pnl_3 = new JPanel();
		pnl_3.setBackground(color);
		pnl_3.setMaximumSize(new Dimension(32767, 50));
		pnlCenTer.add(pnl_3);
		pnl_3.setLayout(new BoxLayout(pnl_3, BoxLayout.X_AXIS));

		lblNgayLap = new JLabel("Ngày Đặt Phòng:");
		lblNgayLap.setPreferredSize(new Dimension(110, 14));
		txtNgayDat = new JTextField(5);
		txtNgayDat.setEditable(false);
		txtNgayDat.setMaximumSize(new Dimension(2147483647, 25));
		txtNgayDat.setHorizontalAlignment(SwingConstants.LEFT);

		Component horizontalStrut = Box.createHorizontalStrut(8);
		pnl_3.add(horizontalStrut);

		lblLoaiPhong = new JLabel("Loại Phòng:");
		pnl_3.add(lblLoaiPhong);
		lblLoaiPhong.setPreferredSize(new Dimension(110, 14));
		lblLoaiPhong.setHorizontalAlignment(SwingConstants.LEFT);

		txtLoaiPhong = new JTextField(5);
		txtLoaiPhong.setEditable(false);
		pnl_3.add(txtLoaiPhong);
		txtLoaiPhong.setMaximumSize(new Dimension(2147483647, 25));

		Component horizontalStrut_1 = Box.createHorizontalStrut(40);
		pnl_3.add(horizontalStrut_1);
		pnl_3.add(lblNgayLap);
		pnl_3.add(txtNgayDat);

		pnl_3.add(Box.createHorizontalStrut(20));

		/*
		 * Panel 4
		 */
		pnl_4 = new JPanel();
		pnl_4.setBackground(color);
		pnl_4.setMaximumSize(new Dimension(32767, 50));
		pnlCenTer.add(pnl_4);
		pnl_4.setLayout(new BoxLayout(pnl_4, BoxLayout.X_AXIS));

		lblgGioRa = new JLabel("Giờ Ra:");
		lblgGioRa.setPreferredSize(new Dimension(110, 14));

		pnl_4.add(Box.createHorizontalStrut(8));

		lblGioVao = new JLabel("Giờ Vào:");
		pnl_4.add(lblGioVao);
		lblGioVao.setPreferredSize(new Dimension(110, 14));
		lblGioVao.setHorizontalAlignment(SwingConstants.LEFT);

		txtGioVao = new JTextField(5);
		txtGioVao.setEditable(false);
		pnl_4.add(txtGioVao);
		txtGioVao.setMaximumSize(new Dimension(2147483647, 25));

		pnl_4.add(Box.createHorizontalStrut(40));
		pnl_4.add(lblgGioRa);

		pnl_GioPhutRa = new JPanel();
		pnl_GioPhutRa.setBackground(color);
		pnl_GioPhutRa.setPreferredSize(new Dimension(250, 10));
		pnl_4.add(pnl_GioPhutRa);
		pnl_GioPhutRa.setLayout(null);

		// Lấy Giờ hiện tại để đặt giờ vào (giờ mở cửa 8h00p-23h59p)
		// Láy Phút Hiện tại

		int mitute = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		hour = (hour < 8) ? 8 : hour;
		spnM = new SpinnerNumberModel(mitute, mitute, 59, 1);
		SpinnerModel spnM2 = new SpinnerNumberModel(0, 0, 59, 1);
		spn_PhutRa = new JSpinner(spnM);
		spn_PhutRa.setEnabled(false);
		spn_PhutRa.setBounds(65, 11, 50, 20);
		pnl_GioPhutRa.add(spn_PhutRa);

		spnMHour = new SpinnerNumberModel(hour, 8, 23, 1);
		spn_GioRa = new JSpinner(spnMHour);
		spn_GioRa.setEnabled(false);
		// Đặt giờ vào là giờ,phútmin là phút hiện tại, nếu h vào > giờ hiện tại, reset
		// là phútmin = 0
		spn_GioRa.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int slHour = (int) spn_GioRa.getValue();
				if (slHour != Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
					spn_PhutRa.setModel(spnM2);
				else
					spn_PhutRa.setModel(spnM);
			}
		});
		spn_GioRa.setBounds(10, 11, 50, 20);
		pnl_GioPhutRa.add(spn_GioRa);

		pnl_4.add(Box.createHorizontalStrut(20));

		pnlDSDichVu = new JPanel();
		pnlDSDichVu.setBackground(color);
		pnlDSDichVu.setPreferredSize(new Dimension(10, 300));
		pnlDSDichVu.setMaximumSize(new Dimension(32767, 300));
		pnlCenTer.add(pnlDSDichVu);
		pnlDSDichVu.setLayout(new BorderLayout(0, 0));

		scrDSDichVu = new JScrollPane();
		tblDSDichVu = new JTable();
		tblDSDichVu.setDefaultEditor(Object.class, null);// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên
		// JTable
		tableModelDSDV = new DefaultTableModel();
		tableModelDSDV.addColumn("STT");
		tableModelDSDV.addColumn("Tên");
		tableModelDSDV.addColumn("Số lượng/Số Giờ");
		tableModelDSDV.addColumn("Đơn vị tính");
		tableModelDSDV.addColumn("Giá");
		tableModelDSDV.addColumn("Thành Tiền");
		tblDSDichVu.setModel(tableModelDSDV);
		scrDSDichVu.setViewportView(tblDSDichVu);
		pnlDSDichVu.add(scrDSDichVu, BorderLayout.CENTER);
		/*
		 * Panel 5
		 */
		pnl_5 = new JPanel();
		pnl_5.setBackground(color);
		pnl_5.setMaximumSize(new Dimension(32767, 50));
		pnlCenTer.add(pnl_5);
		pnl_5.setLayout(new BoxLayout(pnl_5, BoxLayout.X_AXIS));

		pnl_5.add(Box.createHorizontalStrut(8));

		lblTongThanhTien = new JLabel("Tổng Thành Tiền:");
		lblTongThanhTien.setHorizontalAlignment(SwingConstants.LEFT);
		pnl_5.add(lblTongThanhTien);

		txtTongThanhTien = new JTextField(3);
		txtTongThanhTien.setEnabled(false);
		txtTongThanhTien.setBackground(SystemColor.controlHighlight);
		txtTongThanhTien.setMaximumSize(new Dimension(2147483647, 25));
		pnl_5.add(txtTongThanhTien);

		pnl_5.add(Box.createHorizontalStrut(40));

		lblThueVAT = new JLabel("Thuế VAT:");
		lblThueVAT.setPreferredSize(new Dimension(111, 14));
		lblThueVAT.setMaximumSize(new Dimension(111, 14));
		lblThueVAT.setMinimumSize(new Dimension(111, 14));
		pnl_5.add(lblThueVAT);
		txtThue = new JTextField(5);
		txtThue.setPreferredSize(new Dimension(14, 20));
		txtThue.setEnabled(false);
		txtThue.setBackground(SystemColor.controlHighlight);
		txtThue.setMaximumSize(new Dimension(2147483647, 25));
		txtThue.setHorizontalAlignment(SwingConstants.LEFT);
		pnl_5.add(txtThue);

		pnl_5.add(Box.createHorizontalStrut(20));
		/*
		 * Panel 6
		 */
		pnl_6 = new JPanel();
		pnl_6.setBackground(color);
		pnl_6.setMaximumSize(new Dimension(32767, 50));
		pnlCenTer.add(pnl_6);
		pnl_6.setLayout(new BoxLayout(pnl_6, BoxLayout.X_AXIS));
//		txtTienNhan.setHorizontalAlignment(SwingConstants.LEFT);

		pnl_6.add(Box.createHorizontalStrut(8));

		JLabel lblChiecKhau = new JLabel("Chiết Khấu:");
		lblChiecKhau.setPreferredSize(new Dimension(84, 14));
		lblChiecKhau.setHorizontalAlignment(SwingConstants.LEFT);
		pnl_6.add(lblChiecKhau);

		txtChietKhau = new JTextField(0);
		txtChietKhau.setMaximumSize(new Dimension(2147483647, 25));
		txtChietKhau.setEnabled(false);
		txtChietKhau.setBackground(SystemColor.controlHighlight);
		pnl_6.add(txtChietKhau);

		pnl_6.add(Box.createHorizontalStrut(40));

		lblTongTien = new JLabel("Tổng Tiền Thanh Toán:");
		lblTongTien.setForeground(Color.RED);
		lblTongTien.setHorizontalAlignment(SwingConstants.LEFT);
		pnl_6.add(lblTongTien);

		txtTienThanhToan = new JTextField(1);
		txtTienThanhToan.setEnabled(false);
		txtTienThanhToan.setBackground(SystemColor.controlHighlight);
		txtTienThanhToan.setMaximumSize(new Dimension(2147483647, 25));
		pnl_6.add(txtTienThanhToan);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		pnl_6.add(horizontalStrut_7);

		/*
		 * Panel 7
		 */
		pnl_7 = new JPanel();
		pnl_7.setBackground(color);
		pnl_7.setMaximumSize(new Dimension(32767, 100));
		pnlCenTer.add(pnl_7);
		pnl_7.setLayout(null);
		setContentPane(pnl_ContentPaneLHD);
	}

	protected void lapHoaDon() {
		int[] soLuongHang = tbl_DSPhong.getSelectedRows();
		if (soLuongHang.length == 0)
			JOptionPane.showMessageDialog(this, "Hãy chọn khách hàng cần lập hóa đơn!");
		if (soLuongHang.length == 1)
			lapHoaDonMotPhong();
		else {
			String ma = tbl_DSPhong.getValueAt(soLuongHang[0], 1).toString();
			int check = 0;
			for (int i : soLuongHang) {
				String ma2 = tbl_DSPhong.getValueAt(i, 1).toString();
				if (!ma2.equals(ma)) {
					JOptionPane.showMessageDialog(this, "Chọn 1 khách hàng!");
					check = 1;
					break;
				}
			}
			if (check == 0)
				lapHoaDonNhieuPhong(soLuongHang);
		}

	}

	private void lapHoaDonNhieuPhong(int[] soLuongHang) {
		btnThanhToan.setEnabled(true);
		spn_GioRa.setEnabled(true);
		spn_PhutRa.setEnabled(true);
		listPDP = new ArrayList<PhieuDatPhong>();
		for (int i : soLuongHang) {
			String tenPhong = tbl_DSPhong.getModel().getValueAt(i, 2).toString();
			for (PhieuDatPhong pdp : dsPDP) {
				ChiTietPhieuDatPhong ct = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(pdp.getMaPhieu());
				if (pdp.getTrangThai().equals("Đang sử dụng") && ct.getPhong().getTenPhong().equals(tenPhong)) {
					listPDP.add(pdp);
				}
			}
		}
		tableModelDSDV.setRowCount(0);
		taoBangHoaDon(listPDP.get(0));
		int n = 1;
		for (PhieuDatPhong phieuDatPhong : listPDP) {
			n = taoBangDSDichVu(phieuDatPhong, n) + 1; // Thiết lập cột stt tăng dần
		}
		double tongTien = 0.0;
		int m = tableModelDSDV.getRowCount();
		for (int i = 0; i < m; i++) {
			// Loại bỏ dấu phẩy và ký tự không mong muốn
			String chiSoTien = tableModelDSDV.getValueAt(i, 5).toString().replaceAll("[^\\d]", "");
			// Chuyển đổi chuỗi thành giá trị kiểu double
			double soTien = Double.parseDouble(chiSoTien);
			tongTien += soTien;
		}
		TienHat = decimalFormat.format(tongTien);
		txtTongThanhTien.setText(TienHat);
		txtTienThanhToan.setText(TienHat);
		
	}

	private void lapHoaDonMotPhong() {
		int n = tbl_DSPhong.getSelectedRow();
		btnThanhToan.setEnabled(true);
		spn_GioRa.setEnabled(true);
		spn_PhutRa.setEnabled(true);
		String tenPhong = tbl_DSPhong.getModel().getValueAt(n, 2).toString();
		for (PhieuDatPhong pdp : dsPDP) {
			ChiTietPhieuDatPhong ct = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(pdp.getMaPhieu());
			if (pdp.getTrangThai().equals("Đang sử dụng")) {
				if (ct.getPhong().getTenPhong().equals(tenPhong))
					PDP = pdp;
			}
		}
		tableModelDSDV.setRowCount(0);
		taoBangHoaDon(PDP);
		taoBangDSDichVu(PDP, 1);
		double tongTien = 0.0;
		int m = tableModelDSDV.getRowCount();
		for (int i = 0; i < m; i++) {
			// Loại bỏ dấu phẩy và ký tự không mong muốn
			String chiSoTien = tableModelDSDV.getValueAt(i, 5).toString().replaceAll("[^\\d]", "");
			// Chuyển đổi chuỗi thành giá trị kiểu double
			double soTien = Double.parseDouble(chiSoTien);
			tongTien += soTien;
		}
		String TienHat = decimalFormat.format(tongTien);
		txtTongThanhTien.setText(TienHat);
//			double TongThanhTien = tongTien + tongTien * 0.1;
//			String TongTien = decimalFormat.format(tongTien);
		txtTienThanhToan.setText(TienHat);
	}

	protected void thanhToan() {
		int n;
		ChiTietPhieuDichVu_dao ctdv_dao = new ChiTietPhieuDichVu_dao();
		List<ChiTietPhieuDichVu> listct = ctdv_dao.getAllCTPDV();
		List<PhieuDatDichVu> dsDDV = pddv_dao.getAllPhieuDatDichVu();
		ChiTietPhieuDatPhong ct = new ChiTietPhieuDatPhong();
		HoaDon HD = new HoaDon();
		if (listPDP.isEmpty()) {
			ct = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(PDP.getMaPhieu());
			HD = new HoaDon(taoMaHoaDon(), PDP.getKhachHang(), PDP.getNhanVien(), LocalDate.now());
		}
		else
			HD = new HoaDon(taoMaHoaDon(), listPDP.get(0).getKhachHang(), listPDP.get(0).getNhanVien(),
					LocalDate.now());
		hd_dao.addHD(HD);
		if (tblDSDichVu.getRowCount() == 1) {
			n = 1;
			ChiTietHoaDon cthd = new ChiTietHoaDon(HD, ct.getPhong(), ct.getGioVao(), gioRa);
			cthd_dao.addCTHDnoDV(cthd);
			pdp_dao.updateTrangThaiPDPhong("Đã Thanh Toán", PDP.getMaPhieu());
		} else if (!listPDP.isEmpty()) {
			n = 3;
			List<PhieuDatDichVu> dsPDDV = new ArrayList<PhieuDatDichVu>();
			for (PhieuDatPhong pdp : listPDP) {
				ChiTietPhieuDatPhong ctpdp = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(pdp.getMaPhieu());
				for (PhieuDatDichVu pddv : dsDDV) {
					if (pddv.getTinhTrang().equalsIgnoreCase("Chưa thanh toán")
							&& pddv.getKhachHang().getMaKH().equals(pdp.getKhachHang().getMaKH())
							&& pddv.getPhong().getMaPhong().equals(ctpdp.getPhong().getMaPhong())) {
						dsPDDV.add(pddv);
					}
				}
			}
			for (PhieuDatDichVu pDDV : dsPDDV) {
				ChiTietPhieuDatPhong ctPDP = new ChiTietPhieuDatPhong();
				for (PhieuDatPhong Pdp : listPDP) {
					ChiTietPhieuDatPhong ctpdp = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(Pdp.getMaPhieu());
					if (ctpdp.getPhong().getMaPhong().equals(pDDV.getPhong().getMaPhong())) {
						ctPDP = ctpdp;
						break;
					}

				}
				for (ChiTietPhieuDichVu ctdv : listct) {
					if (ctdv.getPhieuDichVu().getMaPhieu().equals(pDDV.getMaPhieu())) {
						DichVu dv2 = ctdv.getDichVu();
						ChiTietHoaDon cthd = new ChiTietHoaDon(HD, ctPDP.getPhong(), dv2, ctdv.getSoLuongBan(),
								ctPDP.getGioVao(), gioRa);
						cthd_dao.addCTHD(cthd);
					}
				}
				pDDV.setTinhTrang("Đã Thanh Toán");
				pddv_dao.updateTrangThaiPhieu(pDDV.getTinhTrang(), pDDV.getMaPhieu());
				pdp_dao.updateTrangThaiPDPhong("Đã Thanh Toán", ctPDP.getPhieuDatPhong().getMaPhieu());
			}
		} else {
			n = 2;
			List<PhieuDatDichVu> dsPDDV = new ArrayList<PhieuDatDichVu>();
			ChiTietPhieuDatPhong ctpdp = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(PDP.getMaPhieu());
			for (PhieuDatDichVu pddv : dsDDV) {
				if (pddv.getTinhTrang().equalsIgnoreCase("Chưa thanh toán")
						&& pddv.getKhachHang().getMaKH().equals(PDP.getKhachHang().getMaKH())
						&& pddv.getPhong().getMaPhong().equals(ctpdp.getPhong().getMaPhong())) {
					dsPDDV.add(pddv);
				}
			}
			for (PhieuDatDichVu phieuDatDichVu : dsPDDV) {
				for (ChiTietPhieuDichVu ctdv : listct) {
					if (ctdv.getPhieuDichVu().getMaPhieu().equals(phieuDatDichVu.getMaPhieu())) {
						DichVu dv2 = ctdv.getDichVu();
						ChiTietHoaDon cthd = new ChiTietHoaDon(HD, ctpdp.getPhong(), dv2, ctdv.getSoLuongBan(),
								ctpdp.getGioVao(), gioRa);
						cthd_dao.addCTHD(cthd);
					}
				}
				phieuDatDichVu.setTinhTrang("Đã Thanh Toán");
				pddv_dao.updateTrangThaiPhieu(phieuDatDichVu.getTinhTrang(), phieuDatDichVu.getMaPhieu());
			}
			pdp_dao.updateTrangThaiPDPhong("Đã Thanh Toán", PDP.getMaPhieu());
		}
		JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công!");
		taoBangPhong();
		lamMoiLapHD();
		if (n == 2)
			inHoaDon(HD.getMaHD());
		else if (n == 1)
			inHoaDonPhong(HD.getMaHD());
		else
			inHoaDonNhieuPhong(HD.getMaHD());
		
	}

	private int taoBangDSDichVu(PhieuDatPhong pDP, int n) {
		int gio = (int) spn_GioRa.getValue();
		int phut = (int) spn_PhutRa.getValue();
		// Chuyển giá trị giờ và phút thành LocalTime
		gioRa = LocalTime.of(gio, phut);
		// tính thời gian hát
		ChiTietPhieuDatPhong ctpdp = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(pDP.getMaPhieu());
		long soPhut = ctpdp.getGioVao().until(gioRa, ChronoUnit.MINUTES);
		long soGio = soPhut / 60;
		long phutDu = soPhut % 60;
		String soGiosoPhut = soGio + " giờ " + phutDu + " phút";
		// Tính tiền hát
		tienHat = Math.round((ctpdp.getPhong().getLoaiPhong().getGiaHat() / 60) * soPhut);
		String TienHat = decimalFormat.format(tienHat);
		String giaHat = decimalFormat.format(ctpdp.getPhong().getLoaiPhong().getGiaHat());

		Object[] rowData = { n, ctpdp.getPhong().getTenPhong(), soGiosoPhut, "Giờ", giaHat, TienHat };
		tableModelDSDV.addRow(rowData);

		PhieuDatDichVu_dao pdv_dao = new PhieuDatDichVu_dao();
		ChiTietPhieuDichVu_dao ctdv = new ChiTietPhieuDichVu_dao();
		List<ChiTietPhieuDichVu> listdv = ctdv.getAllCTPDV();
		DichVu_dao dv_dao = new DichVu_dao();
		for (ChiTietPhieuDichVu ct : listdv) {
			PhieuDatDichVu pdv = pdv_dao.findPhieuDVTheoMa(ct.getPhieuDichVu().getMaPhieu());
			if (pdv.getTinhTrang().equals("Chưa thanh toán")
					&& pdv.getPhong().getMaPhong().equals(ctpdp.getPhong().getMaPhong())
					&& pdv.getNgayLap().equals(ctpdp.getNgayNhan())
					&& pdv.getKhachHang().getMaKH().equals(pDP.getKhachHang().getMaKH())) {
				// Tìm dịch vụ theo mã dịch vụ
				DichVu dv = dv_dao.findDichVuTheoMa(ct.getDichVu().getMaDV());
				String giaDV = decimalFormat.format(dv.getDonGia());
				double tienDV = dv.getDonGia() * ct.getSoLuongBan();
				String tiendv = decimalFormat.format(tienDV);
				n = n + 1;
				Object[] row = { n, dv.getTenDV(), ct.getSoLuongBan(), dv.getDonViTinh(), giaDV, tiendv };
				tableModelDSDV.addRow(row);
			}
		}
		return n;
	}

	private void taoBangHoaDon(PhieuDatPhong pDP) {
		ChiTietPhieuDatPhong ct = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(pDP.getMaPhieu());
		LocalDate localDate2 = ct.getNgayNhan();
		LocalTime localTime = ct.getGioVao();
		// Định dạng ngày tháng năm thành chuỗi
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		String ngayNhan = localDate2.format(formatter);
		String gioVao = localTime.format(formatter2);

		txtTenKH.setText(pDP.getKhachHang().getHoTen());
		txtSDT.setText(pDP.getKhachHang().getSDT());
		txtMaNV.setText(pDP.getNhanVien().getHoTen());
		txtTenPhong.setText(ct.getPhong().getTenPhong());
		txtLoaiPhong.setText(ct.getPhong().getLoaiPhong().getTenLoai());
		txtNgayDat.setText(ngayNhan);
		txtGioVao.setText(gioVao);
		txtThue.setText("10%");
		txtChietKhau.setText("10%");

	}

	/*
	 * Tạo bảng chứa các phòng cần lập hóa đơn
	 */
	protected void taoBangPhong() {
		dsPDP = pdp_dao.getAllPhieuDatPhong();
		DefaultTableModel tableModelPhong = new DefaultTableModel();
		tableModelPhong.addColumn("Tên Khách Hàng");
		tableModelPhong.addColumn("SDT Khách Hàng");
		tableModelPhong.addColumn("Tên Phòng");
		tableModelPhong.addColumn("Loại Phòng");
		for (PhieuDatPhong pdp : dsPDP) {
			if (pdp.getTrangThai().equals("Đang sử dụng")) {
				ChiTietPhieuDatPhong ct = ctpdp_dao.findPhieuDatPhongTheoMaPhieu(pdp.getMaPhieu());
				Object[] rowData = { pdp.getKhachHang().getHoTen(), pdp.getKhachHang().getSDT(),
						ct.getPhong().getTenPhong(), ct.getPhong().getLoaiPhong().getTenLoai() };
				tableModelPhong.addRow(rowData);
			}
		}
		tbl_DSPhong.setModel(tableModelPhong);
	}

	protected void inHoaDon(String maHD) {
		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport("src/gui/HoaDon.jrxml");
			map.put("ma", maHD);
			JasperPrint p = JasperFillManager.fillReport(report, map, connectSQL.con);
			JasperViewer.viewReport(p, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void inHoaDonPhong(String maHD) {
		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport("src/gui/HoaDonPhong.jrxml");
			map.put("ma", maHD);
			JasperPrint p = JasperFillManager.fillReport(report, map, connectSQL.con);
			JasperViewer.viewReport(p, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void inHoaDonNhieuPhong(String maHD) {
		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport("src/gui/HoaDonNhieuPhong.jrxml");
			map.put("ma", maHD);
			map.put("tongtien",TienHat);
			JasperPrint p = JasperFillManager.fillReport(report, map, connectSQL.con);
			JasperViewer.viewReport(p, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Tạo mới một mã hóa đơn
	 */
	private String taoMaHoaDon() {
		String maHD;
		// Sử dụng Formatter để định dạng chuỗi thành "KHXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maHD = formatter.format("HD%08d", HDCounter).toString();
			HDCounter++;
		} while (!timMaHD(maHD));
		formatter.close();
		return maHD;
	}

	private boolean timMaHD(String maHD) {
		maHD = maHD.toUpperCase();
		HoaDon_dao HD_dao = new HoaDon_dao();
		List<HoaDon> list = HD_dao.getAllHoaDon();
		for (HoaDon hd : list) {
			if (hd.getMaHD().contains(maHD))
				return false;
		}
		return true;
	}

	private void lamMoiLapHD() {
		txtMaNV.setText("");
		txtSDT.setText("");
		txtTongThanhTien.setText("");
		txtNgayDat.setText("");
		txtTienThanhToan.setText("");
		txtTenPhong.setText("");
		txtGioVao.setText("");
		txtMaNV.setText("");
		txtTenKH.setText("");
		txtChietKhau.setText("");
		txtLoaiPhong.setText("");
		tableModelDSDV.setRowCount(0);
	}

}
