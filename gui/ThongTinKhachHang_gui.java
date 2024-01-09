package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.KhachHang_dao;
import entity.KhachHang;
import entity.Phong;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class ThongTinKhachHang_gui extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static int khachHangCounter = 1;
	protected JPanel pnl_ContentPaneQLKH;
	private JPanel pnl_DanhSachKhachHang;
	private JTextField txtHoTen;
	private JTextField txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtTim;
	private KhachHang_dao khachHang_dao;
	private JButton btnLuu;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JTextField txtMaKH;
	private JButton btnLamMoi;
	private JButton btnTimKH;
	private DefaultTableModel tblModel_DSKH;
	private JTable tbl_DSKhachHang;
	private int edit;
	private JLabel lblMaKH;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ThongTinKhachHang_gui frame = new ThongTinKhachHang_gui();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ThongTinKhachHang_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width,screenSize.height);
		this.setLocationRelativeTo(null);
		
		Color color = new Color(197,199,199);
		
		this.setBackground(color);
		pnl_ContentPaneQLKH = new JPanel();
		pnl_ContentPaneQLKH.setBackground(color);
		pnl_ContentPaneQLKH.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPaneQLKH.setLayout(new BorderLayout());
		
		pnl_DanhSachKhachHang = new JPanel();
		pnl_ContentPaneQLKH.add(pnl_DanhSachKhachHang,BorderLayout.CENTER);	
		pnl_DanhSachKhachHang.setLayout(new BorderLayout(0, 0));
	
		JPanel pnl_TieuDe = new JPanel();
		pnl_TieuDe.setBackground(color);
		pnl_DanhSachKhachHang.add(pnl_TieuDe, BorderLayout.NORTH);

		JLabel lblDSKhachHang = new JLabel("Quản Lí Khách Hàng");
		lblDSKhachHang.setForeground(new Color(15,102,165));
		lblDSKhachHang.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_TieuDe.add(lblDSKhachHang);
		
		JPanel pnl_ThongTinKH = new JPanel();
		pnl_ThongTinKH.setBackground(color);
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		Color raisedColor = new Color(255, 150, 150); // Màu nền
        Color shadowColor = new Color(150, 50, 50);  // Màu viền
        BorderFactory.createRaisedBevelBorder();
        BevelBorder customRaisedBevel = new BevelBorder(BevelBorder.LOWERED, raisedColor, shadowColor);
        BevelBorder c = new BevelBorder(BevelBorder.RAISED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(160, 160, 160), new Color(160, 160, 160));
        // Tạo tiêu đề "Border Title"
        TitledBorder titledBorder = new TitledBorder(c, "Thông Tin Khách Hàng",TitledBorder.LEFT,TitledBorder.ABOVE_TOP);
        pnl_ThongTinKH.setBorder(titledBorder);
		pnl_DanhSachKhachHang.add(pnl_ThongTinKH, BorderLayout.CENTER);
		pnl_ThongTinKH.setLayout(null);
		
		JLabel lblHoTen = new JLabel("Họ Tên:");
		lblHoTen.setBounds(10, 34, 93, 13);
		pnl_ThongTinKH.add(lblHoTen);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setBounds(557, 34, 68, 13);
		pnl_ThongTinKH.add(lblNgaySinh);
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(113, 31, 402, 19);
		pnl_ThongTinKH.add(txtHoTen);
		txtHoTen.setColumns(10);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(647, 31, 395, 19);
		pnl_ThongTinKH.add(txtNgaySinh);
		
		JLabel lblSDT = new JLabel("Số Điện Thoại:");
		lblSDT.setBounds(10, 89, 96, 13);
		pnl_ThongTinKH.add(lblSDT);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(557, 89, 45, 13);
		pnl_ThongTinKH.add(lblEmail);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(113, 86, 402, 19);
		pnl_ThongTinKH.add(txtSDT);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(647, 86, 395, 19);
		pnl_ThongTinKH.add(txtEmail);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themKhachHang();
			}
		});
		btnThem.setBounds(175, 176, 121, 31);
		Image imgAdd = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();
		btnThem.setIcon(new ImageIcon(imgAdd));
		pnl_ThongTinKH.add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
//		btnCapNhat.setBorder(new Round(10));
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatKH();
			}
		});
		btnCapNhat.setBounds(341, 176, 121, 31);
		Image imgEdit = new ImageIcon(this.getClass().getResource("" + "/Edit.24.png")).getImage();
		btnCapNhat.setIcon(new ImageIcon(imgEdit));	
		pnl_ThongTinKH.add(btnCapNhat);
		
		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.addActionListener(this);
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongTextfields();
			}
		});
		btnXoaTrang.setBounds(513, 176, 121, 31);
		Image imgClean = new ImageIcon(this.getClass().getResource("" + "/bubbles.png")).getImage();
		btnXoaTrang.setIcon(new ImageIcon(imgClean));
		pnl_ThongTinKH.add(btnXoaTrang);
		
		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luuKhachHangVaoDS();
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(this);
		btnLuu.setBounds(690, 176, 121, 31);
		Image imgSave = new ImageIcon(this.getClass().getResource("" + "/Save.24.png")).getImage();
		btnLuu.setIcon(new ImageIcon(imgSave));
		pnl_ThongTinKH.add(btnLuu);
		
		lblMaKH = new JLabel("Tìm khách hàng:");
		lblMaKH.setBounds(10, 238, 144, 13);
		pnl_ThongTinKH.add(lblMaKH);
		
		txtTim = new JTextField();
		txtTim.setColumns(10);
		txtTim.setBounds(112, 235, 136, 19);
		pnl_ThongTinKH.add(txtTim);
		
		btnTimKH = new JButton("Tìm");
		btnTimKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemKhachHang();
			}
		});
		btnTimKH.setBounds(260, 234, 121, 21);
		
		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTimKH.setIcon(new ImageIcon(imgTim));
		pnl_ThongTinKH.add(btnTimKH);
		
		txtMaKH = new JTextField();
		//txtMaKH.setEnabled(false);
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(113, 136, 402, 19);
		txtMaKH.setEditable(false);
		pnl_ThongTinKH.add(txtMaKH);
		
		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setBounds(10, 139, 93, 13);
		pnl_ThongTinKH.add(lblMKhchHng);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));
		btnLamMoi.setBounds(861, 176, 121, 31);
		pnl_ThongTinKH.add(btnLamMoi);
		
		JPanel pnl_DanhSachKH = new JPanel();
		LineBorder lineBorder1 = new LineBorder(Color.DARK_GRAY, 1);
        // Tạo tiêu đề "Border Title"
        TitledBorder titledBorder1 = new TitledBorder(lineBorder1, "Danh Sách Khách Hàng");
        pnl_DanhSachKH.setBorder(titledBorder1);
        pnl_DanhSachKH.setBackground(new Color(200,200,200));
		pnl_DanhSachKH.setPreferredSize(new Dimension(10, 300));
		pnl_DanhSachKhachHang.add(pnl_DanhSachKH, BorderLayout.SOUTH);
		pnl_DanhSachKH.setLayout(new BorderLayout(0, 0));
		
		String[] headers = {"Mã khách hàng ", "Tên khách hàng", "Ngày sinh", "Số điện thoại ", "Email"}; 
		khachHang_dao = new KhachHang_dao();
		khachHang_dao.doctuBang();
		tblModel_DSKH = new DefaultTableModel(headers,0);
		JScrollPane scrDSKH;
		tbl_DSKhachHang = new JTable(tblModel_DSKH);
		
		scrDSKH = new JScrollPane(tbl_DSKhachHang);
		scrDSKH.setBackground(color);
//		tbl_DSKhachHang.setBackground(new Color(200,200,200));
		pnl_DanhSachKH.add(scrDSKH, BorderLayout.NORTH);
		updateTableData();

		setContentPane(pnl_ContentPaneQLKH);
	}

	private void xoaRongTextfields() {
		// TODO Auto-generated method stub
		txtMaKH.setText("");
		txtHoTen.setText("");
		txtNgaySinh.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		txtMaKH.requestFocus();
	}

	protected void themKhachHang() {
		// TODO Auto-generated method stub
		edit = 1;
		if (btnThem.getText().equalsIgnoreCase("Thêm")) {
			xoaRongTextfields();
			// vô hiệu hóa button cập nhật và button xóa
			btnCapNhat.setEnabled(false);
			btnThem.setText("Hủy");
			// Mở khóa Button lưu và txtMaPhong
			btnLuu.setEnabled(true);
			// txtMaKH.setEnabled(false);
			// đặt con trỏ chuột vào ô mã phòng
			txtMaKH.requestFocus();
		} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
			edit = 0;
			btnCapNhat.setEnabled(true);
			btnLuu.setEnabled(false);
			btnThem.setText("Thêm");
			xoaRongTextfields();
		}
	}

	private boolean timMaKH(String maKH) {
		// TODO Auto-generated method stub
		maKH = maKH.toUpperCase();
		List<KhachHang> list = khachHang_dao.doctuBang();
		for (KhachHang kh : list) {
			if (kh.getMaKH().contains(maKH))
				return false;
		}
		return true;
	}

	protected void luuKhachHangVaoDS() {
		// TODO Auto-generated method stub
		String maKH = txtMaKH.getText();
		String hoTen = txtHoTen.getText();
		String ngaySinh = txtNgaySinh.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		if (hoTen.equals("") || ngaySinh.equals("") || sdt.equals("")) {
			JOptionPane.showMessageDialog(this, "Thông tin nhập không được để trống");
		} else {
			if (edit == 1) {
				if (validData()) {
					String ma = taoMaKhachHang();
					KhachHang kh = new KhachHang(ma, hoTen, ngaySinh, sdt, email);
					khachHang_dao.createKhachHang(kh);
					lamMoiBang();
					JOptionPane.showMessageDialog(this, "Tạo mới thành công");
					xoaRongTextfields();
					lamMoiBang();
					btnCapNhat.setEnabled(true);
					btnThem.setText("Thêm");
				}

			} else if (edit == 2) {
				if (validData()) {
				KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, sdt, email);
				khachHang_dao.updateKhachHang(kh);
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				xoaRongTextfields();
				lamMoiBang();
				btnThem.setEnabled(true);
				btnCapNhat.setText("Cập Nhật");
				}
			}
		}
	}

	private void capNhatKH() {
		edit = 2;
		int row = tbl_DSKhachHang.getSelectedRow();
		if (row >= 0) {
			String maKH = txtMaKH.getText();
			String hoTen = txtHoTen.getText();
			String ngaySinh = txtNgaySinh.getText();
			String sdt = txtSDT.getText();
			String email = txtEmail.getText();
			if (btnCapNhat.getText().equalsIgnoreCase("Hủy")) {
				btnCapNhat.setText("Cập Nhật");
				btnThem.setEnabled(true);
				btnXoaTrang.setEnabled(true);
			} else if (row != -1) {
				edit = 2;
				if (btnCapNhat.getText().equalsIgnoreCase("Cập Nhật")) {
					btnCapNhat.setText("Hủy");
					btnLuu.setEnabled(true);
					btnThem.setEnabled(false);
					// Đưa dữ liệu từ bảng vào textField để cập nhật
					txtMaKH.setText(tbl_DSKhachHang.getModel().getValueAt(row, 0).toString());
					txtHoTen.setText(tbl_DSKhachHang.getModel().getValueAt(row, 1).toString());
					txtNgaySinh.setText(tbl_DSKhachHang.getModel().getValueAt(row, 2).toString());
					txtSDT.setText(tbl_DSKhachHang.getModel().getValueAt(row, 3).toString());
					txtEmail.setText(tbl_DSKhachHang.getModel().getValueAt(row, 4).toString());
				}
			} else {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật thông tin không?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, sdt, email);
					if (khachHang_dao.updateKhachHang(kh)) {
						tblModel_DSKH.setValueAt(maKH, row, 0);
						tblModel_DSKH.setValueAt(hoTen, row, 1);
						tblModel_DSKH.setValueAt(ngaySinh, row, 2);
						tblModel_DSKH.setValueAt(sdt, row, 3);
						tblModel_DSKH.setValueAt(email, row, 4);
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						xoaRongTextfields();
						btnCapNhat.setText("Cập Nhật");
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng trong bảng để cập nhật thông tin khách hàng.");
		}
	}

	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		tblModel_DSKH.setRowCount(0);
		updateTableData();
	}

	protected void timKiemKhachHang() {
		String a = txtTim.getText();
		String khachHang = "";
		if (!a.equals("")) {
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			khachHang = "maKH like N'%" + a + "%' or hoTen like N'%" + a + "%' or ngaySinh like  N'%" + a
					+ "%' or SDT like N'%" + a + "%' or email like N'%" + a + "%'";
			updata(khachHang_dao.findKhachHang(khachHang));
		} else
			JOptionPane.showMessageDialog(null, "Hãy Nhập Thông Tin Tìm Kiếm!");
	}

	private void updateTableData() {
		khachHang_dao = new KhachHang_dao();
		List<KhachHang> list = khachHang_dao.doctuBang();
		for (KhachHang kh : list) {
			String[] rowData = { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(), kh.getSDT(), kh.getEmail() };
			tblModel_DSKH.addRow(rowData);
		}
		tbl_DSKhachHang.setModel(tblModel_DSKH);
	}

	private void updata(List<KhachHang> list) {
		tblModel_DSKH.setRowCount(0);
		khachHang_dao = new KhachHang_dao();
		for (KhachHang kh : list) {
			String[] rowData = { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(), kh.getSDT(), kh.getEmail() + "" };
			tblModel_DSKH.addRow(rowData);
		}
		tbl_DSKhachHang.setModel(tblModel_DSKH);
	}

	public String taoMaKhachHang() {
		String maKhachHang;
		// Sử dụng Formatter để định dạng chuỗi thành "KHXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maKhachHang = formatter.format("KH%06d", khachHangCounter).toString();
			khachHangCounter++;
		} while (!timMaKH(maKhachHang));
		formatter.close();
		return maKhachHang;
	}

	private boolean validData() {
		String maKH = txtMaKH.getText();
		String hoTen = txtHoTen.getText();
		String ngaySinh = txtNgaySinh.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
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
				if (birthDate.plusYears(15).isAfter(now)) {
					JOptionPane.showMessageDialog(this, "Ngày sinh phải là người trên 14 tuổi");
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
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}