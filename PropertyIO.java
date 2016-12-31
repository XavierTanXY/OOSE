//FILE:             PropertyIO.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:          A class is responsible of reading Property File - It uses Strategy Pattern
//RELATIONSHIP:		Implement IOBehaviour interface for Strategy Pattern

import java.io.*;
import java.util.*;

public class PropertyIO implements IOBehaviour
{
	private List<Property> propertiesList;

	//PURPOSE:	Reads property file 
	//IMPORT:	Property file name
	//EXPORT:	None
	public void performReading( String filename ) throws IOException
	{
		
		String strLine;
		FileInputStream fstream = null;
		Property returnProperty;
		propertiesList = new ArrayList<Property>();
		List<String> names = new ArrayList<String>();
		int check = 0;
	
		try{
				System.out.println( "===========================" );
				System.out.println( "Reading Property File..." );

				fstream = new FileInputStream(filename);  //Open the file
				DataInputStream in = new DataInputStream(fstream); //Create a reader to read the stream
				BufferedReader br = new BufferedReader(new InputStreamReader(in)); //To read the stream one line at a time
				
				strLine = br.readLine(); //Get rid of the first line of file which is not required for processing

				//Read File Line By Line
				while ( ( strLine = br.readLine() ) != null ) 	
				{
					returnProperty = processProperty( strLine ); //Returns a property from the line that has been read
					checkDuplicateName( returnProperty ); //Check for duplicate propert name in the file
					propertiesList.add( returnProperty ); //Stores property into a list

					String ownerName = returnProperty.getOwner().getName();
			
					check = 0; //reset check when returning new property
					
					if( !( ownerName ).equals("Unamed Owner") ) //Checks whether the owner of a property exists
					//if( !( returnProperty.checkUnamedOwner() ) )
					{
						for( Property property : propertiesList )
						{
							if( ownerName.equals( property.getName() ) ) //If exists, set as owner
							{
								returnProperty.setOwner( property );
								check++;
							}
							
						}

						if( check == 0 ) //If owner does not exists, throw Exception
						{
							throw new IOException( "Invalid Property File Format" );
						}
						
					}	

				}
				
				System.out.println( "Finish reading Property File!" );
				System.out.println( "===========================" );

		}

		catch ( IOException e ) //Catch IO exception if any
		{
				throw new IOException( "Error in reading Property File: " + e.getMessage() , e);
		}
		catch ( IllegalArgumentException e2 )
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

	//PURPOSE:	Process each property from a line using StringTokenizer
	//IMPORT:	Each line from property file
	//EXPORT:	Returns each property that has been processed
	private Property processProperty( String strLine )
	{
		String thisToken = null;
		StringTokenizer strTok;

		String name, type, owner, nextTok;
		double monetaryValue, revenue, wages;
		int numToken;
		Property returnProperty;
	


		strTok = new StringTokenizer( strLine, "," );
		numToken = strTok.countTokens();
		
		name = strTok.nextToken();
		type = strTok.nextToken();

		switch( numToken )
		{
			case 3:
						if( !checkValidCreationType( type, "C") ) //Checks whether the number of tokens is matched with the Property type 
						{
							throw new IllegalArgumentException( "Invalid Creation of Company" );
						} 
						else
						{
							owner = "Unamed Owner";
							monetaryValue = Double.valueOf( strTok.nextToken() );

							returnProperty = new Company( name, monetaryValue, new Company("Unamed Owner", 0.0, null) );
						}
		
					break;
			case 4:

					if( !checkValidCreationType( type, "C") ) //Checks whether the number of tokens is matched with the Property type 
					{
						throw new IllegalArgumentException( "Invalid Creation of Company" );
					} 
					else
					{
						owner = strTok.nextToken();
						monetaryValue = Double.valueOf( strTok.nextToken() );

						returnProperty = new Company( name, monetaryValue, new Company(owner, 0.0, null) );
					}
					
					break;
			case 5:

					if( !checkValidCreationType( type, "B") ) //Checks whether the number of tokens is matched with the Property type 
					{
						throw new IllegalArgumentException( "Invalid Creation of Business" );
					} 
					else
					{
						owner = "Unamed Owner";
						monetaryValue = Double.valueOf( strTok.nextToken() );
						revenue = Double.valueOf( strTok.nextToken() );
						wages = Double.valueOf( strTok.nextToken() );
						
						returnProperty = new Business( name, monetaryValue, revenue, wages, new Company("Unamed Owner", 0.0, null)  );
					}
					
					break;
			default:

					if( !checkValidCreationType( type, "B") ) //Checks whether the number of tokens is matched with the Property type 
					{
						throw new IllegalArgumentException( "Invalid Creation of Business" );
					} 
					else
					{

						owner = strTok.nextToken();
						monetaryValue = Double.valueOf( strTok.nextToken() );
						revenue = Double.valueOf( strTok.nextToken() );
						wages = Double.valueOf( strTok.nextToken() );

						returnProperty = new Business( name, monetaryValue, revenue, wages, new Company(owner, 0.0, null)  );

					}

					break;
		}

		
		return returnProperty;
		
	}

	//PURPOSE:	Checks whether type token from file is matched with the actual Propert type 
	//IMPORT:	Type token from file, property actual type
	//EXPORT:	Boolean valid
	private boolean checkValidCreationType( String typeToken, String type )
	{
		boolean valid;

		if( !typeToken.equals( type ) )
		{
			valid = false;
		}
		else
		{
			valid = true;
		}

		return valid;
	}

	//PURPOSE:	Checks duplicate property name from the file against the properties that have been read
	//IMPORT:	A property from the file line
	//EXPORT:	None
	private void checkDuplicateName( Property returnProperty )
	{
		for( Property property : propertiesList )
		{
			if( ( returnProperty.getName() ).equals( property.getName() ) )
			{
				throw new IllegalArgumentException( "Duplicate Property Name is found" );
			}
		}
	}

	//PURPOSE:	Get properties from file
	//IMPORT:	None
	//EXPORT:	List of Properties
	public List<Property> getProcessedProperties()
	{
		return propertiesList;
	}

	//PURPOSE:	Get events from file
	//IMPORT:	None
	//EXPORT:	List of Events
	public List<Event> getProcessedEvent() throws InvalidOperationException
	{
		throw new InvalidOperationException( "PropertyIO does not return Events" );
	}

	//PURPOSE:	Get plans from file
	//IMPORT:	None
	//EXPORT:	List of Plans
	public List<Plan> getProcessedPlan() throws InvalidOperationException
	{
		throw new InvalidOperationException( "PropertyIO does not return Plans" );
	}



}