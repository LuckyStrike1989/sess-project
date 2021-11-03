package project.sess.vo;

public class OutputDataVO {
	// 입력전압
	private String infoInputVolt = "0";
	
	// 전류
	private String infoElectricCurrent = "0";
	
	// 출력전압
	private String infoOutputVolt = "0";
	
	// 온동(방열판)
	private String infoFirstTemperature = "0";
	
	// 온도(CAP)
	private String infoSecondTemperature = "0";

	public String getInfoInputVolt() {
		return infoInputVolt;
	}

	public void setInfoInputVolt(String infoInputVolt) {
		this.infoInputVolt = infoInputVolt;
	}

	public String getInfoElectricCurrent() {
		return infoElectricCurrent;
	}

	public void setInfoElectricCurrent(String infoElectricCurrent) {
		this.infoElectricCurrent = infoElectricCurrent;
	}

	public String getInfoOutputVolt() {
		return infoOutputVolt;
	}

	public void setInfoOutputVolt(String infoOutputVolt) {
		this.infoOutputVolt = infoOutputVolt;
	}

	public String getInfoFirstTemperature() {
		return infoFirstTemperature;
	}

	public void setInfoFirstTemperature(String infoFirstTemperature) {
		this.infoFirstTemperature = infoFirstTemperature;
	}

	public String getInfoSecondTemperature() {
		return infoSecondTemperature;
	}

	public void setInfoSecondTemperature(String infoSecondTemperature) {
		this.infoSecondTemperature = infoSecondTemperature;
	}
}
