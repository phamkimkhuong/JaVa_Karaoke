package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietPhieuDichVu_dao;
import dao.DichVu_dao;
import dao.PhieuDatDichVu_dao;
import entity.ChiTietPhieuDichVu;
import entity.DichVu;
import entity.PhieuDatDichVu;
import gui.DatDichVu_gui;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.border.EtchedBorder;

public class PhieuDatDichVu_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_ContentPanePDDV;
	private JPanel pnl_PhieuDatDichVu;
	private JLabel lblTieuDe2;
	private JTable tbltable;
	private JScrollPane scrPhieuDatDV;
	private DefaultTableModel tblModelPhieuDatDV;
	private JTable tblPhieuDatDV;
	private DichVu_dao dichVu_dao;
	private PhieuDatDichVu_dao phieuDatDichVu_dao;
	private ChiTietPhieuDichVu_dao chiTietPhieuDichVu_dao;
	private DefaultTableModel tblModelCTPhieuDatDV;
	private JScrollPane scrCTPhieuDatDV;
	private JTable tblCTPhieuDatDV;
	private List<PhieuDatDichVu> list;
	private String maKH;
	private JTextField txtTimPhieu;
	private DateTimeFormatter formatter;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public PhieuDatDichVu_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPanePDDV = new JPanel();
		pnl_ContentPanePDDV.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_ContentPanePDDV.setLayout(new BorderLayout());
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		/*
		 * (2)** Panel Phieu Đặt Dịch Vụ
		 */
		pnl_PhieuDatDichVu = new JPanel();
		pnl_ContentPanePDDV.add(pnl_PhieuDatDichVu, BorderLayout.CENTER);
		pnl_PhieuDatDichVu.setLayout(new BorderLayout(0, 0));
		Color color = new Color(197,199,199);
		JPanel pnl_TieuDe = new JPanel();
		pnl_TieuDe.setBackground(color);
		pnl_ContentPanePDDV.setBackground(color);
		pnl_PhieuDatDichVu.add(pnl_TieuDe, BorderLayout.NORTH);

		JLabel lblPhieuDatDV = new JLabel("Danh Sách Phiếu Đặt Dịch Vụ");
		lblPhieuDatDV.setForeground(new Color(15,102,165));
		lblPhieuDatDV.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_TieuDe.add(lblPhieuDatDV);

		JPanel pnl_West = new JPanel();
		pnl_West.setBackground(color);
		pnl_West.setPreferredSize(new Dimension(300, 10));
		pnl_West.setLayout(new BoxLayout(pnl_West, BoxLayout.Y_AXIS));
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setPreferredSize(new Dimension(600, 10));
		pnlChucNang.setBackground(color);
		LineBorder lineBorder = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder = new TitledBorder(lineBorder, "Chức năng");
		pnl_West.setBorder(titledBorder);
		pnl_West.add(pnlChucNang);
		pnlChucNang.setLayout(null);

		pnlChucNang.setBounds(25, 22, 66, 13);
		Image imgPrint = new ImageIcon(this.getClass().getResource("" + "/printer.png")).getImage();
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();

		JButton btnXemChiTiet = new JButton("Xem chi tiết phiếu đặt");
		btnXemChiTiet.setBounds(37, 99, 197, 27);
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// updateTableCTPDV();
				int selectedRow = tblPhieuDatDV.getSelectedRow();
				if (selectedRow != -1) {
					String maPhong = tblPhieuDatDV.getValueAt(selectedRow, 1).toString();// Lấy mã khách hàng từ bảng
					String ngayLap = tblPhieuDatDV.getValueAt(selectedRow, 6).toString();
					LocalDate NgayLap = LocalDate.parse(ngayLap,formatter);
					hienThiChiTietPhong(maPhong,NgayLap);
				}
			}

		});
		pnlChucNang.add(btnXemChiTiet);

		txtTimPhieu = new JTextField();
		txtTimPhieu.setBounds(37, 44, 100, 19);
		pnlChucNang.add(txtTimPhieu);
		txtTimPhieu.setColumns(10);

		JButton btnTim = new JButton("Tìm");
		btnTim.setBounds(149, 43, 85, 21);
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemPhieuDatDichVu();
			}
		});
		pnlChucNang.add(btnTim);

		JLabel lblTimPhieu = new JLabel("Tìm phiếu đặt dịch vụ:");
		lblTimPhieu.setBounds(37, 21, 197, 13);
		pnlChucNang.add(lblTimPhieu);
		
		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setBounds(37, 160, 197, 27);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		pnlChucNang.add(btnLamMoi);
		pnl_PhieuDatDichVu.add(pnl_West, BorderLayout.WEST);
		/*
		 * 
		 */

		JPanel pnl_CenTer = new JPanel();
		pnl_CenTer.setBackground(color);
		pnl_CenTer.setPreferredSize(new Dimension(10, 400));
		LineBorder lineBorder1 = new LineBorder(Color.DARK_GRAY, 1);
		// Tạo tiêu đề "Border Title"
		TitledBorder titledBorder1 = new TitledBorder(lineBorder1, "");
		pnl_CenTer.setBorder(titledBorder1);
		pnl_CenTer.setLayout(new BoxLayout(pnl_CenTer, BoxLayout.Y_AXIS));
		JPanel pnl_p1 = new JPanel();
		pnl_p1.setBackground(color);
		pnl_p1.setPreferredSize(new Dimension(10, 400));
		String headersPhieuDatDV[] = "Mã Phiếu;Mã phòng;Tên phòng;Mã khách hàng;Tên khách hàng;Số điện thoại;Ngày lập"
				.split(";");
		tblModelPhieuDatDV = new DefaultTableModel(headersPhieuDatDV, 0);
		pnl_p1.setLayout(new BoxLayout(pnl_p1, BoxLayout.X_AXIS));
		scrPhieuDatDV = new JScrollPane();
		scrPhieuDatDV.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh S\u00E1ch Phi\u1EBFu \u0110\u1EB7t D\u1ECBch V\u1EE5", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		scrPhieuDatDV.setViewportView(tblPhieuDatDV = new JTable(tblModelPhieuDatDV));
		scrPhieuDatDV.setBackground(color);
		pnl_p1.add(scrPhieuDatDV);
		pnl_CenTer.add(pnl_p1);

		JPanel pnl_p2 = new JPanel();
		pnl_p2.setBackground(color);
		pnl_p2.setBorder(new TitledBorder(null, "Chi Tiết Phiếu Đặt dịch vụ",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		String headersCTPhieuDatDV[] = "STT;Tên dịch vụ;Loại;Số lượng;Đơn giá".split(";");
		tblModelCTPhieuDatDV = new DefaultTableModel(headersCTPhieuDatDV, 0);
		pnl_p2.setLayout(new BoxLayout(pnl_p2, BoxLayout.X_AXIS));
		scrCTPhieuDatDV = new JScrollPane();
		scrCTPhieuDatDV.setViewportView(tblCTPhieuDatDV = new JTable(tblModelCTPhieuDatDV));
		pnl_p2.add(scrCTPhieuDatDV);
		pnl_CenTer.add(pnl_p2);
		pnl_PhieuDatDichVu.add(pnl_CenTer, BorderLayout.CENTER);

		setContentPane(pnl_ContentPanePDDV);
		updateTableDSPDV();
		gopPhieuDichVu();
	}

	private void updateTableDSPDV() {
		phieuDatDichVu_dao = new PhieuDatDichVu_dao();
		list = phieuDatDichVu_dao.getAllPhieuDatDichVu();
		for (PhieuDatDichVu pddv : list) {
			String[] rowData = { pddv.getMaPhieu(), pddv.getPhong().getMaPhong(), pddv.getPhong().getTenPhong(),
					pddv.getKhachHang().getMaKH(), pddv.getKhachHang().getHoTen(), pddv.getKhachHang().getSDT(),
					pddv.getNgayLap() + "" };
			tblModelPhieuDatDV.addRow(rowData);
		}
		tblPhieuDatDV.setModel(tblModelPhieuDatDV);
	}
	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		tblModelPhieuDatDV.setRowCount(0);
		updateTableDSPDV();
		gopPhieuDichVu();
	}
//	public List<ChiTietPhieuDichVu> layDanhSachChiTietDichVuTheoMaKhachHang(String maKH) {
//	    // Thay thế đoạn mã sau đây với cách thức lấy dữ liệu từ cơ sở dữ liệu hoặc nguồn dữ liệu thực tế
//	    ChiTietPhieuDichVu_dao ctPDDV = new ChiTietPhieuDichVu_dao();
//	    return ctPDDV.layDanhSachChiTietDichVuTheoMaKhachHang(maKH);
//	}
//	private void hienThiChiTietKhachHang(String maKhachHang) {
//	    tableModelCTPhieuDatDV.setRowCount(0); // Xóa dữ liệu cũ trong bảng chi tiết
//
//	    List<ChiTietPhieuDichVu> dsChiTiet = layDanhSachChiTietDichVuTheoMaKhachHang(maKhachHang);
//
//	    if (dsChiTiet != null && !dsChiTiet.isEmpty()) {
//	        for (ChiTietPhieuDichVu ct : dsChiTiet) {
//	            String[] rowData = {
//	                ct.getDichVu().getTenDV(),
//	                ct.getDichVu().getLoaiDichVu(),
//	                ct.getSoLuongBan() + "",
//	                ct.getDichVu().getDonGia() + ""
//	            };
//	            tableModelCTPhieuDatDV.addRow(rowData);
//	        }
//	    }
//
//	    tblCTPhieuDatDV.setModel(tableModelCTPhieuDatDV);
//	}
//
//	private void gopPhieuDichVu() {
//	    // Tạo một danh sách mới để lưu trữ các phiếu dịch vụ sau khi gộp
//	    List<PhieuDatDichVu> danhSachDaGop = new ArrayList<>();
//	    // Duyệt qua danh sách hiện tại các phiếu dịch vụ
//	    for (PhieuDatDichVu p : list) {
//	        boolean daThem = false;
//	        // Duyệt qua danh sách các phiếu đã được gộp
//	        for (PhieuDatDichVu pGop : danhSachDaGop) {
//	            // Nếu tìm thấy mã khách hàng giống nhau
//	            if (p.getKhachHang().getMaKH().equals(pGop.getKhachHang().getMaKH())) {
//	                daThem = true;
//	                break;
//	            }
//	        }
//	        // Nếu không tìm thấy phiếu đã gộp cho mã khách hàng này, thêm phiếu mới
//	        if (!daThem) {
//	            danhSachDaGop.add(p);
//	        }
//	    }
//
//	    // Hiển thị các phiếu dịch vụ sau khi gộp
//	    hienThiPhieuSauKhiGop(danhSachDaGop);
//	}
	public List<ChiTietPhieuDichVu> layDanhSachChiTietDichVuTheoMaPhong(String maPhong, LocalDate ngayLap) {
		// Thay thế đoạn mã sau đây với cách thức lấy dữ liệu từ cơ sở dữ liệu hoặc
		// nguồn dữ liệu thực tế
		ChiTietPhieuDichVu_dao ctPDDV = new ChiTietPhieuDichVu_dao();
		return ctPDDV.layDanhSachChiTietDichVuTheoMaPhong(maPhong,ngayLap);
	}

	private void hienThiChiTietPhong(String maPhong, LocalDate ngayLap) {
	    int STT = 0;
	    tblModelCTPhieuDatDV.setRowCount(0); // Xóa dữ liệu cũ trong bảng chi tiết
	    List<ChiTietPhieuDichVu> dsChiTiet = layDanhSachChiTietDichVuTheoMaPhong(maPhong,ngayLap);
	    if (dsChiTiet != null && !dsChiTiet.isEmpty()) {
	        for (ChiTietPhieuDichVu ct : dsChiTiet) {
	        	STT++;
	            String[] rowData = {STT+"",
	                ct.getDichVu().getTenDV(),
	                ct.getDichVu().getLoaiDichVu(),
	                ct.getSoLuongBan() + "",
	                ct.getDichVu().getDonGia() + ""
	            };
//	            System.out.println(ct.toString());
	            tblModelCTPhieuDatDV.addRow(rowData);
	            
	        }
	    }

	    tblCTPhieuDatDV.setModel(tblModelCTPhieuDatDV);
	}

	private void gopPhieuDichVu() {
		// Tạo một danh sách mới để lưu trữ các phiếu dịch vụ sau khi gộp
		List<PhieuDatDichVu> danhSachDaGop = new ArrayList<>();
		// Duyệt qua danh sách hiện tại các phiếu dịch vụ
		for (PhieuDatDichVu p : list) {
			boolean daThem = false;
			// Duyệt qua danh sách các phiếu đã được gộp
			for (PhieuDatDichVu pGop : danhSachDaGop) {
				// Nếu tìm thấy mã khách hàng giống nhau
				if (p.getPhong().getMaPhong().equals(pGop.getPhong().getMaPhong())
						&& p.getNgayLap().equals(pGop.getNgayLap()) && p.getKhachHang().getMaKH().equals(pGop.getKhachHang().getMaKH())) {
					daThem = true;
					break;
				}
			}
			// Nếu không tìm thấy phiếu đã gộp cho mã khách hàng này, thêm phiếu mới
			if (!daThem) {
				danhSachDaGop.add(p);
			}
		}

		// Hiển thị các phiếu dịch vụ sau khi gộp
		hienThiPhieuSauKhiGop(danhSachDaGop);
	}

	private void hienThiPhieuSauKhiGop(List<PhieuDatDichVu> danhSachDaGop) {
		// Xóa dữ liệu cũ
		tblModelPhieuDatDV.setRowCount(0);
		// Hiển thị dữ liệu mới sau khi gộp
		for (PhieuDatDichVu p : danhSachDaGop) {
			// Thêm các dòng mới cho bảng hiển thị
			String[] rowData = { p.getMaPhieu(), p.getPhong().getMaPhong(), p.getPhong().getTenPhong(),
					p.getKhachHang().getMaKH(), p.getKhachHang().getHoTen(), p.getKhachHang().getSDT(),
					p.getNgayLap() + "" };
			tblModelPhieuDatDV.addRow(rowData);
		}
		// Cập nhật bảng hiển thị
		tblPhieuDatDV.setModel(tblModelPhieuDatDV);
	}

	private void updata(List<PhieuDatDichVu> list) {
		tblModelPhieuDatDV.setRowCount(0);
		phieuDatDichVu_dao = new PhieuDatDichVu_dao();
		for (PhieuDatDichVu pddv : list) {
			String[] rowData = { pddv.getMaPhieu(), pddv.getPhong().getMaPhong(), pddv.getPhong().getTenPhong(),
					pddv.getKhachHang().getMaKH(), pddv.getKhachHang().getHoTen(), pddv.getKhachHang().getSDT(),
					pddv.getNgayLap() + "" };
			tblModelPhieuDatDV.addRow(rowData);
		}
		tblPhieuDatDV.setModel(tblModelPhieuDatDV);
	}

	protected void timKiemPhieuDatDichVu() {
		String a = txtTimPhieu.getText();
		String phieuDichVu = "";
		if (!a.equals("")) {
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			phieuDichVu = "pddv.maPhieu like N'%" + a + "%' or pddv.maPhong like N'%" + a + "%' or tenPhong like  N'%"
					+ a + "%' or pddv.maKH like N'%" + a + "%' or hoTen like N'%" + a + "%' or SDT like N'%" + a
					+ "%' or ngayLap like N'%" + a + "%'";
			updata(phieuDatDichVu_dao.findPhieuDichVu(phieuDichVu));
		} else
			JOptionPane.showMessageDialog(null, "Hãy Nhập Thông Tin Tìm Kiếm!");
	}
}