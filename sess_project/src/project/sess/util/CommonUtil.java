package project.sess.util;

import gnu.io.SerialPort;

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
		
		System.out.format("CheckSum : %x\n", (sumData & 0xff));
		
		isPacketError = ( strings[33].equals(Integer.toHexString((sumData & 0xff))) ) ? true : false;
		
		return isPacketError;
	}
}
