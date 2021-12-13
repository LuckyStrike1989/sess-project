package project.sess.vo;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class ImageVO 
{	
	private Image global_title_tab = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("top_tab.png"));
	private Image global_middle_tab = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("middle_tab.png"));
	private Image Monitoring_BackGround = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Monitoring_Image_j.png"));
	private Image FanControl_BackGround = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("FanControl_Image.png"));
	private Image Graph_BackGround = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Graph_Image.png"));
	
	
	//private Image Power_BackGround = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Power_Image.png"));
	private Image Setting_BackGround = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Setting_Image.png"));	
	private ImageIcon global_botton1 = new ImageIcon(getClass().getClassLoader().getResource("Monitoring_disable.png"));
	private ImageIcon global_botton1_selected = new ImageIcon(getClass().getClassLoader().getResource("Monitoring_enable.png"));
	private ImageIcon global_botton2 = new ImageIcon(getClass().getClassLoader().getResource("FanControl_disable1.png"));
	
	
	private ImageIcon global_botton2_selected = new ImageIcon(getClass().getClassLoader().getResource("FanControl_enable1.png"));
	private ImageIcon global_botton3 = new ImageIcon(getClass().getClassLoader().getResource("Graph_disable.png"));
	private ImageIcon global_botton3_selected = new ImageIcon(getClass().getClassLoader().getResource("Graph_enable.png"));
	private ImageIcon global_botton4 = new ImageIcon(getClass().getClassLoader().getResource("Setting_disable.png"));
	private ImageIcon global_botton4_selected = new ImageIcon(getClass().getClassLoader().getResource("Setting_enable.png"));
	
	
	private ImageIcon global_power_button = new ImageIcon(getClass().getClassLoader().getResource("Power_OFF.png"));
	private ImageIcon global_power_button_selected = new ImageIcon(getClass().getClassLoader().getResource("Power_ON.png"));
	//private ImageIcon setDefault = new ImageIcon(getClass().getClassLoader().getResource("State_Imformation1.png"));
	private ImageIcon Green = new ImageIcon(getClass().getClassLoader().getResource("State_Imformation4.png"));
	private ImageIcon Red = new ImageIcon(getClass().getClassLoader().getResource("State_Imformation2.png"));
	
	
	private ImageIcon JuHwan = new ImageIcon(getClass().getClassLoader().getResource("State_Imformation3.png"));
	private ImageIcon ON_Image = new ImageIcon(getClass().getClassLoader().getResource("control_check_on.png"));
	private ImageIcon OFF_Image = new ImageIcon(getClass().getClassLoader().getResource("control_check_off.png"));
	private ImageIcon UP_Image_check = new ImageIcon(getClass().getClassLoader().getResource("deming_up_check.png"));
	private ImageIcon UP_Image_uncheck = new ImageIcon(getClass().getClassLoader().getResource("deming_up_uncheck.png"));
	
	
	private ImageIcon DOWN_Image_check = new ImageIcon(getClass().getClassLoader().getResource("deming_down_check.png"));
	private ImageIcon DOWN_Image_uncheck = new ImageIcon(getClass().getClassLoader().getResource("deming_down_uncheck.png"));
	private ImageIcon setting_chackbox_image_check = new ImageIcon(getClass().getClassLoader().getResource("setting_check.png"));
	private ImageIcon setting_chackbox_image_uncheck = new ImageIcon(getClass().getClassLoader().getResource("setting_unchecked.png"));
	private ImageIcon setting_button1_image = new ImageIcon(getClass().getClassLoader().getResource("setting_button1_image.jpg"));
	
	
	private ImageIcon setting_button2_image = new ImageIcon(getClass().getClassLoader().getResource("setting_button2_image.png"));
	//private ImageIcon fancontrol_on = new ImageIcon(getClass().getClassLoader().getResource("FanControl_ON.png"));
	//private ImageIcon fancontrol_off = new ImageIcon(getClass().getClassLoader().getResource("FanControl_OFF.png"));
	private ImageIcon gauge_point1 = new ImageIcon(getClass().getClassLoader().getResource("im_gauge-point-0.png"));
	private ImageIcon gauge_point2 = new ImageIcon(getClass().getClassLoader().getResource("im_gauge-point-90.png"));
	
	
	private ImageIcon gauge_point3 = new ImageIcon(getClass().getClassLoader().getResource("im_gauge-point-180.png"));
	private ImageIcon safty_on = new ImageIcon(getClass().getClassLoader().getResource("bt_safty_on.png"));
	private ImageIcon safty_off= new ImageIcon(getClass().getClassLoader().getResource("bt_safty_off.png"));
	private ImageIcon warn_on = new ImageIcon(getClass().getClassLoader().getResource("bt_warn_on.png"));
	private ImageIcon warn_off = new ImageIcon(getClass().getClassLoader().getResource("bt_warn_off.png"));
	
	
	private ImageIcon danger_on = new ImageIcon(getClass().getClassLoader().getResource("bt_danger_on.png"));
	private ImageIcon danger_off = new ImageIcon(getClass().getClassLoader().getResource("bt_danger_off.png"));
	
	
	public Image getGlobal_title_tab() {
		return global_title_tab;
	}
	
	public void setGlobal_title_tab(Image global_title_tab) {
		this.global_title_tab = global_title_tab;
	}
	
	public Image getGlobal_middle_tab() {
		return global_middle_tab;
	}
	
	public void setGlobal_middle_tab(Image global_middle_tab) {
		this.global_middle_tab = global_middle_tab;
	}
	
	public Image getMonitoring_BackGround() {
		return Monitoring_BackGround;
	}
	
	public void setMonitoring_BackGround(Image monitoring_BackGround) {
		Monitoring_BackGround = monitoring_BackGround;
	}
	
	public Image getFanControl_BackGround() {
		return FanControl_BackGround;
	}
	
	public void setFanControl_BackGround(Image fanControl_BackGround) {
		FanControl_BackGround = fanControl_BackGround;
	}
	
	public Image getGraph_BackGround() {
		return Graph_BackGround;
	}
	
	public void setGraph_BackGround(Image graph_BackGround) {
		Graph_BackGround = graph_BackGround;
	}
	
	public Image getSetting_BackGround() {
		return Setting_BackGround;
	}
	
	public void setSetting_BackGround(Image setting_BackGround) {
		Setting_BackGround = setting_BackGround;
	}
	
	public ImageIcon getGlobal_botton1() {
		return global_botton1;
	}
	
	public void setGlobal_botton1(ImageIcon global_botton1) {
		this.global_botton1 = global_botton1;
	}
	
	public ImageIcon getGlobal_botton1_selected() {
		return global_botton1_selected;
	}
	
	public void setGlobal_botton1_selected(ImageIcon global_botton1_selected) {
		this.global_botton1_selected = global_botton1_selected;
	}
	
	public ImageIcon getGlobal_botton2() {
		return global_botton2;
	}
	
	public void setGlobal_botton2(ImageIcon global_botton2) {
		this.global_botton2 = global_botton2;
	}
	
	public ImageIcon getGlobal_botton2_selected() {
		return global_botton2_selected;
	}
	
	public void setGlobal_botton2_selected(ImageIcon global_botton2_selected) {
		this.global_botton2_selected = global_botton2_selected;
	}
	
	public ImageIcon getGlobal_botton3() {
		return global_botton3;
	}
	
	public void setGlobal_botton3(ImageIcon global_botton3) {
		this.global_botton3 = global_botton3;
	}
	
	public ImageIcon getGlobal_botton3_selected() {
		return global_botton3_selected;
	}
	
	public void setGlobal_botton3_selected(ImageIcon global_botton3_selected) {
		this.global_botton3_selected = global_botton3_selected;
	}
	
	public ImageIcon getGlobal_botton4() {
		return global_botton4;
	}
	
	public void setGlobal_botton4(ImageIcon global_botton4) {
		this.global_botton4 = global_botton4;
	}
	
	public ImageIcon getGlobal_botton4_selected() {
		return global_botton4_selected;
	}
	
	public void setGlobal_botton4_selected(ImageIcon global_botton4_selected) {
		this.global_botton4_selected = global_botton4_selected;
	}
	
	public ImageIcon getGlobal_power_button() {
		return global_power_button;
	}
	
	public void setGlobal_power_button(ImageIcon global_power_button) {
		this.global_power_button = global_power_button;
	}
	
	public ImageIcon getGlobal_power_button_selected() {
		return global_power_button_selected;
	}
	
	public void setGlobal_power_button_selected(ImageIcon global_power_button_selected) {
		this.global_power_button_selected = global_power_button_selected;
	}
	
	public ImageIcon getGreen() {
		return Green;
	}
	
	public void setGreen(ImageIcon green) {
		Green = green;
	}
	
	public ImageIcon getRed() {
		return Red;
	}
	
	public void setRed(ImageIcon red) {
		Red = red;
	}
	
	public ImageIcon getJuHwan() {
		return JuHwan;
	}
	
	public void setJuHwan(ImageIcon juHwan) {
		JuHwan = juHwan;
	}
	
	public ImageIcon getON_Image() {
		return ON_Image;
	}
	
	public void setON_Image(ImageIcon oN_Image) {
		ON_Image = oN_Image;
	}
	
	public ImageIcon getOFF_Image() {
		return OFF_Image;
	}
	
	public void setOFF_Image(ImageIcon oFF_Image) {
		OFF_Image = oFF_Image;
	}
	
	public ImageIcon getUP_Image_check() {
		return UP_Image_check;
	}
	
	public void setUP_Image_check(ImageIcon uP_Image_check) {
		UP_Image_check = uP_Image_check;
	}
	
	public ImageIcon getUP_Image_uncheck() {
		return UP_Image_uncheck;
	}
	
	public void setUP_Image_uncheck(ImageIcon uP_Image_uncheck) {
		UP_Image_uncheck = uP_Image_uncheck;
	}
	
	public ImageIcon getDOWN_Image_check() {
		return DOWN_Image_check;
	}
	
	public void setDOWN_Image_check(ImageIcon dOWN_Image_check) {
		DOWN_Image_check = dOWN_Image_check;
	}
	
	public ImageIcon getDOWN_Image_uncheck() {
		return DOWN_Image_uncheck;
	}
	
	public void setDOWN_Image_uncheck(ImageIcon dOWN_Image_uncheck) {
		DOWN_Image_uncheck = dOWN_Image_uncheck;
	}
	
	public ImageIcon getSetting_chackbox_image_check() {
		return setting_chackbox_image_check;
	}
	
	public void setSetting_chackbox_image_check(ImageIcon setting_chackbox_image_check) {
		this.setting_chackbox_image_check = setting_chackbox_image_check;
	}
	
	public ImageIcon getSetting_chackbox_image_uncheck() {
		return setting_chackbox_image_uncheck;
	}
	
	public void setSetting_chackbox_image_uncheck(ImageIcon setting_chackbox_image_uncheck) {
		this.setting_chackbox_image_uncheck = setting_chackbox_image_uncheck;
	}
	
	public ImageIcon getSetting_button1_image() {
		return setting_button1_image;
	}
	
	public void setSetting_button1_image(ImageIcon setting_button1_image) {
		this.setting_button1_image = setting_button1_image;
	}
	
	public ImageIcon getSetting_button2_image() {
		return setting_button2_image;
	}
	
	public void setSetting_button2_image(ImageIcon setting_button2_image) {
		this.setting_button2_image = setting_button2_image;
	}
	
	public ImageIcon getGauge_point1() {
		return gauge_point1;
	}
	
	public void setGauge_point1(ImageIcon gauge_point1) {
		this.gauge_point1 = gauge_point1;
	}
	
	public ImageIcon getGauge_point2() {
		return gauge_point2;
	}
	
	public void setGauge_point2(ImageIcon gauge_point2) {
		this.gauge_point2 = gauge_point2;
	}
	
	public ImageIcon getGauge_point3() {
		return gauge_point3;
	}
	
	public void setGauge_point3(ImageIcon gauge_point3) {
		this.gauge_point3 = gauge_point3;
	}
	
	public ImageIcon getSafty_on() {
		return safty_on;
	}
	
	public void setSafty_on(ImageIcon safty_on) {
		this.safty_on = safty_on;
	}
	
	public ImageIcon getSafty_off() {
		return safty_off;
	}
	
	public void setSafty_off(ImageIcon safty_off) {
		this.safty_off = safty_off;
	}
	
	public ImageIcon getWarn_on() {
		return warn_on;
	}
	
	public void setWarn_on(ImageIcon warn_on) {
		this.warn_on = warn_on;
	}
	
	public ImageIcon getWarn_off() {
		return warn_off;
	}
	
	public void setWarn_off(ImageIcon warn_off) {
		this.warn_off = warn_off;
	}
	
	public ImageIcon getDanger_on() {
		return danger_on;
	}
	
	public void setDanger_on(ImageIcon danger_on) {
		this.danger_on = danger_on;
	}
	
	public ImageIcon getDanger_off() {
		return danger_off;
	}
	
	public void setDanger_off(ImageIcon danger_off) {
		this.danger_off = danger_off;
	}	
	
}
