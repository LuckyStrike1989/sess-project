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
	 * 시리얼 포트 세팅
	 * @param type 시리얼포트 세팅 타입
	 * @param vlues 시리얼포트 세팅 값
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
			// 에러
			retValue = -1;
		}
		
		return retValue;
	}
	
	/**
	 * Hex To ASCII 변환
	 * @param strings 패킷 데이터
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
	 * 체크썸
	 * @param strings 패킷 데이터
	 * @return
	 */
	public static boolean checksumConfirm(String[] strings) {
		// 에러 : false / 정상 : true
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
	 * 입력전압 구하기
	 * @param buffer 패킷 데이터(16진수)
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
	 * 전류 구하기
	 * @param buffer 패킷 데이터(16진수)
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
	 * 출력전압 구하기
	 * @param buffer 패킷 데이터(16진수)
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
	 * 온도(방열판) 구하기
	 * @param buffer 패킷 데이터(16진수)
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
	 * 온도(CAP) 구하기
	 * @param buffer 패킷 데이터(16진수)
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
	 * 현재 시간
	 * @return
	 */
	public static String currentDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss", Locale.KOREA);
		
		return format.format(date).toString();
	}
	
	/**
	 * 알럿 창
	 * @param message 알럿 메시지
	 * @param title 알럿 제목
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
	 * 패킷 정렬
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
		
		// 버포 비움(리셋)
		ringBuffer.clear();
		packBuffer.clear();
		
		return retData;
	}
	
	/**
	 * json데이터를 만들기위한 HashMap
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
	 * getTextAreaOutput 값 리턴
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
//				+ " 입력 전압 : " + outputData.getInfoInputVolt() 
//				+ " / 전류 : " + outputData.getInfoElectricCurrent() 
//				+ " / 출력 전압 : " + outputData.getInfoOutputVolt()
//				+ " / 온도(방열판) : " + outputData.getInfoFirstTemperature()
//				+ " / 온도(CAP) : " + outputData.getInfoSecondTemperature()
//				+ "\n";
	}
	
	/**
	 * 상태 비트 체크
	 * @param data
	 * @return
	 */
	public static boolean checkStatusBit(String data) {
		return "80".equals(data) ? true : false;
	}
}