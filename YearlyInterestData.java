//FILE:				YearlyInterestData.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A class that is responsible for yearly interest
//RELATIONSHIP:		Observer interface, ProcessData interface

import java.util.*;
public class YearlyInterestData implements Observer, ProcessData 
{
	private List<Property> properties;
	private Subject trainingData;

	//Alternate Constructor - register this class into observer
	public YearlyInterestData( Subject trainingData )
	{
		this.trainingData = trainingData;
		trainingData.registerObserver( this );
	}

	//PURPOSE:	Update Interest for properties
	//IMPORT:	List of events, List of affected properties, currentYear, List of plans, Primary Company 
	//EXPORT:	None
	public void update( List<Event> events, List<Property> properties, int currentYear, List<Plan> plans, PrimaryCompany pCompany ) throws InvalidOperationException 
	{
		this.properties = properties;
		process();
	}

	//PURPOSE:	Process Interest 
	//IMPORT:	None
	//EXPORT:	None
	public void process()
	{
		for( Property p : properties )
		{
			if( p instanceof Company) //Only for Company
			{
				((Company)p).calcInterest();
			}
				
		}
	}
	
}
