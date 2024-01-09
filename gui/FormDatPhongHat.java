package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHang_dao;
import entity.KhachHang;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.List;

public class FormDatPhongHat extends JDialog {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPanel = new JPanel();
	protected KhachHang_dao kh_dao;
	private JPanel pnlThongTin;
	private JTable tblDSKhachHang;
	private int edit;
	private KhachHang KH1;
	private KhachHang KH2;
	private JButton btnYes;
	private JButton btnNo;
	private int userOption;

	/**
	 * Create the dialog.
	 */
	public FormDatPhongHat(JFrame parentFrame, List<KhachHang> ds, KhachHang kh) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(650, 300);
		this.setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		Color color = new Color(197, 199, 199);
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(color);

		pnlTieuDe.setPreferredSize(new Dimension(10, 35));

		contentPanel.add(pnlTieuDe, BorderLayout.NORTH);
		contentPanel.setBackground(color);
		
		JLabel lblPhieuDatPhong = new JLabel("Thông Tin Khách Hàng");
		lblPhieuDatPhong.setForeground(new Color(15, 102, 165));
		lblPhieuDatPhong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhieuDatPhong.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPhieuDatPhong.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTieuDe.add(lblPhieuDatPhong);
		/*
		 * 
		 */
		pnlThongTin = new JPanel();
		pnlThongTin.setPreferredSize(new Dimension(10, 200));
		contentPanel.add(pnlThongTin, BorderLayout.CENTER);
		pnlThongTin.setLayout(new BorderLayout(0, 0));

		kh_dao = new KhachHang_dao();
		tblDSKhachHang = new JTable();

		DefaultTableModel table = new DefaultTableModel();
		table.addColumn("Mã Khách Hàng");
		table.addColumn("Họ Tên");
		table.addColumn("Số Điện Thoại");
		table.addColumn("Email");
		table.addColumn("Ngày Sinh");
		for (KhachHang kh1 : ds) {
			String[] rowData = { kh1.getMaKH(), kh1.getHoTen(), kh1.getSDT(), kh1.getEmail(), kh1.getNgaySinh() };
			table.addRow(rowData);
		}
		tblDSKhachHang.setModel(table);

		JScrollPane scrDSKhachHang = new JScrollPane(tblDSKhachHang);
		pnlThongTin.add(scrDSKhachHang);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(color);
		buttonPane.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(null);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			userOption = JOptionPane.CANCEL_OPTION;
			dispose(); // Đóng dialog khi nút Canner được nhấn
			JOptionPane.showMessageDialog(null, "Đặt Phòng Không Thành Công!");
		});
		cancelButton.setBounds(10, 11, 79, 28);
//		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		btnNo = new JButton("Tạo Mới Khách Hàng");
		btnNo.setBounds(467, 11, 159, 28);
		btnNo.addActionListener(e -> {
			userOption = JOptionPane.NO_OPTION;
			dispose(); 

		});
//		btnNo.setActionCommand("OK");
		buttonPane.add(btnNo);
//		getRootPane().setDefaultButton(btnTaoMoi);
		btnYes = new JButton("Cập Nhật Thông Tin");
		btnYes.setBounds(215, 11, 148, 28);
		btnYes.addActionListener(e -> {
			int row = tblDSKhachHang.getSelectedRow();
			if (row != -1) { // row đầu tiên = 0
				String ma = tblDSKhachHang.getModel().getValueAt(row, 0).toString();
				String email = tblDSKhachHang.getModel().getValueAt(row, 2).toString();
				String ngaySinh = tblDSKhachHang.getModel().getValueAt(row, 4).toString();
				KH2 = new KhachHang(ma, kh.getHoTen(), ngaySinh, kh.getSDT(), email);
				kh_dao.updateKhachHang(KH2);
				DatPhong_gui.editKH = KH2;
				userOption = JOptionPane.YES_OPTION;
				dispose(); 
			} else {
				JOptionPane.showMessageDialog(null,
						"Vui lòng chọn một hàng trong bảng để cập nhật thông tin khách hàng.");
			}
		});
		buttonPane.add(btnYes);
		setVisible(true);
	}

	public int getUserOption() {
		return userOption;
	}
}
