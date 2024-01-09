package gui;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class FormNgayHenDatPhong extends JDialog {
	private static final long serialVersionUID = 1L;
	private int userOption;
	private JDateChooser dtcNgayNhan;
	private Date selectedDate;
	private LocalDate localDate;

	public FormNgayHenDatPhong(JFrame parentFrame) {
		setModal(true);
		setTitle("Chọn Ngày Hẹn Đặt Phòng");
		JPanel panel = new JPanel(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		JButton yesButton = new JButton("OK");
		yesButton.addActionListener(e -> {
			selectedDate = dtcNgayNhan.getDate();
			localDate = selectedDate.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate();
			userOption = JOptionPane.YES_OPTION;
			dispose(); // Close the dialog when Yes button is clicked
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			userOption = JOptionPane.CANCEL_OPTION;
			dispose(); // Đóng dialog khi nút Canner được nhấn
		});

		cancelButton.addActionListener(e -> {
			userOption = JOptionPane.CANCEL_OPTION;
			dispose(); // Close the dialog when Cancel button is clicked
		});

		buttonPanel.add(yesButton);
		yesButton.setEnabled(false);
		buttonPanel.add(cancelButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);

		dtcNgayNhan = new JDateChooser();
		dtcNgayNhan.setPreferredSize(new Dimension(120, 20));
		dtcNgayNhan.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName()) && evt.getNewValue() != null) {
					// Người dùng đã chọn ngày, mở khóa nút "OK"
					yesButton.setEnabled(true);
				}
			}
		});

		panel_1.add(dtcNgayNhan);
		// Lấy thời gian hiện tại

		Calendar cal = Calendar.getInstance();
		// Thiết Lập thời gian ngày nhỏ nhất được chọn là ngày hiện tại

		dtcNgayNhan.setMinSelectableDate(cal.getTime());

		// Thiết Lập thời gian ngày lớn nhất được chọn là ngày hiện tại + 7

		cal.add(Calendar.DAY_OF_MONTH, 7);
		dtcNgayNhan.setMaxSelectableDate(cal.getTime());

		setSize(300, 120);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parentFrame);
		setVisible(true);
	}

	public int getUserOption() {
		return userOption;
	}

	public LocalDate getSelectedDate() {
		return localDate;
	}
}
