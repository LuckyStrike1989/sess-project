package project.sess.vo;

public class OutputDataVO {
	// �Է�����
	private String infoInputVolt = "0";
	
	// ����
	private String infoElectricCurrent = "0";
	
	// �������
	private String infoOutputVolt = "0";
	
	// �µ�(�濭��)
	private String infoFirstTemperature = "0";
	
	// �µ�(CAP)
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
