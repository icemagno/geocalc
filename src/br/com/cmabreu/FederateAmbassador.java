package br.com.cmabreu;

import hla.rti1516e.AttributeHandleSet;
import hla.rti1516e.AttributeHandleValueMap;
import hla.rti1516e.NullFederateAmbassador;
import hla.rti1516e.ObjectClassHandle;
import hla.rti1516e.ObjectInstanceHandle;
import hla.rti1516e.OrderType;
import hla.rti1516e.TransportationTypeHandle;
import hla.rti1516e.exceptions.FederateInternalError;

public class FederateAmbassador extends NullFederateAmbassador {
	private GeoControllerFederate federate;
	
	// The constructor. Must store the Federate to allow 
	// interactivity
	public FederateAmbassador( GeoControllerFederate federate ) {
		this.federate = federate;
	}

	private void log( String message )	{
		System.out.println( "> " + message );
	}
	
	@Override
	public void attributeOwnershipAcquisitionNotification( ObjectInstanceHandle theObject,
			AttributeHandleSet securedAttributes, byte[] userSuppliedTag) throws FederateInternalError {
			
		log("Ownsership acquired : " + theObject );
	}	
	
	@Override
	public void requestAttributeOwnershipRelease( ObjectInstanceHandle theObject,
			AttributeHandleSet candidateAttributes, byte[] userSuppliedTag)
			throws FederateInternalError {
			//
	}	
	
	@Override
	public void removeObjectInstance(ObjectInstanceHandle theObject,
               byte[] userSuppliedTag, OrderType sentOrdering,
               SupplementalRemoveInfo removeInfo) {

		if ( federate.getUnitClass().isAUnit( theObject ) ) {
			federate.getUnitClass().removeByRTIRequest( theObject );
		}
		
	}
	
	// All new object - rtiamb.registerObjectInstance( classHandle ) -  
	// that arrives into RTI will trigger this event
	@Override
	public void discoverObjectInstance( ObjectInstanceHandle theObject,
	                                    ObjectClassHandle theObjectClass,
	                                    String objectName ) throws FederateInternalError {
		// Is the object we found a kind of Unit?
		if ( federate.getUnitClass().isClassOf( theObjectClass ) ) {
			try {
				// If so, create a new Unit object in our list.
				federate.newUnit( theObjectClass, theObject  );
			} catch ( Exception e ) {
				log( e.getMessage() );
			}
		}
		
	}

	@Override
	public void reflectAttributeValues( ObjectInstanceHandle theObject,
	                                    AttributeHandleValueMap theAttributes,
	                                    byte[] tag,
	                                    OrderType sentOrder,
	                                    TransportationTypeHandle transport,
	                                    SupplementalReflectInfo reflectInfo ) throws FederateInternalError {
		// Is this attribute from a Unit?
		if ( federate.getUnitClass().isAUnit( theObject ) ) {
			// If so, update my Unit object attributes
			try {
				federate.getUnitClass().update( theAttributes, theObject );
			} catch ( Exception e ) {
				log( e.getMessage() );
			}
		}
		
	}


}
