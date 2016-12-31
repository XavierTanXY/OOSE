//FILE:				TrainingSystemManager.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:			A class that is responsible for processing the whole program
//RELATIONSHIP:		None

import java.io.*;
import java.util.*;

public class TrainingSystemManager
{
	//PURPOSE:	Runs the whole program
	//IMPORT:	Property File, Event File, Plan File, Start Year, End Year
	//EXPORT:	None
	public static void run( String propertyFile, String eventFile, String planFile, String startYear, String endYear ) 
	{
		FileIO file;
		List<Property> properties;
		List<Event> events;
		List<Plan> plans;

		Property firstCompany;
		PrimaryCompany pCompany;

		YearlyTrainingData data;
		YearlyEventData eventOb;
		YearlyProfitData profitOb; 
		YearlyPlanData planOB;
		YearlyInterestData interestOb; 


		int sYear = Integer.parseInt( startYear );
		int eYear = Integer.parseInt( endYear );

		try{
			
			if( sYear < 0 || eYear < 0 || sYear > eYear ) //Checks valid year
			{
				throw new IllegalArgumentException( "Invalid input of years" );
			}
			else
			{
				//First read Property file and get Properties
				file = new FileIO( new PropertyIO() );
				file.read( propertyFile );
				properties = file.getProperties();

				//Second change reading type to EventIO and get Events
				file.setReadingType( new EventIO() );
				file.read( eventFile );
				events = file.getEvent();
				
				//Third change reading type to PlanIO and get Plan
				file.setReadingType( new PlanIO() );
				file.read( planFile );
				plans = file.getPlan();

				//Get Primary Company from List of Properties that is returned from Property File
				firstCompany = getPrimaryCompany( properties );
				pCompany = new PrimaryCompany( firstCompany );
				
				//Process data according to this order: Event, Profit, Plan, Interest
				data = new YearlyTrainingData();
				eventOb = new YearlyEventData( data );
				profitOb = new YearlyProfitData( data );
				planOB = new YearlyPlanData( data );
				interestOb = new YearlyInterestData( data );

				

				for( int i = sYear; i <= eYear; i++ ) //Run through every year
				{

					System.out.println();
					System.out.println( "System Outpus: Primary Company: " + pCompany.getName() + " - Previous Year: " + ( i - 1 ) + " - Current Year: " + i );
					System.out.println("******************************************************************************************************************************");
			
					printOutputs( properties );
					data.setChanges( events, properties, i, plans, pCompany ); //Year is the only that changes

					System.out.println("******************************************************************************************************************************");

				}

			}
			
		}
		catch( IllegalArgumentException e )
		{
			System.out.println( "Errors in: " + e.getMessage() );
		}
		catch( InvalidOperationException e1 )
		{
			System.out.println( "Error in invalid operation: " + e1.getMessage() );
		}
		catch( IOException e2)
		{
			System.out.println( "Error is file processing: " + e2.getMessage() );
		}
		catch( Exception e3)
		{
			System.out.println( "Exception: " + e3.getMessage() );
		}



	}

	//PURPOSE:	Get primary company from the List of Properties
	//IMPORT:	List of Properties
	//EXPORT:	Primary Company
	private static Property getPrimaryCompany( List<Property> properties )
	{
		List<Property> tempProperties = new ArrayList<Property>();
		Property firstCompany = null;

		for( Property p : properties )
		{
			if( p instanceof Company )
			{
				tempProperties.add( p );
			}
		}

		if( tempProperties.size() == 0 )
		{
			throw new IllegalArgumentException( "There is no Primary Company" );
		}
		else 
		{
			firstCompany = tempProperties.get(0);
		}

		
		return firstCompany;
	}

	//PURPOSE:	Display details on the screen for each year
	//IMPORT:	List of Properties
	//EXPORT:	None
	private static void printOutputs( List<Property> properties ) throws InvalidOperationException
	{
	
		for( Property p : properties )
		{
			if( p instanceof Company ) //Only prints Company details
			{
				System.out.println( p.toString() );
			}
				
		}
	}
}