package project.sess.ui;

import java.awt.Color;

import javax.swing.JPanel;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

public class GraphPanel extends JPanel {
	/*
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1910152556834302411L;
	
	private final int CAPACITY = 20;
	
	private double[] xInputVoltData = new double[CAPACITY];
	private double[] yInputVoltData = new double[CAPACITY];
	
	private double[] xElectricCurrentData = new double[CAPACITY];
	private double[] yElectricCurrentData = new double[CAPACITY];
	
	private double[] xOutputVoltData = new double[CAPACITY];
	private double[] yOutputVoltData = new double[CAPACITY];
	
	double[][] inputVoltDatas = this.getInputVoltData(0, 0.0);
	double[][] electricCurrentDatas = this.getElectricCurrentData(0, 0.0);
	double[][] outputVoltDatas = this.getOutputVoltData(0, 0.0);

	public XYChart chart;
	public JPanel graph; 
	
	public GraphPanel() 
	{		
		this.setGraphPanel();
		
		chart = QuickChart.getChart("그래프", "시간", "전압", "입력전압", inputVoltDatas[0], inputVoltDatas[1]);
		
		chart.addSeries("전류[A]", electricCurrentDatas[0], electricCurrentDatas[1]);
		chart.addSeries("출력전압[V]", outputVoltDatas[0], outputVoltDatas[1]);
		chart.addSeries("전력[P]", inputVoltDatas[0], inputVoltDatas[1]);  // 전류 X 출력전압 
		
		//chart.add
		
		chart.getStyler().setYAxisMin(-5.0);   // -5
		chart.getStyler().setYAxisMax(400.0);  // 100
		
		
		graph = new XChartPanel<>(chart);
		
		// 전체 SIZE
		graph.setBounds(15, 20, 730, 420);//(25, 25, 700, 380);
		this.add(graph);
	}	
	
	private void setGraphPanel() 
	{
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 102, 762, 458);
		this.setVisible(false);
	}
	
	// 입력전압
	public double[][] getInputVoltData(int inputVoltCount, double data) {
		xInputVoltData[inputVoltCount % CAPACITY] = inputVoltCount;
		yInputVoltData[inputVoltCount % CAPACITY] = data;
		
		if( inputVoltCount <= CAPACITY ) {
			for(int i = inputVoltCount+1; i < xInputVoltData.length; i++) {
				xInputVoltData[i] = inputVoltCount;
				yInputVoltData[i] = data;
			}
		}
		
		return new double[][] {xInputVoltData, yInputVoltData};
	}
	
	// 전류
	public double[][] getElectricCurrentData(int electricCurrentCount, double data) {
		xElectricCurrentData[electricCurrentCount % CAPACITY] = electricCurrentCount;
		yElectricCurrentData[electricCurrentCount % CAPACITY] = data;
		
		if( electricCurrentCount <= CAPACITY ) {
			for(int i = electricCurrentCount+1; i < xElectricCurrentData.length; i++) {
				xElectricCurrentData[i] = electricCurrentCount;
				yElectricCurrentData[i] = data;
			}
		}
		
		return new double[][] {xElectricCurrentData, yElectricCurrentData};
	}
	
	// 출력전압
	public double[][] getOutputVoltData(int outputVoltCount, double data) {
		xOutputVoltData[outputVoltCount % CAPACITY] = outputVoltCount;
		yOutputVoltData[outputVoltCount % CAPACITY] = data;
		
		if( outputVoltCount <= CAPACITY ) {
			for(int i = outputVoltCount+1; i < xOutputVoltData.length; i++) {
				xOutputVoltData[i] = outputVoltCount;
				yOutputVoltData[i] = data;
			}
		}
		
		return new double[][] {xOutputVoltData, yOutputVoltData};
	}
}

