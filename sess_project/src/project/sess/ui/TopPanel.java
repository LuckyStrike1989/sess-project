package project.sess.ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import project.sess.vo.ImageVO;

public class TopPanel extends JPanel {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5562594312126970519L;

	private ImageVO images;
	
	public JButton monitoringButton;
	public JButton deviceControlButton;
	public JButton graphButton;
	public JButton settingButton;
	
	public TopPanel(ImageVO image) {
		this.setLayout(null);
		
		this.images = image;
		
		monitoringButton = new JButton(images.getGlobal_botton1());
		monitoringButton.setBorderPainted(false);
		monitoringButton.setBackground(new Color(191,191,191));
		monitoringButton.setContentAreaFilled(false);
		monitoringButton.setBounds(17, 3, 180, 59);
		monitoringButton.setIcon(images.getGlobal_botton1_selected());
		this.add(monitoringButton);
		
		deviceControlButton = new JButton(images.getGlobal_botton2());
		deviceControlButton.setBorderPainted(false);
		deviceControlButton.setBackground(new Color(191,191,191));
		deviceControlButton.setContentAreaFilled(false);
		deviceControlButton.setBounds(197, 3, 180, 59);
		deviceControlButton.setIcon(images.getGlobal_botton2());
		this.add(deviceControlButton);
		
		graphButton = new JButton(images.getGlobal_botton3());
		graphButton.setBorderPainted(false);
		graphButton.setBackground(new Color(191,191,191));
		graphButton.setContentAreaFilled(false);
		graphButton.setBounds(378, 3, 180, 59);
		graphButton.setIcon(images.getGlobal_botton3());
		this.add(graphButton);
		
		settingButton = new JButton(images.getGlobal_botton4());
		settingButton.setBorderPainted(false);
		settingButton.setBackground(new Color(221,191,191));
		settingButton.setContentAreaFilled(false);
		settingButton.setBounds(558, 3, 180, 59);
		settingButton.setIcon(images.getGlobal_botton4());
		this.add(settingButton);
		
	}
}
