//FILE:             PlanIO.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:          A class is responsible of reading Plan from a file - It uses Strategy Pattern
//RELATIONSHIP:		Implement IOBehaviour interface for Strategy Pattern

import java.io.*;
import java.util.*;

public class PlanIO implements IOBehaviour
{
	private List<Plan> planList;
	private List<Integer> checkChronologicalArray;

	//PURPOSE:	Reads plan file 
	//IMPORT:	Plan file name
	//EXPORT:	None
	public void performReading( String filename ) throws IOException
	{
		

		String strLine;
		FileInputStream fstream = null;
		Plan plan;
		planList = new ArrayList<Plan>();
		checkChronologicalArray = new ArrayList<Integer>();

		try{

			System.out.println( "===========================" );
			System.out.println( "Reading Plan File..." );

			fstream = new FileInputStream(filename);  //Open the file
			DataInputStream in = new DataInputStream(fstream); //Create a reader to read the stream
			BufferedReader br = new BufferedReader(new InputStreamReader(in)); //To read the stream one line at a time
			
			strLine = br.readLine(); //Get rid of the first line of file which is not required for processing
			
			//Read File Line By Line
			while ( ( strLine = br.readLine() ) != null ) 	
			{
				plan = proccessPlan(strLine);
				planList.add( plan );
				checkChronologicalArray.add( plan.getYear() ); //Add each year into a list for checking
			}
			
			checkValidFile(); //Checks whether events happens in chronological order

			System.out.println( "Finish reading Plan File!" );
			System.out.println( "===========================" );
		
			
		}
		catch ( IOException e ) //Catch IO exception if any
		{
			throw new IOException( "Error in reading Plan File: " + e.getMessage(), e);
		}
		finally //Close file
		{
			if (fstream != null) 
			{
		    
		    	try 
		    	{
		        	fstream.close();
		        } 
		        catch (IOException e1) 
		        {
		        	System.out.println( "Error in file processing: " + e1.getMessage() );
		        }
		    }
			
		}
	
		
	}

	//PURPOSE:	Process each plan from a line using StringTokenizer
	//IMPORT:	Each line from plan file
	//EXPORT:	Returns each plan that has been processed
	private Plan proccessPlan( String strLine )
	{
		String thisToken = null;
		StringTokenizer strTok;
		int numToken, year;
		String propertyName;
		char type;
		Plan plan;

		
		strTok = new StringTokenizer( strLine, "," );
		numToken = strTok.countTokens();

		year = Integer.parseInt( strTok.nextToken() );
		type = ( strTok.nextToken() ).charAt(0);
		propertyName = strTok.nextToken();

		plan = new Plan( year, type, propertyName );
	
		
		return plan;

	}

	//PURPOSE:	Checks plan year happens in chronological order - if next year is greater then previous year
	//IMPORT:	None
	//EXPORT:	None
	public void checkValidFile() throws IOException
	{
		for( int i = 1; i < checkChronologicalArray.size(); i++ )
		{
			if( checkChronologicalArray.get( i - 1 ) > checkChronologicalArray.get( i ) )
			{
				throw new IOException( "Invalid Plan File Format" );
			}	
		}

	}

	//PURPOSE:	Get properties from file
	//IMPORT:	None
	//EXPORT:	List of Properties
	public List<Property> getProcessedProperties() throws InvalidOperationException
	{
		throw new InvalidOperationException( "PlanIO does not return Properties" );
	}

	//PURPOSE:	Get events from file
	//IMPORT:	None
	//EXPORT:	List of Events
	public List<Event> getProcessedEvent() throws InvalidOperationException
	{
		throw new InvalidOperationException( "PlanIO does not return Events" );
	}

	//PURPOSE:	Get plans from file
	//IMPORT:	None
	//EXPORT:	List of Plans
	public List<Plan> getProcessedPlan()
	{
		return planList;
	}

}