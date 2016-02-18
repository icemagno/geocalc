package br.com.cmabreu.geo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Reference {
	private double totalMeters;
	private double kilometers;
	private double meters;
	private double centimeters;
	private double angle;
	private DecimalFormat df;
	private Point source;
	private Point target;

	public Point getSource() {
		return source;
	}

	public String toString() {
		return totalMeters + "m ("+kilometers + "Km " + meters + "m " + centimeters + "cm) (" + angle + ")";
	}
	
	public Point getTarget() {
		return target;
	}

	public Reference(Point source, double totalMeters, double angle, Point target) {
		DecimalFormatSymbols symbols;
	    symbols = new DecimalFormatSymbols( Locale.getDefault() );
	    symbols.setDecimalSeparator('.');

	    df = new DecimalFormat("#.###", symbols);	    
		
		this.totalMeters = Double.valueOf( df.format( totalMeters ) );
	    
		int tMeters = (int)this.totalMeters;
		
		int km = tMeters / 1000;
	    int meters = tMeters - (km * 1000);
	    float remaining_cm = (float) (totalMeters - tMeters) * 10000;
	    remaining_cm = Math.round(remaining_cm);
	    float cm = remaining_cm / 100;	    
	    
	    
		this.kilometers = km;
		this.meters = meters;
		this.centimeters = cm;
		this.angle = angle;
		this.source = source;
		this.target = target;
	}
	
	public double getAngle() {
		return angle;
	}


	public double getTotalMeters() {
		return totalMeters;
	}
	
	
	public double getKilometers() {
		return kilometers;
	}
	
	
	public double getMeters() {
		return meters;
	}
	
	
	public double getCentimeters() {
		return centimeters;
	}
	
	
}
