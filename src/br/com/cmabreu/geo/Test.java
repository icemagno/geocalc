package br.com.cmabreu.geo;

public class Test {

	private static void calcDistanceAndCourseBetweenPoints( Point source, Point target ) throws Exception {
		Reference result =  GeoCalculator.calcDistanceAndCourseBetweenPoints(source, target);
		System.out.println("azimuth " + result.getAngle() + " degrees and " + result.getTotalMeters() + " meters");		
	}
	
	public static void main(String[] args) throws Exception {
		Point src = new Point(0,0);
		Point trgt = new Point(0,1);
		calcDistanceAndCourseBetweenPoints(src,trgt);
		
		System.out.println( GeoCalculator.projectCoordinates(src, 0, 110574.389).toString() );
	}

}

