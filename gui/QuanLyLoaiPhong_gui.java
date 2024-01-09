package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.LoaiPhong_dao;
import dao.Phong_dao;
import entity.LoaiPhong;
import entity.Phong;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Formatter;
import java.util.List;
import java.awt.event.ActionEvent;

public class QuanLyLoaiPhong_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_contentPaneQLLP;
	private JPanel pnl_QuanLyLoaiPhong;
	private JPanel pnlTieuDe;
	private JLabel lblTieuDe;
	private JPanel pnl_ThaoTac;
	private JPanel pnlThongTin;
	private JLabel lblMaLoai;
	private JTextField txtMaLoai;
	private JLabel lblLoaiPhong;
	private JLabel lblMota;
	private JButton btnThem;
	private JButton btnCapNhatPhong;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnLuu;
	private JPanel pnlChucNang;
	private JPanel pnl_DSLoaiPhong;
	private JTable tbl_DSLoaiPhong;
	private JScrollPane scr_DSLoaiPhong;
	private JButton btnTim;
	private JTextField txtTenLoai;
	private JTextArea textArea;
	private JScrollPane scr_Mota;
	private JTextField txtGiaHat;
	private int edit;
	private DefaultTableModel tableModel;
	private LoaiPhong_dao loaiPhong_dao;
	private JButton btnLamMoi;
	private JLabel lblGiaHat;
	private JLabel lblTimKiem;
	private JTextField txtTimKiem;
	private LoaiPhong_dao lp_dao;
	private int LPHCounter = 1;
	private List<Phong> dsPhong;
	private DecimalFormat decimalFormat;

	/**
	 * Create the frame.
	 */
	public QuanLyLoaiPhong_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		Color color = new Color(197, 199, 199);
		Phong_dao p_dao = new Phong_dao();
		dsPhong = p_dao.selectALL();
		decimalFormat = new DecimalFormat("#,### VND");
		
		pnl_contentPaneQLLP = new JPanel();
		pnl_contentPaneQLLP.setBackground(color);
		pnl_contentPaneQLLP.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_contentPaneQLLP.setLayout(new BorderLayout());
		lp_dao = new LoaiPhong_dao();
		/*
		 * Panel Quản lý Loại Phòng
		 */
		pnl_QuanLyLoaiPhong = new JPanel();
		pnl_QuanLyLoaiPhong.setBackground(color);
		pnl_contentPaneQLLP.add(pnl_QuanLyLoaiPhong, BorderLayout.CENTER);
		pnl_QuanLyLoaiPhong.setLayout(new BoxLayout(pnl_QuanLyLoaiPhong, BoxLayout.Y_AXIS));

		pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(color);
		pnl_QuanLyLoaiPhong.add(pnlTieuDe);
		pnlTieuDe.setLayout(new BorderLayout(0, 0));

		lblTieuDe = new JLabel("Danh Sách Loại Phòng");
		lblTieuDe.setForeground(new Color(15, 102, 165));
		lblTieuDe.setPreferredSize(new Dimension(84, 40));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnlTieuDe.add(lblTieuDe);

		pnl_QuanLyLoaiPhong.add(Box.createVerticalStrut(20));

		pnl_ThaoTac = new JPanel();
		pnl_ThaoTac.setBackground(color);
		pnl_ThaoTac.setPreferredSize(new Dimension(10, 250));
		pnl_ThaoTac.setMaximumSize(new Dimension(32767, 150));
		pnl_QuanLyLoaiPhong.add(pnl_ThaoTac);
		pnl_ThaoTac.setLayout(new BoxLayout(pnl_ThaoTac, BoxLayout.X_AXIS));

		pnlThongTin = new JPanel();
		pnlThongTin.setBackground(color);
		pnlThongTin.setPreferredSize(new Dimension(100, 10));
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông Tin Loại Phòng");
		pnlThongTin.setBorder(titledBorder);
		pnl_ThaoTac.add(pnlThongTin);
		pnlThongTin.setLayout(null);

		lblMaLoai = new JLabel("Mã Loại:");
		lblMaLoai.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaLoai.setBounds(13, 24, 72, 14);
		pnlThongTin.add(lblMaLoai);

		txtMaLoai = new JTextField();
		txtMaLoai.setBounds(114, 21, 111, 20);
		pnlThongTin.add(txtMaLoai);
		txtMaLoai.setEditable(false);
		txtMaLoai.setColumns(10);

		lblLoaiPhong = new JLabel("Tên Loại:");
		lblLoaiPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoaiPhong.setBounds(291, 24, 72, 14);
		pnlThongTin.add(lblLoaiPhong);

		txtTenLoai = new JTextField();
		txtTenLoai.setBounds(379, 21, 128, 20);
		pnlThongTin.add(txtTenLoai);

		lblMota = new JLabel("Mô Tả:");
		lblMota.setHorizontalAlignment(SwingConstants.CENTER);
		lblMota.setBounds(13, 109, 72, 14);
		pnlThongTin.add(lblMota);

		textArea = new JTextArea(5, 1);

		scr_Mota = new JScrollPane(textArea);
		scr_Mota.setBounds(112, 98, 395, 39);
		pnlThongTin.add(scr_Mota);

		lblGiaHat = new JLabel("Giá Hát:");
		lblGiaHat.setBounds(24, 66, 61, 14);
		pnlThongTin.add(lblGiaHat);

		txtGiaHat = new JTextField();
		txtGiaHat.setColumns(10);
		txtGiaHat.setBounds(114, 60, 111, 20);
		pnlThongTin.add(txtGiaHat);

		lblTimKiem = new JLabel("Nhập thông tin phòng cần tìm:");
		lblTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimKiem.setBounds(30, 166, 195, 20);
		pnlThongTin.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(235, 166, 236, 20);
		pnlThongTin.add(txtTimKiem);

		pnlChucNang = new JPanel();
		pnlChucNang.setBackground(color);
		TitledBorder titledBorder2 = new TitledBorder(lineBorder, "Chọn Chức Năng");
		pnlChucNang.setBorder(titledBorder2);
		pnl_ThaoTac.add(pnlChucNang);
		pnlChucNang.setLayout(null);

		btnTim = new JButton("Tìm Kiếm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemLoaiPhong();
			}
		});
		btnTim.setBounds(60, 137, 125, 31);
		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTim.setIcon(new ImageIcon(imgTim));
		pnlChucNang.add(btnTim);
		/*
		 * Buttton thêm dùng để thêm mới một loại phòng
		 */
		Image imgAdd = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themLoaiPhong();
			}
		});
		btnThem.setBounds(60, 21, 125, 31);
		pnlChucNang.add(btnThem);
		btnThem.setIcon(new ImageIcon(imgAdd));
		/*
		 * Button cập nhật dùng để chỉnh sửa thông tin phòng, chỉ đc cập nhật khi trạng
		 * thái phòng trống
		 */
		Image imgEdit = new ImageIcon(this.getClass().getResource("" + "/Edit.24.png")).getImage();
		btnCapNhatPhong = new JButton("Cập Nhật");
		btnCapNhatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatThongTinLoaiPhong();
			}
		});
		btnCapNhatPhong.setBounds(60, 75, 125, 31);
		pnlChucNang.add(btnCapNhatPhong);
		btnCapNhatPhong.setIcon(new ImageIcon(imgEdit));
		/*
		 * Button xóa dùng để xóa một loại phòng, chỉ đc xóa khi không có phòng nào
		 * thuộc loại phòng đó
		 */
		Image imgExit = new ImageIcon(this.getClass().getResource("" + "/Button-Close.24.png")).getImage();
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaLoaiPhong();
			}
		});
		btnXoa.setBounds(253, 21, 125, 31);
		pnlChucNang.add(btnXoa);
		btnXoa.setIcon(new ImageIcon(imgExit));
		/*
		 * Button xóa trắng: dùng để làm mới các JTextfield
		 */
		Image imgClean = new ImageIcon(this.getClass().getResource("" + "/bubbles.png")).getImage();
		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangThongTinNhap();
			}
		});
		btnXoaTrang.setBounds(253, 75, 125, 31);
		pnlChucNang.add(btnXoaTrang);
		btnXoaTrang.setIcon(new ImageIcon(imgClean));
		/*
		 * Button lưu dùng để lưu lại thông tin loại phòng sau thi thêm loại phòng hoặc
		 * cập nhật loại phòng
		 */
		Image imgSave = new ImageIcon(this.getClass().getResource("" + "/Save.24.png")).getImage();
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luuLoaiPhong();
			}
		});
		btnLuu.setBounds(253, 137, 125, 31);
		pnlChucNang.add(btnLuu);
		btnLuu.setIcon(new ImageIcon(imgSave));
		/*
		 * Button làm mới => làm mới lại danh sách loại phòng sau khi tìm kiếm
		 */
		btnLamMoi = new JButton("Làm Mới  (F5)");
		btnLamMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		btnLamMoi.setBounds(420, 137, 149, 31);
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));
		pnlChucNang.add(btnLamMoi);

		/*
		 * Panel Danh sách Phòng
		 */
		pnl_DSLoaiPhong = new JPanel();
		pnl_DSLoaiPhong.setBackground(color);
		pnl_DSLoaiPhong.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch Lo\u1EA1i  Ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnl_QuanLyLoaiPhong.add(pnl_DSLoaiPhong);
		pnl_DSLoaiPhong.setLayout(new BorderLayout(0, 0));

		/*
		 * khởi Tạo class Phòng_Dao : lấy danh sách phòng
		 */
		loaiPhong_dao = new LoaiPhong_dao();
		tbl_DSLoaiPhong = new JTable();
		tableModel = taoBang(loaiPhong_dao.selectALL());
		tbl_DSLoaiPhong.setModel(tableModel);

		// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên JTable
		tbl_DSLoaiPhong.setDefaultEditor(Object.class, null);

		scr_DSLoaiPhong = new JScrollPane(tbl_DSLoaiPhong);
		scr_DSLoaiPhong.setPreferredSize(new Dimension(452, 302));

		pnl_DSLoaiPhong.add(scr_DSLoaiPhong, BorderLayout.CENTER);

		setContentPane(pnl_contentPaneQLLP);
	}

	protected void timKiemLoaiPhong() {
		String phong = "";
		String a = txtTimKiem.getText();
		Double gia = 0.0;
		if (!a.equals("")) {
			if (a.chars().allMatch(Character::isDigit)) // Kiểm tra txtGia phải là số
				gia = Double.valueOf(a); // Ép kiểu txtTimphong sang số ==> tìm phòng theo giá
		}
		// Tạo câu lệnh truy vấn tìm kiếm trên sql
		if (!a.equals("")) {
			phong = "maLoai like N'%" + a + "%' or tenLoai like N'%" + a + "%' or moTa like  N'%" + a
					+ "%' or giaHat = " + gia;
			tbl_DSLoaiPhong.setModel(taoBang(loaiPhong_dao.findByCondition(phong)));
		} else
			JOptionPane.showMessageDialog(this, "Hãy nhập thông tin tìm kiếm!");

	}

	protected void lamMoiBang() {
		tableModel = taoBang(loaiPhong_dao.selectALL());
		tbl_DSLoaiPhong.setModel(tableModel);
	}

	protected void luuLoaiPhong() {
		String maLoai = txtMaLoai.getText().trim();
		String tenLoai = txtTenLoai.getText().trim();
		String moTa = textArea.getText().trim();
		Double gia = 0.0;
		String a = txtGiaHat.getText().trim();
		if (tenLoai.equals("") || a.equals("")) {
			JOptionPane.showMessageDialog(this, "Thông tin nhập không được để trống");
		} else {
			if (a.chars().allMatch(Character::isDigit)) {// Kiểm tra txtGia phải là số
				gia = Double.valueOf(a); // Ép kiểu txtTimphong sang số ==> tìm phòng theo giá
				if (edit == 1) {
//					if (timMaLoai(maLoai)) {
					String maLoaiP = taoMaLoaiPhong();
					LoaiPhong lp = new LoaiPhong(maLoaiP, tenLoai, gia, moTa);
					lp_dao.add(lp);
					lamMoiBang();
					JOptionPane.showMessageDialog(this, "Tạo mới thành công");
					xoaTrangThongTinNhap();
					btnCapNhatPhong.setEnabled(true);
					btnXoa.setEnabled(true);
					btnThem.setText("Thêm");
//					} else JOptionPane.showMessageDialog(this, "Mã Loại không được trùng");
				} else if (edit == 2) {
					LoaiPhong lp = new LoaiPhong(maLoai, tenLoai, gia, moTa);
					lp_dao.update(lp);
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					xoaTrangThongTinNhap();
					lamMoiBang();
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnCapNhatPhong.setText("Cập Nhật");
				}
			} else
				JOptionPane.showMessageDialog(this, "Giá hát phải là sô!");
		}

	}

//	private boolean timMaLoai(String maLoai) {
//		maLoai = maLoai.toUpperCase();
//		List<LoaiPhong> dsLPhong = lp_dao.selectALL();
//		for (LoaiPhong lp : dsLPhong) {
//			if (lp.getMaLoai().contains(maLoai))
//				return false;
//		}
//		return true;
//	}

	protected void xoaTrangThongTinNhap() {
		txtMaLoai.setText("");
		txtTenLoai.setText("");
		txtGiaHat.setText("");
		textArea.setText("");
		txtMaLoai.requestFocus();
	}

	protected void xoaLoaiPhong() {
		int n = tbl_DSLoaiPhong.getSelectedRow();
		int kt = 0;
		if (n != -1) {
			String maLP = tbl_DSLoaiPhong.getModel().getValueAt(n, 0).toString();
			for (Phong p : dsPhong) {
				if (maLP.equals(p.getLoaiPhong().getMaLoai())) {
					if (p.getTrangThai().equals("Đang sử dụng") || p.getTrangThai().equals("Chờ Nhận Phòng")) {
						JOptionPane.showMessageDialog(null, "Bạn không thể xóa loại phòng đang sử dụng!");
						kt = 1;
						break;
					}
				}
			}
			if (kt == 0) {
				int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?");
				if (tb == JOptionPane.YES_OPTION) {
					String maLoai = tbl_DSLoaiPhong.getModel().getValueAt(n, 0).toString();
					loaiPhong_dao.delete(maLoai);
					JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
					lamMoiBang();
				}

			}

		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn phòng cần xóa!");
	}

	protected void capNhatThongTinLoaiPhong() {
		// lấy số dòng thông tin phòng được chọn từ bảng
		int n = tbl_DSLoaiPhong.getSelectedRow();
		if (btnCapNhatPhong.getText().equalsIgnoreCase("Hủy")) {
			edit = 0; // Phân biệt hủy = 0 và cập nhật = 2, thêm = 1
			mokhoaKhoaCapNhatPhong(true);
			xoaTrangThongTinNhap();
			btnCapNhatPhong.setText("Cập Nhật");
		} else
		// Kiểm tra hàng đã được chọn hay không
		if (n != -1) {
//			if (!tbl_DSLoaiPhong.getModel().getValueAt(n, 3).toString().equalsIgnoreCase("Đang sử dụng")) {
			edit = 2;
			if (btnCapNhatPhong.getText().equalsIgnoreCase("Cập Nhật")) {
				mokhoaKhoaCapNhatPhong(false);
				btnCapNhatPhong.setText("Hủy");
				// Đưa dữ liệu từ bảng vào textField để cập nhật
				Number giaHat = null;
				try {
					giaHat = decimalFormat.parse(tbl_DSLoaiPhong.getModel().getValueAt(n, 2).toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				txtMaLoai.setText(tbl_DSLoaiPhong.getModel().getValueAt(n, 0).toString());
				txtTenLoai.setText(tbl_DSLoaiPhong.getModel().getValueAt(n, 1).toString());
				txtGiaHat.setText(String.valueOf(giaHat.intValue()));
				textArea.setText(tbl_DSLoaiPhong.getModel().getValueAt(n, 3).toString());
			}
//			} else
//				JOptionPane.showMessageDialog(null, "Bạn không thể cập nhật loại phòng đang sử dụng!");

		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn loại phòng cần cập nhật!");

	}

	protected void themLoaiPhong() {
		edit = 1;
		if (btnThem.getText().equalsIgnoreCase("Thêm")) {
			// vô hiệu hóa button cập nhật và button xóa
			moKhoaThemPhong(false);
			btnThem.setText("Hủy");
			txtTenLoai.requestFocus();// đặt con trỏ chuột vào ô tên loại
		} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
			edit = 0;
			moKhoaThemPhong(true);
			btnThem.setText("Thêm");
		}
	}

	protected void moKhoaThemPhong(boolean a) {
		xoaTrangThongTinNhap();
		btnCapNhatPhong.setEnabled(a);
		btnXoa.setEnabled(a);
		btnLuu.setEnabled(!a);
	}

	protected void mokhoaKhoaCapNhatPhong(boolean a) {
		btnThem.setEnabled(a);
		btnXoa.setEnabled(a);
		btnLuu.setEnabled(!a);
	}

	private String taoMaLoaiPhong() {
		String maLPhong;
		// Sử dụng Formatter để định dạng chuỗi thành "KHXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maLPhong = formatter.format("LPH%02d", LPHCounter).toString();
			LPHCounter++;
		} while (!timMaPhong(maLPhong));
		formatter.close();
		return maLPhong;
	}

	private boolean timMaPhong(String maLPhong) {
		maLPhong = maLPhong.toUpperCase();
		List<LoaiPhong> dsLPhong = loaiPhong_dao.selectALL();
		for (LoaiPhong lp : dsLPhong) {
			if (lp.getMaLoai().contains(maLPhong))
				return false;
		}
		return true;
	}

	/*
	 * Truyền vào danh sách loại phòng, trả về table Model theo danh sách loại phòng
	 */
	protected DefaultTableModel taoBang(List<LoaiPhong> ds) {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Mã Loại");
		table.addColumn("Tên Loại");
		table.addColumn("Giá Hát");
		table.addColumn("Mô Tả");
		for (LoaiPhong lp : ds) {
			String giaHat = decimalFormat.format(lp.getGiaHat());
			Object[] rowData = { lp.getMaLoai(), lp.getTenLoai(),giaHat, lp.getMota() };
			table.addRow(rowData);
		}
		return table;
	}
}
