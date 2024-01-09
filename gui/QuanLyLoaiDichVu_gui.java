package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.DichVu_dao;
import dao.LoaiDichVu_dao;
import entity.DichVu;
import entity.LoaiDichVu;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class QuanLyLoaiDichVu_gui extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel pnl_ContentPaneQLLDV;
	private JTextField txtMaLoai;
	private JTextField txtTenLoai;
	private JTextField txtTimKiem;
	private JScrollPane scrLDV;
	private DefaultTableModel tableModelLDV;
	private JTable tblLDV;
	private JButton btnThem;
	private JButton btnLamMoi;
	private JButton btnCapNhat;
	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnXoaTrang;
	private int edit;
	private LoaiDichVu_dao loaiDichVu_dao;
	private static int loaiDichVuCounter = 1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public QuanLyLoaiDichVu_gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width,screenSize.height);
		this.setLocationRelativeTo(null);
		pnl_ContentPaneQLLDV = new JPanel();
		pnl_ContentPaneQLLDV.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setContentPane(pnl_ContentPaneQLLDV);
		pnl_ContentPaneQLLDV.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_North = new JPanel();
		
		pnl_North.setPreferredSize(new Dimension(10, 50));
		pnl_ContentPaneQLLDV.add(pnl_North, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("Quản Lý Loại Dich Vụ");
		lblTieuDe.setForeground(new Color(15,102,165));
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		pnl_North.add(lblTieuDe);
		
		JPanel pnl_ThongTin = new JPanel();
		pnl_ThongTin.setPreferredSize(new Dimension(650, 10));
		pnl_ThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng tin lo\u1EA1i d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_ContentPaneQLLDV.add(pnl_ThongTin, BorderLayout.WEST);
		pnl_ThongTin.setLayout(null);
		
		JLabel lblMaLoai = new JLabel("Mã loại:");
		lblMaLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMaLoai.setBounds(10, 23, 103, 13);
		pnl_ThongTin.add(lblMaLoai);
		
		txtMaLoai = new JTextField();
		txtMaLoai.setBounds(123, 20, 367, 19);
		pnl_ThongTin.add(txtMaLoai);
		txtMaLoai.setColumns(10);
		txtMaLoai.setEditable(false);
		
		JLabel lblTenLoai = new JLabel("Tên loại:");
		lblTenLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenLoai.setBounds(10, 69, 103, 13);
		pnl_ThongTin.add(lblTenLoai);
		
		txtTenLoai = new JTextField();
		txtTenLoai.setColumns(10);
		txtTenLoai.setBounds(123, 66, 367, 19);
		pnl_ThongTin.add(txtTenLoai);
		
		JLabel lblTimKiem = new JLabel("Tìm loại dịch vụ:");
		lblTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTimKiem.setBounds(10, 128, 167, 13);
		pnl_ThongTin.add(lblTimKiem);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(10, 162, 280, 19);
		pnl_ThongTin.add(txtTimKiem);
		
		JPanel pnl_ChucNang = new JPanel();
		pnl_ChucNang.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_ChucNang.setPreferredSize(new Dimension(500, 10));
		pnl_ContentPaneQLLDV.add(pnl_ChucNang, BorderLayout.CENTER);
		pnl_ChucNang.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(27, 51, 128, 36);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themLoaiDichVu();
			}
		});
		Image imgAdd = new ImageIcon(this.getClass().getResource("" + "/Button-Add.24.png")).getImage();
		btnThem.setIcon(new ImageIcon(imgAdd));
		pnl_ChucNang.add(btnThem);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(452, 139, 128, 36);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lamMoiBang();
			}
		});
		Image imgLamMoi = new ImageIcon(this.getClass().getResource("" + "/Refresh.24.png")).getImage();
		btnLamMoi.setIcon(new ImageIcon(imgLamMoi));
		pnl_ChucNang.add(btnLamMoi);
		
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setBounds(165, 51, 128, 36);
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatLDV();
			}
		});
		Image imgEdit = new ImageIcon(this.getClass().getResource("" + "/Edit.24.png")).getImage();
		btnCapNhat.setIcon(new ImageIcon(imgEdit));
		pnl_ChucNang.add(btnCapNhat);
		
		btnTim = new JButton("Tìm");
		btnTim.setBounds(303, 139, 128, 36);
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemLoaiDichVu();
			}
		});
		Image imgTim = new ImageIcon(this.getClass().getResource("" + "/Search.24.png")).getImage();
		btnTim.setIcon(new ImageIcon(imgTim));
		pnl_ChucNang.add(btnTim);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(303, 51, 128, 36);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaLDV();
			}
		});
		Image imgExit = new ImageIcon(this.getClass().getResource("" + "/Button-Close.24.png")).getImage();
		btnXoa.setIcon(new ImageIcon(imgExit));
		pnl_ChucNang.add(btnXoa);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(165, 139, 128, 36);
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luuLoaiDichVuVaoDS();
			}
		});
		Image imgSave = new ImageIcon(this.getClass().getResource("" + "/Save.24.png")).getImage();
		btnLuu.setIcon(new ImageIcon(imgSave));
		pnl_ChucNang.add(btnLuu);
		
		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBounds(27, 139, 128, 36);
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangLDV();
			}
		});
		Image imgClean = new ImageIcon(this.getClass().getResource("" + "/bubbles.png")).getImage();
		btnXoaTrang.setIcon(new ImageIcon(imgClean));
		pnl_ChucNang.add(btnXoaTrang);
		
		JPanel pnl_South = new JPanel();
		pnl_South.setBorder(new TitledBorder(null, "Danh s\u00E1ch lo\u1EA1i d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_South.setPreferredSize(new Dimension(10, 350));
		String headersLDV [] ="Mã dịch vụ;Tên dịch vụ".split(";");
		tableModelLDV = new DefaultTableModel(headersLDV,0);
		scrLDV = new JScrollPane();
		scrLDV.setViewportView(tblLDV = new JTable(tableModelLDV));
		scrLDV.setPreferredSize(new Dimension(1050, 400));
		pnl_South.setLayout(new BorderLayout(0, 0));
		pnl_South.add(scrLDV);
		pnl_ContentPaneQLLDV.add(pnl_South, BorderLayout.SOUTH);
		updateTableData();
	}
	private void updateTableData() {
		loaiDichVu_dao = new LoaiDichVu_dao();
		List<LoaiDichVu> list = loaiDichVu_dao.getAllLoaiDichVu();
		for(LoaiDichVu ldv : list) {
			String [] rowData = {ldv.getMaLoai(),ldv.getTenLoai()};
			tableModelLDV.addRow(rowData);
		}
		tblLDV.setModel(tableModelLDV);
	}
	protected void lamMoiBang() {
		// TODO Auto-generated method stub
		tableModelLDV.setRowCount(0);
		updateTableData();
	}
	private void xoaTrangLDV() {
		// TODO Auto-generated method stub
		txtMaLoai.setText("");
		txtTenLoai.setText("");
		txtTimKiem.setText("");
		txtMaLoai.requestFocus();
	}
	protected void themLoaiDichVu() {
		// TODO Auto-generated method stub
		edit = 1;
		if (btnThem.getText().equalsIgnoreCase("Thêm")) {
			xoaTrangLDV();
			// vô hiệu hóa button cập nhật và button xóa
			btnCapNhat.setEnabled(false);
			btnThem.setText("Hủy");
			// Mở khóa Button lưu và txtMaPhong
			btnLuu.setEnabled(true);
			btnXoa.setEnabled(false);
			//txtMaKH.setEnabled(false);
			// đặt con trỏ chuột vào ô mã phòng
			txtMaLoai.requestFocus();
		} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
			edit = 0;
			btnCapNhat.setEnabled(true);
			btnLuu.setEnabled(false);
			btnXoa.setEnabled(true);
			btnThem.setText("Thêm");
			xoaTrangLDV();
		}
	}
	protected void xoaLDV() {
		// Kiểm tra hàng đẫ được chọn hay không
		int n = tblLDV.getSelectedRow();
		if (n != -1) {
			if (!tblLDV.getModel().getValueAt(n, 1).toString().equalsIgnoreCase("")) {
				int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?");
				if (tb == JOptionPane.YES_OPTION) {
					String maLoai = tblLDV.getModel().getValueAt(n, 0).toString();
					loaiDichVu_dao.deleteLoaiDichVu(maLoai);
					lamMoiBang();
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "Hãy chọn loại dịch vụ cần xóa!");

	}
	private void capNhatLDV() {
	    edit = 2;
	    int row = tblLDV.getSelectedRow();
	    if (row >= 0) {
	    	String maLoai = txtMaLoai.getText();
			String tenLoai = txtTenLoai.getText();
			if(btnCapNhat.getText().equalsIgnoreCase("Hủy")) {
				btnCapNhat.setText("Cập Nhật");
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnLuu.setEnabled(false);
			}else if (row != -1) {
				edit = 2;
				if (btnCapNhat.getText().equalsIgnoreCase("Cập Nhật")) {
					btnCapNhat.setText("Hủy");
					 btnLuu.setEnabled(true);
					 btnThem.setEnabled(false);
					 btnXoa.setEnabled(false);
					// Đưa dữ liệu từ bảng vào textField để cập nhật
					txtMaLoai.setText(tblLDV.getModel().getValueAt(row, 0).toString());
					txtTenLoai.setText(tblLDV.getModel().getValueAt(row, 1).toString());
				}
		}else {
	            int dialogButton = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật thông tin không?", "Warning", JOptionPane.YES_NO_OPTION);
	            if (dialogButton == JOptionPane.YES_OPTION) {
	                LoaiDichVu ldv = new LoaiDichVu(maLoai, tenLoai);
	                if (loaiDichVu_dao.updateLoaiDichVu(ldv)) {
	                    tableModelLDV.setValueAt(maLoai, row, 0);
	                    tableModelLDV.setValueAt(tenLoai, row, 1);
	                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
	                    xoaTrangLDV();
	                    btnCapNhat.setText("Cập Nhật");
	                } else {
	                    JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
	                }
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng trong bảng để cập nhật thông tin loại dịch vụ.");
	    }
	}
	protected void luuLoaiDichVuVaoDS() {
		// TODO Auto-generated method stub
		String maLoai = txtMaLoai.getText();
		String tenLoai = txtTenLoai.getText();
		if (tenLoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Thông tin nhập không được để trống");
		} else {
			if (edit == 1) {
				if(validData()) {
					String ma = taoMaLoaiDichVu();
					LoaiDichVu ldv = new LoaiDichVu(ma,tenLoai);
					loaiDichVu_dao.addLoaiDichVu(ldv);
					lamMoiBang();
					JOptionPane.showMessageDialog(this, "Tạo mới thành công");
					xoaTrangLDV();
					lamMoiBang();
					btnCapNhat.setEnabled(true);
					btnXoa.setEnabled(true);
					btnThem.setText("Thêm");
				}
			}
			else if (edit == 2) {
				LoaiDichVu ldv = new LoaiDichVu(maLoai, tenLoai);
				loaiDichVu_dao.updateLoaiDichVu(ldv);
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				xoaTrangLDV();
				lamMoiBang();
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnCapNhat.setText("Cập Nhật");

			}
		}
	}
	private void updata(List<LoaiDichVu> list) {
		tableModelLDV.setRowCount(0);
		loaiDichVu_dao = new LoaiDichVu_dao();
		for(LoaiDichVu ldv : list) {
			String [] rowData = {ldv.getMaLoai(),ldv.getTenLoai()};
			tableModelLDV.addRow(rowData);
		}
		tblLDV.setModel(tableModelLDV);
	}
	protected void timKiemLoaiDichVu() {
		String a = txtTimKiem.getText();
		String loaiDichVu = "";
		if (!a.equals("")) {
			// Tạo câu lệnh truy vấn tìm kiếm trên sql
			loaiDichVu = "maLoai like N'%" + a + "%' or tenLoai like N'%" + a + "%'";
			updata(loaiDichVu_dao.findLoaiDichVu(loaiDichVu));
		} else
			JOptionPane.showMessageDialog(null, "Hãy Nhập Thông Tin Tìm Kiếm!");
	}
	private boolean timMaLDV(String maLoai) {
		// TODO Auto-generated method stub
		maLoai = maLoai.toUpperCase();
		List<LoaiDichVu>list = loaiDichVu_dao.getAllLoaiDichVu();
		for (LoaiDichVu ldv : list) {
			if (ldv.getMaLoai().contains(maLoai))
				return false;
		}
		return true;
	}
	public String taoMaLoaiDichVu() {
		String maLoai;
		// Sử dụng Formatter để định dạng chuỗi thành "NVXXXXXX"
		Formatter formatter = new Formatter();
		do {
			formatter = new Formatter();
			// Lấy chuỗi đã định dạng từ Formatter và trả về
			maLoai = formatter.format("LDV%03d", loaiDichVuCounter).toString();
			loaiDichVuCounter++;
		} while (!timMaLDV(maLoai));
		formatter.close();
		return maLoai;
	}
	private boolean validData() {
    	String maLoai = txtMaLoai.getText();
		String tenLoai = txtTenLoai.getText();
        if (!(tenLoai.length() > 0 && tenLoai.matches("[\\p{L}' ]+"))) {
            JOptionPane.showMessageDialog(this, "Tên dịch vụ không được chứa số và kí tự đặc biệt");
            return false;
        }
		return true;
	}
}
