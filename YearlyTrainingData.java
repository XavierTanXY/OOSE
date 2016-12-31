//FILE:				YearlyTrainingData.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A class that is responsible for yearly data which includes everything
//RELATIONSHIP:		Subject Interface

import java.util.*;
public class YearlyTrainingData implements Subject 
{

	private List<Observer> observers;
	private List<Event> events;
	private List<Property> properties;
	private int currentYear;
	private List<Plan> plans;
	private PrimaryCompany pCompany;

	//Default Constructor
	public YearlyTrainingData() 
	{
		observers = new ArrayList<Observer>();
	} 

	//PURPOSE:	Register Observer
	//IMPORT:	Observer
	//EXPORT:	None
	public void registerObserver( Observer o ) 
	{
		observers.add( o );
	}

	//PURPOSE:	Notify all the observers for changes
	//IMPORT:	None
	//EXPORT:	None
	public void notifyObservers() throws InvalidOperationException
	{
		for( Observer ob : observers) 
		{
			ob.update( events, properties, currentYear, plans, pCompany );
		}
	}

	//PURPOSE:	Call notifyObservers() for changes
	//IMPORT:	None
	//EXPORT:	None
	private void dataChanged() throws InvalidOperationException
	{
		notifyObservers();
	}

	//PURPOSE:	Changes happen and call dataChanged() for further operation
	//IMPORT:	List of events, List of affected properties, currentYear, List of plans, Primary Company 
	//EXPORT:	None
	public void setChanges( List<Event> events, List<Property> properties, int currentYear, List<Plan> plans, PrimaryCompany pCompany ) throws InvalidOperationException
	{
		this.events = events;
		this.properties = properties;
		this.currentYear = currentYear;
		this.plans = plans;
		this.pCompany = pCompany;

		dataChanged();
	}
}