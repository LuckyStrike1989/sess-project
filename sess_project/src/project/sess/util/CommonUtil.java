package project.sess.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.JOptionPane;

import gnu.io.SerialPort;
import project.sess.vo.OutputDataVO;

public class CommonUtil {
	/**
	 * �ø��� ��Ʈ ����
	 * @param type �ø�����Ʈ ���� Ÿ��
	 * @param vlues �ø�����Ʈ ���� ��
	 * @return
	 */
	public static int portSettingVlues(String type, String vlues) {
		int retValue = 0;
		
		if( "DATABITS".equals(type) ) {
			switch (vlues) {
				case "6":
					retValue = SerialPort.DATABITS_6;
					break;
				case "7":
					retValue = SerialPort.DATABITS_7;
					break;
				case "8":
					retValue = SerialPort.DATABITS_8;
					break;
				default:
					retValue = -2;
					break;
			}
		} else if( "STOPBITS".equals(type) ) {
			switch (vlues) {
				case "1":
					retValue = SerialPort.STOPBITS_1;
					break;
				case "2":
					retValue = SerialPort.STOPBITS_2;
					break;
				default:
					retValue = -3;
					break;
			}
		} else if( "PARITY_NONE".equals(type) ) {
			switch (vlues) {
				case "None":
					retValue = SerialPort.PARITY_NONE;
					break;
				case "Even":
					retValue = SerialPort.PARITY_EVEN;
					break;
				case "Odd":
					retValue = SerialPort.PARITY_ODD;
					break;
				default:
					retValue = -4;
					break;
			}
		} else {
			// ����
			retValue = -1;
		}
		
		return retValue;
	}
	
	/**
	 * Hex To ASCII ��ȯ
	 * @param strings ��Ŷ ������
	 * @return
	 */
	public static StringBuilder stringToASCII(String ... strings) {
		StringBuilder sb = new StringBuilder();
		
		sb.setLength(0);
		for(int i = 0; i<strings.length; i++) {
			int decimal = Integer.parseInt(strings[i], 16);
			sb.append((char) decimal);
		}
		
		return sb;
	}
	
	/**
	 * üũ��
	 * @param strings ��Ŷ ������
	 * @return
	 */
	public static boolean checksumConfirm(String[] strings) {
		// ���� : false / ���� : true
		boolean isPacketError = false;
		int sumData = 0;
		
		for(int i=1; i<=32; i++) {
			sumData += Integer.parseInt(strings[i], 16);
		}
		
		System.out.format("[CheckSum] : %x\n", (sumData & 0xff));
		
		isPacketError = ( strings[33].equals(Integer.toHexString((sumData & 0xff))) ) ? true : false;
		
		return isPacketError;
	}
	
	/**
	 * �Է����� ���ϱ�
	 * @param buffer ��Ŷ ������(16����)
	 * @return
	 */
	public static int makeInfoInputVolt(String[] buffer) {
		StringBuilder sb = new StringBuilder();
		
		sb.setLength(0);
		for(int i = 5; i<=8; i++) {
			int decimal = Integer.parseInt(buffer[i], 16);
			sb.append((char) decimal);
		}
		
		return Integer.parseInt(sb.toString());
	}
	
	/**
	 * ���� ���ϱ�
	 * @param buffer ��Ŷ ������(16����)
	 * @return
	 */
	public static int makeInfoElectricCurrent(String[] buffer) {
		StringBuilder sb = new StringBuilder();
		
		sb.setLength(0);
		for(int i = 9; i<=12; i++) {
			int decimal = Integer.parseInt(buffer[i], 16);
			sb.append((char) decimal);
		}
		
		return Integer.parseInt(sb.toString());
	}
	
	/**
	 * ������� ���ϱ�
	 * @param buffer ��Ŷ ������(16����)
	 * @return
	 */
	public static int makeInfoOutputVolt(String[] buffer) {
		StringBuilder sb = new StringBuilder();
		
		sb.setLength(0);
		for(int i = 13; i<=16; i++) {
			int decimal = Integer.parseInt(buffer[i], 16);
			sb.append((char) decimal);
		}
		
		return Integer.parseInt(sb.toString());
	}
	
	/**
	 * �µ�(�濭��) ���ϱ�
	 * @param buffer ��Ŷ ������(16����)
	 * @return
	 */
	public static double makeInfoFirstTemperature(String[] buffer) {
		StringBuilder sb = new StringBuilder();
		
		sb.setLength(0);
		for(int i = 17; i<=20; i++) {
			int decimal = Integer.parseInt(buffer[i], 16);
			sb.append((char) decimal);
		}
		
		sb.insert(3, ".");
		
		return Double.parseDouble(sb.toString()) ;
	}
	
	/**
	 * �µ�(CAP) ���ϱ�
	 * @param buffer ��Ŷ ������(16����)
	 * @return
	 */
	public static double makeInfoSecondTemperature(String[] buffer) {
		StringBuilder sb = new StringBuilder();
		
		sb.setLength(0);
		for(int i = 21; i<=24; i++) {
			int decimal = Integer.parseInt(buffer[i], 16);
			sb.append((char) decimal);
		}
		
		sb.insert(3, ".");
		
		return Double.parseDouble(sb.toString()) ;
	}
	
	/**
	 * ���� �ð�
	 * @return
	 */
	public static String currentDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss", Locale.KOREA);
		
		return format.format(date).toString();
	}
	
	/**
	 * �˷� â
	 * @param message �˷� �޽���
	 * @param title �˷� ����
	 */
	public static void showAlert(String message, String title, String type) {
		int alertType = 0;
		
		if( "error".equals(type) ) {
			alertType = JOptionPane.ERROR_MESSAGE;
		} else {
			alertType = JOptionPane.DEFAULT_OPTION;
		}
		
		JOptionPane.showMessageDialog(null, message, title, alertType);
	}
	
	/**
	 * ��Ŷ ����
	 * @param ringBuffer
	 * @param packBuffer
	 * @return
	 */
	public static String[] alignPacket(RingBuffer ringBuffer, RingBuffer packBuffer) {
		String[] data = ringBuffer.getQue();
		String[] retData;
		
		for(int i = ringBuffer.indexOf("2"); i <= ringBuffer.indexOf("2")+34; i++) {
			if( "2".equals(data[i]) ) {
				if( "3".equals(data[i+34]) ) {
					System.out.print("[Align Packet Log] : ");
					for(int j = i; j < i+35; j++) {
						System.out.print(data[j] + " ");
						packBuffer.enque(data[j].toString());
					}
					System.out.println();
					break;
				} else {
					ringBuffer.deque();
				}
			} else {
				ringBuffer.deque();
			}
		}
		
		retData = packBuffer.getQue();
		
		// ���� ���(����)
		ringBuffer.clear();
		packBuffer.clear();
		
		return retData;
	}
	
	/**
	 * json�����͸� ��������� HashMap
	 * @param outputData
	 * @return
	 */
	public static HashMap<String, String> getJsonHashMap(OutputDataVO outputData) {
		HashMap<String, String> jsonHashMap = new HashMap<String, String>();
		
		jsonHashMap.put("dateTime", CommonUtil.currentDateTime());
		jsonHashMap.put("inputVolt", outputData.getInfoInputVolt());
		jsonHashMap.put("electricCurrent", outputData.getInfoElectricCurrent());
		jsonHashMap.put("outputVolt", outputData.getInfoOutputVolt());
		jsonHashMap.put("firstTemperature", outputData.getInfoFirstTemperature());
		jsonHashMap.put("secondTemperature", outputData.getInfoSecondTemperature());
		
		return jsonHashMap;
	}
	
	/**
	 * getTextAreaOutput �� ����
	 * @param outputData
	 * @return
	 */
	public static String getTextAreaOutput(OutputDataVO outputData) {
		return " [ " + CommonUtil.currentDateTime() + " ]"
				+ "            " +   outputData.getInfoInputVolt() 
				+ "            " +   outputData.getInfoElectricCurrent() 
				+ "            " +   outputData.getInfoOutputVolt()
				+ "            " +   outputData.getInfoFirstTemperature()
				+ "            " +   outputData.getInfoSecondTemperature()
				+ "\n";
		
//		return " [ " + CommonUtil.currentDateTime() + " ]"
//				+ " �Է� ���� : " + outputData.getInfoInputVolt() 
//				+ " / ���� : " + outputData.getInfoElectricCurrent() 
//				+ " / ��� ���� : " + outputData.getInfoOutputVolt()
//				+ " / �µ�(�濭��) : " + outputData.getInfoFirstTemperature()
//				+ " / �µ�(CAP) : " + outputData.getInfoSecondTemperature()
//				+ "\n";
	}
	
	/**
	 * ���� ��Ʈ üũ
	 * @param data
	 * @return
	 */
	public static boolean checkStatusBit(String data) {
		return "80".equals(data) ? true : false;
	}
}