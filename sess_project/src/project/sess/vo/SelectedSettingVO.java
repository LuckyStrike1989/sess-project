package project.sess.vo;

public class SelectedSettingVO {
	private String device;
	private String baudRate;
	private String dataBits;
	private String stopBits;
	private String parity;
	
	public String getDevice() {
		return device;
	}
	
	public void setDevice(String device) {
		this.device = device;
	}
	
	public String getBaudRate() {
		return baudRate;
	}
	
	public void setBaudRate(String baudRate) {
		this.baudRate = baudRate;
	}
	
	public String getDataBits() {
		return dataBits;
	}
	
	public void setDataBits(String dataBits) {
		this.dataBits = dataBits;
	}
	
	public String getStopBits() {
		return stopBits;
	}
	
	public void setStopBits(String stopBits) {
		this.stopBits = stopBits;
	}
	
	public String getParity() {
		return parity;
	}
	
	public void setParity(String parity) {
		this.parity = parity;
	}
}
