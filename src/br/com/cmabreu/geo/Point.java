package br.com.cmabreu.geo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Point {
	private String latitude = "";
	private String longitude = "";
	private Double dlat = 0.0;
	private Double dlon = 0.0;
	private DecimalFormat df;
	
	public String toString() {
		return longitude + " " + latitude;
	}	
	
	public Point(String longitude, String latitude) {
		insertAsDouble(Double.parseDouble(longitude), Double.parseDouble(latitude));
	}
	
	
	private void insertAsDouble(double longitude, double latitude ) {
		DecimalFormatSymbols symbols;
	    symbols = new DecimalFormatSymbols( Locale.getDefault() );
	    symbols.setDecimalSeparator('.');

	    df = new DecimalFormat("###.############", symbols);	    
		
		this.dlon = Double.valueOf( df.format( longitude ) );
		this.dlat = Double.valueOf( df.format(latitude ) );
		this.latitude = String.valueOf(latitude);
		this.longitude = String.valueOf(longitude);
	}

	
	public Point(double longitude, double latitude) {
		insertAsDouble(longitude, latitude);
	}
	
	public double getLatitudeSeconds() {
		return  dlat / 3600;
	}
	
	public double getLongitudeSeconds() {
		return dlon / 3600;
	}	
	
	public Double getLongitudeDouble() {
		return dlon;
	}
	
	
	public Double getLatitudeDouble() {
		return dlat;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
	public void setLatitude(String latitude) {
		this.dlat = Double.valueOf( df.format(latitude ) );
		this.latitude = latitude;
	}	

	public void setLongitude(String longitude) {
		this.dlon = Double.valueOf( df.format( longitude ) );
		this.longitude = longitude;
	}
	
	
}
