package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Hashtable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import connectDB.connectSQL;
import dao.ChiTietPhieuDatPhong_dao;
import dao.PhieuDatPhong_dao;
import entity.ChiTietPhieuDatPhong;
import entity.PhieuDatPhong;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JCheckBox;

public class PhieuDatPhong_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_ContentPanePDP;
	private JPanel pnl_PhieuDatPhong;
	private JPanel pnlTieuDe;
	private JLabel lblTieuDe;
	private JPanel pnl_TimKiem;
	private JTable tbl_PhieuDatP;
	private JComponent pnlThaoTac;
	private JButton btnTaoPhieuDP;
	private JButton btnSuaPDP;
	private JButton btnNhanPhong;
	private JButton btnHuyPhieuDP;
	private JButton btnInPhieuDP;
	private JButton btnLamMoi;
	private Image imgTim;
	private JTextField txtTimKiem;
	private PhieuDatPhong_dao pdp_dao;
	private JDateChooser dtcNgayNhan;
	private DefaultTableModel table;
	private JButton btnTimPhieu;
	private JComboBox<String> cmb_TrangThai;
	private JLabel lblTrangThai;
	private String trangThai = "";
	private LocalDate ngayNhan = null;
	private JCheckBox chk_NgayNhan;
	private JCheckBox chk_TrangThai;
	private ChiTietPhieuDatPhong_dao ct_dao;

	/**
	 * Create the frame.
	 */
	public PhieuDatPhong_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);

		pdp_dao = new PhieuDatPhong_dao();
		ct_dao = new ChiTietPhieuDatPhong_dao();
		Color color = new Color(197, 199, 199);
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
		imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		
		pnl_ContentPanePDP = new JPanel();
		pnl_ContentPanePDP.setBackground(color);
		pnl_ContentPanePDP.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPanePDP.setLayout(new BorderLayout());
		/*
		 * Create Panel Phieu Đặt Phòng
		 */
		pnl_PhieuDatPhong = new JPanel();
		pnl_PhieuDatPhong.setBackground(color);
		pnl_PhieuDatPhong.setPreferredSize(new Dimension(10, 22));
		pnl_PhieuDatPhong.setMaximumSize(new Dimension(32767, 22));
		pnl_ContentPanePDP.add(pnl_PhieuDatPhong, BorderLayout.CENTER);
		pnl_PhieuDatPhong.setLayout(new BoxLayout(pnl_PhieuDatPhong, BoxLayout.Y_AXIS));
		/*
		 * Panel tiêu đề Phiếu đặt phòng
		 */
		pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(color);
		pnl_PhieuDatPhong.add(pnlTieuDe);

		lblTieuDe = new JLabel("Danh Sách Phiếu Đặt Phòng");
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		lblTieuDe.setForeground(new Color(15, 102, 165));
		pnlTieuDe.add(lblTieuDe);

		pnl_PhieuDatPhong.add(Box.createVerticalStrut(20));
		/*
		 * Panel Tìm kiếm (theo mã, theo loai, theo trạng thái)
		 */
		pnl_TimKiem = new JPanel();
		pnl_TimKiem.setBackground(color);
		pnl_TimKiem.setPreferredSize(new Dimension(10, 180));
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông Tin Tìm Kiếm");
		pnl_TimKiem.setMaximumSize(new Dimension(32767, 10));
		pnl_TimKiem
				.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64)), "Th\u00F4ng Tin T\u00ECm Ki\u1EBFm",
						TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		pnl_PhieuDatPhong.add(pnl_TimKiem);
		pnl_TimKiem.setLayout(null);

		JLabel lblTimPhieu = new JLabel("Nhập Thông Tin Phòng Cần Tìm:");
		lblTimPhieu.setBounds(38, 25, 200, 25);
		pnl_TimKiem.add(lblTimPhieu);

		btnTimPhieu = new JButton("Tìm");
		btnTimPhieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timPhieuHenDatPhong();
			}
		});
		btnTimPhieu.setBounds(863, 62, 133, 33);
		btnTimPhieu.setIcon(new ImageIcon(imgTim));
		pnl_TimKiem.add(btnTimPhieu);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(232, 27, 193, 20);
		pnl_TimKiem.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JLabel lblNgayNhan = new JLabel("Ngày Nhận: ");
		lblNgayNhan.setBounds(38, 97, 100, 25);
		pnl_TimKiem.add(lblNgayNhan);

		dtcNgayNhan = new JDateChooser();
		// Thêm lắng nghe sự kiện cho JDateChooser
		dtcNgayNhan.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date selectedDate = (Date) evt.getNewValue();
					ngayNhan = selectedDate.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId())
							.toLocalDate();
					timPHDHTheoTrangThai();
				}
			}
		});
		dtcNgayNhan.setBounds(232, 97, 115, 20);
		dtcNgayNhan.setEnabled(false);
		pnl_TimKiem.add(dtcNgayNhan);

		cmb_TrangThai = new JComboBox<String>();
		cmb_TrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Đã Nhận Phòng", "Chờ Nhận Phòng","Hết Hạn" }));
		cmb_TrangThai.setBounds(672, 26, 145, 24);
		cmb_TrangThai.setEnabled(false);
		cmb_TrangThai.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					trangThai = cmb_TrangThai.getSelectedItem().toString();
					timPHDHTheoTrangThai();
				}
			}
		});
		pnl_TimKiem.add(cmb_TrangThai);

		lblTrangThai = new JLabel("Trạng Thái Phiếu:");
		lblTrangThai.setBounds(545, 30, 121, 18);
		pnl_TimKiem.add(lblTrangThai);

		chk_NgayNhan = new JCheckBox("");
		chk_NgayNhan.setBounds(357, 99, 21, 20);
		chk_NgayNhan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// Kiểm tra trạng thái mới của checkbox
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Nếu checkbox được chọn, vô hiệu hóa Combobox trạng thái
					dtcNgayNhan.setEnabled(true);
				} else {
					// Nếu checkbox không được chọn, kích hoạt ComBobox trạng thái
					dtcNgayNhan.setEnabled(false);
					ngayNhan = null;
				}
			}
		});
		pnl_TimKiem.add(chk_NgayNhan);

		chk_TrangThai = new JCheckBox("");
		chk_TrangThai.setBounds(823, 30, 21, 20);
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
		pnl_TimKiem.add(chk_TrangThai);

		/*
		 * Panel Danh sách phiếu đặt phòng
		 */
		JPanel pnl_DSPhieuDatPhong = new JPanel();
		pnl_DSPhieuDatPhong.setBackground(color);
		pnl_DSPhieuDatPhong.setLayout(new BorderLayout(0, 0));
		table = new DefaultTableModel();
		table.addColumn("Mã Phiếu");
		table.addColumn("Tên Khách Hàng");
		table.addColumn("SDT Khách Hàng");
		table.addColumn("Tên Phòng");
		table.addColumn("Nhân Viên Lập");
		table.addColumn("Ngày Đặt");
		table.addColumn("Ngày Nhận");
		table.addColumn("Giờ Vào");
		table.addColumn("Trạng Thái");
		PhieuDatPhong_dao phdp = new PhieuDatPhong_dao();
		List<PhieuDatPhong> ds = phdp.getPhieuHenDatPhong();
		for (PhieuDatPhong p : ds) {
			ChiTietPhieuDatPhong ct = ct_dao.findPhieuDatPhongTheoMaPhieu(p.getMaPhieu());
			Object[] rowData = { p.getMaPhieu(), p.getKhachHang().getHoTen(), p.getKhachHang().getSDT(),
					ct.getPhong().getTenPhong(), p.getNhanVien().getHoTen(), p.getNgayLap(), ct.getNgayNhan(),
					ct.getGioVao(), p.getTrangThai() };
			table.addRow(rowData);
		}
		tbl_PhieuDatP = new JTable(table);
		tbl_PhieuDatP.setDefaultEditor(Object.class, null);// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên
		JScrollPane scr_DSPDPhong = new JScrollPane(tbl_PhieuDatP);
		pnl_DSPhieuDatPhong.add(scr_DSPDPhong);
		pnl_DSPhieuDatPhong.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch phi\u1EBFu \u0111\u1EB7t ph\u00F2ng", TitledBorder.LEADING, TitledBorder.ABOVE_TOP,
				null, new Color(0, 0, 0)));
		pnl_PhieuDatPhong.add(pnl_DSPhieuDatPhong);
		/*
		 * Panel chứa các Button thực hiện các nghiệp vụ
		 */
		pnlThaoTac = new JPanel();
		pnlThaoTac.setBackground(color);
		pnlThaoTac.setPreferredSize(new Dimension(10, 80));
		pnl_PhieuDatPhong.add(pnlThaoTac, BorderLayout.SOUTH);

		// Button cập nhật: cập nhật lại thông tin phiếu đặt phòng vừa đặt

//		btnSuaPDP = new JButton("Cập Nhật");
//		Image imgCapNhat = new ImageIcon(this.getClass().getResource("" + "/Text-Edit.24.png")).getImage();
//		btnSuaPDP.setIcon(new ImageIcon(imgCapNhat));
//		pnlThaoTac.add(btnSuaPDP);

		// Button nhận phòng theo phiếu đặt phòng

		btnNhanPhong = new JButton("Nhận Phòng");
		btnNhanPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nhanPhong();
			}
		});
		Image imgGetRomm = new ImageIcon(this.getClass().getResource("" + "/room.png")).getImage();
		btnNhanPhong.setIcon(new ImageIcon(imgGetRomm));
		pnlThaoTac.add(btnNhanPhong);

		// Button hủy nhận phòng khi quá thời gian (không xóa phiếu), trạng thái phiếu
		// trở thành hết hạn

		btnHuyPhieuDP = new JButton("Hủy Nhận Phòng");
		btnHuyPhieuDP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyNhanPhong();
			}
		});
		Image imgExit = new ImageIcon(this.getClass().getResource("" + "/Button-Close.24.png")).getImage();
		btnHuyPhieuDP.setIcon(new ImageIcon(imgExit));
		pnlThaoTac.add(btnHuyPhieuDP);

		// Button In phiếu đặt phòng sau khi đặt

		btnInPhieuDP = new JButton("In Phiếu Đặt Phòng");
		btnInPhieuDP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = tbl_PhieuDatP.getSelectedRow();
				if (n != -1) {
					inPhieuHenDatPhong(table.getValueAt(n, 0).toString());
				} else
					JOptionPane.showMessageDialog(null, "Hãy Chọn Phiếu Cần In!");

			}
		});
		Image imgPrint = new ImageIcon(this.getClass().getResource("" + "/printer.png")).getImage();
		btnInPhieuDP.setIcon(new ImageIcon(imgPrint));
		pnlThaoTac.add(btnInPhieuDP);

		// Sau khi tìm kiếm thì click button làm mới danh sách

		btnLamMoi = new JButton("Làm Mới   (F5)");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiDSPhieu();
			}
		});
		pnlThaoTac.add(btnLamMoi);
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));

		setContentPane(pnl_ContentPanePDP);
	}

	protected void inPhieuHenDatPhong(String idhd) {

		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport("src/gui/PhieuDatPhong.jrxml");
			map.put("maPhieu", idhd);
			JasperPrint p = JasperFillManager.fillReport(report, map, connectSQL.con);
			JasperViewer.viewReport(p, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	protected void lamMoiDSPhieu() {
		table.setRowCount(0);
		table = taoBang(pdp_dao.getPhieuHenDatPhong());
		tbl_PhieuDatP.setModel(table);
	}

	/*
	 * Tìm Phiếu Hẹn Đặt Phòng theo thông tin nhập
	 */
	protected void timPhieuHenDatPhong() {
		String phieu = "";
		String a = txtTimKiem.getText();
		if (!a.equals("")) {
			phieu = " trangThaiPhieu like N'%" + a + "%' or pdp.maPhieu like N'%" + a + "%' or hoTen like N'%" + a
					+ "%' or kh.SDT like  N'%" + a + "%' or tenPhong like N'%" + a + "%'";
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			table.setRowCount(0);
			tbl_PhieuDatP.setModel(taoBang(pdp_dao.findPhieuDatPhong(phieu)));
		} else
			JOptionPane.showMessageDialog(this, "Hãy nhập thông tin tìm kiếm!");
	}

	/*
	 * Tìm phiếu hẹn đặt phòng theo trạng thái và ngày nhận
	 */
	protected void timPHDHTheoTrangThai() {
		String timKiem = "";
		if (trangThai.equals(""))
			timKiem = " ngayNhanPhong = '" + ngayNhan + "'";
		else if (ngayNhan == null)
			timKiem = " trangThaiPhieu = N'" + trangThai + "'";
		else
			timKiem = " trangThaiPhieu = N'" + trangThai + "' and " + " ngayNhanPhong = '" + ngayNhan + "'";
		table.setRowCount(0);
		tbl_PhieuDatP.setModel(taoBang(pdp_dao.findPhieuDatPhong(timKiem)));
	}

	private DefaultTableModel taoBang(List<PhieuDatPhong> dsPDP) {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Mã Phiếu");
		table.addColumn("Tên Khách Hàng");
		table.addColumn("SDT Khách Hàng");
		table.addColumn("Tên Phòng");
		table.addColumn("Nhân Viên Lập");
		table.addColumn("Ngày Đặt");
		table.addColumn("Ngày Nhận");
		table.addColumn("Giờ Vào");
		table.addColumn("Trạng Thái");
		for (PhieuDatPhong p : dsPDP) {
			ChiTietPhieuDatPhong ct = ct_dao.findPhieuDatPhongTheoMaPhieu(p.getMaPhieu());
			Object[] rowData = { p.getMaPhieu(), p.getKhachHang().getHoTen(), p.getKhachHang().getSDT(),
					ct.getPhong().getTenPhong(), p.getNhanVien().getHoTen(), p.getNgayLap(), ct.getNgayNhan(),
					ct.getGioVao(), p.getTrangThai() };
			table.addRow(rowData);
		}
		return table;
	}

	protected void huyNhanPhong() {
		int n = tbl_PhieuDatP.getSelectedRow();
		if (n != -1) {
			String txtngayNhan = tbl_PhieuDatP.getModel().getValueAt(n, 6).toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của chuỗi ngày tháng
			LocalDate ngayNhan = LocalDate.parse(txtngayNhan, formatter);
			String maPhieu = tbl_PhieuDatP.getModel().getValueAt(n, 0).toString();
			// so sánh trạng thái và ngày nhận phiếu
			if (tbl_PhieuDatP.getModel().getValueAt(n, 8).toString().equalsIgnoreCase("Chờ Nhận Phòng")) {
				JOptionPane.showMessageDialog(null, "Hủy Phòng Thành Công!");
				pdp_dao.updateTrangThaiPDPhong("Hết Hạn", maPhieu);
				lamMoiDSPhieu();
			} else
				JOptionPane.showMessageDialog(null, "Hãy chọn đúng phòng cần hủy (đúng trạng thái chờ nhận phòng)!");
		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn phiếu cần hủy!");
	}

	protected void nhanPhong() {
		int n = tbl_PhieuDatP.getSelectedRow();
		if (n != -1) {
			String txtngayNhan = tbl_PhieuDatP.getModel().getValueAt(n, 6).toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng của chuỗi ngày tháng
			LocalDate ngayNhan = LocalDate.parse(txtngayNhan, formatter);
			String maPhieu = tbl_PhieuDatP.getModel().getValueAt(n, 0).toString();
			// so sánh trạng thái và ngày nhận phiếu
			if (tbl_PhieuDatP.getModel().getValueAt(n, 8).toString().equalsIgnoreCase("Chờ Nhận Phòng")
					&& ngayNhan.isEqual(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "Đặt Phòng Thành Công!");
				pdp_dao.updateTrangThaiPDPhong("Đã Nhận Phòng", maPhieu);

				PhieuDatPhong pdp = pdp_dao.findPhieuDatPhongTheoMaPhieu(maPhieu);
				pdp.setMaPhieu(DatPhong_gui.taoMaPhieuDatPhong());
				pdp.setTrangThai("Đang sử dụng");
				pdp_dao.addPDPhong(pdp);
				lamMoiDSPhieu();
			} else
				JOptionPane.showMessageDialog(null, "Hãy chọn đúng phòng cần nhận (đúng ngày và đúng trạng thái)!");
		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn phiếu cần nhận!");
	}
}
