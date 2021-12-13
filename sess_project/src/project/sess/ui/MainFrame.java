package project.sess.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.text.BadLocationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gnu.io.SerialPort;
import project.sess.util.CommonUtil;
import project.sess.util.ConnectionComport;
import project.sess.util.RingBuffer;
import project.sess.util.SaveFileManage;
import project.sess.vo.ImageVO;
import project.sess.vo.OutputDataVO;
import project.sess.vo.SelectedSettingVO;

public class MainFrame extends JFrame
{
	/*
	 * 
	 * serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 4926262734808408462L;
	private ImageVO image = new ImageVO();
	private Container container;

	private TopPanel topPanel;
	private MiddlePanel middlePanel;
	private MonitoringPanel monitoringPanel;
	private PowerControlPanel powerControlPanel;
	private GraphPanel graphPanel;
	private SettingPanel settingPanel;

	private SerialPort serialPort;
	private InputStream in;
	
	private Runnable runnable;
	private Thread thread;

	private SelectedSettingVO selectedSetting = new SelectedSettingVO();
	private OutputDataVO outputData = new OutputDataVO();
	
	private boolean captureDataStart = false;
	
	private JSONObject jsonObject;
	private JSONArray jsonObjectArray = new JSONArray();
	
	private byte[] buffer = new byte[35];
	private int[] bufferToInt = new int[35];
	private String[] bufferToString = new String[35];
	
	private RingBuffer ringBuffer = new RingBuffer(140);
	private RingBuffer packBuffer = new RingBuffer(35);
	
	private int count = 1;
	
	public MainFrame() {
		this.settingFrame();
		/**
		 * Button Action
		 */
		// monitoringButton Click
		topPanel.monitoringButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				monitoringPanel.setVisible(true);
				powerControlPanel.setVisible(false);
				graphPanel.setVisible(false);
				settingPanel.setVisible(false);

				topPanel.monitoringButton.setIcon(image.getGlobal_botton1_selected());
				topPanel.deviceControlButton.setIcon(image.getGlobal_botton2());
				topPanel.graphButton.setIcon(image.getGlobal_botton3());
				topPanel.settingButton.setIcon(image.getGlobal_botton4());
			}
		});

		// deviceControlButton Click
		topPanel.deviceControlButton.addActionListener(new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent e) {
				monitoringPanel.setVisible(false);
				powerControlPanel.setVisible(true);
				graphPanel.setVisible(false);
				settingPanel.setVisible(false);

				topPanel.monitoringButton.setIcon(image.getGlobal_botton1());
				topPanel.deviceControlButton.setIcon(image.getGlobal_botton2_selected());
				topPanel.graphButton.setIcon(image.getGlobal_botton3());
				topPanel.settingButton.setIcon(image.getGlobal_botton4());
			}
		});

		// graphButton Click
		topPanel.graphButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				monitoringPanel.setVisible(false);
				powerControlPanel.setVisible(false);
				graphPanel.setVisible(true);
				settingPanel.setVisible(false);

				topPanel.monitoringButton.setIcon(image.getGlobal_botton1());
				topPanel.deviceControlButton.setIcon(image.getGlobal_botton2());
				topPanel.graphButton.setIcon(image.getGlobal_botton3_selected());
				topPanel.settingButton.setIcon(image.getGlobal_botton4());
			}
		});

		// settingButton Click
		topPanel.settingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				monitoringPanel.setVisible(false);
				powerControlPanel.setVisible(false);
				graphPanel.setVisible(false);
				settingPanel.setVisible(true);

				topPanel.monitoringButton.setIcon(image.getGlobal_botton1());
				topPanel.deviceControlButton.setIcon(image.getGlobal_botton2());
				topPanel.graphButton.setIcon(image.getGlobal_botton3());
				topPanel.settingButton.setIcon(image.getGlobal_botton4_selected());
			}
		});
		
		// Clear ��ư ����
		powerControlPanel.buttonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					powerControlPanel
					.textArea
					.getDocument()
					.remove(0, powerControlPanel.textArea.getDocument().getLength());
					
					if( !jsonObjectArray.isEmpty() ) {
						jsonObjectArray.clear();
						
						CommonUtil.showAlert("������ �ʱ�ȭ �Ϸ�!", "����(success)", "default");
						System.out.println("[System Log] : ������ �α� �ʱ�ȭ");
					}
					
					powerControlPanel.buttonSaveAs.setEnabled(false);
					powerControlPanel.buttonSave.setEnabled(false);
					
					captureDataStart = false;
				} catch (BadLocationException ex) {
					CommonUtil.showAlert("������ �ʱ�ȭ ���� �߽��ϴ�. �����ڿ��� �����ϼ���!", "����(Error)", "error");
					System.out.println("[System Log] : �����͸� �ʱ�ȭ ���� ���߽��ϴ�.");
					ex.printStackTrace();
				}
			}
		});
		
		powerControlPanel.buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( !jsonObjectArray.isEmpty() ) {
					new SaveFileManage(jsonObjectArray, "save");
					
					if( !jsonObjectArray.isEmpty() ) {
						jsonObjectArray.clear();
					}
					
					powerControlPanel.buttonSaveAs.setEnabled(false);
					powerControlPanel.buttonSave.setEnabled(false);
					
					powerControlPanel.textArea.append(" ["+ CommonUtil.currentDateTime() +"] ������ => [ C:/sess_monitoring/ ]\n");
				} else {
					CommonUtil.showAlert("����� �����Ͱ� �����ϴ�. �����ڿ��� �����ϼ���!", "����(Error)", "error");
				}
			}
		});
		
		powerControlPanel.buttonSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( !jsonObjectArray.isEmpty() ) {
					new SaveFileManage(jsonObjectArray, "saveas");
					
					if( !jsonObjectArray.isEmpty() ) {
						jsonObjectArray.clear();
					}
					
					powerControlPanel.buttonSaveAs.setEnabled(false);
					powerControlPanel.buttonSave.setEnabled(false);
					
					powerControlPanel.textArea.append(" ["+ CommonUtil.currentDateTime() +"] ���� �Ϸ�\n");
				} else {
					CommonUtil.showAlert("����� �����Ͱ� �����ϴ�. �����ڿ��� �����ϼ���!", "����(Error)", "error");
				}
			}
		});
		
		powerControlPanel.buttonWriteData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if( serialPort!=null ) {
					if( !captureDataStart ) {
						captureDataStart = true;
						
						powerControlPanel.buttonClear.setEnabled(false);
						powerControlPanel.buttonSaveAs.setEnabled(false);
						powerControlPanel.buttonSave.setEnabled(false);
						powerControlPanel.buttonWriteData.setText("������ ��� ����");

						powerControlPanel.textArea.append(" ["+ CommonUtil.currentDateTime() +"] ������ ��� ����\n");
						powerControlPanel.textArea.append(" ["+ CommonUtil.currentDateTime() +"] [�Է� ����] | [����] | [��� ����] | [�µ�(�濭��)] | [�µ�(CAP)]\n");
					} 
					else 
					{
						captureDataStart = false;
						
						powerControlPanel.buttonClear.setEnabled(true);
						powerControlPanel.buttonSaveAs.setEnabled(true);
						powerControlPanel.buttonSave.setEnabled(true);
						powerControlPanel.buttonWriteData.setText("������ ��� ����");
						//powerControlPanel.buttonWriteData.setText("�Է� ����  | ����  | ��� ����  | �µ�(�濭��) | �µ�(CAP)");
						
						powerControlPanel.textArea.append(" ["+ CommonUtil.currentDateTime() +"] ������ ��� ����\n");
					}
				} else {
					CommonUtil.showAlert("�ø��� ��Ʈ�� ���� �ȉ���ϴ�. ���� �������ּ���.", "����(Error)", "error");
				}
			}
		});

		// settingPanel > serialPortConnect Click
		settingPanel.serialPortConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ������ ����
				selectedSetting.setDevice(settingPanel.device.getSelectedItem().toString());
				selectedSetting.setBaudRate(settingPanel.baudRate.getSelectedItem().toString());
				selectedSetting.setDataBits(settingPanel.dataBits.getSelectedItem().toString());
				selectedSetting.setStopBits(settingPanel.stopBits.getSelectedItem().toString());
				selectedSetting.setParity(settingPanel.parity.getSelectedItem().toString());

				System.out.println(selectedSetting.getDevice());
				serialPort = ConnectionComport.getConnection(selectedSetting);
				
				if( serialPort!=null ) {
					// �þ���Ʈ ���(���׺�) ������
					runnable = new SerialPortProcess();
					thread = new Thread(runnable);

					thread.setName("SerialPort");
					thread.start();
					
					settingPanel.serialPortConnect.setText("���Ӽ���");
					
					settingPanel.device.setEnabled(false);
					settingPanel.baudRate.setEnabled(false);
					settingPanel.dataBits.setEnabled(false);
					settingPanel.stopBits.setEnabled(false);
					settingPanel.parity.setEnabled(false);
					
					settingPanel.serialPortConnect.setEnabled(false);
					settingPanel.serialPortClose.setEnabled(true);
					
					CommonUtil.showAlert("�ø��� ��Ʈ ����("+ settingPanel.device.getSelectedItem().toString() +")", "����(Success)", "default");
				} else {
					CommonUtil.showAlert("���� ���� �߽��ϴ�. ���ð��� Ȯ���� �ּ���!", "����(Error)", "error");
				}
			}
		});
		
		// settingPanel > serialPortClose Click
		settingPanel.serialPortClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thread.interrupt();

				ConnectionComport.closeComport();
				
				settingPanel.serialPortConnect.setText("����");
				
				settingPanel.device.setEnabled(true);
				settingPanel.baudRate.setEnabled(true);
				settingPanel.dataBits.setEnabled(true);
				settingPanel.stopBits.setEnabled(true);
				settingPanel.parity.setEnabled(true);
				
				settingPanel.serialPortConnect.setEnabled(true);
				settingPanel.serialPortClose.setEnabled(false);
				
				CommonUtil.showAlert("�ø��� ��� ����", "����(Success)", "default");
			}
		});
	}

	// JFrame ����
	private void settingFrame() {

		this.setTitle("Power Monitor System");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img_logo = toolkit.getImage(getClass().getClassLoader().getResource("sess_logo.png"));
		this.setIconImage(img_logo);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(762, 580);
		this.setResizable(false);
		this.setVisible(true);

		// Panel ����
		topPanel = this.drawTopPanel();
		middlePanel = this.drawMiddlePanel();
		monitoringPanel = this.drawMonitoringPanel();
		powerControlPanel = this.drawPowerControlPanel();
		graphPanel = this.drawGraphPanel();
		settingPanel = this.drawSettingPanel();

		// Container ����
		container = getContentPane();
		container.setLayout(null);

		container.add(topPanel);
		container.add(middlePanel);
		container.add(monitoringPanel);
		container.add(powerControlPanel);
		container.add(graphPanel);
		container.add(settingPanel);

		topPanel.repaint();
		middlePanel.repaint();
		monitoringPanel.repaint();
	}

	// TopPanel �׸���
	private TopPanel drawTopPanel() {
		TopPanel top_panel = new TopPanel(image) {
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 6048562697869660068L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getGlobal_title_tab(), 0, 0, this);
			}
		};
		top_panel.setLayout(new BorderLayout());
		top_panel.setBounds(0, 0, 762, 70);

		return top_panel;
	}

	// MiddlePanel �׸���
	private MiddlePanel drawMiddlePanel() {
		MiddlePanel middle_panel = new MiddlePanel() {
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = -1707340281267587718L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getGlobal_middle_tab(), -1, 0, this);
			}
		};
		middle_panel.setBounds(0, 70, 762, 33);

		return middle_panel;
	}

	// MonitoringPanel �׸���
	private MonitoringPanel drawMonitoringPanel() {
		MonitoringPanel Monitoring_Panel = new MonitoringPanel(image) {
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = -576075715766043545L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getMonitoring_BackGround(), -1, 0, this);
			}
		};

		return Monitoring_Panel;
	}

	// powerControlPanel �׸���
	private PowerControlPanel drawPowerControlPanel() {
		PowerControlPanel Power_control_panel = new PowerControlPanel() {
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 2518309728115677375L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getFanControl_BackGround(), -1, 0, this);
			}
		};

		return Power_control_panel;
	}

	// GraphPanel �׸���
	private GraphPanel drawGraphPanel() {
		GraphPanel graph_panel = new GraphPanel() {
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = 8622604382479705480L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getGraph_BackGround(), 1, -1, this);
			}
		};

		return graph_panel;
	}

	// SettingPanel �׸���
	private SettingPanel drawSettingPanel() {
		SettingPanel setting_panel = new SettingPanel() {
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = -576075715766043545L;

			protected void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(image.getSetting_BackGround(), 0, 0, this);
			}
		};
		return setting_panel;
	}

	// ��Ŷ ó�� ������
	private class SerialPortProcess implements Runnable
	{		
		@Override
		public void run() {
			while ( true ) {
				try {
					Thread.sleep(1000);
					
					while( ringBuffer.size()!=ringBuffer.capacity() ) {
						in = serialPort.getInputStream();
						in.read(buffer, 0, buffer.length);
						
						for (int i = 0; i < buffer.length; i++) {
							bufferToInt[i] = (int) (buffer[i] & 0xff);
							bufferToString[i] = Integer.toHexString(bufferToInt[i]);	
						}
						
						for(int i = 0; i < bufferToString.length; i++) {
							ringBuffer.enque(bufferToString[i].toString().trim());	
						}
					}
					
					// ������ ������ ���
					ringBuffer.dump();
					
					bufferToString = CommonUtil.alignPacket(ringBuffer, packBuffer);
					
					ringBuffer.clear();
					packBuffer.clear();
					
					// ���� ��Ʈ üũ
					if( !CommonUtil.checkStatusBit(bufferToString[4]) ) {
						System.out.println("[Status Bit] : " + bufferToString[4] + " => �̻�");
						System.out.println("[System Log] : �ø��� ��Ʈ�� ����� ���������ϴ�.");					
						thread.interrupt();					
						ConnectionComport.closeComport();					
						settingPanel.serialPortConnect.setText("����");
						
						settingPanel.device.setEnabled(true);
						settingPanel.baudRate.setEnabled(true);
						settingPanel.dataBits.setEnabled(true);
						settingPanel.stopBits.setEnabled(true);
						settingPanel.parity.setEnabled(true);
						
						settingPanel.serialPortConnect.setEnabled(true);
						settingPanel.serialPortClose.setEnabled(false);
						
						CommonUtil.showAlert("�ø��� ����� ���������ϴ�. ����� Ȯ�����ּ���.", "����(Error)", "error");
						break;
					} else {
						System.out.println("[Status Bit] : " + bufferToString[4] +" => ����");
					}
					
					
					if (CommonUtil.checksumConfirm(bufferToString)) 
					{
						outputData.setInfoInputVolt(String.valueOf(CommonUtil.makeInfoInputVolt(bufferToString)));					// �Է� ����
						outputData.setInfoElectricCurrent(String.valueOf(CommonUtil.makeInfoElectricCurrent(bufferToString)));			// ����
						outputData.setInfoOutputVolt(String.valueOf(CommonUtil.makeInfoOutputVolt(bufferToString)));				// ��� ����
						outputData.setInfoFirstTemperature(String.valueOf(CommonUtil.makeInfoFirstTemperature(bufferToString)));	// �µ�(�濭��)
						outputData.setInfoSecondTemperature(String.valueOf(CommonUtil.makeInfoSecondTemperature(bufferToString)));	// �µ�(CAP)

						middlePanel.info_input_volt.setText(outputData.getInfoInputVolt());
						middlePanel.info_electric_current.setText(outputData.getInfoElectricCurrent());
						middlePanel.info_output_volt.setText(outputData.getInfoOutputVolt());
						middlePanel.info_first_temperature.setText(outputData.getInfoFirstTemperature());
						middlePanel.info_second_temperature.setText(outputData.getInfoSecondTemperature());

						monitoringPanel.infoInputVoltData.setText(outputData.getInfoInputVolt());
						monitoringPanel.infoElectricCurrentData.setText(outputData.getInfoElectricCurrent());
						monitoringPanel.infoOutputVoltData.setText(outputData.getInfoOutputVolt());
						monitoringPanel.infoFirstTemperatureData.setText(outputData.getInfoFirstTemperature());
						monitoringPanel.infoSecondTemperatureData.setText(outputData.getInfoSecondTemperature());
						
						if( captureDataStart ) {
							jsonObject = new JSONObject(CommonUtil.getJsonHashMap(outputData));
							
							jsonObjectArray.add(jsonObject);
							
							powerControlPanel.textArea.append(CommonUtil.getTextAreaOutput(outputData));
							
							powerControlPanel
							.scrollPane
							.getVerticalScrollBar()
							.setValue(powerControlPanel.scrollPane.getVerticalScrollBar().getMaximum());
						}
						
							double[][] inputVoltDatas = graphPanel.getInputVoltData(count, Double.parseDouble(outputData.getInfoInputVolt()));
							double[][] electricCurrentDatas = graphPanel.getElectricCurrentData(count, Double.parseDouble(outputData.getInfoElectricCurrent()));
							double[][] outputVoltDatas = graphPanel.getOutputVoltData(count, Double.parseDouble(outputData.getInfoOutputVolt()));
							
							graphPanel.chart.updateXYSeries("�Է�����", inputVoltDatas[0], inputVoltDatas[1], null);
							graphPanel.chart.updateXYSeries("����[A]", electricCurrentDatas[0], electricCurrentDatas[1], null);
							graphPanel.chart.updateXYSeries("�������[V]", outputVoltDatas[0], outputVoltDatas[1], null);
							graphPanel.chart.updateXYSeries("����[P]", inputVoltDatas[0], inputVoltDatas[1], null);
							graphPanel.graph.repaint();
							
							count++;
					}
				} 
				catch (InterruptedException e) 
				{
					System.out.println("[System Log] : ������ �� ����Ʈ ���� ����");
					break;
				}
				catch (Exception e) 
				{
					System.out.println("[System Log] : �ø��� ��Ʈ�� ����� ���������ϴ�.");					
					thread.interrupt();					
					ConnectionComport.closeComport();					
					settingPanel.serialPortConnect.setText("����");
					
					settingPanel.device.setEnabled(true);
					settingPanel.baudRate.setEnabled(true);
					settingPanel.dataBits.setEnabled(true);
					settingPanel.stopBits.setEnabled(true);
					settingPanel.parity.setEnabled(true);
					
					settingPanel.serialPortConnect.setEnabled(true);
					settingPanel.serialPortClose.setEnabled(false);
					
					CommonUtil.showAlert("�ø��� ����� ���������ϴ�. ����� Ȯ�����ּ���.", "����(Error)", "error");
					break;
				}
			}
		}
	}
}