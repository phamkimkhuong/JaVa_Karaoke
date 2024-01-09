package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import connectDB.connectSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Color;

public class DangNhap_gui extends JFrame {
	private JTextField txtTenDangNhap;
	private JPasswordField txtPassword;

	public DangNhap_gui() {
		getContentPane().setPreferredSize(new Dimension(550, 300));
		setLocationRelativeTo(null);
		JPanel pnl_East = new JPanel();
		pnl_East.setBackground(new Color(230, 230, 250));
		JLabel lblImages = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("" + "/BackgroundDangNhap.jpg")).getImage();
		pnl_East.setLayout(new BoxLayout(pnl_East, BoxLayout.X_AXIS));
		lblImages.setIcon(new ImageIcon(img));
		pnl_East.add(lblImages);
		getContentPane().add(pnl_East, BorderLayout.WEST);

		JPanel pnl_CenTer = new JPanel();
		pnl_CenTer.setBackground(new Color(230, 230, 250));
		getContentPane().add(pnl_CenTer, BorderLayout.CENTER);
		pnl_CenTer.setLayout(null);

		JLabel lblDangNhap = new JLabel("Đăng Nhập");
		lblDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDangNhap.setBounds(87, 10, 118, 44);
		pnl_CenTer.add(lblDangNhap);

		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
		lblTenDangNhap.setBounds(23, 71, 119, 13);
		pnl_CenTer.add(lblTenDangNhap);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setBounds(23, 124, 119, 13);
		pnl_CenTer.add(lblMatKhau);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setBounds(130, 68, 118, 19);
		pnl_CenTer.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(130, 121, 118, 19);
		pnl_CenTer.add(txtPassword);

		JButton btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setBounds(23, 169, 119, 21);
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtTenDangNhap.getText().trim();
				String password = new String(txtPassword.getPassword());
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Thông tin nhập không được để trống!");
				} else if (username.equals("QL") && password.equals("123")) {
					setVisible(false);
					ManHinhChinh_gui mhc = new ManHinhChinh_gui();
				} else if (username.equals("NV") && password.equals("123")) {
					setVisible(false);
					ManHinhChinh_gui mhc = new ManHinhChinh_gui();
					mhc.mniDoanhThu.setVisible(false);
					mhc.mniQuanLiDichVu.setVisible(false);
				//	mhc.mniQuanLiLoaiDichVu.setVisible(false);
					mhc.mnNhanVien.setVisible(false);
					mhc.mniQuanLyLoaiPhong.setVisible(false);
					mhc.mniQuanLyPhong.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu sai!");
			}
		});
		pnl_CenTer.add(btnDangNhap);

		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setBounds(168, 169, 96, 21);
		pnl_CenTer.add(btnThoat);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) throws SQLException {
		connectSQL.getInstance().connect();
		new DangNhap_gui();
	}
}
