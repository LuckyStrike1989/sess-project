package project.sess.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Random;

import javax.swing.JFrame;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import project.sess.util.CommonUtil;
import project.sess.vo.ImageVO;
import project.sess.vo.OutputDataVO;
import project.sess.vo.SelectedSettingVO;

public class MainFrame extends JFrame {
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
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;
	private InputStream in;
	
	private Runnable runnable;
	private Thread thread;
	private boolean threadKill = false;

	private SelectedSettingVO selectedSetting = new SelectedSettingVO();
	private OutputDataVO outputData = new OutputDataVO();

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
		topPanel.deviceControlButton.addActionListener(new ActionListener() {
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

		// settingPanel > serialPortConnect Click
		settingPanel.serialPortConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 쓰레드 실행
				if (!thread.isAlive()) {
					selectedSetting.setDevice(settingPanel.device.getSelectedItem().toString());
					selectedSetting.setBaudRate(settingPanel.baudRate.getSelectedItem().toString());
					selectedSetting.setDataBits(settingPanel.dataBits.getSelectedItem().toString());
					selectedSetting.setStopBits(settingPanel.stopBits.getSelectedItem().toString());
					selectedSetting.setParity(settingPanel.parity.getSelectedItem().toString());

					System.out.println(selectedSetting.getDevice());

					threadKill = false;
					
					if( !thread.isAlive() ) {
						thread.setName("SerialPort");
						thread.start();
					}
				}
			}
		});
		
		// settingPanel > serialPortClose Click
		settingPanel.serialPortClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				threadKill = true;
				// serialPort.close();
				// thread.interrupt();
			}
		});
	}

	// JFrame 세팅
	private void settingFrame() {

		this.setTitle("Power Monitor System");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img_logo = toolkit.getImage("./images/sess_logo.png");
		this.setIconImage(img_logo);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(762, 580);
		this.setResizable(false);
		this.setVisible(true);

		// Panel 세팅
		topPanel = this.drawTopPanel();
		middlePanel = this.drawMiddlePanel();
		monitoringPanel = this.drawMonitoringPanel();
		powerControlPanel = this.drawPowerControlPanel();
		graphPanel = this.drawGraphPanel();
		settingPanel = this.drawSettingPanel();

		// Container 세팅
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

		// 시얼포트 통신(지그비) 쓰레드
		runnable = new SerialPortProcess();
		thread = new Thread(runnable);
	}

	// TopPanel 그리기
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

	// MiddlePanel 그리기
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

	// MonitoringPanel 그리기
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

	// powerControlPanel 그리기
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

	// GraphPanel 그리기
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

	// SettingPanel 그리기
	private SettingPanel drawSettingPanel() {
		SettingPanel setting_panel = new SettingPanel() {
			/**
			 * serialVersionUID
			 */
			private static final long serialVersionUID = -576075715766043545L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image.getSetting_BackGround(), 0, 0, this);
			}
		};

		return setting_panel;
	}

	// 패킷 처리 쓰레드
	private class SerialPortProcess implements Runnable {
		

		private final Random random = new Random();

		private byte[] buffer = new byte[35];
		private int[] bufferToInt = new int[35];
		private String[] bufferToString = new String[35];
		
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println("- " + threadName + " has been started");

			/* SerialPort Open */
			try {
				System.out.println("getDevice : " + selectedSetting.getDevice().toString());
				portIdentifier = CommPortIdentifier.getPortIdentifier(selectedSetting.getDevice().toString());

				if (portIdentifier.isCurrentlyOwned()) {
					System.out.println("Error: Port is currently in use");
				} else {
					System.out.println("ComPort Open Success");

					commPort = portIdentifier.open(this.getClass().getName(), 2000);

					if (commPort instanceof SerialPort) {
						serialPort = (SerialPort) commPort;

						System.out.println("Baudrate : " + selectedSetting.getBaudRate());
						System.out.println("DATABITS : " + selectedSetting.getDataBits() + " / "
								+ CommonUtil.portSettingVlues("DATABITS", selectedSetting.getDataBits()));
						System.out.println("STOPBITS : " + selectedSetting.getStopBits() + " / "
								+ CommonUtil.portSettingVlues("STOPBITS", selectedSetting.getStopBits()));
						System.out.println("PARITY_NONE : " + selectedSetting.getParity() + " / "
								+ CommonUtil.portSettingVlues("PARITY_NONE", selectedSetting.getParity()));

						serialPort.setSerialPortParams(Integer.parseInt(selectedSetting.getBaudRate()), // 통신속도
								CommonUtil.portSettingVlues("DATABITS", selectedSetting.getDataBits()), // 데이터 비트
								CommonUtil.portSettingVlues("STOPBITS", selectedSetting.getStopBits()), // stop 비트
								CommonUtil.portSettingVlues("PARITY_NONE", selectedSetting.getParity()) // 패리티 비트
						);
					} else {

					}
				}

				while (!threadKill) {

					int delay = 1000 + random.nextInt(4000);

					try {
						Thread.sleep(1000);
  
						in = serialPort.getInputStream();
						in.read(buffer, 0, buffer.length);

						for (int i = 0; i < buffer.length; i++) {
							bufferToInt[i] = (int) (buffer[i] & 0xff);
							bufferToString[i] = Integer.toHexString(bufferToInt[i]);
						}

						System.out.print("[Log] : ");
						for (int i = 0; i < bufferToString.length; i++) {
							System.out.print(bufferToString[i] + " ");
						}
						System.out.println("");
						
						if (CommonUtil.checksumConfirm(bufferToString)) {
							outputData.setInfoInputVolt(CommonUtil.makeInfoInputVolt(bufferToString));					// 입력 전압
							outputData.setInfoElectricCurrent(CommonUtil.makeInfoInputVolt(bufferToString));			// 전류
							outputData.setInfoOutputVolt(CommonUtil.makeInfoOutputVolt(bufferToString));				// 출력 전압
							outputData.setInfoFirstTemperature(CommonUtil.makeInfoFirstTemperature(bufferToString));	// 온도(방열판)
							outputData.setInfoSecondTemperature(CommonUtil.makeInfoSecondTemperature(bufferToString));	// 온도(CAP)
							
							middlePanel.info_input_volt.setText(outputData.getInfoInputVolt());
							middlePanel.info_electric_current.setText(outputData.getInfoInputVolt());
							middlePanel.info_output_volt.setText(outputData.getInfoOutputVolt());
							middlePanel.info_first_temperature.setText(outputData.getInfoFirstTemperature());
							middlePanel.info_second_temperature.setText(outputData.getInfoSecondTemperature());

							monitoringPanel.infoInputVoltData.setText(outputData.getInfoInputVolt());
							monitoringPanel.infoElectricCurrentData.setText(outputData.getInfoInputVolt());
							monitoringPanel.infoOutputVoltData.setText(outputData.getInfoOutputVolt());
							monitoringPanel.infoFirstTemperatureData.setText(outputData.getInfoFirstTemperature());
							monitoringPanel.infoSecondTemperatureData.setText(outputData.getInfoSecondTemperature());

							// phase += 2 * Math.PI * 2 / 20.0;
							// System.out.println("phase : " + phase);
							//double[][] data = graphPanel.getSineData(delay);

							//graphPanel.chart.updateXYSeries("sine", data[1], data[1], null);
							
							graphPanel.graph.repaint();
						} else 
						{

						}
					} catch (Exception e) 
					{
						System.out.println("Error ComPort Close");
						serialPort.close();
						e.printStackTrace();
					}
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}