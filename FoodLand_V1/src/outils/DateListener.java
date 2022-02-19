package outils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class DateListener<T> implements PropertyChangeListener {
	
	private T t;
	public JDateChooser jdc;
	private String date;
	
	public DateListener(JDateChooser jdc) {	
		this.jdc = jdc;
		jdc.addPropertyChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		date = (String) evt.toString();
		//System.out.println(date);
		//System.out.println("date: " + jdc.getDate());
		setJdc(jdc);
		
	}

	public void setJdc(JDateChooser jdc) {
		this.jdc = jdc;
	}

	public JDateChooser getJdc() {
		return jdc;
	}

	
}
