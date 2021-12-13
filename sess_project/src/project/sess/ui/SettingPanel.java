package project.sess.ui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import project.sess.vo.SettingVO;

public class SettingPanel extends JPanel {
	/*
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2258018595062088395L;

	public JComboBox<String> device;
	public JComboBox<String> baudRate;
	public JComboBox<String> dataBits;
	public JComboBox<String> stopBits;
	public JComboBox<String> parity;

	public JButton serialPortConnect;
	public JButton serialPortClose;

	// private final String SERIAL_PORT_CONNECT_BUTTON_NAME = "���� ����";
	// private final String SERIAL_PORT_CLOSE_BUTTON_NAME = "���� ����";

	private SettingVO settingVO = new SettingVO();

	public SettingPanel() {
		this.setSettingPanel();

		device = new JComboBox<String>(settingVO.getDeviceItems());
		device.setBounds(470, 60, 200, 30);
		this.add(device);

		baudRate = new JComboBox<String>(settingVO.getBaudRate());
		baudRate.setBounds(470, 95, 200, 30);
		this.add(baudRate);

		dataBits = new JComboBox<String>(settingVO.getDataBits());
		dataBits.setBounds(470, 130, 200, 30);
		this.add(dataBits);
		

		stopBits = new JComboBox<String>(settingVO.getStopBits());
		stopBits.setBounds(470, 165, 200, 30);
		this.add(stopBits);

		parity = new JComboBox<String>(settingVO.getParity());
		parity.setBounds(470, 200, 200, 30);
		this.add(parity);

		// serialPortConnect = new JButton(SERIAL_PORT_CONNECT_BUTTON_NAME);
		//serialPortConnect = new JButton(new ImageIcon("./images/bt_connect_on.png"));
		// ImageIcon pressedIcon = new ImageIcon("./images/bt_connect_on_bk.png");
		serialPortConnect = new JButton("����");
		//serialPortConnect.setPressedIcon(pressedIcon);
		serialPortConnect.setBorderPainted(false);
		serialPortConnect.setContentAreaFilled(false);
		serialPortConnect.setFocusPainted(false);
		serialPortConnect.setEnabled(true);
		serialPortConnect.setBounds(450, 300, 100, 42);
		this.add(serialPortConnect);

		// serialPortClose = new JButton(SERIAL_PORT_CLOSE_BUTTON_NAME);
		serialPortClose = new JButton("��������");
		// ImageIcon pressedIcon_close = new ImageIcon("./images/bt_connect_close_bk.png");
		//serialPortClose.setPressedIcon(pressedIcon_close);
		serialPortClose.setBorderPainted(false);
		serialPortClose.setContentAreaFilled(false);
		serialPortClose.setFocusPainted(false);
		serialPortClose.setEnabled(false);
		serialPortClose.setBounds(600, 300, 100, 42);
		this.add(serialPortClose);
	}

	public void setSettingPanel() {
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 102, 762, 458);
		this.setVisible(false);
	}
}
