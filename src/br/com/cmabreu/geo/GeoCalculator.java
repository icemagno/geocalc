package br.com.cmabreu.geo;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.xml.crypto.dsig.TransformException;

import org.geotools.referencing.CRS;
import org.geotools.referencing.GeodeticCalculator;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class GeoCalculator {

	
	public static double courseInDegreesToAzimuth(double courseInDegrees) {
	    double azimuth;
	    if (courseInDegrees > 180.0) {
	        azimuth = -180.0 + (courseInDegrees - 180.0);
	    } else {
	        azimuth = courseInDegrees;
	    }
	    return azimuth;
	}
	
	public static double azimuthToCourseInDegrees(double azimuth) {
	    double courseInDegrees;
	    if (azimuth < 0.0) {
	        courseInDegrees = 360.0 + azimuth;
	    } else {
	        courseInDegrees = azimuth;
	    }
	    return courseInDegrees;
	}	
	
	
	public static void doATest() throws NoSuchAuthorityCodeException, FactoryException, TransformException {
		CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
		GeodeticCalculator calc = new GeodeticCalculator( crs );
		
	    calc.setStartingGeographicPoint(0, 0);
	    calc.setDirection( -90, 2000 );

	    Point2D dest = calc.getDestinationGeographicPoint();

	    System.out.println(calc);
	    System.out.println( dest.getX() + " " + dest.getY() );
	    
	    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols( Locale.getDefault() );
	    otherSymbols.setDecimalSeparator('.');
	    DecimalFormat df = new DecimalFormat("###.##########", otherSymbols);	    
	    
		System.out.println("XX : " + df.format(dest.getX()));
		System.out.println("YY : " + df.format(dest.getY()));
	    
	    

	}
	
	
	public static Point projectCoordinates(Point startCoordinates, double course, double meters) throws NoSuchAuthorityCodeException, FactoryException  {
		CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
		GeodeticCalculator calc = new GeodeticCalculator( crs );
	    calc.setStartingGeographicPoint(startCoordinates.getLongitudeDouble(), startCoordinates.getLatitudeDouble() );
	    double azimuth = courseInDegreesToAzimuth(course);
	    calc.setDirection( azimuth , meters );

	    Point2D dest = calc.getDestinationGeographicPoint();
	    Point result = new Point( dest.getX() , dest.getY() );
		return result;
	}
	
	
	public static Reference calcDistanceAndCourseBetweenPoints(Point startCoordinates, Point endCoordinates) throws TransformException, NoSuchAuthorityCodeException, FactoryException {
		CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
	    GeodeticCalculator gc = new GeodeticCalculator( crs );
	    
		gc.setStartingGeographicPoint(startCoordinates.getLongitudeDouble(), startCoordinates.getLatitudeDouble() );
	    gc.setDestinationGeographicPoint(endCoordinates.getLongitudeDouble(), endCoordinates.getLatitudeDouble() );

	    double angle = azimuthToCourseInDegrees( gc.getAzimuth() );	    
	    double distance = gc.getOrthodromicDistance();

	    Reference result = new Reference(startCoordinates,distance,angle,endCoordinates);
	    return result;
	}
	
	
}
