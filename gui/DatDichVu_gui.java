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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import dao.ChiTietPhieuDatPhong_dao;
import dao.ChiTietPhieuDichVu_dao;
import dao.DichVu_dao;
import dao.PhieuDatDichVu_dao;
import dao.PhieuDatPhong_dao;
import dao.Phong_dao;
import entity.ChiTietPhieuDatPhong;
import entity.ChiTietPhieuDichVu;
import entity.DichVu;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatDichVu;
import entity.PhieuDatPhong;
import entity.Phong;
import gui.PhieuDatDichVu_gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DatDichVu_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_ContentPaneDDV;
	private JPanel pnl_DanhSachDichVu;
	private JTable tblDSDV;
	private JTable tbl_DichVuDaDat;
	private JTextField txtTimPhieuDatDV;
	private JButton btnTimPhieuDichVu;
	private JTextField txtTimDV;
	private JButton btnTimDV;
	private JButton btnInPhieuDatDV;
	private JButton btnHuyDichVu;
	private JButton btnXemPhieuDatDV;
	private JButton btnDatDichVu;
	private JButton btnLapPhieuDat;
	private DichVu_dao dichVu_dao;
	private PhieuDatDichVu_dao phieuDatDichVu_dao;
	private DefaultTableModel tblModelDSDV;
	private JScrollPane scrDSDV;
	private DefaultTableModel tblModelDVDaDat;
	private JScrollPane scrDVDaDat;
	// private int STT;
	private int selectedRow;
	private JComboBox cmbPhong;
	private JTextField txtTongTien;
	private Object maNV;
	private int edit;
	private int newQuantity;
	private String maDV;
	private String maPhong;
	private String tenDV;
	private String donGia;
	private int quantity;
	private List<PhieuDatPhong> pdp;
	private PhieuDatPhong_dao pdp_dao;
	private static int phieuDichVuCounter = 1;
	private int soLuong;
	private String donViTinh;
	private String loaiDV;
	private ChiTietPhieuDatPhong_dao ct_dao;

	/**
	 * Create the frame.
	 */
	public DatDichVu_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPaneDDV = new JPanel();
		pnl_ContentPaneDDV.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPaneDDV.setLayout(new BorderLayout());
		
		pdp_dao = new PhieuDatPhong_dao();
		ct_dao = new ChiTietPhieuDatPhong_dao();
		pdp = pdp_dao.getAllPhieuDatPhong();
		/*
		 * Crette Panel danh sách phòng Panel tiêu đề, panel center, panel chức năng
		 */
		pnl_DanhSachDichVu = new JPanel();
		pnl_ContentPaneDDV.add(pnl_DanhSachDichVu, BorderLayout.CENTER);
		pnl_DanhSachDichVu.setLayout(new BorderLayout(0, 0));
		Color color = new Color(197,199,199);
		pnl_ContentPaneDDV.setBackground(color);
		JPanel pnl_TieuDe = new JPanel();
		pnl_TieuDe.setBackground(color);
		pnl_DanhSachDichVu.add(pnl_TieuDe, BorderLayout.NORTH);

		JLabel lblDSDichVu = new JLabel("Đặt Dịch Vụ");
		lblDSDichVu.setForeground(new Color(15,102,165));
		lblDSDichVu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_TieuDe.add(lblDSDichVu);

		JPanel pnl_CenTer = new JPanel();
		pnl_DanhSachDichVu.add(pnl_CenTer, BorderLayout.CENTER);
		pnl_CenTer.setLayout(new BoxLayout(pnl_CenTer, BoxLayout.Y_AXIS));

		JPanel pnl_DSDV = new JPanel();
		pnl_CenTer.add(pnl_DSDV);
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Danh Sách Dịch Vụ");
		pnl_CenTer.setBorder(titledBorder);
		pnl_DSDV.setLayout(new BorderLayout(0, 0));
		pnl_CenTer.setBackground(color);
		String headersDV[] = "Mã dịch vụ;Tên dịch vụ;Loại dịch vụ;Đơn vị tính;Số lượng;Đơn giá".split(";");
		tblModelDSDV = new DefaultTableModel(headersDV, 0);
		scrDSDV = new JScrollPane();
		scrDSDV.setViewportView(tblDSDV = new JTable(tblModelDSDV));
		scrDSDV.setPreferredSize(new Dimension(1050, 400));
		pnl_DSDV.setLayout(new BorderLayout(0, 0));
		pnl_DSDV.add(scrDSDV);
		updateTableData();


		JPanel pnl_ThaoTac = new JPanel();
		pnl_ThaoTac.setPreferredSize(new Dimension(10, 50));
		pnl_CenTer.add(pnl_ThaoTac);
		pnl_ThaoTac.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel pnl_DichVuDaDat = new JPanel();
		pnl_DichVuDaDat.setBackground(color);
		pnl_DichVuDaDat.setPreferredSize(new Dimension(10, 500));
		LineBorder lineBorder1 = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder1 = new TitledBorder(lineBorder1, "Danh Sách Dịch Vụ Đã Đặt");
		pnl_DichVuDaDat.setBorder(titledBorder1);

		pnl_CenTer.add(pnl_DichVuDaDat);
		pnl_DichVuDaDat.setLayout(new BorderLayout(0, 0));

		String headersDVDaDat[] = "Mã phòng;Mã dịch vụ;Tên dịch vụ;Số lượng;Đơn giá;Ngày đặt".split(";");
		tblModelDVDaDat = new DefaultTableModel(headersDVDaDat, 0);
		scrDVDaDat = new JScrollPane();
		scrDVDaDat.setViewportView(tbl_DichVuDaDat = new JTable(tblModelDVDaDat));
		scrDVDaDat.setPreferredSize(new Dimension(1050, 400));
		pnl_DichVuDaDat.setLayout(new BorderLayout(0, 0));
		pnl_DichVuDaDat.add(scrDVDaDat);

		/*
		 * Panel Control Button Tìm phong theo mã(số phòng) Button Tìm phòng theo Loại
		 * phòng
		 */
		/*
		 * Panel Control Button Tìm phong theo mã(số phòng) Button Tìm phòng theo Loại
		 * phòng
		 */
		JPanel pnlControlPane = new JPanel();
		pnlControlPane.setBackground(color);
		pnlControlPane.setPreferredSize(new Dimension(250, 10));
		pnl_DanhSachDichVu.add(pnlControlPane, BorderLayout.WEST);
		pnlControlPane.setLayout(null);
		LineBorder lineBorder2 = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder2 = new TitledBorder(lineBorder2, "Chức năng");
		pnlControlPane.setBorder(titledBorder2);

		JLabel lblTimDichVu = new JLabel("Tìm dịch vụ:");
		lblTimDichVu.setBounds(10, 20, 112, 14);
		pnlControlPane.add(lblTimDichVu);

		txtTimDV = new JTextField();
		txtTimDV.setBounds(10, 40, 120, 26);
		pnlControlPane.add(txtTimDV);

		btnTimDV = new JButton("Tìm");
		btnTimDV.setBounds(140, 40, 100, 23);
		btnTimDV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemDichVu();
			}
		});
		Image imgTK = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTimDV.setIcon(new ImageIcon(imgTK));
		pnlControlPane.add(btnTimDV);

		btnHuyDichVu = new JButton("Hủy Dịch Vụ");
		btnHuyDichVu.setBounds(10, 327, 192, 32);
		btnHuyDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyDichVu();
			}
		});
		Image imgExit = new ImageIcon(this.getClass().getResource("" + "/Button-Close.24.png")).getImage();
		btnHuyDichVu.setIcon(new ImageIcon(imgExit));
		pnlControlPane.add(btnHuyDichVu);

		btnLapPhieuDat = new JButton("Lập Phiếu Đặt");
		btnLapPhieuDat.setBounds(10, 384, 192, 32);
		btnLapPhieuDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lapPhieuDat();
			}
		});
		Image imgAdd = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();
		btnLapPhieuDat.setIcon(new ImageIcon(imgAdd));
		pnlControlPane.add(btnLapPhieuDat);

		btnDatDichVu = new JButton("Đặt Dịch Vụ");
		btnDatDichVu.setBounds(10, 267, 192, 32);
		btnDatDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datDichVu();
			}
		});
		Image imgDatDV = new ImageIcon(this.getClass().getResource("" + "/Ordering.24.png")).getImage();
		btnDatDichVu.setIcon(new ImageIcon(imgDatDV));
		pnlControlPane.add(btnDatDichVu);
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();

		JLabel lblChonPhong = new JLabel("Phòng đặt dịch vụ:");
		lblChonPhong.setBounds(10, 90, 112, 14);
		pnlControlPane.add(lblChonPhong);

		cmbPhong = new JComboBox();
		cmbPhong.setBounds(10, 112, 230, 21);
		DefaultComboBoxModel<String> de = new DefaultComboBoxModel<String>();
		Phong_dao p_dao = new Phong_dao();
		List<Phong> dsPhong = p_dao.selectALL();
		for (Phong p : dsPhong) {
			if (p.getTrangThai().equals("Đang sử dụng")) {
				de.addElement(p.getTenPhong());
			}
		}
		cmbPhong.setModel(de);
		pnlControlPane.add(cmbPhong);

		JLabel lblTongTien = new JLabel("Tổng Tiền: ");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongTien.setBounds(10, 621, 150, 45);
		pnlControlPane.add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setBounds(126, 621, 114, 45);
		txtTongTien.setEditable(false);
		pnlControlPane.add(txtTongTien);
		txtTongTien.setColumns(10);

		setContentPane(pnl_ContentPaneDDV);
	}

	private void updateTableData() {
		dichVu_dao = new DichVu_dao();
		List<DichVu> list = dichVu_dao.getAllDichVu();
		
		for (DichVu dv : list) {
			String[] rowData = { dv.getMaDV(), dv.getTenDV(), dv.getLoaiDichVu(), dv.getDonViTinh(),
					dv.getSoLuong() + "", dv.getDonGia() + "" };
			tblModelDSDV.addRow(rowData);
		}
		tblDSDV.setModel(tblModelDSDV);
	}

	/*protected void huyDichVu() {
		// Kiểm tra hàng đẫ được chọn hay không
		int selectedRow = tbl_DichVuDaDat.getSelectedRow();
		if (selectedRow != -1) {
			DefaultTableModel model = (DefaultTableModel) tbl_DichVuDaDat.getModel();
			String maDV = tbl_DichVuDaDat.getValueAt(selectedRow, 1).toString();
			String soLuongHuy = tbl_DichVuDaDat.getValueAt(selectedRow, 3).toString();
			// Yêu cầu người dùng nhập số lượng muốn hủy
	        String soLuongNhap = JOptionPane.showInputDialog(null, "Nhập số lượng muốn hủy:", "Hủy Dịch Vụ", JOptionPane.QUESTION_MESSAGE);

	        // Kiểm tra xem người dùng đã nhập hay chưa
	        if (soLuongNhap != null && !soLuongNhap.isEmpty()) {
	            int soLuongHuyNhap = Integer.parseInt(soLuongNhap);

	            // Kiểm tra số lượng nhập có hợp lệ hay không
	            if (soLuongHuyNhap <= 0) {
	                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ (lớn hơn 0).");
	                return;
	            }
	        }
			// Update the quantity in the inventory
			int rowToUpdate = -1;
			for (int i = 0; i < tblDSDV.getRowCount(); i++) {
				if (tblDSDV.getValueAt(i, 0).toString().equals(maDV)) {
					rowToUpdate = i;
					break;
				}
			}
			if (rowToUpdate != -1) {
				int currentQuantity = Integer.parseInt(tblDSDV.getValueAt(rowToUpdate, 4).toString());
				int newQuantity = currentQuantity + Integer.parseInt(soLuongHuy);
				tblDSDV.setValueAt(newQuantity, rowToUpdate, 4);
			}
			model.removeRow(selectedRow);
			TongTien();
		} else {
			JOptionPane.showMessageDialog(null, "Hãy chọn dịch vụ cần hủy!");
		}
	}*/
	protected void huyDichVu() {
	    int selectedRow = tbl_DichVuDaDat.getSelectedRow();
	    if (selectedRow != -1) {
	        DefaultTableModel model = (DefaultTableModel) tbl_DichVuDaDat.getModel();
	        String maDV = tbl_DichVuDaDat.getValueAt(selectedRow, 1).toString();
	        String soLuongHuy = tbl_DichVuDaDat.getValueAt(selectedRow, 3).toString();

	        String soLuongNhap = JOptionPane.showInputDialog(null, "Nhập số lượng muốn hủy:", "Hủy Dịch Vụ", JOptionPane.QUESTION_MESSAGE);

	        // Kiểm tra xem người dùng đã nhập hay chưa
	        if (soLuongNhap != null && !soLuongNhap.isEmpty()) {
	            int soLuongHuyNhap = Integer.parseInt(soLuongNhap);

	            // Kiểm tra số lượng nhập có hợp lệ hay không
	            if (soLuongHuyNhap <= 0) {
	                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ (lớn hơn 0).");
	                return;
	            }

	            // Kiểm tra số lượng nhập có nhỏ hơn hoặc bằng số lượng đã đặt ban đầu hay không
	            int soLuongDaDat = (int) Double.parseDouble(tbl_DichVuDaDat.getValueAt(selectedRow, 3).toString());
	            if (soLuongHuyNhap <= soLuongDaDat) {
	                int rowToUpdate = -1;
	                for (int i = 0; i < tblDSDV.getRowCount(); i++) {
	                    if (tblDSDV.getValueAt(i, 0).toString().equals(maDV)) {
	                        rowToUpdate = i;
	                        break;
	                    }
	                }

	                if (rowToUpdate != -1) {
	                    int currentQuantity = Integer.parseInt(tblDSDV.getValueAt(rowToUpdate, 4).toString());
	                    int newQuantity = currentQuantity + soLuongHuyNhap;
	                    tblDSDV.setValueAt(newQuantity, rowToUpdate, 4);
	                }

	                model.removeRow(selectedRow);
	                TongTien();
	            } else {
	                JOptionPane.showMessageDialog(null, "Số lượng hủy không được lớn hơn số lượng đã đặt.");
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Hãy chọn dịch vụ cần hủy!");
	    }
	}
	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		tblModelDVDaDat.setRowCount(0);
		// updateTableData();
	}

	private void updata(List<DichVu> list) {
		tblModelDSDV.setRowCount(0);
		dichVu_dao = new DichVu_dao();
		for (DichVu dv : list) {
			String[] rowData = { dv.getMaDV(), dv.getTenDV(), dv.getLoaiDichVu(), dv.getDonViTinh(),
					dv.getSoLuong() + "", dv.getDonGia() + "" };
			tblModelDSDV.addRow(rowData);
		}
		tblDSDV.setModel(tblModelDSDV);
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

	private void TongTien() {
		double TongTien = 0;

		for (int i = 0; i < tbl_DichVuDaDat.getRowCount(); i++) {
			int soLuong = Integer.parseInt(tbl_DichVuDaDat.getValueAt(i, 3).toString());
			double giaBan = Double.parseDouble(tbl_DichVuDaDat.getValueAt(i, 4).toString());
			TongTien += soLuong * giaBan;
		}

		txtTongTien.setText(String.valueOf(TongTien));
	}

	private boolean timMaPhieu(String maPhieu) {
		// TODO Auto-generated method stub
		maPhieu = maPhieu.toUpperCase();
		List<PhieuDatDichVu> list = phieuDatDichVu_dao.getAllPhieuDatDichVu();
		for (PhieuDatDichVu pddv : list) {
			if (pddv.getMaPhieu().contains(maPhieu))
				return false;
		}
		return true;
	}
	protected void datDichVu() {
	    int selectedRow = tblDSDV.getSelectedRow();
	    Date currentDate = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String ngayDat = dateFormat.format(currentDate);

	    if (selectedRow != -1) {
	        DefaultTableModel modelDSDV = (DefaultTableModel) tblDSDV.getModel();
	        DefaultTableModel modelDaDat = (DefaultTableModel) tbl_DichVuDaDat.getModel();

	        maDV = tblDSDV.getValueAt(selectedRow, 0).toString();
	        maPhong = cmbPhong.getSelectedItem().toString();
	        tenDV = tblDSDV.getValueAt(selectedRow, 1).toString();
	        loaiDV = tblDSDV.getValueAt(selectedRow, 2).toString();
	        donViTinh = tblDSDV.getValueAt(selectedRow, 3).toString();
	        donGia = tblDSDV.getValueAt(selectedRow, 5).toString();
	        int soLuongTonKho = Integer.parseInt(tblDSDV.getValueAt(selectedRow, 4).toString());

	        boolean daDat = false;
	        int existingRow = -1;

	        // Kiểm tra xem dịch vụ đã có trong đơn đặt hàng chưa
	        for (int i = 0; i < modelDaDat.getRowCount(); i++) {
	            if (modelDaDat.getValueAt(i, 1).equals(maDV)) {
	                existingRow = i;
	                daDat = true;
	                break;
	            }
	        }
	        if (daDat) {
	            String quantityString = JOptionPane.showInputDialog("Nhập số lượng:");
	            quantity = 0;

	            try {
	                quantity = Integer.parseInt(quantityString);
	            } catch (NumberFormatException ex) {
	                ex.printStackTrace();
	            }
	            if (quantity > soLuongTonKho) {
	                JOptionPane.showMessageDialog(this, "Số lượng đặt vượt quá số lượng tồn kho.");
	            } else {
	                newQuantity = soLuongTonKho - quantity;

	                // Cập nhật số lượng trong bảng hiển thị (tblDSDV)
	                tblDSDV.setValueAt(newQuantity, selectedRow, 4);

	                // Cập nhật số lượng trong bảng dữ liệu (tbl_DichVuDaDat)
	                int currentQuantity = (int) modelDaDat.getValueAt(existingRow, 3);
	                int newQuantity = currentQuantity + quantity;
	                modelDaDat.setValueAt(newQuantity, existingRow, 3);
	            }
	        } else {
	            String quantityString = JOptionPane.showInputDialog("Nhập số lượng:");
	            quantity = 0;

	            try {
	                quantity = Integer.parseInt(quantityString);
	            } catch (NumberFormatException ex) {
	                ex.printStackTrace();
	            }

	            if (quantity > soLuongTonKho) {
	                JOptionPane.showMessageDialog(this, "Số lượng đặt vượt quá số lượng tồn kho.");
	            } else {
	                newQuantity = soLuongTonKho - quantity;

	                // Cập nhật số lượng trong bảng hiển thị (tblDSDV)
	                tblDSDV.setValueAt(newQuantity, selectedRow, 4);
	                DichVu dv = new DichVu(maDV, tenDV, loaiDV, donViTinh , newQuantity , Double.valueOf(donGia));
	                // Thêm một dòng mới vào bảng dữ liệu (tbl_DichVuDaDat)
	                DichVu_dao dv_dao = new DichVu_dao();
	                dv_dao.updateDichVu(dv);
	                Object[] rowData = { maPhong, maDV, tenDV, quantity, donGia, ngayDat };
	                modelDaDat.addRow(rowData);
	            }
	        }
	        TongTien();
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dịch vụ từ danh sách trước khi đặt.");
	    }
	}
	
	protected void lapPhieuDat() {
		phieuDatDichVu_dao = new PhieuDatDichVu_dao();
	    Phong_dao p_dao = new Phong_dao();
	    DefaultTableModel model = (DefaultTableModel) tbl_DichVuDaDat.getModel();

	    // Duyệt qua từng dịch vụ đã đặt trong bảng DichVuDaDat
	    for (int i = 0; i < model.getRowCount(); i++) {
	        String ma = taoMaPhieu();
	        String maDV = model.getValueAt(i, 1).toString();
	        String tenDV = model.getValueAt(i, 2).toString();
	        double donGia = Double.parseDouble(model.getValueAt(i, 4).toString());
	        int quantity = Integer.parseInt(model.getValueAt(i, 3).toString());

	        Phong p = p_dao.findByID(cmbPhong.getSelectedItem().toString());
	        KhachHang kh = new KhachHang();

	        // Tìm khách hàng tương ứng với phòng
	        for (PhieuDatPhong phieuDP : pdp) {
	        	ChiTietPhieuDatPhong ct = ct_dao.findPhieuDatPhongTheoMaPhieu(phieuDP.getMaPhieu());
	            if (cmbPhong.getSelectedItem().toString().equals(ct.getPhong().getTenPhong()) && phieuDP.getTrangThai().equals("Đang sử dụng")) {
	                kh = phieuDP.getKhachHang();
	            }
	        }
	        // Tạo mới một phiếu đặt dịch vụ và chi tiết phiếu dịch vụ cho mỗi dịch vụ
	        PhieuDatDichVu pddv = new PhieuDatDichVu(ma, p, kh, LocalDate.now(),"Chưa thanh toán");
	        ChiTietPhieuDichVu_dao ct_dao = new ChiTietPhieuDichVu_dao();
	        DichVu DV = new DichVu(maDV, tenDV, donGia);
	        ChiTietPhieuDichVu ct = new ChiTietPhieuDichVu(DV, quantity, pddv);
	        
	        // Thêm phiếu đặt dịch vụ vào danh sách
	        phieuDatDichVu_dao.addPhieuDatDichVu(pddv);
	        ct_dao.addCTPDV(ct);
	    }

	    // Làm mới bảng và thông báo thành công
	    lamMoiBang();
	    txtTongTien.setText("");
	    JOptionPane.showMessageDialog(this, "Lập thành công");
	}
	private String taoMaPhieu() {
		String maPhieu;
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maPhieu = formatter.format("PDV%06d", phieuDichVuCounter).toString();
			phieuDichVuCounter++;
		} while (!timMaPhieu(maPhieu));
		// TODO Auto-generated method stub
		formatter.close();
		return maPhieu;
	}
}
