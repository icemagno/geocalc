package br.com.cmabreu;

import java.util.Random;

import hla.rti1516e.ObjectInstanceHandle;

// All our objects in RTI must be represented by Java classes. 
public class UnitObject {
	// To hold the RTI object handle for a specific Tank
	private ObjectInstanceHandle instance;
	private Position position;
	private String name;
	private String serial;
	private String imageName = "army";
	private int unitType = UNKNOWN;
	
	public static final int ME = 100;
	public static final int FRIEND = 101;
	public static final int FOE = 102;
	public static final int UNKNOWN = 103;
	
	public void update() {
		Random random = new Random();
		double rate = random.nextInt(9)+1;
		position.setLongitude( position.getLongitude() + ( rate / 10000 )  );
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}
	
	// Constructor. Must hold the instance handle of this Tank in RTI.
	public UnitObject( ObjectInstanceHandle instance ) {
		this.instance = instance;
	}
	
	// Check if a given object is this specific Tank
	public boolean isMe( ObjectInstanceHandle obj ) {
		return obj.equals( instance );
	}
	
	// Just a getter...
	public ObjectInstanceHandle getHandle() {
		return instance;
	}

	
}
