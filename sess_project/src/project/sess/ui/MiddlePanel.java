package project.sess.ui;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MiddlePanel extends JPanel {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1932638371640021258L;
	
	private JLabel input_volt;
	private JLabel electric_current;
	private JLabel output_volt;
	private JLabel first_temperature;
	private JLabel second_temperature;
	private JLabel current_date;
	
	public JLabel info_input_volt;
	public JLabel info_electric_current;
	public JLabel info_output_volt;
	public JLabel info_first_temperature;
	public JLabel info_second_temperature;
	
	private GregorianCalendar gc = new GregorianCalendar();
	private String[] weekDay = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
	private String now = gc.get(Calendar.YEAR)+"-" + (gc.get(Calendar.MONTH)+1)+"-"
			+ (gc.get(Calendar.DATE))+ "  " + weekDay[gc.get(Calendar.DAY_OF_WEEK)-1];
	
	public MiddlePanel() {
		this.setLayout(null);
		
		input_volt = new JLabel("입력V : ");
		input_volt.setBounds(47, 10, 50, 15);
		info_input_volt = new JLabel("0000");
		info_input_volt.setBounds(87, 10, 50, 15);
		this.add(input_volt);
		this.add(info_input_volt);
		
		electric_current = new JLabel("전류 : ");
		electric_current.setBounds(130, 10, 50, 15);
		info_electric_current = new JLabel("0000");
		info_electric_current.setBounds(165, 10, 50, 15);
		this.add(electric_current);
		this.add(info_electric_current);
		
		output_volt = new JLabel("출력V : ");
		output_volt.setBounds(200, 10, 50, 15);
		info_output_volt = new JLabel("0000");
		info_output_volt.setBounds(240, 10, 50, 15);
		this.add(output_volt);
		this.add(info_output_volt);
		
		first_temperature = new JLabel("온도1 : ");
		first_temperature.setBounds(285, 10, 75, 15);
		info_first_temperature = new JLabel("0000");
		info_first_temperature.setBounds(327, 10, 50, 15);
		this.add(first_temperature);
		this.add(info_first_temperature);
		
		second_temperature = new JLabel("�온도2 : ");
		second_temperature.setBounds(370, 10, 50, 15);
		info_second_temperature = new JLabel("0000");
		info_second_temperature.setBounds(410, 10, 50, 15);
		this.add(second_temperature);
		this.add(info_second_temperature);
		
		current_date = new JLabel(now);
		current_date.setBounds(640, 10, 126, 15);
		this.add(current_date);
	}
}
