package project.sess.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class SaveFileManage {
	private String SheetName = "log"; // ��Ʈ �̸�
	private String FilePath = "C:/sess_monitoring/"; // ���� ��� �� ���ϸ�
	
	private WritableWorkbook workbook;
	private WritableSheet sheet;
	private BufferedWriter outFile = null;
	private JSONArray jsonArrayData;
	
	private String[] sel_message = { "xls", "txt" };
	private String[] header = { "����", "��¥", "�Է�����", "����", "�������", "�µ�(�濭��)", "�µ�(CAP)" }; // �÷���
	
	private String input = (String) JOptionPane.showInputDialog(null, "������ Ÿ���� �����ϼ���.", "����", JOptionPane.INFORMATION_MESSAGE, null, sel_message, "Ÿ�Լ���");
	
	public SaveFileManage(JSONArray jsonArrayData, String type) {
		this.jsonArrayData = jsonArrayData;
		
		boolean typeCompare = ( "save".equals(type) ) ? true : false;
		
		if( typeCompare ) {
			if( "xls".equals(input) ) {
				this.saveExcelFile();
			} else {
				this.saveTextFile();
			}
		} else {
			this.saveAsExcelFile();
		}		
	}
	
	private void saveExcelFile() {
		this.jsonLogWrite();
		
		File makeDir = new File(FilePath);
		
		if( !makeDir.isDirectory() ) {
			System.out.println("[System Log] �b�ΰ� �������� �ʽ��ϴ�. ��θ� �����մϴ�.");
			makeDir.mkdir();
		}
		
		try {			
			workbook = Workbook.createWorkbook(new File(FilePath + CommonUtil.currentDateTime() + "." + input)); // Ư����ο� �ڵ�����
			sheet = workbook.createSheet(SheetName, 0); // ��Ʈ�̸��� ���° ��Ʈ�ε���
			
			jxl.write.WritableCellFormat format_column = new WritableCellFormat(); // �÷� ����. ��Ʈ��
			format_column.setBackground(jxl.format.Colour.GRAY_25); 							// �÷� ��׶��� �� ����.
			format_column.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); 	// �÷� �������� ��Ÿ�� ����.
			
			// �÷��� �ֱ�
			for (int i = 0; i < header.length; i++) { 
				Label label = new jxl.write.Label(i, 0, header[i], format_column);
				sheet.addCell(label);
			}
			
			// ������ �ֱ�. �÷����� 0���� ���Ƿ� �����ʹ� 1���� �ִ´�.
			for (int i = 0; i < this.jsonArrayData.size(); i++) { 
				JSONObject resultData = (JSONObject) this.jsonArrayData.get(i);
				
				sheet.addCell(new jxl.write.Label(0, i + 1, String.valueOf((i+1))));
				sheet.addCell(new jxl.write.Label(1, i + 1, resultData.get("dateTime").toString()));
				sheet.addCell(new jxl.write.Label(2, i + 1, resultData.get("outputVolt").toString()));
				sheet.addCell(new jxl.write.Label(3, i + 1, resultData.get("electricCurrent").toString()));
				sheet.addCell(new jxl.write.Label(4, i + 1, resultData.get("inputVolt").toString()));
				sheet.addCell(new jxl.write.Label(5, i + 1, resultData.get("firstTemperature").toString()));
				sheet.addCell(new jxl.write.Label(6, i + 1, resultData.get("secondTemperature").toString()));
			}
			
			workbook.write(); // ���� ����
			workbook.close(); // ���� �ݱ�
			
			this.jsonArrayData.clear();
			this.fileWirteSuccessMessage();
			CommonUtil.showAlert(FilePath + CommonUtil.currentDateTime() 
					+ "." + input + " ����Ǿ����ϴ�.", "����(Success)", "default");
		} catch (Exception e) {
			System.out.println("[Error Log] : ���� ���� ���� ����");
			e.printStackTrace();
		}
	}
	
	private void saveAsExcelFile() {
		this.jsonLogWrite();
		
		try {
			final JFileChooser saveAsFileChooser = new JFileChooser();
			saveAsFileChooser.setMultiSelectionEnabled(false); // ���� ���� �Ұ� ����
			saveAsFileChooser.setApproveButtonText("����");
			saveAsFileChooser.setFileFilter(new FileNameExtensionFilter("Excel & Text File", "xlsx", "xls", "txt"));
			int actionDialog = saveAsFileChooser.showOpenDialog(saveAsFileChooser);
			
			if (actionDialog != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File file = saveAsFileChooser.getSelectedFile();
			
			
			if ( "xls".equals(input) ) {
				file = new File(file.getAbsolutePath() + ".xls");
				
				workbook = Workbook.createWorkbook(file); // Ư����ο� �ڵ�����
				sheet = workbook.createSheet(SheetName, 0); // ��Ʈ�̸��� ���° ��Ʈ�ε���
				
				jxl.write.WritableCellFormat format_column = new WritableCellFormat(); // �÷� ����. ��Ʈ��
				format_column.setBackground(jxl.format.Colour.GRAY_25); 							// �÷� ��׶��� �� ����.
				format_column.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); 	// �÷� �������� ��Ÿ�� ����.
				
				// �÷��� �ֱ�
				for (int i = 0; i < header.length; i++) { 
					Label label = new jxl.write.Label(i, 0, header[i], format_column);
					sheet.addCell(label);
				}
				
				// ������ �ֱ�. �÷����� 0���� ���Ƿ� �����ʹ� 1���� �ִ´�.
				for (int i = 0; i < this.jsonArrayData.size(); i++) { 
					JSONObject resultData = (JSONObject) this.jsonArrayData.get(i);
					
					sheet.addCell(new jxl.write.Label(0, i + 1, String.valueOf((i+1))));
					sheet.addCell(new jxl.write.Label(1, i + 1, resultData.get("dateTime").toString()));
					sheet.addCell(new jxl.write.Label(2, i + 1, resultData.get("outputVolt").toString()));
					sheet.addCell(new jxl.write.Label(3, i + 1, resultData.get("electricCurrent").toString()));
					sheet.addCell(new jxl.write.Label(4, i + 1, resultData.get("inputVolt").toString()));
					sheet.addCell(new jxl.write.Label(5, i + 1, resultData.get("firstTemperature").toString()));
					sheet.addCell(new jxl.write.Label(6, i + 1, resultData.get("secondTemperature").toString()));
				}
				
				workbook.write(); // ���� ����
				workbook.close(); // ���� �ݱ�
				
				this.jsonArrayData.clear();
				CommonUtil.showAlert("������ ��ġ�� ���������� ����Ǿ����ϴ�.", "����(Success)", "default");
			} else {
				String data = "";
				
				file = new File(file.getAbsolutePath() + ".txt");
				
				outFile = new BufferedWriter(new FileWriter(file)); // Ư����ο� �ڵ�����
				
				for (int i = 0; i < this.jsonArrayData.size(); i++) {
					JSONObject resultData = (JSONObject) this.jsonArrayData.get(i);
					
//					data += " [ " + resultData.get("dateTime").toString() + " ]"
//							+ " �Է� ���� : " + resultData.get("inputVolt").toString() 
//							+ " / ���� : " + resultData.get("electricCurrent").toString() 
//							+ " / ��� ���� : " + resultData.get("outputVolt").toString()
//							+ " / �µ�(�濭��) : " + resultData.get("firstTemperature").toString()
//							+ " / �µ�(CAP) : " + resultData.get("secondTemperature").toString()
//							+ "\n";
					data += " [ " + resultData.get("dateTime").toString() + " ]"
							+ "  " + resultData.get("inputVolt").toString() 
							+ "  " + resultData.get("electricCurrent").toString() 
							+ "  " + resultData.get("outputVolt").toString()
							+ "  " + resultData.get("firstTemperature").toString()
							+ "  " + resultData.get("secondTemperature").toString()
							+ "\n";
				}
				
				outFile.write(data);
				outFile.flush();
				outFile.close();
				
				this.fileWirteSuccessMessage();
				CommonUtil.showAlert("������ ��ġ�� �ؽ�Ʈ ������ ����Ǿ����ϴ�.", "����(Success)", "default");
			}
		} catch (Exception e) {
			System.out.println("[Error Log] : Text ���� ���� ����");
			e.printStackTrace();
		}
	}
	
	private void saveTextFile() {
		this.jsonLogWrite();
		
		File makeDir = new File(FilePath);
		
		if( !makeDir.isDirectory() ) {
			System.out.println("[System Log] �b�ΰ� �������� �ʽ��ϴ�. ��θ� �����մϴ�.");
			makeDir.mkdir(); 
		}
		
		try {
			String data = "";
			
			outFile = new BufferedWriter(new FileWriter(new File(FilePath + CommonUtil.currentDateTime() + "." + input))); // Ư����ο� �ڵ�����
			
			for (int i = 0; i < this.jsonArrayData.size(); i++) {
				JSONObject resultData = (JSONObject) this.jsonArrayData.get(i);
				
//				data += " [ " + resultData.get("dateTime").toString() + " ]"
//						+ " �Է� ���� : " + resultData.get("inputVolt").toString() 
//						+ " / ���� : " + resultData.get("electricCurrent").toString() 
//						+ " / ��� ���� : " + resultData.get("outputVolt").toString()
//						+ " / �µ�(�濭��) : " + resultData.get("firstTemperature").toString()
//						+ " / �µ�(CAP) : " + resultData.get("secondTemperature").toString()
//						+ "\n";
				
				data += " [ " + resultData.get("dateTime").toString() + " ]"
						+ "  " + resultData.get("inputVolt").toString() 
						+ "  " + resultData.get("electricCurrent").toString() 
						+ "  " + resultData.get("outputVolt").toString()
						+ "  " + resultData.get("firstTemperature").toString()
						+ "  " + resultData.get("secondTemperature").toString()
						+ "\n";
			}
			
			outFile.write(data);
			outFile.flush();
			outFile.close();
			
			this.fileWirteSuccessMessage();
			CommonUtil.showAlert(FilePath + CommonUtil.currentDateTime() 
					+ "." + input + " ����Ǿ����ϴ�.", "����(Success)", "default");
		} catch (Exception e) {
			System.out.println("[Error Log] : Text ���� ���� ����");
			e.printStackTrace();
		}
	}
	
	private void jsonLogWrite() {
		System.out.println("[JsonObjectArray Log] : " + this.jsonArrayData.toJSONString());
	}
	
	private void fileWirteSuccessMessage() {
		System.out.println("[ " + CommonUtil.currentDateTime() + " ] : " 
				+ FilePath + CommonUtil.currentDateTime() 
				+ "." + input + " ����Ǿ����ϴ�.");	
	}
}
