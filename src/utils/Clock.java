package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Clock{
	
	public static void Clock(JLabel time) {
		LocalDateTime now=  LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		String formatterDateTime= now.format(formatter);
		time.setText(formatterDateTime);
	}
}

