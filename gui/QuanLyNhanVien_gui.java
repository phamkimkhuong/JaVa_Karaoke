package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Formatter;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChucVu_dao;
import dao.KhachHang_dao;
import dao.NhanVien_dao;
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;

import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;

public class QuanLyNhanVien_gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_ContentPaneQLNV;
	private static int nhanVienCounter = 1;
	private JTextField txtHoTen;
	private JTextField txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtTimNhanVien;
	private JComboBox cmbTrinhDo;
	private JComboBox cmbChucVu;
	private JComboBox cmbThang;
	private JComboBox cmbNam;
	private JButton btnThemNV;
	private JButton btnCapNhatNV;
	private JButton btnXoaTrangNV;
	private JButton btnLuuNV;
	private JButton btnTimNV;
	private JPanel pnl_DanhSachNhanVien;
	private JScrollPane scrNhanVien;
	private DefaultTableModel tblModelNhanVien;
	private JTable tblNhanVien;
	private JTextField txtHeSoLuong;
	private JTextField txtLuongCoBan;
	private JTextField txtMaNV;
	private int edit;
	private NhanVien_dao nhanVien_dao;
	private JButton btnLamMoi;
	private ChucVu_dao cv_dao;

	/**
	 * Create the frame.
	 */
	public QuanLyNhanVien_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPaneQLNV = new JPanel();
		pnl_ContentPaneQLNV.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPaneQLNV.setLayout(new BorderLayout());
		Color color = new Color(197, 199, 199);
		cv_dao = new ChucVu_dao();
		pnl_ContentPaneQLNV.setBackground(color);
		pnl_DanhSachNhanVien = new JPanel();
		pnl_ContentPaneQLNV.add(pnl_DanhSachNhanVien, BorderLayout.CENTER);
		pnl_DanhSachNhanVien.setLayout(new BorderLayout(0, 0));
		JPanel pnl_TieuDe = new JPanel();
		pnl_TieuDe.setBackground(color);
		pnl_DanhSachNhanVien.add(pnl_TieuDe, BorderLayout.NORTH);

		JLabel lblDSNhanVien = new JLabel("Quản Lí Nhân Viên");
		lblDSNhanVien.setForeground(new Color(15, 102, 165));
		lblDSNhanVien.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_TieuDe.add(lblDSNhanVien);

		JPanel pnl_CenTer = new JPanel();
		pnl_CenTer.setLayout(new BoxLayout(pnl_CenTer, BoxLayout.Y_AXIS));
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông tin Nhân Viên");
		pnl_CenTer.setBorder(titledBorder);
		pnl_CenTer.setBackground(color);
		JPanel pnl_p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_p1.setBackground(color);
		JLabel lblHoTen = new JLabel("Họ Tên:  ");
		txtHoTen = new JTextField(50);

		JLabel lblNgaySinh = new JLabel("Ngày Sinh:   ");
		txtNgaySinh = new JTextField(40);
		pnl_p1.add(lblHoTen);
		pnl_p1.add(txtHoTen);

		pnl_p1.add(lblNgaySinh);
		pnl_p1.add(txtNgaySinh);

		JPanel pnl_p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_p2.setBackground(color);
		JLabel lblSDT = new JLabel("Số Điện Thoại: ");
		txtSDT = new JTextField(50);
		JLabel lblEmail = new JLabel("Email: ");
		txtEmail = new JTextField(40);
		pnl_p2.add(lblSDT);
		pnl_p2.add(txtSDT);
		pnl_p2.add(lblEmail);
		pnl_p2.add(txtEmail);

		JPanel pnl_p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_p3.setBackground(color);
		JLabel lblTrinhDo = new JLabel("Trình Độ: ");
		cmbTrinhDo = new JComboBox();
		cmbTrinhDo.setPreferredSize(new Dimension(450, 20));
		cmbTrinhDo.setModel(new DefaultComboBoxModel(new String[] { "Đại Học", "Cao Đẳng", "12/12" }));
		JLabel lblChucVu = new JLabel("Chức Vụ:  ");
		cmbChucVu = new JComboBox();
		cmbChucVu.setPreferredSize(new Dimension(405, 20));
		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] { "Nhân Viên", "Quản Lý" }));
		pnl_p3.add(lblTrinhDo);
		pnl_p3.add(cmbTrinhDo);
		pnl_p3.add(lblChucVu);
		pnl_p3.add(cmbChucVu);

		JPanel pnl_p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_p4.setBackground(color);
		JLabel lblHeSoLuong = new JLabel("Hệ số lương: ");
		txtHeSoLuong = new JTextField(50);
		JLabel lblLuongCoBan = new JLabel("Lương cơ bản: ");
		txtLuongCoBan = new JTextField(40);
		pnl_p4.add(lblHeSoLuong);
		pnl_p4.add(txtHeSoLuong);
		pnl_p4.add(lblLuongCoBan);
		pnl_p4.add(txtLuongCoBan);

		JPanel pnl_p5 = new JPanel();
		pnl_p5.setBackground(color);
		pnl_p5.add(btnThemNV = new JButton("Thêm"));
		btnThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNhanVien();
			}
		});
		Image imgAdd = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();
		btnThemNV.setIcon(new ImageIcon(imgAdd));
		pnl_p5.add(btnCapNhatNV = new JButton("Cập Nhật"));
		btnCapNhatNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatNhanVien();
			}
		});
		Image imgEdit = new ImageIcon(this.getClass().getResource("" + "/Edit.24.png")).getImage();
		btnCapNhatNV.setIcon(new ImageIcon(imgEdit));
		pnl_p5.add(btnXoaTrangNV = new JButton("Xóa Trắng"));
		btnXoaTrangNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongTextfields();
			}
		});
		Image imgClean = new ImageIcon(this.getClass().getResource("" + "/bubbles.png")).getImage();
		btnXoaTrangNV.setIcon(new ImageIcon(imgClean));
		pnl_p5.add(btnLuuNV = new JButton("Lưu"));
		btnLuuNV.setEnabled(false);
		btnLuuNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luuNhanVienVaoDS();
			}
		});
		Image imgSave = new ImageIcon(this.getClass().getResource("" + "/Save.24.png")).getImage();
		btnLuuNV.setIcon(new ImageIcon(imgSave));
		pnl_p5.add(btnLamMoi = new JButton("Làm Mới"));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));
		JPanel pnl_p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_p6.setBackground(color);
		JLabel lblTimNhanVien = new JLabel("Tìm Nhân Viên:");
		txtTimNhanVien = new JTextField(10);
		pnl_p6.add(lblTimNhanVien);
		pnl_p6.add(txtTimNhanVien);
		pnl_p6.add(btnTimNV = new JButton("Tìm"));
		btnTimNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemNhanVien();
			}
		});
		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTimNV.setIcon(new ImageIcon(imgTim));

		JPanel pnl_p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_p7.setBackground(color);
		JLabel lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(50);
		txtMaNV.setEditable(false);
		pnl_p7.add(lblMaNV);
		pnl_p7.add(txtMaNV);

		pnl_CenTer.add(pnl_p1);
		pnl_CenTer.add(pnl_p2);
		pnl_CenTer.add(pnl_p3);
		pnl_CenTer.add(pnl_p4);
		pnl_CenTer.add(pnl_p7);
		pnl_CenTer.add(pnl_p5);
		pnl_CenTer.add(pnl_p6);

		lblHoTen.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblEmail.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblChucVu.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblTrinhDo.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblHeSoLuong.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblSDT.setPreferredSize(lblLuongCoBan.getPreferredSize());
		lblMaNV.setPreferredSize(lblLuongCoBan.getPreferredSize());

		// South
		JPanel pnl_South = new JPanel();
		pnl_South.setPreferredSize(new Dimension(10, 300));
		LineBorder lineBorder1 = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder1 = new TitledBorder(lineBorder1, "Thông tin Nhân Viên");
		pnl_South.setBorder(titledBorder1);
		String headersNhanVien[] = "Mã nhân viên;Họ tên;Ngày sinh;Số điện thoại;Email;Chức Vụ;Trình độ;Hệ Số Lương;Lương Cơ Bản"
				.split(";");
		tblModelNhanVien = new DefaultTableModel(headersNhanVien, 0);
		scrNhanVien = new JScrollPane();
		scrNhanVien.setViewportView(tblNhanVien = new JTable(tblModelNhanVien));
		scrNhanVien.setPreferredSize(new Dimension(1050, 300));
		pnl_South.setLayout(new BoxLayout(pnl_South, BoxLayout.X_AXIS));
		pnl_South.add(scrNhanVien);

		pnl_DanhSachNhanVien.add(pnl_CenTer, BorderLayout.CENTER);
		pnl_DanhSachNhanVien.add(pnl_South, BorderLayout.SOUTH);
		setContentPane(pnl_ContentPaneQLNV);
		updateTableData();
	}

	private void xoaRongTextfields() {
		// TODO Auto-generated method stub
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtNgaySinh.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		txtHeSoLuong.setText("");
		txtLuongCoBan.setText("");
		txtMaNV.requestFocus();
	}

	protected void themNhanVien() {
		// TODO Auto-generated method stub
		edit = 1;
		if (btnThemNV.getText().equalsIgnoreCase("Thêm")) {
			xoaRongTextfields();
			// vô hiệu hóa button cập nhật và button xóa
			btnCapNhatNV.setEnabled(false);
			btnThemNV.setText("Hủy");
			// Mở khóa Button lưu và txtMaPhong
			btnLuuNV.setEnabled(true);
			// txtMaKH.setEnabled(false);
			// đặt con trỏ chuột vào ô mã phòng
			txtMaNV.requestFocus();
		} else if (btnThemNV.getText().equalsIgnoreCase("Hủy")) {
			edit = 0;
			btnCapNhatNV.setEnabled(true);
			btnLuuNV.setEnabled(false);
			btnThemNV.setText("Thêm");
			xoaRongTextfields();
		}
	}

	private boolean timMaNV(String maNV) {
		// TODO Auto-generated method stub
		maNV = maNV.toUpperCase();
		List<NhanVien> list = nhanVien_dao.getAllNhanVien();
		for (NhanVien nv : list) {
			if (nv.getMaNV().contains(maNV))
				return false;
		}
		return true;
	}

	protected void luuNhanVienVaoDS() {
		// TODO Auto-generated method stub
		String maNV = txtMaNV.getText();
		String hoTen = txtHoTen.getText();
		String ngaySinh = txtNgaySinh.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String trinhDo = cmbTrinhDo.getSelectedItem().toString();
		String chucVu = cmbChucVu.getSelectedItem().toString();
		String maCV = "";
		List<ChucVu> dscv = cv_dao.getAllChucVu();
		for (ChucVu cv : dscv) {
			if (cv.getTenCV().trim().equalsIgnoreCase(chucVu.trim())) {
				maCV = cv.getMaCV();
			}
		}
		String heSoLuong = txtHeSoLuong.getText();
		String luongCoBan = txtLuongCoBan.getText();
		if (hoTen.equals("") || ngaySinh.equals("") || sdt.equals("") || email.equals("") || trinhDo.equals("")
				|| chucVu.equals("") || heSoLuong.equals("") || luongCoBan.equals("")) {
			JOptionPane.showMessageDialog(this, "Thông tin nhập không được để trống");
		} else {
			if (edit == 1) {
				if (validData()) {
					double heSoLuong1 = Double.parseDouble(txtHeSoLuong.getText());
					double luongCoBan1 = Double.parseDouble(txtLuongCoBan.getText());
					String ma = taoMaNhanVien();
					NhanVien nv = new NhanVien(ma, hoTen, ngaySinh, sdt, email, trinhDo, maCV, heSoLuong1,
							luongCoBan1, null);
					nhanVien_dao.addNhanVien(nv);
					lamMoiBang();
					JOptionPane.showMessageDialog(this, "Tạo mới thành công");
					xoaRongTextfields();
					lamMoiBang();
					btnCapNhatNV.setEnabled(true);
					btnThemNV.setText("Thêm");
				}

			} else if (edit == 2) {
				double heSoLuong1 = Double.parseDouble(txtHeSoLuong.getText());
				double luongCoBan1 = Double.parseDouble(txtLuongCoBan.getText());
				NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, sdt, email, trinhDo, maCV, heSoLuong1, luongCoBan1,
						null);
				nhanVien_dao.updateNhanVien(nv);
				;
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				xoaRongTextfields();
				lamMoiBang();
				btnThemNV.setEnabled(true);
				btnCapNhatNV.setText("Cập Nhật");

			}
		}
	}

	private void capNhatNhanVien() {
		edit = 2;
		int row = tblNhanVien.getSelectedRow();
		if (row >= 0) {
			String maNV = txtMaNV.getText();
			String hoTen = txtHoTen.getText();
			String ngaySinh = txtNgaySinh.getText();
			String sdt = txtSDT.getText();
			String email = txtEmail.getText();
			String trinhDo = cmbTrinhDo.getSelectedItem().toString();
			String chucVu = cmbChucVu.getSelectedItem().toString();
			String heSoLuong = txtHeSoLuong.getText();
			String luongCoBan = txtLuongCoBan.getText();
			if (btnCapNhatNV.getText().equalsIgnoreCase("Hủy")) {
				btnCapNhatNV.setText("Cập Nhật");
				btnThemNV.setEnabled(true);
				btnXoaTrangNV.setEnabled(true);
				btnLuuNV.setEnabled(false);
			} else if (row != -1) {
				edit = 2;
				if (btnCapNhatNV.getText().equalsIgnoreCase("Cập Nhật")) {
					btnCapNhatNV.setText("Hủy");
					btnLuuNV.setEnabled(true);
					btnThemNV.setEnabled(false);
					// Đưa dữ liệu từ bảng vào textField để cập nhật
					txtMaNV.setText(tblNhanVien.getModel().getValueAt(row, 0).toString());
					txtHoTen.setText(tblNhanVien.getModel().getValueAt(row, 1).toString());
					txtNgaySinh.setText(tblNhanVien.getModel().getValueAt(row, 2).toString());
					txtSDT.setText(tblNhanVien.getModel().getValueAt(row, 3).toString());
					txtEmail.setText(tblNhanVien.getModel().getValueAt(row, 4).toString());
					txtHeSoLuong.setText(tblNhanVien.getModel().getValueAt(row, 7).toString());
					txtLuongCoBan.setText(tblNhanVien.getModel().getValueAt(row, 8).toString());
				}
			} else {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật thông tin không?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					double heSoLuong1 = Double.parseDouble(txtHeSoLuong.getText());
					double luongCoBan1 = Double.parseDouble(txtLuongCoBan.getText());
					NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, sdt, email, trinhDo, chucVu, heSoLuong1,
							luongCoBan1, null);
					if (nhanVien_dao.updateNhanVien(nv)) {
						tblModelNhanVien.setValueAt(maNV, row, 0);
						tblModelNhanVien.setValueAt(hoTen, row, 1);
						tblModelNhanVien.setValueAt(ngaySinh, row, 2);
						tblModelNhanVien.setValueAt(sdt, row, 3);
						tblModelNhanVien.setValueAt(email, row, 4);
						tblModelNhanVien.setValueAt(trinhDo, row, 5);
						tblModelNhanVien.setValueAt(chucVu, row, 6);
						tblModelNhanVien.setValueAt(heSoLuong, row, 7);
						tblModelNhanVien.setValueAt(luongCoBan, row, 8);
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						xoaRongTextfields();
						btnCapNhatNV.setText("Cập Nhật");
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng trong bảng để cập nhật thông tin nhân viên.");
		}
	}

	protected void timKiemNhanVien() {
		String a = txtTimNhanVien.getText();
		String nhanVien = "";
		if (!a.equals("")) {
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			nhanVien = "maNV like N'%" + a + "%' or hoTenNV like N'%" + a + "%' or ngaySinh like  N'%" + a
					+ "%' or SDT like N'%" + a + "%' or email like N'%" + a + "%'or chucVu like N'%" + a
					+ "%'or heSoLuong like N'%" + a + "%'or luongCoBan like N'%" + a + "%'";
			updata(nhanVien_dao.findNhanVien(nhanVien));
		} else
			JOptionPane.showMessageDialog(null, "Hãy Nhập Thông Tin Tìm Kiếm!");
	}

	private void updateTableData() {
		nhanVien_dao = new NhanVien_dao();
		List<NhanVien> list = nhanVien_dao.getAllNhanVien();
		for (NhanVien nv : list) {
			ChucVu chucVu = cv_dao.findChucVuTheoMa(nv.getChucVu());
			String[] rowData = { nv.getMaNV(), nv.getHoTen(), nv.getNgaySinh(), nv.getsDT(), nv.getEmail(),
					chucVu.getTenCV(), nv.getTrinhDo(), nv.getHeSoLuong() + "", nv.getLuongCoBan() + "" };
			tblModelNhanVien.addRow(rowData);
		}
		tblNhanVien.setModel(tblModelNhanVien);
	}

	private void updata(List<NhanVien> list) {
		tblModelNhanVien.setRowCount(0);
		nhanVien_dao = new NhanVien_dao();
		for (NhanVien nv : list) {
			String[] rowData = { nv.getMaNV(), nv.getHoTen(), nv.getNgaySinh(), nv.getsDT(), nv.getEmail(),
					nv.getChucVu(), nv.getTrinhDo(), nv.getHeSoLuong() + "", nv.getLuongCoBan() + "" };
			tblModelNhanVien.addRow(rowData);
		}
		tblNhanVien.setModel(tblModelNhanVien);
	}

	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		tblModelNhanVien.setRowCount(0);
		updateTableData();
	}

	public String taoMaNhanVien() {
		String maNhanVien;
		// Sử dụng Formatter để định dạng chuỗi thành "NVXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maNhanVien = formatter.format("NV%06d", nhanVienCounter).toString();
			nhanVienCounter++;
		} while (!timMaNV(maNhanVien));
		formatter.close();
		return maNhanVien;
	}

	private boolean validData() {
		String maNV = txtMaNV.getText();
		String hoTen = txtHoTen.getText();
		String ngaySinh = txtNgaySinh.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String trinhDo = cmbTrinhDo.getSelectedItem().toString();
		String chucVu = cmbChucVu.getSelectedItem().toString();
		String heSoLuong = txtHeSoLuong.getText();
		String luongCoBan = txtLuongCoBan.getText();
//		Họ tên không được để trống, có thể gồm nhiều từ ngăn cách bởi khoảng 
//		trắng. Không chứa ký số hoặc các ký tự đặc biệt khác, ngoại trừ ký tự 
		if (!(hoTen.length() > 0 && hoTen.matches("[\\p{L}' ]+"))) {
			JOptionPane.showMessageDialog(this, "Họ tên không được chứa số và kí tự đặc biệt");
			return false;
		}
		if (ngaySinh.length() > 0) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate birthDate = LocalDate.parse(ngaySinh, formatter);
				LocalDate now = LocalDate.now();

				// Ensure the person is at least 15 years old (considering 14 years and 364 days
				// as the minimum age)
				if (birthDate.plusYears(18).isAfter(now)) {
					JOptionPane.showMessageDialog(this, "Ngày sinh phải là người trên 18 tuổi");
					return false;
				}
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(this,
						"Định dạng ngày sinh không đúng. Vui lòng sử dụng định dạng yyyy-MM-dd");
				return false;
			}
		}
		if (!(sdt.length() >= 10 && sdt.length() <= 11 && sdt.matches("\\d+"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10-11 chữ số và không chứa ký tự đặc biệt");
			return false;
		}
		if (!(email.matches(".+@gmail\\.com"))) {
			JOptionPane.showMessageDialog(this, "Email phải có ít nhất một ký tự trước @ và kết thúc bằng @gmail.com");
			return false;
		}
		if (!(heSoLuong.length() > 0 && heSoLuong.matches("\\d+"))) {
			JOptionPane.showMessageDialog(this, "Hệ số lương phải là số và > 0");
			return false;
		}
		if (!(luongCoBan.length() > 0 && luongCoBan.matches("\\d+"))) {
			JOptionPane.showMessageDialog(this, "Lương cơ bản phải là số và > 0");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
