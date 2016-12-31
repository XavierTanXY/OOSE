//FILE:				YearlyEventData.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A class that is responsible for yearly event data
//RELATIONSHIP:		Observer interface, ProcessData interface

import java.util.*;
public class YearlyEventData implements Observer, ProcessData 
{
	private List<Event> events;
	private List<Property> properties;
	private int currentYear;
	private Subject trainingData;

	//Alternate Constructor - register this class into observer
	public YearlyEventData( Subject trainingData ) 
	{
		this.trainingData = trainingData;
		trainingData.registerObserver( this );
	} 

	//PURPOSE:	Update Events data
	//IMPORT:	List of events, List of affected properties, currentYear, List of plans, Primary Company 
	//EXPORT:	None
	public void update(  List<Event> events, List<Property> properties, int currentYear, List<Plan> plans, PrimaryCompany pCompany ) throws InvalidOperationException
	{
		this.events = events;
		this.properties = properties;
		this.currentYear = currentYear;

		process();

	}

	//PURPOSE:	Process Event data
	//IMPORT:	None
	//EXPORT:	None
	public void process() throws InvalidOperationException 
	{
		for( Event event : events )
		{ 
			if( event.getYear() == currentYear ) //Process the event for current year
			{
				event.eventAction( properties );
			}
		}
	}

}