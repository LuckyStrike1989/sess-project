package project.sess.vo;

public class SettingVO {
	private String deviceItems[] = 
	{
		"COM1", "COM2", "COM3"
		, "COM4", "COM5", "COM6"
		, "COM7", "COM8", "COM9"
		, "COM10", "COM11", "COM12"
		, "COM13", "COM14", "COM15"
		, "COM16", "COM17", "COM18"
		, "COM19", "COM20", "COM21"
		, "COM22", "COM23", "COM24"
	};
	
	private String baudRate[] = {
			"9600", "19200", "38400", "57600","115200"  //"9600",
	};
	
	private String dataBits[] = {
			"8", "7", "6"
	};
	
	private String stopBits[] = {
			"1", "2"
	};

	
	private String parity[] = {
			"None","Even","Odd"
	};
	

	public String[] getDeviceItems() {
		return deviceItems;
	}

	public void setDeviceItems(String[] deviceItems) {
		this.deviceItems = deviceItems;
	}

	public String[] getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(String[] baudRate) {
		this.baudRate = baudRate;
	}

	public String[] getDataBits() {
		return dataBits;
	}

	public void setDataBits(String[] dataBits) {
		this.dataBits = dataBits;
	}

	public String[] getStopBits() {
		return stopBits;
	}

	public void setStopBits(String[] stopBits) {
		this.stopBits = stopBits;
	}

	public String[] getParity() {
		return parity;
	}

	public void setParity(String[] parity) {
		this.parity = parity;
	}
}
