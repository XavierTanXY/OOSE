//FILE:             FileIO.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Object Oriented Software Engineering (COMP2003)
//PURPOSE:          A class is responsible of reading from a file - It serves as a context for Strategy Pattern for IOBehaviour
//RELATIONSHIP:		None

import java.io.*;
import java.util.*;

public class FileIO
{
	
	private IOBehaviour ioBehaviour;

	//Alternate Constructor
	public FileIO( IOBehaviour io )
	{
		ioBehaviour = io;
	}

	//Mutator - set reading type
	public void setReadingType( IOBehaviour io )
	{
		ioBehaviour = io;
	}
	
	//PURPOSE:	Perform reading from a file
	//IMPORT:	File name
	//EXPORT:	None
	public void read( String filename ) throws IOException
	{
		if( filename == null || filename.equals("") )
		{
			throw new IllegalArgumentException( "File name is invalid" );
		}
		else
		{
			ioBehaviour.performReading( filename );
		}
	}

	//PURPOSE:	Get properties from file
	//IMPORT:	None
	//EXPORT:	List of Properties
	public List<Property> getProperties() throws InvalidOperationException
	{
		return ioBehaviour.getProcessedProperties();
	}

	//PURPOSE:	Get events from file
	//IMPORT:	None
	//EXPORT:	List of Events
	public List<Event> getEvent() throws InvalidOperationException
	{
		return ioBehaviour.getProcessedEvent();
	}

	//PURPOSE:	Get plans from file
	//IMPORT:	None
	//EXPORT:	List of Plans
	public List<Plan> getPlan() throws InvalidOperationException
	{
		return ioBehaviour.getProcessedPlan();
	}

}
