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
	private String SheetName = "log"; // 시트 이름
	private String FilePath = "C:/sess_monitoring/"; // 파일 경로 및 파일명
	
	private WritableWorkbook workbook;
	private WritableSheet sheet;
	private BufferedWriter outFile = null;
	private JSONArray jsonArrayData;
	
	private String[] sel_message = { "xls", "txt" };
	private String[] header = { "순서", "날짜", "입력전압", "전류", "출력전압", "온도(방열판)", "온도(CAP)" }; // 컬럼명
	
	private String input = (String) JOptionPane.showInputDialog(null, "저장할 타입을 선택하세요.", "저장", JOptionPane.INFORMATION_MESSAGE, null, sel_message, "타입선택");
	
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
			System.out.println("[System Log] 꼉로가 존재하지 않습니다. 경로를 생성합니다.");
			makeDir.mkdir();
		}
		
		try {			
			workbook = Workbook.createWorkbook(new File(FilePath + CommonUtil.currentDateTime() + "." + input)); // 특정경로에 자동저장
			sheet = workbook.createSheet(SheetName, 0); // 시트이름과 몇번째 시트인덱스
			
			jxl.write.WritableCellFormat format_column = new WritableCellFormat(); // 컬럼 포멧. 스트링
			format_column.setBackground(jxl.format.Colour.GRAY_25); 							// 컬럼 백그라운드 색 설정.
			format_column.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); 	// 컬럼 보더라인 스타일 설정.
			
			// 컬럼명 넣기
			for (int i = 0; i < header.length; i++) { 
				Label label = new jxl.write.Label(i, 0, header[i], format_column);
				sheet.addCell(label);
			}
			
			// 데이터 넣기. 컬럼명이 0번에 들어가므로 데이터는 1부터 넣는다.
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
			
			workbook.write(); // 파일 저장
			workbook.close(); // 파일 닫기
			
			this.jsonArrayData.clear();
			this.fileWirteSuccessMessage();
			CommonUtil.showAlert(FilePath + CommonUtil.currentDateTime() 
					+ "." + input + " 저장되었습니다.", "성공(Success)", "default");
		} catch (Exception e) {
			System.out.println("[Error Log] : 엑셀 파일 저장 에러");
			e.printStackTrace();
		}
	}
	
	private void saveAsExcelFile() {
		this.jsonLogWrite();
		
		try {
			final JFileChooser saveAsFileChooser = new JFileChooser();
			saveAsFileChooser.setMultiSelectionEnabled(false); // 다중 선택 불가 설정
			saveAsFileChooser.setApproveButtonText("저장");
			saveAsFileChooser.setFileFilter(new FileNameExtensionFilter("Excel & Text File", "xlsx", "xls", "txt"));
			int actionDialog = saveAsFileChooser.showOpenDialog(saveAsFileChooser);
			
			if (actionDialog != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File file = saveAsFileChooser.getSelectedFile();
			
			
			if ( "xls".equals(input) ) {
				file = new File(file.getAbsolutePath() + ".xls");
				
				workbook = Workbook.createWorkbook(file); // 특정경로에 자동저장
				sheet = workbook.createSheet(SheetName, 0); // 시트이름과 몇번째 시트인덱스
				
				jxl.write.WritableCellFormat format_column = new WritableCellFormat(); // 컬럼 포멧. 스트링
				format_column.setBackground(jxl.format.Colour.GRAY_25); 							// 컬럼 백그라운드 색 설정.
				format_column.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); 	// 컬럼 보더라인 스타일 설정.
				
				// 컬럼명 넣기
				for (int i = 0; i < header.length; i++) { 
					Label label = new jxl.write.Label(i, 0, header[i], format_column);
					sheet.addCell(label);
				}
				
				// 데이터 넣기. 컬럼명이 0번에 들어가므로 데이터는 1부터 넣는다.
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
				
				workbook.write(); // 파일 저장
				workbook.close(); // 파일 닫기
				
				this.jsonArrayData.clear();
				CommonUtil.showAlert("지정된 위치에 엑셀파일이 저장되었습니다.", "성공(Success)", "default");
			} else {
				String data = "";
				
				file = new File(file.getAbsolutePath() + ".txt");
				
				outFile = new BufferedWriter(new FileWriter(file)); // 특정경로에 자동저장
				
				for (int i = 0; i < this.jsonArrayData.size(); i++) {
					JSONObject resultData = (JSONObject) this.jsonArrayData.get(i);
					
//					data += " [ " + resultData.get("dateTime").toString() + " ]"
//							+ " 입력 전압 : " + resultData.get("inputVolt").toString() 
//							+ " / 전류 : " + resultData.get("electricCurrent").toString() 
//							+ " / 출력 전압 : " + resultData.get("outputVolt").toString()
//							+ " / 온도(방열판) : " + resultData.get("firstTemperature").toString()
//							+ " / 온도(CAP) : " + resultData.get("secondTemperature").toString()
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
				CommonUtil.showAlert("지정된 위치에 텍스트 파일이 저장되었습니다.", "성공(Success)", "default");
			}
		} catch (Exception e) {
			System.out.println("[Error Log] : Text 파일 저장 에러");
			e.printStackTrace();
		}
	}
	
	private void saveTextFile() {
		this.jsonLogWrite();
		
		File makeDir = new File(FilePath);
		
		if( !makeDir.isDirectory() ) {
			System.out.println("[System Log] 꼉로가 존재하지 않습니다. 경로를 생성합니다.");
			makeDir.mkdir(); 
		}
		
		try {
			String data = "";
			
			outFile = new BufferedWriter(new FileWriter(new File(FilePath + CommonUtil.currentDateTime() + "." + input))); // 특정경로에 자동저장
			
			for (int i = 0; i < this.jsonArrayData.size(); i++) {
				JSONObject resultData = (JSONObject) this.jsonArrayData.get(i);
				
//				data += " [ " + resultData.get("dateTime").toString() + " ]"
//						+ " 입력 전압 : " + resultData.get("inputVolt").toString() 
//						+ " / 전류 : " + resultData.get("electricCurrent").toString() 
//						+ " / 출력 전압 : " + resultData.get("outputVolt").toString()
//						+ " / 온도(방열판) : " + resultData.get("firstTemperature").toString()
//						+ " / 온도(CAP) : " + resultData.get("secondTemperature").toString()
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
					+ "." + input + " 저장되었습니다.", "성공(Success)", "default");
		} catch (Exception e) {
			System.out.println("[Error Log] : Text 파일 저장 에러");
			e.printStackTrace();
		}
	}
	
	private void jsonLogWrite() {
		System.out.println("[JsonObjectArray Log] : " + this.jsonArrayData.toJSONString());
	}
	
	private void fileWirteSuccessMessage() {
		System.out.println("[ " + CommonUtil.currentDateTime() + " ] : " 
				+ FilePath + CommonUtil.currentDateTime() 
				+ "." + input + " 저장되었습니다.");	
	}
}
