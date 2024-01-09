package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import dao.ChiTietHoaDon_dao;
import dao.HoaDon_dao;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ThongKeDoanhThu_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_ContentPaneDT;
	private JPanel pnl_DoanhThuMonth;
	private JPanel pnl_DoanhThuYear;
	private JPanel pnl_DoanhThuDate;
	private JDateChooser dtc_Thang;
	private ChartPanel chartPnl;
	private JLabel lbl_SoHoaDon;
	private JLabel lbl_TongDoanhThu;
	private JLabel lbl_DTPhongThuong;
	private JLabel lbl_DTPhongVIP;
	private JLabel lbl_SoGio;
	private JLabel lbl_TongTienHat;
	private JLabel lbl_TongTienDV;
	private DecimalFormat decimalFormat;
	private List<HoaDon> listHD;
	private HoaDon_dao hd_dao;
	private ChiTietHoaDon_dao cthd_dao;
	private List<ChiTietHoaDon> listCT;
	private ChartPanel chartPnlYear;
	private JComboBox<Integer> cmbYear;
	private JLabel lbl_SoHDYear;
	private JLabel lbl_TongDTYear;
	private JLabel lbl_DTPhongTHYear;
	private JLabel lbl_DTPhongVIPYear;
	private JLabel lbl_SoGioYear;
	private JLabel lbl_TongTienHatYear;
	private JLabel lbl_TongTienDVYear;
	private JLabel lblTienTongDoanhThuDay;
	private JLabel lblTienPhongThuongDay;
	private JLabel lblTienPhongVipDay;
	private JLabel lblTongSoHDNgay;
	private JLabel lblTienPhong;
	private JLabel lblTienDVDay;
	private JLabel lblTongGioHatDay;
	private JLabel lblDoanhThuTrungBinhDay;
	private JLabel lblSoHDNgay;
	private DefaultTableModel tblModelDoanhThuNgay;
	private JTable tblDoanhThuNgay;
	private JScrollPane scrDoanhThuNgay;

	/**
	 * Create the frame.
	 */
	public ThongKeDoanhThu_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPaneDT = new JPanel();
		pnl_ContentPaneDT.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnl_ContentPaneDT);
		pnl_ContentPaneDT.setLayout(new BorderLayout(0, 0));

		decimalFormat = new DecimalFormat("#,### VND");
		hd_dao = new HoaDon_dao();
		listHD = hd_dao.getAllHoaDon();
		cthd_dao = new ChiTietHoaDon_dao();
		listCT = cthd_dao.getAllDVCTHD();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pnl_ContentPaneDT.add(tabbedPane, BorderLayout.CENTER);

		/*
		 * (1) Panel Doanh Thu theo ngày
		 */
		Color color = new Color(197, 199, 199);
		pnl_DoanhThuDate = new JPanel();
		tabbedPane.addTab("Doanh Thu Theo Ngày", null, pnl_DoanhThuDate, null);
		pnl_DoanhThuDate.setLayout(new BorderLayout(0, 0));

		JPanel pnl_TieuDe_2 = new JPanel();
		pnl_TieuDe_2.setPreferredSize(new Dimension(10, 40));
		pnl_TieuDe_2.setBackground(color);
		pnl_DoanhThuDate.add(pnl_TieuDe_2, BorderLayout.NORTH);

		JLabel lblTieuDe_2 = new JLabel("Thống Kê Doanh Thu Theo Ngày");
		lblTieuDe_2.setForeground(new Color(15, 102, 165));
		lblTieuDe_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_TieuDe_2.add(lblTieuDe_2);

		JPanel pnl_ThongTin_2 = new JPanel();
		pnl_ThongTin_2.setBackground(color);
		LineBorder lineBorder2 = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder2 = new TitledBorder(lineBorder2, "Thông tin chi tiết");
		pnl_ThongTin_2.setBorder(titledBorder2);
		pnl_ThongTin_2.setPreferredSize(new Dimension(350, 10));
		pnl_DoanhThuDate.add(pnl_ThongTin_2, BorderLayout.WEST);
		pnl_ThongTin_2.setLayout(null);

		JLabel lblThoiGian_2 = new JLabel("Chọn Thời Gian:");
		lblThoiGian_2.setBounds(10, 20, 138, 19);
		pnl_ThongTin_2.add(lblThoiGian_2);

		JDateChooser dtc_Ngay = new JDateChooser();
		dtc_Ngay = new JDateChooser((Date) null, "dd/MM/yyyy");
		dtc_Ngay.setBounds(158, 20, 131, 19);
		dtc_Ngay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date selectedDate = (Date) evt.getNewValue();
					LocalDate ngayLap = selectedDate.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId())
							.toLocalDate();
					hienThiBang(ngayLap);
					hienThiThongTinDTTheoNgay(ngayLap);
				}
			}
		});
		pnl_ThongTin_2.add(dtc_Ngay);

		JPanel panel = new JPanel();

		panel.setBounds(0, 0, 10, 10);
		pnl_ThongTin_2.add(panel);

		JPanel lblTongTienDichVuDay = new JPanel();
		lblTongTienDichVuDay.setBackground(color);
		lblTongTienDichVuDay.setBounds(3, 79, 343, 518);
		pnl_ThongTin_2.add(lblTongTienDichVuDay);
		lblTongTienDichVuDay.setLayout(null);

		JLabel lblTongDoanhThuNgay = new JLabel("Tổng Doanh Thu:");
		lblTongDoanhThuNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongDoanhThuNgay.setForeground(Color.BLACK);
		lblTongDoanhThuNgay.setBounds(10, 21, 253, 21);
		lblTongTienDichVuDay.add(lblTongDoanhThuNgay);

		lblTongSoHDNgay = new JLabel("Tổng Số Hóa Đơn:");
		lblTongSoHDNgay.setForeground(Color.BLACK);
		lblTongSoHDNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongSoHDNgay.setBounds(10, 70, 274, 21);
		lblTongTienDichVuDay.add(lblTongSoHDNgay);

		JLabel lblPhongThuong = new JLabel("Doanh thu phòng thường:");
		lblPhongThuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhongThuong.setBounds(10, 129, 253, 13);
		lblTongTienDichVuDay.add(lblPhongThuong);

		JLabel lblPhongVip = new JLabel("Doanh thu phòng vip:");
		lblPhongVip.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhongVip.setBounds(10, 167, 253, 13);
		lblTongTienDichVuDay.add(lblPhongVip);

		JLabel lblSoGH = new JLabel("Tổng số giờ hát:");
		lblSoGH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoGH.setBounds(10, 205, 253, 13);
		lblTongTienDichVuDay.add(lblSoGH);

		JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng:");
		lblTongTienPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongTienPhong.setBounds(10, 245, 253, 13);
		lblTongTienDichVuDay.add(lblTongTienPhong);

		JLabel lblTongDV = new JLabel("Tổng tiền dịch vụ:");
		lblTongDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongDV.setBounds(10, 285, 253, 13);
		lblTongTienDichVuDay.add(lblTongDV);

		JLabel lblDoanhThuTrungBinh = new JLabel("Doanh thu trung bình:");
		lblDoanhThuTrungBinh.setForeground(Color.BLACK);
		lblDoanhThuTrungBinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDoanhThuTrungBinh.setBounds(10, 322, 253, 21);
		lblTongTienDichVuDay.add(lblDoanhThuTrungBinh);

		lblTienTongDoanhThuDay = new JLabel("VND");
		lblTienTongDoanhThuDay.setBounds(209, 21, 134, 21);
		lblTongTienDichVuDay.add(lblTienTongDoanhThuDay);

		lblTienPhongThuongDay = new JLabel("VND");
		lblTienPhongThuongDay.setBounds(209, 129, 134, 13);
		lblTongTienDichVuDay.add(lblTienPhongThuongDay);

		lblTienPhongVipDay = new JLabel("VND");
		lblTienPhongVipDay.setBounds(209, 169, 134, 13);
		lblTongTienDichVuDay.add(lblTienPhongVipDay);

		lblTienPhong = new JLabel("VND");
		lblTienPhong.setBounds(209, 247, 134, 13);
		lblTongTienDichVuDay.add(lblTienPhong);

		lblTienDVDay = new JLabel("VND");
		lblTienDVDay.setBounds(209, 287, 134, 13);
		lblTongTienDichVuDay.add(lblTienDVDay);

		lblTongGioHatDay = new JLabel("Giờ");
		lblTongGioHatDay.setBounds(209, 207, 134, 13);
		lblTongTienDichVuDay.add(lblTongGioHatDay);

		lblDoanhThuTrungBinhDay = new JLabel("VND");
		lblDoanhThuTrungBinhDay.setBounds(209, 328, 134, 13);
		lblTongTienDichVuDay.add(lblDoanhThuTrungBinhDay);

		lblSoHDNgay = new JLabel("0");
		lblSoHDNgay.setBounds(209, 76, 134, 13);
		lblTongTienDichVuDay.add(lblSoHDNgay);

		JPanel pnl_tblDoanhThuNgay = new JPanel();
		pnl_tblDoanhThuNgay.setBackground(color);
		pnl_DoanhThuDate.add(pnl_tblDoanhThuNgay, BorderLayout.CENTER);

		String headersDV[] = "Mã hóa đơn ;Ngày lập;Tên khách hàng;SĐT;Tổng tiền".split(";");
		tblModelDoanhThuNgay = new DefaultTableModel(headersDV, 0);
		scrDoanhThuNgay = new JScrollPane();
		scrDoanhThuNgay.setBackground(color);
		scrDoanhThuNgay.setViewportView(tblDoanhThuNgay = new JTable(tblModelDoanhThuNgay));
		scrDoanhThuNgay.setPreferredSize(new Dimension(1050, 300));
		tblDoanhThuNgay.setRowHeight(20);
		pnl_tblDoanhThuNgay.setLayout(new BorderLayout(0, 0));
		pnl_tblDoanhThuNgay.add(scrDoanhThuNgay);

		/*
		 * (2) Panel Doanh Thu theo tháng
		 */
		pnl_DoanhThuMonth = new JPanel();
		tabbedPane.addTab("Doanh Thu Theo Tháng", null, pnl_DoanhThuMonth, null);
		pnl_DoanhThuMonth.setLayout(new BorderLayout(0, 0));

		// Panel tiêu đề
		JPanel pnl_TieuDe = new JPanel();
		pnl_TieuDe.setPreferredSize(new Dimension(10, 40));
		pnl_TieuDe.setBackground(color);
		pnl_DoanhThuMonth.add(pnl_TieuDe, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("Thống Kê Doanh Thu Theo Tháng");
		lblTieuDe.setForeground(new Color(15, 102, 165));
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnl_TieuDe.add(lblTieuDe);

		// Panel Thông tin

		JPanel pnl_ThongTin = new JPanel();
		pnl_ThongTin.setBackground(color);
		pnl_ThongTin.setPreferredSize(new Dimension(350, 10));
		pnl_DoanhThuMonth.add(pnl_ThongTin, BorderLayout.WEST);
		pnl_ThongTin.setLayout(null);
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông Tin Chi Tiết");
		pnl_ThongTin.setBorder(titledBorder);

		JPanel pnl_ThongtinChiTiet = new JPanel();
		pnl_ThongtinChiTiet.setBackground(color);
		pnl_ThongtinChiTiet.setBounds(3, 79, 343, 518);
		pnl_ThongTin.add(pnl_ThongtinChiTiet);
		pnl_ThongtinChiTiet.setLayout(null);

		JLabel lblThoiGian = new JLabel("Chọn Thời Gian:");
		lblThoiGian.setBounds(22, 20, 101, 21);
		pnl_ThongTin.add(lblThoiGian);

		dtc_Thang = new JDateChooser((Date) null, "MM/yyyy");
		dtc_Thang.setBounds(124, 20, 125, 20);
		dtc_Thang.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date selectedDate = (Date) evt.getNewValue();
					LocalDate ngayLap = selectedDate.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId())
							.toLocalDate();
					taoBieuDoDoanhThuTheoThang(ngayLap);
					hienThiThongTinDTTheoThang(ngayLap);
				}
			}
		});
		pnl_ThongTin.add(dtc_Thang);

		JLabel lblTongHD = new JLabel("Tổng Số Hóa Đơn: ");
		lblTongHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongHD.setBounds(22, 24, 122, 14);
		pnl_ThongtinChiTiet.add(lblTongHD);

		lbl_SoHoaDon = new JLabel("");
		lbl_SoHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_SoHoaDon.setBounds(183, 21, 157, 18);
		pnl_ThongtinChiTiet.add(lbl_SoHoaDon);

		JLabel lblTongDT = new JLabel("Tổng Doanh Thu:");
		lblTongDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongDT.setBounds(22, 67, 122, 14);
		pnl_ThongtinChiTiet.add(lblTongDT);

		lbl_TongDoanhThu = new JLabel("");
		lbl_TongDoanhThu.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongDoanhThu.setBounds(183, 64, 157, 18);
		pnl_ThongtinChiTiet.add(lbl_TongDoanhThu);

		JLabel lblDTPhongThuong = new JLabel("Doanh Thu Phòng Thường:");
		lblDTPhongThuong.setBounds(22, 106, 175, 16);
		pnl_ThongtinChiTiet.add(lblDTPhongThuong);

		lbl_DTPhongThuong = new JLabel("SumDTPT");
		lbl_DTPhongThuong.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_DTPhongThuong.setBounds(183, 104, 157, 18);
		pnl_ThongtinChiTiet.add(lbl_DTPhongThuong);

		JLabel lblDTPhongVIP = new JLabel("Doanh Thu Phòng VIP:");
		lblDTPhongVIP.setBounds(22, 148, 149, 16);
		pnl_ThongtinChiTiet.add(lblDTPhongVIP);

		lbl_DTPhongVIP = new JLabel("SumDTPV");
		lbl_DTPhongVIP.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_DTPhongVIP.setBounds(183, 146, 157, 18);
		pnl_ThongtinChiTiet.add(lbl_DTPhongVIP);

		JLabel lblSoGioHat = new JLabel("Tổng Số Giờ Hát:");
		lblSoGioHat.setBounds(22, 191, 149, 16);
		pnl_ThongtinChiTiet.add(lblSoGioHat);

		lbl_SoGio = new JLabel("");
		lbl_SoGio.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_SoGio.setBounds(183, 189, 157, 18);
		pnl_ThongtinChiTiet.add(lbl_SoGio);

		JLabel lblTongTienHat = new JLabel("Tổng Tiền Hát:");
		lblTongTienHat.setBounds(22, 241, 149, 16);
		pnl_ThongtinChiTiet.add(lblTongTienHat);

		lbl_TongTienHat = new JLabel("SumTienHat");
		lbl_TongTienHat.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongTienHat.setBounds(183, 239, 157, 18);
		pnl_ThongtinChiTiet.add(lbl_TongTienHat);

		JLabel lblTongTienDV = new JLabel("Tổng Tiền Dịch Vụ:");
		lblTongTienDV.setBounds(22, 291, 149, 16);
		pnl_ThongtinChiTiet.add(lblTongTienDV);

		lbl_TongTienDV = new JLabel("SumTienDV");
		lbl_TongTienDV.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongTienDV.setBounds(183, 281, 157, 18);
		pnl_ThongtinChiTiet.add(lbl_TongTienDV);

		// Biểu đồ doanh thu theo tháng
		JPanel pnl_BieuDo = new JPanel();
		pnl_DoanhThuMonth.add(pnl_BieuDo, BorderLayout.CENTER);
		pnl_BieuDo.setLayout(new BorderLayout(0, 0));

		// Tạo dữ liệu cho biểu đồ cột

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		LocalDate monthNow = LocalDate.now();
//		for (int i = 1; i <= monthNow.lengthOfMonth(); i++) {
//			dataset.addValue(i, "Doanh Thu", i + "");
//		}
		hienThiThongTinDTTheoThang(monthNow);
		JFreeChart free = ChartFactory.createBarChart(
				"Biểu đồ Doanh Thu Tháng " + monthNow.getMonthValue() + " Năm " + monthNow.getYear(), "Ngày",
				"Doanh Thu", dataset, PlotOrientation.VERTICAL, false, true, false);
		chartPnl = new ChartPanel(free);
		taoBieuDoDoanhThuTheoThang(monthNow);

		pnl_BieuDo.add(chartPnl, BorderLayout.CENTER);
		/*
		 * (3) Panel Doanh thu theo năm
		 */

		pnl_DoanhThuYear = new JPanel();
		tabbedPane.addTab("Doanh Thu Theo Năm", null, pnl_DoanhThuYear, null);
		pnl_DoanhThuYear.setLayout(new BorderLayout(0, 0));

		JPanel pnl_TieuDe_1 = new JPanel();
		pnl_TieuDe_1.setPreferredSize(new Dimension(10, 40));
		pnl_TieuDe_1.setBackground(color);
		pnl_DoanhThuYear.add(pnl_TieuDe_1, BorderLayout.NORTH);
		pnl_TieuDe_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTieuDe_1 = new JLabel("Thống Kê Doanh Thu Theo Năm");
		lblTieuDe_1.setForeground(new Color(15, 102, 165));
		lblTieuDe_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_TieuDe_1.add(lblTieuDe_1);

		JPanel pnl_ThongTin_1 = new JPanel();
		pnl_ThongTin_1.setBackground(color);
		pnl_ThongTin_1.setLayout(null);
		pnl_ThongTin_1.setBorder(titledBorder);
		pnl_ThongTin_1.setPreferredSize(new Dimension(350, 10));
		pnl_DoanhThuYear.add(pnl_ThongTin_1, BorderLayout.WEST);

		JPanel pnl_ThongtinChiTiet_1 = new JPanel();
		pnl_ThongtinChiTiet_1.setBackground(color);
		pnl_ThongtinChiTiet_1.setLayout(null);
		pnl_ThongtinChiTiet_1.setBounds(3, 79, 343, 518);
		pnl_ThongTin_1.add(pnl_ThongtinChiTiet_1);

		JLabel lblTongHD_1 = new JLabel("Tổng Số Hóa Đơn: ");
		lblTongHD_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongHD_1.setBounds(22, 22, 122, 18);
		pnl_ThongtinChiTiet_1.add(lblTongHD_1);

		lbl_SoHDYear = new JLabel();
		lbl_SoHDYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_SoHDYear.setBounds(183, 21, 157, 18);
		pnl_ThongtinChiTiet_1.add(lbl_SoHDYear);

		JLabel lblTongDT_1 = new JLabel("Tổng Doanh Thu:");
		lblTongDT_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongDT_1.setBounds(22, 64, 122, 18);
		pnl_ThongtinChiTiet_1.add(lblTongDT_1);

		lbl_TongDTYear = new JLabel();
		lbl_TongDTYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongDTYear.setBounds(183, 64, 157, 18);
		pnl_ThongtinChiTiet_1.add(lbl_TongDTYear);

		JLabel lblDTPhongThuong_1 = new JLabel("Doanh Thu Phòng Thường:");
		lblDTPhongThuong_1.setBounds(22, 106, 175, 16);
		pnl_ThongtinChiTiet_1.add(lblDTPhongThuong_1);

		lbl_DTPhongTHYear = new JLabel();
		lbl_DTPhongTHYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_DTPhongTHYear.setBounds(183, 104, 157, 18);
		pnl_ThongtinChiTiet_1.add(lbl_DTPhongTHYear);

		JLabel lblDTPhongVIP_1 = new JLabel("Doanh Thu Phòng VIP:");
		lblDTPhongVIP_1.setBounds(22, 148, 149, 16);
		pnl_ThongtinChiTiet_1.add(lblDTPhongVIP_1);

		lbl_DTPhongVIPYear = new JLabel();
		lbl_DTPhongVIPYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_DTPhongVIPYear.setBounds(183, 146, 157, 18);
		pnl_ThongtinChiTiet_1.add(lbl_DTPhongVIPYear);

		JLabel lblSoGioHat_1 = new JLabel("Tổng Số Giờ Hát:");
		lblSoGioHat_1.setBounds(22, 191, 149, 16);
		pnl_ThongtinChiTiet_1.add(lblSoGioHat_1);

		lbl_SoGioYear = new JLabel();
		lbl_SoGioYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_SoGioYear.setBounds(183, 189, 157, 18);
		pnl_ThongtinChiTiet_1.add(lbl_SoGioYear);

		JLabel lblTongTienHat_1 = new JLabel("Tổng Tiền Hát:");
		lblTongTienHat_1.setBounds(22, 241, 149, 16);
		pnl_ThongtinChiTiet_1.add(lblTongTienHat_1);

		lbl_TongTienHatYear = new JLabel();
		lbl_TongTienHatYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongTienHatYear.setBounds(183, 239, 157, 18);
		pnl_ThongtinChiTiet_1.add(lbl_TongTienHatYear);

		JLabel lblTongTienDV_1 = new JLabel("Tổng Tiền Dịch Vụ:");
		lblTongTienDV_1.setBounds(22, 291, 149, 16);
		pnl_ThongtinChiTiet_1.add(lblTongTienDV_1);

		lbl_TongTienDVYear = new JLabel();
		lbl_TongTienDVYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongTienDVYear.setBounds(183, 289, 157, 18);
		pnl_ThongtinChiTiet_1.add(lbl_TongTienDVYear);

		JLabel lblThoiGian_1 = new JLabel("Chọn Năm:");
		lblThoiGian_1.setBounds(22, 20, 101, 21);
		pnl_ThongTin_1.add(lblThoiGian_1);

		cmbYear = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> cmbModel = new DefaultComboBoxModel<Integer>();
		int yearNow = Year.now().getValue();
		for (int i = 2020; i <= yearNow; i++) {
			cmbModel.addElement(i);
		}
		cmbYear.setModel(cmbModel);
		cmbYear.setSelectedItem(yearNow);
		cmbYear.setBounds(119, 19, 113, 22);
		cmbYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int selectedValue = Integer.valueOf(cmbYear.getSelectedItem().toString());
					taoBieuDoThoanhThuTheoNam(selectedValue);
					hienThiThongTinDTTheoNam(selectedValue);
				}
			}
		});

		pnl_ThongTin_1.add(cmbYear);

		// Tạo dữ liệu cho biểu đồ cột
		hienThiThongTinDTTheoNam(yearNow);
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		JFreeChart freeYear = ChartFactory.createBarChart("Biểu đồ Doanh Thu Năm yyyy", "Tháng", "Doanh Thu", dataset,
				PlotOrientation.VERTICAL, false, true, false);
		chartPnlYear = new ChartPanel(freeYear);
		taoBieuDoThoanhThuTheoNam(yearNow);
		pnl_DoanhThuYear.add(chartPnlYear, BorderLayout.CENTER);
	}

	/*
	 * Hiển thị các thông tin báo cáo doanh thu một tháng
	 */
	protected void hienThiThongTinDTTheoThang(LocalDate ngayLap) {
		int soHD = 0;
		long tongTienHD = 0;
		long tongTienHat = 0;
		long tongTienDV = 0;
		long tongPhutHat = 0;
		long tongTienPTH = 0;
		long tongTienPVIP = 0;
		for (HoaDon hoaDon : listHD) {
			if (hoaDon.getNgayLap().getMonthValue() == ngayLap.getMonthValue()
					&& hoaDon.getNgayLap().getYear() == ngayLap.getYear()) {
				soHD += 1;

				long tienMotHD = 0;
				long tienHatMotHD = 0;
				long tienMotDV = 0;
				long soPhutMotHD = 0;
				long tienPTH = 0;
				long tienPVIP = 0;
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hoaDon.getMaHD())) {
						tienMotDV += ct.tinhTongTienDV();
						tienPTH += ct.tinhTongTienDVPhongThuong();
						tienPVIP += ct.tinhTongTienDVPhongVIP();
					}
				}
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hoaDon.getMaHD())) {
						tienHatMotHD += ct.tinhTongTienHat();
						soPhutMotHD += ct.tinhSoPhutHat();
						tienPVIP+=ct.tinhTongTienHatPhongVIP();
						tienPTH += ct.tinhTongTienHatPhongThuong();
						break;
					}
				}
				tongTienDV += tienMotDV;
				tongTienHat += tienHatMotHD;
				tongPhutHat += soPhutMotHD;
				tongTienPTH += tienPTH;
				tongTienPVIP += tienPVIP;
				tongTienHD = tongTienHat + tongTienDV;
			}
		}
		long soGio = tongPhutHat / 60;
		long phutDu = tongPhutHat % 60;
		String soGiosoPhut = soGio + " giờ " + phutDu + " phút";
		lbl_SoHoaDon.setText(soHD + "");
		lbl_TongDoanhThu.setText(decimalFormat.format(tongTienHD));
		lbl_TongTienHat.setText(decimalFormat.format(tongTienHat));
		lbl_TongTienDV.setText(decimalFormat.format(tongTienDV));
		lbl_SoGio.setText(soGiosoPhut);
		lbl_DTPhongThuong.setText(decimalFormat.format(tongTienPTH));
		lbl_DTPhongVIP.setText(decimalFormat.format(tongTienPVIP));
	}

	/*
	 * Tạo Biểu ĐÒ doanh thu của tháng
	 */
	private void taoBieuDoDoanhThuTheoThang(LocalDate ngayLap) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= ngayLap.lengthOfMonth(); i++) {
			long tienMotHD = 0;
			for (HoaDon hd : listHD) {
				if (hd.getNgayLap().getMonthValue() == ngayLap.getMonthValue() // Lọc hóa đơn theo ngày/tháng/năm
						&& hd.getNgayLap().getYear() == ngayLap.getYear() && hd.getNgayLap().getDayOfMonth() == i) {
					for (ChiTietHoaDon ct : listCT) {
						if (ct.getHoaDon().getMaHD().equals(hd.getMaHD())) {
							tienMotHD += ct.tinhTongTien(); // Tính tiền hóa đơn theo ngày
						}
					}
				}
			}
			dataset.addValue(tienMotHD, "Doanh Thu Cua Ngay ", i + "");
		}

		JFreeChart free = ChartFactory.createBarChart(
				"Biểu đồ Doanh Thu Tháng " + ngayLap.getMonthValue() + " Năm " + ngayLap.getYear(), "Ngày", "Doanh Thu",
				dataset, PlotOrientation.VERTICAL, false, true, false);
		chartPnl.setChart(free);
	}

	/*-----------------------------------------------------------Tính Doanh Thu Theo Năm----------------------------------------------------*/
	protected void hienThiThongTinDTTheoNam(int year) {
		int soHD = 0;
		long tongTienHD = 0;
		long tongTienHat = 0;
		long tongTienDV = 0;
		long tongPhutHat = 0;
		long tongTienPTH = 0;
		long tongTienPVIP = 0;
		for (HoaDon hoaDon : listHD) {
			if (hoaDon.getNgayLap().getYear() == year) {
				soHD += 1;

				long tienMotHD = 0;
				long tienHatMotHD = 0;
				long tienMotDV = 0;
				long soPhutMotHD = 0;
				long tienPTH = 0;
				long tienPVIP = 0;
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hoaDon.getMaHD())) {
						tienMotDV += ct.tinhTongTienDV();
						tienPTH += ct.tinhTongTienDVPhongThuong();
						tienPVIP += ct.tinhTongTienDVPhongVIP();
					}
				}
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hoaDon.getMaHD())) {
						tienHatMotHD += ct.tinhTongTienHat();
						soPhutMotHD += ct.tinhSoPhutHat();
						tienPVIP+=ct.tinhTongTienHatPhongVIP();
						tienPTH += ct.tinhTongTienHatPhongThuong();
						break;
					}
				}
				tongTienDV += tienMotDV;
				tongTienHat += tienHatMotHD;
				tongPhutHat += soPhutMotHD;
				tongTienPTH += tienPTH;
				tongTienPVIP += tienPVIP;
				tongTienHD = tongTienHat + tongTienDV;
			}
		}
		long soGio = tongPhutHat / 60;
		long phutDu = tongPhutHat % 60;
		String soGiosoPhut = soGio + " giờ " + phutDu + " phút";
		lbl_SoHDYear.setText(soHD + "");
		lbl_TongDTYear.setText(decimalFormat.format(tongTienHD));
		lbl_TongTienHatYear.setText(decimalFormat.format(tongTienHat));
		lbl_TongTienDVYear.setText(decimalFormat.format(tongTienDV));
		lbl_SoGioYear.setText(soGiosoPhut);
		lbl_DTPhongTHYear.setText(decimalFormat.format(tongTienPTH));
		lbl_DTPhongVIPYear.setText(decimalFormat.format(tongTienPVIP));
	}

	protected void taoBieuDoThoanhThuTheoNam(int year) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= 12; i++) {
			long tienHDMotThang = 0;
			for (HoaDon hd : listHD) {
				if (hd.getNgayLap().getMonthValue() == i && hd.getNgayLap().getYear() == year) { // lọc hóa đơn theo
																									// tháng và năm
					long tienMotHD = 0;
					for (ChiTietHoaDon ct : listCT) {
						if (ct.getHoaDon().getMaHD().equals(hd.getMaHD())) {
							tienMotHD += ct.tinhTongTien();
						}
					}
					tienHDMotThang += tienMotHD; // Sum tất cả hóa đơn theo ngày của tháng
				}
			}
			dataset.addValue(tienHDMotThang, "Doanh Thu Của Thang ", i + "");
		}
		JFreeChart freeYear = ChartFactory.createBarChart("Biểu đồ Doanh Thu Năm " + year, "Tháng", "Doanh Thu",
				dataset, PlotOrientation.VERTICAL, false, true, false);
		chartPnlYear.setChart(freeYear);
	}

	/*-----------------------------------------------------------Tính Doanh Thu Theo Ngày----------------------------------------------------*/
	protected void hienThiBang(LocalDate ngayLap) {
		tblModelDoanhThuNgay.setRowCount(0);
		String headersDV[] = "Mã hóa đơn ;Ngày lập;Tên khách hàng;SĐT;Tổng tiền".split(";");
		DefaultTableModel tableModel = new DefaultTableModel(headersDV, 0);
		for (HoaDon hd : listHD) {
			if (hd.getNgayLap().isEqual(ngayLap)) {
				List<ChiTietHoaDon> dsct = cthd_dao.timCTHoaDonTheoMa(hd.getMaHD());
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				if(!dsct.isEmpty()) {
					cthd = dsct.get(0);
				}
				
				double tongtienDV = 0.0;
				double tienHat = 0.0;
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hd.getMaHD())) {
						tongtienDV += ct.tinhTongTienDV();
					}
				}
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hd.getMaHD())) {
						tienHat += ct.tinhTongTienHat();
						break;
					}
				}
				String TongTien = decimalFormat.format(tongtienDV + tienHat);
				Object[] rowData = { hd.getMaHD(), hd.getNgayLap(), hd.getKhachHang().getHoTen(),
						hd.getKhachHang().getSDT(), TongTien };
				tableModel.addRow(rowData);
			}
		}
		tblDoanhThuNgay.setModel(tableModel);
	}

	protected void hienThiThongTinDTTheoNgay(LocalDate ngayLap) {
		int soHD = 0;
		long tongTienHD = 0;
		long tongTienHat = 0;
		long tongTienDV = 0;
		long tongPhutHat = 0;
		long tongTienPTH = 0;
		long tongTienPVIP = 0;
		long tongDoanhThuTrungBinh = 0;
		for (HoaDon hoaDon : listHD) {
			if (hoaDon.getNgayLap().isEqual(ngayLap)) {
				soHD += 1;
				long tienMotHD = 0;
				long tienHatMotHD = 0;
				long tongTienPhongHD = 0;
				long tienMotDV = 0;
				long soPhutMotHD = 0;
				long tienPTH = 0;
				long tienPVIP = 0;
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hoaDon.getMaHD())) {
						tienMotDV += ct.tinhTongTienDV();
						tienPTH += ct.tinhTongTienDVPhongThuong();
						tienPVIP += ct.tinhTongTienDVPhongVIP();
					}
				}
				for (ChiTietHoaDon ct : listCT) {
					if (ct.getHoaDon().getMaHD().equals(hoaDon.getMaHD())) {
						tienHatMotHD += ct.tinhTongTienHat();
						soPhutMotHD += ct.tinhSoPhutHat();
						tienPVIP+=ct.tinhTongTienHatPhongVIP();
						tienPTH += ct.tinhTongTienHatPhongThuong();
						break;
					}
				}
				tongTienDV += tienMotDV;
				tongTienHat += tienHatMotHD;
				tongPhutHat += soPhutMotHD;
				tongTienPTH += tienPTH;
				tongTienPVIP += tienPVIP;
				tongTienHD = tongTienHat + tongTienDV;
				tongDoanhThuTrungBinh = tongTienHD / soHD;
			}
		}
		long soGio = tongPhutHat / 60;
		long phutDu = tongPhutHat % 60;
		String soGiosoPhut = soGio + " giờ " + phutDu + " phút";
		lblSoHDNgay.setText(soHD + "");
		lblTienTongDoanhThuDay.setText(decimalFormat.format(tongTienHD));
		lblTienPhong.setText(decimalFormat.format(tongTienHat));
		lblTienDVDay.setText(decimalFormat.format(tongTienDV));
		lblTongGioHatDay.setText(soGiosoPhut);
		lblTienPhongThuongDay.setText(decimalFormat.format(tongTienPTH));
		lblTienPhongVipDay.setText(decimalFormat.format(tongTienPVIP));
		lblDoanhThuTrungBinhDay.setText(decimalFormat.format(tongDoanhThuTrungBinh));
	}
}
