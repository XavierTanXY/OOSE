//FILE:             EventIO.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:          A class is responsible of reading Event File - It uses Strategy Pattern
//RELATIONSHIP:		Implement IOBehaviour interface for Strategy Pattern

import java.io.*;
import java.util.*;

public class EventIO implements IOBehaviour
{
	public static final String EMPTY = "";

	private List<Event> eventList;
	private List<Integer> checkChronologicalArray;

	//PURPOSE:	Reads event file 
	//IMPORT:	Event file name
	//EXPORT:	None
	public void performReading( String filename ) throws IOException
	{
		String strLine;
		FileInputStream fstream = null;
		Event event; 
		eventList = new ArrayList<Event>();
		checkChronologicalArray = new ArrayList<Integer>();


		try{

			System.out.println( "===========================" );
			System.out.println( "Reading Event File..." );

			fstream = new FileInputStream(filename);  //Open the file
			DataInputStream in = new DataInputStream(fstream); //Create a reader to read the stream
			BufferedReader br = new BufferedReader(new InputStreamReader(in)); //To read the stream one line at a time
			
			strLine = br.readLine(); //Get rid of the first line of file which is not required for processing

			//Read File Line By Line
			while ( ( strLine = br.readLine() ) != null ) 	
			{
				event = proccessEvent(strLine);
				eventList.add( event );
				checkChronologicalArray.add( event.getYear() ); //Add each year into a list for checking
			}

			checkValidFile(); //Checks whether events happens in chronological order

			System.out.println( "Finish reading Event File!" );
			System.out.println( "===========================" );
		
			
		}
		catch ( IOException e ) //Catch IO exception and rethrow
		{
			throw new IOException( "Error in reading Event File: " + e.getMessage(), e );
		}
		catch ( IllegalArgumentException e2 ) //Catch IO exception and rethrow IO
		{
				throw new IOException( "Error in Property File: " + e2.getMessage() , e2);
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

	//PURPOSE:	Process each event from a line using StringTokenizer
	//IMPORT:	Each line from event file
	//EXPORT:	Returns each event that has been processed
	private Event proccessEvent( String strLine )
	{
		String thisToken = null;
		StringTokenizer strTok;
		int numToken, year;
		String type, propertyName;
		Event event = null;
		
		strTok = new StringTokenizer( strLine, "," );

		numToken = strTok.countTokens();

		year = Integer.parseInt( strTok.nextToken() );
		type = strTok.nextToken();

		switch( numToken )
		{
			case 2:
					event = new Event( year, type, EMPTY );
					break;
			case 3:
					propertyName = strTok.nextToken();
					event = new Event( year, type, propertyName );
					break;
		}

		return event;

	}

	//PURPOSE:	Checks event year happens in chronological order - if next year is greater then previous year
	//IMPORT:	None
	//EXPORT:	None
	public void checkValidFile() throws IOException
	{
		for( int i = 1; i < checkChronologicalArray.size(); i++ )
		{
			if( checkChronologicalArray.get( i - 1 ) > checkChronologicalArray.get( i ) )
			{
				throw new IOException( "Invalid Event File Format" );
			}	
		}

	}
	
	//PURPOSE:	Get properties from file
	//IMPORT:	None
	//EXPORT:	List of Properties
	public List<Property> getProcessedProperties() throws InvalidOperationException
	{
		throw new InvalidOperationException( "EventIO does not return Properties" );
	}

	//PURPOSE:	Get events from file
	//IMPORT:	None
	//EXPORT:	List of Events
	public List<Event> getProcessedEvent()
	{
		return eventList;
	}	

	//PURPOSE:	Get plans from file
	//IMPORT:	None
	//EXPORT:	List of Plans
	public List<Plan> getProcessedPlan() throws InvalidOperationException
	{
		throw new InvalidOperationException( "EventIO does not return Plans" );
	}
	


}