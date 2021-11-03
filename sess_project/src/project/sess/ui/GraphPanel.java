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
	
	//public double phase = 0;
	//private double[][] initdata = getSineData(phase);
	
	public XYChart chart;
	public JPanel graph; 

	public GraphPanel() 
	{		
		this.setGraphPanel();

		double[] xData = new double[] { 0.0, 1.0, 2.0, 3.0, 5.0 };
		double[] yData = new double[] { 2.0, 1.0, 0.0, 3.0, 6.0 };

		XYChart chart = QuickChart.getChart("그래프", "시간", "전기", "y(x)", xData, yData);

		/*chart = QuickChart.getChart("Real-time Graph", "Radians", "Sine", "sine", initdata[0], initdata[1]);
	    chart.getStyler().setYAxisMin(-5.0);
	    chart.getStyler().setYAxisMax(5.0);
	    chart.getStyler().setLegendVisible(false);
	    chart.getStyler().setXAxisTicksVisible(false);*/

		chart.addSeries("a", new double[] { 0.0, 5.0, 10.0, 3.0, 12.9 }, new double[] { 10.0, 5.0, 64.0, 32.0, 33.0 });
		chart.addSeries("b", new double[] { 0.0, 12.0, 24.0, 22.0, 30.0 }, new double[] { 24.0, 12.0, 17.0, 33.0, 29.0 });
		chart.addSeries("c", new double[] { 0.0, 8.0, 16.0, 25.0, 27.0 }, new double[] { 16.0, 8.0, 77.0, 60.0, 9.0 });

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
	
	public double[][] getSineData(double phase) 
	{
		double[] xData = new double[100];
		double[] yData = new double[100];
		
		for(int i = 0; i < xData.length; i++) 
		{
			double radians = phase + (2 * Math.PI / xData.length * i);
			xData[i] = radians;
			yData[i] = Math.sin(radians);
		}
		
		return new double[][] {xData, yData};
	}
}

