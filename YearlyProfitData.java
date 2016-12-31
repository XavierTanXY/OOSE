//FILE:				YearlyProfitData.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A class that is responsible for yearly profit
//RELATIONSHIP:		Observer interface, ProcessData interface

import java.util.*;
public class YearlyProfitData implements Observer, ProcessData
{
	private List<Property> properties;
	private Subject trainingData;

	//Alternate Constructor - register this class into observer
	public YearlyProfitData( Subject trainingData ) 
	{
		this.trainingData = trainingData;
		trainingData.registerObserver( this );
	} 

	//PURPOSE:	Update Profit for Properties
	//IMPORT:	List of events, List of affected properties, currentYear, List of plans, Primary Company 
	//EXPORT:	None
	public void update(  List<Event> events, List<Property> properties, int currentYear, List<Plan> plans, PrimaryCompany pCompany ) throws InvalidOperationException
	{
		this.properties = properties;

		process(); //For Business
		processCompany(); //For Company

	}

	//PURPOSE:	Process Profit for Business
	//IMPORT:	None
	//EXPORT:	None
	public void process() throws InvalidOperationException
	{
		for( Property property : properties )
		{
			if( property instanceof Business )
			{
				double profit = property.getProfit();
				Property ppp = property.getOwner();
				
				ppp.setProfit( profit );
				ppp.calcProfit();	
			}
			
		}
	}

	//PURPOSE:	Process Profit Company
	//IMPORT:	None
	//EXPORT:	None
	private void processCompany() throws InvalidOperationException
	{
		for( Property p2 : properties )
		{
			
			if( p2 instanceof Company )
			{
				double profit = p2.getProfit();

				Property ppp = p2.getOwner();
				ppp.setProfit( ppp.getProfit() + profit );
				((Company)ppp).addBalance( ( profit  ) );
				((Company)p2).resetProfit();
			}
			
		}
	}
}