package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectDB.connectSQL;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;

public class ManHinhChinh_gui extends JFrame {

	protected JPanel contentPane;
	protected JMenu mnHoaDon;
	protected JMenuBar mnuMenu;
	protected JMenu mnDichVu;
	protected JMenu mnPhong;
	protected JMenu mnKhachHang;
	protected JMenu mnNhaCungCap;
	protected JMenu mnNhanVien;
	protected JMenuItem mniDoanhThu;
	protected JMenuItem mniDSHoaDon;
	protected JMenuItem mniLapHoaDon;
	protected JMenuItem mniPhieuDatPhong;
	protected JMenuItem mniDatPhong;
	protected JMenuItem mniDatDichVu;
	protected JMenuItem mniPhieuDatDichVu;
	protected JMenuItem mniQuanLiDichVu;
	protected JPanel pnlMain_gui;
	protected JPanel pnlPhong;
	protected JPanel pnlDichVu;
	protected JPanel pnlHoaDon;
	protected JPanel pnlKhachHang;
	protected JPanel pnl_NguoiDung;
	protected JPanel pnlPhieuDatPhong;
	protected JPanel pnlNhanVien;
	protected JMenuItem mniQuanLyPhong;
	protected JLabel lblHoTen;
	protected JButton btnDangXuat;
	protected JMenuItem mniQuanLyNhanVien;
	protected JMenuItem mniTienLuong;
	protected JMenuItem mniQuanLyLoaiPhong;
	protected JMenuItem mniQuanLyKhachhang;
	protected JMenuItem mniDoanhThuTheoNgay;
	protected JMenuItem mniDoanhThuTheoThang;
	protected JMenuItem mniDoanhThuTheoNam;
	private Dimension screenSize;
	protected JMenuItem mniQuanLiLoaiDichVu;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//
//			public void run() {
//				try {
//					connectSQL.getInstance().connect();
//					ManHinhChinh_gui frame = new ManHinhChinh_gui();
//					frame.setVisible(true);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the frame.
	 */
	public ManHinhChinh_gui() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screenSize.width, screenSize.height);
		setMinimumSize(new Dimension(screenSize.width, screenSize.height));
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		Color color = new Color(197, 199, 199);
		contentPane = new JPanel();
		contentPane.setBackground(color);
		contentPane.setLayout(new CardLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		/*
		 * Màn Hình chính
		 */
		pnlMain_gui = new JPanel();
		pnlMain_gui.setLayout(new BorderLayout());

		// Thiết Lập ckích thước image = kích thước màn hình
		Image image = new ImageIcon(this.getClass().getResource("" + "/Background.png")).getImage()
				.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
		;
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(image));
		lblNewLabel.setOpaque(true);
		pnlMain_gui.add(lblNewLabel, BorderLayout.CENTER);

		/*
		 * Create Panel Khách Hàng chứa các màn hình thực hiện chức năng của Khách hàng
		 */
		pnlKhachHang = new JPanel();
		pnlKhachHang.setLayout(new CardLayout());
		/*
		 * Create Panel Phòng chứa các màn hình thực hiện chức năng của phòng
		 */
		pnlPhong = new JPanel();
		pnlPhong.setLayout(new CardLayout(0, 0));
		/*
		 * Create Panel dịch vụ chứa các màn hình thực hiện chức năng của dịch vụ
		 */
		pnlDichVu = new JPanel();
		pnlDichVu.setLayout(new CardLayout());
		/*
		 * Create Panel Nhân viên chứa các màn hình thực hiện chức năng của Nhân viên
		 */
		pnlNhanVien = new JPanel();
		pnlNhanVien.setLayout(new CardLayout());
		/*
		 * Create Panel hóa đơn chứa các màn hình thực hiện chức năng của hóa đơn
		 */
		pnlHoaDon = new JPanel();
		pnlHoaDon.setLayout(new CardLayout());
		/*
		 * Thêm các màn hình lớn vào Jframe
		 */
		contentPane.add(pnlMain_gui);
		contentPane.add(pnlKhachHang, "PanelKhachHang");
		contentPane.add(pnlPhong, "PanelPhong");
		contentPane.add(pnlDichVu, "PanelDichVu");
		contentPane.add(pnlHoaDon, "PanelHoaDon");
		contentPane.add(pnlNhanVien, "PanelNhanVien");

		/*
		 * Lấy Layout của contentPane và panelPhong,panelDichVu sau đó ép kiểu Sự kiện
		 * click vào JmenuItem
		 */
		CardLayout cardPanel = (CardLayout) contentPane.getLayout();
		CardLayout cardKhachHang = (CardLayout) pnlKhachHang.getLayout();
		CardLayout cardPhong = (CardLayout) pnlPhong.getLayout();
		CardLayout cardDichVu = (CardLayout) pnlDichVu.getLayout();
		CardLayout cardHoaDon = (CardLayout) pnlHoaDon.getLayout();
		CardLayout cardNhanVien = (CardLayout) pnlNhanVien.getLayout();
		/*
		 * Create MenuBar: Create Menu Khách Hàng,Phòng, Dịch Vụ, Hóa Đơn, Doanh Thu
		 */
		mnuMenu = new JMenuBar();
		setJMenuBar(mnuMenu);
		/*
		 * Menu Khách Hàng
		 */
		mnKhachHang = new JMenu("Khách Hàng");
		mnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Image imgKH = new ImageIcon(this.getClass().getResource("" + "/Customer-Male-Light.24.png")).getImage();
		mnKhachHang.setIcon(new ImageIcon(imgKH));
		mnuMenu.add(mnKhachHang);
		// MenuItem Phiếu đặt Phòng
		// Event Open Jpanel Phiếu đặt Phòng when click on MenuItem Phiếu đặt Phòng

		mniQuanLyKhachhang = new JMenuItem("Cập Nhật Khách Hàng");
		Image imgUKH = new ImageIcon(this.getClass().getResource("" + "/Update-Customer.24.png")).getImage();
		mniQuanLyKhachhang.setIcon(new ImageIcon(imgUKH));
		mniQuanLyKhachhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongTinKhachHang_gui TTKH = new ThongTinKhachHang_gui();
				pnlKhachHang.add(TTKH.pnl_ContentPaneQLKH, "QuanLyKhachHang");
				cardPanel.show(contentPane, "PanelKhachHang");
				cardKhachHang.show(pnlKhachHang, "QuanLyKhachHang");
			}
		});
		mnKhachHang.add(mniQuanLyKhachhang);
		/*
		 * Menu Phòng
		 */
		mnPhong = new JMenu("Phòng");
		mnPhong.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Image imgPhong = new ImageIcon(this.getClass().getResource("" + "/Iconshow-Construction-House.24.png"))
				.getImage();
		mnPhong.setIcon(new ImageIcon(imgPhong));
		mnuMenu.add(mnPhong);

		// MenuItem Phiếu đặt Phòng
		// Event Open Jpanel Phiếu đặt Phòng when click on MenuItem Phiếu đặt Phòng

		mniPhieuDatPhong = new JMenuItem("Tìm Phiếu Đặt Phòng");
		mniPhieuDatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuDatPhong_gui PDP = new PhieuDatPhong_gui();
				pnlPhong.add(PDP.pnl_ContentPanePDP, "PhieuDatPhong");
				cardPanel.show(contentPane, "PanelPhong");
				cardPhong.show(pnlPhong, "PhieuDatPhong");
			}
		});
		mnPhong.add(mniPhieuDatPhong);

		// MenuItem Đặt Phòng
		// Event Open Jpanel Đặt Phòng when click on MenuItem Đặt Phòng

		mniDatPhong = new JMenuItem("Đặt Phòng");
		mniDatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatPhong_gui DP = new DatPhong_gui();
				pnlPhong.add(DP.pnl_ContentPaneDP, "DatPhong");
				cardPanel.show(contentPane, "PanelPhong");
				cardPhong.show(pnlPhong, "DatPhong");
			}
		});
		mnPhong.add(mniDatPhong);

		// MenuItem Đặt Phòng
		// Event Open Jpanel Đặt Phòng when click on MenuItem Đặt Phòng

		mniQuanLyPhong = new JMenuItem("Cập Nhật Phòng");
		mniQuanLyPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyPhong_gui QLP = new QuanLyPhong_gui();
				pnlPhong.add(QLP.pnl_contentPaneQLP, "QuanLyPhong");
				cardPanel.show(contentPane, "PanelPhong");
				cardPhong.show(pnlPhong, "QuanLyPhong");
			}
		});
		mnPhong.add(mniQuanLyPhong);
		// MenuItem Đặt Phòng
		// Event Open Jpanel Đặt Phòng when click on MenuItem Đặt Phòng

		mniQuanLyLoaiPhong = new JMenuItem("Cập Nhật Loại Phòng");
		mniQuanLyLoaiPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyLoaiPhong_gui QLLP = new QuanLyLoaiPhong_gui();
				pnlPhong.add(QLLP.pnl_contentPaneQLLP, "QuanLyLoaiPhong");
				cardPanel.show(contentPane, "PanelPhong");
				cardPhong.show(pnlPhong, "QuanLyLoaiPhong");
			}
		});
		mnPhong.add(mniQuanLyLoaiPhong);
		/*
		 * Menu Dịch Vụ
		 */
		mnDichVu = new JMenu("Dịch Vụ");
		mnDichVu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Image imgDV = new ImageIcon(this.getClass().getResource("" + "/Food-Dome.24.png")).getImage();
		mnDichVu.setIcon(new ImageIcon(imgDV));
		mnuMenu.add(mnDichVu);

		// MenuItem PhieuDatDichVu

		mniPhieuDatDichVu = new JMenuItem("Phiếu Đặt Dịch Vụ");
		mniPhieuDatDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuDatDichVu_gui PDDV = new PhieuDatDichVu_gui();
				pnlDichVu.add(PDDV.pnl_ContentPanePDDV, "PhieuDatDichVu");
				cardPanel.show(contentPane, "PanelDichVu");
				cardDichVu.show(pnlDichVu, "PhieuDatDichVu");
			}
		});
		mnDichVu.add(mniPhieuDatDichVu);
		/*
		 * MenuItem DatDichVu
		 */
		mniDatDichVu = new JMenuItem("Đặt Dịch Vụ");
		Image imgDatDV = new ImageIcon(this.getClass().getResource("" + "/Ordering.24.png")).getImage();
		mniDatDichVu.setIcon(new ImageIcon(imgDatDV));
		mniDatDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatDichVu_gui DDV = new DatDichVu_gui();
				pnlDichVu.add(DDV.pnl_ContentPaneDDV, "DatDichVu");
				cardPanel.show(contentPane, "PanelDichVu");
				cardDichVu.show(pnlDichVu, "DatDichVu");
			}
		});
		mnDichVu.add(mniDatDichVu);

		// MenuItem Quản Lí Dịch Vụ
		// Event Open Jpanel Quản Lí Dịch Vụ when click on MenuItem Quản Lí Dịch Vụ

		mniQuanLiDichVu = new JMenuItem("Cập Nhật Dịch Vụ");
		mniQuanLiDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyDichVu_gui QLDV = new QuanLyDichVu_gui();
				pnlDichVu.add(QLDV.pnl_ContentPaneQLDV, "QuanLiDichVu");
				cardPanel.show(contentPane, "PanelDichVu");
				cardDichVu.show(pnlDichVu, "QuanLiDichVu");
			}
		});
		mnDichVu.add(mniQuanLiDichVu);
		
		// MenuItem Quản Lí Dịch Vụ
				// Event Open Jpanel Quản Lí Dịch Vụ when click on MenuItem Quản Lí Dịch Vụ

				mniQuanLiLoaiDichVu = new JMenuItem("Cập Nhật Loại Dịch Vụ");
				mniQuanLiLoaiDichVu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						QuanLyLoaiDichVu_gui QLDV = new QuanLyLoaiDichVu_gui();
						pnlDichVu.add(QLDV.pnl_ContentPaneQLLDV, "QuanLiLoaiDichVu");
						cardPanel.show(contentPane, "PanelDichVu");
						cardDichVu.show(pnlDichVu, "QuanLiLoaiDichVu");
					}
				});
		mnDichVu.add(mniQuanLiLoaiDichVu);
		
		/*
		 * Menu Hóa Đơn MenuItem: Lập Hóa Đơn MenuIten: Danh Sach Hóa Đơn
		 */
		mnHoaDon = new JMenu("Hóa Đơn");
		mnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Image imgHD = new ImageIcon(this.getClass().getResource("" + "/Invoice.24.png")).getImage();
		mnHoaDon.setIcon(new ImageIcon(imgHD));
		mnuMenu.add(mnHoaDon);

		// MenuItem: Lập Hóa Đơn
		// Event Open Jpanel Lập Hóa Đơn when click on MenuItem Lập Hóa Đơn

		mniLapHoaDon = new JMenuItem("Lập Hóa Đơn");
		mniLapHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LapHoaDon_gui LHD = new LapHoaDon_gui();
				pnlHoaDon.add(LHD.pnl_ContentPaneLHD, "LapHoaDon");
				cardPanel.show(contentPane, "PanelHoaDon");
				cardHoaDon.show(pnlHoaDon, "LapHoaDon");
			}
		});
		mnHoaDon.add(mniLapHoaDon);

		// MenuItem: Danh Sách Hóa Đơn
		// Event Open Jpanel Danh Sách Hóa Đơn when click on MenuItem Danh Sách Hóa Đơn

		mniDSHoaDon = new JMenuItem("Danh Sách Hóa Đơn");
		mniDSHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DanhSachHoaDon_gui DSHD = new DanhSachHoaDon_gui();
				pnlHoaDon.add(DSHD.pnl_ContentPaneDSHD, "DanhSachHoaDon");
				cardPanel.show(contentPane, "PanelHoaDon");
				cardHoaDon.show(pnlHoaDon, "DanhSachHoaDon");
			}
		});
		mnHoaDon.add(mniDSHoaDon);

		/*
		 * Menu Nhân Viên
		 */
		mnNhanVien = new JMenu("Nhân Viên");
		mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Image imgNV = new ImageIcon(this.getClass().getResource("" + "/Employee.24.png")).getImage();
		mnNhanVien.setIcon(new ImageIcon(imgNV));
		mnuMenu.add(mnNhanVien);

		// MenuItem: Quản lý nhân viên

		mniQuanLyNhanVien = new JMenuItem("Cập Nhật Nhân Viên");
		Image imgUNV = new ImageIcon(this.getClass().getResource("" + "/staff.24.png")).getImage();
		mniQuanLyNhanVien.setIcon(new ImageIcon(imgUNV));
		mniQuanLyNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyNhanVien_gui QLNV = new QuanLyNhanVien_gui();
				pnlNhanVien.add(QLNV.pnl_ContentPaneQLNV, "QuanLyNhanVien");
				cardPanel.show(contentPane, "PanelNhanVien");
				cardNhanVien.show(pnlNhanVien, "QuanLyNhanVien");
			}
		});
		mnNhanVien.add(mniQuanLyNhanVien);

		// MenuItem: DoanhThu

		mniDoanhThu = new JMenuItem("Doanh Thu");
		Image imgDT = new ImageIcon(this.getClass().getResource("" + "/Artistic-Chart.24.png")).getImage();
		mniDoanhThu.setIcon(new ImageIcon(imgDT));
		mniDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeDoanhThu_gui TKDT = new ThongKeDoanhThu_gui();
				pnlNhanVien.add(TKDT.pnl_ContentPaneDT, "DoanhThu");
				cardPanel.show(contentPane, "PanelNhanVien");
				cardNhanVien.show(pnlNhanVien, "DoanhThu");
			}
		});

		mnNhanVien.add(mniDoanhThu);
		/*
		 * 
		 */
		pnl_NguoiDung = new JPanel();
		pnl_NguoiDung.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mnuMenu.add(pnl_NguoiDung);

		lblHoTen = new JLabel("NV001");
		pnl_NguoiDung.add(lblHoTen);

		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new DangNhap_gui();
			}
		});
		Color color2 = new Color(15,122,200);
		mnDichVu.setForeground(color2);
		mnHoaDon.setForeground(color2);
		mnNhanVien.setForeground(color2);
		mnPhong.setForeground(color2);
		mnKhachHang.setForeground(color2);
		
		pnl_NguoiDung.add(btnDangXuat);
		setContentPane(contentPane);
		setVisible(true);
	}

}
