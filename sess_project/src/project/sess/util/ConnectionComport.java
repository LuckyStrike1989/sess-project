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

					serialPort.setSerialPortParams(Integer.parseInt(selectedSetting.getBaudRate()), // 통신속도
							CommonUtil.portSettingVlues("DATABITS", selectedSetting.getDataBits()), // 데이터 비트
							CommonUtil.portSettingVlues("STOPBITS", selectedSetting.getStopBits()), // stop 비트
							CommonUtil.portSettingVlues("PARITY_NONE", selectedSetting.getParity()) // 패리티 비트
					);
				} else {}
			}
		} catch (Exception e) {
			System.out.println("시리얼 포트("+ selectedSetting.getDevice().toString() +") 연결 실패");
			// e.printStackTrace();
		}
		
		return serialPort;
	}
	
	public static void closeComport() {
		serialPort.close();
		commPort.close();
	}
}
