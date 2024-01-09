package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import dao.LoaiPhong_dao;
import dao.Phong_dao;
import entity.LoaiPhong;
import entity.Phong;

import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;

public class QuanLyPhong_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_contentPaneQLP;
	private Phong_dao phong_dao;
	private JPanel pnl_QuanLyPhong;
	private JPanel pnlTieuDe;
	private JLabel lblTieuDe;
	private JPanel pnl_ThaoTac;
	private JPanel pnl;
	private JLabel lblMaPhong;
	private JTextField txtMaPhong;
	private JLabel lblLoaiPhong;
	private JComboBox<String> cmb_LoaiPhong;
	private JLabel lblTrangThai;
	private JLabel lblGia;
	private JTextField txtGia;
	private JButton btnThem;
	private JButton btnCapNhatPhong;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnLuu;
	private JPanel pnlChucNang;
	private JPanel pnl_DSPhong;
	private JTable tbl_DSPhong;
	private JScrollPane scr_DSPhong;
	private JButton btnTim;
	private DefaultTableModel tableModel;
	private JTextField txtTenPhong;
	private int edit;
	private JButton btnLamMoi;
	private JComboBox<String> cmb_TrangThai;
	private JTextField txtTimKiem;
	private JLabel lblTimKiem;
	private JLabel lblTenPhong;
	private String selectedValue;
	private DecimalFormat decimalFormat;
	private int PHCounter = 1;
	private static boolean isEventListenerEnabled = true;

	/**
	 * Create the frame.
	 */
	public QuanLyPhong_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);

		// Thiết lập màu chữ đen cho trạng thái vô hiệu hóa
		UIManager.put("TextField.inactiveForeground", new ColorUIResource(Color.BLACK));
		decimalFormat = new DecimalFormat("#,### VND");
		Color color = new Color(197, 199, 199);

		pnl_contentPaneQLP = new JPanel();
		pnl_contentPaneQLP.setBackground(color);
		pnl_contentPaneQLP.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_contentPaneQLP.setLayout(new BorderLayout());
		/*
		 * Panel Quản lý Phòng
		 */
		pnl_QuanLyPhong = new JPanel();
		pnl_QuanLyPhong.setBackground(color);
		pnl_contentPaneQLP.add(pnl_QuanLyPhong, BorderLayout.CENTER);
		pnl_QuanLyPhong.setLayout(new BoxLayout(pnl_QuanLyPhong, BoxLayout.Y_AXIS));

		pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(color);
		pnl_QuanLyPhong.add(pnlTieuDe);
		pnlTieuDe.setLayout(new BorderLayout(0, 0));

		lblTieuDe = new JLabel("Danh Sách Phòng");
		lblTieuDe.setPreferredSize(new Dimension(84, 40));
		lblTieuDe.setForeground(new Color(15, 102, 165));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Sitka Text", Font.BOLD, 20));
		pnlTieuDe.add(lblTieuDe);

		pnl_QuanLyPhong.add(Box.createVerticalStrut(20));

		pnl_ThaoTac = new JPanel();
		pnl_ThaoTac.setBackground(color);
		pnl_ThaoTac.setPreferredSize(new Dimension(10, 270));
		pnl_ThaoTac.setMaximumSize(new Dimension(32767, 150));
		pnl_QuanLyPhong.add(pnl_ThaoTac);
		pnl_ThaoTac.setLayout(new BoxLayout(pnl_ThaoTac, BoxLayout.X_AXIS));

		pnl = new JPanel();
		pnl.setBackground(color);
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông Tin Phòng");
		pnl.setBorder(titledBorder);
		pnl_ThaoTac.add(pnl);
		pnl.setLayout(null);

		lblMaPhong = new JLabel("Mã Phòng:");
		lblMaPhong.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaPhong.setBounds(20, 68, 97, 14);
		pnl.add(lblMaPhong);

		txtMaPhong = new JTextField();
		txtMaPhong.setBounds(114, 65, 141, 20);
		pnl.add(txtMaPhong);
		txtMaPhong.setEditable(false);
		txtMaPhong.setColumns(10);

		lblLoaiPhong = new JLabel("Loại Phòng:");
		lblLoaiPhong.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiPhong.setBounds(20, 134, 72, 14);
		pnl.add(lblLoaiPhong);

		cmb_LoaiPhong = new JComboBox<String>();
		cmb_LoaiPhong.setModel(getTenLoaiPhong());
		cmb_LoaiPhong.setBounds(114, 129, 141, 24);
		cmb_LoaiPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectedValue = cmb_LoaiPhong.getSelectedItem().toString();
					txtGia.setText(getGiaHat(selectedValue) + "");
				}
				if (isEventListenerEnabled) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String selectedValue = cmb_LoaiPhong.getSelectedItem().toString();
						timPhongTheoLoaiPhong(selectedValue);
					}
				}

			}
		});

		pnl.add(cmb_LoaiPhong);

		lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrangThai.setBounds(296, 132, 73, 19);
		pnl.add(lblTrangThai);

		lblGia = new JLabel("Giá Phòng:");
		lblGia.setHorizontalAlignment(SwingConstants.LEFT);
		lblGia.setBounds(20, 186, 72, 14);
		pnl.add(lblGia);

		txtGia = new JTextField();
		txtGia.setBounds(114, 183, 141, 20);
		pnl.add(txtGia);
		txtGia.setEditable(false);
		txtGia.setColumns(10);

		lblTenPhong = new JLabel("Tên Phòng:");
		lblTenPhong.setBounds(297, 68, 72, 14);
		pnl.add(lblTenPhong);

		txtTenPhong = new JTextField();
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(379, 65, 161, 20);
		pnl.add(txtTenPhong);

		cmb_TrangThai = new JComboBox<String>();
		cmb_TrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Trống", "Đang sử dụng", "Chờ Nhận Phòng" }));
		cmb_TrangThai.setBounds(380, 129, 141, 24);
		cmb_TrangThai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (isEventListenerEnabled) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String selectedValue = cmb_TrangThai.getSelectedItem().toString();
						timPhongTheoTrangThai(selectedValue);
					}
				}
			}
		});
		pnl.add(cmb_TrangThai);

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(225, 24, 236, 20);
		pnl.add(txtTimKiem);

		lblTimKiem = new JLabel("Nhập thông tin phòng cần tìm:");
		lblTimKiem.setBounds(20, 24, 195, 20);
		pnl.add(lblTimKiem);
		lblTimKiem.setHorizontalAlignment(SwingConstants.LEFT);

		// Panel chức năng : thực hiện các chức năng

		pnlChucNang = new JPanel();
		pnlChucNang.setBackground(color);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder2 = new TitledBorder(lineBorder, "Chọn Chức Năng");
		pnlChucNang.setBorder(titledBorder2);
		pnl_ThaoTac.add(pnlChucNang);
		pnlChucNang.setLayout(null);

		btnTim = new JButton("Tìm Kiếm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemPhong();
			}
		});
		btnTim.setBounds(36, 137, 125, 31);
		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTim.setIcon(new ImageIcon(imgTim));
		pnlChucNang.add(btnTim);
		/*
		 * Buttton thêm dùng để thêm mới một phòng
		 */
		Image imgAdd = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themPhong();
			}
		});
		btnThem.setBounds(36, 21, 125, 31);
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
				capNhatPhong();
			}
		});
		btnCapNhatPhong.setBounds(36, 75, 125, 31);
		pnlChucNang.add(btnCapNhatPhong);
		btnCapNhatPhong.setIcon(new ImageIcon(imgEdit));
		/*
		 * Button xóa dùng để xóa một phòng, chỉ đc xóa khi phòng ở trạng thái trống
		 */
		Image imgExit = new ImageIcon(this.getClass().getResource("" + "/Button-Close.24.png")).getImage();
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaPhong();
			}
		});
		btnXoa.setBounds(228, 21, 156, 31);
		pnlChucNang.add(btnXoa);
		btnXoa.setIcon(new ImageIcon(imgExit));
		/*
		 * Button xóa trắng: dùng để làm mới các JTextfield
		 */
		Image imgClean = new ImageIcon(this.getClass().getResource("" + "/bubbles.png")).getImage();
		btnXoaTrang = new JButton("Xóa Trắng  (F4)");
		btnXoaTrang.setMnemonic(KeyEvent.VK_O);

		btnXoaTrang.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangThongTinNhap();
			}
		});
		btnXoaTrang.setBounds(228, 75, 156, 31);
		pnlChucNang.add(btnXoaTrang);
		btnXoaTrang.setIcon(new ImageIcon(imgClean));
		/*
		 * Button lưu dùng để lưu lại thông tin phòng sau thi thêm phòng hoặc cập nhật
		 * phòng
		 */
		Image imgSave = new ImageIcon(this.getClass().getResource("" + "/Save.24.png")).getImage();
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luuPhongVaoDanhSach();
			}
		});
		btnLuu.setBounds(228, 137, 156, 31);
		pnlChucNang.add(btnLuu);
		btnLuu.setIcon(new ImageIcon(imgSave));

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
		
		pnl_DSPhong = new JPanel();
		pnl_DSPhong.setBackground(color);
		pnl_DSPhong.setBorder(new TitledBorder(null, "Danh S\u00E1ch Ph\u00F2ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnl_QuanLyPhong.add(pnl_DSPhong);
		pnl_DSPhong.setLayout(new BorderLayout(0, 0));

		/*
		 * khởi Tạo class Phòng_Dao : lấy danh sách phòng
		 */
		phong_dao = new Phong_dao();
		tbl_DSPhong = new JTable();
		tableModel = taoBang(phong_dao.selectALL());
		tbl_DSPhong.setModel(tableModel);

		// ngăn chặn nhân viên chỉnh sửa dữ liệu trực tiếp trên JTable
		tbl_DSPhong.setDefaultEditor(Object.class, null);

		scr_DSPhong = new JScrollPane(tbl_DSPhong);
		scr_DSPhong.setPreferredSize(new Dimension(452, 350));

		pnl_DSPhong.add(scr_DSPhong, BorderLayout.CENTER);

		setContentPane(pnl_contentPaneQLP);
	}

	/*
	 * Làm mới lại danh sách phòng (sau khi click Button Làm Mới)
	 */
	protected void lamMoiBang() {
		txtTimKiem.setText("");
		tableModel = taoBang(phong_dao.selectALL());
		tbl_DSPhong.setModel(tableModel);
	}

	/*
	 * Tiến Hành Tạo Phòng mới hoặc cập nhật phòng lên CSDL
	 */
	protected void luuPhongVaoDanhSach() {
		String maPhong = txtMaPhong.getText().trim();
		String tenPhong = txtTenPhong.getText().trim();
		String loaiPhong = cmb_LoaiPhong.getSelectedItem().toString().trim();
		String trangThai = cmb_TrangThai.getSelectedItem().toString().trim();
		String giaPhong = txtGia.getText().trim();
		LoaiPhong lp = getLoaiPhong(loaiPhong);
		if (tenPhong.equals("")) {
			JOptionPane.showMessageDialog(this, "Thông tin nhập không được để trống");
		} else {
//    			if (KT(maPhong)) {
			if (edit == 1) {
//				if (timMaPhong(maPhong)) {
				Phong p = new Phong(taoMaPhong(), tenPhong, lp, "Trống");
				phong_dao.add(p);
				lamMoiBang();
				JOptionPane.showMessageDialog(this, "Tạo mới thành công");
				xoaTrangThongTinNhap();
				btnCapNhatPhong.setEnabled(true);
				btnXoa.setEnabled(true);
				btnThem.setText("Thêm");
//				} else
//					JOptionPane.showMessageDialog(this, "Mã Phòng không được trùng");
			} else if (edit == 2) {
				Phong p = new Phong(maPhong, tenPhong, lp, trangThai);
				phong_dao.updateALLPhong(p);
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				xoaTrangThongTinNhap();
				lamMoiBang();
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnCapNhatPhong.setText("Cập Nhật");
			}
			isEventListenerEnabled = true;
		}
//		else	JOptionPane.showMessageDialog(this, "");
	}

	/*
	 * Kiểm tra mã phòng có nằm trùng với các mã phòng trong ds phòng
	 */
	private boolean timMaPhong(String maPhong) {
		maPhong = maPhong.toUpperCase();
		List<Phong> dsPhong = phong_dao.selectALL();
		for (Phong p : dsPhong) {
			if (p.getMaPhong().contains(maPhong))
				return false;
		}
		return true;
	}

	private String taoMaPhong() {
		String maPhong;
		// Sử dụng Formatter để định dạng chuỗi thành "KHXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maPhong = formatter.format("PH%02d", PHCounter).toString();
			PHCounter++;
		} while (!timMaPhong(maPhong));
		formatter.close();
		return maPhong;
	}

	/*
	 * Tìm kiếm Phòng dựa vào JtextField Tìm kiếm (khi click vào Button Tìm Kiếm)
	 */
	protected void timKiemPhong() {
		String phong = "";
		String loai = "", trangthai = "";
		String a = txtTimKiem.getText();
		Double gia = 0.0;
		if (!a.equals("")) {
			if (a.chars().allMatch(Character::isDigit)) // Kiểm tra txtGia phải là số
				gia = Double.valueOf(a); // Ép kiểu txtTimphong sang số ==> tìm phòng theo giá
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			phong = "maPhong like N'%" + a + "%' or tenPhong like N'%" + a + "%' or tenLoai like  N'%" + a
					+ "%' or trangThai like N'%" + a + "%' or giaHat = " + gia;
			tbl_DSPhong.setModel(taoBang(phong_dao.findByCondition(phong)));
		} else
			JOptionPane.showMessageDialog(this, "Hãy nhập thông tin tìm kiếm!");

	}

	protected void timPhongTheoTrangThai(String selectedValue) {
		String trangThai = "trangThai = N'" + selectedValue + "'";
		tbl_DSPhong.setModel(taoBang(phong_dao.findByCondition(trangThai)));
	}

	/*
	 * Tìm Phòng theo Loại Phòng
	 */
	protected void timPhongTheoLoaiPhong(String selectedValue) {
		String loaiPH = " tenLoai = N'" + selectedValue + "'";
		tbl_DSPhong.setModel(taoBang(phong_dao.findByCondition(loaiPH)));
	}

	// Xóa trắng, làm mới các Jtextfield thông tin nhập
	protected void xoaTrangThongTinNhap() {
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtGia.setText("");
		txtMaPhong.requestFocus();
	}

	/*
	 * Xóa một Phòng ở trạng thái trống
	 */
	protected void xoaPhong() {
		// Kiểm tra hàng đẫ được chọn hay không
		int n = tbl_DSPhong.getSelectedRow();
		if (n != -1) {
			if (tbl_DSPhong.getModel().getValueAt(n, 3).toString().equalsIgnoreCase("Trống")) {
				int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?");
				if (tb == JOptionPane.YES_OPTION) {
					String maPhong = tbl_DSPhong.getModel().getValueAt(n, 0).toString();
					phong_dao.delete(maPhong);
					lamMoiBang();
				}
			} else
				JOptionPane.showMessageDialog(null, "Bạn không thể xóa phòng đang sử dụng!");

		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn phòng cần xóa!");

	}

	/*
	 * Sửa đổi thông tin phòng đang trống theo mã phòng
	 */
	protected void capNhatPhong() {
		// lấy số dòng thông tin phòng được chọn từ bảng
		int n = tbl_DSPhong.getSelectedRow();
		if (btnCapNhatPhong.getText().equalsIgnoreCase("Hủy")) {
			edit = 0; // Phân biệt hủy = 0 và cập nhật = 2, thêm = 1
			mokhoaKhoaCapNhatPhong(true);
			isEventListenerEnabled = true;
			xoaTrangThongTinNhap();
			btnCapNhatPhong.setText("Cập Nhật");
		} else
		// Kiểm tra hàng đẫ được chọn hay không
		if (n != -1) {
			if (tbl_DSPhong.getModel().getValueAt(n, 3).toString().equalsIgnoreCase("Trống")) {
				edit = 2;
				if (btnCapNhatPhong.getText().equalsIgnoreCase("Cập Nhật")) {
					mokhoaKhoaCapNhatPhong(false);
					isEventListenerEnabled = false;
					btnCapNhatPhong.setText("Hủy");
					// Đưa dữ liệu từ bảng vào textField để cập nhật
					txtMaPhong.setText(tbl_DSPhong.getModel().getValueAt(n, 0).toString());
					txtTenPhong.setText(tbl_DSPhong.getModel().getValueAt(n, 1).toString());
					cmb_LoaiPhong.setSelectedItem(tbl_DSPhong.getModel().getValueAt(n, 2).toString());
					txtGia.setText(tbl_DSPhong.getModel().getValueAt(n, 4).toString());
				}
			} else
				JOptionPane.showMessageDialog(null, "Bạn không thể cập nhật phòng đang sử dụng!");

		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn phòng cần cập nhật!");
	}

	/*
	 * Thêm một phòng mới
	 */
	protected void themPhong() {
		if (btnThem.getText().equalsIgnoreCase("Thêm")) {
			edit = 1;
			isEventListenerEnabled = false;
			xoaTrangThongTinNhap();
			moKhoaThemPhong(false);
			btnThem.setText("Hủy");
			txtTenPhong.requestFocus();// đặt con trỏ chuột vào ô tên phòng
			txtGia.setText(getGiaHat(cmb_LoaiPhong.getSelectedItem().toString()) + "");
		} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
			edit = 0;
			moKhoaThemPhong(true);
			isEventListenerEnabled = true;
			btnThem.setText("Thêm");
			xoaTrangThongTinNhap();
		}
	}

	/*
	 * Truyền vào danh sách phòng, trả về table Model theo danh sách phòng
	 */
	protected DefaultTableModel taoBang(List<Phong> ds) {
		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Mã Phòng");
		table.addColumn("Tên Phòng");
		table.addColumn("Loại Phòng");
		table.addColumn("Trạng Thái");
		table.addColumn("Giá hát");
		for (Phong p : ds) {
			String TienHat = decimalFormat.format(p.getLoaiPhong().getGiaHat());
			Object[] rowData = { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getTenLoai(), p.getTrangThai(),
					TienHat };
			table.addRow(rowData);
		}
		return table;
	}

	/*
	 * Mở khóa các button thêm phòng và khóa các ô thông tin không được nhập
	 */
	protected void moKhoaThemPhong(boolean a) {
		btnCapNhatPhong.setEnabled(a);
		btnXoa.setEnabled(a);
		cmb_TrangThai.setEnabled(a);
		btnLuu.setEnabled(!a);
	}

	/*
	 * Mở khóa các button cập nhật phòng và khóa các Jtextfield không được nhập
	 */
	protected void mokhoaKhoaCapNhatPhong(boolean a) {
		btnThem.setEnabled(a);
		btnXoa.setEnabled(a);
		btnLuu.setEnabled(!a);
		txtMaPhong.setEnabled(a);
		cmb_TrangThai.setEnabled(a);
	}

	/*
	 * Trả về giá hát của loại phòng tương ứng => khi chọn loại phòng thì hiển thị
	 * giá hát
	 */
	protected double getGiaHat(String tenloai) {
		LoaiPhong_dao lp = new LoaiPhong_dao();
		List<LoaiPhong> dslp = lp.selectALL();
		for (LoaiPhong loaiPhong : dslp) {
			if (loaiPhong.getTenLoai().equals(tenloai))
				return loaiPhong.getGiaHat();
		}
		return 0;
	}

	/*
	 * Trả về LoaiPhong dựa trên tên loại phòng
	 */
	protected LoaiPhong getLoaiPhong(String loai) {
		LoaiPhong_dao lp_dao = new LoaiPhong_dao();
		List<LoaiPhong> dslp = lp_dao.selectALL();
		LoaiPhong lp = new LoaiPhong();
		for (LoaiPhong loaiPhong : dslp) {
			if (loaiPhong.getTenLoai().equals(loai))
				return loaiPhong;

		}
		return lp;
	}

	protected static DefaultComboBoxModel<String> getTenLoaiPhong() {
		DefaultComboBoxModel<String> cmbModel = new DefaultComboBoxModel<String>();
		LoaiPhong_dao lp_dao = new LoaiPhong_dao();
		List<LoaiPhong> dslp = lp_dao.selectALL();
		for (LoaiPhong loaiPhong : dslp) {
			cmbModel.addElement(loaiPhong.getTenLoai());
		}
		return cmbModel;
	}
}
