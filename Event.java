//FILE:				Event.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A container class that stores and manages Event
//RELATIONSHIP:		None

import java.util.*;
public class Event
{
	//Constant for event types
	public static final String WAGES_INCREASE = "W+";
	public static final String WAGES_DECREASE = "W-";
	public static final String REVENUE_INCREASE = "R+";
	public static final String REVENUE_DECREASE = "R-";
	public static final String MONETARY_VALUE_INCREASE = "V+";
	public static final String MONETARY_VALUE_DECREASE = "V-";

	//Constant for mutiplier
	public static final double INCREASE_MULTIPLY = 1.05;
	public static final double DECREASE_MULTIPLY = 0.95;

	private int year;
	private String eventType;
	private String propertyName;

	//Alternate Constructor
	public Event( int inYear, String inEventType, String inPropertyName )
	{
		setYear( inYear );
		setEventType( inEventType );
		setProperty( inPropertyName );
	}

	//Mutator - set year
	private void setYear( int inYear )
	{
		if( inYear < 0 )
		{
			throw new IllegalArgumentException( "Event year invalid" );
		}
		else 
		{
			year = inYear;
		}
	}

	//Mutator - set event type
	private void setEventType( String inEventType )
	{
		if( !( inEventType.equals( WAGES_INCREASE ) ) && !( inEventType.equals( WAGES_DECREASE ) ) && !( inEventType.equals( REVENUE_INCREASE ) ) && !( inEventType.equals( REVENUE_DECREASE ) ) && !( inEventType.equals( MONETARY_VALUE_INCREASE ) ) && !( inEventType.equals( MONETARY_VALUE_DECREASE ) ) )
		{
			throw new IllegalArgumentException( "Event type invalid" );
		}
		else
		{
			eventType = inEventType;
		}
		
	}

	//Mutator - set property affected by the event
	private void setProperty( String inPropertyName )
	{
		propertyName = inPropertyName;
	}

	//Accessor - get year
	public int getYear()
	{
		return year;
	}

	//Accessor - get property affected by the event
	public String getPropertyName()
	{
		return propertyName;
	}

	//PURPOSE:	Decides which type of event operation to take place - throws InvalidOperationException
	//IMPORT:	List of properties
	//EXPORT:	None
	public void eventAction( List<Property> properties ) throws InvalidOperationException
	{
		switch ( eventType )
		{
			case WAGES_INCREASE :
									increaseWages( properties );
									break;
			case WAGES_DECREASE :
									decreaseWages( properties );
									break;
			case REVENUE_INCREASE :
									increaseRevenue( properties );
									break;
			case REVENUE_DECREASE :
									decreaseRevenue( properties );
									break;
			case MONETARY_VALUE_INCREASE :
									increaseMonetaryValue( properties );
									break;
			case MONETARY_VALUE_DECREASE :
									decreaseMonetaryValue( properties );
									break;

		}
	}

	//PURPOSE:	Perform wages increases operation for all business unit and checks whether the affected property existed 
	//IMPORT:	List of properties
	//EXPORT:	None
	private void increaseWages( List<Property> properties ) throws InvalidOperationException
	{
		double wages, newWages;
		int check = 0;

		for( Property p : properties )
		{
			if( p instanceof Business )
			{
				wages = ( (Business)p ).getWages();
				newWages = wages * INCREASE_MULTIPLY;
				( (Business)p ).setWages(  newWages );
				p.calcProfit(); //Calculate the profit for particular business each time
				check++;
			}
		}

		checkNameMatch( check, WAGES_INCREASE );
	}

	//PURPOSE:	Perform wages decreases operation for all business unit and checks whether the affected property existed
	//IMPORT:	List of properties
	//EXPORT:	None
	private void decreaseWages( List<Property> properties ) throws InvalidOperationException
	{
		double wages, newWages;
		int check = 0;

		for( Property p : properties )
		{
			if( p instanceof Business )
			{
				wages = ( (Business)p ).getWages();
				newWages = wages * DECREASE_MULTIPLY;
				( (Business)p ).setWages(  newWages );
				p.calcProfit(); //Calculate the profit for particular business each time
				check++;
			}
		}

		checkNameMatch( check, WAGES_DECREASE );
	}

	//PURPOSE:	Perform revenue increases operation for specific business unit and checks whether the affected property existed
	//IMPORT:	List of properties
	//EXPORT:	None
	private void increaseRevenue( List<Property> properties  ) throws InvalidOperationException
	{
		double revenue, newRevenue;
		int check = 0;

		for( Property p : properties )
		{
			if( p instanceof Business )
			{
				if( ( p.getName() ).equals( getPropertyName() ) )
				{
					revenue = ( (Business)p ).getRevenue();
					newRevenue = revenue * INCREASE_MULTIPLY;
					( (Business)p ).setRevenue( newRevenue );
					p.calcProfit(); //Calculate the profit for particular business each time
					check++;
				}
				
			}
		}

		checkNameMatch( check, REVENUE_INCREASE );
	}

	//PURPOSE:	Perform revenue decreases operation for specific business unit and checks whether the affected property existed 
	//EXPORT:	None
	private void decreaseRevenue( List<Property> properties  ) throws InvalidOperationException
	{
		double revenue, newRevenue;
		int check = 0;

		for( Property p : properties )
		{
			if( p instanceof Business )
			{
				if( ( p.getName() ).equals( getPropertyName() ) )
				{
					revenue = ( (Business)p ).getRevenue();
					newRevenue = revenue * DECREASE_MULTIPLY;
					( (Business)p ).setRevenue( newRevenue );
					p.calcProfit(); //Calculate the profit for particular business each time
					check++;
				}
				
			}
		}

		checkNameMatch( check, REVENUE_DECREASE );
	}

	//PURPOSE:	Perform monetary value increases operation for specific property (company or business unit) and checks whether the affected property existed
	//IMPORT:	List of properties
	//EXPORT:	None
	private void increaseMonetaryValue( List<Property> properties ) throws InvalidOperationException
	{
		double monetaryValue;
		int check = 0;

		for( Property p : properties )
		{
			if( ( p.getName() ).equals( getPropertyName() ) )
			{
				monetaryValue = p.getMonetaryValue();
				p.setMonetaryValue(  monetaryValue * INCREASE_MULTIPLY );
				check++;
			}
				
		}

		checkNameMatch( check, MONETARY_VALUE_INCREASE );
	}

	//PURPOSE:	Perform monetary value decreases operation for specific property (company or business unit) and checks whether the affected property existed
	//IMPORT:	List of properties
	//EXPORT:	None
	private void decreaseMonetaryValue( List<Property> properties ) throws InvalidOperationException
	{
		double monetaryValue;
		int check = 0;

		for( Property p : properties )
		{
			if( ( p.getName() ).equals( getPropertyName() ) )
			{
				monetaryValue = p.getMonetaryValue();
				p.setMonetaryValue(  monetaryValue * DECREASE_MULTIPLY );
				check++;
			}
				
		}

		checkNameMatch( check, MONETARY_VALUE_DECREASE );
	}

	//PURPOSE:	Checks whether the affected property existed - throws InvalidOperationException
	//IMPORT:	An int for check and also the event type for better exception message
	//EXPORT:	None	
	private void checkNameMatch( int check, String eventType ) throws InvalidOperationException
	{
		if( check == 0 )
		{
			throw new InvalidOperationException( "No property name is found to be matched in the event: " + eventType );
		}
	}

	//PURPOSE:	Returns details of an event
	//IMPORT:	None
	//EXPORT:	Event's details in String
	public String toString()
	{
		return new String( "Event Year: " + year + " - Affected Name: " + propertyName + " - Event Type: " + eventType ); 
	}

}