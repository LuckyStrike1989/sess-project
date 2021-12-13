package project.sess.util;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import project.sess.vo.SelectedSettingVO;

public class ConnectionComport {
	private static SerialPort serialPort;
	private static CommPortIdentifier portIdentifier;
	private static CommPort commPort;
	
	public static SerialPort getConnection(SelectedSettingVO selectedSetting) {
		try {
			System.out.println("getDevice : " + selectedSetting.getDevice().toString());
			portIdentifier = CommPortIdentifier.getPortIdentifier(selectedSetting.getDevice().toString());
			
			if( portIdentifier.isCurrentlyOwned() ) {
				System.out.println("Error: Port is currently in use");
			} else {
				System.out.println("ComPort Open Success");
				
				commPort = portIdentifier.open(portIdentifier.getClass().getName(), 2000);
				
				if (commPort instanceof SerialPort) {
					serialPort = (SerialPort) commPort;

					System.out.println("Baudrate : " + selectedSetting.getBaudRate());
					System.out.println("DATABITS : " + selectedSetting.getDataBits() + " / "
							+ CommonUtil.portSettingVlues("DATABITS", selectedSetting.getDataBits()));
					System.out.println("STOPBITS : " + selectedSetting.getStopBits() + " / "
							+ CommonUtil.portSettingVlues("STOPBITS", selectedSetting.getStopBits()));
					System.out.println("PARITY_NONE : " + selectedSetting.getParity() + " / "
							+ CommonUtil.portSettingVlues("PARITY_NONE", selectedSetting.getParity()));

					serialPort.setSerialPortParams(Integer.parseInt(selectedSetting.getBaudRate()), // ��żӵ�
							CommonUtil.portSettingVlues("DATABITS", selectedSetting.getDataBits()), // ������ ��Ʈ
							CommonUtil.portSettingVlues("STOPBITS", selectedSetting.getStopBits()), // stop ��Ʈ
							CommonUtil.portSettingVlues("PARITY_NONE", selectedSetting.getParity()) // �и�Ƽ ��Ʈ
					);
				} else {}
			}
		} catch (Exception e) {
			System.out.println("�ø��� ��Ʈ("+ selectedSetting.getDevice().toString() +") ���� ����");
			// e.printStackTrace();
		}
		
		return serialPort;
	}
	
	public static void closeComport() {
		serialPort.close();
		commPort.close();
	}
}
