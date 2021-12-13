package project.sess.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project.sess.vo.ImageVO;

public class MonitoringPanel extends JPanel {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2248164786233729021L;
	
	private ImageVO images;
	
	private Font zoneLabelFont = new Font("Serif", Font.ITALIC, 13);
	private Font infoLabelFont = new Font("Serif", Font.ITALIC, 25);
	
	private JLabel[] stateLabel = new JLabel[20];
	
	private JLabel labelZone;
	
	private JLabel infoInputVolt;
	private JLabel infoElectricCurrent;
	private JLabel infoOutputVolt;
	private JLabel infoFirstTemperature;
	private JLabel infoSecondTemperature;
	
	// 데이터 레이블
	public JLabel infoInputVoltData;
	public JLabel infoElectricCurrentData;
	public JLabel infoOutputVoltData;
	public JLabel infoFirstTemperatureData;
	public JLabel infoSecondTemperatureData;

	int parseIntInfoInputVolt = 0;
	
	public MonitoringPanel(ImageVO image) {
		this.images = image;
		
		this.setMonitoringPanel();
		
		/**
		 * Zone JLabel 세팅
		 */
		this.setZoneLabel();
		
		/**
		 * 입력전압
		 */
		this.setInputVolt();
		
		/**
		 * 전류
		 */
		this.setElectricCurrent();
		
		/**
		 * 출력전압
		 */
		this.setOutputVolt();
		
		/**
		 * 온도1
		 */
		this.setFirstTemperature();
		
		/**
		 * 온도2
		 */
		this.setSecondTemperature();
	}
	
	// MonitoringPanel 세팅
	private void setMonitoringPanel() {
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 103, 762, 447);
		this.setVisible(true);
	}
	
	private void setZoneLabel() {
		labelZone = new JLabel("ZONE");
		labelZone.setBounds(330, 125, 50, 30);
		labelZone.setFont(zoneLabelFont);
		//this.add(labelZone);
	}
	
	// 입력전압 세팅
	private void setInputVolt() {
		infoInputVolt = new JLabel("[V]");
		infoInputVolt.setBounds(670, 117, 60, 30);
		infoInputVolt.setFont(zoneLabelFont);
		this.add(infoInputVolt);
		
		infoInputVoltData = new JLabel("000");
		infoInputVoltData.setFont(infoLabelFont);
		infoInputVoltData.setBounds(617, 115, 60, 30);
		this.add(infoInputVoltData);
		
		stateLabel[0] = new JLabel(images.getRed());
		stateLabel[0].setBounds(543, 141, 200, 30);
		this.add(stateLabel[0]);
		
		stateLabel[1] = new JLabel(images.getRed());
		stateLabel[1].setBounds(543, 141, 200, 30);
		this.add(stateLabel[1]);
		
		stateLabel[2] = new JLabel(images.getJuHwan());
		stateLabel[2].setBounds(543, 141, 200, 30);
		this.add(stateLabel[2]);
		
		stateLabel[3] = new JLabel(images.getGreen());
		stateLabel[3].setBounds(543, 141, 200, 30);
		this.add(stateLabel[3]);
		
		stateLabel[0].setVisible(false);
		stateLabel[1].setVisible(false);
		stateLabel[2].setVisible(false);
		stateLabel[3].setVisible(true);
	}
	
	private void setElectricCurrent() {
		infoElectricCurrent = new JLabel("[A]");
		infoElectricCurrent.setBounds(470, 220, 60, 30);
		infoElectricCurrent.setFont(zoneLabelFont);
		this.add(infoElectricCurrent);
		
		infoElectricCurrentData = new JLabel("111");
		infoElectricCurrentData.setFont(infoLabelFont);
		infoElectricCurrentData.setBounds(420, 217, 60, 30);
		this.add(infoElectricCurrentData);
		
		stateLabel[4] = new JLabel(images.getRed());
		stateLabel[4].setBounds(350, 245, 200, 30);
		this.add(stateLabel[4]);
		
		stateLabel[5] = new JLabel(images.getRed());
		stateLabel[5].setBounds(350, 245, 200, 30);
		this.add(stateLabel[5]);
		
		stateLabel[6] = new JLabel(images.getJuHwan());
		stateLabel[6].setBounds(350, 245, 200, 30);
		this.add(stateLabel[6]);
		
		stateLabel[7] = new JLabel(images.getGreen());
		stateLabel[7].setBounds(350, 245, 200, 30);
		this.add(stateLabel[7]);
		
		stateLabel[4].setVisible(false);
		stateLabel[5].setVisible(false);
		stateLabel[6].setVisible(false);
		stateLabel[7].setVisible(true);
	}
	
	private void setOutputVolt() {
		infoOutputVolt = new JLabel("[V]");
		infoOutputVolt.setFont(zoneLabelFont);
		infoOutputVolt.setBounds(670, 220, 60, 30);
		this.add(infoOutputVolt);
		
		infoOutputVoltData = new JLabel("222");
		infoOutputVoltData.setFont(infoLabelFont);
		infoOutputVoltData.setBounds(617, 217, 60, 30);
		this.add(infoOutputVoltData);
		
		stateLabel[8] = new JLabel(images.getRed());
		stateLabel[8].setBounds(543, 245, 200, 30);
		this.add(stateLabel[8]);
		
		stateLabel[9] = new JLabel(images.getRed());
		stateLabel[9].setBounds(543, 245, 200, 30);
		this.add(stateLabel[9]);
		
		stateLabel[10] = new JLabel(images.getJuHwan());
		stateLabel[10].setBounds(543, 245, 200, 30);
		this.add(stateLabel[10]);
		
		stateLabel[11] = new JLabel(images.getGreen());
		stateLabel[11].setBounds(543, 245, 200, 30);
		this.add(stateLabel[11]);
		
		stateLabel[8].setVisible(false);
		stateLabel[9].setVisible(false);
		stateLabel[10].setVisible(false);
		stateLabel[11].setVisible(true);
	}
	
	private void setFirstTemperature() {
		infoFirstTemperature = new JLabel("[℃]");
		infoFirstTemperature.setFont(zoneLabelFont);
		infoFirstTemperature.setBounds(470, 325, 60, 30);
		this.add(infoFirstTemperature);
		
		infoFirstTemperatureData = new JLabel("333");
		infoFirstTemperatureData.setFont(infoLabelFont);
		infoFirstTemperatureData.setBounds(420, 323, 60, 30);
		this.add(infoFirstTemperatureData);
		
		stateLabel[12] = new JLabel(images.getRed());
		stateLabel[12].setBounds(350, 350, 200, 30);
		this.add(stateLabel[12]);
		
		stateLabel[13] = new JLabel(images.getRed());
		stateLabel[13].setBounds(350, 350, 200, 30);
		this.add(stateLabel[13]);
		
		stateLabel[14] = new JLabel(images.getJuHwan());
		stateLabel[14].setBounds(350, 350, 200, 30);
		this.add(stateLabel[14]);
		
		stateLabel[15] = new JLabel(images.getGreen());
		stateLabel[15].setBounds(350, 350, 200, 30);
		this.add(stateLabel[15]);
		
		stateLabel[12].setVisible(false);
		stateLabel[13].setVisible(false);
		stateLabel[14].setVisible(false);
		stateLabel[15].setVisible(true);
	}
	
	private void setSecondTemperature() 
	{
		infoSecondTemperature = new JLabel("[℃]");
		infoSecondTemperature.setFont(zoneLabelFont);
		infoSecondTemperature.setBounds(660, 325, 60, 30);
		this.add(infoSecondTemperature);
		
		infoSecondTemperatureData = new JLabel("444");
		infoSecondTemperatureData.setFont(infoLabelFont);
		infoSecondTemperatureData.setBounds(617, 323, 60, 30);
		this.add(infoSecondTemperatureData);
		
		stateLabel[16] = new JLabel(images.getRed());
		stateLabel[16].setBounds(534, 350, 200, 30);
		this.add(stateLabel[16]);
		
		stateLabel[17] = new JLabel(images.getRed());
		stateLabel[17].setBounds(543, 350, 200, 30);
		this.add(stateLabel[17]);
		
		stateLabel[18] = new JLabel(images.getJuHwan());
		stateLabel[18].setBounds(543, 350, 200, 30);
		this.add(stateLabel[18]);
		
		stateLabel[19] = new JLabel(images.getGreen());
		stateLabel[19].setBounds(543, 350, 200, 30);
		this.add(stateLabel[19]);
		
		stateLabel[16].setVisible(false);
		stateLabel[17].setVisible(false);
		stateLabel[18].setVisible(false);
		stateLabel[19].setVisible(true);
	}
}
