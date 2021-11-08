package project.sess.ui;

import java.awt.Color;

import javax.swing.JPanel;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

public class GraphPanel extends JPanel {
	/*
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1910152556834302411L;
	
	private final int CAPACITY = 5;
	
	private double[] xInputVoltData = new double[5];
	private double[] yInputVoltData = new double[5];
	
	private double[] xElectricCurrentData = new double[5];
	private double[] yElectricCurrentData = new double[5];
	
	private double[] xOutputVoltData = new double[5];
	private double[] yOutputVoltData = new double[5];
	
	private int inputVoltCount = 0;
	private int electricCurrentCount = 0;
	private int outputVoltCount = 0;
	
	private double[][] initdata = initData(0);

	public XYChart chart;
	public JPanel graph; 
	
	public GraphPanel() 
	{		
		this.setGraphPanel();
		
		chart = QuickChart.getChart("그래프", "시간", "전기", "입력전압", initdata[0], initdata[1]);
		
		chart.addSeries("전류", initdata[0], initdata[1]);
		chart.addSeries("출력전압", initdata[0], initdata[1]);
		chart.addSeries("전류 X 출력전압", initdata[0], initdata[1]);
		
		chart.getStyler().setYAxisMin(-5.0);
		chart.getStyler().setYAxisMax(100.0);
		
		graph = new XChartPanel<>(chart);
		
		graph.setBounds(25, 25, 700, 380);
		this.add(graph);
	}	
	
	private void setGraphPanel() 
	{
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 102, 762, 458);
		this.setVisible(false);
	}
	
	// 데이터 초기화
	public double[][] initData(double data) {
		double[] xData = new double[5];
		double[] yData = new double[5];
		
		for(int i=0; i<xData.length; i++) {
			xData[i] = data;
			yData[i] = data;
		}
		
		return new double[][] {xData, yData};
	}
	
	// 입력전압
	public double[][] getInputVoltData(double data) {
		xInputVoltData[inputVoltCount++ % CAPACITY] = inputVoltCount++ % CAPACITY;
		yInputVoltData[inputVoltCount++ % CAPACITY] = data;
		
		return new double[][] {xInputVoltData, yInputVoltData};
	}
	
	// 전류
	public double[][] getElectricCurrentData(double data) {
		xElectricCurrentData[electricCurrentCount++ % CAPACITY] = electricCurrentCount++ % CAPACITY;
		yElectricCurrentData[electricCurrentCount++ % CAPACITY] = data;
		
		return new double[][] {xElectricCurrentData, yElectricCurrentData};
	}
	
	// 출력전압
	public double[][] getOutputVoltData(double data) {
		xOutputVoltData[outputVoltCount++ % CAPACITY] = outputVoltCount++ % CAPACITY;
		yOutputVoltData[outputVoltCount++ % CAPACITY] = data;
		
		return new double[][] {xOutputVoltData, yOutputVoltData};
	}
}

