package project.sess.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PowerControlPanel extends JPanel{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2784314840185587371L;
	public JTextArea textArea;
	public JButton buttonWriteData;
	public JButton buttonClear;
	public JButton buttonSaveAs;
	public JButton buttonSave;
	public JScrollPane scrollPane;
	
	public PowerControlPanel() {				
		this.setPowerControlPanel();
		
		textArea = new JTextArea(10, 10);
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea); 
		scrollPane.setBounds(50, 50, 660, 300);
		this.add(scrollPane, this.getJScrollPaneOption());
		
		// 이하 버튼을 해당 위치에 세팅
		// buttonClear = new JButton(new ImageIcon("./images/bt_connect_on.png"));
		buttonClear = new JButton("초기화");
		buttonClear.setBorderPainted(false);
		buttonClear.setContentAreaFilled(false);
		buttonClear.setFocusPainted(false);
		buttonClear.setBounds(80, 350, 100, 42);
		this.add(buttonClear);

		// buttonDown = new JButton(new ImageIcon("./images/bt_connect_close.png"));
		buttonSaveAs = new JButton("다른이름으로 저장");
		buttonSaveAs.setBorderPainted(false);
		buttonSaveAs.setContentAreaFilled(false);
		buttonSaveAs.setFocusPainted(false);
		buttonSaveAs.setEnabled(false);
		buttonSaveAs.setBounds(170, 350, 160, 42);
		this.add(buttonSaveAs);

		buttonSave = new JButton("저장하기");
		buttonSave.setBorderPainted(false);
		buttonSave.setContentAreaFilled(false);
		buttonSave.setFocusPainted(false);
		buttonSave.setEnabled(false);
		buttonSave.setBounds(320, 350, 100, 42);
		this.add(buttonSave);
		
		buttonWriteData = new JButton("데이터 기록 시작");
		buttonWriteData.setBorderPainted(false);
		buttonWriteData.setContentAreaFilled(false);
		buttonWriteData.setFocusPainted(false);
		buttonWriteData.setBounds(420, 350, 145, 42);
		this.add(buttonWriteData);
	}
	
	public void setPowerControlPanel() {
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 102, 762, 458);
		this.setVisible(false);
	}
	
	// JScrollPane 옵션 세팅
	public GridBagConstraints getJScrollPaneOption() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.insets = new Insets(40, 30, 160, 30);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		
		return constraints;
	}
}
