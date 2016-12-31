//FILE:				YearlyPlanData.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A class that is responsible for yearly plan data
//RELATIONSHIP:		Observer interface, ProcessData interface

import java.util.*;
public class YearlyPlanData implements Observer, ProcessData 
{
	private List<Plan> plans;
	private List<Property> properties;
	private PrimaryCompany pCompany;
	private int currentYear;
	private Subject trainingData;

	//Alternate Constructor - register this class into observer
	public YearlyPlanData( Subject trainingData )
	{
		this.trainingData = trainingData;
		trainingData.registerObserver( this );
	}

	//PURPOSE:	Update Events data
	//IMPORT:	List of events, List of affected properties, currentYear, List of plans, Primary Company 
	//EXPORT:	None
	public void update( List<Event> events, List<Property> properties, int currentYear, List<Plan> plans, PrimaryCompany pCompany ) throws InvalidOperationException 
	{
		this.plans = plans;
		this.properties = properties;
		this.pCompany = pCompany;
		this.currentYear = currentYear;

		process();
	}

	//PURPOSE:	Process Plan data
	//IMPORT:	None
	//EXPORT:	None
	public void process() throws InvalidOperationException 
	{
		for( Plan plan : plans ) 
		{
			if( plan.getYear() == currentYear ) //Process plan for current year
			{
				pCompany.buySellTransaction( plan, properties );
			}
		}
	}
}