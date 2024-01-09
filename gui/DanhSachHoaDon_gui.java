package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;

import com.toedter.calendar.JDateChooser;

import connectDB.connectSQL;
import dao.ChiTietHoaDon_dao;
import dao.HoaDon_dao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.awt.event.ActionEvent;

public class DanhSachHoaDon_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_ContentPaneDSHD;
	private JPanel pnlTieuDe;
	private JLabel lblTieuDe;
	private JPanel pnlThaoTac;
	private JPanel pnl_DSHoaDon;
	private JScrollPane scr_DSHoaDon;
	private JTable tbl_DSHoaDon;
	private JButton btnTimTenKH;
	private JLabel lblNgayLap;
	private JLabel lblTimKiem;
	private JTextField txtTimKiem;
	private JButton btnXemChiTiet;
	private JButton btnLamMoi;
	private JDateChooser dtc_NgayLap;
	private DecimalFormat decimalFormat;
	private DefaultTableModel tableModel;
	private HoaDon_dao hd_dao;
	private ChiTietHoaDon_dao cthd_dao;
	private double tienNhieuPhong=0.0;

	/**
	 * Create the frame.
	 */
	public DanhSachHoaDon_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPaneDSHD = new JPanel();
		pnl_ContentPaneDSHD.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPaneDSHD.setLayout(new BoxLayout(pnl_ContentPaneDSHD, BoxLayout.Y_AXIS));
		decimalFormat = new DecimalFormat("#,### VND");
		Color color = new Color(197, 199, 199);

		cthd_dao = new ChiTietHoaDon_dao();
		hd_dao = new HoaDon_dao();
		/*
		 * Panel tiêu đề Phiếu đặt phòng
		 */
		pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(color);
		pnl_ContentPaneDSHD.add(pnlTieuDe);

		lblTieuDe = new JLabel("Danh Sách Hóa ĐƠn");
		lblTieuDe.setForeground(new Color(15, 102, 165));
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnlTieuDe.add(lblTieuDe);

		setContentPane(pnl_ContentPaneDSHD);

		pnlThaoTac = new JPanel();
		pnlThaoTac.setBackground(color);
		pnlThaoTac.setPreferredSize(new Dimension(10, 140));
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông Tin Tìm Kiếm");
		pnlThaoTac.setBorder(titledBorder);

		pnl_ContentPaneDSHD.add(pnlThaoTac);
		pnlThaoTac.setLayout(null);

		lblTimKiem = new JLabel("Nhập Thông Tin Tìm Kiếm:");
		lblTimKiem.setBounds(26, 26, 170, 14);
		pnlThaoTac.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(195, 21, 323, 26);
		pnlThaoTac.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimTenKH = new JButton("Tìm Kiếm");
		btnTimTenKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timHoaDon();
			}
		});
		btnTimTenKH.setBounds(547, 51, 128, 28);
		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTimTenKH.setIcon(new ImageIcon(imgTim));
		pnlThaoTac.add(btnTimTenKH);

		lblNgayLap = new JLabel("Ngày Lập: ");
		lblNgayLap.setBounds(26, 86, 77, 19);
		pnlThaoTac.add(lblNgayLap);

		btnXemChiTiet = new JButton("Xem Chi Tiết");
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemChiTietHoaDon();
			}
		});
		btnXemChiTiet.setBounds(715, 51, 138, 28);
		Image imgHD = new ImageIcon(this.getClass().getResource("" + "/Invoice.24.png")).getImage();
		btnXemChiTiet.setIcon(new ImageIcon(imgHD));
		pnlThaoTac.add(btnXemChiTiet);

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiDanhSachHoaDon();
			}
		});
		btnLamMoi.setBounds(892, 51, 120, 28);
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));
		pnlThaoTac.add(btnLamMoi);

		dtc_NgayLap = new JDateChooser();
		dtc_NgayLap.setBounds(195, 83, 138, 24);
		dtc_NgayLap.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date selectedDate = (Date) evt.getNewValue();
					LocalDate ngayLap = selectedDate.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId())
							.toLocalDate();
					timHoaDonTheoNgay(ngayLap);
				}
			}
		});
		pnlThaoTac.add(dtc_NgayLap);

		pnl_DSHoaDon = new JPanel();
		pnl_DSHoaDon.setBorder(new TitledBorder(null, "Danh S\u00E1ch H\u00F3a \u0110\u01A1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnl_ContentPaneDSHD.add(pnl_DSHoaDon);
		pnl_DSHoaDon.setLayout(new BorderLayout(0, 0));

		tbl_DSHoaDon = new JTable();
		tbl_DSHoaDon.setDefaultEditor(Object.class, null);
		tableModel = new DefaultTableModel();
		tableModel = taoBang(hd_dao.getAllHoaDon());
		tbl_DSHoaDon.setModel(tableModel);

		scr_DSHoaDon = new JScrollPane(tbl_DSHoaDon);

		pnl_DSHoaDon.add(scr_DSHoaDon, BorderLayout.CENTER);
	}

	protected void lamMoiDanhSachHoaDon() {
		tableModel.setRowCount(0);
		tableModel = taoBang(hd_dao.getAllHoaDon());
		tbl_DSHoaDon.setModel(tableModel);
	}

	protected void xemChiTietHoaDon() {
		int n = tbl_DSHoaDon.getSelectedRow();
		if (n != -1) {
			String maHD = tbl_DSHoaDon.getModel().getValueAt(n, 0).toString();
			List<ChiTietHoaDon> dscthd = cthd_dao.timCTHoaDonTheoMa(maHD); // lấy dsCTHD có dịch vụ
			ChiTietHoaDon cthdp = cthd_dao.timCTHDPTheoMa(maHD); // lấy CTHD không Dịch vụ
			ChiTietHoaDon cthd = cthd_dao.timAllCTHoaDonTheoMa(maHD);
			String maPhong = "";
			if(!dscthd.isEmpty()) {
				maPhong = dscthd.get(0).getPhong().getMaPhong();
			}
			
			for (ChiTietHoaDon ct : dscthd) {
				if (!ct.getPhong().getMaPhong().trim().equalsIgnoreCase(maPhong.trim())) {
					n = -1;
					break;
				} 
			}
			if (n == -1) {
				inChiTietHoaDonNhieuPhong(maHD);
			}
			else if (n != -1 && cthd.getDv().getMaDV() != null) {
				inChiTietHoaDonDV(maHD);
			}
			else if (cthdp.getDv() == null) {// In chi tiết hóa đơn ko dịch vụ
				inChiTietHoaDonPhong(maHD);
			}	
			
		} else
			JOptionPane.showMessageDialog(this, "Hãy chọn một hóa đơn!");
	}

	private void inChiTietHoaDonNhieuPhong(String maHD) {
		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport("src/gui/HoaDonNhieuPhong.jrxml");
			map.put("ma", maHD);
			String TongTienP = decimalFormat.format(tienNhieuPhong);
			map.put("tongtien",TongTienP);
			JasperPrint p = JasperFillManager.fillReport(report, map, connectSQL.con);
			JasperViewer.viewReport(p, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void inChiTietHoaDonDV(String maHD) {
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

	private void inChiTietHoaDonPhong(String maHD) {
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

	protected void timHoaDonTheoNgay(LocalDate ngayLap) {
		String DateLap = " ngayLap = '" + ngayLap + "'";
		tableModel.setRowCount(0);
		tbl_DSHoaDon.setModel(taoBang(hd_dao.timKiemHoaDon(DateLap)));
	}

	protected void timHoaDon() {
		String hoaDon = "";
		String a = txtTimKiem.getText();
		if (!a.equals("")) {
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			hoaDon = " hd.maHD like '%" + a + "%' or kh.hoTen like N'%" + a + "%' or kh.SDT like  N'%" + a
					+ "%' or p.tenPhong like N'%" + a + "%'";
			tableModel.setRowCount(0);
			tbl_DSHoaDon.setModel(taoBang(hd_dao.timKiemHoaDon(hoaDon)));
		} else
			JOptionPane.showMessageDialog(this, "Hãy nhập thông tin tìm kiếm!");
	}

	private DefaultTableModel taoBang(List<HoaDon> dsHD) {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Mã Hóa Đơn");
		table.addColumn("Ngày Lập");
		table.addColumn("Tên Khách Hàng");
		table.addColumn("SDT Khách Hàng");
		table.addColumn("Tên Phòng");
		table.addColumn("Nhân Viên Lập");
		table.addColumn("Tổng Tiền");
		List<ChiTietHoaDon> listCT = cthd_dao.getAllDVCTHD(); // ds hd có dv
		List<ChiTietHoaDon> listCTP = cthd_dao.getAllCTHD(); // ds hd không có dv
		for (HoaDon hd : dsHD) {
			
			List<ChiTietHoaDon> dscthd = cthd_dao.timCTHoaDonTheoMa(hd.getMaHD());
			ChiTietHoaDon cthd = new ChiTietHoaDon();
			if(!dscthd.isEmpty()) {
				cthd = dscthd.get(0);
			}
			ChiTietHoaDon cthdp = cthd_dao.timCTHDPTheoMa(hd.getMaHD());
//			System.out.println(cthd.toString()); 
			double tongtienDV = 0.0;
			double tienHat = 0.0;
			double tienHatP = 0.0;
			for (ChiTietHoaDon ct : listCT) {
				if (ct.getHoaDon().getMaHD().trim().equalsIgnoreCase(hd.getMaHD().trim())) {
					tongtienDV += ct.tinhTongTienDV();
				}
			}
			for (ChiTietHoaDon ct : listCTP) {
				if (ct.getHoaDon().getMaHD().trim().equals(hd.getMaHD().trim())) {
					tienHatP += ct.tinhTongTienHat();
					break;
				}
			}
//			System.out.println(tienHatP);
			String maPhong = "";
			for (ChiTietHoaDon ct : listCT) {
				if (ct.getHoaDon().getMaHD().trim().equalsIgnoreCase(hd.getMaHD().trim())) {
					maPhong = ct.getPhong().getMaPhong().trim();
					tienHat += ct.tinhTongTienHat();
					break;
				}
			}
			for (ChiTietHoaDon ct : listCT) {
				if (ct.getHoaDon().getMaHD().trim().equals(hd.getMaHD().trim()) && !ct.getPhong().getMaPhong().trim().equalsIgnoreCase(maPhong)) {
					tienHat += ct.tinhTongTienHat();
					tienNhieuPhong=tienHat;
					break;
				}
			}
			String TongTien = decimalFormat.format(tongtienDV + tienHat);
			String TongTienP = decimalFormat.format(tienHatP);
			
			
			if (cthd.getPhong() != null) {
				Object[] rowData = { hd.getMaHD(), hd.getNgayLap(), hd.getKhachHang().getHoTen(),
						hd.getKhachHang().getSDT(), cthd.getPhong().getTenPhong(), hd.getNhanVien().getHoTen(),
						TongTien };
				table.addRow(rowData);
			}
			
			if (cthdp.getDv() == null && tienHatP != 0.0) {
				Object[] rowData = { hd.getMaHD(), hd.getNgayLap(), hd.getKhachHang().getHoTen(),
						hd.getKhachHang().getSDT(), cthdp.getPhong().getTenPhong(), hd.getNhanVien().getHoTen(),
						TongTienP };
				table.addRow(rowData);
			}

		}
		return table;
	}
}