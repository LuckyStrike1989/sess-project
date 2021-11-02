package project.sess.ui;

import java.awt.Color;

import javax.swing.JPanel;

public class PowerControlPanel extends JPanel{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2784314840185587371L;

	public PowerControlPanel() {
		this.setPowerControlPanel();
	}
	
	public void setPowerControlPanel() {
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 102, 762, 458);
		this.setVisible(false);
	}
}
