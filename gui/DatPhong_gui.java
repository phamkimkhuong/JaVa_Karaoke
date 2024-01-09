package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.border.LineBorder;

import dao.ChiTietPhieuDatPhong_dao;
import dao.KhachHang_dao;
import dao.PhieuDatPhong_dao;
import dao.Phong_dao;
import entity.ChiTietPhieuDatPhong;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class DatPhong_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private Phong_dao phong_dao;
	private Phong phong;
	private KhachHang_dao kh_dao;
	private ThongTinKhachHang_gui ttkh;
	protected JPanel pnl_ContentPaneDP;
	private JPanel pnl_TieuDe;
	private JPanel pnl_CenTer;
	private JPanel pnl_ThaoTac;
	private JPanel pnl_DSPhongTrong;
	private JPanel pnlNhapThongTin;
	private JPanel pnl_TimKiem;
	private JPanel pnl_PhongActive;
	private JPanel pnl_DatPhong;
	private JLabel lblDSPhong;
	private JLabel lblTenKh;
	private JLabel lblGioVao;
	private JLabel lblSDT;
	private JLabel lblNgayNhan;
	private JLabel lblTimPhong;
	private JTable tbl_DSPhongActive;
	private JTable tblDSPhongTrong;
	private JButton btnDatPhong;
	private JButton btnLuu;
	private JButton btnHenDP;
	private JButton btnCapNhap;
	private JButton btnLamMoi;
	private JButton btnDatDichVu;
	private JButton btnTimPhong;
	private JTextField txtTimPhong;
	private JTextField txtSDT;
	private JTextField txtTenKh;
	private JSpinner spnPhut;
	private JSpinner spnGioVao;
	private JScrollPane scr_DSPhongAcTive;

	private JScrollPane scr_DSPhongTrong;
	private JLabel lblTimTheoLoai;
	private JTextField txtEmail;
	private int edit;
	private String sdtKH; //
	private String tenKH; //
	private String trangThai = "";
	private String loaiPhong = "";
	private DefaultTableModel tableModel;
	private static int PDPCounter = 1;
	private int PHDPCounter = 1;
	private LocalDate ngayNhan = LocalDate.now();
	private List<PhieuDatPhong> dsPhieuHenDatPhong;
	private SpinnerNumberModel spnM;
	private SpinnerNumberModel spnMHour;
	private Calendar cal;
	private DecimalFormat decimalFormat;
	private JCheckBox chk_TrangThai;
	private JComboBox<String> cmb_TrangThai;
	private static PhieuDatPhong_dao PDP_dao;

	protected static KhachHang editKH;
	private JLabel lblLoaiPhong;
	private JComboBox<String> cmb_LoaiPhong;
	private JCheckBox chk_LoaiPhong;
	private ChiTietPhieuDatPhong_dao ct_dao;

	/**
	 * Launch the application.
	 */
	public DatPhong_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);

		kh_dao = new KhachHang_dao();
		Color color = new Color(197, 199, 199);

		pnl_ContentPaneDP = new JPanel();
		pnl_ContentPaneDP.setBackground(color);
		pnl_ContentPaneDP.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPaneDP.setLayout(new BorderLayout());
		PDP_dao = new PhieuDatPhong_dao();
		ct_dao = new ChiTietPhieuDatPhong_dao();
		decimalFormat = new DecimalFormat("#,### VND");
		// Lấy thời gian hiện tại

		cal = Calendar.getInstance();

		// Khởi Tạo các Icon

		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
//		Image imgDatDV = new ImageIcon(this.getClass().getResource("" + "/Ordering.24.png")).getImage();
		Image imgDP = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();

		/*
		 * (1) Panel North: Panel tiêu đề
		 */

		pnl_TieuDe = new JPanel();
		pnl_TieuDe.setBackground(color);
		pnl_ContentPaneDP.add(pnl_TieuDe, BorderLayout.NORTH);

		lblDSPhong = new JLabel("Đặt Phòng Hát");
		lblDSPhong.setForeground(new Color(15, 102, 165));
		lblDSPhong.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_TieuDe.add(lblDSPhong);

		/*
		 * (2) PanelCenTer: chứa panel Danh sách phòng chưa sử dụng và Panel danh sách
		 * phòng đang sử dụng
		 */

		pnl_CenTer = new JPanel();
		pnl_CenTer.setBackground(color);
		pnl_ContentPaneDP.add(pnl_CenTer, BorderLayout.CENTER);
		pnl_CenTer.setLayout(new BoxLayout(pnl_CenTer, BoxLayout.Y_AXIS));

		/*
		 * 
		 * Panel Danh sách phòng chưa sử dụng
		 */
		pnl_DSPhongTrong = new JPanel();
		pnl_DSPhongTrong.setBackground(color);
		pnl_CenTer.add(pnl_DSPhongTrong);
		pnl_DSPhongTrong.setBorder(new TitledBorder(null, "Danh s\u00E1ch ph\u00F2ng ngày " + ngayNhan,
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_DSPhongTrong.setLayout(new BorderLayout(0, 0));
		tblDSPhongTrong = new JTable();

		/*
		 * khởi Tạo class Phòng_Dao : lấy danh sách phòng
		 */
		phong_dao = new Phong_dao();
		// Đưa danh sách phòng hiển thị lên bảng
		List<Phong> dsPhong = phong_dao.selectALL();
		for (Phong p : dsPhong) {
			p.setTrangThai("Trống");
			phong_dao.update(p);
		}
		setTrangThaiPhong();
		dsPhong = phong_dao.selectALL();
		String[] columnPhong = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Trạng Thái", "Giá hát" };
		tblDSPhongTrong.setModel(taoBang(dsPhong, columnPhong));
		// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên JTable
		tblDSPhongTrong.setDefaultEditor(Object.class, null);

		scr_DSPhongTrong = new JScrollPane(tblDSPhongTrong);
		pnl_DSPhongTrong.add(scr_DSPhongTrong);

		/*
		 * Pane ThaoTac: gồm chức năng đặt phòng ngay , hẹn đặt phòng nhật
		 */

		pnl_ThaoTac = new JPanel();
		pnl_ThaoTac.setBackground(color);
		pnl_ThaoTac.setPreferredSize(new Dimension(10, 50));
		pnl_CenTer.add(pnl_ThaoTac);
		pnl_ThaoTac.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Button đặt phòng mới, khi khách hàng đặt phòng hát tại quầy

		btnDatPhong = new JButton("Đặt Phòng Ngay");
		btnDatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datPhongNgay();
			}
		});
		btnDatPhong.setIcon(new ImageIcon(imgDP));
		pnl_ThaoTac.add(btnDatPhong);

		// Button Hẹn Đặt Phòng, khi khách hàng muốn đặt phòng cho ngày khác

		btnHenDP = new JButton("Hẹn Đặt Phòng");
		btnHenDP.addActionListener(new ActionListener() {
			private JTextField txtNgayLap;

			public void actionPerformed(ActionEvent e) {
				henDatPhong();

//				spnGioVao.setMinimum(8);

				// Định dạng ngày tháng theo định dạng mong muốn
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

				// Chuyển đổi ngày thành chuỗi theo định dạng
				String formattedDate = dateFormat.format(cal.getTime());

				txtNgayLap = new JTextField(formattedDate);
			}
		});
		pnl_ThaoTac.add(btnHenDP);

		/*
		 * Panel Danh sách phòng đang sử dụng
		 */
		pnl_PhongActive = new JPanel();
		pnl_PhongActive.setBackground(color);
		pnl_PhongActive.setPreferredSize(new Dimension(10, 500));
		pnl_PhongActive.setBorder(BorderFactory.createTitledBorder("" + "Danh sách phòng đang sử dụng"));
		pnl_CenTer.add(pnl_PhongActive);
		pnl_PhongActive.setLayout(new BorderLayout(0, 0));

		tbl_DSPhongActive = new JTable();
		List<Phong> dsPhongActive = new ArrayList<Phong>();

		tableModel = new DefaultTableModel();
		String[] columnNamesPhongActive = { "Tên Khách Hàng", "SDT Khách Hàng", "Tên Phòng", "Loại Phòng", "Giá",
				"Giờ Vào" };
		for (int i = 0; i < columnNamesPhongActive.length; i++) {
			tableModel.addColumn(columnNamesPhongActive[i]);
		}
		List<PhieuDatPhong> ds = PDP_dao.getAllPhieuDatPhong();
		for (PhieuDatPhong p : ds) {
			ChiTietPhieuDatPhong ct = TimCTPDPTheoMaPhieu(p.getMaPhieu());
			if (ct.getNgayNhan().isEqual(LocalDate.now()) && !p.getTrangThai().equals("Đã Thanh Toán")) {
				String TienHat = decimalFormat.format(ct.getPhong().getLoaiPhong().getGiaHat());
				Object[] rowData = { p.getKhachHang().getHoTen(), p.getKhachHang().getSDT(), ct.getPhong().getTenPhong(),
						ct.getPhong().getLoaiPhong().getTenLoai(), TienHat, ct.getGioVao() };
				tableModel.addRow(rowData);
			}
		}
		tbl_DSPhongActive.setModel(tableModel);
		tbl_DSPhongActive.setDefaultEditor(Object.class, null);// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên
																// JTable
		scr_DSPhongAcTive = new JScrollPane(tbl_DSPhongActive);
		pnl_PhongActive.add(scr_DSPhongAcTive);

		/*
		 * Panel Nhâp Thông tin: chứa Panel tìm kiếm và panel đặt phòng
		 */
		pnlNhapThongTin = new JPanel();
		pnlNhapThongTin.setBackground(UIManager.getColor("Button.background"));
		pnlNhapThongTin.setPreferredSize(new Dimension(330, 10));
		pnl_ContentPaneDP.add(pnlNhapThongTin, BorderLayout.WEST);
		pnlNhapThongTin.setLayout(new BoxLayout(pnlNhapThongTin, BoxLayout.Y_AXIS));
		/*
		 * Panel Tìm kiếm Phòng theo tên và theo loại
		 */
		pnl_TimKiem = new JPanel();
		pnl_TimKiem.setBackground(color);
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông Tin Tìm Kiếm");
		pnl_TimKiem.setBorder(titledBorder);
		pnlNhapThongTin.add(pnl_TimKiem);
		pnl_TimKiem.setLayout(null);

		lblTimPhong = new JLabel("Tìm phòng:");
		lblTimPhong.setBounds(10, 24, 69, 14);
		pnl_TimKiem.add(lblTimPhong);

		txtTimPhong = new JTextField();
		txtTimPhong.setForeground(SystemColor.controlShadow);
		txtTimPhong.setBounds(89, 21, 199, 28);
		pnl_TimKiem.add(txtTimPhong);
		txtTimPhong.setToolTipText("Nhập thông tin phòng cần tìm ");
		btnTimPhong = new JButton("Tìm Phòng");
		btnTimPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemPhong();
			}
		});
		btnTimPhong.setBounds(28, 163, 125, 33);
		pnl_TimKiem.add(btnTimPhong);
		btnTimPhong.setHorizontalAlignment(SwingConstants.LEFT);
		btnTimPhong.setIcon(new ImageIcon(imgTim));

		// btnCapNhap = new JButton("Cập Nhật");
		// btnCapNhap.setHorizontalTextPosition(SwingConstants.RIGHT);
		// Image imgCapNhat = new ImageIcon(this.getClass().getResource("" +
		// "/Text-Edit.24.png")).getImage();
		// btnCapNhap.setIcon(new ImageIcon(imgCapNhat));
		// pnl_ThaoTac.add(btnCapNhap);

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiDanhSachPhong();
			}
		});
		btnLamMoi.setBounds(175, 163, 125, 33);
		pnl_TimKiem.add(btnLamMoi);
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));
		btnLamMoi.setHorizontalTextPosition(SwingConstants.RIGHT);

		JLabel lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setBounds(10, 68, 81, 18);
		pnl_TimKiem.add(lblTrangThai);

		cmb_TrangThai = new JComboBox<String>();
		DefaultComboBoxModel<String> defaultcmb = new DefaultComboBoxModel<String>(
				new String[] { "Trống", "Chờ Nhận Phòng", "Đang sử dụng" });
		cmb_TrangThai.setModel(defaultcmb);
		cmb_TrangThai.setBounds(89, 65, 133, 24);
		cmb_TrangThai.setEnabled(false);
		cmb_TrangThai.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					trangThai = cmb_TrangThai.getSelectedItem().toString();
					timPHTheoTrangThaiVaLP();
				}
			}
		});
		pnl_TimKiem.add(cmb_TrangThai);

		chk_TrangThai = new JCheckBox("");
		chk_TrangThai.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// Kiểm tra trạng thái mới của checkbox
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Nếu checkbox được chọn, vô hiệu hóa Combobox trạng thái
					cmb_TrangThai.setEnabled(true);
				} else {
					// Nếu checkbox không được chọn, kích hoạt ComBobox trạng thái
					cmb_TrangThai.setEnabled(false);
					trangThai = "";
				}
			}
		});
		chk_TrangThai.setBounds(228, 68, 24, 26);
		pnl_TimKiem.add(chk_TrangThai);

		lblLoaiPhong = new JLabel("Loại Phòng:");
		lblLoaiPhong.setBounds(10, 113, 81, 18);
		pnl_TimKiem.add(lblLoaiPhong);

		cmb_LoaiPhong = new JComboBox<String>();
		cmb_LoaiPhong.setBounds(88, 110, 133, 24);
		cmb_LoaiPhong.setModel(QuanLyPhong_gui.getTenLoaiPhong());
		cmb_LoaiPhong.setEnabled(false);
		cmb_LoaiPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					loaiPhong = cmb_LoaiPhong.getSelectedItem().toString();
					timPHTheoTrangThaiVaLP();
				}
			}
		});
		pnl_TimKiem.add(cmb_LoaiPhong);

		chk_LoaiPhong = new JCheckBox("");
		chk_LoaiPhong.setBounds(228, 111, 24, 20);
		chk_LoaiPhong.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// Kiểm tra trạng thái mới của checkbox
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Nếu checkbox được chọn, vô hiệu hóa Combobox Loại Phòng
					cmb_LoaiPhong.setEnabled(true);
				} else {
					// Nếu checkbox không được chọn, kích hoạt ComBobox Loại Ph
					cmb_LoaiPhong.setEnabled(false);
					loaiPhong = "";
				}
			}
		});
		pnl_TimKiem.add(chk_LoaiPhong);
		/*
		 * Panel Đặt Phòng
		 */
		pnl_DatPhong = new JPanel();
		pnl_DatPhong.setBackground(color);
		pnl_DatPhong.setPreferredSize(new Dimension(10, 180));

		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder2 = new TitledBorder(lineBorder, "Đặt Phòng");
		pnl_DatPhong.setBorder(titledBorder2);

		pnlNhapThongTin.add(pnl_DatPhong);
		pnl_DatPhong.setLayout(null);

		lblSDT = new JLabel("SDT Khách Hàng:");
		lblSDT.setBounds(20, 32, 103, 14);
		pnl_DatPhong.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setEnabled(false);
		txtSDT.setBounds(20, 57, 245, 26);
		pnl_DatPhong.add(txtSDT);
		txtSDT.setColumns(10);

		lblTenKh = new JLabel("Tên Khách Hàng:");
		lblTenKh.setBounds(20, 103, 103, 14);
		pnl_DatPhong.add(lblTenKh);

		txtTenKh = new JTextField();
		txtTenKh.setColumns(10);
		txtTenKh.setBounds(20, 128, 245, 26);
		txtTenKh.setEnabled(false);
		pnl_DatPhong.add(txtTenKh);

		lblGioVao = new JLabel("Giờ Vào:");
		lblGioVao.setBounds(21, 274, 60, 14);
		pnl_DatPhong.add(lblGioVao);

		// Lấy Giờ hiện tại để đặt giờ vào (giờ mở cửa 7h00p-23h59p)
		// Láy Phút Hiện tại

		int mitute = cal.get(Calendar.MINUTE);
		spnM = new SpinnerNumberModel(mitute, mitute, 59, 1);
		SpinnerModel spnM2 = new SpinnerNumberModel(0, 0, 59, 1);
		spnPhut = new JSpinner(spnM);
		spnPhut.setEnabled(false);
		spnPhut.setBounds(151, 271, 50, 20);
		pnl_DatPhong.add(spnPhut);

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		hour = (hour < 7) ? 7 : hour;
		spnMHour = new SpinnerNumberModel(hour, 7, 23, 1);
		spnGioVao = new JSpinner(spnMHour);
		spnGioVao.setEnabled(false);
		// Đặt giờ vào là giờ,phútmin là phút hiện tại, nếu h vào > giờ hiện tại, reset
		// là phútmin = 0
		spnGioVao.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int slHour = (int) spnGioVao.getValue();
				if (slHour != Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
					spnPhut.setModel(spnM2);
				else
					spnPhut.setModel(spnM);
			}
		});
		spnGioVao.setBounds(91, 271, 50, 20);

		pnl_DatPhong.add(spnGioVao);

		// Button Kiểm tra thông tin khách hàng, nếu khách hàng mới thì thêm mới khách
		// hàng
		// Nếu khách hàng củ thì không, nếu khách hàng đổi SDT khác thì cấp nhật lại SDT
		// (nếu cần)
		Image imgSave = new ImageIcon(this.getClass().getResource("" + "/Save.24.png")).getImage();
		btnLuu = new JButton("Lưu ");
		btnLuu.setIcon(new ImageIcon(imgSave));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kiemTraKHandSave();
			}
		});
		btnLuu.setBounds(61, 324, 157, 33);
		btnLuu.setEnabled(false);
		pnl_DatPhong.add(btnLuu);

		lblNgayNhan = new JLabel("Ngày Nhận Phòng:");
		lblNgayNhan.setVisible(false);
		lblNgayNhan.setBounds(20, 210, 245, 14);
		pnl_DatPhong.add(lblNgayNhan);

		// Thiết Lập thời gian ngày lớn nhất được chọn là ngày hiện tại + 2

		cal.add(Calendar.DAY_OF_MONTH, 2);

		setContentPane(pnl_ContentPaneDP);
	}

	/*
	 * Đặt lại trạng thái phòng ngày hôm nay để thực hiện "Đặt Phòng Ngay"
	 */

	/*
	 * Phương thức run khi click vào Button "Hẹn Đặt Phòng"
	 */
	protected void henDatPhong() {
		if (btnHenDP.getText().equalsIgnoreCase("Hủy")) {
			moKhoaHenDatPhong(false);
			btnLuu.setEnabled(false);
			btnHenDP.setText("Hẹn Đặt Phòng");
		} else if (btnHenDP.getText().equalsIgnoreCase("Hẹn Đặt Phòng")) {
			txtSDT.setText("");
			txtTenKh.setText("");
			FormNgayHenDatPhong hdp = new FormNgayHenDatPhong(this);
			int userOption = hdp.getUserOption();
			if (userOption == JOptionPane.YES_OPTION) {
				edit = 2;
				ngayNhan = hdp.getSelectedDate();
				pnl_DSPhongTrong.setBorder(new TitledBorder(null, "Danh s\u00E1ch ph\u00F2ng ngày " + ngayNhan,
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				taoBangNgayNhan();
				lblNgayNhan.setText("Ngày Nhận Phòng:" + ngayNhan);
				moKhoaHenDatPhong(true);
				btnLuu.setEnabled(true);
				btnHenDP.setText("Hủy");
			}
		}

	}

// Kiểm tra trạng thái Phiếu đặt/hẹn phòng => update thành trạng thái phòng 
	protected void setTrangThaiPhong() {
		List<PhieuDatPhong> dsDatPhongNgay = PDP_dao.getAllPhieuDatPhong();
		List<PhieuDatPhong> dsDatPhongCho = PDP_dao.getPhieuHenDatPhong();
		// Kiểm tra "Phiếu Hẹn đặt phòng" các tình trạng của phiếu hẹn 3 trạng thái

		// Điều Kiện : "Ngày Nhận Phòng" = "Ngày Hôm Nay" ****
		// TH1 : Phiếu hẹn "Đã nhận phòng" => phòng "Đang sử dụng"
		// TH2 : Phiếu "Hết hạn" => phòng "trống"
		// TH3 : Ngược lại Phiếu "Chờ Nhận Phòng" => Chờ Nhận Phòng

		// Điều Kiện Ngoại Lệ : "Ngày Nhận Phòng" != "Ngày Hôm Nay"
		// và Trạng Thái Phiếu "Chờ Nhận Phòng" => Phòng "Trống" (Tránh TH chuyển đổi
		// ngày ko làm mới Trạng thái Phòng)
		for (PhieuDatPhong DatP : dsDatPhongCho) {
			ChiTietPhieuDatPhong ct = TimCTPDPTheoMaPhieu(DatP.getMaPhieu());
			if (ct.getNgayNhan().isEqual(ngayNhan)) {
				if (DatP.getTrangThai().equalsIgnoreCase("Đã Nhận Phòng"))
					ct.getPhong().setTrangThai("Đang sử dụng");
				if (DatP.getTrangThai().equalsIgnoreCase("Hết Hạn"))
					ct.getPhong().setTrangThai("Trống");
				else // Không dùng if thì vòng for "Hen" phải lọc trước "đặt ngay"
					ct.getPhong().setTrangThai("Chờ Nhận Phòng");

				phong_dao.update(ct.getPhong());
			} else if (!ct.getNgayNhan().isEqual(ngayNhan)
					&& ct.getPhong().getTrangThai().equals("Chờ Nhận Phòng")) {
				ct.getPhong().setTrangThai("Trống");
				phong_dao.update(ct.getPhong());
			}
		} // Kết Thúc Vòng Lặp
			// Kiểm tra "Phiếu đặt phòng" các tình trạng Phiếu đặt 2 tráng thái

		// Điều Kiện : "Ngày Nhận Phòng" = "Ngày Hôm Nay" ****
		// TH1 : Phiếu "Đang sử dụng" => Phòng "đang sử dụng"
		// TH2 : Phiếu "Đã thanh toán" => Phòng "trống"
		for (PhieuDatPhong DatP : dsDatPhongNgay) {
			ChiTietPhieuDatPhong ct = TimCTPDPTheoMaPhieu(DatP.getMaPhieu());
			if (ct.getNgayNhan().isEqual(ngayNhan)) {
				if (DatP.getTrangThai().equals("Đang sử dụng")) {
//					System.out.println(DatP.toString());
					ct.getPhong().setTrangThai("Đang sử dụng");
				} else if (DatP.getTrangThai().equals("Đã Thanh Toán")) {
					ct.getPhong().setTrangThai("Trống");
				}
				phong_dao.update(ct.getPhong());
			}
		}
	}

	private ChiTietPhieuDatPhong TimCTPDPTheoMaPhieu(String maPhieu) {
	ChiTietPhieuDatPhong ctpdp = new ChiTietPhieuDatPhong();
	List<ChiTietPhieuDatPhong> dsct = ct_dao.getAllPDatPhong();
	for (ChiTietPhieuDatPhong ct : dsct) {
		if(ct.getPhieuDatPhong().getMaPhieu().trim().equalsIgnoreCase(maPhieu.trim())) {
			ctpdp=ct;
			break;
		}
	}
	return ctpdp;
}

	/*
	 * Tạo Phiếu hẹn đặt phòng (sau khi click hẹn đặt phòng -> click lưu -> Kiểm tra
	 * KH và save)
	 */
	private void taoPhieuHenDatPhong(KhachHang kh) {
		int gio = (int) spnGioVao.getValue();
		int phut = (int) spnPhut.getValue();
		LocalTime gioVao = LocalTime.of(gio, phut);
		NhanVien nv = new NhanVien("NV000001", tenKH);
		// Chuyển giá trị giờ và phút thành LocalTime
		phong.setTrangThai("Chờ Nhận Phòng");
		phong_dao.update(phong);
		PhieuDatPhong pdp = new PhieuDatPhong(taoMaPhieuHenDatPhong(), LocalDate.now(), kh, nv, "Chờ Nhận Phòng");
		ChiTietPhieuDatPhong ct = new ChiTietPhieuDatPhong(pdp, phong, ngayNhan, gioVao);
		PDP_dao.addPDPhong(pdp);
		ct_dao.addCTPDPhong(ct);

		lamMoiDanhSachPhong();
	}

	/*
	 * Đặt lại trạng thái phòng cho từng ngày => khi click vào ngày hẹn đặt phòng
	 * thì set trạng thái ngày đó
	 */
	protected void taoBangNgayNhan() {
		List<Phong> dsPhong = phong_dao.selectALL();
		dsPhieuHenDatPhong = PDP_dao.getPhieuHenDatPhong();
		// Điều Kiện : Ngày Nhận Phòng = "Ngày Hôm Nay"
		if (ngayNhan.isEqual(LocalDate.now())) {
			List<PhieuDatPhong> dsPDP = PDP_dao.getAllPhieuDatPhong();
			// Kiểm Tra Trạng Thái "Phiếu Hẹn Đặt Phòng"
			// TH1: "Đã Nhận Phòng" => Phòng "Đang sử dụng"
			// TH2: "Chờ Nhận Phòng" => Phòng "Chờ Nhận Phòng"
			// Th3: Ngược lại "Hết Hạn" => Phòng "Trống"
			// TH Khác Ngày Hôm Nay : "Chờ Nhận Phòng" => "Phòng Trống"
			for (PhieuDatPhong pHDP : dsPhieuHenDatPhong) {
				ChiTietPhieuDatPhong ct = TimCTPDPTheoMaPhieu(pHDP.getMaPhieu());
				if (ct.getNgayNhan().isEqual(ngayNhan)) {

					if (pHDP.getTrangThai().equalsIgnoreCase("Đã Nhận Phòng"))
						ct.getPhong().setTrangThai("Đang sử dụng");

					else if (pHDP.getTrangThai().equalsIgnoreCase("Chờ Nhận Phòng"))
						ct.getPhong().setTrangThai("Chờ Nhận Phòng");

					else
						ct.getPhong().setTrangThai("Trống");

					phong_dao.update(ct.getPhong());
				} else if (ct.getPhong().getTrangThai().equals("Chờ Nhận Phòng")) {
					ct.getPhong().setTrangThai("Trống");
					phong_dao.update(ct.getPhong());
				}
			} // Kết Thúc Vòng Lặp
				// Kiểm Tra Trạng Thái "Phiếu Đặt Phòng"
				// TH1: "Đang sử dụng" => Phòng "Đang sử dụng"
			for (PhieuDatPhong pDP : dsPDP) {
				ChiTietPhieuDatPhong ct = TimCTPDPTheoMaPhieu(pDP.getMaPhieu());
				if (pDP.getTrangThai().equalsIgnoreCase("Đang sử dụng")) {
					ct.getPhong().setTrangThai("Đang sử dụng");
					phong_dao.update(ct.getPhong());
				}
			} // Kết Thúc Vòng Lặp
		}
		// Điều Kiện: "Ngày Nhận Phòng" là Ngày Khác
		else {
			// Làm Trống Tất Cả Trạng Thái Phòng
			for (Phong p : dsPhong) {
				p.setTrangThai("Trống");
				phong_dao.update(p);
			}
			// Kiểm tra Trạng Thái "Phiếu Hẹn Đặt Phòng"
			// TH1 : "Chờ Nhận Phòng" => Phòng "Trống"
			// Th2 : "Hết Hạn" => Phòng "Trống" không cần viết vì đã reset ở vòng for trên
			for (PhieuDatPhong pHDP : dsPhieuHenDatPhong) {
				ChiTietPhieuDatPhong ct = TimCTPDPTheoMaPhieu(pHDP.getMaPhieu());
				if (ct.getNgayNhan().isEqual(ngayNhan) && pHDP.getTrangThai().equals("Chờ Nhận Phòng")) {
					ct.getPhong().setTrangThai("Chờ Nhận Phòng");
					phong_dao.update(ct.getPhong());
				}
			} // Kết thúc Vòng For
		}
		lamMoiDanhSachPhong();
	}

	/*
	 * Click button đặt phòng ngay thì mở khóa nhập liệu, mở khóa button lưu; xem
	 * danh sách phòng hiện tại
	 */
	protected void datPhongNgay() {
		if (btnDatPhong.getText().equalsIgnoreCase("Hủy")) {
			moKhoaDatPhong(false);
			btnDatPhong.setText("Đặt Phòng Ngay");
		} else if (btnDatPhong.getText().equalsIgnoreCase("Đặt Phòng Ngay")) {
			ngayNhan = LocalDate.now();
			txtSDT.setText("");
			txtTenKh.setText("");
			pnl_DSPhongTrong.setBorder(new TitledBorder(null, "Danh s\u00E1ch ph\u00F2ng ngày " + ngayNhan,
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			edit = 1;
			moKhoaDatPhong(true);
			List<Phong> dsPhong = phong_dao.selectALL();
			for (Phong p : dsPhong) {
				p.setTrangThai("Trống");
				phong_dao.update(p);
			}
			setTrangThaiPhong();
			lamMoiDanhSachPhong();
			btnDatPhong.setText("Hủy");
		}
	}

	/*
	 * Kiểm tra đã chọn phòng trống cần đặt hay chưa
	 */
	protected boolean kiemTraPhongIsSelected() {
		int n = tblDSPhongTrong.getSelectedRow();
		if (n != -1) {
			String trangThai = tblDSPhongTrong.getModel().getValueAt(n, 3).toString();
			if (trangThai.equals("Trống")) {
				String ma = tblDSPhongTrong.getModel().getValueAt(n, 0).toString();
				String ten = tblDSPhongTrong.getModel().getValueAt(n, 1).toString();
				String loai = tblDSPhongTrong.getModel().getValueAt(n, 2).toString();
				String chiSoTien = tblDSPhongTrong.getValueAt(n, 4).toString().replaceAll("[^\\d]", "");
				// Chuyển đổi chuỗi thành giá trị kiểu double
				double soTien = Double.parseDouble(chiSoTien);
//				Double gia = Double.parseDouble(tblDSPhongTrong.getModel().getValueAt(n, 4).toString());
				LoaiPhong lp = new LoaiPhong(loai, soTien);
				phong = new Phong(ma, ten, lp, trangThai);
				return true;
			} else
				JOptionPane.showMessageDialog(null, "Phòng đang sử dụng!");
		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn phòng cần đặt!");
		return false;
	}

	/*
	 * Kiểm tra khách hàng(mới,củ,cập nhật khách hàng) và đặt phòng
	 */
	protected void kiemTraKHandSave() {
		sdtKH = txtSDT.getText().trim();
		tenKH = txtTenKh.getText().trim();
		if (kiemTraPhongIsSelected()) {
			if (!tenKH.equals("") && (!sdtKH.equals(""))) {
				// KTra SDT phải là 10 ký tự số và bắt đầu = 0
				if (sdtKH.startsWith("0") && sdtKH.length() == 10 && sdtKH.matches("\\d+")) {
					// Tạo câu lệnh truy vấn tìm kiếm trên sql
					String khachHang = " hoTen like N'%" + tenKH + "%' or SDT = '" + sdtKH + "'";
					String kh2 = " hoTen like N'" + tenKH + "' and SDT = '" + sdtKH + "'";
					List<KhachHang> dskh = kh_dao.findKhachHang(khachHang);
					List<KhachHang> dskh2 = kh_dao.findKhachHang(kh2);
					/*
					 * Kiểm tra khách hàng củ -> đặt phòng
					 */
					if (dskh2.size() == 1) {
						JOptionPane.showMessageDialog(null, "Đặt Phòng Thành Công!");
						if (edit == 1) {
							taoBangDatPhong(dskh2.get(0));
							btnDatPhong.setText("Đặt Phòng Ngay");
							btnHenDP.setEnabled(true);
						} else if (edit == 2) {
							taoPhieuHenDatPhong(dskh2.get(0));
							btnHenDP.setText("Hẹn Đặt Phòng");
							btnDatPhong.setEnabled(true);
						}
						btnLuu.setEnabled(false);
						/*
						 * Kiểm tra Khách Hàng mới-> đặt phòng,
						 */
					} else if (dskh.size() == 0) {
						int tb = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm mới khách hàng không ?");
						if (tb == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(this, "Đặt Phòng Thành Công!");
							if (edit == 1) {
								taoBangDatPhong(taoMoiKhachHang());
								btnDatPhong.setText("Đặt Phòng Ngay");
								btnHenDP.setEnabled(true);
							} else if (edit == 2) {
								taoPhieuHenDatPhong(taoMoiKhachHang());
								btnHenDP.setText("Hẹn Đặt Phòng");
								btnDatPhong.setEnabled(true);
							}
							txtSDT.setEnabled(false);
							txtTenKh.setEnabled(false);
							btnLuu.setEnabled(false);
							
						} else {
							JOptionPane.showMessageDialog(null, "Đặt Phòng Không Thành Công!");
							btnLuu.setEnabled(false);
							btnDatPhong.setText("Đặt Phòng Ngay");
						}
						/*
							 * Kiểm tra Khách hàng củ dùng SDT khác -> đặt Phòng or là khách hàng mới trùng
							 * tên
							 */
					} else if (dskh.size() >= 1) {
						FormDatPhongHat form = new FormDatPhongHat(this, dskh, new KhachHang(tenKH, sdtKH));
						int userOption = form.getUserOption();
						if (userOption == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(form, "Đặt Phòng Thành Công!");
							if (edit == 1) {
								taoBangDatPhong(editKH);
								btnDatPhong.setText("Đặt Phòng Ngay");
								btnHenDP.setEnabled(true);
							} else if (edit == 2) {
								taoPhieuHenDatPhong(editKH);
								btnHenDP.setText("Hẹn Đặt Phòng");
								btnDatPhong.setEnabled(true);
							}

							txtSDT.setEnabled(false);
							txtTenKh.setEnabled(false);
							btnLuu.setEnabled(false);
						} else if (userOption == JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(null, "Đặt Phòng Thành Công!");
							txtSDT.setEnabled(false);
							txtTenKh.setEnabled(false);
							btnLuu.setEnabled(false);
							taoBangDatPhong(taoMoiKhachHang());
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "SDT phải là 10 ký tự số theo định dạng [0XXXXXXXXX]");
			} else
				JOptionPane.showMessageDialog(null, "Hãy nhập thông tin khách hàng cần đặt phòng");
		}
	}

	/*
	 * Tạo bảng các chứa danh sách khách hàng và phòng đang sử dụng phòng
	 */
	protected void taoBangDatPhong(KhachHang kh) {
		List<PhieuDatPhong> dsPDP = PDP_dao.getAllPhieuDatPhong();
		int gio = (int) spnGioVao.getValue();
		int phut = (int) spnPhut.getValue();
		// Chuyển giá trị giờ và phút thành LocalTime
		LocalTime gioVao = LocalTime.of(gio, phut);
		NhanVien nv = new NhanVien("NV000001", tenKH);
		phong.setTrangThai("Đang sử dụng");
		phong_dao.update(phong);
		PhieuDatPhong pdp = new PhieuDatPhong(taoMaPhieuDatPhong(), LocalDate.now(), kh, nv, "Đang sử dụng");
		ChiTietPhieuDatPhong ctpdp = new ChiTietPhieuDatPhong(pdp, phong, LocalDate.now(), gioVao);
		PDP_dao.addPDPhong(pdp);
		ct_dao.addCTPDPhong(ctpdp);
		List<PhieuDatPhong> dsDatPhongNgay = PDP_dao.getAllPhieuDatPhong();
		tableModel.setRowCount(0);
		for (PhieuDatPhong pDP : dsDatPhongNgay) {
			ChiTietPhieuDatPhong ct = TimCTPDPTheoMaPhieu(pDP.getMaPhieu());
			if (ct.getNgayNhan().isEqual(LocalDate.now()) && !pDP.getTrangThai().equals("Đã Thanh Toán")) {
				String TienHat = decimalFormat.format(ct.getPhong().getLoaiPhong().getGiaHat());
				Object[] rowData = { pDP.getKhachHang().getHoTen(), pDP.getKhachHang().getSDT(),
						ct.getPhong().getTenPhong(), ct.getPhong().getLoaiPhong().getTenLoai(), TienHat,
						ct.getGioVao() };
				tableModel.addRow(rowData);
			}
		}
		lamMoiDanhSachPhong();
		tbl_DSPhongActive.setModel(tableModel);
	}

	/*
	 * Tìm Kiếm các Phòng theo thông tin phòng
	 */
	protected void timKiemPhong() {
		String a = txtTimPhong.getText();
		String phong = "";
		Double gia = 0.0;
		if (!a.equals("")) {
			if (a.chars().allMatch(Character::isDigit)) { // Kiểm tra txtTimPhong phải là số
				gia = Double.valueOf(a); // Ép kiểu txtTimphong sang số ==> tìm phòng theo giá
			}
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			phong = "maPhong like N'%" + a + "%' or tenPhong like N'%" + a + "%' or tenLoai like  N'%" + a
					+ "%' or trangThai like N'%" + a + "%' or giaHat = " + gia;
			// Trả về danh sách phòng tìm kiếm => tạo tableModel theo ds Phòng => add vào
			// table
			String[] columnPhongNoUse = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Trạng Thái", "Giá hát" };
			tblDSPhongTrong.setModel(taoBang(phong_dao.findByCondition(phong), columnPhongNoUse));
		} else
			JOptionPane.showMessageDialog(null, "Hãy Nhập Thông Tin Tìm Kiếm!");
	}

	// Làm mới lại ds phòng sau khi tìm kiếm (bổ sung nếu quản lý thêm
	// phòng mới vẫn làm mới đc )
	protected void lamMoiDanhSachPhong() {
		txtTimPhong.setText("");
		List<Phong> dsPhongLamMoi = new ArrayList<Phong>();
		List<Phong> dsPhong = phong_dao.selectALL();
		for (Phong p : dsPhong) {
			dsPhongLamMoi.add(p);
		}
		String[] columnPhong = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Trạng Thái", "Giá hát" };
		tblDSPhongTrong.setModel(taoBang(dsPhongLamMoi, columnPhong));
	}

	/*
	 * Tìm Phòng Theo Trạng Thái
	 */
	protected void timPHTheoTrangThaiVaLP() {
		String timKiem = "";
		if (trangThai.equals("")) {
			timKiem = " tenLoai = N'" + loaiPhong + "'";
		} else if (loaiPhong.equals(""))
			timKiem = " trangThai = N'" + trangThai + "'";
		else
			timKiem = " trangThai = N'" + trangThai + "' and " + " tenLoai = N'" + loaiPhong + "'";
		String[] columnPhongNoUse = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Trạng Thái", "Giá hát" };
		tblDSPhongTrong.setModel(taoBang(phong_dao.findByCondition(timKiem), columnPhongNoUse));
	}

	/*
	 * Tìm Phòng theo Loại Phòng
	 */
//	protected void timPhongTheoLoaiPhong() {
//		String timKiem = ;
//		String[] columnPhongNoUse = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Trạng Thái", "Giá hát" };
//		tblDSPhongTrong.setModel(taoBang(phong_dao.findPhong(timKiem), columnPhongNoUse));
//	}
	/*
	 * Truyền vào danh sách phòng, trả về table Model theo danh sách phòng
	 */
	protected DefaultTableModel taoBang(List<Phong> ds, String[] column) {
		DefaultTableModel table = new DefaultTableModel();
		for (int i = 0; i < column.length; i++) {
			table.addColumn(column[i]);
		}
		for (Phong p : ds) {
			String TienHat = decimalFormat.format(p.getLoaiPhong().getGiaHat());
			Object[] rowData = { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getTenLoai(), p.getTrangThai(),
					TienHat };
			table.addRow(rowData);
		}
		return table;
	}

	/*
	 * Mở Khóa lưu , khóa đặt phòng ngay, mở khóa các JtextField nhập liêuj
	 */
	protected void moKhoaHenDatPhong(boolean b) {
		int mitute = cal.get(Calendar.MINUTE);
		spnM = new SpinnerNumberModel(mitute, mitute, 59, 1);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		hour = (hour < 7) ? 7 : hour;
		spnMHour = new SpinnerNumberModel(hour, 7, 23, 1);
		btnDatPhong.setEnabled(!b);
		lblNgayNhan.setVisible(b);
		spnGioVao.setEnabled(b);
		spnPhut.setEnabled(b);
		spnPhut.setModel(spnM);
		spnGioVao.setModel(spnMHour);
		txtSDT.setEnabled(b);
		txtTenKh.setEnabled(b);
	}

	/*
	 * Tạo một khách hàng mới (khi đặt phòng/hẹn đặt phòng nhập ttkh mới thì tạo mới
	 * KH)
	 */
	private KhachHang taoMoiKhachHang() {
		ttkh = new ThongTinKhachHang_gui();
		KhachHang createKH = new KhachHang(ttkh.taoMaKhachHang(), tenKH, "", sdtKH, "");
		kh_dao.createKhachHang(createKH);
		return createKH;
	}

	/*
	 * Hàm tự phát sinh mã phiếu hẹn đặt phòng
	 */
	public String taoMaPhieuHenDatPhong() {
		String maPhieuDatPhong;
		// Sử dụng Formatter để định dạng chuỗi thành "KHXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maPhieuDatPhong = formatter.format("PHDP%08d", PHDPCounter).toString();
			PHDPCounter++;
		} while (!timMaPHDP(maPhieuDatPhong));
		formatter.close();
		return maPhieuDatPhong;
	}

	/*
	 * Mở Khóa lưu , khóa hẹn đặt phòng ngay, mở khóa các JtextField nhập liêuj
	 */
	protected void moKhoaDatPhong(boolean b) {
		int mitute = cal.get(Calendar.MINUTE);
		spnM = new SpinnerNumberModel(mitute, mitute, 59, 1);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		hour = (hour < 8) ? 8 : hour;
		btnLuu.setEnabled(b);
		btnHenDP.setEnabled(!b);
		spnGioVao.setEnabled(b);
		spnPhut.setEnabled(b);
		spnPhut.setModel(spnM);
		spnGioVao.setModel(spnMHour);
		txtSDT.setEnabled(b);
		txtTenKh.setEnabled(b);
	}

	/*
	 * Tạo mã mới cho đặt Phòng
	 */
	public static String taoMaPhieuDatPhong() {
		String maPhieuDatPhong;
		// Sử dụng Formatter để định dạng chuỗi thành "KHXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maPhieuDatPhong = formatter.format("PDP%08d", PDPCounter).toString();
			PDPCounter++;
		} while (!timMaPDP(maPhieuDatPhong));
		formatter.close();
		return maPhieuDatPhong;
	}

	/*
	 * Tìm mã phiếu hẹn đặt phòng trong ds Phiếu hẹn đặt phòng tránh TH trùng mã
	 */
	private boolean timMaPHDP(String maPhieuDatPhong) {
		maPhieuDatPhong = maPhieuDatPhong.toUpperCase();
		List<PhieuDatPhong> list = PDP_dao.getPhieuHenDatPhong();
		for (PhieuDatPhong pdp : list) {
			if (pdp.getMaPhieu().contains(maPhieuDatPhong))
				return false;
		}
		return true;
	}

	/*
	 * Tìm mã phiếu đặt phòng trong ds Phieus đặt phòng tránh TH trùng mã
	 */
	private static boolean timMaPDP(String maPhieuDatPhong) {
		// TODO Auto-generated method stub
		maPhieuDatPhong = maPhieuDatPhong.toUpperCase();
		PhieuDatPhong_dao PDP_dao = new PhieuDatPhong_dao();
		List<PhieuDatPhong> list = PDP_dao.getAllPhieuDatPhong();
		for (PhieuDatPhong pdp : list) {
			if (pdp.getMaPhieu().contains(maPhieuDatPhong))
				return false;
		}
		return true;
	}
}
