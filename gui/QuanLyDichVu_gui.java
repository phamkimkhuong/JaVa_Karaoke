package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Formatter;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DichVu_dao;
import dao.LoaiDichVu_dao;
import dao.LoaiPhong_dao;
import entity.DichVu;
import entity.LoaiDichVu;
import entity.LoaiPhong;

import javax.swing.Box;

public class QuanLyDichVu_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int dichVuCounter = 1;
	private DichVu_dao dichVu_dao;
	protected JPanel pnl_ContentPaneQLDV;
	private JTextField txtTenDV;
	private JComboBox cmbLoaiDV;
	private JTextField txtDonGia;
	private JButton btnThemDV;
	private JButton btnCapNhatDV;
	private JButton btnXoaDV;
	private JButton btnXoaTrangDV;
	private JButton btnLuuDV;
	private JComboBox cmbTimDV;
	private JButton btnTimDV;
	private JTextField txtTenLoaiDV;
	private DefaultTableModel tblModelLoaiDV;
	private JScrollPane scrLoaiDV;
	private JTable tblLoaiDV;
	private JButton btnCapNhatLoaiDV;
	private JButton btnXoaTrangLoaiDV;
	private JButton btnLuuLoaiDV;
	private JButton btnThemLoaiDV;
	private JButton btnXoaLoaiDV;
	private DefaultTableModel tblModelDV;
	private JScrollPane scrDV;
	private JTable tblDV;
	private Component verticalStrut;
	private JTextField txtDonViTinh;
	private JTextField txtSoLuong;
	private JTextField txtMaDV;
	private int edit;
	private JButton btnLamMoi;
	private JTextField txtTimDV;
	private String selectedValue;
	private LoaiDichVu_dao ldv_dao;
	private static boolean isEventListenerEnabled = true;

	/**
	 * Create the frame.
	 */
	public QuanLyDichVu_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPaneQLDV = new JPanel();
		pnl_ContentPaneQLDV.setBorder(new EmptyBorder(5, 5, 5, 5));
		ldv_dao = new LoaiDichVu_dao();
		/*
		 * (3)** Panel Quản Lí Dịch Vụ
		 */
		/*
		 * 
		 */
		Color color = new Color(197, 199, 199);
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(color);
		pnl_ContentPaneQLDV.setBackground(color);
		JLabel lblTieuDe = new JLabel("Quản Lí Dịch Vụ");
		lblTieuDe.setForeground(new Color(15, 102, 165));
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnlNorth.add(lblTieuDe);

		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(color);
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Thông Tin Dịch Vụ");
		pnlWest.setBorder(titledBorder);

		JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl1.setBackground(color);
		JLabel lblTenDV = new JLabel("Tên dịch vụ: ");
		txtTenDV = new JTextField(50);
		pnl1.add(lblTenDV);
		pnl1.add(txtTenDV);

		JPanel pnl2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl2.setBackground(color);
		JLabel lblLoaiDV = new JLabel("Loại dịch vụ: ");
		cmbLoaiDV = new JComboBox();
		cmbLoaiDV.setPreferredSize(new Dimension(450, 20));
		// cmbLoaiDV.setModel(new DefaultComboBoxModel(new String[] { "Đồ ăn", "Đồ
		// uống","Khai vị"}));
		cmbLoaiDV.setModel(getTenLoaiDichVu());
		/*
		 * cmbLoaiDV.addItemListener(new ItemListener() { public void
		 * itemStateChanged(ItemEvent e) { if (isEventListenerEnabled) { if
		 * (e.getStateChange() == ItemEvent.SELECTED) { String selectedValue =
		 * cmbLoaiDV.getSelectedItem().toString(); timDVTheoLoaiDV(selectedValue); } }
		 * 
		 * } });
		 */

		JLabel lblDonViTinh = new JLabel("Đơn vị tính: ");
		txtDonViTinh = new JTextField(50);
		pnl2.add(lblLoaiDV);
		pnl2.add(cmbLoaiDV);
		pnl2.add(lblDonViTinh);
		pnl2.add(txtDonViTinh);
		JPanel pnl3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl3.setBackground(color);
		JLabel lblSoLuong = new JLabel("Số lượng: ");
		txtSoLuong = new JTextField(50);
		JLabel lblDonGia = new JLabel("Đơn giá: ");
		txtDonGia = new JTextField(50);
		pnl3.add(lblSoLuong);
		pnl3.add(txtSoLuong);
		pnl3.add(lblDonGia);
		pnl3.add(txtDonGia);

		JPanel pnl4 = new JPanel();
		pnl4.setBackground(color);
		pnl4.add(btnThemDV = new JButton("Thêm"));
		btnThemDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themDichVu();
			}
		});
		Image imgAdd = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();
		btnThemDV.setIcon(new ImageIcon(imgAdd));
		pnl4.add(btnCapNhatDV = new JButton("Cập nhật"));
		btnCapNhatDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatDV();
			}
		});
		Image imgEdit = new ImageIcon(this.getClass().getResource("" + "/Edit.24.png")).getImage();
		btnCapNhatDV.setIcon(new ImageIcon(imgEdit));
		pnl4.add(btnXoaDV = new JButton("Xóa"));
		btnXoaDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaDV();
			}
		});
		Image imgExit = new ImageIcon(this.getClass().getResource("" + "/Button-Close.24.png")).getImage();
		btnXoaDV.setIcon(new ImageIcon(imgExit));
		pnl4.add(btnXoaTrangDV = new JButton("Xóa Trắng"));
		btnXoaTrangDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangDV();
			}
		});
		Image imgClean = new ImageIcon(this.getClass().getResource("" + "/bubbles.png")).getImage();
		btnXoaTrangDV.setIcon(new ImageIcon(imgClean));
		pnl4.add(btnLuuDV = new JButton("Lưu"));
		btnLuuDV.setEnabled(false);
		btnLuuDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luuDichVuVaoDS();
			}
		});
		pnl4.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));
		btnLamMoi.setBounds(861, 176, 121, 31);
		Image imgSave = new ImageIcon(this.getClass().getResource("" + "/Save.24.png")).getImage();
		btnLuuDV.setIcon(new ImageIcon(imgSave));

		JPanel pnl5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl5.setBackground(color);
		JLabel lblTimDV = new JLabel("Tìm dịch vụ: ");

		txtTimDV = new JTextField(25);
		pnl5.add(lblTimDV);
		pnl5.add(txtTimDV);
		pnl5.add(btnTimDV = new JButton("Tìm"));
		btnTimDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemDichVu();
			}
		});
		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTimDV.setIcon(new ImageIcon(imgTim));
		lblDonGia.setPreferredSize(lblLoaiDV.getPreferredSize());
		JPanel pnl6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl6.setBackground(color);
		JLabel lblMaDV = new JLabel("Mã dịch vụ: ");
		txtMaDV = new JTextField(50);
		txtMaDV.setEditable(false);
		pnl6.add(lblMaDV);
		pnl6.add(txtMaDV);
		pnlWest.add(pnl1);
		pnlWest.add(pnl2);
		pnlWest.add(pnl3);
		pnlWest.add(pnl6);
		pnlWest.add(pnl4);
		pnlWest.add(pnl5);

		lblSoLuong.setPreferredSize(lblLoaiDV.getPreferredSize());
		lblDonGia.setPreferredSize(lblLoaiDV.getPreferredSize());
		lblDonViTinh.setPreferredSize(lblLoaiDV.getPreferredSize());
		lblMaDV.setPreferredSize(lblLoaiDV.getPreferredSize());
		// South
		JPanel pnlSouth = new JPanel();
		pnlSouth.setPreferredSize(new Dimension(10, 350));
		pnlSouth.setBackground(color);
		LineBorder lineBorder1 = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder1 = new TitledBorder(lineBorder1, "Danh Sách Dịch Vụ");
		pnlSouth.setBorder(titledBorder1);

		String headersDV[] = "Mã dịch vụ;Tên dịch vụ;Loại dịch vụ;Đơn vị tính;Số lượng;Đơn giá".split(";");
		tblModelDV = new DefaultTableModel(headersDV, 0);
		scrDV = new JScrollPane();
		scrDV.setViewportView(tblDV = new JTable(tblModelDV));
		pnlSouth.setLayout(new BorderLayout(0, 0));
		pnlSouth.add(scrDV);
		// updateTableData();
		pnl_ContentPaneQLDV.setLayout(new BorderLayout(0, 0));
		pnl_ContentPaneQLDV.add(pnlWest, BorderLayout.CENTER);
		pnl_ContentPaneQLDV.add(pnlNorth, BorderLayout.NORTH);
		pnl_ContentPaneQLDV.add(pnlSouth, BorderLayout.SOUTH);
		dichVu_dao = new DichVu_dao();
		updateTableData();
		setContentPane(pnl_ContentPaneQLDV);

	}

	protected void timDVTheoLoaiDV(String selectedValue) {
		String loaiDV = " tenLoai = N'" + selectedValue + "'";
		updateTableData();
	}

	private void xoaTrangDV() {
		// TODO Auto-generated method stub
		txtMaDV.setText("");
		txtTenDV.setText("");
		txtDonViTinh.setText("");
		txtSoLuong.setText("");
		txtDonGia.setText("");
		txtMaDV.requestFocus();
	}

	protected void themDichVu() {
		// TODO Auto-generated method stub
		edit = 1;
		if (btnThemDV.getText().equalsIgnoreCase("Thêm")) {
			xoaTrangDV();
			// vô hiệu hóa button cập nhật và button xóa
			btnCapNhatDV.setEnabled(false);
			btnThemDV.setText("Hủy");
			// Mở khóa Button lưu và txtMaPhong
			btnLuuDV.setEnabled(true);
			btnXoaDV.setEnabled(false);
			// txtMaKH.setEnabled(false);
			// đặt con trỏ chuột vào ô mã phòng
			txtMaDV.requestFocus();
		} else if (btnThemDV.getText().equalsIgnoreCase("Hủy")) {
			edit = 0;
			btnCapNhatDV.setEnabled(true);
			btnXoaDV.setEnabled(true);
			btnLuuDV.setEnabled(false);
			btnThemDV.setText("Thêm");
			xoaTrangDV();
		}
	}

	private boolean timMaDV(String maDV) {
		// TODO Auto-generated method stub
		maDV = maDV.toUpperCase();
		List<DichVu> list = dichVu_dao.getAllDichVu();
		for (DichVu dv : list) {
			if (dv.getMaDV().contains(maDV))
				return false;
		}
		return true;
	}

	protected void luuDichVuVaoDS() {
		// TODO Auto-generated method stub
		String maDV = txtMaDV.getText();
		String tenDV = txtTenDV.getText();
		String loaiDV = cmbLoaiDV.getSelectedItem().toString();
		List<LoaiDichVu> dsldv = ldv_dao.getAllLoaiDichVu();
		String loaiDichVu = "";
		for (LoaiDichVu lDV : dsldv) {
			if(lDV.getTenLoai().trim().equalsIgnoreCase(loaiDV.trim())) {
				loaiDV=lDV.getMaLoai();
			}
		}
		
		String donViTinh = txtDonViTinh.getText();
		String soLuong = txtSoLuong.getText();
		String donGia = txtDonGia.getText();
		/*
		 * int soLuong = Integer.parseInt(txtSoLuong.getText()); double donGia =
		 * Double.parseDouble(txtDonGia.getText());
		 */
		if (tenDV.equals("") || donViTinh.equals("") || soLuong.equals("") || donGia.equals("")) {
			JOptionPane.showMessageDialog(this, "Thông tin nhập không được để trống");
		} else {
			if (edit == 1) {
				if (validData()) {
					int soLuong1 = Integer.parseInt(txtSoLuong.getText());
					double donGia1 = Double.parseDouble(txtDonGia.getText());
					String ma = taoMaDichVu();
					DichVu dv = new DichVu(ma, tenDV, loaiDV, donViTinh, soLuong1, donGia1);
					// DichVu dv = new DichVu();
					dichVu_dao.addDichVu(dv);
					lamMoiBang();
					JOptionPane.showMessageDialog(this, "Tạo mới thành công");
					xoaTrangDV();
					lamMoiBang();
					btnCapNhatDV.setEnabled(true);
					btnXoaDV.setEnabled(true);
					btnThemDV.setText("Thêm");
				}
			} else if (edit == 2) {
				int soLuong1 = Integer.parseInt(txtSoLuong.getText());
				double donGia1 = Double.parseDouble(txtDonGia.getText());
				DichVu dv = new DichVu(maDV, tenDV, loaiDV, donViTinh, soLuong1, donGia1);
				// DichVu dv = new DichVu();
				dichVu_dao.updateDichVu(dv);
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				xoaTrangDV();
				lamMoiBang();
				btnThemDV.setEnabled(true);
				btnXoaDV.setEnabled(true);
				btnLuuDV.setEnabled(false);
				btnCapNhatDV.setText("Cập Nhật");

			}
		}
	}

	private void updateTableData() {
		dichVu_dao = new DichVu_dao();
		List<DichVu> list = dichVu_dao.getAllDichVu();
		List<LoaiDichVu> dsldv = ldv_dao.getAllLoaiDichVu();
		for(DichVu dv : list) {
			String loaiDichVu = "";
			for (LoaiDichVu loaiDV : dsldv) {
				if(loaiDV.getMaLoai().trim().equalsIgnoreCase(dv.getLoaiDichVu().trim())) {
					loaiDichVu=loaiDV.getTenLoai();
				}
			}
			String [] rowData = {dv.getMaDV(),dv.getTenDV(),loaiDichVu,
					dv.getDonViTinh(),dv.getSoLuong()+"",dv.getDonGia()+""};
			tblModelDV.addRow(rowData);
		}
		tblDV.setModel(tblModelDV);
	}

	protected void lamMoiBang() {
		tblModelDV.setRowCount(0);
		updateTableData();
	}

	protected void xoaDV() {
		// Kiểm tra hàng đẫ được chọn hay không
		int n = tblDV.getSelectedRow();
		if (n != -1) {
			if (!tblDV.getModel().getValueAt(n, 1).toString().equalsIgnoreCase("")) {
				int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?");
				if (tb == JOptionPane.YES_OPTION) {
					String maDV = tblDV.getModel().getValueAt(n, 0).toString();
					dichVu_dao.deleteDichVu(maDV);
					lamMoiBang();
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn dịch vụ cần xóa!");

	}

	private void capNhatDV() {
		edit = 2;
		int row = tblDV.getSelectedRow();
		if (row >= 0) {
			String maDV = txtMaDV.getText();
			String tenDV = txtTenDV.getText();
			String loaiDV = cmbLoaiDV.getSelectedItem().toString();
			String donViTinh = txtDonViTinh.getText();
			String soLuong = txtSoLuong.getText();
			String donGia = txtDonGia.getText();
			/*
			 * int soLuong = Integer.parseInt(txtSoLuong.getText()); double donGia =
			 * Double.parseDouble(txtDonGia.getText());
			 */
			if (btnCapNhatDV.getText().equalsIgnoreCase("Hủy")) {
				btnCapNhatDV.setText("Cập Nhật");
				btnThemDV.setEnabled(true);
				btnXoaDV.setEnabled(true);
				btnLuuDV.setEnabled(false);
			} else if (row != -1) {
				edit = 2;
				if (btnCapNhatDV.getText().equalsIgnoreCase("Cập Nhật")) {
					btnCapNhatDV.setText("Hủy");
					btnLuuDV.setEnabled(true);
					btnThemDV.setEnabled(false);
					btnXoaDV.setEnabled(false);
					// Đưa dữ liệu từ bảng vào textField để cập nhật
					txtMaDV.setText(tblDV.getModel().getValueAt(row, 0).toString());
					txtTenDV.setText(tblDV.getModel().getValueAt(row, 1).toString());
					cmbLoaiDV.setSelectedItem(tblDV.getModel().getValueAt(row, 2).toString());
					txtDonViTinh.setText(tblDV.getModel().getValueAt(row, 3).toString());
					txtSoLuong.setText(tblDV.getModel().getValueAt(row, 4).toString());
					txtDonGia.setText(tblDV.getModel().getValueAt(row, 5).toString());
				}
			} else {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật thông tin không?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					int soLuong1 = Integer.parseInt(txtSoLuong.getText());
					double donGia1 = Double.parseDouble(txtDonGia.getText());
					DichVu dv = new DichVu(maDV, tenDV, loaiDV, donViTinh, soLuong1, donGia1);
					if (dichVu_dao.updateDichVu(dv)) {
						tblModelDV.setValueAt(maDV, row, 0);
						tblModelDV.setValueAt(tenDV, row, 1);
						tblModelDV.setValueAt(loaiDV, row, 2);
						tblModelDV.setValueAt(donViTinh, row, 3);
						tblModelDV.setValueAt(soLuong1, row, 4);
						tblModelDV.setValueAt(donGia1, row, 5);
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						xoaTrangDV();
						btnCapNhatDV.setText("Cập Nhật");
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng trong bảng để cập nhật thông tin dịch vụ.");
		}
	}

	private void updata(List<DichVu> list) {
		tblModelDV.setRowCount(0);
		dichVu_dao = new DichVu_dao();
		for (DichVu dv : list) {
			String[] rowData = { dv.getMaDV(), dv.getTenDV(), dv.getLoaiDichVu(), dv.getDonViTinh(),
					dv.getSoLuong() + "", dv.getDonGia() + "" };
			tblModelDV.addRow(rowData);
		}
		tblDV.setModel(tblModelDV);
	}

	protected void timKiemDichVu() {
		String a = txtTimDV.getText();
		String dichVu = "";
		if (!a.equals("")) {
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			dichVu = "maDV like N'%" + a + "%' or tenDV like N'%" + a + "%' or loaiDV like  N'%" + a
					+ "%' or donViTinh like N'%" + a + "%' or soLuong like N'%" + a + "%' or donGia like N'%" + a
					+ "%'";
			updata(dichVu_dao.findDichVu(dichVu));
		} else
			JOptionPane.showMessageDialog(null, "Hãy Nhập Thông Tin Tìm Kiếm!");
	}

	public String taoMaDichVu() {
		String maDichVu;
		// Sử dụng Formatter để định dạng chuỗi thành "NVXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maDichVu = formatter.format("DV%03d", dichVuCounter).toString();
			dichVuCounter++;
		} while (!timMaDV(maDichVu));
		formatter.close();
		return maDichVu;
	}

	private boolean validData() {
		String maDV = txtMaDV.getText();
		String tenDV = txtTenDV.getText();
		String loaiDV = cmbLoaiDV.getSelectedItem().toString();
		String donViTinh = txtDonViTinh.getText();
		String soLuong = txtSoLuong.getText();
		String donGia = txtDonGia.getText();
//		Họ tên không được để trống, có thể gồm nhiều từ ngăn cách bởi khoảng 
//		trắng. Không chứa ký số hoặc các ký tự đặc biệt khác, ngoại trừ ký tự 
		if (!(tenDV.length() > 0 && tenDV.matches("[\\p{L}' ]+"))) {
			JOptionPane.showMessageDialog(this, "Tên dịch vụ không được chứa số và kí tự đặc biệt");
			return false;
		}
		if (!(donViTinh.length() > 0 && donViTinh.matches("[\\p{L}' ]+"))) {
			JOptionPane.showMessageDialog(this, "Đơn vị tính không được chứa số và kí tự đặc biệt");
			return false;
		}
		if (!(soLuong.length() > 0 && soLuong.matches("\\d+"))) {
			JOptionPane.showMessageDialog(this, "Số lượng phải là số và > 0");
			return false;
		}
		if (!(donGia.length() > 0 && donGia.matches("\\d+"))) {
			JOptionPane.showMessageDialog(this, "Đơn giá phải là số và > 0");
			return false;
		}
		return true;
	}

	protected static DefaultComboBoxModel<String> getTenLoaiDichVu() {
		DefaultComboBoxModel<String> cmbModel = new DefaultComboBoxModel<String>();
		LoaiDichVu_dao ldv_dao = new LoaiDichVu_dao();
		List<LoaiDichVu> dslp = ldv_dao.getAllLoaiDichVu();
		LoaiDichVu ldv = new LoaiDichVu();
		for (LoaiDichVu loaiDV : dslp) {
			cmbModel.addElement(loaiDV.getTenLoai());
		}
		return cmbModel;
	}
}
